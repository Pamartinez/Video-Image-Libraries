package com.samsung.android.gallery.support.utils;

import N2.j;
import android.os.Handler;
import android.os.Looper;
import android.util.Printer;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class WatchDog {
    public static long messageCount;
    /* access modifiers changed from: private */
    public volatile boolean mActivate;
    /* access modifiers changed from: private */
    public WeakReference<WatchdogCallback> mCallback;
    /* access modifiers changed from: private */
    public Handler mHandler;
    /* access modifiers changed from: private */
    public Runnable mLogRunnable;
    /* access modifiers changed from: private */
    public Looper mMainLooper;
    /* access modifiers changed from: private */
    public String mStartLog;
    /* access modifiers changed from: private */
    public long mStartTime;
    private Looper mWatchdogLooper;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class WatchDogPrinter implements Printer {
        public /* synthetic */ WatchDogPrinter(WatchDog watchDog, int i2) {
            this();
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$println$0() {
            WatchdogCallback watchdogCallback = (WatchdogCallback) WatchDog.this.mCallback.get();
            if (watchdogCallback != null && WatchDog.this.mActivate) {
                watchdogCallback.onAnr();
            }
        }

        public void println(String str) {
            if (WatchDog.this.mHandler == null) {
                return;
            }
            if (str.charAt(0) == '>') {
                WatchDog.this.mStartLog = str;
                WatchDog.this.mStartTime = System.currentTimeMillis();
                if (WatchDog.this.mCallback != null && WatchDog.this.mActivate && !WatchDog.this.mHandler.postDelayed(new C(2, this), 20000)) {
                    Log.e("WatchDog", "stop Looper Logging");
                    WatchDog.this.mCallback = null;
                    WatchDog.this.mMainLooper.setMessageLogging((Printer) null);
                }
                if (WatchDog.this.mLogRunnable != null && WatchDog.this.mActivate && !WatchDog.this.mHandler.postDelayed(WatchDog.this.mLogRunnable, 100)) {
                    Log.e("WatchDog", "stop Looper Logging");
                    WatchDog.this.mLogRunnable = null;
                    WatchDog.this.mMainLooper.setMessageLogging((Printer) null);
                    return;
                }
                return;
            }
            WatchDog.this.mHandler.removeCallbacksAndMessages((Object) null);
            long currentTimeMillis = System.currentTimeMillis() - WatchDog.this.mStartTime;
            if (currentTimeMillis > 100 && WatchDog.this.mActivate) {
                StringBuilder j2 = j.j(currentTimeMillis, "[", "] msec,  ");
                j2.append(WatchDog.this.mStartLog);
                Log.p("WatchDog", j2.toString());
                WatchdogCallback watchdogCallback = (WatchdogCallback) WatchDog.this.mCallback.get();
                if (watchdogCallback != null && WatchDog.this.mStartLog.contains("Choreographer")) {
                    watchdogCallback.onSlowChoreographer();
                }
            }
            WatchDog.messageCount++;
        }

        private WatchDogPrinter() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface WatchdogCallback {
        void onAnr();

        void onSlowChoreographer();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$createLooperWatchDogLogging$0(Looper looper) {
        if (this.mActivate) {
            String logFromStack = ThreadUtil.getLogFromStack(0, 100, looper.getThread().getStackTrace());
            Log.v("WatchDog", "[Slow Message Call Stack]\n" + logFromStack);
        }
    }

    public void createLooperWatchDogLogging(Looper looper, boolean z, WatchdogCallback watchdogCallback) {
        C0685x xVar;
        this.mWatchdogLooper = ThreadUtil.createBackgroundThreadLooper("WatchDog");
        this.mMainLooper = looper;
        if (z) {
            xVar = new C0685x(6, this, looper);
        } else {
            xVar = null;
        }
        this.mLogRunnable = xVar;
        this.mCallback = new WeakReference<>(watchdogCallback);
        this.mHandler = new Handler(this.mWatchdogLooper);
        this.mMainLooper.setMessageLogging(new WatchDogPrinter(this, 0));
        messageCount = 0;
    }

    public void quit() {
        this.mMainLooper.setMessageLogging((Printer) null);
        this.mMainLooper = null;
        this.mHandler.removeCallbacksAndMessages((Object) null);
        this.mHandler = null;
        this.mWatchdogLooper.quit();
        this.mWatchdogLooper = null;
        this.mCallback = null;
        this.mLogRunnable = null;
    }

    public void setActivate(boolean z) {
        this.mActivate = z;
    }
}
