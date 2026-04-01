package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject.video;

import B7.d;
import com.samsung.android.gallery.app.ui.viewer2.common.action.ViewerAction;
import com.samsung.android.gallery.app.ui.viewer2.contentviewer.ViewerObject;
import com.samsung.android.gallery.widget.videoview.DoubleTapSeekListener;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaPlayerDoubleTapSeeker extends ViewerObject implements DoubleTapSeekListener {
    private IMediaPlayerView mMediaPlayerView;

    private void bindView(IMediaPlayerView iMediaPlayerView) {
        this.mMediaPlayerView = iMediaPlayerView;
        iMediaPlayerView.setDoubleTapSeekListener(this);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$addActionInvokeListener$0(Object[] objArr) {
        bindView(objArr[0]);
    }

    private void seekStep(int i2) {
        if (this.mMediaPlayerView.isInPlayState()) {
            this.mMediaPlayerView.seekBegin();
            this.mMediaPlayerView.seekTo(i2);
            this.mMediaPlayerView.seekFinish();
            this.mActionInvoker.invoke(ViewerAction.PLAY_TIME_UPDATED, Integer.valueOf(this.mMediaPlayerView.getDuration()), Integer.valueOf(i2), this.mModel.getMediaItem());
        }
    }

    public void addActionInvokeListener() {
        this.mActionInvoker.add(ViewerAction.MEDIA_VIEW, new d(6, this));
    }

    public void onDoubleTapFf() {
        int currentPosition = this.mMediaPlayerView.getCurrentPosition();
        if (currentPosition > 0) {
            int i2 = currentPosition + 10000;
            if (i2 > this.mMediaPlayerView.getDuration()) {
                i2 = this.mMediaPlayerView.getDuration();
            }
            seekStep(i2);
        }
    }

    public void onDoubleTapRew() {
        int currentPosition = this.mMediaPlayerView.getCurrentPosition();
        if (currentPosition > 0) {
            int i2 = currentPosition - 10000;
            if (i2 < 0) {
                i2 = 0;
            }
            seekStep(i2);
        }
    }
}
