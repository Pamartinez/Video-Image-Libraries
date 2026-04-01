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
class LedSystemEventListenerDelegate extends INfcLedCoverTouchListenerCallback.Stub {
    private static final int MSG_SYSTEM_COVER_EVENT = 0;
    private ListenerDelegateHandler mHandler;
    private ScoverManager.LedSystemEventListener mListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ListenerDelegateHandler extends Handler {
        private final WeakReference<ScoverManager.LedSystemEventListener> mListenerRef;

        public ListenerDelegateHandler(Looper looper, ScoverManager.LedSystemEventListener ledSystemEventListener) {
            super(looper);
            this.mListenerRef = new WeakReference<>(ledSystemEventListener);
        }

        public void handleMessage(Message message) {
            ScoverManager.LedSystemEventListener ledSystemEventListener = this.mListenerRef.get();
            if (ledSystemEventListener != null && message.what == 0) {
                ledSystemEventListener.onSystemCoverEvent(message.arg1, (Bundle) message.obj);
            }
        }
    }

    public LedSystemEventListenerDelegate(ScoverManager.LedSystemEventListener ledSystemEventListener, Handler handler, Context context) {
        Looper looper;
        this.mListener = ledSystemEventListener;
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
        Message obtainMessage = this.mHandler.obtainMessage(0);
        obtainMessage.arg1 = i2;
        obtainMessage.obj = bundle;
        obtainMessage.sendToTarget();
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
