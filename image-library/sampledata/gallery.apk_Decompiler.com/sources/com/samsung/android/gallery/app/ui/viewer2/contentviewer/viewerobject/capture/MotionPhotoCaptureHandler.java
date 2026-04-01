package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.capture;

import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoViewMode;
import com.samsung.android.gallery.module.motionphoto.MotionPlayViewer;
import o6.p;
import u7.C0520a;
import w7.C0537c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MotionPhotoCaptureHandler extends CaptureHandler {
    public MotionPhotoCaptureHandler() {
        super(2);
    }

    /* access modifiers changed from: private */
    public boolean isSupportVideoCapture() {
        if ((this.mModel.getStateHelper().isFilmExpanded(this.mModel.getPosition()) || this.mMediaPlayerView.isPaused()) && !isUnsupportedViewMode()) {
            return true;
        }
        return false;
    }

    private boolean isUnsupportedViewMode() {
        MotionPhotoViewMode motionPhotoViewMode;
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem == null) {
            motionPhotoViewMode = null;
        } else {
            motionPhotoViewMode = DetailsData.of(mediaItem).motionPhotoViewMode;
        }
        if (motionPhotoViewMode == MotionPhotoViewMode.BOOMERANG || motionPhotoViewMode == MotionPhotoViewMode.SLOW_MO) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$switchToImageCaptureInternal$0() {
        return this.mIsZoomed;
    }

    /* access modifiers changed from: private */
    public void onMotionPlayViewerChanged(Object... objArr) {
        MotionPlayViewer motionPlayViewer = objArr[0];
        MotionPlayViewer motionPlayViewer2 = objArr[1];
        if (motionPlayViewer.isVideo != motionPlayViewer2.isVideo || MotionPlayViewer.isViewModeChanged(motionPlayViewer, motionPlayViewer2)) {
            updateCapture();
        }
    }

    private boolean supportVideoCaptureInMotionPhoto() {
        if (!this.mModel.getMotionPlayViewer().isVideo || !this.mModel.isFragmentResumed() || !this.mModel.isViewConfirmed()) {
            return false;
        }
        return true;
    }

    private void switchToImageCaptureInternal() {
        ViewerCapture viewerCapture = this.mViewerCapture;
        if (viewerCapture != null && this.mCaptureViewLayout != null) {
            viewerCapture.unbindView();
            ViewerImageCapture viewerImageCapture = new ViewerImageCapture(this.mModel, this.mPhotoView, new C0537c(this, 0));
            this.mViewerCapture = viewerImageCapture;
            viewerImageCapture.bindView(this.mCaptureViewLayout);
            this.mViewerCapture.setEnabled(true);
            this.mViewerCapture.updateButtonVisibility(false);
        }
    }

    private void switchToVideoCaptureInternal() {
        ViewerCapture viewerCapture = this.mViewerCapture;
        if (viewerCapture != null && this.mCaptureViewLayout != null) {
            viewerCapture.unbindView();
            ViewerMediaPlayerCapture viewerMediaPlayerCapture = new ViewerMediaPlayerCapture(this.mModel, this.mMediaPlayerView, new C0537c(this, 1));
            this.mViewerCapture = viewerMediaPlayerCapture;
            viewerMediaPlayerCapture.bindView(this.mCaptureViewLayout);
            this.mViewerCapture.setEnabled(true);
            this.mViewerCapture.updateButtonVisibility(false);
        }
    }

    private void updateCapture() {
        ViewerCapture viewerCapture = this.mViewerCapture;
        if (viewerCapture == null || !viewerCapture.isCaptureProcessing() || !this.mModel.isFragmentResumed()) {
            updateCaptureInternal();
        } else {
            this.mViewerCapture.setCaptureEndListener(new p(23, this));
        }
    }

    /* access modifiers changed from: private */
    public void updateCaptureInternal() {
        if (supportVideoCaptureInMotionPhoto()) {
            switchToVideoCaptureInternal();
        } else {
            switchToImageCaptureInternal();
        }
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
        this.mActionInvoker.add(ViewerAction.MOTION_PLAY_VIEWER_CHANGED, new C0520a(5, this));
    }

    public void onPause() {
        super.onPause();
        updateCapture();
    }

    public void onViewConfirm() {
        super.onViewConfirm();
        updateCapture();
    }

    public void onViewDetached() {
        super.onViewDetached();
        updateCapture();
    }
}
