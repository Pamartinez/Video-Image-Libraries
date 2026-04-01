package com.samsung.android.gallery.app.ui.viewer2.common.shotmode;

import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.StartAppLinkCmd;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class AppLinkHandler extends AbsShotModeHandler {
    public void executeInternal(EventContext eventContext, MediaItem mediaItem) {
        new StartAppLinkCmd().execute(eventContext, mediaItem);
    }

    public int getTitleId() {
        return R.string.play_in_liteplay;
    }

    public boolean support(MediaItem mediaItem) {
        String str = DetailsData.of(mediaItem).capturedUrl;
        if (str == null || !str.startsWith("package://")) {
            return false;
        }
        return true;
    }
}
