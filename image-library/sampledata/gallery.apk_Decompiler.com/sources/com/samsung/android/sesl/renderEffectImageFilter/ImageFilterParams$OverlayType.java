package com.samsung.android.sesl.renderEffectImageFilter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum ImageFilterParams$OverlayType {
    NORMAL(0),
    OVERLAY(1),
    MASK(2);
    
    private int type;

    private ImageFilterParams$OverlayType(int i2) {
        this.type = i2;
    }

    public int getNumericType() {
        return this.type;
    }
}
