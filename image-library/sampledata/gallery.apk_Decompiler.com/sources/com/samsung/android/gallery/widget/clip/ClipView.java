package com.samsung.android.gallery.widget.clip;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ClipView extends FrameLayout {
    private int mBottomMargin;
    private Rect mBounds = null;
    protected RectF mDefaultRect = null;
    protected RectF mDisplayRect = null;
    protected final int[] mLocation = new int[2];
    private int[] mParentLocation = new int[2];
    protected float mScale = 1.0f;
    protected boolean mScaling = false;
    private OnToggleConsumeListener mToggleConsumeListener;
    protected int mTopMargin;
    protected float mTransX;
    protected float mTransY;
    protected boolean mTranslating = false;
    private boolean mVideoMode = false;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OnToggleConsumeListener {
        void onToggleConsumed();
    }

    public ClipView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void destroy() {
        this.mToggleConsumeListener = null;
    }

    public Rect getBounds() {
        int height = getHeight();
        float f = this.mScale;
        int i2 = height - ((int) ((((((float) height) * f) + ((float) this.mLocation[1])) - ((float) (height - this.mBottomMargin))) / f));
        Rect rect = this.mBounds;
        if (rect == null) {
            this.mBounds = new Rect(0, 0, getWidth(), i2);
        } else {
            rect.right = getWidth();
            this.mBounds.bottom = i2;
        }
        return this.mBounds;
    }

    public float getCoordinatedX(float f) {
        if (!this.mVideoMode) {
            return (f - ((float) this.mLocation[0])) / this.mScale;
        }
        float centerX = f - this.mDisplayRect.centerX();
        float f5 = this.mScale;
        return (((((float) getWidth()) * f5) / 2.0f) + centerX) / f5;
    }

    public float getCoordinatedY(float f) {
        if (!this.mVideoMode) {
            return ((f - ((float) this.mLocation[1])) - ((float) this.mTopMargin)) / this.mScale;
        }
        float centerY = f - this.mDisplayRect.centerY();
        float f5 = this.mScale;
        return (((((float) getHeight()) * f5) / 2.0f) + centerY) / f5;
    }

    public void notifyToggleConsumed() {
        OnToggleConsumeListener onToggleConsumeListener = this.mToggleConsumeListener;
        if (onToggleConsumeListener != null) {
            onToggleConsumeListener.onToggleConsumed();
        }
    }

    public abstract void onReady(RectF rectF, int[] iArr, int i2, int i7, boolean z);

    public abstract void onRefresh(RectF rectF, int[] iArr, int i2, int i7, boolean z, boolean z3);

    public void setDefaultRect(RectF rectF) {
        this.mDefaultRect = rectF;
    }

    public void setDisplayRect(RectF rectF, int[] iArr, int i2, int i7) {
        boolean z;
        this.mDisplayRect = rectF;
        this.mParentLocation = iArr;
        this.mTopMargin = i2;
        this.mBottomMargin = i7;
        if (rectF == null || getWidth() <= 0 || getHeight() <= 0) {
            this.mScale = 1.0f;
            this.mTransX = 0.0f;
            this.mTransY = 0.0f;
            return;
        }
        float max = Math.max(this.mDisplayRect.width() / ((float) getWidth()), this.mDisplayRect.height() / ((float) getHeight()));
        boolean z3 = false;
        if (this.mScale != max) {
            z = true;
        } else {
            z = false;
        }
        this.mScaling = z;
        float centerX = this.mDisplayRect.centerX() - (((float) getWidth()) / 2.0f);
        float centerY = this.mDisplayRect.centerY() - (((float) getHeight()) / 2.0f);
        if (Math.abs(this.mTransX - centerX) > 0.001f || Math.abs(this.mTransY - centerY) > 0.001f) {
            z3 = true;
        }
        this.mTranslating = z3;
        this.mScale = max;
        this.mTransX = centerX;
        this.mTransY = centerY + ((float) this.mTopMargin);
    }

    public void setOnToggleConsumeListener(OnToggleConsumeListener onToggleConsumeListener) {
        this.mToggleConsumeListener = onToggleConsumeListener;
    }

    public void setVideoMode(boolean z) {
        this.mVideoMode = z;
    }

    public void updateViewLocation() {
        getLocationInWindow(this.mLocation);
        int[] iArr = this.mLocation;
        int i2 = iArr[0];
        int[] iArr2 = this.mParentLocation;
        iArr[0] = i2 - iArr2[0];
        iArr[1] = iArr[1] - iArr2[1];
    }
}
