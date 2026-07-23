package com.gallerytransferlibrary.data.util

/**
 * App-specific file logger for Gallery Transfer. Writes rolling daily log files to
 *   <External Storage>/Documents/GalleryTransfer/logs/
 * Used primarily to record Dropbox transfer failures so they can be inspected from the
 * About screen's "Open Logs Folder" button.
 */
object FileLogger : com.example.common.data.util.FileLogger("GalleryTransfer")
