package com.samsung.android.gallery.widget.animator;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.CheckBox;
import com.samsung.android.sdk.cover.ScoverState;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TintAnimator extends PropertyAnimator {
    private int[] mFrom;
    private int[] mTo;

    public TintAnimator(View view, int[] iArr, int[] iArr2) {
        super(view);
        setFloatValues(new float[]{0.0f, 1.0f});
        this.mFrom = iArr;
        this.mTo = iArr2;
    }

    private ColorFilter getColorFilter(int i2) {
        return new ColorMatrixColorFilter(new float[]{0.0f, 0.0f, 0.0f, 0.0f, (float) ((16711680 & i2) / 65535), 0.0f, 0.0f, 0.0f, 0.0f, (float) ((65280 & i2) / ScoverState.TYPE_NFC_SMART_COVER), 0.0f, 0.0f, 0.0f, 0.0f, (float) (i2 & ScoverState.TYPE_NFC_SMART_COVER), 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        super.onAnimationUpdate(valueAnimator);
        View view = this.mView;
        if (view instanceof CheckBox) {
            Drawable buttonDrawable = ((CheckBox) view).getButtonDrawable();
            if (buttonDrawable != null) {
                ArgbEvaluator argbEvaluator = new ArgbEvaluator();
                char c5 = !((CheckBox) this.mView).isChecked();
                buttonDrawable.setColorFilter(getColorFilter(((Integer) argbEvaluator.evaluate(this.mCurrentValue, Integer.valueOf(this.mFrom[c5]), Integer.valueOf(this.mTo[c5]))).intValue()));
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Support tint animation for Check only");
    }
}
