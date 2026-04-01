package com.samsung.android.gallery.module.grid;

import com.samsung.android.gallery.module.R$array;
import com.samsung.android.gallery.support.utils.PreferenceAnalytics;
import com.samsung.android.gallery.support.utils.PreferenceName;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class TimelineImpl extends GridHelper {
    public TimelineImpl() {
        super("location://timeline");
    }

    public int[] buildSpanInfo() {
        return new int[]{2, 1, 1, 17};
    }

    public int getDefaultDepth() {
        return 1;
    }

    public int getGridArrayResource() {
        return R$array.timeline_column_count;
    }

    public String getNotifyKey() {
        return "command://TimelineViewChanged";
    }

    public PreferenceName getPreferenceName() {
        return PreferenceName.TIMELINE_GRID_SIZE;
    }

    public int getSplitArrayResource() {
        return R$array.timeline_split_column_count;
    }

    public void saveGridCount(int i2) {
        PreferenceAnalytics.TimelineColumnCount.setInteger(i2);
    }
}
