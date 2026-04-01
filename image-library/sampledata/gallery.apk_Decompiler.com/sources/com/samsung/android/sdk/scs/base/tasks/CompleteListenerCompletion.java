package com.samsung.android.sdk.scs.base.tasks;

import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class CompleteListenerCompletion<TResult> implements TaskListenerCompletion<TResult> {
    private final Executor mExecutor;
    private OnCompleteListener<TResult> mListener;
    private final Object mLock = new Object();

    public CompleteListenerCompletion(Executor executor, OnCompleteListener<TResult> onCompleteListener) {
        this.mExecutor = executor;
        this.mListener = onCompleteListener;
    }

    public final void cancel() {
        synchronized (this.mLock) {
            this.mListener = null;
        }
    }

    public final OnCompleteListener<TResult> getCompeteListener() {
        return this.mListener;
    }

    public final void onComplete(Task<TResult> task) {
        synchronized (this.mLock) {
            try {
                if (this.mListener != null) {
                    this.mExecutor.execute(new CompleteListenerRunnable(this, task));
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }
}
