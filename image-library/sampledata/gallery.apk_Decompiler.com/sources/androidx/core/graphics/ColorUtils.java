package androidx.core.graphics;

import android.graphics.Color;
import com.samsung.android.sdk.cover.ScoverState;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ColorUtils {
    private static final ThreadLocal<double[]> TEMP_ARRAY = new ThreadLocal<>();

    public static void RGBToXYZ(int i2, int i7, int i8, double[] dArr) {
        double pow;
        double pow2;
        double d;
        double[] dArr2 = dArr;
        if (dArr2.length == 3) {
            double d2 = ((double) i2) / 255.0d;
            if (d2 < 0.04045d) {
                pow = d2 / 12.92d;
            } else {
                pow = Math.pow((d2 + 0.055d) / 1.055d, 2.4d);
            }
            double d3 = ((double) i7) / 255.0d;
            if (d3 < 0.04045d) {
                pow2 = d3 / 12.92d;
            } else {
                pow2 = Math.pow((d3 + 0.055d) / 1.055d, 2.4d);
            }
            double d5 = ((double) i8) / 255.0d;
            if (d5 < 0.04045d) {
                d = d5 / 12.92d;
            } else {
                d = Math.pow((d5 + 0.055d) / 1.055d, 2.4d);
            }
            dArr2[0] = ((0.1805d * d) + (0.3576d * pow2) + (0.4124d * pow)) * 100.0d;
            dArr2[1] = ((0.0722d * d) + (0.7152d * pow2) + (0.2126d * pow)) * 100.0d;
            double d6 = d * 0.9505d;
            dArr2[2] = (d6 + (pow2 * 0.1192d) + (pow * 0.0193d)) * 100.0d;
            return;
        }
        throw new IllegalArgumentException("outXyz must have a length of 3.");
    }

    public static int XYZToColor(double d, double d2, double d3) {
        double d5;
        double d6;
        double d7;
        double d9 = ((-0.4986d * d3) + ((-1.5372d * d2) + (3.2406d * d))) / 100.0d;
        double d10 = ((0.0415d * d3) + ((1.8758d * d2) + (-0.9689d * d))) / 100.0d;
        double d11 = ((1.057d * d3) + ((-0.204d * d2) + (0.0557d * d))) / 100.0d;
        if (d9 > 0.0031308d) {
            d5 = (Math.pow(d9, 0.4166666666666667d) * 1.055d) - 0.055d;
        } else {
            d5 = d9 * 12.92d;
        }
        if (d10 > 0.0031308d) {
            d6 = (Math.pow(d10, 0.4166666666666667d) * 1.055d) - 0.055d;
        } else {
            d6 = d10 * 12.92d;
        }
        if (d11 > 0.0031308d) {
            d7 = (Math.pow(d11, 0.4166666666666667d) * 1.055d) - 0.055d;
        } else {
            d7 = d11 * 12.92d;
        }
        return Color.rgb(constrain((int) Math.round(d5 * 255.0d), 0, ScoverState.TYPE_NFC_SMART_COVER), constrain((int) Math.round(d6 * 255.0d), 0, ScoverState.TYPE_NFC_SMART_COVER), constrain((int) Math.round(d7 * 255.0d), 0, ScoverState.TYPE_NFC_SMART_COVER));
    }

    public static int blendARGB(int i2, int i7, float f) {
        float f5 = 1.0f - f;
        return Color.argb((int) ((((float) Color.alpha(i7)) * f) + (((float) Color.alpha(i2)) * f5)), (int) ((((float) Color.red(i7)) * f) + (((float) Color.red(i2)) * f5)), (int) ((((float) Color.green(i7)) * f) + (((float) Color.green(i2)) * f5)), (int) ((((float) Color.blue(i7)) * f) + (((float) Color.blue(i2)) * f5)));
    }

    public static double calculateLuminance(int i2) {
        double[] tempDouble3Array = getTempDouble3Array();
        colorToXYZ(i2, tempDouble3Array);
        return tempDouble3Array[1] / 100.0d;
    }

    public static void colorToXYZ(int i2, double[] dArr) {
        RGBToXYZ(Color.red(i2), Color.green(i2), Color.blue(i2), dArr);
    }

    private static int compositeAlpha(int i2, int i7) {
        return 255 - (((255 - i2) * (255 - i7)) / ScoverState.TYPE_NFC_SMART_COVER);
    }

    public static int compositeColors(int i2, int i7) {
        int alpha = Color.alpha(i7);
        int alpha2 = Color.alpha(i2);
        int compositeAlpha = compositeAlpha(alpha2, alpha);
        return Color.argb(compositeAlpha, compositeComponent(Color.red(i2), alpha2, Color.red(i7), alpha, compositeAlpha), compositeComponent(Color.green(i2), alpha2, Color.green(i7), alpha, compositeAlpha), compositeComponent(Color.blue(i2), alpha2, Color.blue(i7), alpha, compositeAlpha));
    }

    private static int compositeComponent(int i2, int i7, int i8, int i10, int i11) {
        if (i11 == 0) {
            return 0;
        }
        return (((255 - i7) * (i8 * i10)) + ((i2 * ScoverState.TYPE_NFC_SMART_COVER) * i7)) / (i11 * ScoverState.TYPE_NFC_SMART_COVER);
    }

    private static int constrain(int i2, int i7, int i8) {
        if (i2 < i7) {
            return i7;
        }
        return Math.min(i2, i8);
    }

    private static double[] getTempDouble3Array() {
        ThreadLocal<double[]> threadLocal = TEMP_ARRAY;
        double[] dArr = threadLocal.get();
        if (dArr != null) {
            return dArr;
        }
        double[] dArr2 = new double[3];
        threadLocal.set(dArr2);
        return dArr2;
    }

    public static int setAlphaComponent(int i2, int i7) {
        if (i7 >= 0 && i7 <= 255) {
            return (i2 & 16777215) | (i7 << 24);
        }
        throw new IllegalArgumentException("alpha must be between 0 and 255.");
    }
}
