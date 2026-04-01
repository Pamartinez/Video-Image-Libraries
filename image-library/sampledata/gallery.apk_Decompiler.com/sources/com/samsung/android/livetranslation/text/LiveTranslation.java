package com.samsung.android.livetranslation.text;

import C3.i;
import N2.j;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import c0.C0086a;
import com.samsung.android.imagetranslation.data.LttOcrResult;
import com.samsung.android.livetranslation.LttEngine;
import com.samsung.android.livetranslation.common.Config;
import com.samsung.android.livetranslation.common.LttEngineProperty;
import com.samsung.android.livetranslation.data.ImageBuffer;
import com.samsung.android.livetranslation.task.LiveTranslationTask;
import com.samsung.android.livetranslation.task.LiveTranslationTaskManager;
import com.samsung.android.livetranslation.text.KeyFrame;
import com.samsung.android.livetranslation.util.LTTLogger;
import com.samsung.android.livetranslation.util.Util;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LiveTranslation {
    public static int NATIVE_API_VERSION = -1;
    private static String NATIVE_LIBRARY_VERSION = null;
    private static final int NEW_VERSION_SUCCESS = 100;
    /* access modifiers changed from: private */
    public static final String TAG = "LiveTranslation";
    public static boolean isJarAndNativeCompatible;
    private static Handler lttRendererHandler;
    /* access modifiers changed from: private */
    public static HandlerThread lttRendererHandlerThread;
    /* access modifiers changed from: private */
    public static long mLatestFrameID;
    /* access modifiers changed from: private */
    public static OnEngineListener mOnEngineListener;
    /* access modifiers changed from: private */
    public static Handler uiHandler;
    /* access modifiers changed from: private */
    public static Handler updateTextHandler;
    private static HandlerThread updateTextHandlerThread;
    /* access modifiers changed from: private */
    public AtomicBoolean mInProgress;
    private boolean mIsImageModeSelected = false;
    /* access modifiers changed from: private */
    public OnTaskControllerListener mOnTaskControllerListener;
    private AtomicBoolean mPrevFreezeStatus;
    private int mPreviewHeight;
    private int mPreviewWidth;
    private Rect mROIinLandscapeAxis;
    private int mSTRCP;
    private int mScreenRotationAngle;
    /* access modifiers changed from: private */
    public String mSrcLang;
    private AtomicBoolean mStability;
    /* access modifiers changed from: private */
    public LiveTranslationTaskManager.STATUS mStatus = LiveTranslationTaskManager.STATUS.STR_PREPARING;
    private int mTRLCP;
    private KeyFrame.TRL_UNIT mTRLUnit = KeyFrame.TRL_UNIT.PARAGRAPH;
    /* access modifiers changed from: private */
    public String mTarLang;
    private AtomicBoolean mTrackingSuccessInCurFrame;
    private AtomicBoolean mTrackingSuccessInPrevFrame;
    /* access modifiers changed from: private */
    public long startLTT_Time;
    private int versionAvailable;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class LiveTranslationTaskManagerListenerImpl implements LiveTranslationTaskManager.LiveTranslationTaskManagerListener {
        public /* synthetic */ LiveTranslationTaskManagerListenerImpl(LiveTranslation liveTranslation, int i2) {
            this();
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onSTRFinish$0(KeyFrame keyFrame, int i2) {
            LiveTranslation.this.mStatus = LiveTranslationTaskManager.STATUS.STR_COMPLETED;
            String n = LiveTranslation.TAG;
            LTTLogger.i(n, "onSTRFinish: Status - " + LiveTranslation.this.mStatus);
            LiveTranslation.this.updateTexts(keyFrame, true, true, i2);
            String n3 = LiveTranslation.TAG;
            LTTLogger.d(n3, "onSTRFinish: Profiling - UpdateTexts(OCR) : " + (((double) System.currentTimeMillis()) - ((double) System.currentTimeMillis())) + "ms");
            LiveTranslation.this.mOnTaskControllerListener.requestSuccessiveTRL(LiveTranslation.this.mSrcLang, LiveTranslation.this.mTarLang);
            LiveTranslation.this.mStatus = LiveTranslationTaskManager.STATUS.TRL_WAITING;
            String n6 = LiveTranslation.TAG;
            LTTLogger.i(n6, "onSTRFinish: Status - " + LiveTranslation.this.mStatus);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onSTRFinish$1() {
            LiveTranslation.mOnEngineListener.onError(LiveTranslation.this.mStatus.toString());
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onSTRFinish$2(KeyFrame keyFrame, int i2) {
            LiveTranslation.this.mStatus = LiveTranslationTaskManager.STATUS.STR_FAIL;
            String n = LiveTranslation.TAG;
            LTTLogger.i(n, "onSTRFinish: Status - " + LiveTranslation.this.mStatus);
            LiveTranslation.this.updateTexts(keyFrame, true, false, i2);
            if (LiveTranslation.mOnEngineListener != null && LiveTranslation.this.mInProgress.get()) {
                LiveTranslation.this.mInProgress.set(false);
                LiveTranslation.uiHandler.post(new c(0, this));
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onTRLFinish$3(int i2, KeyFrame keyFrame) {
            LiveTranslation.this.mStatus = LiveTranslationTaskManager.STATUS.TRL_COMPLETED;
            String n = LiveTranslation.TAG;
            LTTLogger.i(n, "onTRLJobFinish(): Status - " + LiveTranslation.this.mStatus);
            if (i2 == 3004) {
                LiveTranslation._clearRenderingText();
            }
            LiveTranslation.this.updateTexts(keyFrame, false, true, i2);
            if (Config.IS_PROFILING_ENABLED) {
                String n3 = LiveTranslation.TAG;
                LTTLogger.i(n3, "Profiling - LTT elapsed time: " + (System.currentTimeMillis() - LiveTranslation.this.startLTT_Time) + "ms");
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onTRLFinish$4(KeyFrame keyFrame, int i2) {
            LiveTranslation.this.mStatus = LiveTranslationTaskManager.STATUS.TRL_FAIL;
            String n = LiveTranslation.TAG;
            LTTLogger.i(n, "onTRLJobFinish(): Status - " + LiveTranslation.this.mStatus);
            LiveTranslation.this.updateTexts(keyFrame, false, false, i2);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onTRLFinish$5(int i2) {
            String str;
            OnEngineListener q = LiveTranslation.mOnEngineListener;
            StringBuilder sb2 = new StringBuilder();
            sb2.append(LiveTranslation.this.mStatus.toString());
            sb2.append(" ");
            if (i2 == 3004) {
                str = "Same Language detected";
            } else {
                str = "Invalid Parsing Data";
            }
            sb2.append(str);
            q.onError(sb2.toString());
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onTRLFinish$6(int i2) {
            if (LiveTranslation.mOnEngineListener != null && LiveTranslation.this.mInProgress.get()) {
                LiveTranslation.this.mInProgress.set(false);
                if (i2 != 3005) {
                    LiveTranslation.uiHandler.post(new b(this, i2, 1));
                } else {
                    LiveTranslation.mOnEngineListener.finishProcess();
                }
            }
        }

        private boolean needFurtherProcessForSTR(long j2) {
            if (LiveTranslation.this.isLiveTranslationValid() && LiveTranslation.this.isLastestKeyFrameResponse(j2)) {
                return true;
            }
            String n = LiveTranslation.TAG;
            LTTLogger.i(n, "needFurtherProcessForSTR() : false, LATEST_FRAME_ID(" + LiveTranslation.mLatestFrameID + ") vs KeyFrame.mFrameId(" + j2 + ")");
            return false;
        }

        public void onSTRFinish(boolean z, KeyFrame keyFrame, LttOcrResult lttOcrResult, int i2) {
            LiveTranslation.this.mStatus = LiveTranslationTaskManager.STATUS.STR_ARRIVED;
            String n = LiveTranslation.TAG;
            LTTLogger.i(n, "onSTRFinish: Status - " + LiveTranslation.this.mStatus);
            if (needFurtherProcessForSTR(keyFrame.getFrameId())) {
                String n3 = LiveTranslation.TAG;
                LTTLogger.d(n3, "onSTRFinish() success:" + z);
                if (z) {
                    LiveTranslation.updateTextHandler.post(new a(this, keyFrame, i2, 2));
                } else {
                    LiveTranslation.updateTextHandler.post(new a(this, keyFrame, i2, 3));
                }
            }
        }

        public void onTRLFinish(boolean z, KeyFrame keyFrame, int i2) {
            LiveTranslation.this.mStatus = LiveTranslationTaskManager.STATUS.TRL_ARRIVED;
            String n = LiveTranslation.TAG;
            LTTLogger.i(n, "onTRLFinish : E (" + z + ") errortype : (" + i2 + ")");
            String n3 = LiveTranslation.TAG;
            StringBuilder sb2 = new StringBuilder("onTRLFinish: Status - ");
            sb2.append(LiveTranslation.this.mStatus);
            LTTLogger.i(n3, sb2.toString());
            if (LiveTranslation.this.needFurtherProcessForTRL(keyFrame.getFrameId(), keyFrame.getSrcLang(), keyFrame.getTarLang())) {
                if (z) {
                    LiveTranslation.updateTextHandler.post(new a(i2, keyFrame, this));
                } else {
                    LiveTranslation.updateTextHandler.post(new a(this, keyFrame, i2, 1));
                }
                LiveTranslation.updateTextHandler.post(new b(this, i2, 0));
            }
        }

        private LiveTranslationTaskManagerListenerImpl() {
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnEngineListener {
        void checkEngineStability(boolean z);

        void finishProcess();

        void onCaptureSuccess(RenderingScreen renderingScreen);

        void onError(String str);

        void startProcess();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnTaskControllerListener {
        void cancelAllRequests();

        int getManagerSize();

        void requestNewTask(Context context, KeyFrame keyFrame, Rect rect, LiveTranslationTask.TASKTYPE tasktype, LiveTranslationTaskManager.LiveTranslationTaskManagerListener liveTranslationTaskManagerListener);

        void requestSuccessiveTRL(String str, String str2);

        void requestTask(int i2, String str, String str2, LiveTranslationTask.TASKTYPE tasktype);
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class RenderingScreen {
        private final byte[] bytes;
        private final boolean isTranslationRendered;

        public RenderingScreen(byte[] bArr, boolean z) {
            this.bytes = bArr;
            this.isTranslationRendered = z;
        }

        public byte[] getBytes() {
            return this.bytes;
        }

        public boolean isTranslationRendered() {
            return this.isTranslationRendered;
        }
    }

    static {
        try {
            LTTLogger.d(TAG, "Load library: LttEngine.camera.samsung");
            System.loadLibrary("LttEngine.camera.samsung");
        } catch (Throwable th) {
            String str = TAG;
            LTTLogger.i(str, "Failed to load library: " + th.getMessage());
            th.printStackTrace();
        }
    }

    public LiveTranslation(Context context, int i2, int i7, Rect rect, String str, String str2, int i8, boolean z, int i10, int i11, int i12, float f) {
        String str3 = TAG;
        StringBuilder sb2 = new StringBuilder("LiveTranslation() : context=");
        sb2.append(context);
        sb2.append(", previewWidth=");
        sb2.append(i2);
        sb2.append(", previewHeight=");
        sb2.append(i7);
        sb2.append(", roi=");
        sb2.append(rect.flattenToString());
        sb2.append(", srcLang=");
        C0086a.z(sb2, str, ", tarLang=", str2, ", surfaceRotationAngle=");
        sb2.append(i8);
        sb2.append(", isSystemAutoRotatePreferenceEnabled=");
        sb2.append(z);
        sb2.append(", DeviceOrientation=");
        sb2.append(i10);
        LTTLogger.i(str3, sb2.toString());
        this.mPreviewWidth = i2;
        this.mPreviewHeight = i7;
        this.mSrcLang = str;
        this.mTarLang = str2;
        this.mROIinLandscapeAxis = rect;
        this.mScreenRotationAngle = i8;
        this.mTrackingSuccessInCurFrame = new AtomicBoolean(false);
        this.mTrackingSuccessInPrevFrame = new AtomicBoolean(false);
        this.mStability = new AtomicBoolean(false);
        this.mInProgress = new AtomicBoolean(false);
        this.mPrevFreezeStatus = new AtomicBoolean(false);
        HandlerThread handlerThread = new HandlerThread("LttRendererThread");
        lttRendererHandlerThread = handlerThread;
        handlerThread.start();
        lttRendererHandler = new Handler(lttRendererHandlerThread.getLooper());
        HandlerThread handlerThread2 = new HandlerThread("updateTextThread");
        updateTextHandlerThread = handlerThread2;
        handlerThread2.start();
        updateTextHandler = new Handler(updateTextHandlerThread.getLooper());
        uiHandler = new Handler(Looper.getMainLooper());
        int i13 = this.mPreviewWidth;
        int i14 = this.mPreviewHeight;
        Rect rect2 = this.mROIinLandscapeAxis;
        int i15 = rect2.left;
        int i16 = rect2.top;
        int _initialize = _initialize(i13, i14, i15, i16, rect2.right - i15, rect2.bottom - i16);
        this.versionAvailable = _initialize;
        if (_initialize == 100) {
            NATIVE_LIBRARY_VERSION = _getNativeParameter(1);
            NATIVE_API_VERSION = Integer.parseInt(_getNativeParameter(2));
            LTTLogger.i(str3, "Native Version - " + NATIVE_LIBRARY_VERSION);
            LTTLogger.i(str3, "ApiVersion - " + NATIVE_API_VERSION);
            LTTLogger.i(str3, "Client " + context.getPackageName());
            int i17 = (context.getPackageName().contains("aiselect") || context.getPackageName().contains("smartcapture")) ? 0 : 1;
            int[] deviceResolution = Util.getDeviceResolution(context);
            LttEngineProperty lttEngineProperty = new LttEngineProperty(3, Integer.valueOf(deviceResolution[0]));
            LttEngineProperty lttEngineProperty2 = new LttEngineProperty(4, Integer.valueOf(deviceResolution[1]));
            LttEngineProperty lttEngineProperty3 = new LttEngineProperty(5, Integer.valueOf(i11));
            LttEngineProperty lttEngineProperty4 = new LttEngineProperty(6, Integer.valueOf(i12));
            LttEngineProperty lttEngineProperty5 = new LttEngineProperty(7, Integer.valueOf((int) (100.0f * f)));
            LttEngineProperty lttEngineProperty6 = new LttEngineProperty(8, Integer.valueOf(i17));
            if (_setNativeParameter(lttEngineProperty) != 1) {
                LTTLogger.i(str3, "Failed to set device width");
            }
            if (_setNativeParameter(lttEngineProperty2) != 1) {
                LTTLogger.i(str3, "Failed to set device height");
            }
            if (_setNativeParameter(lttEngineProperty3) != 1) {
                LTTLogger.i(str3, "Failed to set image width");
            }
            if (_setNativeParameter(lttEngineProperty4) != 1) {
                LTTLogger.i(str3, "Failed to set image height");
            }
            if (_setNativeParameter(lttEngineProperty5) != 1) {
                LTTLogger.i(str3, "Failed to set resize scale factor");
            }
            if (_setNativeParameter(lttEngineProperty6) != 1) {
                LTTLogger.i(str3, "Failed to set mser");
            }
        }
        isJarAndNativeCompatible = Util.isJarAndNativeVersionCompatible(LttEngine.JAR_VERSION, NATIVE_LIBRARY_VERSION);
        LTTLogger.i(str3, "Version Matches : " + isJarAndNativeCompatible);
    }

    /* access modifiers changed from: private */
    public static native int _clearRenderingText();

    private static native String _getNativeParameter(int i2);

    private static native RenderingScreen _getRenderingScreenInJPG();

    private static native int _initOpenGL(int i2, int i7);

    private static native int _initialize(int i2, int i7, int i8, int i10, int i11, int i12);

    private static native int _processImage(byte[] bArr);

    private static native int _refreshSession();

    private static native int _release();

    /* access modifiers changed from: private */
    public static native int _renderingText(boolean z);

    public static void _requestRenderCallback() {
        LTTLogger.i(TAG, "requestRenderCallback");
        lttRendererHandler.post(new Runnable() {
            public void run() {
                String n = LiveTranslation.TAG;
                LTTLogger.i(n, "OpenGL Thread Id: " + LiveTranslation.lttRendererHandlerThread.getThreadId());
                LiveTranslation._renderingText(true);
            }
        });
    }

    private static native int _setNativeParameter(LttEngineProperty lttEngineProperty);

    private static native int _updateTexts(KeyFrame keyFrame, boolean z, boolean z3);

    private boolean doesTrackingStartToFail() {
        if (!this.mTrackingSuccessInPrevFrame.get() || this.mTrackingSuccessInCurFrame.get()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public synchronized boolean isLastestKeyFrameResponse(long j2) {
        boolean z;
        String str = TAG;
        LTTLogger.i(str, "isLastestFrameResponse : mLatestFrameID=" + mLatestFrameID + ", inFrameID=" + j2);
        if (mLatestFrameID <= j2) {
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    /* access modifiers changed from: private */
    public boolean isLiveTranslationValid() {
        OnTaskControllerListener onTaskControllerListener = this.mOnTaskControllerListener;
        if (onTaskControllerListener == null || onTaskControllerListener.getManagerSize() < 1) {
            return false;
        }
        return true;
    }

    private boolean isSTRRequestingSignal(int i2) {
        if (i2 == 2 || i2 == 3) {
            return true;
        }
        return false;
    }

    private boolean isSameLang(String str, String str2) {
        if (str == null || !str.equals(str2)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public boolean needFurtherProcessForTRL(long j2, String str, String str2) {
        if (isLiveTranslationValid() && isLastestKeyFrameResponse(j2) && (this.mIsImageModeSelected || this.mTrackingSuccessInCurFrame.get())) {
            return true;
        }
        String str3 = TAG;
        StringBuilder k = j.k("needFurtherProcessForTRL() : false, KeyFrame.mSrcLang(", str, "), CurrentSrcLang(");
        C0086a.z(k, this.mSrcLang, "), KeyFrame.mTarLang(", str2, "), CurrentTarLang(");
        k.append(this.mTarLang);
        k.append("), LATEST_FRAME_ID(");
        k.append(mLatestFrameID);
        k.append(") vs KeyFrame.mFrameId(");
        k.append(j2);
        k.append(") + CURRENT_TRACKING_STATUS(");
        k.append(this.mTrackingSuccessInCurFrame.get());
        k.append(")");
        LTTLogger.i(str3, k.toString());
        return false;
    }

    private KeyFrame prepareKeyFrame(byte[] bArr, Context context, float f, int i2, int i7, int i8) {
        KeyFrame keyFrame = new KeyFrame();
        keyFrame.setSTRCP(this.mSTRCP);
        keyFrame.setTRLCP(this.mTRLCP);
        keyFrame.setTRLUnit(this.mTRLUnit);
        keyFrame.setRotation(i8);
        keyFrame.setFrameId(mLatestFrameID);
        keyFrame.setOriginalImageWidth(i2);
        keyFrame.setOriginalImageHeight(i7);
        if (bArr != null) {
            keyFrame.setBufferWithCopy(bArr, 0, (int) (((double) (this.mPreviewWidth * this.mPreviewHeight)) * 1.5d));
        }
        keyFrame.setDpScaleFactor(Util.getDpToPxFactor(context));
        keyFrame.setFrameFormat(KeyFrame.FrameFormat.NV21);
        keyFrame.setWidth(this.mPreviewWidth);
        keyFrame.setHeight(this.mPreviewHeight);
        keyFrame.setResizeRatio(f);
        synchronized (this) {
            keyFrame.setSrcLang(this.mSrcLang);
            keyFrame.setTarLang(this.mTarLang);
        }
        return keyFrame;
    }

    private void releaseBitmaps(KeyFrame keyFrame) {
        Iterator<SceneText> it = keyFrame.getSceneTexts().iterator();
        while (it.hasNext()) {
            releaseTrsMaskBitmap(it.next());
        }
    }

    private void releaseResources(KeyFrame keyFrame) {
        releaseBitmaps(keyFrame);
    }

    private void releaseTrsMaskBitmap(SceneText sceneText) {
        if (sceneText.getComponents() != null) {
            Iterator<SceneText> it = sceneText.getComponents().iterator();
            while (it.hasNext()) {
                releaseTrsMaskBitmap(it.next());
            }
        }
        if (sceneText.getTrsTextMask() != null) {
            sceneText.setTrsTextMask((Bitmap) null);
        }
    }

    private void updateEngine(boolean z) {
        if (this.mStatus != LiveTranslationTaskManager.STATUS.TRL_COMPLETED && z && !this.mPrevFreezeStatus.get()) {
            LTTLogger.i(TAG, "refresh Session is called for tab freeze before text rendering");
            _refreshSession();
            this.mOnTaskControllerListener.cancelAllRequests();
            if (mOnEngineListener != null && this.mInProgress.get()) {
                this.mInProgress.set(false);
            }
            this.mStatus = LiveTranslationTaskManager.STATUS.STR_PREPARING;
        }
        if (this.mStability.get() && doesTrackingStartToFail()) {
            this.mStability.set(false);
            if (mOnEngineListener != null && this.mInProgress.get()) {
                this.mInProgress.set(false);
            }
        }
        OnEngineListener onEngineListener = mOnEngineListener;
        if (onEngineListener != null) {
            onEngineListener.checkEngineStability(this.mStability.get());
        }
        this.mTrackingSuccessInPrevFrame.set(this.mTrackingSuccessInCurFrame.get());
        this.mPrevFreezeStatus.set(z);
    }

    /* access modifiers changed from: private */
    public void updateTexts(KeyFrame keyFrame, boolean z, boolean z3, int i2) {
        if (isLastestKeyFrameResponse(keyFrame.getFrameId())) {
            _updateTexts(keyFrame, z, z3);
            if (!z && z3 && !isSameLang(keyFrame.getTRLReqSrcLang(), keyFrame.getTarLang()) && i2 != 3004) {
                LTTLogger.i(TAG, "updateTexts: Translation result updated");
            }
        } else {
            String str = TAG;
            LTTLogger.i(str, "Not call _updateTexts() : mLastestFrmaID=" + mLatestFrameID + ", KeyFrameID=" + keyFrame.getFrameId());
        }
        if (!z && z3) {
            releaseResources(keyFrame);
        }
    }

    public void _captureRequestCallback() {
        LTTLogger.i(TAG, "captureRequestCallback");
        RenderingScreen renderingScreen = getRenderingScreen();
        if (renderingScreen != null) {
            uiHandler.post(new c(1, renderingScreen));
        } else {
            uiHandler.post(new i(21));
        }
    }

    public RenderingScreen getRenderingScreen() {
        LTTLogger.i(TAG, "getRenderingScreen");
        return _getRenderingScreenInJPG();
    }

    public synchronized void processImage(ImageBuffer imageBuffer, Context context, float f, int i2, int i7) {
        Object obj;
        synchronized (this) {
            try {
                String str = TAG;
                StringBuilder sb2 = new StringBuilder("processImage() : E (imageBuffer.getmImageBuffer()=");
                if (imageBuffer.getImageBuffer() == null) {
                    obj = "null";
                } else {
                    obj = Integer.valueOf(imageBuffer.getImageBuffer().length);
                }
                sb2.append(obj);
                sb2.append(")");
                LTTLogger.i(str, sb2.toString());
                this.mIsImageModeSelected = true;
                int _processImage = _processImage(imageBuffer.getImageBuffer());
                LTTLogger.d(str, "processImage: STR_signal - " + _processImage);
                if (isSTRRequestingSignal(_processImage)) {
                    LTTLogger.i(str, "Frame is ready to process");
                    this.mStability.set(true);
                    mLatestFrameID = 1;
                    KeyFrame prepareKeyFrame = prepareKeyFrame(imageBuffer.getImageBuffer(), context, f, i2, i7, 0);
                    prepareKeyFrame.setBaseImageHash(imageBuffer.getBaseImageHash());
                    if (Config.IS_PROFILING_ENABLED) {
                        this.startLTT_Time = System.currentTimeMillis();
                    }
                    LTTLogger.i(str, "processImage: STATUS - " + this.mStatus);
                    this.mOnTaskControllerListener.requestNewTask(context, prepareKeyFrame, this.mROIinLandscapeAxis, LiveTranslationTask.TASKTYPE.POST_STR, new LiveTranslationTaskManagerListenerImpl(this, 0));
                    this.mStatus = LiveTranslationTaskManager.STATUS.STR_WAITING;
                    LTTLogger.i(str, "processImage: Status - " + this.mStatus);
                    if (!this.mInProgress.get() && mOnEngineListener != null) {
                        this.mInProgress.set(true);
                        mOnEngineListener.startProcess();
                    }
                }
                updateEngine(false);
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
    }

    public void refreshSession() {
        String str = TAG;
        LTTLogger.d(str, "refreshSession-E");
        _refreshSession();
        LTTLogger.d(str, "refreshSession-X");
    }

    public synchronized void release() {
        try {
            String str = TAG;
            LTTLogger.i(str, "release()");
            Handler handler = lttRendererHandler;
            if (handler != null) {
                handler.removeCallbacksAndMessages((Object) null);
                lttRendererHandler = null;
            }
            Handler handler2 = updateTextHandler;
            if (handler2 != null) {
                handler2.removeCallbacksAndMessages((Object) null);
                updateTextHandler = null;
            }
            Handler handler3 = uiHandler;
            if (handler3 != null) {
                handler3.removeCallbacksAndMessages((Object) null);
                uiHandler = null;
            }
            if (lttRendererHandlerThread.isAlive()) {
                lttRendererHandlerThread.quitSafely();
                lttRendererHandlerThread = null;
                LTTLogger.i(str, "lttRendererHandlerThread released");
            }
            if (updateTextHandlerThread.isAlive()) {
                updateTextHandlerThread.quitSafely();
                updateTextHandlerThread.join();
                updateTextHandlerThread = null;
                LTTLogger.i(TAG, "updateTextHandlerThread released");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
        mOnEngineListener = null;
        _release();
    }

    public void setOnEngineListener(OnEngineListener onEngineListener) {
        String str = TAG;
        LTTLogger.i(str, "setOnEngineListener: " + Objects.toString(onEngineListener, "null"));
        mOnEngineListener = onEngineListener;
    }

    public void setOnTaskControllerListener(OnTaskControllerListener onTaskControllerListener) {
        String str = TAG;
        LTTLogger.d(str, "setOnTaskControllerListener : " + Objects.toString(onTaskControllerListener, "null"));
        this.mOnTaskControllerListener = onTaskControllerListener;
    }

    public static void callback(int i2, int i7, int i8, int i10, boolean z) {
    }
}
