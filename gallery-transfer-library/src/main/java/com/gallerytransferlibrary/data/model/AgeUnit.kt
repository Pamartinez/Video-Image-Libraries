package com.gallerytransferlibrary.data.model

import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

/** Unit for an "older than N" age threshold. */
enum class AgeUnit(val id: Int, val label: String, val chronoUnit: ChronoUnit) {
    DAYS(0, "days", ChronoUnit.DAYS),
    MONTHS(1, "months", ChronoUnit.MONTHS),
    YEARS(2, "years", ChronoUnit.YEARS);

    /** Singular/plural label for [amount], e.g. "1 day" / "3 days". */
    fun labelFor(amount: Int): String =
        if (amount == 1) label.removeSuffix("s") else label

    companion object {
        fun fromId(id: Int): AgeUnit = entries.firstOrNull { it.id == id } ?: DAYS
    }
}

/**
 * Single definition of the "older than" cutoff, shared by the bulk-upload screen, the bulk-upload
 * worker and the periodic auto-upload worker. Previously each site recomputed
 * `now - days * 86_400` independently.
 */
object AgeCutoff {

    /**
     * Epoch SECONDS of the instant [amount] [unit]s before [nowSec]. An item is "older than" the
     * threshold when its creation time is strictly less than this.
     *
     * Uses real calendar arithmetic, so "1 month" before Mar 31 is Feb 28/29 and "1 year" accounts
     * for leap days — neither of which `30 * 86_400` / `365 * 86_400` can express. [zone] is
     * injectable so this is testable against a fixed clock.
     */
    fun cutoffSeconds(
        amount: Int,
        unit: AgeUnit,
        nowSec: Long = System.currentTimeMillis() / 1000L,
        zone: ZoneId = ZoneId.systemDefault()
    ): Long = ZonedDateTime.ofInstant(Instant.ofEpochSecond(nowSec), zone)
        .minus(amount.coerceAtLeast(0).toLong(), unit.chronoUnit)
        .toEpochSecond()
}
