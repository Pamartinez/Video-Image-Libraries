package androidx.core.content.res;

import android.graphics.Color;
import androidx.core.graphics.ColorUtils;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class CamUtils {
    static final float[][] CAM16RGB_TO_XYZ = {new float[]{1.8620678f, -1.0112547f, 0.14918678f}, new float[]{0.38752654f, 0.62144744f, -0.00897398f}, new float[]{-0.0158415f, -0.03412294f, 1.0499644f}};
    static final float[][] SRGB_TO_XYZ = {new float[]{0.41233894f, 0.35762063f, 0.18051042f}, new float[]{0.2126f, 0.7152f, 0.0722f}, new float[]{0.01932141f, 0.11916382f, 0.9503448f}};
    static final float[] WHITE_POINT_D65 = {95.047f, 100.0f, 108.883f};
    static final float[][] XYZ_TO_CAM16RGB = {new float[]{0.401288f, 0.650173f, -0.051461f}, new float[]{-0.250268f, 1.204414f, 0.045854f}, new float[]{-0.002079f, 0.048952f, 0.953127f}};

    public static int intFromLStar(float f) {
        float f5;
        boolean z;
        float f8;
        if (f < 1.0f) {
            return -16777216;
        }
        if (f > 99.0f) {
            return -1;
        }
        float f10 = (f + 16.0f) / 116.0f;
        if (f > 8.0f) {
            f5 = f10 * f10 * f10;
        } else {
            f5 = f / 903.2963f;
        }
        float f11 = f10 * f10 * f10;
        if (f11 > 0.008856452f) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            f8 = f11;
        } else {
            f8 = ((f10 * 116.0f) - 16.0f) / 903.2963f;
        }
        if (!z) {
            f11 = ((f10 * 116.0f) - 16.0f) / 903.2963f;
        }
        float[] fArr = WHITE_POINT_D65;
        return ColorUtils.XYZToColor((double) (f8 * fArr[0]), (double) (f5 * fArr[1]), (double) (f11 * fArr[2]));
    }

    public static float lStarFromInt(int i2) {
        return lStarFromY(yFromInt(i2));
    }

    public static float lStarFromY(float f) {
        float f5 = f / 100.0f;
        if (f5 <= 0.008856452f) {
            return f5 * 903.2963f;
        }
        return (((float) Math.cbrt((double) f5)) * 116.0f) - 16.0f;
    }

    public static float lerp(float f, float f5, float f8) {
        return C0212a.a(f5, f, f8, f);
    }

    public static float linearized(int i2) {
        float pow;
        float f = ((float) i2) / 255.0f;
        if (f <= 0.04045f) {
            pow = f / 12.92f;
        } else {
            pow = (float) Math.pow((double) ((f + 0.055f) / 1.055f), 2.4000000953674316d);
        }
        return pow * 100.0f;
    }

    public static void xyzFromInt(int i2, float[] fArr) {
        float linearized = linearized(Color.red(i2));
        float linearized2 = linearized(Color.green(i2));
        float linearized3 = linearized(Color.blue(i2));
        float[][] fArr2 = SRGB_TO_XYZ;
        float[] fArr3 = fArr2[0];
        float f = fArr3[1] * linearized2;
        fArr[0] = (fArr3[2] * linearized3) + f + (fArr3[0] * linearized);
        float[] fArr4 = fArr2[1];
        float f5 = fArr4[1] * linearized2;
        fArr[1] = (fArr4[2] * linearized3) + f5 + (fArr4[0] * linearized);
        float[] fArr5 = fArr2[2];
        fArr[2] = (linearized3 * fArr5[2]) + (linearized2 * fArr5[1]) + (linearized * fArr5[0]);
    }

    public static float yFromInt(int i2) {
        float linearized = linearized(Color.red(i2));
        float linearized2 = linearized(Color.green(i2));
        float linearized3 = linearized(Color.blue(i2));
        float[] fArr = SRGB_TO_XYZ[1];
        return (linearized3 * fArr[2]) + (linearized2 * fArr[1]) + (linearized * fArr[0]);
    }

    public static float yFromLStar(float f) {
        float f5;
        if (f > 8.0f) {
            f5 = (float) Math.pow((((double) f) + 16.0d) / 116.0d, 3.0d);
        } else {
            f5 = f / 903.2963f;
        }
        return f5 * 100.0f;
    }
}
