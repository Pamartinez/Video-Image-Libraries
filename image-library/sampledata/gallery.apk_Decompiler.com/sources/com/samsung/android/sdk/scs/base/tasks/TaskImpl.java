package com.samsung.android.sdk.scs.base.tasks;

import com.samsung.android.sdk.scs.base.utils.Preconditions;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class TaskImpl<TResult> extends Task<TResult> {
    protected volatile boolean mCanceled;
    protected boolean mComplete;
    protected Exception mException;
    protected final TaskListenersManager<TResult> mListenersManager;
    protected final Object mLock;
    protected TResult mResult;
    protected String mTaskId;

    public TaskImpl() {
        this(new TaskListenersManager());
    }

    private void checkComplete() {
        Preconditions.checkState(this.mComplete, "Task is not yet complete");
    }

    private void checkNotComplete() {
        Preconditions.checkState(!this.mComplete, "Task is already complete");
    }

    private void process() {
        synchronized (this.mLock) {
            try {
                if (this.mComplete) {
                    this.mListenersManager.processCompletion(this);
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    public Task<TResult> addOnCompleteListener(OnCompleteListener<? super TResult> onCompleteListener) {
        return addOnCompleteListener(TaskExecutors.MAIN_THREAD, onCompleteListener);
    }

    public boolean cancel() {
        synchronized (this.mLock) {
            try {
                if (this.mComplete) {
                    return false;
                }
                this.mComplete = true;
                this.mCanceled = true;
                this.mListenersManager.processCompletion(this);
                return true;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    public void checkCanceled() {
        if (this.mCanceled) {
            throw new CancellationException("Task is already canceled.");
        }
    }

    public Exception getException() {
        Exception exc;
        synchronized (this.mLock) {
            exc = this.mException;
        }
        return exc;
    }

    public TResult getResult() {
        TResult tresult;
        synchronized (this.mLock) {
            try {
                checkComplete();
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

    public String getTaskId() {
        return this.mTaskId;
    }

    public boolean isCanceled() {
        return this.mCanceled;
    }

    public boolean isComplete() {
        boolean z;
        synchronized (this.mLock) {
            z = this.mComplete;
        }
        return z;
    }

    public boolean isSuccessful() {
        boolean z;
        synchronized (this.mLock) {
            try {
                if (!this.mComplete || this.mCanceled || this.mException != null) {
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

    public void setException(Exception exc) {
        Preconditions.checkNotNull(exc, "Exception must not be null");
        synchronized (this.mLock) {
            checkNotComplete();
            this.mComplete = true;
            this.mException = exc;
        }
        this.mListenersManager.processCompletion(this);
    }

    public void setResult(TResult tresult) {
        synchronized (this.mLock) {
            checkNotComplete();
            this.mComplete = true;
            this.mResult = tresult;
        }
        this.mListenersManager.processCompletion(this);
    }

    public boolean trySetException(Exception exc) {
        Preconditions.checkNotNull(exc, "Exception must not be null");
        synchronized (this.mLock) {
            try {
                if (this.mComplete) {
                    return false;
                }
                this.mComplete = true;
                this.mException = exc;
                this.mListenersManager.processCompletion(this);
                return true;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    public boolean trySetResult(TResult tresult) {
        synchronized (this.mLock) {
            try {
                if (this.mComplete) {
                    return false;
                }
                this.mComplete = true;
                this.mResult = tresult;
                this.mListenersManager.processCompletion(this);
                return true;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    public TaskImpl(TaskListenersManager<TResult> taskListenersManager) {
        this.mLock = new Object();
        this.mListenersManager = taskListenersManager;
        this.mTaskId = UUID.randomUUID().toString();
    }

    public Task<TResult> addOnCompleteListener(Executor executor, OnCompleteListener<? super TResult> onCompleteListener) {
        this.mListenersManager.add(new CompleteListenerCompletion(executor, onCompleteListener));
        process();
        return this;
    }
}
