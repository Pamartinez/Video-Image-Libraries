package com.samsung.android.sum.core.channel;

import com.samsung.android.sum.core.buffer.MediaBuffer;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class p implements Consumer {
    public final /* synthetic */ int d;
    public final /* synthetic */ SurfaceChannelImpl e;

    public /* synthetic */ p(SurfaceChannelImpl surfaceChannelImpl, int i2) {
        this.d = i2;
        this.e = surfaceChannelImpl;
    }

    public final void accept(Object obj) {
        int i2 = this.d;
        SurfaceChannelImpl surfaceChannelImpl = this.e;
        MediaBuffer mediaBuffer = (MediaBuffer) obj;
        switch (i2) {
            case 0:
                surfaceChannelImpl.lambda$new$0(mediaBuffer);
                return;
            default:
                surfaceChannelImpl.writeToSurface(mediaBuffer);
                return;
        }
    }
}
