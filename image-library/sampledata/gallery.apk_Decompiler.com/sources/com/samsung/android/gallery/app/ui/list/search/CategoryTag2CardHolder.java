package com.samsung.android.gallery.app.ui.list.search;

import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryPropertyHelper;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryTagItemAdapterV2;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CategoryTag2CardHolder extends ListViewHolder implements ICategoryCardViewHolder {
    ImageView mArrow;
    View mDivider;
    private CategoryTagItemAdapterV2 mItemAdapter;
    RecyclerView mListView;
    private CategoryPropertyHelper mPropertyHelper;

    public CategoryTag2CardHolder(View view, int i2) {
        super(view, i2);
    }

    private void bindListView(ISearchView iSearchView, String str) {
        if (this.mItemAdapter != null) {
            return;
        }
        if (!isMediaDataAvailable(iSearchView, str)) {
            String str2 = this.TAG;
            Log.sw(str2, "bindListView : mediaData is not available " + str);
            return;
        }
        CategoryTagItemAdapterV2 categoryTagItemAdapterV2 = new CategoryTagItemAdapterV2(iSearchView, str, this.mPropertyHelper, false);
        this.mItemAdapter = categoryTagItemAdapterV2;
        this.mListView.setAdapter(categoryTagItemAdapterV2);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(iSearchView.getActivity());
        flexboxLayoutManager.setFlexWrap(1);
        flexboxLayoutManager.setFlexDirection(0);
        flexboxLayoutManager.setAlignItems(4);
        this.mListView.setLayoutManager(flexboxLayoutManager);
    }

    private void createCategoryPropertyHelper(String str) {
        if (this.mPropertyHelper == null) {
            this.mPropertyHelper = CategoryPropertyHelper.getInstance(str, false);
        }
    }

    private int getContentPaddingBottom(boolean z) {
        if (z) {
            return getDimensionPixelOffset(R.dimen.search_card_last_content_padding_bottom) - this.mPropertyHelper.getItemMarginBottom(this.mListView.getContext());
        }
        return 0;
    }

    private int getContentPaddingTop() {
        int dimensionPixelOffset = getDimensionPixelOffset(R.dimen.search_tag_card_padding_top);
        if (this.mPropertyHelper.hasItemMarginTopOnCard()) {
            return dimensionPixelOffset - this.mPropertyHelper.getItemMarginTop(this.mListView.getContext());
        }
        return dimensionPixelOffset;
    }

    private int getDimensionPixelOffset(int i2) {
        return this.mListView.getContext().getResources().getDimensionPixelOffset(i2);
    }

    private boolean isMediaDataAvailable(ISearchView iSearchView, String str) {
        MediaData mediaData = iSearchView.getMediaData(str);
        if (mediaData == null || !mediaData.isDataAvailable()) {
            return false;
        }
        return true;
    }

    private void setArrow(String str, int i2) {
        if (!this.mPropertyHelper.enableMoreButton(this.mListView.getContext(), i2)) {
            this.mArrow.setVisibility(8);
            return;
        }
        this.mArrow.setVisibility(0);
        this.mArrow.setTag(str);
        this.mArrow.setContentDescription(this.mListView.getContext().getString(R.string.view_more));
    }

    public void bind(ISearchView iSearchView, MediaData mediaData) {
        String locationKey = mediaData.getLocationKey();
        createCategoryPropertyHelper(locationKey);
        setArrow(locationKey, mediaData.getCount());
        this.mArrow.setOnClickListener(new o(iSearchView, locationKey));
        bindListView(iSearchView, locationKey);
        this.mListView.setNestedScrollingEnabled(false);
    }

    public final void bindView(View view) {
        this.mListView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        this.mArrow = (ImageView) view.findViewById(R.id.item_arrow);
        this.mDivider = view.findViewById(R.id.category_divider);
    }

    public boolean hasCheckbox() {
        return false;
    }

    public void onConfigurationChanged() {
        CategoryTagItemAdapterV2 categoryTagItemAdapterV2 = this.mItemAdapter;
        if (categoryTagItemAdapterV2 != null) {
            categoryTagItemAdapterV2.notifyDataSetChanged();
        }
    }

    public void recycle() {
        super.recycle();
        CategoryTagItemAdapterV2 categoryTagItemAdapterV2 = this.mItemAdapter;
        if (categoryTagItemAdapterV2 != null) {
            categoryTagItemAdapterV2.destroy();
        }
        this.mItemAdapter = null;
        RecyclerView recyclerView = this.mListView;
        if (recyclerView != null) {
            recyclerView.setAdapter((RecyclerView.Adapter) null);
        }
        this.mPropertyHelper = null;
    }

    public void updateContentPadding(boolean z) {
        RecyclerView recyclerView = this.mListView;
        recyclerView.setPadding(recyclerView.getPaddingStart(), getContentPaddingTop(), this.mListView.getPaddingEnd(), getContentPaddingBottom(z));
    }

    public void updateDivider(boolean z) {
        if (z) {
            this.mDivider.setVisibility(8);
            return;
        }
        this.mDivider.setVisibility(0);
        updateDividerMarginTop();
    }

    public void updateDividerMarginTop() {
        ViewMarginUtils.setMarginRelative(this.mDivider, (Integer) null, Integer.valueOf(getDimensionPixelOffset(R.dimen.search_card_divider_margin_top) - this.mPropertyHelper.getItemMarginBottom(this.mListView.getContext())), (Integer) null, (Integer) null);
    }
}
