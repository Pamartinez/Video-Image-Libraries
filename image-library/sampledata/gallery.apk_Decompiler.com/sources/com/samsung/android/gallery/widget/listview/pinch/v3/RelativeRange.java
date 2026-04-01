package com.samsung.android.gallery.widget.listview.pinch.v3;

import A8.C0545b;
import android.util.SparseArray;
import com.samsung.android.gallery.widget.listviewholder.ViewHolderValue;
import java.lang.reflect.Array;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RelativeRange {
    private final SparseArray<Integer> mColumnCounts = new SparseArray<>();
    private final boolean mIsData;
    private final PinchRange[] mPinchRanges;

    public RelativeRange(boolean z, PinchRange... pinchRangeArr) {
        this.mPinchRanges = pinchRangeArr;
        this.mIsData = z;
        updateColumns();
    }

    private int getMaxColumnCount(int i2) {
        int i7;
        int i8 = -1;
        for (PinchRange pinchRange : this.mPinchRanges) {
            boolean z = this.mIsData;
            if (z || i2 != 0) {
                int row = pinchRange.getRow(i2, z);
                if (isValidViewType(pinchRange, row)) {
                    if (row < pinchRange.getStartRow() || row > pinchRange.getEndRow()) {
                        i7 = -1;
                    } else {
                        i7 = pinchRange.getColumnCount(row);
                    }
                    if (i7 > i8) {
                        i8 = i7;
                    }
                }
            }
        }
        return i8;
    }

    private int getRowsAboveFocusedRow(PinchRange pinchRange) {
        int i2 = 0;
        for (int startRow = pinchRange.getStartRow(); startRow < pinchRange.getFocusedRow(); startRow++) {
            if (isValidViewType(pinchRange, startRow)) {
                i2++;
            }
        }
        return i2;
    }

    private int getRowsBelowFocusedRow(PinchRange pinchRange) {
        int i2 = 0;
        for (int focusedRow = pinchRange.getFocusedRow() + 1; focusedRow <= pinchRange.getEndRow(); focusedRow++) {
            if (isValidViewType(pinchRange, focusedRow)) {
                i2++;
            }
        }
        return i2;
    }

    private boolean isValidViewType(PinchRange pinchRange, int i2) {
        if (this.mIsData == ViewHolderValue.isData(pinchRange.getViewType(i2))) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$updateColumns$0(int[] iArr) {
        return iArr[0];
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int lambda$updateColumns$1(int[] iArr) {
        return iArr[1];
    }

    private void updateColumns() {
        int length = this.mPinchRanges.length;
        int[] iArr = new int[2];
        iArr[1] = 2;
        iArr[0] = length;
        int[][] iArr2 = (int[][]) Array.newInstance(Integer.TYPE, iArr);
        int i2 = 0;
        while (true) {
            PinchRange[] pinchRangeArr = this.mPinchRanges;
            if (i2 >= pinchRangeArr.length) {
                break;
            }
            iArr2[i2][0] = -getRowsAboveFocusedRow(pinchRangeArr[i2]);
            iArr2[i2][1] = getRowsBelowFocusedRow(this.mPinchRanges[i2]);
            i2++;
        }
        int orElse = Arrays.stream(iArr2).mapToInt(new C0545b(11)).min().orElse(0);
        int orElse2 = Arrays.stream(iArr2).mapToInt(new C0545b(12)).max().orElse(0);
        for (PinchRange updateRowOffset : this.mPinchRanges) {
            updateRowOffset.updateRowOffset(orElse, this.mIsData);
        }
        while (orElse <= orElse2) {
            int maxColumnCount = getMaxColumnCount(orElse);
            if (maxColumnCount > 0) {
                this.mColumnCounts.put(orElse, Integer.valueOf(maxColumnCount));
            }
            orElse++;
        }
    }

    public int getColumnCount(int i2) {
        return this.mColumnCounts.valueAt(i2).intValue();
    }

    public int getRow(int i2) {
        if (i2 < 0 || this.mColumnCounts.size() <= i2) {
            return 0;
        }
        return this.mColumnCounts.keyAt(i2);
    }

    public int getRowCount() {
        return this.mColumnCounts.size();
    }

    public boolean isData() {
        return this.mIsData;
    }
}
