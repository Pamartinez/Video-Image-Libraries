package com.samsung.android.gallery.module.clip.textextraction;

import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class TextExtractionCache {
    private static final ConcurrentHashMap<Long, Integer> mCache = new ConcurrentHashMap<>();

    public static int get(long j2) {
        Integer num = mCache.get(Long.valueOf(j2));
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    public static void put(long j2, int i2) {
        mCache.put(Long.valueOf(j2), Integer.valueOf(i2));
    }
}
