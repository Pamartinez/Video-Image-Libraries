package androidx.interpolator.view.animation;

import android.view.animation.Interpolator;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class LookupTableInterpolator implements Interpolator {
    private final float mStepSize;
    private final float[] mValues;

    public LookupTableInterpolator(float[] fArr) {
        this.mValues = fArr;
        this.mStepSize = 1.0f / ((float) (fArr.length - 1));
    }

    public float getInterpolation(float f) {
        if (f >= 1.0f) {
            return 1.0f;
        }
        if (f <= 0.0f) {
            return 0.0f;
        }
        float[] fArr = this.mValues;
        int min = Math.min((int) (((float) (fArr.length - 1)) * f), fArr.length - 2);
        float f5 = this.mStepSize;
        float[] fArr2 = this.mValues;
        float f8 = fArr2[min];
        return C0212a.a(fArr2[min + 1], f8, (f - (((float) min) * f5)) / f5, f8);
    }
}
