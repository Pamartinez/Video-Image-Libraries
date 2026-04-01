package com.samsung.android.sdk.scs.ai.asr.safety;

import java.util.concurrent.ThreadFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class h implements ThreadFactory {
    public final Thread newThread(Runnable runnable) {
        return WatchDogService.watchdogThread(runnable);
    }
}
