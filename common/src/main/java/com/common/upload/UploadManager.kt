package com.common.upload

import com.common.data.dropbox.DropboxAuthManager
import com.common.data.dropbox.DropboxRepository
import com.common.data.dropbox.UploadResult
import android.net.Uri
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/** How the user chose to resolve a filename conflict. */
enum class ConflictResolution { REPLACE, KEEP_BOTH, SKIP }

/**
 * Orchestrates uploading a queue of [UploadItem]s to Dropbox, exposing a single [state] StateFlow
 * the UI observes for progress, conflict prompts and auth prompts.
 *
 * Design notes:
 *  - **Auth-on-demand (D9):** if there is no valid Dropbox session when an upload starts, [state]
 *    flips `needsAuth = true`; the UI shows the sign-in modal and calls [onAuthResult] to resume
 *    (or cancel) the held upload.
 *  - **Conflicts (D3):** mirrors MediaTransferHelper — first attempt uses add + autorename=false;
 *    a 409 conflict suspends and asks the user (Replace / Keep both / Skip), with "apply to all".
 *
 * App-agnostic: lives in `common` so any app can reuse it.
 */
class UploadManager(
    private val repository: DropboxRepository,
    private val authManager: DropboxAuthManager,
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO),
    /**
     * Optional hook invoked whenever an upload fails (per-file error, batch exception, or missing
     * auth). App-agnostic: apps may wire this to their own file logger. Defaults to a no-op so
     * existing callers are unaffected.
     */
    private val logFailure: (message: String, throwable: Throwable?) -> Unit = { _, _ -> }
) {
    data class State(
        val isUploading: Boolean = false,
        val isPreparing: Boolean = false,
        val current: Int = 0,
        val total: Int = 0,
        val fileProgress: Float = 0f,
        /** Batch-wide progress weighted by bytes (0..1): reflects total bytes uploaded across all files. */
        val overallProgress: Float = 0f,
        val conflictFileName: String? = null,
        val applyToAll: Boolean = false,
        val needsAuth: Boolean = false,
        val errorMessage: String? = null,
        val completedMessage: String? = null,
        /**
         * URIs of items that were uploaded successfully and should be deleted from the device
         * (populated only when the batch requested delete-after-upload). The UI trashes them and
         * then calls [clearUploadedUris].
         */
        val uploadedUris: List<Uri> = emptyList(),
        /**
         * URIs of items uploaded successfully in the last run, regardless of delete-after-upload.
         * Used by the background auto-upload worker to learn which items succeeded (for dedup and
         * for background trashing). Not consumed by the interactive UI.
         */
        val lastUploadedUris: List<Uri> = emptyList()
    )

    /** Outcome of one completed batch, returned to the caller instead of being read off [state]. */
    data class BatchResult(
        val uploadedUris: List<Uri> = emptyList(),
        val uploaded: Int = 0,
        val skipped: Int = 0,
        val failed: Int = 0
    )

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    /**
     * Serializes batches process-wide. [state], [cancelled], [bulkResolution] and the byte counters
     * are single-slot shared fields, so two batches running concurrently would clobber each other
     * (progress resets, one batch's completion nulling the other's work, Stop killing both). A second
     * caller waits here instead.
     */
    private val runMutex = Mutex()

    /** True while a batch is actually uploading — callers can show "waiting" instead of progress. */
    val isBatchRunning: Boolean get() = runMutex.isLocked

    private var authDeferred: CompletableDeferred<Boolean>? = null
    private var conflictDeferred: CompletableDeferred<ConflictResolution>? = null
    private var bulkResolution: ConflictResolution? = null

    /** Byte budget for the whole batch and bytes from files already finished — used to weight [State.overallProgress]. */
    private var totalBytes: Long = 1L
    private var completedBytes: Long = 0L

    /** Set by [cancel]; checked between items so cancelling during Phase 1 fully stops the run. */
    @Volatile private var cancelled: Boolean = false

    /** True while an app UI is in the foreground and can show the interactive conflict/auth dialogs. */
    @Volatile private var uiActive: Boolean = false
    /** Background conflict policy (from settings): true = overwrite, false = keep both (autorename). */
    @Volatile private var backgroundOverwrite: Boolean = false

    /** A batch of work, stashed for the [UploadWorker] to pick up (same-process handoff). */
    data class Batch(
        val items: List<UploadItem>,
        val destRoot: String,
        val overwrite: Boolean,
        val deleteAfterUpload: Boolean,
        /**
         * Whether to publish the uploaded URIs on [State.uploadedUris] for the UI to trash.
         *
         * True for the interactive path, where the UI owns deletion. Background workers set this
         * false and trash from their own [BatchResult] instead — otherwise, with the app open, both
         * the worker and the UI would act on the same URIs and the user could get asked to confirm
         * the same deletion twice.
         */
        val delegateTrashToUi: Boolean = true
    )

    /**
     * Batches staged by [prepareBatch] and claimed by [runPending], FIFO. A queue rather than a single
     * slot: the scheduler can enqueue a second [UploadWorker] while the first is still running, and a
     * single slot meant the second `prepareBatch` overwrote the first batch — so one of the two was
     * silently dropped. Guarded by its own lock since producers are UI threads and the consumer is a
     * worker thread.
     */
    private val pendingBatches = ArrayDeque<Batch>()

    /**
     * Called by the UI lifecycle. When the UI goes away, any pending conflict prompt is auto-resolved
     * using the background policy so a detached [UploadWorker] never blocks waiting on a dialog.
     */
    fun setUiActive(active: Boolean) {
        uiActive = active
        if (!active) {
            conflictDeferred?.let { if (!it.isCompleted) it.complete(backgroundResolution()) }
        }
    }

    private fun backgroundResolution(): ConflictResolution =
        if (backgroundOverwrite) ConflictResolution.REPLACE else ConflictResolution.KEEP_BOTH

    /**
     * Stages [items] for upload into [destRoot]. The actual work is performed by [runPending],
     * invoked from the [UploadWorker].
     *
     * Per-batch flags are deliberately NOT reset here — [runBatch] resets them once it actually
     * starts. Resetting at stage time let a newly queued batch wipe a running batch's progress and,
     * worse, flip `cancelled` back to false mid-run, resurrecting a batch the user had just stopped.
     */
    fun prepareBatch(
        items: List<UploadItem>,
        destRoot: String,
        overwrite: Boolean,
        deleteAfterUpload: Boolean = false
    ) {
        synchronized(pendingBatches) {
            pendingBatches.addLast(Batch(items, destRoot, overwrite, deleteAfterUpload))
        }
        // Show "preparing" immediately so the UI reacts on tap — but only when nothing is running,
        // so staging a second batch doesn't stomp the live one's progress.
        if (!runMutex.isLocked) {
            // No progress bar yet — the up-front conflict check (Phase 1) shows only the conflict
            // dialog(s). The progress bar appears in Phase 2 (the actual upload).
            _state.update { State(isPreparing = true, total = items.size, current = 0) }
        }
    }

    /** Claims and runs the oldest staged batch. Safe to call from a WorkManager [UploadWorker]. */
    suspend fun runPending(): BatchResult {
        // Claim it up front: leaving it queued meant a concurrently finishing batch could clear it.
        val batch = synchronized(pendingBatches) { pendingBatches.removeFirstOrNull() }
            ?: return BatchResult()
        return runBatch(batch)
    }

    /**
     * Runs [batch] to completion and returns what happened. Serialized process-wide via [runMutex]:
     * if another batch is already running, this suspends until it finishes rather than corrupting
     * its state.
     *
     * Prefer this over [prepareBatch] + [runPending] for callers that already hold their own item
     * list (e.g. background workers), since the result comes back directly instead of having to be
     * read off the shared [state] afterwards — which attributed one batch's uploads to another.
     */
    suspend fun runBatch(batch: Batch): BatchResult = runMutex.withLock { runBatchLocked(batch) }

    private suspend fun runBatchLocked(batch: Batch): BatchResult {
        // Per-batch state is reset here, inside the lock, where it cannot race a queued batch.
        backgroundOverwrite = batch.overwrite
        bulkResolution = null
        cancelled = false
        totalBytes = batch.items.sumOf { it.size }.coerceAtLeast(1L)
        completedBytes = 0L
        _state.update { State(isPreparing = true, total = batch.items.size, current = 0) }

        var uploaded = 0
        var skipped = 0
        var failed = 0
        val uploadedUris = ArrayList<Uri>()
        fun result() = BatchResult(uploadedUris.toList(), uploaded, skipped, failed)

        try {
            if (!ensureAuth()) {
                logFailure("Upload aborted: sign-in required to upload.", null)
                _state.update { State(errorMessage = "Sign-in required to upload.") }
                return result()
            }

            // ── Phase 1: check every destination for name clashes and resolve them up front ──
            // (only the conflict dialog shows here; no progress bar). resolutions[index] == null
            // means "no conflict"; otherwise it's the user's (or background) choice for that file.
            val resolutions = resolveConflictsUpFront(batch)
            if (cancelled) return result()

            // ── Phase 2: upload with the resolved modes, now showing the progress bar ──
            _state.update { it.copy(isPreparing = false, isUploading = true, current = 0, overallProgress = 0f) }
            for ((index, item) in batch.items.withIndex()) {
                if (cancelled) return result()
                _state.update { it.copy(current = index + 1, fileProgress = 0f) }
                when (uploadResolved(item, batch.destRoot, resolutions[index])) {
                    Outcome.UPLOADED -> { uploaded++; uploadedUris.add(item.uri) }
                    Outcome.SKIPPED -> skipped++
                    Outcome.FAILED -> failed++
                }
                // Count the whole file toward the budget regardless of outcome so the bar reaches 100%.
                completedBytes += item.size
                _state.update {
                    it.copy(overallProgress = (completedBytes.toFloat() / totalBytes).coerceIn(0f, 1f))
                }
            }
            _state.update {
                State(
                    completedMessage = summary(uploaded, skipped, batch.items.size),
                    uploadedUris = if (batch.deleteAfterUpload && batch.delegateTrashToUi) {
                        uploadedUris.toList()
                    } else {
                        emptyList()
                    },
                    lastUploadedUris = uploadedUris.toList()
                )
            }
            return result()
        } catch (e: Exception) {
            logFailure("Upload batch failed: ${e.message ?: "unknown error"}", e)
            _state.update { State(errorMessage = e.message ?: "Upload failed.") }
            return result()
        }
    }

    private enum class Outcome { UPLOADED, SKIPPED, FAILED }

    /**
     * Phase 1: lists existing file names at each distinct destination folder and, for every item whose
     * name already exists, prompts the user (or applies the background policy). Returns a per-index map
     * of the chosen [ConflictResolution] (absent = no conflict → plain add).
     */
    private suspend fun resolveConflictsUpFront(batch: Batch): Map<Int, ConflictResolution> {
        val resolutions = HashMap<Int, ConflictResolution>()
        val dirNameCache = HashMap<String, Set<String>>()
        batch.items.forEachIndexed { index, item ->
            if (cancelled) return resolutions
            val destPath = item.destPath(batch.destRoot)
            val dir = destPath.substringBeforeLast('/', "")
            val existing = dirNameCache.getOrPut(dir) {
                runCatching { repository.listFileNames(dir) }.getOrDefault(emptySet())
            }
            if (existing.contains(item.name.lowercase())) {
                resolutions[index] = bulkResolution ?: awaitConflict(item.name)
            }
        }
        return resolutions
    }

    /** Phase 2: uploads [item] using the pre-resolved [resolution] (null = no conflict → add). */
    private suspend fun uploadResolved(
        item: UploadItem,
        destRoot: String,
        resolution: ConflictResolution?
    ): Outcome {
        if (resolution == ConflictResolution.SKIP) return Outcome.SKIPPED
        val destPath = item.destPath(destRoot)
        val (mode, autorename) = when (resolution) {
            ConflictResolution.REPLACE -> "overwrite" to false
            ConflictResolution.KEEP_BOTH -> "add" to true
            ConflictResolution.SKIP -> return Outcome.SKIPPED
            null -> "add" to false
        }
        var result = repository.uploadFile(
            uri = item.uri,
            destPath = destPath,
            size = item.size,
            mode = mode,
            autorename = autorename,
            onProgress = { written, total -> reportProgress(written, total) }
        )
        // Safety net: if a clash still appears (e.g. added between the check and now), fall back to
        // the background policy silently rather than popping a second dialog mid-progress.
        if (result is UploadResult.Conflict) {
            val fallback = if (backgroundResolution() == ConflictResolution.REPLACE) "overwrite" to false
            else "add" to true
            result = repository.uploadFile(
                item.uri, destPath, item.size, mode = fallback.first, autorename = fallback.second,
                onProgress = { w, t -> reportProgress(w, t) }
            )
        }
        return when (result) {
            is UploadResult.Success -> Outcome.UPLOADED
            is UploadResult.Conflict -> Outcome.SKIPPED
            is UploadResult.Error -> {
                logFailure(
                    "Failed to upload '${item.name}' to $destPath (code ${result.code}): " +
                        (result.message ?: "unknown error"),
                    null
                )
                _state.update { it.copy(errorMessage = "Failed to upload ${item.name}") }
                Outcome.FAILED
            }
        }
    }

    private fun reportProgress(written: Long, total: Long) {
        if (total > 0) {
            val overall = ((completedBytes + written).toFloat() / totalBytes).coerceIn(0f, 1f)
            _state.update {
                it.copy(
                    fileProgress = (written.toFloat() / total).coerceIn(0f, 1f),
                    overallProgress = overall
                )
            }
        }
    }

    // ── Auth gate (D9) ──────────────────────────────────────────────────

    private suspend fun ensureAuth(): Boolean {
        if (authManager.getValidAccessToken() != null) return true
        // Cannot show a sign-in modal without a live UI — fail fast in the background.
        if (!uiActive) return false
        val deferred = CompletableDeferred<Boolean>()
        authDeferred = deferred
        _state.update { it.copy(needsAuth = true) }
        val ok = deferred.await()
        authDeferred = null
        _state.update { it.copy(needsAuth = false) }
        return ok
    }

    /** Called by the UI after the sign-in modal finishes (true = signed in, false = cancelled). */
    fun onAuthResult(success: Boolean) {
        authDeferred?.complete(success)
    }

    // ── Conflict gate (D3) ──────────────────────────────────────────────

    private suspend fun awaitConflict(fileName: String): ConflictResolution {
        // No UI to prompt on (background upload) → apply the user's configured policy.
        if (!uiActive) return backgroundResolution()
        val deferred = CompletableDeferred<ConflictResolution>()
        conflictDeferred = deferred
        _state.update { it.copy(conflictFileName = fileName) }
        val resolution = deferred.await()
        conflictDeferred = null
        _state.update { it.copy(conflictFileName = null) }
        return resolution
    }

    fun resolveConflict(resolution: ConflictResolution) {
        if (_state.value.applyToAll) bulkResolution = resolution
        conflictDeferred?.complete(resolution)
    }

    fun toggleApplyToAll() {
        _state.update { it.copy(applyToAll = !it.applyToAll) }
    }

    // ── Cancellation / dismissal ────────────────────────────────────────

    /**
     * Stops the running batch and discards anything still staged. Note this cancels the *running*
     * batch only in the sense that `cancelled` is checked between items; a worker that has already
     * been enqueued for a later batch must also be cancelled through its scheduler.
     */
    fun cancel() {
        cancelled = true
        conflictDeferred?.complete(ConflictResolution.SKIP)
        authDeferred?.complete(false)
        synchronized(pendingBatches) { pendingBatches.clear() }
        _state.update { State() }
    }

    fun clearCompletion() {
        _state.update { it.copy(completedMessage = null, errorMessage = null) }
    }

    /** Called by the UI once the pending [State.uploadedUris] have been trashed (or the user cancelled). */
    fun clearUploadedUris() {
        _state.update { it.copy(uploadedUris = emptyList()) }
    }

    private fun summary(uploaded: Int, skipped: Int, total: Int): String = buildString {
        append("Uploaded $uploaded of $total")
        if (skipped > 0) append(" • $skipped skipped")
    }
}
