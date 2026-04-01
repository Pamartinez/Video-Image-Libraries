package com.samsung.android.gallery.module.search.recommendation;

import android.content.Context;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import java.util.AbstractQueue;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IQuerySuggester {
    void clear();

    AbstractQueue<IRecommendationItem> consume(Context context, IRecommendationItem iRecommendationItem);

    AbstractQueue<IRecommendationItem> getSuggestion(Context context, Blackboard blackboard, long j2);

    void preloadSuggestion(Context context, Blackboard blackboard);
}
