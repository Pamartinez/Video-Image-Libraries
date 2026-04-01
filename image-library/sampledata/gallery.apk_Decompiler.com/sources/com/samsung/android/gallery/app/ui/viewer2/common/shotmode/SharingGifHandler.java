package com.samsung.android.gallery.app.ui.viewer2.common.shotmode;

import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.sharing.RequestDownloadImageCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Features;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingGifHandler extends AbsShotModeHandler {
    public void executeInternal(EventContext eventContext, MediaItem mediaItem) {
        AnalyticsLogger.getInstance().postLog(eventContext.getScreenId(), AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_ICON_CLOUD_AGIF.toString());
        new RequestDownloadImageCmd().execute(eventContext, mediaItem, Boolean.TRUE);
    }

    public int getTitleId() {
        return R.string.view_cloud_gif;
    }

    public boolean support(MediaItem mediaItem) {
        if (!mediaItem.isGif() || !mediaItem.isSharing() || !Features.isEnabled(Features.SUPPORT_SHARED_GIF_PLAY) || !TextUtils.isEmpty(MediaItemMde.getHiddenFilePath(mediaItem))) {
            return false;
        }
        return true;
    }

    public boolean supportExecuteInSharing() {
        return true;
    }
}
