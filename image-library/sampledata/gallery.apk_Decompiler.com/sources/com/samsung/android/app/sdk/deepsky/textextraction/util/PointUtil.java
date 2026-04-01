package com.samsung.android.app.sdk.deepsky.textextraction.util;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import com.samsung.android.app.sdk.deepsky.textextraction.logger.LibLogger;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1195m;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005*\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u001f\u0010\f\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\f\u0010\rJ1\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0006¢\u0006\u0004\b\u0011\u0010\u0012J\u001b\u0010\u0014\u001a\u00020\u00042\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0014\u0010\u0015J\u001b\u0010\u0017\u001a\u00020\u00162\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0017\u0010\u0018J\u001b\u0010\u001a\u001a\u00020\u00192\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u001a\u0010\u001bJ1\u0010\u001f\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u00062\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u001f\u0010 J\u001b\u0010!\u001a\u00020\u00042\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b!\u0010\u0015J'\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005*\u00020\u00042\u0006\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020\u0006¢\u0006\u0004\b%\u0010&J#\u0010'\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u00062\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b'\u0010(J3\u0010-\u001a\u00020\u00192\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010,\u001a\u00020+¢\u0006\u0004\b-\u0010.¨\u0006/"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/textextraction/util/PointUtil;", "", "<init>", "()V", "Landroid/graphics/Rect;", "", "Landroid/graphics/Point;", "toPoly", "(Landroid/graphics/Rect;)[Landroid/graphics/Point;", "source", "target", "", "calcIntersectionRatio", "(Landroid/graphics/Rect;Landroid/graphics/Rect;)F", "points", "ratio", "offset", "getTransformedPoly", "([Landroid/graphics/Point;FLandroid/graphics/Point;)[Landroid/graphics/Point;", "poly", "polyToRect", "([Landroid/graphics/Point;)Landroid/graphics/Rect;", "Landroid/graphics/Path;", "createPathFromPoly", "([Landroid/graphics/Point;)Landroid/graphics/Path;", "", "isVertical", "([Landroid/graphics/Point;)Z", "point", "line1", "line2", "isPointBetweenLines", "(Landroid/graphics/Point;[Landroid/graphics/Point;[Landroid/graphics/Point;)Z", "getRectContainingPoly", "", "angle", "pivot", "getRotatedRect", "(Landroid/graphics/Rect;ILandroid/graphics/Point;)[Landroid/graphics/Point;", "isPointInsidePoly", "(Landroid/graphics/Point;[Landroid/graphics/Point;)Z", "sourcePoly", "targetPoly", "", "threshold", "isPolyOverlapsPoly", "([Landroid/graphics/Point;[Landroid/graphics/Point;D)Z", "deepsky-sdk-textextraction-8.5.30_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class PointUtil {
    public static final PointUtil INSTANCE = new PointUtil();

    private PointUtil() {
    }

    private final float calcIntersectionRatio(Rect rect, Rect rect2) {
        int i2 = rect2.right;
        int i7 = rect2.left;
        int i8 = ((rect2.bottom - rect2.top) + 1) * ((i2 - i7) + 1);
        int max = Math.max(rect.left, i7);
        int max2 = Math.max(rect.top, rect2.top);
        int min = Math.min(rect.right, rect2.right);
        int min2 = Math.min(rect.bottom, rect2.bottom);
        return ((float) (Math.max(0, (min2 - max2) + 1) * Math.max(0, (min - max) + 1))) / ((float) i8);
    }

    public static /* synthetic */ boolean isPolyOverlapsPoly$default(PointUtil pointUtil, Point[] pointArr, Point[] pointArr2, double d, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            d = 0.9d;
        }
        return pointUtil.isPolyOverlapsPoly(pointArr, pointArr2, d);
    }

    private final Point[] toPoly(Rect rect) {
        return new Point[]{new Point(rect.left, rect.top), new Point(rect.right, rect.top), new Point(rect.right, rect.bottom), new Point(rect.left, rect.bottom)};
    }

    public final Path createPathFromPoly(Point[] pointArr) {
        j.e(pointArr, "poly");
        Path path = new Path();
        Point point = pointArr[0];
        path.moveTo((float) point.x, (float) point.y);
        Point point2 = pointArr[1];
        path.lineTo((float) point2.x, (float) point2.y);
        Point point3 = pointArr[2];
        path.lineTo((float) point3.x, (float) point3.y);
        Point point4 = pointArr[3];
        path.lineTo((float) point4.x, (float) point4.y);
        Point point5 = pointArr[0];
        path.lineTo((float) point5.x, (float) point5.y);
        path.close();
        return path;
    }

    public final Rect getRectContainingPoly(Point[] pointArr) {
        j.e(pointArr, "poly");
        if (pointArr.length != 0) {
            int i2 = pointArr[0].x;
            int i7 = 1;
            int length = pointArr.length - 1;
            if (1 <= length) {
                int i8 = 1;
                while (true) {
                    int i10 = pointArr[i8].x;
                    if (i2 > i10) {
                        i2 = i10;
                    }
                    if (i8 == length) {
                        break;
                    }
                    i8++;
                }
            }
            if (pointArr.length != 0) {
                int i11 = pointArr[0].y;
                int length2 = pointArr.length - 1;
                if (1 <= length2) {
                    int i12 = 1;
                    while (true) {
                        int i13 = pointArr[i12].y;
                        if (i11 > i13) {
                            i11 = i13;
                        }
                        if (i12 == length2) {
                            break;
                        }
                        i12++;
                    }
                }
                if (pointArr.length != 0) {
                    int i14 = pointArr[0].x;
                    int length3 = pointArr.length - 1;
                    if (1 <= length3) {
                        int i15 = 1;
                        while (true) {
                            int i16 = pointArr[i15].x;
                            if (i14 < i16) {
                                i14 = i16;
                            }
                            if (i15 == length3) {
                                break;
                            }
                            i15++;
                        }
                    }
                    if (pointArr.length != 0) {
                        int i17 = pointArr[0].y;
                        int length4 = pointArr.length - 1;
                        if (1 <= length4) {
                            while (true) {
                                int i18 = pointArr[i7].y;
                                if (i17 < i18) {
                                    i17 = i18;
                                }
                                if (i7 == length4) {
                                    break;
                                }
                                i7++;
                            }
                        }
                        return new Rect(i2, i11, i14, i17);
                    }
                    throw new NoSuchElementException();
                }
                throw new NoSuchElementException();
            }
            throw new NoSuchElementException();
        }
        throw new NoSuchElementException();
    }

    public final Point[] getRotatedRect(Rect rect, int i2, Point point) {
        j.e(rect, "<this>");
        j.e(point, "pivot");
        Point[] poly = toPoly(rect);
        double d = ((double) (((float) i2) / 180.0f)) * 3.141592653589793d;
        for (Point point2 : poly) {
            double d2 = (double) (point2.x - point.x);
            double d3 = (double) (point2.y - point.y);
            double sin = Math.sin(d) * d2;
            point2.x = ((int) ((Math.cos(d) * d2) - (Math.sin(d) * d3))) + point.x;
            point2.y = ((int) ((Math.cos(d) * d3) + sin)) + point.y;
        }
        return poly;
    }

    public final Point[] getTransformedPoly(Point[] pointArr, float f, Point point) {
        j.e(pointArr, "points");
        j.e(point, "offset");
        ArrayList arrayList = new ArrayList(pointArr.length);
        for (Point point2 : pointArr) {
            arrayList.add(new Point((int) ((((float) point2.x) * f) + ((float) point.x) + 0.5f), (int) ((((float) point2.y) * f) + ((float) point.y) + 0.5f)));
        }
        return (Point[]) arrayList.toArray(new Point[0]);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x001b, code lost:
        r4 = r11[0];
        r5 = r4.x;
        r6 = r11[1];
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean isPointBetweenLines(android.graphics.Point r9, android.graphics.Point[] r10, android.graphics.Point[] r11) {
        /*
            r8 = this;
            java.lang.String r8 = "point"
            kotlin.jvm.internal.j.e(r9, r8)
            java.lang.String r8 = "line1"
            kotlin.jvm.internal.j.e(r10, r8)
            java.lang.String r8 = "line2"
            kotlin.jvm.internal.j.e(r11, r8)
            r8 = 0
            r0 = r10[r8]
            int r1 = r0.x
            r2 = 1
            r10 = r10[r2]
            int r3 = r10.x
            if (r1 == r3) goto L_0x0061
            r4 = r11[r8]
            int r5 = r4.x
            r6 = r11[r2]
            int r7 = r6.x
            if (r5 != r7) goto L_0x0026
            goto L_0x0061
        L_0x0026:
            int r10 = r10.y
            int r11 = r0.y
            int r10 = r10 - r11
            float r10 = (float) r10
            int r3 = r3 - r1
            float r0 = (float) r3
            float r10 = r10 / r0
            float r0 = -r10
            float r1 = (float) r1
            float r0 = r0 * r1
            float r11 = (float) r11
            float r0 = r0 + r11
            int r11 = r6.y
            int r1 = r4.y
            int r11 = r11 - r1
            float r11 = (float) r11
            int r7 = r7 - r5
            float r3 = (float) r7
            float r11 = r11 / r3
            float r3 = -r11
            float r4 = (float) r5
            float r3 = r3 * r4
            float r1 = (float) r1
            float r3 = r3 + r1
            int r1 = r9.x
            float r4 = (float) r1
            float r10 = r10 * r4
            float r10 = r10 + r0
            float r0 = (float) r1
            float r11 = r11 * r0
            float r11 = r11 + r3
            int r9 = r9.y
            float r0 = (float) r9
            int r1 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r1 > 0) goto L_0x0056
            int r0 = (r0 > r11 ? 1 : (r0 == r11 ? 0 : -1))
            if (r0 > 0) goto L_0x0056
            goto L_0x005f
        L_0x0056:
            float r9 = (float) r9
            int r11 = (r11 > r9 ? 1 : (r11 == r9 ? 0 : -1))
            if (r11 > 0) goto L_0x0060
            int r9 = (r9 > r10 ? 1 : (r9 == r10 ? 0 : -1))
            if (r9 > 0) goto L_0x0060
        L_0x005f:
            return r2
        L_0x0060:
            return r8
        L_0x0061:
            r10 = r11[r8]
            int r10 = r10.x
            int r9 = r9.x
            if (r1 > r9) goto L_0x006c
            if (r9 > r10) goto L_0x006c
            goto L_0x0070
        L_0x006c:
            if (r10 > r9) goto L_0x0071
            if (r9 > r1) goto L_0x0071
        L_0x0070:
            return r2
        L_0x0071:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.app.sdk.deepsky.textextraction.util.PointUtil.isPointBetweenLines(android.graphics.Point, android.graphics.Point[], android.graphics.Point[]):boolean");
    }

    public final boolean isPointInsidePoly(Point point, Point[] pointArr) {
        int i2;
        int i7;
        boolean z;
        boolean z3;
        j.e(point, "point");
        j.e(pointArr, "poly");
        int i8 = Integer.MAX_VALUE;
        int i10 = Integer.MIN_VALUE;
        int i11 = Integer.MIN_VALUE;
        int i12 = Integer.MAX_VALUE;
        for (Point point2 : pointArr) {
            i8 = Math.min(point2.x, i8);
            i10 = Math.max(point2.x, i10);
            i12 = Math.min(point2.y, i12);
            i11 = Math.max(point2.y, i11);
        }
        int i13 = point.x;
        if (i13 >= i8 && i13 <= i10 && (i2 = point.y) >= i12 && i2 <= i11) {
            ArrayList<Point[]> arrayList = new ArrayList<>();
            int length = pointArr.length;
            int i14 = 0;
            while (i14 < length) {
                Point point3 = pointArr[i14];
                i14++;
                arrayList.add(new Point[]{point3, pointArr[i14 % pointArr.length]});
            }
            if (arrayList.isEmpty()) {
                i7 = 0;
            } else {
                i7 = 0;
                for (Point[] pointArr2 : arrayList) {
                    Point point4 = pointArr2[0];
                    int i15 = point4.y;
                    int i16 = point.y;
                    if (i15 > i16) {
                        z = true;
                    } else {
                        z = false;
                    }
                    Point point5 = pointArr2[1];
                    int i17 = point5.y;
                    if (i17 > i16) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (z != z3) {
                        int i18 = point.x;
                        int i19 = point4.x;
                        if (i18 <= (((i16 - i15) * (point5.x - i19)) / (i17 - i15)) + i19 && (i7 = i7 + 1) < 0) {
                            C1195m.u0();
                            throw null;
                        }
                    }
                }
            }
            if (i7 % 2 == 1) {
                return true;
            }
        }
        return false;
    }

    public final boolean isPolyOverlapsPoly(Point[] pointArr, Point[] pointArr2, double d) {
        j.e(pointArr, "sourcePoly");
        j.e(pointArr2, "targetPoly");
        if (((double) calcIntersectionRatio(polyToRect(pointArr), polyToRect(pointArr2))) > d) {
            return true;
        }
        return false;
    }

    public final boolean isVertical(Point[] pointArr) {
        float f;
        j.e(pointArr, "poly");
        Point point = pointArr[0];
        Point point2 = pointArr[1];
        try {
            f = Math.abs((float) Math.toDegrees(Math.atan2((double) (point2.y - point.y), (double) (point2.x - point.x))));
        } catch (NumberFormatException unused) {
            LibLogger.e("PointUtil", "Negative or Positive Infinity");
            f = 90.0f;
        }
        double d = (double) f;
        if (45.0d > d || d > 135.0d) {
            return false;
        }
        return true;
    }

    public final Rect polyToRect(Point[] pointArr) {
        j.e(pointArr, "poly");
        if (pointArr.length != 0) {
            int i2 = pointArr[0].x;
            int i7 = 1;
            int length = pointArr.length - 1;
            if (1 <= length) {
                int i8 = 1;
                while (true) {
                    int i10 = pointArr[i8].x;
                    if (i2 > i10) {
                        i2 = i10;
                    }
                    if (i8 == length) {
                        break;
                    }
                    i8++;
                }
            }
            if (pointArr.length != 0) {
                int i11 = pointArr[0].y;
                int length2 = pointArr.length - 1;
                if (1 <= length2) {
                    int i12 = 1;
                    while (true) {
                        int i13 = pointArr[i12].y;
                        if (i11 > i13) {
                            i11 = i13;
                        }
                        if (i12 == length2) {
                            break;
                        }
                        i12++;
                    }
                }
                if (pointArr.length != 0) {
                    int i14 = pointArr[0].x;
                    int length3 = pointArr.length - 1;
                    if (1 <= length3) {
                        int i15 = 1;
                        while (true) {
                            int i16 = pointArr[i15].x;
                            if (i14 < i16) {
                                i14 = i16;
                            }
                            if (i15 == length3) {
                                break;
                            }
                            i15++;
                        }
                    }
                    if (pointArr.length != 0) {
                        int i17 = pointArr[0].y;
                        int length4 = pointArr.length - 1;
                        if (1 <= length4) {
                            while (true) {
                                int i18 = pointArr[i7].y;
                                if (i17 < i18) {
                                    i17 = i18;
                                }
                                if (i7 == length4) {
                                    break;
                                }
                                i7++;
                            }
                        }
                        return new Rect(i2, i11, i14, i17);
                    }
                    throw new NoSuchElementException();
                }
                throw new NoSuchElementException();
            }
            throw new NoSuchElementException();
        }
        throw new NoSuchElementException();
    }
}
