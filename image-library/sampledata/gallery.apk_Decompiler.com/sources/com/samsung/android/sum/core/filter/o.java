package com.samsung.android.sum.core.filter;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.channel.BufferChannel;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class o implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ BufferChannel e;

    public /* synthetic */ o(int i2, BufferChannel bufferChannel) {
        this.d = i2;
        this.e = bufferChannel;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        BufferChannel bufferChannel = this.e;
        MediaBuffer mediaBuffer = (MediaBuffer) obj;
        switch (i2) {
            case 0:
                OutStreamPluginFilter.lambda$prepare$2(bufferChannel, mediaBuffer);
                return;
            default:
                bufferChannel.send(mediaBuffer);
                return;
        }
    }
}
