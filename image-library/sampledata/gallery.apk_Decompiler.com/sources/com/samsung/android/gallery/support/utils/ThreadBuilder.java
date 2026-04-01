package com.samsung.android.gallery.support.utils;

import java.util.concurrent.Executor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ThreadBuilder {
    private Executor mExecutor;
    private Runnable mPostExecutor;
    private Runnable mPreExecutor;

    private void executeWorker(Runnable runnable) {
        Executor executor = this.mExecutor;
        if (executor == null) {
            executor = SimpleThreadPool.getInstance().getPoolExecutor();
        }
        executor.execute(new T(this, runnable, 1));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$execute$0(Runnable runnable) {
        this.mPreExecutor.run();
        this.mPreExecutor = null;
        executeWorker(runnable);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$executeWorker$1(Runnable runnable) {
        runnable.run();
        Runnable runnable2 = this.mPostExecutor;
        if (runnable2 != null) {
            ThreadUtil.postOnUiThread(runnable2);
            this.mPostExecutor = null;
        }
    }

    public void execute(Runnable runnable) {
        if (this.mPreExecutor != null) {
            ThreadUtil.postOnUiThread(new T(this, runnable, 0));
        } else {
            executeWorker(runnable);
        }
    }

    public ThreadBuilder setPostExecutor(Runnable runnable) {
        this.mPostExecutor = runnable;
        return this;
    }

    public ThreadBuilder setPreExecutor(Runnable runnable) {
        this.mPreExecutor = runnable;
        return this;
    }
}
