package com.samsung.android.gallery.widget.listview;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.abstraction.GridValue;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.listview.scroller.OverScrollDetector;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GalleryGridLayoutManager extends GridLayoutManager {
    private int mDefaultStartPadding;
    protected int mExtraStartPadding = 0;
    protected boolean mIsDrawerOpened;
    private OverScrollDetector mOverScrollDetector;

    public GalleryGridLayoutManager(Context context, int i2) {
        super(context, i2);
    }

    public int getExtraStartPadding(int i2) {
        if (hintDrawerOpened(i2)) {
            return this.mExtraStartPadding;
        }
        return this.mDefaultStartPadding;
    }

    public int getHintHorizontalPadding(int i2) {
        return 0;
    }

    public int getHintPaddingLeft(int i2) {
        int i7;
        if (isLayoutRTL()) {
            i7 = 0;
        } else {
            i7 = getExtraStartPadding(i2);
        }
        return getSpacing(i2) + i7;
    }

    public int getHintPaddingStart(int i2) {
        return getExtraStartPadding(i2) - getSpacing(i2);
    }

    public int getPaddingLeft() {
        return getHintPaddingLeft(getSpanCount());
    }

    public int getPaddingRight() {
        int i2;
        if (isLayoutRTL()) {
            i2 = getExtraStartPadding(getSpanCount());
        } else {
            i2 = 0;
        }
        return getSpacing(getSpanCount()) + i2;
    }

    public int getPaddingStart() {
        return getHintPaddingStart(getSpanCount());
    }

    public int getRealGridSize(int i2) {
        return GridValue.revert(i2);
    }

    public int getSpacing(int i2) {
        return 0;
    }

    public boolean hintDrawerOpened(int i2) {
        return GridValue.isSplit(i2, this.mIsDrawerOpened, true);
    }

    public void onAdapterChanged(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2) {
        onAdapterChangedInternal(adapter, adapter2);
    }

    public View onFocusSearchFailed(View view, int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.e("GalleryGridLayoutManager", "focus search failed");
        if (i2 == 130) {
            if (view.getParentForAccessibility() instanceof RecyclerView) {
                int findLastVisibleItemPosition = findLastVisibleItemPosition();
                int spanCount = getSpanCount() + findLastVisibleItemPosition;
                View findViewByPosition = findViewByPosition(findLastVisibleItemPosition);
                if (spanCount < state.getItemCount() && findViewByPosition != null) {
                    ((RecyclerView) view.getParentForAccessibility()).scrollBy(0, findViewByPosition.getHeight());
                    return view;
                }
            } else {
                Log.e("GalleryGridLayoutManager", "focus out of list : " + view);
            }
        }
        return super.onFocusSearchFailed(view, i2, recycler, state);
    }

    public int scrollVerticallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int scrollVerticallyBy = super.scrollVerticallyBy(i2, recycler, state);
        OverScrollDetector overScrollDetector = this.mOverScrollDetector;
        if (overScrollDetector != null) {
            overScrollDetector.scrollVerticallyBy(i2, recycler, state, scrollVerticallyBy);
        }
        return scrollVerticallyBy;
    }

    public void setOverScrollListener(OverScrollDetector.OverScrollListener overScrollListener) {
        if (this.mOverScrollDetector == null) {
            this.mOverScrollDetector = new OverScrollDetector();
        }
        this.mOverScrollDetector.setOverScrollListener(overScrollListener);
    }

    public boolean supportsPredictiveItemAnimations() {
        return false;
    }

    public boolean updateExtraStartPadding(int i2, int i7, float f, boolean z, boolean z3) {
        boolean z7;
        if (this.mIsDrawerOpened != z) {
            z7 = true;
        } else {
            z7 = false;
        }
        this.mExtraStartPadding = i2;
        this.mDefaultStartPadding = i7;
        this.mIsDrawerOpened = z;
        return z7;
    }

    public void onAdapterChangedInternal(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2) {
    }
}
