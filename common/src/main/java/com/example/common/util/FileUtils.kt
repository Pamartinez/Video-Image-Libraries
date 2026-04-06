package com.example.common.util

/**
 * Shared file and path utilities for both image-library and video-library.
 */
object FileUtils {

    /**
     * Extract the folder name from a full path.
     * Example: "/storage/emulated/0/DCIM/Screenshots/" → "Screenshots"
     */
    fun getFolderNameFromPath(path: String): String {
        return path.trimEnd('/').substringAfterLast('/')
    }

    /**
     * Get the parent directory path from a file path.
     * Example: "/storage/emulated/0/DCIM/Camera/IMG_123.jpg" → "/storage/emulated/0/DCIM/Camera"
     */
    fun getParentPath(filePath: String): String? {
        return java.io.File(filePath).parent
    }
}

