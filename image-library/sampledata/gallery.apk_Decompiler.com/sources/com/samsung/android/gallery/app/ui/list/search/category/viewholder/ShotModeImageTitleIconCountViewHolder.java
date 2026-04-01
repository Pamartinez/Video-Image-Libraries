package com.samsung.android.gallery.app.ui.list.search.category.viewholder;

import A.a;
import android.view.View;
import com.samsung.android.gallery.app.ui.viewholders.ImageTitleCountViewHolder;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.type.CategoryType;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ShotModeImageTitleIconCountViewHolder extends ImageTitleCountViewHolder.IconViewHolder {
    public ShotModeImageTitleIconCountViewHolder(View view, int i2) {
        super(view, i2);
    }

    public void bind(MediaItem mediaItem) {
        super.bind(mediaItem);
        try {
            bindIcon(CategoryType.VISUAL_SEARCH_SUB_CATEGORY_SHOT_MODE_ICON_MAP.get(mediaItem.getSubCategory()).intValue());
        } catch (Exception e) {
            a.s(e, new StringBuilder("bindIcon failed "), this.TAG);
        }
    }
}
