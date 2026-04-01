package com.samsung.android.gallery.support.utils;

import N2.j;
import android.util.Pair;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ObjectDictionary {
    private static final ConcurrentHashMap<Integer, Pair<WeakReference<Object>, ConcurrentHashMap<String, Object>>> sDictionary = new ConcurrentHashMap<>();

    public static void clear(Object obj) {
        if (obj != null) {
            sDictionary.remove(Integer.valueOf(obj.hashCode()));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0028, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int decreaseRefCounter(java.lang.Object r4) {
        /*
            java.lang.String r0 = "RefCounter"
            monitor-enter(r0)
            int r1 = getRefCounter(r4)     // Catch:{ all -> 0x0025 }
            int r1 = r1 + -1
            if (r1 < 0) goto L_0x0029
            java.lang.String r2 = "RefCounter"
            java.util.concurrent.atomic.AtomicInteger r3 = new java.util.concurrent.atomic.AtomicInteger     // Catch:{ all -> 0x0025 }
            r3.<init>(r1)     // Catch:{ all -> 0x0025 }
            setTag(r4, r2, r3)     // Catch:{ all -> 0x0025 }
            if (r1 != 0) goto L_0x0027
            java.util.concurrent.ConcurrentHashMap<java.lang.Integer, android.util.Pair<java.lang.ref.WeakReference<java.lang.Object>, java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.Object>>> r2 = sDictionary     // Catch:{ all -> 0x0025 }
            int r4 = r4.hashCode()     // Catch:{ all -> 0x0025 }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ all -> 0x0025 }
            r2.remove(r4)     // Catch:{ all -> 0x0025 }
            goto L_0x0027
        L_0x0025:
            r4 = move-exception
            goto L_0x003b
        L_0x0027:
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            return r1
        L_0x0029:
            java.lang.String r2 = "ObjectDictionary"
            java.lang.String r3 = "decreaseRefCounter failed"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x0025 }
            java.lang.Object[] r4 = new java.lang.Object[]{r4, r1}     // Catch:{ all -> 0x0025 }
            com.samsung.android.gallery.support.utils.Log.w((java.lang.CharSequence) r2, (java.lang.String) r3, (java.lang.Object[]) r4)     // Catch:{ all -> 0x0025 }
            r4 = -1
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            return r4
        L_0x003b:
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.support.utils.ObjectDictionary.decreaseRefCounter(java.lang.Object):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0031, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getRefCounter(java.lang.Object r3) {
        /*
            java.lang.String r0 = "RefCounter"
            monitor-enter(r0)
            trimEmptyTags()     // Catch:{ all -> 0x001b }
            java.util.concurrent.ConcurrentHashMap<java.lang.Integer, android.util.Pair<java.lang.ref.WeakReference<java.lang.Object>, java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.Object>>> r1 = sDictionary     // Catch:{ all -> 0x001b }
            int r3 = r3.hashCode()     // Catch:{ all -> 0x001b }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ all -> 0x001b }
            java.lang.Object r3 = r1.get(r3)     // Catch:{ all -> 0x001b }
            android.util.Pair r3 = (android.util.Pair) r3     // Catch:{ all -> 0x001b }
            r1 = 0
            if (r3 != 0) goto L_0x001d
            monitor-exit(r0)     // Catch:{ all -> 0x001b }
            return r1
        L_0x001b:
            r3 = move-exception
            goto L_0x0032
        L_0x001d:
            java.lang.Object r3 = r3.second     // Catch:{ all -> 0x001b }
            java.util.concurrent.ConcurrentHashMap r3 = (java.util.concurrent.ConcurrentHashMap) r3     // Catch:{ all -> 0x001b }
            java.lang.String r2 = "RefCounter"
            java.lang.Object r3 = r3.get(r2)     // Catch:{ all -> 0x001b }
            java.util.concurrent.atomic.AtomicInteger r3 = (java.util.concurrent.atomic.AtomicInteger) r3     // Catch:{ all -> 0x001b }
            if (r3 != 0) goto L_0x002c
            goto L_0x0030
        L_0x002c:
            int r1 = r3.get()     // Catch:{ all -> 0x001b }
        L_0x0030:
            monitor-exit(r0)     // Catch:{ all -> 0x001b }
            return r1
        L_0x0032:
            monitor-exit(r0)     // Catch:{ all -> 0x001b }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.support.utils.ObjectDictionary.getRefCounter(java.lang.Object):int");
    }

    public static Object getTag(Object obj, String str) {
        Pair pair = sDictionary.get(Integer.valueOf(obj.hashCode()));
        if (pair == null) {
            return null;
        }
        return ((ConcurrentHashMap) pair.second).get(str);
    }

    public static void increaseRefCounter(Object obj) {
        synchronized ("RefCounter") {
            setTag(obj, "RefCounter", new AtomicInteger(getRefCounter(obj) + 1));
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$trimEmptyTags$0(ArrayList arrayList, Integer num, Pair pair) {
        if (((WeakReference) pair.first).get() == null) {
            arrayList.add(num);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: java.util.concurrent.ConcurrentHashMap} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void setTag(java.lang.Object r5, java.lang.String r6, java.lang.Object r7) {
        /*
            trimEmptyTags()
            java.util.concurrent.ConcurrentHashMap<java.lang.Integer, android.util.Pair<java.lang.ref.WeakReference<java.lang.Object>, java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.Object>>> r0 = sDictionary
            int r1 = r5.hashCode()
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.Object r1 = r0.get(r1)
            android.util.Pair r1 = (android.util.Pair) r1
            if (r1 != 0) goto L_0x0030
            java.util.concurrent.ConcurrentHashMap r1 = new java.util.concurrent.ConcurrentHashMap
            r1.<init>()
            int r2 = r5.hashCode()
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            android.util.Pair r3 = new android.util.Pair
            java.lang.ref.WeakReference r4 = new java.lang.ref.WeakReference
            r4.<init>(r5)
            r3.<init>(r4, r1)
            r0.put(r2, r3)
            goto L_0x0035
        L_0x0030:
            java.lang.Object r5 = r1.second
            r1 = r5
            java.util.concurrent.ConcurrentHashMap r1 = (java.util.concurrent.ConcurrentHashMap) r1
        L_0x0035:
            r1.put(r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.support.utils.ObjectDictionary.setTag(java.lang.Object, java.lang.String, java.lang.Object):void");
    }

    private static void trimEmptyTags() {
        ArrayList arrayList = new ArrayList();
        ConcurrentHashMap<Integer, Pair<WeakReference<Object>, ConcurrentHashMap<String, Object>>> concurrentHashMap = sDictionary;
        concurrentHashMap.forEach(new G(arrayList, 2));
        if (!arrayList.isEmpty()) {
            int size = concurrentHashMap.size();
            arrayList.forEach(new P(3, concurrentHashMap));
            String g = j.g(new StringBuilder("trim : "), arrayList);
            Log.d("ObjectDictionary", g, size + ">" + concurrentHashMap.size());
        }
    }
}
