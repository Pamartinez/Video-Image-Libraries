package com.samsung.o3dp.lib3dphotography;

import A0.l;
import N2.j;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.SensorManager;
import android.os.RemoteException;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.o3dp.lib3dphotography.MeshUtils;
import com.samsung.o3dp.lib3dphotography.animation.Animation;
import com.samsung.o3dp.lib3dphotography.animation.AnimationManager;
import com.samsung.o3dp.lib3dphotography.graphics.EGLHandler;
import com.samsung.o3dp.lib3dphotography.graphics.ImageRenderer;
import com.samsung.o3dp.lib3dphotography.graphics.SharedRenderParams;
import com.samsung.o3dp.lib3dphotography.graphics.Texture;
import com.samsung.o3dp.lib3dphotography.graphics.Vector3;
import com.samsung.o3dp.lib3dphotography.interaction.InputAdapter;
import com.samsung.o3dp.lib3dphotography.interaction.O3DPSensor;
import com.samsung.o3dp.lib3dphotography.nativelib.JNI;
import com.samsung.o3dp.lib3dphotography.utils.ColorUtil;
import com.samsung.o3dp.lib3dphotography.utils.DebugUtil;
import com.samsung.o3dp.lib3dphotography.utils.ImageUtil;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import com.samsung.o3dp.lib3dphotography.utils.RemasterUtil;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class O3DPContext extends OnConfigChangedListener {
    private static final double FAR_PLANE = 100.0d;
    private static final double FOV = 58.5d;
    private static final double NEAR_PLANE = 0.05d;
    private static final Boolean REIMPORT_AFTER_WRITE_MESH = Boolean.FALSE;
    private static final String TAG = "O3DPContext";
    public final Object mAbortSyncLock = new Object();
    private final AnimationManager mAnimationManager;
    private boolean mAreObjectsScattered = false;
    private int mBgColor = 0;
    private boolean mBlockStaticAnimation;
    private int mCropHeight = 0;
    private int mCropWidth = 0;
    private final Display mDisplay;
    private final EGLHandler mEglHandler;
    private Point mFocusPoint;
    private CompletableFuture<DepthMap> mFutureDepthMap = new CompletableFuture<>();
    private InputAdapter mInputAdapter;
    private boolean mIsFloating = false;
    private boolean mIsNoSegInCenter = false;
    private boolean mIsSegObjectBehind = false;
    private final O3DPhotoConfig mO3DPConfig;
    List<O3DPObject> mO3DPObjects = new ArrayList();
    private final O3DPSensor mO3DPSensor;
    private Bitmap mOriginBmp;
    private volatile boolean mRecordAbortRequested;
    private final ResultRenderer mRenderer;
    private int mSalientObjCnt;
    private int mTargetRenderHeight = 0;
    private int mTargetRenderWidth = 0;
    private volatile boolean mVideoRecording;
    private Texture m_colorTex = null;
    private Texture m_depthTex = null;
    List<Face> m_faces = new ArrayList();
    private Texture m_foregroundMaskTex = null;
    private ImageRenderer m_imageRenderer;
    private Texture m_outTex = null;
    private OutputType m_outputType = OutputType.DEFAULT;
    private Texture m_rawDepthTex = null;
    private Texture m_segmentedTex = null;
    private boolean m_texturesInitialized = false;
    private Texture m_videoOutTex = null;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LayerBitmapInfo {
        Bitmap bitmap;
        Bitmap composite;
        Bitmap inPaintedBitmap;
        Texture texture;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum OutputType {
        LOOP,
        DEFAULT
    }

    public O3DPContext(Context context, O3DPhotoConfig o3DPhotoConfig) {
        AnimationManager animationManager = new AnimationManager();
        this.mAnimationManager = animationManager;
        this.m_imageRenderer = null;
        EGLHandler eGLHandler = new EGLHandler();
        this.mEglHandler = eGLHandler;
        this.mBlockStaticAnimation = false;
        this.mO3DPConfig = o3DPhotoConfig;
        eGLHandler.initEGL();
        this.mRenderer = new ResultRenderer(eGLHandler);
        this.mO3DPSensor = new O3DPSensor((SensorManager) context.getSystemService("sensor"));
        animationManager.loadAnimations(context);
        this.mDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
    }

    private void applyLayerInpainting(int i2, boolean z, LayerBitmapInfo layerBitmapInfo, Context context) {
        Bitmap bitmap;
        Bitmap bitmap2 = this.mOriginBmp;
        if (bitmap2 == null || bitmap2.isRecycled()) {
            LogUtil.e(TAG, "mOriginBmp is recycled or null");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        LogUtil.d(TAG, "Applying layer inpainting service");
        LogUtil.d(TAG, "[VERBOSE_TIMING] applyLayerInpainting: Starting for layer " + i2 + ", bitmap " + layerBitmapInfo.bitmap.getWidth() + "x" + layerBitmapInfo.bitmap.getHeight());
        long currentTimeMillis2 = System.currentTimeMillis();
        Bitmap createBitmap = Bitmap.createBitmap(this.mOriginBmp.getWidth(), this.mOriginBmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(this.mBgColor);
        canvas.drawBitmap(this.mOriginBmp, 0.0f, 0.0f, (Paint) null);
        JNI.copyAlpha(layerBitmapInfo.composite, createBitmap);
        long currentTimeMillis3 = System.currentTimeMillis();
        LogUtil.d(TAG, "[VERBOSE_TIMING] applyLayerInpainting: Input bitmap preparation took " + (currentTimeMillis3 - currentTimeMillis2) + "ms");
        try {
            long currentTimeMillis4 = System.currentTimeMillis();
            String str = "LiveEffectServiceConnector().inpaint";
            if (RemasterUtil.isAtLeastOneUi7_0()) {
                LogUtil.d(TAG, "Using LiveEffect inpainter for layer " + i2);
                bitmap = new l().m(context, createBitmap, z);
            } else {
                LogUtil.d(TAG, "Using LiveEffect inpainter for layer " + i2);
                bitmap = RemasterUtil.requestRemasterService(context, createBitmap, RemasterUtil.REMASTER_SERVICE_PURPOSE_FOR_3D_PHOTO, 3, 22);
                str = "RemasterUtil.requestRemasterService()";
            }
            long currentTimeMillis5 = System.currentTimeMillis();
            LogUtil.d(TAG, "[VERBOSE_TIMING] " + str + ": Inpainting service call took " + (currentTimeMillis5 - currentTimeMillis4) + "ms");
            layerBitmapInfo.inPaintedBitmap = bitmap;
            long currentTimeMillis6 = System.currentTimeMillis();
            LogUtil.d(TAG, "[VERBOSE_TIMING] applyLayerInpainting: Total method execution time: " + (currentTimeMillis6 - currentTimeMillis) + "ms");
        } catch (RemoteException e) {
            LogUtil.e(TAG, "RemoteException at applyInPainting() : " + e);
        }
    }

    private Point getTouchLocation(MotionEvent motionEvent, Rect rect, boolean z, Texture.FitMode fitMode) {
        Texture.ScaleParams calculateScaling = Texture.calculateScaling(rect, this.mRenderer.getRenderRect(), z, fitMode);
        float x9 = motionEvent.getX();
        float y = motionEvent.getY();
        if (!calculateScaling.dst.contains(Math.round(x9), Math.round(y))) {
            return null;
        }
        Rect rect2 = calculateScaling.dst;
        float width = (x9 - ((float) rect2.left)) / ((float) rect2.width());
        Rect rect3 = calculateScaling.dst;
        float height = (y - ((float) rect3.top)) / ((float) rect3.height());
        Rect rect4 = calculateScaling.src;
        float width2 = width * ((float) rect4.width());
        Rect rect5 = calculateScaling.src;
        return new Point(Math.round(width2 + ((float) rect4.left)), Math.round((height * ((float) rect5.height())) + ((float) rect5.top)));
    }

    private void setFaceCentroid(Vector3 vector3) {
        Vector3 vector32 = new Vector3(-0.05f, -0.05f, 0.0f);
        this.mAnimationManager.getAnimator().setCameraLookAt(vector3.min(vector32).max(new Vector3(0.05f, 0.05f, 1.0f)));
    }

    private void setLayerInfo() {
        int numLayers = this.m_imageRenderer.getNumLayers();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < numLayers; i2++) {
            LayerInfo layerInfo = new LayerInfo();
            layerInfo.minDepth = this.m_imageRenderer.getMinDepthForLayer(i2);
            layerInfo.maxDepth = this.m_imageRenderer.getMaxDepthForLayer(i2);
            arrayList.add(layerInfo);
            LogUtil.d(TAG, "layer[" + i2 + "] = { " + layerInfo.minDepth + ArcCommonLog.TAG_COMMA + layerInfo.maxDepth + " }");
        }
        this.mAnimationManager.getAnimator().setLayerInfo(arrayList);
    }

    private float smoothEdgeTransition(float f) {
        return 1.0f - Math.abs((f * 2.0f) - 1.0f);
    }

    private float smoothstep(float f, float f5, float f8) {
        float max = Math.max(0.0f, Math.min(1.0f, (f8 - f) / (f5 - f)));
        return (3.0f - (max * 2.0f)) * max * max;
    }

    private void writeMeshFile(String str, Context context) {
        Context context2 = context;
        ImageUtil.FormatInfo formatInfo = new ImageUtil.FormatInfo(Bitmap.CompressFormat.JPEG, 95);
        MeshUtils.MeshInfo meshInfo = this.m_imageRenderer.getMeshInfo();
        if (meshInfo == null) {
            LogUtil.w(TAG, "Mesh data unavailable. Unable to export.");
            return;
        }
        File file = new File(DebugUtil.getDebugOutputDirectory(), C0212a.A(str, ".glb"));
        if (GLTFMeshUtils.saveMesh(context2, file, meshInfo, formatInfo)) {
            LogUtil.i(TAG, "Written mesh data to " + file);
        }
        if (REIMPORT_AFTER_WRITE_MESH.booleanValue()) {
            MeshUtils.MeshInfo loadMesh = GLTFMeshUtils.loadMesh(context2, file);
            if (loadMesh == null) {
                LogUtil.e(TAG, "Unable to import generated mesh data");
                return;
            }
            int i2 = 0;
            int i7 = 0;
            int i8 = 0;
            int i10 = 0;
            int i11 = 0;
            int i12 = 0;
            int i13 = 0;
            int i14 = 0;
            int i15 = 0;
            int i16 = 0;
            int i17 = 0;
            while (i2 < meshInfo.layers.size()) {
                MeshUtils.MeshLayerInfo meshLayerInfo = meshInfo.layers.get(i2);
                MeshUtils.MeshInfo meshInfo2 = meshInfo;
                MeshUtils.MeshLayerInfo meshLayerInfo2 = loadMesh.layers.get(i2);
                int i18 = i2;
                MeshUtils.MeshInfo meshInfo3 = loadMesh;
                if (Float.compare((float) meshLayerInfo2.primitive, (float) meshLayerInfo.primitive) == 0) {
                    i7++;
                }
                i8++;
                int min = Math.min(meshLayerInfo2.vertices.length, meshLayerInfo.vertices.length);
                int i19 = 0;
                while (i19 < min) {
                    int i20 = min;
                    int i21 = i19;
                    if (Float.compare(meshLayerInfo2.vertices[i19], meshLayerInfo.vertices[i21]) == 0) {
                        i10++;
                    }
                    i19 = i21 + 1;
                    min = i20;
                }
                i11 += Math.max(meshLayerInfo2.vertices.length, meshLayerInfo.vertices.length);
                int min2 = Math.min(meshLayerInfo2.colors.length, meshLayerInfo.colors.length);
                int i22 = 0;
                while (i22 < min2) {
                    int i23 = min2;
                    int i24 = i22;
                    if (Float.compare(meshLayerInfo2.colors[i22], meshLayerInfo.colors[i24]) == 0) {
                        i14++;
                    }
                    i22 = i24 + 1;
                    min2 = i23;
                }
                i15 += Math.max(meshLayerInfo2.colors.length, meshLayerInfo.colors.length);
                int min3 = Math.min(meshLayerInfo2.texCoords.length, meshLayerInfo.texCoords.length);
                int i25 = 0;
                while (i25 < min3) {
                    int i26 = min3;
                    int i27 = i25;
                    if (Float.compare(meshLayerInfo2.texCoords[i25], meshLayerInfo.texCoords[i27]) == 0) {
                        i12++;
                    }
                    i25 = i27 + 1;
                    min3 = i26;
                }
                i13 += Math.max(meshLayerInfo2.texCoords.length, meshLayerInfo.texCoords.length);
                int min4 = Math.min(meshLayerInfo2.indices.length, meshLayerInfo.indices.length);
                int i28 = 0;
                while (i28 < min4) {
                    int i29 = min4;
                    int i30 = i28;
                    if (meshLayerInfo2.indices[i28] == meshLayerInfo.indices[i30]) {
                        i16++;
                    }
                    i28 = i30 + 1;
                    min4 = i29;
                }
                i17 += Math.max(meshLayerInfo2.indices.length, meshLayerInfo.indices.length);
                i2 = i18 + 1;
                meshInfo = meshInfo2;
                loadMesh = meshInfo3;
            }
            MeshUtils.MeshInfo meshInfo4 = loadMesh;
            LogUtil.i(TAG, "Primitives compare: " + i7 + "/" + i8);
            LogUtil.i(TAG, "Vertices compare: " + i10 + "/" + i11);
            LogUtil.i(TAG, "TexCoords compare: " + i12 + "/" + i13);
            LogUtil.i(TAG, "Colors compare: " + i14 + "/" + i15);
            LogUtil.i(TAG, "Indices compare: " + i16 + "/" + i17);
            this.m_imageRenderer.setMeshInfo(meshInfo4);
        }
    }

    public void applyInPainting(LayerBitmapInfo[] layerBitmapInfoArr, Context context) {
        LogUtil.i(TAG, "[TEST-FLAG] applyInPainting called for " + layerBitmapInfoArr.length + " layers");
        StringBuilder sb2 = new StringBuilder("[TEST-FLAG] O3DPContext instance hashcode in applyInPainting: ");
        sb2.append(hashCode());
        LogUtil.i(TAG, sb2.toString());
        for (int i2 = 0; i2 < layerBitmapInfoArr.length; i2++) {
            LayerBitmapInfo layerBitmapInfo = layerBitmapInfoArr[i2];
            boolean z = true;
            if (i2 != layerBitmapInfoArr.length - 1) {
                z = false;
            }
            applyLayerInpainting(i2, z, layerBitmapInfo, context);
        }
    }

    public void applyStereo(boolean z) {
        ImageRenderer imageRenderer = this.m_imageRenderer;
        if (imageRenderer != null) {
            imageRenderer.setStereo(z);
        }
    }

    public boolean areObjectsScattered() {
        return this.mAreObjectsScattered;
    }

    public void blockStaticAnimation() {
        this.mBlockStaticAnimation = true;
    }

    public void buildShader(LayerBitmapInfo[] layerBitmapInfoArr, Context context) {
        for (LayerBitmapInfo layerBitmapInfo : layerBitmapInfoArr) {
            layerBitmapInfo.texture.upload(layerBitmapInfo.inPaintedBitmap);
        }
        for (LayerBitmapInfo layerBitmapInfo2 : layerBitmapInfoArr) {
            layerBitmapInfo2.texture.release();
        }
        this.m_imageRenderer.processDepthInpainting();
        this.m_imageRenderer.generateSurfaceModels();
        setLayerInfo();
        this.mO3DPConfig.getWriteMeshFile();
        this.mO3DPConfig.getWriteImageFiles();
        if (!this.mO3DPConfig.isPanorama()) {
            Vector3 vector3 = new Vector3(0.0f, 0.0f, this.m_imageRenderer.getMinDepthForLayer(this.m_imageRenderer.getNumLayers() - 1));
            vector3.f4210x = ((((float) this.mFocusPoint.x) * 2.0f) / ((float) this.m_colorTex.width())) - 1.0f;
            vector3.y = ((((float) this.mFocusPoint.y) * 2.0f) / ((float) this.m_colorTex.height())) - 1.0f;
            setFaceCentroid(vector3);
        }
        this.m_imageRenderer.setBokehEnabled(this.mAnimationManager.getAnimator().getAnimation().isBokehEnabled());
    }

    public void cinemaUpdate() {
        SharedRenderParams sharedRenderParams = getSharedRenderParams();
        if (sharedRenderParams != null) {
            sharedRenderParams.applyAnimation(this.mAnimationManager.getAnimator());
        }
    }

    public void computeAnimation(int i2, int i7) {
        this.mAnimationManager.getAnimator().computeAnimation(this.m_colorTex.width(), this.m_colorTex.height(), i2, i7);
    }

    public void consumeNextFrame() {
        if (this.m_outputType == OutputType.LOOP) {
            this.mAnimationManager.getAnimator().consumeNextAnimationTime();
            cinemaUpdate();
        }
    }

    public synchronized void create3DShader(Bitmap bitmap, Bitmap bitmap2, DepthMap depthMap, DepthMap depthMap2) {
        setDepthData(depthMap, depthMap2);
        setColorBitmap(bitmap);
        setSegmentedBitmap(bitmap2);
    }

    public void finalize() {
        ImageRenderer imageRenderer = this.m_imageRenderer;
        if (imageRenderer != null) {
            imageRenderer.destroyImageRenderer();
        }
    }

    public void finishRecording() {
        synchronized (this.mAbortSyncLock) {
            setVideoRecording(false);
            this.mAnimationManager.getAnimator().updateDeltaTime(this.mO3DPConfig.getAnimationSpeed());
            this.mAbortSyncLock.notifyAll();
        }
    }

    public AnimationManager getAnimationManager() {
        return this.mAnimationManager;
    }

    public int getCropHeight() {
        return this.mCropHeight;
    }

    public int getCropWidth() {
        return this.mCropWidth;
    }

    public List<O3DPObject> getDetectedObjects() {
        return this.mO3DPObjects;
    }

    public int getDeviceRotation() {
        Display display = this.mDisplay;
        if (display == null) {
            return 0;
        }
        return display.getRotation();
    }

    public List<Face> getFaces() {
        return this.m_faces;
    }

    public Bitmap getForegroundMask() {
        return this.m_foregroundMaskTex.download();
    }

    public LayerBitmapInfo[] getInpaintLayerBitmaps() {
        int numLayers = this.m_imageRenderer.getNumLayers() - 1;
        LayerBitmapInfo[] layerBitmapInfoArr = new LayerBitmapInfo[numLayers];
        Bitmap bitmap = null;
        for (int i2 = 0; i2 < numLayers; i2++) {
            LayerBitmapInfo layerBitmapInfo = new LayerBitmapInfo();
            Texture texture = new Texture();
            layerBitmapInfo.texture = texture;
            texture.textureObj = this.m_imageRenderer.getColorTexForLayer(i2);
            Bitmap download = layerBitmapInfo.texture.download();
            layerBitmapInfo.bitmap = download;
            if (bitmap == null) {
                bitmap = Bitmap.createBitmap(download.getWidth(), layerBitmapInfo.bitmap.getHeight(), layerBitmapInfo.bitmap.getConfig(), layerBitmapInfo.bitmap.hasAlpha());
            }
            new Canvas(bitmap).drawBitmap(layerBitmapInfo.bitmap, new Matrix(), (Paint) null);
            layerBitmapInfo.composite = bitmap.copy(bitmap.getConfig(), bitmap.isMutable());
            layerBitmapInfoArr[i2] = layerBitmapInfo;
        }
        return layerBitmapInfoArr;
    }

    public InputAdapter getInputAdapter() {
        if (this.mInputAdapter == null) {
            this.mInputAdapter = new InputAdapter(this.mAnimationManager, new InputAdapter.IContract() {
                public int getRotation() {
                    return O3DPContext.this.getDeviceRotation();
                }

                public boolean isTopLayerTouched(MotionEvent motionEvent) {
                    return O3DPContext.this.isTopLayerTouched(motionEvent);
                }
            });
        }
        return this.mInputAdapter;
    }

    public byte[] getMesh(Context context, MeshCompressOption meshCompressOption) {
        boolean z;
        MeshUtils.MeshInfo meshInfo = this.m_imageRenderer.getMeshInfo();
        if (meshInfo == null) {
            LogUtil.e(TAG, "MeshInfo is null");
            return null;
        }
        ImageUtil.FormatInfo formatInfo = new ImageUtil.FormatInfo(Bitmap.CompressFormat.JPEG, 95);
        if (meshCompressOption == MeshCompressOption.DRACO) {
            z = true;
        } else {
            z = false;
        }
        return GLTFMeshUtils.buildMeshData(context, meshInfo, formatInfo, z);
    }

    public final Texture getOutTex() {
        return new Texture(this.m_outTex.textureObj);
    }

    public ResultRenderer getRenderer() {
        return this.mRenderer;
    }

    public int getSalientObjCnt() {
        return this.mSalientObjCnt;
    }

    public SharedRenderParams getSharedRenderParams() {
        ImageRenderer imageRenderer = this.m_imageRenderer;
        if (imageRenderer != null) {
            return imageRenderer.getSharedRenderParams();
        }
        LogUtil.w(TAG, "m_imageRenderer is null");
        return null;
    }

    public int getTargetRenderHeight() {
        return this.mTargetRenderHeight;
    }

    public int getTargetRenderWidth() {
        return this.mTargetRenderWidth;
    }

    public Bitmap getVideoFrame() {
        return this.m_videoOutTex.download();
    }

    public void initEGL() {
        this.mEglHandler.makeCurrent();
    }

    public void initRenderer(boolean z) {
        ImageRenderer imageRenderer = this.m_imageRenderer;
        if (imageRenderer != null) {
            this.m_imageRenderer = null;
            imageRenderer.destroyImageRenderer();
        }
        ImageRenderer imageRenderer2 = new ImageRenderer();
        this.m_imageRenderer = imageRenderer2;
        imageRenderer2.setClipperConfig(this.mO3DPConfig.getUseClipper());
        this.m_imageRenderer.setDepthClusteringConfig(this.mO3DPConfig.getDepthClusteringConfig());
        this.m_imageRenderer.setLayerGeneratorConfig(this.mO3DPConfig.getLayerGeneratorConfig());
        this.m_imageRenderer.setMeshDecimatorConfig(this.mO3DPConfig.getMeshDecimatorConfig());
        this.m_imageRenderer.setColorTex(this.m_colorTex);
        this.m_imageRenderer.setProjectionParams(FOV, NEAR_PLANE, FAR_PLANE);
        float[] convertColor = ColorUtil.convertColor(this.mBgColor);
        this.m_imageRenderer.setBgColor(convertColor);
        this.mRenderer.setBgColor(convertColor);
        this.m_imageRenderer.setStereo(this.mO3DPConfig.getStereo());
        this.m_imageRenderer.setPanorama(this.mO3DPConfig.isPanorama());
        if (this.mO3DPConfig.isPanorama()) {
            updatePanoramaOutSize();
        }
        if (z) {
            this.m_imageRenderer.setDepthTex(this.m_rawDepthTex, this.m_depthTex);
            this.m_imageRenderer.setSegmentedTex(this.m_segmentedTex);
            this.m_imageRenderer.generateInitialLayers();
        }
    }

    public void initTextures() {
        if (!this.m_texturesInitialized) {
            this.m_rawDepthTex = Texture.create(33326, 6403, 5126, 3553);
            this.m_depthTex = Texture.create(33326, 6403, 5126, 3553);
            this.m_colorTex = Texture.create(32856, 6408, 5121, 3553);
            this.m_segmentedTex = new Texture();
            this.m_outTex = new Texture();
            this.m_texturesInitialized = true;
        }
    }

    public boolean isFloating() {
        return this.mIsFloating;
    }

    public boolean isNoSegInCenter() {
        return this.mIsNoSegInCenter;
    }

    public boolean isRecordAbortRequested() {
        return this.mRecordAbortRequested;
    }

    public boolean isSegObjectBehind() {
        return this.mIsSegObjectBehind;
    }

    public boolean isStaticAnimationBlocked() {
        return this.mBlockStaticAnimation;
    }

    public boolean isSupportForegroundMask() {
        if (this.m_foregroundMaskTex != null) {
            return true;
        }
        return false;
    }

    public boolean isTopLayerTouched(MotionEvent motionEvent) {
        Point touchLocation;
        boolean z = false;
        if ((motionEvent.getAction() != 0 && motionEvent.getAction() != 5) || this.m_imageRenderer == null) {
            return false;
        }
        Texture texture = new Texture(this.m_imageRenderer.getResultTex());
        Texture.FitMode fitMode = Texture.FitMode.FIT_FULL;
        int numLayers = this.m_imageRenderer.getNumLayers();
        boolean z3 = true;
        if (numLayers > 1 && (touchLocation = getTouchLocation(motionEvent, texture.getRect(), true, fitMode)) != null) {
            this.mEglHandler.makeCurrent();
            if (this.m_imageRenderer.getLayerAtPixel(touchLocation) == numLayers - 1) {
                z = true;
            }
            z3 = z;
        }
        texture.release();
        return z3;
    }

    public boolean isVideoRecording() {
        return this.mVideoRecording;
    }

    public void onAnimationChanged(Animation animation) {
        setAnimation(animation);
    }

    public void onAnimationNameChanged(String str) {
        setAnimation(str);
    }

    public void onAnimationSpeedChanged(float f) {
        getAnimationManager().getAnimator().updateDeltaTime(f);
    }

    public void onBgColorChanged(int i2) {
        setBgColor(i2);
    }

    public void onDynamicsEnableChanged(boolean z) {
        setDynamicOffsetEnabled(z);
    }

    public void onOutputTypeChanged(OutputType outputType) {
        setOutputType(outputType);
    }

    public void onStereoChanged(boolean z) {
        applyStereo(z);
    }

    public void pauseMotionSensor() {
        this.mO3DPSensor.pause();
        InputAdapter inputAdapter = this.mInputAdapter;
        if (inputAdapter != null) {
            inputAdapter.resetFirstEvent();
        }
    }

    public void release() {
        try {
            this.mRenderer.release();
            Texture texture = this.m_colorTex;
            if (texture != null) {
                texture.release();
            }
            this.m_colorTex = null;
            Texture texture2 = this.m_rawDepthTex;
            if (texture2 != null) {
                texture2.release();
            }
            this.m_rawDepthTex = null;
            Texture texture3 = this.m_depthTex;
            if (texture3 != null) {
                texture3.release();
            }
            this.m_depthTex = null;
            Texture texture4 = this.m_segmentedTex;
            if (texture4 != null) {
                texture4.release();
            }
            this.m_segmentedTex = null;
            Texture texture5 = this.m_outTex;
            if (texture5 != null) {
                texture5.release();
            }
            this.m_outTex = null;
            Texture texture6 = this.m_foregroundMaskTex;
            if (texture6 != null) {
                texture6.release();
            }
            this.m_foregroundMaskTex = null;
            Texture texture7 = this.m_videoOutTex;
            if (texture7 != null) {
                texture7.release();
            }
            this.m_videoOutTex = null;
            ImageRenderer imageRenderer = this.m_imageRenderer;
            if (imageRenderer != null) {
                imageRenderer.destroyImageRenderer();
            }
            this.m_imageRenderer = null;
            this.mEglHandler.release();
            this.m_faces.clear();
            this.mO3DPSensor.unregisterListener();
            LogUtil.i(TAG, "Released");
        } catch (Exception e) {
            throw new RuntimeException("Got error while releasing o3dpContext", e);
        }
    }

    public void resetAnimationParams() {
        this.mO3DPConfig.applyConfig();
        this.mAnimationManager.resetAnimationParams(this.m_imageRenderer, this.m_colorTex, this.m_videoOutTex, this.m_outTex);
        cinemaUpdate();
    }

    public void resetVideoTexture() {
        Texture texture = this.m_videoOutTex;
        if (texture != null) {
            texture.release();
            this.m_videoOutTex = null;
        }
    }

    public void resizeColorTex(int i2, int i7) {
        this.m_colorTex.resize(i2, i7);
    }

    public void resumeMotionSensor() {
        this.mO3DPSensor.resume();
    }

    public void saveResult(String str, String str2, Boolean bool) {
        Texture texture = this.m_outTex;
        if (texture != null) {
            Texture create = Texture.create(texture.width(), this.m_outTex.height(), 32856, 6408, 5121, 3553);
            create.clear();
            this.m_outTex.copyToTexture(create.getID(), this.m_outTex.width(), this.m_outTex.height(), Texture.Orientation.ORI_NONE, this.mO3DPConfig.getFitMode());
            Bitmap download = create.download();
            ImageUtil.FormatInfo formatInfo = new ImageUtil.FormatInfo(Bitmap.CompressFormat.PNG, 100);
            ImageUtil.saveImage(download, str + str2, formatInfo);
            if (bool.booleanValue()) {
                Bitmap download2 = this.m_depthTex.download();
                Canvas canvas = new Canvas(download2);
                ColorMatrix colorMatrix = new ColorMatrix(new float[]{1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
                Paint paint = new Paint();
                paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
                canvas.drawBitmap(download2, 0.0f, 0.0f, paint);
                ImageUtil.saveImage(download, j.f(new StringBuilder(), str, str2, "_depth"), formatInfo);
            }
        }
    }

    public void setAnimation(Animation animation) {
        if (animation == null) {
            LogUtil.e(TAG, "setAnimation: animation is null");
            return;
        }
        this.mFutureDepthMap.thenAccept(new g(2, animation));
        this.mAnimationManager.getAnimator().setAnimation(animation);
        this.mAnimationManager.getAnimator().updateDeltaTime(this.mO3DPConfig.getAnimationSpeed());
        ImageRenderer imageRenderer = this.m_imageRenderer;
        if (imageRenderer != null) {
            imageRenderer.setBokehEnabled(animation.isBokehEnabled());
        }
        if (!animation.isSensorInteraction() || this.mVideoRecording) {
            this.mO3DPSensor.unregisterListener();
        } else {
            this.mO3DPSensor.registerListener(getInputAdapter(), animation.getSensorType());
        }
        this.mO3DPConfig.updateCurrentAnimation(animation);
    }

    public void setBgColor(int i2) {
        this.mBgColor = i2;
        if (this.m_imageRenderer != null) {
            float[] convertColor = ColorUtil.convertColor(i2);
            this.m_imageRenderer.setBgColor(convertColor);
            this.mRenderer.setBgColor(convertColor);
        }
    }

    public void setColorBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            LogUtil.e(TAG, "setColorBitmap(), bitmap is null");
            return;
        }
        this.mOriginBmp = bitmap;
        this.m_colorTex.resize(bitmap.getWidth(), bitmap.getHeight());
        this.m_colorTex.upload(bitmap);
    }

    public void setCropSize(int i2, int i7) {
        this.mCropWidth = i2;
        this.mCropHeight = i7;
    }

    public void setDepthData(DepthMap depthMap, DepthMap depthMap2) {
        if (depthMap2 == null) {
            LogUtil.i(TAG, "setDepthData() : depthMap is null");
            return;
        }
        CompletableFuture completableFuture = new CompletableFuture();
        this.mFutureDepthMap = new CompletableFuture<>();
        completableFuture.complete(depthMap);
        this.mFutureDepthMap.complete(depthMap2);
        this.m_rawDepthTex.resize(depthMap.width, depthMap.height);
        this.m_rawDepthTex.uploadTextureFloat(depthMap.data);
        this.m_depthTex.resize(depthMap2.width, depthMap2.height);
        this.m_depthTex.uploadTextureFloat(depthMap2.data);
        ImageRenderer imageRenderer = this.m_imageRenderer;
        if (imageRenderer != null) {
            imageRenderer.depthChanged();
        }
    }

    public void setDetectedObjects(List<O3DPObject> list) {
        this.mO3DPObjects = list;
    }

    public void setDynamicOffsetEnabled(boolean z) {
        ImageRenderer imageRenderer = this.m_imageRenderer;
        if (imageRenderer != null) {
            imageRenderer.setDynamicOffsetEnabled(z);
        }
    }

    public void setFaces(List<Face> list) {
        this.m_faces = list;
    }

    public void setFloating(boolean z) {
        this.mIsFloating = z;
    }

    public void setFocusPoint(Point point) {
        this.mFocusPoint = point;
    }

    public void setForegroundMaskResolution(int i2, int i7) {
        Texture create = Texture.create(33321, 6403, 5121, 3553);
        this.m_foregroundMaskTex = create;
        create.resize(i2, i7);
        this.m_foregroundMaskTex.clear();
    }

    public void setMesh(MeshUtils.MeshInfo meshInfo) {
        this.m_imageRenderer.setMeshInfo(meshInfo);
    }

    public void setNoSegInCenter(boolean z) {
        this.mIsNoSegInCenter = z;
    }

    public void setObjectsScattered(boolean z) {
        this.mAreObjectsScattered = z;
    }

    public void setOutputType(OutputType outputType) {
        this.m_outputType = outputType;
    }

    public void setRecordAbortRequested(boolean z) {
        this.mRecordAbortRequested = z;
    }

    public void setSaliencyObjCnt(int i2) {
        this.mSalientObjCnt = i2;
    }

    public void setSegObjectBehind(boolean z) {
        this.mIsSegObjectBehind = z;
    }

    public void setSegmentedBitmap(Bitmap bitmap) {
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (this.m_segmentedTex.textureObj == 0) {
                this.m_segmentedTex = Texture.create(32856, 6408, 5121, 3553);
            }
            this.m_segmentedTex.resize(width, height);
            this.m_segmentedTex.upload(bitmap);
            return;
        }
        this.m_segmentedTex.release();
    }

    public void setTargetRenderSize(int i2, int i7) {
        this.mTargetRenderWidth = i2;
        this.mTargetRenderHeight = i7;
    }

    public void setVideoRecording(boolean z) {
        this.mVideoRecording = z;
        this.mAnimationManager.getAnimator().setVideoRecording(z);
    }

    public void setVideoResolution(int i2, int i7) {
        Texture create = Texture.create(32856, 6408, 5121, 3553);
        this.m_videoOutTex = create;
        create.resize(i2, i7);
        this.m_videoOutTex.clear();
    }

    public void updatePanoramaOutSize() {
        Texture texture = this.m_videoOutTex;
        if (texture != null) {
            this.m_imageRenderer.setOutSize(texture.width(), this.m_videoOutTex.height());
        } else {
            this.m_imageRenderer.setOutSize(this.mRenderer.getWidth(), this.mRenderer.getHeight());
        }
    }

    public boolean visualize() {
        ImageRenderer imageRenderer = this.m_imageRenderer;
        if (imageRenderer == null || imageRenderer.isDepthChanged()) {
            return false;
        }
        this.m_imageRenderer.visualize();
        this.m_outTex.release();
        this.m_outTex.textureObj = this.m_imageRenderer.getResultTex();
        Texture texture = this.m_videoOutTex;
        if (texture != null) {
            this.m_outTex.copyToTexture(texture.getID(), this.m_videoOutTex.width(), this.m_videoOutTex.height(), Texture.Orientation.ORI_NONE, this.mO3DPConfig.getFitMode());
        }
        if (this.m_foregroundMaskTex == null) {
            return true;
        }
        Texture texture2 = new Texture(this.m_imageRenderer.getForegroundMaskTex());
        Texture.copyToTexture(texture2.textureObj, this.m_foregroundMaskTex.getID(), this.m_foregroundMaskTex.width(), this.m_foregroundMaskTex.height(), Texture.Orientation.ORI_NONE, this.mO3DPConfig.getFitMode());
        texture2.release();
        return true;
    }

    public void setAnimation(String str) {
        if (str == null) {
            LogUtil.e(TAG, "setAnimation: animationName is null");
        } else if (!this.mAnimationManager.getAnimations().containsKey(str)) {
            LogUtil.e(TAG, "setAnimation: given animation is not contained ".concat(str));
        } else {
            setAnimation(this.mAnimationManager.getAnimations().get(str));
        }
    }

    public void buildShader(Context context, MeshUtils.MeshInfo meshInfo) {
        setMesh(meshInfo);
        setLayerInfo();
        this.mO3DPConfig.getWriteMeshFile();
        this.mO3DPConfig.getWriteImageFiles();
        this.m_imageRenderer.setBokehEnabled(this.mAnimationManager.getAnimator().getAnimation().isBokehEnabled());
    }
}
