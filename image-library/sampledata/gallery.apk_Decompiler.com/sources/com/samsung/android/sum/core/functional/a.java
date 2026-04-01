package com.samsung.android.sum.core.functional;

import com.samsung.android.sum.core.functional.CountingLatch;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ CountingLatch.CountingDownLatch e;
    public final /* synthetic */ int f;

    public /* synthetic */ a(CountingLatch.CountingDownLatch countingDownLatch, int i2, int i7) {
        this.d = i7;
        this.e = countingDownLatch;
        this.f = i2;
    }

    public final Object get() {
        switch (this.d) {
            case 0:
                return this.e.lambda$await$0(this.f);
            default:
                return this.e.lambda$await$1(this.f);
        }
    }
}
