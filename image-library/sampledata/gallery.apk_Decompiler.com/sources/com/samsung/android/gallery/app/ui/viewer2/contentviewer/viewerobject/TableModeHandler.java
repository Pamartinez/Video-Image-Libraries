package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.view.WindowInsets;
import android.view.animation.Animation;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.BottomSheetState;
import com.samsung.android.gallery.app.ui.viewer2.common.state.OverlayViewState;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimationListener;
import com.samsung.android.gallery.widget.animations.viewer.ViewerBottomMarginAnimation;
import com.samsung.android.gallery.widget.animator.PathInterpolator;
import com.samsung.android.gallery.widget.photoview.PhotoView;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import o4.a;
import v7.t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TableModeHandler extends ViewerObject implements FragmentLifeCycle {
    private final ViewerBottomMarginAnimation mBottomMarginAnimation = new ViewerBottomMarginAnimation();
    /* access modifiers changed from: private */
    public View mContainer;
    private IMediaPlayerView mMediaView;
    private PhotoView mPhotoView;

    private boolean blockUpdateLayout(boolean z) {
        if (z || !BottomSheetState.isExpanded(this.mModel.getContainerModel())) {
            return false;
        }
        return true;
    }

    private int getBottomMargin(boolean z) {
        if (z) {
            return DeviceInfo.getDisplayHeight(this.mContainer.getContext()) / 2;
        }
        if (!LocationKey.isSecondDepthGroupPanelView(this.mModel.getContainerModel().getLocationKey()) || !this.mModel.getSystemUi().isPortrait()) {
            return 0;
        }
        return this.mModel.getContainerModel().getOverlaySize().bottom;
    }

    private int getRightMargin(boolean z, View view) {
        int i2 = 0;
        if (!LocationKey.isSecondDepthGroupPanelView(this.mModel.getContainerModel().getLocationKey()) || this.mModel.getContainerModel().isTableMode() || !z) {
            return 0;
        }
        if (!this.mModel.getStateHelper().isTargetViewHalfOfWidth(ResourceCompat.isLandscape((Context) this.mModel.getActivity()))) {
            return view.getWidth() - view.getHeight();
        }
        WindowInsets rootWindowInsets = view.getRootWindowInsets();
        if (rootWindowInsets != null) {
            i2 = rootWindowInsets.getStableInsetRight() / 2;
        }
        return (view.getWidth() / 2) + i2;
    }

    private int getTopMargin(View view, boolean z, boolean z3) {
        if (!LocationKey.isSecondDepthGroupPanelView(this.mModel.getContainerModel().getLocationKey())) {
            return ViewMarginUtils.getTopMargin(view);
        }
        if (z || z3) {
            return 0;
        }
        return SystemUi.getToolBarHeightWithPadding(this.mModel.getContext()) + this.mModel.getSystemUi().getStatusBarHeight(this.mModel.getActivity());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mContainer = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        this.mPhotoView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$2(Object[] objArr) {
        this.mMediaView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateLayout$4(Void voidR) {
        this.mActionInvoker.invoke(ViewerAction.ON_TABLE_MODE_ANIMATION_UPDATED, new Object[0]);
    }

    /* access modifiers changed from: private */
    public void resetScale() {
        PhotoView photoView = this.mPhotoView;
        if (photoView != null) {
            photoView.resetScaleAndCenter();
            if (this.mModel.getContainerModel().isTableMode()) {
                this.mPhotoView.blockPendingScale(false);
            }
        }
        IMediaPlayerView iMediaPlayerView = this.mMediaView;
        if (iMediaPlayerView != null) {
            iMediaPlayerView.resetScale();
        }
    }

    private boolean supportAnimation(int i2) {
        if (!this.mModel.isFragmentResumed() || i2 != 0 || BottomSheetState.isExpanded(this.mModel) || OverlayViewState.isShow((OverlayViewState.StateListener) this.mModel)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    /* renamed from: updateLayout */
    public void lambda$onTableModeChanged$3(boolean z, int i2) {
        if (!blockUpdateLayout(z)) {
            int topMargin = getTopMargin(this.mContainer, z, ResourceCompat.isLandscape((Context) this.mModel.getActivity()));
            int bottomMargin = getBottomMargin(z);
            int rightMargin = getRightMargin(ResourceCompat.isLandscape((Context) this.mModel.getActivity()), this.mContainer.getRootView());
            resetScale();
            if (supportAnimation(i2)) {
                this.mBottomMarginAnimation.setDuration(450);
                this.mBottomMarginAnimation.setInterpolator(PathInterpolator.create(0.22f, 0.25f, 0.0f, 1.0f));
                this.mBottomMarginAnimation.startAnimation(this.mContainer, topMargin, bottomMargin, rightMargin);
                this.mBottomMarginAnimation.withAnimationCallback(new a(23, this));
                this.mBottomMarginAnimation.setAnimationListener(new SimpleAnimationListener() {
                    /* access modifiers changed from: private */
                    public /* synthetic */ void lambda$onAnimationEnd$0() {
                        TableModeHandler.this.resetScale();
                    }

                    public void onAnimationEnd(Animation animation) {
                        ViewUtils.post(TableModeHandler.this.mContainer, new b(this));
                    }
                });
                return;
            }
            if (this.mBottomMarginAnimation.isAnimationRunning()) {
                this.mContainer.clearAnimation();
            }
            this.mBottomMarginAnimation.applyManualTransformation(this.mContainer, topMargin, bottomMargin, rightMargin);
        }
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.CONTENT_CONTAINER, new t(this, 0));
        this.mActionInvoker.add(ViewerAction.IMAGE_PHOTO_VIEW, new t(this, 1));
        this.mActionInvoker.add(ViewerAction.MEDIA_VIEW, new t(this, 2));
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (this.mBottomMarginAnimation.isAnimationRunning()) {
            lambda$onTableModeChanged$3(this.mModel.getContainerModel().isTableMode(), 1);
        }
    }

    public void onTableModeChanged(boolean z, int i2) {
        if (this.mContainer.getRootView().getRootWindowInsets() == null) {
            ViewUtils.post(this.mContainer.getRootView(), new M6.a(this, z, i2));
        } else {
            lambda$onTableModeChanged$3(z, i2);
        }
    }

    public void onViewAttached() {
        super.onViewAttached();
        lambda$onTableModeChanged$3(this.mModel.getContainerModel().isTableMode(), 1);
    }

    public void onViewConfirm() {
        super.onViewConfirm();
        lambda$onTableModeChanged$3(this.mModel.getContainerModel().isTableMode(), 1);
    }
}
