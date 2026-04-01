package com.samsung.android.gallery.app.controller.externals;

import com.samsung.android.gallery.support.analytics.AnalyticsEventId;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PlayFastMotionVideoCmd extends PlaySlowMotionVideoCmd {
    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_PLAY_ICON_FAST_MOTION.toString();
    }

    public int getMotionType() {
        return 1;
    }
}
