package com.samsung.android.gallery.support.blackboard.key;

import c0.C0086a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class EventKey {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class EventKeyNameHolder {
        static final String[] name = {"EVENT_DATA_CHANGED_BASE", "EVENT_PICTURES_DATA_CHANGED", "EVENT_STORIES_DATA_CHANGED", "EVENT_SEARCH_DATA_CHANGED", "EVENT_ALBUMS_DATA_CHANGED", "EVENT_MTP_DATA_CHANGED", "EVENT_MDE_SPACE_DATA_CHANGED", "EVENT_MDE_GROUP_DATA_CHANGED", "EVENT_MDE_INVITATION_DATA_CHANGED", "EVENT_MDE_SPACE_DETAIL_DATA_CHANGED", "EVENT_DUMMY_DATA_CHANGED", ""};
    }

    public static String getName(int i2) {
        if (i2 >= 110 || i2 <= 100) {
            return C0086a.i(i2, "E");
        }
        return EventKeyNameHolder.name[i2 - 100];
    }
}
