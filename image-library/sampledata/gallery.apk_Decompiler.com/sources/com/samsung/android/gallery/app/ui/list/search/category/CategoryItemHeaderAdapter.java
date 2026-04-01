package com.samsung.android.gallery.app.ui.list.search.category;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import com.samsung.android.gallery.app.ui.list.search.ISearchView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CategoryItemHeaderAdapter<V extends ISearchView> extends CategoryItemAdapterV2<V> {
    protected View mHeaderView;

    public CategoryItemHeaderAdapter(V v, String str, View view, GalleryListView galleryListView, CategoryPropertyHelper categoryPropertyHelper, boolean z) {
        super(v, str, galleryListView, categoryPropertyHelper, z);
        setHeaderView(view);
    }

    private void attachHeaderViewToHolder(ListViewHolder listViewHolder) {
        if (listViewHolder != null && isHeader(listViewHolder.getViewPosition())) {
            ViewGroup viewGroup = (ViewGroup) listViewHolder.getRootView();
            View view = this.mHeaderView;
            if (view != null) {
                if (view.getParent() != null) {
                    ViewUtils.removeSelf(this.mHeaderView);
                }
                viewGroup.addView(this.mHeaderView, 0);
            }
        }
    }

    public int getDataRowCount(int i2, int i7) {
        return super.getDataRowCount(i2, i7 - 1);
    }

    public int getFirstDataPosition() {
        return super.getFirstDataPosition() + 1;
    }

    public View getHeaderView() {
        return this.mHeaderView;
    }

    public int getHeaderViewHeight() {
        View view = this.mHeaderView;
        if (view == null || view.getVisibility() != 0) {
            return 0;
        }
        return this.mHeaderView.getHeight();
    }

    public int getItemCount() {
        return super.getItemCount() + 1;
    }

    public int getItemHeight(int i2) {
        if (isHeader(i2)) {
            return getHeaderViewHeight();
        }
        return super.getItemHeight(i2);
    }

    public int getItemViewType(int i2) {
        if (i2 == 0) {
            return -3;
        }
        return super.getItemViewType(i2);
    }

    public int getMaxScroll() {
        int maxScroll = super.getMaxScroll();
        if (isHeader(0)) {
            return getHeaderViewHeight() + maxScroll;
        }
        return maxScroll;
    }

    public MediaItem getMediaItemFromCache(int i2) {
        if (isHeader(i2)) {
            return null;
        }
        return super.getMediaItemFromCache(i2);
    }

    public int getMediaItemPosition(int i2) {
        return super.getMediaItemPosition(i2 - 1);
    }

    public int getNextRowIndex(int i2, int i7) {
        if (isHeader(i2)) {
            return i2 + 1;
        }
        return super.getNextRowIndex(i2, i7);
    }

    public int getSpanSize(int i2) {
        if (isHeader(i2)) {
            return ((GridLayoutManager) ((ISearchView) this.mView).getLayoutManager()).getSpanCount();
        }
        return 1;
    }

    public int getStartSpan(int i2, int i7) {
        if (isHeader(i2)) {
            return 0;
        }
        return (i2 - 1) % i7;
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

    public final boolean supportHeader() {
        return true;
    }

    public MediaItem getMediaItemFromCache(int i2, int i7) {
        if (isHeader(i2)) {
            return null;
        }
        return super.getMediaItemFromCache(i2, i7);
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        if (isHeader(i2)) {
            attachHeaderViewToHolder(listViewHolder);
        } else {
            super.onBindViewHolder(listViewHolder, i2);
        }
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        if (ViewHolderValue.isHeader(i2)) {
            return this.mViewHolderFactory.createHeaderViewHolder(R.layout.recycler_item_empty_container_layout, viewGroup);
        }
        return super.onCreateViewHolder(viewGroup, i2);
    }

    public void onViewAttachedToWindow(ListViewHolder listViewHolder) {
        super.onViewAttachedToWindow(listViewHolder);
        attachHeaderViewToHolder(listViewHolder);
    }
}
