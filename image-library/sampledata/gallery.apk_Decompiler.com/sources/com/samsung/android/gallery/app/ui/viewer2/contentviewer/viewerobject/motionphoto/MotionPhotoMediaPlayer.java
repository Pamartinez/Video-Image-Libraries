package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.motionphoto;

import B4.c;
import F7.a;
import F7.b;
import android.graphics.Bitmap;
import android.view.View;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.BottomSheetState;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video.MediaViewPlayerHandler;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.abstraction.VideoReqInfo;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.media.InstantSlowMoUtils;
import com.samsung.android.gallery.module.media.MetadataManager;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoUtils;
import com.samsung.android.gallery.module.motionphoto.MotionPlayViewer;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.photoview.PhotoViewCompat;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MotionPhotoMediaPlayer extends MediaViewPlayerHandler {
    private boolean mIsPhotoAnimCallbackEnable;
    private boolean mNeedMotionPreview;

    private void enableDecoIcons(boolean z) {
        if (z) {
            super.showDecoIcons();
            return;
        }
        this.mActionInvoker.invoke(ViewerAction.VIDEO_HAS_AUDIO, Boolean.valueOf(this.mMediaPlayerView.hasAudioTrack()), Boolean.FALSE);
        this.mActionInvoker.invoke(ViewerAction.HIDE_AUDIO_ICON, new Object[0]);
        this.mActionInvoker.invoke(ViewerAction.HIDE_CAPTURE_ICON, new Object[0]);
    }

    private boolean isFilmExpanded() {
        return this.mModel.getStateHelper().isFilmExpanded(this.mModel.getPosition());
    }

    private boolean isPlayableSharedItem(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isSharing() || !MediaItemMde.hasDownloadMotionPhotoPath(mediaItem)) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        if (this.mMediaPlayerView.isInPlayState()) {
            this.mMediaPlayerView.close();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onVideoCompleted$5() {
        if (!this.mModel.isInstantSlowMoPlayEnabled()) {
            if (ViewUtils.isVisible(this.mPhotoView) || this.mModel.getMotionPlayViewer() != MotionPlayViewer.VIDEO_FILM) {
                this.mActionInvoker.invoke(ViewerAction.CHANGE_MOTION_PLAY_VIEWER, MotionPlayViewer.PHOTO, Integer.valueOf(this.mModel.getPosition()));
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onVideoPlay$4() {
        this.mActionInvoker.invoke(ViewerAction.CHANGE_MOTION_PLAY_VIEWER, MotionPlayViewer.PHOTO_PREVIEW, Integer.valueOf(this.mModel.getPosition()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$playMotionPhotoVideo$2() {
        enableDecoIcons(isFilmExpanded());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$playMotionPhotoVideo$3() {
        if (!this.mMediaPlayerView.isPaused() && !this.mMediaPlayerView.isPlaying()) {
            initAlphaBlendingTime();
            playVideo(this.mModel.getMediaItem());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setMotionPhotoMode$1() {
        if (this.mIsPhotoAnimCallbackEnable) {
            stopVideoView();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setPhotoViewVisibility$6(boolean z) {
        int i2;
        PhotoViewCompat photoViewCompat = this.mPhotoView;
        if (z) {
            i2 = 0;
        } else {
            i2 = 4;
        }
        ViewUtils.setVisibility(photoViewCompat, i2);
    }

    /* access modifiers changed from: private */
    public void onImageLoaded(Object... objArr) {
        if (this.mMediaPlayerView.isPlaying()) {
            setPhotoViewVisibility(false);
            Log.w(this.TAG, "PhotoView must be invisible when playing");
        }
    }

    /* access modifiers changed from: private */
    public void onMotionPlayViewerChanged(Object... objArr) {
        MotionPlayViewer motionPlayViewer = objArr[0];
        MotionPlayViewer motionPlayViewer2 = objArr[1];
        this.mNeedMotionPreview = false;
        if (motionPlayViewer2.isOriginVideo) {
            playMotionPhotoVideo();
        } else if ((motionPlayViewer.isOriginVideo || motionPlayViewer == MotionPlayViewer.PHOTO_PREVIEW) && motionPlayViewer2 == MotionPlayViewer.PHOTO && !this.mModel.isInstantSlowMoPlayEnabled()) {
            setMotionPhotoMode(this.mModel.getMediaItem());
        }
    }

    private void playMotionPhotoVideo() {
        this.mIsPhotoAnimCallbackEnable = false;
        this.mThread.runOnUiThread(new a(this, 4), 100);
        this.mThread.runOnUiThread(new a(this, 5), 0);
    }

    private void setMotionPhotoMode(MediaItem mediaItem) {
        if (ViewUtils.isVisible(this.mPhotoView) || !this.mModel.isFragmentResumed()) {
            stopVideoView();
        } else {
            this.mIsPhotoAnimCallbackEnable = true;
            SimpleAnimator.setVisibility((View) this.mPhotoView, 0, 350, (Runnable) new a(this, 0));
        }
        this.mActionInvoker.invoke(ViewerAction.SET_TO_MOTION_PHOTO_ORIGINAL_IMAGE, mediaItem);
    }

    private boolean supportMotionPhotoFpsScaleUp() {
        if (!PreferenceFeatures.OneUi7x.SUPPORT_MOTION_PHOTO_FPS_SCALE_UP || !InstantSlowMoUtils.supportMotionPhotoFpsScaleUp() || MediaItemUtil.isMotionPhotoAutoPlayViewMode(this.mModel.getMediaItem())) {
            return false;
        }
        return true;
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
        this.mActionInvoker.add(ViewerAction.PREPARE_EDITOR, new b(this, 0));
        this.mActionInvoker.add(ViewerAction.MOTION_PLAY_VIEWER_CHANGED, new b(this, 1));
        this.mActionInvoker.add(ViewerAction.BITMAP_LOADED, new b(this, 2));
    }

    public void endInstantSlowMoPlay() {
        if (supportMotionPhotoFpsScaleUp()) {
            this.mMediaPlayerView.setVideoFrc(2);
        } else {
            super.endInstantSlowMoPlay();
        }
    }

    public int getAlphaBlendingTime() {
        return 350;
    }

    public Bitmap getBitmapWhenAttached() {
        return this.mModel.getBitmap();
    }

    public MediaItem getPlayableItem(MediaItem mediaItem) {
        if (!isPlayableSharedItem(mediaItem)) {
            return mediaItem;
        }
        MediaItem clone = mediaItem.clone();
        clone.setPath(FileUtils.createNewFileIfAbsent(MediaItemMde.getDownloadMotionPhotoPath(mediaItem)).getAbsolutePath());
        clone.setStorageType(StorageType.Local);
        return clone;
    }

    public void handleInstantSlowMoPlay(boolean z, boolean z3) {
        super.handleInstantSlowMoPlay(z, z3);
        if (!z && !this.mMediaPlayerView.isPlaying() && this.mModel.getMotionPlayViewer() != MotionPlayViewer.VIDEO_FILM) {
            this.mActionInvoker.invoke(ViewerAction.CHANGE_MOTION_PLAY_VIEWER, MotionPlayViewer.PHOTO, Integer.valueOf(this.mModel.getPosition()));
        }
    }

    public void hideMediaView() {
        ViewUtils.cancelAnimation(this.mPhotoView);
        ViewUtils.setVisibility(this.mPhotoView, 0);
        ViewUtils.setVisibility((View) this.mMediaPlayerView, 8);
    }

    public void invalidateInternal(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        if (!MediaItemUtil.equalsBitmap(mediaItem2, mediaItem)) {
            setMotionPhotoMode(mediaItem);
        }
    }

    public boolean isAlphaBlendingEnabled() {
        if (!super.isAlphaBlendingEnabled() || !this.mModel.getMotionPlayViewer().isVideo) {
            return false;
        }
        return true;
    }

    public boolean isEnablePreviewPlay() {
        if (this.mModel.getMotionPlayViewer() == MotionPlayViewer.PHOTO_PREVIEW || this.mModel.getMotionPlayViewer() == MotionPlayViewer.PHOTO_MOTION_OFF) {
            return super.isEnablePreviewPlay();
        }
        return true;
    }

    public boolean isLoopEnabled() {
        return false;
    }

    public boolean isRunPreviewOnPageChanged() {
        if (PreferenceFeatures.OneUi7x.IS_ONE_UI_70 || PocFeatures.isEnabled(PocFeatures.PreviewVideoOnPageChanged)) {
            return true;
        }
        return false;
    }

    public void onVideoButtonClicked(boolean z) {
        super.onVideoButtonClicked(z);
        if (z && !isFilmExpanded()) {
            enableDecoIcons(false);
        }
    }

    public void onVideoCompleted() {
        super.onVideoCompleted();
        if (this.mModel.getMotionPlayViewer() == MotionPlayViewer.PHOTO_PREVIEW) {
            Log.d(this.TAG, "onVideoCompleted : recover from preview mute");
            onAudioMuteChanged();
        }
        this.mNeedMotionPreview = false;
        this.mThread.runOnUiThread(new a(this, 2));
    }

    public void onVideoPause(int i2) {
        super.onVideoPause(i2);
        if (this.mModel.getMotionPlayViewer() == MotionPlayViewer.VIDEO_BUTTON) {
            enableDecoIcons(true);
        }
    }

    public void onVideoPlay(int i2) {
        super.onVideoPlay(i2);
        if (this.mNeedMotionPreview) {
            this.mThread.runOnUiThread(new a(this, 3));
        }
    }

    public void onVideoPrepared(int i2, int i7) {
        super.onVideoPrepared(i2, i7);
        if (this.mNeedMotionPreview) {
            long duration = (long) this.mMediaPlayerView.getDuration();
            long calcMotionPresentationTime = MetadataManager.getInstance().calcMotionPresentationTime(this.mModel.getMediaItem(), duration);
            long max = Math.max(0, calcMotionPresentationTime - 500);
            Log.d(this.TAG, "onVideoPrepared", A.a.f("playback=", max), A.a.f("presentation=", calcMotionPresentationTime), A.a.f("dur=", duration));
            if (calcMotionPresentationTime < 30) {
                stopVideoView(true);
                return;
            }
            int i8 = (int) max;
            this.mMediaPlayerView.setRenderingPosition(i8);
            this.mMediaPlayerView.setPlaybackRange(i8, (int) calcMotionPresentationTime);
            this.mMediaPlayerView.setAudioMute(true);
        } else {
            if (supportMotionPhotoFpsScaleUp()) {
                this.mMediaPlayerView.setVideoFrc(2);
            }
            this.mMediaPlayerView.setAudioMute(this.mModel.getContainerModel().isAudioMute());
        }
        enableDecoIcons(isFilmExpanded());
    }

    public void onViewAttached() {
        super.onViewAttached();
        this.mNeedMotionPreview = this.mModel.getStateHelper().isMotionPreviewEnabled(this.mModel);
    }

    public void onViewDetached() {
        super.onViewDetached();
        this.mMediaPlayerView.setDefaultPosition(false);
        this.mNeedMotionPreview = false;
    }

    public boolean openVideo(MediaItem mediaItem, int i2, VideoReqInfo videoReqInfo) {
        return super.openVideo(getPlayableItem(mediaItem), i2, videoReqInfo);
    }

    public void runPreview() {
        if (this.mModel.getStateHelper().isMotionPreviewEnabled(this.mModel)) {
            super.runPreview(MotionPhotoUtils.supportMotionSefPlay(this.mModel.getMediaItem()));
        } else if (this.mModel.getMotionPlayViewer() == MotionPlayViewer.VIDEO_FILM) {
            super.runPreview(false);
        }
    }

    public void setPhotoViewVisibility(boolean z) {
        this.mThread.runOnUiThread(new c((Object) this, z, 5));
    }

    public void setScaleOnStop() {
        if (!BottomSheetState.Details.isExpanded(this.mModel) && this.mModel.getMotionPlayViewer().isVideo) {
            this.mPhotoView.resetScaleAndCenter();
            this.mMediaPlayerView.resetScale();
        }
    }

    public boolean supportRemotePresentationPlay() {
        return true;
    }

    public void syncPrevAsMediaView() {
        this.mThread.runOnUiThread(new a(this, 1));
    }

    public void hidePhotoViewOnSeekBegin() {
    }

    public void onResume() {
    }

    public void setCapturedBitmap() {
    }

    public void showDecoIcons() {
    }

    public void onBackKeyPrepare(Object... objArr) {
    }

    public void onReturnTransitionEnd(Object... objArr) {
    }
}
