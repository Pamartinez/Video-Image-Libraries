package com.samsung.android.vexfwk;

import android.util.Log;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.k;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u0005H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005XT¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/vexfwk/VexFwkJniLoader;", "", "<init>", "()V", "TAG", "", "loadedLibraries", "Ljava/util/concurrent/ConcurrentHashMap;", "", "loadLibrary", "libName", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkJniLoader {
    public static final VexFwkJniLoader INSTANCE = new VexFwkJniLoader();
    private static final String TAG = "VexFwkJniLoader";
    private static ConcurrentHashMap<String, Boolean> loadedLibraries = new ConcurrentHashMap<>();

    private VexFwkJniLoader() {
    }

    public static final synchronized boolean loadLibrary(String str) {
        Boolean bool;
        boolean booleanValue;
        Object obj;
        synchronized (VexFwkJniLoader.class) {
            try {
                j.e(str, "libName");
                ConcurrentHashMap<String, Boolean> concurrentHashMap = loadedLibraries;
                bool = concurrentHashMap.get(str);
                if (bool == null) {
                    long currentTimeMillis = System.currentTimeMillis();
                    System.loadLibrary(str);
                    obj = Integer.valueOf(Log.d(TAG, "Load library " + str + " in " + (System.currentTimeMillis() - currentTimeMillis) + " ms"));
                    Throwable a7 = k.a(obj);
                    if (a7 != null) {
                        Log.d(TAG, "Unable to load library ".concat(str), a7);
                    }
                    Boolean valueOf = Boolean.valueOf(!(obj instanceof me.j));
                    Boolean putIfAbsent = concurrentHashMap.putIfAbsent(str, valueOf);
                    if (putIfAbsent == null) {
                        bool = valueOf;
                    } else {
                        bool = putIfAbsent;
                    }
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
            booleanValue = bool.booleanValue();
        }
        return booleanValue;
    }
}
