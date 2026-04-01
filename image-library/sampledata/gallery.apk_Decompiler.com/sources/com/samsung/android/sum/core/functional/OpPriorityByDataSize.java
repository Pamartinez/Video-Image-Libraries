package com.samsung.android.sum.core.functional;

import com.samsung.android.sum.core.format.Shape;
import com.samsung.android.sum.core.types.ColorFormat;
import com.samsung.android.sum.core.types.DataType;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OpPriorityByDataSize implements OpPriorityCompute {
    public float compute(Shape shape, Shape shape2) {
        return ((float) shape2.getDimension()) / ((float) shape.getDimension());
    }

    public float compute(ColorFormat colorFormat, ColorFormat colorFormat2) {
        return colorFormat2.bytePerPixel() / colorFormat.bytePerPixel();
    }

    public float compute(DataType dataType, DataType dataType2) {
        return dataType2.size() / dataType.size();
    }
}
