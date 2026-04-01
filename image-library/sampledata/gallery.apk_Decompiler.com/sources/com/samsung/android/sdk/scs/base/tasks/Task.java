package com.samsung.android.sdk.scs.base.tasks;

import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Task<TResult> {
    public Task<TResult> addOnCompleteListener(OnCompleteListener<? super TResult> onCompleteListener) {
        throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
    }

    public abstract Exception getException();

    public abstract TResult getResult();

    public abstract String getTaskId();

    public abstract boolean isCanceled();

    public abstract boolean isComplete();

    public abstract boolean isSuccessful();

    public Task<TResult> addOnCompleteListener(Executor executor, OnCompleteListener<? super TResult> onCompleteListener) {
        throw new UnsupportedOperationException("addOnCompleteListener is not implemented");
    }
}
