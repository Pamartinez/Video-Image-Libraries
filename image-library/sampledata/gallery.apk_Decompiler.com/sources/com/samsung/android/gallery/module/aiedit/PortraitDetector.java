package com.samsung.android.gallery.module.aiedit;

import A2.d;
import com.samsung.android.gallery.module.abstraction.PortraitType;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.ocr.MOCRLang;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import yc.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PortraitDetector {
    public static final boolean SUPPORT;
    private static final PortraitDetector sInstance = new PortraitDetector();
    private final Object LOCK = new Object();
    private final HashSet<Integer> mHashSet = new HashSet<>();
    private volatile b mPortraitSolution;
    private final AtomicInteger mRefCount = new AtomicInteger(0);
    private final ConcurrentHashMap<Integer, List<PortraitType>> mResultMap = new ConcurrentHashMap<>();

    static {
        boolean z;
        if (!SdkConfig.atLeast(SdkConfig.SEM.U) || !Features.isEnabled(Features.SUPPORT_PORTRAIT)) {
            z = false;
        } else {
            z = true;
        }
        SUPPORT = z;
    }

    private int convertOrientation(int i2) {
        switch (i2) {
            case 3:
            case 4:
                return 90;
            case 5:
            case 6:
                return 0;
            case 7:
            case 8:
                return MOCRLang.KHMER;
            default:
                return 270;
        }
    }

    private ArrayList<PortraitType> getDetectedType(boolean z) {
        ArrayList<PortraitType> arrayList = new ArrayList<>();
        if (z) {
            arrayList.add(PortraitType.PORTRAIT);
        }
        return arrayList;
    }

    public static PortraitDetector getInstance() {
        return sInstance;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$releaseAsync$0(b bVar) {
        long currentTimeMillis = System.currentTimeMillis();
        synchronized (this.LOCK) {
            try {
                int hashCode = bVar.hashCode();
                if (this.mHashSet.remove(Integer.valueOf(hashCode))) {
                    bVar.a();
                    Log.d("PortraitDetector", "release" + Logger.vt(Integer.toHexString(hashCode), Integer.valueOf(this.mHashSet.size()), Long.valueOf(currentTimeMillis)));
                } else {
                    Log.w((CharSequence) "PortraitDetector", "release skip. already released", Integer.toHexString(hashCode), Integer.valueOf(this.mHashSet.size()));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void release() {
        b bVar = this.mPortraitSolution;
        this.mPortraitSolution = null;
        if (bVar != null) {
            releaseAsync(bVar);
        }
    }

    private void releaseAsync(b bVar) {
        SimpleThreadPool.getInstance().execute(new d(7, this, bVar));
    }

    public void close() {
        if (this.mRefCount.decrementAndGet() == 0) {
            release();
            Log.d("PortraitDetector", "close");
        }
    }

    /* JADX WARNING: type inference failed for: r10v1, types: [yc.a, yc.b, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r11v2, types: [com.samsung.android.portrait.engine.SinglePortraitWrapper, java.lang.Object] */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00c5, code lost:
        if (r3 != 0) goto L_0x00c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00c7, code lost:
        r9 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        r12 = getDetectedType(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        r8 = new java.lang.StringBuilder("detect");
        r9 = java.lang.Long.valueOf(r17.getFileId());
        r10 = java.lang.Integer.toHexString(r5.hashCode());
        r11 = java.lang.Integer.valueOf(r3);
        r2 = new java.lang.StringBuilder("H=");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00ec, code lost:
        r15 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r2.append(r7.check());
        r8.append(com.samsung.android.gallery.support.utils.Logger.vt(r9, r10, r11, r12, r2.toString(), r14));
        com.samsung.android.gallery.support.utils.Log.d("PortraitDetector", r8.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0110, code lost:
        if (r1.mRefCount.get() != 0) goto L_0x0173;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0112, code lost:
        com.samsung.android.gallery.support.utils.Log.i("PortraitDetector", "detect#release. view already destroyed");
        releaseAsync(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x011f, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0120, code lost:
        r6 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0122, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0123, code lost:
        r15 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x012a, code lost:
        r0 = e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x013c A[Catch:{ all -> 0x011d }] */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0145 A[Catch:{ all -> 0x011d }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0175  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.samsung.android.gallery.module.abstraction.PortraitType> detect(com.samsung.android.gallery.module.data.MediaItem r17, android.graphics.Bitmap r18) {
        /*
            r16 = this;
            r1 = r16
            java.lang.String r0 = "H="
            java.lang.String r2 = "detect"
            java.lang.String r3 = "init"
            java.lang.String r4 = "PortraitDetector#detect should be on worker thread"
            com.samsung.android.gallery.support.utils.ThreadUtil.assertBgThread(r4)
            int r4 = r17.getFileHashCode()
            java.util.concurrent.ConcurrentHashMap<java.lang.Integer, java.util.List<com.samsung.android.gallery.module.abstraction.PortraitType>> r5 = r1.mResultMap
            java.lang.Integer r6 = java.lang.Integer.valueOf(r4)
            java.lang.Object r5 = r5.get(r6)
            java.util.List r5 = (java.util.List) r5
            if (r5 == 0) goto L_0x0020
            return r5
        L_0x0020:
            yc.b r5 = r1.mPortraitSolution
            r6 = 0
            com.samsung.android.gallery.support.utils.MemLog r7 = new com.samsung.android.gallery.support.utils.MemLog     // Catch:{ Error | Exception -> 0x0125 }
            java.lang.String r8 = "PortraitDetector"
            r7.<init>(r8)     // Catch:{ Error | Exception -> 0x0125 }
            com.samsung.android.gallery.support.utils.TimeTickLog r14 = new com.samsung.android.gallery.support.utils.TimeTickLog     // Catch:{ Error | Exception -> 0x0125 }
            r14.<init>()     // Catch:{ Error | Exception -> 0x0125 }
            java.lang.Object r8 = r1.LOCK     // Catch:{ Error | Exception -> 0x0125 }
            monitor-enter(r8)     // Catch:{ Error | Exception -> 0x0125 }
            r9 = 0
            if (r5 != 0) goto L_0x009b
            yc.a r10 = new yc.a     // Catch:{ all -> 0x00ae }
            r10.<init>()     // Catch:{ all -> 0x00ae }
            java.lang.String r11 = "SecPortraitInterface"
            java.lang.String r12 = "version: 2.5.0"
            android.util.Log.i(r11, r12)     // Catch:{ all -> 0x00ae }
            r10.f3300a = r9     // Catch:{ all -> 0x00ae }
            r10.b = r9     // Catch:{ all -> 0x00ae }
            r10.f3301c = r6     // Catch:{ all -> 0x00ae }
            r10.d = r9     // Catch:{ all -> 0x00ae }
            com.samsung.android.portrait.engine.SinglePortraitWrapper r11 = new com.samsung.android.portrait.engine.SinglePortraitWrapper     // Catch:{ all -> 0x00ae }
            r11.<init>()     // Catch:{ all -> 0x00ae }
            r10.e = r11     // Catch:{ all -> 0x00ae }
            r1.mPortraitSolution = r10     // Catch:{ all -> 0x0093 }
            java.util.HashSet<java.lang.Integer> r5 = r1.mHashSet     // Catch:{ all -> 0x0093 }
            int r11 = r10.hashCode()     // Catch:{ all -> 0x0093 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)     // Catch:{ all -> 0x0093 }
            r5.add(r11)     // Catch:{ all -> 0x0093 }
            r10.d()     // Catch:{ all -> 0x0093 }
            r14.tick()     // Catch:{ all -> 0x0093 }
            java.lang.String r5 = "PortraitDetector"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0093 }
            r11.<init>(r3)     // Catch:{ all -> 0x0093 }
            int r3 = r10.hashCode()     // Catch:{ all -> 0x0093 }
            java.lang.String r3 = java.lang.Integer.toHexString(r3)     // Catch:{ all -> 0x0093 }
            java.util.HashSet<java.lang.Integer> r12 = r1.mHashSet     // Catch:{ all -> 0x0093 }
            int r12 = r12.size()     // Catch:{ all -> 0x0093 }
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)     // Catch:{ all -> 0x0093 }
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r12, r14}     // Catch:{ all -> 0x0093 }
            java.lang.String r3 = com.samsung.android.gallery.support.utils.Logger.vt((java.lang.Object[]) r3)     // Catch:{ all -> 0x0093 }
            r11.append(r3)     // Catch:{ all -> 0x0093 }
            java.lang.String r3 = r11.toString()     // Catch:{ all -> 0x0093 }
            com.samsung.android.gallery.support.utils.Log.d(r5, r3)     // Catch:{ all -> 0x0093 }
            r5 = r10
            goto L_0x009b
        L_0x0093:
            r0 = move-exception
            r15 = r4
            r5 = r10
            goto L_0x0128
        L_0x0098:
            r15 = r4
            goto L_0x0128
        L_0x009b:
            boolean r3 = r17.isCloudOnly()     // Catch:{ all -> 0x00ae }
            if (r3 == 0) goto L_0x00b0
            java.lang.String r3 = r17.getPath()     // Catch:{ all -> 0x00ae }
            androidx.exifinterface.media.ExifInterface r3 = com.samsung.android.gallery.support.utils.ExifUtils.getExif((java.lang.String) r3)     // Catch:{ all -> 0x00ae }
            int r3 = com.samsung.android.gallery.support.utils.ExifUtils.getOrientationTag(r3)     // Catch:{ all -> 0x00ae }
            goto L_0x00b4
        L_0x00ae:
            r0 = move-exception
            goto L_0x0098
        L_0x00b0:
            int r3 = r17.getOrientationTag()     // Catch:{ all -> 0x00ae }
        L_0x00b4:
            int r3 = r1.convertOrientation(r3)     // Catch:{ all -> 0x00ae }
            r5.c(r3)     // Catch:{ all -> 0x00ae }
            r3 = r18
            int r3 = r5.b(r3)     // Catch:{ all -> 0x00ae }
            r14.tick()     // Catch:{ all -> 0x00ae }
            monitor-exit(r8)     // Catch:{ all -> 0x00ae }
            if (r3 != 0) goto L_0x00c8
            r9 = 1
        L_0x00c8:
            java.util.ArrayList r12 = r1.getDetectedType(r9)     // Catch:{ Error | Exception -> 0x0125 }
            java.lang.String r6 = "PortraitDetector"
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Error | Exception -> 0x0122 }
            r8.<init>(r2)     // Catch:{ Error | Exception -> 0x0122 }
            long r9 = r17.getFileId()     // Catch:{ Error | Exception -> 0x0122 }
            java.lang.Long r9 = java.lang.Long.valueOf(r9)     // Catch:{ Error | Exception -> 0x0122 }
            int r2 = r5.hashCode()     // Catch:{ Error | Exception -> 0x0122 }
            java.lang.String r10 = java.lang.Integer.toHexString(r2)     // Catch:{ Error | Exception -> 0x0122 }
            java.lang.Integer r11 = java.lang.Integer.valueOf(r3)     // Catch:{ Error | Exception -> 0x0122 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Error | Exception -> 0x0122 }
            r2.<init>(r0)     // Catch:{ Error | Exception -> 0x0122 }
            r15 = r4
            long r3 = r7.check()     // Catch:{ Error | Exception -> 0x011f }
            r2.append(r3)     // Catch:{ Error | Exception -> 0x011f }
            java.lang.String r13 = r2.toString()     // Catch:{ Error | Exception -> 0x011f }
            java.lang.Object[] r0 = new java.lang.Object[]{r9, r10, r11, r12, r13, r14}     // Catch:{ Error | Exception -> 0x011f }
            java.lang.String r0 = com.samsung.android.gallery.support.utils.Logger.vt((java.lang.Object[]) r0)     // Catch:{ Error | Exception -> 0x011f }
            r8.append(r0)     // Catch:{ Error | Exception -> 0x011f }
            java.lang.String r0 = r8.toString()     // Catch:{ Error | Exception -> 0x011f }
            com.samsung.android.gallery.support.utils.Log.d(r6, r0)     // Catch:{ Error | Exception -> 0x011f }
            java.util.concurrent.atomic.AtomicInteger r0 = r1.mRefCount
            int r0 = r0.get()
            if (r0 != 0) goto L_0x0173
            java.lang.String r0 = "PortraitDetector"
            java.lang.String r2 = "detect#release. view already destroyed"
            com.samsung.android.gallery.support.utils.Log.i(r0, r2)
            r1.releaseAsync(r5)
            goto L_0x0173
        L_0x011d:
            r0 = move-exception
            goto L_0x017f
        L_0x011f:
            r0 = move-exception
        L_0x0120:
            r6 = r12
            goto L_0x012e
        L_0x0122:
            r0 = move-exception
            r15 = r4
            goto L_0x0120
        L_0x0125:
            r0 = move-exception
            r15 = r4
            goto L_0x012e
        L_0x0128:
            monitor-exit(r8)     // Catch:{ all -> 0x012c }
            throw r0     // Catch:{ Error | Exception -> 0x012a }
        L_0x012a:
            r0 = move-exception
            goto L_0x012e
        L_0x012c:
            r0 = move-exception
            goto L_0x0128
        L_0x012e:
            java.lang.String r2 = "PortraitDetector"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x011d }
            r3.<init>()     // Catch:{ all -> 0x011d }
            java.lang.String r4 = "detect failed. {"
            r3.append(r4)     // Catch:{ all -> 0x011d }
            if (r5 == 0) goto L_0x0145
            int r4 = r5.hashCode()     // Catch:{ all -> 0x011d }
            java.lang.String r4 = java.lang.Integer.toHexString(r4)     // Catch:{ all -> 0x011d }
            goto L_0x0147
        L_0x0145:
            java.lang.String r4 = "0"
        L_0x0147:
            r3.append(r4)     // Catch:{ all -> 0x011d }
            java.lang.String r4 = "} e="
            r3.append(r4)     // Catch:{ all -> 0x011d }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x011d }
            r3.append(r0)     // Catch:{ all -> 0x011d }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x011d }
            com.samsung.android.gallery.support.utils.Log.e(r2, r0)     // Catch:{ all -> 0x011d }
            java.util.concurrent.atomic.AtomicInteger r0 = r1.mRefCount
            int r0 = r0.get()
            if (r0 != 0) goto L_0x0172
            if (r5 == 0) goto L_0x0172
            java.lang.String r0 = "PortraitDetector"
            java.lang.String r2 = "detect#release. view already destroyed"
            com.samsung.android.gallery.support.utils.Log.i(r0, r2)
            r1.releaseAsync(r5)
        L_0x0172:
            r12 = r6
        L_0x0173:
            if (r12 == 0) goto L_0x017e
            java.util.concurrent.ConcurrentHashMap<java.lang.Integer, java.util.List<com.samsung.android.gallery.module.abstraction.PortraitType>> r0 = r1.mResultMap
            java.lang.Integer r1 = java.lang.Integer.valueOf(r15)
            r0.put(r1, r12)
        L_0x017e:
            return r12
        L_0x017f:
            java.util.concurrent.atomic.AtomicInteger r2 = r1.mRefCount
            int r2 = r2.get()
            if (r2 != 0) goto L_0x0193
            if (r5 == 0) goto L_0x0193
            java.lang.String r2 = "PortraitDetector"
            java.lang.String r3 = "detect#release. view already destroyed"
            com.samsung.android.gallery.support.utils.Log.i(r2, r3)
            r1.releaseAsync(r5)
        L_0x0193:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.aiedit.PortraitDetector.detect(com.samsung.android.gallery.module.data.MediaItem, android.graphics.Bitmap):java.util.List");
    }

    public void open() {
        if (this.mRefCount.getAndIncrement() == 0) {
            Log.d("PortraitDetector", "open");
        }
    }

    public void releaseOnStop() {
        if (this.mPortraitSolution != null) {
            Log.d("PortraitDetector", "releaseOnStop");
            release();
        }
    }
}
