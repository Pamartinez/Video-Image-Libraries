package com.samsung.android.sdk.scs.ai.asr.safety;

import java.util.function.Function;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class i implements Function {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ boolean f1648a;

    public /* synthetic */ i(boolean z) {
        this.f1648a = z;
    }

    public final Object apply(Object obj) {
        return WatchDogService.lambda$create$0(this.f1648a, (Thread) obj);
    }
}
