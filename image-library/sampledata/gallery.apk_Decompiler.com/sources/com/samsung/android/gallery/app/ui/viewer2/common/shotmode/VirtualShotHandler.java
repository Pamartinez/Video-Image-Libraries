package com.samsung.android.gallery.app.ui.viewer2.common.shotmode;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.PlayVirtualShotCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class VirtualShotHandler extends AbsShotModeHandler {
    public void executeInternal(EventContext eventContext, MediaItem mediaItem) {
        new PlayVirtualShotCmd().execute(eventContext, mediaItem);
    }

    public int getTitleId() {
        return R.string.view_virtual_shot;
    }

    public boolean support(MediaItem mediaItem) {
        if (SdkConfig.atLeast(SdkConfig.GED.R) || !mediaItem.isShotMode("virtual_shot")) {
            return false;
        }
        return true;
    }

    public boolean supportUpsm() {
        return false;
    }
}
