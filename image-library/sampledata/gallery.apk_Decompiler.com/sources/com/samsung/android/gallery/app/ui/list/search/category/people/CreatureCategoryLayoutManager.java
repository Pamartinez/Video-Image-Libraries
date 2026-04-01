package com.samsung.android.gallery.app.ui.list.search.category.people;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import c0.C0086a;
import com.samsung.android.gallery.app.ui.list.search.category.people.top.TopPeopleLayout;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.listview.GalleryGridLayoutManager;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CreatureCategoryLayoutManager extends GalleryGridLayoutManager implements ICreatureLayoutManager {
    private static final Object mLock = new Object();
    private CreatureCategoryItemAdapter mAdapter;
    private int[] mCellBorders;
    private boolean mDataUpdated = false;
    private int mFirstVisiblePosition;
    private int mFirstVisibleRow;
    private int mFooterHeight = 0;
    private int mHeaderHeight = 0;
    private final Rect mItemDecorationInsets = new Rect();
    private int mItemHeight;
    private int mItemSize;
    private int mLastVisiblePosition;
    private int mLastVisibleRow;
    private int mMeItemSize;
    private int mMinimumFirstRow;
    private int mPendingScrollOffset = -1;
    private int mPendingScrollPosition = -1;
    private int mRowCounts;
    private int mScrollOffset;
    private boolean mScrollPending;
    private int mSideSpacing;
    private int mTop5ItemSpacing;
    private TopPeopleLayout mTopLayout;
    private int mTopPeopleCount;

    public CreatureCategoryLayoutManager(Context context, int i2) {
        super(context, i2);
        initDimen(context);
        setInitialPrefetchItemCount(0);
        setItemPrefetchEnabled(false);
        recalculate();
    }

    private void calculateCellBorders() {
        int i2;
        this.mCellBorders = new int[(getSpanCount() + 1)];
        int hintWidthSpace = getHintWidthSpace(getSpanCount());
        int paddingLeft = getPaddingLeft();
        int i7 = 0;
        this.mCellBorders[0] = paddingLeft;
        int spanCount = hintWidthSpace / getSpanCount();
        int spanCount2 = hintWidthSpace % getSpanCount();
        for (int i8 = 1; i8 <= getSpanCount(); i8++) {
            i7 += spanCount2;
            if (i7 <= 0 || getSpanCount() - i7 >= spanCount2) {
                i2 = spanCount;
            } else {
                i2 = spanCount + 1;
                i7 -= getSpanCount();
            }
            paddingLeft += i2;
            this.mCellBorders[i8] = paddingLeft;
        }
    }

    private void calculateWindowSize() {
        int floor = (int) Math.floor(((double) getHintWidthSpace(getSpanCount())) / ((double) getSpanCount()));
        this.mItemSize = floor;
        if (this.mItemHeight == 0) {
            this.mItemHeight = floor;
        }
        this.mMeItemSize = (int) (((float) floor) * 1.3f);
        calculateCellBorders();
    }

    private int getFirstPositionInRow(int i2) {
        if (hasFooter() && i2 == getRowCount() - 1) {
            return getItemCount() - 1;
        }
        if (hasTop5()) {
            if (i2 > 1) {
                return (getSpanCount() * (i2 - 2)) + this.mTopPeopleCount + 1;
            }
        } else if (i2 > 0) {
            return (getSpanCount() * (i2 - 1)) + 1;
        }
        return i2;
    }

    private int getFooterHeight() {
        CreatureCategoryItemAdapter creatureCategoryItemAdapter = this.mAdapter;
        if (creatureCategoryItemAdapter == null) {
            return this.mFooterHeight;
        }
        return creatureCategoryItemAdapter.getFooterViewHeight();
    }

    private int getHeaderHeight() {
        CreatureCategoryItemAdapter creatureCategoryItemAdapter = this.mAdapter;
        if (creatureCategoryItemAdapter == null) {
            return this.mHeaderHeight;
        }
        return creatureCategoryItemAdapter.getHeaderViewHeight();
    }

    private int getHintWidthSpace(int i2) {
        return getWidth() - getHintHorizontalPadding(i2);
    }

    private int getItemWidth(int i2) {
        if (isMeOnTop5(i2)) {
            return this.mMeItemSize;
        }
        return getSpanSizeLookup().getSpanSize(i2) * this.mItemSize;
    }

    private int getLastPositionInRow(int i2, RecyclerView.State state) {
        int itemCount;
        if (i2 < getRowCount() - 1) {
            itemCount = getFirstPositionInRow(i2 + 1);
        } else {
            itemCount = state.getItemCount();
        }
        return itemCount - 1;
    }

    private int getMaxScroll() {
        CreatureCategoryItemAdapter creatureCategoryItemAdapter = this.mAdapter;
        if (creatureCategoryItemAdapter != null) {
            return creatureCategoryItemAdapter.getMaxScroll();
        }
        return getHeight();
    }

    private int[] getMinimumVisibleRowAndOffset(int i2) {
        int height = getHeight();
        int rowIndex = getRowIndex(i2);
        int rowHeight = height - getRowHeight(rowIndex);
        while (rowIndex > 0 && rowHeight > 0) {
            rowIndex--;
            rowHeight -= getRowHeight(rowIndex);
            if (rowHeight <= 0) {
                int firstPositionInRow = getFirstPositionInRow(rowIndex);
                while (rowIndex > 0 && getFirstPositionInRow(rowIndex - 1) == firstPositionInRow) {
                    rowIndex--;
                    rowHeight -= getRowHeight(rowIndex);
                }
            }
        }
        if (rowIndex == 0) {
            rowHeight = 0;
        }
        return new int[]{rowIndex, rowHeight};
    }

    private int getRowHeight(int i2) {
        TopPeopleLayout topPeopleLayout;
        if (hasFooter() && i2 == getRowCount() - 1) {
            return getFooterHeight();
        }
        if (i2 == 0) {
            return getHeaderHeight();
        }
        if (i2 == 1 && (topPeopleLayout = this.mTopLayout) != null) {
            return topPeopleLayout.getHeight();
        }
        return this.mItemHeight;
    }

    private int getRowIndex(int i2) {
        if (hasFooter() && i2 == getItemCount() - 1) {
            return getRowCount() - 1;
        }
        if (hasTop5()) {
            if (i2 == 0) {
                return 0;
            }
            int i7 = this.mTopPeopleCount;
            if (i2 <= i7) {
                return 1;
            }
            return (((i2 - i7) - 1) / getSpanCount()) + 2;
        } else if (i2 == 0) {
            return 0;
        } else {
            return ((i2 - 1) / getSpanCount()) + 1;
        }
    }

    private int getSubCreatureSize() {
        int i2 = this.mTopPeopleCount;
        int i7 = this.mItemSize;
        if (i2 > 5) {
            return (int) (((float) i7) * 0.9375f);
        }
        return i7;
    }

    private int getTotalHeightAbove(int i2) {
        if (hasFooter() && i2 == getRowCount()) {
            return getTotalHeightAbove(i2 - 1) + getFooterHeight();
        }
        if (i2 == 0) {
            return 0;
        }
        if (i2 == 1) {
            return getHeaderHeight();
        }
        if (i2 == 2 && this.mTopLayout != null) {
            return this.mTopLayout.getHeight() + getHeaderHeight();
        } else if (this.mTopLayout != null) {
            int headerHeight = getHeaderHeight();
            return ((i2 - 2) * this.mItemHeight) + this.mTopLayout.getHeight() + headerHeight;
        } else {
            return ((i2 - 1) * this.mItemHeight) + getHeaderHeight();
        }
    }

    private void handlePendingScroll() {
        int i2 = this.mPendingScrollPosition;
        if (i2 != -1) {
            int i7 = 0;
            if (this.mPendingScrollOffset != -1) {
                int rowIndex = getRowIndex(i2);
                int i8 = this.mPendingScrollOffset;
                while (rowIndex > 0 && i8 > 0) {
                    rowIndex--;
                    i8 -= getRowHeight(rowIndex);
                    if (i8 <= 0) {
                        int firstPositionInRow = getFirstPositionInRow(rowIndex);
                        while (rowIndex > 0 && getFirstPositionInRow(rowIndex - 1) == firstPositionInRow) {
                            rowIndex--;
                            i8 -= getRowHeight(rowIndex);
                        }
                    }
                }
                this.mFirstVisibleRow = rowIndex;
                if (rowIndex != 0) {
                    i7 = i8;
                }
                this.mScrollOffset = i7;
            } else if (i2 < this.mFirstVisiblePosition) {
                this.mFirstVisibleRow = getRowIndex(i2);
            } else {
                int[] minimumVisibleRowAndOffset = getMinimumVisibleRowAndOffset(i2);
                this.mFirstVisibleRow = minimumVisibleRowAndOffset[0];
                this.mScrollOffset = minimumVisibleRowAndOffset[1];
            }
            resetVisibleItemTracking();
            this.mScrollPending = true;
        }
        this.mPendingScrollOffset = -1;
        this.mPendingScrollPosition = -1;
    }

    private boolean hasFooter() {
        CreatureCategoryItemAdapter creatureCategoryItemAdapter = this.mAdapter;
        if (creatureCategoryItemAdapter == null || !creatureCategoryItemAdapter.hasFooter()) {
            return false;
        }
        return true;
    }

    private boolean hasTop5() {
        if (this.mTopPeopleCount > 0) {
            return true;
        }
        return false;
    }

    private void initRowInfo(RecyclerView.State state) {
        int i2;
        int i7;
        TopPeopleLayout topPeopleLayout;
        if (this.mDataUpdated) {
            int itemCount = state.getItemCount();
            int i8 = ((itemCount - this.mTopPeopleCount) - (hasFooter() ? 1 : 0)) - 1;
            int spanCount = i8 / getSpanCount();
            if (i8 % getSpanCount() == 0) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            int i10 = spanCount + i2;
            if (hasTop5()) {
                i7 = 2;
            } else {
                i7 = 1;
            }
            this.mRowCounts = i10 + i7 + (hasFooter() ? 1 : 0);
            if (hasTop5()) {
                topPeopleLayout = TopPeopleLayout.create(this.mTopPeopleCount, this.mMeItemSize, getSubCreatureSize(), this.mTop5ItemSpacing);
            } else {
                topPeopleLayout = null;
            }
            this.mTopLayout = topPeopleLayout;
            this.mDataUpdated = false;
            synchronized (mLock) {
                this.mMinimumFirstRow = getMinimumVisibleRowAndOffset(itemCount - 1)[0];
            }
        }
    }

    private boolean isFooter(int i2) {
        if (!hasFooter() || i2 != getItemCount() - 1) {
            return false;
        }
        return true;
    }

    private boolean isMeOnTop5(int i2) {
        if (!hasTop5() || i2 != 1) {
            return false;
        }
        return true;
    }

    private int layoutRow(int i2, int i7, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i8;
        boolean z;
        boolean z3;
        int i10;
        int i11;
        int i12;
        int i13 = i2;
        int firstPositionInRow = getFirstPositionInRow(i2);
        RecyclerView.State state2 = state;
        int lastPositionInRow = getLastPositionInRow(i13, state2);
        boolean z7 = false;
        if (i13 < this.mFirstVisibleRow) {
            i8 = 0;
        } else {
            i8 = getChildCount();
        }
        int i14 = firstPositionInRow;
        int i15 = 0;
        while (i14 <= lastPositionInRow) {
            try {
                View viewForPosition = recycler.getViewForPosition(i14);
                updateViewSize(viewForPosition, i14);
                addView(viewForPosition, i8);
                if (i14 == 0) {
                    z = true;
                } else {
                    z = z7;
                }
                if (!hasFooter() || i14 != getItemCount() - 1) {
                    z3 = z7;
                } else {
                    z3 = true;
                }
                GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) viewForPosition.getLayoutParams();
                int i16 = i8;
                measureChildWithDecorationsAndMargin(viewForPosition, RecyclerView.LayoutManager.getChildMeasureSpec(getItemWidth(i14), 1073741824, z7 ? 1 : 0, layoutParams.width, z7), RecyclerView.LayoutManager.getChildMeasureSpec(getItemHeight(i14), z7, z7, layoutParams.height, z7));
                int decoratedMeasuredHeight = getDecoratedMeasuredHeight(viewForPosition);
                int decoratedMeasuredWidth = getDecoratedMeasuredWidth(viewForPosition);
                if (z) {
                    this.mHeaderHeight = decoratedMeasuredHeight;
                } else if (z3) {
                    this.mFooterHeight = decoratedMeasuredHeight;
                } else if (this.mTopLayout == null || i13 > 1) {
                    this.mItemHeight = decoratedMeasuredHeight;
                }
                boolean isLayoutRTL = isLayoutRTL();
                int totalHeightAbove = getTotalHeightAbove(i2);
                TopPeopleLayout topPeopleLayout = this.mTopLayout;
                View view = viewForPosition;
                if (topPeopleLayout == null || i13 != 1) {
                    if (!z) {
                        if (!z3) {
                            if (isLayoutRTL) {
                                i12 = this.mCellBorders[(getSpanCount() - i15) - 1];
                            } else {
                                i12 = this.mCellBorders[i15];
                            }
                            i10 = i12 + layoutParams.leftMargin;
                        }
                    }
                    i10 = layoutParams.leftMargin;
                } else {
                    i10 = topPeopleLayout.getLeft(i14, decoratedMeasuredWidth, getWidth(), isLayoutRTL);
                }
                int i17 = i7 + totalHeightAbove + layoutParams.topMargin;
                TopPeopleLayout topPeopleLayout2 = this.mTopLayout;
                if (topPeopleLayout2 == null || i13 != 1) {
                    i11 = 0;
                } else {
                    i11 = topPeopleLayout2.getTop(i14, decoratedMeasuredHeight, this.mMeItemSize);
                }
                int i18 = i17 + i11;
                layoutDecorated(view, i10, i18, i10 + decoratedMeasuredWidth, i18 + decoratedMeasuredHeight);
                i14++;
                i8 = i16 + 1;
                i15++;
                z7 = false;
            } catch (Exception e) {
                Log.w((CharSequence) "GalleryGridLayoutManager", C0086a.i(firstPositionInRow, "layoutRow : "), Integer.valueOf(lastPositionInRow), Integer.valueOf(state2.getItemCount()), e.toString());
            }
        }
        if (firstPositionInRow < this.mFirstVisiblePosition) {
            this.mFirstVisiblePosition = firstPositionInRow;
            this.mFirstVisibleRow = getRowIndex(firstPositionInRow);
        }
        if (lastPositionInRow > this.mLastVisiblePosition) {
            this.mLastVisiblePosition = lastPositionInRow;
            this.mLastVisibleRow = getRowIndex(lastPositionInRow);
        }
        return getRowHeight(i2);
    }

    private void measureChildWithDecorationsAndMargin(View view, int i2, int i7) {
        calculateItemDecorationsForChild(view, this.mItemDecorationInsets);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        int i8 = layoutParams.leftMargin;
        Rect rect = this.mItemDecorationInsets;
        int updateSpecWithExtra = updateSpecWithExtra(i2, i8 + rect.left, layoutParams.rightMargin + rect.right);
        int i10 = layoutParams.topMargin;
        Rect rect2 = this.mItemDecorationInsets;
        view.measure(updateSpecWithExtra, updateSpecWithExtra(i7, i10 + rect2.top, layoutParams.bottomMargin + rect2.bottom));
    }

    private void recalculate() {
        this.mScrollPending = false;
        this.mDataUpdated = true;
    }

    private void recycleRow(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int firstPositionInRow = getFirstPositionInRow(i2);
        int lastPositionInRow = getLastPositionInRow(i2, state);
        for (int i7 = lastPositionInRow; i7 >= firstPositionInRow; i7--) {
            View childAt = getChildAt(i7 - this.mFirstVisiblePosition);
            if (childAt != null) {
                removeAndRecycleView(childAt, recycler);
            }
        }
        if (i2 == this.mFirstVisibleRow) {
            int i8 = lastPositionInRow + 1;
            this.mFirstVisiblePosition = i8;
            this.mFirstVisibleRow = getRowIndex(i8);
        }
        if (i2 == this.mLastVisibleRow) {
            int i10 = firstPositionInRow - 1;
            this.mLastVisiblePosition = i10;
            this.mLastVisibleRow = getRowIndex(i10);
        }
    }

    private void resetVisibleItemTracking() {
        int i2 = this.mFirstVisibleRow;
        int i7 = this.mMinimumFirstRow;
        if (i2 > i7) {
            this.mFirstVisibleRow = i7;
        }
        int firstPositionInRow = getFirstPositionInRow(this.mFirstVisibleRow);
        this.mFirstVisiblePosition = firstPositionInRow;
        this.mLastVisibleRow = this.mFirstVisibleRow;
        this.mLastVisiblePosition = firstPositionInRow;
    }

    private int scrollDown(int i2, RecyclerView.Recycler recycler, RecyclerView.State state, int i7) {
        View childAt = getChildAt(getChildCount() - 1);
        int i8 = 0;
        if (childAt == null) {
            return 0;
        }
        int decoratedBottom = getDecoratedBottom(childAt);
        if (this.mLastVisiblePosition == state.getItemCount() - 1) {
            return Math.min(i2, Math.max(getPaddingBottom() + (decoratedBottom - getHeight()), 0));
        }
        int i10 = decoratedBottom - i2;
        int totalHeightAbove = i7 - getTotalHeightAbove(this.mFirstVisibleRow);
        while (i10 + i8 < getHeight()) {
            int i11 = this.mLastVisibleRow + 1;
            if (i11 >= getRowCount()) {
                return (decoratedBottom + i8) - getHeight();
            }
            i8 += layoutRow(i11, totalHeightAbove, recycler, state);
            View childAt2 = getChildAt(getLastPositionInRow(this.mFirstVisibleRow, state) - this.mFirstVisiblePosition);
            if (childAt2 == null) {
                break;
            } else if (getDecoratedBottom(childAt2) + i8 < 0) {
                recycleRow(this.mFirstVisibleRow, recycler, state);
            }
        }
        return i2;
    }

    private int scrollUp(int i2, RecyclerView.Recycler recycler, RecyclerView.State state, int i7) {
        int totalHeightAbove = i7 - getTotalHeightAbove(this.mFirstVisibleRow);
        int max = Math.max(i2, totalHeightAbove);
        int i8 = i7 - max;
        while (i8 > 0) {
            int i10 = 1;
            int i11 = this.mFirstVisibleRow - 1;
            if (i11 < 0) {
                return max + i8;
            }
            int layoutRow = layoutRow(i11, totalHeightAbove, recycler, state);
            View childAt = getChildAt(getFirstPositionInRow(this.mLastVisibleRow) - this.mFirstVisiblePosition);
            if (childAt != null) {
                i10 = getDecoratedTop(childAt);
            }
            if (i10 + i8 > getHeight()) {
                recycleRow(this.mLastVisibleRow, recycler, state);
            }
            i8 -= layoutRow;
        }
        return max;
    }

    private int updateSpecWithExtra(int i2, int i7, int i8) {
        int mode;
        if ((i7 != 0 || i8 != 0) && ((mode = View.MeasureSpec.getMode(i2)) == Integer.MIN_VALUE || mode == 1073741824)) {
            return View.MeasureSpec.makeMeasureSpec((View.MeasureSpec.getSize(i2) - i7) - i8, mode);
        }
        return i2;
    }

    private void updateViewSize(View view, int i2) {
        int i7;
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (i2 == 0 || isFooter(i2)) {
            marginLayoutParams.width = getWidth();
            if (ViewUtils.isVisible(view)) {
                i7 = -2;
            } else {
                i7 = 0;
            }
            marginLayoutParams.height = i7;
        } else if (isMeOnTop5(i2)) {
            marginLayoutParams.width = this.mMeItemSize;
        } else if (i2 <= this.mTopPeopleCount) {
            marginLayoutParams.width = getSubCreatureSize();
        } else {
            marginLayoutParams.width = this.mItemSize;
        }
        view.setLayoutParams(marginLayoutParams);
    }

    public int computeVerticalScrollExtent(RecyclerView.State state) {
        return getHeight();
    }

    public int computeVerticalScrollOffset(RecyclerView.State state) {
        View childAt;
        if (getChildCount() == 0 || (childAt = getChildAt(0)) == null) {
            return 0;
        }
        return (getPaddingTop() + getTotalHeightAbove(this.mFirstVisibleRow)) - getDecoratedTop(childAt);
    }

    public int computeVerticalScrollRange(RecyclerView.State state) {
        CreatureCategoryItemAdapter creatureCategoryItemAdapter = this.mAdapter;
        if (creatureCategoryItemAdapter != null) {
            return creatureCategoryItemAdapter.getMaxScroll();
        }
        int totalHeightAbove = getTotalHeightAbove(getRowCount());
        return getPaddingBottom() + getPaddingTop() + totalHeightAbove;
    }

    public View findViewByPosition(int i2) {
        int i7 = this.mFirstVisiblePosition;
        if (i2 < i7 || i2 > this.mLastVisiblePosition) {
            return super.findViewByPosition(i2);
        }
        return getChildAt(i2 - i7);
    }

    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new GridLayoutManager.LayoutParams(-2, -2);
    }

    public int getHintHorizontalPadding(int i2) {
        return (getSpacing(i2) * 2) + getExtraStartPadding(i2);
    }

    public int getItemHeight(int i2) {
        if (isMeOnTop5(i2)) {
            return this.mMeItemSize;
        }
        return getRowHeight(getRowIndex(i2));
    }

    public int getRowCount() {
        return this.mRowCounts;
    }

    public int getSpacing(int i2) {
        return this.mSideSpacing;
    }

    public int getTopLayoutHeight() {
        TopPeopleLayout topPeopleLayout = this.mTopLayout;
        if (topPeopleLayout != null) {
            return topPeopleLayout.getHeight();
        }
        return 0;
    }

    public int getTopPeopleCount() {
        return this.mTopPeopleCount;
    }

    public void initDimen(Context context) {
        if (context != null) {
            this.mSideSpacing = context.getResources().getDimensionPixelOffset(R.dimen.search_category_people_list_side_spacing);
            this.mTop5ItemSpacing = context.getResources().getDimensionPixelOffset(R.dimen.search_category_people_top_5_item_spacing);
        }
    }

    public void onAdapterChangedInternal(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2) {
        this.mAdapter = (CreatureCategoryItemAdapter) adapter2;
    }

    public void onItemsAdded(RecyclerView recyclerView, int i2, int i7) {
        super.onItemsAdded(recyclerView, i2, i7);
        recalculate();
    }

    public void onItemsChanged(RecyclerView recyclerView) {
        super.onItemsChanged(recyclerView);
        recalculate();
    }

    public void onItemsMoved(RecyclerView recyclerView, int i2, int i7, int i8) {
        super.onItemsMoved(recyclerView, i2, i7, i8);
        recalculate();
    }

    public void onItemsRemoved(RecyclerView recyclerView, int i2, int i7) {
        super.onItemsRemoved(recyclerView, i2, i7);
        recalculate();
    }

    public void onItemsUpdated(RecyclerView recyclerView, int i2, int i7) {
        super.onItemsUpdated(recyclerView, i2, i7);
        recalculate();
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i2;
        calculateWindowSize();
        initRowInfo(state);
        if (state.getItemCount() == 0) {
            detachAndScrapAttachedViews(recycler);
            this.mFirstVisibleRow = 0;
            resetVisibleItemTracking();
            return;
        }
        handlePendingScroll();
        int totalHeightAbove = getTotalHeightAbove(this.mFirstVisibleRow);
        if (this.mScrollPending) {
            i2 = this.mScrollOffset;
            this.mScrollPending = false;
            this.mScrollOffset = 0;
        } else if (getChildAt(0) == null || getChildCount() == 0) {
            i2 = 0;
        } else {
            i2 = getDecoratedTop(getChildAt(0));
        }
        int max = Math.max(Math.min(0, getHeight() - getMaxScroll()), i2 - totalHeightAbove);
        resetVisibleItemTracking();
        detachAndScrapAttachedViews(recycler);
        if (totalHeightAbove != 0 || i2 >= (-getHeight()) * 2) {
            int i7 = this.mFirstVisibleRow;
            int height = getHeight() - i2;
            int itemCount = state.getItemCount() - 1;
            while (height > 0 && this.mLastVisiblePosition < itemCount) {
                height -= layoutRow(i7, max, recycler, state);
                i7++;
            }
        }
    }

    public void scrollToPosition(int i2) {
        if (i2 >= getItemCount()) {
            i2 = getItemCount() - 1;
        }
        int i7 = this.mFirstVisiblePosition;
        if (i2 < i7 || i2 > this.mLastVisiblePosition) {
            this.mPendingScrollPosition = i2;
            requestLayout();
            return;
        }
        View childAt = getChildAt(i2 - i7);
        if (childAt == null) {
            super.scrollToPosition(i2);
            return;
        }
        int decoratedTop = getDecoratedTop(childAt) - getPaddingTop();
        int decoratedBottom = getDecoratedBottom(childAt) - getHeight();
        if (decoratedTop < 0) {
            offsetChildrenVertical(-decoratedTop);
        } else if (decoratedBottom > 0) {
            offsetChildrenVertical(-decoratedBottom);
        }
    }

    public void scrollToPositionWithOffset(int i2, int i7) {
        if (i7 == 0) {
            scrollToPosition(i2);
            return;
        }
        this.mPendingScrollPosition = i2;
        this.mPendingScrollOffset = i7;
        requestLayout();
    }

    public int scrollVerticallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        View childAt;
        int i7;
        if (getChildCount() == 0 || i2 == 0 || (childAt = getChildAt(0)) == null) {
            return 0;
        }
        int decoratedTop = getDecoratedTop(childAt);
        if (i2 < 0) {
            i7 = scrollUp(i2, recycler, state, decoratedTop);
        } else {
            i7 = scrollDown(i2, recycler, state, decoratedTop);
        }
        offsetChildrenVertical(-i7);
        return i7;
    }

    public void setTop5Count(Integer num) {
        this.mTopPeopleCount = num.intValue();
        recalculate();
    }

    public void onItemsUpdated(RecyclerView recyclerView, int i2, int i7, Object obj) {
        super.onItemsUpdated(recyclerView, i2, i7, obj);
        recalculate();
    }
}
