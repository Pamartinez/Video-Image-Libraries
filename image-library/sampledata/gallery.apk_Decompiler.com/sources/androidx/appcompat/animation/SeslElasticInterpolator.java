package androidx.appcompat.animation;

import android.view.animation.Interpolator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SeslElasticInterpolator implements Interpolator {
    private float mAmplitude;
    private float mPeriod;

    public SeslElasticInterpolator(float f, float f5) {
        this.mAmplitude = f;
        this.mPeriod = f5;
    }

    private float out(float f, float f5, float f8) {
        float f10;
        if (f == 0.0f) {
            return 0.0f;
        }
        if (f >= 1.0f) {
            return 1.0f;
        }
        if (f8 == 0.0f) {
            f8 = 0.3f;
        }
        if (f5 == 0.0f || f5 < 1.0f) {
            f10 = f8 / 4.0f;
            f5 = 1.0f;
        } else {
            f10 = (float) (Math.asin((double) (1.0f / f5)) * (((double) f8) / 6.283185307179586d));
        }
        return (float) ((Math.sin((((double) (f - f10)) * 6.283185307179586d) / ((double) f8)) * Math.pow(2.0d, (double) (-10.0f * f)) * ((double) f5)) + 1.0d);
    }

    public float getInterpolation(float f) {
        return out(f, this.mAmplitude, this.mPeriod);
    }
}
