package com.samsung.android.livetranslation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.Size;
import com.samsung.android.imagetranslation.LttEngineListener;
import com.samsung.android.imagetranslation.data.LttOcrResult;
import com.samsung.android.livetranslation.common.Config;
import com.samsung.android.livetranslation.common.Dump;
import com.samsung.android.livetranslation.data.ImageBuffer;
import com.samsung.android.livetranslation.data.ProcessParam;
import com.samsung.android.livetranslation.task.LiveTranslationTask;
import com.samsung.android.livetranslation.task.LiveTranslationTaskManager;
import com.samsung.android.livetranslation.task.LiveTranslationTaskManagerController;
import com.samsung.android.livetranslation.text.KeyFrame;
import com.samsung.android.livetranslation.text.LiveTranslation;
import com.samsung.android.livetranslation.util.LTTLogger;
import com.samsung.android.livetranslation.util.LineBreakUtil;
import com.samsung.android.livetranslation.util.Util;
import com.samsung.android.sum.core.types.NumericEnum;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LttEngine {
    public static final String JAR_VERSION = "4.6.0";
    private static final int MAX_RESOLUTION_SUPPORTED = 16000;
    private static final int MAX_UPSCALE_LENGTH = 1080;
    protected static final int ORIENTATION_ANGLE_DOWN = 270;
    protected static final int ORIENTATION_ANGLE_LEFT = 0;
    protected static final int ORIENTATION_ANGLE_RIGHT = 180;
    protected static final int ORIENTATION_ANGLE_UP = 90;
    private static final int RESIZE_THRESHOLD = 512;
    /* access modifiers changed from: private */
    public static final String TAG = "LttEngine";
    private static LttEngine lttEngine = null;
    protected static volatile int mCurrSensorOrientationAngle = 90;
    private int imageHeight;
    private int imageWidth;
    /* access modifiers changed from: private */
    public LttEngineListener lttEngineListener;
    /* access modifiers changed from: private */
    public LttOcrResult lttOcrResult;
    private Context mAppContext;
    protected int mCurrScreenOrientationAngle = 90;
    private String mDstLang;
    private final LiveTranslation.OnEngineListener mEngineListener = new LiveTranslation.OnEngineListener() {
        public void checkEngineStability(boolean z) {
            String e = LttEngine.TAG;
            LTTLogger.d(e, "checkEngineStability - stable: " + z);
        }

        public void finishProcess() {
            LTTLogger.i(LttEngine.TAG, "Finish LTT process");
        }

        public void onCaptureSuccess(LiveTranslation.RenderingScreen renderingScreen) {
            LTTLogger.i(LttEngine.TAG, "onCaptureSuccess()");
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(renderingScreen.getBytes(), 0, renderingScreen.getBytes().length);
            String e = LttEngine.TAG;
            LTTLogger.d(e, "onCaptureSuccess: " + decodeByteArray.getWidth() + " " + decodeByteArray.getHeight());
            if (Dump.IS_INIT_DUMP_SUCCESS && Dump.IS_RENDERED_FRAME_ENABLED) {
                Dump.dumpBitmap(decodeByteArray, "result_dump" + Util.getTimeInMilliSecond() + ".png", "Result");
            }
            LttEngine.this.lttEngineListener.onRenderSuccess(decodeByteArray);
            LttEngine.this.release();
        }

        public void onError(String str) {
            String e = LttEngine.TAG;
            LTTLogger.d(e, "onError: STATUS - " + str);
            LttEngine.this.lttEngineListener.onRenderFailure(str);
        }

        public void startProcess() {
            LTTLogger.i(LttEngine.TAG, "Start LTT process");
        }
    };
    private final AtomicBoolean mIsEngineInitialized = new AtomicBoolean(false);
    protected LiveTranslation mLiveTranslation;
    /* access modifiers changed from: private */
    public LiveTranslationTaskManagerController mLiveTranslationTaskManagerController = null;
    protected int mOriginScreenOrientation = -1;
    private String mSrcLang;
    private final LiveTranslation.OnTaskControllerListener mTaskControllerListener = new LiveTranslation.OnTaskControllerListener() {
        public void cancelAllRequests() {
            LttEngine.this.mLiveTranslationTaskManagerController.release();
            LttEngine.this.mLiveTranslationTaskManagerController.init();
        }

        public int getManagerSize() {
            if (LttEngine.this.mLiveTranslationTaskManagerController != null) {
                return LttEngine.this.mLiveTranslationTaskManagerController.getManagerSize();
            }
            return 0;
        }

        public void requestNewTask(Context context, KeyFrame keyFrame, Rect rect, LiveTranslationTask.TASKTYPE tasktype, LiveTranslationTaskManager.LiveTranslationTaskManagerListener liveTranslationTaskManagerListener) {
            String e = LttEngine.TAG;
            LTTLogger.i(e, "requestNewTask: " + tasktype);
            if (LttEngine.this.mLiveTranslationTaskManagerController != null) {
                LttEngine.this.mLiveTranslationTaskManagerController.requestNewTask(context, keyFrame, rect, tasktype, liveTranslationTaskManagerListener, LttEngine.this.lttOcrResult);
            }
        }

        public void requestSuccessiveTRL(String str, String str2) {
            LTTLogger.i(LttEngine.TAG, "requestTRLTask");
            if (LttEngine.this.mLiveTranslationTaskManagerController != null) {
                LttEngine.this.mLiveTranslationTaskManagerController.requestSuccessiveTRL(str, str2, LttEngine.this.trlResult);
            }
        }

        public void requestTask(int i2, String str, String str2, LiveTranslationTask.TASKTYPE tasktype) {
            if (LttEngine.this.mLiveTranslationTaskManagerController != null) {
                LttEngine.this.mLiveTranslationTaskManagerController.requestTask(i2, str, str2, tasktype);
            }
        }
    };
    private ImageBuffer nv21InputImage;
    private int originalImageHeight;
    private int originalImageWidth;
    private int resizedImageHeight;
    private int resizedImageWidth;
    private float scaleFactor = 1.0f;
    /* access modifiers changed from: private */
    public List<String> trlResult;

    private LttEngine(Context context) {
        this.mAppContext = context;
        LTTLogger.i(TAG, "LttEngine: JAR_VERSION : 4.6.0");
    }

    private void createCoreEngine() {
        String str = TAG;
        LTTLogger.i(str, "createCoreEngine: E");
        LTTLogger.d(str, "createCoreEngine: ENGINE_STATE - " + isEngineInitialized());
        if (isEngineInitialized()) {
            this.mLiveTranslation.refreshSession();
            return;
        }
        if (this.mLiveTranslation != null) {
            LTTLogger.e(str, "createCoreEngine: bad thing happens, FC prevention here");
            try {
                this.mLiveTranslation.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        LiveTranslation liveTranslation = new LiveTranslation(this.mAppContext, this.imageWidth, this.imageHeight, new Rect(0, 0, this.imageWidth, this.imageHeight), this.mSrcLang, this.mDstLang, this.mCurrScreenOrientationAngle, false, mCurrSensorOrientationAngle, this.originalImageWidth, this.originalImageHeight, this.scaleFactor);
        this.mLiveTranslation = liveTranslation;
        liveTranslation.setOnEngineListener(this.mEngineListener);
        this.mLiveTranslation.setOnTaskControllerListener(this.mTaskControllerListener);
        this.mLiveTranslationTaskManagerController = new LiveTranslationTaskManagerController(false);
        this.mIsEngineInitialized.set(true);
        String str2 = TAG;
        LTTLogger.i(str2, "createCoreEngine: Engine created");
        LTTLogger.i(str2, "createCoreEngine: X");
        boolean startLTT = startLTT(this.nv21InputImage);
        LTTLogger.d(str2, "createCoreEngine: processImageState - " + startLTT);
    }

    public static LttEngine createInstance(Context context) {
        LttEngine lttEngine2 = new LttEngine(context);
        lttEngine = lttEngine2;
        return lttEngine2;
    }

    private Size decideAndSetResizeParams(int i2, int i7) {
        int i8;
        if (i2 <= 512 && i7 <= 512) {
            if (i2 == i7) {
                this.resizedImageWidth = 1080;
                this.resizedImageHeight = 1080;
            } else if (i2 > i7) {
                this.resizedImageWidth = 1080;
                this.resizedImageHeight = (int) Math.ceil((((double) i7) / ((double) i2)) * 1080.0d);
            } else {
                this.resizedImageHeight = 1080;
                this.resizedImageWidth = (int) Math.ceil((((double) i2) / ((double) i7)) * 1080.0d);
            }
            String str = TAG;
            LTTLogger.d(str, "upscaleWidth=" + this.resizedImageWidth + ", upscaleHeight=" + this.resizedImageHeight);
            int i10 = this.resizedImageWidth;
            if (!(i10 == 0 || (i8 = this.resizedImageHeight) == 0)) {
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
            String str2 = TAG;
            LTTLogger.d(str2, "After 4bit aligned : width=" + this.resizedImageWidth + ", height=" + this.resizedImageHeight);
        }
        return new Size(i2, i7);
    }

    private void setLttEngineListener(LttEngineListener lttEngineListener2) {
        this.lttEngineListener = lttEngineListener2;
    }

    private void updateScaleFactor(int i2, int i7, int i8, int i10) {
        if (i7 >= i2) {
            this.scaleFactor = ((float) i7) / ((float) i10);
        } else {
            this.scaleFactor = ((float) i2) / ((float) i8);
        }
        String str = TAG;
        LTTLogger.v(str, "setImage: scale factor - " + this.scaleFactor);
    }

    public List<String> getResultsWithLineBreak(LttOcrResult lttOcrResult2, LttOcrResult lttOcrResult3) {
        return new LineBreakUtil(lttOcrResult3).initResultWithSourceText(lttOcrResult2.getBlockInfoList());
    }

    public boolean isEngineInitialized() {
        return this.mIsEngineInitialized.get();
    }

    public void processImage(Bitmap bitmap, LttOcrResult lttOcrResult2, List<String> list, ProcessParam processParam, LttEngineListener lttEngineListener2) {
        try {
            String str = TAG;
            LTTLogger.i(str, "processImage: E");
            setImage(bitmap);
            setLttOcrResult(lttOcrResult2);
            setTRLResult(list);
            LTTLogger.i(str, "processImage: " + lttOcrResult2.getBlockInfoList().size() + " " + list.size());
            if (lttOcrResult2.getBlockInfoList().size() != list.size()) {
                lttEngineListener2.onRenderFailure("No. of Blocks(" + lttOcrResult2.getBlockInfoList().size() + ") and No. of translation results(" + list.size() + ") doesn't match");
            } else if (lttOcrResult2.getBlockInfoList().size() == 0) {
                lttEngineListener2.onRenderFailure("provided no.of blocks(" + lttOcrResult2.getBlockInfoList().size() + "). So there is nothing to render");
            } else {
                LTTLogger.d(str, "processImage: " + processParam);
                if (!(processParam == null || processParam.getDestLanguage() == null)) {
                    if (processParam.getDestLanguage().toLowerCase().contains("zh")) {
                        setDstLang("zh");
                    } else {
                        setDstLang(processParam.getDestLanguage());
                    }
                    LTTLogger.d(str, "processImage: DestLanguage - " + processParam.getDestLanguage());
                }
                setLttEngineListener(lttEngineListener2);
                LTTLogger.i(str, "processImage:All Inputs received");
                if (Config.IS_USER_DEBUG) {
                    Dump.initDump(this.mAppContext, bitmap);
                }
                if (Dump.IS_INIT_DUMP_SUCCESS && Dump.IS_OCR_JSON_DUMP_ENABLED) {
                    String convertToJson = Util.convertToJson(lttOcrResult2);
                    Dump.dumpStringToFile(convertToJson, "Json_result_dump" + Util.getTimeInMilliSecond() + ".json", "Json_Result");
                }
                ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < list.size(); i2++) {
                    arrayList.add("Block " + i2 + NumericEnum.SEP + list.get(i2));
                }
                if (Dump.IS_INIT_DUMP_SUCCESS && Dump.IS_TRANSLATED_DUMP_ENABLED) {
                    String obj = arrayList.toString();
                    Dump.dumpStringToFile(obj, "Trl_result_dump" + Util.getTimeInMilliSecond() + ".txt", "Trl_Result");
                }
                createCoreEngine();
            }
        } catch (Exception e) {
            String str2 = TAG;
            LTTLogger.e(str2, "processImage: " + e.getMessage());
            lttEngineListener2.onRenderFailure(e.getMessage());
        }
    }

    public void release() {
        String str = TAG;
        LTTLogger.i(str, "releaseCoreEngine");
        if (isEngineInitialized()) {
            this.mIsEngineInitialized.set(false);
            LiveTranslation liveTranslation = this.mLiveTranslation;
            if (liveTranslation != null) {
                liveTranslation.release();
                this.mLiveTranslation = null;
            } else {
                LTTLogger.e(str, "stateMisMatched");
            }
        } else {
            LTTLogger.d(str, "Release engine skipped because engine is not initialized");
        }
        if (this.mAppContext != null) {
            this.mAppContext = null;
            LTTLogger.d(str, "release: Application Context released");
        }
        if (this.nv21InputImage != null) {
            this.nv21InputImage = null;
        }
        if (this.lttOcrResult != null) {
            this.lttOcrResult = null;
        }
        if (this.mLiveTranslationTaskManagerController != null) {
            this.mLiveTranslationTaskManagerController = null;
        }
        if (this.lttEngineListener != null) {
            this.lttEngineListener = null;
            LTTLogger.d(str, "release: trlRenderListener released");
        }
    }

    public void setDstLang(String str) {
        String str2 = TAG;
        LTTLogger.d(str2, "Destination Language: " + str);
        this.mDstLang = str;
    }

    public void setImage(Bitmap bitmap) {
        String str = TAG;
        LTTLogger.v(str, "setImage: E");
        if (bitmap == null) {
            throw new Exception("Invalid Image");
        } else if (bitmap.getHeight() > 16000 || bitmap.getWidth() > 16000) {
            throw new Exception("Height : " + bitmap.getHeight() + " OR Width : " + bitmap.getWidth() + " is greater than supported resolution");
        } else {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            setOriginalImageSize(width, height);
            LTTLogger.v(str, "setImage: Original height - " + height + " width - " + width);
            Size decideAndSetResizeParams = decideAndSetResizeParams(width, height);
            int width2 = decideAndSetResizeParams.getWidth();
            int height2 = decideAndSetResizeParams.getHeight();
            int i2 = this.originalImageWidth;
            if (!(width2 == i2 && height2 == this.originalImageHeight)) {
                updateScaleFactor(this.originalImageHeight, i2, height2, width2);
                long currentTimeMillis = System.currentTimeMillis();
                bitmap = Bitmap.createScaledBitmap(bitmap, width2, height2, true);
                long currentTimeMillis2 = System.currentTimeMillis();
                LTTLogger.d(str, "upScaling time: " + (currentTimeMillis2 - currentTimeMillis) + "ms");
            }
            if (Config.IS_USER_DEBUG) {
                Dump.setScaledImage(bitmap);
            }
            setImageSize(bitmap.getWidth(), bitmap.getHeight());
            byte[] convertBitmapToNV21 = Util.convertBitmapToNV21(bitmap);
            LTTLogger.v(str, "setImage: After convertBitmapToNV21  height - " + height2 + " width - " + width2);
            this.nv21InputImage = new ImageBuffer(convertBitmapToNV21, bitmap.hashCode());
            LTTLogger.v(str, "setImage: X");
        }
    }

    public void setImageSize(int i2, int i7) {
        this.imageWidth = i2;
        this.imageHeight = i7;
    }

    public void setLttOcrResult(LttOcrResult lttOcrResult2) {
        LTTLogger.v(TAG, "setLttOcrResult()");
        if (lttOcrResult2 != null) {
            this.lttOcrResult = lttOcrResult2;
            return;
        }
        throw new Exception("LttOcrResult Invalid");
    }

    public void setOriginalImageSize(int i2, int i7) {
        this.originalImageWidth = i2;
        this.originalImageHeight = i7;
    }

    public void setSrcLang(String str) {
        this.mSrcLang = str;
    }

    public void setTRLResult(List<String> list) {
        LTTLogger.v(TAG, "setTRLResult()");
        if (list != null) {
            Iterator<String> it = list.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (!it.next().isEmpty()) {
                        break;
                    }
                } else {
                    LTTLogger.d(TAG, "Translation Results are Empty");
                    break;
                }
            }
            for (String str : list) {
                String str2 = TAG;
                LTTLogger.d(str2, "setTRLResult: " + str);
            }
            this.trlResult = list;
            return;
        }
        throw new Exception("Translation Result Invalid");
    }

    public boolean startLTT(ImageBuffer imageBuffer) {
        String str = TAG;
        LTTLogger.d(str, "startLTT: Engine State - " + isEngineInitialized());
        if (!isEngineInitialized()) {
            LTTLogger.i(str, "startLTT : Engine not initialized, processImage failed");
            return false;
        }
        LTTLogger.i(str, "startLTT : Start LTT process");
        this.mLiveTranslation.processImage(imageBuffer, this.mAppContext, this.scaleFactor, this.originalImageWidth, this.originalImageHeight);
        return true;
    }
}
