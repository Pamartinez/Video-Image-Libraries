package com.samsung.android.gallery.app.ui.viewer2.common.shotmode;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.externals.StartBrowserCmd;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.deepsky.ScreenShotAction;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ScreenShotHandler extends AbsShotModeHandler {
    ScreenShotAction mAction;

    public ScreenShotHandler() {
        this.mDualButton = true;
    }

    private boolean isGotoUrlEnabled() {
        return Features.isEnabled(Features.SUPPORT_GO_TO_URL);
    }

    private boolean supportAiButton(MediaItem mediaItem) {
        ScreenShotAction load = new ScreenShotAction().load(mediaItem);
        this.mAction = load;
        return load.supported();
    }

    public void executeExtra(EventContext eventContext, MediaItem mediaItem) {
        String actionUrl = this.mAction.getActionUrl();
        String str = this.TAG;
        Log.d(str, "executeExtra : " + actionUrl);
        try {
            Uri parse = Uri.parse(actionUrl);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(parse);
            eventContext.getContext().startActivity(intent);
            AnalyticsLogger.getInstance().postLog(AnalyticsScreenId.SCREEN_DETAIL_VIEW_PICTURE.toString(), AnalyticsEventId.EVENT_DETAIL_VIEW_ADD_TO_WALLET_SELECT.toString(), getClassification());
        } catch (ActivityNotFoundException e) {
            String str2 = this.TAG;
            Log.e((CharSequence) str2, "executeExtra failed e=" + e.getMessage(), actionUrl);
        }
    }

    public void executeInternal(EventContext eventContext, MediaItem mediaItem) {
        if (this.mDualButton) {
            new StartBrowserCmd().execute(eventContext, mediaItem);
        } else {
            executeExtra(eventContext, mediaItem);
        }
    }

    public String getClassification() {
        if (this.mAction.getClassification() != null) {
            return this.mAction.getClassification();
        }
        return "";
    }

    public Uri getExtraButtonUri() {
        return this.mAction.getIconUri();
    }

    public String getExtraTitleString() {
        return this.mAction.getTitle();
    }

    public int getPlayButtonIconId() {
        return R.drawable.internet;
    }

    public int getTitleId() {
        if (this.mDualButton) {
            return R.string.go_to_source;
        }
        return 0;
    }

    public boolean isEnableToRunCloudOnly() {
        return true;
    }

    public boolean support(MediaItem mediaItem) {
        boolean z;
        boolean supportAiButton = supportAiButton(mediaItem);
        if (TextUtils.isEmpty(DetailsData.of(mediaItem).capturedUrl) || !isGotoUrlEnabled() || !supportAiButton) {
            z = false;
        } else {
            z = true;
        }
        this.mDualButton = z;
        return supportAiButton;
    }
}
