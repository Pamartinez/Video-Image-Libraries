package com.samsung.android.gallery.module.aiedit;

import A.a;
import com.samsung.android.gallery.support.library.abstraction.VslMesDetectorCompat;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class RemasterDetector2 extends RemasterDetector {
    private final Object LOCK = new Object();
    private final HashSet<Integer> mHashSet = new HashSet<>();
    private final AtomicInteger mRefCount = new AtomicInteger(0);
    volatile VslMesDetectorCompat mVslMesDetector;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LazyHolder {
        static final RemasterDetector INSTANCE = new RemasterDetector2();
    }

    private VslMesDetectorCompat createIfAbsent() {
        if (this.mVslMesDetector == null) {
            long currentTimeMillis = System.currentTimeMillis();
            this.mVslMesDetector = createDetector();
            if (this.mVslMesDetector != null) {
                int hashCode = this.mVslMesDetector.hashCode();
                this.mHashSet.add(Integer.valueOf(hashCode));
                String str = this.TAG;
                a.A(new Object[]{Integer.toHexString(hashCode), Integer.valueOf(this.mHashSet.size()), Long.valueOf(currentTimeMillis)}, new StringBuilder("create"), str);
            }
        }
        return this.mVslMesDetector;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$init$0() {
        if (this.mRefCount.get() > 0) {
            synchronized (this.LOCK) {
                createIfAbsent();
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$releaseAsync$1(VslMesDetectorCompat vslMesDetectorCompat) {
        long currentTimeMillis = System.currentTimeMillis();
        int hashCode = vslMesDetectorCompat.hashCode();
        synchronized (this.LOCK) {
            if (this.mHashSet.remove(Integer.valueOf(hashCode))) {
                try {
                    vslMesDetectorCompat.deInit(Features.isEnabled(Features.SUPPORT_ON_DEMAND_REMASTER_GIF));
                    String str = this.TAG;
                    Log.d(str, "release" + Logger.vt(Integer.toHexString(hashCode), Long.valueOf(currentTimeMillis)));
                } catch (Error | Exception e) {
                    String str2 = this.TAG;
                    Log.e(str2, "deInit failed. e=" + e.getMessage());
                }
            } else {
                String str3 = this.TAG;
                Log.e(str3, "release skip. already release" + Logger.v(Integer.toHexString(hashCode)));
            }
        }
    }

    private void releaseAsync(VslMesDetectorCompat vslMesDetectorCompat) {
        SimpleThreadPool.getInstance().execute(new b(this, vslMesDetectorCompat));
    }

    public void close() {
        if (this.mRefCount.decrementAndGet() == 0) {
            release();
            Log.d(this.TAG, "close");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003f, code lost:
        return r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0069, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x006a, code lost:
        r13 = r0;
        r4 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x006e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x006f, code lost:
        r13 = r0;
        r14 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00c1, code lost:
        r1 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00c3, code lost:
        r1 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00dc, code lost:
        r12.mVslMesDetector = null;
        releaseAsync(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00e9, code lost:
        if (r12.mRefCount.get() == 0) goto L_0x00eb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00eb, code lost:
        com.samsung.android.gallery.support.utils.Log.i(r12.TAG, "detect#release. view already closed");
        r12.mVslMesDetector = null;
        releaseAsync(r4);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0069 A[ExcHandler: all (r0v7 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:26:0x004d] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00c1 A[Catch:{ all -> 0x0040 }] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00c3 A[Catch:{ all -> 0x0040 }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00dc A[Catch:{ all -> 0x0040 }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00e3 A[SYNTHETIC, Splitter:B:53:0x00e3] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<java.lang.String> detect(com.samsung.android.gallery.module.data.MediaItem r13, java.lang.String r14) {
        /*
            r12 = this;
            java.lang.String r1 = "detect failed."
            java.lang.String r0 = "detect"
            com.samsung.android.gallery.support.utils.TimeTickLog r2 = new com.samsung.android.gallery.support.utils.TimeTickLog
            r2.<init>()
            java.lang.Object r3 = r12.LOCK
            monitor-enter(r3)
            com.samsung.android.gallery.support.library.abstraction.VslMesDetectorCompat r4 = r12.mVslMesDetector     // Catch:{ all -> 0x003a }
            r5 = 0
            if (r4 != 0) goto L_0x004c
            com.samsung.android.gallery.support.library.abstraction.VslMesDetectorCompat r4 = r12.createIfAbsent()     // Catch:{ Error | Exception -> 0x0044 }
            if (r4 != 0) goto L_0x0049
            java.lang.String r13 = r12.TAG     // Catch:{ Error | Exception -> 0x0044 }
            java.lang.String r14 = "detect skip. init failed"
            com.samsung.android.gallery.support.utils.Log.e(r13, r14)     // Catch:{ Error | Exception -> 0x0044 }
            java.util.ArrayList r13 = new java.util.ArrayList     // Catch:{ Error | Exception -> 0x0044 }
            r13.<init>()     // Catch:{ Error | Exception -> 0x0044 }
            if (r4 == 0) goto L_0x003e
            java.util.concurrent.atomic.AtomicInteger r14 = r12.mRefCount     // Catch:{ all -> 0x003a }
            int r14 = r14.get()     // Catch:{ all -> 0x003a }
            if (r14 != 0) goto L_0x003e
            java.lang.String r14 = r12.TAG     // Catch:{ all -> 0x003a }
            java.lang.String r0 = "detect#release. view already closed"
            com.samsung.android.gallery.support.utils.Log.i(r14, r0)     // Catch:{ all -> 0x003a }
            r12.mVslMesDetector = r5     // Catch:{ all -> 0x003a }
            r12.releaseAsync(r4)     // Catch:{ all -> 0x003a }
            goto L_0x003e
        L_0x003a:
            r0 = move-exception
            r12 = r0
            goto L_0x0110
        L_0x003e:
            monitor-exit(r3)     // Catch:{ all -> 0x003a }
            return r13
        L_0x0040:
            r0 = move-exception
            r13 = r0
            goto L_0x00f9
        L_0x0044:
            r0 = move-exception
            r13 = r0
            r14 = r5
            goto L_0x00b8
        L_0x0049:
            r2.tick()     // Catch:{ Error | Exception -> 0x0044 }
        L_0x004c:
            r6 = r4
            android.net.Uri r7 = com.samsung.android.gallery.module.data.ContentUri.getUri(r13)     // Catch:{ Error | Exception -> 0x006e, all -> 0x0069 }
            java.lang.String r8 = r13.getPath()     // Catch:{ Error | Exception -> 0x006e, all -> 0x0069 }
            long r9 = r13.getDateTaken()     // Catch:{ Error | Exception -> 0x006e, all -> 0x0069 }
            r11 = r14
            boolean r14 = r6.detectImage(r7, r8, r9, r11)     // Catch:{ Error | Exception -> 0x006e, all -> 0x0069 }
            if (r14 == 0) goto L_0x0073
            java.lang.String r14 = r6.getTagAnalyzedFull()     // Catch:{ Error | Exception -> 0x006e, all -> 0x0069 }
            java.util.ArrayList r14 = r12.unmarshalAnalyzedTag(r14)     // Catch:{ Error | Exception -> 0x006e, all -> 0x0069 }
            goto L_0x0074
        L_0x0069:
            r0 = move-exception
            r13 = r0
            r4 = r6
            goto L_0x00f9
        L_0x006e:
            r0 = move-exception
            r13 = r0
            r14 = r5
        L_0x0071:
            r4 = r6
            goto L_0x00b8
        L_0x0073:
            r14 = r5
        L_0x0074:
            r2.tick()     // Catch:{ Error | Exception -> 0x00b5, all -> 0x0069 }
            java.lang.String r4 = r12.TAG     // Catch:{ Error | Exception -> 0x00b5, all -> 0x0069 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Error | Exception -> 0x00b5, all -> 0x0069 }
            r7.<init>(r0)     // Catch:{ Error | Exception -> 0x00b5, all -> 0x0069 }
            long r8 = r13.getFileId()     // Catch:{ Error | Exception -> 0x00b5, all -> 0x0069 }
            java.lang.Long r13 = java.lang.Long.valueOf(r8)     // Catch:{ Error | Exception -> 0x00b5, all -> 0x0069 }
            int r0 = r6.hashCode()     // Catch:{ Error | Exception -> 0x00b5, all -> 0x0069 }
            java.lang.String r0 = java.lang.Integer.toHexString(r0)     // Catch:{ Error | Exception -> 0x00b5, all -> 0x0069 }
            java.lang.Object[] r13 = new java.lang.Object[]{r13, r0, r11, r14, r2}     // Catch:{ Error | Exception -> 0x00b5, all -> 0x0069 }
            java.lang.String r13 = com.samsung.android.gallery.support.utils.Logger.vt((java.lang.Object[]) r13)     // Catch:{ Error | Exception -> 0x00b5, all -> 0x0069 }
            r7.append(r13)     // Catch:{ Error | Exception -> 0x00b5, all -> 0x0069 }
            java.lang.String r13 = r7.toString()     // Catch:{ Error | Exception -> 0x00b5, all -> 0x0069 }
            com.samsung.android.gallery.support.utils.Log.d(r4, r13)     // Catch:{ Error | Exception -> 0x00b5, all -> 0x0069 }
            java.util.concurrent.atomic.AtomicInteger r13 = r12.mRefCount     // Catch:{ all -> 0x003a }
            int r13 = r13.get()     // Catch:{ all -> 0x003a }
            if (r13 != 0) goto L_0x00f7
            java.lang.String r13 = r12.TAG     // Catch:{ all -> 0x003a }
            java.lang.String r0 = "detect#release. view already closed"
            com.samsung.android.gallery.support.utils.Log.i(r13, r0)     // Catch:{ all -> 0x003a }
            r12.mVslMesDetector = r5     // Catch:{ all -> 0x003a }
            r12.releaseAsync(r6)     // Catch:{ all -> 0x003a }
            goto L_0x00f7
        L_0x00b5:
            r0 = move-exception
            r13 = r0
            goto L_0x0071
        L_0x00b8:
            java.lang.String r0 = r12.TAG     // Catch:{ all -> 0x0040 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0040 }
            r2.<init>(r1)     // Catch:{ all -> 0x0040 }
            if (r4 == 0) goto L_0x00c3
            r1 = 1
            goto L_0x00c4
        L_0x00c3:
            r1 = 0
        L_0x00c4:
            r2.append(r1)     // Catch:{ all -> 0x0040 }
            java.lang.String r1 = ", e="
            r2.append(r1)     // Catch:{ all -> 0x0040 }
            java.lang.String r13 = r13.getMessage()     // Catch:{ all -> 0x0040 }
            r2.append(r13)     // Catch:{ all -> 0x0040 }
            java.lang.String r13 = r2.toString()     // Catch:{ all -> 0x0040 }
            com.samsung.android.gallery.support.utils.Log.e(r0, r13)     // Catch:{ all -> 0x0040 }
            if (r4 == 0) goto L_0x00e1
            r12.mVslMesDetector = r5     // Catch:{ all -> 0x0040 }
            r12.releaseAsync(r4)     // Catch:{ all -> 0x0040 }
        L_0x00e1:
            if (r4 == 0) goto L_0x00f7
            java.util.concurrent.atomic.AtomicInteger r13 = r12.mRefCount     // Catch:{ all -> 0x003a }
            int r13 = r13.get()     // Catch:{ all -> 0x003a }
            if (r13 != 0) goto L_0x00f7
            java.lang.String r13 = r12.TAG     // Catch:{ all -> 0x003a }
            java.lang.String r0 = "detect#release. view already closed"
            com.samsung.android.gallery.support.utils.Log.i(r13, r0)     // Catch:{ all -> 0x003a }
            r12.mVslMesDetector = r5     // Catch:{ all -> 0x003a }
            r12.releaseAsync(r4)     // Catch:{ all -> 0x003a }
        L_0x00f7:
            monitor-exit(r3)     // Catch:{ all -> 0x003a }
            return r14
        L_0x00f9:
            if (r4 == 0) goto L_0x010f
            java.util.concurrent.atomic.AtomicInteger r14 = r12.mRefCount     // Catch:{ all -> 0x003a }
            int r14 = r14.get()     // Catch:{ all -> 0x003a }
            if (r14 != 0) goto L_0x010f
            java.lang.String r14 = r12.TAG     // Catch:{ all -> 0x003a }
            java.lang.String r0 = "detect#release. view already closed"
            com.samsung.android.gallery.support.utils.Log.i(r14, r0)     // Catch:{ all -> 0x003a }
            r12.mVslMesDetector = r5     // Catch:{ all -> 0x003a }
            r12.releaseAsync(r4)     // Catch:{ all -> 0x003a }
        L_0x010f:
            throw r13     // Catch:{ all -> 0x003a }
        L_0x0110:
            monitor-exit(r3)     // Catch:{ all -> 0x003a }
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.aiedit.RemasterDetector2.detect(com.samsung.android.gallery.module.data.MediaItem, java.lang.String):java.util.ArrayList");
    }

    public void init() {
        SimpleThreadPool.getInstance().execute(new c(this));
    }

    public void open() {
        if (this.mRefCount.getAndIncrement() == 0) {
            Log.d(this.TAG, "open");
        }
    }

    public void release() {
        VslMesDetectorCompat vslMesDetectorCompat = this.mVslMesDetector;
        this.mVslMesDetector = null;
        if (vslMesDetectorCompat != null) {
            releaseAsync(vslMesDetectorCompat);
        }
    }

    public void releaseIfExist() {
        if (this.mRefCount.get() > 0 && this.mVslMesDetector != null) {
            Log.i(this.TAG, "releaseIfExist");
            release();
        }
    }

    public void releaseOnStop() {
        if (this.mVslMesDetector != null) {
            Log.d(this.TAG, "releaseOnStop");
            release();
        }
    }
}
