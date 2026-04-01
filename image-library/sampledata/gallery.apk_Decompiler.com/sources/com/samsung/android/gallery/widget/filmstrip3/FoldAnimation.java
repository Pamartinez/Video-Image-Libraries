package com.samsung.android.gallery.widget.filmstrip3;

import android.animation.ValueAnimator;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FoldAnimation implements ValueAnimator.AnimatorUpdateListener {
    private final FilmStripViewHolder mCenterVh;
    private final int mCenterX1;
    private final int mCenterX2;
    private final View mItemView;
    private final FilmStripView mParent;

    public FoldAnimation(View view, RecyclerView recyclerView) {
        int i2;
        this.mItemView = view;
        FilmStripView filmStripView = (FilmStripView) recyclerView;
        this.mParent = filmStripView;
        FilmStripViewHolder centerViewHolder = filmStripView.getCenterViewHolder();
        this.mCenterVh = centerViewHolder;
        if (centerViewHolder != null) {
            i2 = centerViewHolder.getMaxWidth() / 2;
        } else {
            i2 = 0;
        }
        int width = recyclerView.getWidth() / 2;
        this.mCenterX1 = width - i2;
        this.mCenterX2 = width + i2;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        if (this.mItemView != null && this.mCenterVh != null) {
            int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
            ViewUtils.setWidth(this.mItemView, intValue);
            int x9 = (int) this.mCenterVh.itemView.getX();
            int x10 = ((int) this.mCenterVh.itemView.getX()) + intValue;
            int i2 = this.mCenterX1;
            if (x9 > i2) {
                this.mParent.scrollBy(x9 - i2, 0);
            }
            int i7 = this.mCenterX2;
            if (x10 < i7) {
                this.mParent.scrollBy(x10 - i7, 0);
            }
        }
    }
}
