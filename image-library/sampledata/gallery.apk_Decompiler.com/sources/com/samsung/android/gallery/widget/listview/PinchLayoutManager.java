package com.samsung.android.gallery.widget.listview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.tables.SpanInfo;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PinchLayoutManager extends GalleryGridLayoutManager {
    public PinchLayoutManager(Context context, int i2) {
        super(context, i2);
    }

    public abstract void bindHolder(ListViewHolder listViewHolder, int i2);

    public ListViewHolder createHintListViewHolder(ViewGroup viewGroup, int i2, int i7) {
        return createListViewHolder(viewGroup, i2);
    }

    public abstract ListViewHolder createListViewHolder(ViewGroup viewGroup, int i2);

    public int getCurrentSpanCount() {
        return getSpanCount();
    }

    public int getHeaderBottomMargin(int i2) {
        return 0;
    }

    public TextView getHeaderDescriptionView() {
        return null;
    }

    public int getHeaderDescriptionWidthOffset() {
        return 0;
    }

    public int getHeaderStartPos(int i2, int i7) {
        if (getHeaderView() != null) {
            return getHeaderView().getLeft();
        }
        return 0;
    }

    public View getHeaderView() {
        return null;
    }

    public int getHeaderViewHeight() {
        return 0;
    }

    public int getHeaderWidth(int i2) {
        return getWidth() - getExtraStartPadding(i2);
    }

    public int getHintColumnSpan(int i2, int i7) {
        return 1;
    }

    public int getHintDataCountInView(int i2, int i7) {
        return 1;
    }

    public int getHintDataPosition(int i2, float f, float f5, int i7) {
        return i2;
    }

    public int getHintExtraOffset(int i2, int i7, int i8) {
        return 0;
    }

    public int getHintItemCount(int i2) {
        return getItemCount();
    }

    public abstract int getHintItemViewType(int i2, int i7);

    public int getHintNextDividerIndex(int i2, int i7, int i8) {
        return getHintNextRowIndex(i2, i7, i8);
    }

    public int getHintNextRowIndex(int i2, int i7, int i8) {
        return Math.min(i8 - 1, getRealGridSize(i7) + i2);
    }

    public int getHintPrevDividerIndex(int i2, int i7) {
        return getHintPrevRowIndex(i2, i7);
    }

    public int getHintPrevRowIndex(int i2, int i7) {
        return Math.max(0, i2 - getRealGridSize(i7));
    }

    public Integer[] getHintRowInfo(int i2, int i7) {
        return null;
    }

    public int getHintRowSpan(int i2, int i7) {
        return 1;
    }

    public int getHintSpanCount(int i2) {
        return getRealGridSize(i2);
    }

    public SpanInfo getHintSpanInfo(int i2, int i7) {
        return new SpanInfo(i2 / i7, i2 % i7, getHintItemViewType(i2, i7));
    }

    public int getHintStartSpan(int i2, int i7) {
        return i2 % getRealGridSize(i7);
    }

    public int getHintViewCount(int i2) {
        return getHintItemCount(i2);
    }

    public int getHintViewHeight(int i2, int i7) {
        return getHintWidthSpace(i7) / getRealGridSize(i7);
    }

    public abstract int getHintWidthSpace(int i2);

    public int getHintYearDataPosition(int i2, float f, float f5, int i7) {
        return getHintDataPosition(i2, f, f5, i7);
    }

    public MediaItem getMediaItem(int i2, int i7) {
        return null;
    }

    public int getTitleHeight(int i2) {
        return 0;
    }

    public boolean hasCluster(int i2) {
        return true;
    }

    public boolean hasFooter() {
        return false;
    }

    public boolean hasHeader() {
        return false;
    }

    public boolean isAlbum() {
        return false;
    }

    public boolean isAppbarCollapsed() {
        return false;
    }

    public boolean isAppbarVisible() {
        return false;
    }

    public boolean isDefaultDepth(int i2) {
        return true;
    }

    public boolean isHintHideDecoItem(int i2) {
        return false;
    }

    public boolean isHintSpannable(int i2) {
        return false;
    }

    public boolean isLayoutRtl() {
        return isLayoutRTL();
    }

    public abstract boolean isSelectionMode();

    public abstract boolean isSingleSelectionMode();

    public boolean isStories() {
        return false;
    }

    public boolean isYear(int i2) {
        return false;
    }

    public boolean updateExtraStartPadding(int i2, int i7, float f, boolean z, boolean z3) {
        boolean updateExtraStartPadding = super.updateExtraStartPadding(i2, i7, f, z, z3);
        if (z3) {
            calculateToPosition(getCurrentSpanCount());
        }
        return updateExtraStartPadding;
    }

    public abstract void updateViewSize(View view, int i2, int i7);

    public int getHintDataPosition(int i2, int i7) {
        return i2;
    }

    public void resetFakeCluster() {
    }

    public void calculateToPosition(int i2) {
    }

    public void preparePinchAnimation(int i2) {
    }

    public void calculateToPositionOnCluster(int i2, int i7) {
    }

    public void createMonthXsFakeCluster(int i2, int i7) {
    }

    public int getHintViewPosition(int i2, int i7) {
        return i2;
    }

    public void setViewHolderMargin(ListViewHolder listViewHolder, int i2) {
    }

    public void updateViewBorders(ListViewHolder listViewHolder, int i2) {
    }

    public void bindFakeHolder(ListViewHolder listViewHolder, int i2, int i7) {
    }

    public void updateAlbumBlurInfo(ListViewHolder listViewHolder, int i2, int i7) {
    }

    public void updateAlbumSyncMargin(ListViewHolder listViewHolder, int i2, boolean z) {
    }

    public void updateAlbumTypeMargin(ListViewHolder listViewHolder, int i2, boolean z) {
    }

    public void updateEmptyAlbumViewSize(ListViewHolder listViewHolder, int i2, boolean z) {
    }

    public void updateFolderChildViewSize(ListViewHolder[] listViewHolderArr, int i2, int i7) {
    }

    public void updateFolderViewSize(ListViewHolder listViewHolder, int i2, boolean z) {
    }

    public void updateTitleContainerMargin(ListViewHolder listViewHolder, int i2, boolean z) {
    }

    public void updateVirtualAlbumViewSize(ListViewHolder listViewHolder, int i2, boolean z) {
    }
}
