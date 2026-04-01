package Vf;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import qe.C1232h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class E extends W implements Runnable {
    private static volatile Thread _thread;
    private static volatile int debugStatus;
    public static final E k;
    public static final long l;

    /* JADX WARNING: type inference failed for: r0v0, types: [Vf.W, Vf.X, Vf.E] */
    static {
        Long l8;
        ? w = new W();
        k = w;
        w.p(false);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        try {
            l8 = Long.getLong("kotlinx.coroutines.DefaultExecutor.keepAlive", 1000);
        } catch (SecurityException unused) {
            l8 = 1000L;
        }
        l = timeUnit.toNanos(l8.longValue());
    }

    public final O f(long j2, A0 a0, C1232h hVar) {
        long j3 = 0;
        if (j2 > 0) {
            if (j2 >= 9223372036854L) {
                j3 = Long.MAX_VALUE;
            } else {
                j3 = 1000000 * j2;
            }
        }
        if (j3 >= 4611686018427387903L) {
            return r0.d;
        }
        long nanoTime = System.nanoTime();
        T t = new T(j3 + nanoTime, a0);
        x(nanoTime, t);
        return t;
    }

    public final Thread n() {
        Thread thread;
        Thread thread2 = _thread;
        if (thread2 != null) {
            return thread2;
        }
        synchronized (this) {
            thread = _thread;
            if (thread == null) {
                thread = new Thread(this, "kotlinx.coroutines.DefaultExecutor");
                _thread = thread;
                thread.setContextClassLoader(k.getClass().getClassLoader());
                thread.setDaemon(true);
                thread.start();
            }
        }
        return thread;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001a, code lost:
        _thread = null;
        y();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0023, code lost:
        if (isEmpty() != false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0025, code lost:
        n();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0028, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x002f, code lost:
        r9 = Long.MAX_VALUE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0035, code lost:
        java.lang.Thread.interrupted();
        r11 = q();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0040, code lost:
        if (r11 != Long.MAX_VALUE) goto L_0x006b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0042, code lost:
        r15 = java.lang.System.nanoTime();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0048, code lost:
        if (r9 != Long.MAX_VALUE) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004c, code lost:
        r9 = l + r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0050, code lost:
        r15 = r9 - r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0054, code lost:
        if (r15 > 0) goto L_0x0065;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0056, code lost:
        _thread = null;
        y();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x005f, code lost:
        if (isEmpty() != false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0061, code lost:
        n();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0064, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0067, code lost:
        if (r11 <= r15) goto L_0x006c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0069, code lost:
        r11 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x006b, code lost:
        r9 = Long.MAX_VALUE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x006e, code lost:
        if (r11 <= 0) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
        r0 = debugStatus;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0072, code lost:
        if (r0 == 2) goto L_0x0079;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0074, code lost:
        if (r0 != 3) goto L_0x0077;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0077, code lost:
        r0 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0079, code lost:
        r0 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x007a, code lost:
        if (r0 == false) goto L_0x008b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x007c, code lost:
        _thread = null;
        y();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0085, code lost:
        if (isEmpty() != false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0087, code lost:
        n();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:?, code lost:
        java.util.concurrent.locks.LockSupport.parkNanos(r1, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r17 = this;
            r1 = r17
            java.lang.ThreadLocal r0 = Vf.y0.f3876a
            r0.set(r1)
            r2 = 0
            monitor-enter(r17)     // Catch:{ all -> 0x004e }
            int r0 = debugStatus     // Catch:{ all -> 0x008f }
            r3 = 0
            r4 = 3
            r5 = 2
            r6 = 1
            if (r0 == r5) goto L_0x0016
            if (r0 != r4) goto L_0x0014
            goto L_0x0016
        L_0x0014:
            r0 = r3
            goto L_0x0017
        L_0x0016:
            r0 = r6
        L_0x0017:
            if (r0 == 0) goto L_0x0029
            monitor-exit(r17)     // Catch:{ all -> 0x004e }
            _thread = r2
            r1.y()
            boolean r0 = r1.isEmpty()
            if (r0 != 0) goto L_0x008a
            r1.n()
            return
        L_0x0029:
            debugStatus = r6     // Catch:{ all -> 0x008f }
            r1.notifyAll()     // Catch:{ all -> 0x008f }
            monitor-exit(r17)     // Catch:{ all -> 0x004e }
            r7 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            r9 = r7
        L_0x0035:
            java.lang.Thread.interrupted()     // Catch:{ all -> 0x004e }
            long r11 = r1.q()     // Catch:{ all -> 0x004e }
            int r0 = (r11 > r7 ? 1 : (r11 == r7 ? 0 : -1))
            r13 = 0
            if (r0 != 0) goto L_0x006b
            long r15 = java.lang.System.nanoTime()     // Catch:{ all -> 0x004e }
            int r0 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r0 != 0) goto L_0x0050
            long r9 = l     // Catch:{ all -> 0x004e }
            long r9 = r9 + r15
            goto L_0x0050
        L_0x004e:
            r0 = move-exception
            goto L_0x0092
        L_0x0050:
            long r15 = r9 - r15
            int r0 = (r15 > r13 ? 1 : (r15 == r13 ? 0 : -1))
            if (r0 > 0) goto L_0x0065
            _thread = r2
            r1.y()
            boolean r0 = r1.isEmpty()
            if (r0 != 0) goto L_0x008a
            r1.n()
            return
        L_0x0065:
            int r0 = (r11 > r15 ? 1 : (r11 == r15 ? 0 : -1))
            if (r0 <= 0) goto L_0x006c
            r11 = r15
            goto L_0x006c
        L_0x006b:
            r9 = r7
        L_0x006c:
            int r0 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r0 <= 0) goto L_0x0035
            int r0 = debugStatus     // Catch:{ all -> 0x004e }
            if (r0 == r5) goto L_0x0079
            if (r0 != r4) goto L_0x0077
            goto L_0x0079
        L_0x0077:
            r0 = r3
            goto L_0x007a
        L_0x0079:
            r0 = r6
        L_0x007a:
            if (r0 == 0) goto L_0x008b
            _thread = r2
            r1.y()
            boolean r0 = r1.isEmpty()
            if (r0 != 0) goto L_0x008a
            r1.n()
        L_0x008a:
            return
        L_0x008b:
            java.util.concurrent.locks.LockSupport.parkNanos(r1, r11)     // Catch:{ all -> 0x004e }
            goto L_0x0035
        L_0x008f:
            r0 = move-exception
            monitor-exit(r17)     // Catch:{ all -> 0x008f }
            throw r0     // Catch:{ all -> 0x004e }
        L_0x0092:
            _thread = r2
            r1.y()
            boolean r2 = r1.isEmpty()
            if (r2 != 0) goto L_0x00a0
            r1.n()
        L_0x00a0:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: Vf.E.run():void");
    }

    public final void s(long j2, U u) {
        throw new RejectedExecutionException("DefaultExecutor was shut down. This error indicates that Dispatchers.shutdown() was invoked prior to completion of exiting coroutines, leaving coroutines in incomplete state. Please refer to Dispatchers.shutdown documentation for more details");
    }

    public final void shutdown() {
        debugStatus = 4;
        super.shutdown();
    }

    public final void t(Runnable runnable) {
        if (debugStatus != 4) {
            super.t(runnable);
            return;
        }
        throw new RejectedExecutionException("DefaultExecutor was shut down. This error indicates that Dispatchers.shutdown() was invoked prior to completion of exiting coroutines, leaving coroutines in incomplete state. Please refer to Dispatchers.shutdown documentation for more details");
    }

    public final String toString() {
        return "DefaultExecutor";
    }

    public final synchronized void y() {
        boolean z;
        int i2 = debugStatus;
        if (i2 == 2 || i2 == 3) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            debugStatus = 3;
            W.f3847h.set(this, (Object) null);
            W.f3848i.set(this, (Object) null);
            notifyAll();
        }
    }
}
