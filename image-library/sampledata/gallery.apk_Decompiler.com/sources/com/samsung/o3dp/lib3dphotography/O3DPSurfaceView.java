package com.samsung.o3dp.lib3dphotography;

import A.a;
import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.o3dp.lib3dphotography.mesh.AnimatedMesh;
import com.samsung.o3dp.lib3dphotography.mesh.ExtractOption;
import com.samsung.o3dp.lib3dphotography.mesh.SupportedExtension;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class O3DPSurfaceView extends GLSurfaceView implements GLSurfaceView.Renderer, IGLThread {
    private static final String TAG = "O3DPSurfaceView";
    private O3DPContextFactory mEglContextFactory;
    private final Deque<Runnable> mEventQueue = new ArrayDeque();
    private GestureDetector mGestureDetector;
    private boolean mIsSurfaceReady = false;
    private boolean mNeedBgColorClear = false;
    private O3DPhotoConfig mO3DPConfig;
    /* access modifiers changed from: private */
    public View.OnClickListener mOnClickListener;
    private final O3DPhotoPipeline mPipeline;

    public O3DPSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
        O3DPhotoPipeline o3DPhotoPipeline = new O3DPhotoPipeline(context, this.mO3DPConfig, (IGLThread) this);
        this.mPipeline = o3DPhotoPipeline;
        this.mEglContextFactory.setEGLContext(o3DPhotoPipeline.getResultRenderer().getEglContext());
        if (new O3DPAttributeParser().parseAttributes(getContext(), this, attributeSet, this.mO3DPConfig)) {
            this.mNeedBgColorClear = true;
        }
        setOnTouchListener(o3DPhotoPipeline.getInputAdapter());
    }

    private void consumeEventQueue() {
        Runnable pollFirst;
        while (!this.mEventQueue.isEmpty() && (pollFirst = this.mEventQueue.pollFirst()) != null) {
            queueEvent(pollFirst);
        }
    }

    private GestureDetector.OnGestureListener getGestureListener() {
        return new GestureDetector.SimpleOnGestureListener() {
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                if (O3DPSurfaceView.this.mOnClickListener == null) {
                    return false;
                }
                O3DPSurfaceView.this.mOnClickListener.onClick(O3DPSurfaceView.this);
                return true;
            }
        };
    }

    private void init() {
        this.mEglContextFactory = new O3DPContextFactory();
        setEGLContextClientVersion(3);
        setEGLContextFactory(this.mEglContextFactory);
        setRenderer(this);
        super.setRenderMode(0);
        this.mO3DPConfig = new O3DPhotoConfig();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDrawFrame$2() {
        super.setBackgroundColor(0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$resumeAnimation$0(Void voidR) {
        requestRender();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$surfaceCreated$1(Void voidR) {
        requestRender();
    }

    private void updateRenderSize(int i2, int i7) {
        if (i2 == 0 || i7 == 0) {
            LogUtil.e(TAG, a.d(i2, i7, "updateRenderSize failed. invalid size {", GlobalPostProcInternalPPInterface.SPLIT_REGEX, "}"));
            return;
        }
        this.mPipeline.setRenderSize(i2, i7);
        this.mPipeline.computeAnimation(i2, i7);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        GestureDetector gestureDetector = this.mGestureDetector;
        if (gestureDetector == null) {
            return super.dispatchTouchEvent(motionEvent);
        }
        gestureDetector.onTouchEvent(motionEvent);
        super.dispatchTouchEvent(motionEvent);
        return true;
    }

    public O3DPhotoConfig getConfig() {
        return this.mO3DPConfig;
    }

    public O3DPhotoPipeline getPipeline() {
        return this.mPipeline;
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mPipeline.release();
    }

    public void onDrawFrame(GL10 gl10) {
        if (this.mPipeline.isRenderStarted()) {
            if (this.mNeedBgColorClear) {
                post(new b(1, this));
                this.mNeedBgColorClear = false;
            }
            this.mPipeline.consumeNextFrame();
            this.mPipeline.visualize();
        }
    }

    public void onMeasure(int i2, int i7) {
        super.onMeasure(i2, i7);
        int size = View.MeasureSpec.getSize(i2);
        int size2 = View.MeasureSpec.getSize(i7);
        int width = this.mPipeline.getResultRenderer().getWidth();
        int height = this.mPipeline.getResultRenderer().getHeight();
        if (width <= 0 || height <= 0 || this.mPipeline.getConfig().isPanorama()) {
            setMeasuredDimension(size, size2);
            return;
        }
        float f = ((float) width) / ((float) height);
        float f5 = (float) size;
        float f8 = ((float) size2) * f;
        if (f5 < f8) {
            setMeasuredDimension(size, Math.round(f5 / f));
        } else {
            setMeasuredDimension(Math.round(f8), size2);
        }
    }

    public void onSurfaceChanged(GL10 gl10, int i2, int i7) {
        LogUtil.d(TAG, "onSurfaceChanged: " + i2 + "x" + i7);
        if (!this.mIsSurfaceReady) {
            consumeEventQueue();
            this.mIsSurfaceReady = true;
        }
        updateRenderSize(i2, i7);
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        LogUtil.i(TAG, "onSurfaceCreated");
    }

    public void pauseAnimation() {
        O3DPhotoPipeline o3DPhotoPipeline = this.mPipeline;
        if (o3DPhotoPipeline == null) {
            return;
        }
        if (!o3DPhotoPipeline.isRenderStarted()) {
            LogUtil.w(TAG, "Failed to pause animation because rendering had not begun");
        } else {
            this.mPipeline.pauseAnimation();
        }
    }

    public void renderLiveEffect(String str, O3DPListener o3DPListener, SupportedExtension supportedExtension) {
        try {
            AnimatedMesh extract = MeshExtractor.extract(getContext(), str, supportedExtension);
            if (extract == null) {
                LogUtil.e(TAG, "Failed to render live effect: No mesh extracted from file");
                return;
            }
            this.mPipeline.render(extract, o3DPListener);
            updateRenderSize(getWidth(), getHeight());
        } catch (IOException e) {
            LogUtil.e(TAG, "Failed to render live effect: Please check file is valid", e);
        }
    }

    public void resumeAnimation() {
        O3DPhotoPipeline o3DPhotoPipeline = this.mPipeline;
        if (o3DPhotoPipeline == null) {
            return;
        }
        if (!o3DPhotoPipeline.isRenderStarted()) {
            LogUtil.w(TAG, "Failed to resume animation because rendering had not begun");
        } else {
            this.mPipeline.postFrameCallback(new g(0, this));
        }
    }

    public void runOnGLThread(Runnable runnable) {
        if (!this.mIsSurfaceReady) {
            this.mEventQueue.addLast(runnable);
            LogUtil.i(TAG, "runOnGLThread : Surface is not ready, EventQueue's size : " + this.mEventQueue.size());
            return;
        }
        queueEvent(runnable);
    }

    public void setBackgroundColor(int i2) {
        this.mO3DPConfig.setBgColor(i2);
    }

    public void setEGLContextClientVersion(int i2) {
        this.mEglContextFactory.setEGLContextClientVersion(i2);
        super.setEGLContextClientVersion(i2);
    }

    public void setImageBitmap(Bitmap bitmap, O3DPListener o3DPListener) {
        LogUtil.i(TAG, "setImageBitmap");
        this.mPipeline.render(bitmap, o3DPListener);
        updateRenderSize(getWidth(), getHeight());
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
        if (onClickListener == null) {
            this.mGestureDetector = null;
        } else {
            this.mGestureDetector = new GestureDetector(getContext(), getGestureListener());
        }
    }

    public void setRenderMode(int i2) {
        super.setRenderMode(i2);
        if (i2 == 1) {
            pauseAnimation();
        } else if (i2 == 0) {
            resumeAnimation();
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        LogUtil.i(TAG, "surfaceCreated");
        super.surfaceCreated(surfaceHolder);
        this.mPipeline.postFrameCallback(new g(1, this));
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        LogUtil.i(TAG, "surfaceDestroyed");
        super.surfaceDestroyed(surfaceHolder);
        this.mPipeline.pauseAnimation();
        this.mIsSurfaceReady = false;
    }

    public void renderLiveEffect(String str, O3DPListener o3DPListener, ExtractOption extractOption) {
        try {
            AnimatedMesh extract = MeshExtractor.extract(str, extractOption);
            if (extract == null) {
                LogUtil.e(TAG, "Failed to render live effect: No mesh extracted from file");
                return;
            }
            this.mPipeline.render(extract, o3DPListener);
            updateRenderSize(getWidth(), getHeight());
        } catch (IOException e) {
            LogUtil.e(TAG, "Failed to render live effect: Please check file is valid", e);
        }
    }
}
