package com.samsung.android.sdk.ocr;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.util.Log;
import com.samsung.android.ocr.MOCR;
import com.samsung.android.ocr.MOCROptions;
import com.samsung.android.ocr.MOCRResult;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class MOCRecognizer implements IOCRecognizer {
    private static final String TAG = "MOCRecgonzier";
    private static HashMap<OCRLanguage, Integer> mMapID_MOCR;
    private int mLanguageID;
    private MOCR mocr = null;

    static {
        HashMap<OCRLanguage, Integer> hashMap = new HashMap<>();
        mMapID_MOCR = hashMap;
        hashMap.put(OCRLanguage.AUTO, 1001);
        mMapID_MOCR.put(OCRLanguage.ENGLISH, 1);
        mMapID_MOCR.put(OCRLanguage.FRENCH, 11);
        mMapID_MOCR.put(OCRLanguage.GERMAN, 13);
        mMapID_MOCR.put(OCRLanguage.ITALIAN, 18);
        mMapID_MOCR.put(OCRLanguage.SPANISH, 26);
        mMapID_MOCR.put(OCRLanguage.KOREAN, 40);
        mMapID_MOCR.put(OCRLanguage.PORTUGUESE, 24);
        mMapID_MOCR.put(OCRLanguage.CHINESE, 60);
    }

    public MOCRecognizer(OCRLanguage oCRLanguage, boolean z) {
        this.mLanguageID = getMOCRLanguageID(oCRLanguage);
        MOCR instance = MOCR.getInstance(new MOCROptions.Builder().setGetCharResult(true).build());
        this.mocr = instance;
        instance.initialize(this.mLanguageID, z);
    }

    private static int getMOCRLanguageID(OCRLanguage oCRLanguage) {
        return mMapID_MOCR.get(oCRLanguage).intValue();
    }

    public static boolean isSupported() {
        if (MOCR.isOCRSupport()) {
            return true;
        }
        Log.e(TAG, "MOCR does not supported!");
        return false;
    }

    public static boolean isSupportedLanguage(OCRLanguage oCRLanguage) {
        return mMapID_MOCR.containsKey(oCRLanguage);
    }

    public void cancel() {
        Log.w(TAG, "Cancel operation is not supported on MOCR");
    }

    public void destroy() {
        this.mocr.deinitialize();
    }

    public boolean detect(Bitmap bitmap, OCRResult oCRResult) {
        Log.i(TAG, "MOCRecognizer::detect_ARGB_bmp() Used to MOCR library");
        MOCRResult.Page page = new MOCRResult.Page();
        int detect_ARGB_bmp = this.mocr.detect_ARGB_bmp(bitmap, page);
        boolean z = false;
        if (detect_ARGB_bmp != 0) {
            Log.e(TAG, "MOCRecognizer::detect_ARGB_bmp() MOCR(detect_ARGB_bmp) is Failed! ErrorCode : " + detect_ARGB_bmp);
        } else {
            MOCRResult.Block[] blockArr = page.blocks;
            if (blockArr != null && blockArr.length > 0) {
                z = true;
            }
            if (z) {
                MOCRConverter.convertPage(page, oCRResult);
            } else {
                Log.e(TAG, "MOCRecognizer::detect_ARGB_bmp() MOCR(detect_ARGB_bmp) is Failed! There is no detected text block");
            }
        }
        oCRResult.getProcessInfo().setHasText(z);
        return z;
    }

    public boolean detectBlock(Bitmap bitmap, Point[] pointArr) {
        return detectBlock(bitmap, new Point(bitmap.getWidth() / 2, bitmap.getHeight() / 2), pointArr);
    }

    public boolean detectText(Bitmap bitmap) {
        Log.i(TAG, "MOCRecognizer::detectText_ARGB_bmp() Used to MOCR library");
        return this.mocr.detectText_ARGB_bmp(bitmap);
    }

    public String getVersion() {
        String str;
        MOCR mocr2 = this.mocr;
        if (mocr2 != null) {
            str = mocr2.getVersion();
        } else {
            str = "";
        }
        Log.i(TAG, "Version(MOCR) = " + str);
        return str;
    }

    public boolean hasText(Bitmap bitmap) {
        Log.i(TAG, "Deprecated: MOCRecognizer::detectText_ARGB_bmp() Used to MOCR library");
        return this.mocr.detectText_ARGB_bmp(bitmap);
    }

    public boolean recognize(Bitmap bitmap, OCRResult oCRResult) {
        Log.i(TAG, "MOCRecognizer::process_ARGB_bmp() Used to MOCR library");
        Log.i(TAG, "MOCRecognizer::process_ARGB_bmp() ID: " + this.mLanguageID);
        MOCRResult.Page page = new MOCRResult.Page();
        int process_ARGB_bmp = this.mocr.process_ARGB_bmp(bitmap, page);
        if (process_ARGB_bmp == 0) {
            MOCRConverter.convertPage(page, oCRResult);
            return true;
        }
        Log.e(TAG, "MOCRecognizer::recognize() MOCR(process_ARGB_bmp) is Failed! ErrorCode : " + process_ARGB_bmp);
        return false;
    }

    public boolean recognizeBlockAt(Bitmap bitmap, Point point, boolean z, OCRResult oCRResult) {
        Log.i(TAG, "MOCRecognizer::recognizeBlock_ARGB() Used to MOCR library");
        return false;
    }

    public boolean recognizeDetectedLines(Bitmap bitmap, OCRResult oCRResult) {
        return recognize(bitmap, oCRResult);
    }

    public boolean detectBlock(Bitmap bitmap, Point point, Point[] pointArr) {
        Log.i(TAG, "Use MOCR::detectBlock_ARGB_bmp");
        return false;
    }
}
