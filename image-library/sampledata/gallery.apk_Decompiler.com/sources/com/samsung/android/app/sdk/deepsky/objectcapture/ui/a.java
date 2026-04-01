package com.samsung.android.app.sdk.deepsky.objectcapture.ui;

import android.animation.ValueAnimator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class a implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ int d;
    public final /* synthetic */ MaskedImageView e;

    public /* synthetic */ a(MaskedImageView maskedImageView, int i2) {
        this.d = i2;
        this.e = maskedImageView;
    }

    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i2 = this.d;
        MaskedImageView maskedImageView = this.e;
        switch (i2) {
            case 0:
                MaskedImageView.getInnerCircleAnimator$lambda$8$lambda$7(maskedImageView, valueAnimator);
                return;
            case 1:
                MaskedImageView.getOuterCircleAnimator$lambda$6$lambda$5(maskedImageView, valueAnimator);
                return;
            default:
                MaskedImageView.initViewUpdateAnimator$lambda$3(maskedImageView, valueAnimator);
                return;
        }
    }
}
