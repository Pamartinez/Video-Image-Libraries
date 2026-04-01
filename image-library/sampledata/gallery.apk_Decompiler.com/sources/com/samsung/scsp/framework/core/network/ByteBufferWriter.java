package com.samsung.scsp.framework.core.network;

import com.samsung.scsp.framework.core.ScspException;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class ByteBufferWriter {
    private static final int BUFFER_SIZE = 327680;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface BufferWriterListener {
        void onWritten(long j2);
    }

    public static void write(OutputStream outputStream, InputStream inputStream, BufferWriterListener bufferWriterListener, long j2) {
        int read;
        try {
            byte[] bArr = new byte[BUFFER_SIZE];
            while (j2 > 0 && (read = inputStream.read(bArr)) != -1) {
                long j3 = (long) read;
                j2 -= j3;
                outputStream.write(bArr, 0, read);
                if (bufferWriterListener != null) {
                    bufferWriterListener.onWritten(j3);
                }
            }
        } catch (Throwable th) {
            throw new ScspException(60000000, "An error occurred while transmitting data to the server.", th);
        }
    }
}
