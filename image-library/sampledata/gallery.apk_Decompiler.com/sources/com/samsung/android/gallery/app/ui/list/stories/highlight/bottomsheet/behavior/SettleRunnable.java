package com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.behavior;

import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.customview.widget.ViewDragHelper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SettleRunnable implements Runnable {
    private final StoryHighlightBehavior behavior;
    boolean isCancelled;
    boolean isPosted;
    int targetState;
    private final View view;

    public SettleRunnable(StoryHighlightBehavior storyHighlightBehavior, View view2, int i2) {
        this.behavior = storyHighlightBehavior;
        this.view = view2;
        this.targetState = i2;
    }

    public static boolean isExpanding(StoryHighlightBehavior storyHighlightBehavior, SettleRunnable settleRunnable) {
        if (settleRunnable != null && storyHighlightBehavior.getState() == 2 && settleRunnable.targetState == 3) {
            return true;
        }
        return false;
    }

    public void cancel() {
        this.isCancelled = true;
    }

    public void run() {
        if (!this.isCancelled) {
            ViewDragHelper viewDragHelper = this.behavior.viewDragHelper;
            if (viewDragHelper == null || !viewDragHelper.continueSettling(true)) {
                this.behavior.setStateInternal(this.targetState);
            } else {
                ViewCompat.postOnAnimation(this.view, this);
            }
            this.isPosted = false;
        }
    }
}
