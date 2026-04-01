package com.samsung.android.gallery.support.utils;

import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class X implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f3175a;

    public /* synthetic */ X(int i2) {
        this.f3175a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f3175a) {
            case 0:
                return Logger.decrypt((byte[]) obj);
            default:
                return obj.toString();
        }
    }
}
