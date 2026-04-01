package com.samsung.android.gallery.module.grid;

import com.samsung.android.gallery.module.R$array;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.PreferenceName;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class StoriesImpl extends GridHelper {
    private final boolean ONE_UI_50 = PreferenceFeatures.OneUi5x.STORY_ONE_UI_50;

    public StoriesImpl() {
        super("location://story/albums");
    }

    public int[] buildSpanInfo() {
        return new int[]{1, 1};
    }

    public int getDefaultDepth() {
        return 0;
    }

    public int getGridArrayResource() {
        if (this.ONE_UI_50) {
            return R$array.stories_pinch_column_count_oneui_60;
        }
        return R$array.stories_pinch_column_count;
    }

    public int getGridArrayResourceOnDex() {
        if (this.ONE_UI_50) {
            return R$array.stories_pinch_column_count_oneui_60;
        }
        return R$array.stories_pinch_column_count;
    }

    public PreferenceName getPreferenceName() {
        return PreferenceName.STORIES_GRID_SIZE;
    }

    public int getSplitArrayResource() {
        if (this.ONE_UI_50) {
            return R$array.stories_split_column_count_oneui_60;
        }
        return R$array.stories_split_column_count;
    }

    public int getSplitArrayResourceOnDex() {
        if (this.ONE_UI_50) {
            return R$array.stories_split_column_count_oneui_60;
        }
        return R$array.stories_split_column_count;
    }
}
