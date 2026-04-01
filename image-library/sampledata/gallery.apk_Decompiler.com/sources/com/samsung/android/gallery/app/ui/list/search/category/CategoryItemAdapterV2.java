package com.samsung.android.gallery.app.ui.list.search.category;

import android.content.Context;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.search.ISearchView;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.module.dataset.MediaDataFactory;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import h.C0199b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CategoryItemAdapterV2<V extends ISearchView> extends BaseListViewAdapter<V> {
    private final boolean mIsFromExpansion;
    protected int mItemHeight = -1;
    private int mItemSpacing;
    protected final CategoryPropertyHelper mPropertyHelper;
    protected final CategoryItemViewHolderFactory mViewHolderFactory;

    public CategoryItemAdapterV2(V v, String str, GalleryListView galleryListView, CategoryPropertyHelper categoryPropertyHelper, boolean z) {
        super(v, str);
        this.mGalleryListView = galleryListView;
        this.mIsFromExpansion = z;
        this.mViewHolderFactory = createViewHolderFactory(v.getContext());
        this.mPropertyHelper = categoryPropertyHelper;
        setItemSpacing(categoryPropertyHelper);
    }

    private void setItemSpacing(CategoryPropertyHelper categoryPropertyHelper) {
        this.mItemSpacing = categoryPropertyHelper.getItemHorizontalSpacing(getContext()) * 2;
    }

    public CategoryItemViewHolderFactory createViewHolderFactory(Context context) {
        return new CategoryItemViewHolderFactory(context);
    }

    public int getDataRowCount(int i2, int i7) {
        int i8;
        int i10 = i7 / i2;
        if (i7 % i2 > 0) {
            i8 = 1;
        } else {
            i8 = 0;
        }
        return i10 + i8;
    }

    public int getFirstDataPosition() {
        return 0;
    }

    public int getGridItemSpacing() {
        return this.mItemSpacing;
    }

    public int getHeight(ListViewHolder listViewHolder) {
        int height = listViewHolder.itemView.getHeight();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) listViewHolder.itemView.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + height;
    }

    public int getItemCount() {
        int maxDisplayCountOnCard;
        int dataCount = getDataCount();
        if (!this.mIsFromExpansion && (maxDisplayCountOnCard = this.mPropertyHelper.getMaxDisplayCountOnCard(getContext())) != -1 && dataCount >= maxDisplayCountOnCard) {
            return maxDisplayCountOnCard;
        }
        return dataCount;
    }

    public int getItemHeight(int i2) {
        return this.mItemHeight;
    }

    public int getItemViewType(int i2) {
        return this.mPropertyHelper.getItemViewType();
    }

    public int getMaxScroll() {
        int dataRowCount = getDataRowCount(getGridSize(), getItemCount());
        return this.mGalleryListView.getPaddingBottom() + (getItemHeight(getFirstDataPosition()) * dataRowCount);
    }

    public int getNextRowIndex(int i2, int i7) {
        return Math.min(getGridSize() + i2, i7 - 1);
    }

    public int getPrevRowIndex(int i2) {
        return Math.max(0, i2 - getGridSize());
    }

    public int getPrimaryViewType() {
        return this.mPropertyHelper.getItemViewType();
    }

    public ThumbKind getThumbnailKind() {
        return ThumbKind.MEDIUM_KIND;
    }

    public void onConfigurationChanged() {
        setItemSpacing(this.mPropertyHelper);
    }

    public void onDensityChange() {
        setItemSpacing(this.mPropertyHelper);
    }

    public void setColumnCount(int[] iArr) {
        this.mGalleryListView.setColumnCount(iArr);
    }

    public void setThumbnailShape(ListViewHolder listViewHolder) {
        if (this.mPropertyHelper.hasThumbnail()) {
            listViewHolder.setThumbnailShape(this.mPropertyHelper.getThumbnailShape(), (float) this.mPropertyHelper.getRoundCorner(getContext()));
        }
    }

    public boolean supportVideoPreview() {
        return false;
    }

    /* renamed from: updateItemHeight */
    public void lambda$onBindViewHolder$0(ListViewHolder listViewHolder) {
        this.mItemHeight = getHeight(listViewHolder);
    }

    public void updateThumbnailBorder(ListViewHolder listViewHolder) {
        listViewHolder.addThumbnailBorder(getContext().getDrawable(this.mPropertyHelper.getThumbnailBorder()));
    }

    public void updateViewHolderMargin(ListViewHolder listViewHolder) {
        if (this.mPropertyHelper.isNeedUpdateItemHorizontalMargin()) {
            ViewMarginUtils.setHorizontalMargin(listViewHolder.itemView, this.mItemSpacing / 2);
        }
    }

    public MediaData getMediaData(V v, String str) {
        MediaData mediaData = super.getMediaData(v, str);
        if (mediaData != null) {
            return mediaData;
        }
        return MediaDataFactory.getInstance(this.mBlackBoard).create(str);
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        super.onBindViewHolder(listViewHolder, i2);
        setThumbnailShape(listViewHolder);
        updateViewHolderMargin(listViewHolder);
        updateThumbnailBorder(listViewHolder);
        listViewHolder.itemView.post(new C0199b(4, this, listViewHolder));
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return this.mViewHolderFactory.createListViewHolder(viewGroup, i2, this.mIsFromExpansion);
    }
}
