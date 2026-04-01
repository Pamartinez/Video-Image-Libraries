package com.samsung.android.gallery.app.ui.list.search;

import android.os.Bundle;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryItemAdapterV2;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CategoryPeople2CardHolder extends CategoryCardHolderV2 {
    protected boolean mIsHiddenOnlyMode;

    public CategoryPeople2CardHolder(View view, int i2) {
        super(view, i2);
    }

    public void bind(ISearchView iSearchView, MediaData mediaData) {
        super.bind(iSearchView, mediaData);
        if (this.mIsHiddenOnlyMode) {
            ViewMarginUtils.setTopMargin(this.mHeaderLayout, getDimensionPixelOffset(R.dimen.search_card_header_margin_top_on_no_item));
        }
    }

    public int getContentPaddingTop() {
        CategoryItemAdapterV2 categoryItemAdapterV2 = this.mItemAdapter;
        if (categoryItemAdapterV2 == null || categoryItemAdapterV2.getItemCount() <= 0) {
            return 0;
        }
        return super.getContentPaddingTop();
    }

    public int getHiddenItemCount(Bundle bundle) {
        if (bundle != null) {
            return bundle.getInt("hiddenPeopleCount", 0);
        }
        return 0;
    }

    public void setArrow(ISearchView iSearchView, int i2, MediaData mediaData) {
        int hiddenItemCount;
        if (!PreferenceFeatures.OneUi5x.SEARCH_HIDE_PEOPLE || i2 != 0 || (hiddenItemCount = getHiddenItemCount(mediaData.getExtras())) <= 0) {
            this.mIsHiddenOnlyMode = false;
            super.setArrow(iSearchView, i2, mediaData);
            return;
        }
        this.mIsHiddenOnlyMode = true;
        super.setArrow(iSearchView, hiddenItemCount, mediaData);
    }

    public void updateDividerMarginTop() {
        if (this.mIsHiddenOnlyMode) {
            ViewMarginUtils.setTopMargin(this.mDivider, getDimensionPixelOffset(R.dimen.search_card_divider_margin_top_on_no_item));
        } else {
            super.updateDividerMarginTop();
        }
    }
}
