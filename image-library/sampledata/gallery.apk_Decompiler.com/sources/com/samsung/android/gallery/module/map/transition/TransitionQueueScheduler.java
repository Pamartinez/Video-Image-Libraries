package com.samsung.android.gallery.module.map.transition;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TransitionQueueScheduler extends Handler implements MessageQueue.IdleHandler {
    private final Condition mBusyCondition;
    private boolean mIdleListenerAdded;
    private final Lock mLock;
    private final TransitionTaskQueue mQueue = new TransitionTaskQueue();

    public TransitionQueueScheduler() {
        super(Looper.getMainLooper());
        ReentrantLock reentrantLock = new ReentrantLock();
        this.mLock = reentrantLock;
        this.mBusyCondition = reentrantLock.newCondition();
    }

    private boolean isBusy() {
        this.mLock.lock();
        try {
            boolean z = !this.mQueue.isEmpty();
            return z;
        } finally {
            this.mLock.unlock();
        }
    }

    public void addTask(AbsTask absTask) {
        this.mLock.lock();
        try {
            sendEmptyMessage(0);
            this.mQueue.addTask(absTask);
        } finally {
            this.mLock.unlock();
        }
    }

    public void clear() {
        this.mQueue.clear();
    }

    public void handleMessage(Message message) {
        if (!this.mIdleListenerAdded) {
            Looper.myQueue().addIdleHandler(this);
            this.mIdleListenerAdded = true;
        }
        removeMessages(0);
        this.mLock.lock();
        int i2 = 0;
        while (i2 < 10) {
            try {
                this.mQueue.performNextTask(this);
                i2++;
            } catch (Throwable th) {
                this.mLock.unlock();
                throw th;
            }
        }
        if (!isBusy()) {
            this.mIdleListenerAdded = false;
            Looper.myQueue().removeIdleHandler(this);
            this.mBusyCondition.signalAll();
        }
        this.mLock.unlock();
    }

    public boolean queueIdle() {
        sendEmptyMessage(0);
        return true;
    }

    public void waitUntilFree() {
        while (isBusy()) {
            sendEmptyMessage(0);
            this.mLock.lock();
            try {
                if (isBusy()) {
                    this.mBusyCondition.await();
                }
                this.mLock.unlock();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (Throwable th) {
                this.mLock.unlock();
                throw th;
            }
        }
    }
}
