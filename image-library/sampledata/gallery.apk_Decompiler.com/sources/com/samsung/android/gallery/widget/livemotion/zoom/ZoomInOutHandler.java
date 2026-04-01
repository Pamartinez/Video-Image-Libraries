package com.samsung.android.gallery.widget.livemotion.zoom;

import android.animation.AnimatorSet;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.samsung.android.gallery.widget.R$id;
import com.samsung.android.gallery.widget.animator.ImageMatrixAnimator;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.ocr.MOCRLang;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ZoomInOutHandler extends ZoomInHandler {
    private final RectF mBound = new RectF();
    private float mCorrFactor;
    private int mDrawableHeight;
    private int mDrawableWidth;
    protected ImageView mImageView;
    private int mImageViewHeight;
    private int mImageViewWidth;
    protected Matrix mInitialMatrix;
    protected float mInitialMatrixScale;
    private float mMinScale;
    private float mPivotX;
    private float mPivotY;
    private int mRotation;

    private float[] getCurrentMatrixValues() {
        float[] fArr = new float[9];
        this.mImageView.getImageMatrix().getValues(fArr);
        return fArr;
    }

    private float getScale(float[] fArr) {
        return Math.abs(fArr[0] + fArr[1]);
    }

    private float[] getScaledBaseXY(float f) {
        float[] currentMatrixValues = getCurrentMatrixValues();
        float scale = (getScale(currentMatrixValues) - f) * this.mCorrFactor;
        return new float[]{currentMatrixValues[2] + (((float) this.mDrawableWidth) * scale * this.mPivotX), currentMatrixValues[5] + (((float) this.mDrawableHeight) * scale * this.mPivotY)};
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0018, code lost:
        if (r3 > r4) goto L_0x0011;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private float[] getTranslateXY(float r7, float r8) {
        /*
            r6 = this;
            float[] r0 = r6.getCurrentMatrixValues()
            r1 = 2
            r2 = r0[r1]
            float r3 = r2 + r7
            android.graphics.RectF r6 = r6.mBound
            float r4 = r6.left
            int r5 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r5 >= 0) goto L_0x0014
        L_0x0011:
            float r7 = r4 - r2
            goto L_0x001b
        L_0x0014:
            float r4 = r6.right
            int r3 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r3 <= 0) goto L_0x001b
            goto L_0x0011
        L_0x001b:
            r2 = 5
            r0 = r0[r2]
            float r2 = r0 + r8
            float r3 = r6.top
            int r4 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1))
            if (r4 >= 0) goto L_0x0029
            float r8 = r3 - r0
            goto L_0x0031
        L_0x0029:
            float r6 = r6.bottom
            int r2 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r2 <= 0) goto L_0x0031
            float r8 = r6 - r0
        L_0x0031:
            float[] r6 = new float[r1]
            r0 = 0
            r6[r0] = r7
            r7 = 1
            r6[r7] = r8
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.widget.livemotion.zoom.ZoomInOutHandler.getTranslateXY(float, float):float[]");
    }

    private void initImageView(View view) {
        this.mImageView = (ImageView) view.findViewById(R$id.thumbnail);
        this.mInitialMatrix = new Matrix(this.mImageView.getImageMatrix());
    }

    private void initPivot(float f, float f5) {
        float[] currentMatrixValues = getCurrentMatrixValues();
        float scale = getScale(currentMatrixValues);
        float f8 = ((float) this.mDrawableWidth) * scale;
        float f10 = ((float) this.mDrawableHeight) * scale;
        int i2 = this.mRotation;
        if (i2 == 90) {
            this.mPivotX = (-((f - currentMatrixValues[2]) + f8)) / f8;
            this.mPivotY = (f5 - currentMatrixValues[5]) / f10;
        } else if (i2 == 180) {
            this.mPivotX = (-((f - currentMatrixValues[2]) + f8)) / f8;
            this.mPivotY = (-((f5 - currentMatrixValues[5]) + f10)) / f10;
        } else if (i2 != 270) {
            this.mPivotX = (f - currentMatrixValues[2]) / f8;
            this.mPivotY = (f5 - currentMatrixValues[5]) / f10;
        } else {
            this.mPivotX = (f - currentMatrixValues[2]) / f8;
            this.mPivotY = (-((f5 - currentMatrixValues[5]) + f10)) / f10;
        }
    }

    private void initRotation() {
        float[] currentMatrixValues = getCurrentMatrixValues();
        int i2 = 0;
        int i7 = ((currentMatrixValues[0] + currentMatrixValues[1]) > 0.0f ? 1 : ((currentMatrixValues[0] + currentMatrixValues[1]) == 0.0f ? 0 : -1));
        int i8 = ((currentMatrixValues[4] + currentMatrixValues[3]) > 0.0f ? 1 : ((currentMatrixValues[4] + currentMatrixValues[3]) == 0.0f ? 0 : -1));
        if (i7 < 0) {
            if (i8 > 0) {
                i2 = 90;
            } else {
                i2 = MOCRLang.KHMER;
            }
        } else if (i8 <= 0) {
            i2 = 270;
        }
        this.mRotation = i2;
    }

    private void initScale() {
        float scale = getScale(getCurrentMatrixValues());
        this.mInitialMatrixScale = scale;
        float min = Math.min(((float) this.mImageViewHeight) / ((float) this.mDrawableHeight), ((float) this.mImageViewWidth) / ((float) this.mDrawableWidth)) + (1.0f - scale);
        this.mMinScale = min;
        if (min < 1.0f - getMaxScale()) {
            float abs = Math.abs((this.mInitialMatrixScale / this.mMinScale) / 2.0f);
            this.mCorrFactor = abs;
            this.mMinScale = ((this.mMinScale + abs) - 1.0f) / abs;
            return;
        }
        this.mCorrFactor = 1.0f;
    }

    private void initSize() {
        int i2;
        int i7;
        this.mImageViewWidth = this.mImageView.getWidth();
        this.mImageViewHeight = this.mImageView.getHeight();
        Drawable drawable = this.mImageView.getDrawable();
        if (drawable != null) {
            int i8 = this.mRotation;
            if (i8 == 90 || i8 == 270) {
                i2 = drawable.getIntrinsicHeight();
            } else {
                i2 = drawable.getIntrinsicWidth();
            }
            this.mDrawableWidth = i2;
            int i10 = this.mRotation;
            if (i10 == 90 || i10 == 270) {
                i7 = drawable.getIntrinsicWidth();
            } else {
                i7 = drawable.getIntrinsicHeight();
            }
            this.mDrawableHeight = i7;
            return;
        }
        this.mDrawableWidth = this.mImageViewWidth;
        this.mDrawableHeight = this.mImageViewHeight;
    }

    private void updateBound(float f) {
        float f5;
        float f8;
        float f10;
        int i2 = this.mImageViewWidth;
        float f11 = ((float) i2) - (((float) this.mDrawableWidth) * f);
        float f12 = 0.0f;
        int i7 = (f11 > 0.0f ? 1 : (f11 == 0.0f ? 0 : -1));
        if (i7 > 0) {
            f5 = f11 / 2.0f;
        } else {
            f5 = f11;
        }
        if (i7 > 0) {
            f8 = f11 / 2.0f;
        } else {
            f8 = 0.0f;
        }
        int i8 = this.mImageViewHeight;
        float f13 = ((float) i8) - (((float) this.mDrawableHeight) * f);
        int i10 = (f13 > 0.0f ? 1 : (f13 == 0.0f ? 0 : -1));
        if (i10 > 0) {
            f10 = f13 / 2.0f;
        } else {
            f10 = f13;
        }
        if (i10 > 0) {
            f12 = f13 / 2.0f;
        }
        int i11 = this.mRotation;
        if (i11 == 90) {
            this.mBound.set(((float) i2) - f8, f10, ((float) i2) - f5, f12);
        } else if (i11 == 180) {
            this.mBound.set(((float) i2) - f8, ((float) i8) - f12, ((float) i2) - f5, ((float) i8) - f10);
        } else if (i11 != 270) {
            this.mBound.set(f5, f10, f8, f12);
        } else {
            this.mBound.set(f5, ((float) i8) - f12, f8, ((float) i8) - f10);
        }
    }

    public AnimatorSet createRestoreAnimator(View view, int i2, float f) {
        AnimatorSet animatorSet;
        if (f > 1.0f) {
            animatorSet = super.createRestoreAnimator(view, i2, f);
        } else {
            animatorSet = new AnimatorSet();
        }
        if (!this.mImageView.getImageMatrix().equals(this.mInitialMatrix)) {
            ImageView imageView = this.mImageView;
            PropertyAnimator duration = new ImageMatrixAnimator(imageView, imageView.getImageMatrix(), this.mInitialMatrix).setDuration(i2);
            duration.setStartPoint();
            animatorSet.play(duration);
        }
        return animatorSet;
    }

    public float getMinScale() {
        return this.mMinScale;
    }

    public float getThreshold() {
        return 0.003f / this.mCorrFactor;
    }

    public void move(View view, TouchCoordinates touchCoordinates, float f) {
        if (f > 1.0f) {
            super.move(view, touchCoordinates, f);
            return;
        }
        float[] translateXY = getTranslateXY(touchCoordinates.getDeltaX(), touchCoordinates.getDeltaY());
        this.mImageView.getImageMatrix().postTranslate(translateXY[0], translateXY[1]);
        this.mImageView.invalidate();
    }

    public void onScale(View view, float f) {
        if (f > 1.0f) {
            super.onScale(view, f);
            return;
        }
        float f5 = ((f - 1.0f) * this.mCorrFactor) + this.mInitialMatrixScale;
        updateBound(f5);
        Matrix imageMatrix = this.mImageView.getImageMatrix();
        float[] scaledBaseXY = getScaledBaseXY(f5);
        imageMatrix.setScale(f5, f5);
        imageMatrix.postRotate((float) this.mRotation);
        float[] translateXY = getTranslateXY(scaledBaseXY[0], scaledBaseXY[1]);
        imageMatrix.postTranslate(translateXY[0], translateXY[1]);
    }

    public void onScaleBegin(View view, float f, float f5) {
        super.onScaleBegin(view, f, f5);
        initImageView(view);
        initRotation();
        initSize();
        initScale();
        initPivot(f, f5);
    }
}
