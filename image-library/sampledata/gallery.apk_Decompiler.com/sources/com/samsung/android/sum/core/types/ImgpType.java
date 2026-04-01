package com.samsung.android.sum.core.types;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum ImgpType implements NumericEnum {
    ANY(0),
    RESIZE(1),
    CVT_COLOR(2),
    CVT_DATA(3),
    CVT_GAMUT(4),
    CVT_HDR2SDR(5),
    ROTATE(6),
    CROP(7),
    SPLIT(8),
    MERGE(9),
    QUALITY_MEASURE(10),
    DECODE(11),
    ENCODE(12),
    ENCODE_HDR(13),
    FLIP(14);
    
    private final int value;

    private ImgpType(int i2) {
        this.value = i2;
    }

    public int getValue() {
        return this.value;
    }

    public String stringfy() {
        return name() + NumericEnum.SEP + this.value;
    }
}
