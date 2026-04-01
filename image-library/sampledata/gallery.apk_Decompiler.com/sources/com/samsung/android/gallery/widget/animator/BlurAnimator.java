package com.samsung.android.gallery.widget.animator;

import android.animation.ValueAnimator;
import android.graphics.RenderEffect;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.support.blur.BlurEffectHolder;
import com.samsung.android.gallery.support.blur.BlurImageInfo;
import com.samsung.android.gallery.support.blur.BlurParams;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class BlurAnimator extends PropertyAnimator {
    private final BlurImageInfo mInfo;

    public BlurAnimator(View view, BlurImageInfo blurImageInfo) {
        super(view);
        this.mInfo = blurImageInfo;
    }

    private float getCurveLevel(float f) {
        float f5;
        if (this.mInfo.isUseEmptyCoverBlur()) {
            f5 = 15.0f;
        } else {
            f5 = -15.0f;
        }
        return (float) ((int) (f * f5));
    }

    private float getCurveMaxY(float f) {
        float f5;
        if (this.mInfo.isUseEmptyCoverBlur()) {
            f5 = 180.0f;
        } else {
            f5 = 206.0f;
        }
        return 250.0f - ((250.0f - f5) * f);
    }

    private float getPercent(float f) {
        return ((float) Math.round(((this.mInfo.getTargetViewHeight() * 2.0f) / f) * 100.0f)) / 100.0f;
    }

    private float getPivotPercent(boolean z, float f) {
        return 0.25f - (f * 0.0f);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i2;
        int i7;
        float f;
        float f5;
        float f8;
        float f10;
        super.onAnimationUpdate(valueAnimator);
        if (Build.VERSION.SDK_INT >= 31) {
            ViewGroup.LayoutParams layoutParams = this.mView.getLayoutParams();
            if (this.mInfo.isGridType()) {
                i2 = this.mView.getWidth();
            } else {
                i2 = layoutParams.width;
            }
            if (this.mInfo.isGridType()) {
                i7 = this.mView.getHeight();
            } else {
                i7 = layoutParams.height;
            }
            if (this.mInfo.isSkipBlur()) {
                this.mView.setRenderEffect((RenderEffect) null);
            } else if (this.mInfo.isGridType()) {
                View view = this.mView;
                BlurParams.Builder builder = new BlurParams.Builder(i2, i7);
                if (this.mInfo.isUseEmptyCoverBlur()) {
                    f5 = 15.0f;
                } else {
                    f5 = -15.0f;
                }
                BlurParams.Builder curveLevel = builder.setCurveLevel(f5);
                if (this.mInfo.isUseEmptyCoverBlur()) {
                    f8 = 180.0f;
                } else {
                    f8 = 206.0f;
                }
                BlurParams.Builder radius = curveLevel.setCurveMaxY(f8).setPercent(getPercent(this.mView.getScaleY() * ((float) i7))).setPivotPercent(getPivotPercent(this.mInfo.isReverseOperation(), this.mCurrentValue)).setRadius(BlurEffectHolder.getDefaultAlbumRadius(i2));
                if (this.mInfo.isUseEmptyCoverBlur()) {
                    f10 = 0.4f;
                } else {
                    f10 = 0.2f;
                }
                view.setRenderEffect(BlurEffectHolder.getRenderEffectForAlbumAnimation(radius.setSaturation(f10).build()));
            } else {
                if (this.mInfo.isReverseOperation()) {
                    f = 1.0f - this.mCurrentValue;
                } else {
                    f = this.mCurrentValue;
                }
                this.mView.setRenderEffect(BlurEffectHolder.getRenderEffectForAlbumAnimation(new BlurParams.Builder(i2, i7).setCurveLevel(getCurveLevel(f)).setCurveMaxY(getCurveMaxY(f)).setPercent(getPercent((float) i7)).setPivotPercent(0.25f).setRadius(BlurEffectHolder.getDefaultAlbumRadius(i2) * f).build()));
            }
        }
    }
}
