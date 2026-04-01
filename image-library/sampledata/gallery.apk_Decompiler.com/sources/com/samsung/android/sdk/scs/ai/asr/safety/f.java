package com.samsung.android.sdk.scs.ai.asr.safety;

import com.samsung.android.sdk.scs.ai.asr.safety.WatchDogService;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class f implements Runnable {
    public final /* synthetic */ int d;
    public final /* synthetic */ WatchDogService.RepeatableTask e;

    public /* synthetic */ f(WatchDogService.RepeatableTask repeatableTask, int i2) {
        this.d = i2;
        this.e = repeatableTask;
    }

    public final void run() {
        int i2 = this.d;
        WatchDogService.RepeatableTask repeatableTask = this.e;
        switch (i2) {
            case 0:
                Map<Thread, WatchDog> map = WatchDogService.watchDogHolders;
                repeatableTask.exec();
                return;
            default:
                repeatableTask.iteration();
                return;
        }
    }
}
