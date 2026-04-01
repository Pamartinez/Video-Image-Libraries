package com.samsung.android.gallery.support.utils;

import android.util.Log;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MathUtils {
    public static float clamp(float f, float f5, float f8) {
        if (f > f8) {
            return f8;
        }
        return Math.max(f, f5);
    }

    public static int compare(float f, float f5) {
        return ((int) (f * 10000.0f)) - ((int) (f5 * 10000.0f));
    }

    public static boolean equals(float f, float f5) {
        return Math.abs(Math.round(f * 10.0f) - Math.round(f5 * 10.0f)) <= 1;
    }

    public static float getRatio(int i2, int i7) {
        if (i2 == 0 || i7 == 0) {
            return -1.0f;
        }
        if (i2 > i7) {
            return ((float) i2) / ((float) i7);
        }
        return ((float) i7) / ((float) i2);
    }

    public static int prevPowerOf2(int i2) {
        if (i2 > 0) {
            return Integer.highestOneBit(i2);
        }
        Log.w("MathUtils", "prevPowerOf2 throw IllegalArgumentException n[" + i2 + "]");
        throw new IllegalArgumentException();
    }

    public static float roundToDecimalPlace(double d, double d2) {
        float pow = (float) Math.pow(10.0d, d2 - 1.0d);
        return ((float) Math.round(d * ((double) pow))) / pow;
    }

    public static <R, S> int compare(Map<? extends R, ? extends S> map, Map<? extends R, ? extends S> map2) {
        if (map != null && map2 != null) {
            int size = map2.size() - map.size();
            if (size != 0) {
                return size > 0 ? 1 : -1;
            }
            for (Map.Entry next : map.entrySet()) {
                if (!next.getValue().equals(map2.get(next.getKey()))) {
                    return 2;
                }
            }
            return 0;
        } else if (map == map2) {
            return 0;
        } else {
            return map == null ? 1 : -1;
        }
    }

    public static boolean equals(float f, float f5, float f8) {
        return Math.abs(f - f5) < f8;
    }

    public static boolean equals(double d, double d2, double d3) {
        return Math.abs(d - d2) < d3;
    }
}
