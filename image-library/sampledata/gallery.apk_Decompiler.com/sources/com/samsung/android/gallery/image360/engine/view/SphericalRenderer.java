package com.samsung.android.gallery.image360.engine.view;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLException;
import android.opengl.GLSurfaceView;
import android.util.Log;
import com.samsung.android.gallery.image360.engine.IGallery360Viewer;
import com.samsung.android.gallery.image360.engine.texture.ITexture;
import com.samsung.android.gallery.image360.engine.texture.RenderRequestListener;
import com.samsung.android.gallery.image360.engine.texture.StatusHandler;
import com.samsung.android.gallery.image360.engine.view.SphericalGlView;
import java.lang.ref.WeakReference;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import x8.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class SphericalRenderer implements GLSurfaceView.Renderer {
    private int mAutoPlayDirection = -1;
    private boolean mAutoPlayEnabled = true;
    private BackgroundView mBackgroundView;
    private boolean mBitmapRenderMode = false;
    private String mCaptureFilePath = null;
    private final WeakReference<Context> mContext;
    private ITexture mCurrentTexture;
    private float mDeltaX = 0.0f;
    private float mDeltaY = 0.0f;
    private boolean mFlingNeeded = false;
    private SphericalGlView.GlIdleListener mGlIdleListener = null;
    private volatile boolean mNeedBackground = false;
    private RenderRequestListener mRendererRequester = null;
    private int mScreenHeight = 0;
    private int mScreenWidth = 0;
    private String mSourceFilePath;
    private StatusHandler mStatusHandler;
    private HashMap<IGallery360Viewer.ViewMode, ITexture> mTextureList = null;

    public SphericalRenderer(Context context) {
        this.mContext = new WeakReference<>(context);
    }

    private void createBitmapFromGLSurface(int i2, int i7, GL10 gl10) {
        int[] iArr = new int[(i2 * i7)];
        IntBuffer wrap = IntBuffer.wrap(iArr);
        wrap.position(0);
        try {
            gl10.glReadPixels(0, 0, i2, i7, 6408, 5121, wrap);
            new SaveAsTask(this.mContext.get(), i2, i7, this.mCaptureFilePath, this.mStatusHandler, this.mSourceFilePath).execute(new int[][]{iArr});
        } catch (GLException e) {
            Log.e("SphericalRenderer", "createBitmapFromGLSurface: " + e.getMessage());
            new SaveAsTask(this.mContext.get(), i2, i7, this.mCaptureFilePath, this.mStatusHandler, this.mSourceFilePath).execute(new int[][]{null});
        } catch (Throwable th) {
            th = th;
            iArr = null;
            new SaveAsTask(this.mContext.get(), i2, i7, this.mCaptureFilePath, this.mStatusHandler, this.mSourceFilePath).execute(new int[][]{iArr});
            this.mCaptureFilePath = null;
            throw th;
        }
        this.mCaptureFilePath = null;
    }

    private void fetchCurrent360Bitmap(int i2, int i7, GL10 gl10) {
        int[] iArr = new int[(i2 * i7)];
        IntBuffer wrap = IntBuffer.wrap(iArr);
        wrap.position(0);
        int i8 = i2;
        int i10 = i7;
        try {
            gl10.glReadPixels(0, 0, i8, i10, 6408, 5121, wrap);
            new BitmapTask(i8, i10, this.mStatusHandler).execute(new int[][]{iArr});
        } catch (GLException e) {
            GLException gLException = e;
            Log.e("SphericalRenderer", "fetchCurrent360Bitmap: " + gLException.getMessage());
            new BitmapTask(i8, i10, this.mStatusHandler).execute(new int[][]{null});
        } catch (Throwable th) {
            Throwable th2 = th;
            new BitmapTask(i8, i10, this.mStatusHandler).execute(new int[][]{null});
            this.mBitmapRenderMode = false;
            throw th2;
        }
        this.mBitmapRenderMode = false;
    }

    private void requestRenderIfNeeded() {
        if (this.mRendererRequester != null) {
            if (this.mFlingNeeded) {
                updateFlingValues();
                ((a) this.mRendererRequester).a();
            } else if (this.mAutoPlayEnabled) {
                this.mCurrentTexture.setScroll(((float) this.mAutoPlayDirection) * 1.6f, 0.0f);
                ((a) this.mRendererRequester).a();
            }
        }
    }

    private void updateFlingValues() {
        float f;
        if (this.mAutoPlayEnabled) {
            f = 1.6f;
        } else {
            f = 0.001f;
        }
        this.mCurrentTexture.setScroll(this.mDeltaX, this.mDeltaY);
        if (Math.abs(this.mDeltaX) >= f || Math.abs(this.mDeltaY) >= f) {
            this.mDeltaX /= 1.05f;
            this.mDeltaY /= 1.05f;
            return;
        }
        resetFlingValues();
    }

    private void updateTextureGlAttr() {
        HashMap<IGallery360Viewer.ViewMode, ITexture> hashMap = this.mTextureList;
        if (hashMap != null) {
            for (Map.Entry<IGallery360Viewer.ViewMode, ITexture> value : hashMap.entrySet()) {
                ITexture iTexture = (ITexture) value.getValue();
                if (iTexture != null) {
                    iTexture.updateGlAttributes();
                }
            }
        }
        BackgroundView backgroundView = this.mBackgroundView;
        if (backgroundView != null) {
            backgroundView.updateGlAttributes();
        }
    }

    public void enableAutoPlay(boolean z) {
        this.mAutoPlayEnabled = z;
    }

    public void onDrawFrame(GL10 gl10) {
        BackgroundView backgroundView;
        ((a) this.mGlIdleListener).f2752a.lambda$setGlIdleListener$0();
        GLES20.glClear(16640);
        if (this.mNeedBackground && (backgroundView = this.mBackgroundView) != null) {
            backgroundView.draw();
        }
        ITexture iTexture = this.mCurrentTexture;
        if (iTexture != null) {
            iTexture.draw();
            requestRenderIfNeeded();
        }
        if (this.mCaptureFilePath != null) {
            createBitmapFromGLSurface(this.mScreenWidth, this.mScreenHeight, gl10);
        }
        if (this.mBitmapRenderMode) {
            fetchCurrent360Bitmap(this.mScreenWidth, this.mScreenHeight, gl10);
        }
    }

    public void onSurfaceChanged(GL10 gl10, int i2, int i7) {
        this.mScreenWidth = i2;
        this.mScreenHeight = i7;
        GLES20.glViewport(0, 0, i2, i7);
        ITexture iTexture = this.mCurrentTexture;
        if (iTexture != null) {
            iTexture.setScreenSize(i2, i7);
        }
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        updateTextureGlAttr();
    }

    public void resetFlingValues() {
        this.mFlingNeeded = false;
        this.mDeltaX = 0.0f;
        this.mDeltaY = 0.0f;
    }

    public void setBackgroundColor(int i2) {
        if (this.mBackgroundView != null) {
            this.mNeedBackground = true;
            this.mBackgroundView.setBackgroundColor(i2);
            return;
        }
        this.mNeedBackground = false;
    }

    public void setBackgroundView(BackgroundView backgroundView) {
        this.mBackgroundView = backgroundView;
    }

    public void setBitmapRenderMode(StatusHandler statusHandler) {
        this.mStatusHandler = statusHandler;
        this.mBitmapRenderMode = true;
    }

    public void setCaptureFileName(String str, StatusHandler statusHandler, String str2) {
        this.mCaptureFilePath = str;
        this.mStatusHandler = statusHandler;
        this.mSourceFilePath = str2;
    }

    public void setFlingValues(float f, float f5) {
        this.mDeltaX = f;
        this.mDeltaY = f5;
        this.mFlingNeeded = true;
        if (this.mAutoPlayEnabled && Math.abs(f) > 2.0f && Math.abs(f) * 1.5f > Math.abs(f5)) {
            if (f >= 0.0f) {
                this.mAutoPlayDirection = 1;
            } else {
                this.mAutoPlayDirection = -1;
            }
        }
    }

    public void setGlIdleListener(SphericalGlView.GlIdleListener glIdleListener) {
        this.mGlIdleListener = glIdleListener;
    }

    public void setRendererRequester(RenderRequestListener renderRequestListener) {
        this.mRendererRequester = renderRequestListener;
    }

    public void setTextureList(HashMap<IGallery360Viewer.ViewMode, ITexture> hashMap) {
        this.mTextureList = hashMap;
    }

    public void updateTexture(ITexture iTexture) {
        int i2;
        if (iTexture != null) {
            this.mCurrentTexture = iTexture;
            int i7 = this.mScreenHeight;
            if (i7 != 0 && (i2 = this.mScreenWidth) != 0) {
                iTexture.setScreenSize(i2, i7);
            }
        }
    }
}
