package com.gallerytransferlibrary.data.model

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

/**
 * Covers the calendar arithmetic that fixed-length day maths cannot express. Fixed clock + fixed
 * zone so these never depend on when or where they run.
 */
class AgeCutoffTest {

    private val utc = ZoneId.of("UTC")

    private fun at(y: Int, m: Int, d: Int, hour: Int = 12): Long =
        LocalDateTime.of(y, m, d, hour, 0).toEpochSecond(ZoneOffset.UTC)

    private fun cutoffDate(nowSec: Long, amount: Int, unit: AgeUnit): LocalDate =
        LocalDateTime.ofEpochSecond(
            AgeCutoff.cutoffSeconds(amount, unit, nowSec, utc), 0, ZoneOffset.UTC
        ).toLocalDate()

    @Test
    fun `one month before March 31 clamps to end of February`() {
        // The whole reason for calendar arithmetic: 30 days before Mar 31 is Mar 1, not Feb 28.
        assertEquals(LocalDate.of(2025, 2, 28), cutoffDate(at(2025, 3, 31), 1, AgeUnit.MONTHS))
    }

    @Test
    fun `one month before March 31 in a leap year clamps to February 29`() {
        assertEquals(LocalDate.of(2024, 2, 29), cutoffDate(at(2024, 3, 31), 1, AgeUnit.MONTHS))
    }

    @Test
    fun `one month spans the actual length of the preceding month`() {
        // Feb 28 is 31 days back from Mar 31 but only 28 days back from Mar 28.
        assertEquals(LocalDate.of(2025, 2, 15), cutoffDate(at(2025, 3, 15), 1, AgeUnit.MONTHS))
        assertEquals(LocalDate.of(2025, 6, 15), cutoffDate(at(2025, 7, 15), 1, AgeUnit.MONTHS))
    }

    @Test
    fun `six months crosses a year boundary`() {
        assertEquals(LocalDate.of(2024, 9, 10), cutoffDate(at(2025, 3, 10), 6, AgeUnit.MONTHS))
    }

    @Test
    fun `one year before a leap day clamps to February 28`() {
        assertEquals(LocalDate.of(2023, 2, 28), cutoffDate(at(2024, 2, 29), 1, AgeUnit.YEARS))
    }

    @Test
    fun `one year accounts for the leap day`() {
        val now = at(2025, 1, 1)
        val cutoff = AgeCutoff.cutoffSeconds(1, AgeUnit.YEARS, now, utc)
        // 2024 was a leap year, so a calendar year back is 366 days, not 365.
        assertEquals(366L * 86_400L, now - cutoff)
    }

    @Test
    fun `days are plain multiples in a fixed-offset zone`() {
        val now = at(2025, 5, 20)
        assertEquals(7L * 86_400L, now - AgeCutoff.cutoffSeconds(7, AgeUnit.DAYS, now, utc))
    }

    @Test
    fun `amount of zero yields now`() {
        val now = at(2025, 5, 20)
        assertEquals(now, AgeCutoff.cutoffSeconds(0, AgeUnit.DAYS, now, utc))
    }

    @Test
    fun `negative amount is clamped rather than moving the cutoff into the future`() {
        val now = at(2025, 5, 20)
        assertEquals(now, AgeCutoff.cutoffSeconds(-5, AgeUnit.MONTHS, now, utc))
    }

    @Test
    fun `a DST day is 23 hours, not 24`() {
        // US spring-forward is 2025-03-09 02:00, so going back a day from noon that day crosses it
        // and "1 day ago" is 23 hours of wall-clock, keeping the same local time of day.
        val ny = ZoneId.of("America/New_York")
        val nowSec = LocalDateTime.of(2025, 3, 9, 12, 0).atZone(ny).toEpochSecond()
        val elapsed = nowSec - AgeCutoff.cutoffSeconds(1, AgeUnit.DAYS, nowSec, ny)
        assertEquals(23L * 3600L, elapsed)
    }
}
