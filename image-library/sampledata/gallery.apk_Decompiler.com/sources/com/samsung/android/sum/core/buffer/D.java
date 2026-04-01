package com.samsung.android.sum.core.buffer;

import java.nio.ByteBuffer;
import java.util.function.Supplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class D implements Supplier {
    public final /* synthetic */ int d;
    public final /* synthetic */ ByteBuffer e;

    public /* synthetic */ D(ByteBuffer byteBuffer, int i2) {
        this.d = i2;
        this.e = byteBuffer;
    }

    public final Object get() {
        int i2 = this.d;
        ByteBuffer byteBuffer = this.e;
        switch (i2) {
            case 0:
                return Byte.valueOf(byteBuffer.get());
            case 1:
                return Short.valueOf(byteBuffer.asShortBuffer().get());
            case 2:
                return Integer.valueOf(byteBuffer.asIntBuffer().get());
            case 3:
                return Long.valueOf(byteBuffer.asLongBuffer().get());
            default:
                return Float.valueOf(byteBuffer.asFloatBuffer().get());
        }
    }
}
