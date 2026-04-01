package com.samsung.android.sum.core.buffer;

import android.util.Pair;
import com.samsung.android.sum.core.types.DataType;
import java.util.Map;
import java.util.function.Predicate;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class t implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4057a;
    public final /* synthetic */ Object b;

    public /* synthetic */ t(int i2, Object obj) {
        this.f4057a = i2;
        this.b = obj;
    }

    public final boolean test(Object obj) {
        int i2 = this.f4057a;
        Object obj2 = this.b;
        switch (i2) {
            case 0:
                return ((MediaBufferBase) obj2).lambda$addExtra$1((Map.Entry) obj);
            case 1:
                return BufferExtension.lambda$findAvailableUnaryKey$43((Class) obj2, (String) obj);
            case 2:
                return ((Class) ((Pair) obj2).second).isAssignableFrom((Class) ((Pair) obj).first);
            case 3:
                return ((String) obj2).equals((String) obj);
            default:
                return MediaBufferFileWriter.lambda$writeRawImageSingle$3((DataType) obj2, (DataType) obj);
        }
    }
}
