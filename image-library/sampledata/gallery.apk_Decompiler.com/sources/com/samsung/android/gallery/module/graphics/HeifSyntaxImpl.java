package com.samsung.android.gallery.module.graphics;

import A.a;
import P1.h;
import com.samsung.android.gallery.support.cache.LruCache;
import com.samsung.android.gallery.support.utils.Log;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class HeifSyntaxImpl {
    static final Object LOCK = new Object();
    static final LruCache<Integer, Integer> cache = new LruCache<>(1000);
    static final HashMap<String, AtomicInteger> locks = new HashMap<>();

    public static Integer getCache(int i2) {
        return cache.get(Integer.valueOf(i2));
    }

    public static int isEditRequired(byte[] bArr, int i2) {
        ByteArrayInputStream byteArrayInputStream;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bArr, i2, bArr.length - i2);
            int h5 = h.h(byteArrayInputStream);
            byteArrayInputStream.close();
            return h5;
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("isEditRequired(s) failed. e="), "HeifSyntaxEditorImpl");
            return -2;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ AtomicInteger lambda$lock$0(String str) {
        return new AtomicInteger(0);
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.Object, java.util.function.Function] */
    public static Object lock(String str) {
        AtomicInteger computeIfAbsent;
        synchronized (LOCK) {
            computeIfAbsent = locks.computeIfAbsent(str, new Object());
            computeIfAbsent.incrementAndGet();
        }
        return computeIfAbsent;
    }

    public static int patch(File file) {
        int c5;
        String path = file.getPath();
        synchronized (lock(path)) {
            try {
                c5 = h.c(file, file);
                unlock(path);
            } catch (Error | Exception e) {
                try {
                    Log.e("HeifSyntaxEditorImpl", "patch failed. e=" + e.getMessage());
                    unlock(path);
                    return -2;
                } catch (Throwable th) {
                    unlock(path);
                    throw th;
                }
            }
        }
        return c5;
    }

    public static void putCache(int i2, int i7) {
        cache.put(Integer.valueOf(i2), Integer.valueOf(i7));
    }

    public static void unlock(String str) {
        synchronized (LOCK) {
            try {
                HashMap<String, AtomicInteger> hashMap = locks;
                AtomicInteger atomicInteger = hashMap.get(str);
                if (atomicInteger != null && atomicInteger.decrementAndGet() == 0) {
                    hashMap.remove(str);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public static int isEditRequired(File file) {
        try {
            return h.h(file);
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("isEditRequired(f) failed. e="), "HeifSyntaxEditorImpl");
            return -2;
        }
    }
}
