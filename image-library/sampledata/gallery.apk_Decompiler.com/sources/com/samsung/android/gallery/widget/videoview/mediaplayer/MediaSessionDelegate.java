package com.samsung.android.gallery.widget.videoview.mediaplayer;

import android.content.Intent;
import android.media.session.MediaSession;
import android.view.KeyEvent;
import com.samsung.android.gallery.module.media.GalleryMediaSession;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StringCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaSessionDelegate extends MediaSession.Callback implements MediaPlayerListener {
    private final StringCompat TAG = new StringCompat(getClass().getSimpleName());
    private final IMediaPlayerInnerView mView;

    public MediaSessionDelegate(IMediaPlayerInnerView iMediaPlayerInnerView) {
        this.mView = iMediaPlayerInnerView;
    }

    private boolean isMediaButtonActionDown(Intent intent, KeyEvent keyEvent) {
        if (!"android.intent.action.MEDIA_BUTTON".equals(intent.getAction()) || keyEvent == null || keyEvent.getAction() != 0) {
            return false;
        }
        return true;
    }

    private void togglePlayback() {
        if (this.mView.isPlaying()) {
            this.mView.pauseVideo();
        } else if (this.mView.isPaused()) {
            this.mView.resumeVideo();
        }
    }

    public void createMediaSession() {
        Log.d(this.TAG, "createMediaSession");
        GalleryMediaSession.createMediaSession(this, false);
    }

    public boolean onMediaButtonEvent(Intent intent) {
        int keyCode;
        KeyEvent keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
        if (!isMediaButtonActionDown(intent, keyEvent) || ((keyCode = keyEvent.getKeyCode()) != 79 && keyCode != 85 && keyCode != 126 && keyCode != 127)) {
            return super.onMediaButtonEvent(intent);
        }
        togglePlayback();
        return true;
    }

    public void onVideoCompleted() {
        GalleryMediaSession.setPlaybackState(1, 0);
    }

    public void onVideoPause(int i2) {
        GalleryMediaSession.setPlaybackState(2, (long) i2);
    }

    public void onVideoPlay(int i2) {
        GalleryMediaSession.setPlaybackState(3, (long) i2);
    }

    public void releaseMediaSession() {
        Log.d(this.TAG, "releaseMediaSession");
        GalleryMediaSession.releaseMediaSession();
    }

    public void setLogTag(Object obj) {
        this.TAG.setTag(obj);
    }
}
