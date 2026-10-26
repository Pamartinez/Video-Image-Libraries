package com.gallerytransferlibrary.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.gallerytransferlibrary.data.model.AgeUnit
import com.gallerytransferlibrary.data.model.BulkUploadCriteria
import com.gallerytransferlibrary.data.model.FilterType
import com.gallerytransferlibrary.data.model.MediaItem
import com.gallerytransferlibrary.data.model.SizeUnit
import com.gallerytransferlibrary.data.repository.MediaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/** How many items the current criteria match, for the live label on the Bulk upload screen. */
sealed interface MatchState {
    /** Still reading MediaStore — the expensive part, not the filtering. */
    data object Loading : MatchState
    /** One of the amounts is 0, so there is nothing meaningful to count. */
    data object Invalid : MatchState
    /**
     * Holds the matching items, not just a tally, so the count on screen and the previewable list
     * are the same thing and cannot drift. These are references to items already in memory.
     */
    data class Ready(val items: List<MediaItem>, val totalBytes: Long) : MatchState {
        val count: Int get() = items.size
    }
}

/**
 * Backs the Bulk upload screen. Separate from `MediaListViewModel` on purpose: that one already
 * carries 25 state fields and re-filters *and re-sorts* its whole media list on every read of
 * `filteredSortedMedia`, so hanging a second live-filtering pipeline off it would make typing janky.
 */
@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class BulkUploadViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = MediaRepository(app)

    private val _criteria = MutableStateFlow(BulkUploadCriteria.DEFAULT)
    val criteria: StateFlow<BulkUploadCriteria> = _criteria.asStateFlow()

    /**
     * Items the user unticked in the preview. Lives here rather than in the preview composable so it
     * survives navigating back to the criteria and returning — but it is cleared whenever the
     * criteria change, because a different search produces a different set of items and carrying
     * stale exclusions into it would silently drop things the user never looked at.
     */
    private val _excludedKeys = MutableStateFlow<Set<String>>(emptySet())
    val excludedKeys: StateFlow<Set<String>> = _excludedKeys.asStateFlow()

    /** null until the first MediaStore read finishes. */
    private val allMedia = MutableStateFlow<List<MediaItem>?>(null)
    private var loadJob: Job? = null
    private var stale = false

    /**
     * The live match count. Debounced — but note it is the *count* that is debounced, never the text
     * field, which renders straight from [criteria] with no delay. `mapLatest` cancels a superseded
     * count, so a fast typist never queues up work.
     */
    val matchState: StateFlow<MatchState> =
        combine(_criteria, allMedia) { criteria, media -> criteria to media }
            .debounce(COUNT_DEBOUNCE_MS)
            .mapLatest { (criteria, media) ->
                when {
                    media == null -> MatchState.Loading
                    !criteria.isValid -> MatchState.Invalid
                    else -> withContext(Dispatchers.Default) { collectMatches(criteria, media) }
                }
            }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), MatchState.Loading)

    private fun collectMatches(
        criteria: BulkUploadCriteria,
        media: List<MediaItem>
    ): MatchState.Ready {
        val cutoff = criteria.cutoffSeconds()
        val matches = ArrayList<MediaItem>()
        var bytes = 0L
        for (item in media) {
            if (criteria.matches(item, cutoff)) {
                matches += item
                bytes += item.size
            }
        }
        // Newest first, matching how the rest of the app lists media by default.
        matches.sortByDescending { it.dateCreated }
        return MatchState.Ready(matches, bytes)
    }

    /**
     * Called when the screen opens: resets the criteria and kicks off the media scan if it hasn't
     * run yet.
     *
     * The scan is deliberately NOT in `init`. This ViewModel is activity-scoped (so the list
     * survives between visits and reopening is instant), which means an `init` load would make
     * every single app launch pay for a full MediaStore walk even when the user never comes here.
     * Only the criteria are transient, which is what makes the on-screen "Defaults:" note true.
     */
    fun onScreenOpened() {
        _criteria.value = BulkUploadCriteria.DEFAULT
        _excludedKeys.value = emptySet()
        if (loadJob?.isActive == true) return
        if (allMedia.value != null && !stale) return
        stale = false
        loadJob = viewModelScope.launch { allMedia.value = repository.getAllMedia() }
    }

    /**
     * Marks the cached library as out of date — e.g. items were trashed after an upload. Cheaper
     * than rescanning eagerly: the reload happens on the next visit, and costs nothing at all for
     * users who never open this screen.
     */
    fun markStale() {
        stale = true
    }

    fun setMediaType(type: FilterType) = changeCriteria { it.copy(mediaType = type) }
    fun setSizeUnit(unit: SizeUnit) = changeCriteria { it.copy(sizeUnit = unit) }
    fun setAgeUnit(unit: AgeUnit) = changeCriteria { it.copy(ageUnit = unit) }

    /** Blank means "use the default"; anything unparseable (incl. `0`) becomes 0, i.e. invalid. */
    fun setSizeAmount(raw: String) = changeCriteria { it.copy(sizeAmount = parseAmount(raw)) }
    fun setAgeAmount(raw: String) = changeCriteria { it.copy(ageAmount = parseAmount(raw)) }

    /**
     * Every criteria edit goes through here so that re-searching always resets the ticks to
     * all-selected. Routing it through one place means a future setter can't forget to.
     */
    private fun changeCriteria(transform: (BulkUploadCriteria) -> BulkUploadCriteria) {
        val updated = transform(_criteria.value)
        if (updated == _criteria.value) return
        _criteria.value = updated
        _excludedKeys.value = emptySet()
    }

    /** Ticks or unticks one item. Unticked items are left out of the upload. */
    fun toggleExcluded(key: String) {
        _excludedKeys.update { current ->
            if (key in current) current - key else current + key
        }
    }

    fun selectAllMatches() {
        _excludedKeys.value = emptySet()
    }

    private fun parseAmount(raw: String): Int =
        if (raw.isBlank()) BulkUploadCriteria.DEFAULT_AMOUNT else raw.toIntOrNull() ?: 0

    companion object {
        private const val COUNT_DEBOUNCE_MS = 200L
    }
}
