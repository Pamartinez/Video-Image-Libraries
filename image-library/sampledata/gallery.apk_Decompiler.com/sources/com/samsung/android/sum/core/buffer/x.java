package com.samsung.android.sum.core.buffer;

import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class x implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4060a;
    public final /* synthetic */ Object b;

    public /* synthetic */ x(int i2, Object obj) {
        this.f4060a = i2;
        this.b = obj;
    }

    public final Object apply(Object obj) {
        int i2 = this.f4060a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                return MediaBufferBase.lambda$getExtra$2(obj2, (String) obj);
            default:
                return BufferExtension.lambda$doStringfy$42(obj2, (Function) obj);
        }
    }
}
