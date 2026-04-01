package com.samsung.android.sum.core.filter;

import com.samsung.android.sum.core.buffer.MutableMediaBuffer;
import com.samsung.android.sum.core.format.MutableMediaFormat;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4080a;
    public final /* synthetic */ MutableMediaBuffer b;

    public /* synthetic */ d(MutableMediaBuffer mutableMediaBuffer, int i2) {
        this.f4080a = i2;
        this.b = mutableMediaBuffer;
    }

    public final Object apply(Object obj) {
        int i2 = this.f4080a;
        MutableMediaBuffer mutableMediaBuffer = this.b;
        MutableMediaFormat mutableMediaFormat = (MutableMediaFormat) obj;
        switch (i2) {
            case 0:
                return ImgpDecorateFilter.lambda$run$2(mutableMediaBuffer, mutableMediaFormat);
            default:
                return ImgpDecorateFilter.lambda$run$3(mutableMediaBuffer, mutableMediaFormat);
        }
    }
}
