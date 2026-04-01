package com.samsung.android.gallery.app.ui.list.stories.abstraction;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.IStoriesBaseView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class StoriesHeaderViewAdapter<V extends IStoriesBaseView> extends StoriesBaseViewAdapter<V> {
    private View mHeaderContentsView;

    public StoriesHeaderViewAdapter(V v, String str, View view) {
        super(v, str);
        setHeaderView(view);
    }

    private int getDataPosition(int i2) {
        return Math.max(0, i2 - 1);
    }

    public void attachHeaderViewToHolder(ListViewHolder listViewHolder) {
        if (listViewHolder != null && ViewHolderValue.isHeader(listViewHolder.getItemViewType())) {
            ViewGroup viewGroup = (ViewGroup) listViewHolder.getRootView();
            View view = this.mHeaderContentsView;
            if (view != null) {
                ViewUtils.removeSelf(view);
                viewGroup.addView(this.mHeaderContentsView, 0);
            }
        }
    }

    public void calculateItemHeight(ListViewHolder listViewHolder) {
        if (listViewHolder.getViewType() == 0) {
            super.calculateItemHeight(listViewHolder);
        }
    }

    public View getHeaderView() {
        return this.mHeaderContentsView;
    }

    public int getHeaderViewHeight() {
        if (ViewUtils.isVisible(this.mHeaderContentsView)) {
            return this.mHeaderContentsView.getHeight();
        }
        return 0;
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
        int i2;
        int gridSize = getGridSize();
        int dataCount = getDataCount();
        int i7 = dataCount / gridSize;
        if (dataCount % gridSize > 0) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        int i8 = i7 + i2;
        if (dataCount == 0) {
            return getHeaderViewHeight();
        }
        return getHeaderViewHeight() + (getItemHeight(1) * i8);
    }

    public int getMediaItemPosition(int i2) {
        return super.getMediaItemPosition(getDataPosition(i2));
    }

    public int getNextRowIndex(int i2, int i7) {
        if (isHeader(i2)) {
            return i2 + 1;
        }
        return super.getNextRowIndex(i2, i7);
    }

    public int getPrevRowIndex(int i2) {
        if (isHeader(i2)) {
            return 0;
        }
        return super.getPrevRowIndex(i2);
    }

    public int getSpanSize(int i2) {
        if (isHeader(i2)) {
            return ((GridLayoutManager) ((IStoriesBaseView) this.mView).getLayoutManager()).getSpanCount();
        }
        return 1;
    }

    public int getViewPosition(int i2) {
        return Math.min(i2 + 1, getItemCount() - 1);
    }

    public boolean setHeaderView(View view) {
        View view2 = this.mHeaderContentsView;
        ViewUtils.removeSelf(view2);
        this.mHeaderContentsView = view;
        if (view2 != view) {
            return true;
        }
        return false;
    }

    public boolean supportHeader() {
        return true;
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
