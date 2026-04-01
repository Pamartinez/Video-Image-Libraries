package com.samsung.o3dp.lib3dphotography.utils;

import android.graphics.Color;
import android.graphics.ColorSpace;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ColorUtil {
    public static float[] convertColor(int i2) {
        long convert = Color.convert(i2, ColorSpace.get(ColorSpace.Named.SRGB));
        return new float[]{Color.red(convert), Color.green(convert), Color.blue(convert), Color.alpha(convert)};
    }
}
