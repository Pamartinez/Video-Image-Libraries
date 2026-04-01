package com.samsung.android.sum.core.channel;

import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class n implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ StapleSurfaceWriteChannel e;

    public /* synthetic */ n(StapleSurfaceWriteChannel stapleSurfaceWriteChannel, int i2) {
        this.d = i2;
        this.e = stapleSurfaceWriteChannel;
    }

    public final Object get() {
        int i2 = this.d;
        StapleSurfaceWriteChannel stapleSurfaceWriteChannel = this.e;
        switch (i2) {
            case 0:
                return stapleSurfaceWriteChannel.lambda$isClosedForReceive$7();
            default:
                return stapleSurfaceWriteChannel.lambda$isClosedForSend$6();
        }
    }
}
