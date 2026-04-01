package com.samsung.android.gallery.app.ui.list.search;

import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.app.ui.map.search.SearchMapFragmentContainerView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.MediaData;
import com.samsung.android.gallery.settings.ui.delegate.NavigateAppCmd;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listview.GalleryListAdapter;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;
import java.util.HashSet;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CategoryCardListAdapterV2 extends GalleryListAdapter<ListViewHolder> {
    private static final boolean SUPPORT_STABLE_ID = PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71;
    protected View mHeaderView;
    private int mItemCount = 0;
    private SearchMapFragmentContainerView mMapView;
    private final MediaData mMediaData;
    private Runnable mPendingRunnable;
    private final HashSet<Integer> mUpdatedCategoryItemViewType = new HashSet<>();
    private final ISearchView mView;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class FooterViewHolder extends ListViewHolder {
        private TextView mButton;

        public FooterViewHolder(View view, int i2) {
            super(view, i2);
        }

        public void bindView(View view) {
            TextView textView = (TextView) view.findViewById(R.id.edit_collection_button);
            this.mButton = textView;
            textView.setContentDescription(this.mButton.getText() + ArcCommonLog.TAG_COMMA + this.mButton.getContext().getString(R.string.speak_button));
        }

        public void setOnButtonClickListener(View.OnClickListener onClickListener) {
            this.mButton.setOnClickListener(onClickListener);
        }
    }

    public CategoryCardListAdapterV2(ISearchView iSearchView, View view) {
        super(iSearchView.getBlackboard());
        this.mView = iSearchView;
        this.mMediaData = iSearchView.getMediaData(iSearchView.getLocationKey());
        calcItemCount();
        setHasStableIds(true);
        if (supportHeader()) {
            setHeaderView(view);
        }
    }

    private void attachHeaderViewToHolder(ListViewHolder listViewHolder) {
        if (supportHeader() && listViewHolder != null && isHeader(listViewHolder.getViewPosition()) && this.mHeaderView != null) {
            ViewUtils.removeSelf(this.mHeaderView);
            ((ViewGroup) listViewHolder.getRootView()).addView(this.mHeaderView, 0);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: bindMapView */
    public void lambda$bindMapViewIfLocationCard$1(ICategoryCardViewHolder iCategoryCardViewHolder, MediaData mediaData) {
        if (!this.mView.isDestroyed()) {
            if (this.mMapView == null) {
                this.mMapView = new SearchMapFragmentContainerView(this.mView.getEventContext());
            }
            iCategoryCardViewHolder.bindMapView(this.mView, this.mMapView, mediaData);
        }
    }

    private void calcItemCount() {
        int i2;
        if (supportHeader()) {
            i2 = this.mMediaData.getCount() + 1;
        } else {
            i2 = this.mMediaData.getCount();
        }
        this.mItemCount = i2;
        if (hasFooter()) {
            this.mItemCount++;
        }
    }

    private ListViewHolder createFooterViewHolder(int i2, ViewGroup viewGroup) {
        return new FooterViewHolder(C0086a.d(viewGroup, i2, viewGroup, false), -4);
    }

    private ListViewHolder createHeaderViewHolder(int i2, ViewGroup viewGroup) {
        return new HeaderViewHolder(C0086a.d(viewGroup, i2, viewGroup, false), -3);
    }

    private int getDataPosition(int i2) {
        if (supportHeader()) {
            return i2 - 1;
        }
        return i2;
    }

    /* access modifiers changed from: private */
    /* renamed from: needNotify */
    public boolean lambda$bindViewHolderInternal$0(ListViewHolder listViewHolder) {
        if (!SUPPORT_STABLE_ID || !(listViewHolder instanceof CategoryCardHolderV2) || this.mUpdatedCategoryItemViewType.isEmpty() || this.mUpdatedCategoryItemViewType.contains(Integer.valueOf(listViewHolder.getItemViewType()))) {
            return false;
        }
        this.mUpdatedCategoryItemViewType.add(Integer.valueOf(listViewHolder.getItemViewType()));
        return true;
    }

    /* access modifiers changed from: private */
    public void onFooterClick(View view) {
        new NavigateAppCmd(this.mView.getScreenId()).startSearchCustom(getContext());
    }

    private void updateCardLayout(ICategoryCardViewHolder iCategoryCardViewHolder, int i2, int i7) {
        int dataPosition = getDataPosition(i2);
        boolean z = true;
        if (dataPosition != i7 - 1) {
            z = false;
        }
        iCategoryCardViewHolder.updateDivider(z);
        iCategoryCardViewHolder.updateContentPadding(z);
    }

    public void bindMapViewIfLocationCard(ListViewHolder listViewHolder, MediaData mediaData) {
        if (CategoryCardViewType.isLocationType(listViewHolder.getViewType())) {
            ICategoryCardViewHolder iCategoryCardViewHolder = (ICategoryCardViewHolder) listViewHolder;
            if (this.mView.isEnterTransitionRunning()) {
                this.mPendingRunnable = new e(this, iCategoryCardViewHolder, mediaData, 0);
            } else {
                lambda$bindMapViewIfLocationCard$1(iCategoryCardViewHolder, mediaData);
            }
        }
    }

    public void bindViewHolderInternal(ListViewHolder listViewHolder, int i2) {
        if (supportHeader() && isHeader(i2)) {
            attachHeaderViewToHolder(listViewHolder);
        } else if (!hasFooter() || !isFooter(i2)) {
            MediaData childMediaData = this.mMediaData.getChildMediaData(getDataPosition(i2));
            if (childMediaData != null) {
                int count = this.mMediaData.getCount();
                ICategoryCardViewHolder iCategoryCardViewHolder = (ICategoryCardViewHolder) listViewHolder;
                iCategoryCardViewHolder.setNotifySupplier(new d(this, listViewHolder));
                iCategoryCardViewHolder.bind(this.mView, childMediaData);
                updateCardLayout(iCategoryCardViewHolder, i2, count);
                if (PreferenceFeatures.OneUi8x.VISUAL_SEARCH_85 || !PreferenceFeatures.OneUi7x.VISUAL_SEARCH_71) {
                    bindMapViewIfLocationCard(listViewHolder, childMediaData);
                }
            }
        } else {
            ((FooterViewHolder) listViewHolder).setOnButtonClickListener(new c(0, this));
        }
    }

    public boolean canScrollVertically() {
        SearchMapFragmentContainerView searchMapFragmentContainerView = this.mMapView;
        if (searchMapFragmentContainerView == null || !searchMapFragmentContainerView.isTouchOngoing()) {
            return true;
        }
        return false;
    }

    public boolean checkIfEmpty() {
        if (getDataCount() == 0) {
            return true;
        }
        return false;
    }

    public void destroy() {
        super.destroy();
        SearchMapFragmentContainerView searchMapFragmentContainerView = this.mMapView;
        if (searchMapFragmentContainerView != null) {
            searchMapFragmentContainerView.destroy();
            this.mMapView = null;
        }
    }

    public int getDataCount() {
        return this.mMediaData.getCount();
    }

    public View getHeaderView() {
        return this.mHeaderView;
    }

    public int getItemCount() {
        return this.mItemCount;
    }

    public long getItemId(int i2) {
        int hashCode;
        if (supportHeader() && i2 == 0) {
            return 0;
        }
        if (!hasFooter() || i2 != getItemCount() - 1) {
            MediaData childMediaData = this.mMediaData.getChildMediaData(getDataPosition(i2));
            if (childMediaData == null) {
                return -1;
            }
            if (SUPPORT_STABLE_ID) {
                hashCode = childMediaData.getLocationKey().hashCode();
            } else {
                hashCode = childMediaData.hashCode();
            }
        } else {
            hashCode = getItemCount() - 1;
        }
        return (long) hashCode;
    }

    public int getItemViewType(int i2) {
        if (supportHeader() && i2 == 0) {
            return -3;
        }
        if (hasFooter() && i2 == getItemCount() - 1) {
            return -4;
        }
        MediaData childMediaData = this.mMediaData.getChildMediaData(getDataPosition(i2));
        if (childMediaData != null) {
            return CategoryCardViewType.getTypeFromLocationKey(childMediaData.getLocationKey());
        }
        Log.sw("CategoryCardListAdapterV2", "getItemViewType : categoryMediaData is null");
        return -1;
    }

    public boolean hasFooter() {
        return this.mView.supportFooter();
    }

    public void onDataChanged() {
        int i2;
        boolean z;
        calcItemCount();
        if (SUPPORT_STABLE_ID) {
            this.mUpdatedCategoryItemViewType.clear();
            GalleryListView listView = this.mView.getListView();
            if (listView != null) {
                int childCount = listView.getChildCount();
                int dataCount = getDataCount();
                if (supportHeader()) {
                    i2 = childCount - 1;
                } else {
                    i2 = childCount;
                }
                if (dataCount != i2) {
                    z = true;
                } else {
                    z = false;
                }
                if (hasFooter()) {
                    childCount--;
                }
                for (int supportHeader = supportHeader(); supportHeader < childCount; supportHeader++) {
                    CategoryCardHolderV2 categoryCardHolderV2 = (CategoryCardHolderV2) listView.getChildViewHolder(listView.getChildAt(supportHeader));
                    if (categoryCardHolderV2 != null) {
                        categoryCardHolderV2.notifyDataSetChanged();
                        if (z) {
                            this.mUpdatedCategoryItemViewType.add(Integer.valueOf(categoryCardHolderV2.getItemViewType()));
                        }
                    }
                }
            }
        }
    }

    public void onDataRangeChangedOnUi(int i2, int i7) {
        int i8;
        MediaData childMediaData = this.mMediaData.getChildMediaData(i2);
        GalleryListView listView = this.mView.getListView();
        if (childMediaData != null && listView != null) {
            int supportHeader = supportHeader();
            if (hasFooter()) {
                i8 = listView.getChildCount() - 1;
            } else {
                i8 = listView.getChildCount();
            }
            while (supportHeader < i8) {
                CategoryCardHolderV2 categoryCardHolderV2 = (CategoryCardHolderV2) listView.getChildViewHolder(listView.getChildAt(supportHeader));
                if (categoryCardHolderV2 == null || categoryCardHolderV2.getItemViewType() != CategoryCardViewType.getTypeFromLocationKey(childMediaData.getLocationKey())) {
                    supportHeader++;
                } else {
                    categoryCardHolderV2.notifyDataSetChanged();
                    return;
                }
            }
        }
    }

    public void onDataRangeInserted(int i2, int i7) {
        onDataChanged();
    }

    public void onEmptySpaceSecondaryClick(PointF pointF) {
        this.mView.onEmptySpaceSecondaryClick(pointF);
    }

    public void onEnterTransitionEnd() {
        Runnable runnable = this.mPendingRunnable;
        if (runnable != null) {
            runnable.run();
            this.mPendingRunnable = null;
        }
    }

    public boolean onListItemLongClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        return false;
    }

    public void removePendingRunnable() {
        this.mPendingRunnable = null;
    }

    public boolean setHeaderView(View view) {
        View view2 = this.mHeaderView;
        if (!(view2 == null || view2.getParent() == null)) {
            ViewUtils.removeSelf(this.mHeaderView);
        }
        this.mHeaderView = view;
        if (view2 != view) {
            return true;
        }
        return false;
    }

    public boolean supportHeader() {
        return PreferenceFeatures.OneUi8x.COLLECTION_TAB;
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        if (supportHeader() && ViewHolderValue.isHeader(i2)) {
            return createHeaderViewHolder(R.layout.recycler_item_empty_container_layout, viewGroup);
        }
        if (!hasFooter() || !ViewHolderValue.isFooter(i2)) {
            return CategoryCardHolderFactory.create(viewGroup, i2);
        }
        return createFooterViewHolder(R.layout.edit_collection_button_layout, viewGroup);
    }

    public void onViewAttachedToWindow(ListViewHolder listViewHolder) {
        super.onViewAttachedToWindow(listViewHolder);
        attachHeaderViewToHolder(listViewHolder);
    }

    public void onViewRecycled(ListViewHolder listViewHolder) {
        listViewHolder.recycle();
        super.onViewRecycled(listViewHolder);
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        bindViewHolderInternal(listViewHolder, i2);
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2, List<Object> list) {
        if (!list.contains("onConfigurationChanged")) {
            super.onBindViewHolder(listViewHolder, i2, list);
        } else if (!(listViewHolder instanceof HeaderViewHolder)) {
            ((ICategoryCardViewHolder) listViewHolder).onConfigurationChanged();
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class HeaderViewHolder extends ListViewHolder {
        public HeaderViewHolder(View view, int i2) {
            super(view, i2);
        }

        public void recycle() {
            super.recycle();
            ViewUtils.removeAllViews((ViewGroup) this.itemView);
        }

        public void bindView(View view) {
        }
    }

    public void onListItemClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
    }

    public void onListItemSecondaryClickInternal(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7, PointF pointF, MotionEvent motionEvent) {
    }
}
