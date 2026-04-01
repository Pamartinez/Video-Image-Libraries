package com.samsung.android.gallery.widget.animator;

import android.animation.ValueAnimator;
import android.graphics.Outline;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.support.utils.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ScaleAnimator extends PropertyAnimator {
    private GradientDrawable mBgDrawable;
    private boolean mBlockUpdate;
    private boolean mChangeOutLine;
    private int mColor;
    private GradientDrawable mFgDrawable;
    private RectF mFrom;
    private int mFromPadding;
    private float mFromRadii;
    private float mFromScaleX;
    private float mFromScaleY;
    private boolean mScaleBackground;
    private boolean mScaleForeground;
    private float mThickness;
    private RectF mTo;
    private int mToPadding;
    private float mToRadii;
    private float mToScaleX;
    private float mToScaleY;
    private boolean mUpdateLayoutParam;
    private boolean mUpdatePadding;

    public ScaleAnimator(View view, RectF rectF, RectF rectF2) {
        this(view, rectF, rectF, rectF2);
    }

    private boolean isUpdateBlocked() {
        return this.mBlockUpdate;
    }

    public ScaleAnimator addPadding(int i2, int i7) {
        if (i2 == 0 && i7 == 0) {
            return this;
        }
        this.mFromPadding = i2;
        this.mToPadding = i7;
        this.mUpdatePadding = true;
        return this;
    }

    public void enableBorder(boolean z, float f, float f5, float f8, int i2) {
        if (z) {
            this.mThickness = f;
            this.mFromRadii = f5;
            this.mToRadii = f8;
            this.mColor = i2;
            if (this.mView.getBackground() instanceof GradientDrawable) {
                this.mScaleBackground = true;
                this.mBgDrawable = new GradientDrawable();
            }
            if (this.mView.getForeground() instanceof GradientDrawable) {
                this.mScaleForeground = true;
                this.mFgDrawable = new GradientDrawable();
            }
        }
    }

    public ScaleAnimator enableUpdateLayoutParam(boolean z) {
        this.mUpdateLayoutParam = z;
        return this;
    }

    public void forceProgressDone() {
        setCurrentFraction(1.0f);
        onAnimationUpdate(this);
        this.mBlockUpdate = true;
    }

    public void notifyPropertyAnimationEnd() {
        super.notifyPropertyAnimationEnd();
        this.mBgDrawable = null;
        this.mFgDrawable = null;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        int i2;
        if (!isUpdateBlocked()) {
            super.onAnimationUpdate(valueAnimator);
            if (!this.mUpdateLayoutParam || this.mFrom == null) {
                try {
                    View view = this.mView;
                    float f = this.mFromScaleX;
                    view.setScaleX(((this.mToScaleX - f) * this.mCurrentValue) + f);
                    View view2 = this.mView;
                    float f5 = this.mFromScaleY;
                    view2.setScaleY(((this.mToScaleY - f5) * this.mCurrentValue) + f5);
                } catch (Exception unused) {
                    new InternalException("ScaleAnimator fail v2 :" + Logger.v(Float.valueOf(this.mFromScaleX), Float.valueOf(this.mFromScaleY), Float.valueOf(this.mToScaleX), Float.valueOf(this.mToScaleY), Float.valueOf(this.mCurrentValue))).post();
                    return;
                }
            } else {
                this.mView.setScaleX(1.0f);
                this.mView.setScaleY(1.0f);
                ViewGroup.LayoutParams layoutParams = this.mView.getLayoutParams();
                if (this.mUpdatePadding) {
                    int i7 = this.mFromPadding;
                    i2 = (int) ((this.mCurrentValue * ((float) (this.mToPadding - i7))) + ((float) i7));
                } else {
                    i2 = 0;
                }
                int i8 = i2 * 2;
                layoutParams.width = ((int) (((this.mTo.width() - this.mFrom.width()) * this.mCurrentValue) + this.mFrom.width())) - i8;
                layoutParams.height = (int) ((((this.mTo.height() - this.mFrom.height()) * this.mCurrentValue) + this.mFrom.height()) - ((float) i8));
                this.mView.setLayoutParams(layoutParams);
            }
            if (this.mChangeOutLine) {
                this.mView.invalidateOutline();
            }
            if (this.mScaleBackground && this.mBgDrawable != null) {
                float f8 = this.mCurrentValue;
                float f10 = (1.0f - f8) * this.mFromRadii;
                this.mBgDrawable.setCornerRadius(((this.mToRadii / this.mView.getScaleX()) * f8) + f10);
                this.mBgDrawable.setStroke(Math.round(this.mThickness / this.mView.getScaleX()), this.mColor);
                this.mView.setBackground(this.mBgDrawable);
            }
            if (this.mScaleForeground && this.mFgDrawable != null) {
                float f11 = this.mCurrentValue;
                float f12 = (1.0f - f11) * this.mFromRadii;
                this.mFgDrawable.setCornerRadius(((this.mToRadii / this.mView.getScaleX()) * f11) + f12);
                this.mFgDrawable.setStroke(Math.round(this.mThickness / this.mView.getScaleX()), this.mColor);
                this.mView.setForeground(this.mFgDrawable);
            }
        }
    }

    public ScaleAnimator pivotX(float f) {
        this.mView.setPivotX(f);
        return this;
    }

    public ScaleAnimator pivotY(float f) {
        this.mView.setPivotY(f);
        return this;
    }

    public ScaleAnimator setOutlineProvider(final float f, final float f5) {
        this.mView.setOutlineProvider(new ViewOutlineProvider() {
            public void getOutline(View view, Outline outline) {
                float f = ScaleAnimator.this.mCurrentValue;
                float f5 = (1.0f - f) * f;
                Outline outline2 = outline;
                outline2.setRoundRect(0, 0, view.getWidth(), view.getHeight(), ((f5 / view.getScaleX()) * f) + f5);
            }
        });
        this.mView.setClipToOutline(true);
        this.mChangeOutLine = true;
        return this;
    }

    public ScaleAnimator(View view, RectF rectF, RectF rectF2, RectF rectF3) {
        super(view);
        this.mUpdateLayoutParam = false;
        this.mUpdatePadding = false;
        this.mScaleBackground = false;
        this.mScaleForeground = false;
        this.mBgDrawable = null;
        this.mFgDrawable = null;
        this.mThickness = 0.0f;
        this.mFromRadii = 0.0f;
        this.mToRadii = 0.0f;
        this.mColor = 0;
        setFloatValues(new float[]{0.0f, 1.0f});
        this.mFrom = rectF2;
        this.mTo = rectF3;
        this.mFromScaleX = rectF2.width() / rectF.width();
        this.mFromScaleY = rectF2.height() / rectF.height();
        this.mToScaleX = rectF3.width() / rectF.width();
        this.mToScaleY = rectF3.height() / rectF.height();
        if (Float.isNaN(this.mFromScaleY) || Float.isInfinite(this.mFromScaleY)) {
            this.mFromScaleY = 1.0f;
        }
        if (Float.isNaN(this.mToScaleY) || Float.isInfinite(this.mToScaleY)) {
            this.mToScaleY = 1.0f;
        }
        this.mFromPadding = 0;
        this.mToPadding = 0;
        pivotX(0.0f);
        pivotY(0.0f);
    }

    public ScaleAnimator setOutlineProvider(final float f, final float f5, final boolean[] zArr) {
        this.mView.setOutlineProvider(new ViewOutlineProvider() {
            public void getOutline(View view, Outline outline) {
                float f;
                float f5;
                float f8;
                float f10 = ScaleAnimator.this.mCurrentValue;
                float scaleX = ((f5 / view.getScaleX()) * f10) + ((1.0f - f10) * f);
                boolean[] zArr = zArr;
                boolean z = zArr[0];
                float f11 = 0.0f;
                if (z || zArr[2]) {
                    f = 0.0f;
                } else {
                    f = scaleX;
                }
                int i2 = -((int) f);
                if (z || zArr[1]) {
                    f5 = 0.0f;
                } else {
                    f5 = scaleX;
                }
                int i7 = -((int) f5);
                int width = view.getWidth();
                boolean[] zArr2 = zArr;
                if (zArr2[1] || zArr2[3]) {
                    f8 = 0.0f;
                } else {
                    f8 = scaleX;
                }
                int i8 = width + ((int) f8);
                int height = view.getHeight();
                boolean[] zArr3 = zArr;
                if (!zArr3[2] && !zArr3[3]) {
                    f11 = scaleX;
                }
                outline.setRoundRect(i2, i7, i8, height + ((int) f11), scaleX);
            }
        });
        this.mView.setClipToOutline(true);
        this.mChangeOutLine = true;
        return this;
    }

    public ScaleAnimator(View view, float f, float f5) {
        super(view);
        this.mUpdateLayoutParam = false;
        this.mUpdatePadding = false;
        this.mScaleBackground = false;
        this.mScaleForeground = false;
        this.mBgDrawable = null;
        this.mFgDrawable = null;
        this.mThickness = 0.0f;
        this.mFromRadii = 0.0f;
        this.mToRadii = 0.0f;
        this.mColor = 0;
        setFloatValues(new float[]{0.0f, 1.0f});
        this.mFromScaleX = f;
        this.mFromScaleY = f;
        this.mToScaleX = f5;
        this.mToScaleY = f5;
        this.mFromPadding = 0;
        this.mToPadding = 0;
    }
}
