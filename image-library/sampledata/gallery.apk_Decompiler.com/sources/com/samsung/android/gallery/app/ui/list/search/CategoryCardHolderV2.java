package com.samsung.android.gallery.app.ui.list.search;

import android.animation.StateListAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryItemAdapterV2;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryPropertyHelper;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.support.blackboard.key.LocationKey;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.GalleryRecyclerView;
import com.samsung.android.gallery.widget.listview.SynchronizedViewPool;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.sec.android.gallery3d.R;
import java.util.function.BooleanSupplier;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CategoryCardHolderV2 extends ListViewHolder implements ICategoryCardViewHolder {
    protected final String TAG = getClass().getSimpleName();
    ImageView mArrow;
    private SynchronizedViewPool mChildViewPool;
    View mDivider;
    private GridLayoutManager mGridLayoutManager;
    TextView mHeader;
    TextView mHeaderCount;
    ViewGroup mHeaderLayout;
    protected CategoryItemAdapterV2 mItemAdapter;
    GalleryListView mListView;
    protected BooleanSupplier mNotifySupplier;
    protected CategoryPropertyHelper mPropertyHelper;

    public CategoryCardHolderV2(View view, int i2) {
        super(view, i2);
    }

    private void applyHeaderMargin() {
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85 && !this.mPropertyHelper.isExpansionMode()) {
            ViewMarginUtils.setVerticalMargin(this.mHeaderLayout, getDimensionPixelOffset(R.dimen.search_item_card_header_margin_85));
        }
    }

    private void bindListViewWithGridLayout(ISearchView iSearchView, String str) {
        int[] columnCount = this.mPropertyHelper.getColumnCount(iSearchView.getContext());
        if (this.mItemAdapter != null) {
            BooleanSupplier booleanSupplier = this.mNotifySupplier;
            if (booleanSupplier != null && booleanSupplier.getAsBoolean()) {
                this.mItemAdapter.notifyDataSetChanged();
            }
        } else if (!isMediaDataAvailable(iSearchView, str)) {
            String str2 = this.TAG;
            Log.sw(str2, "bindListView : mediaData is not available " + str);
            return;
        } else {
            CategoryItemAdapterV2 createAdapter = createAdapter(iSearchView, str);
            this.mItemAdapter = createAdapter;
            this.mListView.setRecycledViewPool(createViewPool(createAdapter));
            this.mListView.setAdapter(this.mItemAdapter);
            this.mGridLayoutManager = new GridLayoutManager(iSearchView.getApplicationContext(), columnCount[0]);
        }
        int cardListHorizontalPadding = this.mPropertyHelper.getCardListHorizontalPadding(iSearchView.getContext());
        GalleryListView galleryListView = this.mListView;
        galleryListView.setPadding(cardListHorizontalPadding, galleryListView.getPaddingTop(), cardListHorizontalPadding, this.mListView.getPaddingBottom());
        this.mItemAdapter.setColumnCount(columnCount);
        this.mGridLayoutManager.setSpanCount(columnCount[0]);
        this.mListView.setLayoutManager(this.mGridLayoutManager);
    }

    private void bindListViewWithLinearLayout(ISearchView iSearchView, String str) {
        if (this.mItemAdapter != null) {
            return;
        }
        if (!isMediaDataAvailable(iSearchView, str)) {
            String str2 = this.TAG;
            Log.sw(str2, "bindListView : mediaData is not available " + str);
            return;
        }
        CategoryItemAdapterV2 createAdapter = createAdapter(iSearchView, str);
        this.mItemAdapter = createAdapter;
        this.mListView.setRecycledViewPool(createViewPool(createAdapter));
        this.mListView.setAdapter(this.mItemAdapter);
        this.mListView.setLayoutManager(new LinearLayoutManager(iSearchView.getContext(), 0, false));
    }

    private void expandHeaderTouchArea() {
        ViewUtils.setTouchAreaComposite(this.mHeaderLayout, 0, 0, this.itemView.getResources().getDimensionPixelOffset(R.dimen.search_card_header_margin_end_85), 0);
    }

    private String getDescriptionString(int i2) {
        if (i2 == 2) {
            return AppResources.getString(getShowMoreCreatureStringResId());
        }
        if (i2 == 3) {
            return AppResources.getString(R.string.show_more_locations);
        }
        if (i2 != 5) {
            return AppResources.getString(R.string.view_more);
        }
        return AppResources.getString(R.string.show_more_documents);
    }

    private int getShowMoreCreatureStringResId() {
        return R.string.show_more_faces;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setArrow$0(ISearchView iSearchView, MediaData mediaData, int i2, View view) {
        iSearchView.onCategoryExpansionClick(getExpansionTargetLocation(mediaData, i2));
    }

    private void setOnCategoryExpansionClickListener(boolean z, View.OnClickListener onClickListener) {
        if (z) {
            this.mHeaderLayout.setFocusable(true);
            this.mHeaderLayout.setOnClickListener(onClickListener);
            this.mHeaderLayout.setBackgroundResource(R.drawable.recoil_ripple_rect);
            return;
        }
        this.mHeaderLayout.setFocusable(false);
        this.mHeaderLayout.setOnClickListener((View.OnClickListener) null);
        this.mHeaderLayout.setStateListAnimator((StateListAnimator) null);
    }

    public void bind(ISearchView iSearchView, MediaData mediaData) {
        bindBasicInfo(iSearchView, mediaData);
        bindListView(iSearchView, mediaData.getLocationKey());
    }

    public void bindBasicInfo(ISearchView iSearchView, MediaData mediaData) {
        createCategoryPropertyHelper(mediaData.getLocationKey());
        int count = mediaData.getCount();
        setTitle(iSearchView.getContext(), count);
        setArrow(iSearchView, count, mediaData);
        applyHeaderMargin();
    }

    public void bindListView(ISearchView iSearchView, String str) {
        initListView(iSearchView, str);
        GalleryListView galleryListView = this.mListView;
        if (galleryListView != null) {
            galleryListView.disableSelectMode(true);
            this.mListView.setNestedScrollingEnabled(false);
        }
    }

    public void bindView(View view) {
        this.mHeaderLayout = (ViewGroup) view.findViewById(R.id.header_layout);
        this.mHeader = (TextView) view.findViewById(R.id.item_header_name);
        this.mHeaderCount = (TextView) view.findViewById(R.id.item_header_count);
        this.mArrow = (ImageView) view.findViewById(R.id.item_arrow);
        this.mListView = (GalleryListView) view.findViewById(R.id.my_recycler_view);
        this.mDivider = view.findViewById(R.id.category_divider);
        GalleryListView galleryListView = this.mListView;
        if (galleryListView != null) {
            galleryListView.setFadingEdge(GalleryRecyclerView.FadingEdgeDirection.NONE);
            this.mListView.setHorizontalFadingEdgeEnabled(!Features.isEnabled(Features.IS_ENABLED_REDUCE_TRANSPARENCY));
        }
        expandHeaderTouchArea();
    }

    public CategoryItemAdapterV2 createAdapter(ISearchView iSearchView, String str) {
        return new CategoryItemAdapterV2(iSearchView, str, this.mListView, this.mPropertyHelper, false);
    }

    public void createCategoryPropertyHelper(String str) {
        if (this.mPropertyHelper == null) {
            this.mPropertyHelper = CategoryPropertyHelper.getInstance(str, false);
        }
    }

    public RecyclerView.RecycledViewPool createViewPool(CategoryItemAdapterV2<?> categoryItemAdapterV2) {
        if (this.mChildViewPool == null) {
            SynchronizedViewPool synchronizedViewPool = new SynchronizedViewPool();
            this.mChildViewPool = synchronizedViewPool;
            synchronizedViewPool.setMaxRecycledViews(categoryItemAdapterV2.getPrimaryViewType(), 15);
        }
        return this.mChildViewPool;
    }

    public int getContentPaddingBottom(boolean z) {
        if (!z || PreferenceFeatures.OneUi8x.IS_ONE_UI_85) {
            return 0;
        }
        return getDimensionPixelOffset(R.dimen.search_card_last_content_padding_bottom) - this.mPropertyHelper.getItemMarginBottom(this.itemView.getContext());
    }

    public int getContentPaddingTop() {
        int i2;
        if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85) {
            i2 = R.dimen.search_card_padding_85;
        } else if (PreferenceFeatures.OneUi6x.VISUAL_SEARCH_61) {
            i2 = R.dimen.search_card_padding_top_61;
        } else {
            i2 = R.dimen.search_card_padding_top;
        }
        int dimensionPixelOffset = getDimensionPixelOffset(i2);
        if (this.mPropertyHelper.hasItemMarginTopOnCard()) {
            return dimensionPixelOffset - this.mPropertyHelper.getItemMarginTop(this.itemView.getContext());
        }
        return dimensionPixelOffset;
    }

    public int getDimensionPixelOffset(int i2) {
        return this.itemView.getContext().getResources().getDimensionPixelOffset(i2);
    }

    public String getExpansionTargetLocation(MediaData mediaData, int i2) {
        String locationKey = mediaData.getLocationKey();
        if (this.mPropertyHelper.getItemViewType() == 2) {
            locationKey = ArgumentsUtil.appendArgs(locationKey, "count", String.valueOf(i2));
        }
        if (LocationKey.isCategoryList(locationKey)) {
            return ArgumentsUtil.appendArgs(locationKey, "searchToolbar", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE);
        }
        return locationKey;
    }

    public void initListView(ISearchView iSearchView, String str) {
        if (this.mListView != null) {
            if (this.mPropertyHelper.useGridLayoutOnCard()) {
                bindListViewWithGridLayout(iSearchView, str);
            } else {
                bindListViewWithLinearLayout(iSearchView, str);
            }
        }
    }

    public boolean isMediaDataAvailable(ISearchView iSearchView, String str) {
        MediaData mediaData = iSearchView.getMediaData(str);
        if (mediaData == null || !mediaData.isDataAvailable()) {
            return false;
        }
        return true;
    }

    public void notifyDataSetChanged() {
        CategoryItemAdapterV2 categoryItemAdapterV2 = this.mItemAdapter;
        if (categoryItemAdapterV2 != null) {
            categoryItemAdapterV2.onDataChanged();
            this.mItemAdapter.notifyDataSetChanged();
        }
    }

    public void onConfigurationChanged() {
        CategoryItemAdapterV2 categoryItemAdapterV2 = this.mItemAdapter;
        if (categoryItemAdapterV2 != null) {
            GalleryListView galleryListView = this.mListView;
            if (galleryListView != null) {
                this.mChildViewPool = null;
                galleryListView.setRecycledViewPool(createViewPool(categoryItemAdapterV2));
            }
            this.mItemAdapter.onConfigurationChanged();
            if (this.mPropertyHelper.useGridLayoutOnCard()) {
                int[] columnCount = this.mPropertyHelper.getColumnCount(this.mItemAdapter.getContext());
                this.mItemAdapter.setColumnCount(columnCount);
                this.mGridLayoutManager.setSpanCount(columnCount[0]);
            }
            notifyDataSetChanged();
        }
    }

    public void recycle() {
        super.recycle();
        CategoryItemAdapterV2 categoryItemAdapterV2 = this.mItemAdapter;
        if (categoryItemAdapterV2 != null) {
            categoryItemAdapterV2.destroy();
        }
        this.mItemAdapter = null;
        GalleryListView galleryListView = this.mListView;
        if (galleryListView != null) {
            galleryListView.setAdapter((RecyclerView.Adapter) null);
        }
        this.mHeaderLayout.setOnClickListener((View.OnClickListener) null);
        this.mHeaderLayout.setBackground((Drawable) null);
        this.mPropertyHelper = null;
        this.mNotifySupplier = null;
    }

    public void setArrow(ISearchView iSearchView, int i2, MediaData mediaData) {
        int i7;
        boolean enableMoreButton = this.mPropertyHelper.enableMoreButton(this.itemView.getContext(), i2);
        ImageView imageView = this.mArrow;
        if (enableMoreButton) {
            i7 = 0;
        } else {
            i7 = 8;
        }
        imageView.setVisibility(i7);
        this.mArrow.setContentDescription(getDescriptionString(this.mPropertyHelper.getItemViewType()));
        setOnCategoryExpansionClickListener(enableMoreButton, new b(this, iSearchView, mediaData, i2));
    }

    public void setNotifySupplier(BooleanSupplier booleanSupplier) {
        this.mNotifySupplier = booleanSupplier;
    }

    public void setTitle(Context context, int i2) {
        String str;
        String categoryTitle = this.mPropertyHelper.getCategoryTitle(context);
        if (!TextUtils.isEmpty(categoryTitle)) {
            this.mHeader.setText(categoryTitle);
        }
        TextView textView = this.mHeaderCount;
        if (!this.mPropertyHelper.useCountOnCard() || i2 <= 0) {
            str = "";
        } else {
            str = StringCompat.toReadableCount(i2);
        }
        ViewUtils.setText(textView, str);
        if (this.mPropertyHelper.hasRoundedHeader()) {
            ViewUtils.setViewShape(this.mHeaderLayout, 1, (float) this.mPropertyHelper.getHeaderRoundCorner(context));
        }
    }

    public void updateContentPadding(boolean z) {
        if (this.mListView != null) {
            updateItemViewMargin(z);
            if (!z || this.mPropertyHelper.getItemViewType() != 6) {
                GalleryListView galleryListView = this.mListView;
                galleryListView.setPadding(galleryListView.getPaddingStart(), getContentPaddingTop(), this.mListView.getPaddingEnd(), getContentPaddingBottom(z));
                return;
            }
            GalleryListView galleryListView2 = this.mListView;
            galleryListView2.setPadding(galleryListView2.getPaddingStart(), 0, this.mListView.getPaddingEnd(), getDimensionPixelOffset(R.dimen.search_card_last_content_padding_bottom));
        }
    }

    public void updateDivider(boolean z) {
        ViewUtils.setVisibleOrGone(this.mDivider, !z);
        GalleryListView galleryListView = this.mListView;
        if (galleryListView != null) {
            galleryListView.setFocusable(z);
        }
        if (z) {
            updateDividerMarginTop();
        }
    }

    public void updateDividerMarginTop() {
        ViewMarginUtils.setTopMargin(this.mDivider, getDimensionPixelOffset(R.dimen.search_card_divider_margin_top) - this.mPropertyHelper.getItemMarginBottom(this.itemView.getContext()));
    }

    public void updateItemViewMargin(boolean z) {
        ViewMarginUtils.setTopMargin(this.itemView, this.mPropertyHelper.getCardListTopMargin(getItemView().getContext()));
        ViewMarginUtils.setBottomMargin(this.itemView, this.mPropertyHelper.getCardListBottomMargin(getItemView().getContext(), z));
    }
}
