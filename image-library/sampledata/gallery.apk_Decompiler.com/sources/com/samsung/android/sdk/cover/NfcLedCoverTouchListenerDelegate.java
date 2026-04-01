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
class NfcLedCoverTouchListenerDelegate extends INfcLedCoverTouchListenerCallback.Stub {
    private static final int MSG_LISTEN_COVER_TOUCH_ACCEPT = 0;
    private static final int MSG_LISTEN_COVER_TOUCH_REJECT = 1;
    private static final int MSG_LISTEN_COVER_TOUCH_REJECT_TAP_LEFT = 2;
    private static final int MSG_LISTEN_COVER_TOUCH_REJECT_TAP_MID = 3;
    private static final int MSG_LISTEN_COVER_TOUCH_REJECT_TAP_RIGHT = 4;
    private ListenerDelegateHandler mHandler;
    private ScoverManager.NfcLedCoverTouchListener mListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ListenerDelegateHandler extends Handler {
        private final WeakReference<ScoverManager.NfcLedCoverTouchListener> mListenerRef;

        public ListenerDelegateHandler(Looper looper, ScoverManager.NfcLedCoverTouchListener nfcLedCoverTouchListener) {
            super(looper);
            this.mListenerRef = new WeakReference<>(nfcLedCoverTouchListener);
        }

        public void handleMessage(Message message) {
            ScoverManager.NfcLedCoverTouchListener nfcLedCoverTouchListener = this.mListenerRef.get();
            if (nfcLedCoverTouchListener != null) {
                int i2 = message.what;
                if (i2 == 0) {
                    nfcLedCoverTouchListener.onCoverTouchAccept();
                } else if (i2 == 1) {
                    nfcLedCoverTouchListener.onCoverTouchReject();
                } else if (i2 == 2) {
                    nfcLedCoverTouchListener.onCoverTapLeft();
                } else if (i2 == 3) {
                    nfcLedCoverTouchListener.onCoverTapMid();
                } else if (i2 == 4) {
                    nfcLedCoverTouchListener.onCoverTapRight();
                }
            }
        }
    }

    public NfcLedCoverTouchListenerDelegate(ScoverManager.NfcLedCoverTouchListener nfcLedCoverTouchListener, Handler handler, Context context) {
        Looper looper;
        this.mListener = nfcLedCoverTouchListener;
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

    public void onCoverTapLeft() {
        this.mHandler.obtainMessage(2).sendToTarget();
    }

    public void onCoverTapMid() {
        this.mHandler.obtainMessage(3).sendToTarget();
    }

    public void onCoverTapRight() {
        this.mHandler.obtainMessage(4).sendToTarget();
    }

    public void onCoverTouchAccept() {
        this.mHandler.obtainMessage(0).sendToTarget();
    }

    public void onCoverTouchReject() {
        this.mHandler.obtainMessage(1).sendToTarget();
    }

    public void onSystemCoverEvent(int i2, Bundle bundle) {
    }
}
