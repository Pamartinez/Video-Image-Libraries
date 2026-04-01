package com.samsung.android.gallery.app.ui.viewer2.common.shotmode;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.PlaySuperSlowMotionVideoCmd;
import com.samsung.android.gallery.app.controller.externals.PlayVideoCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SuperSlowMotionHandler extends SlowMotionHandler {
    public void executeInternal(EventContext eventContext, MediaItem mediaItem) {
        if (mediaItem.isSharing()) {
            assertSharingVideo(mediaItem);
        } else if (executableMotionEditor()) {
            new PlaySuperSlowMotionVideoCmd().execute(eventContext, mediaItem);
        } else {
            new PlayVideoCmd().execute(eventContext, mediaItem);
        }
    }

    public int getTitleId() {
        return R.string.play_super_slow_mo_video;
    }

    public boolean support(MediaItem mediaItem) {
        return mediaItem.isShotMode("super_slow_mo");
    }
}
