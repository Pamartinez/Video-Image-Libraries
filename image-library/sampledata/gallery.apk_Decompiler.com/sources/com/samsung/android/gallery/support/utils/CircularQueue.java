package com.samsung.android.gallery.support.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CircularQueue<T> {
    private boolean mFulled;
    private final Semaphore mLock;
    private int mMax;
    private Queue<T> mQueue;

    public CircularQueue(int i2, boolean z) {
        Semaphore semaphore;
        this.mMax = i2 - 1;
        if (z) {
            semaphore = new Semaphore(1);
        } else {
            semaphore = null;
        }
        this.mLock = semaphore;
        this.mQueue = new LinkedList();
    }

    private void lock() {
        Semaphore semaphore = this.mLock;
        if (semaphore != null) {
            try {
                semaphore.tryAcquire(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void unlock() {
        Semaphore semaphore = this.mLock;
        if (semaphore != null) {
            semaphore.release();
        }
    }

    public void add(T t) {
        lock();
        if (this.mFulled) {
            this.mQueue.poll();
        } else if (this.mQueue.size() >= this.mMax) {
            this.mFulled = true;
        }
        this.mQueue.add(t);
        unlock();
    }

    public ArrayList<T> readAll() {
        lock();
        ArrayList<T> arrayList = new ArrayList<>(this.mQueue.size());
        this.mQueue.iterator().forEachRemaining(new C0683v(arrayList, 5));
        unlock();
        return arrayList;
    }
}
