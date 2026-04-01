package com.samsung.android.sum.core.types;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum HwUnit implements NumericEnum {
    NONE(-1),
    CPU(0),
    GPU(1),
    NPU(2),
    DSP(3),
    IP(4);
    
    private final int value;

    private HwUnit(int i2) {
        this.value = i2;
    }

    public static HwUnit from(int i2) {
        return (HwUnit) NumericEnum.fromValue(HwUnit.class, i2);
    }

    public int getValue() {
        return this.value;
    }

    public String stringfy() {
        return name() + NumericEnum.SEP + this.value;
    }
}
