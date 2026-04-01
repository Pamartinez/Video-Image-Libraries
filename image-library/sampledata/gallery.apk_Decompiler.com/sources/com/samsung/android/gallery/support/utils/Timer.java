package com.samsung.android.gallery.support.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Timer {
    private static Handler sTimerHandler = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message message) {
            ((OnTimer) message.obj).onTimer(message.what);
            super.handleMessage(message);
        }
    };
    private static int sTimerIndex = 1000;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnTimer {
        void onTimer(int i2);
    }

    public static int getTimerId() {
        int i2 = sTimerIndex;
        sTimerIndex = i2 + 1;
        return i2;
    }

    public static boolean isTimerRunning(int i2) {
        return sTimerHandler.hasMessages(i2);
    }

    public static void startTimer(int i2, long j2, OnTimer onTimer) {
        stopTimer(i2);
        Handler handler = sTimerHandler;
        handler.sendMessageDelayed(handler.obtainMessage(i2, 0, 0, onTimer), j2);
    }

    public static void stopTimer(int i2) {
        if (sTimerHandler.hasMessages(i2)) {
            sTimerHandler.removeMessages(i2);
        }
    }
}
