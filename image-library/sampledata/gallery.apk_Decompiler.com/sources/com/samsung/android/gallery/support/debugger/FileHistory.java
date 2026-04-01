package com.samsung.android.gallery.support.debugger;

import com.samsung.android.gallery.support.cache.LruCache;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class FileHistory {
    private static final LruCache<Long, String> sLruCache = new LruCache<>(40);

    public static String get() {
        return sLruCache.dump();
    }

    public static void put(long j2, String str) {
        sLruCache.put(Long.valueOf(j2), str);
    }
}
