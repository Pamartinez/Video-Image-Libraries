package com.samsung.android.gallery.app.ui.viewer2.common.shotmode;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.PlayOutOfFocusCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SelectiveFocusHandler extends AbsShotModeHandler {
    public void executeInternal(EventContext eventContext, MediaItem mediaItem) {
        new PlayOutOfFocusCmd().execute(eventContext, mediaItem);
    }

    public int getTitleId() {
        return R.string.adjust_background_blur;
    }

    public boolean isEditable() {
        return true;
    }

    public boolean support(MediaItem mediaItem) {
        if (SdkConfig.atLeast(SdkConfig.GED.R) || !mediaItem.isShotMode("selective_focus")) {
            return false;
        }
        return true;
    }

    public boolean supportUpsm() {
        return false;
    }
}
