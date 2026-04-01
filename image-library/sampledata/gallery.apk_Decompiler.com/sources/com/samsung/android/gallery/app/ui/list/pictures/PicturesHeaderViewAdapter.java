package com.samsung.android.gallery.app.ui.list.pictures;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.dataset.tables.SpanInfo;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PicturesHeaderViewAdapter<V extends IPicturesView> extends PicturesViewAdapter<V> {
    protected View mHeaderView;

    public PicturesHeaderViewAdapter(V v, String str, View view, boolean z) {
        super(v, str, z);
        setHeaderView(view);
    }

    public void attachHeaderViewToHolder(ListViewHolder listViewHolder) {
        if (listViewHolder != null && ViewHolderValue.isHeader(listViewHolder.getItemViewType())) {
            ViewGroup viewGroup = (ViewGroup) listViewHolder.getRootView();
            viewGroup.setTag(this.mMultiClusterAdapter.getScrollText(1));
            View view = this.mHeaderView;
            if (view != null) {
                if (view.getParent() != null) {
                    ViewUtils.removeSelf(this.mHeaderView);
                }
                viewGroup.addView(this.mHeaderView, 0);
            }
            setViewHolderMargin(listViewHolder, getGridSize());
        }
    }

    public TextView getHeaderDescriptionView() {
        View view = this.mHeaderView;
        if (view != null) {
            return (TextView) view.findViewById(getHeaderDescriptionViewId());
        }
        return null;
    }

    public int getHeaderDescriptionViewId() {
        return R.id.headerContent;
    }

    public int getHeaderDescriptionWidthOffset() {
        View view = this.mHeaderView;
        if (view == null) {
            return 0;
        }
        int dimensionPixelOffset = this.mHeaderView.getResources().getDimensionPixelOffset(R.dimen.tip_card_padding_end) + view.getResources().getDimensionPixelOffset(R.dimen.tip_card_padding_start);
        return this.mHeaderView.getResources().getDimensionPixelOffset(R.dimen.tip_card_text_layout_padding_end) + this.mHeaderView.getResources().getDimensionPixelOffset(R.dimen.tip_card_text_layout_padding_start) + dimensionPixelOffset;
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

    public int getHintDataPosition(int i2, int i7) {
        if (isHeader(i2)) {
            return -1;
        }
        return super.getHintDataPosition(i2, i7);
    }

    public Integer[] getHintRowInfo(int i2, int i7) {
        if (i2 != 0) {
            return super.getHintRowInfo(i2, i7);
        }
        return new Integer[]{-1, 1};
    }

    public SpanInfo getHintSpanInfo(int i2, int i7) {
        if (isHeader(i2)) {
            return new SpanInfo(1, i7, -3, false);
        }
        return super.getHintSpanInfo(i2, i7);
    }

    public int getHintViewHeight(int i2, int i7, int i8) {
        if (isHeader(i2)) {
            return getHeaderViewHeight();
        }
        return super.getHintViewHeight(i2, i7, i8);
    }

    public int getItemHeight(int i2) {
        if (isHeader(i2)) {
            return getHeaderViewHeight();
        }
        return super.getItemHeight(i2);
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

    public int getNextRowIndex(int i2, int i7) {
        if (isHeader(i2)) {
            return i2 + 1;
        }
        return super.getNextRowIndex(i2, i7);
    }

    public int getPrevRowIndex(int i2) {
        if (isHeader(i2)) {
            return i2 - 1;
        }
        return super.getPrevRowIndex(i2);
    }

    public int getRowCount(int i2) {
        return super.getRowCount(i2) + 1;
    }

    public int getSpanSize(int i2) {
        if (isHeader(i2)) {
            return ((IPicturesView) this.mView).getLayoutManager().getSpanCount();
        }
        return super.getSpanSize(i2);
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

    public void onViewAttachedToWindow(ListViewHolder listViewHolder) {
        super.onViewAttachedToWindow(listViewHolder);
        attachHeaderViewToHolder(listViewHolder);
    }
}
