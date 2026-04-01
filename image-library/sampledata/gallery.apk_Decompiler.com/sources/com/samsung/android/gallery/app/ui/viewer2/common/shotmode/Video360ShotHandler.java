package com.samsung.android.gallery.app.ui.viewer2.common.shotmode;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.PlayVideoCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class Video360ShotHandler extends AbsShotModeHandler {
    public void executeInternal(EventContext eventContext, MediaItem mediaItem) {
        if (mediaItem.isSharing()) {
            assertSharingVideo(mediaItem);
        } else {
            new PlayVideoCmd().execute(eventContext, mediaItem);
        }
    }

    public int getTitleId() {
        return R.string.play_360_video;
    }

    public boolean isAudioEnabled() {
        return true;
    }

    public boolean isEnableToRunCloudOnly() {
        return true;
    }

    public boolean support(MediaItem mediaItem) {
        return mediaItem.isShotMode("360_video");
    }
}
