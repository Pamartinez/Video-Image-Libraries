package com.samsung.android.gallery.widget.animator;

import android.animation.ValueAnimator;
import android.graphics.Matrix;
import android.widget.ImageView;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ImageMatrixAnimator extends PropertyAnimator {
    private final float[] mCurrentValues = new float[9];
    private final float[] mFromValues;
    private final float[] mToValues;

    public ImageMatrixAnimator(ImageView imageView, Matrix matrix, Matrix matrix2) {
        super(imageView);
        float[] fArr = new float[9];
        this.mFromValues = fArr;
        float[] fArr2 = new float[9];
        this.mToValues = fArr2;
        setFloatValues(new float[]{0.0f, 1.0f});
        matrix.getValues(fArr);
        matrix2.getValues(fArr2);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        super.onAnimationUpdate(valueAnimator);
        for (int i2 = 0; i2 < 9; i2++) {
            float[] fArr = this.mCurrentValues;
            float f = this.mFromValues[i2];
            fArr[i2] = C0212a.a(this.mToValues[i2], f, this.mCurrentValue, f);
        }
        ((ImageView) this.mView).getImageMatrix().setValues(this.mCurrentValues);
        this.mView.invalidate();
    }
}
