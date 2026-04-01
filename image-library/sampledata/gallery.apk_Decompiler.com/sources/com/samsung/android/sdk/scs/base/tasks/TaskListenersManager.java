package com.samsung.android.sdk.scs.base.tasks;

import java.util.ArrayDeque;
import java.util.Queue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class TaskListenersManager<TResult> {
    protected boolean mIsProcessingCompletion;
    protected final Object mLock = new Object();
    protected Queue<TaskListenerCompletion<TResult>> mQueue;

    public final void add(TaskListenerCompletion<TResult> taskListenerCompletion) {
        synchronized (this.mLock) {
            try {
                if (this.mQueue == null) {
                    this.mQueue = new ArrayDeque();
                }
                this.mQueue.add(taskListenerCompletion);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0010, code lost:
        r1 = r2.mLock;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0012, code lost:
        monitor-enter(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r0 = r2.mQueue.poll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x001b, code lost:
        if (r0 != null) goto L_0x0024;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001d, code lost:
        r2.mIsProcessingCompletion = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0020, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0021, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0022, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0024, code lost:
        monitor-exit(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0025, code lost:
        r0.onComplete(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x002a, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void processCompletion(com.samsung.android.sdk.scs.base.tasks.Task<TResult> r3) {
        /*
            r2 = this;
            java.lang.Object r0 = r2.mLock
            monitor-enter(r0)
            java.util.Queue<com.samsung.android.sdk.scs.base.tasks.TaskListenerCompletion<TResult>> r1 = r2.mQueue     // Catch:{ all -> 0x002b }
            if (r1 == 0) goto L_0x002d
            boolean r1 = r2.mIsProcessingCompletion     // Catch:{ all -> 0x002b }
            if (r1 == 0) goto L_0x000c
            goto L_0x002d
        L_0x000c:
            r1 = 1
            r2.mIsProcessingCompletion = r1     // Catch:{ all -> 0x002b }
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
        L_0x0010:
            java.lang.Object r1 = r2.mLock
            monitor-enter(r1)
            java.util.Queue<com.samsung.android.sdk.scs.base.tasks.TaskListenerCompletion<TResult>> r0 = r2.mQueue     // Catch:{ all -> 0x0022 }
            java.lang.Object r0 = r0.poll()     // Catch:{ all -> 0x0022 }
            com.samsung.android.sdk.scs.base.tasks.TaskListenerCompletion r0 = (com.samsung.android.sdk.scs.base.tasks.TaskListenerCompletion) r0     // Catch:{ all -> 0x0022 }
            if (r0 != 0) goto L_0x0024
            r3 = 0
            r2.mIsProcessingCompletion = r3     // Catch:{ all -> 0x0022 }
            monitor-exit(r1)     // Catch:{ all -> 0x0022 }
            return
        L_0x0022:
            r2 = move-exception
            goto L_0x0029
        L_0x0024:
            monitor-exit(r1)     // Catch:{ all -> 0x0022 }
            r0.onComplete(r3)
            goto L_0x0010
        L_0x0029:
            monitor-exit(r1)     // Catch:{ all -> 0x0022 }
            throw r2
        L_0x002b:
            r2 = move-exception
            goto L_0x002f
        L_0x002d:
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            return
        L_0x002f:
            monitor-exit(r0)     // Catch:{ all -> 0x002b }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.scs.base.tasks.TaskListenersManager.processCompletion(com.samsung.android.sdk.scs.base.tasks.Task):void");
    }
}
