package com.samsung.android.gallery.app.controller.viewer;

import android.content.Context;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.StringResources;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SaveBurstShotCmd extends SaveGroupShotCmd {
    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_SAVE_GROUP_IMAGE.toString();
    }

    public String getShotName(Context context) {
        return context.getResources().getString(StringResources.getBurstShotStringId(R.string.burst_shot));
    }

    public boolean isContainVideo() {
        return false;
    }
}
