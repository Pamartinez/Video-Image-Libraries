package com.samsung.android.sum.core.types;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum SplitType implements NumericEnum {
    NONE(0),
    CHANNELS(1),
    PLANES(2),
    ALPHA(3),
    TILE(4);
    
    private final int value;

    private SplitType(int i2) {
        this.value = i2;
    }

    public int getValue() {
        return this.value;
    }

    public String stringfy() {
        return name() + NumericEnum.SEP + this.value;
    }
}
