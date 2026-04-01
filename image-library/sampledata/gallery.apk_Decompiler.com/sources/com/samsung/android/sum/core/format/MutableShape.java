package com.samsung.android.sum.core.format;

import android.util.Pair;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MutableShape extends Shape {
    MutableShape scale(Pair<Float, Float> pair);

    MutableShape scale(Float f);

    MutableShape setBatch(int i2);

    MutableShape setChannels(int i2);

    MutableShape setCols(int i2);

    MutableShape setRows(int i2);

    Shape toShape();
}
