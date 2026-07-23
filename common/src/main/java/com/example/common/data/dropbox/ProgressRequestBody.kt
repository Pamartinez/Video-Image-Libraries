package com.example.common.data.dropbox

import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okio.BufferedSink
import java.io.InputStream

/**
 * Streams an [InputStream] as an octet-stream request body, reporting progress as bytes are written.
 * [totalBytes] enables percentage reporting; pass -1 if unknown.
 */
class ProgressRequestBody(
    private val streamProvider: () -> InputStream,
    private val totalBytes: Long,
    private val onProgress: (bytesWritten: Long, total: Long) -> Unit
) : RequestBody() {

    override fun contentType(): MediaType = "application/octet-stream".toMediaType()

    override fun contentLength(): Long = totalBytes

    override fun writeTo(sink: BufferedSink) {
        streamProvider().use { input ->
            val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
            var written = 0L
            var read = input.read(buffer)
            while (read != -1) {
                sink.write(buffer, 0, read)
                written += read
                onProgress(written, totalBytes)
                read = input.read(buffer)
            }
        }
    }

    private companion object {
        const val DEFAULT_BUFFER_SIZE = 16 * 1024
    }
}
