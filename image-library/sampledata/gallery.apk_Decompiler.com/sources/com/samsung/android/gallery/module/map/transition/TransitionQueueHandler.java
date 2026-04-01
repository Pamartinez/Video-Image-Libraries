package com.samsung.android.gallery.module.map.transition;

import O3.l;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import bc.C0584a;
import java.lang.ref.WeakReference;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TransitionQueueHandler extends Handler {
    private final WeakReference<Context> mContextRef;
    private Runnable mListener;
    private boolean mTaskInProgress = false;
    private Transition mTransition = null;

    public TransitionQueueHandler(Context context) {
        this.mContextRef = new WeakReference<>(context);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleMessage$0() {
        sendEmptyMessage(1);
        Optional.ofNullable(this.mListener).ifPresent(new l(0));
    }

    public void clear() {
        Transition transition = this.mTransition;
        if (transition != null) {
            transition.cancel(true);
            this.mTransition = null;
        }
    }

    public void handleMessage(Message message) {
        Transition transition;
        if (message.what == 1) {
            this.mTaskInProgress = false;
            if (this.mTransition != null) {
                sendEmptyMessage(0);
                return;
            }
            return;
        }
        removeMessages(0);
        if (!this.mTaskInProgress && this.mTransition != null && this.mContextRef.get() != null) {
            synchronized (this) {
                transition = this.mTransition;
                this.mTransition = null;
                this.mTaskInProgress = true;
            }
            transition.setCallback(new C0584a(18, this));
            transition.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Object[]) null);
        }
    }

    public void queue(Transition transition) {
        synchronized (this) {
            this.mTransition = transition;
        }
        sendEmptyMessage(0);
    }

    public void setOnTransitionEndListener(Runnable runnable) {
        this.mListener = runnable;
    }
}
