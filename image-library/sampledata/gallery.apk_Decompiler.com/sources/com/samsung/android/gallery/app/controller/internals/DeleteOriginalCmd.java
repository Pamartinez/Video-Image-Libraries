package com.samsung.android.gallery.app.controller.internals;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeleteOriginalCmd extends DeleteCmd {
    public String getAnalyticsDetail() {
        MediaItem[] selectedItems = getHandler().getSelectedItems();
        if (selectedItems == null) {
            return "0";
        }
        return String.valueOf(selectedItems.length);
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_STORY_DELETE_ORIGINAL.toString();
    }
}
