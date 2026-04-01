package com.samsung.android.sdk.pen.ocr;

import android.graphics.Point;
import android.util.Log;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpenOcrBlockData {
    private static final String TAG = "SpenOcrBlockData";
    private ArrayList<SpenOcrLineData> mLineDataList;
    private Point[] mRect;
    private long mUID;

    public SpenOcrBlockData() {
        this.mLineDataList = null;
        this.mUID = 0;
        this.mLineDataList = new ArrayList<>();
        this.mRect = new Point[4];
    }

    public void add(SpenOcrLineData spenOcrLineData) {
        this.mLineDataList.add(spenOcrLineData);
    }

    public void clear() {
        this.mLineDataList.clear();
    }

    public ArrayList<SpenOcrLineData> getLineDataList() {
        return this.mLineDataList;
    }

    public final Point[] getRect() {
        return this.mRect;
    }

    public String getRectInfo() {
        StringBuilder sb2 = new StringBuilder();
        for (Point point : this.mRect) {
            if (point != null) {
                sb2.append("(");
                sb2.append(point.x);
                sb2.append(ArcCommonLog.TAG_COMMA);
                sb2.append(point.y);
                sb2.append(") ");
            }
        }
        return sb2.toString();
    }

    public String getText() {
        StringBuilder sb2 = new StringBuilder();
        Iterator<SpenOcrLineData> it = this.mLineDataList.iterator();
        while (it.hasNext()) {
            sb2.append(it.next().getText());
            sb2.append("\n");
        }
        return new String(sb2).trim();
    }

    public final long getUID() {
        return this.mUID;
    }

    public boolean scale(float f) {
        for (Point point : this.mRect) {
            point.x = (int) (((float) point.x) * f);
            point.y = (int) (((float) point.y) * f);
        }
        Iterator<SpenOcrLineData> it = this.mLineDataList.iterator();
        while (it.hasNext()) {
            if (!it.next().scale(f)) {
                return false;
            }
        }
        return true;
    }

    public void setLineDataList(ArrayList<SpenOcrLineData> arrayList) {
        this.mLineDataList.addAll(arrayList);
    }

    public void setRect(Point[] pointArr) {
        this.mRect = pointArr;
        Log.d(TAG, String.format("SpenOcrTextBlock::setRect [LT(%d, %d), RT(%d, %d), RB(%d, %d), LB(%d, %d)]: %s", new Object[]{Integer.valueOf(pointArr[0].x), Integer.valueOf(this.mRect[0].y), Integer.valueOf(this.mRect[1].x), Integer.valueOf(this.mRect[1].y), Integer.valueOf(this.mRect[2].x), Integer.valueOf(this.mRect[2].y), Integer.valueOf(this.mRect[3].x), Integer.valueOf(this.mRect[3].y)}));
    }

    public void setUID(long j2) {
        this.mUID = j2;
    }

    public void setRect(int[] iArr, int i2) {
        int[] iArr2 = iArr;
        int i7 = i2;
        if (i7 < 8) {
            Log.e(TAG, String.format(C0086a.i(i7, "SpenOcrTextBlock::setRect rect(int array)'s length is "), new Object[0]));
            return;
        }
        StringBuilder o2 = C0086a.o(i7, "SpenOcrTextBlock::setRect len : ", ", length : ");
        o2.append(iArr2.length);
        Log.d(TAG, o2.toString());
        Point[] pointArr = {new Point(iArr2[0], iArr2[1]), new Point(iArr2[2], iArr2[3]), new Point(iArr2[4], iArr2[5]), new Point(iArr2[6], iArr2[7])};
        this.mRect = pointArr;
        Log.d(TAG, String.format("SpenOcrTextBlock::setRect [LT(%d, %d), RT(%d, %d), RB(%d, %d), LB(%d, %d)]", new Object[]{Integer.valueOf(pointArr[0].x), Integer.valueOf(this.mRect[0].y), Integer.valueOf(this.mRect[1].x), Integer.valueOf(this.mRect[1].y), Integer.valueOf(this.mRect[2].x), Integer.valueOf(this.mRect[2].y), Integer.valueOf(this.mRect[3].x), Integer.valueOf(this.mRect[3].y)}));
    }
}
