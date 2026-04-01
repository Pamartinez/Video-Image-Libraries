package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video;

import H7.g;
import O3.l;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.abstraction.VideoBackupInfo;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HighlightMediaViewPlayerHandler extends MediaViewPlayerHandler {
    private Runnable mHighlightFrcCaptureRunnable = null;

    /* access modifiers changed from: private */
    public void handleSaveHighlightFrc(Object... objArr) {
        this.mHighlightFrcCaptureRunnable = objArr[0];
        stopVideoView();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        handleInstantSlowMoPlay(objArr[0].booleanValue(), false);
    }

    /* access modifiers changed from: private */
    public void onHighlightFrcViewClicked(Object... objArr) {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem != null) {
            int videoThumbStartTime = (int) (mediaItem.getVideoThumbStartTime() / 1000);
            if (this.mMediaPlayerView.isInPlayState()) {
                this.mMediaPlayerView.seekTo(videoThumbStartTime);
            } else {
                playVideo(mediaItem, videoThumbStartTime);
            }
        }
        if (!this.mModel.isHighlightFrcMode()) {
            handleInstantSlowMoPlay(false, false);
        }
    }

    private void setHighlightPlaybackRange() {
        int i2;
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem != null) {
            VideoBackupInfo videoBackupInfo = (VideoBackupInfo) this.mModel.getBlackboard().read("data://viewer_group_panel_video_current_info");
            int videoThumbStartTime = (int) (mediaItem.getVideoThumbStartTime() / 1000);
            int highLightClipEndTime = (int) (MediaItemUtil.getHighLightClipEndTime(mediaItem) / 1000);
            if (videoThumbStartTime > 0 || highLightClipEndTime > 0) {
                IMediaPlayerView iMediaPlayerView = this.mMediaPlayerView;
                if (videoBackupInfo != null) {
                    i2 = videoBackupInfo.position;
                } else {
                    i2 = videoThumbStartTime;
                }
                iMediaPlayerView.setRenderingPosition(i2);
                this.mMediaPlayerView.setPlaybackRange(videoThumbStartTime, highLightClipEndTime);
            }
        }
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
        this.mActionInvoker.add(ViewerAction.HIGHLIGHT_FRC_PLAY, new g(this, 0));
        this.mActionInvoker.add(ViewerAction.HIGHLIGHT_FRC_BUTTON_CLICKED, new g(this, 1));
        this.mActionInvoker.add(ViewerAction.SAVE_HIGHLIGHT_FRC, new g(this, 2));
    }

    public boolean handleBlackboardEvent(EventMessage eventMessage) {
        if (eventMessage.what != 1117) {
            return super.handleBlackboardEvent(eventMessage);
        }
        if (!this.mModel.isHighlightFrcMode() || this.mMediaPlayerView.isOpened()) {
            return false;
        }
        Log.d(this.TAG, "reopen video - stopped by handleSaveHighlightFrc");
        playVideo(this.mModel.getMediaItem());
        return false;
    }

    public void onVideoPrepared(int i2, int i7) {
        setHighlightPlaybackRange();
        super.onVideoPrepared(i2, i7);
    }

    public void onVideoReleased() {
        super.onVideoReleased();
        Optional.ofNullable(this.mHighlightFrcCaptureRunnable).ifPresent(new l(0));
        this.mHighlightFrcCaptureRunnable = null;
    }

    public void onViewDetached() {
        super.onViewDetached();
        this.mHighlightFrcCaptureRunnable = null;
    }
}
