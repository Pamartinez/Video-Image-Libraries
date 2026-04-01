package com.samsung.android.sdk.ocr;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.Log;
import com.samsung.android.feature.SemFloatingFeature;
import com.samsung.android.sdk.pen.ocr.SpenOcrEngine;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Recognizer implements IRecognizer {
    private static final int SOCR_LIB_VERSION_FOR_ONEUI41 = 100;
    private static final int SOCR_LIB_VERSION_FOR_ONEUI411 = 200;
    private static final int SOCR_LIB_VERSION_FOR_ONEUI50 = 300;
    private static final String TAG = "Recognizer";
    protected IRecognizer instance;
    private boolean mClosing;
    private final SafeCloser mSafeCloser;

    /* renamed from: com.samsung.android.sdk.ocr.Recognizer$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$sdk$ocr$RecognizerVersion;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.samsung.android.sdk.ocr.RecognizerVersion[] r0 = com.samsung.android.sdk.ocr.RecognizerVersion.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$sdk$ocr$RecognizerVersion = r0
                com.samsung.android.sdk.ocr.RecognizerVersion r1 = com.samsung.android.sdk.ocr.RecognizerVersion.RECOGNIZER_FOR_FW_ONEUI41     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$sdk$ocr$RecognizerVersion     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.sdk.ocr.RecognizerVersion r1 = com.samsung.android.sdk.ocr.RecognizerVersion.RECOGNIZER_FOR_FW_ONEUI411     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$sdk$ocr$RecognizerVersion     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.sdk.ocr.RecognizerVersion r1 = com.samsung.android.sdk.ocr.RecognizerVersion.RECOGNIZER_FOR_FW_LATEST     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$sdk$ocr$RecognizerVersion     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.sdk.ocr.RecognizerVersion r1 = com.samsung.android.sdk.ocr.RecognizerVersion.RECOGNIZER_FOR_DATA_PROVIDER_SERVICE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.ocr.Recognizer.AnonymousClass1.<clinit>():void");
        }
    }

    public Recognizer(RecognizerParams recognizerParams) {
        this(recognizerParams.context, recognizerParams.ocrType, recognizerParams.language, recognizerParams.keepOpen);
    }

    private boolean checkInstanceNull(RecognizerApi recognizerApi) {
        return checkInstanceNull(recognizerApi, true);
    }

    private boolean checkIsClosing(RecognizerApi recognizerApi) {
        if (!this.mClosing) {
            return false;
        }
        String name = recognizerApi.toName();
        Log.e(TAG, "[" + name + "] This instance is closing...");
        decreaseCallingCountOf(recognizerApi);
        return true;
    }

    private void closeWith(boolean z) {
        Log.i(TAG, "Recognizer close() - start : waiting = " + z);
        if (!checkInstanceNull(RecognizerApi.CLOSE, false)) {
            if (z) {
                waitUntilOtherThreadIsDone();
            }
            this.mClosing = true;
            synchronized (this.mSafeCloser) {
                this.instance.close();
            }
            this.mClosing = false;
            Log.i(TAG, "Recognizer close() - finish");
        }
    }

    private void decreaseCallingCountOf(RecognizerApi recognizerApi) {
        SafeCloser safeCloser = this.mSafeCloser;
        if (safeCloser != null) {
            safeCloser.decreaseCallingCountOf(recognizerApi);
        }
    }

    public static int getVersionOfNativeLibInDevice(Context context, int i2) {
        if (i2 >= 200) {
            return RecognizerUtils.getVersionOfNativeLibInDevice();
        }
        return RecognizerUtils.getVersionOfNativeLibInOneUI41Device(context);
    }

    public static int getVersionOfNativeLibInServiceProvider(Context context) {
        return RecognizerUtils.getVersionOfNativeLibInServiceProvider(context);
    }

    private void increaseCallingCountOf(RecognizerApi recognizerApi) {
        SafeCloser safeCloser = this.mSafeCloser;
        if (safeCloser != null) {
            safeCloser.increaseCallingCountOf(recognizerApi);
        }
    }

    public static boolean isSupported(Context context, OCRType oCRType) {
        try {
            String string = SemFloatingFeature.getInstance().getString("SEC_FLOATING_FEATURE_CAMERA_CONFIG_STRIDE_OCR_VERSION");
            Log.i(TAG, "SEC_FLOATING_FEATURE_CAMERA_CONFIG_STRIDE_OCR_VERSION=[" + string + "]");
            if (!"V1".equals(string) && !"V2".equals(string)) {
                if (!"Lite".equals(string)) {
                    if ("None".equals(string)) {
                        Log.e(TAG, "Recognizer.isSupported() Return false with feature version[None]");
                        return false;
                    }
                    Log.e(TAG, "Recognizer.isSupported() Undefined version [" + string + "], checking libs in device");
                    Log.e(TAG, "Recognizer.isSupported() There is no floatingFeature for OCR_VERSION");
                    int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$sdk$ocr$RecognizerVersion[selectRecognizerVersion(context).ordinal()];
                    if (i2 == 1 || i2 == 2 || i2 == 3) {
                        if (!RecognizerInternal.isSupported(context, oCRType)) {
                            Log.e(TAG, "Recognizer.isSupported() RecognizerInternal.isSupported() is failed");
                            return false;
                        }
                    } else if (i2 != 4) {
                        Log.e(TAG, "Recognizer.isSupported() Undefined Recosgnizer Version");
                        return false;
                    } else if (!RecognizerProxy.isSupported(oCRType)) {
                        Log.e(TAG, "Recognizer.isSupported() RecognizerProxy.isSupported() is failed");
                        return false;
                    }
                    Log.w(TAG, "Recognizer.isSupported() Return true without feature");
                    return true;
                }
            }
            Log.i(TAG, "Recognizer.isSupported() Return true with feature version[" + string + "]");
            return true;
        } catch (NoClassDefFoundError e) {
            Log.e(TAG, "Recognizer.isSupported() [NoClassDefFoundError] " + e.getMessage());
        } catch (NoSuchMethodError e7) {
            Log.e(TAG, "Recognizer.isSupported() [NoSuchMethodError] " + e7.getMessage());
        }
    }

    public static RecognizerVersion selectRecognizerVersion(Context context) {
        if (!SpenOcrEngine.isSupported(context)) {
            Log.w(TAG, "SOCR is not supported!");
            return RecognizerVersion.RECOGNIZER_FOR_UNKNOWN;
        }
        int versionOfNativeLibInServiceProvider = getVersionOfNativeLibInServiceProvider(context);
        int versionOfNativeLibInDevice = getVersionOfNativeLibInDevice(context, versionOfNativeLibInServiceProvider);
        Log.i(TAG, String.format("Version: OCRDataProvider(%d), Device(%d)", new Object[]{Integer.valueOf(versionOfNativeLibInServiceProvider), Integer.valueOf(versionOfNativeLibInDevice)}));
        if (versionOfNativeLibInServiceProvider > versionOfNativeLibInDevice) {
            Log.i(TAG, "Version for RECOGNIZER_FOR_DATA_PROVIDER_SERVICE is selected");
            return RecognizerVersion.RECOGNIZER_FOR_DATA_PROVIDER_SERVICE;
        } else if (versionOfNativeLibInDevice >= 300) {
            Log.i(TAG, "Version for RECOGNIZER_FOR_FW_LATEST is selected");
            return RecognizerVersion.RECOGNIZER_FOR_FW_LATEST;
        } else if (versionOfNativeLibInDevice >= 200) {
            Log.i(TAG, "Version for RECOGNIZER_FOR_FW_ONEUI411 is selected");
            return RecognizerVersion.RECOGNIZER_FOR_FW_ONEUI411;
        } else {
            Log.i(TAG, "Version for RECOGNIZER_FOR_FW_ONEUI41 is selected");
            return RecognizerVersion.RECOGNIZER_FOR_FW_ONEUI41;
        }
    }

    private void waitUntilOtherThreadIsDone() {
        SafeCloser safeCloser = this.mSafeCloser;
        if (safeCloser != null) {
            safeCloser.waitUntilOtherThreadIsDone();
        }
    }

    public void cancel() {
        RecognizerApi recognizerApi = RecognizerApi.CANCEL;
        increaseCallingCountOf(recognizerApi);
        if (!checkInstanceNull(recognizerApi) && !checkIsClosing(recognizerApi)) {
            this.instance.cancel();
            decreaseCallingCountOf(recognizerApi);
        }
    }

    public void close() {
        closeWith(true);
    }

    public boolean detect(Bitmap bitmap, OCRResult oCRResult) {
        RecognizerApi recognizerApi = RecognizerApi.DETECT;
        increaseCallingCountOf(recognizerApi);
        if (checkInstanceNull(recognizerApi) || checkIsClosing(recognizerApi)) {
            return false;
        }
        boolean detect = this.instance.detect(bitmap, oCRResult);
        decreaseCallingCountOf(recognizerApi);
        return detect;
    }

    public boolean detectBlock(Bitmap bitmap, Point[] pointArr) {
        RecognizerApi recognizerApi = RecognizerApi.DETECT_BLOCK2;
        increaseCallingCountOf(recognizerApi);
        if (checkInstanceNull(recognizerApi) || checkIsClosing(recognizerApi)) {
            return false;
        }
        boolean detectBlock = this.instance.detectBlock(bitmap, pointArr);
        decreaseCallingCountOf(recognizerApi);
        return detectBlock;
    }

    public boolean detectText(Bitmap bitmap) {
        RecognizerApi recognizerApi = RecognizerApi.DETECT_TEXT;
        increaseCallingCountOf(recognizerApi);
        if (checkInstanceNull(recognizerApi) || checkIsClosing(recognizerApi)) {
            return false;
        }
        boolean detectText = this.instance.detectText(bitmap);
        decreaseCallingCountOf(recognizerApi);
        return detectText;
    }

    public void finalize() {
        closeWith(false);
    }

    public boolean hasText(Bitmap bitmap) {
        RecognizerApi recognizerApi = RecognizerApi.HAS_TEXT1;
        increaseCallingCountOf(recognizerApi);
        if (checkInstanceNull(recognizerApi) || checkIsClosing(recognizerApi)) {
            return false;
        }
        boolean hasText = this.instance.hasText(bitmap);
        decreaseCallingCountOf(recognizerApi);
        return hasText;
    }

    public boolean isHandwritten(Bitmap bitmap) {
        RecognizerApi recognizerApi = RecognizerApi.IS_HANDWRITTEN;
        increaseCallingCountOf(recognizerApi);
        if (checkInstanceNull(recognizerApi) || checkIsClosing(recognizerApi)) {
            return false;
        }
        boolean isHandwritten = this.instance.isHandwritten(bitmap);
        decreaseCallingCountOf(recognizerApi);
        return isHandwritten;
    }

    public boolean isPrinted(Bitmap bitmap) {
        RecognizerApi recognizerApi = RecognizerApi.IS_PRINTED;
        increaseCallingCountOf(recognizerApi);
        if (checkInstanceNull(recognizerApi) || checkIsClosing(recognizerApi)) {
            return false;
        }
        boolean isPrinted = this.instance.isPrinted(bitmap);
        decreaseCallingCountOf(recognizerApi);
        return isPrinted;
    }

    public boolean recognize(Bitmap bitmap, OCRResult oCRResult) {
        RecognizerApi recognizerApi = RecognizerApi.RECOGNIZE;
        increaseCallingCountOf(recognizerApi);
        if (checkInstanceNull(recognizerApi) || checkIsClosing(recognizerApi)) {
            return false;
        }
        boolean recognize = this.instance.recognize(bitmap, oCRResult);
        decreaseCallingCountOf(recognizerApi);
        return recognize;
    }

    public boolean recognizeBlockAt(Bitmap bitmap, Point point, boolean z, OCRResult oCRResult) {
        RecognizerApi recognizerApi = RecognizerApi.RECOGNIZE_BLOCK_AT;
        increaseCallingCountOf(recognizerApi);
        if (checkInstanceNull(recognizerApi) || checkIsClosing(recognizerApi)) {
            return false;
        }
        boolean recognizeBlockAt = this.instance.recognizeBlockAt(bitmap, point, z, oCRResult);
        decreaseCallingCountOf(recognizerApi);
        return recognizeBlockAt;
    }

    public Recognizer(Context context, OCRType oCRType, OCRLanguage oCRLanguage) {
        this(context, oCRType, oCRLanguage, false);
    }

    private boolean checkInstanceNull(RecognizerApi recognizerApi, boolean z) {
        if (this.instance != null) {
            return false;
        }
        String name = recognizerApi.toName();
        Log.e(TAG, "[" + name + "] Instance has not been created yet.");
        if (!z) {
            return true;
        }
        decreaseCallingCountOf(recognizerApi);
        return true;
    }

    public Recognizer(Context context, OCRType oCRType, OCRLanguage oCRLanguage, boolean z) {
        this.instance = null;
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$sdk$ocr$RecognizerVersion[selectRecognizerVersion(context).ordinal()];
        if (i2 == 1) {
            Log.i(TAG, "OCR Recognizer is initialized as RecognizerInternal_OneUI41 with version: 3.4.251128");
            this.instance = new RecognizerInternal_OneUI41(context, oCRType, oCRLanguage);
        } else if (i2 == 2) {
            Log.i(TAG, "OCR Recognizer is initialized as RecognizerInternal_OneUI411 with version: 3.4.251128");
            this.instance = new RecognizerInternal_OneUI411(context, oCRType, oCRLanguage);
        } else if (i2 == 3) {
            Log.i(TAG, "OCR Recognizer is initialized as RecognizerInternal with version: 3.4.251128");
            this.instance = new RecognizerInternal(context, oCRType, oCRLanguage, z);
        } else if (i2 != 4) {
            Log.e(TAG, "Undefined Recognizer Version");
        } else {
            Log.i(TAG, "OCR Recognizer is initialized as RecognizerProxy with version: 3.4.251128");
            this.instance = new RecognizerProxy(context, oCRType, oCRLanguage);
        }
        this.mSafeCloser = new SafeCloser(System.identityHashCode(this));
        this.mClosing = false;
    }

    public boolean detectBlock(Bitmap bitmap, Point point, Point[] pointArr) {
        RecognizerApi recognizerApi = RecognizerApi.DETECT_BLOCK3;
        increaseCallingCountOf(recognizerApi);
        if (checkInstanceNull(recognizerApi) || checkIsClosing(recognizerApi)) {
            return false;
        }
        boolean detectBlock = this.instance.detectBlock(bitmap, point, pointArr);
        decreaseCallingCountOf(recognizerApi);
        return detectBlock;
    }

    public boolean hasText(Bitmap bitmap, boolean z) {
        RecognizerApi recognizerApi = RecognizerApi.HAS_TEXT2;
        increaseCallingCountOf(recognizerApi);
        if (checkInstanceNull(recognizerApi) || checkIsClosing(recognizerApi)) {
            return false;
        }
        boolean hasText = this.instance.hasText(bitmap, z);
        decreaseCallingCountOf(recognizerApi);
        return hasText;
    }
}
