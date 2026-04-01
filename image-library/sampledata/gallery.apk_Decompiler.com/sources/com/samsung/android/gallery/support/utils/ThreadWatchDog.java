package com.samsung.android.gallery.support.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Printer;
import com.samsung.android.gallery.support.config.DeviceConfig;
import com.samsung.android.sum.core.Def;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ThreadWatchDog extends Handler {
    private static final HandlerThread WATCHDOG_THREAD;
    static final AtomicBoolean mActive = new AtomicBoolean(true);
    private long lastDumpTime;
    private boolean mExpired = false;
    private Consumer<StackTraceElement[]> mOnExpired;
    private final ConcurrentHashMap<Integer, Long> mStartTime = new ConcurrentHashMap<>();
    private final String mTarget;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class WatchDogPrinter implements Printer {
        public /* synthetic */ WatchDogPrinter(ThreadWatchDog threadWatchDog, int i2) {
            this();
        }

        public void println(String str) {
            if (str.charAt(0) == '>') {
                ThreadWatchDog.this.set();
            } else {
                ThreadWatchDog.this.reset();
            }
        }

        private WatchDogPrinter() {
        }
    }

    static {
        HandlerThread handlerThread = new HandlerThread("ThreadWatchDog");
        WATCHDOG_THREAD = handlerThread;
        handlerThread.start();
    }

    public ThreadWatchDog(String str) {
        super(WATCHDOG_THREAD.getLooper());
        this.mTarget = str;
    }

    private String getElapsedTime(int i2) {
        long j2 = 0;
        Long orDefault = this.mStartTime.getOrDefault(Integer.valueOf(i2), 0L);
        if (orDefault != null) {
            j2 = orDefault.longValue();
        }
        return Logger.vt(j2);
    }

    private boolean needToUpload(int i2) {
        Long orDefault = this.mStartTime.getOrDefault(Integer.valueOf(i2), 0L);
        if (orDefault == null || System.currentTimeMillis() - orDefault.longValue() <= 15000) {
            return false;
        }
        return true;
    }

    public static void setActive(boolean z) {
        mActive.set(z);
    }

    public void attachLooper(Looper looper) {
        looper.setMessageLogging(new WatchDogPrinter(this, 0));
    }

    public void handleMessage(Message message) {
        if (!DeviceConfig.UNIT_TEST) {
            int i2 = message.what;
            if (this.mStartTime.containsKey(Integer.valueOf(i2)) && System.currentTimeMillis() - this.lastDumpTime > Def.SURFACE_CHANNEL_TIMEOUT_MILLIS) {
                String elapsedTime = getElapsedTime(i2);
                AtomicBoolean atomicBoolean = mActive;
                Log.e((CharSequence) "ThreadWatchDog", "Expired : " + this.mTarget + " id=" + i2, elapsedTime, atomicBoolean);
                if (atomicBoolean.get()) {
                    this.mExpired = true;
                    StackTraceElement[] dumpThreads = ThreadUtil.dumpThreads(new P(4, this), this.mTarget);
                    sendEmptyMessageDelayed(i2, 10000);
                    this.lastDumpTime = System.currentTimeMillis();
                    if (this.mOnExpired != null && needToUpload(i2)) {
                        this.mOnExpired.accept(dumpThreads);
                        return;
                    }
                    return;
                }
                reset(i2);
            }
        }
    }

    public void onLog(String str) {
        Log.e("ThreadWatchDog", str);
    }

    public void reset() {
        reset(1);
    }

    public void set() {
        set(1);
    }

    public void setExpireCallback(Consumer<StackTraceElement[]> consumer) {
        this.mOnExpired = consumer;
    }

    public void reset(int i2) {
        removeMessages(i2);
        if (this.mExpired) {
            Log.d("ThreadWatchDog", "Recovered : " + this.mTarget + " " + i2, getElapsedTime(i2));
            this.mExpired = false;
        }
        this.mStartTime.remove(Integer.valueOf(i2));
    }

    public void set(int i2) {
        this.mStartTime.put(Integer.valueOf(i2), Long.valueOf(System.currentTimeMillis()));
        if (!hasMessages(i2)) {
            sendEmptyMessageDelayed(i2, Def.SURFACE_CHANNEL_TIMEOUT_MILLIS);
        }
        if (this.mExpired) {
            Log.e("ThreadWatchDog", this.mTarget + " may be not responding");
        }
    }
}
