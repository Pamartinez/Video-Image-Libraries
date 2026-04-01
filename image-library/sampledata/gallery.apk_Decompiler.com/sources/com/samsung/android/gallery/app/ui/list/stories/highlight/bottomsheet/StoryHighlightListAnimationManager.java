package com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet;

import android.graphics.RectF;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPinchAnimationManagerV2;
import com.samsung.android.gallery.widget.animator.ScaleAnimator;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.GridInfo;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryHighlightListAnimationManager extends PicturesPinchAnimationManagerV2 {
    public StoryHighlightListAnimationManager(PinchLayoutManager pinchLayoutManager, GridInfo.ClusterInfo clusterInfo, boolean z) {
        super(pinchLayoutManager, clusterInfo, z);
    }

    public ScaleAnimator createScaleAnimator(ListViewHolder listViewHolder, boolean z, RectF[] rectFArr, boolean z3) {
        return super.createScaleAnimator(listViewHolder, z, rectFArr, z3).setOutlineProvider((float) GridDimenHelper.getRadiusFromDepth(getRecyclerView(), this.mGridInfo.from()), (float) GridDimenHelper.getRadiusFromDepth(getRecyclerView(), this.mGridInfo.to()));
    }
}
