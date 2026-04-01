package com.samsung.android.gallery.support.utils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MutableValue<V> {
    V value;

    public MutableValue(V v) {
        this.value = v;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0010, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean setValue(V r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            V r0 = r1.value     // Catch:{ all -> 0x0012 }
            if (r0 != 0) goto L_0x0007
            if (r2 == 0) goto L_0x000f
        L_0x0007:
            if (r0 == 0) goto L_0x0014
            boolean r0 = r0.equals(r2)     // Catch:{ all -> 0x0012 }
            if (r0 == 0) goto L_0x0014
        L_0x000f:
            monitor-exit(r1)
            r1 = 0
            return r1
        L_0x0012:
            r2 = move-exception
            goto L_0x0019
        L_0x0014:
            r1.value = r2     // Catch:{ all -> 0x0012 }
            monitor-exit(r1)
            r1 = 1
            return r1
        L_0x0019:
            monitor-exit(r1)     // Catch:{ all -> 0x0012 }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.support.utils.MutableValue.setValue(java.lang.Object):boolean");
    }
}
