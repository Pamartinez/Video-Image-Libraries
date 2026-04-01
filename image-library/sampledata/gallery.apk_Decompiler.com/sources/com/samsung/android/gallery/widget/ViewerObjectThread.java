package com.samsung.android.gallery.widget;

import android.os.Handler;
import android.os.Looper;
import com.samsung.android.gallery.support.utils.ThreadUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ViewerObjectThread {
    private final Handler mBgHandler = new Handler(ThreadUtil.getBackgroundThreadLooper());
    private final Handler mUiHandler = new Handler(Looper.getMainLooper());

    public void cancel() {
        this.mBgHandler.removeCallbacksAndMessages((Object) null);
        this.mUiHandler.removeCallbacksAndMessages((Object) null);
    }

    public void cancelBgThread(Runnable runnable) {
        if (runnable != null) {
            this.mBgHandler.removeCallbacks(runnable);
        } else {
            this.mBgHandler.removeCallbacksAndMessages((Object) null);
        }
    }

    public void cancelUiThread(Runnable runnable) {
        if (runnable != null) {
            this.mUiHandler.removeCallbacks(runnable);
        } else {
            this.mUiHandler.removeCallbacksAndMessages((Object) null);
        }
    }

    public void runOnBgThread(Runnable runnable) {
        ThreadUtil.runOnHandler(this.mBgHandler, runnable);
    }

    public void runOnUiThread(Runnable runnable) {
        ThreadUtil.runOnHandler(this.mUiHandler, runnable);
    }

    public void runOnBgThread(Runnable runnable, long j2) {
        ThreadUtil.runOnHandlerDelayed(this.mBgHandler, runnable, j2);
    }

    public void runOnUiThread(Runnable runnable, long j2) {
        ThreadUtil.runOnHandlerDelayed(this.mUiHandler, runnable, j2);
    }

    public void cancelBgThread(Object obj) {
        this.mBgHandler.removeCallbacksAndMessages(obj);
    }

    public void runOnBgThread(Runnable runnable, Object obj, long j2) {
        ThreadUtil.runOnHandlerDelayed(this.mBgHandler, obj, runnable, j2);
    }
}
