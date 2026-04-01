package com.samsung.android.sdk.pen.ocr;

import android.graphics.Point;
import android.util.Log;
import c4.C0431a;
import com.samsung.android.motionphoto.utils.ex.b;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpenOcrPageData {
    private static final String TAG = "SpenOcrPageData";
    private ArrayList<SpenOcrBlockData> mTextBlockList = new ArrayList<>();
    private ArrayList<SpenOcrTableData> mTextTableList = new ArrayList<>();

    private static boolean isPointInPolygon(Point point, Point[] pointArr) {
        boolean z;
        int length = pointArr.length;
        int i2 = length - 1;
        boolean z3 = false;
        for (int i7 = 0; i7 < length; i7++) {
            Point point2 = pointArr[i7];
            int i8 = point2.y;
            int i10 = point.y;
            boolean z7 = true;
            if (i8 > i10) {
                z = true;
            } else {
                z = false;
            }
            Point point3 = pointArr[i2];
            int i11 = point3.y;
            if (i11 <= i10) {
                z7 = false;
            }
            if (z != z7) {
                int i12 = point.x;
                int i13 = point3.x;
                int i14 = point2.x;
                if (i12 < (((i10 - i8) * (i13 - i14)) / (i11 - i8)) + i14) {
                    z3 = !z3;
                }
            }
            i2 = i7;
        }
        return z3;
    }

    public void add(SpenOcrBlockData spenOcrBlockData) {
        this.mTextBlockList.add(spenOcrBlockData);
    }

    public void clear() {
        this.mTextBlockList.clear();
        this.mTextTableList.clear();
    }

    public int findNearestTextBlock(Point point) {
        if (getTextBlockList().size() <= 0) {
            Log.w(TAG, "There is no detected text block");
            return -1;
        }
        Log.d(TAG, "pivotPoint : " + point.toString());
        List list = (List) getTextBlockList().stream().map(new C0431a(21)).map(new b(1, point)).collect(Collectors.toList());
        return list.indexOf(Collections.min(list));
    }

    public String getText() {
        StringBuilder sb2 = new StringBuilder();
        Iterator<SpenOcrBlockData> it = this.mTextBlockList.iterator();
        while (it.hasNext()) {
            sb2.append(it.next().getText());
            sb2.append("\n\n");
        }
        return new String(sb2).trim();
    }

    public ArrayList<SpenOcrBlockData> getTextBlockList() {
        return this.mTextBlockList;
    }

    public ArrayList<SpenOcrTableData> getTextTableList() {
        return this.mTextTableList;
    }

    public boolean scale(float f) {
        Iterator<SpenOcrBlockData> it = this.mTextBlockList.iterator();
        while (it.hasNext()) {
            if (!it.next().scale(f)) {
                return false;
            }
        }
        return true;
    }

    public void setTextBlockList(ArrayList<SpenOcrBlockData> arrayList) {
        this.mTextBlockList.addAll(arrayList);
    }

    public void setTextTableList(ArrayList<SpenOcrTableData> arrayList) {
        this.mTextTableList.addAll(arrayList);
    }

    public void add(SpenOcrTableData spenOcrTableData) {
        this.mTextTableList.add(spenOcrTableData);
    }
}
