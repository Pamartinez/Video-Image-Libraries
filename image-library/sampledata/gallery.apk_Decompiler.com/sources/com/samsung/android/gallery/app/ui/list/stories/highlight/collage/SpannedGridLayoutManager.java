package com.samsung.android.gallery.app.ui.list.stories.highlight.collage;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.samsung.android.gallery.support.utils.Log;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpannedGridLayoutManager extends RecyclerView.LayoutManager {
    private float cellAspectRatio;
    private int[] cellBorders;
    /* access modifiers changed from: private */
    public int cellHeight;
    private SparseArray<GridCell> cells;
    private int columns;
    private List<Integer> firstChildPositionForRow;
    private int firstVisiblePosition;
    /* access modifiers changed from: private */
    public int firstVisibleRow;
    private boolean forceClearOffsets;
    private boolean isRealRatioSpan;
    private final Rect itemDecorationInsets;
    private int lastVisiblePosition;
    private int lastVisibleRow;
    private GridSpanLookup spanLookup;
    private int totalRows;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class GridCell {
        final int column;
        final int columnSpan;
        final int row;
        final int rowSpan;

        public GridCell(int i2, int i7, int i8, int i10) {
            this.row = i2;
            this.rowSpan = i7;
            this.column = i8;
            this.columnSpan = i10;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public interface GridSpanLookup {
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LayoutParams extends RecyclerView.LayoutParams {
        int columnSpan;
        int rowSpan;

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

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SpanInfo {
        public static final SpanInfo SINGLE_CELL = new SpanInfo(1, 1);
        public int columnSpan;
        public int rowSpan;

        public SpanInfo(int i2, int i7) {
            this.columnSpan = i2;
            this.rowSpan = i7;
        }
    }

    private SpannedGridLayoutManager(int i2) {
        this.columns = 1;
        this.cellAspectRatio = 1.0f;
        this.itemDecorationInsets = new Rect();
        this.columns = Math.max(i2, 1);
    }

    private boolean almostRowFilled(int i2, int i7) {
        int i8;
        if (!this.isRealRatioSpan || (i8 = i2 - i7) <= 0 || Math.abs(i8) >= 5) {
            return false;
        }
        return true;
    }

    private void calculateCellBorders() {
        int i2;
        int i7 = 1;
        this.cellBorders = new int[(this.columns + 1)];
        int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
        int paddingLeft = getPaddingLeft();
        int i8 = 0;
        this.cellBorders[0] = paddingLeft;
        int i10 = this.columns;
        int i11 = width / i10;
        int i12 = width % i10;
        while (true) {
            int i13 = this.columns;
            if (i7 <= i13) {
                i8 += i12;
                if (i8 <= 0 || i13 - i8 >= i12) {
                    i2 = i11;
                } else {
                    i2 = i11 + 1;
                    i8 -= i13;
                }
                paddingLeft += i2;
                this.cellBorders[i7] = paddingLeft;
                i7++;
            } else {
                return;
            }
        }
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0065 A[EDGE_INSN: B:44:0x0065->B:19:0x0065 ?: BREAK  , SYNTHETIC] */
    private void calculateCellPositions(androidx.recyclerview.widget.RecyclerView.Recycler r17, androidx.recyclerview.widget.RecyclerView.State r18) {
        /*
            r16 = this;
            r0 = r16
            int r1 = r18.getItemCount()
            android.util.SparseArray r2 = new android.util.SparseArray
            r2.<init>(r1)
            r0.cells = r2
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r0.firstChildPositionForRow = r2
            r2 = 0
            r0.recordSpannedRowStartPosition(r2, r2)
            int r3 = r0.columns
            int[] r4 = new int[r3]
            r5 = r2
            r6 = r5
            r7 = r6
            r8 = r7
        L_0x0020:
            r9 = 1
            if (r5 >= r1) goto L_0x00af
            r10 = r17
            int r11 = r10.convertPreLayoutPositionToPostLayout(r5)
            r12 = -1
            if (r11 == r12) goto L_0x003b
            com.samsung.android.gallery.app.ui.list.stories.highlight.collage.SpannedGridLayoutManager$GridSpanLookup r12 = r0.spanLookup
            com.samsung.android.gallery.app.ui.list.stories.highlight.collage.b r12 = (com.samsung.android.gallery.app.ui.list.stories.highlight.collage.b) r12
            com.samsung.android.gallery.app.ui.list.stories.highlight.collage.CollageView r13 = r12.f2557a
            int r14 = r12.b
            float r12 = r12.f2558c
            com.samsung.android.gallery.app.ui.list.stories.highlight.collage.SpannedGridLayoutManager$SpanInfo r11 = r13.lambda$setSpanLookup$0(r14, r12, r11)
            goto L_0x003f
        L_0x003b:
            com.samsung.android.gallery.app.ui.list.stories.highlight.collage.SpannedGridLayoutManager$SpanInfo r11 = r0.getSpanInfoFromAttachedView(r5)
        L_0x003f:
            int r12 = r11.columnSpan
            int r13 = r0.columns
            if (r12 <= r13) goto L_0x0047
            r11.columnSpan = r13
        L_0x0047:
            int r12 = r11.columnSpan
            int r12 = r12 + r6
            if (r12 <= r13) goto L_0x0052
            int r7 = r7 + 1
            r0.recordSpannedRowStartPosition(r7, r5)
        L_0x0051:
            r6 = r2
        L_0x0052:
            r12 = r4[r6]
            if (r12 <= r7) goto L_0x0065
            int r6 = r6 + 1
            int r12 = r11.columnSpan
            int r12 = r12 + r6
            int r13 = r0.columns
            if (r12 <= r13) goto L_0x0052
            int r7 = r7 + 1
            r0.recordSpannedRowStartPosition(r7, r5)
            goto L_0x0051
        L_0x0065:
            int r12 = r11.rowSpan
            int r12 = r12 + r7
            int r8 = java.lang.Math.max(r12, r8)
            android.util.SparseArray<com.samsung.android.gallery.app.ui.list.stories.highlight.collage.SpannedGridLayoutManager$GridCell> r13 = r0.cells
            com.samsung.android.gallery.app.ui.list.stories.highlight.collage.SpannedGridLayoutManager$GridCell r14 = new com.samsung.android.gallery.app.ui.list.stories.highlight.collage.SpannedGridLayoutManager$GridCell
            int r15 = r11.rowSpan
            r18 = r2
            int r2 = r11.columnSpan
            r14.<init>(r7, r15, r6, r2)
            r13.put(r5, r14)
            r2 = r18
        L_0x007e:
            int r13 = r11.columnSpan
            if (r2 >= r13) goto L_0x0092
            int r13 = r6 + r2
            boolean r14 = r0.almostRowFilled(r8, r12)
            if (r14 == 0) goto L_0x008c
            r14 = r8
            goto L_0x008d
        L_0x008c:
            r14 = r12
        L_0x008d:
            r4[r13] = r14
            int r2 = r2 + 1
            goto L_0x007e
        L_0x0092:
            int r2 = r11.rowSpan
            if (r2 <= r9) goto L_0x00a6
            int r2 = r0.getFirstPositionInSpannedRow(r7)
        L_0x009a:
            int r12 = r11.rowSpan
            if (r9 >= r12) goto L_0x00a6
            int r12 = r7 + r9
            r0.recordSpannedRowStartPosition(r12, r2)
            int r9 = r9 + 1
            goto L_0x009a
        L_0x00a6:
            int r2 = r11.columnSpan
            int r6 = r6 + r2
            int r5 = r5 + 1
            r2 = r18
            goto L_0x0020
        L_0x00af:
            r18 = r2
            r1 = r4[r18]
            r0.totalRows = r1
        L_0x00b5:
            if (r9 >= r3) goto L_0x00c2
            r1 = r4[r9]
            int r2 = r0.totalRows
            if (r1 <= r2) goto L_0x00bf
            r0.totalRows = r1
        L_0x00bf:
            int r9 = r9 + 1
            goto L_0x00b5
        L_0x00c2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.stories.highlight.collage.SpannedGridLayoutManager.calculateCellPositions(androidx.recyclerview.widget.RecyclerView$Recycler, androidx.recyclerview.widget.RecyclerView$State):void");
    }

    private void calculateWindowSize() {
        this.cellHeight = Math.max((int) Math.floor((double) ((1.0f / this.cellAspectRatio) * ((float) ((int) Math.floor((double) (((float) ((getWidth() - getPaddingLeft()) - getPaddingRight())) / ((float) this.columns))))))), 1);
        calculateCellBorders();
    }

    private int getFirstPositionInSpannedRow(int i2) {
        return this.firstChildPositionForRow.get(i2).intValue();
    }

    private int getLastPositionInSpannedRow(int i2, RecyclerView.State state) {
        int itemCount;
        int nextSpannedRow = getNextSpannedRow(i2);
        if (nextSpannedRow != getSpannedRowCount()) {
            itemCount = getFirstPositionInSpannedRow(nextSpannedRow);
        } else {
            itemCount = state.getItemCount();
        }
        return itemCount - 1;
    }

    private int getMinimumFirstVisibleRow() {
        int ceil = ((int) Math.ceil((double) (((float) getHeight()) / ((float) this.cellHeight)))) + 1;
        int i2 = this.totalRows;
        if (i2 < ceil || ceil < 0) {
            return 0;
        }
        return getRowIndex(getFirstPositionInSpannedRow(i2 - ceil));
    }

    private int getNextSpannedRow(int i2) {
        int firstPositionInSpannedRow = getFirstPositionInSpannedRow(i2);
        do {
            i2++;
            if (i2 >= getSpannedRowCount() || getFirstPositionInSpannedRow(i2) != firstPositionInSpannedRow) {
                return i2;
            }
            i2++;
            break;
        } while (getFirstPositionInSpannedRow(i2) != firstPositionInSpannedRow);
        return i2;
    }

    /* access modifiers changed from: private */
    public int getRowIndex(int i2) {
        if (i2 >= this.cells.size() || this.cells.get(i2) == null) {
            return -1;
        }
        return this.cells.get(i2).row;
    }

    private SpanInfo getSpanInfoFromAttachedView(int i2) {
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            View childAt = getChildAt(i7);
            if (i2 == getPosition(childAt)) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                return new SpanInfo(layoutParams.columnSpan, layoutParams.rowSpan);
            }
        }
        return SpanInfo.SINGLE_CELL;
    }

    private int getSpannedRowCount() {
        return this.firstChildPositionForRow.size();
    }

    private int layoutRow(int i2, int i7, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i8;
        int i10 = i2;
        int firstPositionInSpannedRow = getFirstPositionInSpannedRow(i2);
        int lastPositionInSpannedRow = getLastPositionInSpannedRow(i10, state);
        if (i10 < this.firstVisibleRow) {
            i8 = 0;
        } else {
            i8 = getChildCount();
        }
        int i11 = i8;
        int i12 = firstPositionInSpannedRow;
        boolean z = false;
        while (i12 <= lastPositionInSpannedRow) {
            boolean z3 = z;
            View viewForPosition = recycler.getViewForPosition(i12);
            LayoutParams layoutParams = (LayoutParams) viewForPosition.getLayoutParams();
            boolean isItemRemoved = z3 | layoutParams.isItemRemoved();
            addView(viewForPosition, i11);
            GridCell gridCell = this.cells.get(i12);
            if (gridCell == null) {
                Log.w((CharSequence) "SpannedGridLayoutManager", "layoutRow failed because cell could not be found", Integer.valueOf(i12));
            } else {
                int[] iArr = this.cellBorders;
                int i13 = gridCell.column;
                measureChildWithDecorationsAndMargin(viewForPosition, RecyclerView.LayoutManager.getChildMeasureSpec(iArr[gridCell.columnSpan + i13] - iArr[i13], 1073741824, 0, layoutParams.width, false), RecyclerView.LayoutManager.getChildMeasureSpec(gridCell.rowSpan * this.cellHeight, 1073741824, 0, layoutParams.height, true));
                int i14 = this.cellBorders[gridCell.column] + layoutParams.leftMargin;
                int i15 = (gridCell.row * this.cellHeight) + i7 + layoutParams.topMargin;
                layoutDecorated(viewForPosition, i14, i15, getDecoratedMeasuredWidth(viewForPosition) + i14, getDecoratedMeasuredHeight(viewForPosition) + i15);
                layoutParams.columnSpan = gridCell.columnSpan;
                layoutParams.rowSpan = gridCell.rowSpan;
            }
            i12++;
            i11++;
            z = isItemRemoved;
        }
        boolean z7 = z;
        if (firstPositionInSpannedRow < this.firstVisiblePosition) {
            this.firstVisiblePosition = firstPositionInSpannedRow;
            this.firstVisibleRow = getRowIndex(firstPositionInSpannedRow);
        }
        if (lastPositionInSpannedRow > this.lastVisiblePosition) {
            this.lastVisiblePosition = lastPositionInSpannedRow;
            this.lastVisibleRow = getRowIndex(lastPositionInSpannedRow);
        }
        if (z7) {
            return 0;
        }
        GridCell gridCell2 = this.cells.get(firstPositionInSpannedRow);
        GridCell gridCell3 = this.cells.get(lastPositionInSpannedRow);
        if (gridCell3 == null || gridCell2 == null) {
            return 0;
        }
        return ((gridCell3.row + gridCell3.rowSpan) - gridCell2.row) * this.cellHeight;
    }

    private void measureChildWithDecorationsAndMargin(View view, int i2, int i7) {
        calculateItemDecorationsForChild(view, this.itemDecorationInsets);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        int i8 = layoutParams.leftMargin;
        Rect rect = this.itemDecorationInsets;
        int updateSpecWithExtra = updateSpecWithExtra(i2, i8 + rect.left, layoutParams.rightMargin + rect.right);
        int i10 = layoutParams.topMargin;
        Rect rect2 = this.itemDecorationInsets;
        view.measure(updateSpecWithExtra, updateSpecWithExtra(i7, i10 + rect2.top, layoutParams.bottomMargin + rect2.bottom));
    }

    private void recordSpannedRowStartPosition(int i2, int i7) {
        if (getSpannedRowCount() < i2 + 1) {
            this.firstChildPositionForRow.add(Integer.valueOf(i7));
        }
    }

    private void recycleRow(int i2, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int firstPositionInSpannedRow = getFirstPositionInSpannedRow(i2);
        int lastPositionInSpannedRow = getLastPositionInSpannedRow(i2, state);
        for (int i7 = lastPositionInSpannedRow; i7 >= firstPositionInSpannedRow; i7--) {
            removeAndRecycleViewAt(i7 - this.firstVisiblePosition, recycler);
        }
        if (i2 == this.firstVisibleRow) {
            int i8 = lastPositionInSpannedRow + 1;
            this.firstVisiblePosition = i8;
            this.firstVisibleRow = getRowIndex(i8);
        }
        if (i2 == this.lastVisibleRow) {
            int i10 = firstPositionInSpannedRow - 1;
            this.lastVisiblePosition = i10;
            this.lastVisibleRow = getRowIndex(i10);
        }
    }

    private void reset() {
        this.cells = null;
        this.firstChildPositionForRow = null;
        this.firstVisiblePosition = 0;
        this.firstVisibleRow = 0;
        this.lastVisiblePosition = 0;
        this.lastVisibleRow = 0;
        this.cellHeight = 0;
        this.forceClearOffsets = false;
    }

    private void resetVisibleItemTracking() {
        int minimumFirstVisibleRow = getMinimumFirstVisibleRow();
        if (this.firstVisibleRow > minimumFirstVisibleRow) {
            this.firstVisibleRow = minimumFirstVisibleRow;
        }
        int firstPositionInSpannedRow = getFirstPositionInSpannedRow(this.firstVisibleRow);
        this.firstVisiblePosition = firstPositionInSpannedRow;
        this.lastVisibleRow = this.firstVisibleRow;
        this.lastVisiblePosition = firstPositionInSpannedRow;
    }

    private int updateSpecWithExtra(int i2, int i7, int i8) {
        int mode;
        if ((i7 != 0 || i8 != 0) && ((mode = View.MeasureSpec.getMode(i2)) == Integer.MIN_VALUE || mode == 1073741824)) {
            return View.MeasureSpec.makeMeasureSpec((View.MeasureSpec.getSize(i2) - i7) - i8, mode);
        }
        return i2;
    }

    public boolean canScrollVertically() {
        return true;
    }

    public boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public int computeVerticalScrollExtent(RecyclerView.State state) {
        return getHeight();
    }

    public int computeVerticalScrollOffset(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return ((this.firstVisibleRow * this.cellHeight) + getPaddingTop()) - getDecoratedTop(getChildAt(0));
    }

    public int computeVerticalScrollRange(RecyclerView.State state) {
        int spannedRowCount = getSpannedRowCount() * this.cellHeight;
        return getPaddingBottom() + getPaddingTop() + spannedRowCount;
    }

    public View findViewByPosition(int i2) {
        int i7 = this.firstVisiblePosition;
        if (i2 < i7 || i2 > this.lastVisiblePosition) {
            return null;
        }
        return getChildAt(i2 - i7);
    }

    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    public RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    public boolean isAutoMeasureEnabled() {
        return true;
    }

    public void onAdapterChanged(RecyclerView.Adapter adapter, RecyclerView.Adapter adapter2) {
        removeAllViews();
        reset();
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        calculateWindowSize();
        calculateCellPositions(recycler, state);
        int i2 = 0;
        if (state.getItemCount() == 0) {
            detachAndScrapAttachedViews(recycler);
            this.firstVisibleRow = 0;
            resetVisibleItemTracking();
            return;
        }
        int paddingTop = getPaddingTop();
        if (this.forceClearOffsets) {
            paddingTop = -(this.firstVisibleRow * this.cellHeight);
            this.forceClearOffsets = false;
        } else if (getChildCount() != 0) {
            i2 = getDecoratedTop(getChildAt(0));
            paddingTop = i2 - (this.firstVisibleRow * this.cellHeight);
            resetVisibleItemTracking();
        }
        detachAndScrapAttachedViews(recycler);
        int i7 = this.firstVisibleRow;
        int height = getHeight() - i2;
        int itemCount = state.getItemCount() - 1;
        while (height > 0 && this.lastVisiblePosition < itemCount) {
            height -= layoutRow(i7, paddingTop, recycler, state);
            i7 = getNextSpannedRow(i7);
        }
        layoutDisappearingViews(recycler, state, paddingTop);
    }

    public void scrollToPosition(int i2) {
        if (i2 >= getItemCount()) {
            i2 = getItemCount() - 1;
        }
        this.firstVisibleRow = getRowIndex(i2);
        resetVisibleItemTracking();
        this.forceClearOffsets = true;
        removeAllViews();
        requestLayout();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0027, code lost:
        r1 = r5.firstVisibleRow;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int scrollVerticallyBy(int r6, androidx.recyclerview.widget.RecyclerView.Recycler r7, androidx.recyclerview.widget.RecyclerView.State r8) {
        /*
            r5 = this;
            int r0 = r5.getChildCount()
            r1 = 0
            if (r0 == 0) goto L_0x00b5
            if (r6 != 0) goto L_0x000b
            goto L_0x00b5
        L_0x000b:
            android.view.View r0 = r5.getChildAt(r1)
            int r0 = r5.getDecoratedTop(r0)
            if (r6 >= 0) goto L_0x0052
            int r1 = r5.firstVisibleRow
            if (r1 != 0) goto L_0x0023
            int r1 = r5.getPaddingTop()
            int r1 = r1 - r0
            int r1 = -r1
            int r6 = java.lang.Math.max(r6, r1)
        L_0x0023:
            int r1 = r0 - r6
            if (r1 < 0) goto L_0x0034
            int r1 = r5.firstVisibleRow
            int r2 = r1 + -1
            if (r2 < 0) goto L_0x0034
            int r3 = r5.cellHeight
            int r1 = r1 * r3
            int r0 = r0 - r1
            r5.layoutRow(r2, r0, r7, r8)
        L_0x0034:
            int r0 = r5.lastVisibleRow
            int r0 = r5.getFirstPositionInSpannedRow(r0)
            int r1 = r5.firstVisiblePosition
            int r0 = r0 - r1
            android.view.View r0 = r5.getChildAt(r0)
            int r0 = r5.getDecoratedTop(r0)
            int r0 = r0 - r6
            int r1 = r5.getHeight()
            if (r0 <= r1) goto L_0x00b0
            int r0 = r5.lastVisibleRow
            r5.recycleRow(r0, r7, r8)
            goto L_0x00b0
        L_0x0052:
            int r2 = r5.getChildCount()
            int r2 = r2 + -1
            android.view.View r2 = r5.getChildAt(r2)
            int r2 = r5.getDecoratedBottom(r2)
            int r3 = r5.lastVisiblePosition
            int r4 = r5.getItemCount()
            int r4 = r4 + -1
            if (r3 != r4) goto L_0x007d
            int r3 = r5.getHeight()
            int r3 = r2 - r3
            int r4 = r5.getPaddingBottom()
            int r4 = r4 + r3
            int r1 = java.lang.Math.max(r4, r1)
            int r6 = java.lang.Math.min(r6, r1)
        L_0x007d:
            int r2 = r2 - r6
            int r1 = r5.getHeight()
            if (r2 >= r1) goto L_0x0097
            int r1 = r5.lastVisibleRow
            int r1 = r1 + 1
            int r2 = r5.getSpannedRowCount()
            if (r1 >= r2) goto L_0x0097
            int r2 = r5.firstVisibleRow
            int r3 = r5.cellHeight
            int r2 = r2 * r3
            int r0 = r0 - r2
            r5.layoutRow(r1, r0, r7, r8)
        L_0x0097:
            int r0 = r5.firstVisibleRow
            int r0 = r5.getLastPositionInSpannedRow(r0, r8)
            int r1 = r5.firstVisiblePosition
            int r0 = r0 - r1
            android.view.View r0 = r5.getChildAt(r0)
            int r0 = r5.getDecoratedBottom(r0)
            int r0 = r0 - r6
            if (r0 >= 0) goto L_0x00b0
            int r0 = r5.firstVisibleRow
            r5.recycleRow(r0, r7, r8)
        L_0x00b0:
            int r7 = -r6
            r5.offsetChildrenVertical(r7)
            return r6
        L_0x00b5:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.app.ui.list.stories.highlight.collage.SpannedGridLayoutManager.scrollVerticallyBy(int, androidx.recyclerview.widget.RecyclerView$Recycler, androidx.recyclerview.widget.RecyclerView$State):int");
    }

    public void setSpanLookup(GridSpanLookup gridSpanLookup) {
        this.spanLookup = gridSpanLookup;
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i2) {
        if (i2 >= getItemCount()) {
            i2 = getItemCount() - 1;
        }
        AnonymousClass1 r22 = new LinearSmoothScroller(recyclerView.getContext()) {
            public PointF computeScrollVectorForPosition(int i2) {
                return new PointF(0.0f, (float) (SpannedGridLayoutManager.this.cellHeight * (SpannedGridLayoutManager.this.getRowIndex(i2) - SpannedGridLayoutManager.this.firstVisibleRow)));
            }
        };
        r22.setTargetPosition(i2);
        startSmoothScroll(r22);
    }

    public boolean supportsPredictiveItemAnimations() {
        return true;
    }

    public RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    public SpannedGridLayoutManager(int i2, boolean z) {
        this(i2);
        this.isRealRatioSpan = z;
    }

    private void layoutDisappearingViews(RecyclerView.Recycler recycler, RecyclerView.State state, int i2) {
    }
}
