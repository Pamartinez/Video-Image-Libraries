package com.samsung.android.gallery.app.ui.list.stories.pinch;

import android.animation.Animator;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPinchAnimationManagerV2;
import com.samsung.android.gallery.app.ui.list.stories.abstraction.StoriesBaseLayoutManager;
import com.samsung.android.gallery.widget.animator.ScaleAnimator;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.GridInfo;
import com.samsung.android.gallery.widget.listview.pinch.fakelayoutmanager.PinchFakeLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.fakelayoutmanager.StoriesPinchFakeLayoutManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.samsung.android.sdk.scs.base.StatusCodes;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesPinchAnimationManager extends PicturesPinchAnimationManagerV2 {
    private float mFromRadius;
    private float mToRadius;

    public StoriesPinchAnimationManager(PinchLayoutManager pinchLayoutManager, GridInfo.ClusterInfo clusterInfo, boolean z) {
        super(pinchLayoutManager, clusterInfo, z);
    }

    public void addDecoViewFadeInAnimation(ArrayList<Animator> arrayList, ListViewHolder listViewHolder) {
        super.addDecoViewFadeInAnimation(arrayList, listViewHolder);
        View decoView = listViewHolder.getDecoView(32);
        if (decoView != null && ViewUtils.getAlpha(decoView) != 1.0f) {
            addFadeInAnimation(arrayList, decoView, decoView.getAlpha(), 1.0f);
        }
    }

    public void addHeaderTranslateAnimator(View view, float f, float f5, float f8) {
        super.addHeaderTranslateAnimator(view, f + ((float) ((this.mLayoutManager.getHintPaddingLeft(this.mGridInfo.to()) + (this.mToPadding - this.mFromPadding)) - this.mLayoutManager.getHintPaddingLeft(this.mGridInfo.from()))), f5, f8);
    }

    public PinchFakeLayoutManager createFakeLayoutManager(ViewGroup viewGroup) {
        return new StoriesPinchFakeLayoutManager(this.mLayoutManager, getRecyclerView(), viewGroup, this.mPositionCache);
    }

    public ScaleAnimator createScaleAnimator(ListViewHolder listViewHolder, boolean z, RectF[] rectFArr, boolean z3) {
        return super.createScaleAnimator(listViewHolder, z, rectFArr, z3).setOutlineProvider(this.mFromRadius, this.mToRadius);
    }

    public int getLastVisibleItemPosition() {
        int lastVisibleItemPosition = super.getLastVisibleItemPosition();
        do {
            lastVisibleItemPosition++;
            if (lastVisibleItemPosition >= this.mLayoutManager.getItemCount()) {
                return this.mLayoutManager.getItemCount() - 1;
            }
        } while (ViewUtils.isAttachedToWindow(this.mLayoutManager.findViewByPosition(lastVisibleItemPosition)));
        return lastVisibleItemPosition - 1;
    }

    public void initPadding() {
        StoriesBaseLayoutManager storiesBaseLayoutManager = (StoriesBaseLayoutManager) this.mLayoutManager;
        this.mFromPadding = storiesBaseLayoutManager.getGridItemPadding(this.mGridInfo.from());
        this.mToPadding = storiesBaseLayoutManager.getGridItemPadding(this.mGridInfo.getRealToGrid());
        this.mFromRadius = storiesBaseLayoutManager.getThumbnailRadius(this.mGridInfo.from());
        this.mToRadius = storiesBaseLayoutManager.getThumbnailRadius(this.mGridInfo.getRealToGrid());
        this.mFadeInDuration = StatusCodes.INPUT_MISSING;
    }

    public boolean withRealRatio() {
        return false;
    }
}
