package com.samsung.android.gallery.app.ui.list.pictures;

import N2.j;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.module.dataset.tables.SpanInfo;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpannableGridLayoutManager extends PicturesLayoutManager {
    private static final Object mLock = new Object();
    private int[] mCellBorders;
    /* access modifiers changed from: private */
    public int mCellHeight;
    private SparseArray<GridCell> mCells;
    private int mColumnCount;
    private boolean mDataUpdated = false;
    private int mFirstVisiblePosition;
    private int mFirstVisibleRow;
    /* access modifiers changed from: private */
    public int mHeaderHeight = -1;
    private boolean mIsSpannable;
    private final Rect mItemDecorationInsets = new Rect();
    private int mLastVisiblePosition;
    private int mLastVisibleRow;
    private int mMinimumFirstRow;
    private int mPendingScrollOffset = -1;
    private int mPendingScrollPosition = -1;
    private ArrayList<RowInfo> mRows;
    private int mScrollOffset;
    private boolean mScrollPending;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class GridCell {
        final int mColumn;
        final int mColumnSpan;
        final int mRow;
        final int mRowSpan;
        final int mViewType;
        final boolean misResized;

        public GridCell(int i2, int i7, int i8, int i10, int i11, boolean z) {
            this.mRow = i2;
            this.mRowSpan = i7;
            this.mColumn = i8;
            this.mColumnSpan = i10;
            this.mViewType = i11;
            this.misResized = z;
        }

        public boolean isHeader() {
            return ViewHolderValue.isHeader(this.mViewType);
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public class RowInfo {
        int mFirstChildPosition;
        int mIndex;
        boolean mIsHeader;

        public RowInfo(int i2, int i7, boolean z) {
            this.mIndex = i2;
            this.mFirstChildPosition = i7;
            this.mIsHeader = z;
        }

        public int getFirstChildInRow() {
            return this.mFirstChildPosition;
        }

        public int getRowHeight() {
            boolean z = this.mIsHeader;
            SpannableGridLayoutManager spannableGridLayoutManager = SpannableGridLayoutManager.this;
            if (z) {
                return spannableGridLayoutManager.mHeaderHeight;
            }
            return spannableGridLayoutManager.mCellHeight;
        }
    }

    public SpannableGridLayoutManager(Context context, int i2) {
        super(context, i2);
        this.mColumnCount = i2;
        setInitialPrefetchItemCount(0);
        setItemPrefetchEnabled(false);
        recalculate();
    }

    private void calculateCellBorders() {
        int i2;
        int i7 = this.mColumnCount;
        this.mCellBorders = new int[(i7 + 1)];
        int hintWidthSpace = getHintWidthSpace(i7);
        int paddingLeft = getPaddingLeft();
        int i8 = 0;
        this.mCellBorders[0] = paddingLeft;
        int i10 = this.mColumnCount;
        int i11 = hintWidthSpace / i10;
        int i12 = hintWidthSpace % i10;
        int i13 = 1;
        while (true) {
            int i14 = this.mColumnCount;
            if (i13 <= i14) {
                i8 += i12;
                if (i8 <= 0 || i14 - i8 >= i12) {
                    i2 = i11;
                } else {
                    i2 = i11 + 1;
                    i8 -= i14;
                }
                paddingLeft += i2;
                this.mCellBorders[i13] = paddingLeft;
                i13++;
            } else {
                return;
            }
        }
    }

    private void calculateCellPositions(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mDataUpdated) {
            int itemCount = state.getItemCount();
            SparseArray<GridCell> sparseArray = new SparseArray<>(itemCount);
            ArrayList<RowInfo> arrayList = new ArrayList<>();
            for (int i2 = 0; i2 < itemCount; i2++) {
                SpanInfo spanInfoForPosition = getSpanInfoForPosition(i2, recycler);
                if (spanInfoForPosition != null) {
                    sparseArray.put(i2, new GridCell(spanInfoForPosition.getRow(), spanInfoForPosition.getRowSpan(), spanInfoForPosition.getColumn(), spanInfoForPosition.getColumnSpan(), spanInfoForPosition.getViewType(), spanInfoForPosition.isResized()));
                }
            }
            int i7 = 0;
            while (true) {
                boolean z = true;
                if (i7 < this.mListAdapter.getRowCount(this.mColumnCount)) {
                    Integer[] hintRowInfo = this.mListAdapter.getHintRowInfo(i7, this.mColumnCount);
                    int intValue = hintRowInfo[0].intValue() + (hasHeader() ? 1 : 0);
                    if (hintRowInfo[1].intValue() <= 0) {
                        z = false;
                    }
                    arrayList.add(new RowInfo(i7, intValue, z));
                    i7++;
                } else {
                    this.mDataUpdated = false;
                    synchronized (mLock) {
                        try {
                            this.mRows = arrayList;
                            this.mCells = sparseArray;
                            this.mMinimumFirstRow = getMinimumVisibleRowAndOffset(itemCount - 1)[0];
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                    return;
                }
            }
        }
    }

    private void calculateWindowSize() {
        this.mCellHeight = (int) Math.floor(((double) getHintWidthSpace(this.mColumnCount)) / ((double) this.mColumnCount));
        calculateCellBorders();
    }

    private int getCompensatedRowIndex(int i2) {
        if (i2 >= getRowCount()) {
            return getRowCount() - 1;
        }
        return i2;
    }

    private int getEndRowIndex(int i2) {
        if (isInvalidPosition(i2)) {
            return getLastRow();
        }
        return (this.mCells.get(i2).mRow + this.mCells.get(i2).mRowSpan) - 1;
    }

    private int getFirstChildInRow(int i2) {
        ArrayList<RowInfo> arrayList = this.mRows;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.get(getCompensatedRowIndex(i2)).getFirstChildInRow();
    }

    private int getLastPositionInSpannedRow(int i2, RecyclerView.State state) {
        int itemCount;
        int nextSpannedRow = getNextSpannedRow(i2);
        if (nextSpannedRow < getRowCount()) {
            itemCount = getFirstChildInRow(nextSpannedRow);
        } else {
            itemCount = state.getItemCount();
        }
        return itemCount - 1;
    }

    private int getLastRow() {
        ArrayList<RowInfo> arrayList = this.mRows;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size() - 1;
    }

    private int[] getMinimumVisibleRowAndOffset(int i2) {
        int i7;
        GridCell gridCell = this.mCells.get(i2);
        int height = getHeight();
        int startRowIndex = getStartRowIndex(i2);
        int i8 = 0;
        if (gridCell == null) {
            if (startRowIndex == 0) {
                height = 0;
            }
            return new int[]{startRowIndex, height};
        }
        if (gridCell.isHeader()) {
            i7 = this.mHeaderHeight;
        } else {
            i7 = gridCell.mRowSpan * this.mCellHeight;
        }
        int i10 = height - i7;
        while (startRowIndex > 0 && i10 > 0) {
            startRowIndex--;
            i10 -= getRowHeight(startRowIndex);
            if (i10 <= 0) {
                int firstChildInRow = getFirstChildInRow(startRowIndex);
                while (startRowIndex > 0 && getFirstChildInRow(startRowIndex - 1) == firstChildInRow) {
                    startRowIndex--;
                    i10 -= getRowHeight(startRowIndex);
                }
            }
        }
        if (startRowIndex != 0) {
            i8 = i10;
        }
        return new int[]{startRowIndex, i8};
    }

    private int getNextSpannedRow(int i2) {
        int firstChildInRow = getFirstChildInRow(i2);
        do {
            i2++;
            if (i2 >= getRowCount() || getFirstChildInRow(i2) != firstChildInRow) {
                return i2;
            }
            i2++;
            break;
        } while (getFirstChildInRow(i2) != firstChildInRow);
        return i2;
    }

    private SpanInfo getSpanInfoForPosition(int i2, RecyclerView.Recycler recycler) {
        int convertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(i2);
        if (convertPreLayoutPositionToPostLayout != -1) {
            return this.mListAdapter.getHintSpanInfo(convertPreLayoutPositionToPostLayout, this.mColumnCount);
        }
        return getSpanInfoFromAttachedView(i2);
    }

    private SpanInfo getSpanInfoFromAttachedView(int i2) {
        int i7 = 0;
        while (i7 < getChildCount()) {
            View childAt = getChildAt(i7);
            if (childAt == null || i2 != getPosition(childAt)) {
                i7++;
            } else {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                return new SpanInfo(layoutParams.mRowSpan, layoutParams.mColumnSpan, getViewType(layoutParams.mIsHeader), layoutParams.mIsResized);
            }
        }
        return SpanInfo.SINGLE_CELL;
    }

    private int getStartRowIndex(int i2) {
        if (isInvalidPosition(i2)) {
            return getLastRow();
        }
        return this.mCells.get(i2).mRow;
    }

    private int getTotalHeightAbove(int i2) {
        int i7;
        if (i2 > 0) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        return ((i2 - i7) * this.mCellHeight) + (i7 * this.mHeaderHeight);
    }

    private int getViewType(boolean z) {
        if (z) {
            return -3;
        }
        return 0;
    }

    private void handlePendingScroll() {
        int i2 = this.mPendingScrollPosition;
        if (i2 != -1) {
            int i7 = 0;
            if (this.mPendingScrollOffset != -1) {
                int startRowIndex = getStartRowIndex(i2);
                int i8 = this.mPendingScrollOffset;
                while (startRowIndex > 0 && i8 > 0) {
                    startRowIndex--;
                    i8 -= getRowHeight(startRowIndex);
                    if (i8 <= 0) {
                        int firstChildInRow = getFirstChildInRow(startRowIndex);
                        while (startRowIndex > 0 && getFirstChildInRow(startRowIndex - 1) == firstChildInRow) {
                            startRowIndex--;
                            i8 -= getRowHeight(startRowIndex);
                        }
                    }
                }
                this.mFirstVisibleRow = startRowIndex;
                if (startRowIndex != 0) {
                    i7 = i8;
                }
                this.mScrollOffset = i7;
            } else if (i2 < this.mFirstVisiblePosition) {
                this.mFirstVisibleRow = getStartRowIndex(i2);
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

    private boolean isInvalidPosition(int i2) {
        SparseArray<GridCell> sparseArray = this.mCells;
        if (sparseArray == null || sparseArray.get(i2) == null || i2 >= this.mCells.size()) {
            return true;
        }
        return false;
    }

    private int layoutRow(int i2, int i7, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i8;
        boolean z;
        int i10;
        int i11;
        int i12 = i2;
        int firstChildInRow = getFirstChildInRow(i2);
        int lastPositionInSpannedRow = getLastPositionInSpannedRow(i12, state);
        if (i12 < this.mFirstVisibleRow) {
            i8 = 0;
        } else {
            i8 = getChildCount();
        }
        int i13 = i8;
        int i14 = firstChildInRow;
        boolean z3 = false;
        while (true) {
            if (i14 > lastPositionInSpannedRow) {
                z = z3;
                break;
            }
            z = z3;
            View viewForPosition = recycler.getViewForPosition(i14);
            GridCell gridCell = this.mCells.get(i14);
            if (gridCell == null) {
                break;
            }
            LayoutParams layoutParams = (LayoutParams) viewForPosition.getLayoutParams();
            layoutParams.mColumnSpan = gridCell.mColumnSpan;
            layoutParams.mRowSpan = gridCell.mRowSpan;
            layoutParams.mIsHeader = gridCell.isHeader();
            layoutParams.mIsResized = gridCell.misResized;
            boolean isItemRemoved = z | layoutParams.isItemRemoved();
            addView(viewForPosition, i13);
            int[] iArr = this.mCellBorders;
            int i15 = gridCell.mColumn;
            int childMeasureSpec = RecyclerView.LayoutManager.getChildMeasureSpec(iArr[gridCell.mColumnSpan + i15] - iArr[i15], 1073741824, 0, layoutParams.width, false);
            if (gridCell.isHeader()) {
                i10 = RecyclerView.LayoutManager.getChildMeasureSpec(gridCell.mRowSpan * this.mCellHeight, 0, 0, layoutParams.height, true);
            } else {
                i10 = RecyclerView.LayoutManager.getChildMeasureSpec(gridCell.mRowSpan * this.mCellHeight, 1073741824, 0, layoutParams.height, true);
            }
            measureChildWithDecorationsAndMargin(viewForPosition, childMeasureSpec, i10);
            int decoratedMeasuredHeight = getDecoratedMeasuredHeight(viewForPosition);
            int decoratedMeasuredWidth = getDecoratedMeasuredWidth(viewForPosition);
            if (gridCell.isHeader() && i14 == 0) {
                this.mHeaderHeight = decoratedMeasuredHeight;
            }
            int totalHeightAbove = getTotalHeightAbove(gridCell.mRow);
            if (isLayoutRTL()) {
                i11 = ((getHintWidthSpace(this.mColumnCount) - decoratedMeasuredWidth) - this.mCellBorders[gridCell.mColumn]) - layoutParams.leftMargin;
            } else {
                i11 = layoutParams.leftMargin + this.mCellBorders[gridCell.mColumn];
            }
            int i16 = layoutParams.topMargin + i7 + totalHeightAbove;
            layoutDecorated(viewForPosition, i11, i16, i11 + decoratedMeasuredWidth, i16 + decoratedMeasuredHeight);
            i14++;
            i13++;
            int i17 = i2;
            z3 = isItemRemoved;
        }
        if (firstChildInRow < this.mFirstVisiblePosition) {
            this.mFirstVisiblePosition = firstChildInRow;
            this.mFirstVisibleRow = getStartRowIndex(firstChildInRow);
        }
        if (lastPositionInSpannedRow > this.mLastVisiblePosition) {
            this.mLastVisiblePosition = lastPositionInSpannedRow;
            this.mLastVisibleRow = getEndRowIndex(lastPositionInSpannedRow);
        }
        if (z) {
            return 0;
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
        int firstChildInRow = getFirstChildInRow(i2);
        int lastPositionInSpannedRow = getLastPositionInSpannedRow(i2, state);
        for (int i7 = lastPositionInSpannedRow; i7 >= firstChildInRow; i7--) {
            View childAt = getChildAt(i7 - this.mFirstVisiblePosition);
            if (childAt != null) {
                removeAndRecycleView(childAt, recycler);
            }
        }
        if (i2 == this.mFirstVisibleRow) {
            int i8 = lastPositionInSpannedRow + 1;
            this.mFirstVisiblePosition = i8;
            this.mFirstVisibleRow = getStartRowIndex(i8);
        }
        if (i2 == this.mLastVisibleRow) {
            int i10 = firstChildInRow - 1;
            this.mLastVisiblePosition = i10;
            this.mLastVisibleRow = getEndRowIndex(i10);
        }
    }

    private void resetVisibleItemTracking() {
        int i2 = this.mFirstVisibleRow;
        int i7 = this.mMinimumFirstRow;
        if (i2 > i7) {
            this.mFirstVisibleRow = i7;
        }
        int firstChildInRow = getFirstChildInRow(this.mFirstVisibleRow);
        this.mFirstVisiblePosition = firstChildInRow;
        this.mLastVisibleRow = this.mFirstVisibleRow;
        this.mLastVisiblePosition = firstChildInRow;
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
            View childAt2 = getChildAt(getLastPositionInSpannedRow(this.mFirstVisibleRow, state) - this.mFirstVisiblePosition);
            if (childAt2 == null) {
                break;
            } else if (getDecoratedBottom(childAt2) + i10 + i8 < 0) {
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
            View childAt = getChildAt(getFirstChildInRow(this.mLastVisibleRow) - this.mFirstVisiblePosition);
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

    public boolean canScrollVertically() {
        if ((this.mDataUpdated || !this.mIsSpannable) && !super.canScrollVertically()) {
            return false;
        }
        return true;
    }

    public boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public int computeVerticalScrollExtent(RecyclerView.State state) {
        if (this.mIsSpannable) {
            return getHeight();
        }
        return super.computeVerticalScrollExtent(state);
    }

    public int computeVerticalScrollOffset(RecyclerView.State state) {
        View childAt;
        if (!this.mIsSpannable) {
            return super.computeVerticalScrollOffset(state);
        }
        if (getChildCount() == 0 || (childAt = getChildAt(0)) == null) {
            return 0;
        }
        return (getPaddingTop() + getTotalHeightAbove(this.mFirstVisibleRow)) - getDecoratedTop(childAt);
    }

    public int computeVerticalScrollRange(RecyclerView.State state) {
        if (!this.mIsSpannable) {
            return super.computeVerticalScrollRange(state);
        }
        PicturesViewAdapter picturesViewAdapter = this.mListAdapter;
        if (picturesViewAdapter != null) {
            picturesViewAdapter.getMaxScroll();
        }
        int rowCount = ((getRowCount() - 1) * this.mCellHeight) + this.mHeaderHeight;
        return getPaddingBottom() + getPaddingTop() + rowCount;
    }

    public View findViewByPosition(int i2) {
        int i7;
        if (!this.mIsSpannable || i2 < (i7 = this.mFirstVisiblePosition) || i2 > this.mLastVisiblePosition) {
            return super.findViewByPosition(i2);
        }
        return getChildAt(i2 - i7);
    }

    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    public RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    public int getFirstVisibleRowIndex() {
        return this.mFirstVisibleRow;
    }

    public int getItemHeight(int i2) {
        if (isInvalidPosition(i2)) {
            return -1;
        }
        return getRowHeight(getStartRowIndex(i2));
    }

    public int getRowCount() {
        ArrayList<RowInfo> arrayList = this.mRows;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public int getRowHeight(int i2) {
        ArrayList<RowInfo> arrayList = this.mRows;
        if (arrayList == null || i2 < 0 || i2 >= arrayList.size()) {
            return 0;
        }
        return this.mRows.get(i2).getRowHeight();
    }

    public boolean isAutoMeasureEnabled() {
        return this.mIsSpannable;
    }

    public boolean isSpannable() {
        return this.mIsSpannable;
    }

    public void onAdapterChanged(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2) {
        if (this.mIsSpannable) {
            removeAllViews();
            recalculate();
        }
        super.onAdapterChanged(adapter, adapter2);
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
        if (!this.mIsSpannable) {
            try {
                super.onLayoutChildren(recycler, state);
            } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
                j.u(e, new StringBuilder("onLayoutChildren failed e="), "GalleryGridLayoutManager");
            }
        } else {
            calculateWindowSize();
            calculateCellPositions(recycler, state);
            int i2 = 0;
            if (state.getItemCount() == 0) {
                detachAndScrapAttachedViews(recycler);
                this.mFirstVisibleRow = 0;
                resetVisibleItemTracking();
                return;
            }
            handlePendingScroll();
            int totalHeightAbove = getTotalHeightAbove(this.mFirstVisibleRow);
            if (this.mScrollPending) {
                int i7 = this.mScrollOffset;
                this.mScrollPending = false;
                this.mScrollOffset = 0;
                i2 = i7;
            } else if (getChildCount() != 0) {
                i2 = getDecoratedTop(getChildAt(0));
            }
            int i8 = i2 - totalHeightAbove;
            resetVisibleItemTracking();
            detachAndScrapAttachedViews(recycler);
            if (totalHeightAbove != 0 || i2 >= (-getHeight()) * 2) {
                int i10 = this.mFirstVisibleRow;
                int height = getHeight() - i2;
                int itemCount = state.getItemCount() - 1;
                while (height > 0 && this.mLastVisiblePosition < itemCount) {
                    height -= layoutRow(i10, i8, recycler, state);
                    i10 = getNextSpannedRow(i10);
                }
            }
        }
    }

    public void scrollToPosition(int i2) {
        if (!this.mIsSpannable) {
            super.scrollToPosition(i2);
            return;
        }
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
        if (!this.mIsSpannable) {
            super.scrollToPositionWithOffset(i2, i7);
        } else if (i7 == 0) {
            scrollToPosition(i2);
        } else {
            this.mPendingScrollPosition = i2;
            this.mPendingScrollOffset = i7;
            requestLayout();
        }
    }

    public void scrollToRowWithOffset(int i2, int i7) {
        if (this.mIsSpannable) {
            int firstChildInRow = getFirstChildInRow(i2);
            while (i2 > 0 && getFirstChildInRow(i2 - 1) == firstChildInRow) {
                i2--;
                i7 -= getRowHeight(i2);
            }
            scrollToPositionWithOffset(firstChildInRow, i7);
            return;
        }
        super.scrollToPositionWithOffset(i2, i7);
    }

    public int scrollVerticallyBy(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        View childAt;
        int i7;
        if (!this.mIsSpannable) {
            return super.scrollVerticallyBy(i2, recycler, state);
        }
        if (getChildCount() == 0 || i2 == 0 || (childAt = getChildAt(0)) == null || this.mRows == null) {
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

    public void setSpanCount(int i2) {
        super.setSpanCount(i2);
        if (this.mIsSpannable) {
            this.mColumnCount = i2;
            recalculate();
        }
    }

    public void setSpannable(boolean z) {
        this.mIsSpannable = z;
    }

    public boolean supportsPredictiveItemAnimations() {
        if (this.mIsSpannable || super.supportsPredictiveItemAnimations()) {
            return true;
        }
        return false;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LayoutParams extends GridLayoutManager.LayoutParams {
        int mColumnSpan;
        boolean mIsHeader;
        boolean mIsResized = false;
        int mRowSpan;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(int i2, int i7) {
            super(i2, i7);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }
    }

    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    public void onItemsUpdated(RecyclerView recyclerView, int i2, int i7, Object obj) {
        super.onItemsUpdated(recyclerView, i2, i7, obj);
        recalculate();
    }
}
