package com.samsung.android.sum.core.channel;

import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Supplier {
    public final /* synthetic */ int d;

    public /* synthetic */ h(int i2) {
        this.d = i2;
    }

    public final Object get() {
        switch (this.d) {
            case 0:
                return new IllegalArgumentException();
            default:
                return SurfaceChannelImpl.lambda$new$3();
        }
    }
}
