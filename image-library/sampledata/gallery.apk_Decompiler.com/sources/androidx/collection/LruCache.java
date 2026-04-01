package androidx.collection;

import androidx.collection.internal.Lock;
import androidx.collection.internal.LruHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u00012\u00020\u0001B\u0011\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\n\u001a\u00020\u00042\u0006\u0010\b\u001a\u00028\u00002\u0006\u0010\t\u001a\u00028\u0001H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\f\u001a\u0004\u0018\u00018\u00012\u0006\u0010\b\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\f\u0010\rJ\u001f\u0010\u000e\u001a\u0004\u0018\u00018\u00012\u0006\u0010\b\u001a\u00028\u00002\u0006\u0010\t\u001a\u00028\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0011\u0010\u0007J1\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\b\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00028\u00012\b\u0010\u0015\u001a\u0004\u0018\u00018\u0001H\u0014¢\u0006\u0004\b\u0016\u0010\u0017J\u0019\u0010\u0018\u001a\u0004\u0018\u00018\u00012\u0006\u0010\b\u001a\u00028\u0000H\u0014¢\u0006\u0004\b\u0018\u0010\rJ\u001f\u0010\u0019\u001a\u00020\u00042\u0006\u0010\b\u001a\u00028\u00002\u0006\u0010\t\u001a\u00028\u0001H\u0014¢\u0006\u0004\b\u0019\u0010\u000bJ\u000f\u0010\u001b\u001a\u00020\u001aH\u0016¢\u0006\u0004\b\u001b\u0010\u001cR\u0016\u0010\u0005\u001a\u00020\u00048\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u0005\u0010\u001dR \u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001e8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\u00020!8\u0002X\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R\u0016\u0010$\u001a\u00020\u00048\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b$\u0010\u001dR\u0016\u0010%\u001a\u00020\u00048\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b%\u0010\u001dR\u0016\u0010&\u001a\u00020\u00048\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b&\u0010\u001dR\u0016\u0010'\u001a\u00020\u00048\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b'\u0010\u001dR\u0016\u0010(\u001a\u00020\u00048\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b(\u0010\u001dR\u0016\u0010)\u001a\u00020\u00048\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b)\u0010\u001d¨\u0006*"}, d2 = {"Landroidx/collection/LruCache;", "", "K", "V", "", "maxSize", "<init>", "(I)V", "key", "value", "safeSizeOf", "(Ljava/lang/Object;Ljava/lang/Object;)I", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "Lme/x;", "trimToSize", "", "evicted", "oldValue", "newValue", "entryRemoved", "(ZLjava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V", "create", "sizeOf", "", "toString", "()Ljava/lang/String;", "I", "Landroidx/collection/internal/LruHashMap;", "map", "Landroidx/collection/internal/LruHashMap;", "Landroidx/collection/internal/Lock;", "lock", "Landroidx/collection/internal/Lock;", "size", "putCount", "createCount", "evictionCount", "hitCount", "missCount", "collection"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LruCache<K, V> {
    private int createCount;
    private int evictionCount;
    private int hitCount;
    private final Lock lock;
    private final LruHashMap<K, V> map;
    private int maxSize;
    private int missCount;
    private int putCount;
    private int size;

    public LruCache(int i2) {
        this.maxSize = i2;
        if (i2 > 0) {
            this.map = new LruHashMap<>(0, 0.75f);
            this.lock = new Lock();
            return;
        }
        throw new IllegalArgumentException("maxSize <= 0");
    }

    private final int safeSizeOf(K k, V v) {
        int sizeOf = sizeOf(k, v);
        if (sizeOf >= 0) {
            return sizeOf;
        }
        throw new IllegalStateException(("Negative size: " + k + '=' + v).toString());
    }

    public V create(K k) {
        j.e(k, "key");
        return null;
    }

    public void entryRemoved(boolean z, K k, V v, V v6) {
        j.e(k, "key");
        j.e(v, "oldValue");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0021, code lost:
        r0 = create(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0025, code lost:
        if (r0 != null) goto L_0x0029;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0027, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0029, code lost:
        r1 = r5.lock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002b, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r5.createCount++;
        r2 = r5.map.put(r6, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0038, code lost:
        if (r2 == null) goto L_0x0042;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003a, code lost:
        r5.map.put(r6, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0040, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0042, code lost:
        r5.size += safeSizeOf(r6, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004b, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004c, code lost:
        if (r2 == null) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004e, code lost:
        entryRemoved(false, r6, r0, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0052, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0053, code lost:
        trimToSize(r5.maxSize);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0058, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x005a, code lost:
        throw r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final V get(K r6) {
        /*
            r5 = this;
            java.lang.String r0 = "key"
            kotlin.jvm.internal.j.e(r6, r0)
            androidx.collection.internal.Lock r0 = r5.lock
            monitor-enter(r0)
            androidx.collection.internal.LruHashMap<K, V> r1 = r5.map     // Catch:{ all -> 0x0018 }
            java.lang.Object r1 = r1.get(r6)     // Catch:{ all -> 0x0018 }
            if (r1 == 0) goto L_0x001a
            int r6 = r5.hitCount     // Catch:{ all -> 0x0018 }
            int r6 = r6 + 1
            r5.hitCount = r6     // Catch:{ all -> 0x0018 }
            monitor-exit(r0)
            return r1
        L_0x0018:
            r5 = move-exception
            goto L_0x005b
        L_0x001a:
            int r1 = r5.missCount     // Catch:{ all -> 0x0018 }
            int r1 = r1 + 1
            r5.missCount = r1     // Catch:{ all -> 0x0018 }
            monitor-exit(r0)
            java.lang.Object r0 = r5.create(r6)
            if (r0 != 0) goto L_0x0029
            r5 = 0
            return r5
        L_0x0029:
            androidx.collection.internal.Lock r1 = r5.lock
            monitor-enter(r1)
            int r2 = r5.createCount     // Catch:{ all -> 0x0040 }
            int r2 = r2 + 1
            r5.createCount = r2     // Catch:{ all -> 0x0040 }
            androidx.collection.internal.LruHashMap<K, V> r2 = r5.map     // Catch:{ all -> 0x0040 }
            java.lang.Object r2 = r2.put(r6, r0)     // Catch:{ all -> 0x0040 }
            if (r2 == 0) goto L_0x0042
            androidx.collection.internal.LruHashMap<K, V> r3 = r5.map     // Catch:{ all -> 0x0040 }
            r3.put(r6, r2)     // Catch:{ all -> 0x0040 }
            goto L_0x004b
        L_0x0040:
            r5 = move-exception
            goto L_0x0059
        L_0x0042:
            int r3 = r5.size     // Catch:{ all -> 0x0040 }
            int r4 = r5.safeSizeOf(r6, r0)     // Catch:{ all -> 0x0040 }
            int r3 = r3 + r4
            r5.size = r3     // Catch:{ all -> 0x0040 }
        L_0x004b:
            monitor-exit(r1)
            if (r2 == 0) goto L_0x0053
            r1 = 0
            r5.entryRemoved(r1, r6, r0, r2)
            return r2
        L_0x0053:
            int r6 = r5.maxSize
            r5.trimToSize(r6)
            return r0
        L_0x0059:
            monitor-exit(r1)
            throw r5
        L_0x005b:
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.LruCache.get(java.lang.Object):java.lang.Object");
    }

    public final V put(K k, V v) {
        V put;
        j.e(k, "key");
        j.e(v, "value");
        synchronized (this.lock) {
            this.putCount++;
            this.size += safeSizeOf(k, v);
            put = this.map.put(k, v);
            if (put != null) {
                this.size -= safeSizeOf(k, put);
            }
        }
        if (put != null) {
            entryRemoved(false, k, put, v);
        }
        trimToSize(this.maxSize);
        return put;
    }

    public int sizeOf(K k, V v) {
        j.e(k, "key");
        j.e(v, "value");
        return 1;
    }

    public String toString() {
        int i2;
        String str;
        synchronized (this.lock) {
            try {
                int i7 = this.hitCount;
                int i8 = this.missCount + i7;
                if (i8 != 0) {
                    i2 = (i7 * 100) / i8;
                } else {
                    i2 = 0;
                }
                str = "LruCache[maxSize=" + this.maxSize + ",hits=" + this.hitCount + ",misses=" + this.missCount + ",hitRate=" + i2 + "%]";
            } catch (Throwable th) {
                throw th;
            }
        }
        return str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x005e, code lost:
        throw new java.lang.IllegalStateException("LruCache.sizeOf() is reporting inconsistent results!");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void trimToSize(int r6) {
        /*
            r5 = this;
        L_0x0000:
            androidx.collection.internal.Lock r0 = r5.lock
            monitor-enter(r0)
            int r1 = r5.size     // Catch:{ all -> 0x0014 }
            if (r1 < 0) goto L_0x0057
            androidx.collection.internal.LruHashMap<K, V> r1 = r5.map     // Catch:{ all -> 0x0014 }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0014 }
            if (r1 == 0) goto L_0x0016
            int r1 = r5.size     // Catch:{ all -> 0x0014 }
            if (r1 != 0) goto L_0x0057
            goto L_0x0016
        L_0x0014:
            r5 = move-exception
            goto L_0x005f
        L_0x0016:
            int r1 = r5.size     // Catch:{ all -> 0x0014 }
            if (r1 <= r6) goto L_0x0055
            androidx.collection.internal.LruHashMap<K, V> r1 = r5.map     // Catch:{ all -> 0x0014 }
            boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0014 }
            if (r1 == 0) goto L_0x0023
            goto L_0x0055
        L_0x0023:
            androidx.collection.internal.LruHashMap<K, V> r1 = r5.map     // Catch:{ all -> 0x0014 }
            java.util.Set r1 = r1.getEntries()     // Catch:{ all -> 0x0014 }
            java.lang.Object r1 = ne.C1194l.M0(r1)     // Catch:{ all -> 0x0014 }
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1     // Catch:{ all -> 0x0014 }
            if (r1 != 0) goto L_0x0033
            monitor-exit(r0)
            return
        L_0x0033:
            java.lang.Object r2 = r1.getKey()     // Catch:{ all -> 0x0014 }
            java.lang.Object r1 = r1.getValue()     // Catch:{ all -> 0x0014 }
            androidx.collection.internal.LruHashMap<K, V> r3 = r5.map     // Catch:{ all -> 0x0014 }
            r3.remove(r2)     // Catch:{ all -> 0x0014 }
            int r3 = r5.size     // Catch:{ all -> 0x0014 }
            int r4 = r5.safeSizeOf(r2, r1)     // Catch:{ all -> 0x0014 }
            int r3 = r3 - r4
            r5.size = r3     // Catch:{ all -> 0x0014 }
            int r3 = r5.evictionCount     // Catch:{ all -> 0x0014 }
            r4 = 1
            int r3 = r3 + r4
            r5.evictionCount = r3     // Catch:{ all -> 0x0014 }
            monitor-exit(r0)
            r0 = 0
            r5.entryRemoved(r4, r2, r1, r0)
            goto L_0x0000
        L_0x0055:
            monitor-exit(r0)
            return
        L_0x0057:
            java.lang.String r5 = "LruCache.sizeOf() is reporting inconsistent results!"
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0014 }
            r6.<init>(r5)     // Catch:{ all -> 0x0014 }
            throw r6     // Catch:{ all -> 0x0014 }
        L_0x005f:
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.collection.LruCache.trimToSize(int):void");
    }
}
