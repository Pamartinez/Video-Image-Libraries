package com.samsung.android.sum.core.buffer;

import com.samsung.android.sum.core.types.NumericEnum;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum BufferType implements NumericEnum {
    NONE(0),
    HEAP(1),
    SHARED(2),
    PROPRIETARY(3),
    LIST(4);
    
    private final int value;

    private BufferType(int i2) {
        this.value = i2;
    }

    public static BufferType from(int i2) {
        return (BufferType) NumericEnum.fromValue(BufferType.class, i2);
    }

    public int getValue() {
        return this.value;
    }

    public String stringfy() {
        return name() + NumericEnum.SEP + this.value;
    }
}
