package com.glidebitmappool.internal;

import N2.j;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import android.util.Log;
import c0.C0086a;
import com.samsung.android.gallery.support.utils.Logger;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LruBitmapPool implements BitmapPool {
    private static final boolean DEBUG_LOGGABLE = Log.isLoggable("LruBitmapPool", 3);
    private static final Bitmap.Config DEFAULT_CONFIG = Bitmap.Config.ARGB_8888;
    private final Set<Bitmap.Config> allowedConfigs;
    private long currentSize;
    private int evictions;
    private int hits;
    private final long initialMaxSize;
    private long maxSize;
    private int misses;
    private int puts;
    private final LruPoolStrategy strategy;
    private final BitmapTracker tracker;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class BitmapTracker {
        private final Set<Integer> bitmaps;

        public /* synthetic */ BitmapTracker(int i2) {
            this();
        }

        public void add(Bitmap bitmap) {
            this.bitmaps.add(Integer.valueOf(bitmap.hashCode()));
        }

        public boolean contains(Bitmap bitmap) {
            return this.bitmaps.contains(Integer.valueOf(bitmap.hashCode()));
        }

        public void remove(Bitmap bitmap) {
            this.bitmaps.remove(Integer.valueOf(bitmap.hashCode()));
        }

        private BitmapTracker() {
            this.bitmaps = Collections.synchronizedSet(new HashSet());
        }
    }

    private LruBitmapPool(long j2, LruPoolStrategy lruPoolStrategy, Set<Bitmap.Config> set) {
        this.tracker = new BitmapTracker(0);
        this.initialMaxSize = j2;
        this.maxSize = j2;
        this.strategy = lruPoolStrategy;
        this.allowedConfigs = set;
    }

    private static Bitmap createBitmap(int i2, int i7, Bitmap.Config config) {
        Bitmap.Config config2;
        long currentTimeMillis = System.currentTimeMillis();
        if (config != null) {
            config2 = config;
        } else {
            config2 = DEFAULT_CONFIG;
        }
        Bitmap createBitmap = Bitmap.createBitmap(i2, i7, config2);
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (DEBUG_LOGGABLE) {
            Log.i("LruBitmapPool", "createBitmap {" + i2 + ',' + i7 + ',' + config + "} +" + currentTimeMillis2);
        }
        return createBitmap;
    }

    private void dump() {
        if (DEBUG_LOGGABLE) {
            int i2 = (this.puts - this.hits) - this.evictions;
            StringBuilder sb2 = new StringBuilder("Hits=");
            sb2.append(this.hits);
            sb2.append(", misses=");
            sb2.append(this.misses);
            sb2.append(", puts=");
            sb2.append(this.puts);
            sb2.append(", evictions=");
            j.x(sb2, this.evictions, ", remains=", i2, ", currentSize=");
            sb2.append(this.currentSize);
            sb2.append(", maxSize=");
            sb2.append(this.maxSize);
            sb2.append("\nStrategy=");
            sb2.append(this.strategy);
            Log.v("LruBitmapPool", sb2.toString());
        }
    }

    private static Set<Bitmap.Config> getDefaultAllowedConfigs() {
        HashSet hashSet = new HashSet(Arrays.asList(Bitmap.Config.values()));
        hashSet.add((Object) null);
        hashSet.remove(Bitmap.Config.HARDWARE);
        return Collections.unmodifiableSet(hashSet);
    }

    private static LruPoolStrategy getDefaultStrategy() {
        return new SizeConfigStrategy();
    }

    private synchronized Bitmap getDirtyOrNull(int i2, int i7, Bitmap.Config config) {
        Bitmap.Config config2;
        Bitmap bitmap;
        try {
            LruPoolStrategy lruPoolStrategy = this.strategy;
            if (config != null) {
                config2 = config;
            } else {
                config2 = DEFAULT_CONFIG;
            }
            bitmap = lruPoolStrategy.get(i2, i7, config2);
            if (bitmap == null) {
                if (DEBUG_LOGGABLE) {
                    Log.w("LruBitmapPool", "Missing bitmap=" + Logger.v(Integer.valueOf(i2), Integer.valueOf(i7), config) + this.strategy.logBitmap(i2, i7, config));
                }
                this.misses++;
            } else {
                this.hits++;
                this.currentSize -= (long) this.strategy.getSize(bitmap);
                this.tracker.remove(bitmap);
                normalize(bitmap);
                if (DEBUG_LOGGABLE) {
                    Log.w("LruBitmapPool", "Hit bitmap=" + Logger.v(Integer.valueOf(i2), Integer.valueOf(i7), config) + this.strategy.logBitmap(i2, i7, config));
                }
            }
            dump();
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        return bitmap;
    }

    private static void maybeSetPreMultiplied(Bitmap bitmap) {
        bitmap.setPremultiplied(true);
    }

    private static void normalize(Bitmap bitmap) {
        bitmap.setHasAlpha(true);
        maybeSetPreMultiplied(bitmap);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x007c, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void trimToSize(long r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0023 }
            r0.<init>()     // Catch:{ all -> 0x0023 }
        L_0x0006:
            long r1 = r6.currentSize     // Catch:{ all -> 0x0023 }
            int r1 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r1 <= 0) goto L_0x0069
            com.glidebitmappool.internal.LruPoolStrategy r1 = r6.strategy     // Catch:{ all -> 0x0023 }
            android.graphics.Bitmap r1 = r1.removeLast()     // Catch:{ all -> 0x0023 }
            if (r1 != 0) goto L_0x002b
            boolean r7 = DEBUG_LOGGABLE     // Catch:{ all -> 0x0023 }
            if (r7 == 0) goto L_0x0025
            java.lang.String r7 = "LruBitmapPool"
            java.lang.String r8 = "Size mismatch, resetting"
            android.util.Log.w(r7, r8)     // Catch:{ all -> 0x0023 }
            r6.dump()     // Catch:{ all -> 0x0023 }
            goto L_0x0025
        L_0x0023:
            r7 = move-exception
            goto L_0x007d
        L_0x0025:
            r7 = 0
            r6.currentSize = r7     // Catch:{ all -> 0x0023 }
            monitor-exit(r6)
            return
        L_0x002b:
            com.glidebitmappool.internal.LruBitmapPool$BitmapTracker r2 = r6.tracker     // Catch:{ all -> 0x0023 }
            r2.remove(r1)     // Catch:{ all -> 0x0023 }
            long r2 = r6.currentSize     // Catch:{ all -> 0x0023 }
            com.glidebitmappool.internal.LruPoolStrategy r4 = r6.strategy     // Catch:{ all -> 0x0023 }
            int r4 = r4.getSize(r1)     // Catch:{ all -> 0x0023 }
            long r4 = (long) r4     // Catch:{ all -> 0x0023 }
            long r2 = r2 - r4
            r6.currentSize = r2     // Catch:{ all -> 0x0023 }
            int r2 = r6.evictions     // Catch:{ all -> 0x0023 }
            int r2 = r2 + 1
            r6.evictions = r2     // Catch:{ all -> 0x0023 }
            boolean r2 = DEBUG_LOGGABLE     // Catch:{ all -> 0x0023 }
            if (r2 == 0) goto L_0x0062
            java.lang.String r2 = "LruBitmapPool"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0023 }
            r3.<init>()     // Catch:{ all -> 0x0023 }
            java.lang.String r4 = "Evicting bitmap="
            r3.append(r4)     // Catch:{ all -> 0x0023 }
            com.glidebitmappool.internal.LruPoolStrategy r4 = r6.strategy     // Catch:{ all -> 0x0023 }
            java.lang.String r4 = r4.logBitmap(r1)     // Catch:{ all -> 0x0023 }
            r3.append(r4)     // Catch:{ all -> 0x0023 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0023 }
            android.util.Log.w(r2, r3)     // Catch:{ all -> 0x0023 }
        L_0x0062:
            r6.dump()     // Catch:{ all -> 0x0023 }
            r0.add(r1)     // Catch:{ all -> 0x0023 }
            goto L_0x0006
        L_0x0069:
            boolean r7 = r0.isEmpty()     // Catch:{ all -> 0x0023 }
            if (r7 != 0) goto L_0x007b
            r7 = 0
            android.graphics.Bitmap[] r7 = new android.graphics.Bitmap[r7]     // Catch:{ all -> 0x0023 }
            java.lang.Object[] r7 = r0.toArray(r7)     // Catch:{ all -> 0x0023 }
            android.graphics.Bitmap[] r7 = (android.graphics.Bitmap[]) r7     // Catch:{ all -> 0x0023 }
            com.samsung.android.gallery.support.utils.MemoryUtils.forceFree(r7)     // Catch:{ all -> 0x0023 }
        L_0x007b:
            monitor-exit(r6)
            return
        L_0x007d:
            monitor-exit(r6)     // Catch:{ all -> 0x0023 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.glidebitmappool.internal.LruBitmapPool.trimToSize(long):void");
    }

    public void clearMemory() {
        if (DEBUG_LOGGABLE) {
            Log.d("LruBitmapPool", "clearMemory");
        }
        trimToSize(0);
    }

    public Bitmap get(int i2, int i7, Bitmap.Config config) {
        Bitmap dirtyOrNull = getDirtyOrNull(i2, i7, config);
        if (dirtyOrNull == null) {
            return createBitmap(i2, i7, config);
        }
        dirtyOrNull.eraseColor(0);
        return dirtyOrNull;
    }

    public long getMaxSize() {
        return this.maxSize;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x009d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00d8, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void put(android.graphics.Bitmap r9) {
        /*
            r8 = this;
            java.lang.String r0 = "Reject bitmap from pool, bitmap: "
            java.lang.String r1 = "Put bitmap in pool="
            java.lang.String r2 = "bitmap already contained: "
            monitor-enter(r8)
            if (r9 == 0) goto L_0x00e1
            boolean r3 = r9.isRecycled()     // Catch:{ all -> 0x004d }
            if (r3 != 0) goto L_0x00d9
            boolean r3 = r9.isMutable()     // Catch:{ all -> 0x004d }
            if (r3 == 0) goto L_0x009e
            com.glidebitmappool.internal.LruPoolStrategy r3 = r8.strategy     // Catch:{ all -> 0x004d }
            int r3 = r3.getSize(r9)     // Catch:{ all -> 0x004d }
            long r3 = (long) r3     // Catch:{ all -> 0x004d }
            long r5 = r8.maxSize     // Catch:{ all -> 0x004d }
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 > 0) goto L_0x009e
            java.util.Set<android.graphics.Bitmap$Config> r3 = r8.allowedConfigs     // Catch:{ all -> 0x004d }
            android.graphics.Bitmap$Config r4 = r9.getConfig()     // Catch:{ all -> 0x004d }
            boolean r3 = r3.contains(r4)     // Catch:{ all -> 0x004d }
            if (r3 != 0) goto L_0x002f
            goto L_0x009e
        L_0x002f:
            com.glidebitmappool.internal.LruBitmapPool$BitmapTracker r0 = r8.tracker     // Catch:{ all -> 0x004d }
            boolean r0 = r0.contains(r9)     // Catch:{ all -> 0x004d }
            if (r0 == 0) goto L_0x0050
            com.samsung.android.gallery.support.exception.InternalException r0 = new com.samsung.android.gallery.support.exception.InternalException     // Catch:{ all -> 0x004d }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x004d }
            r1.<init>(r2)     // Catch:{ all -> 0x004d }
            r1.append(r9)     // Catch:{ all -> 0x004d }
            java.lang.String r9 = r1.toString()     // Catch:{ all -> 0x004d }
            r0.<init>(r9)     // Catch:{ all -> 0x004d }
            r0.post()     // Catch:{ all -> 0x004d }
            monitor-exit(r8)
            return
        L_0x004d:
            r9 = move-exception
            goto L_0x00e9
        L_0x0050:
            com.glidebitmappool.internal.LruPoolStrategy r0 = r8.strategy     // Catch:{ all -> 0x004d }
            int r0 = r0.getSize(r9)     // Catch:{ all -> 0x004d }
            long r2 = r8.currentSize     // Catch:{ all -> 0x004d }
            long r4 = (long) r0     // Catch:{ all -> 0x004d }
            long r2 = r2 + r4
            long r6 = r8.maxSize     // Catch:{ all -> 0x004d }
            int r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r2 <= 0) goto L_0x0067
            int r0 = r0 + 1000
            long r2 = (long) r0     // Catch:{ all -> 0x004d }
            long r6 = r6 - r2
            r8.trimToSize(r6)     // Catch:{ all -> 0x004d }
        L_0x0067:
            com.glidebitmappool.internal.LruPoolStrategy r0 = r8.strategy     // Catch:{ all -> 0x004d }
            r0.put(r9)     // Catch:{ all -> 0x004d }
            com.glidebitmappool.internal.LruBitmapPool$BitmapTracker r0 = r8.tracker     // Catch:{ all -> 0x004d }
            r0.add(r9)     // Catch:{ all -> 0x004d }
            int r0 = r8.puts     // Catch:{ all -> 0x004d }
            int r0 = r0 + 1
            r8.puts = r0     // Catch:{ all -> 0x004d }
            long r2 = r8.currentSize     // Catch:{ all -> 0x004d }
            long r2 = r2 + r4
            r8.currentSize = r2     // Catch:{ all -> 0x004d }
            boolean r0 = DEBUG_LOGGABLE     // Catch:{ all -> 0x004d }
            if (r0 == 0) goto L_0x009c
            java.lang.String r0 = "LruBitmapPool"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x004d }
            r2.<init>(r1)     // Catch:{ all -> 0x004d }
            java.lang.Object[] r9 = new java.lang.Object[]{r9}     // Catch:{ all -> 0x004d }
            java.lang.String r9 = com.samsung.android.gallery.support.utils.Logger.v(r9)     // Catch:{ all -> 0x004d }
            r2.append(r9)     // Catch:{ all -> 0x004d }
            java.lang.String r9 = r2.toString()     // Catch:{ all -> 0x004d }
            android.util.Log.v(r0, r9)     // Catch:{ all -> 0x004d }
            r8.dump()     // Catch:{ all -> 0x004d }
        L_0x009c:
            monitor-exit(r8)
            return
        L_0x009e:
            boolean r1 = DEBUG_LOGGABLE     // Catch:{ all -> 0x004d }
            if (r1 == 0) goto L_0x00d7
            java.lang.String r1 = "LruBitmapPool"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x004d }
            r2.<init>(r0)     // Catch:{ all -> 0x004d }
            com.glidebitmappool.internal.LruPoolStrategy r0 = r8.strategy     // Catch:{ all -> 0x004d }
            java.lang.String r0 = r0.logBitmap(r9)     // Catch:{ all -> 0x004d }
            r2.append(r0)     // Catch:{ all -> 0x004d }
            java.lang.String r0 = ", is mutable: "
            r2.append(r0)     // Catch:{ all -> 0x004d }
            boolean r0 = r9.isMutable()     // Catch:{ all -> 0x004d }
            r2.append(r0)     // Catch:{ all -> 0x004d }
            java.lang.String r0 = ", is allowed config: "
            r2.append(r0)     // Catch:{ all -> 0x004d }
            java.util.Set<android.graphics.Bitmap$Config> r0 = r8.allowedConfigs     // Catch:{ all -> 0x004d }
            android.graphics.Bitmap$Config r9 = r9.getConfig()     // Catch:{ all -> 0x004d }
            boolean r9 = r0.contains(r9)     // Catch:{ all -> 0x004d }
            r2.append(r9)     // Catch:{ all -> 0x004d }
            java.lang.String r9 = r2.toString()     // Catch:{ all -> 0x004d }
            android.util.Log.v(r1, r9)     // Catch:{ all -> 0x004d }
        L_0x00d7:
            monitor-exit(r8)
            return
        L_0x00d9:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch:{ all -> 0x004d }
            java.lang.String r0 = "Cannot pool recycled bitmap"
            r9.<init>(r0)     // Catch:{ all -> 0x004d }
            throw r9     // Catch:{ all -> 0x004d }
        L_0x00e1:
            java.lang.NullPointerException r9 = new java.lang.NullPointerException     // Catch:{ all -> 0x004d }
            java.lang.String r0 = "Bitmap must not be null"
            r9.<init>(r0)     // Catch:{ all -> 0x004d }
            throw r9     // Catch:{ all -> 0x004d }
        L_0x00e9:
            monitor-exit(r8)     // Catch:{ all -> 0x004d }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.glidebitmappool.internal.LruBitmapPool.put(android.graphics.Bitmap):void");
    }

    public void trimMemory(int i2) {
        if (DEBUG_LOGGABLE) {
            C0086a.C(i2, "trimMemory, level=", "LruBitmapPool");
        }
        if (i2 >= 40) {
            clearMemory();
        } else if (i2 >= 20) {
            trimToSize(getMaxSize() / 2);
        }
    }

    public Bitmap get(int i2, int i7, Bitmap.Config config, ColorSpace colorSpace) {
        Bitmap dirtyOrNull = getDirtyOrNull(i2, i7, config);
        if (dirtyOrNull == null) {
            return createBitmap(i2, i7, config, colorSpace);
        }
        dirtyOrNull.eraseColor(0);
        return dirtyOrNull;
    }

    private static Bitmap createBitmap(int i2, int i7, Bitmap.Config config, ColorSpace colorSpace) {
        Bitmap.Config config2;
        long currentTimeMillis = System.currentTimeMillis();
        if (config != null) {
            config2 = config;
        } else {
            config2 = DEFAULT_CONFIG;
        }
        Bitmap createBitmap = Bitmap.createBitmap(i2, i7, config2, true, colorSpace);
        long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
        if (DEBUG_LOGGABLE) {
            Log.i("LruBitmapPool", "createBitmap {" + i2 + ',' + i7 + ',' + config + ',' + colorSpace + "} +" + currentTimeMillis2);
        }
        return createBitmap;
    }

    public LruBitmapPool(long j2) {
        this(j2, getDefaultStrategy(), getDefaultAllowedConfigs());
    }

    public LruBitmapPool(long j2, Set<Bitmap.Config> set) {
        this(j2, getDefaultStrategy(), set);
    }
}
