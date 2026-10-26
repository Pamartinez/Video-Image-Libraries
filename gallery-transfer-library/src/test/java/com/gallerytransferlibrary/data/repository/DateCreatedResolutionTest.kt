package com.gallerytransferlibrary.data.repository

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * The DATE_TAKEN → DATE_ADDED → DATE_MODIFIED fallback. The cases that matter are the ones where a
 * column is absent or nonsense, because treating those as real dates would put the item in 1970 and
 * sweep it into every "older than" filter.
 */
class DateCreatedResolutionTest {

    /** 2025-06-15 12:00 UTC. */
    private val nowSec = 1_750_000_000L
    private val takenSec = 1_700_000_000L   // 2023-11-14
    private val addedSec = 1_720_000_000L   // 2024-07-03
    private val modifiedSec = 1_740_000_000L // 2025-02-19

    private fun resolve(taken: Long, added: Long, modified: Long = modifiedSec) =
        MediaRepository.resolveDateCreatedSeconds(taken, added, modified, nowSec)

    @Test
    fun `prefers DATE_TAKEN and converts it from millis to seconds`() {
        assertEquals(takenSec, resolve(taken = takenSec * 1000L, added = addedSec))
    }

    @Test
    fun `falls back to DATE_ADDED when DATE_TAKEN is absent`() {
        // Screenshots, downloads and most videos have no DATE_TAKEN.
        assertEquals(addedSec, resolve(taken = 0L, added = addedSec))
    }

    @Test
    fun `falls back to DATE_MODIFIED when both others are absent`() {
        assertEquals(modifiedSec, resolve(taken = 0L, added = 0L))
    }

    @Test
    fun `rejects a DATE_TAKEN that is really in seconds`() {
        // Some OEMs store seconds in a millis column; read as millis that is 1970, which must not win.
        assertEquals(addedSec, resolve(taken = takenSec, added = addedSec))
    }

    @Test
    fun `rejects a pre-2000 timestamp`() {
        assertEquals(addedSec, resolve(taken = 100_000L * 1000L, added = addedSec))
    }

    @Test
    fun `rejects a future DATE_TAKEN from a wrong camera clock`() {
        val nextYear = (nowSec + 365L * 86_400L) * 1000L
        assertEquals(addedSec, resolve(taken = nextYear, added = addedSec))
    }

    @Test
    fun `allows a DATE_TAKEN slightly ahead of now for clock skew`() {
        val inAnHour = (nowSec + 3600L) * 1000L
        assertEquals(nowSec + 3600L, resolve(taken = inAnHour, added = addedSec))
    }

    @Test
    fun `rejects a future DATE_ADDED too`() {
        val future = nowSec + 365L * 86_400L
        assertEquals(modifiedSec, resolve(taken = 0L, added = future))
    }

    @Test
    fun `returns DATE_MODIFIED unfiltered as the last resort even when implausible`() {
        // Better a wrong-but-present date than 0, which would silently match every age filter.
        assertEquals(5L, resolve(taken = 0L, added = 0L, modified = 5L))
    }
}
