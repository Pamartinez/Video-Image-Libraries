package com.samsung.android.gallery.app.ui.list.search.recommendation.abstraction;

import android.view.MotionEvent;
import android.view.View;
import com.samsung.android.gallery.app.ui.list.search.category.CategoryItemViewHolderFactory;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.search.history.HistoryItem;
import com.samsung.android.gallery.module.search.recommendation.IRecommendationItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface RecommendationViewListener {
    CategoryItemViewHolderFactory getViewHolderFactory() {
        return null;
    }

    void onDeleteHistoryClick(HistoryItem historyItem);

    void onDeleteRemainingHistory(HistoryItem historyItem);

    void onRecentlyHistoryClick(HistoryItem historyItem);

    void onSuggestionKeywordClick(IRecommendationItem iRecommendationItem, int i2);

    boolean onViewTouch(View view, MotionEvent motionEvent);

    void onCategoryItemClicked(MediaItem mediaItem) {
    }
}
