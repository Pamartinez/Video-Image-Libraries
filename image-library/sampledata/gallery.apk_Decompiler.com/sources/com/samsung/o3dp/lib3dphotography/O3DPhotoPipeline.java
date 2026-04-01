package com.samsung.o3dp.lib3dphotography;

import A.a;
import B5.c;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import bc.d;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectInfo;
import com.samsung.android.app.sdk.deepsky.objectcapture.base.ObjectResult;
import com.samsung.o3dp.lib3dphotography.MeshUtils;
import com.samsung.o3dp.lib3dphotography.O3DPContext;
import com.samsung.o3dp.lib3dphotography.O3DPListener;
import com.samsung.o3dp.lib3dphotography.animation.Animation;
import com.samsung.o3dp.lib3dphotography.animation.AnimationPolicy;
import com.samsung.o3dp.lib3dphotography.animation.Animator;
import com.samsung.o3dp.lib3dphotography.animation.animations.PanoramaAnimation;
import com.samsung.o3dp.lib3dphotography.animation.dynamics.DynamicOffset;
import com.samsung.o3dp.lib3dphotography.graphics.O3DPRectUtil;
import com.samsung.o3dp.lib3dphotography.interaction.InputAdapter;
import com.samsung.o3dp.lib3dphotography.mesh.AnimatedMesh;
import com.samsung.o3dp.lib3dphotography.mesh.Heif3dDecoder;
import com.samsung.o3dp.lib3dphotography.mesh.Heif3dEncoder;
import com.samsung.o3dp.lib3dphotography.mesh.Heif3dException;
import com.samsung.o3dp.lib3dphotography.mesh.Jpeg3dEncoder;
import com.samsung.o3dp.lib3dphotography.mesh.Jpeg3dRemover;
import com.samsung.o3dp.lib3dphotography.mesh.MeshSefManager;
import com.samsung.o3dp.lib3dphotography.mesh.SupportedExtension;
import com.samsung.o3dp.lib3dphotography.mesh.exception.MeshEncodingException;
import com.samsung.o3dp.lib3dphotography.mesh.exception.MeshRemovingException;
import com.samsung.o3dp.lib3dphotography.nativelib.JNI;
import com.samsung.o3dp.lib3dphotography.utils.ActivityUtil;
import com.samsung.o3dp.lib3dphotography.utils.FileExtensionParser;
import com.samsung.o3dp.lib3dphotography.utils.FileUtil;
import com.samsung.o3dp.lib3dphotography.utils.ImageUtil;
import com.samsung.o3dp.lib3dphotography.utils.LogUtil;
import com.samsung.o3dp.lib3dphotography.utils.SefUtil;
import com.samsung.o3dp.lib3dphotography.utils.SegmentUtil;
import com.samsung.o3dp.lib3dphotography.video.VideoEncoderTask;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import java.util.function.Consumer;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class O3DPhotoPipeline {
    private static final String INFO_RENDER_FRAME_RATE = "RENDER_FRAME_RATE";
    private static final String INFO_VIDEO_FRAME_RATE = "VIDEO_FRAME_RATE";
    private static final String TAG = "O3DPhotoPipeline";
    private final Context mContext;
    private Bitmap mCroppedBitmap;
    private List<Bitmap> mEachSegmentedBitmaps;
    private Rect mEnclosingRect;
    private FrameRateController mFrameRateController;
    private GLContextManager mGLContextManager;
    private JSONObject mInfo;
    private DepthMap mNormalizedDepthMap;
    private final O3DPContext mO3DPContext;
    private O3DPDepthEstimator mO3DPDepthEstimator;
    private O3DPListener mO3DPListener;
    protected O3DPhotoConfig mO3DPhotoConfig;
    private final List<O3DPObject> mOriginBodyObjects;
    private final List<Face> mOriginFaces;
    private final List<O3DPObject> mOriginOtherObjects;
    private Bitmap mOriginalBitmap;
    private DepthMap mScaledDepthMap;
    private List<Rect> mSegBounds;
    private Bitmap mSegmentedBitmap;

    public O3DPhotoPipeline(Context context, O3DPhotoConfig o3DPhotoConfig, IGLThread iGLThread) {
        this(context, o3DPhotoConfig);
        FrameRateController frameRateController = new FrameRateController(iGLThread);
        this.mFrameRateController = frameRateController;
        this.mGLContextManager = new GLContextManager(this.mO3DPContext, this.mO3DPhotoConfig, frameRateController);
    }

    private void autoAnimation(Point point) {
        if (!this.mO3DPhotoConfig.isAutoAnimation()) {
            LogUtil.i(TAG, "Skip the automatic animation process because there is an already defined " + this.mO3DPhotoConfig.getAnimationName() + " animation.");
            return;
        }
        boolean isStaticAnimation = isStaticAnimation();
        String chooseAnimation = AnimationPolicy.chooseAnimation(this.mCroppedBitmap, this.mSegmentedBitmap, this.mEnclosingRect, this.mO3DPContext.getFaces(), this.mO3DPContext.getDetectedObjects(), point, this.mO3DPContext, this.mO3DPhotoConfig.isPanorama(), isStaticAnimation, this.mInfo);
        LogUtil.i(TAG, "Selected animation by AnimationPolicy : " + chooseAnimation);
        Animation animation = getAnimations(this.mContext).get(chooseAnimation);
        this.mO3DPhotoConfig.setAnimation(animation);
        this.mO3DPListener.onRecommendedAnimation(animation);
        this.mO3DPContext.getAnimationManager().getAnimator().getAnimation().setStaticAnimation(isStaticAnimation);
    }

    private static float calculateScaleFactor(int i2, int i7, int i8) {
        return ((float) i8) / ((float) Math.min(i2, i7));
    }

    private boolean checkValidVideoRecordOptions() {
        Animation animation = getAnimator().getAnimation();
        if (animation.getAnimationParams().length > 1 && animation.getSpeedFactor() > 0.0f) {
            return true;
        }
        O3DPListener.ErrorType errorType = O3DPListener.ErrorType.VIDEO_RECORDING_FAIL;
        notifyFailure(errorType, animation.getName() + " animation do not support video recording.");
        return false;
    }

    private boolean compositeForegroundSegments(List<Bitmap> list, Bitmap bitmap, DepthMap depthMap) {
        if (list.size() == 1) {
            LogUtil.d(TAG, "There is only one segmented object, skip split segmented objects");
            return false;
        }
        float[] fArr = depthMap.data;
        if (fArr != null) {
            return JNI.compositeForegroundSegments(list, bitmap, fArr, depthMap.width, depthMap.height);
        }
        LogUtil.w(TAG, "Depth map is null, skip split segmented objects");
        return false;
    }

    private void createVideo(Context context, byte[] bArr) {
        try {
            ThreadHandler.getInstance().runOnWorkerThread(new VideoEncoderTask(context, this.mO3DPContext, this.mO3DPhotoConfig, (O3DPVideoListener) this.mO3DPListener, bArr));
        } catch (IOException e) {
            IOException iOException = e;
            this.mO3DPContext.finishRecording();
            O3DPListener.ErrorType errorType = O3DPListener.ErrorType.VIDEO_RECORDING_FAIL;
            notifyFailure(errorType, "Failed to create a video file " + iOException);
            throw new RuntimeException(iOException);
        }
    }

    private boolean isInBlockAnimationList(String str) {
        return this.mO3DPhotoConfig.getBlockAnimations().contains(str);
    }

    private boolean isStaticAnimation() {
        return this.mO3DPContext.isSegObjectBehind();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Surface surface) {
        this.mO3DPContext.getRenderer().initEglSurface(surface);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$prepareToRender$9(Rect rect) {
        O3DPObject o3DPObject = new O3DPObject(O3DPObjType.OBJECT);
        o3DPObject.setBound(rect);
        this.mOriginOtherObjects.add(o3DPObject);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$recordHeif3d$3(Bitmap bitmap, MeshCompressOption meshCompressOption, String str, O3DPHeif3DListener o3DPHeif3DListener) {
        if (!initO3DPWrapper(bitmap)) {
            notifyFailure(O3DPListener.ErrorType.JPEG3D_RECORDING_FAIL, "Failed at init");
        } else if (!prepareToRender()) {
            notifyFailure(O3DPListener.ErrorType.JPEG3D_RECORDING_FAIL, "Failed at prepareToRender");
        } else {
            this.mO3DPContext.getRenderer().makeCurrent();
            this.mO3DPContext.create3DShader(this.mCroppedBitmap, this.mSegmentedBitmap, this.mNormalizedDepthMap, this.mScaledDepthMap);
            this.mO3DPContext.initRenderer(true);
            O3DPContext.LayerBitmapInfo[] inpaintLayerBitmaps = this.mO3DPContext.getInpaintLayerBitmaps();
            this.mO3DPContext.applyInPainting(inpaintLayerBitmaps, this.mContext);
            this.mO3DPContext.buildShader(inpaintLayerBitmaps, this.mContext);
            byte[] mesh = getMesh(meshCompressOption);
            if (mesh == null) {
                notifyFailure(O3DPListener.ErrorType.JPEG3D_RECORDING_FAIL, "Failed to get Mesh");
                return;
            }
            try {
                ActivityUtil.saveBytesToFile(str, new Heif3dEncoder().encodeImage(this.mCroppedBitmap, mesh, getAnimator().getAnimation().getAnimationScript().getBytes(StandardCharsets.UTF_8), this.mContext));
                o3DPHeif3DListener.onHeif3DEncoded();
            } catch (Heif3dException e) {
                notifyFailure(O3DPListener.ErrorType.JPEG3D_RECORDING_FAIL, e.toString());
            }
            LogUtil.d(TAG, "X: recordHeif3d");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$recordJpeg3d$2(Bitmap bitmap, MeshCompressOption meshCompressOption, String str, O3DPJpeg3DListener o3DPJpeg3DListener) {
        if (!initO3DPWrapper(bitmap)) {
            notifyFailure(O3DPListener.ErrorType.JPEG3D_RECORDING_FAIL, "Failed at init");
        } else if (!prepareToRender()) {
            notifyFailure(O3DPListener.ErrorType.JPEG3D_RECORDING_FAIL, "Failed at prepareToRender");
        } else {
            this.mO3DPContext.getRenderer().makeCurrent();
            this.mO3DPContext.create3DShader(this.mCroppedBitmap, this.mSegmentedBitmap, this.mNormalizedDepthMap, this.mScaledDepthMap);
            this.mO3DPContext.initRenderer(true);
            O3DPContext.LayerBitmapInfo[] inpaintLayerBitmaps = this.mO3DPContext.getInpaintLayerBitmaps();
            this.mO3DPContext.applyInPainting(inpaintLayerBitmaps, this.mContext);
            this.mO3DPContext.buildShader(inpaintLayerBitmaps, this.mContext);
            byte[] mesh = getMesh(meshCompressOption);
            if (mesh == null) {
                notifyFailure(O3DPListener.ErrorType.JPEG3D_RECORDING_FAIL, "Failed to get Mesh");
                return;
            }
            try {
                ActivityUtil.saveBytesToFile(str, new Jpeg3dEncoder().encodeImage(this.mCroppedBitmap, mesh, getAnimator().getAnimation().getAnimationScript().getBytes(StandardCharsets.UTF_8)));
                o3DPJpeg3DListener.onJpeg3DEncoded();
            } catch (MeshEncodingException e) {
                notifyFailure(O3DPListener.ErrorType.JPEG3D_RECORDING_FAIL, e.toString());
            }
            LogUtil.d(TAG, "X: recordJpeg3d");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$recordVideo$1(String str, Bitmap bitmap) {
        O3DPhotoConfig o3DPhotoConfig = this.mO3DPhotoConfig;
        if (o3DPhotoConfig == null) {
            notifyFailure(O3DPListener.ErrorType.VIDEO_RECORDING_FAIL, "O3DPhotoConfig is null");
            LogUtil.e(TAG, "O3DPhotoConfig is null");
            this.mO3DPContext.finishRecording();
            return;
        }
        try {
            o3DPhotoConfig.setFilePath(str);
            if (!initO3DPWrapper(bitmap)) {
                notifyFailure(O3DPListener.ErrorType.VIDEO_RECORDING_ABORTED, "Failed at init");
                this.mO3DPContext.finishRecording();
            } else if (!prepareToRender()) {
                notifyFailure(O3DPListener.ErrorType.VIDEO_RECORDING_ABORTED, "Failed at prepareToRender");
                this.mO3DPContext.finishRecording();
            } else if (checkValidVideoRecordOptions()) {
                if (this.mO3DPContext.isRecordAbortRequested()) {
                    notifyFailure(O3DPListener.ErrorType.VIDEO_RECORDING_ABORTED, "Aborted by user");
                    this.mO3DPContext.finishRecording();
                    return;
                }
                this.mO3DPContext.getRenderer().makeCurrent();
                this.mO3DPContext.create3DShader(this.mCroppedBitmap, this.mSegmentedBitmap, this.mNormalizedDepthMap, this.mScaledDepthMap);
                this.mO3DPContext.initRenderer(true);
                O3DPContext.LayerBitmapInfo[] inpaintLayerBitmaps = this.mO3DPContext.getInpaintLayerBitmaps();
                this.mO3DPContext.applyInPainting(inpaintLayerBitmaps, this.mContext);
                this.mO3DPContext.buildShader(inpaintLayerBitmaps, this.mContext);
                createVideo(this.mContext, (byte[]) null);
            }
        } catch (IllegalArgumentException e) {
            O3DPListener.ErrorType errorType = O3DPListener.ErrorType.VIDEO_RECORDING_FAIL;
            String message = e.getMessage();
            Objects.requireNonNull(message);
            notifyFailure(errorType, message);
            LogUtil.e(TAG, "invalid file path " + e);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$release$11() {
        LogUtil.i(TAG, "Release in HandlerThread for sequence guarantee");
        this.mO3DPContext.release();
        this.mFrameRateController.release();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$render$7(Bitmap bitmap) {
        if (!initO3DPWrapper(bitmap)) {
            LogUtil.w(TAG, "Failed to initialize O3DP wrapper");
        } else if (prepareToRender()) {
            this.mGLContextManager.init3DShader(this.mContext, this.mCroppedBitmap, this.mSegmentedBitmap, this.mNormalizedDepthMap, this.mScaledDepthMap, this.mO3DPListener);
            this.mFrameRateController.runOnGLThread(new h(this, 3));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$render$8(AnimatedMesh animatedMesh) {
        MeshUtils.MeshInfo loadMesh = GLTFMeshUtils.loadMesh(this.mContext, animatedMesh.getMesh());
        if (loadMesh == null) {
            notifyFailure(O3DPListener.ErrorType.RENDERING_FAIL, "Failed to load mesh, please check mesh is valid");
            return;
        }
        Bitmap bitmap = loadMesh.layers.get(0).bitmap;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (initO3DPWrapperWithMesh(width, height)) {
            Animation animation = animatedMesh.getAnimation();
            if (animation == null) {
                LogUtil.e(TAG, "Animation is null, cannot proceed with rendering.");
                return;
            }
            this.mO3DPhotoConfig.setAnimation(animation);
            this.mO3DPListener.onRecommendedAnimation(animation);
            this.mGLContextManager.init3DShader(this.mContext, loadMesh, width, height, this.mO3DPListener);
            this.mFrameRateController.runOnGLThread(new h(this, 3));
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$requestRender$6() {
        this.mO3DPContext.getRenderer().makeCurrent();
        consumeNextFrame();
        visualize();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$resumeAnimation$4() {
        this.mO3DPContext.getRenderer().makeCurrent();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$resumeAnimation$5(Void voidR) {
        consumeNextFrame();
        visualize();
    }

    private void notifyFailure(O3DPListener.ErrorType errorType, String str) {
        LogUtil.e(TAG, str);
        this.mO3DPListener.onFailed(errorType, str);
    }

    private byte[] removeMesh(SupportedExtension supportedExtension, byte[] bArr) {
        byte[] bArr2;
        if (supportedExtension.equals(SupportedExtension.JPEG)) {
            LogUtil.d(TAG, "supportedExtension: " + supportedExtension);
            bArr2 = removeMeshFromJpeg3d(bArr);
        } else if (supportedExtension.equals(SupportedExtension.HEIF)) {
            bArr2 = removeMeshFromHeif3d(bArr);
        } else {
            throw new IllegalArgumentException("Not supported extension: " + supportedExtension.name());
        }
        if (bArr2 != null) {
            return bArr2;
        }
        throw new IllegalStateException("Removed bytes of image is null.");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void runDeformableDetector() {
        /*
            r4 = this;
            java.lang.String r0 = "The number of deformable object(s) : "
            monitor-enter(r4)
            android.graphics.Bitmap r1 = r4.mOriginalBitmap     // Catch:{ all -> 0x0010 }
            if (r1 != 0) goto L_0x0012
            java.lang.String r0 = "O3DPhotoPipeline"
            java.lang.String r1 = "runDeformableDetector() : mOriginalBitmap is null"
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r0, r1)     // Catch:{ all -> 0x0010 }
            monitor-exit(r4)
            return
        L_0x0010:
            r0 = move-exception
            goto L_0x003c
        L_0x0012:
            android.content.Context r1 = r4.mContext     // Catch:{ all -> 0x0010 }
            android.graphics.Bitmap r2 = r4.mCroppedBitmap     // Catch:{ all -> 0x0010 }
            java.util.List r1 = com.samsung.o3dp.lib3dphotography.DeformableObjDetector.run(r1, r2)     // Catch:{ all -> 0x0010 }
            boolean r2 = r1.isEmpty()     // Catch:{ all -> 0x0010 }
            if (r2 != 0) goto L_0x003a
            java.lang.String r2 = "O3DPhotoPipeline"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0010 }
            r3.<init>(r0)     // Catch:{ all -> 0x0010 }
            int r0 = r1.size()     // Catch:{ all -> 0x0010 }
            r3.append(r0)     // Catch:{ all -> 0x0010 }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x0010 }
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.i(r2, r0)     // Catch:{ all -> 0x0010 }
            com.samsung.o3dp.lib3dphotography.O3DPListener r0 = r4.mO3DPListener     // Catch:{ all -> 0x0010 }
            r0.onDeformableObjectDetected()     // Catch:{ all -> 0x0010 }
        L_0x003a:
            monitor-exit(r4)
            return
        L_0x003c:
            monitor-exit(r4)     // Catch:{ all -> 0x0010 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.o3dp.lib3dphotography.O3DPhotoPipeline.runDeformableDetector():void");
    }

    private void runObjectDetector(Bitmap bitmap) {
        ObjectDetectorFactory.create().detect(this.mContext, bitmap, this.mOriginBodyObjects, this.mOriginFaces);
    }

    private void updateDynamicsIntensity(Animation animation) {
        if (animation == null) {
            LogUtil.i(TAG, "updateDynamicsIntensity failed. animation is null");
            return;
        }
        if (this.mO3DPContext.isSegObjectBehind()) {
            animation.setDynamicsIntensity(DynamicOffset.DynamicsIntensity.Level0.level);
        } else if (!this.mO3DPContext.isFloating()) {
            animation.setDynamicsIntensity(DynamicOffset.DynamicsIntensity.Level1.level);
        } else if (!this.mO3DPContext.getFaces().isEmpty() || !this.mO3DPContext.getDetectedObjects().isEmpty()) {
            animation.setDynamicsIntensity(DynamicOffset.DynamicsIntensity.Level3.level);
        } else {
            animation.setDynamicsIntensity(DynamicOffset.DynamicsIntensity.Level5.level);
        }
        if (this.mEnclosingRect != null && this.mCroppedBitmap != null) {
            animation.setStaticDynamicsDirection(O3DPRectUtil.getOverlapDirection(new Rect(0, 0, this.mCroppedBitmap.getWidth(), this.mCroppedBitmap.getHeight()), this.mEnclosingRect));
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00a5  */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateResultRendererSize() {
        /*
            r9 = this;
            com.samsung.o3dp.lib3dphotography.O3DPhotoConfig r0 = r9.mO3DPhotoConfig
            java.lang.String r1 = "O3DPhotoPipeline"
            if (r0 != 0) goto L_0x000c
            java.lang.String r9 = "mO3DPhotoConfig is null"
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r1, r9)
            return
        L_0x000c:
            com.samsung.o3dp.lib3dphotography.O3DPContext r0 = r9.mO3DPContext
            int r0 = r0.getTargetRenderWidth()
            com.samsung.o3dp.lib3dphotography.O3DPContext r2 = r9.mO3DPContext
            int r2 = r2.getTargetRenderHeight()
            java.lang.String r3 = "}"
            if (r0 <= 0) goto L_0x00ab
            if (r2 > 0) goto L_0x0020
            goto L_0x00ab
        L_0x0020:
            com.samsung.o3dp.lib3dphotography.O3DPhotoConfig r4 = r9.mO3DPhotoConfig
            com.samsung.o3dp.lib3dphotography.graphics.Texture$FitMode r4 = r4.getFitMode()
            com.samsung.o3dp.lib3dphotography.graphics.Texture$FitMode r5 = com.samsung.o3dp.lib3dphotography.graphics.Texture.FitMode.FIT_FULL
            if (r4 != r5) goto L_0x006c
            com.samsung.o3dp.lib3dphotography.O3DPContext r4 = r9.mO3DPContext
            int r4 = r4.getCropWidth()
            if (r4 == 0) goto L_0x006c
            com.samsung.o3dp.lib3dphotography.O3DPContext r4 = r9.mO3DPContext
            int r4 = r4.getCropHeight()
            if (r4 == 0) goto L_0x006c
            com.samsung.o3dp.lib3dphotography.O3DPhotoConfig r4 = r9.mO3DPhotoConfig
            boolean r4 = r4.getStereo()
            if (r4 == 0) goto L_0x0045
            r4 = 1073741824(0x40000000, float:2.0)
            goto L_0x0047
        L_0x0045:
            r4 = 1065353216(0x3f800000, float:1.0)
        L_0x0047:
            com.samsung.o3dp.lib3dphotography.O3DPContext r5 = r9.mO3DPContext
            int r5 = r5.getCropWidth()
            float r5 = (float) r5
            float r4 = r4 * r5
            com.samsung.o3dp.lib3dphotography.O3DPContext r5 = r9.mO3DPContext
            int r5 = r5.getCropHeight()
            float r5 = (float) r5
            float r4 = r4 / r5
            float r5 = (float) r0
            float r6 = (float) r2
            float r6 = r6 * r4
            int r7 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
            if (r7 >= 0) goto L_0x0066
            float r5 = r5 / r4
            int r4 = java.lang.Math.round(r5)
            r5 = r4
            r4 = r0
            goto L_0x006e
        L_0x0066:
            int r4 = java.lang.Math.round(r6)
        L_0x006a:
            r5 = r2
            goto L_0x006e
        L_0x006c:
            r4 = r0
            goto L_0x006a
        L_0x006e:
            com.samsung.o3dp.lib3dphotography.ResultRenderer r6 = r9.getResultRenderer()
            r6.setSize(r4, r5)
            com.samsung.o3dp.lib3dphotography.ResultRenderer r6 = r9.getResultRenderer()
            int r7 = r0 - r4
            int r8 = r2 - r5
            r6.setOffset(r7, r8)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "updateResultRendererSize {"
            r6.<init>(r7)
            java.lang.String r7 = ", "
            N2.j.x(r6, r0, r7, r2, r7)
            r6.append(r4)
            r6.append(r7)
            r6.append(r5)
            r6.append(r3)
            java.lang.String r0 = r6.toString()
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.i(r1, r0)
            com.samsung.o3dp.lib3dphotography.O3DPListener r0 = r9.mO3DPListener
            boolean r0 = r0 instanceof com.samsung.o3dp.lib3dphotography.O3DPLayerEffectListener
            if (r0 == 0) goto L_0x00aa
            com.samsung.o3dp.lib3dphotography.O3DPContext r9 = r9.mO3DPContext
            r9.setForegroundMaskResolution(r4, r5)
        L_0x00aa:
            return
        L_0x00ab:
            java.lang.String r9 = "updateResultRendererSize failed. invalid size {"
            java.lang.String r4 = ","
            java.lang.String r9 = A.a.d(r0, r2, r9, r4, r3)
            com.samsung.o3dp.lib3dphotography.utils.LogUtil.e(r1, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.o3dp.lib3dphotography.O3DPhotoPipeline.updateResultRendererSize():void");
    }

    public void abortRecordingVideo() {
        LogUtil.d(TAG, "E: abortRecordingVideo");
        this.mO3DPContext.setRecordAbortRequested(true);
        synchronized (this.mO3DPContext.mAbortSyncLock) {
            while (this.mO3DPContext.isVideoRecording()) {
                try {
                    LogUtil.i(TAG, "abort : mAbortLock.wait() start");
                    this.mO3DPContext.mAbortSyncLock.wait();
                    LogUtil.i(TAG, "abort : mAbortLock.wait() done");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    LogUtil.e(TAG, "Abort wait interrupted " + e);
                }
            }
        }
        this.mO3DPContext.setRecordAbortRequested(false);
        LogUtil.d(TAG, "X: abortRecordingVideo");
    }

    public void blockStaticAnimation() {
        this.mO3DPContext.blockStaticAnimation();
    }

    public void computeAnimation(int i2, int i7) {
        this.mO3DPContext.computeAnimation(i2, i7);
    }

    public void consumeNextFrame() {
        this.mO3DPContext.consumeNextFrame();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return this.mO3DPContext.getInputAdapter().onTouch((View) null, motionEvent);
    }

    public byte[] encodeHeif3d(byte[] bArr, MeshCompressOption meshCompressOption) {
        byte[] mesh = getMesh(meshCompressOption);
        if (mesh == null) {
            notifyFailure(O3DPListener.ErrorType.JPEG3D_RECORDING_FAIL, "Mesh retrieval failed. Check if O3DPipeline has a mesh");
            return null;
        }
        return new Heif3dEncoder().encodeImage(bArr, mesh, getAnimator().getAnimation().getAnimationScript().getBytes(StandardCharsets.UTF_8), this.mContext);
    }

    public byte[] encodeJpeg3d(byte[] bArr, MeshCompressOption meshCompressOption) {
        byte[] mesh = getMesh(meshCompressOption);
        if (mesh == null) {
            notifyFailure(O3DPListener.ErrorType.JPEG3D_RECORDING_FAIL, "Mesh retrieval failed. Check if O3DPipeline has a mesh");
            return null;
        }
        try {
            return new Jpeg3dEncoder().encodeImage(bArr, mesh, getAnimator().getAnimation().getAnimationScript().getBytes(StandardCharsets.UTF_8));
        } catch (MeshEncodingException e) {
            LogUtil.e(TAG, "Failed to encode jpeg3d", e);
            return null;
        }
    }

    public TreeMap<String, Animation> getAnimations(Context context) {
        return this.mO3DPContext.getAnimationManager().getAnimations();
    }

    public Animator getAnimator() {
        return this.mO3DPContext.getAnimationManager().getAnimator();
    }

    public O3DPhotoConfig getConfig() {
        return this.mO3DPhotoConfig;
    }

    public JSONObject getInfo() {
        return this.mInfo;
    }

    public InputAdapter getInputAdapter() {
        return this.mO3DPContext.getInputAdapter();
    }

    public byte[] getMesh() {
        return getMesh(MeshCompressOption.DRACO);
    }

    public O3DPContext getO3DPContext() {
        return this.mO3DPContext;
    }

    public ResultRenderer getResultRenderer() {
        return this.mO3DPContext.getRenderer();
    }

    public boolean initO3DPWrapper(Bitmap bitmap) {
        if (this.mO3DPhotoConfig == null) {
            LogUtil.e(TAG, "initO3DPWrapper : O3DPhotoConfig is null");
            return false;
        }
        JSONObject jSONObject = new JSONObject();
        this.mInfo = jSONObject;
        try {
            jSONObject.put(INFO_VIDEO_FRAME_RATE, 30);
            this.mInfo.put(INFO_RENDER_FRAME_RATE, 60);
        } catch (JSONException e) {
            LogUtil.w(TAG, "Failed to put information into mInfo : " + e);
        }
        this.mOriginFaces.clear();
        this.mOriginBodyObjects.clear();
        this.mOriginOtherObjects.clear();
        Bitmap copy = bitmap.copy(bitmap.getConfig(), bitmap.isMutable());
        this.mOriginalBitmap = copy;
        BitmapCropUtil.updateCropSize(this.mO3DPContext, copy.getWidth(), this.mOriginalBitmap.getHeight());
        if (getConfig().isPanorama()) {
            O3DPhotoConfig o3DPhotoConfig = this.mO3DPhotoConfig;
            if (o3DPhotoConfig != null) {
                return o3DPhotoConfig.applyConfig();
            }
            LogUtil.e(TAG, "initO3DPWrapper : O3DPhotoConfig became null during initialization");
            return false;
        }
        runObjectDetector(bitmap);
        O3DPhotoConfig o3DPhotoConfig2 = this.mO3DPhotoConfig;
        if (o3DPhotoConfig2 != null) {
            return o3DPhotoConfig2.applyConfig();
        }
        LogUtil.e(TAG, "initO3DPWrapper : O3DPhotoConfig became null during initialization");
        return false;
    }

    public boolean initO3DPWrapperWithMesh(int i2, int i7) {
        if (this.mO3DPhotoConfig == null) {
            LogUtil.e(TAG, "initO3DPWrapperWithMesh : O3DPhotoConfig is null");
            return false;
        }
        JSONObject jSONObject = new JSONObject();
        this.mInfo = jSONObject;
        try {
            jSONObject.put(INFO_VIDEO_FRAME_RATE, 30);
            this.mInfo.put(INFO_RENDER_FRAME_RATE, 60);
        } catch (JSONException e) {
            LogUtil.w(TAG, "Failed to put information into mInfo : " + e);
        }
        this.mOriginFaces.clear();
        this.mOriginBodyObjects.clear();
        this.mOriginOtherObjects.clear();
        this.mO3DPContext.setCropSize(i2, i7);
        O3DPhotoConfig o3DPhotoConfig = this.mO3DPhotoConfig;
        if (o3DPhotoConfig != null) {
            return o3DPhotoConfig.applyConfig();
        }
        LogUtil.e(TAG, "initO3DPWrapperWithMesh : O3DPhotoConfig became null during initialization");
        return false;
    }

    public boolean isRenderStarted() {
        return this.mGLContextManager.isRenderStarted();
    }

    public void pauseAnimation() {
        LogUtil.i(TAG, "pause Animation");
        pauseMotionSensor();
        this.mFrameRateController.removeFrameCallback();
    }

    public void pauseMotionSensor() {
        LogUtil.i(TAG, "pauseMotionSensor");
        this.mO3DPContext.pauseMotionSensor();
    }

    public void postFrameCallback(Consumer<Void> consumer) {
        resumeMotionSensor();
        this.mFrameRateController.removeFrameCallback();
        this.mFrameRateController.postFrameCallback(consumer);
    }

    public synchronized boolean prepareToRender() {
        List<Rect> list;
        Bitmap runEngine;
        try {
            int width = this.mOriginalBitmap.getWidth();
            int height = this.mOriginalBitmap.getHeight();
            if (width * height < 1000000 && (runEngine = MiracleFilterEngine.runEngine(this.mContext, this.mOriginalBitmap)) != null) {
                this.mOriginalBitmap = runEngine;
            }
            float calculateScaleFactor = calculateScaleFactor(width, height, 1080);
            if (this.mO3DPhotoConfig.isPanorama()) {
                this.mCroppedBitmap = ImageUtil.scaleBitmap(this.mOriginalBitmap, calculateScaleFactor);
                PanoramaAnimation panoramaAnimation = new PanoramaAnimation(this.mContext);
                this.mO3DPhotoConfig.setAnimation(panoramaAnimation);
                this.mO3DPListener.onRecommendedAnimation((Animation) panoramaAnimation);
                this.mO3DPListener.onRenderReady();
                return true;
            }
            this.mSegmentedBitmap = null;
            this.mEnclosingRect = null;
            this.mSegBounds = null;
            this.mEachSegmentedBitmaps = null;
            this.mO3DPContext.setSaliencyObjCnt(0);
            this.mO3DPContext.setNoSegInCenter(false);
            this.mO3DPContext.setObjectsScattered(false);
            ObjectResult predict = SegmentUtil.predict(this.mContext, this.mOriginalBitmap);
            if (predict != null) {
                ObjectInfo output = predict.getOutput();
                this.mSegmentedBitmap = SegmentUtil.getSegImage(output, width, height);
                this.mEnclosingRect = new Rect(output.getBoundRect());
                this.mSegBounds = new ArrayList();
                this.mEachSegmentedBitmaps = new ArrayList();
                for (ObjectInfo next : predict.getObjects()) {
                    this.mSegBounds.add(new Rect(next.getBoundRect()));
                    this.mEachSegmentedBitmaps.add(SegmentUtil.getSegImage(next, width, height));
                }
                this.mO3DPContext.setSaliencyObjCnt(this.mSegBounds.size());
                if (this.mOriginFaces.isEmpty() && this.mOriginBodyObjects.isEmpty()) {
                    this.mSegBounds.forEach(new j(this, 1));
                }
            }
            List<T> copyO3DPRectInstances = O3DPRectUtil.copyO3DPRectInstances(this.mOriginFaces);
            List<T> copyO3DPRectInstances2 = O3DPRectUtil.copyO3DPRectInstances(this.mOriginBodyObjects);
            copyO3DPRectInstances2.addAll(O3DPRectUtil.copyO3DPRectInstances(this.mOriginOtherObjects));
            Rect standardizeInputsObj = BitmapCropUtil.standardizeInputsObj(width, height, copyO3DPRectInstances, copyO3DPRectInstances2, this.mEnclosingRect, this.mSegBounds, calculateScaleFactor, this.mO3DPContext.getSalientObjCnt());
            this.mCroppedBitmap = ImageUtil.scaleBitmap(this.mOriginalBitmap, calculateScaleFactor, standardizeInputsObj);
            Bitmap bitmap = this.mSegmentedBitmap;
            if (bitmap != null) {
                this.mSegmentedBitmap = ImageUtil.scaleBitmap(bitmap, calculateScaleFactor, standardizeInputsObj);
                this.mEachSegmentedBitmaps.replaceAll(new k(calculateScaleFactor, standardizeInputsObj));
                if (this.mCroppedBitmap.getWidth() == this.mSegmentedBitmap.getWidth() && this.mCroppedBitmap.getHeight() == this.mSegmentedBitmap.getHeight() && ((float) JNI.countTransparentPixels(this.mSegmentedBitmap, 250)) < ((float) this.mSegmentedBitmap.getWidth()) * 0.01f * ((float) this.mSegmentedBitmap.getHeight())) {
                    this.mSegmentedBitmap = null;
                    this.mEnclosingRect = null;
                    this.mSegBounds = null;
                    this.mEachSegmentedBitmaps = null;
                    this.mO3DPContext.setSaliencyObjCnt(0);
                }
            }
            if (this.mO3DPContext.getSalientObjCnt() > 1 && (list = this.mSegBounds) != null) {
                this.mO3DPContext.setNoSegInCenter(AnimationPolicy.isNoSegInCenter(list, this.mCroppedBitmap.getWidth(), this.mCroppedBitmap.getHeight()));
            }
            Rect rect = this.mEnclosingRect;
            if (rect != null) {
                copyO3DPRectInstances = O3DPRectUtil.removeRectIfNotOverlap(copyO3DPRectInstances, rect);
                copyO3DPRectInstances2 = O3DPRectUtil.removeRectIfNotOverlap(copyO3DPRectInstances2, this.mEnclosingRect);
                this.mO3DPContext.setObjectsScattered(AnimationPolicy.areObjectsScattered(this.mCroppedBitmap, this.mEnclosingRect, copyO3DPRectInstances2));
            }
            this.mO3DPContext.setFaces(copyO3DPRectInstances);
            this.mO3DPContext.setDetectedObjects(copyO3DPRectInstances2);
            if (!this.mO3DPDepthEstimator.estimate(this.mCroppedBitmap)) {
                notifyFailure(O3DPListener.ErrorType.DEPTH_ESTIMATION_FAIL, "Depth map is null! Stop pipeline");
                return false;
            }
            DepthMap depthMap = this.mO3DPDepthEstimator.getDepthMap();
            if (depthMap == null) {
                notifyFailure(O3DPListener.ErrorType.DEPTH_ESTIMATION_FAIL, "Depth map is null!");
                return false;
            }
            Bitmap refineFPInSegmentImg = SegmentUtil.refineFPInSegmentImg(depthMap, this.mSegmentedBitmap);
            if (refineFPInSegmentImg != null) {
                this.mSegmentedBitmap.recycle();
                this.mSegmentedBitmap = refineFPInSegmentImg;
            }
            O3DPDepthScaler o3DPDepthScaler = new O3DPDepthScaler(depthMap);
            this.mNormalizedDepthMap = o3DPDepthScaler.getNormalizedDepthMap();
            O3DPListener o3DPListener = this.mO3DPListener;
            if (o3DPListener instanceof O3DPDataListener) {
                ((O3DPDataListener) o3DPListener).onDepthMapReady(depthMap);
            }
            if (o3DPDepthScaler.convertMetricDepth(this.mCroppedBitmap).scaleDepthBySegmentArea(this.mSegmentedBitmap)) {
                DepthMap depthMap2 = o3DPDepthScaler.getDepthMap();
                this.mScaledDepthMap = depthMap2;
                O3DPListener o3DPListener2 = this.mO3DPListener;
                if (o3DPListener2 instanceof O3DPDataListener) {
                    ((O3DPDataListener) o3DPListener2).onScaledDepthMapReady(depthMap2);
                }
            } else {
                this.mScaledDepthMap = this.mNormalizedDepthMap;
            }
            Bitmap bitmap2 = this.mSegmentedBitmap;
            if (bitmap2 != null) {
                if (this.mEachSegmentedBitmaps != null) {
                    Bitmap createBitmap = Bitmap.createBitmap(bitmap2.getWidth(), this.mSegmentedBitmap.getHeight(), Bitmap.Config.ARGB_8888);
                    if (compositeForegroundSegments(this.mEachSegmentedBitmaps, createBitmap, this.mO3DPDepthEstimator.getDepthMap())) {
                        this.mSegmentedBitmap = createBitmap;
                    }
                }
                O3DPListener o3DPListener3 = this.mO3DPListener;
                if (o3DPListener3 instanceof O3DPDataListener) {
                    ((O3DPDataListener) o3DPListener3).onSegmentationMapReady(this.mSegmentedBitmap);
                }
                this.mO3DPContext.setFloating(AnimationPolicy.isFloating(this.mSegmentedBitmap, copyO3DPRectInstances, copyO3DPRectInstances2));
                DepthMap depthMap3 = this.mScaledDepthMap;
                this.mO3DPContext.setSegObjectBehind(JNI.isSegmentedObjectBehind(depthMap3.data, depthMap3.width, depthMap3.height, this.mSegmentedBitmap));
            }
            Point focusPointOfObjects = AnimationPolicy.getFocusPointOfObjects(this.mCroppedBitmap.getWidth(), this.mCroppedBitmap.getHeight(), this.mScaledDepthMap, this.mSegmentedBitmap, this.mO3DPContext.getFaces(), this.mO3DPContext.getDetectedObjects(), this.mO3DPContext.getSalientObjCnt());
            autoAnimation(focusPointOfObjects);
            this.mO3DPContext.setFocusPoint(focusPointOfObjects);
            this.mInfo.put("AnimationName", this.mO3DPhotoConfig.getAnimationName());
            this.mInfo.put("SalientObjCnt", this.mO3DPContext.getSalientObjCnt());
            this.mInfo.put("faceCnt in Segmentation", this.mO3DPContext.getFaces().size());
            this.mInfo.put("isFloating", this.mO3DPContext.isFloating());
            this.mInfo.put("isSegObjectBehind", this.mO3DPContext.isSegObjectBehind());
            this.mInfo.put("isNoSegInCenter", this.mO3DPContext.isNoSegInCenter());
            String animationName = this.mO3DPhotoConfig.getAnimationName();
            if (isInBlockAnimationList(animationName)) {
                O3DPListener.ErrorType errorType = O3DPListener.ErrorType.BLOCKED_ANIMATION;
                notifyFailure(errorType, animationName + " : Blocked animation for Live Effect");
                return false;
            } else if (!this.mO3DPContext.isStaticAnimationBlocked() || !this.mO3DPContext.getAnimationManager().getAnimator().getAnimation().isStaticAnimation()) {
                for (Animation updateDynamicsIntensity : this.mO3DPContext.getAnimationManager().getAnimations().values()) {
                    updateDynamicsIntensity(updateDynamicsIntensity);
                }
                this.mO3DPListener.onRenderReady();
                return true;
            } else {
                O3DPListener.ErrorType errorType2 = O3DPListener.ErrorType.BLOCKED_ANIMATION;
                notifyFailure(errorType2, animationName + " : Blocked static animation for Live Effect");
                return false;
            }
        } catch (JSONException e) {
            LogUtil.w(TAG, "Failed to put information into mInfo : " + e);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public void recordHeif3d(Bitmap bitmap, String str, MeshCompressOption meshCompressOption, O3DPHeif3DListener o3DPHeif3DListener) {
        LogUtil.d(TAG, "E: recordHeif3d");
        this.mO3DPListener = o3DPHeif3DListener;
        ThreadHandler.getInstance().runOnWorkerThread(new c(this, bitmap, meshCompressOption, str, o3DPHeif3DListener, 24));
    }

    public void recordJpeg3d(Bitmap bitmap, String str, MeshCompressOption meshCompressOption, O3DPJpeg3DListener o3DPJpeg3DListener) {
        LogUtil.d(TAG, "E: recordJpeg3d");
        this.mO3DPListener = o3DPJpeg3DListener;
        ThreadHandler.getInstance().runOnWorkerThread(new c(this, bitmap, meshCompressOption, str, o3DPJpeg3DListener, 23));
    }

    public synchronized void recordSef(String str, MeshCompressOption meshCompressOption) {
        byte[] mesh = getMesh(meshCompressOption);
        byte[] bytes = getAnimator().getAnimation().getAnimationScript().getBytes(StandardCharsets.UTF_8);
        if (this.mOriginalBitmap == null) {
            throw new IllegalStateException("Failed to record jpeg3d because bitmap is not given, please check setBitmap is called first");
        } else if (mesh == null || bytes == null) {
            throw new IllegalStateException("Failed to record jpeg3d because mesh or camera animation is not set");
        } else {
            MeshSefManager.mux(str, mesh, this.mO3DPContext.getAnimationManager().getAnimator().getAnimation().getAnimationScript());
            LogUtil.d(TAG, "recordJpeg3dSef done");
        }
    }

    public void recordVideo(Bitmap bitmap, String str, O3DPVideoListener o3DPVideoListener) {
        LogUtil.d(TAG, "E: recordVideo");
        this.mO3DPContext.setVideoRecording(true);
        this.mO3DPListener = o3DPVideoListener;
        ThreadHandler.getInstance().runOnWorkerThread(new d((Object) this, (Object) str, (Object) bitmap, 10));
        LogUtil.d(TAG, "X: recordVideo");
    }

    public synchronized void release() {
        try {
            LogUtil.i(TAG, "E: release");
            ThreadHandler.getInstance().release();
            this.mO3DPDepthEstimator = null;
            this.mEnclosingRect = null;
            Bitmap bitmap = this.mOriginalBitmap;
            if (bitmap != null) {
                bitmap.recycle();
                this.mOriginalBitmap = null;
            }
            Bitmap bitmap2 = this.mCroppedBitmap;
            if (bitmap2 != null) {
                bitmap2.recycle();
                this.mCroppedBitmap = null;
            }
            O3DPhotoConfig o3DPhotoConfig = this.mO3DPhotoConfig;
            if (o3DPhotoConfig != null) {
                o3DPhotoConfig.removeOnConfigChangedListener(this.mO3DPContext);
                this.mO3DPhotoConfig = null;
            }
            Bitmap bitmap3 = this.mSegmentedBitmap;
            if (bitmap3 != null) {
                bitmap3.recycle();
                this.mSegmentedBitmap = null;
            }
            this.mO3DPContext.setVideoRecording(false);
            this.mO3DPContext.setRecordAbortRequested(false);
            FrameRateController frameRateController = this.mFrameRateController;
            if (frameRateController == null || !frameRateController.hasHandlerThread()) {
                this.mO3DPContext.release();
                FrameRateController frameRateController2 = this.mFrameRateController;
                if (frameRateController2 != null) {
                    frameRateController2.release();
                }
            } else {
                this.mFrameRateController.runOnGLThread(new h(this, 2));
            }
            LogUtil.i(TAG, "X: release");
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public void removeLiveEffect(String str) {
        long lastModified = FileUtil.getLastModified(str);
        SefUtil.SefData allSefData = SefUtil.getAllSefData(str);
        ActivityUtil.saveBytesToFile(str, removeMesh(FileExtensionParser.getSupportedExtension(str), FileUtil.readFileToByteArray(str)));
        SefUtil.addSefData(str, allSefData);
        SefUtil.deleteLiveEffectSefData(str);
        FileUtil.restoreLastModified(str, lastModified);
    }

    public byte[] removeMeshFromHeif3d(byte[] bArr) {
        return new Heif3dDecoder().decode(this.mContext, bArr).image();
    }

    public byte[] removeMeshFromJpeg3d(byte[] bArr) {
        try {
            return new Jpeg3dRemover().remove(bArr);
        } catch (MeshRemovingException e) {
            LogUtil.e(TAG, "Failed to remove mesh", e);
            return null;
        }
    }

    public void render(Bitmap bitmap, O3DPListener o3DPListener) {
        LogUtil.i(TAG, "E: render by bitmap");
        this.mO3DPListener = o3DPListener;
        if (this.mFrameRateController == null) {
            notifyFailure(O3DPListener.ErrorType.ABNORMAL_USE, "This method requires either a glThread or a surfaceHolder.");
            return;
        }
        ThreadHandler.getInstance().runOnWorkerThread(new i(0, this, bitmap));
        LogUtil.i(TAG, "X: render by bitmap");
    }

    public void requestRender() {
        LogUtil.i(TAG, "request Render");
        this.mFrameRateController.runOnGLThread(new h(this, 1));
    }

    public void resetAnimation() {
        LogUtil.i(TAG, "reset Animation");
        getAnimator().resetAnimation();
    }

    public void resumeAnimation() {
        LogUtil.i(TAG, "resume Animation");
        if (!this.mGLContextManager.isRenderStarted()) {
            LogUtil.w(TAG, "Rendering has not started yet");
            return;
        }
        resumeMotionSensor();
        this.mFrameRateController.removeFrameCallback();
        this.mFrameRateController.runOnGLThread(new h(this, 0));
        this.mFrameRateController.postFrameCallback(new j(this, 0));
    }

    public void resumeMotionSensor() {
        LogUtil.i(TAG, "resume MotionSensor");
        this.mO3DPContext.resumeMotionSensor();
    }

    public void setRenderSize(int i2, int i7) {
        LogUtil.i(TAG, a.d(i2, i7, "setRenderSize(", ArcCommonLog.TAG_COMMA, ")"));
        this.mO3DPContext.setTargetRenderSize(i2, i7);
        updateResultRendererSize();
    }

    public void visualize() {
        this.mGLContextManager.visualize(this.mO3DPListener);
    }

    public byte[] getMesh(MeshCompressOption meshCompressOption) {
        return this.mO3DPContext.getMesh(this.mContext, meshCompressOption);
    }

    public O3DPhotoPipeline(Context context, O3DPhotoConfig o3DPhotoConfig, Surface surface) {
        this(context, o3DPhotoConfig);
        FrameRateController frameRateController = new FrameRateController();
        this.mFrameRateController = frameRateController;
        frameRateController.runOnGLThread(new i(2, this, surface));
        this.mGLContextManager = new GLContextManager(this.mO3DPContext, this.mO3DPhotoConfig, this.mFrameRateController);
    }

    public synchronized void recordHeif3d(Uri uri) {
        recordHeif3d(this.mCroppedBitmap, uri, MeshCompressOption.DRACO);
    }

    public synchronized void recordJpeg3d(Uri uri) {
        recordJpeg3d(this.mCroppedBitmap, uri, MeshCompressOption.DRACO);
    }

    public synchronized void recordHeif3d(Uri uri, MeshCompressOption meshCompressOption) {
        recordHeif3d(this.mCroppedBitmap, uri, meshCompressOption);
    }

    public synchronized void recordJpeg3d(Uri uri, MeshCompressOption meshCompressOption) {
        recordJpeg3d(this.mCroppedBitmap, uri, meshCompressOption);
    }

    public void render(AnimatedMesh animatedMesh, O3DPListener o3DPListener) {
        LogUtil.i(TAG, "E: render by animatedMesh");
        if (this.mFrameRateController == null) {
            notifyFailure(O3DPListener.ErrorType.ABNORMAL_USE, "This method requires either a glThread or a surfaceHolder.");
            return;
        }
        this.mO3DPListener = o3DPListener;
        ThreadHandler.getInstance().runOnWorkerThread(new i(1, this, animatedMesh));
        LogUtil.i(TAG, "X: render by animatedMesh");
    }

    public O3DPhotoPipeline(Context context, O3DPhotoConfig o3DPhotoConfig) {
        this.mOriginFaces = new ArrayList();
        this.mOriginBodyObjects = new ArrayList();
        this.mOriginOtherObjects = new ArrayList();
        LogUtil.i(TAG, "O3DP SDK : 8.5.50");
        this.mO3DPhotoConfig = o3DPhotoConfig;
        this.mContext = context;
        O3DPContext o3DPContext = new O3DPContext(context, o3DPhotoConfig);
        this.mO3DPContext = o3DPContext;
        o3DPContext.initEGL();
        o3DPContext.initTextures();
        this.mO3DPhotoConfig.addOnConfigChangedListener(o3DPContext);
        this.mO3DPDepthEstimator = new O3DPDepthEstimator(context);
    }

    public synchronized void recordHeif3d(Bitmap bitmap, Uri uri, MeshCompressOption meshCompressOption) {
        Heif3dEncoder heif3dEncoder = new Heif3dEncoder();
        byte[] mesh = getMesh(meshCompressOption);
        byte[] bytes = getAnimator().getAnimation().getAnimationScript().getBytes(StandardCharsets.UTF_8);
        if (this.mOriginalBitmap == null) {
            throw new Heif3dException("Failed to record heif3d because bitmap is not given, please check setBitmap is called first.");
        } else if (mesh == null || bytes == null) {
            throw new Heif3dException("Failed to record heif3d because mesh or camera animation is not set.");
        } else {
            ActivityUtil.save(this.mContext.getContentResolver(), uri, heif3dEncoder.encodeImage(bitmap, mesh, bytes, this.mContext));
        }
    }

    public synchronized void recordJpeg3d(Bitmap bitmap, Uri uri, MeshCompressOption meshCompressOption) {
        Jpeg3dEncoder jpeg3dEncoder = new Jpeg3dEncoder();
        byte[] mesh = getMesh(meshCompressOption);
        byte[] bytes = getAnimator().getAnimation().getAnimationScript().getBytes(StandardCharsets.UTF_8);
        if (this.mOriginalBitmap == null) {
            throw new IllegalStateException("Failed to record jpeg3d because bitmap is not given, please check setBitmap is called first.");
        } else if (mesh == null || bytes == null) {
            throw new IllegalStateException("Failed to record jpeg3d because mesh or camera animation is not set.");
        } else {
            ActivityUtil.save(this.mContext.getContentResolver(), uri, jpeg3dEncoder.encodeImage(bitmap, mesh, bytes));
        }
    }
}
