package com.samsung.android.sdk.pen.ocr;

import android.graphics.Point;
import android.util.Log;
import c0.C0086a;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpenOcrLineData {
    private static final String TAG = "SpenOcrLineData";
    private float mAngle;
    private Point[] mRect;
    private long mUID;
    private ArrayList<SpenOcrWordData> mWordDataList;

    public SpenOcrLineData() {
        this.mWordDataList = null;
        this.mAngle = 0.0f;
        this.mUID = 0;
        this.mWordDataList = new ArrayList<>();
    }

    public void add(SpenOcrWordData spenOcrWordData) {
        this.mWordDataList.add(spenOcrWordData);
    }

    public void clear() {
        this.mWordDataList.clear();
    }

    public final float getAngle() {
        return this.mAngle;
    }

    public final Point[] getRect() {
        return this.mRect;
    }

    public final String getText() {
        StringBuilder sb2 = new StringBuilder();
        int i2 = 0;
        while (i2 < this.mWordDataList.size()) {
            sb2.append(this.mWordDataList.get(i2).getText());
            i2++;
            if (i2 < this.mWordDataList.size()) {
                sb2.append(" ");
            }
        }
        return sb2.toString();
    }

    public final long getUID() {
        return this.mUID;
    }

    public ArrayList<SpenOcrWordData> getWordDataList() {
        return this.mWordDataList;
    }

    public boolean scale(float f) {
        for (Point point : this.mRect) {
            point.x = (int) (((float) point.x) * f);
            point.y = (int) (((float) point.y) * f);
        }
        Iterator<SpenOcrWordData> it = this.mWordDataList.iterator();
        while (it.hasNext()) {
            if (!it.next().scale(f)) {
                return false;
            }
        }
        return true;
    }

    public void setAngle(float f) {
        this.mAngle = f;
    }

    public void setRect(Point[] pointArr) {
        this.mRect = pointArr;
        Log.d(TAG, String.format("SpenOcrLineData::setRect [LT(%d, %d), RT(%d, %d), RB(%d, %d), LB(%d, %d)]", new Object[]{Integer.valueOf(pointArr[0].x), Integer.valueOf(this.mRect[0].y), Integer.valueOf(this.mRect[1].x), Integer.valueOf(this.mRect[1].y), Integer.valueOf(this.mRect[2].x), Integer.valueOf(this.mRect[2].y), Integer.valueOf(this.mRect[3].x), Integer.valueOf(this.mRect[3].y)}));
    }

    public void setUID(long j2) {
        this.mUID = j2;
    }

    public void setWordDataList(ArrayList<SpenOcrWordData> arrayList) {
        this.mWordDataList.addAll(arrayList);
    }

    public void setRect(int[] iArr, int i2) {
        int i7 = i2;
        if (i7 < 8) {
            Log.e(TAG, String.format(C0086a.i(i7, "SpenOcrLineData::setRect rect(int array)'s length is "), new Object[0]));
            return;
        }
        Point[] pointArr = {new Point(iArr[0], iArr[1]), new Point(iArr[2], iArr[3]), new Point(iArr[4], iArr[5]), new Point(iArr[6], iArr[7])};
        this.mRect = pointArr;
        Log.d(TAG, String.format("SpenOcrLineData::setRect [LT(%d, %d), RT(%d, %d), RB(%d, %d), LB(%d, %d)]", new Object[]{Integer.valueOf(pointArr[0].x), Integer.valueOf(this.mRect[0].y), Integer.valueOf(this.mRect[1].x), Integer.valueOf(this.mRect[1].y), Integer.valueOf(this.mRect[2].x), Integer.valueOf(this.mRect[2].y), Integer.valueOf(this.mRect[3].x), Integer.valueOf(this.mRect[3].y)}));
    }
}
