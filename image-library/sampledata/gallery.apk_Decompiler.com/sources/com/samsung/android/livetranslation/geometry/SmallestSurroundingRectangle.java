package com.samsung.android.livetranslation.geometry;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import com.samsung.android.livetranslation.util.LTTLogger;
import i.C0212a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SmallestSurroundingRectangle {
    private static final String TAG = "SSR";

    private static RectF computeAlignedBounds(List<PointF> list, int i2) {
        return computeBounds(transform(list, createTransform(-computeEdgeAngleRad(list, i2), list.get(i2))));
    }

    private static int computeAlignmentPointIndex(List<PointF> list) {
        double d = Double.MAX_VALUE;
        int i2 = -1;
        for (int i7 = 0; i7 < list.size(); i7++) {
            RectF computeAlignedBounds = computeAlignedBounds(list, i7);
            double height = (double) (computeAlignedBounds.height() * computeAlignedBounds.width());
            if (height < d) {
                i2 = i7;
                d = height;
            }
        }
        return i2;
    }

    private static RectF computeBounds(List<PointF> list) {
        float f = Float.MAX_VALUE;
        float f5 = -3.4028235E38f;
        float f8 = -3.4028235E38f;
        float f10 = Float.MAX_VALUE;
        for (PointF next : list) {
            float f11 = next.x;
            float f12 = next.y;
            f = Math.min(f, f11);
            f10 = Math.min(f10, f12);
            f5 = Math.max(f5, f11);
            f8 = Math.max(f8, f12);
        }
        return new RectF(f, f10, f5 - f, f8 - f10);
    }

    public static List<PointF> computeConvexHullPoints(List<PointF> list) {
        ArrayList arrayList = new ArrayList();
        for (PointF next : list) {
            arrayList.add(new Point((int) next.x, (int) next.y));
        }
        ArrayList<Point> execute = FastConvexHull.execute(arrayList);
        ArrayList arrayList2 = new ArrayList();
        for (Point next2 : execute) {
            arrayList2.add(new PointF((float) next2.x, (float) next2.y));
        }
        return arrayList2;
    }

    public static List<PointF> computeCorners(List<PointF> list) {
        List<PointF> computeConvexHullPoints = computeConvexHullPoints(list);
        int computeAlignmentPointIndex = computeAlignmentPointIndex(computeConvexHullPoints);
        RectF computeAlignedBounds = computeAlignedBounds(computeConvexHullPoints, computeAlignmentPointIndex);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new PointF(computeAlignedBounds.left, computeAlignedBounds.bottom));
        arrayList.add(new PointF(computeAlignedBounds.right, computeAlignedBounds.bottom));
        arrayList.add(new PointF(computeAlignedBounds.right, computeAlignedBounds.top));
        arrayList.add(new PointF(computeAlignedBounds.left, computeAlignedBounds.top));
        double computeEdgeAngleRad = computeEdgeAngleRad(computeConvexHullPoints, computeAlignmentPointIndex);
        PointF pointF = computeConvexHullPoints.get(computeAlignmentPointIndex);
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.concatenate(AffineTransform.getTranslateInstance(pointF.x, pointF.y));
        affineTransform.concatenate(AffineTransform.getRotateInstance(computeEdgeAngleRad));
        List<PointF> transform = transform(arrayList, affineTransform);
        Collections.sort(transform, new Comparator<PointF>() {
            public int compare(PointF pointF, PointF pointF2) {
                float f = pointF.y;
                float f5 = pointF2.y;
                if (f < f5) {
                    return -1;
                }
                return (f != f5 || pointF.x >= pointF2.x) ? 1 : -1;
            }
        });
        return transform;
    }

    private static double computeEdgeAngleRad(List<PointF> list, int i2) {
        PointF pointF = list.get(i2);
        PointF pointF2 = list.get((i2 + 1) % list.size());
        return Math.atan2((double) (pointF2.y - pointF.y), (double) (pointF2.x - pointF.x));
    }

    public static Point[] computeMinAreaRect(List<Point> list) {
        Point[] pointArr = new Point[list.size()];
        for (int i2 = 0; i2 < list.size(); i2++) {
            pointArr[i2] = new Point(list.get(i2).x, list.get(i2).y);
        }
        LTTLogger.d(TAG, "points : " + list);
        return computeCorners(pointArr, pointArr);
    }

    public static Point[] computePolys(List<PointF> list) {
        List<PointF> computeConvexHullPoints = computeConvexHullPoints(list);
        int computeAlignmentPointIndex = computeAlignmentPointIndex(computeConvexHullPoints);
        RectF computeAlignedBounds = computeAlignedBounds(computeConvexHullPoints, computeAlignmentPointIndex);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new PointF(computeAlignedBounds.left, computeAlignedBounds.bottom));
        arrayList.add(new PointF(computeAlignedBounds.right, computeAlignedBounds.bottom));
        arrayList.add(new PointF(computeAlignedBounds.right, computeAlignedBounds.top));
        arrayList.add(new PointF(computeAlignedBounds.left, computeAlignedBounds.top));
        PointF pointF = computeConvexHullPoints.get(computeAlignmentPointIndex);
        double computeEdgeAngleRad = computeEdgeAngleRad(computeConvexHullPoints, computeAlignmentPointIndex);
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.concatenate(AffineTransform.getTranslateInstance(pointF.x, pointF.y));
        affineTransform.concatenate(AffineTransform.getRotateInstance(computeEdgeAngleRad));
        return sortPoint(transform(arrayList, affineTransform));
    }

    public static Path createPath(List<PointF> list) {
        Path path = new Path();
        for (int i2 = 0; i2 < list.size(); i2++) {
            PointF pointF = list.get(i2);
            float f = pointF.x;
            float f5 = pointF.y;
            if (i2 == 0) {
                path.moveTo(f, f5);
            } else {
                path.lineTo(f, f5);
            }
        }
        path.close();
        return path;
    }

    private static AffineTransform createTransform(double d, PointF pointF) {
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.concatenate(AffineTransform.getRotateInstance(d));
        affineTransform.concatenate(AffineTransform.getTranslateInstance(-pointF.x, -pointF.y));
        return affineTransform;
    }

    private static float crossProduct(PointF pointF, PointF pointF2, PointF pointF3) {
        float f = pointF2.x;
        float f5 = pointF.x;
        float f8 = pointF3.y;
        float f10 = pointF.y;
        return ((f8 - f10) * (f - f5)) - ((pointF3.x - f5) * (pointF2.y - f10));
    }

    private static int getLeftUpPoint(List<PointF> list) {
        double d = -1.0d;
        int i2 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i2 < 4) {
            int i10 = i2 + 1;
            for (int i11 = i10; i11 < 4; i11++) {
                double length = getLength(list.get(i2), list.get(i11));
                if (length > d) {
                    i7 = i2;
                    i8 = i11;
                    d = length;
                }
            }
            i2 = i10;
        }
        if (getSlope(list.get(i7), list.get(i8)) <= 0.0f) {
            int i12 = -1;
            int i13 = -1;
            for (int i14 = 0; i14 < 4; i14++) {
                if (!(i14 == i7 || i14 == i8 || i12 != -1)) {
                    i12 = i14;
                }
                if (!(i14 == i7 || i14 == i8 || i12 == -1)) {
                    i13 = i14;
                }
            }
            if (list.get(i7).x >= list.get(i8).x ? !isLeft(list.get(i8), list.get(i7), list.get(i12)) : !isLeft(list.get(i7), list.get(i8), list.get(i12))) {
                return i13;
            }
            return i12;
        } else if (list.get(i7).x < list.get(i8).x) {
            return i7;
        } else {
            return i8;
        }
    }

    private static double getLength(PointF pointF, PointF pointF2) {
        float f = pointF.x;
        float f5 = pointF2.x;
        float f8 = f - f5;
        float f10 = pointF.y;
        float f11 = pointF2.y;
        return Math.sqrt((double) C0212a.a(f10, f11, f10 - f11, (f - f5) * f8));
    }

    private static float getSlope(PointF pointF, PointF pointF2) {
        float f;
        float f5 = pointF2.x;
        float f8 = pointF.x;
        if (f5 == f8) {
            f = 1.0E-4f;
        } else {
            f = f5 - f8;
        }
        return (pointF2.y - pointF.y) / f;
    }

    private static boolean isLeft(PointF pointF, PointF pointF2, PointF pointF3) {
        if (crossProduct(pointF, pointF2, pointF3) < 0.0f) {
            return true;
        }
        return false;
    }

    public static Point[] sortPoint(List<PointF> list) {
        int i2;
        int i7;
        List<PointF> list2 = list;
        int leftUpPoint = getLeftUpPoint(list2);
        PointF[] pointFArr = {new PointF(), new PointF(), new PointF()};
        int i8 = 3;
        int[] iArr = new int[3];
        int i10 = 0;
        for (int i11 = 0; i11 < 4; i11++) {
            if (i11 != leftUpPoint) {
                pointFArr[i10].x = list2.get(i11).x - list2.get(leftUpPoint).x;
                pointFArr[i10].y = list2.get(i11).y - list2.get(leftUpPoint).y;
                iArr[i10] = i11;
                i10++;
            }
        }
        int i12 = 0;
        int i13 = -1;
        int i14 = -1;
        int i15 = -1;
        while (i12 < i8) {
            int i16 = 0;
            int i17 = 0;
            while (i16 < i8) {
                if (i12 == i16) {
                    i2 = i8;
                } else {
                    i2 = i8;
                    if (crossProduct(pointFArr[i12], pointFArr[i16]) > 0.0f) {
                        i7 = 1;
                    } else {
                        i7 = -1;
                    }
                    i17 += i7;
                }
                i16++;
                i8 = i2;
            }
            int i18 = i8;
            if (i17 == 2) {
                i13 = iArr[i12];
            } else if (i17 == 0) {
                i15 = iArr[i12];
            } else {
                i14 = iArr[i12];
            }
            i12++;
            i8 = i18;
        }
        int i19 = i8;
        if (i13 == -1 || i14 == -1 || i15 == -1) {
            return sortPointSJ(list2);
        }
        if (i13 == leftUpPoint || i14 == leftUpPoint || i15 == leftUpPoint) {
            return sortPointSJ(list2);
        }
        Point[] pointArr = {new Point(), new Point(), new Point(), new Point()};
        pointArr[0].x = (int) list2.get(leftUpPoint).x;
        pointArr[0].y = (int) list2.get(leftUpPoint).y;
        pointArr[1].x = (int) list2.get(i13).x;
        pointArr[1].y = (int) list2.get(i13).y;
        pointArr[2].x = (int) list2.get(i15).x;
        pointArr[2].y = (int) list2.get(i15).y;
        pointArr[i19].x = (int) list2.get(i14).x;
        pointArr[i19].y = (int) list2.get(i14).y;
        return pointArr;
    }

    private static Point[] sortPointSJ(List<PointF> list) {
        Collections.sort(list, new Comparator<PointF>() {
            public int compare(PointF pointF, PointF pointF2) {
                return pointF.x < pointF2.x ? -1 : 1;
            }
        });
        Point[] pointArr = {new Point(), new Point(), new Point(), new Point()};
        if (list.get(0).y < list.get(1).y) {
            pointArr[0].x = (int) list.get(0).x;
            pointArr[3].x = (int) list.get(1).x;
            pointArr[0].y = (int) list.get(0).y;
            pointArr[3].y = (int) list.get(1).y;
        } else {
            pointArr[0].x = (int) list.get(1).x;
            pointArr[3].x = (int) list.get(0).x;
            pointArr[0].y = (int) list.get(1).y;
            pointArr[3].y = (int) list.get(0).y;
        }
        if (list.get(2).y < list.get(3).y) {
            pointArr[1].x = (int) list.get(2).x;
            pointArr[1].y = (int) list.get(2).y;
            pointArr[2].x = (int) list.get(3).x;
            pointArr[2].y = (int) list.get(3).y;
            return pointArr;
        }
        pointArr[1].x = (int) list.get(3).x;
        pointArr[1].y = (int) list.get(3).y;
        pointArr[2].x = (int) list.get(2).x;
        pointArr[2].y = (int) list.get(2).y;
        return pointArr;
    }

    private static List<PointF> transform(List<PointF> list, AffineTransform affineTransform) {
        ArrayList arrayList = new ArrayList();
        for (PointF transform : list) {
            arrayList.add(affineTransform.transform(transform, (PointF) null));
        }
        return arrayList;
    }

    private static float crossProduct(PointF pointF, PointF pointF2) {
        return (pointF.x * pointF2.y) - (pointF.y * pointF2.x);
    }

    public static Point[] computeCorners(Point[] pointArr, Point[] pointArr2) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < 4; i2++) {
            Point point = pointArr[i2];
            arrayList.add(new PointF((float) point.x, (float) point.y));
            Point point2 = pointArr2[i2];
            arrayList.add(new PointF((float) point2.x, (float) point2.y));
        }
        return sortPoint(computeCorners(arrayList));
    }
}
