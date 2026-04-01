package com.samsung.android.gallery.app.ui.viewer2.slideshow.transformer;

import android.animation.Animator;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateDecelerateInterpolator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SShowPageTransformerFaceCircleUp extends SShowPageTransformerFadeInOut {
    private Animator mCircularRevealAnim;
    private final RectF mFaceRect;

    public SShowPageTransformerFaceCircleUp(RectF rectF, boolean z, int i2) {
        super(z, i2);
        this.mFaceRect = rectF;
    }

    public void doFadeIn(View view, float f) {
        if (checkRemain(f)) {
            view.setAlpha(0.0f);
            return;
        }
        view.setAlpha(1.0f - (Math.abs(f) * 2.0f));
        view.setScaleX(this.mScaleFactor);
        view.setScaleY(this.mScaleFactor);
        if (this.mCircularRevealAnim == null) {
            Animator createCircularReveal = ViewAnimationUtils.createCircularReveal(view, (int) this.mFaceRect.centerX(), (int) this.mFaceRect.centerY(), this.mFaceRect.width(), (float) Math.hypot((double) (((float) view.getWidth()) * this.mScaleFactor), (double) (((float) view.getHeight()) * this.mScaleFactor)));
            this.mCircularRevealAnim = createCircularReveal;
            createCircularReveal.setDuration(2500);
            this.mCircularRevealAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        }
        if (!this.mCircularRevealAnim.isStarted()) {
            this.mCircularRevealAnim.start();
        }
    }

    public void transformPage(View view, float f) {
        if (f < 0.0f) {
            view.setTranslationZ(-1.0f);
        }
        super.transformPage(view, f);
    }
}
