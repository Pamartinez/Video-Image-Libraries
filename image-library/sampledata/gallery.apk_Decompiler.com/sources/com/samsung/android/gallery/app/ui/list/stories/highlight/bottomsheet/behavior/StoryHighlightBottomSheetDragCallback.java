package com.samsung.android.gallery.app.ui.list.stories.highlight.bottomsheet.behavior;

import android.view.View;
import androidx.core.math.MathUtils;
import androidx.customview.widget.ViewDragHelper;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryHighlightBottomSheetDragCallback extends ViewDragHelper.Callback {
    StoryHighlightBehavior mBehavior;

    public <V extends View> StoryHighlightBottomSheetDragCallback(StoryHighlightBehavior storyHighlightBehavior) {
        this.mBehavior = storyHighlightBehavior;
    }

    private boolean releasedLow(View view) {
        int top = view.getTop();
        StoryHighlightBehavior storyHighlightBehavior = this.mBehavior;
        if (top > (storyHighlightBehavior.getExpandedOffset() + storyHighlightBehavior.parentHeight) / 2) {
            return true;
        }
        return false;
    }

    public int clampViewPositionHorizontal(View view, int i2, int i7) {
        return view.getLeft();
    }

    public int clampViewPositionVertical(View view, int i2, int i7) {
        return MathUtils.clamp(i2, this.mBehavior.getExpandedOffset(), this.mBehavior.parentHeight);
    }

    public int getViewVerticalDragRange(View view) {
        return this.mBehavior.parentHeight;
    }

    public void onViewDragStateChanged(int i2) {
        if (i2 == 1) {
            StoryHighlightBehavior storyHighlightBehavior = this.mBehavior;
            if (storyHighlightBehavior.draggable) {
                storyHighlightBehavior.setStateInternal(1);
            }
        }
    }

    public void onViewPositionChanged(View view, int i2, int i7, int i8, int i10) {
        this.mBehavior.dispatchOnSlide(i7);
    }

    public void onViewReleased(View view, float f, float f5) {
        int i2;
        int i7 = 6;
        if (f5 < 0.0f) {
            StoryHighlightBehavior storyHighlightBehavior = this.mBehavior;
            if (storyHighlightBehavior.fitToContents) {
                i2 = storyHighlightBehavior.fitToContentsOffset;
            } else {
                int top = view.getTop();
                StoryHighlightBehavior storyHighlightBehavior2 = this.mBehavior;
                int i8 = storyHighlightBehavior2.halfExpandedOffset;
                if (top > i8) {
                    i2 = i8;
                    this.mBehavior.startSettlingAnimation(view, i7, i2, true);
                }
                i2 = storyHighlightBehavior2.getExpandedOffset();
            }
        } else if ((Math.abs(f) >= Math.abs(f5) || f5 <= 500.0f) && !releasedLow(view)) {
            StoryHighlightBehavior storyHighlightBehavior3 = this.mBehavior;
            if (storyHighlightBehavior3.fitToContents) {
                i2 = storyHighlightBehavior3.fitToContentsOffset;
            } else if (Math.abs(view.getTop() - this.mBehavior.getExpandedOffset()) < Math.abs(view.getTop() - this.mBehavior.halfExpandedOffset)) {
                i2 = this.mBehavior.getExpandedOffset();
            } else {
                i2 = this.mBehavior.halfExpandedOffset;
                this.mBehavior.startSettlingAnimation(view, i7, i2, true);
            }
        } else {
            i2 = this.mBehavior.parentHeight;
            i7 = 5;
            this.mBehavior.startSettlingAnimation(view, i7, i2, true);
        }
        i7 = 3;
        this.mBehavior.startSettlingAnimation(view, i7, i2, true);
    }

    public boolean tryCaptureView(View view, int i2) {
        View view2;
        StoryHighlightBehavior storyHighlightBehavior = this.mBehavior;
        int i7 = storyHighlightBehavior.state;
        if (i7 != 1 && !storyHighlightBehavior.touchingScrollingChild) {
            if (i7 == 3 && storyHighlightBehavior.activePointerId == i2) {
                WeakReference<View> weakReference = storyHighlightBehavior.nestedScrollingChildRef;
                if (weakReference != null) {
                    view2 = weakReference.get();
                } else {
                    view2 = null;
                }
                if (view2 != null && view2.canScrollVertically(-1)) {
                    return false;
                }
            }
            WeakReference<V> weakReference2 = this.mBehavior.viewRef;
            if (weakReference2 == null || weakReference2.get() != view) {
                return false;
            }
            return true;
        }
        return false;
    }
}
