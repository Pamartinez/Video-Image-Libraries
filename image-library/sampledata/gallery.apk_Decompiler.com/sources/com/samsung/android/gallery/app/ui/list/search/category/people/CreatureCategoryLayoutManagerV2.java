package com.samsung.android.gallery.app.ui.list.search.category.people;

import android.content.Context;
import com.samsung.android.gallery.widget.listview.GalleryGridLayoutManager;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreatureCategoryLayoutManagerV2 extends GalleryGridLayoutManager implements ICreatureLayoutManager {
    private int mSideSpacing;

    public CreatureCategoryLayoutManagerV2(Context context, int i2) {
        super(context, i2);
        initDimen(context);
        setInitialPrefetchItemCount(0);
        setItemPrefetchEnabled(false);
    }

    public int getItemHeight(int i2) {
        return 0;
    }

    public int getSpacing(int i2) {
        return this.mSideSpacing;
    }

    public int getTopLayoutHeight() {
        return 0;
    }

    public int getTopPeopleCount() {
        return 0;
    }

    public void initDimen(Context context) {
        this.mSideSpacing = context.getResources().getDimensionPixelOffset(R.dimen.search_category_creature_list_side_spacing_85);
    }

    public void setTop5Count(Integer num) {
    }
}
