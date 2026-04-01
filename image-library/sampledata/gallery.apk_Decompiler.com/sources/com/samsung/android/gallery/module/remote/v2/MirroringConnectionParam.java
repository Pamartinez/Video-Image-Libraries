package com.samsung.android.gallery.module.remote.v2;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
enum MirroringConnectionParam {
    PAUSED,
    HIGH_RES,
    DMR,
    VIDEO_PLAYING,
    APP_CASTING;

    public boolean isBackgroundPlaying() {
        if (this == VIDEO_PLAYING || this == APP_CASTING) {
            return true;
        }
        return false;
    }

    public boolean isHidingParam() {
        if (this == PAUSED || this == VIDEO_PLAYING || this == APP_CASTING) {
            return true;
        }
        return false;
    }
}
