package androidx.media3.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CopyOnWriteMultiset<E> implements Iterable<E> {
    private final Map<E, Integer> elementCounts = new HashMap();
    private Set<E> elementSet = Collections.EMPTY_SET;
    private List<E> elements = Collections.EMPTY_LIST;
    private final Object lock = new Object();

    public void add(E e) {
        synchronized (this.lock) {
            try {
                ArrayList arrayList = new ArrayList(this.elements);
                arrayList.add(e);
                this.elements = Collections.unmodifiableList(arrayList);
                Integer num = this.elementCounts.get(e);
                if (num == null) {
                    HashSet hashSet = new HashSet(this.elementSet);
                    hashSet.add(e);
                    this.elementSet = Collections.unmodifiableSet(hashSet);
                }
                Map<E, Integer> map = this.elementCounts;
                int i2 = 1;
                if (num != null) {
                    i2 = 1 + num.intValue();
                }
                map.put(e, Integer.valueOf(i2));
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public int count(E e) {
        int i2;
        synchronized (this.lock) {
            try {
                if (this.elementCounts.containsKey(e)) {
                    i2 = this.elementCounts.get(e).intValue();
                } else {
                    i2 = 0;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return i2;
    }

    public Set<E> elementSet() {
        Set<E> set;
        synchronized (this.lock) {
            set = this.elementSet;
        }
        return set;
    }

    public Iterator<E> iterator() {
        Iterator<E> it;
        synchronized (this.lock) {
            it = this.elements.iterator();
        }
        return it;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void remove(E r5) {
        /*
            r4 = this;
            java.lang.Object r0 = r4.lock
            monitor-enter(r0)
            java.util.Map<E, java.lang.Integer> r1 = r4.elementCounts     // Catch:{ all -> 0x000f }
            java.lang.Object r1 = r1.get(r5)     // Catch:{ all -> 0x000f }
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch:{ all -> 0x000f }
            if (r1 != 0) goto L_0x0011
            monitor-exit(r0)     // Catch:{ all -> 0x000f }
            return
        L_0x000f:
            r4 = move-exception
            goto L_0x004e
        L_0x0011:
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x000f }
            java.util.List<E> r3 = r4.elements     // Catch:{ all -> 0x000f }
            r2.<init>(r3)     // Catch:{ all -> 0x000f }
            r2.remove(r5)     // Catch:{ all -> 0x000f }
            java.util.List r2 = java.util.Collections.unmodifiableList(r2)     // Catch:{ all -> 0x000f }
            r4.elements = r2     // Catch:{ all -> 0x000f }
            int r2 = r1.intValue()     // Catch:{ all -> 0x000f }
            r3 = 1
            if (r2 != r3) goto L_0x003e
            java.util.Map<E, java.lang.Integer> r1 = r4.elementCounts     // Catch:{ all -> 0x000f }
            r1.remove(r5)     // Catch:{ all -> 0x000f }
            java.util.HashSet r1 = new java.util.HashSet     // Catch:{ all -> 0x000f }
            java.util.Set<E> r2 = r4.elementSet     // Catch:{ all -> 0x000f }
            r1.<init>(r2)     // Catch:{ all -> 0x000f }
            r1.remove(r5)     // Catch:{ all -> 0x000f }
            java.util.Set r5 = java.util.Collections.unmodifiableSet(r1)     // Catch:{ all -> 0x000f }
            r4.elementSet = r5     // Catch:{ all -> 0x000f }
            goto L_0x004c
        L_0x003e:
            java.util.Map<E, java.lang.Integer> r4 = r4.elementCounts     // Catch:{ all -> 0x000f }
            int r1 = r1.intValue()     // Catch:{ all -> 0x000f }
            int r1 = r1 - r3
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x000f }
            r4.put(r5, r1)     // Catch:{ all -> 0x000f }
        L_0x004c:
            monitor-exit(r0)     // Catch:{ all -> 0x000f }
            return
        L_0x004e:
            monitor-exit(r0)     // Catch:{ all -> 0x000f }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media3.common.util.CopyOnWriteMultiset.remove(java.lang.Object):void");
    }
}
