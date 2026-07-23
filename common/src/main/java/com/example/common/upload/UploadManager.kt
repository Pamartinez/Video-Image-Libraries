package com.example.common.upload

import com.example.common.data.dropbox.DropboxAuthManager
import com.example.common.data.dropbox.DropboxRepository
import com.example.common.data.dropbox.UploadResult
import android.net.Uri
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

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

    private val _state = MutableStateFlow(State())
    val state: StateFlow<State> = _state.asStateFlow()

    private var uploadJob: Job? = null
    private var authDeferred: CompletableDeferred<Boolean>? = null
    private var conflictDeferred: CompletableDeferred<ConflictResolution>? = null
    private var bulkResolution: ConflictResolution? = null

    /** Set by [cancel]; checked between items so cancelling during Phase 1 fully stops the run. */
    @Volatile private var cancelled: Boolean = false

    /** True while an app UI is in the foreground and can show the interactive conflict/auth dialogs. */
    @Volatile private var uiActive: Boolean = false
    /** Background conflict policy (from settings): true = overwrite, false = keep both (autorename). */
    @Volatile private var backgroundOverwrite: Boolean = false

    /** One queued batch, stashed for the [UploadWorker] to pick up (same-process handoff). */
    data class Batch(
        val items: List<UploadItem>,
        val destRoot: String,
        val overwrite: Boolean,
        val deleteAfterUpload: Boolean
    )

    @Volatile
    var pendingBatch: Batch? = null
        private set

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
     * Stages [items] for upload into [destRoot], resetting state so the UI shows progress immediately.
     * The actual work is performed by [runPending], invoked from the [UploadWorker].
     */
    fun prepareBatch(
        items: List<UploadItem>,
        destRoot: String,
        overwrite: Boolean,
        deleteAfterUpload: Boolean = false
    ) {
        pendingBatch = Batch(items, destRoot, overwrite, deleteAfterUpload)
        backgroundOverwrite = overwrite
        bulkResolution = null
        cancelled = false
        // Note: no progress bar yet — we first run the up-front conflict check (Phase 1) which shows
        // only the conflict dialog(s). The progress bar appears in Phase 2 (the actual upload).
        _state.update { State(isPreparing = true, total = items.size, current = 0) }
    }

    /** Runs the staged [pendingBatch]. Safe to call from a WorkManager [UploadWorker]. */
    suspend fun runPending() {
        val batch = pendingBatch ?: return
        try {
            if (!ensureAuth()) {
                logFailure("Upload aborted: sign-in required to upload.", null)
                _state.update { State(errorMessage = "Sign-in required to upload.") }
                return
            }

            // ── Phase 1: check every destination for name clashes and resolve them up front ──
            // (only the conflict dialog shows here; no progress bar). resolutions[index] == null
            // means "no conflict"; otherwise it's the user's (or background) choice for that file.
            val resolutions = resolveConflictsUpFront(batch)
            if (cancelled) return

            // ── Phase 2: upload with the resolved modes, now showing the progress bar ──
            _state.update { it.copy(isPreparing = false, isUploading = true, current = 0) }
            var uploaded = 0
            var skipped = 0
            val uploadedUris = ArrayList<Uri>()
            for ((index, item) in batch.items.withIndex()) {
                if (cancelled) return
                _state.update { it.copy(current = index + 1, fileProgress = 0f) }
                when (uploadResolved(item, batch.destRoot, resolutions[index])) {
                    Outcome.UPLOADED -> { uploaded++; uploadedUris.add(item.uri) }
                    Outcome.SKIPPED -> skipped++
                    Outcome.FAILED -> { /* error already surfaced */ }
                }
            }
            _state.update {
                State(
                    completedMessage = summary(uploaded, skipped, batch.items.size),
                    uploadedUris = if (batch.deleteAfterUpload) uploadedUris else emptyList(),
                    lastUploadedUris = uploadedUris.toList()
                )
            }
        } catch (e: Exception) {
            logFailure("Upload batch failed: ${e.message ?: "unknown error"}", e)
            _state.update { State(errorMessage = e.message ?: "Upload failed.") }
        } finally {
            pendingBatch = null
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
            _state.update { it.copy(fileProgress = (written.toFloat() / total).coerceIn(0f, 1f)) }
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

    fun cancel() {
        cancelled = true
        conflictDeferred?.complete(ConflictResolution.SKIP)
        authDeferred?.complete(false)
        pendingBatch = null
        uploadJob?.cancel()
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
