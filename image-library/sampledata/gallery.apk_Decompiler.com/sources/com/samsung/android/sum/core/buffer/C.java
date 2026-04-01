package com.samsung.android.sum.core.buffer;

import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ MutableMediaBuffer e;

    public /* synthetic */ C(MutableMediaBuffer mutableMediaBuffer, int i2) {
        this.d = i2;
        this.e = mutableMediaBuffer;
    }

    public final Object get() {
        int i2 = this.d;
        MutableMediaBuffer mutableMediaBuffer = this.e;
        switch (i2) {
            case 0:
                return mutableMediaBuffer.lambda$toString$13();
            case 1:
                return mutableMediaBuffer.lambda$stream$3();
            default:
                return mutableMediaBuffer.getFixedFormat();
        }
    }
}
