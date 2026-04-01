package com.samsung.android.gallery.widget.pinch;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewParent;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.abstraction.GridSpans;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IPinchRecyclerView {
    void addDefaultItemAnimator();

    IPinchAdapter getAdapter();

    int getBottom();

    RecyclerView.ViewHolder getChildViewHolder(View view);

    Context getContext();

    int getDepth();

    int getDepthFromColumnCount(int i2);

    GridSpans getGridSpans() {
        return null;
    }

    int getHeight();

    int getMaxDepth();

    int getPaddingBottom();

    ViewParent getParent();

    RecyclerView.RecycledViewPool getRecycledViewPool();

    Resources getResources();

    int getTop();

    boolean isSelectionMode() {
        return false;
    }

    void refreshScrollPosition();

    void removeItemAnimator();

    void resetRecyclerViewCache();

    void setClipChildren(boolean z);

    void setItemViewCacheSize(int i2);

    void setPreserveFocusAfterLayout(boolean z);
}
