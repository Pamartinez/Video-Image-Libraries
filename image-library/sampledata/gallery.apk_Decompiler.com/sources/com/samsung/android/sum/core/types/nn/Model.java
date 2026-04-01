package com.samsung.android.sum.core.types.nn;

import com.samsung.android.sum.core.types.NumericEnum;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum Model implements NumericEnum {
    NONE(0),
    IMAGE_UPSCALER_X4(1),
    MIRACLE_ESTIMATOR(2),
    MIRACLE_FILTER(3),
    OLD_PHOTO_ESTIMATOR(4),
    OLD_PHOTO_ENHANCER(5),
    OLD_PHOTO_FACE_ENHANCER(6),
    VIDEO_UPSCALER_X4(7);
    
    private final int value;

    private Model(int i2) {
        this.value = i2;
    }

    public static Model from(int i2) {
        return (Model) NumericEnum.fromValue(Model.class, i2);
    }

    public int getValue() {
        return this.value;
    }

    public String stringfy() {
        return name() + NumericEnum.SEP + this.value;
    }
}
