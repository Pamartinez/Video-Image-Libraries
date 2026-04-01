package com.samsung.android.sdk.ocr;

import android.content.Context;
import android.util.Log;
import com.samsung.android.sdk.ocr.OCRException;
import com.samsung.android.sdk.pen.ocr.SpenOcrEngine;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecognizerSupporter {
    private static final String TAG = "RecognizerSupporter";
    public boolean isMOCRSupported;
    public boolean isSOCRSupported;

    public RecognizerSupporter(Context context) {
        this.isMOCRSupported = false;
        this.isSOCRSupported = false;
        this.isMOCRSupported = MOCRecognizer.isSupported();
        this.isSOCRSupported = SpenOcrEngine.isSupported(context);
    }

    public static boolean isSupported(Context context, OCRType oCRType) {
        if (OCRType.OCR_ALL == oCRType) {
            if (!MOCRecognizer.isSupported() || !SpenOcrEngine.isSupported(context)) {
                return false;
            }
            return true;
        } else if (OCRType.OCR_PRINTED == oCRType) {
            return MOCRecognizer.isSupported();
        } else {
            if (OCRType.OCR_HANDWRITTEN == oCRType) {
                return SpenOcrEngine.isSupported(context);
            }
            Log.e(TAG, "Undefined OCR Type : " + oCRType);
            return false;
        }
    }

    public void checkRecognizerSupportedType(OCRType oCRType) {
        boolean z = this.isMOCRSupported;
        if (!z && !this.isSOCRSupported) {
            throw new OCRException.UnsupportedRecognizerException("OCR Recognizer is not supported");
        } else if (!z && (oCRType == OCRType.OCR_PRINTED || oCRType == OCRType.OCR_ALL)) {
            throw new OCRException.UnsupportedRecognizerException("OCR Recognizer is not supported MOCR Type");
        } else if (this.isSOCRSupported) {
        } else {
            if (oCRType == OCRType.OCR_HANDWRITTEN || oCRType == OCRType.OCR_ALL) {
                throw new OCRException.UnsupportedRecognizerException("OCR Recognizer is not supported SOCR Type");
            }
        }
    }
}
