package com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem;

import android.view.View;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesCatTransitoryItemRecapViewHolder extends StoriesCatTransitoryItemViewHolderV80 {
    public StoriesCatTransitoryItemRecapViewHolder(View view, int i2, String str) {
        super(view, i2, str);
    }

    public void initSaveIcon(View view) {
        ViewUtils.setVisibleOrGone(view.findViewById(R.id.icon_save), false);
    }
}
