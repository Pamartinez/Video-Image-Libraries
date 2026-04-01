package com.samsung.android.gallery.app.ui.list.stories.category.ondemand;

import android.view.View;
import android.widget.TextView;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OnDemandRecommendViewHolder extends OnDemandFloatingViewHolder {
    private TextView mRecommendQuery;

    public OnDemandRecommendViewHolder(View view) {
        super(view);
    }

    public void bind(OnDemandFloatingItem onDemandFloatingItem) {
        super.bind(onDemandFloatingItem);
        this.mRecommendQuery.setText(onDemandFloatingItem.getQuery());
        this.mRecommendQuery.requestLayout();
    }

    public void bindView(View view) {
        this.mRecommendQuery = (TextView) view.findViewById(R.id.title);
    }

    public void recycle() {
        super.recycle();
        this.mRecommendQuery.setText("");
    }
}
