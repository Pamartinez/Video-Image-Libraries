package com.samsung.android.sdk.pen.ocr;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum SpenOcrError {
    OE_Success(0),
    OE_BlockAnalyzerNotLoaded(1),
    OE_DBNotLoaded(2),
    OE_UnknownImageFormat(3),
    OE_UknownOrientation(4),
    OE_RecogError(5),
    OE_DetectError(6);
    
    private final int value;

    private SpenOcrError(int i2) {
        this.value = i2;
    }

    public int getValue() {
        return this.value;
    }
}
