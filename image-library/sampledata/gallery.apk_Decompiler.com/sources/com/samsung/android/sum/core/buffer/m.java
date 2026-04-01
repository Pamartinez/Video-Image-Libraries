package com.samsung.android.sum.core.buffer;

import com.samsung.android.sum.core.buffer.BufferExtension;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class m implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4051a;
    public final /* synthetic */ Object b;

    public /* synthetic */ m(int i2, Object obj) {
        this.f4051a = i2;
        this.b = obj;
    }

    public final Object apply(Object obj) {
        int i2 = this.f4051a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                return (BufferExtension.TransformFunction) ((Map) obj2).get((String) obj);
            case 1:
                return Boolean.valueOf(((HashMap) obj).containsKey((String) obj2));
            case 2:
                return ((MediaBufferFileReader) obj2).lambda$read$7((String) obj);
            default:
                return Boolean.valueOf(((MediaBuffer) obj).containFlags((int[]) obj2));
        }
    }
}
