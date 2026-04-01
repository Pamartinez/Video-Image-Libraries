package com.samsung.android.gallery.widget.videoview.mediaplayer;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewParent;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.OnTranslationListener;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.animator.ValueAnimatorIgnoreSetting;
import com.samsung.android.gallery.widget.photoview.OnViewerExitGestureListener;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.videoview.ScaleEndListener;
import ic.q;
import u7.C0523d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ScaleDelegate {
    private static final boolean SUPPORT_MOTION_PHOTO_ZOOM = PreferenceFeatures.OneUi30.MOTION_PHOTO_ZOOM;
    private boolean mCenterCrop;
    private final IDelegateListener mDelegateListener;
    private OnViewerExitGestureListener mExitListener;
    private OnTranslationListener mOnTranslationListener;
    private boolean mResetScaleOnLayout;
    private final ScaleCoordinateInfo mScaleCoordinateInfo = new ScaleCoordinateInfo();
    /* access modifiers changed from: private */
    public ScaleEndListener mScaleEndListener;
    private final IMediaPlayerInnerView mView;
    private Animator.AnimatorListener mZoomAnimatorListener;

    public ScaleDelegate(IMediaPlayerInnerView iMediaPlayerInnerView, IDelegateListener iDelegateListener) {
        this.mView = iMediaPlayerInnerView;
        this.mDelegateListener = iDelegateListener;
    }

    private float calculateX(float f, float f5) {
        return this.mScaleCoordinateInfo.calculateX(f, f5);
    }

    private float calculateY(float f, float f5) {
        return this.mScaleCoordinateInfo.calculateY(f, f5);
    }

    private float getCenterCropScale() {
        float surfaceWidth = (float) this.mDelegateListener.getSurfaceWidth();
        float surfaceHeight = (float) this.mDelegateListener.getSurfaceHeight();
        if (surfaceHeight == 0.0f || surfaceWidth == 0.0f) {
            return 0.0f;
        }
        return Math.max(getParentRect().width() / surfaceWidth, getParentRect().height() / surfaceHeight);
    }

    private PointF getDefaultPosition() {
        return new PointF((getParentRect().width() - ((float) getMainView().getWidth())) / 2.0f, (getParentRect().height() - ((float) getMainView().getHeight())) / 2.0f);
    }

    private float getExtraXForZoomToPaddingAreaFirst(float f) {
        int minScale = (int) (minScale() * ((float) this.mDelegateListener.getSurfaceWidth()));
        return Math.max(0.0f, getGapRatio(getParentRect().width() - ((float) ((int) (((float) this.mDelegateListener.getSurfaceWidth()) * f))), getParentRect().width() - ((float) minScale))) * ((float) (getMainView().getPaddingLeft() - getMainView().getPaddingRight()));
    }

    private float getExtraYForZoomToPaddingAreaFirst(float f) {
        int minScale = (int) (minScale() * ((float) this.mDelegateListener.getSurfaceHeight()));
        return Math.max(0.0f, getGapRatio(getParentRect().height() - ((float) ((int) (((float) this.mDelegateListener.getSurfaceHeight()) * f))), getParentRect().height() - ((float) minScale))) * ((float) (getMainView().getPaddingTop() - getMainView().getPaddingBottom()));
    }

    private float getGapRatio(float f, float f5) {
        if (f5 != 0.0f) {
            return f / f5;
        }
        return 1.0f;
    }

    private int getHorizontalPadding() {
        return getMainView().getPaddingRight() + getMainView().getPaddingLeft();
    }

    private View getMainView() {
        return this.mView.getView();
    }

    private int getVerticalPadding() {
        return getMainView().getPaddingTop() + getMainView().getPaddingBottom();
    }

    private Animator.AnimatorListener getZoomAnimatorListener() {
        if (this.mZoomAnimatorListener == null) {
            this.mZoomAnimatorListener = new Animator.AnimatorListener() {
                public void onAnimationEnd(Animator animator) {
                    if (ScaleDelegate.this.mScaleEndListener != null) {
                        ((C0523d) ScaleDelegate.this.mScaleEndListener).b();
                    }
                }

                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationStart(Animator animator) {
                }
            };
        }
        return this.mZoomAnimatorListener;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$zoomTo$0(float f, float f5, View view, float f8, PointF pointF, float f10, ValueAnimator valueAnimator) {
        Float f11 = (Float) valueAnimator.getAnimatedValue();
        float floatValue = f - (f11.floatValue() * (f - f5));
        view.setScaleX(floatValue);
        view.setScaleY(floatValue);
        float floatValue2 = f8 - (f11.floatValue() * (f8 - pointF.x));
        float floatValue3 = f11.floatValue();
        view.setX(floatValue2);
        view.setY(f10 - (floatValue3 * (f10 - pointF.y)));
        this.mDelegateListener.getClipViewDelegate().onDraw();
    }

    private void setStartValueForScale() {
        ScaleCoordinateInfo scaleCoordinateInfo = this.mScaleCoordinateInfo;
        scaleCoordinateInfo.startFocusX = scaleCoordinateInfo.parentRect.width() / 2.0f;
        ScaleCoordinateInfo scaleCoordinateInfo2 = this.mScaleCoordinateInfo;
        scaleCoordinateInfo2.startFocusY = scaleCoordinateInfo2.parentRect.height() / 2.0f;
        this.mScaleCoordinateInfo.startX = this.mView.getView().getX();
        this.mScaleCoordinateInfo.startY = this.mView.getView().getY();
        this.mScaleCoordinateInfo.startW = (float) this.mView.getView().getWidth();
        this.mScaleCoordinateInfo.startH = (float) this.mView.getView().getHeight();
        this.mScaleCoordinateInfo.startScale = this.mView.getView().getScaleX();
    }

    public PointF ensureBoundary(float f, float f5, float f8) {
        Pair<Float, Float> xRange = getXRange(f8);
        float max = Math.max(((Float) xRange.first).floatValue(), Math.min(((Float) xRange.second).floatValue(), f));
        Pair<Float, Float> yRange = getYRange(f8);
        return new PointF(max, Math.max(((Float) yRange.first).floatValue(), Math.min(((Float) yRange.second).floatValue(), f5)));
    }

    public float ensureScale(float f) {
        return Math.max(minScale(), Math.min(5.0f, f));
    }

    public RectF getParentRect() {
        return this.mScaleCoordinateInfo.parentRect;
    }

    public ScaleCoordinateInfo getScaleCoordinateInfo() {
        return this.mScaleCoordinateInfo;
    }

    public Pair<Float, Float> getXRange(float f) {
        float width = ((float) getMainView().getWidth()) * f;
        float width2 = ((getParentRect().width() - ((float) getMainView().getWidth())) + getExtraXForZoomToPaddingAreaFirst(f)) / 2.0f;
        float max = Math.max(0.0f, (width - getParentRect().width()) / 2.0f);
        return new Pair<>(Float.valueOf(width2 - max), Float.valueOf(width2 + max));
    }

    public Pair<Float, Float> getYRange(float f) {
        float height = ((float) getMainView().getHeight()) * f;
        float height2 = ((getParentRect().height() - ((float) getMainView().getHeight())) + getExtraYForZoomToPaddingAreaFirst(f)) / 2.0f;
        float max = Math.max(0.0f, (height - getParentRect().height()) / 2.0f);
        return new Pair<>(Float.valueOf(height2 - max), Float.valueOf(height2 + max));
    }

    public boolean isMaxScale() {
        if (this.mView.getView().getScaleX() >= 5.0f) {
            return true;
        }
        return false;
    }

    public boolean isMinScale() {
        if (this.mView.getView().getScaleX() <= minScale()) {
            return true;
        }
        return false;
    }

    public boolean isPinchShrinkExitCondition() {
        ScaleCoordinateInfo scaleCoordinateInfo = this.mScaleCoordinateInfo;
        if (scaleCoordinateInfo.onceScaledUp || scaleCoordinateInfo.startScale > minScale() || this.mScaleCoordinateInfo.currentScale >= 0.95f) {
            return false;
        }
        return true;
    }

    public boolean isScaleAvailable() {
        OnTranslationListener onTranslationListener = this.mOnTranslationListener;
        if (onTranslationListener == null || onTranslationListener.isTranslated()) {
            return false;
        }
        return true;
    }

    public boolean isZoomed() {
        if (this.mView.getView().getScaleX() > minScale()) {
            return true;
        }
        return false;
    }

    public float minScale() {
        if (this.mCenterCrop) {
            float centerCropScale = getCenterCropScale();
            if (centerCropScale > 0.0f) {
                return centerCropScale;
            }
        }
        if (getVerticalPadding() == 0 && getHorizontalPadding() == 0) {
            return 1.0f;
        }
        float width = (float) ((View) this.mView.getParentView()).getWidth();
        float height = (float) ((View) this.mView.getParentView()).getHeight();
        float min = Math.min((width - ((float) getHorizontalPadding())) / ((float) this.mDelegateListener.getSurfaceWidth()), (height - ((float) getVerticalPadding())) / ((float) this.mDelegateListener.getSurfaceHeight()));
        if (!Float.isNaN(min) && !Float.isInfinite(min)) {
            return min;
        }
        Log.e((CharSequence) "ScaleDelegate", "min Scale fault : " + min, Float.valueOf(width), Float.valueOf(height), Integer.valueOf(getHorizontalPadding()), Integer.valueOf(getVerticalPadding()), Integer.valueOf(this.mDelegateListener.getSurfaceWidth()), Integer.valueOf(this.mDelegateListener.getSurfaceHeight()));
        return 1.0f;
    }

    public boolean onDoubleTap(MotionEvent motionEvent) {
        if (!this.mDelegateListener.getMediaPlayerDelegate().isSurfaceReady() && !SUPPORT_MOTION_PHOTO_ZOOM) {
            return true;
        }
        if (!isScaleAvailable()) {
            return false;
        }
        if (this.mView.getView().getScaleX() == minScale()) {
            RectF rectF = this.mScaleCoordinateInfo.parentRect;
            zoomTo(2.31f, new PointF(((rectF.width() - (((float) this.mView.getView().getWidth()) / 2.0f)) - motionEvent.getX()) * 1.31f, ((rectF.height() - (((float) this.mView.getView().getHeight()) / 2.0f)) - motionEvent.getY()) * 1.31f));
            return true;
        }
        setDefaultPosition();
        return true;
    }

    public void onLayout(ViewParent viewParent, boolean z, int i2, int i7, int i8, int i10) {
        if (z || i8 - i2 == i10 - i7) {
            View view = (View) viewParent;
            this.mScaleCoordinateInfo.parentRect.set(view.getX(), view.getY(), view.getX() + ((float) view.getWidth()), view.getY() + ((float) view.getHeight()));
            if (this.mResetScaleOnLayout) {
                this.mResetScaleOnLayout = false;
                resetScale();
            }
            View mainView = getMainView();
            PointF ensureBoundary = ensureBoundary(mainView.getX(), mainView.getY(), mainView.getScaleX());
            mainView.setX(ensureBoundary.x);
            mainView.setY(ensureBoundary.y);
            this.mDelegateListener.getClipViewDelegate().onDraw();
            if (this.mScaleCoordinateInfo.setDefaultPosition) {
                setDefaultPosition();
                this.mScaleCoordinateInfo.setDefaultPosition = true;
            }
        }
    }

    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        float f;
        float f5;
        if (this.mDelegateListener.getMediaPlayerDelegate().isSurfaceReady() || SUPPORT_MOTION_PHOTO_ZOOM) {
            if (!isScaleAvailable()) {
                return false;
            }
            float scaleFactor = scaleGestureDetector.getScaleFactor();
            if (!Float.isInfinite(scaleFactor) && !Float.isNaN(scaleFactor)) {
                this.mScaleCoordinateInfo.currentScale *= scaleFactor;
            }
            ScaleCoordinateInfo scaleCoordinateInfo = this.mScaleCoordinateInfo;
            if (!scaleCoordinateInfo.onceScaledUp && scaleCoordinateInfo.startScale <= minScale()) {
                ScaleCoordinateInfo scaleCoordinateInfo2 = this.mScaleCoordinateInfo;
                if (scaleCoordinateInfo2.currentScale > scaleCoordinateInfo2.startScale) {
                    scaleCoordinateInfo2.onceScaledUp = true;
                }
            }
            if (this.mExitListener == null || !isPinchShrinkExitCondition() || !ViewUtils.isVisible(this.mView.getView())) {
                float ensureScale = ensureScale(this.mScaleCoordinateInfo.currentScale);
                this.mView.getView().setScaleX(ensureScale);
                this.mView.getView().setScaleY(ensureScale);
                if (this.mView.getTouchInteractionViewParent() != null && isZoomed()) {
                    this.mView.getTouchInteractionViewParent().requestDisallowInterceptTouchEvent(true);
                }
                ScaleCoordinateInfo scaleCoordinateInfo3 = this.mScaleCoordinateInfo;
                if (scaleCoordinateInfo3.fixedFocusX) {
                    f = scaleCoordinateInfo3.startFocusX;
                } else {
                    f = scaleGestureDetector.getFocusX();
                }
                float calculateX = calculateX(f, ensureScale);
                ScaleCoordinateInfo scaleCoordinateInfo4 = this.mScaleCoordinateInfo;
                if (scaleCoordinateInfo4.fixedFocusY) {
                    f5 = scaleCoordinateInfo4.startFocusY;
                } else {
                    f5 = scaleGestureDetector.getFocusY();
                }
                PointF ensureBoundary = ensureBoundary(calculateX, calculateY(f5, ensureScale), ensureScale);
                this.mView.getView().setX(ensureBoundary.x);
                this.mView.getView().setY(ensureBoundary.y);
                this.mDelegateListener.getClipViewDelegate().onDraw();
            } else {
                this.mExitListener.onExitGesture(true, false);
                return true;
            }
        }
        return true;
    }

    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        float f;
        View view = this.mView.getView();
        ScaleCoordinateInfo scaleCoordinateInfo = this.mScaleCoordinateInfo;
        float scaleX = view.getScaleX();
        scaleCoordinateInfo.currentScale = scaleX;
        scaleCoordinateInfo.startScale = scaleX;
        if (this.mScaleCoordinateInfo.startScale <= minScale()) {
            this.mScaleCoordinateInfo.onceScaledUp = false;
        }
        RectF parentRect = getParentRect();
        ScaleCoordinateInfo scaleCoordinateInfo2 = this.mScaleCoordinateInfo;
        scaleCoordinateInfo2.fixedFocusX = false;
        scaleCoordinateInfo2.fixedFocusY = false;
        scaleCoordinateInfo2.startFocusX = scaleGestureDetector.getFocusX();
        ScaleCoordinateInfo scaleCoordinateInfo3 = this.mScaleCoordinateInfo;
        if (scaleCoordinateInfo3.fixedFocusY) {
            f = parentRect.height() / 2.0f;
        } else {
            f = scaleGestureDetector.getFocusY();
        }
        scaleCoordinateInfo3.startFocusY = f;
        this.mScaleCoordinateInfo.startX = view.getX();
        this.mScaleCoordinateInfo.startY = view.getY();
        this.mScaleCoordinateInfo.startW = (float) view.getWidth();
        this.mScaleCoordinateInfo.startH = (float) view.getHeight();
        return true;
    }

    public void resetScale() {
        this.mView.getView().setScaleX(minScale());
        this.mView.getView().setScaleY(minScale());
    }

    public void setCenterCrop(boolean z) {
        this.mCenterCrop = z;
        this.mResetScaleOnLayout = z;
    }

    public void setDefaultPosition() {
        setDefaultPosition(true);
    }

    public void setPadding(int i2, int i7, int i8, int i10) {
        this.mResetScaleOnLayout = isMinScale();
    }

    public void setPinchShrinkSupport(OnViewerExitGestureListener onViewerExitGestureListener) {
        this.mExitListener = onViewerExitGestureListener;
    }

    public void setScaleEndListener(ScaleEndListener scaleEndListener) {
        this.mScaleEndListener = scaleEndListener;
    }

    public void setScaleRelative(float f) {
        setStartValueForScale();
        float ensureScale = ensureScale(this.mView.getView().getScaleX() * f);
        zoomTo(ensureScale, new PointF(calculateX(this.mScaleCoordinateInfo.startFocusX, ensureScale), calculateY(this.mScaleCoordinateInfo.startFocusY, ensureScale)));
    }

    public void setTranslationListener(OnTranslationListener onTranslationListener) {
        this.mOnTranslationListener = onTranslationListener;
    }

    public void zoomTo(float f, PointF pointF) {
        View mainView = getMainView();
        float scaleX = mainView.getScaleX();
        float x9 = mainView.getX();
        float y = mainView.getY();
        PointF ensureBoundary = ensureBoundary(pointF.x, pointF.y, f);
        ValueAnimator ofFloat = ValueAnimatorIgnoreSetting.ofFloat(0.0f, 1.0f);
        ofFloat.setDuration(160);
        ofFloat.setInterpolator(PathInterpolator.create(PathInterpolator.Type.TYPE_SINE_IN_OUT_70));
        ofFloat.addUpdateListener(new q(this, scaleX, f, mainView, x9, ensureBoundary, y));
        ofFloat.addListener(getZoomAnimatorListener());
        ofFloat.start();
    }

    public void zoomToWithoutAnimation(float f, PointF pointF) {
        View mainView = getMainView();
        PointF ensureBoundary = ensureBoundary(pointF.x, pointF.y, f);
        mainView.setScaleX(f);
        mainView.setScaleY(f);
        mainView.setX(ensureBoundary.x);
        mainView.setY(ensureBoundary.y);
        this.mDelegateListener.getClipViewDelegate().onDraw();
    }

    public void setDefaultPosition(boolean z) {
        if (getParentRect().height() <= 0.0f || getMainView().getHeight() <= 0) {
            getMainView().setScaleX(minScale());
            getMainView().setScaleY(minScale());
            this.mDelegateListener.getClipViewDelegate().onDraw();
        } else if (z) {
            zoomTo(minScale(), getDefaultPosition());
        } else {
            zoomToWithoutAnimation(minScale(), getDefaultPosition());
        }
    }
}
