package com.samsung.android.gallery.module.grid;

import com.samsung.android.gallery.module.R$array;
import com.samsung.android.gallery.support.utils.PreferenceName;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class StoryPicturesImpl extends GridHelper {
    public StoryPicturesImpl() {
        super("location://story/albums/fileList");
    }

    public int[] buildSpanInfo() {
        return new int[]{1, 17};
    }

    public int getDefaultDepth() {
        return GridHelper.STORY_PICTURES_DEFAULT_DEPTH;
    }

    public int getGridArrayResource() {
        return R$array.story_pictures_legacy_column_count;
    }

    public String getNotifyKey() {
        return "command://StoryPicturesViewChanged";
    }

    public PreferenceName getPreferenceName() {
        return PreferenceName.STORY_PICTURES_GRID_SIZE;
    }
}
