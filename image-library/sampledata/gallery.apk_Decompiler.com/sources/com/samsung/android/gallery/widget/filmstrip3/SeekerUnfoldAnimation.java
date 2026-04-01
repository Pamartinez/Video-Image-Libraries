package com.samsung.android.gallery.widget.filmstrip3;

import android.animation.ValueAnimator;
import android.view.View;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.R$dimen;
import com.samsung.android.gallery.widget.utils.ViewUtils;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SeekerUnfoldAnimation implements ValueAnimator.AnimatorUpdateListener {
    private final boolean IS_RTL = Features.isEnabled(Features.IS_RTL);
    private final View mItemView;
    private final float mOffset;
    private final FilmStripView mParent;

    public SeekerUnfoldAnimation(View view, FilmStripView filmStripView, float f) {
        this.mItemView = view;
        this.mParent = filmStripView;
        this.mOffset = f;
    }

    public static void scrollToOffset(View view, float f, FilmStripView filmStripView, boolean z) {
        if (z) {
            f = 1.0f - f;
        }
        int paddingStart = view.getPaddingStart() + ((int) view.getX());
        int width = (int) (((float) ((view.getWidth() - view.getPaddingStart()) - view.getPaddingEnd())) * f);
        if (filmStripView != null) {
            filmStripView.scrollBy(paddingStart - (filmStripView.getCenterX() - width), 0);
        }
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        valueAnimator.getValues();
        View view = this.mItemView;
        if (view != null) {
            ViewUtils.resize(view, ((Integer) valueAnimator.getAnimatedValue()).intValue(), this.mItemView.getContext().getResources().getDimensionPixelSize(R$dimen.film_strip_focused_height));
            scrollToOffset(this.mItemView, this.mOffset, this.mParent, this.IS_RTL);
        }
    }
}
