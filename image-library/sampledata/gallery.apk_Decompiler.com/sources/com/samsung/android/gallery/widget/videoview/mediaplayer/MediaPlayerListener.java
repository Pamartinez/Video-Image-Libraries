package com.samsung.android.gallery.widget.videoview.mediaplayer;

import android.view.MotionEvent;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface MediaPlayerListener {
    void onVideoPlay(int i2);

    void onSurfaceCreated() {
    }

    void onSurfaceDestroyed() {
    }

    void onVideoCompleted() {
    }

    void onVideoReleased() {
    }

    void onAudioDucked(boolean z) {
    }

    void onAudioFocusChanged(boolean z) {
    }

    void onVideoPause(int i2) {
    }

    void onInstantSlowMoPlay(boolean z, MotionEvent motionEvent) {
    }

    void onTimeTickUpdate(int i2, int i7) {
    }

    void onVideoError(int i2, int i7) {
    }

    void onVideoInfo(int i2, int i7) {
    }

    void onVideoPrepared(int i2, int i7) {
    }

    void onVideoSizeChanged(int i2, int i7) {
    }
}
