package com.samsung.android.gallery.app.ui.list.stories.abstraction;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesBaseLayoutManager extends PinchLayoutManager {
    protected final Context mContext;
    protected StoriesBaseViewAdapter mListAdapter;

    public StoriesBaseLayoutManager(Context context, int i2) {
        super(context, i2);
        this.mContext = context;
        setSpanSizeLookup(createSpanSizeLookup());
        initDimen(context);
    }

    private GridLayoutManager.SpanSizeLookup createSpanSizeLookup() {
        return new GridLayoutManager.SpanSizeLookup() {
            public int getSpanIndex(int i2, int i7) {
                return StoriesBaseLayoutManager.this.getHintStartSpan(i2, i7);
            }

            public int getSpanSize(int i2) {
                StoriesBaseLayoutManager storiesBaseLayoutManager = StoriesBaseLayoutManager.this;
                return storiesBaseLayoutManager.getHintColumnSpan(i2, storiesBaseLayoutManager.getSpanCount());
            }
        };
    }

    public void addView(View view, int i2) {
        updateViewSize(view, getItemViewType(view), getSpanCount());
        super.addView(view, i2);
        if (view.getLayoutParams() != null) {
            updateDecoView(view, ((RecyclerView.LayoutParams) view.getLayoutParams()).getAbsoluteAdapterPosition(), getSpanCount());
        }
    }

    public void bindFakeHolder(ListViewHolder listViewHolder, int i2, int i7) {
        this.mListAdapter.onBindViewHolder(listViewHolder, i2, i7);
        updateViewSize(listViewHolder.itemView, listViewHolder.getItemViewType(), i7);
    }

    public void bindHolder(ListViewHolder listViewHolder, int i2) {
        this.mListAdapter.onBindViewHolder(listViewHolder, i2);
    }

    public ListViewHolder createListViewHolder(ViewGroup viewGroup, int i2) {
        return (ListViewHolder) this.mListAdapter.createViewHolder(viewGroup, i2);
    }

    public int getGridItemPadding(int i2) {
        return getItemPadding(i2);
    }

    public int getHeaderStartPos(int i2, int i7) {
        if (!hasHeader()) {
            return 0;
        }
        return (getGridItemPadding(i7) + getHintPaddingLeft(i7)) - getGridItemPadding(i2);
    }

    public View getHeaderView() {
        StoriesBaseViewAdapter storiesBaseViewAdapter = this.mListAdapter;
        if (storiesBaseViewAdapter != null) {
            return storiesBaseViewAdapter.getHeaderView();
        }
        return null;
    }

    public int getHintColumnSpan(int i2, int i7) {
        if (!hasHeader() || !isHeaderPosition(i2)) {
            return 1;
        }
        return i7;
    }

    public int getHintItemViewType(int i2, int i7) {
        return this.mListAdapter.getItemViewType(getRealGridSize(i2));
    }

    public int getHintStartSpan(int i2, int i7) {
        if (!hasHeader()) {
            return super.getHintStartSpan(i2, i7);
        }
        if (isHeaderPosition(i2)) {
            return 0;
        }
        return super.getHintStartSpan(i2 - 1, i7);
    }

    public int getHintWidthSpace(int i2) {
        return (getWidth() - (getSpacing(i2) * 2)) - getExtraStartPadding(i2);
    }

    public int getItemPadding(int i2) {
        return 14;
    }

    public int getSpacing(int i2) {
        return 14;
    }

    public float getThumbnailRadius(int i2) {
        return 0.0f;
    }

    public View getViewForHolderMargin(ViewGroup viewGroup) {
        if (viewGroup != null) {
            return viewGroup.getChildAt(0);
        }
        return null;
    }

    public int getViewHolderMargin(int i2) {
        return getItemPadding(i2);
    }

    public boolean isHeaderPosition(int i2) {
        if (i2 == 0) {
            return true;
        }
        return false;
    }

    public boolean isSelectionMode() {
        return this.mListAdapter.isSelectionMode();
    }

    public boolean isSingleSelectionMode() {
        return this.mListAdapter.isSingleSelectionMode();
    }

    public boolean isStories() {
        return true;
    }

    public void onAdapterChangedInternal(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2) {
        this.mListAdapter = (StoriesBaseViewAdapter) adapter2;
    }

    public void setViewHolderMargin(ListViewHolder listViewHolder, int i2) {
        setViewHolderMargin((ViewGroup) listViewHolder.itemView, i2);
    }

    public void updateViewSize(View view, int i2, int i7) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (i2 == 0) {
            int hintWidthSpace = getHintWidthSpace(i7) / getRealGridSize(i7);
            layoutParams.width = hintWidthSpace;
            layoutParams.height = getItemHeight(hintWidthSpace, i7);
            view.setLayoutParams(layoutParams);
            setViewHolderMargin((ViewGroup) view, i7);
            return;
        }
        layoutParams.width = getHintWidthSpace(i7);
        layoutParams.height = -2;
        view.setLayoutParams(layoutParams);
    }

    public void setViewHolderMargin(ViewGroup viewGroup, int i2) {
        ViewMarginUtils.setMargin(getViewForHolderMargin(viewGroup), getViewHolderMargin(i2));
    }

    public void initDimen(Context context) {
    }

    public int getItemHeight(int i2, int i7) {
        return i2;
    }

    public void updateDecoView(View view, int i2, int i7) {
    }
}
