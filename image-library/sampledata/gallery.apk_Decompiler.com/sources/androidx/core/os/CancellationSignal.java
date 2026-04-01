package androidx.core.os;

import A4.Q;

@Deprecated
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class CancellationSignal {
    private boolean mCancelInProgress;
    private Object mCancellationSignalObj;
    private boolean mIsCanceled;
    private OnCancelListener mOnCancelListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnCancelListener {
    }

    private void waitForCancelFinishedLocked() {
        while (this.mCancelInProgress) {
            try {
                wait();
            } catch (InterruptedException unused) {
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0014, code lost:
        if (r0 == null) goto L_0x001b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        ((A4.Q) r0).a();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001b, code lost:
        if (r1 == null) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001d, code lost:
        ((android.os.CancellationSignal) r1).cancel();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0023, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0024, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r3.mCancelInProgress = false;
        notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x002b, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x002f, code lost:
        monitor-enter(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        r3.mCancelInProgress = false;
        notifyAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0035, code lost:
        monitor-exit(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0036, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void cancel() {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.mIsCanceled     // Catch:{ all -> 0x0007 }
            if (r0 == 0) goto L_0x0009
            monitor-exit(r3)     // Catch:{ all -> 0x0007 }
            return
        L_0x0007:
            r0 = move-exception
            goto L_0x003a
        L_0x0009:
            r0 = 1
            r3.mIsCanceled = r0     // Catch:{ all -> 0x0007 }
            r3.mCancelInProgress = r0     // Catch:{ all -> 0x0007 }
            androidx.core.os.CancellationSignal$OnCancelListener r0 = r3.mOnCancelListener     // Catch:{ all -> 0x0007 }
            java.lang.Object r1 = r3.mCancellationSignalObj     // Catch:{ all -> 0x0007 }
            monitor-exit(r3)     // Catch:{ all -> 0x0007 }
            r2 = 0
            if (r0 == 0) goto L_0x001b
            A4.Q r0 = (A4.Q) r0     // Catch:{ all -> 0x0023 }
            r0.a()     // Catch:{ all -> 0x0023 }
        L_0x001b:
            if (r1 == 0) goto L_0x002f
            android.os.CancellationSignal r1 = (android.os.CancellationSignal) r1     // Catch:{ all -> 0x0023 }
            r1.cancel()     // Catch:{ all -> 0x0023 }
            goto L_0x002f
        L_0x0023:
            r0 = move-exception
            monitor-enter(r3)
            r3.mCancelInProgress = r2     // Catch:{ all -> 0x002c }
            r3.notifyAll()     // Catch:{ all -> 0x002c }
            monitor-exit(r3)     // Catch:{ all -> 0x002c }
            throw r0
        L_0x002c:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x002c }
            throw r0
        L_0x002f:
            monitor-enter(r3)
            r3.mCancelInProgress = r2     // Catch:{ all -> 0x0037 }
            r3.notifyAll()     // Catch:{ all -> 0x0037 }
            monitor-exit(r3)     // Catch:{ all -> 0x0037 }
            return
        L_0x0037:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0037 }
            throw r0
        L_0x003a:
            monitor-exit(r3)     // Catch:{ all -> 0x0007 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.os.CancellationSignal.cancel():void");
    }

    public Object getCancellationSignalObject() {
        Object obj;
        synchronized (this) {
            try {
                if (this.mCancellationSignalObj == null) {
                    android.os.CancellationSignal cancellationSignal = new android.os.CancellationSignal();
                    this.mCancellationSignalObj = cancellationSignal;
                    if (this.mIsCanceled) {
                        cancellationSignal.cancel();
                    }
                }
                obj = this.mCancellationSignalObj;
            } catch (Throwable th) {
                throw th;
            }
        }
        return obj;
    }

    public void setOnCancelListener(OnCancelListener onCancelListener) {
        synchronized (this) {
            try {
                waitForCancelFinishedLocked();
                if (this.mOnCancelListener != onCancelListener) {
                    this.mOnCancelListener = onCancelListener;
                    if (this.mIsCanceled) {
                        if (onCancelListener != null) {
                            ((Q) onCancelListener).a();
                        }
                    }
                }
            } finally {
            }
        }
    }
}
