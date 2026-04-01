package com.samsung.android.gallery.module.aiedit;

import android.os.Handler;
import android.os.HandlerThread;
import com.samsung.android.gallery.support.utils.HandlerThreadWatched;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class AiEditThread {
    private static final HandlerThread sBgThread;
    private static final Handler sHandler;

    static {
        HandlerThread createThread = createThread();
        sBgThread = createThread;
        sHandler = new Handler(createThread.getLooper());
    }

    private static HandlerThread createThread() {
        HandlerThreadWatched handlerThreadWatched = new HandlerThreadWatched("AiEdit", 10);
        handlerThreadWatched.start();
        return handlerThreadWatched;
    }

    public static void post(Runnable runnable) {
        sHandler.post(runnable);
    }

    public static void removeCallbacks() {
        sHandler.removeCallbacksAndMessages((Object) null);
    }
}
