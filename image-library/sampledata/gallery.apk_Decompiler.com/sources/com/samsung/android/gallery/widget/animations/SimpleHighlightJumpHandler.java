package com.samsung.android.gallery.widget.animations;

import android.animation.Animator;
import android.graphics.RectF;
import android.view.View;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SimpleHighlightJumpHandler extends SimpleBackShrinkHandler {
    public SimpleHighlightJumpHandler(SimpleShrinkView simpleShrinkView, ListViewHolder listViewHolder) {
        super(simpleShrinkView, listViewHolder);
    }

    public ArrayList<Animator> getAnimatorList(View view, RectF rectF, RectF rectF2) {
        this.mView.mBlackboard.publish("data://user/MoveTimelineViewRectInfo", new Object[]{Float.valueOf(rectF.width()), Float.valueOf(rectF2.width())});
        return super.getAnimatorList(view, rectF, rectF2);
    }
}
