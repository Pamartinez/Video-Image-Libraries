package com.samsung.android.gallery.app.ui.viewer2.common.shotmode;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.PlayDualShotCmd;
import com.samsung.android.gallery.app.controller.viewer.RequestDismissKeyGuardCmd;
import com.samsung.android.gallery.module.abstraction.DualShotState;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class DualShotHandler extends AbsShotModeHandler {
    /* renamed from: executeInternal */
    public void lambda$executeInternal$0(EventContext eventContext, MediaItem mediaItem) {
        if (isLockScreen(eventContext.getBlackboard())) {
            new RequestDismissKeyGuardCmd().execute(eventContext, new a(this, eventContext, mediaItem, 0));
        } else {
            new PlayDualShotCmd().execute(eventContext, mediaItem, Integer.valueOf(PlayDualShotCmd.SEF_VIEWER));
        }
    }

    public int getTitleId() {
        return R.string.change_background_effect;
    }

    public String getType() {
        return "Dual capture";
    }

    public boolean isEditable() {
        return true;
    }

    public boolean support(MediaItem mediaItem) {
        if (!PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_ABOVE_DETAILS && mediaItem.isShotMode("Dual capture") && DetailsData.of(mediaItem).dualShotState == DualShotState.CloseUp) {
            return true;
        }
        return false;
    }

    public boolean supportUpsm() {
        return false;
    }
}
