package com.samsung.android.sum.core.channel;

import com.samsung.android.sum.core.Def;
import com.samsung.android.sum.core.SLog;
import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.buffer.u;
import com.samsung.android.sum.core.functional.BufferSupplier;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BufferSupplyChannel implements BufferChannel, BufferSupplier {
    private static final String TAG = Def.tagOf((Class<?>) BufferSupplyChannel.class);
    private final BufferSupplier bufferSupplier;
    private final BufferChannel channel;

    public BufferSupplyChannel(BufferChannel bufferChannel, BufferSupplier bufferSupplier2) {
        this.channel = bufferChannel;
        this.bufferSupplier = bufferSupplier2;
    }

    public void cancel() {
        this.channel.cancel();
    }

    public void close() {
        this.channel.close();
    }

    public MediaBuffer getBuffer() {
        return (MediaBuffer) Optional.ofNullable(this.bufferSupplier).map(new b(5)).orElseThrow(new u(4));
    }

    public boolean isClosedForReceive() {
        return this.channel.isClosedForReceive();
    }

    public boolean isClosedForSend() {
        return this.channel.isClosedForSend();
    }

    public MediaBuffer receive() {
        return (MediaBuffer) this.channel.receive();
    }

    public void send(MediaBuffer mediaBuffer) {
        String str = TAG;
        SLog.d(str, "send: " + mediaBuffer);
        this.channel.send(mediaBuffer);
    }
}
