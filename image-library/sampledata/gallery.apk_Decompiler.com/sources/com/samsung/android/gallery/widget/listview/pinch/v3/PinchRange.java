package com.samsung.android.gallery.widget.listview.pinch.v3;

import D6.a;
import c0.C0086a;
import com.samsung.android.gallery.module.dataset.tables.SpanInfo;
import com.samsung.android.gallery.support.exception.InternalException;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.data.AnimPositionCache;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.HashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PinchRange {
    private int mDataRowOffset;
    private int mDividerRowOffset;
    /* access modifiers changed from: private */
    public int mEndRow;
    /* access modifiers changed from: private */
    public int mFocusedRow;
    private final int mGridSize;
    private int mItemCount;
    private final AnimPositionCache mPositionCache;
    private final HashMap<Integer, RowInfo> mRowInfo;
    private int mStartOffset;
    /* access modifiers changed from: private */
    public int mStartPosition;
    /* access modifiers changed from: private */
    public int mStartRow;
    /* access modifiers changed from: private */
    public float mStartY;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Builder {
        private IFocused focused;
        private final PinchLayoutManager layoutManager;
        private final AnimPositionCache positionCache;
        private boolean shiftToTop;

        public Builder(PinchLayoutManager pinchLayoutManager, AnimPositionCache animPositionCache) {
            this.layoutManager = pinchLayoutManager;
            this.positionCache = animPositionCache;
        }

        private void calculateRange(PinchRange pinchRange) {
            float f;
            int i2;
            float d = pinchRange.mStartY;
            int gridSize = this.focused.getGridSize();
            int height = this.layoutManager.getHeight();
            int hintViewCount = this.positionCache.getHintViewCount(this.layoutManager, gridSize);
            int b = pinchRange.mStartPosition;
            int hintItemViewType = this.layoutManager.getHintItemViewType(b, gridSize);
            int c5 = pinchRange.mStartRow;
            while (true) {
                f = (float) height;
                i2 = (d > f ? 1 : (d == f ? 0 : -1));
                if (i2 < 0 && pinchRange.getEndPosition() < hintViewCount - 1) {
                    int hintNextRowIndex = this.layoutManager.getHintNextRowIndex(b, gridSize, hintViewCount);
                    int hintItemViewType2 = this.layoutManager.getHintItemViewType(hintNextRowIndex, gridSize);
                    d += (float) this.positionCache.getHintViewHeight(this.layoutManager, b, gridSize);
                    pinchRange.updateRowInfo(c5, b, (PinchRange.isSameRow(this.layoutManager, gridSize, hintNextRowIndex, b) ? 1 : 0) + (hintNextRowIndex - b), hintItemViewType);
                    b = hintNextRowIndex;
                    hintItemViewType = hintItemViewType2;
                    c5++;
                }
            }
            if (this.shiftToTop && i2 < 0) {
                calculateTopRange(pinchRange, f - d);
            }
            pinchRange.mEndRow = Math.max(0, c5 - 1);
            pinchRange.fillFirstRow();
        }

        private void calculateTopRange(PinchRange pinchRange, float f) {
            int b = pinchRange.mStartPosition;
            if (b == 0) {
                pinchRange.mStartY = 0.0f;
                return;
            }
            float d = pinchRange.mStartY + f;
            int gridSize = this.focused.getGridSize();
            int c5 = pinchRange.mStartRow - 1;
            int hintPrevRowIndex = this.layoutManager.getHintPrevRowIndex(b, gridSize);
            while (hintPrevRowIndex >= 0 && d > 0.0f) {
                int hintPrevRowIndex2 = this.layoutManager.getHintPrevRowIndex(hintPrevRowIndex, gridSize);
                if (PinchRange.isSameRow(this.layoutManager, gridSize, hintPrevRowIndex, hintPrevRowIndex2)) {
                    hintPrevRowIndex2 = -1;
                }
                d -= (float) this.positionCache.getHintViewHeight(this.layoutManager, hintPrevRowIndex, gridSize);
                int i2 = hintPrevRowIndex - hintPrevRowIndex2;
                pinchRange.updateRowInfo(c5, (hintPrevRowIndex - i2) + 1, i2, this.layoutManager.getHintItemViewType(hintPrevRowIndex, gridSize));
                hintPrevRowIndex = hintPrevRowIndex2;
                c5--;
            }
            pinchRange.mStartPosition = hintPrevRowIndex + 1;
            pinchRange.mStartRow = c5 + 1;
            pinchRange.mStartY = Math.min(0.0f, d);
        }

        private SpanInfo findFocusedSpanInfo() {
            SpanInfo hintSpanInfo = this.layoutManager.getHintSpanInfo(this.focused.getViewPosition(), this.focused.getGridSize());
            if (hintSpanInfo != null) {
                return hintSpanInfo;
            }
            throw new InternalException("Cannot find SpanInfo [" + this.focused.getViewPosition() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.focused.getGridSize() + "]");
        }

        private void findStartPosition(PinchRange pinchRange) {
            float scrollOffset = this.focused.getScrollOffset();
            int viewPosition = this.focused.getViewPosition();
            while (viewPosition > 0 && scrollOffset > 0.0f) {
                viewPosition = this.layoutManager.getHintPrevRowIndex(viewPosition, this.focused.getGridSize());
                scrollOffset -= (float) this.positionCache.getHintViewHeight(this.layoutManager, viewPosition, this.focused.getGridSize());
            }
            SpanInfo hintSpanInfo = this.layoutManager.getHintSpanInfo(viewPosition, this.focused.getGridSize());
            pinchRange.mStartPosition = Math.max(0, viewPosition - hintSpanInfo.getColumn());
            pinchRange.mStartRow = hintSpanInfo.getRow();
            pinchRange.mStartY = Math.min(0.0f, scrollOffset);
        }

        private void updateFocusedInfo(PinchRange pinchRange) {
            pinchRange.mFocusedRow = findFocusedSpanInfo().getRow();
        }

        public PinchRange build() {
            PinchRange pinchRange = new PinchRange(this.positionCache, this.focused.getGridSize(), 0);
            updateFocusedInfo(pinchRange);
            findStartPosition(pinchRange);
            calculateRange(pinchRange);
            return pinchRange;
        }

        public Builder refer(IFocused iFocused) {
            this.focused = iFocused;
            return this;
        }

        public Builder shiftToTop(boolean z) {
            this.shiftToTop = z;
            return this;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class RowInfo {
        /* access modifiers changed from: private */
        public final int columnCount;
        /* access modifiers changed from: private */
        public final int firstViewPosition;
        /* access modifiers changed from: private */
        public final int viewType;

        public /* synthetic */ RowInfo(int i2, int i7, int i8, int i10) {
            this(i2, i7, i8);
        }

        private RowInfo(int i2, int i7, int i8) {
            this.firstViewPosition = i2;
            this.columnCount = i7;
            this.viewType = i8;
        }
    }

    public /* synthetic */ PinchRange(AnimPositionCache animPositionCache, int i2, int i7) {
        this(animPositionCache, i2);
    }

    private void calculateExtraBottom(PinchLayoutManager pinchLayoutManager, int i2, boolean z) {
        int maxRelativeRow = getMaxRelativeRow(z);
        if (maxRelativeRow < i2) {
            int row = getRow(maxRelativeRow, z);
            int i7 = row + 1;
            RowInfo rowInfo = this.mRowInfo.get(Integer.valueOf(row));
            if (rowInfo != null) {
                if (z) {
                    calculateExtraBottomData(pinchLayoutManager, i2, maxRelativeRow, i7, rowInfo);
                } else {
                    calculateExtraBottomDividers(pinchLayoutManager, i2, maxRelativeRow, i7, rowInfo);
                }
            }
        }
    }

    private void calculateExtraBottomData(PinchLayoutManager pinchLayoutManager, int i2, int i7, int i8, RowInfo rowInfo) {
        int hintViewCount = this.mPositionCache.getHintViewCount(pinchLayoutManager, this.mGridSize);
        int hintNextRowIndex = pinchLayoutManager.getHintNextRowIndex(rowInfo.firstViewPosition, this.mGridSize, hintViewCount);
        int hintItemViewType = pinchLayoutManager.getHintItemViewType(hintNextRowIndex, this.mGridSize);
        while (hintNextRowIndex < hintViewCount - 1 && i7 <= i2) {
            int hintNextRowIndex2 = pinchLayoutManager.getHintNextRowIndex(hintNextRowIndex, this.mGridSize, hintViewCount);
            int hintItemViewType2 = pinchLayoutManager.getHintItemViewType(hintNextRowIndex2, this.mGridSize);
            int i10 = i8 + 1;
            updateRowInfo(i8, hintNextRowIndex, (hintNextRowIndex2 - hintNextRowIndex) + (isSameRow(pinchLayoutManager, this.mGridSize, hintNextRowIndex2, hintNextRowIndex) ? 1 : 0), hintItemViewType);
            if (ViewHolderValue.isData(hintItemViewType2)) {
                i7++;
            }
            hintNextRowIndex = hintNextRowIndex2;
            hintItemViewType = hintItemViewType2;
            i8 = i10;
        }
    }

    private void calculateExtraBottomDividers(PinchLayoutManager pinchLayoutManager, int i2, int i7, int i8, RowInfo rowInfo) {
        int hintViewCount = this.mPositionCache.getHintViewCount(pinchLayoutManager, this.mGridSize);
        int hintNextDividerIndex = pinchLayoutManager.getHintNextDividerIndex(rowInfo.firstViewPosition, this.mGridSize, hintViewCount);
        int hintItemViewType = pinchLayoutManager.getHintItemViewType(hintNextDividerIndex, this.mGridSize);
        while (ViewHolderValue.isDivider(hintItemViewType) && i7 <= i2) {
            updateRowInfo(i8, hintNextDividerIndex, 1, hintItemViewType);
            hintNextDividerIndex = pinchLayoutManager.getHintNextRowIndex(hintNextDividerIndex, this.mGridSize, hintViewCount);
            hintItemViewType = pinchLayoutManager.getHintItemViewType(hintNextDividerIndex, this.mGridSize);
            i7++;
            i8++;
        }
    }

    private void calculateExtraTop(PinchLayoutManager pinchLayoutManager, int i2, boolean z) {
        int minRelativeRow = getMinRelativeRow(z);
        if (minRelativeRow > i2) {
            int row = getRow(minRelativeRow, z);
            int i7 = row - 1;
            RowInfo rowInfo = this.mRowInfo.get(Integer.valueOf(row));
            if (rowInfo != null) {
                if (z) {
                    calculateExtraTopData(pinchLayoutManager, i2, minRelativeRow, i7, rowInfo);
                } else {
                    calculateExtraTopDividers(pinchLayoutManager, i2, minRelativeRow, i7, rowInfo);
                }
            }
        }
    }

    private void calculateExtraTopData(PinchLayoutManager pinchLayoutManager, int i2, int i7, int i8, RowInfo rowInfo) {
        int hintPrevRowIndex = pinchLayoutManager.getHintPrevRowIndex(rowInfo.firstViewPosition, this.mGridSize);
        while (hintPrevRowIndex > 0 && i7 >= i2) {
            int hintPrevRowIndex2 = pinchLayoutManager.getHintPrevRowIndex(hintPrevRowIndex, this.mGridSize);
            int i10 = hintPrevRowIndex - hintPrevRowIndex2;
            int hintItemViewType = pinchLayoutManager.getHintItemViewType(hintPrevRowIndex, this.mGridSize);
            int i11 = i8 - 1;
            if (this.mRowInfo.get(Integer.valueOf(i8)) == null) {
                updateRowInfo(i8, (hintPrevRowIndex - i10) + 1, i10, hintItemViewType);
            }
            if (ViewHolderValue.isData(hintItemViewType)) {
                i7--;
            }
            hintPrevRowIndex = hintPrevRowIndex2;
            i8 = i11;
        }
    }

    private void calculateExtraTopDividers(PinchLayoutManager pinchLayoutManager, int i2, int i7, int i8, RowInfo rowInfo) {
        int hintPrevDividerIndex = pinchLayoutManager.getHintPrevDividerIndex(rowInfo.firstViewPosition, this.mGridSize);
        while (hintPrevDividerIndex > 0 && i7 >= i2) {
            updateRowInfo(i8, hintPrevDividerIndex, 1, pinchLayoutManager.getHintItemViewType(hintPrevDividerIndex, this.mGridSize));
            hintPrevDividerIndex = pinchLayoutManager.getHintPrevDividerIndex(hintPrevDividerIndex, this.mGridSize);
            i7--;
            i8--;
        }
    }

    /* access modifiers changed from: private */
    public void fillFirstRow() {
        if (this.mRowInfo.isEmpty()) {
            updateRowInfo(0, 0, 1, -3);
        }
    }

    private int getMaxRelativeRow(boolean z) {
        int i2 = 0;
        for (int i7 = this.mFocusedRow + 1; i7 <= getMaxRow().intValue(); i7++) {
            RowInfo rowInfo = this.mRowInfo.get(Integer.valueOf(i7));
            if (rowInfo != null && z == ViewHolderValue.isData(rowInfo.viewType)) {
                i2++;
            }
        }
        return i2 - getRowOffset(z);
    }

    private int getMinRelativeRow(boolean z) {
        int i2 = 0;
        for (int i7 = this.mFocusedRow - 1; i7 >= getMinRow().intValue(); i7--) {
            RowInfo rowInfo = this.mRowInfo.get(Integer.valueOf(i7));
            if (rowInfo != null && z == ViewHolderValue.isData(rowInfo.viewType)) {
                i2--;
            }
        }
        return i2;
    }

    private int getRowOffset(boolean z) {
        if (z) {
            return this.mDataRowOffset;
        }
        return this.mDividerRowOffset;
    }

    private int getRowRelativeWithOffset(int i2, boolean z) {
        int rowOffset = i2 + getRowOffset(z);
        if (z || rowOffset != 0) {
            return rowOffset;
        }
        return rowOffset + 1;
    }

    /* access modifiers changed from: private */
    public static boolean isSameRow(PinchLayoutManager pinchLayoutManager, int i2, int i7, int i8) {
        if (pinchLayoutManager.getHintSpanInfo(i8, i2).getRow() == pinchLayoutManager.getHintSpanInfo(i7, i2).getRow()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getLog$0(StringBuilder sb2, Integer num, RowInfo rowInfo) {
        if (rowInfo != null) {
            sb2.append("\n\t[");
            sb2.append(num);
            sb2.append("][");
            sb2.append(rowInfo.firstViewPosition);
            sb2.append("][");
            sb2.append(rowInfo.columnCount);
            sb2.append("][");
            sb2.append(rowInfo.viewType);
            sb2.append("]");
        }
    }

    /* access modifiers changed from: private */
    public void updateRowInfo(int i2, int i7, int i8, int i10) {
        this.mRowInfo.put(Integer.valueOf(i2), new RowInfo(i7, i8, i10, 0));
        this.mItemCount += i8;
    }

    public void calculateExtraRows(PinchLayoutManager pinchLayoutManager, RelativeRange relativeRange) {
        calculateExtraTop(pinchLayoutManager, relativeRange.getRow(0), relativeRange.isData());
        calculateExtraBottom(pinchLayoutManager, relativeRange.getRow(relativeRange.getRowCount() - 1), relativeRange.isData());
    }

    public int findViewPosition(int i2, int i7) {
        RowInfo rowInfo = this.mRowInfo.get(Integer.valueOf(i2));
        if (rowInfo != null) {
            return rowInfo.firstViewPosition + i7;
        }
        return -1;
    }

    public int getColumnCount(int i2) {
        RowInfo rowInfo = this.mRowInfo.get(Integer.valueOf(i2));
        if (rowInfo != null) {
            return rowInfo.columnCount + this.mStartOffset;
        }
        return -1;
    }

    public int getEndPosition() {
        return (this.mStartPosition + this.mItemCount) - 1;
    }

    public int getEndRow() {
        return this.mEndRow;
    }

    public int getFocusedRow() {
        return this.mFocusedRow;
    }

    public String getLog() {
        StringBuilder sb2 = new StringBuilder(C0086a.l(new StringBuilder("PinchRange["), this.mGridSize, "] - "));
        sb2.append("Row[");
        sb2.append(this.mStartRow);
        sb2.append("][");
        sb2.append(this.mFocusedRow);
        sb2.append("][");
        sb2.append(this.mEndRow);
        sb2.append("],StartPos[");
        sb2.append(this.mStartPosition);
        sb2.append("], Offset[");
        sb2.append(this.mDataRowOffset);
        sb2.append("][");
        sb2.append(this.mDividerRowOffset);
        sb2.append("]\nRow info : ");
        this.mRowInfo.forEach(new a(sb2));
        return sb2.toString();
    }

    public Integer getMaxRow() {
        return this.mRowInfo.keySet().stream().max(new a(26)).orElse(Integer.valueOf(this.mEndRow));
    }

    public Integer getMinRow() {
        return this.mRowInfo.keySet().stream().min(new a(26)).orElse(Integer.valueOf(this.mStartRow));
    }

    public int getRow(int i2, boolean z) {
        int i7;
        int rowRelativeWithOffset = getRowRelativeWithOffset(i2, z);
        int i8 = this.mFocusedRow;
        while (true) {
            if (rowRelativeWithOffset == 0) {
                break;
            }
            int i10 = -1;
            if (rowRelativeWithOffset > 0) {
                i7 = 1;
            } else {
                i7 = -1;
            }
            i8 += i7;
            RowInfo rowInfo = this.mRowInfo.get(Integer.valueOf(i8));
            if (rowInfo == null) {
                if (rowRelativeWithOffset <= 0) {
                    i10 = 1;
                }
                rowRelativeWithOffset += i10;
            } else if (z == ViewHolderValue.isData(rowInfo.viewType)) {
                if (rowRelativeWithOffset <= 0) {
                    i10 = 1;
                }
                rowRelativeWithOffset += i10;
            }
        }
        return i8 + rowRelativeWithOffset;
    }

    public int getStartPosition() {
        return this.mStartPosition;
    }

    public int getStartRow() {
        return this.mStartRow;
    }

    public float getStartY() {
        return this.mStartY;
    }

    public int getViewType(int i2) {
        RowInfo rowInfo = this.mRowInfo.get(Integer.valueOf(i2));
        if (rowInfo != null) {
            return rowInfo.viewType;
        }
        return 0;
    }

    public void setStartOffset(int i2) {
        this.mStartOffset = i2;
    }

    public void updateRowOffset(int i2, boolean z) {
        int row = getRow(i2 - getRowOffset(z), z);
        if (row >= 0) {
            return;
        }
        if (z) {
            this.mDataRowOffset = -row;
        } else {
            this.mDividerRowOffset = -row;
        }
    }

    private PinchRange(AnimPositionCache animPositionCache, int i2) {
        this.mRowInfo = new HashMap<>();
        this.mGridSize = i2;
        this.mPositionCache = animPositionCache;
    }
}
