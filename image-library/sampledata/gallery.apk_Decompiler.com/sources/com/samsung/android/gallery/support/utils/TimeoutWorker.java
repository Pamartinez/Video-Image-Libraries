package com.samsung.android.gallery.support.utils;

import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TimeoutWorker {
    int mThreadType;
    int mTimeOut;

    public TimeoutWorker(int i2) {
        this.mTimeOut = i2;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$post$0(Runnable runnable, CountDownLatch countDownLatch) {
        runnable.run();
        countDownLatch.countDown();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$post$1(Runnable runnable, CountDownLatch countDownLatch) {
        runnable.run();
        countDownLatch.countDown();
    }

    private boolean post(Runnable runnable) {
        String str;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        if (this.mThreadType == 0) {
            ThreadUtil.postOnUiThread(new V(runnable, countDownLatch, 0));
        } else {
            SimpleThreadPool.getInstance().execute(new V(runnable, countDownLatch, 1));
        }
        try {
            if (countDownLatch.await((long) this.mTimeOut, TimeUnit.MILLISECONDS)) {
                return true;
            }
            StringBuilder sb2 = new StringBuilder("TimeOut {");
            if (this.mThreadType == 0) {
                str = "UI";
            } else {
                str = "BG";
            }
            sb2.append(str);
            sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb2.append(this.mTimeOut);
            sb2.append("}");
            Log.e("TimeoutWorker", sb2.toString());
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean postOnUi(Runnable runnable) {
        this.mThreadType = 0;
        return post(runnable);
    }
}
