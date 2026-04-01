package com.samsung.android.sdk.pen.ocr;

import android.graphics.Point;
import android.util.Log;
import c0.C0086a;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpenOcrWordData {
    private static final String TAG = "SpenOcrWordData";
    private float mAngle;
    private ArrayList<SpenOcrCharData> mCharDataList;
    private final ArrayList<Point[]> mCharRects;
    private String mLegacyText;
    private Point[] mPoly;
    private Point[] mRect;

    public SpenOcrWordData() {
        this.mLegacyText = "";
        this.mCharRects = new ArrayList<>();
        this.mAngle = 0.0f;
        this.mCharDataList = null;
        this.mCharDataList = new ArrayList<>();
        clear();
    }

    public void add(SpenOcrCharData spenOcrCharData) {
        this.mCharDataList.add(spenOcrCharData);
    }

    public void clear() {
        this.mCharDataList.clear();
        this.mLegacyText = "";
    }

    public final float getAngle() {
        return this.mAngle;
    }

    public ArrayList<SpenOcrCharData> getCharDataList() {
        return this.mCharDataList;
    }

    public ArrayList<Point[]> getCharRects() {
        return this.mCharRects;
    }

    public String getLegacyText() {
        return this.mLegacyText;
    }

    public final Point[] getPoly() {
        return this.mPoly;
    }

    public final Point[] getRect() {
        return this.mRect;
    }

    public final String getText() {
        StringBuilder sb2 = new StringBuilder();
        Iterator<SpenOcrCharData> it = this.mCharDataList.iterator();
        while (it.hasNext()) {
            sb2.append(it.next().getText());
        }
        return sb2.toString();
    }

    public Point[] getTextRect() {
        Log.w(TAG, "SpenOcrWordData.getTextRect() was deprecated. Use getRect() instead!");
        return this.mRect;
    }

    public boolean scale(float f) {
        for (Point point : this.mRect) {
            point.x = (int) (((float) point.x) * f);
            point.y = (int) (((float) point.y) * f);
        }
        return true;
    }

    public void setAngle(float f) {
        this.mAngle = f;
    }

    public void setCharDataList(ArrayList<SpenOcrCharData> arrayList) {
        this.mCharDataList.addAll(arrayList);
    }

    public void setCharRects(int[] iArr) {
        if (iArr == null || iArr.length % 8 != 0) {
            Log.e(TAG, "SpenOcrWordData::setCharRects array either null or has size not multiple of 8");
            return;
        }
        this.mCharRects.clear();
        for (int i2 = 0; i2 < iArr.length; i2 += 8) {
            this.mCharRects.add(new Point[]{new Point(iArr[i2], iArr[i2 + 1]), new Point(iArr[i2 + 2], iArr[i2 + 3]), new Point(iArr[i2 + 4], iArr[i2 + 5]), new Point(iArr[i2 + 6], iArr[i2 + 7])});
        }
    }

    public void setPoly(int[] iArr, int i2) {
        if (iArr == null || i2 <= 0) {
            Log.e(TAG, String.format(C0086a.i(i2, "SpenOcrWordData::setPoly poly is null or zero-length : len = "), new Object[0]));
            return;
        }
        int i7 = i2 / 2;
        Point[] pointArr = new Point[i7];
        for (int i8 = 0; i8 < i7; i8++) {
            int i10 = i8 * 2;
            pointArr[i8] = new Point(iArr[i10], iArr[i10 + 1]);
        }
        this.mPoly = pointArr;
    }

    public void setRect(Point[] pointArr) {
        this.mRect = pointArr;
        Log.d(TAG, String.format("SpenOcrWordData::setRect [LT(%d, %d), RT(%d, %d), RB(%d, %d), LB(%d, %d)]", new Object[]{Integer.valueOf(pointArr[0].x), Integer.valueOf(this.mRect[0].y), Integer.valueOf(this.mRect[1].x), Integer.valueOf(this.mRect[1].y), Integer.valueOf(this.mRect[2].x), Integer.valueOf(this.mRect[2].y), Integer.valueOf(this.mRect[3].x), Integer.valueOf(this.mRect[3].y)}));
    }

    public void setText(String str) {
        this.mLegacyText = str;
    }

    public void setRect(int[] iArr, int i2) {
        int i7 = i2;
        if (i7 < 8) {
            Log.e(TAG, String.format(C0086a.i(i7, "SpenOcrWordData::setRect rect(int array)'s length is "), new Object[0]));
            return;
        }
        Point[] pointArr = {new Point(iArr[0], iArr[1]), new Point(iArr[2], iArr[3]), new Point(iArr[4], iArr[5]), new Point(iArr[6], iArr[7])};
        this.mRect = pointArr;
        Log.d(TAG, String.format("SpenOcrWordData::setRect [LT(%d, %d), RT(%d, %d), RB(%d, %d), LB(%d, %d)]", new Object[]{Integer.valueOf(pointArr[0].x), Integer.valueOf(this.mRect[0].y), Integer.valueOf(this.mRect[1].x), Integer.valueOf(this.mRect[1].y), Integer.valueOf(this.mRect[2].x), Integer.valueOf(this.mRect[2].y), Integer.valueOf(this.mRect[3].x), Integer.valueOf(this.mRect[3].y)}));
    }
}
