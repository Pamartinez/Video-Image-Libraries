package com.example.common.util

/**
 * Shared utilities for file path manipulation.
 * Used by both image-library and video-library.
 */
object FilePathUtils {

    /**
     * Extract the folder name from a full file path.
     *
     * Examples:
     * - "/storage/emulated/0/DCIM/Camera/" → "Camera"
     * - "/storage/emulated/0/Pictures/Screenshots" → "Screenshots"
     * - "/sdcard/Download/" → "Download"
     *
     * @param path Full file path (may have trailing slash)
     * @return Folder name (last path component)
     */
    fun destFolderName(path: String): String {
        return path.trimEnd('/').substringAfterLast('/')
    }

    /**
     * Generate a unique group name by appending (2), (3), etc. if the base name exists.
     *
     * Examples:
     * - "My Group" → "My Group" (if unique)
     * - "My Group" → "My Group (2)" (if "My Group" exists)
     * - "My Group" → "My Group (3)" (if "My Group" and "My Group (2)" exist)
     *
     * @param baseName Desired group name
     * @param existingNames Set of names that already exist
     * @return Unique name, possibly with suffix
     */
    fun generateUniqueGroupName(baseName: String, existingNames: Set<String>): String {
        var name = baseName
        var counter = 2
        while (name in existingNames) {
            name = "$baseName ($counter)"
            counter++
        }
        return name
    }
}

