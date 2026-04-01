package com.samsung.android.gallery.widget.livemotion.transform;

import android.view.View;
import com.samsung.android.gallery.module.story.transcode.config.KenBurnsInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PageTransformTranslate extends PageTransform {
    protected float mInitialTranslateDeltaX = 0.0f;
    protected float mInitialTranslateDeltaY = 0.0f;
    protected float mTargetTranslateDeltaX = 0.0f;
    protected float mTargetTranslateDeltaY = 0.0f;

    public PageTransformTranslate(int i2) {
        super(i2);
    }

    public KenBurnsInfo.Property copyProperty(KenBurnsInfo.Property property) {
        return property.setInitTranslateX(this.mInitialTranslateDeltaX).setTargetTranslateX(this.mTargetTranslateDeltaX).setInitTranslateY(this.mInitialTranslateDeltaY).setTargetTranslateY(this.mTargetTranslateDeltaY).setDelay(this.mDelayProgress);
    }

    public float getTranslateDeltaX(View view, float f) {
        float f5 = this.mTargetTranslateDeltaX - this.mInitialTranslateDeltaX;
        return getProgressDelta(f) * f5 * ((float) view.getWidth());
    }

    public float getTranslateDeltaY(View view, float f) {
        float f5 = this.mTargetTranslateDeltaY - this.mInitialTranslateDeltaY;
        return getProgressDelta(f) * f5 * ((float) view.getHeight());
    }

    public float getTranslateInitialDeltaX(View view) {
        return ((float) view.getWidth()) * this.mInitialTranslateDeltaX;
    }

    public float getTranslateInitialDeltaY(View view) {
        return ((float) view.getHeight()) * this.mInitialTranslateDeltaY;
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
            view.setX(getTranslateInitialDeltaX(view) + getTranslateDeltaX(view, f));
            view.setY(getTranslateInitialDeltaY(view) + getTranslateDeltaY(view, f));
        }
    }

    public void transformStart(View view, float f) {
        view.setX(getTranslateInitialDeltaX(view));
        view.setY(getTranslateInitialDeltaY(view));
    }
}
