package com.samsung.android.gallery.widget.filmstrip3;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.utils.Features;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ItemSetHeightAnimation implements ValueAnimator.AnimatorUpdateListener {
    private final boolean IS_RTL = Features.isEnabled(Features.IS_RTL);
    private final boolean mAlignStart;
    private final int mBasePoint;
    private final View mItemView;
    private final int mParentHeight;
    private final RecyclerView mParentView;
    private final float mRatio;

    public ItemSetHeightAnimation(View view, RecyclerView recyclerView) {
        this.mItemView = view;
        this.mParentView = recyclerView;
        this.mParentHeight = recyclerView.getHeight();
        this.mRatio = ((float) view.getWidth()) / ((float) view.getHeight());
        int width = recyclerView.getWidth() / 2;
        int x9 = (int) view.getX();
        int width2 = view.getWidth() + ((int) view.getX());
        if (Math.abs(x9 - width) < Math.abs(width2 - width)) {
            this.mBasePoint = x9;
            this.mAlignStart = true;
            return;
        }
        this.mBasePoint = width2;
        this.mAlignStart = false;
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        if (this.mItemView != null) {
            int intValue = (this.mParentHeight - ((Integer) valueAnimator.getAnimatedValue()).intValue()) / 2;
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mItemView.getLayoutParams();
            if (marginLayoutParams != null) {
                int i2 = marginLayoutParams.width;
                marginLayoutParams.topMargin = intValue;
                int intValue2 = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                marginLayoutParams.height = intValue2;
                marginLayoutParams.width = Math.round(((float) intValue2) * this.mRatio);
                this.mItemView.setLayoutParams(marginLayoutParams);
                if (this.IS_RTL) {
                    if (this.mAlignStart && marginLayoutParams.width > i2) {
                        this.mParentView.scrollBy(((int) this.mItemView.getX()) - this.mBasePoint, 0);
                    }
                } else if (!this.mAlignStart && marginLayoutParams.width > i2) {
                    this.mParentView.scrollBy((this.mItemView.getWidth() + ((int) this.mItemView.getX())) - this.mBasePoint, 0);
                }
            }
        }
    }
}
