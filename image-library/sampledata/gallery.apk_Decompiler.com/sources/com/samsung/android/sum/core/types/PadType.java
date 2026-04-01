package com.samsung.android.sum.core.types;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum PadType implements NumericEnum {
    NONE(0),
    OVERLAP(1);
    
    private final int value;

    private PadType(int i2) {
        this.value = i2;
    }

    public int getValue() {
        return this.value;
    }

    public String stringfy() {
        return name() + NumericEnum.SEP + this.value;
    }
}
