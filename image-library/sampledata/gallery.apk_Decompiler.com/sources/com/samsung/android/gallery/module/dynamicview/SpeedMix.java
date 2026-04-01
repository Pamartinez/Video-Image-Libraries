package com.samsung.android.gallery.module.dynamicview;

import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpeedMix extends DynamicView {
    public AnalyticsDetail getAnalyticsDetail() {
        return AnalyticsDetail.EVENT_DETAIL_SPEED_MIX;
    }

    public ArrayList<PlaybackOption> getPlayList() {
        return getRawPlayList();
    }

    public int getType() {
        return 0;
    }

    public int getTypeStingId() {
        return R$string.speed_mix;
    }
}
