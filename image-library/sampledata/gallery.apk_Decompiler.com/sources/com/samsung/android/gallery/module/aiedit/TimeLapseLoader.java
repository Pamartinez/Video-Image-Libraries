package com.samsung.android.gallery.module.aiedit;

import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TimeLapseLoader {
    private static final TimeLapseLoader sInstance = new TimeLapseLoader();
    private final Object LOCK = new Object();
    private ConcurrentHashMap<Long, Boolean> mMap = new ConcurrentHashMap<>();

    public static TimeLapseLoader getInstance() {
        return sInstance;
    }

    public void removeInMap(long j2) {
        synchronized (this.LOCK) {
            this.mMap.remove(Long.valueOf(j2));
        }
    }
}
