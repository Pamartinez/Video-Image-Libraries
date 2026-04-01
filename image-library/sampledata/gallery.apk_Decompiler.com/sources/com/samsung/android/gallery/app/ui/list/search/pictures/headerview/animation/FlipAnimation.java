package com.samsung.android.gallery.app.ui.list.search.pictures.headerview.animation;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class FlipAnimation extends Animation {
    private Camera mCamera;
    private float mCenterX;
    private float mCenterY;
    private float mStartDegree = 0.0f;

    public void applyTransformation(float f, Transformation transformation) {
        float f5 = (f * 90.0f) + this.mStartDegree;
        Matrix matrix = transformation.getMatrix();
        this.mCamera.save();
        this.mCamera.rotateY(-f5);
        this.mCamera.getMatrix(matrix);
        this.mCamera.restore();
        matrix.preTranslate(-this.mCenterX, -this.mCenterY);
        matrix.postTranslate(this.mCenterX, this.mCenterY);
    }

    public void initialize(int i2, int i7, int i8, int i10) {
        super.initialize(i2, i7, i8, i10);
        this.mCenterX = ((float) i2) / 2.0f;
        this.mCenterY = ((float) i7) / 2.0f;
        this.mCamera = new Camera();
    }

    public void setStartDegree(float f) {
        this.mStartDegree = f;
    }
}
