package com.samsung.android.sum.core.message;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.samsung.android.sum.core.SLog;
import i.C0212a;
import java.util.concurrent.Future;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FutureHandler<T> implements AutoCloseable {
    private static final int CANCEL = 0;
    /* access modifiers changed from: private */
    public static final String TAG = SLog.tagOf(FutureHandler.class);
    long delayInMillis;
    private Handler handler = new Handler(this.thread.getLooper()) {
        public void handleMessage(Message message) {
            if (message.what == 0) {
                Future future = (Future) message.obj;
                if (!future.isDone() && !future.isCancelled()) {
                    String access$000 = FutureHandler.TAG;
                    SLog.i(access$000, "cancel future due to timeout " + FutureHandler.this.delayInMillis + "ms");
                    future.cancel(true);
                    if (FutureHandler.this.onCancelListener != null) {
                        FutureHandler.this.onCancelListener.run();
                        return;
                    }
                    return;
                }
                return;
            }
            super.handleMessage(message);
        }
    };
    private Future<T> lastFuture;
    /* access modifiers changed from: private */
    public Runnable onCancelListener;
    private HandlerThread thread;

    public FutureHandler() {
        HandlerThread handlerThread = new HandlerThread(C0212a.p(new StringBuilder(), TAG, ": future-handler-thread"));
        this.thread = handlerThread;
        handlerThread.start();
    }

    public synchronized void cancelIfDelayed(Future<T> future, long j2) {
        this.handler.removeMessages(0);
        Handler handler2 = this.handler;
        handler2.sendMessageDelayed(handler2.obtainMessage(0, future), j2);
        this.lastFuture = future;
        this.delayInMillis = j2;
    }

    public void close() {
        try {
            Future<T> future = this.lastFuture;
            if (future != null && !future.isDone() && !this.lastFuture.isCancelled()) {
                this.lastFuture.cancel(true);
            }
            HandlerThread handlerThread = this.thread;
            if (handlerThread != null) {
                handlerThread.quit();
            }
        } catch (Exception e) {
            String str = TAG;
            SLog.w(str, "fail to quit future-handle-thread: " + e);
        } catch (Throwable th) {
            this.onCancelListener = null;
            this.thread = null;
            this.handler = null;
            this.lastFuture = null;
            throw th;
        }
        this.onCancelListener = null;
        this.thread = null;
        this.handler = null;
        this.lastFuture = null;
    }

    public synchronized void delayCancel() {
        Future<T> future = this.lastFuture;
        if (future != null && !future.isDone() && !this.lastFuture.isCancelled()) {
            this.handler.removeMessages(0);
            Handler handler2 = this.handler;
            handler2.sendMessageDelayed(handler2.obtainMessage(0, this.lastFuture), this.delayInMillis);
        }
    }

    public void setOnCancelListener(Runnable runnable) {
        this.onCancelListener = runnable;
    }
}
