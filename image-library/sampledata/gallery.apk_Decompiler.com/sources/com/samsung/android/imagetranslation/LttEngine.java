package com.samsung.android.imagetranslation;

import N2.j;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.util.Size;
import com.samsung.android.imagetranslation.common.Dump;
import com.samsung.android.imagetranslation.common.LTTLogger;
import com.samsung.android.imagetranslation.common.LttEngineErrors;
import com.samsung.android.imagetranslation.data.LttEngineException;
import com.samsung.android.imagetranslation.data.LttOcrResult;
import com.samsung.android.imagetranslation.data.MaskResponse;
import com.samsung.android.imagetranslation.data.Session;
import com.samsung.android.imagetranslation.inpainting.InpainterHelper;
import com.samsung.android.imagetranslation.inpainting.InpainterInitParam;
import com.samsung.android.imagetranslation.inpainting.InpainterParam;
import com.samsung.android.imagetranslation.inpainting.InpainterResult;
import com.samsung.android.imagetranslation.jni.KeyFrameParam;
import com.samsung.android.imagetranslation.jni.LttNativeHelper;
import com.samsung.android.imagetranslation.task.PostHybridMaskGenerationTask;
import com.samsung.android.imagetranslation.task.PostInpaintingTask;
import com.samsung.android.imagetranslation.task.PostRenderTask;
import com.samsung.android.imagetranslation.util.Util;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LttEngine {
    private static final String INPAINTER_SERVICE = "Inpainter";
    public static final String JAR_VERSION = "5.4.0";
    private static final String MASK_GENERATION_SERVICE = "Mask";
    private static final String RENDERER_SERVICE = "Renderer";
    /* access modifiers changed from: private */
    public static final String TAG = "LttEngine";
    protected InpainterHelper inpainterHelper;
    protected ExecutorService inpainterService;
    protected boolean isInpainterHelperInitialized;
    protected boolean isLttEngineInitialized;
    protected boolean isMaskHelperInitialized;
    protected boolean isRenderEngineInitialized;
    protected LttEngineListener lttEngineListener;
    protected LttNativeHelper lttNativeHelper;
    Handler mainHandler;
    protected ExecutorService maskGenerationService;
    protected ExecutorService rendererService;
    protected Session session;
    TaskListener taskListener = new TaskListener() {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onFailure$1(int i2, int i7) {
            LttEngine.this.lttEngineListener.onFailure(i2, LttEngineErrors.mapToClientErrorType(i7));
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onInpaintingSuccess$2(int i2, InpainterResult inpainterResult) {
            LttEngine.this.lttEngineListener.onInPaintingSuccess(i2, inpainterResult);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onMaskAvailable$3(int i2, MaskResponse maskResponse) {
            LttEngine.this.lttEngineListener.onMaskAvailable(i2, maskResponse);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onRenderSuccess$0(int i2, Bitmap bitmap) {
            LttEngine.this.lttEngineListener.onRenderSuccess(i2, bitmap);
        }

        public void onFailure(int i2, int i7) {
            if (LttEngineErrors.mapToClientErrorType(i7) == -200) {
                String a7 = LttEngine.TAG;
                LTTLogger.w(a7, "onFailure soft error : request id " + i2 + " code " + i7);
            } else {
                String a10 = LttEngine.TAG;
                LTTLogger.e(a10, "onFailure: request id " + i2 + " code " + i7);
            }
            LttEngine lttEngine = LttEngine.this;
            Handler handler = lttEngine.mainHandler;
            if (handler == null || lttEngine.lttEngineListener == null) {
                String a11 = LttEngine.TAG;
                LTTLogger.w(a11, "mainHandler : " + LttEngine.this.mainHandler + " lttEngineListener" + LttEngine.this.lttEngineListener);
                return;
            }
            handler.post(new a(this, i2, i7));
        }

        public void onInpaintingSuccess(int i2, InpainterResult inpainterResult) {
            String a7 = LttEngine.TAG;
            LTTLogger.i(a7, "onInpaintingSuccess reqId : " + i2);
            LttEngine lttEngine = LttEngine.this;
            lttEngine.printRequestStatus(lttEngine.inpainterService, LttEngine.INPAINTER_SERVICE);
            LttEngine lttEngine2 = LttEngine.this;
            Handler handler = lttEngine2.mainHandler;
            if (handler == null || lttEngine2.lttEngineListener == null) {
                String a10 = LttEngine.TAG;
                LTTLogger.w(a10, "mainHandler : " + LttEngine.this.mainHandler + " lttEngineListener" + LttEngine.this.lttEngineListener);
                return;
            }
            handler.post(new b(this, i2, inpainterResult, 1));
        }

        public void onMaskAvailable(int i2, MaskResponse maskResponse) {
            String a7 = LttEngine.TAG;
            LTTLogger.i(a7, "onMaskAvailable reqId : " + i2);
            LttEngine lttEngine = LttEngine.this;
            lttEngine.printRequestStatus(lttEngine.maskGenerationService, LttEngine.MASK_GENERATION_SERVICE);
            LttEngine lttEngine2 = LttEngine.this;
            Handler handler = lttEngine2.mainHandler;
            if (handler == null || lttEngine2.lttEngineListener == null) {
                String a10 = LttEngine.TAG;
                LTTLogger.w(a10, "mainHandler : " + LttEngine.this.mainHandler + " lttEngineListener" + LttEngine.this.lttEngineListener);
                return;
            }
            handler.post(new b(this, i2, maskResponse, 2));
        }

        public void onRenderSuccess(int i2, Bitmap bitmap) {
            String a7 = LttEngine.TAG;
            LTTLogger.i(a7, "onRenderSuccess : request id " + i2);
            LttEngine lttEngine = LttEngine.this;
            lttEngine.printRequestStatus(lttEngine.rendererService, LttEngine.RENDERER_SERVICE);
            if (Dump.IS_INIT_DUMP_SUCCESS && Dump.IS_RENDERED_FRAME_ENABLED) {
                Dump.dumpBitmap(bitmap, "result_dump" + System.currentTimeMillis() + ".png", "Result");
            }
            LttEngine lttEngine2 = LttEngine.this;
            Handler handler = lttEngine2.mainHandler;
            if (handler == null || lttEngine2.lttEngineListener == null) {
                String a10 = LttEngine.TAG;
                LTTLogger.w(a10, "mainHandler : " + LttEngine.this.mainHandler + " lttEngineListener" + LttEngine.this.lttEngineListener);
                return;
            }
            handler.post(new b(this, i2, bitmap, 0));
        }
    };

    private Size decideAndSetResizeParams(int i2, int i7) {
        boolean z;
        int i8;
        if (i2 > 512 || i7 > 512) {
            z = false;
        } else {
            z = true;
        }
        if (z) {
            int i10 = 1080;
            if (i2 == i7) {
                i8 = 1080;
            } else if (i2 > i7) {
                i8 = (int) Math.ceil((((double) i7) / ((double) i2)) * 1080.0d);
            } else {
                i8 = 1080;
                i10 = (int) Math.ceil((((double) i2) / ((double) i7)) * 1080.0d);
            }
            LTTLogger.d(TAG, "upscaleWidth=" + i10 + ", upscaleHeight=" + i8);
            if (!(i10 == 0 || i8 == 0)) {
                i2 = i10;
                i7 = i8;
            }
        }
        int i11 = (i2 >> 2) << 2;
        if (!(i2 - i11 == 0 && i7 - ((i7 >> 2) << 2) == 0)) {
            int i12 = (i7 >> 2) << 2;
            if (i11 < i2) {
                i11 += 4;
            }
            i2 = i11;
            if (i12 < i7) {
                i12 += 4;
            }
            i7 = i12;
            LTTLogger.d(TAG, "After 4bit aligned : width=" + i2 + ", height=" + i7);
        }
        return new Size(i2, i7);
    }

    private float updateScaleFactor(int i2, int i7, int i8, int i10) {
        float f;
        float f5;
        if (i2 >= i7) {
            f = (float) i2;
            f5 = (float) i10;
        } else {
            f = (float) i7;
            f5 = (float) i8;
        }
        float f8 = f / f5;
        String str = TAG;
        LTTLogger.v(str, "setImage: scale factor - " + f8);
        return f8;
    }

    public void inPaintImage(InpainterParam inpainterParam) {
        if (!Dump.IS_INIT_DUMP_SUCCESS || !Dump.DISABLE_INPAINTING) {
            inpainterParam.setContext(this.session.getContext());
            this.inpainterService.submit(new PostInpaintingTask(this.inpainterHelper, this.lttNativeHelper, inpainterParam, this.taskListener));
            printRequestStatus(this.inpainterService, INPAINTER_SERVICE);
            return;
        }
        LTTLogger.i(TAG, "Inpainting feature disabled");
        this.taskListener.onFailure(inpainterParam.getRequestId(), LttEngineErrors.ERROR_INPAINTING_NOT_SUPPORTED);
    }

    public int initialize(Session session2) {
        String str = TAG;
        LTTLogger.i(str, "LttEngine: JAR_VERSION : 5.4.0");
        try {
            long currentTimeMillis = System.currentTimeMillis();
            setSession(session2);
            this.mainHandler = new Handler(Looper.getMainLooper());
            if (!this.isRenderEngineInitialized) {
                long currentTimeMillis2 = System.currentTimeMillis();
                LttNativeHelper lttNativeHelper2 = new LttNativeHelper();
                this.lttNativeHelper = lttNativeHelper2;
                lttNativeHelper2.initialize();
                this.isRenderEngineInitialized = true;
                this.rendererService = Executors.newFixedThreadPool(1);
                LTTLogger.i(str, "Renderer module initialized");
                LTTLogger.p(str, "Renderer module initialized in : " + (System.currentTimeMillis() - currentTimeMillis2) + "ms");
            }
            if (!this.isInpainterHelperInitialized && session2.isInpainterModuleEnabled()) {
                long currentTimeMillis3 = System.currentTimeMillis();
                InpainterHelper createInstance = InpainterHelper.createInstance();
                this.inpainterHelper = createInstance;
                createInstance.initializeInpainter(new InpainterInitParam(session2.getContext(), this.taskListener));
                this.inpainterService = Executors.newFixedThreadPool(1);
                this.isInpainterHelperInitialized = true;
                LTTLogger.i(str, "Inpainter module initialized");
                LTTLogger.p(str, "Inpainter module initialized in : " + (System.currentTimeMillis() - currentTimeMillis3) + "ms");
            }
            if (!session2.isInpainterModuleEnabled()) {
                this.maskGenerationService = Executors.newFixedThreadPool(1);
                this.isMaskHelperInitialized = true;
            }
            this.isLttEngineInitialized = true;
            LTTLogger.i(str, "Engine initialized");
            LTTLogger.p(str, "Engine initialized in : " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
            Dump.initDump(session2.getContext());
            return 1;
        } catch (LttEngineException e) {
            String str2 = TAG;
            LTTLogger.e(str2, "Initialize API Exception: error code " + e.getErrorCode() + ", exception - " + e.getMessage());
            if (!this.isRenderEngineInitialized) {
                return -400;
            }
            if (!this.isInpainterHelperInitialized || LttEngineErrors.mapToClientErrorType(e.getErrorCode()) == -200) {
                return LttEngineErrors.ERROR_INPAINTING_INITIALIZATION_FAILED;
            }
            return e.getErrorCode();
        }
    }

    public void postMaskTask(InpainterParam inpainterParam) {
        inpainterParam.setContext(this.session.getContext());
        this.maskGenerationService.submit(new PostHybridMaskGenerationTask(this.lttNativeHelper, inpainterParam, this.taskListener));
        printRequestStatus(this.maskGenerationService, MASK_GENERATION_SERVICE);
    }

    public void postRenderTask(KeyFrameParam keyFrameParam) {
        PostRenderTask postRenderTask = new PostRenderTask(keyFrameParam, this.lttNativeHelper, this.taskListener);
        String str = TAG;
        LTTLogger.d(str, "New request posted into queue - request id " + keyFrameParam.getRequestId());
        this.rendererService.submit(postRenderTask);
        printRequestStatus(this.rendererService, RENDERER_SERVICE);
    }

    public void preProcessOnInputImage(Bitmap bitmap, InpainterParam inpainterParam) {
        String str = TAG;
        LTTLogger.v(str, "preProcessOnInputImage_Inpainter: E");
        long currentTimeMillis = System.currentTimeMillis();
        inpainterParam.setOriginalImageSize(new Size(bitmap.getWidth(), bitmap.getHeight()));
        inpainterParam.setInputImage(bitmap);
        LTTLogger.v(str, "preProcessOnInputImage_Inpainter: Original width - " + bitmap.getWidth() + " height - " + bitmap.getHeight());
        Size decideAndSetResizeParams = decideAndSetResizeParams(bitmap.getWidth(), bitmap.getHeight());
        inpainterParam.setResizedImageSize(decideAndSetResizeParams);
        int width = decideAndSetResizeParams.getWidth();
        int height = decideAndSetResizeParams.getHeight();
        if (!(width == bitmap.getWidth() && height == bitmap.getHeight())) {
            inpainterParam.setResizeRatio(updateScaleFactor(bitmap.getWidth(), bitmap.getHeight(), height, width));
            long currentTimeMillis2 = System.currentTimeMillis();
            bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
            LTTLogger.p(str, "preProcessOnInputImage_Inpainter: upScaling time: " + (System.currentTimeMillis() - currentTimeMillis2) + "ms");
        }
        inpainterParam.setInputImage(bitmap);
        LTTLogger.d(str, "preProcessOnInputImage_Inpainter: Input width - " + bitmap.getWidth() + " height - " + bitmap.getHeight());
        StringBuilder sb2 = new StringBuilder("preProcessOnInputImage_Inpainter: Total time taken : ");
        sb2.append(System.currentTimeMillis() - currentTimeMillis);
        sb2.append("ms");
        LTTLogger.p(str, sb2.toString());
        LTTLogger.v(str, "preProcessOnInputImage_Inpainter: X");
    }

    public void printRequestStatus(ExecutorService executorService, String str) {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
        String str2 = TAG;
        StringBuilder k = j.k("service: ", str, ", total request received: ");
        k.append(threadPoolExecutor.getTaskCount());
        k.append(", active count: ");
        k.append(threadPoolExecutor.getActiveCount());
        k.append(", queue size: ");
        k.append(threadPoolExecutor.getQueue().size());
        LTTLogger.d(str2, k.toString());
    }

    public void release() {
        String str = TAG;
        LTTLogger.i(str, "Release - E");
        long currentTimeMillis = System.currentTimeMillis();
        if (this.session != null) {
            this.session = null;
            LTTLogger.d(str, "release: session released");
        }
        if (this.mainHandler != null) {
            this.mainHandler = null;
            LTTLogger.d(str, "release: mainHandler released");
        }
        if (this.lttEngineListener != null) {
            this.lttEngineListener = null;
            LTTLogger.d(str, "release: lttEngineListener callback released");
        }
        InpainterHelper inpainterHelper2 = this.inpainterHelper;
        if (inpainterHelper2 != null) {
            inpainterHelper2.releaseInpainter();
            this.inpainterHelper = null;
            LTTLogger.d(str, "release: inpainterHelper helper released");
        }
        LttNativeHelper lttNativeHelper2 = this.lttNativeHelper;
        if (lttNativeHelper2 != null) {
            lttNativeHelper2.release();
            this.lttNativeHelper = null;
            LTTLogger.d(str, "release: native helper released");
        }
        ExecutorService executorService = this.rendererService;
        if (executorService != null) {
            executorService.shutdownNow();
            this.rendererService = null;
            LTTLogger.d(str, "release: renderer service released");
        }
        ExecutorService executorService2 = this.inpainterService;
        if (executorService2 != null) {
            executorService2.shutdownNow();
            this.inpainterService = null;
            LTTLogger.d(str, "release: inpainter service released");
        }
        ExecutorService executorService3 = this.maskGenerationService;
        if (executorService3 != null) {
            executorService3.shutdownNow();
            this.maskGenerationService = null;
            LTTLogger.d(str, "release: maskGenerationService service released");
        }
        this.isRenderEngineInitialized = false;
        this.isInpainterHelperInitialized = false;
        this.isMaskHelperInitialized = false;
        this.isLttEngineInitialized = false;
        LTTLogger.p(str, "Engine release in : " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
        LTTLogger.i(str, "Release - X");
    }

    public void setDstLang(String str, KeyFrameParam keyFrameParam) {
        if (str != null) {
            if (str.toLowerCase().contains("zh")) {
                keyFrameParam.setDestLanguage("zh");
            } else {
                keyFrameParam.setDestLanguage(str);
            }
            String str2 = TAG;
            LTTLogger.d(str2, "setDstLang: DestLanguage - " + keyFrameParam.getDestLanguage());
            return;
        }
        LTTLogger.d(TAG, "setDstLang: DestLanguage not provided");
    }

    public void setImageFormat(String str, KeyFrameParam keyFrameParam) {
        if (str != null) {
            keyFrameParam.setImageFormat(str);
            String str2 = TAG;
            LTTLogger.d(str2, "setImageFormat: ImageFormat - " + keyFrameParam.getImageFormat());
            return;
        }
        keyFrameParam.setImageFormat("");
        LTTLogger.d(TAG, "setImageFormat: ImageFormat not provided");
    }

    public void setSession(Session session2) {
        if (session2 != null) {
            this.lttEngineListener = session2.getLttEngineListener();
            if (session2.getLttEngineListener() == null) {
                throw new LttEngineException(-2, "callback is null");
            } else if (session2.getContext() != null) {
                this.session = session2;
            } else {
                throw new LttEngineException(-3, "context is null");
            }
        } else {
            throw new LttEngineException(-1, "session is null");
        }
    }

    public void validateInpainterImage(Bitmap bitmap) {
        if (bitmap == null) {
            throw new LttEngineException(LttEngineErrors.ERROR_INPAINTING_INPUT_IMAGE_NULL, "Invalid input image");
        }
    }

    public void validateInpainterOCR(LttOcrResult lttOcrResult) {
        if (lttOcrResult == null) {
            throw new LttEngineException(LttEngineErrors.ERROR_INPAINTING_OCR_NULL, "lttOcrResult invalid");
        } else if (lttOcrResult.getBlockInfoList().isEmpty()) {
            throw new LttEngineException(LttEngineErrors.ERROR_INPAINTING_OCR_BLOCK_EMPTY, "No.of blocks(" + lttOcrResult.getBlockInfoList().size() + "). So there is nothing to inpaint");
        } else if (Dump.IS_OCR_JSON_DUMP_ENABLED) {
            String convertToJson = Util.convertToJson(lttOcrResult);
            Dump.dumpFile(convertToJson, "inpainter_ocr_" + System.currentTimeMillis() + ".json", "InpainterOCR");
        }
    }

    public void validateInpaintingNeeded(InpainterParam inpainterParam) {
        if (Math.ceil(((double) Math.max(inpainterParam.getInputImage().getHeight(), inpainterParam.getInputImage().getWidth())) / ((double) Math.min(inpainterParam.getInputImage().getHeight(), inpainterParam.getInputImage().getWidth()))) >= 7.0d) {
            throw new LttEngineException(LttEngineErrors.ERROR_INPAINTING_RESOLUTION_NOT_SUPPORTED, "Aspect Ratio for image is >= 7.0");
        }
    }

    public void validateInputImage(Bitmap bitmap) {
        if (bitmap == null) {
            throw new LttEngineException(-7, "Invalid input image");
        } else if (bitmap.getHeight() > 16000 || bitmap.getWidth() > 16000) {
            throw new LttEngineException(-8, "Height : " + bitmap.getHeight() + " OR Width : " + bitmap.getWidth() + " is greater than supported resolution");
        } else if (Dump.IS_INIT_DUMP_SUCCESS && Dump.IS_ORIGINAL_IMAGE_DUMP) {
            Dump.dumpBitmap(bitmap, "original_image_" + System.currentTimeMillis(), "OriginalImage");
        }
    }

    public void validateMaskInputImage(Bitmap bitmap) {
        if (bitmap == null) {
            throw new LttEngineException(-7, "Invalid input image");
        } else if (bitmap.getHeight() > 16000 || bitmap.getWidth() > 16000) {
            throw new LttEngineException(-8, "Height : " + bitmap.getHeight() + " OR Width : " + bitmap.getWidth() + " is greater than supported resolution");
        } else {
            if (Math.ceil(((double) Math.max(bitmap.getHeight(), bitmap.getWidth())) / ((double) Math.min(bitmap.getHeight(), bitmap.getWidth()))) >= 7.0d) {
                throw new LttEngineException(-8, "Aspect Ratio for image is >= 7.0");
            } else if (Dump.IS_INIT_DUMP_SUCCESS && Dump.IS_ORIGINAL_IMAGE_DUMP) {
                Dump.dumpBitmap(bitmap, "mask_request_input_image_" + System.currentTimeMillis(), "MaskRequest");
            }
        }
    }

    public void validateOCRAndTranslation(LttOcrResult lttOcrResult, List<String> list) {
        if (lttOcrResult == null) {
            throw new LttEngineException(-9, "lttOcrResult invalid");
        } else if (lttOcrResult.getBlockInfoList().isEmpty()) {
            throw new LttEngineException(-10, "No.of blocks(" + lttOcrResult.getBlockInfoList().size() + "). So there is nothing to render");
        } else if (list == null) {
            throw new LttEngineException(-11, "Translation Result Invalid");
        } else if (!list.isEmpty()) {
            for (String isEmpty : list) {
                if (!isEmpty.isEmpty()) {
                    if (lttOcrResult.getBlockInfoList().size() == list.size()) {
                        String str = TAG;
                        LTTLogger.i(str, "validateBlockAndTranslation: Block size - " + lttOcrResult.getBlockInfoList().size() + ", Translation size - " + list.size());
                        if (Dump.IS_INIT_DUMP_SUCCESS && Dump.IS_OCR_JSON_DUMP_ENABLED) {
                            String convertToJson = Util.convertToJson(lttOcrResult);
                            Dump.dumpFile(convertToJson, "Json_result_dump" + System.currentTimeMillis() + ".json", "Json_Result");
                        }
                        if (Dump.IS_INIT_DUMP_SUCCESS && Dump.IS_TRANSLATED_DUMP_ENABLED) {
                            Dump.dumpStringListToJsonFile(list, "Trl_result_dump" + System.currentTimeMillis() + ".json", "Trl_Result");
                            return;
                        }
                        return;
                    }
                    throw new LttEngineException(-14, "No. of Blocks(" + lttOcrResult.getBlockInfoList().size() + ") and No. of translation results(" + list.size() + ") doesn't match");
                }
            }
            throw new LttEngineException(-13, "Translation is empty for all the lines");
        } else {
            throw new LttEngineException(-12, "No.of translation(" + list.size() + "). So there is nothing to render");
        }
    }

    public void preProcessOnInputImage(Bitmap bitmap, KeyFrameParam keyFrameParam) {
        String str = TAG;
        LTTLogger.v(str, "preProcessOnInputImage: E");
        keyFrameParam.setOriginalImageSize(new Size(bitmap.getWidth(), bitmap.getHeight()));
        keyFrameParam.setInputImage(bitmap);
        LTTLogger.v(str, "preProcessOnInputImage: Original width - " + bitmap.getWidth() + " height - " + bitmap.getHeight());
        Size decideAndSetResizeParams = decideAndSetResizeParams(bitmap.getWidth(), bitmap.getHeight());
        keyFrameParam.setResizedImageSize(decideAndSetResizeParams);
        int width = decideAndSetResizeParams.getWidth();
        int height = decideAndSetResizeParams.getHeight();
        if (!(width == bitmap.getWidth() && height == bitmap.getHeight())) {
            keyFrameParam.setResizeRatio(updateScaleFactor(bitmap.getWidth(), bitmap.getHeight(), height, width));
            long currentTimeMillis = System.currentTimeMillis();
            bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
            LTTLogger.p(str, "preProcessOnInputImage: upScaling time: " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
        }
        keyFrameParam.setInputImage(bitmap);
        LTTLogger.d(str, "preProcessOnInputImage: Input width - " + bitmap.getWidth() + " height - " + bitmap.getHeight());
        if (Dump.IS_INIT_DUMP_SUCCESS && Dump.IS_ORIGINAL_UPSCALED_IMAGE_DUMP) {
            Dump.dumpBitmap(bitmap, "input_image_" + System.currentTimeMillis(), "InputImage");
        }
        LTTLogger.v(str, "preProcessOnInputImage: X");
    }
}
