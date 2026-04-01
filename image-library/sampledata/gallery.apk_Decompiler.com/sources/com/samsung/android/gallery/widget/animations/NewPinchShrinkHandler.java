package com.samsung.android.gallery.widget.animations;

import A.a;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import i.C0212a;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class NewPinchShrinkHandler extends SimpleDragShrinkHandler implements ScaleGestureDetector.OnScaleGestureListener {
    private float mCurrentSpan;
    private final ScaleGestureDetector mDetector;
    private float mFirstSpan;
    private float mPrevSpan;
    private long mStartTime;

    public NewPinchShrinkHandler(SimpleShrinkView simpleShrinkView, ListViewHolder listViewHolder) {
        super(simpleShrinkView, listViewHolder);
        this.TARGET_SHRINK_SCALE = 0.6f;
        this.mDetector = new ScaleGestureDetector(simpleShrinkView.mActivity, this);
    }

    private int computePinchDirection(float f) {
        float f5 = this.mCurrentSpan;
        this.mCurrentSpan = f;
        float f8 = this.mPrevSpan - f;
        if (Math.abs(f8) <= 1.0f) {
            return 0;
        }
        this.mPrevSpan = f5;
        if (f8 > 0.0f) {
            return -1;
        }
        return 1;
    }

    private float getCurrentSpan(ScaleGestureDetector scaleGestureDetector) {
        int i2;
        float currentSpan = scaleGestureDetector.getCurrentSpan();
        if (Math.abs(currentSpan - this.mCurrentSpan) <= 50.0f) {
            return currentSpan;
        }
        float f = this.mCurrentSpan;
        if (currentSpan - f < 0.0f) {
            i2 = -1;
        } else {
            i2 = 1;
        }
        return (((float) i2) * 50.0f) + f;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDragMove$0(float f, View view) {
        if (f > 0.01f) {
            if (view.getVisibility() != 0) {
                view.setVisibility(0);
            }
            View view2 = this.mView.mTopView;
            if (!(view2 == null || view2.getVisibility() == 0)) {
                this.mView.mTopView.setVisibility(0);
            }
            view.setAlpha(f);
            ViewUtils.setAlpha(this.mView.mTopView, f);
            if (this.mIsQuickView) {
                setSystemUiWithAlpha();
                return;
            }
            return;
        }
        ViewUtils.setVisibleOrGone(view, false);
        ViewUtils.setVisibleOrGone(this.mView.mTopView, false);
    }

    private boolean onDragMove(View view, float f, float f5) {
        int i2;
        SimpleShrinkView simpleShrinkView = this.mView;
        ImageView imageView = simpleShrinkView.mShrinkView;
        ImageView imageView2 = simpleShrinkView.mShrinkBackgroundView;
        if (imageView == null || imageView2 == null) {
            Log.ste(this.TAG, "onDragMove failed. null view");
            return true;
        }
        double min = (double) Math.min(1.0f, Math.max(this.TARGET_SHRINK_SCALE, this.mCurrentSpan / this.mFirstSpan));
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.width = (int) (((double) this.mFromW) * min);
        layoutParams.height = (int) (((double) this.mFromH) * min);
        float f8 = 65.0f / (1.0f - this.TARGET_SHRINK_SCALE);
        float f10 = (float) (1.0d - min);
        this.mView.scaleRatio = (double) f10;
        float f11 = 0.0f;
        this.mCornerRadius = C0212a.a(f8, 0.0f, f10, 0.0f);
        Log.stv(this.TAG, "onDragMove" + Logger.v(Float.valueOf(this.mCornerRadius), Float.valueOf(f10), Float.valueOf(this.mFromW), Integer.valueOf(layoutParams.width)));
        imageView.invalidateOutline();
        imageView.setLayoutParams(layoutParams);
        float width = ((float) (imageView.getWidth() - layoutParams.width)) / 2.0f;
        if (Features.isEnabled(Features.IS_RTL)) {
            i2 = -1;
        } else {
            i2 = 1;
        }
        imageView.setX(((imageView.getX() + f) - this.mPrevX) + (width * ((float) i2)));
        imageView.setY(((imageView.getY() + f5) - this.mPrevY) + (((float) (imageView.getHeight() - layoutParams.height)) / 2.0f));
        if (min > 0.8999999761581421d) {
            f11 = Math.max(((float) Math.cos(((min * 3.141592653589793d) * 0.5d) / 0.3d)) * 2.0f, 0.0f);
        }
        imageView2.post(new f(this, imageView2, f11));
        this.mPrevY = f5;
        this.mPrevX = f;
        return true;
    }

    /* access modifiers changed from: private */
    public void onRotationGesture(float f) {
        ImageView imageView = this.mView.mShrinkView;
        if (imageView != null) {
            imageView.setPivotX(((float) imageView.getWidth()) * 0.5f);
            imageView.setPivotY(((float) imageView.getHeight()) * 0.5f);
            imageView.setRotation(imageView.getRotation() - f);
        }
    }

    /* access modifiers changed from: private */
    public boolean onShrinkGesture(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0 || actionMasked == 5 || this.mState.get() >= 2 || !this.mDetector.onTouchEvent(motionEvent)) {
            return false;
        }
        if (actionMasked == 2) {
            onMove(this.mDetector);
            return false;
        } else if ((actionMasked != 1 && actionMasked != 3 && actionMasked != 6) || this.mStartTime != 0) {
            return false;
        } else {
            Log.e(this.TAG, "scale failed");
            onShrinkError();
            return false;
        }
    }

    public ArrayList<Animator> createAnimatorSet(final View view, View view2) {
        ArrayList<Animator> createAnimatorSet = super.createAnimatorSet(view, view2);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        final float rotation = view.getRotation();
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            final float fromRadius;
            final float toRadius = 0.0f;

            {
                this.fromRadius = NewPinchShrinkHandler.this.mCornerRadius;
            }

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                try {
                    NewPinchShrinkHandler newPinchShrinkHandler = NewPinchShrinkHandler.this;
                    float f = this.fromRadius;
                    newPinchShrinkHandler.mCornerRadius = C0212a.a(0.0f, this.fromRadius, valueAnimator.getAnimatedFraction(), f);
                    view.invalidateOutline();
                    View view = view;
                    view.setPivotX(((float) view.getWidth()) * 0.5f);
                    View view2 = view;
                    view2.setPivotY(((float) view2.getHeight()) * 0.5f);
                    float f5 = rotation;
                    view.setRotation((1.0f - valueAnimator.getAnimatedFraction()) * f5);
                } catch (Exception e) {
                    a.s(e, new StringBuilder("onAnimationUpdate failed "), NewPinchShrinkHandler.this.TAG);
                }
            }
        });
        createAnimatorSet.add(ofFloat);
        return createAnimatorSet;
    }

    public void enableDragMasking() {
        super.enableDragMasking();
        this.mView.mMaskingRate = 1.0f;
    }

    public RectF getAnimationFromRect(View view) {
        return new RectF(view.getX(), view.getY(), view.getX() + ((float) view.getWidth()), view.getY() + ((float) view.getHeight()));
    }

    public boolean isReturn(float f, float f5) {
        long currentTimeMillis = System.currentTimeMillis() - this.mStartTime;
        ImageView imageView = this.mView.mShrinkBackgroundView;
        if (imageView == null || imageView.getAlpha() < 0.3f || currentTimeMillis <= 300) {
            return false;
        }
        return true;
    }

    public boolean onMove(ScaleGestureDetector scaleGestureDetector) {
        scaleGestureDetector.getCurrentSpan();
        float currentSpan = getCurrentSpan(scaleGestureDetector);
        float f = currentSpan - this.mPrevSpan;
        if (this.mState.get() < 1) {
            if (Math.abs(f) > 1.0f) {
                onDragBegin(this.mView.getDecorView(), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
            }
            return true;
        }
        float currentSpan2 = getCurrentSpan(scaleGestureDetector);
        this.mCurrentSpan = currentSpan2;
        this.mFirstSpan = Math.max(this.mFirstSpan, currentSpan2);
        computePinchDirection(currentSpan);
        onDragMove(this.mView.getDecorView(), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
        return true;
    }

    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        return true;
    }

    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
        Log.st(this.TAG, "onScaleBegin");
        this.mStartTime = System.currentTimeMillis();
        return true;
    }

    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        Log.st(this.TAG, "onScaleEnd");
        this.mView.mBlackboard.erase("function://pinch_shrink_listeners");
        SimpleShrinkView simpleShrinkView = this.mView;
        if (simpleShrinkView.mShrinkView == null) {
            onShrinkFinish();
        } else {
            onDragEnd(simpleShrinkView.getDecorView(), scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
        }
    }

    public void onShrinkError() {
        onScaleBegin(this.mDetector);
        onScaleEnd(this.mDetector);
    }

    public void onShrinkFinish() {
        this.mView.mBlackboard.erase("function://pinch_shrink_listeners");
        super.onShrinkFinish();
    }

    public void showInternal() {
        ImageView imageView = this.mView.mShrinkView;
        if (imageView != null) {
            this.mFromW = (float) imageView.getWidth();
            this.mFromH = (float) imageView.getHeight();
            this.mToW = this.mFromW * this.TARGET_SHRINK_SCALE;
            this.mView.mBlackboard.publish("function://pinch_shrink_listeners", new Object[]{new d(this), new e(this)});
        }
    }

    public void updateRotation(ImageView imageView, float f, float f5) {
        imageView.setPivotX(((float) imageView.getWidth()) * 0.5f);
        imageView.setPivotY(((float) imageView.getHeight()) * 0.5f);
        imageView.setRotation((1.0f - f5) * f);
    }

    public void removeDragListener() {
    }
}
