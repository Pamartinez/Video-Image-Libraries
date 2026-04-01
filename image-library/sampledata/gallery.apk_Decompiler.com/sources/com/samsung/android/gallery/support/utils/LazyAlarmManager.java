package com.samsung.android.gallery.support.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LazyAlarmManager {
    /* access modifiers changed from: private */
    public final Runnable mCallback;
    /* access modifiers changed from: private */
    public final AtomicInteger mCounter = new AtomicInteger(0);
    private final Handler mHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            int i2 = 1;
            removeMessages(1);
            int i7 = LazyAlarmManager.this.mCounter.get();
            if (i7 > 0) {
                sendEmptyMessageDelayed(1, 2000);
                AtomicInteger b = LazyAlarmManager.this.mCounter;
                if (i7 <= 1) {
                    i2 = 0;
                }
                b.set(i2);
                LazyAlarmManager.this.mCallback.run();
            }
        }
    };

    public LazyAlarmManager(Runnable runnable) {
        this.mCallback = runnable;
    }

    public void post(Runnable runnable) {
        this.mHandler.post(runnable);
    }

    public void postDelayed(Runnable runnable, long j2) {
        this.mHandler.postDelayed(runnable, j2);
    }

    public void postLazy(long j2) {
        long j3;
        int andIncrement = this.mCounter.getAndIncrement();
        if (!this.mHandler.hasMessages(1)) {
            Handler handler = this.mHandler;
            if (andIncrement == 1) {
                j3 = Math.max(j2, 60);
            } else {
                j3 = 2000;
            }
            handler.sendEmptyMessageDelayed(1, j3);
        } else if (andIncrement == 0) {
            this.mHandler.sendEmptyMessageDelayed(1, Math.max(j2, 60));
        }
    }

    public void removeCallbacks(Runnable runnable) {
        if (runnable != null) {
            this.mHandler.removeCallbacks(runnable);
        }
    }

    public void removeLazy() {
        this.mCounter.set(0);
        this.mHandler.removeMessages(1);
    }
}
