package com.samsung.android.gallery.app.controller.externals;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartSecMPSettingsCmd extends BaseCommand {
    public String getEventId() {
        return AnalyticsEventId.EVENT_SEC_MP_PERMISSION_SETTINGS.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        try {
            getContext().startActivity(new Intent("android.settings.MANAGE_APP_ALL_FILES_ACCESS_PERMISSION", Uri.fromParts("package", "com.samsung.android.providers.media", (String) null)).addFlags(268435456));
        } catch (ActivityNotFoundException unused) {
            Log.e(this.TAG, "can not find sec media provider permission activity");
        }
    }
}
