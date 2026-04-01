package com.samsung.android.gallery.widget.animations;

import android.animation.Animator;
import android.graphics.RectF;
import android.view.View;
import com.samsung.android.gallery.widget.animator.CircleAnimator;
import com.samsung.android.gallery.widget.animator.Color2Animator;
import com.samsung.android.gallery.widget.animator.PropertyAnimator;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.sdk.scs.base.StatusCodes;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class QuickViewBackShrinkHandler extends SimpleBackShrinkHandler {
    public QuickViewBackShrinkHandler(SimpleShrinkView simpleShrinkView, ListViewHolder listViewHolder) {
        super(simpleShrinkView, listViewHolder);
        this.mDuration = StatusCodes.INPUT_MISSING;
    }

    public PropertyAnimator createBackgroundColorAnimator() {
        Color2Animator color2Animator = (Color2Animator) super.createBackgroundColorAnimator();
        color2Animator.setThreshold(0.1f);
        return color2Animator;
    }

    public ArrayList<Animator> getAnimatorList(View view, RectF rectF, RectF rectF2) {
        ArrayList<Animator> animatorList = super.getAnimatorList(view, rectF, rectF2);
        animatorList.add(new CircleAnimator(view));
        return animatorList;
    }

    public boolean isBackToCamera() {
        return true;
    }

    public RectF makeDummyRect(RectF rectF) {
        return makeDummyRectForQuickView(rectF);
    }

    public void clearShrinkData(boolean z) {
    }
}
