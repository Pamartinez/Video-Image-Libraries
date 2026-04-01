package com.samsung.android.gallery.app.ui.viewer2.common.shotmode;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.ViewLiveEffectCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LiveEffectHandler extends AbsShotModeHandler {
    private boolean supportItem(MediaItem mediaItem) {
        if (!mediaItem.isImage() || !mediaItem.isLocal() || !mediaItem.isLiveEffect()) {
            return false;
        }
        return true;
    }

    public void executeInternal(EventContext eventContext, MediaItem mediaItem) {
        ViewLiveEffectCmd viewLiveEffectCmd = new ViewLiveEffectCmd();
        Boolean bool = Boolean.FALSE;
        viewLiveEffectCmd.execute(eventContext, mediaItem, bool, bool);
    }

    public int getTitleId() {
        return R.string.view_live_effect;
    }

    public boolean support(MediaItem mediaItem) {
        if (!SdkConfig.atLeast(SdkConfig.SEM.B_MR5) || !Features.isEnabled(Features.SUPPORT_LIVE_EFFECT_VIEWER) || !supportItem(mediaItem)) {
            return false;
        }
        return true;
    }
}
