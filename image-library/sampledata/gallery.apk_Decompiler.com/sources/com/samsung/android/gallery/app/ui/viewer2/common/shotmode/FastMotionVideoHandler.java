package com.samsung.android.gallery.app.ui.viewer2.common.shotmode;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.PlayFastMotionVideoCmd;
import com.samsung.android.gallery.app.controller.externals.PlayVideoCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class FastMotionVideoHandler extends SlowMotionHandler {
    public void executeInternal(EventContext eventContext, MediaItem mediaItem) {
        if (mediaItem.isSharing()) {
            assertSharingVideo(mediaItem);
        } else if (executableMotionEditor()) {
            new PlayFastMotionVideoCmd().execute(eventContext, mediaItem);
        } else {
            new PlayVideoCmd().execute(eventContext, mediaItem);
        }
    }

    public int getTitleId() {
        return R.string.play_fast_mo_video;
    }

    public boolean support(MediaItem mediaItem) {
        if (SdkConfig.atLeast(SdkConfig.GED.R) || !mediaItem.isShotMode("fast_motion")) {
            return false;
        }
        return true;
    }
}
