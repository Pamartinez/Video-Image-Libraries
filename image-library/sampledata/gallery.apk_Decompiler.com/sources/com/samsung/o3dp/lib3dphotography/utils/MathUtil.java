package com.samsung.o3dp.lib3dphotography.utils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MathUtil {
    public static float clamp(float f, float f5, float f8) {
        return Math.min(f8, Math.max(f, f5));
    }

    public static float normalizedFraction(float f, float f5, float f8) {
        if (f <= f5) {
            return 0.0f;
        }
        if (f >= f8) {
            return 1.0f;
        }
        return (f - f5) / (f8 - f5);
    }

    public static float round(float f, float f5) {
        if (!Float.isNaN(f) && !Float.isInfinite(f)) {
            return ((float) Math.round(f * f5)) / f5;
        }
        throw new IllegalArgumentException("Invalid input value: " + f);
    }
}
