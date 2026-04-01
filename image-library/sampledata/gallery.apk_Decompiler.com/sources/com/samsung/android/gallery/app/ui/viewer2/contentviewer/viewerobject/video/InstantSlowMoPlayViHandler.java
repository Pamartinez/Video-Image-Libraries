package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video;

import H7.h;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.animator.AlphaAnimator;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.animator.ScaleAnimator;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class InstantSlowMoPlayViHandler extends ViewerObject implements FragmentLifeCycle {
    private ViewGroup mContentContainer;
    protected RelativeLayout mInitCircleContainer;
    /* access modifiers changed from: private */
    public ImageView mInitCircleView;
    private boolean mInstantSlowMoPlayOn;
    private int mLayoutDirection;
    private int mOffsetY = 0;
    private View mPlayView;
    private float mStartX = -1.0f;
    private float mStartY = -1.0f;

    private int calcDistance() {
        float f;
        float f5;
        RectF viewRect = ViewUtils.getViewRect(this.mInitCircleContainer);
        if (!RectUtils.isValidRect(viewRect)) {
            ViewGroup.LayoutParams layoutParams = this.mInitCircleContainer.getLayoutParams();
            viewRect.right = viewRect.left + ((float) layoutParams.width);
            viewRect.bottom = viewRect.top + ((float) layoutParams.height);
        }
        float f8 = this.mStartX;
        float f10 = this.mStartY;
        if (f8 >= viewRect.centerX() && f10 < viewRect.centerY()) {
            f = viewRect.left;
            f5 = viewRect.bottom;
        } else if (f8 >= viewRect.centerX() && f10 >= viewRect.centerY()) {
            f = viewRect.left;
            f5 = viewRect.top;
        } else if (f8 >= viewRect.centerX() || f10 < viewRect.centerY()) {
            f = viewRect.right;
            f5 = viewRect.bottom;
        } else {
            f = viewRect.right;
            f5 = viewRect.top;
        }
        int abs = Math.abs((int) (f - f8));
        int abs2 = Math.abs((int) (f5 - f10));
        return ((int) Math.sqrt(Math.pow((double) abs2, 2.0d) + Math.pow((double) abs, 2.0d))) * 2;
    }

    /* access modifiers changed from: private */
    public void handleInstantSlowMoPlayVi(Object[] objArr) {
        this.mInstantSlowMoPlayOn = objArr[0].booleanValue();
        this.mStartX = objArr[1].floatValue();
        this.mStartY = objArr[2].floatValue();
        if (this.mInstantSlowMoPlayOn) {
            startInitIncreaseCircle();
            SeApiCompat.performHapticFeedback(this.mModel.getContext(), 1);
        }
    }

    private void initView() {
        if (this.mPlayView == null) {
            this.mPlayView = this.mContentContainer.findViewById(R.id.video_view_layout);
        }
        setInitCircleContainer();
        setInitCircleView();
    }

    private boolean isOnGroupPanelView() {
        return LocationKey.isSecondDepthGroupPanelView(this.mModel.getContainerModel().getLocationKey());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mContentContainer = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        resetView();
    }

    private void resetView() {
        this.mPlayView = null;
        this.mInitCircleView = null;
        this.mInitCircleContainer = null;
    }

    private void setInitCircleContainer() {
        if (this.mInitCircleContainer == null) {
            this.mInitCircleContainer = new RelativeLayout(this.mModel.getContext());
            this.mInitCircleContainer.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            this.mLayoutDirection = this.mContentContainer.getLayoutDirection();
            this.mContentContainer.setLayoutDirection(0);
            this.mContentContainer.addView(this.mInitCircleContainer);
        }
        updateInitCircleContainerSizeAndPosition();
    }

    private void setInitCircleView() {
        if (this.mInitCircleView == null) {
            ImageView imageView = new ImageView(this.mModel.getContext());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            imageView.setImageBitmap(BitmapUtils.blur(this.mModel.getContext(), BitmapUtils.getBitmapFromDrawable(this.mModel.getContext().getDrawable(R.drawable.video_instant_slow_mo_init_gradient_effect))));
            this.mInitCircleView = imageView;
            this.mInitCircleContainer.addView(imageView);
        }
        int width = this.mInitCircleView.getWidth();
        if (width <= 0) {
            width = this.mModel.getContext().getResources().getDimensionPixelOffset(R.dimen.video_instant_slow_mo_circle_size);
        }
        float f = ((float) width) / 2.0f;
        this.mInitCircleView.setX((this.mStartX - this.mInitCircleContainer.getX()) - f);
        this.mInitCircleView.setY(((this.mStartY - this.mInitCircleContainer.getY()) - ((float) this.mOffsetY)) - f);
    }

    private void startInitIncreaseCircle() {
        initView();
        int dimensionPixelOffset = this.mModel.getContext().getResources().getDimensionPixelOffset(R.dimen.video_instant_slow_mo_inner_circle_size);
        ScaleAnimator scaleAnimator = new ScaleAnimator((View) this.mInitCircleView, 0.0f, ((float) calcDistance()) / ((float) dimensionPixelOffset));
        scaleAnimator.setInterpolator(PathInterpolator.create(0.22f, 0.25f, 0.0f, 1.0f));
        AlphaAnimator alphaAnimator = new AlphaAnimator((View) this.mInitCircleView, 0.8f, 0.0f);
        alphaAnimator.setInterpolator(PathInterpolator.create(0.33f, 0.0f, 0.4f, 1.0f));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(1000);
        animatorSet.playTogether(new Animator[]{scaleAnimator, alphaAnimator});
        animatorSet.addListener(new SimpleAnimatorListener() {
            public void onAnimationEnd(Animator animator, boolean z) {
                ViewUtils.setVisibleOrGone(InstantSlowMoPlayViHandler.this.mInitCircleView, false);
            }

            public void onAnimationStart(Animator animator) {
                ViewUtils.setVisibleOrGone(InstantSlowMoPlayViHandler.this.mInitCircleView, true);
            }
        });
        animatorSet.start();
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.CONTENT_CONTAINER, new h(this, 0));
        this.mActionInvoker.add(ViewerAction.INSTANT_SLOW_MO_PLAY, new h(this, 1));
        this.mActionInvoker.add(ViewerAction.FOLD_STATE_CHANGED, new h(this, 2));
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        this.mContentContainer.setLayoutDirection(this.mLayoutDirection);
        this.mStartY = -1.0f;
        this.mStartX = -1.0f;
        this.mInstantSlowMoPlayOn = false;
    }

    public void updateInitCircleContainerSizeAndPosition() {
        int i2;
        int i7;
        int i8;
        float f;
        View view = this.mPlayView;
        if (view != null) {
            int[] iArr = new int[2];
            view.getLocationInWindow(iArr);
            int[] iArr2 = new int[2];
            this.mContentContainer.getLocationInWindow(iArr2);
            boolean isPortrait = this.mModel.getSystemUi().isPortrait();
            boolean isOnGroupPanelView = isOnGroupPanelView();
            if (isOnGroupPanelView) {
                i2 = iArr2[1];
            } else {
                i2 = 0;
            }
            this.mOffsetY = i2;
            if (isOnGroupPanelView) {
                i7 = this.mContentContainer.getWidth();
            } else {
                i7 = DeviceInfo.getDisplayWidth(this.mModel.getContext());
            }
            if (isOnGroupPanelView) {
                i8 = this.mContentContainer.getHeight();
            } else {
                i8 = DeviceInfo.getDisplayHeight(this.mModel.getContext());
            }
            ViewGroup.LayoutParams layoutParams = this.mInitCircleContainer.getLayoutParams();
            int i10 = iArr[0];
            float f5 = 0.0f;
            if (i10 <= 0 && iArr[1] - this.mOffsetY <= 0) {
                layoutParams.width = i7;
                layoutParams.height = i8;
                this.mInitCircleContainer.setX(0.0f);
                this.mInitCircleContainer.setY(0.0f);
            } else if (i10 <= 0) {
                layoutParams.width = i7;
                layoutParams.height = Math.min((int) (this.mPlayView.getScaleY() * ((float) this.mPlayView.getHeight())), i8);
                this.mInitCircleContainer.setX(0.0f);
                this.mInitCircleContainer.setY((float) (iArr[1] - this.mOffsetY));
            } else if (iArr[1] - this.mOffsetY <= 0) {
                layoutParams.width = Math.min((int) (this.mPlayView.getScaleX() * ((float) this.mPlayView.getWidth())), i7);
                layoutParams.height = i8;
                this.mInitCircleContainer.setX((float) iArr[0]);
                this.mInitCircleContainer.setY(0.0f);
            } else {
                layoutParams.width = this.mPlayView.getWidth();
                layoutParams.height = this.mPlayView.getHeight();
                RelativeLayout relativeLayout = this.mInitCircleContainer;
                if (isPortrait) {
                    f = 0.0f;
                } else {
                    f = this.mPlayView.getX();
                }
                relativeLayout.setX(f);
                RelativeLayout relativeLayout2 = this.mInitCircleContainer;
                if (isPortrait) {
                    f5 = this.mPlayView.getY();
                }
                relativeLayout2.setY(f5);
            }
            this.mInitCircleContainer.setLayoutParams(layoutParams);
        }
    }
}
