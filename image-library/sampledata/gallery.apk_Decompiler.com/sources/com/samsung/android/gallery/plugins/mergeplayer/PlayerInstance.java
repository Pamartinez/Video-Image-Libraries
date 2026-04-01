package com.samsung.android.gallery.plugins.mergeplayer;

import Aa.a;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.videoview.mediaplayer.IMediaPlayerView;
import com.samsung.android.gallery.widget.videoview.mediaplayer.MediaPlayerListener;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PlayerInstance implements MediaPlayerListener {
    private final MergePlayerListener mListener;
    private final MediaItem mMediaItem;
    private final int mPosition;
    private final IMediaPlayerView mView;

    public PlayerInstance(int i2, MediaItem mediaItem, IMediaPlayerView iMediaPlayerView, MergePlayerListener mergePlayerListener) {
        this.mPosition = i2;
        this.mMediaItem = mediaItem;
        this.mView = iMediaPlayerView;
        iMediaPlayerView.addMediaPlayerListener(this);
        iMediaPlayerView.setLogTag(Integer.valueOf(i2));
        this.mListener = mergePlayerListener;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onTimeTickUpdate$2() {
        this.mListener.onVideoCompleted(this.mPosition);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onVideoPlay$1() {
        this.mListener.onVideoPlay(this.mPosition);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onVideoPrepared$0() {
        this.mListener.onVideoPrepared(this.mPosition);
    }

    public void close() {
        this.mView.setVisibility(8);
        this.mView.close();
    }

    public int getDuration() {
        return this.mView.getDuration();
    }

    public IMediaPlayerView getView() {
        return this.mView;
    }

    public void onTimeTickUpdate(int i2, int i7) {
        if (i2 <= i7 + 100) {
            ThreadUtil.postOnUiThread(new a(this, 1));
        }
    }

    public void onVideoPlay(int i2) {
        ThreadUtil.postOnUiThread(new a(this, 2));
    }

    public void onVideoPrepared(int i2, int i7) {
        Log.d("VVV", "pos = " + this.mPosition + " / prepared ");
        ThreadUtil.postOnUiThread(new a(this, 0));
    }

    public boolean open() {
        this.mView.setAlpha(0.0f);
        if (!this.mView.open(this.mMediaItem)) {
            return false;
        }
        this.mView.setAudioMute(false);
        this.mView.play();
        return true;
    }

    public void onVideoCompleted() {
    }
}
