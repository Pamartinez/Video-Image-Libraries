package com.samsung.android.sdk.pen.ocr;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum SpenDBType {
    OCR(61440),
    BlockAnalyzer(3844),
    MoireDetector(240);
    
    private final int value;

    private SpenDBType(int i2) {
        this.value = i2;
    }

    public int getValue() {
        return this.value;
    }
}
