package com.samsung.android.sdk.scs.base.tasks;

import com.samsung.android.sdk.scs.base.utils.Log;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TaskStreamingImpl<TResult> extends TaskImpl<TResult> implements Cloneable {
    private static final String TAG = "ScsApi@TaskStreamingImpl";

    public TaskStreamingImpl() {
        super(new TaskStreamingListenersManager());
    }

    public /* bridge */ /* synthetic */ Task addOnCompleteListener(OnCompleteListener onCompleteListener) {
        return super.addOnCompleteListener(onCompleteListener);
    }

    public /* bridge */ /* synthetic */ boolean cancel() {
        return super.cancel();
    }

    public Object clone() {
        return super.clone();
    }

    public /* bridge */ /* synthetic */ Exception getException() {
        return super.getException();
    }

    public TResult getResult() {
        TResult tresult;
        synchronized (this.mLock) {
            try {
                checkCanceled();
                if (this.mException == null) {
                    tresult = this.mResult;
                } else {
                    throw new RuntimeException(this.mException);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return tresult;
    }

    public /* bridge */ /* synthetic */ String getTaskId() {
        return super.getTaskId();
    }

    public /* bridge */ /* synthetic */ boolean isCanceled() {
        return super.isCanceled();
    }

    public /* bridge */ /* synthetic */ boolean isComplete() {
        return super.isComplete();
    }

    public final boolean isSuccessful() {
        boolean z;
        synchronized (this.mLock) {
            try {
                if (this.mCanceled || this.mException != null) {
                    z = false;
                } else {
                    z = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return z;
    }

    public /* bridge */ /* synthetic */ void setException(Exception exc) {
        super.setException(exc);
    }

    public void setResult(TResult tresult) {
        synchronized (this.mLock) {
            this.mResult = tresult;
        }
        try {
            this.mListenersManager.processCompletion((Task) clone());
        } catch (Exception e) {
            Log.i(TAG, "setResult, e : " + e.getMessage());
            this.mListenersManager.processCompletion(this);
        }
    }

    public /* bridge */ /* synthetic */ boolean trySetException(Exception exc) {
        return super.trySetException(exc);
    }

    public /* bridge */ /* synthetic */ boolean trySetResult(Object obj) {
        return super.trySetResult(obj);
    }

    public /* bridge */ /* synthetic */ Task addOnCompleteListener(Executor executor, OnCompleteListener onCompleteListener) {
        return super.addOnCompleteListener(executor, onCompleteListener);
    }
}
