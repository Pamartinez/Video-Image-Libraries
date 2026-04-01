package com.samsung.android.sum.core.buffer;

import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class p implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4055a;
    public final /* synthetic */ Class b;

    public /* synthetic */ p(Class cls, int i2) {
        this.f4055a = i2;
        this.b = cls;
    }

    public final Object apply(Object obj) {
        int i2 = this.f4055a;
        Class cls = this.b;
        MediaBuffer mediaBuffer = (MediaBuffer) obj;
        switch (i2) {
            case 0:
                return mediaBuffer.copyTo(cls);
            case 1:
                return mediaBuffer.copyTo(cls);
            case 2:
                return mediaBuffer.getTypedData(cls);
            case 3:
                return mediaBuffer.convertTo(cls);
            default:
                return mediaBuffer.copyTo(cls);
        }
    }
}
