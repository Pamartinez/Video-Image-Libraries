package com.samsung.android.sdk.ocr;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum OCRType {
    OCR_ALL(0),
    OCR_PRINTED(1),
    OCR_HANDWRITTEN(2);
    
    private final int value;

    private OCRType(int i2) {
        this.value = i2;
    }

    public int getValue() {
        return this.value;
    }
}
