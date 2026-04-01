package com.samsung.android.sum.core.channel;

import com.samsung.android.sum.core.buffer.MediaBuffer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class VoidBufferChannel implements BufferChannel {
    public boolean isClosedForReceive() {
        return false;
    }

    public boolean isClosedForSend() {
        return false;
    }

    public void send(MediaBuffer mediaBuffer) {
    }

    public MediaBuffer receive() {
        return MediaBuffer.mutableOf(new Object[0]);
    }

    public void cancel() {
    }

    public void close() {
    }
}
