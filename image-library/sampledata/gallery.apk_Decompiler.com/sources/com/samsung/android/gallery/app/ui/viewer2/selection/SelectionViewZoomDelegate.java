package com.samsung.android.gallery.app.ui.viewer2.selection;

import V7.b;
import V7.c;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.gesture.GestureIdentifier;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SelectionViewZoomDelegate {
    /* access modifiers changed from: private */
    public final StringCompat TAG = new StringCompat("SelectionViewZoomDelegate");
    /* access modifiers changed from: private */
    public ImageView mBackGroundView;
    /* access modifiers changed from: private */
    public final Blackboard mBlackboard;
    /* access modifiers changed from: private */
    public final Context mContext;
    private final GestureIdentifier mGestureIdentifier;
    /* access modifiers changed from: private */
    public ImageView mImageView;
    /* access modifiers changed from: private */
    public ViewGroup mParentView;
    private final Animator.AnimatorListener mResetAnimListener = new Animator.AnimatorListener() {
        public void onAnimationCancel(Animator animator) {
            Log.d(SelectionViewZoomDelegate.this.TAG, "onAnimationCancel");
        }

        public void onAnimationEnd(Animator animator) {
            Log.d(SelectionViewZoomDelegate.this.TAG, "onAnimationEnd");
            ViewUtils.removeSelf(SelectionViewZoomDelegate.this.mImageView);
            ViewUtils.removeSelf(SelectionViewZoomDelegate.this.mBackGroundView);
            ViewUtils.removeSelf(SelectionViewZoomDelegate.this.mParentView);
        }

        public void onAnimationStart(Animator animator) {
            Log.d(SelectionViewZoomDelegate.this.TAG, "onAnimationStart");
        }

        public void onAnimationRepeat(Animator animator) {
        }
    };
    /* access modifiers changed from: private */
    public int mStartX;
    /* access modifiers changed from: private */
    public int mStartY;
    /* access modifiers changed from: private */
    public final ImageView mViewHolderImageView;

    public SelectionViewZoomDelegate(Context context, ImageView imageView) {
        this.mContext = context;
        this.mBlackboard = Blackboard.getInstance(context.toString());
        this.mViewHolderImageView = imageView;
        this.mGestureIdentifier = initGestureIdentifier();
    }

    private GestureIdentifier initGestureIdentifier() {
        return new GestureIdentifier(this.mContext, new GestureIdentifier.SimpleGestureListener() {
            private void initBackgroundView() {
                ViewUtils.removeSelf(SelectionViewZoomDelegate.this.mBackGroundView);
                SelectionViewZoomDelegate.this.mBackGroundView = new ImageView(SelectionViewZoomDelegate.this.mContext);
                SelectionViewZoomDelegate.this.mBackGroundView.setBackgroundResource(R.color.daynight_default_background);
                SelectionViewZoomDelegate.this.mBackGroundView.setAlpha(0.7f);
                Optional.ofNullable(SelectionViewZoomDelegate.this.mParentView).ifPresent(new b(3, this, (ViewGroup) SelectionViewZoomDelegate.this.mViewHolderImageView.getRootView()));
            }

            private void initImageView() {
                ViewUtils.removeSelf(SelectionViewZoomDelegate.this.mImageView);
                SelectionViewZoomDelegate.this.mImageView = new ImageView(SelectionViewZoomDelegate.this.mContext);
                SelectionViewZoomDelegate.this.mImageView.setImageDrawable(SelectionViewZoomDelegate.this.mViewHolderImageView.getDrawable());
                SelectionViewZoomDelegate.this.mImageView.setScaleType(ImageView.ScaleType.MATRIX);
                SelectionViewZoomDelegate.this.mImageView.setImageMatrix(SelectionViewZoomDelegate.this.mViewHolderImageView.getImageMatrix());
                int[] iArr = new int[2];
                SelectionViewZoomDelegate.this.mViewHolderImageView.getLocationInWindow(iArr);
                SelectionViewZoomDelegate.this.mStartX = iArr[0];
                SelectionViewZoomDelegate.this.mStartY = iArr[1];
                SelectionViewZoomDelegate.this.mImageView.setX((float) SelectionViewZoomDelegate.this.mStartX);
                SelectionViewZoomDelegate.this.mImageView.setY((float) SelectionViewZoomDelegate.this.mStartY);
                ViewUtils.setViewShape(SelectionViewZoomDelegate.this.mImageView, 1, SelectionViewZoomDelegate.this.mViewHolderImageView.getResources().getDimension(R.dimen.burst_shot_select_image_radius));
                SelectionViewZoomDelegate.this.mImageView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                    public void onViewAttachedToWindow(View view) {
                        SelectionViewZoomDelegate.this.mViewHolderImageView.setVisibility(4);
                    }

                    public void onViewDetachedFromWindow(View view) {
                        SelectionViewZoomDelegate.this.mViewHolderImageView.setVisibility(0);
                    }
                });
                Optional.ofNullable(SelectionViewZoomDelegate.this.mParentView).ifPresent(new a(1, this));
            }

            private void initParentView() {
                ViewUtils.removeSelf(SelectionViewZoomDelegate.this.mParentView);
                SelectionViewZoomDelegate.this.mParentView = new RelativeLayout(SelectionViewZoomDelegate.this.mContext);
                SelectionViewZoomDelegate.this.mParentView.setLayoutDirection(0);
                ViewGroup viewGroup = (ViewGroup) SelectionViewZoomDelegate.this.mViewHolderImageView.getRootView();
                viewGroup.addView(SelectionViewZoomDelegate.this.mParentView, new ViewGroup.LayoutParams(viewGroup.getWidth(), viewGroup.getHeight()));
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$initBackgroundView$1(ViewGroup viewGroup, ViewGroup viewGroup2) {
                viewGroup2.addView(SelectionViewZoomDelegate.this.mBackGroundView, new ViewGroup.LayoutParams(viewGroup.getWidth(), viewGroup.getHeight()));
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void lambda$initImageView$0(ViewGroup viewGroup) {
                viewGroup.addView(SelectionViewZoomDelegate.this.mImageView, new ViewGroup.LayoutParams(SelectionViewZoomDelegate.this.mViewHolderImageView.getWidth(), SelectionViewZoomDelegate.this.mViewHolderImageView.getHeight()));
            }

            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                if (SelectionViewZoomDelegate.this.mImageView == null) {
                    return false;
                }
                SelectionViewZoomDelegate.this.setScale(Math.max(1.0f, SelectionViewZoomDelegate.this.mImageView.getScaleX() * scaleGestureDetector.getScaleFactor()));
                return true;
            }

            public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
                Log.d(SelectionViewZoomDelegate.this.TAG, "onScaleBegin");
                initParentView();
                initBackgroundView();
                initImageView();
                return true;
            }

            public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
                Log.d(SelectionViewZoomDelegate.this.TAG, "onScaleEnd");
                SelectionViewZoomDelegate.this.mBlackboard.erase("function://pinch_shrink_listeners");
                SelectionViewZoomDelegate selectionViewZoomDelegate = SelectionViewZoomDelegate.this;
                selectionViewZoomDelegate.resetScale(1.0f, (float) selectionViewZoomDelegate.mStartX, (float) SelectionViewZoomDelegate.this.mStartY);
            }

            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f5) {
                if (SelectionViewZoomDelegate.this.mImageView == null) {
                    return false;
                }
                SelectionViewZoomDelegate.this.mImageView.setX(SelectionViewZoomDelegate.this.mImageView.getX() - f);
                SelectionViewZoomDelegate.this.mImageView.setY(SelectionViewZoomDelegate.this.mImageView.getY() - f5);
                return true;
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$resetScale$0(float f, float f5, float f8, float f10, float f11, float f12, ValueAnimator valueAnimator) {
        Float f13 = (Float) valueAnimator.getAnimatedValue();
        float floatValue = f13.floatValue();
        float floatValue2 = f8 - (f13.floatValue() * (f8 - f10));
        float floatValue3 = f13.floatValue();
        setScale(f - (floatValue * (f - f5)));
        setPosition(floatValue2, f11 - (floatValue3 * (f11 - f12)));
    }

    /* access modifiers changed from: private */
    public boolean onShrinkGesture(MotionEvent motionEvent) {
        this.mGestureIdentifier.onTouchEvent(motionEvent);
        return true;
    }

    /* access modifiers changed from: private */
    public void resetScale(float f, float f5, float f8) {
        ImageView imageView = this.mImageView;
        if (imageView != null) {
            float scaleX = imageView.getScaleX();
            float x9 = this.mImageView.getX();
            float y = this.mImageView.getY();
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            ofFloat.setDuration(160);
            ofFloat.setInterpolator(PathInterpolator.create(PathInterpolator.Type.TYPE_SINE_IN_OUT_70));
            ofFloat.addUpdateListener(new c(this, scaleX, f, x9, f5, y, f8));
            ofFloat.addListener(this.mResetAnimListener);
            ofFloat.start();
        }
    }

    private void setPosition(float f, float f5) {
        ImageView imageView = this.mImageView;
        if (imageView != null) {
            imageView.setX(f);
            this.mImageView.setY(f5);
        }
    }

    /* access modifiers changed from: private */
    public void setScale(float f) {
        ImageView imageView = this.mImageView;
        if (imageView != null) {
            imageView.setScaleX(f);
            this.mImageView.setScaleY(f);
        }
    }

    public void start(int i2) {
        this.TAG.setTag(Integer.valueOf(i2));
        Log.d(this.TAG, "start");
        this.mBlackboard.publish("function://pinch_shrink_listeners", new Object[]{new b(this), null});
    }
}
