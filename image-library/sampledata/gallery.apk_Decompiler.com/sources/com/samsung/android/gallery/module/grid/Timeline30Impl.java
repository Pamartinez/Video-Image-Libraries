package com.samsung.android.gallery.module.grid;

import com.samsung.android.gallery.module.R$array;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.PreferenceName;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class Timeline30Impl extends TimelineImpl {
    public int[] buildMonth() {
        return new int[]{1};
    }

    public int[] buildSpanInfo() {
        return new int[]{67, 2, 1, 1, 17};
    }

    public int getDefaultDepth() {
        return 2;
    }

    public int getGridArrayResource() {
        return R$array.timeline_year_column_count;
    }

    public int getGridDepth() {
        GalleryPreference instance = GalleryPreference.getInstance();
        PreferenceName preferenceName = PreferenceName.TIMELINE_GRID_SIZE_V30;
        int loadInt = instance.loadInt(preferenceName, -1);
        if (loadInt < 0) {
            loadInt = GalleryPreference.getInstance().loadInt(PreferenceName.TIMELINE_GRID_SIZE, -1);
            if (loadInt < 0) {
                loadInt = 2;
            } else if (loadInt < 1) {
                loadInt = 1;
            }
            GalleryPreference.getInstance().saveState(preferenceName, loadInt);
        }
        return loadInt;
    }

    public PreferenceName getPreferenceName() {
        return PreferenceName.TIMELINE_GRID_SIZE_V30;
    }

    public int getSplitArrayResource() {
        return R$array.timeline_year_column_count_split;
    }

    public boolean isMonth(int i2) {
        if (i2 == 1) {
            return true;
        }
        return false;
    }

    public boolean isSelectable(int i2) {
        if (i2 > 0) {
            return true;
        }
        return false;
    }

    public boolean isYear(int i2) {
        if (i2 == 0) {
            return true;
        }
        return false;
    }
}
