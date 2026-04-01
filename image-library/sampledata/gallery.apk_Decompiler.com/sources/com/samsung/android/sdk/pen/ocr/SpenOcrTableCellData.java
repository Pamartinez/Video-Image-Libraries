package com.samsung.android.sdk.pen.ocr;

import android.graphics.Point;
import android.util.Log;
import c0.C0086a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpenOcrTableCellData {
    private static final String TAG = "SpenOcrTableCellData";
    private Point[] mRect;
    private String mText;

    public SpenOcrTableCellData() {
        clear();
    }

    public void clear() {
        this.mText = "";
    }

    public final Point[] getRect() {
        return this.mRect;
    }

    public final String getText() {
        return this.mText;
    }

    public void setRect(Point[] pointArr) {
        this.mRect = pointArr;
        Log.d(TAG, String.format("SpenOcrTableCellData::setRect [LT(%d, %d), RT(%d, %d), RB(%d, %d), LB(%d, %d)]", new Object[]{Integer.valueOf(pointArr[0].x), Integer.valueOf(this.mRect[0].y), Integer.valueOf(this.mRect[1].x), Integer.valueOf(this.mRect[1].y), Integer.valueOf(this.mRect[2].x), Integer.valueOf(this.mRect[2].y), Integer.valueOf(this.mRect[3].x), Integer.valueOf(this.mRect[3].y)}));
    }

    public void setText(String str) {
        this.mText = str;
    }

    public void setRect(int[] iArr, int i2) {
        int i7 = i2;
        if (i7 < 8) {
            Log.e(TAG, String.format(C0086a.i(i7, "SpenOcrTableCellData::setRect rect(int array)'s length is "), new Object[0]));
            return;
        }
        Point[] pointArr = {new Point(iArr[0], iArr[1]), new Point(iArr[2], iArr[3]), new Point(iArr[4], iArr[5]), new Point(iArr[6], iArr[7])};
        this.mRect = pointArr;
        Log.d(TAG, String.format("SpenOcrTableCellData::setRect [LT(%d, %d), RT(%d, %d), RB(%d, %d), LB(%d, %d)]", new Object[]{Integer.valueOf(pointArr[0].x), Integer.valueOf(this.mRect[0].y), Integer.valueOf(this.mRect[1].x), Integer.valueOf(this.mRect[1].y), Integer.valueOf(this.mRect[2].x), Integer.valueOf(this.mRect[2].y), Integer.valueOf(this.mRect[3].x), Integer.valueOf(this.mRect[3].y)}));
    }
}
