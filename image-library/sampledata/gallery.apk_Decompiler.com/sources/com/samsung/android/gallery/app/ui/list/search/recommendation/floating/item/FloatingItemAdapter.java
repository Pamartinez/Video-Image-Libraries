package com.samsung.android.gallery.app.ui.list.search.recommendation.floating.item;

import A4.O;
import android.text.TextUtils;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.search.category.viewholder.CategoryShotModeItemViewHolderV2;
import com.samsung.android.gallery.app.ui.list.search.recommendation.abstraction.RecommendationViewListener;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FloatingItemAdapter extends RecyclerView.Adapter<ListViewHolder> {
    private int mColumnCount;
    private int mCount;
    private ArrayList<MediaItem> mData = new ArrayList<>();
    private final RecommendationViewListener mViewListener;
    private final int mViewType;

    public FloatingItemAdapter(RecommendationViewListener recommendationViewListener, int i2, int i7) {
        this.mViewListener = recommendationViewListener;
        this.mViewType = i2;
        this.mColumnCount = i7;
    }

    private boolean isDataChanged(ArrayList<MediaItem> arrayList) {
        if (arrayList.size() != this.mData.size()) {
            return true;
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (!isSameItem(arrayList.get(i2), this.mData.get(i2))) {
                return true;
            }
        }
        return false;
    }

    private boolean isLastLineItem(int i2) {
        if (((int) Math.ceil((double) (((float) (i2 + 1)) / ((float) this.mColumnCount)))) == ((int) Math.ceil((double) (((float) this.mCount) / ((float) this.mColumnCount))))) {
            return true;
        }
        return false;
    }

    private boolean isSameItem(MediaItem mediaItem, MediaItem mediaItem2) {
        if (!mediaItem.equals(mediaItem2) || !TextUtils.equals(mediaItem.getDisplayName(), mediaItem2.getDisplayName())) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void onItemClicked(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        this.mViewListener.onCategoryItemClicked(mediaItem);
    }

    public int getItemCount() {
        return this.mCount;
    }

    public int getItemViewType(int i2) {
        return this.mViewType;
    }

    public boolean isEmpty() {
        if (this.mCount == 0) {
            return true;
        }
        return false;
    }

    public void setData(ArrayList<MediaItem> arrayList) {
        if (isDataChanged(arrayList)) {
            this.mData = arrayList;
            this.mCount = arrayList.size();
            Log.d("FloatingItemAdapter", "setData size " + this.mCount);
            notifyDataSetChanged();
        }
    }

    public void updateColumnCount(int i2) {
        this.mColumnCount = i2;
    }

    public void onBindViewHolder(ListViewHolder listViewHolder, int i2) {
        listViewHolder.bind(this.mData.get(i2));
        listViewHolder.setOnItemClickListener(new O(23, this));
        if (this.mColumnCount > 0 && (listViewHolder instanceof CategoryShotModeItemViewHolderV2)) {
            ((CategoryShotModeItemViewHolderV2) listViewHolder).updateDividerVisibility(!isLastLineItem(i2));
        }
    }

    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return this.mViewListener.getViewHolderFactory().createListViewHolder(viewGroup, i2);
    }

    public void onViewRecycled(ListViewHolder listViewHolder) {
        listViewHolder.recycle();
    }
}
