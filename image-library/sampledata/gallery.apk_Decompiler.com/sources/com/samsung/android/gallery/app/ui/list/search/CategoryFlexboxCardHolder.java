package com.samsung.android.gallery.app.ui.list.search;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryItemAdapterV2;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.utils.ResourceUtil;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CategoryFlexboxCardHolder extends CategoryCardHolderV2 {
    public CategoryFlexboxCardHolder(View view, int i2) {
        super(view, i2);
    }

    private void setGradientBackground(ISearchView iSearchView) {
        if (this.mListView != null) {
            this.mListView.setBackground(new LayerDrawable(new Drawable[]{new ColorDrawable(this.mListView.getContext().getColor(R.color.daynight_default_background)), new GradientDrawable(GradientDrawable.Orientation.TL_BR, ResourceUtil.BG_GRADIENT)}));
            ViewUtils.setViewShape(this.mListView, 1, (float) iSearchView.getResources().getDimensionPixelOffset(R.dimen.stories_category_on_demand_round_radius));
        }
    }

    public void bindView(View view) {
        super.bindView(view);
        GalleryListView galleryListView = this.mListView;
        if (galleryListView != null) {
            galleryListView.setFirstItemDecoration(0);
        }
    }

    public CategoryItemAdapterV2 createAdapter(ISearchView iSearchView, String str) {
        return new CategoryItemAdapterV2(iSearchView, str, this.mListView, this.mPropertyHelper, false);
    }

    public int getContentPaddingTop() {
        if (PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
            return super.getContentPaddingTop();
        }
        return getDimensionPixelOffset(R.dimen.search_category_shot_mode_list_top_margin_61);
    }

    public void initListView(ISearchView iSearchView, String str) {
        if (this.mListView != null) {
            if (!isMediaDataAvailable(iSearchView, str)) {
                String str2 = this.TAG;
                Log.sw(str2, "bindListView : mediaData is not available " + str);
            } else if (this.mItemAdapter != null) {
                BooleanSupplier booleanSupplier = this.mNotifySupplier;
                if (booleanSupplier != null && booleanSupplier.getAsBoolean()) {
                    this.mItemAdapter.notifyDataSetChanged();
                }
            } else {
                CategoryItemAdapterV2 createAdapter = createAdapter(iSearchView, str);
                this.mItemAdapter = createAdapter;
                this.mListView.setRecycledViewPool(createViewPool(createAdapter));
                this.mListView.setAdapter(this.mItemAdapter);
                FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(iSearchView.getActivity());
                flexboxLayoutManager.setFlexWrap(1);
                flexboxLayoutManager.setFlexDirection(0);
                flexboxLayoutManager.setAlignItems(4);
                this.mListView.setLayoutManager(flexboxLayoutManager);
                if (getItemViewType() == 9) {
                    setGradientBackground(iSearchView);
                }
            }
        }
    }

    public void updateContentPadding(boolean z) {
        if (this.mListView != null) {
            if (getItemViewType() == 9) {
                updateItemViewMargin(z);
                GalleryListView galleryListView = this.mListView;
                ViewMarginUtils.setHorizontalMargin(galleryListView, this.mPropertyHelper.getCardListHorizontalMargin(galleryListView.getContext()));
                ViewMarginUtils.setTopMargin(this.mListView, getDimensionPixelOffset(R.dimen.search_card_screenshot_list_margin_top));
                ViewMarginUtils.setHorizontalPadding(this.mListView, this.mPropertyHelper.getCardListHorizontalPadding(this.mListView.getContext()) - this.mPropertyHelper.getItemHorizontalSpacing(this.itemView.getContext()));
                int dimensionPixelOffset = getDimensionPixelOffset(R.dimen.search_card_screenshot_list_padding_vertical);
                if (this.mPropertyHelper.hasItemMarginTopOnCard()) {
                    dimensionPixelOffset -= this.mPropertyHelper.getItemMarginTop(this.itemView.getContext());
                }
                ViewMarginUtils.setVerticalPadding(this.mListView, dimensionPixelOffset);
                return;
            }
            super.updateContentPadding(z);
        }
    }
}
