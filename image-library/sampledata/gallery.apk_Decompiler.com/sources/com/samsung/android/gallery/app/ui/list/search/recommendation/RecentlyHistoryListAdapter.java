package com.samsung.android.gallery.app.ui.list.search.recommendation;

import B2.i;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.list.search.recommendation.abstraction.RecommendationViewListener;
import com.samsung.android.gallery.module.search.history.HistoryItem;
import com.samsung.android.gallery.support.utils.ResourceCompat;
import com.samsung.android.gallery.support.utils.ThreadUtil;
import com.samsung.android.gallery.widget.utils.ViewMarginUtils;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class RecentlyHistoryListAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ArrayList<HistoryItem> mHistoryItems = new ArrayList<>();
    private final RecommendationViewListener mViewListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mCircleThumb;
        ImageView mDeleteButton;
        TextView mTitleView;
        ImageView mTypeIcon;

        public ViewHolder(View view) {
            super(view);
            this.mTitleView = (TextView) view.findViewById(R.id.history_text);
            this.mDeleteButton = (ImageView) view.findViewById(R.id.history_delete_button);
            this.mCircleThumb = (ImageView) view.findViewById(R.id.circle_thumb);
            this.mTypeIcon = (ImageView) view.findViewById(R.id.type_icon);
        }

        public void setOnHistoryItemClickListener(View.OnClickListener onClickListener) {
            this.itemView.setOnClickListener(onClickListener);
        }

        public void setTitle(String str) {
            this.mTitleView.setText(str);
        }

        public void updateMargin() {
            int dimensionPixelOffset = this.itemView.getResources().getDimensionPixelOffset(R.dimen.search_recommend_item_icon_margin);
            if (ResourceCompat.isSmallScreenSize(this.itemView.getResources())) {
                dimensionPixelOffset /= 2;
            }
            ViewMarginUtils.setHorizontalMargin(this.mDeleteButton, dimensionPixelOffset);
        }
    }

    public RecentlyHistoryListAdapter(RecommendationViewListener recommendationViewListener) {
        this.mViewListener = recommendationViewListener;
    }

    private void deleteHistoryItem(ViewHolder viewHolder, HistoryItem historyItem) {
        this.mHistoryItems.remove(historyItem);
        notifyItemRemoved(viewHolder.getLayoutPosition());
        this.mViewListener.onDeleteHistoryClick(historyItem);
    }

    private void drawCreatureThumbnail(ViewHolder viewHolder, HistoryItem historyItem) {
        historyItem.getCreatureThumbnail(new a(this, viewHolder, historyItem));
    }

    private void drawTypeIcon(ViewHolder viewHolder, HistoryItem historyItem) {
        Drawable drawable;
        Integer typeIconResId = historyItem.getTypeIconResId();
        if (typeIconResId != null && (drawable = viewHolder.itemView.getContext().getDrawable(typeIconResId.intValue())) != null) {
            Drawable mutate = drawable.mutate();
            Integer typeIconTintColor = historyItem.getTypeIconTintColor();
            if (typeIconTintColor != null) {
                mutate.setTint(viewHolder.itemView.getContext().getColor(typeIconTintColor.intValue()));
            }
            viewHolder.mTypeIcon.setImageDrawable(mutate);
            viewHolder.mTypeIcon.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$drawCreatureThumbnail$2(Bitmap bitmap, ViewHolder viewHolder, HistoryItem historyItem) {
        if (bitmap != null) {
            viewHolder.mCircleThumb.setImageBitmap(bitmap);
            viewHolder.mCircleThumb.setClipToOutline(true);
            return;
        }
        deleteHistoryItem(viewHolder, historyItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$drawCreatureThumbnail$3(ViewHolder viewHolder, HistoryItem historyItem, Bitmap bitmap) {
        ThreadUtil.postOnUiThread(new d(bitmap, viewHolder, this, historyItem));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setClickListeners$0(HistoryItem historyItem, View view) {
        this.mViewListener.onRecentlyHistoryClick(historyItem);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setClickListeners$1(ViewHolder viewHolder, HistoryItem historyItem, View view) {
        deleteHistoryItem(viewHolder, historyItem);
    }

    private void setClickListeners(ViewHolder viewHolder, HistoryItem historyItem) {
        viewHolder.setOnHistoryItemClickListener(new b(this, historyItem, 0));
        viewHolder.mDeleteButton.setOnClickListener(new c(this, viewHolder, historyItem));
    }

    public void deleteAllHistoryItem() {
        notifyItemRangeRemoved(0, this.mHistoryItems.size());
        this.mHistoryItems.clear();
    }

    public int getItemCount() {
        return this.mHistoryItems.size();
    }

    public boolean isEmpty() {
        return this.mHistoryItems.isEmpty();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        View d = C0086a.d(viewGroup, R.layout.recycler_item_recommendation_recently_history_layout, viewGroup, false);
        RecommendationViewListener recommendationViewListener = this.mViewListener;
        Objects.requireNonNull(recommendationViewListener);
        d.setOnTouchListener(new i(3, recommendationViewListener));
        return new ViewHolder(d);
    }

    public void setHistoryItem(ArrayList<HistoryItem> arrayList) {
        this.mHistoryItems = arrayList;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i2, List<Object> list) {
        if (list.contains("updateSideMargin")) {
            viewHolder.updateMargin();
        }
        super.onBindViewHolder(viewHolder, i2, list);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i2) {
        HistoryItem historyItem = this.mHistoryItems.get(i2);
        viewHolder.setTitle(historyItem.getDisplayName());
        if (historyItem.isPeople() || historyItem.isPet()) {
            viewHolder.mTypeIcon.setVisibility(8);
            viewHolder.mCircleThumb.setVisibility(0);
            drawCreatureThumbnail(viewHolder, historyItem);
        } else if (historyItem.hasTypeIcon()) {
            viewHolder.mCircleThumb.setVisibility(8);
            drawTypeIcon(viewHolder, historyItem);
        } else {
            viewHolder.mCircleThumb.setVisibility(8);
            viewHolder.mTypeIcon.setVisibility(8);
        }
        setClickListeners(viewHolder, historyItem);
    }
}
