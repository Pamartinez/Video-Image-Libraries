package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage;

import android.util.DisplayMetrics;
import android.view.View;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecyclerViewPagerSnapHelper extends LinearSnapHelper {
    /* access modifiers changed from: private */
    public RecyclerView mRecyclerView;

    public void attachToRecyclerView(RecyclerView recyclerView) {
        if (this.mRecyclerView != recyclerView) {
            this.mRecyclerView = recyclerView;
        }
        super.attachToRecyclerView(recyclerView);
    }

    public RecyclerView.SmoothScroller createScroller(RecyclerView.LayoutManager layoutManager) {
        if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)) {
            return null;
        }
        return new LinearSmoothScroller(this.mRecyclerView.getContext()) {
            public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return 220.0f / ((float) displayMetrics.widthPixels);
            }

            public void onTargetFound(View view, RecyclerView.State state, RecyclerView.SmoothScroller.Action action) {
                if (RecyclerViewPagerSnapHelper.this.mRecyclerView != null) {
                    RecyclerViewPagerSnapHelper recyclerViewPagerSnapHelper = RecyclerViewPagerSnapHelper.this;
                    int[] calculateDistanceToFinalSnap = recyclerViewPagerSnapHelper.calculateDistanceToFinalSnap(recyclerViewPagerSnapHelper.mRecyclerView.getLayoutManager(), view);
                    int i2 = calculateDistanceToFinalSnap[0];
                    int i7 = calculateDistanceToFinalSnap[1];
                    int calculateTimeForDeceleration = calculateTimeForDeceleration(Math.max(Math.abs(i2), Math.abs(i7)));
                    if (calculateTimeForDeceleration > 0) {
                        action.update(i2, i7, calculateTimeForDeceleration, this.mDecelerateInterpolator);
                    }
                }
            }
        };
    }
}
