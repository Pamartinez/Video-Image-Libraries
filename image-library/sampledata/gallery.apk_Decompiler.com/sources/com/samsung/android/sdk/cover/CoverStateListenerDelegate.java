package com.samsung.android.sdk.cover;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.samsung.android.cover.ICoverStateListenerCallback;
import com.samsung.android.sdk.cover.ScoverManager;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CoverStateListenerDelegate extends ICoverStateListenerCallback.Stub {
    private static final int MSG_LISTEN_COVER_ATTACH_STATE_CHANGE = 1;
    private static final int MSG_LISTEN_COVER_SWITCH_STATE_CHANGE = 0;
    public static final int TYPE_COVER_STATE_LISTENER = 2;
    private ListenerDelegateHandler mHandler;
    private final ScoverManager.CoverStateListener mListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ListenerDelegateHandler extends Handler {
        private final WeakReference<ScoverManager.CoverStateListener> mListenerRef;

        public ListenerDelegateHandler(Looper looper, ScoverManager.CoverStateListener coverStateListener) {
            super(looper);
            this.mListenerRef = new WeakReference<>(coverStateListener);
        }

        public void handleMessage(Message message) {
            ScoverManager.CoverStateListener coverStateListener = this.mListenerRef.get();
            if (coverStateListener != null) {
                int i2 = message.what;
                boolean z = false;
                if (i2 == 0) {
                    if (message.arg1 == 1) {
                        z = true;
                    }
                    coverStateListener.onCoverSwitchStateChanged(z);
                } else if (i2 == 1) {
                    if (message.arg1 == 1) {
                        z = true;
                    }
                    coverStateListener.onCoverAttachStateChanged(z);
                }
            }
        }
    }

    public CoverStateListenerDelegate(ScoverManager.CoverStateListener coverStateListener, Handler handler, Context context) {
        Looper looper;
        this.mListener = coverStateListener;
        if (handler == null) {
            looper = context.getMainLooper();
        } else {
            looper = handler.getLooper();
        }
        this.mHandler = new ListenerDelegateHandler(looper, coverStateListener);
    }

    public ScoverManager.CoverStateListener getListener() {
        return this.mListener;
    }

    public String getListenerInfo() {
        return this.mListener.toString();
    }

    public void onCoverAttachStateChanged(boolean z) {
        Message.obtain(this.mHandler, 1, z ? 1 : 0, 0).sendToTarget();
    }

    public void onCoverSwitchStateChanged(boolean z) {
        Message.obtain(this.mHandler, 0, z ? 1 : 0, 0).sendToTarget();
    }
}
