package com.samsung.android.sdk.ocr;

import android.content.Context;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecognizerParams {
    public Context context;
    public boolean keepOpen;
    public OCRLanguage language;
    public OCRType ocrType;

    public RecognizerParams(Context context2, OCRType oCRType, OCRLanguage oCRLanguage) {
        this.context = context2;
        this.ocrType = oCRType;
        this.language = oCRLanguage;
        this.keepOpen = false;
    }

    public RecognizerParams(Context context2, OCRType oCRType, OCRLanguage oCRLanguage, boolean z) {
        this.context = context2;
        this.ocrType = oCRType;
        this.language = oCRLanguage;
        this.keepOpen = z;
    }
}
