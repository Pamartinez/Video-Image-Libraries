package com.samsung.android.imagetranslation.geometry;

import android.graphics.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FastConvexHull {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class XCompare implements Comparator<Point>, Serializable {
        public /* synthetic */ XCompare(int i2) {
            this();
        }

        private XCompare() {
        }

        public int compare(Point point, Point point2) {
            return Integer.compare(point.x, point2.x);
        }
    }

    public static ArrayList<Point> execute(ArrayList<Point> arrayList) {
        ArrayList arrayList2 = (ArrayList) arrayList.clone();
        Collections.sort(arrayList2, new XCompare(0));
        int size = arrayList2.size();
        Point[] pointArr = new Point[size];
        pointArr[0] = (Point) arrayList2.get(0);
        pointArr[1] = (Point) arrayList2.get(1);
        int i2 = 2;
        for (int i7 = 2; i7 < size; i7++) {
            pointArr[i2] = (Point) arrayList2.get(i7);
            i2++;
            while (i2 > 2) {
                int i8 = i2 - 2;
                int i10 = i2 - 1;
                if (rightTurn(pointArr[i2 - 3], pointArr[i8], pointArr[i10])) {
                    break;
                }
                pointArr[i8] = pointArr[i10];
                i2--;
            }
        }
        Point[] pointArr2 = new Point[size];
        pointArr2[0] = (Point) arrayList2.get(size - 1);
        pointArr2[1] = (Point) arrayList2.get(size - 2);
        int i11 = 2;
        for (int i12 = size - 3; i12 >= 0; i12--) {
            pointArr2[i11] = (Point) arrayList2.get(i12);
            i11++;
            while (i11 > 2) {
                int i13 = i11 - 2;
                int i14 = i11 - 1;
                if (rightTurn(pointArr2[i11 - 3], pointArr2[i13], pointArr2[i14])) {
                    break;
                }
                pointArr2[i13] = pointArr2[i14];
                i11--;
            }
        }
        ArrayList<Point> arrayList3 = new ArrayList<>();
        for (int i15 = 0; i15 < i2; i15++) {
            arrayList3.add(pointArr[i15]);
        }
        for (int i16 = 1; i16 < i11 - 1; i16++) {
            arrayList3.add(pointArr2[i16]);
        }
        return arrayList3;
    }

    private static boolean rightTurn(Point point, Point point2, Point point3) {
        int i2 = point2.x;
        int i7 = point.x;
        int i8 = point3.y;
        int i10 = point.y;
        if (((i8 - i10) * (i2 - i7)) - ((point3.x - i7) * (point2.y - i10)) > 0) {
            return true;
        }
        return false;
    }
}
