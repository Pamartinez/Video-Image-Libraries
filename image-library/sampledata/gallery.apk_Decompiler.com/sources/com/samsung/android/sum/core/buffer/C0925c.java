package com.samsung.android.sum.core.buffer;

import java.util.function.Supplier;

/* renamed from: com.samsung.android.sum.core.buffer.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class C0925c implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ C0925c(int i2, Object obj) {
        this.d = i2;
        this.e = obj;
    }

    public final Object get() {
        int i2 = this.d;
        Object obj = this.e;
        switch (i2) {
            case 0:
                return BufferExtension.lambda$findAvailableUnaryKey$44((Class) obj);
            case 1:
                return ((DeriveBufferGroup) obj).lambda$toString$2();
            case 2:
                return ((GenericMediaBuffer) obj).lambda$toString$1();
            default:
                return ((MediaBufferGroup) obj).lambda$toString$5();
        }
    }
}
