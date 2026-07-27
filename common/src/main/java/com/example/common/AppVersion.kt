package com.example.common

/**
 * Single source of truth for the app version shown across all three apps
 * (gallery-transfer, image-library, video-library).
 *
 * Versioning rules (see .github/copilot-instructions.md):
 *  - [BUILD] is a build counter. It is incremented by 1 before every install to a phone.
 *  - On every push to the main branch, [BUILD] resets to 0 and [VERSION] bumps by one
 *    tenth (1.1 -> 1.2, and 1.9 -> 2.0).
 *
 * Displayed as "VERSION.BUILD", e.g. "1.1.0".
 */
object AppVersion {
    const val VERSION = "1.2"
    const val BUILD = 19

    /** Full version string shown in the About screen, e.g. "1.1.0". */
    val displayName: String
        get() = "$VERSION.$BUILD"
}

