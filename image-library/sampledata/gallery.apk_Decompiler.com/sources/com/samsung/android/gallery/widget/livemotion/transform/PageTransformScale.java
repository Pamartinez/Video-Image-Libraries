package com.samsung.android.gallery.widget.livemotion.transform;

import android.view.View;
import com.samsung.android.gallery.module.story.transcode.config.KenBurnsInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PageTransformScale extends PageTransform {
    private float mPivotX = -1.0f;
    private float mPivotY = -1.0f;

    public PageTransformScale(int i2) {
        super(i2);
    }

    private void setPivot(View view) {
        if (this.mPivotX != -1.0f) {
            view.setPivotX(((float) view.getWidth()) * this.mPivotX);
        }
        if (this.mPivotY != -1.0f) {
            view.setPivotY(((float) view.getHeight()) * this.mPivotY);
        }
    }

    public KenBurnsInfo.Property copyProperty(KenBurnsInfo.Property property) {
        return property.setInitScaleX(this.mInitialValue).setTargetScaleX(this.mTargetValue).setInitScaleY(this.mInitialValue).setTargetScaleY(this.mTargetValue).setDelay(this.mDelayProgress);
    }

    public float getPivotX() {
        return this.mPivotX;
    }

    public float getPivotY() {
        return this.mPivotY;
    }

    public PageTransformScale setPivotX(float f) {
        this.mPivotX = f;
        return this;
    }

    public PageTransformScale setPivotY(float f) {
        this.mPivotY = f;
        return this;
    }

    public void transformPageInternal(View view, float f) {
        if (this.mDelayProgress < f) {
            float valueDelta = getValueDelta(f) + this.mInitialValue;
            view.setScaleX(valueDelta);
            view.setScaleY(valueDelta);
        }
    }

    public void transformStart(View view, float f) {
        view.setScaleX(this.mInitialValue);
        view.setScaleY(this.mInitialValue);
        setPivot(view);
    }

    public PageTransformScale(int i2, int i7) {
        super(i2, i7);
    }
}
