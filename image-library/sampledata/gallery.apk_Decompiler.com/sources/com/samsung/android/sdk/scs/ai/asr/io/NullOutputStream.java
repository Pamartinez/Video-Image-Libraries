package com.samsung.android.sdk.scs.ai.asr.io;

import com.samsung.android.sdk.scs.base.utils.Log;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NullOutputStream extends OutputStream {
    private final String TAG = ("NullOutputStream@" + hashCode());
    private volatile boolean closed;

    private void ensureOpen() {
        if (this.closed) {
            throw new IOException("Stream closed");
        }
    }

    public void close() {
        this.closed = true;
        Log.i(this.TAG, "closed");
    }

    public void write(int i2) {
        ensureOpen();
    }

    public void write(byte[] bArr, int i2, int i7) {
        Objects.checkFromIndexSize(i2, i7, bArr.length);
        ensureOpen();
    }
}
