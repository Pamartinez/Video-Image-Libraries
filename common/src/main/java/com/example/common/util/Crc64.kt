package com.example.common.util

/**
 * CRC64 hash utility for generating consistent, collision-free cache keys.
 *
 * Based on Samsung Gallery's implementation (analyzed from decompiled code).
 * Uses ECMA-182 polynomial for standard CRC64 computation.
 *
 * **Usage:**
 * ```kotlin
 * val key = Crc64.hash("content://media/video/123")
 * val filename = "$key.jpg"
 * ```
 */
object Crc64 {

    /**
     * CRC64 polynomial (ECMA-182 standard).
     * Same as used by Samsung Gallery for consistent hashing.
     * Value: 0xC96C5795D7870F42 (expressed as negative signed long)
     */
    private const val CRC64_POLY = -3932672073523589438L

    /**
     * Pre-computed lookup table for faster CRC64 computation.
     * Generated once at class initialization.
     */
    private val lookupTable = LongArray(256).apply {
        for (i in 0..255) {
            var crc = i.toLong()
            repeat(8) {
                crc = if ((crc and 1) != 0L) {
                    (crc ushr 1) xor CRC64_POLY
                } else {
                    crc ushr 1
                }
            }
            this[i] = crc
        }
    }

    /**
     * Computes CRC64 hash of a byte array.
     *
     * @param data Input bytes to hash
     * @return 64-bit CRC hash value
     */
    fun hash(data: ByteArray): Long {
        var crc = -1L // Start with all bits set

        for (byte in data) {
            val index = ((crc xor byte.toLong()) and 0xFF).toInt()
            crc = (crc ushr 8) xor lookupTable[index]
        }

        return crc xor -1L // Final XOR
    }

    /**
     * Computes CRC64 hash of a string (UTF-8 encoded).
     *
     * @param text Input string to hash
     * @return 64-bit CRC hash value
     */
    fun hash(text: String): Long {
        return hash(text.toByteArray(Charsets.UTF_8))
    }

    /**
     * Converts CRC64 hash to hexadecimal string for filenames.
     *
     * @param crc CRC64 hash value
     * @return 16-character hexadecimal string (uppercase)
     */
    fun toHexString(crc: Long): String {
        return crc.toULong().toString(16).uppercase().padStart(16, '0')
    }

    /**
     * Convenience function: computes hash and returns hex string directly.
     *
     * @param text Input string to hash
     * @return Hexadecimal string representation of CRC64 hash
     */
    fun hashToHex(text: String): String {
        return toHexString(hash(text))
    }
}



