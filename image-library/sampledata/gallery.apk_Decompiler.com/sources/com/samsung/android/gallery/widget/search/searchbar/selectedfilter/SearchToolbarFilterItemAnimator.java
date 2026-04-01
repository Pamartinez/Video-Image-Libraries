package com.samsung.android.gallery.widget.search.searchbar.selectedfilter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.animation.PathInterpolator;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchToolbarFilterItemAnimator extends DefaultItemAnimator {
    /* access modifiers changed from: private */
    public boolean mIsAnimationRunning = false;

    public boolean animateAdd(final RecyclerView.ViewHolder viewHolder) {
        this.mIsAnimationRunning = true;
        viewHolder.itemView.setScaleX(0.5f);
        viewHolder.itemView.setScaleY(0.5f);
        viewHolder.itemView.setAlpha(0.0f);
        viewHolder.itemView.animate().scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setStartDelay(200).setDuration(200).setInterpolator(new PathInterpolator(0.17f, 1.09f, 0.33f, 1.1f)).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                SearchToolbarFilterItemAnimator.this.dispatchAddFinished(viewHolder);
                viewHolder.itemView.setScaleX(1.0f);
                viewHolder.itemView.setScaleY(1.0f);
                viewHolder.itemView.setAlpha(1.0f);
                SearchToolbarFilterItemAnimator.this.mIsAnimationRunning = false;
            }
        });
        return true;
    }

    public boolean animateRemove(final RecyclerView.ViewHolder viewHolder) {
        this.mIsAnimationRunning = true;
        viewHolder.itemView.animate().scaleX(0.5f).scaleY(0.5f).alpha(0.0f).setDuration(200).setInterpolator(new PathInterpolator(0.22f, 0.0f, 0.0f, 1.0f)).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                SearchToolbarFilterItemAnimator.this.dispatchRemoveFinished(viewHolder);
                viewHolder.itemView.setScaleX(1.0f);
                viewHolder.itemView.setScaleY(1.0f);
                viewHolder.itemView.setAlpha(1.0f);
                SearchToolbarFilterItemAnimator.this.mIsAnimationRunning = false;
            }
        });
        return true;
    }

    public boolean isRunning() {
        if (super.isRunning() || this.mIsAnimationRunning) {
            return true;
        }
        return false;
    }
}
