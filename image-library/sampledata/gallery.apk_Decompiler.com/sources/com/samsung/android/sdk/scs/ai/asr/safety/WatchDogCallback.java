package com.samsung.android.sdk.scs.ai.asr.safety;

@FunctionalInterface
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface WatchDogCallback {
    public static final WatchDogCallback doNothing = new Object();

    void onTimeoutDetected();

    /* access modifiers changed from: private */
    static /* synthetic */ void lambda$static$0() {
    }
}
