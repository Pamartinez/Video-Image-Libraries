package com.samsung.android.gallery.module.dynamicview;

import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface PlayInfo {
    AnalyticsDetail getAnalyticsDetail();

    ArrayList<PlaybackOption> getPlayList();

    int getShortClipIndex() {
        return 0;
    }

    int getType();

    String getTypeString();

    boolean supportBgm() {
        return true;
    }
}
