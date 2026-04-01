package com.samsung.android.gallery.app.ui.list.search.category.location;

import android.content.Context;
import com.samsung.android.gallery.widget.listview.GalleryGridLayoutManager;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchLocationCategoryLayoutManager extends GalleryGridLayoutManager {
    private int mSideSpacing;

    public SearchLocationCategoryLayoutManager(Context context, int i2) {
        super(context, i2);
        initDimen(context);
        setInitialPrefetchItemCount(0);
        setItemPrefetchEnabled(false);
    }

    private void initDimen(Context context) {
        this.mSideSpacing = context.getResources().getDimensionPixelOffset(R.dimen.search_category_location_spacing_expansion_85);
    }

    public int getHeight() {
        return super.getHeight();
    }

    public int getSpacing(int i2) {
        return this.mSideSpacing;
    }
}
