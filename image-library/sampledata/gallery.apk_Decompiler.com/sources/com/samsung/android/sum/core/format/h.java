package com.samsung.android.sum.core.format;

import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ StapleUpdatableMediaFormat e;

    public /* synthetic */ h(StapleUpdatableMediaFormat stapleUpdatableMediaFormat, int i2) {
        this.d = i2;
        this.e = stapleUpdatableMediaFormat;
    }

    public final Object get() {
        int i2 = this.d;
        StapleUpdatableMediaFormat stapleUpdatableMediaFormat = this.e;
        switch (i2) {
            case 0:
                return stapleUpdatableMediaFormat.lambda$getCroppedRect$2();
            default:
                return stapleUpdatableMediaFormat.lambda$getCroppedShape$5();
        }
    }
}
