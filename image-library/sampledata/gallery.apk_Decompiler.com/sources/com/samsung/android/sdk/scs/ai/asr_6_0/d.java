package com.samsung.android.sdk.scs.ai.asr_6_0;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements UnaryOperator {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ExpiringData f1653a;
    public final /* synthetic */ Supplier b;

    public /* synthetic */ d(ExpiringData expiringData, Supplier supplier) {
        this.f1653a = expiringData;
        this.b = supplier;
    }

    public final Object apply(Object obj) {
        return this.f1653a.lambda$getOrCompute$1(this.b, obj);
    }
}
