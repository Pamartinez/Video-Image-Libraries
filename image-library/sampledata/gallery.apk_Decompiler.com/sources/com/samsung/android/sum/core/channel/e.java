package com.samsung.android.sum.core.channel;

import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ DetachableSurfaceReadChannel e;

    public /* synthetic */ e(DetachableSurfaceReadChannel detachableSurfaceReadChannel, int i2) {
        this.d = i2;
        this.e = detachableSurfaceReadChannel;
    }

    public final Object get() {
        int i2 = this.d;
        DetachableSurfaceReadChannel detachableSurfaceReadChannel = this.e;
        switch (i2) {
            case 0:
                return detachableSurfaceReadChannel.lambda$isClosedForReceive$5();
            default:
                return detachableSurfaceReadChannel.lambda$isClosedForSend$4();
        }
    }
}
