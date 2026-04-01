package com.samsung.android.gallery.widget.details;

import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface ViewHolderPlayer {
    MediaItem getMediaItem();

    boolean isPlaying();

    void play();

    void stop();
}
