package androidx.recyclerview.widget;

import N2.j;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class LayoutState {
    int mAvailable;
    int mCurrentPosition;
    int mEndLine = 0;
    boolean mInfinite;
    int mItemDirection;
    int mLayoutDirection;
    boolean mRecycle = true;
    int mStartLine = 0;
    boolean mStopInFocusable;

    public boolean hasMore(RecyclerView.State state) {
        int i2 = this.mCurrentPosition;
        if (i2 < 0 || i2 >= state.getItemCount()) {
            return false;
        }
        return true;
    }

    public View next(RecyclerView.Recycler recycler) {
        View viewForPosition = recycler.getViewForPosition(this.mCurrentPosition);
        this.mCurrentPosition += this.mItemDirection;
        return viewForPosition;
    }

    public String toString() {
        StringBuilder sb2 = new StringBuilder("LayoutState{mAvailable=");
        sb2.append(this.mAvailable);
        sb2.append(", mCurrentPosition=");
        sb2.append(this.mCurrentPosition);
        sb2.append(", mItemDirection=");
        sb2.append(this.mItemDirection);
        sb2.append(", mLayoutDirection=");
        sb2.append(this.mLayoutDirection);
        sb2.append(", mStartLine=");
        sb2.append(this.mStartLine);
        sb2.append(", mEndLine=");
        return j.e(sb2, this.mEndLine, '}');
    }
}
