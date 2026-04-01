package com.samsung.android.sdk.pen.ocr;

import android.graphics.Point;
import android.util.Log;
import c0.C0086a;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpenOcrTableData {
    private static final String TAG = "SpenOcrTableData";
    private Point[] mRect;
    private ArrayList<SpenOcrTableRowData> mRowDataList;

    public SpenOcrTableData() {
        this.mRowDataList = null;
        this.mRowDataList = new ArrayList<>();
        this.mRect = new Point[4];
    }

    public void add(SpenOcrTableRowData spenOcrTableRowData) {
        this.mRowDataList.add(spenOcrTableRowData);
    }

    public void clear() {
        this.mRowDataList.clear();
    }

    public final Point[] getRect() {
        return this.mRect;
    }

    public ArrayList<SpenOcrTableRowData> getRowDataList() {
        return this.mRowDataList;
    }

    public String getText() {
        StringBuilder sb2 = new StringBuilder();
        Iterator<SpenOcrTableRowData> it = this.mRowDataList.iterator();
        while (it.hasNext()) {
            sb2.append(it.next().getText());
            sb2.append("\n");
        }
        return new String(sb2).trim();
    }

    public void setRect(Point[] pointArr) {
        this.mRect = pointArr;
        Log.d(TAG, String.format("SpenOcrTableData::setRect [LT(%d, %d), RT(%d, %d), RB(%d, %d), LB(%d, %d)]: %s", new Object[]{Integer.valueOf(pointArr[0].x), Integer.valueOf(this.mRect[0].y), Integer.valueOf(this.mRect[1].x), Integer.valueOf(this.mRect[1].y), Integer.valueOf(this.mRect[2].x), Integer.valueOf(this.mRect[2].y), Integer.valueOf(this.mRect[3].x), Integer.valueOf(this.mRect[3].y)}));
    }

    public void setRect(int[] iArr, int i2) {
        int[] iArr2 = iArr;
        int i7 = i2;
        if (i7 < 8) {
            Log.e(TAG, String.format(C0086a.i(i7, "SpenOcrTableData::setRect rect(int array)'s length is "), new Object[0]));
            return;
        }
        StringBuilder o2 = C0086a.o(i7, "SpenOcrTableData::setRect len : ", ", length : ");
        o2.append(iArr2.length);
        Log.d(TAG, o2.toString());
        Point[] pointArr = {new Point(iArr2[0], iArr2[1]), new Point(iArr2[2], iArr2[3]), new Point(iArr2[4], iArr2[5]), new Point(iArr2[6], iArr2[7])};
        this.mRect = pointArr;
        Log.d(TAG, String.format("SpenOcrTableData::setRect [LT(%d, %d), RT(%d, %d), RB(%d, %d), LB(%d, %d)]", new Object[]{Integer.valueOf(pointArr[0].x), Integer.valueOf(this.mRect[0].y), Integer.valueOf(this.mRect[1].x), Integer.valueOf(this.mRect[1].y), Integer.valueOf(this.mRect[2].x), Integer.valueOf(this.mRect[2].y), Integer.valueOf(this.mRect[3].x), Integer.valueOf(this.mRect[3].y)}));
    }
}
