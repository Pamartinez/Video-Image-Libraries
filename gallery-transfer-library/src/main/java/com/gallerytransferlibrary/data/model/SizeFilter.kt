package com.gallerytransferlibrary.data.model

private const val MB = 1024L * 1024L

/**
 * Preset size buckets for the flat "Filter" list. Bounds are in bytes; [minBytes] is inclusive,
 * [maxBytes] is exclusive (null = no bound on that side).
 */
enum class SizeFilter(
    val id: Int,
    val label: String,
    private val minBytes: Long?,
    private val maxBytes: Long?
) {
    ALL(0, "All", null, null),
    LT_1MB(1, "< 1 MB", null, 1L * MB),
    MB_1_5(2, "1\u20135 MB", 1L * MB, 5L * MB),
    MB_5_20(3, "5\u201320 MB", 5L * MB, 20L * MB),
    MB_20_100(4, "20\u2013100 MB", 20L * MB, 100L * MB),
    GT_100MB(5, "> 100 MB", 100L * MB, null);

    fun matches(size: Long): Boolean =
        (minBytes == null || size >= minBytes) && (maxBytes == null || size < maxBytes)

    companion object {
        fun fromId(id: Int): SizeFilter = entries.firstOrNull { it.id == id } ?: ALL
    }
}
