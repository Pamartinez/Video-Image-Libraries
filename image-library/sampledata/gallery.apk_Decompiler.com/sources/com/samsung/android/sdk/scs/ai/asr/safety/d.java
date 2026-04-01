package com.samsung.android.sdk.scs.ai.asr.safety;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class d implements Runnable {
    public final /* synthetic */ int d;

    public /* synthetic */ d(int i2) {
        this.d = i2;
    }

    public final void run() {
        switch (this.d) {
            case 0:
                WatchDogService.checkWatchDog();
                return;
            default:
                WatchDogService.onStopSchedule();
                return;
        }
    }
}
