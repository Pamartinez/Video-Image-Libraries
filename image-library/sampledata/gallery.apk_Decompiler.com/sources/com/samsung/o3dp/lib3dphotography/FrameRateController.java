package com.samsung.o3dp.lib3dphotography;

import android.os.Handler;
import android.os.HandlerThread;
import android.view.Choreographer;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FrameRateController implements IGLThread {
    private static final int LOG_INTERVAL = 300;
    private static final long MILLIS_SECONDS_PER_FRAME = 16;
    private static final long NANO_SECONDS_PER_FRAME = 16666666;
    private static final String TAG = "FrameRateController";
    /* access modifiers changed from: private */
    public final AtomicReference<Consumer<Void>> mConsumerRef = new AtomicReference<>();
    /* access modifiers changed from: private */
    public int mFrameCountPerFrameRate = 0;
    private HandlerThread mGlHandlerThread;
    private IGLThread mGlThread;
    private Handler mHandler;
    /* access modifiers changed from: private */
    public long mPrevFrameTime = 0;
    private final Choreographer.FrameCallback mRenderCallback = new Choreographer.FrameCallback() {
        public void doFrame(long j2) {
            Consumer consumer = (Consumer) FrameRateController.this.mConsumerRef.get();
            if (consumer == null) {
                LogUtil.i(FrameRateController.TAG, "Stop frame callback");
                return;
            }
            if (FrameRateController.this.mPrevFrameTime == 0) {
                FrameRateController.this.mPrevFrameTime = j2;
            }
            if ((j2 - FrameRateController.this.mPrevFrameTime) / 1000000 < FrameRateController.MILLIS_SECONDS_PER_FRAME) {
                Choreographer.getInstance().postFrameCallback(this);
                return;
            }
            FrameRateController.this.runOnGLThread(new b(0, consumer));
            Choreographer.getInstance().postFrameCallback(this);
            FrameRateController frameRateController = FrameRateController.this;
            frameRateController.mTimePerFrameRate = (j2 - FrameRateController.this.mPrevFrameTime) + frameRateController.mTimePerFrameRate;
            FrameRateController frameRateController2 = FrameRateController.this;
            int b = frameRateController2.mFrameCountPerFrameRate + 1;
            frameRateController2.mFrameCountPerFrameRate = b;
            if (b == 300) {
                LogUtil.i(FrameRateController.TAG, "Target : 60 FPS, Current " + ((1.0f / (((float) FrameRateController.this.mTimePerFrameRate) / 300.0f)) * 1.0E9f) + " FPS");
                FrameRateController.this.mFrameCountPerFrameRate = 0;
                FrameRateController.this.mTimePerFrameRate = 0;
            }
            FrameRateController.this.mPrevFrameTime = j2;
        }
    };
    /* access modifiers changed from: private */
    public long mTimePerFrameRate = 0;

    public FrameRateController() {
        HandlerThread handlerThread = new HandlerThread("FrameRateController-GLThread");
        this.mGlHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mGlHandlerThread.getLooper());
    }

    public boolean hasHandlerThread() {
        if (this.mHandler != null) {
            return true;
        }
        return false;
    }

    public void postFrameCallback(Consumer<Void> consumer) {
        this.mConsumerRef.set(consumer);
        Choreographer.getInstance().postFrameCallback(this.mRenderCallback);
    }

    public void release() {
        removeFrameCallback();
        Handler handler = this.mHandler;
        if (handler != null) {
            this.mHandler = null;
            handler.removeCallbacksAndMessages((Object) null);
        }
        HandlerThread handlerThread = this.mGlHandlerThread;
        if (handlerThread != null) {
            handlerThread.quit();
            try {
                this.mGlHandlerThread.join();
            } catch (InterruptedException e) {
                LogUtil.e(TAG, "Release GlHandlerThread : " + e);
            }
            this.mGlHandlerThread = null;
        }
        this.mGlThread = null;
        LogUtil.i(TAG, "Released");
    }

    public void removeFrameCallback() {
        this.mPrevFrameTime = 0;
        this.mConsumerRef.set((Object) null);
        Choreographer.getInstance().removeFrameCallback(this.mRenderCallback);
    }

    public void runOnGLThread(Runnable runnable) {
        try {
            IGLThread iGLThread = this.mGlThread;
            if (iGLThread != null) {
                iGLThread.runOnGLThread(runnable);
                return;
            }
            Handler handler = this.mHandler;
            if (handler != null) {
                handler.post(runnable);
            } else {
                LogUtil.w(TAG, "No available thread to execute task");
            }
        } catch (IllegalStateException e) {
            LogUtil.e(TAG, "Thread Error : " + e);
        }
    }

    public FrameRateController(IGLThread iGLThread) {
        this.mGlThread = iGLThread;
    }
}
