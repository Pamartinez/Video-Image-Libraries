package com.samsung.android.livetranslation.util;

import android.graphics.Point;
import android.graphics.PointF;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.livetranslation.geometry.SmallestSurroundingRectangle;
import com.samsung.android.sdk.ocr.OCRResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OCRResultUtils {
    private static final boolean DEBUGLOG_ENABLE = false;
    private static final String TAG = "OCRResultUtils";
    protected static final int THRESHOLD_HEIGHT = 200;
    protected static final int THRESHOLD_HEIGHT_SMALL = 170;
    protected static final int THRESHOLD_WIDTH = 30;
    protected static final int THRESHOLD_WIDTH_MAX = 5;

    public static Point[] calculateRotatedOrderedRect(ArrayList<OCRResult.LineData> arrayList) {
        if (arrayList.size() == 1) {
            return arrayList.get(0).getRect();
        }
        Point[] calculateRotatedRect = calculateRotatedRect(arrayList);
        if (isDebugLTTLoggerEnable()) {
            LTTLogger.e(TAG, "calculateRotatedOrderedRect : E " + arrayList.size());
            int length = calculateRotatedRect.length;
            for (int i2 = 0; i2 < length; i2++) {
                LTTLogger.e(TAG, "calculateRotatedOrderedRect result : " + calculateRotatedRect[i2]);
            }
        }
        double[] dArr = new double[4];
        Iterator<OCRResult.LineData> it = arrayList.iterator();
        while (it.hasNext()) {
            OCRResult.LineData next = it.next();
            for (int i7 = 0; i7 < 4; i7++) {
                dArr[i7] = getDistance(calculateRotatedRect[i7], next.getRect()[0]) + dArr[i7];
            }
        }
        if (isDebugLTTLoggerEnable()) {
            for (int i8 = 0; i8 < 4; i8++) {
                LTTLogger.e(TAG, "calculateRotatedOrderedRect distance : " + dArr[i8]);
            }
        }
        double d = dArr[0];
        int i10 = 0;
        for (int i11 = 1; i11 < 4; i11++) {
            double d2 = dArr[i11];
            if (d2 < d) {
                i10 = i11;
                d = d2;
            }
        }
        Point[] pointArr = new Point[4];
        for (int i12 = 0; i12 < 4; i12++) {
            pointArr[i12] = calculateRotatedRect[(i12 + i10) % 4];
        }
        if (isDebugLTTLoggerEnable()) {
            for (int i13 = 0; i13 < 4; i13++) {
                LTTLogger.d(TAG, "calculateRotatedOrderedRect finalResultPoints : " + pointArr[i13]);
            }
        }
        return pointArr;
    }

    /* JADX WARNING: type inference failed for: r2v1, types: [java.lang.Object, java.util.function.IntFunction] */
    public static Point[] calculateRotatedRect(ArrayList<OCRResult.LineData> arrayList) {
        if (arrayList.size() == 1) {
            return arrayList.get(0).getRect();
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator<OCRResult.LineData> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.addAll(Arrays.asList((PointF[]) Arrays.stream(it.next().getRect()).map(new a(1)).toArray(new Object())));
        }
        return SmallestSurroundingRectangle.computePolys(arrayList2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x0317 A[LOOP:2: B:15:0x00b0->B:55:0x0317, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x030f A[EDGE_INSN: B:60:0x030f->B:54:0x030f ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void convertOCRResult(com.samsung.android.sdk.ocr.OCRResult r27) {
        /*
            java.lang.String r0 = "convertOCRResult E"
            java.lang.String r1 = "OCRResultUtils"
            com.samsung.android.livetranslation.util.LTTLogger.i(r1, r0)
            java.util.ArrayList r0 = r27.getBlockDataList()
            if (r0 == 0) goto L_0x0357
            boolean r2 = r0.isEmpty()
            if (r2 == 0) goto L_0x0015
            goto L_0x0357
        L_0x0015:
            int r2 = r0.size()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r4 = r0.iterator()
        L_0x0022:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0323
            java.lang.Object r5 = r4.next()
            com.samsung.android.sdk.ocr.OCRResult$BlockData r5 = (com.samsung.android.sdk.ocr.OCRResult.BlockData) r5
            boolean r6 = isDebugLTTLoggerEnable()
            java.lang.String r7 = "]: "
            java.lang.String r8 = "]w["
            java.lang.String r9 = "]h["
            if (r6 == 0) goto L_0x0093
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r11 = "block a["
            r6.<init>(r11)
            float r11 = r5.getAngle()
            r6.append(r11)
            r6.append(r8)
            android.graphics.Point[] r11 = r5.getRect()
            int r11 = getWidthDistance(r11)
            r6.append(r11)
            r6.append(r9)
            android.graphics.Point[] r11 = r5.getRect()
            int r11 = getHeightDistance(r11)
            r6.append(r11)
            r6.append(r7)
            java.lang.String r11 = r5.getText()
            r6.append(r11)
            java.lang.String r6 = r6.toString()
            com.samsung.android.livetranslation.util.LTTLogger.e(r1, r6)
            android.graphics.Point[] r6 = r5.getRect()
            int r11 = r6.length
            r12 = 0
        L_0x007b:
            if (r12 >= r11) goto L_0x0093
            r13 = r6[r12]
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            java.lang.String r15 = "o blockPoint : "
            r14.<init>(r15)
            r14.append(r13)
            java.lang.String r13 = r14.toString()
            com.samsung.android.livetranslation.util.LTTLogger.e(r1, r13)
            int r12 = r12 + 1
            goto L_0x007b
        L_0x0093:
            android.graphics.Point[] r6 = r5.getRect()
            int r6 = getWidthDistance(r6)
            int r11 = r6 * 30
            r12 = 100
            int r11 = r11 / r12
            int r13 = r6 * 5
            int r13 = r13 / r12
            java.util.ArrayList r14 = r5.getLineDataList()
            if (r14 != 0) goto L_0x00ab
            goto L_0x0357
        L_0x00ab:
            int r15 = r14.size()
            r10 = 0
        L_0x00b0:
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            java.lang.Object r17 = r14.get(r10)
            r18 = r0
            r0 = r17
            com.samsung.android.sdk.ocr.OCRResult$LineData r0 = (com.samsung.android.sdk.ocr.OCRResult.LineData) r0
            r12.add(r0)
            boolean r0 = isDebugLTTLoggerEnable()
            r17 = r0
            java.lang.String r0 = "linePoint : "
            r19 = r4
            java.lang.String r4 = "] : "
            r20 = r6
            java.lang.String r6 = "block.lines a["
            if (r17 == 0) goto L_0x0153
            r17 = r2
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r6)
            java.lang.Object r21 = r14.get(r10)
            com.samsung.android.sdk.ocr.OCRResult$LineData r21 = (com.samsung.android.sdk.ocr.OCRResult.LineData) r21
            r22 = r7
            float r7 = r21.getAngle()
            r2.append(r7)
            r2.append(r8)
            java.lang.Object r7 = r14.get(r10)
            com.samsung.android.sdk.ocr.OCRResult$LineData r7 = (com.samsung.android.sdk.ocr.OCRResult.LineData) r7
            android.graphics.Point[] r7 = r7.getRect()
            int r7 = getWidthDistance(r7)
            r2.append(r7)
            r2.append(r9)
            java.lang.Object r7 = r14.get(r10)
            com.samsung.android.sdk.ocr.OCRResult$LineData r7 = (com.samsung.android.sdk.ocr.OCRResult.LineData) r7
            android.graphics.Point[] r7 = r7.getRect()
            int r7 = getHeightDistance(r7)
            r2.append(r7)
            r2.append(r4)
            java.lang.Object r7 = r14.get(r10)
            com.samsung.android.sdk.ocr.OCRResult$LineData r7 = (com.samsung.android.sdk.ocr.OCRResult.LineData) r7
            java.lang.String r7 = r7.getText()
            r2.append(r7)
            java.lang.String r2 = r2.toString()
            com.samsung.android.livetranslation.util.LTTLogger.e(r1, r2)
            java.lang.Object r2 = r14.get(r10)
            com.samsung.android.sdk.ocr.OCRResult$LineData r2 = (com.samsung.android.sdk.ocr.OCRResult.LineData) r2
            android.graphics.Point[] r2 = r2.getRect()
            int r7 = r2.length
            r21 = r2
            r2 = 0
        L_0x0137:
            if (r2 >= r7) goto L_0x0157
            r23 = r2
            r2 = r21[r23]
            r24 = r7
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>(r0)
            r7.append(r2)
            java.lang.String r2 = r7.toString()
            com.samsung.android.livetranslation.util.LTTLogger.e(r1, r2)
            int r2 = r23 + 1
            r7 = r24
            goto L_0x0137
        L_0x0153:
            r17 = r2
            r22 = r7
        L_0x0157:
            int r10 = r10 + 1
            r2 = r10
        L_0x015a:
            if (r10 >= r15) goto L_0x0266
            java.lang.Object r7 = r14.get(r10)
            com.samsung.android.sdk.ocr.OCRResult$LineData r7 = (com.samsung.android.sdk.ocr.OCRResult.LineData) r7
            android.graphics.Point[] r7 = r7.getRect()
            int r7 = getWidthDistance(r7)
            r21 = r7
            int r7 = r10 + -1
            java.lang.Object r23 = r14.get(r7)
            com.samsung.android.sdk.ocr.OCRResult$LineData r23 = (com.samsung.android.sdk.ocr.OCRResult.LineData) r23
            android.graphics.Point[] r23 = r23.getRect()
            int r23 = getWidthDistance(r23)
            java.lang.Object r24 = r14.get(r10)
            com.samsung.android.sdk.ocr.OCRResult$LineData r24 = (com.samsung.android.sdk.ocr.OCRResult.LineData) r24
            android.graphics.Point[] r24 = r24.getRect()
            r25 = r2
            int r2 = getHeightDistance(r24)
            java.lang.Object r7 = r14.get(r7)
            com.samsung.android.sdk.ocr.OCRResult$LineData r7 = (com.samsung.android.sdk.ocr.OCRResult.LineData) r7
            android.graphics.Point[] r7 = r7.getRect()
            int r7 = getHeightDistance(r7)
            r24 = r15
            int r15 = java.lang.Math.max(r2, r7)
            int r2 = java.lang.Math.min(r2, r7)
            int r7 = r20 - r23
            r23 = r2
            if (r7 <= r13) goto L_0x01b0
            int r2 = r20 - r21
            if (r2 <= r11) goto L_0x01b0
            goto L_0x026a
        L_0x01b0:
            if (r7 <= r11) goto L_0x01b4
            goto L_0x026a
        L_0x01b4:
            int r2 = r15 * 100
            int r2 = r2 / r23
            r7 = 200(0xc8, float:2.8E-43)
            if (r2 <= r7) goto L_0x01be
            goto L_0x026a
        L_0x01be:
            r7 = 100
            if (r15 >= r7) goto L_0x01c8
            r15 = 170(0xaa, float:2.38E-43)
            if (r2 <= r15) goto L_0x01c8
            goto L_0x026a
        L_0x01c8:
            java.lang.Object r2 = r14.get(r10)
            com.samsung.android.sdk.ocr.OCRResult$LineData r2 = (com.samsung.android.sdk.ocr.OCRResult.LineData) r2
            r12.add(r2)
            int r2 = r25 + 1
            boolean r15 = isDebugLTTLoggerEnable()
            if (r15 == 0) goto L_0x0259
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>(r6)
            java.lang.Object r16 = r14.get(r10)
            com.samsung.android.sdk.ocr.OCRResult$LineData r16 = (com.samsung.android.sdk.ocr.OCRResult.LineData) r16
            float r7 = r16.getAngle()
            r15.append(r7)
            java.lang.String r7 = "] w["
            r15.append(r7)
            java.lang.Object r7 = r14.get(r10)
            com.samsung.android.sdk.ocr.OCRResult$LineData r7 = (com.samsung.android.sdk.ocr.OCRResult.LineData) r7
            android.graphics.Point[] r7 = r7.getRect()
            int r7 = getWidthDistance(r7)
            r15.append(r7)
            r15.append(r9)
            java.lang.Object r7 = r14.get(r10)
            com.samsung.android.sdk.ocr.OCRResult$LineData r7 = (com.samsung.android.sdk.ocr.OCRResult.LineData) r7
            android.graphics.Point[] r7 = r7.getRect()
            int r7 = getHeightDistance(r7)
            r15.append(r7)
            r15.append(r4)
            java.lang.Object r7 = r14.get(r10)
            com.samsung.android.sdk.ocr.OCRResult$LineData r7 = (com.samsung.android.sdk.ocr.OCRResult.LineData) r7
            java.lang.String r7 = r7.getText()
            r15.append(r7)
            java.lang.String r7 = r15.toString()
            com.samsung.android.livetranslation.util.LTTLogger.e(r1, r7)
            java.lang.Object r7 = r14.get(r10)
            com.samsung.android.sdk.ocr.OCRResult$LineData r7 = (com.samsung.android.sdk.ocr.OCRResult.LineData) r7
            android.graphics.Point[] r7 = r7.getRect()
            int r15 = r7.length
            r16 = r2
            r2 = 0
        L_0x023a:
            if (r2 >= r15) goto L_0x0256
            r23 = r2
            r2 = r7[r23]
            r26 = r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r0)
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            com.samsung.android.livetranslation.util.LTTLogger.e(r1, r2)
            int r2 = r23 + 1
            r4 = r26
            goto L_0x023a
        L_0x0256:
            r26 = r4
            goto L_0x025c
        L_0x0259:
            r16 = r2
            goto L_0x0256
        L_0x025c:
            int r10 = r10 + 1
            r2 = r16
            r15 = r24
            r4 = r26
            goto L_0x015a
        L_0x0266:
            r25 = r2
            r24 = r15
        L_0x026a:
            java.util.ArrayList r0 = r5.getLineDataList()
            int r0 = r0.size()
            int r2 = r12.size()
            if (r0 != r2) goto L_0x0283
            r3.add(r5)
        L_0x027b:
            r4 = r22
        L_0x027d:
            r0 = r24
            r10 = r25
            goto L_0x030d
        L_0x0283:
            com.samsung.android.sdk.ocr.OCRResult$BlockData r0 = new com.samsung.android.sdk.ocr.OCRResult$BlockData
            r0.<init>()
            android.graphics.Point[] r2 = calculateRotatedOrderedRect(r12)
            r0.setRect(r2)
            float r2 = r5.getAngle()
            r0.setAngle(r2)
            r0.setLineDataList(r12)
            r3.add(r0)
            boolean r2 = isDebugLTTLoggerEnable()
            if (r2 == 0) goto L_0x027b
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r4 = "newBlock a["
            r2.<init>(r4)
            float r4 = r0.getAngle()
            r2.append(r4)
            java.lang.String r4 = " / "
            r2.append(r4)
            android.graphics.Point[] r4 = r0.getRect()
            double r6 = getOrientationWithPoly(r4)
            r2.append(r6)
            r2.append(r8)
            android.graphics.Point[] r4 = r0.getRect()
            int r4 = getWidthDistance(r4)
            r2.append(r4)
            r2.append(r9)
            android.graphics.Point[] r4 = r0.getRect()
            int r4 = getHeightDistance(r4)
            r2.append(r4)
            r4 = r22
            r2.append(r4)
            java.lang.String r6 = r0.getText()
            r2.append(r6)
            java.lang.String r2 = r2.toString()
            com.samsung.android.livetranslation.util.LTTLogger.e(r1, r2)
            android.graphics.Point[] r0 = r0.getRect()
            int r2 = r0.length
            r6 = 0
        L_0x02f5:
            if (r6 >= r2) goto L_0x027d
            r7 = r0[r6]
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r12 = "newBlock : "
            r10.<init>(r12)
            r10.append(r7)
            java.lang.String r7 = r10.toString()
            com.samsung.android.livetranslation.util.LTTLogger.e(r1, r7)
            int r6 = r6 + 1
            goto L_0x02f5
        L_0x030d:
            if (r10 < r0) goto L_0x0317
            r2 = r17
            r0 = r18
            r4 = r19
            goto L_0x0022
        L_0x0317:
            r15 = r0
            r7 = r4
            r2 = r17
            r0 = r18
            r4 = r19
            r6 = r20
            goto L_0x00b0
        L_0x0323:
            r18 = r0
            r17 = r2
            r18.clear()
            r0 = r27
            r0.setBlockDataList(r3)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "convertOCRResult : X ["
            r2.<init>(r3)
            r3 = r17
            r2.append(r3)
            java.lang.String r3 = "=>"
            r2.append(r3)
            java.util.ArrayList r0 = r0.getBlockDataList()
            int r0 = r0.size()
            r2.append(r0)
            java.lang.String r0 = "]"
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.samsung.android.livetranslation.util.LTTLogger.i(r1, r0)
        L_0x0357:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.livetranslation.util.OCRResultUtils.convertOCRResult(com.samsung.android.sdk.ocr.OCRResult):void");
    }

    public static double getDistance(Point point, Point point2) {
        return Math.sqrt(Math.pow((double) (point2.y - point.y), 2.0d) + Math.pow((double) (point2.x - point.x), 2.0d));
    }

    public static int getHeightDistance(Point[] pointArr) {
        if (pointArr.length == 0) {
            return 0;
        }
        return (int) Math.max(getDistance(pointArr[0], pointArr[3]), getDistance(pointArr[1], pointArr[2]));
    }

    private static double getOrientationWithPoly(Point[] pointArr) {
        if (pointArr.length != 4) {
            return MapUtil.INVALID_LOCATION;
        }
        Point point = pointArr[0];
        Point point2 = pointArr[1];
        Point point3 = new Point();
        int i2 = point2.x - point.x;
        point3.x = i2;
        point3.y = point2.y - point.y;
        double degrees = Math.toDegrees(Math.acos(((double) point3.x) / Math.sqrt(Math.pow((double) point3.y, 2.0d) + Math.pow((double) i2, 2.0d))));
        if (point3.y < 0) {
            degrees = 360.0d - degrees;
        }
        return 360.0d - degrees;
    }

    public static int getWidthDistance(Point[] pointArr) {
        if (pointArr.length == 0) {
            return 0;
        }
        return (int) Math.max(getDistance(pointArr[0], pointArr[1]), getDistance(pointArr[2], pointArr[3]));
    }

    public static boolean isDebugLTTLoggerEnable() {
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ PointF lambda$calculateRotatedRect$0(Point point) {
        return new PointF((float) point.x, (float) point.y);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ PointF[] lambda$calculateRotatedRect$1(int i2) {
        return new PointF[i2];
    }
}
