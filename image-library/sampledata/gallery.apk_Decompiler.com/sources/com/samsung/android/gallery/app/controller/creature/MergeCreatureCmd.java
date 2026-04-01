package com.samsung.android.gallery.app.controller.creature;

import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.UriBuilder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MergeCreatureCmd extends BaseCommand {
    public String getEventId() {
        return AnalyticsEventId.EVENT_SEARCH_MENU_MERGE_NAME.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        MediaItem mediaItem = objArr[0];
        if (mediaItem != null) {
            getBlackboard().publish("data:///CreatureHeaderItemForEdit", mediaItem);
            DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/MergeCreature").build()).start();
        }
    }
}
