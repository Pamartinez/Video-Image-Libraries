package com.samsung.android.gallery.app.ui.viewer2.common.shotmode;

import android.text.TextUtils;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.StartBrowserCmd;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class UrlLinkHandler extends AbsShotModeHandler {
    private boolean isGotoUrlEnabled() {
        return Features.isEnabled(Features.SUPPORT_GO_TO_URL);
    }

    public void executeInternal(EventContext eventContext, MediaItem mediaItem) {
        new StartBrowserCmd().execute(eventContext, mediaItem);
    }

    public int getPlayButtonIconId() {
        return R.drawable.internet;
    }

    public int getTitleId() {
        return R.string.go_to_source;
    }

    public boolean isEnableToRunCloudOnly() {
        return true;
    }

    public boolean support(MediaItem mediaItem) {
        DetailsData of2 = DetailsData.of(mediaItem);
        if (TextUtils.isEmpty(of2.capturedUrl) || !isGotoUrlEnabled()) {
            return false;
        }
        String str = this.TAG;
        Log.d(str, "capture info", Logger.getEncodedString(of2.capturedApp + ArcCommonLog.TAG_COMMA + of2.capturedUrl));
        return StartBrowserCmd.supportAppLink(of2.capturedApp, of2.capturedUrl);
    }
}
