package com.samsung.android.ocr.stride;

import android.content.res.Resources;
import android.graphics.Point;
import com.samsung.android.ocr.MOCR;
import com.samsung.android.ocr.MOCRConstants;
import com.samsung.android.ocr.MOCRImage;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.ocr.MOCRLog;
import com.samsung.android.ocr.MOCROptions;
import com.samsung.android.ocr.MOCRResult;
import com.samsung.android.ocr.stride.postocr.PostProcessCorrection;
import com.samsung.android.ocr.stride.postocr.entity.DateCorrection;
import com.samsung.android.ocr.stride.postocr.entity.EmailCorrection;
import com.samsung.android.ocr.stride.postocr.entity.PhoneNumberCorrection;
import com.samsung.android.ocr.stride.postocr.entity.TimeCorrection;
import com.samsung.android.ocr.stride.postocr.entity.URLCorrection;
import com.samsung.android.ocr.stride.postocr.entity.UnitCorrection;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Stride extends MOCR {
    private static final int NO_LANG = -1;
    private static final String TAG = "Stride";
    private static volatile Stride stride;
    private int lang;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class IconResult {
        public Point[] iconRect;
    }

    private static native synchronized void Close();

    private static native synchronized int Detect(MOCRImage mOCRImage, MOCRResult.Page page);

    private static native synchronized boolean DetectDocument(MOCRImage mOCRImage, long[] jArr);

    public static native synchronized IconResult[] DetectIcon(MOCRImage mOCRImage);

    private static native synchronized int DetectScript(MOCRImage mOCRImage);

    private static native synchronized int DetectScriptAll(MOCRImage mOCRImage, MOCRResult.Page page);

    public static native synchronized int DetectTable(MOCRImage mOCRImage, MOCRResult.Page page);

    public static native synchronized int DetectTableCells(MOCRImage mOCRImage, MOCRResult.Page page, boolean z);

    private static native synchronized boolean DetectText(MOCRImage mOCRImage);

    public static native synchronized int DetectWoLayout(MOCRImage mOCRImage, MOCRResult.Page page);

    private static native synchronized String GetBuildType();

    private static native synchronized String GetVersion();

    private static native synchronized int Init(String str, int i2);

    private static native synchronized int Init(String str, int i2, boolean z);

    public static native synchronized int MakeSmoothPoly(MOCRImage mOCRImage, MOCRResult.Page page);

    private static native synchronized int Recognize(MOCRImage mOCRImage, MOCRResult.Page page);

    public static native synchronized int RecognizeWithLD(MOCRImage mOCRImage, MOCRResult.Page page);

    public static native synchronized int RecognizeWoLD(MOCRImage mOCRImage, MOCRResult.Page page);

    private int Recognize_Java(MOCRImage mOCRImage, MOCRResult.Page page) {
        int Recognize = Recognize(mOCRImage, page);
        if (Recognize == MOCRConstants.MOCRStatus.MOCRSuccess.getValue() && getBuildType().equalsIgnoreCase("Default")) {
            MOCRLog.d(TAG, "Entity PostProcess start");
            EmailCorrection.validateEmailLine(page);
            URLCorrection.validateURLLine(page);
            PhoneNumberCorrection.validatePhoneLine(page);
            DateCorrection.validateDateFormat(page);
            TimeCorrection.validateTimeFormat(page);
            UnitCorrection.validateUnits(page);
            try {
                PostProcessCorrection.process(page);
            } catch (IndexOutOfBoundsException e) {
                MOCRLog.e(TAG, "Error in PostProcessCorrection due to bounds");
                e.printStackTrace();
            }
            MOCRLog.d(TAG, "Entity PostProcess end");
        }
        this.lang = page.ocrLang;
        String str = TAG;
        MOCRLog.d(str, "OCR Lang is " + this.lang);
        return Recognize;
    }

    public static native synchronized void SetFileNameForDebug(String str);

    private static native synchronized void SetOptions(MOCROptions mOCROptions);

    private int getDeviceLang() {
        return MOCRLang.getLangFromLocale(Resources.getSystem().getConfiguration().getLocales().get(0));
    }

    public static Stride getInstance() {
        if (stride == null) {
            synchronized (Stride.class) {
                try {
                    if (stride == null) {
                        stride = new Stride();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return stride;
    }

    public static native synchronized int recognizeLayout(MOCRImage mOCRImage, MOCRResult.Page page);

    public void deinit() {
        Close();
    }

    public int detect(byte[] bArr, int i2, int i7, MOCRConstants.MOCRPxlFmt mOCRPxlFmt, MOCRResult.Page page) {
        return Detect(MOCRImage.fromByteArray(bArr, i7, i2, mOCRPxlFmt.getValue()), page);
    }

    public int detectScript(MOCRImage mOCRImage) {
        if (mOCRImage == null) {
            return MOCRConstants.MOCRStatus.MOCRUnsupportedFormat.getValue();
        }
        return DetectScript(mOCRImage);
    }

    public int detectScriptAll(MOCRImage mOCRImage, MOCRResult.Page page) {
        if (mOCRImage == null) {
            return MOCRConstants.MOCRStatus.MOCRUnsupportedFormat.getValue();
        }
        return DetectScriptAll(mOCRImage, page);
    }

    public boolean detectText(byte[] bArr, int i2, int i7, MOCRConstants.MOCRPxlFmt mOCRPxlFmt) {
        return DetectText(MOCRImage.fromByteArray(bArr, i7, i2, mOCRPxlFmt.getValue()));
    }

    public boolean detectText_ARGB(MOCRImage mOCRImage) {
        if (mOCRImage == null) {
            return false;
        }
        return DetectText(mOCRImage);
    }

    public int detect_ARGB(MOCRImage mOCRImage, MOCRResult.Page page) {
        if (mOCRImage == null) {
            return MOCRConstants.MOCRStatus.MOCRUnsupportedFormat.getValue();
        }
        return Detect(mOCRImage, page);
    }

    public String getBuildType() {
        try {
            return GetBuildType();
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
            return "Default";
        }
    }

    public String getVersion() {
        return GetVersion();
    }

    public int init(String str, int i2, boolean z) {
        this.lang = i2;
        if (i2 == 1001) {
            this.lang = getDeviceLang();
            String str2 = TAG;
            MOCRLog.i(str2, "Auto Device Lang - " + this.lang);
        }
        return Init(str, this.lang, z);
    }

    public boolean isDocumentImage(MOCRImage mOCRImage, long[] jArr) {
        if (mOCRImage == null) {
            return false;
        }
        return DetectDocument(mOCRImage, jArr);
    }

    public int run(byte[] bArr, int i2, int i7, MOCRConstants.MOCRPxlFmt mOCRPxlFmt, MOCRResult.Page page) {
        return Recognize_Java(MOCRImage.fromByteArray(bArr, i7, i2, mOCRPxlFmt.getValue()), page);
    }

    public int run_ARGB(MOCRImage mOCRImage, MOCRResult.Page page) {
        if (mOCRImage == null) {
            return MOCRConstants.MOCRStatus.MOCRUnsupportedFormat.getValue();
        }
        return Recognize_Java(mOCRImage, page);
    }

    public void set_options(MOCROptions mOCROptions) {
        SetOptions(mOCROptions);
    }

    public int init(String str, int i2) {
        this.lang = i2;
        if (i2 == 1001) {
            this.lang = getDeviceLang();
            String str2 = TAG;
            MOCRLog.i(str2, "Auto Device Lang - " + this.lang);
        }
        return Init(str, this.lang);
    }

    public int cvtToLang(int i2) {
        return i2;
    }
}
