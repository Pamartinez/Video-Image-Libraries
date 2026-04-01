package com.samsung.android.gallery.app.ui.viewer2.aiedit;

import android.content.res.Configuration;
import android.graphics.PointF;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.OverlayViewState;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.widget.AiProcessingEffectLayout;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimationListener;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.photoview.PhotoView;
import com.samsung.android.gallery.widget.utils.AnimatedVectorDrawableUtils;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.motionphoto.utils.ex.b;
import com.sec.android.gallery3d.R;
import g6.g;
import g7.h;
import g7.i;
import g7.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AiProcessingViewHandler extends ViewerObject implements FragmentLifeCycle {
    private View mContainer;
    /* access modifiers changed from: private */
    public View mLayout;
    private PhotoView mPhotoView;
    private CoordinatorLayout mViewerLayout;

    /* access modifiers changed from: private */
    public void hideAiProcessingEffect() {
        if (ViewUtils.isVisible(this.mLayout)) {
            ((AiProcessingEffectLayout) this.mLayout.findViewById(R.id.effect_view)).stopEffect();
            this.mLayout.setVisibility(8);
            setPhotoViewPositionChangedListener(true);
            AnimatedVectorDrawableUtils.stop((ImageView) this.mLayout.findViewById(R.id.effect_avd));
            this.mModel.setOverlayViewState(OverlayViewState.hide);
        }
    }

    private void initView() {
        if (this.mLayout == null) {
            this.mLayout = this.mViewerLayout.findViewById(R.id.ai_processing_effect_layout);
        }
        View view = this.mLayout;
        if (view instanceof ViewStub) {
            this.mLayout = ((ViewStub) view).inflate();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mContainer = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        this.mViewerLayout = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$2(Object[] objArr) {
        this.mPhotoView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleBlackboardEvent$3(EventMessage eventMessage) {
        showAiProcessingEffect(eventMessage.arg1, ((Boolean) eventMessage.obj).booleanValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleBlackboardEvent$4(EventMessage eventMessage) {
        updateTextMessage(((Integer) eventMessage.obj).intValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onConfigurationChanged$5() {
        updateViewMargin(this.mModel.getContainerModel().isTableMode());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setPhotoViewPositionChangedListener$7(PointF pointF) {
        updateEffectViewSize();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ Animation lambda$showDimAnimation$6(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setInterpolator(PathInterpolator.create(PathInterpolator.Type.TYPE_SINE_IN_OUT_33));
        alphaAnimation.setAnimationListener(new SimpleAnimationListener() {
            public void onAnimationEnd(Animation animation) {
                if (ViewUtils.isVisible(AiProcessingViewHandler.this.mLayout)) {
                    ((AiProcessingEffectLayout) AiProcessingViewHandler.this.mLayout.findViewById(R.id.effect_view)).playEffect();
                }
            }
        });
        return alphaAnimation;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateEffectViewSize$8() {
        if (ViewUtils.isVisible(this.mLayout) && this.mPhotoView != null) {
            View findViewById = this.mLayout.findViewById(R.id.effect_view);
            ViewGroup.LayoutParams layoutParams = findViewById.getLayoutParams();
            RectF displayRect = this.mPhotoView.getDisplayRect();
            if (displayRect != null) {
                layoutParams.width = (int) displayRect.width();
                layoutParams.height = (int) displayRect.height();
                findViewById.setLayoutParams(layoutParams);
            }
        }
    }

    private void setPhotoViewPositionChangedListener(boolean z) {
        g gVar;
        PhotoView photoView = this.mPhotoView;
        if (photoView != null) {
            if (z) {
                gVar = null;
            } else {
                gVar = new g(1, this);
            }
            photoView.setPositionChangeListener(gVar);
        }
    }

    private void showAiProcessingEffect(int i2, boolean z) {
        initView();
        if (this.mLayout != null) {
            if (z) {
                this.mModel.setOverlayViewState(OverlayViewState.ai_processing);
            }
            ViewUtils.setVisibility(this.mLayout, 0);
            AnimatedVectorDrawableUtils.play((ImageView) this.mLayout.findViewById(R.id.effect_avd), true);
            updateTextMessage(i2);
            updateEffectViewSize();
            updateViewMargin(this.mModel.getContainerModel().isTableMode());
            setPhotoViewPositionChangedListener(false);
            showDimAnimation();
        }
    }

    private void showDimAnimation() {
        if (ViewUtils.isVisible(this.mLayout)) {
            ViewUtils.startAnimation(this.mLayout.findViewById(R.id.dim_layout), new b(21, this));
        }
    }

    private void updateEffectViewSize() {
        ViewUtils.post(this.mPhotoView, new i(this, 2));
    }

    private void updateTextMessage(int i2) {
        if (ViewUtils.isVisible(this.mLayout)) {
            TextView textView = (TextView) this.mLayout.findViewById(R.id.processing_text);
            if (i2 > 0) {
                ViewUtils.setText(textView, i2);
                ViewUtils.setVisibility(textView, 0);
                SimpleAnimator.create(textView, R.anim.viewer_ai_processing_text_anim, (Animation.AnimationListener) null);
                return;
            }
            ViewUtils.setVisibility(textView, 8);
        }
    }

    private void updateViewMargin(boolean z) {
        if (!ViewUtils.isVisible(this.mLayout)) {
            return;
        }
        if (z) {
            View view = this.mLayout;
            ViewMarginUtils.setBottomMargin(view, DeviceInfo.getDisplayHeight(view.getContext()) / 2);
            return;
        }
        View view2 = this.mContainer;
        if (view2 != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view2.getLayoutParams();
            ViewMarginUtils.setMargin(this.mLayout, Integer.valueOf(marginLayoutParams.leftMargin), Integer.valueOf(marginLayoutParams.topMargin), Integer.valueOf(marginLayoutParams.rightMargin), 0);
        }
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.CONTENT_CONTAINER, new j(this, 0));
        this.mActionInvoker.add(ViewerAction.VIEWER_LAYOUT, new j(this, 1));
        this.mActionInvoker.add(ViewerAction.IMAGE_PHOTO_VIEW, new j(this, 2));
    }

    public boolean handleBlackboardEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 3075) {
            this.mThread.runOnUiThread(new h(this, eventMessage, 0));
            return false;
        } else if (i2 == 3076) {
            this.mThread.runOnUiThread(new i(this, 0));
            return false;
        } else {
            if (i2 == 3073) {
                this.mThread.runOnUiThread(new h(this, eventMessage, 1));
            }
            return false;
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        ViewUtils.postDelayed(this.mContainer, new i(this, 1), 50);
    }

    public void onPause() {
        if (ViewUtils.isVisible(this.mLayout) && OverlayViewState.isAiProcessing(this.mModel)) {
            ((AiProcessingEffectLayout) this.mLayout.findViewById(R.id.effect_view)).stopEffect();
        }
    }

    public void onResume() {
        if (ViewUtils.isVisible(this.mLayout) && OverlayViewState.isAiProcessing(this.mModel)) {
            ((AiProcessingEffectLayout) this.mLayout.findViewById(R.id.effect_view)).playEffect();
        }
    }

    public void onTableModeChanged(boolean z, int i2) {
        updateViewMargin(z);
    }

    public void onViewDetached() {
        if (ViewUtils.isVisible(this.mLayout)) {
            hideAiProcessingEffect();
        }
    }
}
