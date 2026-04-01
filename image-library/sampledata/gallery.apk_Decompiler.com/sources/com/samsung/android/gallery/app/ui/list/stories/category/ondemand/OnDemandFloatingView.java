package com.samsung.android.gallery.app.ui.list.stories.category.ondemand;

import android.content.Context;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface OnDemandFloatingView {
    Blackboard getBlackboard();

    Context getContext();

    String getScreenId() {
        return AnalyticsScreenId.SCREEN_ONDEMAND_STORY_VIEW.toString();
    }

    void setData(List<?> list);
}
