package com.samsung.android.gallery.app.ui.list.stories.highlight.lastpage;

import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import o6.p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RecyclerPageScrollHelper extends RecyclerView.OnScrollListener {
    private final RecyclerView mRecyclerView;
    private boolean mScrollHappened;
    private PageScrollListener mScrollListener;
    private final ScrollValues mScrollValues = new ScrollValues(0);

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface PageScrollListener {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ScrollValues {
        float mOffset;
        int mOffsetPx;
        int mPosition;

        public /* synthetic */ ScrollValues(int i2) {
            this();
        }

        public void reset() {
            this.mPosition = -1;
            this.mOffset = 0.0f;
            this.mOffsetPx = 0;
        }

        private ScrollValues() {
            this.mPosition = -1;
        }
    }

    public RecyclerPageScrollHelper(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
        recyclerView.addOnScrollListener(this);
    }

    private boolean containHorizontalRect(RectF rectF, RectF rectF2) {
        if (rectF.left > rectF2.left || rectF.right < rectF2.right) {
            return false;
        }
        return true;
    }

    private void dispatchState(int i2, float f, int i7) {
        if (this.mScrollListener != null) {
            RecyclerView recyclerView = this.mRecyclerView;
            float f5 = -f;
            for (int i8 = 0; i8 < recyclerView.getChildCount(); i8++) {
                View childAt = recyclerView.getChildAt(i8);
                ((LastPageView) ((p) this.mScrollListener).e).lambda$new$10(childAt, ((float) (((RecyclerView.LayoutParams) childAt.getLayoutParams()).getViewLayoutPosition() - i2)) + f5);
            }
        }
    }

    private int findFirstVisibleItemPosition() {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) this.mRecyclerView.getLayoutManager();
        if (linearLayoutManager != null) {
            return linearLayoutManager.findFirstVisibleItemPosition();
        }
        return 0;
    }

    private int findFirstVisibleItemPositionByRect() {
        RecyclerView recyclerView = this.mRecyclerView;
        RectF viewRect = ViewUtils.getViewRect(recyclerView);
        for (int i2 = 0; i2 < recyclerView.getChildCount(); i2++) {
            View childAt = recyclerView.getChildAt(i2);
            int viewLayoutPosition = ((RecyclerView.LayoutParams) childAt.getLayoutParams()).getViewLayoutPosition();
            if (containHorizontalRect(viewRect, ViewUtils.getViewRect(childAt))) {
                return viewLayoutPosition;
            }
        }
        return recyclerView.getChildCount() - 1;
    }

    private boolean isRtl() {
        return Features.isEnabled(Features.IS_RTL);
    }

    private void updateScrollValues() {
        float f;
        ScrollValues scrollValues = this.mScrollValues;
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) this.mRecyclerView.getLayoutManager();
        if (linearLayoutManager != null) {
            int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
            scrollValues.mPosition = findFirstVisibleItemPosition;
            if (findFirstVisibleItemPosition == -1) {
                scrollValues.reset();
                return;
            }
            View findViewByPosition = linearLayoutManager.findViewByPosition(findFirstVisibleItemPosition);
            if (findViewByPosition == null) {
                scrollValues.reset();
                return;
            }
            int leftDecorationWidth = linearLayoutManager.getLeftDecorationWidth(findViewByPosition);
            int rightDecorationWidth = linearLayoutManager.getRightDecorationWidth(findViewByPosition);
            ViewGroup.LayoutParams layoutParams = findViewByPosition.getLayoutParams();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                leftDecorationWidth += marginLayoutParams.leftMargin;
                rightDecorationWidth += marginLayoutParams.rightMargin;
            }
            int width = findViewByPosition.getWidth() + leftDecorationWidth + rightDecorationWidth;
            int left = (findViewByPosition.getLeft() - leftDecorationWidth) - this.mRecyclerView.getPaddingLeft();
            if (isRtl()) {
                left = -left;
            }
            int i2 = -left;
            scrollValues.mOffsetPx = i2;
            if (i2 < 0) {
                Log.e((CharSequence) "RecyclerPageScrollHelper", "unexpected negative mOffsetPx error ", Integer.valueOf(i2));
                scrollValues.mOffsetPx = 0;
            }
            if (width == 0) {
                f = 0.0f;
            } else {
                f = ((float) scrollValues.mOffsetPx) / ((float) width);
            }
            scrollValues.mOffset = f;
        }
    }

    public void invalidateScrollState() {
        dispatchState(findFirstVisibleItemPositionByRect(), 0.0f, 0);
        this.mScrollValues.reset();
    }

    public void onScrollStateChanged(RecyclerView recyclerView, int i2) {
        if (i2 == 0) {
            try {
                updateScrollValues();
                boolean z = true;
                if (!this.mScrollHappened) {
                    int i7 = this.mScrollValues.mPosition;
                    if (i7 != -1) {
                        dispatchState(i7, 0.0f, 0);
                    }
                } else if (this.mScrollValues.mOffsetPx == 0) {
                    int findFirstVisibleItemPosition = findFirstVisibleItemPosition();
                    int i8 = this.mScrollValues.mPosition;
                    if (findFirstVisibleItemPosition == i8) {
                        dispatchState(i8, 0.0f, 0);
                    }
                } else {
                    z = false;
                }
                if (z) {
                    this.mScrollValues.reset();
                }
                Log.d("RecyclerPageScrollHelper", "onScrollStateChanged idle", Boolean.valueOf(z));
            } catch (Exception e) {
                Log.e((CharSequence) "RecyclerPageScrollHelper", "onScrollStateChanged failed e=", e.getMessage());
            }
        }
    }

    public void onScrolled(RecyclerView recyclerView, int i2, int i7) {
        this.mScrollHappened = true;
        updateScrollValues();
        ScrollValues scrollValues = this.mScrollValues;
        int i8 = scrollValues.mPosition;
        if (i8 == -1) {
            i8 = 0;
        }
        dispatchState(i8, scrollValues.mOffset, scrollValues.mOffsetPx);
    }

    public void setPageScrollListener(PageScrollListener pageScrollListener) {
        this.mScrollListener = pageScrollListener;
    }
}
