package com.samsung.android.gallery.widget.listview.pinch.v3;

import android.animation.ValueAnimator;
import android.widget.ImageView;
import com.samsung.android.gallery.widget.PinchMatrix;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PinchMatrixAnimator extends PropertyAnimator {
    private float[] mCurrentValues;
    private final PinchMatrix mFromMatrix;
    private float[] mFromValues;
    private final PinchMatrix mToMatrix;
    private float[] mToValues;

    public PinchMatrixAnimator(ImageView imageView, PinchMatrix pinchMatrix, PinchMatrix pinchMatrix2) {
        super(imageView);
        this.mFromMatrix = pinchMatrix;
        this.mToMatrix = pinchMatrix2;
    }

    private void initMatrix() {
        if (this.mCurrentValues == null) {
            float[] fArr = new float[9];
            this.mFromValues = fArr;
            this.mFromMatrix.getValues(fArr);
            float[] fArr2 = new float[9];
            this.mToValues = fArr2;
            this.mToMatrix.getValues(fArr2);
            this.mCurrentValues = new float[9];
        }
    }

    private boolean isReady() {
        if (!this.mFromMatrix.isReady() || !this.mToMatrix.isReady()) {
            return false;
        }
        return true;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        super.onAnimationUpdate(valueAnimator);
        if (isReady()) {
            initMatrix();
            for (int i2 = 0; i2 < 9; i2++) {
                float[] fArr = this.mCurrentValues;
                float f = this.mFromValues[i2];
                fArr[i2] = C0212a.a(this.mToValues[i2], f, this.mCurrentValue, f);
            }
            this.mToMatrix.setValues(this.mCurrentValues);
        }
    }
}
