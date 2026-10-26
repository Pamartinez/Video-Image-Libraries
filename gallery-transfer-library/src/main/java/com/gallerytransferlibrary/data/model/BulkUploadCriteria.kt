package com.gallerytransferlibrary.data.model

/** Unit for a size threshold. */
enum class SizeUnit(val id: Int, val label: String, val bytes: Long) {
    KB(0, "KB", 1024L),
    MB(1, "MB", 1024L * 1024L),
    GB(2, "GB", 1024L * 1024L * 1024L);

    companion object {
        fun fromId(id: Int): SizeUnit = entries.firstOrNull { it.id == id } ?: MB
    }
}

/**
 * The three conditions of a bulk upload, AND-ed together: media type, minimum size, and minimum
 * age. [matches] is the single definition of the predicate — used both by the live match count on
 * the bulk-upload screen and by the worker that performs the upload, so the number the user sees
 * and the set that gets uploaded can never drift apart.
 */
data class BulkUploadCriteria(
    val mediaType: FilterType = FilterType.ALL,
    val sizeAmount: Int = DEFAULT_AMOUNT,
    val sizeUnit: SizeUnit = SizeUnit.MB,
    val ageAmount: Int = DEFAULT_AMOUNT,
    val ageUnit: AgeUnit = AgeUnit.DAYS
) {
    /** Threshold in bytes; items must be strictly LARGER than this. */
    val minBytes: Long get() = sizeAmount.coerceAtLeast(0).toLong() * sizeUnit.bytes

    /** False when either amount is 0 — an explicit input error, unlike an empty box (see UI). */
    val isValid: Boolean get() = sizeAmount >= 1 && ageAmount >= 1

    fun cutoffSeconds(nowSec: Long = System.currentTimeMillis() / 1000L): Long =
        AgeCutoff.cutoffSeconds(ageAmount, ageUnit, nowSec)

    /**
     * Whether [item] meets all three conditions, against a caller-supplied [cutoffSec].
     *
     * The cutoff is a parameter rather than computed here so the worker can apply the cutoff frozen
     * at enqueue time while the screen applies a live one — through the same predicate.
     */
    fun matches(item: MediaItem, cutoffSec: Long): Boolean {
        val typeOk = when (mediaType) {
            FilterType.ALL -> true
            FilterType.IMAGES -> !item.isVideo
            FilterType.VIDEOS -> item.isVideo
        }
        return typeOk && item.size > minBytes && item.dateCreated < cutoffSec
    }

    /** e.g. "Videos larger than 50 MB, older than 6 months". */
    fun summaryLabel(): String = buildString {
        append(
            when (mediaType) {
                FilterType.ALL -> "Images and videos"
                FilterType.IMAGES -> "Images"
                FilterType.VIDEOS -> "Videos"
            }
        )
        if (sizeAmount >= 1) append(" larger than $sizeAmount ${sizeUnit.label}")
        append(", older than $ageAmount ${ageUnit.labelFor(ageAmount)}")
    }

    companion object {
        const val DEFAULT_AMOUNT = 1
        val DEFAULT = BulkUploadCriteria()

        /**
         * Age-only criteria for Settings → "Back up old items now": every type, any size.
         * [sizeAmount] 0 makes [minBytes] 0, so the size condition only excludes empty files.
         */
        fun olderThanDays(days: Int) = BulkUploadCriteria(
            mediaType = FilterType.ALL,
            sizeAmount = 0,
            ageAmount = days.coerceAtLeast(1),
            ageUnit = AgeUnit.DAYS
        )
    }
}
