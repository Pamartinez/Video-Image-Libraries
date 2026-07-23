package com.gallerytransferlibrary.data.model

/**
 * Fixed set of intervals the user can pick for the background auto-upload worker.
 * [minutes] feeds a WorkManager PeriodicWorkRequest (minimum supported interval is 15 minutes).
 */
enum class AutoUploadFrequency(val id: Int, val label: String, val minutes: Long) {
    EVERY_15_MIN(0, "Every 15 minutes", 15),
    HOURLY(1, "Every hour", 60),
    EVERY_6_HOURS(2, "Every 6 hours", 360),
    EVERY_12_HOURS(3, "Every 12 hours", 720),
    DAILY(4, "Once a day", 1440);

    companion object {
        fun fromId(id: Int): AutoUploadFrequency = entries.firstOrNull { it.id == id } ?: DAILY
    }
}
