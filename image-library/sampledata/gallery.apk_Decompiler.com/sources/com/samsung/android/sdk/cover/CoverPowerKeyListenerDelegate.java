package com.samsung.android.sdk.cover;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.samsung.android.cover.INfcLedCoverTouchListenerCallback;
import com.samsung.android.sdk.cover.ScoverManager;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CoverPowerKeyListenerDelegate extends INfcLedCoverTouchListenerCallback.Stub {
    private static final int MSG_SYSTEM_COVER_EVENT = 0;
    private ListenerDelegateHandler mHandler;
    private ScoverManager.CoverPowerKeyListener mListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ListenerDelegateHandler extends Handler {
        private final WeakReference<ScoverManager.CoverPowerKeyListener> mListenerRef;

        public ListenerDelegateHandler(Looper looper, ScoverManager.CoverPowerKeyListener coverPowerKeyListener) {
            super(looper);
            this.mListenerRef = new WeakReference<>(coverPowerKeyListener);
        }

        public void handleMessage(Message message) {
            ScoverManager.CoverPowerKeyListener coverPowerKeyListener = this.mListenerRef.get();
            if (message.what == 0 && coverPowerKeyListener != null) {
                coverPowerKeyListener.onPowerKeyPress();
            }
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class SystemEvents {
        private static final String KEY_DISABLE_LCD_OFF_BY_COVER = "lcd_off_disabled_by_cover";
        private static final int LCD_OFF_DISABLED_BY_COVER = 4;
        private static final int LED_OFF = 0;
        private static final int NOTIFICATION_ADD = 2;
        private static final int NOTIFICATION_REMOVE = 3;
        private static final int POWER_BUTTON = 1;

        private SystemEvents() {
        }
    }

    public CoverPowerKeyListenerDelegate(ScoverManager.CoverPowerKeyListener coverPowerKeyListener, Handler handler, Context context) {
        Looper looper;
        this.mListener = coverPowerKeyListener;
        if (handler == null) {
            looper = context.getMainLooper();
        } else {
            looper = handler.getLooper();
        }
        this.mHandler = new ListenerDelegateHandler(looper, this.mListener);
    }

    public Object getListener() {
        return this.mListener;
    }

    public void onSystemCoverEvent(int i2, Bundle bundle) {
        if (i2 == 1) {
            this.mHandler.sendEmptyMessage(0);
        }
    }

    public void onCoverTapLeft() {
    }

    public void onCoverTapMid() {
    }

    public void onCoverTapRight() {
    }

    public void onCoverTouchAccept() {
    }

    public void onCoverTouchReject() {
    }
}
