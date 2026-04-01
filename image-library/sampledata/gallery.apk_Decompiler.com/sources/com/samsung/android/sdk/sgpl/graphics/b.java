package com.samsung.android.sdk.sgpl.graphics;

import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class b implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1675a;

    public /* synthetic */ b(int i2) {
        this.f1675a = i2;
    }

    public final Object apply(Object obj) {
        switch (this.f1675a) {
            case 0:
                return ((ImageCodecImpl) obj).getClass().getSimpleName();
            default:
                return Log.toString(obj);
        }
    }
}
