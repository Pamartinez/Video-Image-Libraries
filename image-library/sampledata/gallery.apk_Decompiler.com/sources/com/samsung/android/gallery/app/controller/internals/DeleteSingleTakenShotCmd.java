package com.samsung.android.gallery.app.controller.internals;

import android.util.Pair;
import com.samsung.android.gallery.app.controller.VuAnalytics;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.logger.AnalyticsLogger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeleteSingleTakenShotCmd extends DeleteGroupShotCmd {
    public int getGroupType() {
        return 2;
    }

    public void postAnalyticsLog() {
        MediaItem[] mediaItemArr = this.mItems;
        if (mediaItemArr != null && mediaItemArr.length > 0) {
            AnalyticsLogger.getInstance().postCustomDimension(getScreenId(), getEventId(), getScreenLabel(), (Pair<String, String>[]) VuAnalytics.getViewerCustomDimensions(mediaItemArr[0], getAnalyticsDetail()));
        }
    }
}
