package com.samsung.android.sdk.ocr;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Build;
import android.util.Log;
import com.samsung.android.sdk.ocr.OCRResult;
import com.samsung.android.sdk.pen.ocr.SpenIOcrEngine;
import com.samsung.android.sdk.pen.ocr.SpenIOcrRecognizer;
import com.samsung.android.sdk.pen.ocr.SpenMoireDetector;
import com.samsung.android.sdk.pen.ocr.SpenOcrBlockData;
import com.samsung.android.sdk.pen.ocr.SpenOcrError;
import com.samsung.android.sdk.pen.ocr.SpenOcrLanguage;
import com.samsung.android.sdk.pen.ocr.SpenOcrLineData;
import com.samsung.android.sdk.pen.ocr.SpenOcrPageData;
import com.samsung.android.sdk.pen.ocr.SpenOcrRecognitionListener;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SOCRecognizerBase extends SpenOcrRecognitionListener implements IOCRecognizer {
    private static final String TAG = "SOCRecognizerBase";
    protected String mLanguageString = "";
    protected SpenMoireDetector mMoireDetector = null;
    protected SpenOcrPageData mOcrPageData = null;
    protected SpenIOcrRecognizer mRecognizer = null;
    private boolean mbEnableMoireDetector;

    public SOCRecognizerBase(SpenIOcrEngine spenIOcrEngine, RecognizerParams recognizerParams) {
        boolean z;
        this.mLanguageString = SpenOcrLanguage.from(recognizerParams.language).toLanguageCode();
        long currentTimeMillis = System.currentTimeMillis();
        spenIOcrEngine.loadLanguageDB(this.mLanguageString);
        Log.i(TAG, String.format("Time of Loading DB: %d ms", new Object[]{Long.valueOf(System.currentTimeMillis() - currentTimeMillis)}));
        this.mRecognizer = spenIOcrEngine.createRecognizer();
        if (Build.VERSION.SDK_INT < 35) {
            z = true;
        } else {
            z = false;
        }
        this.mbEnableMoireDetector = z;
        Log.i(TAG, "Support Moire in HWOCR? " + this.mbEnableMoireDetector);
        if (this.mbEnableMoireDetector) {
            createMoireDetector(recognizerParams.context, spenIOcrEngine);
        }
        Log.i(TAG, "SpenRecognizer(SOCRecognizerBase) is created!");
    }

    public void cancel() {
        Log.w(TAG, "Cancel operation is not supported on SOCR Base");
    }

    public void close() {
        SpenIOcrRecognizer spenIOcrRecognizer = this.mRecognizer;
        if (spenIOcrRecognizer != null) {
            spenIOcrRecognizer.close();
            this.mRecognizer = null;
        }
        super.close();
    }

    public void createMoireDetector(Context context, SpenIOcrEngine spenIOcrEngine) {
        this.mMoireDetector = new SpenMoireDetector(context, spenIOcrEngine);
        Log.i(TAG, "mMoireDetector is created!");
    }

    public void destroy() {
        if (this.mbEnableMoireDetector) {
            destroyMoireDetector();
        }
        close();
        Log.i(TAG, "SOCRecognizerBase destroyed");
    }

    public void destroyMoireDetector() {
        this.mMoireDetector.close();
        this.mMoireDetector = null;
        Log.i(TAG, "mMoireDetector is destroyed!");
    }

    public boolean detect(Bitmap bitmap, OCRResult oCRResult) {
        return detectBase(bitmap, oCRResult);
    }

    public boolean detectBase(Bitmap bitmap, OCRResult oCRResult) {
        boolean z;
        SpenOcrError detect = this.mRecognizer.detect(bitmap, new SpenRecogConfig("common"), this);
        if (detect != SpenOcrError.OE_Success) {
            Log.e(TAG, "SpenRecognizer::detect() Failed! ErrorCode : " + detect.toString());
            return false;
        }
        if (this.mOcrPageData.getTextBlockList().size() <= 0) {
            z = true;
        } else {
            z = false;
        }
        oCRResult.getProcessInfo().setHasText(z);
        if (z) {
            Log.w(TAG, "SpenRecognizer::detect() Failed! There is no detected text block");
            return false;
        }
        SOCRConverter.convertPage(this.mOcrPageData, oCRResult);
        return true;
    }

    public boolean detectBlock(Bitmap bitmap, Point[] pointArr) {
        return detectBlock(bitmap, new Point(bitmap.getWidth() / 2, bitmap.getHeight() / 2), pointArr);
    }

    public void detectMoire(Bitmap bitmap) {
        if (!this.mbEnableMoireDetector || !this.mMoireDetector.detectMoire(bitmap)) {
            this.mRecognizer.setConfiguration(SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_KEY_DEMOIRE, SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE);
        } else {
            this.mRecognizer.setConfiguration(SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_KEY_DEMOIRE, SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
        }
    }

    public boolean detectText(Bitmap bitmap) {
        Log.w(TAG, "detectText(bitmap) is true in case of being called when isHandwritten() is true. ");
        return true;
    }

    public void finalize() {
        super.finalize();
        close();
    }

    public String getVersion() {
        String version = this.mRecognizer.getVersion();
        Log.i(TAG, "Version(SOCR) = " + version);
        return version;
    }

    public boolean hasText(Bitmap bitmap) {
        SpenOcrError detect = this.mRecognizer.detect(bitmap);
        if (detect == SpenOcrError.OE_Success) {
            return true;
        }
        Log.e(TAG, "SpenRecognizer::detect() Failed! ErrorCode : " + detect.toString());
        return false;
    }

    public boolean isValidDetectedBlockPoints(Point[] pointArr, Point[] pointArr2) {
        if (pointArr == null) {
            Log.e(TAG, "Result buffer has not been allocated in advance.");
            return false;
        } else if (pointArr2 == null) {
            Log.e(TAG, "Detected Result is null");
            return false;
        } else if (pointArr.length == pointArr2.length) {
            return true;
        } else {
            Log.w(TAG, "Size of output corner points is different.");
            Log.w(TAG, String.format("- #outputCornerPoints=%d, #detectedPoints=%d", new Object[]{Integer.valueOf(pointArr.length), Integer.valueOf(pointArr2.length)}));
            if (pointArr.length < pointArr2.length) {
                Log.w(TAG, String.format("- #Points(%d) of detected block is much more than required points", new Object[]{Integer.valueOf(pointArr2.length)}));
                return true;
            }
            Log.e(TAG, String.format("- #Points(%d) of detected block is not enough.", new Object[]{Integer.valueOf(pointArr2.length)}));
            return false;
        }
    }

    public void onPageRecognitionCompleted(SpenOcrPageData spenOcrPageData) {
        if (spenOcrPageData == null) {
            Log.e(TAG, "onPageRecognitionCompleted() Failed! pageData == null");
            return;
        }
        Log.d(TAG, "onPageRecognitionCompleted() callback");
        this.mOcrPageData = spenOcrPageData;
    }

    public void onPageRecognitionStarted() {
        Log.i(TAG, "onPageRecognitionStarted() callback");
    }

    public void onTextBlockDetected(SpenOcrBlockData spenOcrBlockData) {
        if (spenOcrBlockData == null) {
            Log.e(TAG, "onTextBlockDetected() Failed! textBlock == null");
            return;
        }
        Log.d(TAG, "onTextBlockDetected() textBlock rect : " + spenOcrBlockData.getRectInfo());
    }

    public void onTextLineDetected(SpenOcrLineData spenOcrLineData) {
        if (spenOcrLineData == null) {
            Log.e(TAG, "onTextLineDetected() Failed! lineData == null");
            return;
        }
        Log.d(TAG, "onTextLineDetected() lineData uid : " + spenOcrLineData.getUID());
    }

    public void onTextLineRecognized(SpenOcrLineData spenOcrLineData) {
        if (spenOcrLineData == null) {
            Log.e(TAG, "onTextLineRecognized() Failed! lineData == null");
            return;
        }
        Log.d(TAG, "onTextLineRecognized() lineData uid : " + spenOcrLineData.getUID());
    }

    public boolean recognize(Bitmap bitmap, OCRResult oCRResult) {
        return recognizeBase(bitmap, oCRResult);
    }

    public boolean recognizeBase(Bitmap bitmap, OCRResult oCRResult) {
        int oneUIVersion = SpenRecogConfig.getOneUIVersion();
        if (oneUIVersion >= 60100) {
            String locale = Locale.getDefault().toString();
            Log.i(TAG, "Set system language : " + locale);
            this.mRecognizer.setConfiguration(SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_KEY_SYSTEM_LANGUAGE, locale);
        }
        if (oneUIVersion >= 70000) {
            Log.i(TAG, "Set memory optimization : ALLOW_MEMORY_OPT[True], ALLOW_PREFETCH_MODEL_OPT[FALSE]");
            this.mRecognizer.setConfiguration(SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_KEY_ALLOW_MEMORY_OPTIMIZATION, SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
            this.mRecognizer.setConfiguration(SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_KEY_ALLOW_PREFETCH_MODEL_OPTIMIZATION, SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE);
        }
        SpenOcrError recognize = this.mRecognizer.recognize(bitmap, new SpenRecogConfig(this.mLanguageString), this);
        if (recognize != SpenOcrError.OE_Success) {
            Log.e(TAG, "SpenRecognizer::recognize() Failed! ErrorCode : " + recognize.toString());
            return false;
        }
        SOCRConverter.convertPage(this.mOcrPageData, oCRResult);
        return true;
    }

    public boolean recognizeBlockAt(Bitmap bitmap, Point point, boolean z, OCRResult oCRResult) {
        Log.w(TAG, "recognizeBlockAt operation is not supported on SOCR Base");
        return false;
    }

    public boolean recognizeDetectedLines(Bitmap bitmap, OCRResult oCRResult) {
        SpenRecogConfig spenRecogConfig = new SpenRecogConfig(this.mLanguageString);
        Iterator<OCRResult.BlockData> it = oCRResult.getBlockDataList().iterator();
        while (it.hasNext()) {
            Iterator<OCRResult.LineData> it2 = it.next().getLineDataList().iterator();
            while (true) {
                if (it2.hasNext()) {
                    OCRResult.LineData next = it2.next();
                    SpenOcrLineData spenOcrLineData = new SpenOcrLineData();
                    float angle = next.getAngle();
                    Point point = new Point();
                    Bitmap rotatedROI = OCRResultUtils.getRotatedROI(bitmap, next.getRect(), angle, point);
                    next.offset(-point.x, -point.y);
                    SOCRConverter.convertLine(next, spenOcrLineData);
                    if (this.mRecognizer.recognize_line(rotatedROI, spenRecogConfig, spenOcrLineData) != SpenOcrError.OE_Success) {
                        return false;
                    }
                    SOCRConverter.convertLine(spenOcrLineData, next);
                    next.offset(point.x, point.y);
                    next.rotate(angle, next.getCenter());
                }
            }
        }
        return true;
    }

    public boolean recognizeLineAtBase(Bitmap bitmap, Point point, OCRResult oCRResult) {
        SpenOcrError recognize = this.mRecognizer.recognize(bitmap, new SpenRecogConfig(this.mLanguageString), this);
        if (recognize != SpenOcrError.OE_Success) {
            Log.e(TAG, "SpenRecognizer::recognize() Failed! ErrorCode : " + recognize.toString());
            return false;
        }
        SOCRConverter.convertPage(this.mOcrPageData, oCRResult);
        return true;
    }

    public boolean detectBlock(Bitmap bitmap, Point point, Point[] pointArr) {
        SpenOcrError detect = this.mRecognizer.detect(bitmap, new SpenRecogConfig("common"), this);
        if (detect != SpenOcrError.OE_Success) {
            Log.e(TAG, "SpenRecognizer::detect() Failed! ErrorCode : " + detect.toString());
            return false;
        }
        if (point == null) {
            point = OCRResultUtils.getImageCenter(bitmap);
        }
        int findNearestTextBlock = this.mOcrPageData.findNearestTextBlock(point);
        if (findNearestTextBlock < 0) {
            Log.w(TAG, "Fail to find nearest block");
            return false;
        }
        Point[] rect = this.mOcrPageData.getTextBlockList().get(findNearestTextBlock).getRect();
        if (!isValidDetectedBlockPoints(pointArr, rect)) {
            return false;
        }
        int length = pointArr.length;
        int length2 = rect.length;
        for (int i2 = 0; i2 < pointArr.length; i2++) {
            pointArr[i2] = new Point(rect[i2]);
        }
        return true;
    }
}
