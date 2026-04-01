package com.samsung.android.sdk.ocr;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum OCRLanguage {
    AUTO,
    ENGLISH,
    FRENCH,
    GERMAN,
    ITALIAN,
    SPANISH,
    KOREAN,
    PORTUGUESE,
    CHINESE;

    public static OCRLanguage fromString(String str) {
        try {
            return valueOf(str);
        } catch (Exception unused) {
            return ENGLISH;
        }
    }
}
