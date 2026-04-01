package com.samsung.android.gallery.widget.animations;

import android.animation.Animator;
import android.graphics.RectF;
import android.view.View;
import android.view.animation.PathInterpolator;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.support.helper.DeviceInfo;
import com.samsung.android.gallery.widget.animator.CircleAnimator;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class QuickViewDragShrinkHandler extends SimpleDragShrinkHandler {
    private final boolean mHasQuickViewInfo;

    public QuickViewDragShrinkHandler(SimpleShrinkView simpleShrinkView, ListViewHolder listViewHolder) {
        super(simpleShrinkView, listViewHolder);
        boolean z;
        LaunchIntent launchIntent = (LaunchIntent) this.mView.mBlackboard.read("data://launch_intent");
        if (launchIntent == null || launchIntent.getQuickViewThumbnailRect() == null) {
            z = false;
        } else {
            z = true;
        }
        this.mHasQuickViewInfo = z;
        this.mDuration = 270;
        this.mTimeInterpolator = new PathInterpolator(0.22f, 0.25f, 0.0f, 1.0f);
        this.mIsQuickView = true;
    }

    public boolean canReturn() {
        return true;
    }

    public ArrayList<Animator> getAnimatorList(View view, RectF rectF, RectF rectF2) {
        ArrayList<Animator> animatorList = super.getAnimatorList(view, rectF, rectF2);
        if (this.mHasQuickViewInfo) {
            animatorList.add(new CircleAnimator(view));
        }
        return animatorList;
    }

    public RectF getDummyRectForQuickView(RectF rectF) {
        float displayHeight = (float) DeviceInfo.getDisplayHeight(this.mView.mActivity);
        this.mDuration = (int) Math.min(500.0f, (displayHeight - rectF.top) / 4.68f);
        return new RectF(rectF.left, displayHeight, rectF.right, rectF.height() + displayHeight);
    }

    public RectF makeDummyRect(RectF rectF) {
        return makeDummyRectForQuickView(rectF);
    }

    public void clearShrinkData(boolean z) {
    }
}
