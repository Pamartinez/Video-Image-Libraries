package com.samsung.android.gallery.module.dataset.tables;

import com.arcsoft.libarccommon.utils.ArcCommonLog;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpanInfo {
    public static final SpanInfo SINGLE_CELL = new SpanInfo(1, 1);
    int mColumn;
    int mColumnSpan;
    boolean mIsResized;
    int mRow;
    int mRowSpan;
    int mViewType;

    public SpanInfo(int i2, int i7) {
        this.mRowSpan = i2;
        this.mColumnSpan = i7;
    }

    public int getColumn() {
        return this.mColumn;
    }

    public int getColumnSpan() {
        return this.mColumnSpan;
    }

    public int getRow() {
        return this.mRow;
    }

    public int getRowSpan() {
        return this.mRowSpan;
    }

    public int getViewType() {
        return this.mViewType;
    }

    public SpanInfo increaseRow() {
        this.mRow++;
        return this;
    }

    public boolean isResized() {
        return this.mIsResized;
    }

    public String toString() {
        return "(" + this.mRow + ArcCommonLog.TAG_COMMA + this.mColumn + " - [" + this.mRowSpan + ArcCommonLog.TAG_COMMA + this.mColumnSpan + "], " + this.mViewType;
    }

    public SpanInfo(int i2, int i7, int i8) {
        this(1, 1, i2, i7, i8);
    }

    public SpanInfo(int i2, int i7, int i8, int i10, int i11) {
        this.mRowSpan = i2;
        this.mColumnSpan = i7;
        this.mRow = i8;
        this.mColumn = i10;
        this.mViewType = i11;
    }

    public SpanInfo(int i2, int i7, int i8, boolean z) {
        this.mRowSpan = i2;
        this.mColumnSpan = i7;
        this.mViewType = i8;
        this.mIsResized = z;
    }
}
