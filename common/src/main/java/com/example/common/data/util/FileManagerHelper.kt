package com.example.common.data.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.StrictMode
import android.widget.Toast

/**
 * Opens a folder path in the device's file manager app.
 * Samsung "My Files" is targeted first, with a generic fallback.
 */
object FileManagerHelper {

    fun openFolder(ctx: Context, path: String) {
        val savedPolicy = StrictMode.getVmPolicy()
        StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder().build())

        try {
            try {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    setClassName(
                        "com.sec.android.app.myfiles",
                        "com.sec.android.app.myfiles.ui.MainActivity"
                    )
                    setDataAndType(Uri.parse("file://$path"), "resource/folder")
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                ctx.startActivity(intent)
                return
            } catch (_: Exception) {
            }

            try {
                val intent = Intent.createChooser(
                    Intent(Intent.ACTION_VIEW).apply {
                        setDataAndType(Uri.parse("file://$path"), "resource/folder")
                    },
                    "Open Folder"
                )
                ctx.startActivity(intent)
                return
            } catch (_: Exception) {
            }

            Toast.makeText(ctx, "Folder: $path", Toast.LENGTH_LONG).show()
        } finally {
            StrictMode.setVmPolicy(savedPolicy)
        }
    }
}

