package com.samsung.android.sdk.scs.ai.asr.safety;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements WatchDogCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Runnable f1646a;

    public /* synthetic */ a(Runnable runnable) {
        this.f1646a = runnable;
    }

    public final void onTimeoutDetected() {
        this.f1646a.run();
    }
}
