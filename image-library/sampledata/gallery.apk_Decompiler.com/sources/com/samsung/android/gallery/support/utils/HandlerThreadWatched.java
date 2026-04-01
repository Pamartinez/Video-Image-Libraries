package com.samsung.android.gallery.support.utils;

import android.os.HandlerThread;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HandlerThreadWatched extends HandlerThread {
    private ThreadWatchDog mWatchDog;

    public HandlerThreadWatched(String str, int i2) {
        super(str, i2);
        this.mWatchDog = new ThreadWatchDog(str);
    }

    public void onLooperPrepared() {
        super.onLooperPrepared();
        this.mWatchDog.attachLooper(getLooper());
    }
}
