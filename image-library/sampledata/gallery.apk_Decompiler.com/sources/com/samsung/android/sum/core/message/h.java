package com.samsung.android.sum.core.message;

import java.util.Map;
import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4129a;
    public final /* synthetic */ Map b;

    public /* synthetic */ h(int i2, Map map) {
        this.f4129a = i2;
        this.b = map;
    }

    public final Object apply(Object obj) {
        return ((MessagePublisher) obj).getMessage(this.f4129a, this.b);
    }
}
