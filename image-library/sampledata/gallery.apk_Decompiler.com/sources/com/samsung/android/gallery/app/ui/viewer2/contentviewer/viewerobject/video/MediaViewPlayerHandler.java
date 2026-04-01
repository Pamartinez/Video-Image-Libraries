package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video;

import A4.A;
import A4.H;
import Fa.F;
import H.a;
import H7.s;
import H7.t;
import H7.u;
import H7.v;
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Toast;
import c0.C0086a;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.common.state.BottomSheetState;
import com.samsung.android.gallery.app.ui.viewer2.common.state.OverlayViewState;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.FragmentLifeCycle;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.app.ui.viewer2.model.ContentModel;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.abstraction.VideoReqInfo;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.media.InstantSlowMoPlayLogger;
import com.samsung.android.gallery.module.media.InstantSlowMoUtils;
import com.samsung.android.gallery.module.superslow.SuperSlowUtils;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.module.thumbnail.type.UniqueKey;
import com.samsung.android.gallery.support.actioninvoker.ActionInvoker;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.gallery.widget.abstraction.VideoBackupInfo;
import com.samsung.android.gallery.widget.animator.SimpleAnimator;
import com.samsung.android.gallery.widget.photoview.PhotoViewCompat;
import com.samsung.android.gallery.widget.utils.SystemUi;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerFactory;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerListener;
import com.samsung.android.ocr.MOCRLang;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaViewPlayerHandler extends ViewerObject implements FragmentLifeCycle, MediaPlayerListener {
    private int mAlphaBlendingTime = 0;
    private BecomingNoisyReceiver mBecomingNoisyReceiver;
    private boolean mFragmentStopped = false;
    protected IMediaPlayerView mMediaPlayerView;
    protected IMediaPlayerView mMediaPlayerViewOrg;
    private boolean mPauseOnBottomSheet = false;
    private boolean mPauseOnMultiWindow = false;
    private boolean mPausedByUser = false;
    protected PhotoViewCompat mPhotoView;
    private boolean mPrevAudioMute = true;
    private boolean mResumeRequired = false;
    private boolean mSkipUpdatePos = false;
    private float mSpeed = 1.0f;
    private ArrayList<Long[]> mVideoSearchPos;

    private void changeAudioVolume(boolean z) {
        this.mPrevAudioMute = this.mMediaPlayerView.isAudioMute();
        if (z) {
            this.mMediaPlayerView.setVolume(0.2f, 0.2f);
        } else {
            this.mMediaPlayerView.setVolume(1.0f, 1.0f);
        }
    }

    private void changeMediaView(View view) {
        IMediaPlayerView iMediaPlayerView;
        int i2;
        IMediaPlayerView iMediaPlayerView2 = this.mMediaPlayerViewOrg;
        if (view != null) {
            iMediaPlayerView2 = (IMediaPlayerView) view;
        }
        if (iMediaPlayerView2 != null && iMediaPlayerView2 != (iMediaPlayerView = this.mMediaPlayerView)) {
            if (iMediaPlayerView.isPlaying()) {
                i2 = this.mMediaPlayerView.getRenderingPosition();
            } else {
                i2 = getResumePos(this.mModel.getMediaItem());
            }
            onMediaView(iMediaPlayerView2);
            playVideo(this.mModel.getMediaItem(), i2);
        }
    }

    private void disableKeepScreenOn() {
        ThreadUtil.postOnUiThread(new a(7, this, this.mModel.getActivity()));
    }

    private void eraseVideoBackupInfo() {
        if (hasVideoBackupInfo()) {
            this.mModel.getBlackboard().erase("data://video_viewer_return_info");
            this.mModel.getBlackboard().erase("data://viewer_group_panel_video_current_info");
            MediaItem mediaItem = this.mModel.getMediaItem();
            ThumbnailLoader.getInstance().loadPreview(mediaItem, new H(14, (Object) this, (Object) mediaItem));
        }
    }

    private Bitmap getBitmapForVideoReturnInfo() {
        Bitmap pauseAndCapturedBitmap = pauseAndCapturedBitmap();
        if (pauseAndCapturedBitmap == null) {
            return this.mModel.getBitmap();
        }
        return pauseAndCapturedBitmap;
    }

    private int getResumePos(MediaItem mediaItem) {
        if (mediaItem == null) {
            return 0;
        }
        Integer num = VideoPropData.of(mediaItem).videoResumePos;
        if (num != null) {
            VideoPropData.of(mediaItem).videoResumePos = null;
            return num.intValue();
        }
        ArrayList<Long[]> arrayList = this.mVideoSearchPos;
        if (arrayList == null || arrayList.isEmpty()) {
            return this.mModel.getContainerModel().getLatestVideoPos(mediaItem.getThumbCacheKey().hashCode());
        }
        int intValue = this.mVideoSearchPos.get(0)[1].intValue();
        this.mVideoSearchPos = null;
        return intValue;
    }

    private void handleVideoBackupInfo(String str) {
        VideoBackupInfo videoBackupInfo = (VideoBackupInfo) this.mModel.getBlackboard().read(str);
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (videoBackupInfo != null && mediaItem != null && videoBackupInfo.hash == mediaItem.getSimpleHashCode()) {
            this.mMediaPlayerView.setRenderingPosition(videoBackupInfo.position);
            this.mModel.setVideoSeekPosition(videoBackupInfo.position);
            Optional.ofNullable(videoBackupInfo.preview).ifPresent(new A(19, (Object) this, (Object) mediaItem));
            this.mModel.getContainerModel().setAudioMute(videoBackupInfo.mute);
            setAudioMute(videoBackupInfo.mute);
        }
    }

    private boolean hasVideoBackupInfo() {
        if (this.mModel.getBlackboard().read("data://video_viewer_return_info") == null && this.mModel.getBlackboard().read("data://viewer_group_panel_video_current_info") == null) {
            return false;
        }
        return true;
    }

    private boolean isBottomSheetFullCover() {
        return OverlayViewState.isShow((OverlayViewState.StateListener) this.mModel);
    }

    private boolean isIncomingCall() {
        return this.mMediaPlayerView.isIncomingCall();
    }

    private boolean isLoopEnabledByViewer() {
        String locationKey = this.mModel.getContainerModel().getLocationKey();
        if (this.mModel.isGroupPanelViewer() || LocationKey.isSecondDepthGroupPanelView(locationKey) || LocationKey.isHighlightGroupPanelView(locationKey)) {
            return true;
        }
        return false;
    }

    private boolean isOnNoMemory(int i2, int i7) {
        if (i2 != 1 && i2 != 10951 && i2 != 805) {
            return false;
        }
        if (i7 != -12 && i7 != -38) {
            return false;
        }
        if (this.mModel.isFragmentResumed()) {
            this.mThread.runOnUiThread(new s(this, 2));
        }
        this.mMediaPlayerView.close();
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        this.mPhotoView = objArr[0];
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$1(Object[] objArr) {
        onAudioMuteChanged();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$10(Object[] objArr) {
        this.mMediaPlayerView.pauseVideo();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$11(Object[] objArr) {
        onForceSwipe();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$12(Object[] objArr) {
        handleInstantSlowMoPlay(objArr[0].booleanValue(), true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$13(Object[] objArr) {
        stopVideoView(true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$14(Object[] objArr) {
        updatePlaybackState();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$15(Object[] objArr) {
        resumeVideo();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$16(Object[] objArr) {
        stopVideoView();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$17(Object[] objArr) {
        onAudioEraserOnRequest();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$2(Object[] objArr) {
        onAudioEraserChanged();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$3(Object[] objArr) {
        onVideoButtonClicked(objArr[0].booleanValue());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$4(Object[] objArr) {
        this.mFragmentStopped = true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$5(Object[] objArr) {
        this.mFragmentStopped = false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$6(Object[] objArr) {
        IMediaPlayerView iMediaPlayerView = objArr[0];
        this.mMediaPlayerViewOrg = iMediaPlayerView;
        onMediaView(iMediaPlayerView);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$7(Object[] objArr) {
        changeMediaView(objArr[0]);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$8(Object[] objArr) {
        changeMediaView((View) null);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$9(Object[] objArr) {
        if (this.mModel.isViewConfirmed()) {
            playVideo(this.mModel.getMediaItem());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$disableKeepScreenOn$30(Activity activity) {
        if (activity != null) {
            SystemUi.keepScreenOn(activity, false);
        } else {
            Log.e(this.TAG, "disableKeepScreenOn failed");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$eraseVideoBackupInfo$24(MediaItem mediaItem, Bitmap bitmap, UniqueKey uniqueKey, ThumbKind thumbKind) {
        if (MediaItemUtil.equalsSimple(mediaItem, this.mModel.getMediaItem())) {
            Log.d(this.TAG, "reload preview bmp");
            this.mModel.setPreViewBmp(bitmap, mediaItem, true);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$handleBlackboardEvent$21() {
        ViewUtils.setVisibility(this.mPhotoView, 0);
        ViewUtils.setVisibility((View) this.mMediaPlayerView, 8);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$isOnNoMemory$29() {
        Utils.showToast(this.mModel.getContext(), (int) R.string.unknown_error_description);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onUnsupportedVideo$28() {
        Utils.showToast(this.mModel.getContext(), (int) R.string.cant_play_video_file_type_not_supported);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onVideoCompleted$25() {
        int count = this.mModel.getContainerModel().getMediaData().getCount();
        int position = this.mModel.getPosition() + 1;
        if (position >= count) {
            position = 0;
        }
        this.mActionInvoker.invoke(ViewerAction.SCROLL_TO, Integer.valueOf(position), Boolean.FALSE);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onVideoInfo$27() {
        Toast.makeText(this.mModel.getContext(), R.string.video_playback_may_not_be_smooth, 0).show();
        Optional.ofNullable(this.mModel.getContainerModel().getEventContext()).ifPresent(new F(25));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onVideoPlay$23() {
        if (!this.mMediaPlayerView.isPlaying() && !this.mMediaPlayerView.isPaused() && !this.mMediaPlayerView.isVisualSeeking()) {
            return;
        }
        if (isAlphaBlendingEnabled()) {
            SimpleAnimator.setAlphaOutVisible(this.mPhotoView, this.mAlphaBlendingTime - 52, 4);
            this.mAlphaBlendingTime = getAlphaBlendingTime();
            return;
        }
        ViewUtils.setVisibility(this.mPhotoView, 4);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$runPreview$19() {
        runPreview(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$runPreview$20(MediaItem mediaItem) {
        this.mVideoSearchPos = VideoPropData.of(mediaItem).loadSegmentInfoList();
        this.mThread.runOnUiThread(new s(this, 7));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$syncPrevAsMediaView$22(Bitmap bitmap) {
        lambda$handleVideoBackupInfo$18(this.mModel.getMediaItem(), bitmap);
        hideMediaView();
        this.mActionInvoker.invoke(ViewerAction.VIDEO_PREVIEW_BITMAP_CHANGED, bitmap);
    }

    /* access modifiers changed from: private */
    public void onBottomSheetChanged(Object... objArr) {
        if (isBottomSheetFullCover()) {
            if (this.mMediaPlayerView.isPlaying()) {
                this.mMediaPlayerView.pauseVideo();
                this.mPauseOnBottomSheet = true;
            }
        } else if (this.mPauseOnBottomSheet) {
            this.mPauseOnBottomSheet = false;
            resumeVideo();
        }
    }

    /* access modifiers changed from: private */
    public void onEnablePreviewPlay(Object... objArr) {
        if (this.mModel.isFragmentResumed()) {
            if (objArr[0].booleanValue()) {
                openVideo(this.mModel.getMediaItem());
            } else {
                stopVideoView();
            }
        }
    }

    /* access modifiers changed from: private */
    public void onPauseForMultiWindow(Object... objArr) {
        Log.d(this.TAG, "onPause in MW");
        this.mPauseOnMultiWindow = true;
        onPause();
    }

    /* access modifiers changed from: private */
    public void onPrepareStartGroupItemPanelView(Object... objArr) {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem != null) {
            Bitmap bitmapForVideoReturnInfo = getBitmapForVideoReturnInfo();
            if (bitmapForVideoReturnInfo != null) {
                lambda$handleVideoBackupInfo$18(mediaItem, bitmapForVideoReturnInfo);
                this.mModel.getBlackboard().publish("data://viewer_group_panel_video_current_info", new VideoBackupInfo(mediaItem.getSimpleHashCode(), this.mMediaPlayerView.getRenderingPosition(), bitmapForVideoReturnInfo, this.mModel.getContainerModel().isAudioMute()).addParameter(VideoBackupInfo.KEY.HIGHLIGHT_FRC, Boolean.valueOf(this.mModel.isHighlightFrcMode())));
                return;
            }
            Log.e(this.TAG, "publish video return info failed : bitmap is null");
            return;
        }
        Log.e(this.TAG, "publish video return info failed : mediaItem is null");
    }

    /* access modifiers changed from: private */
    public void onReenterTransitionEnd(Object... objArr) {
        boolean booleanValue = objArr[0].booleanValue();
        this.mMediaPlayerView.updateBackgroundColor(true);
        if (booleanValue) {
            playVideo(this.mModel.getMediaItem());
            this.mSkipUpdatePos = false;
        }
    }

    /* access modifiers changed from: private */
    public void onReplacedComposite(Object... objArr) {
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView != null) {
            iMediaPlayerView.addMediaPlayerListener(this);
        }
    }

    /* access modifiers changed from: private */
    public void onRequestTimeTickUpdate(Object... objArr) {
        int intValue = objArr[0].intValue();
        int intValue2 = objArr[1].intValue();
        if (MediaItemUtil.equalsSimple(objArr[2], this.mModel.getMediaItem())) {
            onTimeTickUpdate(intValue, intValue2);
        }
    }

    /* access modifiers changed from: private */
    public void onRequestVideoSeek(Object... objArr) {
        int intValue = ((Integer) Optional.ofNullable(this.mModel.getMediaItem()).map(new u(objArr[0].floatValue(), 0)).orElse(0)).intValue();
        this.mMediaPlayerView.visualSeekTo(intValue);
        this.mModel.setVideoSeekPosition(intValue);
    }

    /* access modifiers changed from: private */
    public void onRequestVideoSeekBegin(Object... objArr) {
        this.mMediaPlayerView.seekBegin();
        hidePhotoViewOnSeekBegin();
    }

    /* access modifiers changed from: private */
    public void onRequestVideoSeekFinish(Object... objArr) {
        this.mMediaPlayerView.seekFinish();
    }

    /* access modifiers changed from: private */
    public void onResumeForMultiWindow(Object... objArr) {
        if (this.mPauseOnMultiWindow) {
            Log.d(this.TAG, "onResume for MW");
            this.mPauseOnMultiWindow = false;
            onResume();
        }
    }

    private void onUnsupportedVideo() {
        this.mModel.setIsUnsupportedVideo(true);
        if (this.mModel.isFragmentResumed()) {
            this.mThread.runOnUiThread(new s(this, 5));
        }
        this.mMediaPlayerView.close();
    }

    /* access modifiers changed from: private */
    public void onVideoSpeedChanged(Object... objArr) {
        this.mSpeed = objArr[0].floatValue();
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView != null && iMediaPlayerView.isPlaying()) {
            float speed = this.mMediaPlayerView.getSpeed();
            float f = this.mSpeed;
            if (speed != f) {
                this.mMediaPlayerView.setSpeed(f);
            }
        }
    }

    private Bitmap pauseAndCapturedBitmap() {
        if (MediaItemUtil.isHdr10OrHlgVideo(this.mModel.getMediaItem())) {
            return this.mMediaPlayerView.pauseAndCapture();
        }
        if (this.mMediaPlayerView.isPlaying()) {
            this.mMediaPlayerView.pauseVideo();
        }
        return this.mMediaPlayerView.getViewBitmap();
    }

    private void pauseOnReady() {
        if (!this.mMediaPlayerView.isPaused()) {
            int videoSeekPosition = this.mModel.getVideoSeekPosition();
            this.mSkipUpdatePos = true;
            if (openVideo(this.mModel.getMediaItem(), videoSeekPosition, new VideoReqInfo.Builder().requestAudioFocus(false).build())) {
                this.mMediaPlayerView.pauseOnReady(videoSeekPosition);
            }
        }
    }

    private void removeHdrAlphaAnimation() {
        Animation animation = this.mPhotoView.getAnimation();
        if (animation != null) {
            animation.setAnimationListener((Animation.AnimationListener) null);
            animation.cancel();
            this.mPhotoView.clearAnimation();
            this.mPhotoView.setAlpha(1.0f);
        }
    }

    private void setAudioMute(boolean z) {
        setAudioMute(z, true);
    }

    /* access modifiers changed from: private */
    /* renamed from: setBitmap */
    public void lambda$handleVideoBackupInfo$18(MediaItem mediaItem, Bitmap bitmap) {
        setBitmap(mediaItem, bitmap, true);
    }

    private void setSlowMotionEffect(MediaItem mediaItem) {
        Integer num = VideoPropData.of(mediaItem).superSlowClipEffect;
        if (num != null) {
            int intValue = num.intValue();
            this.mMediaPlayerView.setSlowMo(intValue, SuperSlowUtils.getSuperSlowOffset(intValue), true);
        }
    }

    private boolean shouldVideoPlayOnInvalidate(MediaItem mediaItem, MediaItem mediaItem2) {
        if (this.mModel.isFragmentResumed() && this.mModel.isPlaying() && OverlayViewState.isHide((OverlayViewState.StateListener) this.mModel)) {
            return true;
        }
        if (!mediaItem.isLocal() || !mediaItem2.isCloud()) {
            return false;
        }
        return true;
    }

    private boolean supportVideoController() {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem == null || mediaItem.isBroken() || mediaItem.isMotionPhoto() || mediaItem.isSingleTakenShot()) {
            return false;
        }
        if (PreferenceFeatures.VideoPlayerFeature.isOpenInGalleryVideoPlayer() || this.mModel.getStateHelper().isForcePlayVideoInGallery()) {
            return true;
        }
        return false;
    }

    private void updateMediaItem() {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem != null) {
            VideoPropData.of(mediaItem).videoFrameRate = this.mMediaPlayerView.getFrameRate();
            int duration = this.mMediaPlayerView.getDuration();
            if (mediaItem.getFileDuration() != duration) {
                VideoPropData.of(mediaItem).videoDurationInPlayer = duration;
            }
        }
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.IMAGE_PHOTO_VIEW, new t(this, 25));
        this.mActionInvoker.add(ViewerAction.AUDIO_MUTE_CHANGED, new t(this, 3));
        this.mActionInvoker.add(ViewerAction.AUDIO_ERASER_CHANGED, new t(this, 14));
        this.mActionInvoker.add(ViewerAction.REQUEST_VIDEO_SEEK_BEGIN, new t(this, 18));
        this.mActionInvoker.add(ViewerAction.REQUEST_VIDEO_SEEK_FINISH, new t(this, 19));
        this.mActionInvoker.add(ViewerAction.REQUEST_VIDEO_SEEK, new t(this, 20));
        this.mActionInvoker.add(ViewerAction.PLAY_PAUSE_CLICKED, new t(this, 21));
        this.mActionInvoker.add(ViewerAction.REQUEST_VIDEO_VIEW_PAUSE_FOR_MULTI_WINDOW, new t(this, 22));
        this.mActionInvoker.add(ViewerAction.REQUEST_VIDEO_VIEW_RESUME_FOR_MULTI_WINDOW, new t(this, 23));
        this.mActionInvoker.add(ViewerAction.PREPARE_VIDEO_APP_TRANSITION, new t(this, 24));
        this.mActionInvoker.add(ViewerAction.RESUME_FROM_VIDEO_PLAYER, new t(this, 26));
        this.mActionInvoker.add(ViewerAction.MEDIA_VIEW, new t(this, 27));
        this.mActionInvoker.add(ViewerAction.REQUEST_PLAY_TIME_UPDATED, new t(this, 28));
        this.mActionInvoker.add(ViewerAction.VIDEO_EDITOR_REENTER_TRANSITION_END, new t(this, 29));
        this.mActionInvoker.add(ViewerAction.OVERLAY_VIEW_STATE_CHANGED, new v(this, 0));
        this.mActionInvoker.add(ViewerAction.BOTTOM_SHEET_STATE_CHANGED, new v(this, 0));
        this.mActionInvoker.add(ViewerAction.CONTAINER_BACK_KEY_PREPARE, new v(this, 1));
        this.mActionInvoker.add(ViewerAction.ENABLE_PREVIEW_PLAY, new t(this, 0));
        if (supportRemotePresentationPlay()) {
            this.mActionInvoker.add(ViewerAction.CHANGE_MEDIA_VIEW, new t(this, 1));
            this.mActionInvoker.add(ViewerAction.RESTORE_MEDIA_VIEW, new t(this, 2));
        }
        this.mActionInvoker.add(ViewerAction.DOWNLOADED_SHARE_VIDEO_VERIFY_UPDATED, new t(this, 4));
        this.mActionInvoker.add(ViewerAction.PREPARE_FORCE_SWIPE, new t(this, 5));
        this.mActionInvoker.add(ViewerAction.FORCE_SWIPE, new t(this, 6));
        this.mActionInvoker.add(ViewerAction.INSTANT_SLOW_MO_PLAY, new t(this, 7));
        this.mActionInvoker.add(ViewerAction.PREPARE_SHARE_SHEET, new t(this, 8));
        this.mActionInvoker.add(ViewerAction.SHARE_SHEET_CLEARED, new t(this, 9));
        this.mActionInvoker.add(ViewerAction.PREPARE_START_GROUP_ITEM_PANEL_VIEW, new t(this, 10));
        this.mActionInvoker.add(ViewerAction.ON_REPLACED_COMPOSITE, new t(this, 11));
        this.mActionInvoker.add(ViewerAction.VIDEO_SPEED_ON_CHANGED, new t(this, 12));
        this.mActionInvoker.add(ViewerAction.REQUEST_VIDEO_START, new t(this, 13));
        this.mActionInvoker.add(ViewerAction.REQUEST_VIDEO_STOP, new t(this, 15));
        this.mActionInvoker.add(ViewerAction.AUDIO_ERASER_ON_REQUEST, new t(this, 16));
        this.mActionInvoker.add(ViewerAction.RETURN_TRANSITION_END, new t(this, 17));
    }

    public void endInstantSlowMoPlay() {
        this.mMediaPlayerView.endInstantSlowMoPlay();
    }

    public int getAlphaBlendingTime() {
        long j2;
        if (this.mModel.isFirstView()) {
            if (this.mModel.getContainerModel().getStateHelper().isQuickView()) {
                return 0;
            }
            MediaItem mediaItem = this.mModel.getMediaItem();
            if (mediaItem != null) {
                j2 = VideoPropData.of(mediaItem).videoThumbnailFrameTime / 1000;
            } else {
                j2 = 0;
            }
            if (350 < j2) {
                return 350;
            }
        }
        MediaItem mediaItem2 = this.mModel.getMediaItem();
        if (mediaItem2 == null || !mediaItem2.isHdrVideo()) {
            return 0;
        }
        return MOCRLang.KHMER;
    }

    public Bitmap getBitmapWhenAttached() {
        return this.mModel.getPreViewBitmap();
    }

    public boolean handleBlackboardEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 3018 || i2 == 3077) {
            this.mActionInvoker.invoke(ViewerAction.REQUEST_VIDEO_START, new Object[0]);
            return true;
        } else if (i2 == 3201) {
            Log.d(this.TAG, "item edited from video editor");
            this.mThread.runOnUiThread(new s(this, 3));
            return true;
        } else if (i2 != 6011) {
            return false;
        } else {
            this.mActionInvoker.invoke(ViewerAction.INITIALIZE_SHARED_VIDEO, new Object[0]);
            return true;
        }
    }

    public void handleInstantSlowMoPlay(boolean z, boolean z3) {
        this.mModel.setInstantSlowMoPlayEnabled(z);
        if (z3) {
            this.mActionInvoker.invoke(ViewerAction.SET_VIEW_PAGER_TOUCH_ENABLED, Boolean.valueOf(!z));
        }
        if (z) {
            this.mMediaPlayerView.beginInstantSlowMoPlay();
        } else {
            endInstantSlowMoPlay();
        }
    }

    public void hideMediaView() {
        ViewUtils.setVisibility(this.mPhotoView, 0);
        ViewUtils.setAlpha((View) this.mMediaPlayerView, 0.0f);
        this.mMediaPlayerView.setDefaultPosition(false);
    }

    public void hidePhotoViewOnSeekBegin() {
        ViewUtils.setVisibility(this.mPhotoView, 4);
    }

    public void initAlphaBlendingTime() {
        this.mAlphaBlendingTime = getAlphaBlendingTime();
    }

    public void initialize() {
        this.mActionInvoker.invoke(ViewerAction.MEDIA_VIEW_INFLATE, new Object[0]);
    }

    public void invalidate(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        super.invalidate(mediaItem, i2, mediaItem2, i7);
        invalidateInternal(mediaItem, i2, mediaItem2, i7);
    }

    public void invalidateInternal(MediaItem mediaItem, int i2, MediaItem mediaItem2, int i7) {
        boolean z;
        MediaItem mediaItem3 = this.mModel.getMediaItem();
        if (shouldVideoPlayOnInvalidate(mediaItem, mediaItem2)) {
            if (mediaItem3 == null || !mediaItem3.isSingleTakenPostProcessing()) {
                z = false;
            } else {
                z = true;
            }
            if (MediaItemUtil.equalsBitmap(mediaItem3, mediaItem)) {
                this.mMediaPlayerView.updateItem(mediaItem);
                if (MediaPlayerFactory.SUPPORT_AUDIO_FADE_OUT_3D_EFFECT && !this.mMediaPlayerView.isAudioMute()) {
                    this.mMediaPlayerView.release3dEffect();
                }
            } else if (!z) {
                stopVideoView(false);
                playVideo(mediaItem, this.mModel.getVideoSeekPosition());
            }
        } else if (MediaItemUtil.equalsBitmap(mediaItem3, mediaItem)) {
            this.mMediaPlayerView.updateItem(mediaItem);
        } else {
            stopVideoView(false);
            if (this.mModel.isFragmentResumed()) {
                pauseOnReady();
            }
        }
    }

    public boolean isAlphaBlendingEnabled() {
        if (this.mAlphaBlendingTime <= 0 || !ViewUtils.isVisible(this.mPhotoView)) {
            return false;
        }
        return true;
    }

    public boolean isEnablePreviewPlay() {
        return this.mModel.getContainerModel().isEnablePreviewPlay();
    }

    public boolean isLoopEnabled() {
        if (this.mModel.getMediaItem() == null) {
            return false;
        }
        if (isLoopEnabledByViewer()) {
            return true;
        }
        if (!PreferenceFeatures.VideoPlayerFeature.isOpenInVideoPlayer() || this.mModel.getStateHelper().isForcePlayVideoInGallery()) {
            return false;
        }
        return true;
    }

    public boolean isRunPreviewOnPageChanged() {
        return false;
    }

    public void onAudioDucked(boolean z) {
        boolean isAudioMute = this.mMediaPlayerView.isAudioMute();
        StringCompat stringCompat = this.TAG;
        StringBuilder o2 = C0086a.o(z ? 1 : 0, "onAudioDucked {AUDIO_TRANSIENT_DUCKED,", ", isAudioMute:");
        o2.append(this.mMediaPlayerView);
        o2.append(", mPrevAudioMute:");
        o2.append(this.mPrevAudioMute);
        o2.append("}");
        Log.mp(stringCompat, o2.toString());
        if (!z) {
            isAudioMute = this.mPrevAudioMute;
        }
        if (!isAudioMute) {
            changeAudioVolume(z);
        }
    }

    public void onAudioEraserChanged() {
        this.mMediaPlayerView.setAudioEraserOff(this.mModel.getContainerModel().isAudioEraserOff(), "/data/sec/gallery/native/audioeraser");
    }

    public void onAudioEraserOnRequest() {
        this.mMediaPlayerView.setAudioEraserOff(false, "/data/sec/gallery/native/audioeraser");
    }

    public void onAudioFocusChanged(boolean z) {
        int i2;
        StringCompat stringCompat = this.TAG;
        Log.mp(stringCompat, "onAudioFocusChanged {AUDIO_FOCUS_CHANGED," + z + "}");
        if (z && this.mMediaPlayerView.isPaused() && this.mResumeRequired) {
            this.mResumeRequired = false;
            this.mMediaPlayerView.resumeVideo();
            i2 = 1;
        } else if (!z) {
            this.mResumeRequired = this.mMediaPlayerView.isPlaying();
            this.mMediaPlayerView.pauseVideo();
            i2 = 4;
        } else {
            i2 = -1;
        }
        StringCompat stringCompat2 = this.TAG;
        Log.mp(stringCompat2, "onAudioFocusChanged done {" + i2 + "}");
    }

    public void onAudioMuteChanged() {
        setAudioMute(this.mModel.getContainerModel().isAudioMute(), this.mMediaPlayerView.isPlaying());
    }

    public void onBackKeyPrepare(Object... objArr) {
        boolean z;
        boolean isSecondDepthGroupPanelView = LocationKey.isSecondDepthGroupPanelView(this.mModel.getContainerModel().getLocationKey());
        if (!this.mModel.getStateHelper().isFromCamera() || isSecondDepthGroupPanelView) {
            Bitmap bitmapForVideoReturnInfo = getBitmapForVideoReturnInfo();
            if (bitmapForVideoReturnInfo == null) {
                z = true;
            } else {
                z = false;
            }
            if (bitmapForVideoReturnInfo != null) {
                this.mMediaPlayerView.setVideoCaptured(this.mMediaPlayerView.getRenderingPosition(), bitmapForVideoReturnInfo);
                removeHdrAlphaAnimation();
                MediaItem mediaItem = this.mModel.getMediaItem();
                lambda$handleVideoBackupInfo$18(mediaItem, bitmapForVideoReturnInfo);
                if (mediaItem != null) {
                    this.mModel.getBlackboard().publish("data://video_viewer_return_info", new VideoBackupInfo(mediaItem.getSimpleHashCode(), this.mMediaPlayerView.getRenderingPosition(), bitmapForVideoReturnInfo, this.mModel.getContainerModel().isAudioMute()));
                }
                if (isSecondDepthGroupPanelView && mediaItem != null) {
                    this.mModel.getBlackboard().publish("data://viewer_group_panel_video_current_info", new VideoBackupInfo(mediaItem.getSimpleHashCode(), this.mMediaPlayerView.getRenderingPosition(), bitmapForVideoReturnInfo, this.mModel.getContainerModel().isAudioMute()).addParameter(VideoBackupInfo.KEY.HIGHLIGHT_FRC, Boolean.valueOf(this.mModel.isHighlightFrcMode())));
                }
            } else {
                Log.e(this.TAG, "publish video return info failed : bitmap is null");
            }
            stopVideoView(z);
            return;
        }
        this.mMediaPlayerView.pauseVideo();
    }

    public void onBind(MediaItem mediaItem, int i2) {
        super.onBind(mediaItem, i2);
        this.mAlphaBlendingTime = getAlphaBlendingTime();
        this.mMediaPlayerView.setLogTag(Integer.valueOf(this.mModel.getPosition()));
        this.mBecomingNoisyReceiver.setLogTag(Integer.valueOf(this.mModel.getPosition()));
        this.mMediaPlayerView.setLooping(isLoopEnabled());
        handleVideoBackupInfo("data://video_viewer_return_info");
        handleVideoBackupInfo("data://viewer_group_panel_video_current_info");
    }

    public void onFinalized() {
        IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
        if (iMediaPlayerView != null) {
            iMediaPlayerView.removeMediaPlayerListener(this);
        }
    }

    public void onForceSwipe() {
        Bitmap viewBitmap = this.mMediaPlayerView.getViewBitmap();
        if (viewBitmap != null) {
            lambda$handleVideoBackupInfo$18(this.mModel.getMediaItem(), viewBitmap);
            hideMediaView();
        }
    }

    public void onInitialized() {
        this.mBecomingNoisyReceiver = new BecomingNoisyReceiver(this.mMediaPlayerView);
    }

    public void onInstantSlowMoPlay(boolean z, MotionEvent motionEvent) {
        if (InstantSlowMoUtils.supportLocation(this.mModel.getContainerModel().getLocationKey())) {
            this.mActionInvoker.invoke(ViewerAction.INSTANT_SLOW_MO_PLAY, Boolean.valueOf(z), Float.valueOf(motionEvent.getX()), Float.valueOf(motionEvent.getY()));
        }
    }

    public void onMediaView(IMediaPlayerView iMediaPlayerView) {
        IMediaPlayerView iMediaPlayerView2;
        if (iMediaPlayerView != null && iMediaPlayerView != (iMediaPlayerView2 = this.mMediaPlayerView)) {
            if (iMediaPlayerView2 != null) {
                iMediaPlayerView2.close();
                this.mMediaPlayerView.removeMediaPlayerListener(this);
            }
            this.mMediaPlayerView = iMediaPlayerView;
            iMediaPlayerView.addMediaPlayerListener(this);
            this.mMediaPlayerView.setLooping(isLoopEnabled());
            this.mActionInvoker.invoke(ViewerAction.REPLACE_REMOTE_MEDIA_VIEW, iMediaPlayerView);
        }
    }

    public void onPageSelected() {
        if (isRunPreviewOnPageChanged()) {
            IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
            if (iMediaPlayerView == null || !iMediaPlayerView.isPlaying() || !MediaItemUtil.equals(this.mMediaPlayerView.getMediaItem(), this.mModel.getMediaItem())) {
                runPreview();
            } else {
                Log.i(this.TAG, "keep playing onPageSelected");
            }
        }
    }

    public void onPause() {
        Log.d(this.TAG, "onPause()");
        setCapturedBitmap();
        removeHdrAlphaAnimation();
        this.mSkipUpdatePos = true;
        this.mMediaPlayerView.close();
    }

    public void onResume() {
        Log.d(this.TAG, "onResume()");
        if (!this.mPhotoView.isKeepCenterCrop()) {
            this.mPhotoView.setInitialCenterCrop(false);
        }
        handleVideoBackupInfo("data://viewer_group_panel_video_current_info");
        updatePlaybackState();
        this.mFragmentStopped = false;
    }

    public void onReturnTransitionEnd(Object... objArr) {
        updatePlaybackState();
    }

    public void onStop() {
        Log.d(this.TAG, "onStop()");
        setScaleOnStop();
        stopVideoView();
        this.mFragmentStopped = true;
    }

    public void onSurfaceDestroyed() {
        stopVideoView();
    }

    public void onTimeTickUpdate(int i2, int i7) {
        if (!this.mSkipUpdatePos) {
            this.mModel.setVideoSeekPosition(i7);
        }
        if (!this.mMediaPlayerView.isVisualSeeking()) {
            this.mActionInvoker.invoke(ViewerAction.PLAY_TIME_UPDATED, Integer.valueOf(i2), Integer.valueOf(i7), this.mModel.getMediaItem());
        }
    }

    public void onVideoButtonClicked(boolean z) {
        this.mPausedByUser = !z;
        if (z) {
            resumeVideo();
        } else {
            this.mMediaPlayerView.pauseVideo();
        }
    }

    public void onVideoCompleted() {
        this.mModel.setPlaying(false);
        this.mActionInvoker.invoke(ViewerAction.VIDEO_STOPPED, new Object[0]);
        this.mActionInvoker.invoke(ViewerAction.KEEP_SCREEN_ON, Boolean.FALSE);
        if (PocFeatures.VIDEO_AUTO_PLAYBACK_NEXT && !this.mModel.getContainerModel().isOsdVisible()) {
            this.mThread.runOnUiThread(new s(this, 0));
        }
    }

    public void onVideoError(int i2, int i7) {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "onVideoError : " + Logger.v(Integer.valueOf(i2), Integer.valueOf(i7)));
        if (!isOnNoMemory(i2, i7)) {
            if (i2 == 1) {
                this.mActionInvoker.invoke(ViewerAction.VIDEO_ERROR, new Object[0]);
            } else if (i2 != 10951) {
                this.mModel.setPlaying(false);
                stopVideoView();
                this.mActionInvoker.invoke(ViewerAction.VIDEO_ERROR, new Object[0]);
            } else {
                onUnsupportedVideo();
            }
        }
    }

    public void onVideoInfo(int i2, int i7) {
        StringCompat stringCompat = this.TAG;
        Log.d(stringCompat, "onVideoInfo" + Logger.v(Integer.valueOf(i2), Integer.valueOf(i7)));
        if (!isOnNoMemory(i2, i7)) {
            if (i2 == 10951) {
                onUnsupportedVideo();
            } else if (i2 != 10980) {
                if (i2 == 10982) {
                    this.mActionInvoker.invoke(ViewerAction.AUDIO_ERASER_INIT_COMPLETED, new Object[0]);
                }
            } else if (MediaItemUtil.isApv(this.mModel.getMediaItem())) {
                ThreadUtil.runOnUiThread(new s(this, 6));
            }
        }
    }

    public void onVideoPause(int i2) {
        this.mModel.setPlaying(false);
        if (!this.mSkipUpdatePos) {
            this.mModel.setVideoSeekPosition(i2);
        }
        this.mSkipUpdatePos = false;
        this.mActionInvoker.invoke(ViewerAction.VIDEO_STOPPED, new Object[0]);
        this.mActionInvoker.invoke(ViewerAction.KEEP_SCREEN_ON, Boolean.FALSE);
    }

    public void onVideoPlay(int i2) {
        this.mModel.setPlaying(this.mMediaPlayerView.isPlaying());
        this.mThread.runOnUiThread(new s(this, 8), 52);
        this.mActionInvoker.invoke(ViewerAction.VIDEO_STARTED, Boolean.valueOf(this.mMediaPlayerView.isPlaying()));
        if (supportVideoController() && this.mMediaPlayerView.isPlaying()) {
            this.mActionInvoker.invoke(ViewerAction.KEEP_SCREEN_ON, Boolean.TRUE);
        }
    }

    public void onVideoPrepared(int i2, int i7) {
        if (!this.mMediaPlayerView.isVisualSeeking()) {
            this.mActionInvoker.invoke(ViewerAction.PLAY_TIME_UPDATED, Integer.valueOf(this.mMediaPlayerView.getDuration()), Integer.valueOf(this.mMediaPlayerView.getRenderingPosition()), this.mModel.getMediaItem());
        }
        showDecoIcons();
        this.mModel.setIsUnsupportedVideo(false);
        updateMediaItem();
        this.mActionInvoker.invoke(ViewerAction.VIDEO_PREPARED, new Object[0]);
        eraseVideoBackupInfo();
    }

    public void onVideoReleased() {
        Log.d(this.TAG, "onVideoReleased");
        this.mModel.setPlaying(false);
        this.mModel.setInstantSlowMoPlayEnabled(false);
        this.mActionInvoker.invoke(ViewerAction.VIDEO_STOPPED, new Object[0]);
        disableKeepScreenOn();
    }

    public void onViewAttached() {
        this.mPauseOnBottomSheet = false;
        this.mFragmentStopped = false;
        setDefaultPrevImage();
        this.mBecomingNoisyReceiver.register(this.mModel.getContext());
    }

    public void onViewConfirm() {
        if (isRunPreviewOnPageChanged()) {
            IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
            if (iMediaPlayerView != null && !iMediaPlayerView.isOpened()) {
                runPreview();
            }
        } else if (LocationKey.isLongExposure(this.mModel.getContainerModel().getLocationKey())) {
            ThreadUtil.postOnUiThreadDelayed(new s(this, 4), 200);
        } else {
            runPreview();
        }
    }

    public void onViewDetached() {
        int currentPosition = this.mMediaPlayerView.getCurrentPosition();
        if (currentPosition > 0) {
            this.mModel.getContainerModel().setLatestVideoPos(this.mModel.getMediaItem().getThumbCacheKey().hashCode(), currentPosition);
        }
        stopVideoView(false);
        this.mBecomingNoisyReceiver.unregister();
        this.mResumeRequired = false;
        this.mFragmentStopped = false;
        onMediaView(this.mMediaPlayerViewOrg);
        this.mPauseOnBottomSheet = false;
        this.mPauseOnMultiWindow = false;
        this.mVideoSearchPos = null;
    }

    public void onViewRecycled() {
        super.onViewRecycled();
        this.mMediaPlayerView.recycle();
        this.mBecomingNoisyReceiver.unregister();
        this.mResumeRequired = false;
        this.mPauseOnBottomSheet = false;
        this.mFragmentStopped = false;
        this.mPausedByUser = false;
        this.mSkipUpdatePos = false;
        this.mPauseOnMultiWindow = false;
        onMediaView(this.mMediaPlayerViewOrg);
        this.mSpeed = 1.0f;
        this.mAlphaBlendingTime = 0;
        this.mVideoSearchPos = null;
    }

    public boolean openVideo(MediaItem mediaItem) {
        return openVideo(mediaItem, 0);
    }

    public void playVideo(MediaItem mediaItem) {
        playVideo(mediaItem, 0);
    }

    public void resumeVideo() {
        if (this.mMediaPlayerView.isPlaying()) {
            Log.d(this.TAG, "resumePlay() already playing");
        } else if (this.mMediaPlayerView.isInPlayState()) {
            Log.d(this.TAG, "resumePlay() on isInPlayState");
            if (Math.abs(this.mMediaPlayerView.getCurrentPosition() - this.mModel.getVideoSeekPosition()) > 100) {
                this.mMediaPlayerView.seekTo(this.mModel.getVideoSeekPosition());
            }
            if (PreferenceFeatures.OneUi6x.SUPPORT_VIDEO_SPEED_CONTROLLER) {
                float speed = this.mMediaPlayerView.getSpeed();
                float f = this.mSpeed;
                if (speed != f) {
                    this.mMediaPlayerView.setSpeed(f);
                }
            }
            this.mMediaPlayerView.resumeVideo();
        } else {
            Log.d(this.TAG, "resumePlay() as new");
            playVideo(this.mModel.getMediaItem(), this.mModel.getVideoSeekPosition());
        }
    }

    public void runPreview() {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (!PreferenceFeatures.OneUi8x.VIDEO_SEARCH || mediaItem == null || VideoPropData.of(mediaItem).videoFrameIds == null) {
            runPreview(false);
        } else {
            this.mThread.runOnBgThread(new a(8, this, mediaItem));
        }
    }

    public void setCapturedBitmap() {
        Bitmap pauseAndCapturedBitmap;
        boolean z;
        if (this.mMediaPlayerView.isInPlayState() && (pauseAndCapturedBitmap = pauseAndCapturedBitmap()) != null) {
            MediaItem mediaItem = this.mModel.getMediaItem();
            MediaItem mediaItem2 = this.mModel.getMediaItem();
            if (mediaItem == null || VideoPropData.of(mediaItem).longExposure) {
                z = true;
            } else {
                z = false;
            }
            setBitmap(mediaItem2, pauseAndCapturedBitmap, z);
        }
    }

    public void setDefaultPrevImage() {
        Bitmap bitmapWhenAttached = getBitmapWhenAttached();
        if (bitmapWhenAttached != null && !bitmapWhenAttached.equals(this.mPhotoView.getBitmap())) {
            this.mPhotoView.setImage(bitmapWhenAttached, bitmapWhenAttached.getWidth(), bitmapWhenAttached.getHeight());
            ContentModel contentModel = this.mModel;
            contentModel.setBitmap(bitmapWhenAttached, contentModel.getMediaItem());
            this.mActionInvoker.invoke(ViewerAction.VIDEO_PREVIEW_BITMAP_CHANGED, bitmapWhenAttached);
        }
        this.mPhotoView.setVisibility(0);
    }

    public void setScaleOnStop() {
        if (this.mModel.getActivity() != null && this.mModel.getStateHelper().isFromCamera() && this.mModel.getActivity().isFinishing()) {
            Log.d(this.TAG, "do not reset scale when activity finishing");
        } else if (BottomSheetState.Details.isExpanded(this.mModel)) {
            this.mPhotoView.setInitialCenterCrop(true);
        } else {
            this.mPhotoView.resetScaleAndCenter();
            this.mMediaPlayerView.resetScale();
        }
    }

    public void showDecoIcons() {
        ActionInvoker actionInvoker = this.mActionInvoker;
        ViewerAction viewerAction = ViewerAction.VIDEO_HAS_AUDIO;
        Boolean valueOf = Boolean.valueOf(this.mMediaPlayerView.hasAudioTrack());
        Boolean bool = Boolean.TRUE;
        actionInvoker.invoke(viewerAction, valueOf, bool);
        this.mActionInvoker.invoke(ViewerAction.ENABLE_CAPTURE_ICON, bool);
    }

    public void stopVideoView() {
        stopVideoView(true);
    }

    public boolean supportRemotePresentationPlay() {
        return PreferenceFeatures.Remote.PLAY_VIDEO_IN_PRESENTATION;
    }

    public void syncPrevAsMediaView() {
        Bitmap pauseAndCapturedBitmap = pauseAndCapturedBitmap();
        if (pauseAndCapturedBitmap != null) {
            this.mThread.runOnUiThread(new a(6, this, pauseAndCapturedBitmap));
        }
    }

    public void updatePlaybackState() {
        Log.d(this.TAG, "updatePlaybackState", Boolean.valueOf(this.mModel.isViewConfirmed()), Boolean.valueOf(this.mModel.isPendingPhotoViewSet()));
        if (this.mModel.isViewConfirmed() && !this.mModel.isPendingPhotoViewSet()) {
            if (!this.mMediaPlayerView.isOpened()) {
                if (this.mPausedByUser || (((!isLoopEnabled() || !OverlayViewState.isHide((OverlayViewState.StateListener) this.mModel)) && this.mFragmentStopped) || isIncomingCall())) {
                    pauseOnReady();
                    return;
                }
                playVideo(this.mModel.getMediaItem(), this.mModel.getVideoSeekPosition());
                this.mSkipUpdatePos = false;
            } else if (this.mMediaPlayerView.isPaused() && !this.mPausedByUser) {
                this.mMediaPlayerView.resumeVideo();
            }
        }
    }

    private void setAudioMute(boolean z, boolean z3) {
        this.mPrevAudioMute = this.mMediaPlayerView.isAudioMute();
        this.mMediaPlayerView.setAudioMute(z, z3);
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (PreferenceFeatures.OneUi6x.SUPPORT_INSTANT_SLOW_MO && mediaItem != null) {
            InstantSlowMoPlayLogger.getInstance().setAudioMute(mediaItem.getMediaId(), z);
        }
    }

    private void setBitmap(MediaItem mediaItem, Bitmap bitmap, boolean z) {
        if (z) {
            this.mPhotoView.clearBitmap();
        }
        this.mPhotoView.setImage(bitmap, bitmap.getWidth(), bitmap.getHeight());
        this.mModel.setBitmap(bitmap, mediaItem);
    }

    public boolean openVideo(MediaItem mediaItem, int i2) {
        return openVideo(mediaItem, i2, new VideoReqInfo.Builder().build());
    }

    public void playVideo(MediaItem mediaItem, int i2) {
        playVideo(mediaItem, i2, new VideoReqInfo.Builder().build());
    }

    public void stopVideoView(boolean z) {
        if (!z || !this.mMediaPlayerView.isInPlayState()) {
            this.mThread.runOnUiThread(new s(this, 1));
        } else {
            syncPrevAsMediaView();
        }
        this.mMediaPlayerView.close();
        this.mActionInvoker.invoke(ViewerAction.ENABLE_CAPTURE_ICON, Boolean.FALSE);
        this.mActionInvoker.invoke(ViewerAction.DISABLE_AUDIO_ICON, new Object[0]);
    }

    public boolean openVideo(MediaItem mediaItem, int i2, VideoReqInfo videoReqInfo) {
        if (!isEnablePreviewPlay()) {
            return false;
        }
        if (this.mMediaPlayerView.isOpened()) {
            this.mMediaPlayerView.close();
        }
        this.mMediaPlayerView.setSupportTimeTick(true);
        if (this.mMediaPlayerView.open(mediaItem, videoReqInfo)) {
            setSlowMotionEffect(mediaItem);
            setAudioMute(this.mModel.getContainerModel().isAudioMute(), videoReqInfo.requestAudioFocus);
            this.mMediaPlayerView.setAudioEraserOff(this.mModel.getContainerModel().isAudioEraserOff(), "/data/sec/gallery/native/audioeraser");
            if (i2 > 0) {
                this.mMediaPlayerView.setRenderingPosition(i2);
            }
            if (LocationKey.isColorCorrectView(this.mModel.getContainerModel().getLocationKey())) {
                this.mMediaPlayerView.setColorCorrect();
            }
            return true;
        }
        eraseVideoBackupInfo();
        return false;
    }

    public void playVideo(MediaItem mediaItem, int i2, VideoReqInfo videoReqInfo) {
        if (openVideo(mediaItem, i2, videoReqInfo)) {
            this.mMediaPlayerView.play();
        }
    }

    public void runPreview(boolean z) {
        EventContext eventContext;
        if (this.mModel.isFragmentResumed()) {
            playVideo(this.mModel.getMediaItem(), getResumePos(this.mModel.getMediaItem()), new VideoReqInfo.Builder().isMotionSefPlay(z).build());
            if (PreferenceFeatures.OneUi6x.SUPPORT_INSTANT_SLOW_MO && (eventContext = this.mModel.getContainerModel().getEventContext()) != null) {
                InstantSlowMoPlayLogger.getInstance().setScreenId(eventContext.getScreenId());
            }
        }
    }
}
