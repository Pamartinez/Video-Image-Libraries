package com.samsung.android.gallery.support.utils;

import android.graphics.RectF;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class F implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3161a;
    public final /* synthetic */ int b;

    public /* synthetic */ F(int i2, int i7) {
        this.f3161a = i7;
        this.b = i2;
    }

    public final Object apply(Object obj) {
        int i2 = this.f3161a;
        int i7 = this.b;
        switch (i2) {
            case 0:
                return NamedThreadHandler.lambda$new$0(i7, (String) obj);
            default:
                return RectUtils.getRotatedRectFRatio((RectF) obj, i7);
        }
    }
}
