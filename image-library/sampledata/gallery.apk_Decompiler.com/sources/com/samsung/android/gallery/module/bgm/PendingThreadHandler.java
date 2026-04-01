package com.samsung.android.gallery.module.bgm;

import android.os.Handler;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import java.lang.Runnable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class PendingThreadHandler<T extends Runnable> {
    private final Handler mHandler;
    private final AtomicBoolean mPrepared = new AtomicBoolean();
    private final CopyOnWriteArrayList<Runnable> mQueue = new CopyOnWriteArrayList<>();

    public PendingThreadHandler(String str) {
        this.mHandler = new Handler(ThreadUtil.createBackgroundThreadLooper(str));
    }

    /* access modifiers changed from: private */
    public void processPendingJob() {
        while (this.mPrepared.get() && !this.mQueue.isEmpty()) {
            this.mQueue.remove(0).run();
        }
    }

    public void cancel() {
        this.mQueue.clear();
    }

    public void enqueue(T t) {
        this.mQueue.add(t);
        if (this.mPrepared.get()) {
            this.mHandler.post(new a(1, this));
        }
    }

    public void post(T t) {
        this.mHandler.post(t);
    }

    public void release() {
        this.mPrepared.set(false);
        cancel();
        this.mHandler.removeCallbacksAndMessages((Object) null);
        this.mHandler.getLooper().quitSafely();
    }

    public void setPrepared() {
        this.mPrepared.set(true);
        this.mHandler.post(new a(1, this));
    }
}
