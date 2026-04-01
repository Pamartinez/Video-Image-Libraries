package com.samsung.android.imagetranslation.util;

import A.a;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import com.samsung.android.imagetranslation.common.LTTLogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SceneTextUtil {
    private static final float ENLARGEMENT_RATIO = 1.0f;
    private static final int FONT_WEIGHT = 500;
    private static final String TAG = "SceneTextUtil";

    public static void addLangToMap(ArrayList<String> arrayList, LinkedHashMap<String, Integer> linkedHashMap) {
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (!"".equals(next)) {
                Integer num = linkedHashMap.get(next);
                int i2 = 1;
                if (num != null) {
                    i2 = 1 + num.intValue();
                }
                linkedHashMap.put(next, Integer.valueOf(i2));
            }
        }
    }

    public static Matrix generateRotationMatrix(int i2, int i7, float f, int i8) {
        float[] fArr;
        float[] fArr2;
        float[] fArr3;
        float[] fArr4;
        String str = TAG;
        StringBuilder h5 = a.h(i2, i7, "generateRotationMatrix IN  : Width=", " Height=", " ResizeRatio=");
        h5.append(f);
        h5.append(" Orientation=");
        h5.append(i8);
        LTTLogger.d(str, h5.toString());
        float f5 = (float) i2;
        int i10 = (int) ((f5 * 1.0f) / f);
        float f8 = (float) i7;
        int i11 = (int) ((1.0f * f8) / f);
        StringBuilder h6 = a.h(i10, i11, "generateRotationMatrix OUT : Width=", " Height=", " ResizeRatio=");
        h6.append(f);
        h6.append(" Orientation=");
        h6.append(i8);
        LTTLogger.d(str, h6.toString());
        if (i8 == 0) {
            fArr3 = new float[]{0.0f, 0.0f, f5, 0.0f, f5, f8, 0.0f, f8};
            float f10 = (float) i10;
            float f11 = (float) i11;
            fArr4 = new float[]{0.0f, 0.0f, f10, 0.0f, f10, f11, 0.0f, f11};
        } else if (i8 == 90) {
            fArr3 = new float[]{f5, 0.0f, f5, f8, 0.0f, f8, 0.0f, 0.0f};
            float f12 = (float) i11;
            float f13 = (float) i10;
            fArr4 = new float[]{0.0f, 0.0f, f12, 0.0f, f12, f13, 0.0f, f13};
        } else if (i8 == 180) {
            fArr3 = new float[]{f5, f8, 0.0f, f8, 0.0f, 0.0f, f5, 0.0f};
            float f14 = (float) i10;
            float f15 = (float) i11;
            fArr4 = new float[]{0.0f, 0.0f, f14, 0.0f, f14, f15, 0.0f, f15};
        } else if (i8 != 270) {
            fArr2 = null;
            fArr = null;
            Matrix matrix = new Matrix();
            matrix.setPolyToPoly(fArr2, 0, fArr, 0, 4);
            return matrix;
        } else {
            fArr3 = new float[]{0.0f, f8, 0.0f, 0.0f, f5, 0.0f, f5, f8};
            float f16 = (float) i11;
            float f17 = (float) i10;
            fArr4 = new float[]{0.0f, 0.0f, f16, 0.0f, f16, f17, 0.0f, f17};
        }
        fArr = fArr4;
        fArr2 = fArr3;
        Matrix matrix2 = new Matrix();
        matrix2.setPolyToPoly(fArr2, 0, fArr, 0, 4);
        return matrix2;
    }

    public static Point getCeneterPoint(Point[] pointArr) {
        int i2 = 0;
        int i7 = 0;
        for (Point point : pointArr) {
            i2 += point.x;
            i7 += point.y;
        }
        return new Point(i2 / 4, i7 / 4);
    }

    public static int getLongestLine(ArrayList<String> arrayList, Paint paint) {
        int textWidth;
        int i2 = 0;
        int i7 = 0;
        for (int i8 = 0; i8 < arrayList.size(); i8++) {
            if (((double) arrayList.get(i8).length()) >= ((double) arrayList.get(i2).length()) * 0.8d && (textWidth = getTextWidth(arrayList.get(i8), paint)) > i7) {
                i2 = i8;
                i7 = textWidth;
            }
        }
        return i2;
    }

    public static int getMaxTextSize(ArrayList<String> arrayList, Paint paint) {
        Iterator<String> it = arrayList.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            i2 = Math.max(i2, getTextHeight(it.next(), paint));
        }
        return i2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x00b0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.util.Size getOptimalLineMaskSize(android.graphics.Point[] r13, java.lang.String r14, int r15) {
        /*
            r0 = 4
            int[] r1 = new int[r0]
            int[] r2 = new int[r0]
            r3 = 0
            r4 = 1
            r5 = 2
            r6 = 3
            int[] r7 = new int[]{r3, r4, r5, r6, r3}
            r8 = r3
        L_0x000e:
            if (r8 >= r0) goto L_0x003c
            r9 = r7[r8]
            r9 = r13[r9]
            int r9 = r9.x
            int r10 = r8 + 1
            r11 = r7[r10]
            r11 = r13[r11]
            int r11 = r11.x
            int r9 = r9 - r11
            int r9 = java.lang.Math.abs(r9)
            int r9 = r9 + r4
            r1[r8] = r9
            r9 = r7[r8]
            r9 = r13[r9]
            int r9 = r9.y
            r11 = r7[r10]
            r11 = r13[r11]
            int r11 = r11.y
            int r9 = r9 - r11
            int r9 = java.lang.Math.abs(r9)
            int r9 = r9 + r4
            r2[r8] = r9
            r8 = r10
            goto L_0x000e
        L_0x003c:
            float[] r13 = new float[r0]
            r7 = 1621981420(0x60ad78ec, float:1.0E20)
            r8 = r3
            r9 = r8
        L_0x0043:
            if (r8 >= r0) goto L_0x005d
            r10 = r1[r8]
            int r10 = r10 * r10
            r11 = r2[r8]
            int r11 = r11 * r11
            int r11 = r11 + r10
            double r10 = (double) r11
            double r10 = java.lang.Math.sqrt(r10)
            float r10 = (float) r10
            r13[r8] = r10
            int r11 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r11 >= 0) goto L_0x005a
            r9 = r8
            r7 = r10
        L_0x005a:
            int r8 = r8 + 1
            goto L_0x0043
        L_0x005d:
            int r14 = r14.length()
            r1 = 1073741824(0x40000000, float:2.0)
            r2 = 1065353216(0x3f800000, float:1.0)
            if (r14 <= r5) goto L_0x0080
            int r14 = r9 + 1
            int r14 = r14 % r0
            r14 = r13[r14]
            int r15 = r9 + 3
            int r15 = r15 % r0
            r15 = r13[r15]
            float r14 = r14 + r15
            float r14 = r14 * r2
            float r14 = r14 / r1
            int r14 = (int) r14
            r15 = r13[r9]
            int r9 = r9 + r5
            int r9 = r9 % r0
            r13 = r13[r9]
        L_0x007b:
            float r15 = r15 + r13
            float r15 = r15 * r2
            float r15 = r15 / r1
            int r13 = (int) r15
            goto L_0x00ad
        L_0x0080:
            if (r15 == 0) goto L_0x00a0
            r14 = 180(0xb4, float:2.52E-43)
            if (r15 != r14) goto L_0x0087
            goto L_0x00a0
        L_0x0087:
            r14 = 90
            if (r15 == r14) goto L_0x0093
            r14 = 270(0x10e, float:3.78E-43)
            if (r15 != r14) goto L_0x0090
            goto L_0x0093
        L_0x0090:
            r14 = -1
            r13 = r14
            goto L_0x00ad
        L_0x0093:
            r14 = r13[r4]
            r15 = r13[r6]
            float r14 = r14 + r15
            float r14 = r14 * r2
            float r14 = r14 / r1
            int r14 = (int) r14
            r15 = r13[r3]
            r13 = r13[r5]
            goto L_0x007b
        L_0x00a0:
            r14 = r13[r3]
            r15 = r13[r5]
            float r14 = r14 + r15
            float r14 = r14 * r2
            float r14 = r14 / r1
            int r14 = (int) r14
            r15 = r13[r4]
            r13 = r13[r6]
            goto L_0x007b
        L_0x00ad:
            if (r14 >= r13) goto L_0x00b0
            goto L_0x00b3
        L_0x00b0:
            r12 = r14
            r14 = r13
            r13 = r12
        L_0x00b3:
            android.util.Size r15 = new android.util.Size
            r15.<init>(r13, r14)
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.imagetranslation.util.SceneTextUtil.getOptimalLineMaskSize(android.graphics.Point[], java.lang.String, int):android.util.Size");
    }

    public static Paint getTextAlignedPaint(int i2) {
        Paint paint = new Paint();
        if (i2 == 0) {
            paint.setTextAlign(Paint.Align.LEFT);
        } else if (i2 == 1) {
            paint.setTextAlign(Paint.Align.CENTER);
        } else if (i2 != 2) {
            LTTLogger.e(TAG, "Align mode is not correct");
        } else {
            paint.setTextAlign(Paint.Align.RIGHT);
        }
        paint.setColor(-1);
        paint.setAntiAlias(true);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, 500, false));
        return paint;
    }

    public static int getTextBottom(String str, Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect.bottom;
    }

    public static int getTextHeight(String str, Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect.height();
    }

    public static int getTextWidth(String str, Paint paint) {
        Rect rect = new Rect();
        paint.getTextBounds(str, 0, str.length(), rect);
        return rect.width();
    }

    public static Point[] getTrsTextMaskPoly(int i2, int i7, int i8, int i10) {
        Point[] pointArr = new Point[4];
        for (int i11 = 0; i11 < 4; i11++) {
            pointArr[i11] = new Point();
        }
        if (i2 == 0) {
            Point point = pointArr[0];
            point.x = 0;
            point.y = i7;
            Point point2 = pointArr[1];
            int i12 = i8 - 1;
            point2.x = i12;
            point2.y = i7;
            Point point3 = pointArr[2];
            point3.x = i12;
            int i13 = (i7 + i10) - 1;
            point3.y = i13;
            Point point4 = pointArr[3];
            point4.x = 0;
            point4.y = i13;
            return pointArr;
        } else if (i2 == 90) {
            Point point5 = pointArr[0];
            int i14 = i8 - 1;
            point5.x = i14;
            point5.y = i7;
            Point point6 = pointArr[1];
            point6.x = i14;
            int i15 = (i10 + i7) - 1;
            point6.y = i15;
            Point point7 = pointArr[2];
            point7.x = 0;
            point7.y = i15;
            Point point8 = pointArr[3];
            point8.x = 0;
            point8.y = i7;
            return pointArr;
        } else if (i2 == 180) {
            Point point9 = pointArr[0];
            int i16 = i8 - 1;
            point9.x = i16;
            int i17 = (i10 + i7) - 1;
            point9.y = i17;
            Point point10 = pointArr[1];
            point10.x = 0;
            point10.y = i17;
            Point point11 = pointArr[2];
            point11.x = 0;
            point11.y = i7;
            Point point12 = pointArr[3];
            point12.x = i16;
            point12.y = i7;
            return pointArr;
        } else if (i2 != 270) {
            LTTLogger.e(TAG, "Device orientation is not correct");
            return pointArr;
        } else {
            Point point13 = pointArr[0];
            point13.x = 0;
            int i18 = (i10 + i7) - 1;
            point13.y = i18;
            Point point14 = pointArr[1];
            point14.x = 0;
            point14.y = i7;
            Point point15 = pointArr[2];
            int i19 = i8 - 1;
            point15.x = i19;
            point15.y = i7;
            Point point16 = pointArr[3];
            point16.x = i19;
            point16.y = i18;
            return pointArr;
        }
    }

    private static Point[] reArrangePoly(Point[] pointArr, int i2) {
        int i7;
        if (i2 == 0) {
            return pointArr;
        }
        if (i2 == 90) {
            i7 = -1;
        } else if (i2 == 180) {
            i7 = 2;
        } else if (i2 != 270) {
            i7 = 0;
        } else {
            i7 = 1;
        }
        List asList = Arrays.asList(pointArr);
        Collections.rotate(asList, i7);
        return (Point[]) asList.toArray(new Point[asList.size()]);
    }

    public static Point[] rotatePoly(Point[] pointArr, String str, Matrix matrix, int i2) {
        float[] fArr = new float[(pointArr.length * 2)];
        float[] fArr2 = new float[(pointArr.length * 2)];
        for (int i7 = 0; i7 < pointArr.length; i7++) {
            int i8 = i7 * 2;
            Point point = pointArr[i7];
            fArr[i8] = (float) point.x;
            fArr[i8 + 1] = (float) point.y;
        }
        matrix.mapPoints(fArr2, fArr);
        int length = pointArr.length;
        Point[] pointArr2 = new Point[length];
        for (int i10 = 0; i10 < length; i10++) {
            int i11 = i10 * 2;
            pointArr2[i10] = new Point((int) fArr2[i11], (int) fArr2[i11 + 1]);
        }
        return reArrangePoly(pointArr2, i2);
    }

    public static int getLongestLine(String[] strArr, Paint paint) {
        int textWidth;
        int i2 = 0;
        int i7 = 0;
        for (int i8 = 0; i8 < strArr.length; i8++) {
            if (((double) strArr[i8].length()) >= ((double) strArr[i2].length()) * 0.8d && (textWidth = getTextWidth(strArr[i8], paint)) > i7) {
                i2 = i8;
                i7 = textWidth;
            }
        }
        return i2;
    }
}
