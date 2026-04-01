package androidx.core.content.res;

import androidx.core.graphics.ColorUtils;
import com.samsung.android.gallery.support.utils.MapUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CamColor {
    private final float mAstar;
    private final float mBstar;
    private final float mChroma;
    private final float mHue;
    private final float mJ;
    private final float mJstar;
    private final float mM;
    private final float mQ;
    private final float mS;

    public CamColor(float f, float f5, float f8, float f10, float f11, float f12, float f13, float f14, float f15) {
        this.mHue = f;
        this.mChroma = f5;
        this.mJ = f8;
        this.mQ = f10;
        this.mM = f11;
        this.mS = f12;
        this.mJstar = f13;
        this.mAstar = f14;
        this.mBstar = f15;
    }

    private static CamColor findCamByJ(float f, float f5, float f8) {
        float f10 = 100.0f;
        float f11 = 1000.0f;
        float f12 = 0.0f;
        CamColor camColor = null;
        float f13 = 1000.0f;
        while (Math.abs(f12 - f10) > 0.01f) {
            float f14 = ((f10 - f12) / 2.0f) + f12;
            int viewedInSrgb = fromJch(f14, f5, f).viewedInSrgb();
            float lStarFromInt = CamUtils.lStarFromInt(viewedInSrgb);
            float abs = Math.abs(f8 - lStarFromInt);
            if (abs < 0.2f) {
                CamColor fromColor = fromColor(viewedInSrgb);
                float distance = fromColor.distance(fromJch(fromColor.getJ(), fromColor.getChroma(), f));
                if (distance <= 1.0f) {
                    camColor = fromColor;
                    f11 = abs;
                    f13 = distance;
                }
            }
            if (f11 == 0.0f && f13 == 0.0f) {
                return camColor;
            }
            if (lStarFromInt < f8) {
                f12 = f14;
            } else {
                f10 = f14;
            }
        }
        return camColor;
    }

    public static CamColor fromColor(int i2) {
        float[] fArr = new float[7];
        float[] fArr2 = new float[3];
        fromColorInViewingConditions(i2, ViewingConditions.DEFAULT, fArr, fArr2);
        return new CamColor(fArr2[0], fArr2[1], fArr[0], fArr[1], fArr[2], fArr[3], fArr[4], fArr[5], fArr[6]);
    }

    public static void fromColorInViewingConditions(int i2, ViewingConditions viewingConditions, float[] fArr, float[] fArr2) {
        float f;
        float[] fArr3 = fArr2;
        CamUtils.xyzFromInt(i2, fArr3);
        float[][] fArr4 = CamUtils.XYZ_TO_CAM16RGB;
        float f5 = fArr3[0];
        float[] fArr5 = fArr4[0];
        float f8 = fArr3[1];
        float f10 = fArr5[1] * f8;
        float f11 = fArr3[2];
        float f12 = (fArr5[2] * f11) + f10 + (fArr5[0] * f5);
        float[] fArr6 = fArr4[1];
        float f13 = fArr6[1] * f8;
        float f14 = fArr6[2] * f11;
        float[] fArr7 = fArr4[2];
        float f15 = f5 * fArr7[0];
        float f16 = f11 * fArr7[2];
        float f17 = viewingConditions.getRgbD()[0] * f12;
        float f18 = viewingConditions.getRgbD()[1] * (f14 + f13 + (fArr6[0] * f5));
        float f19 = viewingConditions.getRgbD()[2] * (f16 + (f8 * fArr7[1]) + f15);
        float pow = (float) Math.pow(((double) (Math.abs(f17) * viewingConditions.getFl())) / 100.0d, 0.42d);
        float pow2 = (float) Math.pow(((double) (Math.abs(f18) * viewingConditions.getFl())) / 100.0d, 0.42d);
        float pow3 = (float) Math.pow(((double) (Math.abs(f19) * viewingConditions.getFl())) / 100.0d, 0.42d);
        float signum = ((Math.signum(f17) * 400.0f) * pow) / (pow + 27.13f);
        float signum2 = ((Math.signum(f18) * 400.0f) * pow2) / (pow2 + 27.13f);
        float signum3 = ((Math.signum(f19) * 400.0f) * pow3) / (pow3 + 27.13f);
        double d = (double) signum3;
        float f20 = ((float) (((((double) signum2) * -12.0d) + (((double) signum) * 11.0d)) + d)) / 11.0f;
        float f21 = ((float) (((double) (signum + signum2)) - (d * 2.0d))) / 9.0f;
        float f22 = signum2 * 20.0f;
        float f23 = ((21.0f * signum3) + ((signum * 20.0f) + f22)) / 20.0f;
        float f24 = (((signum * 40.0f) + f22) + signum3) / 20.0f;
        float atan2 = (((float) Math.atan2((double) f21, (double) f20)) * 180.0f) / 3.1415927f;
        if (atan2 < 0.0f) {
            atan2 += 360.0f;
        } else if (atan2 >= 360.0f) {
            atan2 -= 360.0f;
        }
        float f25 = (3.1415927f * atan2) / 180.0f;
        float f26 = f23;
        float pow4 = ((float) Math.pow((double) ((f24 * viewingConditions.getNbb()) / viewingConditions.getAw()), (double) (viewingConditions.getC() * viewingConditions.getZ()))) * 100.0f;
        float f27 = f20;
        float aw = (viewingConditions.getAw() + 4.0f) * (4.0f / viewingConditions.getC()) * ((float) Math.sqrt((double) (pow4 / 100.0f))) * viewingConditions.getFlRoot();
        if (((double) atan2) < 20.14d) {
            f = atan2 + 360.0f;
        } else {
            f = atan2;
        }
        float pow5 = ((float) Math.pow(1.64d - Math.pow(0.29d, (double) viewingConditions.getN()), 0.73d)) * ((float) Math.pow((double) ((((((((float) (Math.cos(((((double) f) * 3.141592653589793d) / 180.0d) + 2.0d) + 3.8d)) * 0.25f) * 3846.1538f) * viewingConditions.getNc()) * viewingConditions.getNcb()) * ((float) Math.sqrt((double) ((f21 * f21) + (f27 * f27))))) / (f26 + 0.305f)), 0.9d));
        float sqrt = ((float) Math.sqrt(((double) pow4) / 100.0d)) * pow5;
        float flRoot = viewingConditions.getFlRoot() * sqrt;
        float sqrt2 = ((float) Math.sqrt((double) ((pow5 * viewingConditions.getC()) / (viewingConditions.getAw() + 4.0f)))) * 50.0f;
        float f28 = (1.7f * pow4) / ((0.007f * pow4) + 1.0f);
        float log = ((float) Math.log((double) ((0.0228f * flRoot) + 1.0f))) * 43.85965f;
        double d2 = (double) f25;
        float cos = ((float) Math.cos(d2)) * log;
        float sin = log * ((float) Math.sin(d2));
        fArr3[0] = atan2;
        fArr3[1] = sqrt;
        if (fArr != null) {
            fArr[0] = pow4;
            fArr[1] = aw;
            fArr[2] = flRoot;
            fArr[3] = sqrt2;
            fArr[4] = f28;
            fArr[5] = cos;
            fArr[6] = sin;
        }
    }

    private static CamColor fromJch(float f, float f5, float f8) {
        return fromJchInFrame(f, f5, f8, ViewingConditions.DEFAULT);
    }

    private static CamColor fromJchInFrame(float f, float f5, float f8, ViewingConditions viewingConditions) {
        double d = ((double) f) / 100.0d;
        float aw = (viewingConditions.getAw() + 4.0f) * (4.0f / viewingConditions.getC()) * ((float) Math.sqrt(d)) * viewingConditions.getFlRoot();
        float flRoot = viewingConditions.getFlRoot() * f5;
        float sqrt = ((float) Math.sqrt((double) (((f5 / ((float) Math.sqrt(d))) * viewingConditions.getC()) / (viewingConditions.getAw() + 4.0f)))) * 50.0f;
        float f10 = (1.7f * f) / ((0.007f * f) + 1.0f);
        float log = ((float) Math.log((((double) flRoot) * 0.0228d) + 1.0d)) * 43.85965f;
        double d2 = (double) ((3.1415927f * f8) / 180.0f);
        float sin = log * ((float) Math.sin(d2));
        float f11 = f;
        float cos = ((float) Math.cos(d2)) * log;
        float f12 = aw;
        return new CamColor(f8, f5, f11, f12, flRoot, sqrt, f10, cos, sin);
    }

    public static int toColor(float f, float f5, float f8) {
        return toColor(f, f5, f8, ViewingConditions.DEFAULT);
    }

    public float distance(CamColor camColor) {
        float jStar = getJStar() - camColor.getJStar();
        float aStar = getAStar() - camColor.getAStar();
        float bStar = getBStar() - camColor.getBStar();
        return (float) (Math.pow(Math.sqrt((double) ((bStar * bStar) + (aStar * aStar) + (jStar * jStar))), 0.63d) * 1.41d);
    }

    public float getAStar() {
        return this.mAstar;
    }

    public float getBStar() {
        return this.mBstar;
    }

    public float getChroma() {
        return this.mChroma;
    }

    public float getHue() {
        return this.mHue;
    }

    public float getJ() {
        return this.mJ;
    }

    public float getJStar() {
        return this.mJstar;
    }

    public int viewed(ViewingConditions viewingConditions) {
        float f;
        if (((double) getChroma()) == MapUtil.INVALID_LOCATION || ((double) getJ()) == MapUtil.INVALID_LOCATION) {
            f = 0.0f;
        } else {
            f = getChroma() / ((float) Math.sqrt(((double) getJ()) / 100.0d));
        }
        float pow = (float) Math.pow(((double) f) / Math.pow(1.64d - Math.pow(0.29d, (double) viewingConditions.getN()), 0.73d), 1.1111111111111112d);
        double hue = (double) ((getHue() * 3.1415927f) / 180.0f);
        float aw = viewingConditions.getAw() * ((float) Math.pow(((double) getJ()) / 100.0d, (1.0d / ((double) viewingConditions.getC())) / ((double) viewingConditions.getZ())));
        float cos = ((float) (Math.cos(2.0d + hue) + 3.8d)) * 0.25f * 3846.1538f * viewingConditions.getNc() * viewingConditions.getNcb();
        float nbb = aw / viewingConditions.getNbb();
        float sin = (float) Math.sin(hue);
        float cos2 = (float) Math.cos(hue);
        float f5 = 11.0f * pow * cos2;
        float f8 = (((0.305f + nbb) * 23.0f) * pow) / (((pow * 108.0f) * sin) + (f5 + (cos * 23.0f)));
        float f10 = cos2 * f8;
        float f11 = f8 * sin;
        float f12 = nbb * 460.0f;
        float f13 = ((288.0f * f11) + ((451.0f * f10) + f12)) / 1403.0f;
        float f14 = ((f12 - (891.0f * f10)) - (261.0f * f11)) / 1403.0f;
        float f15 = ((f12 - (f10 * 220.0f)) - (f11 * 6300.0f)) / 1403.0f;
        float max = (float) Math.max(MapUtil.INVALID_LOCATION, (((double) Math.abs(f13)) * 27.13d) / (400.0d - ((double) Math.abs(f13))));
        float fl = (100.0f / viewingConditions.getFl()) * Math.signum(f13) * ((float) Math.pow((double) max, 2.380952380952381d));
        float fl2 = (100.0f / viewingConditions.getFl()) * Math.signum(f14) * ((float) Math.pow((double) ((float) Math.max(MapUtil.INVALID_LOCATION, (((double) Math.abs(f14)) * 27.13d) / (400.0d - ((double) Math.abs(f14))))), 2.380952380952381d));
        float fl3 = (100.0f / viewingConditions.getFl()) * Math.signum(f15) * ((float) Math.pow((double) ((float) Math.max(MapUtil.INVALID_LOCATION, (((double) Math.abs(f15)) * 27.13d) / (400.0d - ((double) Math.abs(f15))))), 2.380952380952381d));
        float f16 = fl / viewingConditions.getRgbD()[0];
        float f17 = fl2 / viewingConditions.getRgbD()[1];
        float f18 = fl3 / viewingConditions.getRgbD()[2];
        float[][] fArr = CamUtils.CAM16RGB_TO_XYZ;
        float[] fArr2 = fArr[0];
        float f19 = fArr2[1] * f17;
        float f20 = (fArr2[2] * f18) + f19 + (fArr2[0] * f16);
        float[] fArr3 = fArr[1];
        float f21 = fArr3[1] * f17;
        float f22 = fArr3[2] * f18;
        float[] fArr4 = fArr[2];
        float f23 = f16 * fArr4[0];
        return ColorUtils.XYZToColor((double) f20, (double) (f22 + f21 + (fArr3[0] * f16)), (double) ((f18 * fArr4[2]) + (f17 * fArr4[1]) + f23));
    }

    public int viewedInSrgb() {
        return viewed(ViewingConditions.DEFAULT);
    }

    public static int toColor(float f, float f5, float f8, ViewingConditions viewingConditions) {
        float f10;
        if (((double) f5) < 1.0d || ((double) Math.round(f8)) <= MapUtil.INVALID_LOCATION || ((double) Math.round(f8)) >= 100.0d) {
            return CamUtils.intFromLStar(f8);
        }
        if (f < 0.0f) {
            f10 = 0.0f;
        } else {
            f10 = Math.min(360.0f, f);
        }
        CamColor camColor = null;
        boolean z = true;
        float f11 = 0.0f;
        float f12 = f5;
        while (Math.abs(f11 - f5) >= 0.4f) {
            CamColor findCamByJ = findCamByJ(f10, f12, f8);
            if (!z) {
                if (findCamByJ == null) {
                    f5 = f12;
                } else {
                    f11 = f12;
                    camColor = findCamByJ;
                }
                f12 = ((f5 - f11) / 2.0f) + f11;
            } else if (findCamByJ != null) {
                return findCamByJ.viewed(viewingConditions);
            } else {
                f12 = ((f5 - f11) / 2.0f) + f11;
                z = false;
            }
        }
        if (camColor == null) {
            return CamUtils.intFromLStar(f8);
        }
        return camColor.viewed(viewingConditions);
    }
}
