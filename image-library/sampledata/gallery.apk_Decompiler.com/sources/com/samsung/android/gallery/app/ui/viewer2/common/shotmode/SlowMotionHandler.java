package com.samsung.android.gallery.app.ui.viewer2.common.shotmode;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.PlaySlowMotionVideoCmd;
import com.samsung.android.gallery.app.controller.externals.PlayVideoCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Features;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SlowMotionHandler extends AbsShotModeHandler {
    public boolean executableMotionEditor() {
        if (!supportMotionEditor() || Features.isEnabled(Features.SUPPORT_VIDEO_PLAYER_FOR_MOTION_VIDEO)) {
            return false;
        }
        return true;
    }

    public void executeInternal(EventContext eventContext, MediaItem mediaItem) {
        if (mediaItem.isSharing()) {
            assertSharingVideo(mediaItem);
        } else if (executableMotionEditor()) {
            new PlaySlowMotionVideoCmd().execute(eventContext, mediaItem);
        } else {
            new PlayVideoCmd().execute(eventContext, mediaItem);
        }
    }

    public int getTitleId() {
        return R.string.play_slow_mo_video;
    }

    public boolean support(MediaItem mediaItem) {
        return mediaItem.isShotMode("slow_motion");
    }
}
