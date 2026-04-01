package com.samsung.android.sdk.scs.base.tasks;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class TaskStreamingListenersManager<TResult> extends TaskListenersManager<TResult> {
    private static final String TAG = "TaskStreamingListenersManager";

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0010, code lost:
        com.samsung.android.sdk.scs.base.utils.Log.d(TAG, "processCompletionStreaming: " + r3.mQueue.size());
        r0 = r3.mQueue.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0033, code lost:
        if (r0.hasNext() == false) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0035, code lost:
        r0.next().onComplete(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003f, code lost:
        r3.mIsProcessingCompletion = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0042, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void processCompletion(com.samsung.android.sdk.scs.base.tasks.Task<TResult> r4) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            java.util.Queue<com.samsung.android.sdk.scs.base.tasks.TaskListenerCompletion<TResult>> r1 = r3.mQueue     // Catch:{ all -> 0x0043 }
            if (r1 == 0) goto L_0x0045
            boolean r1 = r3.mIsProcessingCompletion     // Catch:{ all -> 0x0043 }
            if (r1 == 0) goto L_0x000c
            goto L_0x0045
        L_0x000c:
            r1 = 1
            r3.mIsProcessingCompletion = r1     // Catch:{ all -> 0x0043 }
            monitor-exit(r0)     // Catch:{ all -> 0x0043 }
            java.lang.String r0 = "TaskStreamingListenersManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "processCompletionStreaming: "
            r1.<init>(r2)
            java.util.Queue<com.samsung.android.sdk.scs.base.tasks.TaskListenerCompletion<TResult>> r2 = r3.mQueue
            int r2 = r2.size()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.samsung.android.sdk.scs.base.utils.Log.d(r0, r1)
            java.util.Queue<com.samsung.android.sdk.scs.base.tasks.TaskListenerCompletion<TResult>> r0 = r3.mQueue
            java.util.Iterator r0 = r0.iterator()
        L_0x002f:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x003f
            java.lang.Object r1 = r0.next()
            com.samsung.android.sdk.scs.base.tasks.TaskListenerCompletion r1 = (com.samsung.android.sdk.scs.base.tasks.TaskListenerCompletion) r1
            r1.onComplete(r4)
            goto L_0x002f
        L_0x003f:
            r4 = 0
            r3.mIsProcessingCompletion = r4
            return
        L_0x0043:
            r3 = move-exception
            goto L_0x0047
        L_0x0045:
            monitor-exit(r0)     // Catch:{ all -> 0x0043 }
            return
        L_0x0047:
            monitor-exit(r0)     // Catch:{ all -> 0x0043 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.scs.base.tasks.TaskStreamingListenersManager.processCompletion(com.samsung.android.sdk.scs.base.tasks.Task):void");
    }
}
