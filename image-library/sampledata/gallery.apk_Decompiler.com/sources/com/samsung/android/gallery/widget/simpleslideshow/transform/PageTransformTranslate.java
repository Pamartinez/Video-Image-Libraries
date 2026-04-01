package com.samsung.android.gallery.widget.simpleslideshow.transform;

import android.view.View;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PageTransformTranslate extends PageTransform {
    private float mInitialTranslateDeltaX = 0.0f;
    private float mInitialTranslateDeltaY = 0.0f;
    private float mTargetTranslateDeltaX = 0.0f;
    private float mTargetTranslateDeltaY = 0.0f;

    public PageTransformTranslate(int i2) {
        super(i2);
    }

    private float getTranslateDeltaX(View view, float f) {
        float f5 = this.mTargetTranslateDeltaX - this.mInitialTranslateDeltaX;
        return getProgressDelta(f) * f5 * ((float) view.getWidth());
    }

    private float getTranslateDeltaY(View view, float f) {
        float f5 = this.mTargetTranslateDeltaY - this.mInitialTranslateDeltaY;
        return getProgressDelta(f) * f5 * ((float) view.getHeight());
    }

    public PageTransformTranslate setInitialDeltaX(float f) {
        this.mInitialTranslateDeltaX = f;
        return this;
    }

    public PageTransformTranslate setInitialDeltaY(float f) {
        this.mInitialTranslateDeltaY = f;
        return this;
    }

    public PageTransformTranslate setTargetDeltaX(float f) {
        this.mTargetTranslateDeltaX = f;
        return this;
    }

    public PageTransformTranslate setTargetDeltaY(float f) {
        this.mTargetTranslateDeltaY = f;
        return this;
    }

    public void transformPageInternal(View view, float f) {
        if (this.mDelayProgress < f) {
            view.setX(view.getX() + getTranslateDeltaX(view, f));
            view.setY(view.getY() + getTranslateDeltaY(view, f));
        }
    }

    public void transformStart(View view, float f) {
        view.setX(view.getX() + getTranslateDeltaX(view, f));
        view.setY(view.getY() + getTranslateDeltaY(view, f));
    }
}
