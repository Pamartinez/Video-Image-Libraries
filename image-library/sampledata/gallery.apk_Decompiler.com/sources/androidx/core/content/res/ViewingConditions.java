package androidx.core.content.res;

import com.samsung.android.gallery.support.utils.MapUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
final class ViewingConditions {
    static final ViewingConditions DEFAULT = make(CamUtils.WHITE_POINT_D65, (float) ((((double) CamUtils.yFromLStar(50.0f)) * 63.66197723675813d) / 100.0d), 50.0f, 2.0f, false);
    private final float mAw;
    private final float mC;
    private final float mFl;
    private final float mFlRoot;
    private final float mN;
    private final float mNbb;
    private final float mNc;
    private final float mNcb;
    private final float[] mRgbD;
    private final float mZ;

    private ViewingConditions(float f, float f5, float f8, float f10, float f11, float f12, float[] fArr, float f13, float f14, float f15) {
        this.mN = f;
        this.mAw = f5;
        this.mNbb = f8;
        this.mNcb = f10;
        this.mC = f11;
        this.mNc = f12;
        this.mRgbD = fArr;
        this.mFl = f13;
        this.mFlRoot = f14;
        this.mZ = f15;
    }

    public static ViewingConditions make(float[] fArr, float f, float f5, float f8, boolean z) {
        float lerp;
        float f10;
        float f11 = f;
        float[][] fArr2 = CamUtils.XYZ_TO_CAM16RGB;
        float f12 = fArr[0];
        float[] fArr3 = fArr2[0];
        float f13 = fArr[1];
        float f14 = fArr3[1] * f13;
        float f15 = fArr[2];
        float f16 = (fArr3[2] * f15) + f14 + (fArr3[0] * f12);
        float[] fArr4 = fArr2[1];
        float f17 = (fArr4[2] * f15) + (fArr4[1] * f13) + (fArr4[0] * f12);
        float[] fArr5 = fArr2[2];
        float f18 = (f15 * fArr5[2]) + (f13 * fArr5[1]) + (f12 * fArr5[0]);
        float f19 = (f8 / 10.0f) + 0.8f;
        if (((double) f19) >= 0.9d) {
            lerp = CamUtils.lerp(0.59f, 0.69f, (f19 - 0.9f) * 10.0f);
        } else {
            lerp = CamUtils.lerp(0.525f, 0.59f, (f19 - 0.8f) * 10.0f);
        }
        float f20 = lerp;
        if (z) {
            f10 = 1.0f;
        } else {
            f10 = (1.0f - (((float) Math.exp((double) (((-f11) - 42.0f) / 92.0f))) * 0.2777778f)) * f19;
        }
        double d = (double) f10;
        if (d > 1.0d) {
            f10 = 1.0f;
        } else if (d < MapUtil.INVALID_LOCATION) {
            f10 = 0.0f;
        }
        float[] fArr6 = {(((100.0f / f16) * f10) + 1.0f) - f10, (((100.0f / f17) * f10) + 1.0f) - f10, (((100.0f / f18) * f10) + 1.0f) - f10};
        float f21 = 1.0f / ((5.0f * f11) + 1.0f);
        float f22 = f21 * f21 * f21 * f21;
        float f23 = 1.0f - f22;
        float cbrt = (0.1f * f23 * f23 * ((float) Math.cbrt(((double) f11) * 5.0d))) + (f22 * f11);
        float yFromLStar = CamUtils.yFromLStar(f5) / fArr[1];
        double d2 = (double) yFromLStar;
        float f24 = f19;
        float sqrt = ((float) Math.sqrt(d2)) + 1.48f;
        float pow = 0.725f / ((float) Math.pow(d2, 0.2d));
        float[] fArr7 = {(float) Math.pow(((double) ((fArr6[0] * cbrt) * f16)) / 100.0d, 0.42d), (float) Math.pow(((double) ((fArr6[1] * cbrt) * f17)) / 100.0d, 0.42d), (float) Math.pow(((double) ((fArr6[2] * cbrt) * f18)) / 100.0d, 0.42d)};
        float f25 = fArr7[0];
        float f26 = (f25 * 400.0f) / (f25 + 27.13f);
        float f27 = fArr7[1];
        float f28 = (f27 * 400.0f) / (f27 + 27.13f);
        float f29 = fArr7[2];
        float[] fArr8 = {f26, f28, (400.0f * f29) / (f29 + 27.13f)};
        return new ViewingConditions(yFromLStar, ((fArr8[2] * 0.05f) + (fArr8[0] * 2.0f) + fArr8[1]) * pow, pow, pow, f20, f24, fArr6, cbrt, (float) Math.pow((double) cbrt, 0.25d), sqrt);
    }

    public float getAw() {
        return this.mAw;
    }

    public float getC() {
        return this.mC;
    }

    public float getFl() {
        return this.mFl;
    }

    public float getFlRoot() {
        return this.mFlRoot;
    }

    public float getN() {
        return this.mN;
    }

    public float getNbb() {
        return this.mNbb;
    }

    public float getNc() {
        return this.mNc;
    }

    public float getNcb() {
        return this.mNcb;
    }

    public float[] getRgbD() {
        return this.mRgbD;
    }

    public float getZ() {
        return this.mZ;
    }
}
