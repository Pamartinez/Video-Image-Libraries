package com.samsung.android.gallery.app.controller.viewer;

import android.content.ComponentName;
import android.content.Intent;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StartSmartViewCmd extends BaseCommand {
    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_CONNECT_SMART_VIEW.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        int i2 = 0;
        try {
            MediaItem mediaItem = objArr[0];
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.samsung.android.smartmirroring", "com.samsung.android.smartmirroring.CastingDialog"));
            intent.addFlags(268435456);
            intent.addFlags(32768);
            intent.putExtra("more_actions_package_name", "com.sec.android.gallery3d");
            if (mediaItem.isImage() && mediaItem.isLocal()) {
                i2 = 1;
            }
            intent.putExtra("more_actions_screen_sharing_mode", i2);
            intent.putExtra("more_actions_connection_m2tv", true);
            getContext().startActivity(intent);
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "startActivity failed", (Throwable) e);
        }
    }
}
