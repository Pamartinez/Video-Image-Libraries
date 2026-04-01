package com.samsung.android.gallery.app.remote.v2;

import a6.g;
import android.content.Context;
import android.view.MotionEvent;
import b4.C0423c;
import com.samsung.android.gallery.app.remote.type.ControllerButtonType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.media.GifAnimation;
import com.samsung.android.gallery.module.media.GifPlayer;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerListener;
import java.util.Objects;
import java.util.function.Consumer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PresentationController implements ControlViewClickCallback, MediaPlayerListener {
    private final Context mContext;
    GifPlayer mGifPlayer;
    IMediaPlayerView mMediaViewPlayer;
    private IPresentationView mPresentationView;
    private final int mViewerId;

    public PresentationController(Context context, int i2) {
        this.mContext = context;
        this.mViewerId = i2;
    }

    /* access modifiers changed from: private */
    public void internalGifPlay(MediaItem mediaItem) {
        GifPlayer gifPlayer = this.mGifPlayer;
        if (gifPlayer != null) {
            gifPlayer.releaseMovie();
        } else {
            this.mGifPlayer = new GifPlayer();
        }
        GifPlayer gifPlayer2 = this.mGifPlayer;
        if (gifPlayer2 != null && mediaItem != null) {
            if (!gifPlayer2.hasMovie()) {
                GifPlayer gifPlayer3 = this.mGifPlayer;
                Context context = this.mContext;
                IPresentationView iPresentationView = this.mPresentationView;
                Objects.requireNonNull(iPresentationView);
                gifPlayer3.decodeMovie(context, mediaItem, new g(5, iPresentationView), (GifAnimation.AnimationFrameStartListener) null);
            }
            this.mGifPlayer.startMovie();
        }
    }

    private void releaseMovie() {
        GifPlayer gifPlayer = this.mGifPlayer;
        if (gifPlayer != null) {
            gifPlayer.stopMovie();
            this.mGifPlayer.releaseMovie();
            this.mGifPlayer = null;
        }
    }

    public void clear() {
        releaseMovie();
        closeVideo();
    }

    public void closeVideo() {
        IMediaPlayerView iMediaPlayerView = this.mMediaViewPlayer;
        if (iMediaPlayerView != null) {
            if (iMediaPlayerView.isPlaying()) {
                this.mMediaViewPlayer.pauseVideo();
            }
            if (this.mMediaViewPlayer.isOpened()) {
                this.mMediaViewPlayer.close();
                this.mMediaViewPlayer.removeMediaPlayerListener(this);
            }
        }
    }

    public Consumer<MediaItem> internalPlay(MediaItem mediaItem) {
        if (mediaItem.isVideo()) {
            return new C0423c(this, 0);
        }
        if (mediaItem.isGif()) {
            return new C0423c(this, 1);
        }
        return null;
    }

    public void internalVideoPlay(MediaItem mediaItem) {
        IMediaPlayerView iMediaPlayerView = (IMediaPlayerView) this.mPresentationView.getMediaView(-1);
        this.mMediaViewPlayer = iMediaPlayerView;
        if (iMediaPlayerView != null) {
            if (iMediaPlayerView.isOpened()) {
                this.mMediaViewPlayer.close();
                this.mMediaViewPlayer.removeMediaPlayerListener(this);
            }
            if (this.mMediaViewPlayer.open(mediaItem)) {
                this.mMediaViewPlayer.addMediaPlayerListener(this);
                this.mMediaViewPlayer.play();
            }
        }
    }

    public void onClick(ControllerButtonType controllerButtonType) {
        boolean z;
        if (controllerButtonType == ControllerButtonType.PLAY) {
            Blackboard.postEventGlobal(EventMessage.obtain(9006, Integer.valueOf(this.mViewerId)));
        } else if (controllerButtonType == ControllerButtonType.PREV || controllerButtonType == ControllerButtonType.NEXT) {
            int i2 = this.mViewerId;
            if (controllerButtonType == ControllerButtonType.NEXT) {
                z = true;
            } else {
                z = false;
            }
            Blackboard.postEventGlobal(EventMessage.obtain(9004, i2, Boolean.valueOf(z)));
        } else if (controllerButtonType == ControllerButtonType.CLOSE) {
            Blackboard.postEventGlobal(EventMessage.obtain(9005, Integer.valueOf(this.mViewerId)));
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 1) {
            return false;
        }
        this.mPresentationView.toggleControlView();
        return false;
    }

    public void onVideoPlay(int i2) {
        IPresentationView iPresentationView = this.mPresentationView;
        if (iPresentationView != null) {
            iPresentationView.showMediaView();
        }
    }

    public void setView(IPresentationView iPresentationView) {
        this.mPresentationView = iPresentationView;
        iPresentationView.setControlViewClickListener(this);
    }
}
