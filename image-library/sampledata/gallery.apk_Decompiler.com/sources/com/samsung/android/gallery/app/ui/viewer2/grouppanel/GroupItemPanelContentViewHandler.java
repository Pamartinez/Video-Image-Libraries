package com.samsung.android.gallery.app.ui.viewer2.grouppanel;

import N7.a;
import N7.b;
import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.BottomSheetState;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.widget.MarginParams;
import com.samsung.android.gallery.widget.photoview.PhotoViewCompat;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerViewCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GroupItemPanelContentViewHandler extends ViewerObject implements FragmentLifeCycle {
    private View mContainer;
    private View mDecorLayout;
    private MediaPlayerViewCompat mMediaPlayerView;
    private PhotoViewCompat mPhotoView;

    private int getBottomMargin(boolean z) {
        if (this.mModel.getContainerModel().isTableMode()) {
            return DeviceInfo.getDisplayHeight(this.mContainer.getContext()) / 2;
        }
        if (z) {
            return 0;
        }
        return this.mModel.getContainerModel().getOverlaySize().bottom;
    }

    private int getRightMargin(boolean z, View view) {
        if (this.mModel.getContainerModel().isTableMode() || !z) {
            return 0;
        }
        if (this.mModel.getStateHelper().isTargetViewHalfOfWidth(ResourceCompat.isLandscape((Context) this.mModel.getActivity()))) {
            return view.getWidth() / 2;
        }
        return view.getWidth() - view.getHeight();
    }

    private int getTopMargin(boolean z) {
        if (this.mModel.getContainerModel().isTableMode() || z) {
            return 0;
        }
        return SystemUi.getToolBarHeightWithPadding(this.mModel.getContext()) + this.mModel.getSystemUi().getStatusBarHeight(this.mModel.getActivity());
    }

    private boolean isCenterCrop(boolean z) {
        if (z || !this.mModel.getStateHelper().supportGroupPanelLandscapeMode()) {
            return false;
        }
        return true;
    }

    private boolean isUpdatableMargin() {
        boolean z;
        ContentModel contentModel = this.mModel;
        if (contentModel == null || !contentModel.getStateHelper().supportGroupPanelLandscapeMode()) {
            z = false;
        } else {
            z = true;
        }
        if (!BottomSheetState.isExpanded(this.mModel) || z) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mContainer = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        this.mDecorLayout = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$2(Object[] objArr) {
        this.mPhotoView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$3(Object[] objArr) {
        this.mMediaPlayerView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$updateLayout$4() {
        this.mActionInvoker.invoke(ViewerAction.DETAILS_REFRESH_BEHAVIOR, new Object[0]);
    }

    /* access modifiers changed from: private */
    public void onTableModeAnimationUpdated(Object... objArr) {
        PhotoViewCompat photoViewCompat = this.mPhotoView;
        if (photoViewCompat != null) {
            photoViewCompat.resetScaleAndCenter();
        }
        MediaPlayerViewCompat mediaPlayerViewCompat = this.mMediaPlayerView;
        if (mediaPlayerViewCompat != null) {
            mediaPlayerViewCompat.setCenterCrop(isCenterCrop(this.mModel.getContainerModel().isTableMode()));
        }
    }

    /* access modifiers changed from: private */
    public void setPhotoViewCenterCrop() {
        if (this.mPhotoView != null) {
            boolean isCenterCrop = isCenterCrop(this.mModel.getContainerModel().isTableMode());
            this.mPhotoView.resetBeforeCenterCrop();
            this.mPhotoView.setAndKeepCenterCrop(isCenterCrop);
            this.mPhotoView.invalidate();
        }
    }

    /* access modifiers changed from: private */
    public void setVideoViewCenterCrop() {
        if (this.mMediaPlayerView != null) {
            this.mMediaPlayerView.setCenterCrop(isCenterCrop(this.mModel.getContainerModel().isTableMode()));
        }
    }

    private boolean supportAnimation(int i2) {
        if (!this.mModel.isFragmentResumed() || i2 != 0 || BottomSheetState.isExpanded(this.mModel)) {
            return false;
        }
        return true;
    }

    private void updateDecorLayoutMargin() {
        boolean supportGroupPanelLandscapeMode = this.mModel.getStateHelper().supportGroupPanelLandscapeMode();
        View view = this.mModel.getContainerModel().getView();
        if (view != null) {
            ViewMarginUtils.setRightMargin(this.mDecorLayout, getRightMargin(supportGroupPanelLandscapeMode, view));
        }
    }

    /* access modifiers changed from: private */
    public void updateLayout() {
        boolean supportGroupPanelLandscapeMode = this.mModel.getStateHelper().supportGroupPanelLandscapeMode();
        View view = this.mModel.getContainerModel().getView();
        if (view != null) {
            int topMargin = getTopMargin(supportGroupPanelLandscapeMode);
            int bottomMargin = getBottomMargin(supportGroupPanelLandscapeMode);
            int rightMargin = getRightMargin(supportGroupPanelLandscapeMode, view);
            if (isUpdatableMargin()) {
                ViewMarginUtils.setBottomMargin(this.mContainer, bottomMargin);
                ViewMarginUtils.setTopMargin(this.mContainer, topMargin);
                ViewMarginUtils.setRightMargin(this.mContainer, rightMargin);
            }
            ViewMarginUtils.setRightMargin(this.mDecorLayout, rightMargin);
            this.mModel.setGroupPanelBaseMargin(new MarginParams(0, topMargin, 0, bottomMargin));
            ViewUtils.post(this.mContainer, new a(this, 2));
        }
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.CONTENT_CONTAINER, new b(this, 0));
        this.mActionInvoker.add(ViewerAction.DECOR_LAYOUT, new b(this, 1));
        this.mActionInvoker.add(ViewerAction.IMAGE_PHOTO_VIEW, new b(this, 2));
        this.mActionInvoker.add(ViewerAction.MEDIA_VIEW, new b(this, 3));
        this.mActionInvoker.add(ViewerAction.ON_TABLE_MODE_ANIMATION_UPDATED, new b(this, 4));
    }

    public void onConfigurationChanged(Configuration configuration) {
        ViewUtils.post(this.mContainer, new a(this, 3));
        ViewUtils.post(this.mPhotoView, new a(this, 0));
        ViewUtils.post(this.mMediaPlayerView, new a(this, 1));
    }

    public void onTableModeChanged(boolean z, int i2) {
        this.mActionInvoker.invoke(ViewerAction.REQUEST_UPDATE_DECOR_LAYOUT_VISIBILITY, new Object[0]);
        updateDecorLayoutMargin();
        if (!supportAnimation(i2)) {
            updateLayout();
        }
        ViewUtils.post(this.mPhotoView, new a(this, 0));
        ViewUtils.post(this.mMediaPlayerView, new a(this, 1));
    }

    public void onViewAttached() {
        updateLayout();
        setPhotoViewCenterCrop();
        setVideoViewCenterCrop();
    }

    public void onViewConfirm() {
        updateLayout();
    }
}
