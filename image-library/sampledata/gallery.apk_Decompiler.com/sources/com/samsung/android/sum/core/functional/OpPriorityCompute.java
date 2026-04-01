package com.samsung.android.sum.core.functional;

import com.samsung.android.sum.core.format.Shape;
import com.samsung.android.sum.core.types.ColorFormat;
import com.samsung.android.sum.core.types.DataType;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface OpPriorityCompute {
    public static final float FIRST_CLASS = Float.MIN_VALUE;
    public static final float FIRST_OF_ALL = 0.0f;
    public static final float LAST_CLASS = Float.MAX_VALUE;
    public static final float NOT_APPLICABLE = -1.0f;

    float compute(Shape shape, Shape shape2);

    float compute(ColorFormat colorFormat, ColorFormat colorFormat2);

    float compute(DataType dataType, DataType dataType2);
}
