package com.samsung.android.gallery.app.ui.list.pictures;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.tables.SpanInfo;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PicturesLayoutManager extends PinchLayoutManager {
    private int mDisplayCutLeft = 0;
    private int mDisplayCutRight = 0;
    protected PicturesViewAdapter mListAdapter;

    public PicturesLayoutManager(Context context, int i2) {
        super(context, i2);
    }

    public void addView(View view, int i2) {
        updateViewSize(view, getItemViewType(view), this.mListAdapter.getGridSize());
        super.addView(view, i2);
    }

    public void bindFakeHolder(ListViewHolder listViewHolder, int i2, int i7) {
        this.mListAdapter.onBindViewHolder(listViewHolder, i2, i7);
    }

    public void bindHolder(ListViewHolder listViewHolder, int i2) {
        this.mListAdapter.onBindViewHolder(listViewHolder, i2);
    }

    public void calculateToPosition(int i2) {
        if (this.mListAdapter != null && getWidth() > 0) {
            int hintHorizontalPadding = getHintHorizontalPadding(i2);
            this.mListAdapter.recalculatePosition(getWidth(), hintHorizontalPadding, hintHorizontalPadding);
        }
    }

    public void calculateToPositionOnCluster(int i2, int i7) {
        PicturesViewAdapter picturesViewAdapter = this.mListAdapter;
        if (picturesViewAdapter != null) {
            picturesViewAdapter.calculateToPositionOnCluster(i2, i7);
        }
    }

    public ListViewHolder createHintListViewHolder(ViewGroup viewGroup, int i2, int i7) {
        return this.mListAdapter.createHintViewHolder(viewGroup, i2, getRealGridSize(i7));
    }

    public ListViewHolder createListViewHolder(ViewGroup viewGroup, int i2) {
        return (ListViewHolder) this.mListAdapter.createViewHolder(viewGroup, i2);
    }

    public void createMonthXsFakeCluster(int i2, int i7) {
        PicturesViewAdapter picturesViewAdapter = this.mListAdapter;
        if (picturesViewAdapter != null) {
            picturesViewAdapter.createMonthXsFakeCluster(i2, i7);
        }
    }

    public int getCurrentSpanCount() {
        PicturesViewAdapter picturesViewAdapter = this.mListAdapter;
        if (picturesViewAdapter == null) {
            return getSpanCount();
        }
        return picturesViewAdapter.getGridSize();
    }

    public int getDefaultHorizontalPadding(int i2) {
        return (this.mDisplayCutLeft + this.mDisplayCutRight) - (getSpacing(i2) * 2);
    }

    public TextView getHeaderDescriptionView() {
        PicturesViewAdapter picturesViewAdapter = this.mListAdapter;
        if (picturesViewAdapter != null) {
            return picturesViewAdapter.getHeaderDescriptionView();
        }
        return null;
    }

    public int getHeaderDescriptionWidthOffset() {
        PicturesViewAdapter picturesViewAdapter = this.mListAdapter;
        if (picturesViewAdapter != null) {
            return picturesViewAdapter.getHeaderDescriptionWidthOffset();
        }
        return 0;
    }

    public View getHeaderView() {
        PicturesViewAdapter picturesViewAdapter = this.mListAdapter;
        if (picturesViewAdapter != null) {
            return picturesViewAdapter.getHeaderView();
        }
        return null;
    }

    public int getHeaderViewHeight() {
        PicturesViewAdapter picturesViewAdapter = this.mListAdapter;
        if (picturesViewAdapter != null) {
            return picturesViewAdapter.getHeaderViewHeight();
        }
        return 0;
    }

    public int getHintColumnSpan(int i2, int i7) {
        return this.mListAdapter.getHintColumnSpan(i2, i7);
    }

    public int getHintDataCountInView(int i2, int i7) {
        return this.mListAdapter.getHintDataCountInView(i2, i7);
    }

    public int getHintDataPosition(int i2, int i7) {
        return this.mListAdapter.getHintDataPosition(i2, i7);
    }

    public int getHintExtraOffset(int i2, int i7, int i8) {
        return this.mListAdapter.getHintExtraOffset(i2, i7, i8);
    }

    public int getHintHorizontalPadding(int i2) {
        return getExtraStartPadding(i2) + getDefaultHorizontalPadding(i2);
    }

    public int getHintItemCount(int i2) {
        PicturesViewAdapter picturesViewAdapter = this.mListAdapter;
        if (picturesViewAdapter != null) {
            return picturesViewAdapter.getHintItemCount(i2);
        }
        return 0;
    }

    public int getHintItemViewType(int i2, int i7) {
        PicturesViewAdapter picturesViewAdapter = this.mListAdapter;
        if (picturesViewAdapter != null) {
            return picturesViewAdapter.getHintItemViewType(i2, i7);
        }
        return 0;
    }

    public int getHintNextDividerIndex(int i2, int i7, int i8) {
        return this.mListAdapter.getHintNextDividerIndex(i2, i7);
    }

    public int getHintNextRowIndex(int i2, int i7, int i8) {
        return this.mListAdapter.getHintNextRowIndex(i2, i7, i8);
    }

    public int getHintPaddingLeft(int i2) {
        int i7;
        if (isLayoutRTL()) {
            i7 = this.mDisplayCutRight;
        } else {
            i7 = this.mDisplayCutLeft + getExtraStartPadding(i2);
        }
        return i7 - getSpacing(i2);
    }

    public int getHintPaddingStart(int i2) {
        int i7;
        if (isLayoutRTL()) {
            i7 = this.mDisplayCutLeft;
        } else {
            i7 = this.mDisplayCutRight;
        }
        return getExtraStartPadding(i2) + (i7 - getSpacing(i2));
    }

    public int getHintPrevDividerIndex(int i2, int i7) {
        return this.mListAdapter.getHintPrevDividerIndex(i2, i7);
    }

    public int getHintPrevRowIndex(int i2, int i7) {
        return this.mListAdapter.getHintPrevRowIndex(i2, i7);
    }

    public Integer[] getHintRowInfo(int i2, int i7) {
        return this.mListAdapter.getHintRowInfo(i2, i7);
    }

    public int getHintRowSpan(int i2, int i7) {
        return this.mListAdapter.getHintRowSpan(i2, i7);
    }

    public int getHintSpanCount(int i2) {
        PicturesViewAdapter picturesViewAdapter = this.mListAdapter;
        if (picturesViewAdapter != null) {
            return picturesViewAdapter.getHintSpanCount(i2);
        }
        return 0;
    }

    public SpanInfo getHintSpanInfo(int i2, int i7) {
        return this.mListAdapter.getHintSpanInfo(i2, i7);
    }

    public int getHintStartSpan(int i2, int i7) {
        return this.mListAdapter.getHintStartSpan(i2, i7);
    }

    public int getHintViewCount(int i2) {
        PicturesViewAdapter picturesViewAdapter = this.mListAdapter;
        if (picturesViewAdapter != null) {
            return picturesViewAdapter.getHintViewCount(i2);
        }
        return 0;
    }

    public int getHintViewHeight(int i2, int i7) {
        PicturesViewAdapter picturesViewAdapter = this.mListAdapter;
        if (picturesViewAdapter != null) {
            return picturesViewAdapter.getHintViewHeight(i2, i7, getHintWidthSpace(i7));
        }
        return 0;
    }

    public int getHintViewPosition(int i2, int i7) {
        return this.mListAdapter.getHintViewPosition(i2, i7);
    }

    public int getHintWidthSpace(int i2) {
        return getWidth() - getHintHorizontalPadding(i2);
    }

    public int getHintYearDataPosition(int i2, float f, float f5, int i7) {
        return this.mListAdapter.getHintYearDataPosition(i2, f, f5, i7);
    }

    public int getItemHeight(int i2, int i7) {
        PicturesViewAdapter picturesViewAdapter = this.mListAdapter;
        if (picturesViewAdapter != null) {
            return picturesViewAdapter.getItemHeight(i2);
        }
        return 0;
    }

    public MediaItem getMediaItem(int i2, int i7) {
        MediaItem mediaItemFromCache = this.mListAdapter.getMediaItemFromCache(i2, i7);
        if (mediaItemFromCache != null) {
            return mediaItemFromCache;
        }
        PicturesViewAdapter picturesViewAdapter = this.mListAdapter;
        return picturesViewAdapter.loadMediaItemSync(picturesViewAdapter.getMediaItemPosition(i2, i7));
    }

    public int getPaddingLeft() {
        return getHintPaddingLeft(getCurrentSpanCount());
    }

    public int getPaddingRight() {
        int i2;
        if (isLayoutRTL()) {
            i2 = getExtraStartPadding(getCurrentSpanCount()) + this.mDisplayCutLeft;
        } else {
            i2 = this.mDisplayCutRight;
        }
        return i2 - getSpacing(getCurrentSpanCount());
    }

    public int getPaddingStart() {
        int i2;
        if (isLayoutRTL()) {
            i2 = this.mDisplayCutLeft;
        } else {
            i2 = this.mDisplayCutRight;
        }
        return getExtraStartPadding(getSpanCount()) + (i2 - getSpacing(getCurrentSpanCount()));
    }

    public int getTitleHeight(int i2) {
        PicturesViewAdapter picturesViewAdapter = this.mListAdapter;
        if (picturesViewAdapter != null) {
            return picturesViewAdapter.getTitleHeight(i2);
        }
        return 0;
    }

    public boolean hasCluster(int i2) {
        PicturesViewAdapter picturesViewAdapter = this.mListAdapter;
        if (picturesViewAdapter == null || !picturesViewAdapter.hasCluster(i2)) {
            return false;
        }
        return true;
    }

    public boolean hasHeader() {
        return this.mListAdapter.supportHeader();
    }

    public boolean isHintHideDecoItem(int i2) {
        PicturesViewAdapter picturesViewAdapter = this.mListAdapter;
        if (picturesViewAdapter == null || !picturesViewAdapter.isHideDecoItem(getRealGridSize(i2))) {
            return false;
        }
        return true;
    }

    public boolean isHintSpannable(int i2) {
        return this.mListAdapter.isSpannable(getRealGridSize(i2));
    }

    public boolean isSelectionMode() {
        return this.mListAdapter.isSelectionMode();
    }

    public boolean isSingleSelectionMode() {
        return this.mListAdapter.isSingleSelectionMode();
    }

    public void onAdapterChangedInternal(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2) {
        PicturesViewAdapter picturesViewAdapter = (PicturesViewAdapter) adapter2;
        this.mListAdapter = picturesViewAdapter;
        if (adapter == null && adapter2 != null) {
            int gridSize = picturesViewAdapter.getGridSize();
            if (this.mListAdapter.isRealRatio(gridSize)) {
                setSpanCount(gridSize);
            }
        }
    }

    public void preparePinchAnimation(int i2) {
        PicturesViewAdapter picturesViewAdapter = this.mListAdapter;
        if (picturesViewAdapter != null) {
            picturesViewAdapter.preparePinchAnimation(i2);
        }
    }

    public void recalculatePosition() {
        PicturesViewAdapter picturesViewAdapter = this.mListAdapter;
        if (picturesViewAdapter != null) {
            picturesViewAdapter.recalculatePosition();
        }
    }

    public void resetFakeCluster() {
        PicturesViewAdapter picturesViewAdapter = this.mListAdapter;
        if (picturesViewAdapter != null) {
            picturesViewAdapter.resetFakeCluster();
        }
    }

    public void setDisplayCutLeft(int i2) {
        this.mDisplayCutLeft = i2;
    }

    public void setDisplayCutRight(int i2) {
        this.mDisplayCutRight = i2;
    }

    public void setSpanCount(int i2) {
        int i7;
        PicturesViewAdapter picturesViewAdapter = this.mListAdapter;
        if (picturesViewAdapter != null) {
            i7 = picturesViewAdapter.getHintSpanCount(i2);
        } else {
            i7 = i2;
        }
        if (i7 <= 0) {
            Log.e("GalleryGridLayoutManager", "setSpanCount " + i7 + ArcCommonLog.TAG_COMMA + i2);
        } else {
            i2 = i7;
        }
        super.setSpanCount(i2);
    }

    public void setViewHolderMargin(ListViewHolder listViewHolder, int i2) {
        PicturesViewAdapter picturesViewAdapter = this.mListAdapter;
        if (picturesViewAdapter != null) {
            picturesViewAdapter.setViewHolderMargin(listViewHolder, i2);
        }
    }

    public boolean updateTimelineWidth() {
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0059, code lost:
        r0 = (androidx.recyclerview.widget.RecyclerView.LayoutParams) r4.getLayoutParams();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void updateViewSize(android.view.View r4, int r5, int r6) {
        /*
            r3 = this;
            boolean r0 = com.samsung.android.gallery.widget.listviewholder.ViewHolderValue.isData(r5)
            r1 = -1
            if (r0 == 0) goto L_0x0056
            com.samsung.android.gallery.app.ui.list.pictures.PicturesViewAdapter r0 = r3.mListAdapter
            if (r0 == 0) goto L_0x0039
            int r2 = r3.getRealGridSize(r6)
            boolean r0 = r0.supportCustomViewSize(r2)
            if (r0 == 0) goto L_0x0039
            android.view.ViewGroup$LayoutParams r0 = r4.getLayoutParams()
            androidx.recyclerview.widget.RecyclerView$LayoutParams r0 = (androidx.recyclerview.widget.RecyclerView.LayoutParams) r0
            int r2 = r0.getViewAdapterPosition()
            if (r2 == r1) goto L_0x0031
            int r1 = r3.getHintColumnSpan(r2, r6)
            r0.width = r1
            int r6 = r3.getHintViewHeight(r2, r6)
            r0.height = r6
            r4.setLayoutParams(r0)
            goto L_0x006e
        L_0x0031:
            java.lang.String r6 = "GalleryGridLayoutManager"
            java.lang.String r0 = "update viewSize fail because position is -1"
            com.samsung.android.gallery.support.utils.Log.w(r6, r0)
            goto L_0x006e
        L_0x0039:
            android.view.ViewGroup$LayoutParams r0 = r4.getLayoutParams()
            int r1 = r3.getHintWidthSpace(r6)
            int r6 = r3.getRealGridSize(r6)
            int r1 = r1 / r6
            int r6 = r0.width
            if (r1 != r6) goto L_0x004e
            int r6 = r0.height
            if (r1 == r6) goto L_0x006e
        L_0x004e:
            r0.height = r1
            r0.width = r1
            r4.setLayoutParams(r0)
            goto L_0x006e
        L_0x0056:
            r0 = -2
            if (r5 != r0) goto L_0x006e
            android.view.ViewGroup$LayoutParams r0 = r4.getLayoutParams()
            androidx.recyclerview.widget.RecyclerView$LayoutParams r0 = (androidx.recyclerview.widget.RecyclerView.LayoutParams) r0
            int r2 = r0.getViewAdapterPosition()
            if (r2 == r1) goto L_0x006e
            int r6 = r3.getHintViewHeight(r2, r6)
            r0.height = r6
            r4.setLayoutParams(r0)
        L_0x006e:
            boolean r6 = r3.updateTimelineWidth()
            if (r6 == 0) goto L_0x0097
            boolean r6 = com.samsung.android.gallery.widget.listviewholder.ViewHolderValue.isDivider(r5)
            if (r6 != 0) goto L_0x0080
            boolean r5 = com.samsung.android.gallery.widget.listviewholder.ViewHolderValue.isFooter(r5)
            if (r5 == 0) goto L_0x0097
        L_0x0080:
            android.view.ViewGroup$LayoutParams r5 = r4.getLayoutParams()
            int r6 = r3.getCurrentSpanCount()
            int r3 = r3.getHintWidthSpace(r6)
            if (r3 <= 0) goto L_0x0097
            int r6 = r5.width
            if (r3 == r6) goto L_0x0097
            r5.width = r3
            r4.setLayoutParams(r5)
        L_0x0097:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.pictures.PicturesLayoutManager.updateViewSize(android.view.View, int, int):void");
    }

    public int getHintDataPosition(int i2, float f, float f5, int i7) {
        return this.mListAdapter.getHintDataPosition(i2, f, f5, i7);
    }
}
