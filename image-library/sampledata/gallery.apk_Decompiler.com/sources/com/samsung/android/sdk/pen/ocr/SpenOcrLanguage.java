package com.samsung.android.sdk.pen.ocr;

import com.samsung.android.sdk.ocr.OCRLanguage;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum SpenOcrLanguage {
    AUTO(OCRLanguage.AUTO, "auto"),
    ENGLISH(OCRLanguage.ENGLISH, "en_US"),
    FRENCH(OCRLanguage.FRENCH, "fr_FR"),
    GERMAN(OCRLanguage.GERMAN, "de_DE"),
    ITALIAN(OCRLanguage.ITALIAN, "it_IT"),
    SPANISH(OCRLanguage.SPANISH, "es_ES"),
    KOREAN(OCRLanguage.KOREAN, "ko_KR"),
    PORTUGUESE(OCRLanguage.PORTUGUESE, "pt_PT"),
    CHINESE(OCRLanguage.CHINESE, "zh_CN");
    
    private final String languageCode;
    private final OCRLanguage ocrLanguage;

    private SpenOcrLanguage(OCRLanguage oCRLanguage, String str) {
        this.ocrLanguage = oCRLanguage;
        this.languageCode = str;
    }

    public static SpenOcrLanguage from(OCRLanguage oCRLanguage) {
        for (SpenOcrLanguage spenOcrLanguage : values()) {
            if (oCRLanguage == spenOcrLanguage.toOCRLanguage()) {
                return spenOcrLanguage;
            }
        }
        return ENGLISH;
    }

    public String toLanguageCode() {
        return this.languageCode;
    }

    public OCRLanguage toOCRLanguage() {
        return this.ocrLanguage;
    }
}
