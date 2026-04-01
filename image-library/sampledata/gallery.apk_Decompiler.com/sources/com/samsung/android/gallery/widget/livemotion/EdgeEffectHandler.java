package com.samsung.android.gallery.widget.livemotion;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.widget.abstraction.SimpleAnimatorListener;
import com.samsung.android.gallery.widget.livemotion.abstraction.ILiveMotionAdapter;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EdgeEffectHandler {
    Animator mEdgeEffect;

    private final int getCurrentDataPosition(ViewPager2 viewPager2, ILiveMotionAdapter iLiveMotionAdapter) {
        return iLiveMotionAdapter.getDataPosition(viewPager2.getCurrentItem());
    }

    public RecyclerView.ViewHolder getViewHolder(ViewPager2 viewPager2, int i2) {
        RecyclerView recyclerView = (RecyclerView) viewPager2.getChildAt(0);
        if (recyclerView != null) {
            return recyclerView.findViewHolderForLayoutPosition(i2);
        }
        return null;
    }

    public void showEdgeEffect(ViewPager2 viewPager2, ILiveMotionAdapter iLiveMotionAdapter, int i2) {
        RecyclerView.ViewHolder viewHolder;
        boolean z;
        if (viewPager2 != null && iLiveMotionAdapter != null && this.mEdgeEffect == null) {
            if ((i2 >= iLiveMotionAdapter.getItemCount() || i2 < 0) && (viewHolder = getViewHolder(viewPager2, getCurrentDataPosition(viewPager2, iLiveMotionAdapter))) != null) {
                final View view = viewHolder.itemView;
                if (i2 >= iLiveMotionAdapter.getItemCount()) {
                    z = true;
                } else {
                    z = false;
                }
                float f = 0.0f;
                if (Features.isEnabled(Features.IS_RTL)) {
                    if (!z) {
                        f = (float) view.getWidth();
                    }
                    view.setPivotX(f);
                } else {
                    if (z) {
                        f = (float) view.getWidth();
                    }
                    view.setPivotX(f);
                }
                view.setPivotY(((float) view.getHeight()) / 2.0f);
                AnimatorSet animatorSet = new AnimatorSet();
                ArrayList arrayList = new ArrayList();
                arrayList.add(ObjectAnimator.ofFloat(view, "scaleX", new float[]{1.0f, 1.015f}).setDuration(200));
                arrayList.add(ObjectAnimator.ofFloat(view, "scaleX", new float[]{1.015f, 1.0f}).setDuration(200));
                animatorSet.playSequentially(arrayList);
                animatorSet.addListener(new SimpleAnimatorListener() {
                    public void onAnimationEnd(Animator animator) {
                        View view = view;
                        view.setPivotX(((float) view.getWidth()) / 2.0f);
                        View view2 = view;
                        view2.setPivotY(((float) view2.getHeight()) / 2.0f);
                        EdgeEffectHandler.this.mEdgeEffect = null;
                    }
                });
                animatorSet.start();
                this.mEdgeEffect = animatorSet;
            }
        }
    }
}
