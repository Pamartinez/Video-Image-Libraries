package com.samsung.android.gallery.support.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class NamedThreadHandler {
    private static final HashMap<String, Integer> sHandlerRefCnt = new HashMap<>();
    private static final HashMap<String, HandlerThreadWatched[]> sHandlerThreadMap = new HashMap<>();
    private final Handler[] mHandler;
    protected final int mThreadCount;
    protected int mThreadIndex;
    private final String mThreadName;
    private final Handler mUiHandler;

    public NamedThreadHandler(String str) {
        this(str, 1);
    }

    private synchronized void increaseThreadIndex() {
        int i2 = this.mThreadCount;
        if (i2 > 1) {
            this.mThreadIndex = (this.mThreadIndex + 1) % i2;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ HandlerThreadWatched[] lambda$new$0(int i2, String str) {
        HandlerThreadWatched[] handlerThreadWatchedArr = new HandlerThreadWatched[i2];
        for (int i7 = 0; i7 < i2; i7++) {
            HandlerThreadWatched handlerThreadWatched = new HandlerThreadWatched(str + "#" + i7, 10);
            handlerThreadWatchedArr[i7] = handlerThreadWatched;
            handlerThreadWatched.start();
        }
        return handlerThreadWatchedArr;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Integer lambda$new$1(String str) {
        return 0;
    }

    public void cancel() {
        for (Handler removeCallbacksAndMessages : this.mHandler) {
            removeCallbacksAndMessages.removeCallbacksAndMessages((Object) null);
        }
        this.mUiHandler.removeCallbacksAndMessages((Object) null);
    }

    public void finalize() {
        super.finalize();
        HashMap<String, HandlerThreadWatched[]> hashMap = sHandlerThreadMap;
        synchronized (hashMap) {
            try {
                HashMap<String, Integer> hashMap2 = sHandlerRefCnt;
                int intValue = hashMap2.get(this.mThreadName).intValue() - 1;
                if (intValue > 0) {
                    hashMap2.replace(this.mThreadName, Integer.valueOf(intValue));
                } else {
                    HandlerThreadWatched[] remove = hashMap.remove(this.mThreadName);
                    for (int i2 = 0; i2 < this.mThreadCount; i2++) {
                        remove[i2].quit();
                    }
                    sHandlerRefCnt.remove(this.mThreadName);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public Handler getHandler() {
        return this.mHandler[0];
    }

    public void run(Runnable runnable, long j2) {
        this.mHandler[this.mThreadIndex].postDelayed(runnable, j2);
        increaseThreadIndex();
    }

    public void runOnUiThread(Runnable runnable) {
        ThreadUtil.runOnHandler(this.mUiHandler, runnable);
    }

    public NamedThreadHandler(String str, int i2) {
        this.mUiHandler = new Handler(Looper.getMainLooper());
        str.getClass();
        this.mThreadName = str;
        HashMap<String, HandlerThreadWatched[]> hashMap = sHandlerThreadMap;
        synchronized (hashMap) {
            try {
                HandlerThread[] handlerThreadArr = (HandlerThread[]) hashMap.computeIfAbsent(str, new F(i2, 0));
                i2 = handlerThreadArr.length != i2 ? handlerThreadArr.length : i2;
                HashMap<String, Integer> hashMap2 = sHandlerRefCnt;
                hashMap2.replace(str, Integer.valueOf(hashMap2.computeIfAbsent(str, new C0670h(20)).intValue() + 1));
                this.mHandler = new Handler[i2];
                for (int i7 = 0; i7 < i2; i7++) {
                    this.mHandler[i7] = new Handler(handlerThreadArr[i7].getLooper());
                }
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        this.mThreadCount = i2;
        this.mThreadIndex = 0;
    }

    public void run(Runnable runnable) {
        Handler[] handlerArr = this.mHandler;
        if (handlerArr.length != 1 || !handlerArr[0].getLooper().isCurrentThread()) {
            this.mHandler[this.mThreadIndex].post(runnable);
            increaseThreadIndex();
            return;
        }
        runnable.run();
    }
}
