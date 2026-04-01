package com.samsung.android.gallery.image360.engine.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.WindowManager;
import com.samsung.android.gallery.image360.R$color;
import com.samsung.android.gallery.image360.engine.IGallery360Viewer;
import com.samsung.android.gallery.image360.engine.IOnGLIdleListener;
import com.samsung.android.gallery.image360.engine.texture.DefaultSpreadTexture;
import com.samsung.android.gallery.image360.engine.texture.DualTexture;
import com.samsung.android.gallery.image360.engine.texture.ITexture;
import com.samsung.android.gallery.image360.engine.texture.MirrorBallTexture;
import com.samsung.android.gallery.image360.engine.texture.PanoramaTexture;
import com.samsung.android.gallery.image360.engine.texture.RenderRequestListener;
import com.samsung.android.gallery.image360.engine.texture.SelfieSpreadTexture;
import com.samsung.android.gallery.image360.engine.texture.StatusHandler;
import com.samsung.android.gallery.image360.engine.texture.StereographicTexture;
import com.samsung.android.gallery.image360.engine.texture.TextureManager;
import i.C0212a;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import x8.a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SphericalGlView extends GLSurfaceView implements SensorEventListener {
    private boolean mAutoPlayEnabled;
    /* access modifiers changed from: private */
    public Bitmap mBitmap;
    private ITexture mCurrentTexture;
    private float mFlingDeltaX;
    private float mFlingDeltaY;
    /* access modifiers changed from: private */
    public final ArrayDeque<IOnGLIdleListener> mIdleListeners = new ArrayDeque<>();
    private final IdleRunner mIdleRunner = new IdleRunner(this, 0);
    private boolean mIsSensorEnabled;
    private final HashMap<IGallery360Viewer.ViewMode, ITexture> mLoadedTextures;
    private float mPreviousDisplacement;
    private final RenderRequestListener mRenderRequestListener;
    private final SphericalRenderer mRenderer;
    private SensorManager mSensorManager;
    private final StatusHandler mStatusHandler;
    private final TextureManager mTextureManager;
    private long mTimeStamp;
    private int mTouchCount;
    private final TouchPoint mTouchPoint;
    private final WindowManager mWindowManager;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface GlIdleListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class IdleRunner implements Runnable {
        private boolean mActive;

        public /* synthetic */ IdleRunner(SphericalGlView sphericalGlView, int i2) {
            this();
        }

        /* access modifiers changed from: private */
        public void enable() {
            if (!this.mActive) {
                this.mActive = true;
                SphericalGlView.this.queueEvent(this);
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:12:0x0027, code lost:
            r1.onGLIdle();
            r1 = com.samsung.android.gallery.image360.engine.view.SphericalGlView.c(r3.this$0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0030, code lost:
            monitor-enter(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x003b, code lost:
            if (com.samsung.android.gallery.image360.engine.view.SphericalGlView.c(r3.this$0).isEmpty() != false) goto L_0x0043;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x003d, code lost:
            enable();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:18:0x0041, code lost:
            r3 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0043, code lost:
            r3.this$0.requestRender();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:0x004e, code lost:
            if (com.samsung.android.gallery.image360.engine.view.SphericalGlView.b(r3.this$0) == null) goto L_0x006c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x005a, code lost:
            if (com.samsung.android.gallery.image360.engine.view.SphericalGlView.b(r3.this$0).isRecycled() != false) goto L_0x006c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x005c, code lost:
            android.util.Log.d("SphericalGlView", "Recycling the bitmap!");
            com.samsung.android.gallery.image360.engine.view.SphericalGlView.b(r3.this$0).recycle();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x006c, code lost:
            monitor-exit(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x006d, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x006f, code lost:
            throw r3;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r3 = this;
                com.samsung.android.gallery.image360.engine.view.SphericalGlView r0 = com.samsung.android.gallery.image360.engine.view.SphericalGlView.this
                java.util.ArrayDeque r0 = r0.mIdleListeners
                monitor-enter(r0)
                r1 = 0
                r3.mActive = r1     // Catch:{ all -> 0x0018 }
                com.samsung.android.gallery.image360.engine.view.SphericalGlView r1 = com.samsung.android.gallery.image360.engine.view.SphericalGlView.this     // Catch:{ all -> 0x0018 }
                java.util.ArrayDeque r1 = r1.mIdleListeners     // Catch:{ all -> 0x0018 }
                boolean r1 = r1.isEmpty()     // Catch:{ all -> 0x0018 }
                if (r1 == 0) goto L_0x001a
                monitor-exit(r0)     // Catch:{ all -> 0x0018 }
                return
            L_0x0018:
                r3 = move-exception
                goto L_0x0070
            L_0x001a:
                com.samsung.android.gallery.image360.engine.view.SphericalGlView r1 = com.samsung.android.gallery.image360.engine.view.SphericalGlView.this     // Catch:{ all -> 0x0018 }
                java.util.ArrayDeque r1 = r1.mIdleListeners     // Catch:{ all -> 0x0018 }
                java.lang.Object r1 = r1.removeFirst()     // Catch:{ all -> 0x0018 }
                com.samsung.android.gallery.image360.engine.IOnGLIdleListener r1 = (com.samsung.android.gallery.image360.engine.IOnGLIdleListener) r1     // Catch:{ all -> 0x0018 }
                monitor-exit(r0)     // Catch:{ all -> 0x0018 }
                r1.onGLIdle()
                com.samsung.android.gallery.image360.engine.view.SphericalGlView r0 = com.samsung.android.gallery.image360.engine.view.SphericalGlView.this
                java.util.ArrayDeque r1 = r0.mIdleListeners
                monitor-enter(r1)
                com.samsung.android.gallery.image360.engine.view.SphericalGlView r0 = com.samsung.android.gallery.image360.engine.view.SphericalGlView.this     // Catch:{ all -> 0x0041 }
                java.util.ArrayDeque r0 = r0.mIdleListeners     // Catch:{ all -> 0x0041 }
                boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0041 }
                if (r0 != 0) goto L_0x0043
                r3.enable()     // Catch:{ all -> 0x0041 }
                goto L_0x006c
            L_0x0041:
                r3 = move-exception
                goto L_0x006e
            L_0x0043:
                com.samsung.android.gallery.image360.engine.view.SphericalGlView r0 = com.samsung.android.gallery.image360.engine.view.SphericalGlView.this     // Catch:{ all -> 0x0041 }
                r0.requestRender()     // Catch:{ all -> 0x0041 }
                com.samsung.android.gallery.image360.engine.view.SphericalGlView r0 = com.samsung.android.gallery.image360.engine.view.SphericalGlView.this     // Catch:{ all -> 0x0041 }
                android.graphics.Bitmap r0 = r0.mBitmap     // Catch:{ all -> 0x0041 }
                if (r0 == 0) goto L_0x006c
                com.samsung.android.gallery.image360.engine.view.SphericalGlView r0 = com.samsung.android.gallery.image360.engine.view.SphericalGlView.this     // Catch:{ all -> 0x0041 }
                android.graphics.Bitmap r0 = r0.mBitmap     // Catch:{ all -> 0x0041 }
                boolean r0 = r0.isRecycled()     // Catch:{ all -> 0x0041 }
                if (r0 != 0) goto L_0x006c
                java.lang.String r0 = "SphericalGlView"
                java.lang.String r2 = "Recycling the bitmap!"
                android.util.Log.d(r0, r2)     // Catch:{ all -> 0x0041 }
                com.samsung.android.gallery.image360.engine.view.SphericalGlView r3 = com.samsung.android.gallery.image360.engine.view.SphericalGlView.this     // Catch:{ all -> 0x0041 }
                android.graphics.Bitmap r3 = r3.mBitmap     // Catch:{ all -> 0x0041 }
                r3.recycle()     // Catch:{ all -> 0x0041 }
            L_0x006c:
                monitor-exit(r1)     // Catch:{ all -> 0x0041 }
                return
            L_0x006e:
                monitor-exit(r1)     // Catch:{ all -> 0x0041 }
                throw r3
            L_0x0070:
                monitor-exit(r0)     // Catch:{ all -> 0x0018 }
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.image360.engine.view.SphericalGlView.IdleRunner.run():void");
        }

        private IdleRunner() {
            this.mActive = false;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class TouchPoint {
        /* access modifiers changed from: private */

        /* renamed from: x  reason: collision with root package name */
        public float f2626x;
        /* access modifiers changed from: private */
        public float y;

        public /* synthetic */ TouchPoint(int i2) {
            this();
        }

        private TouchPoint() {
        }
    }

    public SphericalGlView(Context context, boolean z, int i2, int i7) {
        super(context);
        StatusHandler statusHandler = new StatusHandler();
        this.mStatusHandler = statusHandler;
        a aVar = new a(this);
        this.mRenderRequestListener = aVar;
        this.mTimeStamp = 0;
        this.mIsSensorEnabled = false;
        this.mAutoPlayEnabled = true;
        this.mBitmap = null;
        SphericalRenderer sphericalRenderer = new SphericalRenderer(context);
        this.mRenderer = sphericalRenderer;
        setEGLContextClientVersion(2);
        sphericalRenderer.setRendererRequester(aVar);
        setGlIdleListener();
        TextureManager textureManager = new TextureManager();
        this.mTextureManager = textureManager;
        textureManager.setStatusHandler(statusHandler);
        HashMap<IGallery360Viewer.ViewMode, ITexture> hashMap = new HashMap<>();
        this.mLoadedTextures = hashMap;
        if (!z || i2 <= 0 || i7 <= 0) {
            createTextures();
        } else {
            createSelfie360Textures(i2, i7);
        }
        sphericalRenderer.setTextureList(hashMap);
        setRenderer(sphericalRenderer);
        setRenderMode(0);
        this.mTouchPoint = new TouchPoint(0);
        this.mPreviousDisplacement = -1.0f;
        this.mWindowManager = (WindowManager) getContext().getSystemService("window");
        BackgroundView backgroundView = new BackgroundView();
        backgroundView.setStatusHandler(statusHandler);
        sphericalRenderer.setBackgroundView(backgroundView);
        sphericalRenderer.setBackgroundColor(context.getColor(R$color.daynight_default_background));
    }

    private void addOnGLIdleListener(IOnGLIdleListener iOnGLIdleListener, Bitmap bitmap) {
        synchronized (this.mIdleListeners) {
            if (iOnGLIdleListener != null) {
                try {
                    this.mBitmap = bitmap;
                    this.mIdleListeners.addLast(iOnGLIdleListener);
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    private void checkAutoPlayEnabled(float f, float f5, float f8) {
        if (this.mAutoPlayEnabled) {
            f5 = 0.0f;
        }
        this.mCurrentTexture.setSensorScroll(f * f8, f5 * f8);
    }

    private void clearGLIdleListener() {
        synchronized (this.mIdleListeners) {
            this.mIdleListeners.clear();
        }
    }

    private void createSelfie360Textures(int i2, int i7) {
        SelfieSpreadTexture selfieSpreadTexture = new SelfieSpreadTexture(i2, i7);
        initializeTexture(selfieSpreadTexture);
        this.mLoadedTextures.put(IGallery360Viewer.ViewMode.SPREAD, selfieSpreadTexture);
        PanoramaTexture panoramaTexture = new PanoramaTexture();
        initializeTexture(panoramaTexture);
        this.mLoadedTextures.put(IGallery360Viewer.ViewMode.PANORAMA, panoramaTexture);
    }

    private void createTextures() {
        DefaultSpreadTexture defaultSpreadTexture = new DefaultSpreadTexture();
        initializeTexture(defaultSpreadTexture);
        this.mLoadedTextures.put(IGallery360Viewer.ViewMode.SPREAD, defaultSpreadTexture);
        StereographicTexture stereographicTexture = new StereographicTexture();
        initializeTexture(stereographicTexture);
        this.mLoadedTextures.put(IGallery360Viewer.ViewMode.LITTLE_PLANET, stereographicTexture);
        DualTexture dualTexture = new DualTexture();
        initializeTexture(dualTexture);
        this.mLoadedTextures.put(IGallery360Viewer.ViewMode.DUAL, dualTexture);
        PanoramaTexture panoramaTexture = new PanoramaTexture();
        initializeTexture(panoramaTexture);
        this.mLoadedTextures.put(IGallery360Viewer.ViewMode.PANORAMA, panoramaTexture);
        MirrorBallTexture mirrorBallTexture = new MirrorBallTexture();
        initializeTexture(mirrorBallTexture);
        this.mLoadedTextures.put(IGallery360Viewer.ViewMode.MIRROR_BALL, mirrorBallTexture);
    }

    private void disableSensor() {
        SensorManager sensorManager = this.mSensorManager;
        if (sensorManager != null) {
            sensorManager.unregisterListener(this, sensorManager.getDefaultSensor(4));
        }
    }

    private void enableSensor() {
        this.mTimeStamp = 0;
        if (this.mSensorManager == null) {
            this.mSensorManager = (SensorManager) getContext().getSystemService("sensor");
        }
        SensorManager sensorManager = this.mSensorManager;
        if (sensorManager != null) {
            sensorManager.registerListener(this, sensorManager.getDefaultSensor(4), 1);
        } else {
            Log.e("SphericalGlView", "enable sensor failed, mSensorManager is null");
        }
    }

    private void initializeTexture(ITexture iTexture) {
        iTexture.setStatusHandler(this.mStatusHandler);
        iTexture.setTextureManager(this.mTextureManager);
        iTexture.setRendererRequester(this.mRenderRequestListener);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setGlIdleListener$0() {
        synchronized (this.mIdleListeners) {
            try {
                if (!this.mIdleListeners.isEmpty()) {
                    this.mIdleRunner.enable();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void resetAllTextures(IGallery360Viewer.DefaultPlaybackDirection defaultPlaybackDirection) {
        for (Map.Entry<IGallery360Viewer.ViewMode, ITexture> value : this.mLoadedTextures.entrySet()) {
            ITexture iTexture = (ITexture) value.getValue();
            if (iTexture != null) {
                iTexture.reset(defaultPlaybackDirection);
            }
        }
    }

    private void resetData() {
        this.mRenderer.resetFlingValues();
    }

    private void setGlIdleListener() {
        this.mRenderer.setGlIdleListener(new a(this));
    }

    private void setSensorScrollToTexture(float f, float f5, float f8) {
        if (this.mCurrentTexture != null) {
            int rotation = this.mWindowManager.getDefaultDisplay().getRotation();
            if (rotation == 1) {
                checkAutoPlayEnabled(-f5, f, f8);
            } else if (rotation == 3) {
                checkAutoPlayEnabled(f5, -f, f8);
            } else {
                checkAutoPlayEnabled(f, f5, f8);
            }
            requestRender();
        }
    }

    private void updateScroll(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mTouchPoint.f2626x = (float) ((int) motionEvent.getRawX());
            this.mTouchPoint.y = (float) ((int) motionEvent.getRawY());
            this.mFlingDeltaX = 0.0f;
            this.mFlingDeltaY = 0.0f;
        } else if (action == 1) {
            this.mRenderer.setFlingValues(this.mFlingDeltaX, this.mFlingDeltaY);
        } else if (action == 2) {
            this.mFlingDeltaX = motionEvent.getRawX() - this.mTouchPoint.f2626x;
            float rawY = motionEvent.getRawY() - this.mTouchPoint.y;
            this.mFlingDeltaY = rawY;
            this.mCurrentTexture.setScroll(this.mFlingDeltaX, rawY);
            this.mTouchPoint.f2626x = motionEvent.getRawX();
            this.mTouchPoint.y = motionEvent.getRawY();
        }
    }

    private void updateZoom(MotionEvent motionEvent) {
        MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
        MotionEvent.PointerCoords pointerCoords2 = new MotionEvent.PointerCoords();
        motionEvent.getPointerCoords(0, pointerCoords);
        motionEvent.getPointerCoords(1, pointerCoords2);
        int action = motionEvent.getAction();
        if (action == 0) {
            float f = pointerCoords2.x;
            float f5 = pointerCoords.x;
            float f8 = f - f5;
            float f10 = pointerCoords2.y;
            float f11 = pointerCoords.y;
            this.mPreviousDisplacement = (float) Math.sqrt((double) C0212a.a(f10, f11, f10 - f11, (f - f5) * f8));
        } else if (action == 1) {
            this.mPreviousDisplacement = -1.0f;
        } else if (action == 2) {
            float f12 = pointerCoords2.x;
            float f13 = pointerCoords.x;
            float f14 = f12 - f13;
            float f15 = pointerCoords2.y;
            float f16 = pointerCoords.y;
            float sqrt = (float) Math.sqrt((double) C0212a.a(f15, f16, f15 - f16, (f12 - f13) * f14));
            float f17 = this.mPreviousDisplacement;
            if (f17 != -1.0f) {
                this.mCurrentTexture.setZoom(sqrt - f17);
            }
            this.mPreviousDisplacement = sqrt;
        }
    }

    public void captureScreen(String str, Callable<Void> callable, String str2) {
        if (str != null && !str.isEmpty()) {
            this.mStatusHandler.setSaveCompletedListener(str, callable);
            this.mRenderer.setCaptureFileName(str, this.mStatusHandler, str2);
            requestRender();
        }
    }

    public void enableAutoPlay(boolean z) {
        this.mAutoPlayEnabled = z;
        this.mRenderer.enableAutoPlay(z);
        requestRender();
    }

    public void getCurrent360Bitmap(Consumer<Bitmap> consumer) {
        this.mStatusHandler.set360BitmapCreatedListener(consumer);
        this.mRenderer.setBitmapRenderMode(this.mStatusHandler);
        requestRender();
    }

    public int getErrorCode() {
        return this.mStatusHandler.getErrorCode();
    }

    public IGallery360Viewer.SaveStatus getSaveStatus(String str) {
        return this.mStatusHandler.getSaveStatus(str);
    }

    public void init(Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled() || bitmap.getWidth() > 4096 || bitmap.getHeight() > 4096) {
            if (bitmap == null || bitmap.isRecycled()) {
                Log.d("SphericalGlView", "bitmap == null || bitmap.isRecycled()");
            } else {
                Log.d("SphericalGlView", "bitmap.getWidth():" + bitmap.getWidth() + ", bitmap.getHeight():" + bitmap.getHeight());
            }
            throw new IllegalArgumentException("Bitmap null or size not supported");
        }
        addOnGLIdleListener(this.mTextureManager.requestGlUpload(bitmap), bitmap);
    }

    public void onDestroy() {
        clearGLIdleListener();
        stopAutoRotation();
        TextureManager textureManager = this.mTextureManager;
        if (textureManager != null) {
            textureManager.clearGlTexture();
        }
        this.mLoadedTextures.clear();
        this.mStatusHandler.onDestroy();
    }

    public void onPause() {
        if (this.mIsSensorEnabled) {
            disableSensor();
        }
        if (this.mAutoPlayEnabled) {
            this.mRenderer.enableAutoPlay(false);
        }
    }

    public void onResume() {
        if (this.mIsSensorEnabled) {
            this.mRenderer.resetFlingValues();
            enableSensor();
        } else {
            requestRender();
        }
        if (this.mAutoPlayEnabled) {
            this.mRenderer.enableAutoPlay(true);
        }
    }

    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == 4) {
            long j2 = this.mTimeStamp;
            if (j2 != 0) {
                float[] fArr = sensorEvent.values;
                setSensorScrollToTexture(fArr[0], fArr[1], ((float) (sensorEvent.timestamp - j2)) * 1.0E-9f);
            }
            this.mTimeStamp = sensorEvent.timestamp;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mCurrentTexture == null) {
            return false;
        }
        int action = motionEvent.getAction();
        if (this.mIsSensorEnabled) {
            if (action == 1 || action == 3) {
                enableSensor();
            } else if (action == 0 || action == 2) {
                disableSensor();
            }
        }
        if (this.mAutoPlayEnabled) {
            if (action == 1 || action == 3) {
                this.mRenderer.enableAutoPlay(true);
            } else if (action == 0 || action == 2) {
                this.mRenderer.enableAutoPlay(false);
            }
        }
        this.mRenderer.resetFlingValues();
        if (this.mTouchCount != motionEvent.getPointerCount()) {
            this.mTouchCount = motionEvent.getPointerCount();
            motionEvent.setAction(0);
        }
        int pointerCount = motionEvent.getPointerCount();
        if (pointerCount == 1) {
            updateScroll(motionEvent);
        } else if (pointerCount == 2) {
            updateZoom(motionEvent);
        }
        requestRender();
        return false;
    }

    public void resetView(IGallery360Viewer.DefaultPlaybackDirection defaultPlaybackDirection) {
        this.mRenderer.resetFlingValues();
        ITexture iTexture = this.mCurrentTexture;
        if (iTexture != null) {
            iTexture.reset(defaultPlaybackDirection);
        } else {
            resetAllTextures(defaultPlaybackDirection);
        }
        requestRender();
    }

    public void setBackgroundColor(int i2) {
        this.mRenderer.setBackgroundColor(i2);
        requestRender();
    }

    public void setErrorListener(Callable<Void> callable) {
        this.mStatusHandler.setErrorListener(callable);
    }

    public void setViewMode(IGallery360Viewer.ViewMode viewMode) {
        if (viewMode != null) {
            resetData();
            ITexture iTexture = this.mLoadedTextures.get(viewMode);
            this.mCurrentTexture = iTexture;
            if (iTexture != null) {
                this.mRenderer.updateTexture(iTexture);
            }
            requestRender();
            return;
        }
        throw new IllegalArgumentException("View mode null");
    }

    public void startAutoRotation() {
        if (!this.mIsSensorEnabled) {
            this.mIsSensorEnabled = true;
            this.mRenderer.resetFlingValues();
            enableSensor();
        }
    }

    public void stopAutoRotation() {
        if (this.mIsSensorEnabled) {
            this.mIsSensorEnabled = false;
            disableSensor();
        }
    }

    public void onAccuracyChanged(Sensor sensor, int i2) {
    }
}
