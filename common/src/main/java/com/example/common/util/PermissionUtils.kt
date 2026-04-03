package com.example.common.util

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings

/**
 * Shared permission utilities used by both image-library and video-library.
 */
object PermissionUtils {

    /**
     * Requests MANAGE_APP_ALL_FILES_ACCESS_PERMISSION on Android 11+ (API 30+)
     * if the app does not already have it.
     * Falls back to [Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION] if the
     * app-specific intent is unavailable.
     */
    fun requestManageStorageIfNeeded(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R && !Environment.isExternalStorageManager()) {
            try {
                val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION).apply {
                    data = Uri.parse("package:${activity.packageName}")
                }
                activity.startActivity(intent)
            } catch (_: Exception) {
                activity.startActivity(Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION))
            }
        }
    }
}
