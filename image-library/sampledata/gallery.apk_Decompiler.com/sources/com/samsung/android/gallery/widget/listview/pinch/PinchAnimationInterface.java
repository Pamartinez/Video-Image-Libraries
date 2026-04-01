package com.samsung.android.gallery.widget.listview.pinch;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.widget.pinch.IPinchRecyclerView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface PinchAnimationInterface {
    void endYearOrMonthForViewingAnimation();

    int[] getActiveColumns();

    float[] getFocusXY();

    IPinchRecyclerView getRecyclerView();

    RecyclerView.ViewHolder getViewHolder(View view);

    void startAnimation();

    void updateAnimation();
}
