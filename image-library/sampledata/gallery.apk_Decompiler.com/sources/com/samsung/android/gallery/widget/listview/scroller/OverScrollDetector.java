package com.samsung.android.gallery.widget.listview.scroller;

import androidx.recyclerview.widget.RecyclerView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OverScrollDetector {
    private boolean mIdle = true;
    private OverScrollListener mOverScrollListener;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface OverScrollListener {
        void onBottomOverScroll(int i2);

        void onTopOverScroll(int i2);
    }

    public void scrollVerticallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state, int i7) {
        OverScrollListener overScrollListener = this.mOverScrollListener;
        if (overScrollListener != null) {
            int i8 = i2 - i7;
            if (i8 > 0) {
                if (this.mIdle) {
                    overScrollListener.onBottomOverScroll(i8);
                    this.mIdle = false;
                }
            } else if (i8 >= 0) {
                this.mIdle = true;
            } else if (this.mIdle) {
                overScrollListener.onTopOverScroll(i8);
                this.mIdle = false;
            }
        }
    }

    public void setOverScrollListener(OverScrollListener overScrollListener) {
        this.mOverScrollListener = overScrollListener;
    }
}
