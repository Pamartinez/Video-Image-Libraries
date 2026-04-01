package com.samsung.android.sdk.cover;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.samsung.android.cover.CoverState;
import com.samsung.android.cover.ICoverManagerCallback;
import com.samsung.android.sdk.cover.ScoverManager;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CoverListenerDelegate extends ICoverManagerCallback.Stub {
    /* access modifiers changed from: private */
    public static final String TAG = "ScoverManager";
    static final int hasAttachFieldVersion = 16842752;
    static final int hasModelFieldVersion = 16908288;
    static final int hasVisibleRectVersion = 17563648;
    private ListenerDelegateHandler mHandler;
    private final ScoverManager.StateListener mListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ListenerDelegateHandler extends Handler {
        private final WeakReference<ScoverManager.StateListener> mListenerRef;

        public ListenerDelegateHandler(Looper looper, ScoverManager.StateListener stateListener) {
            super(looper);
            this.mListenerRef = new WeakReference<>(stateListener);
        }

        public void handleMessage(Message message) {
            ScoverState scoverState;
            ScoverManager.StateListener stateListener = this.mListenerRef.get();
            if (stateListener != null) {
                CoverState coverState = (CoverState) message.obj;
                if (coverState != null) {
                    if (ScoverManager.isSupportableVersion(CoverListenerDelegate.hasModelFieldVersion)) {
                        scoverState = new ScoverState(coverState.switchState, coverState.type, coverState.color, coverState.widthPixel, coverState.heightPixel, coverState.attached, coverState.model);
                    } else if (ScoverManager.isSupportableVersion(CoverListenerDelegate.hasAttachFieldVersion)) {
                        scoverState = new ScoverState(coverState.switchState, coverState.type, coverState.color, coverState.widthPixel, coverState.heightPixel, coverState.attached);
                    } else {
                        scoverState = new ScoverState(coverState.switchState, coverState.type, coverState.color, coverState.widthPixel, coverState.heightPixel);
                    }
                    if (ScoverManager.isSupportableVersion(CoverListenerDelegate.hasVisibleRectVersion)) {
                        scoverState.setVisibleRect(coverState.getVisibleRect());
                    }
                    stateListener.onCoverStateChanged(scoverState);
                    return;
                }
                Log.e(CoverListenerDelegate.TAG, "coverState : null");
            }
        }
    }

    public CoverListenerDelegate(ScoverManager.StateListener stateListener, Handler handler, Context context) {
        Looper looper;
        this.mListener = stateListener;
        if (handler == null) {
            looper = context.getMainLooper();
        } else {
            looper = handler.getLooper();
        }
        this.mHandler = new ListenerDelegateHandler(looper, stateListener);
    }

    public void coverCallback(CoverState coverState) {
        Message obtain = Message.obtain();
        obtain.what = 0;
        obtain.obj = coverState;
        this.mHandler.sendMessage(obtain);
    }

    public ScoverManager.StateListener getListener() {
        return this.mListener;
    }

    public String getListenerInfo() {
        return this.mListener.toString();
    }
}
