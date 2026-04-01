package com.samsung.android.gallery.app.ui.viewer2.details;

import L7.p;
import L7.q;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.viewer.RequestDismissKeyGuardCmd;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.OverlayViewState;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.foldable.FoldUtils;
import com.samsung.android.gallery.module.utils.BlackboardUtils;
import com.samsung.android.gallery.widget.details.DetailsBehavior;
import com.samsung.android.gallery.widget.details.IDetailsTouchListener;
import com.samsung.android.gallery.widget.photoview.PhotoView;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DetailsTouchListener extends ViewerObject implements IDetailsTouchListener {
    private DetailsBehavior mBehavior;
    private IMediaPlayerView mMediaView;
    private PhotoView mPhotoView;

    private boolean isMovableInSingleTaken() {
        if (this.mModel.isSingleTakenShot()) {
            return !this.mModel.getContainerModel().isSelectMode();
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        DetailsBehavior detailsBehavior = objArr[0];
        this.mBehavior = detailsBehavior;
        detailsBehavior.setTouchListener(this);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        this.mPhotoView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$2(Object[] objArr) {
        this.mMediaView = objArr[0];
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
        this.mActionInvoker.add(ViewerAction.DETAILS_BEHAVIOR, new q(this, 0));
        this.mActionInvoker.add(ViewerAction.IMAGE_PHOTO_VIEW, new q(this, 1));
        this.mActionInvoker.add(ViewerAction.MEDIA_VIEW, new q(this, 2));
    }

    public boolean isLocked() {
        return this.mModel.getContainerModel().getSystemUi().isScreenLocked();
    }

    public boolean isMovable() {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem == null || mediaItem.isPostProcessing() || BlackboardUtils.isViewerShrinkToCamera(this.mModel.getBlackboard())) {
            return false;
        }
        if (mediaItem.isVideo()) {
            IMediaPlayerView iMediaPlayerView = this.mMediaView;
            if (iMediaPlayerView != null && !iMediaPlayerView.isMovable()) {
                return false;
            }
        } else if (!this.mPhotoView.isMovable()) {
            return false;
        }
        if (isMovableInSingleTaken()) {
            return true;
        }
        return false;
    }

    public boolean isMovableOnDetails() {
        if (!OverlayViewState.isHide((OverlayViewState.StateListener) this.mModel) || FoldUtils.isFlipCoverScreen(this.mModel.getActivity()) || !isMovableInSingleTaken()) {
            return false;
        }
        return true;
    }

    public void onTouchSlideUpToExpand() {
        this.mActionInvoker.invoke(ViewerAction.DETAILS_SHOW_ANALYTIC_LOGGING, this.mModel.getMediaItem(), Boolean.TRUE);
    }

    public boolean shouldBlockBehaviorTouchIntercept() {
        if (OverlayViewState.isShow((OverlayViewState.StateListener) this.mModel) || this.mModel.isInstantSlowMoPlayEnabled() || this.mModel.isVideoSpeedChangeOnGoing()) {
            return true;
        }
        return false;
    }

    public void showRequestDismissKeyGuard() {
        RequestDismissKeyGuardCmd requestDismissKeyGuardCmd = new RequestDismissKeyGuardCmd();
        EventContext eventContext = this.mModel.getContainerModel().getEventContext();
        DetailsBehavior detailsBehavior = this.mBehavior;
        Objects.requireNonNull(detailsBehavior);
        requestDismissKeyGuardCmd.execute(eventContext, new p(detailsBehavior, 0));
    }
}
