package com.samsung.android.sdk.ocr;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.Log;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.sdk.ocr.OCRException;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecognizerInternal implements IRecognizer {
    private static final String TAG = "RecognizerInternal";
    private static final Object mSyncObj = new Object();
    protected IOCRecognizer mCurrentRecognizer;
    protected RecognizerProvider mRecognizerProvider;
    protected RecognizerSupporter mRecognizerSupporter;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface ocr_processor {
        boolean process(IOCRecognizer iOCRecognizer, Bitmap bitmap, OCRResult oCRResult);
    }

    public RecognizerInternal(RecognizerParams recognizerParams) {
        this(recognizerParams.context, recognizerParams.ocrType, recognizerParams.language, recognizerParams.keepOpen);
    }

    private void checkRecognizerStatus() {
        RecognizerProvider recognizerProvider = this.mRecognizerProvider;
        if (recognizerProvider != null) {
            recognizerProvider.checkRecognizerIsCreated();
            return;
        }
        throw new OCRException.InvalidUsageException("Recognizer is closed or has not been created");
    }

    public static boolean isSupported(Context context, OCRType oCRType) {
        return RecognizerSupporter.isSupported(context, oCRType);
    }

    private boolean processCommon(Bitmap bitmap, OCRResult oCRResult) {
        checkRecognizerStatus();
        if (!RecognizerAPIChecker.isValidParameter(bitmap, oCRResult)) {
            return false;
        }
        Log.i(TAG, String.format("[process] Input Image Size: (%d, %d)", new Object[]{Integer.valueOf(bitmap.getWidth()), Integer.valueOf(bitmap.getHeight())}));
        long currentTimeMillis = System.currentTimeMillis();
        if (this.mCurrentRecognizer != null) {
            Log.e(TAG, "Recognizer is already in processing");
            return false;
        }
        IOCRecognizer recognizer = this.mRecognizerProvider.getRecognizer(bitmap, oCRResult.getProcessInfo());
        this.mCurrentRecognizer = recognizer;
        if (recognizer == null) {
            Log.e(TAG, "There is no recognizer created.");
            return false;
        }
        Log.i(TAG, String.format("OCR Common processing Time: %d ms", new Object[]{Long.valueOf(System.currentTimeMillis() - currentTimeMillis)}));
        return true;
    }

    private void processEnd() {
        this.mCurrentRecognizer = null;
        Log.i(TAG, "Processing of Recognizer completed");
    }

    public void cancel() {
        if (this.mCurrentRecognizer == null) {
            Log.w(TAG, "There is no recognizer to cancel processing");
            return;
        }
        Log.i(TAG, "Cancel processing...");
        this.mCurrentRecognizer.cancel();
    }

    public void close() {
        Log.i(TAG, "RecognizerInternal close()");
        closeRecognizerProvider();
    }

    public void closeRecognizerProvider() {
        RecognizerProvider recognizerProvider = this.mRecognizerProvider;
        if (recognizerProvider != null) {
            recognizerProvider.close();
        }
        this.mRecognizerProvider = null;
    }

    public void createRecognizerProvider(RecognizerParams recognizerParams) {
        closeRecognizerProvider();
        this.mRecognizerProvider = new RecognizerProvider(recognizerParams, this.mRecognizerSupporter);
    }

    public boolean detect(Bitmap bitmap, OCRResult oCRResult) {
        return process(bitmap, new ocr_processor() {
            public boolean process(IOCRecognizer iOCRecognizer, Bitmap bitmap, OCRResult oCRResult) {
                Log.i(RecognizerInternal.TAG, "OCR Text Detection(Localizing)....");
                boolean detect = iOCRecognizer.detect(bitmap, oCRResult);
                Log.i(RecognizerInternal.TAG, "OCR Text Detection(Localizing) Done(ret=" + detect + ")");
                return detect;
            }
        }, oCRResult);
    }

    public boolean detectBlock(Bitmap bitmap, Point[] pointArr) {
        return detectBlock(bitmap, new Point(bitmap.getWidth() / 2, bitmap.getHeight() / 2), pointArr);
    }

    public boolean detectText(Bitmap bitmap) {
        return process(bitmap, new ocr_processor() {
            public boolean process(IOCRecognizer iOCRecognizer, Bitmap bitmap, OCRResult oCRResult) {
                Log.i(RecognizerInternal.TAG, "OCR Text Detecting(detectText)....");
                boolean detectText = iOCRecognizer.detectText(bitmap);
                Log.i(RecognizerInternal.TAG, "OCR Text Detecting(detectText) Done (ret=" + detectText + ")");
                return detectText;
            }
        }, new OCRResult());
    }

    public void finalize() {
        close();
    }

    public boolean hasText(Bitmap bitmap) {
        return hasText(bitmap, true);
    }

    public boolean isHandwritten(Bitmap bitmap) {
        Log.i(TAG, String.format("[isHandwritten] Input Image Size: (%d, %d)", new Object[]{Integer.valueOf(bitmap.getWidth()), Integer.valueOf(bitmap.getHeight())}));
        checkRecognizerStatus();
        return this.mRecognizerProvider.isHandwritten(bitmap);
    }

    public boolean isPrinted(Bitmap bitmap) {
        Log.i(TAG, String.format("[isPrinted] Input Image Size: (%d, %d)", new Object[]{Integer.valueOf(bitmap.getWidth()), Integer.valueOf(bitmap.getHeight())}));
        checkRecognizerStatus();
        return this.mRecognizerProvider.isPrinted(bitmap);
    }

    public boolean process(Bitmap bitmap, ocr_processor ocr_processor2, OCRResult oCRResult) {
        synchronized (mSyncObj) {
            try {
                if (!processCommon(bitmap, oCRResult)) {
                    return false;
                }
                long currentTimeMillis = System.currentTimeMillis();
                IOCRecognizer iOCRecognizer = this.mCurrentRecognizer;
                if (iOCRecognizer == null) {
                    Log.e(TAG, "OCR processing : Recognizer is null!");
                    return false;
                }
                boolean process = ocr_processor2.process(iOCRecognizer, bitmap, oCRResult);
                Log.i(TAG, "OCR Processing result of processor.process(...): " + process);
                Log.i(TAG, String.format("OCR processing Time: %d ms", new Object[]{Long.valueOf(System.currentTimeMillis() - currentTimeMillis)}));
                processEnd();
                return process;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public boolean recognize(Bitmap bitmap, OCRResult oCRResult) {
        return process(bitmap, new ocr_processor() {
            public boolean process(IOCRecognizer iOCRecognizer, Bitmap bitmap, OCRResult oCRResult) {
                int i2;
                Log.i(RecognizerInternal.TAG, "OCR Text Recognizing....");
                boolean recognize = iOCRecognizer.recognize(bitmap, oCRResult);
                String text = oCRResult.getText();
                if (text.isEmpty()) {
                    i2 = 0;
                } else {
                    i2 = text.length();
                }
                Log.i(RecognizerInternal.TAG, "OCR Text Recognizing Done(success=" + recognize + ")");
                Log.i(RecognizerInternal.TAG, String.format("%d characters(including whitespace) are recognized.", new Object[]{Integer.valueOf(i2)}));
                if (!recognize) {
                    Log.e(RecognizerInternal.TAG, "OCR Process.recognize() failed!");
                }
                return recognize;
            }
        }, oCRResult);
    }

    public boolean recognizeBlockAt(Bitmap bitmap, Point point, boolean z, OCRResult oCRResult) {
        synchronized (mSyncObj) {
            try {
                int i2 = 0;
                if (!processCommon(bitmap, oCRResult)) {
                    return false;
                }
                if (point == null) {
                    Log.e(TAG, "Point of Interest is NULL");
                    return false;
                }
                long currentTimeMillis = System.currentTimeMillis();
                Log.i(TAG, "OCR Text Recognizing(BlocK) at (" + point.x + ArcCommonLog.TAG_COMMA + point.y + ") forceToSelectAny=" + z);
                IOCRecognizer iOCRecognizer = this.mCurrentRecognizer;
                if (iOCRecognizer == null) {
                    Log.e(TAG, "recognizeBlockAt : Recognizer is null!");
                    return false;
                }
                boolean recognizeBlockAt = iOCRecognizer.recognizeBlockAt(bitmap, point, z, oCRResult);
                String text = oCRResult.getText();
                if (!text.isEmpty()) {
                    i2 = text.length();
                }
                Log.i(TAG, "OCR Text Recognizing(BlocK) Done(success=" + recognizeBlockAt + ")");
                Log.i(TAG, String.format("%d characters(including whitespace) are recognized.", new Object[]{Integer.valueOf(i2)}));
                if (!recognizeBlockAt) {
                    Log.e(TAG, "OCR recognizeBlockAt(...) failed!");
                }
                Log.i(TAG, "OCR Processing result of recognizeBlockAt(...): " + recognizeBlockAt);
                Log.i(TAG, String.format("OCR processing Time: %d ms", new Object[]{Long.valueOf(System.currentTimeMillis() - currentTimeMillis)}));
                processEnd();
                return recognizeBlockAt;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public RecognizerInternal(Context context, OCRType oCRType, OCRLanguage oCRLanguage) {
        this(context, oCRType, oCRLanguage, false);
    }

    public boolean hasText(Bitmap bitmap, boolean z) {
        Log.i(TAG, String.format("[hasText] Input Image Size: (%d, %d)", new Object[]{Integer.valueOf(bitmap.getWidth()), Integer.valueOf(bitmap.getHeight())}));
        Log.i(TAG, "[hasText] checkPrintedTypeFirst : " + z);
        if (z) {
            if (this.mRecognizerProvider.isPrinted(bitmap)) {
                Log.i(TAG, "Step 1. hasText() true because isPrinted(bitmap) is true");
                return true;
            }
            Log.i(TAG, "Step 1. hasText() undefined because isPrinted(bitmap) is false");
            if (this.mRecognizerProvider.isHandwritten(bitmap)) {
                Log.i(TAG, "Step 2. hasText() true because isHandwritten(bitmap) is true");
                return true;
            }
            Log.i(TAG, "Step 2. hasText() false because isHandwritten(bitmap) is false");
            return false;
        } else if (this.mRecognizerProvider.isHandwritten(bitmap)) {
            Log.i(TAG, "Step 1. hasText() true because isHandwritten(bitmap) is true");
            return true;
        } else {
            Log.i(TAG, "Step 1. hasText() undefined because isHandwritten(bitmap) is false");
            if (this.mRecognizerProvider.isPrinted(bitmap)) {
                Log.i(TAG, "Step 2. hasText() true because isPrinted(bitmap) is true");
                return true;
            }
            Log.i(TAG, "Step 2. hasText() false because isPrinted(bitmap) is false");
            return false;
        }
    }

    public RecognizerInternal(Context context, OCRType oCRType, OCRLanguage oCRLanguage, boolean z) {
        this.mRecognizerSupporter = null;
        this.mRecognizerProvider = null;
        this.mCurrentRecognizer = null;
        long currentTimeMillis = System.currentTimeMillis();
        Log.i(TAG, "OCR Recognizer(Internal) is initialized with version: 3.4.251128");
        RecognizerParams recognizerParams = new RecognizerParams(context, oCRType, oCRLanguage, z);
        Log.i(TAG, "- Language : " + recognizerParams.language.toString());
        Log.i(TAG, "- OCR Type : " + recognizerParams.ocrType);
        Log.i(TAG, "- Keep Open: " + recognizerParams.keepOpen);
        this.mRecognizerSupporter = new RecognizerSupporter(context);
        Log.i(TAG, "- MOCR Support: " + this.mRecognizerSupporter.isMOCRSupported);
        Log.i(TAG, "- SOCR Support: " + this.mRecognizerSupporter.isSOCRSupported);
        this.mRecognizerSupporter.checkRecognizerSupportedType(oCRType);
        createRecognizerProvider(recognizerParams);
        Log.i(TAG, String.format("OCR Initialization(Recognizer Creation) Time: %d ms", new Object[]{Long.valueOf(System.currentTimeMillis() - currentTimeMillis)}));
    }

    public boolean detectBlock(Bitmap bitmap, Point point, Point[] pointArr) {
        Log.i(TAG, String.format("[detectBlock] Input Image Size: (%d, %d)", new Object[]{Integer.valueOf(bitmap.getWidth()), Integer.valueOf(bitmap.getHeight())}));
        if (!RecognizerAPIChecker.isValidParameter(bitmap, pointArr)) {
            return false;
        }
        OCRResult oCRResult = new OCRResult();
        long currentTimeMillis = System.currentTimeMillis();
        IOCRecognizer recognizer = this.mRecognizerProvider.getRecognizer(bitmap, oCRResult.getProcessInfo());
        if (recognizer == null) {
            Log.e(TAG, "There is no recognizer created.");
            return false;
        }
        boolean detectBlock = recognizer.detectBlock(bitmap, point, pointArr);
        if (!detectBlock) {
            Log.w(TAG, "There is no detected block or OCR Processing Fail");
        }
        Log.i(TAG, String.format("OCR processing(detectBlock) Time: %d ms", new Object[]{Long.valueOf(System.currentTimeMillis() - currentTimeMillis)}));
        return detectBlock;
    }
}
