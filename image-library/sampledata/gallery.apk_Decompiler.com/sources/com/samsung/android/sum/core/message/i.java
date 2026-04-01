package com.samsung.android.sum.core.message;

import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4130a;

    public /* synthetic */ i(int i2) {
        this.f4130a = i2;
    }

    public final Object apply(Object obj) {
        return ((MessagePublisher) obj).getMessage(this.f4130a);
    }
}
