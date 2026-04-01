package com.samsung.android.sum.core.channel;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import com.samsung.android.sum.core.filter.DecoratePluginInOutStreamFilter;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class r implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ BufferChannel e;

    public /* synthetic */ r(int i2, BufferChannel bufferChannel) {
        this.d = i2;
        this.e = bufferChannel;
    }

    public final Object get() {
        int i2 = this.d;
        BufferChannel bufferChannel = this.e;
        switch (i2) {
            case 0:
                return (MediaBuffer) bufferChannel.receive();
            case 1:
                return DecoratePluginInOutStreamFilter.lambda$run$2(bufferChannel);
            default:
                return DecoratePluginInOutStreamFilter.lambda$run$4(bufferChannel);
        }
    }
}
