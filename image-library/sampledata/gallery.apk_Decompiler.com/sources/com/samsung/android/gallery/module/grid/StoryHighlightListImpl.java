package com.samsung.android.gallery.module.grid;

import com.samsung.android.gallery.module.R$array;
import com.samsung.android.gallery.support.utils.PreferenceName;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class StoryHighlightListImpl extends GridHelper {
    public StoryHighlightListImpl() {
        super("location://story/albums/storyHighlightList");
    }

    public int[] buildSpanInfo() {
        return new int[]{1, 1};
    }

    public int getDefaultDepth() {
        return 1;
    }

    public int getGridArrayResource() {
        return R$array.story_highlight_list_v2_column_count;
    }

    public String getNotifyKey() {
        return "command://StoryHighlightListViewChanged";
    }

    public PreferenceName getPreferenceName() {
        return PreferenceName.STORY_HIGHLIGHT_LIST_GRID_SIZE;
    }
}
