package com.samsung.android.sum.core.filter;

import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class g implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4091a;
    public final /* synthetic */ MediaFilterBase b;

    public /* synthetic */ g(MediaFilterBase mediaFilterBase, int i2) {
        this.f4091a = i2;
        this.b = mediaFilterBase;
    }

    public final Object apply(Object obj) {
        int i2 = this.f4091a;
        MediaFilterBase mediaFilterBase = this.b;
        Enum enumR = (Enum) obj;
        switch (i2) {
            case 0:
                return mediaFilterBase.lambda$addTag$0(enumR);
            default:
                return mediaFilterBase.lambda$getTag$1(enumR);
        }
    }
}
