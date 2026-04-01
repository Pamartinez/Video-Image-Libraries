package com.samsung.android.gallery.app.controller.viewer;

import android.content.Context;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SaveSingleTakenShotCmd extends SaveGroupShotCmd {
    public String getDescription(boolean z) {
        if (z) {
            return null;
        }
        return getContext().getString(R.string.delete_all_the_unselected_items);
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_SAVE_GROUP_IMAGE.toString();
    }

    public String getShotName(Context context) {
        return context.getResources().getString(R.string.single_take);
    }

    public String getTitle(int i2) {
        return getContext().getResources().getQuantityString(R.plurals.save_selected_item_plural, i2, new Object[]{Integer.valueOf(i2), getShotName(getContext())});
    }

    public boolean isContainVideo() {
        return true;
    }
}
