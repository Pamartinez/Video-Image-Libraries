package com.samsung.android.gallery.widget.photoview;

import android.graphics.PointF;
import android.view.MotionEvent;
import com.samsung.android.gallery.widget.photoview.PhotoViewMotionControl;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemotePhotoViewMotionControl extends PhotoViewMotionControl {
    private final PhotoViewPositionControl mPrimaryPosCtrl;
    private final int mPrimaryViewHeight;
    private float mPrimaryViewPrevScale;
    private float mPrimaryViewScaleStart;
    private final int mPrimaryViewWidth;
    private float mSecondaryViewScaleRatio;

    public RemotePhotoViewMotionControl(PhotoView photoView, PhotoViewMotionControl.SourceInfoGetter sourceInfoGetter, PhotoViewPositionControl photoViewPositionControl, PhotoViewMotionControl photoViewMotionControl) {
        super(photoView, sourceInfoGetter, photoViewPositionControl);
        this.mPrimaryPosCtrl = photoViewMotionControl.getMainPhotoView().mPosCtrl;
        this.mPrimaryViewWidth = photoViewMotionControl.getMainPhotoView().getWidth();
        this.mPrimaryViewHeight = photoViewMotionControl.getMainPhotoView().getHeight();
    }

    private PointF getRelativeTranslateEndPos(PointF pointF, float f) {
        if (pointF == null) {
            return null;
        }
        float f5 = (((float) this.mPrimaryViewWidth) / 2.0f) + (-pointF.x);
        float f8 = (((float) this.mPrimaryViewHeight) / 2.0f) + (-pointF.y);
        float currentScale = getMainPhotoView().getCurrentScale() / f;
        return new PointF((((float) getMainPhotoView().getWidth()) / 2.0f) + (-(f5 * currentScale)), (((float) getMainPhotoView().getHeight()) / 2.0f) + (-(f8 * currentScale)));
    }

    private PointF getStartPointF() {
        return getRelativeTranslateEndPos(this.mPrimaryPosCtrl.getPosition(), this.mPrimaryViewPrevScale);
    }

    public float getAdjustedNewScale(float f, float f5) {
        float f8;
        PhotoView mainPhotoView = getMainPhotoView();
        float scale = this.mPrimaryPosCtrl.getScale();
        if (scale < mainPhotoView.getMaxScale()) {
            if (this.mPrimaryViewPrevScale == 0.0f) {
                this.mPrimaryViewScaleStart = scale;
                this.mSecondaryViewScaleRatio = (mainPhotoView.getMaxScale() - mainPhotoView.minScale()) / (mainPhotoView.getMaxScale() - this.mPrimaryViewScaleStart);
            }
            if (scale == this.mPrimaryViewScaleStart) {
                f8 = mainPhotoView.minScale();
            } else {
                f8 = ((scale - this.mPrimaryViewPrevScale) * this.mSecondaryViewScaleRatio) + f5;
            }
        } else {
            f8 = mainPhotoView.getMaxScale();
        }
        this.mPrimaryViewPrevScale = scale;
        return f8;
    }

    public float getSourceX(float f) {
        return this.mPrimaryPosCtrl.viewToSourceX(f);
    }

    public float getSourceY(float f) {
        return this.mPrimaryPosCtrl.viewToSourceY(f);
    }

    public PointF getVTranslateEnd(float f, float f5) {
        PointF relativeTranslateEndPos = getRelativeTranslateEndPos(new PointF((f * 0.25f * this.mScaleRatio) + this.mPrimaryPosCtrl.getX(), (0.25f * f5 * this.mScaleRatio) + this.mPrimaryPosCtrl.getY()), this.mPrimaryPosCtrl.getScale());
        if (relativeTranslateEndPos != null) {
            return relativeTranslateEndPos;
        }
        return super.getVTranslateEnd(f, f5);
    }

    public void setPositionOnMultiTouch(MotionEvent motionEvent, float f, float f5, float f8, float f10) {
        PointF startPointF = getStartPointF();
        if (startPointF != null) {
            this.mPosCtrl.setPosition(startPointF.x, startPointF.y, true);
        }
    }
}
