package com.samsung.android.gallery.app.ui.list.search.recommendation.floating.history;

import Bb.l;
import N2.j;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.app.ui.list.abstraction.ThumbnailRequestHolder;
import com.samsung.android.gallery.app.ui.list.search.recommendation.abstraction.RecommendationViewListener;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.search.history.HistoryItem;
import com.samsung.android.gallery.module.thumbnail.ThumbnailLoader;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FloatingHistoryListAdapter extends RecyclerView.Adapter<FloatingHistoryViewHolder> {
    private static final int ITEM_LAYOUT_ID = getLayoutId();
    private int mCount;
    private ArrayList<HistoryItem> mData = new ArrayList<>();
    private final AtomicBoolean mDeleteRemainingHistory = new AtomicBoolean(true);
    private final RecommendationViewListener mViewListener;

    public FloatingHistoryListAdapter(RecommendationViewListener recommendationViewListener) {
        this.mViewListener = recommendationViewListener;
    }

    /* access modifiers changed from: private */
    public void deleteHistoryItem(FloatingHistoryViewHolder floatingHistoryViewHolder, HistoryItem historyItem) {
        HistoryItem lastItem;
        if (this.mDeleteRemainingHistory.getAndSet(false) && (lastItem = getLastItem()) != null) {
            this.mViewListener.onDeleteRemainingHistory(lastItem);
        }
        this.mData.remove(historyItem);
        this.mCount = this.mData.size();
        notifyItemRemoved(floatingHistoryViewHolder.getLayoutPosition());
        this.mViewListener.onDeleteHistoryClick(historyItem);
    }

    private HistoryItem getLastItem() {
        int size = this.mData.size();
        if (size > 0) {
            return this.mData.get(size - 1);
        }
        return null;
    }

    private static int getLayoutId() {
        if (PreferenceFeatures.OneUi8x.FLOATING_RECOMMENDATION_V3) {
            return R.layout.recycler_item_search_floating_history_layout_v3;
        }
        if (PreferenceFeatures.OneUi8x.FLOATING_RECOMMENDATION_V2) {
            return R.layout.recycler_item_search_floating_history_layout_v2;
        }
        return R.layout.recycler_item_search_floating_history_layout;
    }

    private boolean isDataChanged(ArrayList<HistoryItem> arrayList) {
        if (arrayList.size() != this.mData.size()) {
            return true;
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (!arrayList.get(i2).equals(this.mData.get(i2))) {
                return true;
            }
        }
        return false;
    }

    private boolean isViewSame(FloatingHistoryViewHolder floatingHistoryViewHolder, int i2) {
        try {
            if (floatingHistoryViewHolder.getViewPosition() == i2) {
                return true;
            }
            return false;
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            j.u(e, new StringBuilder("isViewSame failed. e="), "FloatingHistoryListAdapter");
            return true;
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$requestThumbnail$3(FloatingHistoryViewHolder floatingHistoryViewHolder, ThumbnailRequestHolder thumbnailRequestHolder) {
        return !isViewSame(floatingHistoryViewHolder, thumbnailRequestHolder.getPosition());
    }

    /* access modifiers changed from: private */
    public void onHistoryItemClicked(FloatingHistoryViewHolder floatingHistoryViewHolder, HistoryItem historyItem) {
        this.mViewListener.onRecentlyHistoryClick(historyItem);
    }

    private void requestThumbnail(FloatingHistoryViewHolder floatingHistoryViewHolder) {
        ThumbnailRequestHolder thumbnailRequestHolder = new ThumbnailRequestHolder(floatingHistoryViewHolder);
        MediaItem mediaItem = thumbnailRequestHolder.getMediaItem();
        if (mediaItem == null || thumbnailRequestHolder.getPosition() == -1) {
            Optional.ofNullable(floatingHistoryViewHolder.getImage()).ifPresent(new l(18));
            return;
        }
        Bitmap brokenBitmap = floatingHistoryViewHolder.getBrokenBitmap();
        if (brokenBitmap != null) {
            floatingHistoryViewHolder.bindImage(brokenBitmap);
        } else {
            ThumbnailLoader.getInstance().getOrLoad(mediaItem, floatingHistoryViewHolder.getThumbKind(), thumbnailRequestHolder, new a(floatingHistoryViewHolder), new b(this, floatingHistoryViewHolder, thumbnailRequestHolder));
        }
    }

    public void deleteAllHistoryItem() {
        notifyItemRangeRemoved(0, this.mData.size());
        this.mData.clear();
        this.mCount = 0;
    }

    public int getItemCount() {
        return this.mCount;
    }

    public boolean isEmpty() {
        if (this.mCount == 0) {
            return true;
        }
        return false;
    }

    public void setData(ArrayList<HistoryItem> arrayList) {
        if (isDataChanged(arrayList)) {
            this.mData = arrayList;
            this.mCount = arrayList.size();
            Log.d("FloatingHistoryListAdapter", "setData size " + this.mCount);
            notifyDataSetChanged();
        }
    }

    public void onBindViewHolder(FloatingHistoryViewHolder floatingHistoryViewHolder, int i2) {
        floatingHistoryViewHolder.bind(this.mData.get(i2));
        requestThumbnail(floatingHistoryViewHolder);
    }

    public FloatingHistoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        return new FloatingHistoryViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(ITEM_LAYOUT_ID, viewGroup, false)).setDeleteCallback(new c(this, 0)).setClickListener(new c(this, 1));
    }

    public void onViewRecycled(FloatingHistoryViewHolder floatingHistoryViewHolder) {
        floatingHistoryViewHolder.recycle();
    }
}
