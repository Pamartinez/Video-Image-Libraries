package com.samsung.android.sum.core.buffer;

import com.samsung.android.sum.core.types.ColorFormat;
import com.samsung.android.sum.core.types.ColorSpace;
import java.util.Map;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class G implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4042a;

    public /* synthetic */ G(int i2) {
        this.f4042a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f4042a) {
            case 0:
                return (ColorFormat) ((Map.Entry) obj).getKey();
            default:
                return ColorSpace.of((android.graphics.ColorSpace) obj);
        }
    }
}
