package com.samsung.android.sum.core.buffer;

import java.nio.ByteBuffer;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class E implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4039a;
    public final /* synthetic */ ByteBuffer b;

    public /* synthetic */ E(ByteBuffer byteBuffer, int i2) {
        this.f4039a = i2;
        this.b = byteBuffer;
    }

    public final Object apply(Object obj) {
        switch (this.f4039a) {
            case 0:
                return Long.valueOf(this.b.asLongBuffer().get(((Integer) obj).intValue()));
            case 1:
                return Float.valueOf(this.b.asFloatBuffer().get(((Integer) obj).intValue()));
            case 2:
                return Byte.valueOf(this.b.get(((Integer) obj).intValue()));
            case 3:
                return Short.valueOf(this.b.asShortBuffer().get(((Integer) obj).intValue()));
            default:
                return Integer.valueOf(this.b.asIntBuffer().get(((Integer) obj).intValue()));
        }
    }
}
