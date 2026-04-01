package com.samsung.android.imagetranslation;

import android.content.Context;
import com.samsung.android.feature.SemFloatingFeature;
import com.samsung.android.imagetranslation.common.LTTLogger;
import com.samsung.android.imagetranslation.common.LttEngineErrors;
import com.samsung.android.imagetranslation.data.LttEngineException;
import com.samsung.android.imagetranslation.data.LttOcrResult;
import com.samsung.android.imagetranslation.data.MaskRequest;
import com.samsung.android.imagetranslation.data.RenderParam;
import com.samsung.android.imagetranslation.data.Session;
import com.samsung.android.imagetranslation.inpainting.InpainterParam;
import com.samsung.android.imagetranslation.jni.KeyFrameParam;
import com.samsung.android.imagetranslation.jni.LttNativeHelper;
import com.samsung.android.imagetranslation.util.LineBreakUtil;
import com.samsung.android.livetranslation.LttEngine;
import com.samsung.android.livetranslation.data.ProcessParam;
import java.io.File;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LttEngineClient extends LttEngine {
    public static final int ERROR_HARD_ERROR = -400;
    public static final int ERROR_INPAINTER_SOFT_ERROR = -200;
    public static final int ERROR_RENDERER_SOFT_ERROR = -300;
    public static final int NO_ERROR = 1;
    private static final String TAG = "LttEngineClient";
    private static LttEngineClient lttEngineClient;
    private LttEngine lttEngine;

    /* renamed from: com.samsung.android.imagetranslation.LttEngineClient$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$imagetranslation$LttEngineClient$Key;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.samsung.android.imagetranslation.LttEngineClient$Key[] r0 = com.samsung.android.imagetranslation.LttEngineClient.Key.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$imagetranslation$LttEngineClient$Key = r0
                com.samsung.android.imagetranslation.LttEngineClient$Key r1 = com.samsung.android.imagetranslation.LttEngineClient.Key.KEY_JAR_VERSION     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$imagetranslation$LttEngineClient$Key     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.imagetranslation.LttEngineClient$Key r1 = com.samsung.android.imagetranslation.LttEngineClient.Key.KEY_NATIVE_VERSION     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.imagetranslation.LttEngineClient.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum Key {
        KEY_JAR_VERSION,
        KEY_NATIVE_VERSION
    }

    public static LttEngineClient createInstance() {
        LttEngineClient lttEngineClient2 = lttEngineClient;
        if (lttEngineClient2 != null) {
            return lttEngineClient2;
        }
        LTTLogger.d(TAG, "lttEngineClient instance created");
        LttEngineClient lttEngineClient3 = new LttEngineClient();
        lttEngineClient = lttEngineClient3;
        return lttEngineClient3;
    }

    public static boolean isImageTranslationAvailable() {
        boolean exists = new File("/system/lib64/libLttEngine.camera.samsung.so").exists();
        if (exists) {
            int i2 = SemFloatingFeature.getInstance().getInt("SEC_FLOATING_FEATURE_VISION_CONFIG_IMAGE_TRANSLATION_OVERLAY_VERSION");
            String str = TAG;
            LTTLogger.i(str, "Image Translation supported, Version : " + i2);
            return exists;
        }
        LTTLogger.i(TAG, "Image Translation not supported ");
        return exists;
    }

    public void destroyInstance() {
        String str = TAG;
        LTTLogger.i(str, "destroyInstance");
        super.release();
        if (lttEngineClient != null) {
            lttEngineClient = null;
            LTTLogger.d(str, "lttEngineClient instance destroyed");
        }
    }

    public void getMaskForInpainting(int i2, MaskRequest maskRequest) {
        String str = TAG;
        LTTLogger.i(str, "Mask request: request id - " + i2);
        long currentTimeMillis = System.currentTimeMillis();
        InpainterParam inpainterParam = new InpainterParam(i2, maskRequest.getInputBitmap(), maskRequest.getLttOcrResult());
        if (!this.isLttEngineInitialized || !this.isMaskHelperInitialized) {
            LTTLogger.e(str, "Error: Ltt Engine not initialized to generate mask");
            LttEngineListener lttEngineListener = this.lttEngineListener;
            if (lttEngineListener != null) {
                lttEngineListener.onFailure(i2, LttEngineErrors.ERROR_INPAINTING_GET_MASK_NATIVE_FAILED);
                return;
            }
            return;
        }
        try {
            validateMaskInputImage(inpainterParam.getInputImage());
            validateInpainterOCR(inpainterParam.getLttOcrResult());
            preProcessOnInputImage(inpainterParam.getInputImage(), inpainterParam);
            inpainterParam.setRequestId(i2);
            super.postMaskTask(inpainterParam);
            LTTLogger.p(str, "getMaskForInpainting: validation time taken : " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
        } catch (LttEngineException e) {
            String str2 = TAG;
            LTTLogger.w(str2, "getMaskForInpainting: error code " + e.getErrorCode() + ", exception - " + e.getMessage());
            this.lttEngineListener.onFailure(i2, -200);
        }
    }

    public Object getParam(Key key) {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$imagetranslation$LttEngineClient$Key[key.ordinal()];
        if (i2 == 1) {
            return LttEngine.JAR_VERSION;
        }
        if (i2 == 2 && this.isLttEngineInitialized) {
            return LttNativeHelper.NATIVE_LIBRARY_VERSION;
        }
        return null;
    }

    @Deprecated
    public List<String> getResultsWithLineBreak(LttOcrResult lttOcrResult, LttOcrResult lttOcrResult2) {
        return new LineBreakUtil(lttOcrResult2).initResultWithSourceText(lttOcrResult.getBlockInfoList());
    }

    public void inPaintImage(int i2, InpainterParam inpainterParam) {
        String str = TAG;
        LTTLogger.i(str, "New inPainting request: request id - " + i2);
        long currentTimeMillis = System.currentTimeMillis();
        if (!this.isInpainterHelperInitialized) {
            LTTLogger.e(str, "Error: Inpainter Engine not initialized");
            LttEngineListener lttEngineListener = this.lttEngineListener;
            if (lttEngineListener != null) {
                lttEngineListener.onFailure(inpainterParam.getRequestId(), -200);
                return;
            }
            return;
        }
        try {
            validateInpainterImage(inpainterParam.getInputImage());
            validateInpainterOCR(inpainterParam.getLttOcrResult());
            validateInpaintingNeeded(inpainterParam);
            preProcessOnInputImage(inpainterParam.getInputImage(), inpainterParam);
            inpainterParam.setRequestId(i2);
            super.inPaintImage(inpainterParam);
            LTTLogger.p(str, "inPaintImage: Total time taken : " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
        } catch (LttEngineException e) {
            String str2 = TAG;
            LTTLogger.w(str2, "inPaintImage: error code " + e.getErrorCode() + ", exception - " + e.getMessage());
            this.lttEngineListener.onFailure(i2, -200);
        }
    }

    public int initialize(Session session) {
        String str = TAG;
        LTTLogger.i(str, "initialize");
        if (!this.isLttEngineInitialized) {
            return super.initialize(session);
        }
        LTTLogger.d(str, "Engine already initialized");
        return 1;
    }

    public void initialize4x(Context context) {
        LTTLogger.i(TAG, "initialize 4x");
        this.lttEngine = LttEngine.createInstance(context);
    }

    public void release() {
        LTTLogger.i(TAG, "release");
        super.release();
    }

    public void release4x() {
        LTTLogger.i(TAG, "release 4x");
        this.lttEngine.release();
    }

    public void renderImage(RenderParam renderParam) {
        if (renderParam.getLttEngineListener() != null) {
            LTTLogger.i(TAG, "New render request for 4.x");
            this.lttEngine.processImage(renderParam.getInputBitmap(), renderParam.getLttOcrResult(), renderParam.getTrlResult(), (ProcessParam) null, renderParam.getLttEngineListener());
        }
    }

    public void setParam(Object obj) {
        LTTLogger.i(TAG, "setParam");
    }

    public List<String> getResultsWithLineBreak(Context context, LttOcrResult lttOcrResult) {
        return LineBreakUtil.initResultWithSourceText(context, lttOcrResult.getBlockInfoList());
    }

    public void renderImage(int i2, RenderParam renderParam) {
        String str = TAG;
        LTTLogger.i(str, "New render request: request id - " + i2);
        if (!this.isRenderEngineInitialized) {
            LTTLogger.e(str, "Error: LttEngine not initialized");
            LttEngineListener lttEngineListener = this.lttEngineListener;
            if (lttEngineListener != null) {
                lttEngineListener.onFailure(i2, -400);
                return;
            }
            return;
        }
        KeyFrameParam keyFrameParam = new KeyFrameParam();
        try {
            validateInputImage(renderParam.getInputBitmap());
            validateOCRAndTranslation(renderParam.getLttOcrResult(), renderParam.getTrlResult());
            preProcessOnInputImage(renderParam.getInputBitmap(), keyFrameParam);
            if (renderParam.getInPaintedBitmap() != null) {
                LTTLogger.i(str, "renderImage: original image size (width-" + keyFrameParam.getInputImage().getWidth() + ", height-" + keyFrameParam.getInputImage().getHeight() + ") \n OE image size (width-" + renderParam.getInPaintedBitmap().getWidth() + " height-" + renderParam.getInPaintedBitmap().getHeight() + ")");
            } else {
                LTTLogger.i(str, "renderImage: original image size (width-" + keyFrameParam.getInputImage().getWidth() + ", height-" + keyFrameParam.getInputImage().getHeight() + ")");
            }
            keyFrameParam.setRequestId(i2);
            keyFrameParam.setContext(this.session.getContext());
            setDstLang(renderParam.getDestLanguage(), keyFrameParam);
            setImageFormat(renderParam.getImageFormat(), keyFrameParam);
            keyFrameParam.setLttOcrResult(renderParam.getLttOcrResult());
            keyFrameParam.setTrlResult(renderParam.getTrlResult());
            keyFrameParam.setInPaintedImage(renderParam.getInPaintedBitmap());
            LTTLogger.i(str, "renderImage:All Inputs received");
            postRenderTask(keyFrameParam);
        } catch (LttEngineException e) {
            String str2 = TAG;
            LTTLogger.e(str2, "Render Image API Exception : error code " + e.getErrorCode() + ", exception - " + e.getMessage());
            this.lttEngineListener.onFailure(i2, e.getErrorCode());
        }
    }
}
