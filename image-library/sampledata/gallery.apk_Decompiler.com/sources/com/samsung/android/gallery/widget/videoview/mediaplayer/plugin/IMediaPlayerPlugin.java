package com.samsung.android.gallery.widget.videoview.mediaplayer.plugin;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.library.abstraction.MediaPlayerCompat;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IMediaPlayerPlugin {
    void close();

    int getRenderingPosition() {
        return 0;
    }

    boolean onVideoInfo(MediaPlayerCompat mediaPlayerCompat, int i2, int i7) {
        return false;
    }

    void open(MediaItem mediaItem);

    void onVideoCompleted(MediaPlayerCompat mediaPlayerCompat) {
    }

    void onVideoPrepared(MediaPlayerCompat mediaPlayerCompat) {
    }

    void setPlaybackLoop(boolean z) {
    }

    void onVideoSizeChanged(MediaPlayerCompat mediaPlayerCompat, int i2, int i7) {
    }

    void setSlowMo(int i2, int i7, boolean z) {
    }
}
