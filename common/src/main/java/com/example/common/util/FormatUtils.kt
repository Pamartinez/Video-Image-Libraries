package com.example.common.util

import android.content.Context
import java.util.Locale

object FormatUtils {

    fun formatDuration(durationMs: Long): String {
        if (durationMs <= 0) return "00:00"
        val totalSeconds = durationMs / 1000
        val hours   = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60
        val seconds = totalSeconds % 60
        return if (hours > 0) {
            String.format(Locale.US, "%d:%02d:%02d", hours, minutes, seconds)
        } else {
            String.format(Locale.US, "%02d:%02d", minutes, seconds)
        }
    }

    fun formatFileSize(context: Context, sizeBytes: Long): String {
        if (sizeBytes <= 0) return "0 B"
        val units = arrayOf("B", "KB", "MB", "GB", "TB", "PB")
        var size = sizeBytes.toDouble()
        var unitIndex = 0
        while (size >= 1024 && unitIndex < units.size - 1) {
            size /= 1024
            unitIndex++
        }
        return if (unitIndex == 0) {
            String.format(Locale.US, "%.0f %s", size, units[unitIndex])
        } else {
            String.format(Locale.US, "%.1f %s", size, units[unitIndex])
        }
    }

    fun formatDate(epochSeconds: Long): String {
        val sdf = java.text.SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        return sdf.format(java.util.Date(epochSeconds * 1000))
    }

    fun formatDateTime(epochSeconds: Long): String {
        val sdf = java.text.SimpleDateFormat("MMM dd, yyyy h:mm a", Locale.getDefault())
        return sdf.format(java.util.Date(epochSeconds * 1000))
    }

    fun getFileExtension(fileName: String): String {
        val lastDot = fileName.lastIndexOf('.')
        return if (lastDot >= 0) fileName.substring(lastDot) else ""
    }

    fun getFileNameWithoutExtension(fileName: String): String {
        val lastDot = fileName.lastIndexOf('.')
        return if (lastDot >= 0) fileName.substring(0, lastDot) else fileName
    }

    fun getImageCountText(count: Int): String =
        if (count == 1) "1 image" else "$count images"

    fun getVideoCountText(count: Int): String =
        if (count == 1) "1 video" else "$count videos"

    fun getSelectedCountText(count: Int): String = "$count selected"
}

