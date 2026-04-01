package com.samsung.android.sum.core.channel;

import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.functional.PlaceHolder;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BufferChannelHolder implements PlaceHolder<BufferChannel>, BufferChannel {
    private static final String TAG = SLog.tagOf(BufferChannelHolder.class);
    private BufferChannel bufferChannel;

    public void cancel() {
        BufferChannel bufferChannel2 = this.bufferChannel;
        if (bufferChannel2 != null) {
            bufferChannel2.cancel();
        }
    }

    public void close() {
        BufferChannel bufferChannel2 = this.bufferChannel;
        if (bufferChannel2 != null) {
            bufferChannel2.close();
        }
    }

    public boolean isClosedForReceive() {
        return ((Boolean) Optional.ofNullable(this.bufferChannel).map(new b(4)).orElse(Boolean.FALSE)).booleanValue();
    }

    public boolean isClosedForSend() {
        return ((Boolean) Optional.ofNullable(this.bufferChannel).map(new b(3)).orElse(Boolean.FALSE)).booleanValue();
    }

    public boolean isEmpty() {
        if (this.bufferChannel == null) {
            return true;
        }
        return false;
    }

    public void put(BufferChannel bufferChannel2) {
        String str = TAG;
        SLog.d(str, "put channel: " + this.bufferChannel + " -> " + bufferChannel2);
        BufferChannel bufferChannel3 = this.bufferChannel;
        if (bufferChannel3 != null) {
            bufferChannel3.close();
        }
        this.bufferChannel = bufferChannel2;
    }

    public MediaBuffer receive() {
        return (MediaBuffer) Optional.ofNullable(this.bufferChannel).map(new b(2)).orElse((Object) null);
    }

    public BufferChannel reset() {
        String str = TAG;
        SLog.d(str, "reset: " + this.bufferChannel);
        BufferChannel bufferChannel2 = this.bufferChannel;
        this.bufferChannel = null;
        return bufferChannel2;
    }

    public void send(MediaBuffer mediaBuffer) {
        BufferChannel bufferChannel2 = this.bufferChannel;
        if (bufferChannel2 != null) {
            bufferChannel2.send(mediaBuffer);
        }
    }
}
