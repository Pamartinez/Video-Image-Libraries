package com.samsung.android.sdk.ocr;

import android.graphics.Bitmap;
import android.util.Log;
import com.adobe.internal.xmp.options.SerializeOptions;
import com.samsung.android.sdk.ocr.OCRException;
import com.samsung.android.sdk.ocr.OCRResult;
import com.samsung.android.sdk.pen.ocr.SpenIOcrEngine;
import com.samsung.android.sdk.pen.ocr.SpenITypeClassifier;
import com.samsung.android.sdk.pen.ocr.SpenOcrEngine;
import com.samsung.android.sdk.pen.ocr.SpenOcrLanguage;
import com.samsung.android.sdk.pen.ocr.SpenOcrModelLoaderFactory;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecognizerProvider {
    private static final String TAG = "RecognizerProvider";
    protected SpenITypeClassifier mClassifier = null;
    protected final DataConverter mDataConverter = new DataConverter(SerializeOptions.SORT);
    protected SpenIOcrEngine mEngine = null;
    protected IOCRecognizer mMOCRecognizer = null;
    protected OCRType mOCRType = OCRType.OCR_ALL;
    protected IOCRecognizer mSOCRecognizer = null;

    /* renamed from: com.samsung.android.sdk.ocr.RecognizerProvider$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$sdk$ocr$OCRType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.samsung.android.sdk.ocr.OCRType[] r0 = com.samsung.android.sdk.ocr.OCRType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$sdk$ocr$OCRType = r0
                com.samsung.android.sdk.ocr.OCRType r1 = com.samsung.android.sdk.ocr.OCRType.OCR_ALL     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$sdk$ocr$OCRType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.sdk.ocr.OCRType r1 = com.samsung.android.sdk.ocr.OCRType.OCR_PRINTED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$sdk$ocr$OCRType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.sdk.ocr.OCRType r1 = com.samsung.android.sdk.ocr.OCRType.OCR_HANDWRITTEN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.ocr.RecognizerProvider.AnonymousClass1.<clinit>():void");
        }
    }

    public RecognizerProvider(RecognizerParams recognizerParams, RecognizerSupporter recognizerSupporter) {
        this.mOCRType = recognizerParams.ocrType;
        if (recognizerSupporter.isSOCRSupported) {
            Log.w(TAG, "SOCR is supported");
            SpenOcrEngine spenOcrEngine = new SpenOcrEngine(recognizerParams.context, whichModelLoader(false));
            this.mEngine = spenOcrEngine;
            this.mClassifier = spenOcrEngine.createTypeClassifier();
            createSOCRRecognizer(recognizerParams);
        } else {
            Log.w(TAG, "SOCR is not supported");
        }
        if (recognizerSupporter.isMOCRSupported) {
            Log.w(TAG, "MOCR is supported");
            createMOCRecognizer(recognizerParams.language, recognizerParams.keepOpen);
            return;
        }
        Log.w(TAG, "MOCR is not supported");
    }

    private void closeCommonSCORecognizer() {
        SpenITypeClassifier spenITypeClassifier = this.mClassifier;
        if (spenITypeClassifier != null) {
            spenITypeClassifier.close();
        }
        this.mClassifier = null;
        IOCRecognizer iOCRecognizer = this.mSOCRecognizer;
        if (iOCRecognizer != null) {
            iOCRecognizer.destroy();
        }
        this.mSOCRecognizer = null;
        SpenIOcrEngine spenIOcrEngine = this.mEngine;
        if (spenIOcrEngine != null) {
            spenIOcrEngine.close();
        }
        this.mEngine = null;
    }

    private void closeMOCRecognizer() {
        IOCRecognizer iOCRecognizer = this.mMOCRecognizer;
        if (iOCRecognizer != null) {
            iOCRecognizer.destroy();
        }
        this.mMOCRecognizer = null;
    }

    private void createMOCRecognizer(OCRLanguage oCRLanguage, boolean z) {
        if (MOCRecognizer.isSupportedLanguage(oCRLanguage)) {
            this.mMOCRecognizer = new MOCRecognizer(oCRLanguage, z);
            return;
        }
        String obj = oCRLanguage.toString();
        Log.w(TAG, "MOCRecognizer does not support language(" + obj + ")");
        this.mMOCRecognizer = null;
    }

    private IOCRecognizer getHandwrittenTypeRecognizer(OCRResult.ProcessInfo processInfo) {
        if (this.mSOCRecognizer != null) {
            Log.i(TAG, "Select recognizer by user selected type: OCRType =" + this.mOCRType);
            return getRecognizer(true, processInfo);
        }
        Log.e(TAG, "User selected ocr type is not supported: " + this.mOCRType);
        return null;
    }

    private IOCRecognizer getPrintedTypeRecognizer(OCRResult.ProcessInfo processInfo) {
        if (this.mMOCRecognizer != null) {
            Log.i(TAG, "Select recognizer by user selected type: OCRType =" + this.mOCRType);
            return getRecognizer(false, processInfo);
        }
        Log.e(TAG, "User selected ocr type is not supported: " + this.mOCRType);
        return null;
    }

    public void checkRecognizerIsCreated() {
        if (this.mMOCRecognizer == null && this.mSOCRecognizer == null) {
            throw new OCRException.UnsupportedRecognizerException("Any recognizer has not been created!");
        }
    }

    public void close() {
        Log.i(TAG, "RecognizerProvider close()");
        closeMOCRecognizer();
        closeCommonSCORecognizer();
    }

    public void createSOCRRecognizer(RecognizerParams recognizerParams) {
        if (this.mEngine.isSupportedLanguage(SpenOcrLanguage.from(recognizerParams.language))) {
            this.mSOCRecognizer = new SOCRecognizer(this.mEngine, recognizerParams);
            return;
        }
        String obj = recognizerParams.language.toString();
        Log.w(TAG, "SOCRecognizer does not support language(" + obj + ")");
        this.mSOCRecognizer = null;
    }

    public void finalize() {
        close();
    }

    public IOCRecognizer getAllTypeRecognizer(Bitmap bitmap, OCRResult.ProcessInfo processInfo) {
        IOCRecognizer iOCRecognizer = this.mMOCRecognizer;
        if (iOCRecognizer != null && this.mSOCRecognizer != null) {
            SpenITypeClassifier spenITypeClassifier = this.mClassifier;
            if (spenITypeClassifier != null) {
                boolean isHandwrittenImage = spenITypeClassifier.isHandwrittenImage(bitmap);
                Log.i(TAG, "Select recognizer by type classification: isHandwritten=" + isHandwrittenImage);
                return getRecognizer(isHandwrittenImage, processInfo);
            }
            throw new NullPointerException("Type classifier is not ready");
        } else if (iOCRecognizer != null) {
            Log.w(TAG, "Select recognizer for Printed image");
            return getRecognizer(false, processInfo);
        } else {
            Log.w(TAG, "Select recognizer for Handwritten image");
            return getRecognizer(true, processInfo);
        }
    }

    public IOCRecognizer getRecognizer(Bitmap bitmap, OCRResult.ProcessInfo processInfo) {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$sdk$ocr$OCRType[this.mOCRType.ordinal()];
        if (i2 == 1) {
            return getAllTypeRecognizer(bitmap, processInfo);
        }
        if (i2 == 2) {
            return getPrintedTypeRecognizer(processInfo);
        }
        if (i2 == 3) {
            return getHandwrittenTypeRecognizer(processInfo);
        }
        Log.e(TAG, "User selected ocr type is not supported: " + this.mOCRType);
        return null;
    }

    public boolean isHandwritten(Bitmap bitmap) {
        long currentTimeMillis = System.currentTimeMillis();
        boolean isHandwrittenImage = this.mClassifier.isHandwrittenImage(bitmap);
        Log.i(TAG, String.format("Type Classification(isHandwrittenImage=%s), Time: %d ms", new Object[]{Boolean.toString(isHandwrittenImage), Long.valueOf(System.currentTimeMillis() - currentTimeMillis)}));
        return isHandwrittenImage;
    }

    public boolean isPrinted(Bitmap bitmap) {
        long currentTimeMillis = System.currentTimeMillis();
        boolean isPrintedImage = this.mClassifier.isPrintedImage(bitmap);
        Log.i(TAG, String.format("Type Classification(isPrintedImage=%s), Time: %d ms", new Object[]{Boolean.toString(isPrintedImage), Long.valueOf(System.currentTimeMillis() - currentTimeMillis)}));
        return isPrintedImage;
    }

    public SpenOcrModelLoaderFactory.MODEL_LOADER whichModelLoader(boolean z) {
        if (z) {
            return SpenOcrModelLoaderFactory.MODEL_LOADER.ASSETS;
        }
        return SpenOcrModelLoaderFactory.MODEL_LOADER.PROVIDER_LATEST;
    }

    public IOCRecognizer getRecognizer(boolean z, OCRResult.ProcessInfo processInfo) {
        processInfo.setHandwrittenResult(z);
        IOCRecognizer iOCRecognizer = z ? this.mSOCRecognizer : this.mMOCRecognizer;
        processInfo.setEngineVersion(iOCRecognizer.getVersion(), z);
        return iOCRecognizer;
    }
}
