package com.samsung.android.gallery.app.ui.list.stories.category.ondemand;

import android.view.View;
import android.view.ViewGroup;
import c0.C0086a;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class OnDemandFloatingViewHolderFactory {
    private static OnDemandRecommendViewHolder createRecommendViewHolder(ViewGroup viewGroup) {
        return new OnDemandRecommendViewHolder(inflateView(viewGroup, R.layout.recycler_item_stories_category_recommendation_layout));
    }

    public static OnDemandFloatingViewHolder createViewHolder(ViewGroup viewGroup, int i2) {
        return createRecommendViewHolder(viewGroup);
    }

    private static View inflateView(ViewGroup viewGroup, int i2) {
        return C0086a.d(viewGroup, i2, viewGroup, false);
    }
}
