package com.samsung.android.sdk.scs.base.tasks;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class CompleteListenerRunnable implements Runnable {
    private CompleteListenerCompletion mCompletion;
    private Task mTask;

    public CompleteListenerRunnable(CompleteListenerCompletion completeListenerCompletion, Task task) {
        this.mCompletion = completeListenerCompletion;
        this.mTask = task;
    }

    public final void run() {
        synchronized (this.mCompletion) {
            try {
                if (this.mCompletion.getCompeteListener() != null) {
                    this.mCompletion.getCompeteListener().onComplete(this.mTask);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
