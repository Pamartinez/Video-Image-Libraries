package com.samsung.android.gallery.app.ui.list.search.category.viewholder;

import android.view.View;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.type.ActivityType;
import com.samsung.android.gallery.support.type.CategoryType;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CategoryActivityItemViewHolder extends CategoryShotModeItemViewHolderV2 {
    public CategoryActivityItemViewHolder(View view, int i2) {
        super(view, i2);
    }

    public int getDrawableResId(MediaItem mediaItem) {
        return CategoryType.VISUAL_SEARCH_SUB_CATEGORY_ACTIVITY_ICON_MAP.get(mediaItem.getTitle()).intValue();
    }

    public String getTitleText(MediaItem mediaItem) {
        return this.itemView.getContext().getString(ActivityType.getTitleRes(mediaItem.getTitle()));
    }

    public boolean showCount(MediaItem mediaItem) {
        return false;
    }
}
