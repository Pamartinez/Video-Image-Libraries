package com.samsung.android.gallery.widget.videoview.mediaplayer;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public enum PlayState {
    ERROR,
    NONE,
    SET_DATA,
    PREPARING,
    PREPARED,
    PLAY,
    PAUSE,
    COMPLETE;

    public int value() {
        return ordinal();
    }
}
