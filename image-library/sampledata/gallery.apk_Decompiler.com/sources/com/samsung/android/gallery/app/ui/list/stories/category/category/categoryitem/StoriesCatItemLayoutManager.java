package com.samsung.android.gallery.app.ui.list.stories.category.category.categoryitem;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.stories.category.helper.ItemProperty;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesCatItemLayoutManager extends GridLayoutManager {
    private final String mLocationKey;

    public StoriesCatItemLayoutManager(Context context, int i2, String str) {
        super(context, i2, 0, false);
        this.mLocationKey = str;
    }

    private void updateLayout(View view) {
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        int[] itemSize = ItemProperty.getItemSize(view.getContext(), this.mLocationKey);
        int[] itemHorizontalMargin = ItemProperty.getItemHorizontalMargin(view.getContext(), this.mLocationKey);
        layoutParams.width = itemSize[0];
        layoutParams.height = itemSize[1];
        layoutParams.setMarginStart(itemHorizontalMargin[0]);
        layoutParams.setMarginEnd(itemHorizontalMargin[1]);
        layoutParams.topMargin = itemHorizontalMargin[0];
        layoutParams.bottomMargin = itemHorizontalMargin[1];
        view.setLayoutParams(layoutParams);
        ViewUtils.setViewShape(view, 1, (float) ItemProperty.getItemRadius(view.getContext(), this.mLocationKey));
    }

    public void addView(View view, int i2) {
        updateLayout(view);
        super.addView(view, i2);
    }
}
