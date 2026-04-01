package com.samsung.android.gallery.support.utils;

import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class UnsafeCast {
    public static boolean isInvalid(double d) {
        return d == Double.MIN_VALUE;
    }

    public static <T> ArrayList<T> toArrayList(Object obj) {
        try {
            return (ArrayList) obj;
        } catch (ClassCastException e) {
            Log.e("UnsafeCast", "toArrayList failed e=" + e.getMessage());
            return null;
        }
    }

    public static boolean toBoolean(String str) {
        return SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE.equalsIgnoreCase(str);
    }

    public static double toDouble(String str) {
        return toDouble(str, Double.MIN_VALUE);
    }

    public static float toFloat(String str) {
        return toFloat(str, Float.MIN_VALUE);
    }

    public static int toInt(String str) {
        return toInt(str, Integer.MIN_VALUE);
    }

    public static long toLong(String str) {
        return toLong(str, Long.MIN_VALUE);
    }

    public static boolean isInvalid(float f) {
        return f == Float.MIN_VALUE;
    }

    public static boolean toBoolean(String str, boolean z) {
        return str != null ? toBoolean(str) : z;
    }

    public static double toDouble(String str, double d) {
        if (str != null) {
            try {
                if (str.length() > 0) {
                    return Double.parseDouble(str);
                }
            } catch (NumberFormatException e) {
                Log.e("UnsafeCast", "toDouble failed e=" + e.getMessage());
            }
        }
        return d;
    }

    public static float toFloat(String str, float f) {
        if (str != null) {
            try {
                if (str.length() > 0) {
                    return Float.parseFloat(str);
                }
            } catch (NumberFormatException e) {
                Log.e("UnsafeCast", "toFloat failed e=" + e.getMessage());
            }
        }
        return f;
    }

    public static int toInt(String str, int i2) {
        if (str != null) {
            try {
                if (str.length() > 0) {
                    return Integer.parseInt(str);
                }
            } catch (NumberFormatException e) {
                Log.e("UnsafeCast", "toInt failed e=" + e.getMessage());
            }
        }
        return i2;
    }

    public static long toLong(String str, long j2) {
        if (str != null) {
            try {
                if (str.length() > 0) {
                    return Long.parseLong(str);
                }
            } catch (NumberFormatException e) {
                Log.e("UnsafeCast", "toLong failed e=" + e.getMessage());
            }
        }
        return j2;
    }

    public static boolean isInvalid(int i2) {
        return i2 == Integer.MIN_VALUE;
    }
}
