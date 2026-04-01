package com.samsung.android.gallery.app.ui.viewer2.remaster.ondemand;

import B2.h;
import U7.b;
import U7.c;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RemasterFinishingViewHandler extends ViewerObject {
    private final Animator.AnimatorListener mAnimatorListener = new SimpleAnimatorListener() {
        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$onAnimationEnd$0() {
            RemasterFinishingViewHandler.this.mActionInvoker.invoke(ViewerAction.ON_HANDLER_MOVING_ANIMATION_ENDED, new Object[0]);
        }

        public void onAnimationEnd(Animator animator) {
            RemasterFinishingViewHandler remasterFinishingViewHandler = RemasterFinishingViewHandler.this;
            remasterFinishingViewHandler.startSecondaryAnim(remasterFinishingViewHandler.mHeader);
            ViewUtils.post(RemasterFinishingViewHandler.this.mContentContainer, new a(this));
        }

        public void onAnimationStart(Animator animator) {
            RemasterFinishingViewHandler remasterFinishingViewHandler = RemasterFinishingViewHandler.this;
            remasterFinishingViewHandler.prepareSecondaryAnim(remasterFinishingViewHandler.mHeader);
            ViewUtils.setVisibility(RemasterFinishingViewHandler.this.mSeparatorView, 0);
        }
    };
    private final ValueAnimator.AnimatorUpdateListener mAnimatorUpdateListener = new h(5, this);
    /* access modifiers changed from: private */
    public View mContentContainer;
    /* access modifiers changed from: private */
    public View mHeader;
    private boolean mIsViewReady = false;
    /* access modifiers changed from: private */
    public View mSeparatorView;
    private final ValueAnimator mSlideAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 0.5f});
    private CoordinatorLayout mViewerLayout;

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mViewerLayout = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$3(ValueAnimator valueAnimator) {
        this.mActionInvoker.invoke(ViewerAction.REMASTER_VIEW_SCROLLED, valueAnimator.getAnimatedValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onPreparedRemasterView$1() {
        this.mIsViewReady = true;
        if (!this.mModel.isViewConfirmed()) {
            return;
        }
        if (this.mModel.isFirstView()) {
            start();
        } else {
            show();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$show$2() {
        this.mActionInvoker.invoke(ViewerAction.REMASTER_VIEW_SCROLLED, Float.valueOf(0.5f));
        this.mActionInvoker.invoke(ViewerAction.ON_HANDLER_MOVING_ANIMATION_ENDED, new Object[0]);
    }

    /* access modifiers changed from: private */
    public void onPreparedRemasterView(Object... objArr) {
        this.mThread.runOnUiThread(new b(this, 1));
    }

    /* access modifiers changed from: private */
    public void onReplacedHandlerLayout(Object... objArr) {
        this.mSeparatorView = this.mViewerLayout.findViewById(R.id.effect_handler_view);
        this.mHeader = this.mViewerLayout.findViewById(R.id.remaster_header_view);
    }

    /* access modifiers changed from: private */
    public void prepareSecondaryAnim(View view) {
        if (view != null) {
            view.setVisibility(4);
            view.setAlpha(0.0f);
            view.setScaleX(0.0f);
            view.setScaleY(0.0f);
        }
    }

    private void show() {
        SimpleAnimator.setAlphaVisible(this.mSeparatorView);
        SimpleAnimator.setAlphaVisible(this.mHeader);
        ViewUtils.post(this.mContentContainer, new b(this, 0));
    }

    private void start() {
        if (!this.mSlideAnimator.isStarted()) {
            this.mSlideAnimator.setDuration(500);
            this.mSlideAnimator.setInterpolator(PathInterpolator.create(0.25f, 0.5f, 0.0f, 1.0f));
            this.mSlideAnimator.addListener(this.mAnimatorListener);
            this.mSlideAnimator.addUpdateListener(this.mAnimatorUpdateListener);
            this.mSlideAnimator.start();
        }
    }

    /* access modifiers changed from: private */
    public void startSecondaryAnim(View view) {
        if (view != null) {
            view.setVisibility(0);
            view.animate().setDuration(500).alpha(1.0f).scaleX(1.0f).scaleY(1.0f).start();
        }
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.VIEWER_LAYOUT, new c(this, 0));
        this.mActionInvoker.add(ViewerAction.ON_READY_REMASTER_VIEW, new c(this, 1));
        this.mActionInvoker.add(ViewerAction.REPLACED_HANDLER_LAYOUT, new c(this, 2));
    }

    public void onInitialized() {
        this.mSeparatorView = this.mViewerLayout.findViewById(R.id.effect_handler_view);
        this.mHeader = this.mViewerLayout.findViewById(R.id.remaster_header_view);
        this.mContentContainer = this.mViewerLayout.findViewById(R.id.content_container);
    }

    public void onViewConfirm() {
        super.onViewConfirm();
        if (!this.mIsViewReady) {
            return;
        }
        if (this.mModel.isFirstView()) {
            start();
        } else {
            show();
        }
    }

    public void onViewDetached() {
        ViewUtils.setVisibility(this.mSeparatorView, 8);
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        this.mIsViewReady = false;
    }
}
