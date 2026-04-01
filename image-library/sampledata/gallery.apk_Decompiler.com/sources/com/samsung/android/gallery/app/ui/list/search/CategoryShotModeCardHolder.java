package com.samsung.android.gallery.app.ui.list.search;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryItemAdapterV2;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryPropertyHelper;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryShotModeItemViewHolderV2;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CategoryShotModeCardHolder extends CategoryCardHolderV2 {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ShotModeItemAdapter extends CategoryItemAdapterV2 {
        public ShotModeItemAdapter(ISearchView iSearchView, String str, GalleryListView galleryListView, CategoryPropertyHelper categoryPropertyHelper, boolean z) {
            super(iSearchView, str, galleryListView, categoryPropertyHelper, z);
        }

        private boolean isLastLineItem(int i2) {
            float f = (float) this.mPropertyHelper.getColumnCount(getContext())[0];
            if (((int) Math.ceil((double) (((float) (i2 + 1)) / f))) == ((int) Math.ceil((double) (((float) getDataCount()) / f)))) {
                return true;
            }
            return false;
        }

        public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
            super.onBindViewHolder(listViewHolder, i2);
            ((CategoryShotModeItemViewHolderV2) listViewHolder).updateDividerVisibility(!isLastLineItem(i2));
        }
    }

    public CategoryShotModeCardHolder(View view, int i2) {
        super(view, i2);
    }

    public /* bridge */ /* synthetic */ void bind(ISearchView iSearchView, MediaData mediaData) {
        super.bind(iSearchView, mediaData);
    }

    public CategoryItemAdapterV2 createAdapter(ISearchView iSearchView, String str) {
        return new ShotModeItemAdapter(iSearchView, str, this.mListView, this.mPropertyHelper, false);
    }

    public /* bridge */ /* synthetic */ void onConfigurationChanged() {
        super.onConfigurationChanged();
    }

    public /* bridge */ /* synthetic */ void recycle() {
        super.recycle();
    }

    public /* bridge */ /* synthetic */ void setNotifySupplier(BooleanSupplier booleanSupplier) {
        super.setNotifySupplier(booleanSupplier);
    }

    public /* bridge */ /* synthetic */ void updateContentPadding(boolean z) {
        super.updateContentPadding(z);
    }

    public /* bridge */ /* synthetic */ void updateDivider(boolean z) {
        super.updateDivider(z);
    }

    public /* bridge */ /* synthetic */ void updateDividerMarginTop() {
        super.updateDividerMarginTop();
    }
}
