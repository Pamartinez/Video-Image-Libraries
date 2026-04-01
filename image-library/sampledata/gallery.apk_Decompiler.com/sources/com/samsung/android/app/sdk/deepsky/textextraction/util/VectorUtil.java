package com.samsung.android.app.sdk.deepsky.textextraction.util;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t¢\u0006\u0002\u0010\nJ!\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t¢\u0006\u0002\u0010\nJ#\u0010\f\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0002¢\u0006\u0002\u0010\nJ\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0007J\u0018\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0007H\u0002J-\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00070\t¢\u0006\u0002\u0010\u0019J\u0016\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0007J\u0010\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0007H\u0002J\u0018\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0007H\u0002J \u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u0005H\u0002J\u0010\u0010 \u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0007H\u0002J!\u0010!\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t¢\u0006\u0002\u0010\nJ#\u0010\"\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0002¢\u0006\u0002\u0010\nJ)\u0010#\u001a\u0004\u0018\u00010\u00072\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00070\t¢\u0006\u0002\u0010&J'\u0010'\u001a\u00020\u000f2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00070\t¢\u0006\u0002\u0010(J!\u0010)\u001a\u00020*2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t2\u0006\u0010+\u001a\u00020\u0005¢\u0006\u0002\u0010,J\u001a\u0010-\u001a\u00020.2\u0012\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\t00J\u0016\u00101\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u00102\u001a\u00020.J\u0016\u00103\u001a\u00020.2\u0006\u00104\u001a\u00020.2\u0006\u00105\u001a\u00020\u000fJ\u0018\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u0007H\u0002¨\u00066"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/util/VectorUtil;", "", "<init>", "()V", "calcVerticalDistanceFromPointToPoly", "", "point", "Landroid/graphics/Point;", "poly", "", "(Landroid/graphics/Point;[Landroid/graphics/Point;)F", "calcHorizontalDistanceFromPointToPoly", "calcDistanceFromPointToLine", "line", "calcDistanceFromPointToPoint", "", "point1", "point2", "dotOperation", "u", "Landroid/graphics/PointF;", "v", "calcMinAreaPoly", "from", "to", "([Landroid/graphics/Point;[Landroid/graphics/Point;)[Landroid/graphics/Point;", "getRotationDegree", "calcUnitVector", "calcVector", "calcLocation", "o", "mag", "calcVectorMag", "calcDistanceFromPointToPoly", "calcDistanceFromPointToLineSegment", "calcIntersectionOfLines", "line0", "line1", "([Landroid/graphics/Point;[Landroid/graphics/Point;)Landroid/graphics/Point;", "calcAngleBetweenLines", "([Landroid/graphics/Point;[Landroid/graphics/Point;)I", "isTilted", "", "threshold", "([Landroid/graphics/Point;F)Z", "calcBoundingRect", "Landroid/graphics/Rect;", "polyList", "", "calcBoundedPoint", "boundingRect", "calcRectWithMargin", "rect", "margin", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VectorUtil {
    public static final VectorUtil INSTANCE = new VectorUtil();

    private VectorUtil() {
    }

    private final float calcDistanceFromPointToLine(Point point, Point[] pointArr) {
        Point point2 = pointArr[0];
        Point point3 = pointArr[1];
        int i2 = point2.y;
        int i7 = point3.y;
        float f = (float) (i2 - i7);
        int i8 = point3.x;
        int i10 = point2.x;
        float f5 = (float) (i8 - i10);
        return Math.abs(((((float) point.y) * f5) + (((float) point.x) * f)) + ((float) ((i10 * i7) - (i8 * i2)))) / ((float) Math.sqrt((double) ((f5 * f5) + (f * f))));
    }

    private final float calcDistanceFromPointToLineSegment(Point point, Point[] pointArr) {
        Point point2 = pointArr[0];
        Point point3 = pointArr[1];
        Point calcVector = calcVector(point2, point3);
        Point calcVector2 = calcVector(point2, point);
        Point calcVector3 = calcVector(point3, point);
        double d = (double) 2;
        float dotOperation = ((float) dotOperation(calcVector, calcVector2)) / ((float) Math.pow((double) calcVectorMag(calcVector), d));
        if (dotOperation < 0.0f) {
            return calcVectorMag(calcVector2);
        }
        if (dotOperation > 1.0f) {
            return calcVectorMag(calcVector3);
        }
        return (float) Math.sqrt((double) (((float) Math.pow((double) calcVectorMag(calcVector2), d)) - ((float) Math.pow((double) (dotOperation * calcVectorMag(calcVector)), d))));
    }

    private final Point calcLocation(Point point, PointF pointF, float f) {
        return new Point(point.x + ((int) ((pointF.x * f) + 0.5f)), point.y + ((int) ((f * pointF.y) + 0.5f)));
    }

    private final PointF calcUnitVector(Point point) {
        float calcVectorMag = calcVectorMag(point);
        return new PointF(((float) point.x) / calcVectorMag, ((float) point.y) / calcVectorMag);
    }

    private final Point calcVector(Point point, Point point2) {
        return new Point(point2.x - point.x, point2.y - point.y);
    }

    private final float calcVectorMag(Point point) {
        int i2 = point.x;
        int i7 = point.y;
        return (float) Math.sqrt((double) ((i7 * i7) + (i2 * i2)));
    }

    private final float dotOperation(PointF pointF, Point point) {
        return (pointF.y * ((float) point.y)) + (pointF.x * ((float) point.x));
    }

    public final int calcAngleBetweenLines(Point[] pointArr, Point[] pointArr2) {
        j.e(pointArr, "line0");
        j.e(pointArr2, "line1");
        Point calcVector = calcVector(pointArr[0], pointArr[1]);
        Point calcVector2 = calcVector(pointArr2[0], pointArr2[1]);
        return (int) Math.toDegrees(Math.acos((double) (((float) dotOperation(calcVector, calcVector2)) / (calcVectorMag(calcVector) * calcVectorMag(calcVector2)))));
    }

    public final Point calcBoundedPoint(Point point, Rect rect) {
        j.e(point, "point");
        j.e(rect, "boundingRect");
        return new Point(Math.min(rect.right, Math.max(rect.left, point.x)), Math.min(rect.bottom, Math.max(rect.top, point.y)));
    }

    public final Rect calcBoundingRect(List<Point[]> list) {
        Integer num;
        int i2;
        Integer num2;
        int i7;
        Integer num3;
        int i8;
        Integer num4;
        int i10;
        Integer num5;
        int i11;
        Integer num6;
        int i12;
        Integer num7;
        int i13;
        Integer num8;
        int i14;
        Integer num9;
        int i15;
        Integer num10;
        int i16;
        Integer num11;
        int i17;
        j.e(list, "polyList");
        Iterable iterable = list;
        Iterator it = iterable.iterator();
        Integer num12 = null;
        int i18 = 0;
        if (!it.hasNext()) {
            num = null;
        } else {
            Point[] pointArr = (Point[]) it.next();
            if (pointArr.length == 0) {
                num10 = null;
            } else {
                num10 = Integer.valueOf(pointArr[0].x);
                int length = pointArr.length - 1;
                if (1 <= length) {
                    int i19 = 1;
                    while (true) {
                        Integer valueOf = Integer.valueOf(pointArr[i19].x);
                        if (num10.compareTo(valueOf) > 0) {
                            num10 = valueOf;
                        }
                        if (i19 == length) {
                            break;
                        }
                        i19++;
                    }
                }
            }
            if (num10 != null) {
                i16 = num10.intValue();
            } else {
                i16 = 0;
            }
            num = Integer.valueOf(i16);
            while (it.hasNext()) {
                Point[] pointArr2 = (Point[]) it.next();
                if (pointArr2.length == 0) {
                    num11 = null;
                } else {
                    num11 = Integer.valueOf(pointArr2[0].x);
                    int length2 = pointArr2.length - 1;
                    if (1 <= length2) {
                        int i20 = 1;
                        while (true) {
                            Integer valueOf2 = Integer.valueOf(pointArr2[i20].x);
                            if (num11.compareTo(valueOf2) > 0) {
                                num11 = valueOf2;
                            }
                            if (i20 == length2) {
                                break;
                            }
                            i20++;
                        }
                    }
                }
                if (num11 != null) {
                    i17 = num11.intValue();
                } else {
                    i17 = 0;
                }
                Integer valueOf3 = Integer.valueOf(i17);
                if (num.compareTo(valueOf3) > 0) {
                    num = valueOf3;
                }
            }
        }
        if (num != null) {
            i2 = num.intValue();
        } else {
            i2 = 0;
        }
        Iterator it2 = iterable.iterator();
        if (!it2.hasNext()) {
            num2 = null;
        } else {
            Point[] pointArr3 = (Point[]) it2.next();
            if (pointArr3.length == 0) {
                num8 = null;
            } else {
                num8 = Integer.valueOf(pointArr3[0].y);
                int length3 = pointArr3.length - 1;
                if (1 <= length3) {
                    int i21 = 1;
                    while (true) {
                        Integer valueOf4 = Integer.valueOf(pointArr3[i21].y);
                        if (num8.compareTo(valueOf4) > 0) {
                            num8 = valueOf4;
                        }
                        if (i21 == length3) {
                            break;
                        }
                        i21++;
                    }
                }
            }
            if (num8 != null) {
                i14 = num8.intValue();
            } else {
                i14 = 0;
            }
            num2 = Integer.valueOf(i14);
            while (it2.hasNext()) {
                Point[] pointArr4 = (Point[]) it2.next();
                if (pointArr4.length == 0) {
                    num9 = null;
                } else {
                    num9 = Integer.valueOf(pointArr4[0].y);
                    int length4 = pointArr4.length - 1;
                    if (1 <= length4) {
                        int i22 = 1;
                        while (true) {
                            Integer valueOf5 = Integer.valueOf(pointArr4[i22].y);
                            if (num9.compareTo(valueOf5) > 0) {
                                num9 = valueOf5;
                            }
                            if (i22 == length4) {
                                break;
                            }
                            i22++;
                        }
                    }
                }
                if (num9 != null) {
                    i15 = num9.intValue();
                } else {
                    i15 = 0;
                }
                Integer valueOf6 = Integer.valueOf(i15);
                if (num2.compareTo(valueOf6) > 0) {
                    num2 = valueOf6;
                }
            }
        }
        if (num2 != null) {
            i7 = num2.intValue();
        } else {
            i7 = 0;
        }
        Iterator it3 = iterable.iterator();
        if (!it3.hasNext()) {
            num3 = null;
        } else {
            Point[] pointArr5 = (Point[]) it3.next();
            if (pointArr5.length == 0) {
                num6 = null;
            } else {
                num6 = Integer.valueOf(pointArr5[0].x);
                int length5 = pointArr5.length - 1;
                if (1 <= length5) {
                    int i23 = 1;
                    while (true) {
                        Integer valueOf7 = Integer.valueOf(pointArr5[i23].x);
                        if (num6.compareTo(valueOf7) < 0) {
                            num6 = valueOf7;
                        }
                        if (i23 == length5) {
                            break;
                        }
                        i23++;
                    }
                }
            }
            if (num6 != null) {
                i12 = num6.intValue();
            } else {
                i12 = 0;
            }
            num3 = Integer.valueOf(i12);
            while (it3.hasNext()) {
                Point[] pointArr6 = (Point[]) it3.next();
                if (pointArr6.length == 0) {
                    num7 = null;
                } else {
                    num7 = Integer.valueOf(pointArr6[0].x);
                    int length6 = pointArr6.length - 1;
                    if (1 <= length6) {
                        int i24 = 1;
                        while (true) {
                            Integer valueOf8 = Integer.valueOf(pointArr6[i24].x);
                            if (num7.compareTo(valueOf8) < 0) {
                                num7 = valueOf8;
                            }
                            if (i24 == length6) {
                                break;
                            }
                            i24++;
                        }
                    }
                }
                if (num7 != null) {
                    i13 = num7.intValue();
                } else {
                    i13 = 0;
                }
                Integer valueOf9 = Integer.valueOf(i13);
                if (num3.compareTo(valueOf9) < 0) {
                    num3 = valueOf9;
                }
            }
        }
        if (num3 != null) {
            i8 = num3.intValue();
        } else {
            i8 = 0;
        }
        Iterator it4 = iterable.iterator();
        if (it4.hasNext()) {
            Point[] pointArr7 = (Point[]) it4.next();
            if (pointArr7.length == 0) {
                num4 = null;
            } else {
                num4 = Integer.valueOf(pointArr7[0].y);
                int length7 = pointArr7.length - 1;
                if (1 <= length7) {
                    int i25 = 1;
                    while (true) {
                        Integer valueOf10 = Integer.valueOf(pointArr7[i25].y);
                        if (num4.compareTo(valueOf10) < 0) {
                            num4 = valueOf10;
                        }
                        if (i25 == length7) {
                            break;
                        }
                        i25++;
                    }
                }
            }
            if (num4 != null) {
                i10 = num4.intValue();
            } else {
                i10 = 0;
            }
            Integer valueOf11 = Integer.valueOf(i10);
            while (it4.hasNext()) {
                Point[] pointArr8 = (Point[]) it4.next();
                if (pointArr8.length == 0) {
                    num5 = null;
                } else {
                    num5 = Integer.valueOf(pointArr8[0].y);
                    int length8 = pointArr8.length - 1;
                    if (1 <= length8) {
                        int i26 = 1;
                        while (true) {
                            Integer valueOf12 = Integer.valueOf(pointArr8[i26].y);
                            if (num5.compareTo(valueOf12) < 0) {
                                num5 = valueOf12;
                            }
                            if (i26 == length8) {
                                break;
                            }
                            i26++;
                        }
                    }
                }
                if (num5 != null) {
                    i11 = num5.intValue();
                } else {
                    i11 = 0;
                }
                Integer valueOf13 = Integer.valueOf(i11);
                if (valueOf11.compareTo(valueOf13) < 0) {
                    valueOf11 = valueOf13;
                }
            }
            num12 = valueOf11;
        }
        if (num12 != null) {
            i18 = num12.intValue();
        }
        return new Rect(i2, i7, i8, i18);
    }

    public final int calcDistanceFromPointToPoint(Point point, Point point2) {
        j.e(point, "point1");
        j.e(point2, "point2");
        return (int) calcVectorMag(new Point(point.x - point2.x, point.y - point2.y));
    }

    public final float calcDistanceFromPointToPoly(Point point, Point[] pointArr) {
        j.e(point, "point");
        j.e(pointArr, "poly");
        ArrayList arrayList = new ArrayList();
        if (PointUtil.INSTANCE.isPointInsidePoly(point, pointArr)) {
            return 0.0f;
        }
        int length = pointArr.length;
        int i2 = 0;
        while (i2 < length) {
            Point point2 = pointArr[i2];
            i2++;
            arrayList.add(new Point[]{point2, pointArr[i2 % pointArr.length]});
        }
        Iterator it = arrayList.iterator();
        j.d(it, "iterator(...)");
        float f = Float.MAX_VALUE;
        while (it.hasNext()) {
            Object next = it.next();
            j.d(next, "next(...)");
            f = Math.min(calcDistanceFromPointToLineSegment(point, (Point[]) next), f);
        }
        return f;
    }

    public final float calcHorizontalDistanceFromPointToPoly(Point point, Point[] pointArr) {
        j.e(point, "point");
        j.e(pointArr, "poly");
        Point[] pointArr2 = {pointArr[1], pointArr[2]};
        Point[] pointArr3 = {pointArr[3], pointArr[0]};
        if (PointUtil.INSTANCE.isPointBetweenLines(point, pointArr2, pointArr3)) {
            return 0.0f;
        }
        return Math.min(calcDistanceFromPointToLine(point, pointArr2), calcDistanceFromPointToLine(point, pointArr3));
    }

    public final Point calcIntersectionOfLines(Point[] pointArr, Point[] pointArr2) {
        j.e(pointArr, "line0");
        j.e(pointArr2, "line1");
        Point point = pointArr[0];
        int i2 = point.x;
        int i7 = point.y;
        Point point2 = pointArr[1];
        int i8 = point2.x;
        int i10 = point2.y;
        Point point3 = pointArr2[0];
        int i11 = point3.x;
        int i12 = point3.y;
        Point point4 = pointArr2[1];
        int i13 = point4.x;
        int i14 = i12 - point4.y;
        int i15 = i11 - i13;
        float f = (float) (((i2 - i8) * i14) - ((i7 - i10) * i15));
        if (f == 0.0f) {
            return null;
        }
        float f5 = ((float) (((i2 - i11) * i14) - ((i7 - i12) * i15))) / f;
        return new Point((int) (((double) ((((float) (i8 - i2)) * f5) + ((float) i2))) + 0.5d), (int) (((double) ((f5 * ((float) (i10 - i7))) + ((float) i7))) + 0.5d));
    }

    public final Point[] calcMinAreaPoly(Point[] pointArr, Point[] pointArr2) {
        j.e(pointArr, "from");
        j.e(pointArr2, "to");
        ArrayList arrayList = new ArrayList();
        PointF calcUnitVector = calcUnitVector(calcVector(pointArr2[3], pointArr2[2]));
        PointF calcUnitVector2 = calcUnitVector(calcVector(pointArr2[0], pointArr2[1]));
        float dotOperation = dotOperation(calcUnitVector, calcVector(pointArr2[3], pointArr[3]));
        float dotOperation2 = dotOperation(calcUnitVector, calcVector(pointArr2[3], pointArr[2]));
        arrayList.add(calcLocation(pointArr2[0], calcUnitVector2, dotOperation));
        arrayList.add(calcLocation(pointArr2[0], calcUnitVector2, dotOperation2));
        arrayList.add(calcLocation(pointArr2[3], calcUnitVector, dotOperation2));
        arrayList.add(calcLocation(pointArr2[3], calcUnitVector, dotOperation));
        return (Point[]) arrayList.toArray(new Point[0]);
    }

    public final Rect calcRectWithMargin(Rect rect, int i2) {
        j.e(rect, "rect");
        return new Rect(rect.left - i2, rect.top - i2, rect.right + i2, rect.bottom + i2);
    }

    public final float calcVerticalDistanceFromPointToPoly(Point point, Point[] pointArr) {
        j.e(point, "point");
        j.e(pointArr, "poly");
        Point[] pointArr2 = {pointArr[0], pointArr[1]};
        Point[] pointArr3 = {pointArr[2], pointArr[3]};
        if (PointUtil.INSTANCE.isPointBetweenLines(point, pointArr2, pointArr3)) {
            return 0.0f;
        }
        return Math.min(calcDistanceFromPointToLine(point, pointArr2), calcDistanceFromPointToLine(point, pointArr3));
    }

    public final float getRotationDegree(Point point, Point point2) {
        j.e(point, "from");
        j.e(point2, "to");
        PointF pointF = new PointF(calcVector(point, point2));
        return (((float) Math.atan2((double) pointF.y, (double) pointF.x)) * 180.0f) / 3.1415927f;
    }

    public final boolean isTilted(Point[] pointArr, float f) {
        j.e(pointArr, "poly");
        float abs = Math.abs(getRotationDegree(pointArr[3], pointArr[2]));
        float abs2 = Math.abs(getRotationDegree(pointArr[2], pointArr[3]));
        if (abs <= f || abs2 <= f) {
            return false;
        }
        return true;
    }

    private final int dotOperation(Point point, Point point2) {
        return (point.y * point2.y) + (point.x * point2.x);
    }
}
