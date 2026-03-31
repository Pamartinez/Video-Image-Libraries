package com.example.common.data.util

import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * File logger that writes rolling daily log files to
 *   <External Storage>/Documents/<folderName>/logs/
 *
 * Subclass (or use as an object) with a specific [folderName]:
 *   object FileLogger : com.example.common.data.util.FileLogger("ImageLibrary")
 */
open class FileLogger(private val folderName: String) {

    private val TAG = "FileLogger"
    private val LOGS_DIR = "logs"
    private val MAX_LOG_FILES = 7

    val logDirectory: File
        get() = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),
            "$folderName/$LOGS_DIR"
        )

    private val dateFormat      = SimpleDateFormat("yyyy-MM-dd",             Locale.US)
    private val timestampFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US)

    private fun logFile(): File {
        val dir = logDirectory
        if (!dir.exists()) dir.mkdirs()
        return File(dir, "log_${dateFormat.format(Date())}.txt")
    }

    fun d(tag: String, message: String) {
        Log.d(tag, message)
        write("D", tag, message)
    }

    fun e(tag: String, message: String, throwable: Throwable? = null) {
        Log.e(tag, message, throwable)
        write("E", tag, message, throwable)
    }

    fun w(tag: String, message: String) {
        Log.w(tag, message)
        write("W", tag, message)
    }

    fun i(tag: String, message: String) {
        Log.i(tag, message)
        write("I", tag, message)
    }

    private fun write(level: String, tag: String, message: String, throwable: Throwable? = null) {
        try {
            val file = logFile()
            FileWriter(file, true).use { fw ->
                PrintWriter(fw).use { pw ->
                    pw.println("${timestampFormat.format(Date())} $level/$tag: $message")
                    throwable?.printStackTrace(pw)
                }
            }
            pruneOldLogs()
        } catch (ex: Exception) {
            Log.e(TAG, "Failed to write log to file", ex)
        }
    }

    private fun pruneOldLogs() {
        try {
            val dir = logDirectory
            if (!dir.exists()) return
            val logFiles = dir.listFiles { f -> f.name.startsWith("log_") && f.name.endsWith(".txt") }
                ?.sortedByDescending { it.name } ?: return
            if (logFiles.size > MAX_LOG_FILES) {
                logFiles.drop(MAX_LOG_FILES).forEach { it.delete() }
            }
        } catch (_: Exception) {}
    }
}

