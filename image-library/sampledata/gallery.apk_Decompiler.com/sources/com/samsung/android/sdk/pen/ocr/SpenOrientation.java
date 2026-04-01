package com.samsung.android.sdk.pen.ocr;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
enum SpenOrientation {
    Rotation_0(240),
    Rotation_90(241),
    Rotation_180(242),
    Rotation_270(243);
    
    private final int value;

    private SpenOrientation(int i2) {
        this.value = i2;
    }

    public int getValue() {
        return this.value;
    }
}
