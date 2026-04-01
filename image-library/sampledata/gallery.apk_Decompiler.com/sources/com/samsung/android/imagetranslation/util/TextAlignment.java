package com.samsung.android.imagetranslation.util;

import android.graphics.Point;
import com.samsung.android.imagetranslation.common.LTTLogger;
import com.samsung.android.imagetranslation.jni.SceneText;
import java.util.concurrent.CopyOnWriteArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TextAlignment {
    public static final int ALIGN_CENTER = 1;
    public static final int ALIGN_LEFT = 0;
    public static final int ALIGN_RIGHT = 2;
    private static final String TAG = "TextAlignment";

    /* JADX WARNING: type inference failed for: r4v5, types: [boolean[]] */
    /* JADX WARNING: type inference failed for: r6v0, types: [boolean] */
    /* JADX WARNING: type inference failed for: r27v2, types: [boolean] */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00fb, code lost:
        r27 = r27;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00fb, code lost:
        r27 = r27;
     */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean[] detectOutliers(com.samsung.android.imagetranslation.jni.SceneText r32) {
        /*
            java.util.concurrent.CopyOnWriteArrayList r0 = r32.getComponents()
            r1 = 0
            java.lang.Object r0 = r0.get(r1)
            com.samsung.android.imagetranslation.jni.SceneText r0 = (com.samsung.android.imagetranslation.jni.SceneText) r0
            android.graphics.Point[] r0 = r0.getPoly()
            r2 = r0[r1]
            int r2 = r2.x
            r3 = 1
            r4 = r0[r3]
            int r4 = r4.x
            int r2 = r2 - r4
            int r2 = java.lang.Math.abs(r2)
            r4 = r0[r1]
            int r4 = r4.y
            r0 = r0[r3]
            int r0 = r0.y
            int r4 = r4 - r0
            int r0 = java.lang.Math.abs(r4)
            if (r0 < r2) goto L_0x002e
            r0 = r3
            goto L_0x002f
        L_0x002e:
            r0 = r1
        L_0x002f:
            java.util.concurrent.CopyOnWriteArrayList r2 = r32.getComponents()
            int r2 = r2.size()
            boolean[] r4 = new boolean[r2]
            java.util.Arrays.fill(r4, r3)
            r5 = r1
        L_0x003d:
            if (r5 >= r2) goto L_0x0113
            boolean r6 = r4[r5]
            if (r6 != 0) goto L_0x004d
        L_0x0043:
            r31 = r0
            r27 = r1
            r28 = r2
            r30 = r3
            goto L_0x0107
        L_0x004d:
            r6 = r1
        L_0x004e:
            if (r6 >= r2) goto L_0x0043
            if (r5 == r6) goto L_0x00f3
            java.util.concurrent.CopyOnWriteArrayList r7 = r32.getComponents()
            java.lang.Object r7 = r7.get(r5)
            com.samsung.android.imagetranslation.jni.SceneText r7 = (com.samsung.android.imagetranslation.jni.SceneText) r7
            java.util.concurrent.CopyOnWriteArrayList r8 = r32.getComponents()
            java.lang.Object r8 = r8.get(r6)
            com.samsung.android.imagetranslation.jni.SceneText r8 = (com.samsung.android.imagetranslation.jni.SceneText) r8
            android.graphics.Point[] r7 = r7.getPoly()
            android.graphics.Point[] r8 = r8.getPoly()
            r9 = r8[r1]
            int r10 = r9.x
            double r11 = (double) r10
            int r9 = r9.y
            double r13 = (double) r9
            r9 = r8[r3]
            int r10 = r9.x
            r27 = r1
            r28 = r2
            double r1 = (double) r10
            int r9 = r9.y
            double r9 = (double) r9
            r29 = 2
            r15 = r8[r29]
            r30 = r3
            int r3 = r15.x
            r31 = r0
            r16 = r1
            double r0 = (double) r3
            int r2 = r15.y
            double r2 = (double) r2
            r15 = 3
            r8 = r8[r15]
            int r15 = r8.x
            r19 = r0
            double r0 = (double) r15
            int r8 = r8.y
            r23 = r0
            double r0 = (double) r8
            r25 = r0
            r21 = r2
            r15 = r16
            r17 = r9
            int[] r0 = getRotatedRectangleCenter(r11, r13, r15, r17, r19, r21, r23, r25)
            r1 = r0[r27]
            r0 = r0[r30]
            if (r31 != 0) goto L_0x00d2
            r1 = r7[r30]
            int r1 = r1.y
            r2 = r7[r29]
            int r2 = r2.y
            int r1 = java.lang.Math.min(r1, r2)
            r2 = r7[r30]
            int r2 = r2.y
            r3 = r7[r29]
            int r3 = r3.y
            int r2 = java.lang.Math.max(r2, r3)
            if (r0 < r1) goto L_0x00fb
            if (r0 > r2) goto L_0x00fb
            r4[r5] = r27
            r4[r6] = r27
            goto L_0x00fb
        L_0x00d2:
            r0 = r7[r30]
            int r0 = r0.x
            r2 = r7[r29]
            int r2 = r2.x
            int r0 = java.lang.Math.min(r0, r2)
            r2 = r7[r30]
            int r2 = r2.x
            r3 = r7[r29]
            int r3 = r3.x
            int r2 = java.lang.Math.max(r2, r3)
            if (r1 < r0) goto L_0x00fb
            if (r1 > r2) goto L_0x00fb
            r4[r5] = r27
            r4[r6] = r27
            goto L_0x00fb
        L_0x00f3:
            r31 = r0
            r27 = r1
            r28 = r2
            r30 = r3
        L_0x00fb:
            int r6 = r6 + 1
            r1 = r27
            r2 = r28
            r3 = r30
            r0 = r31
            goto L_0x004e
        L_0x0107:
            int r5 = r5 + 1
            r1 = r27
            r2 = r28
            r3 = r30
            r0 = r31
            goto L_0x003d
        L_0x0113:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.imagetranslation.util.TextAlignment.detectOutliers(com.samsung.android.imagetranslation.jni.SceneText):boolean[]");
    }

    public static double[] generateLineEquation(double d, double d2, double d3, double d5) {
        if (d == d3) {
            return new double[]{1.0d, 0.0d, -d};
        }
        double d6 = (d5 - d2) / (d3 - d);
        return new double[]{-d6, 1.0d, -((-(d * d6)) + d2)};
    }

    public static int getAlignmentOfBlock(SceneText sceneText) {
        int max;
        int i2;
        int i7;
        int i8;
        boolean[] detectOutliers = detectOutliers(sceneText);
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        int i10 = 1;
        for (int i11 = 1; i11 <= sceneText.getComponents().size(); i11++) {
            int i12 = i11 - 1;
            if (detectOutliers[i12]) {
                copyOnWriteArrayList.add(sceneText.getComponents().get(i12));
            }
        }
        LTTLogger.d(TAG, "detectOutliers: Lines to consider - " + copyOnWriteArrayList.size() + " out of " + sceneText.getComponents().size());
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        int i17 = 1;
        while (i17 <= copyOnWriteArrayList.size()) {
            if (i17 == i10) {
                i8 = i13;
                i7 = i10;
                i2 = i17;
            } else {
                Point[] poly = ((SceneText) copyOnWriteArrayList.get(i17 - 2)).getPoly();
                int i18 = i17 - 1;
                Point[] poly2 = ((SceneText) copyOnWriteArrayList.get(i18)).getPoly();
                Point point = poly2[i13];
                Point point2 = poly2[3];
                i8 = i13;
                i7 = i10;
                i2 = i17;
                int i19 = i18;
                double[] generateLineEquation = generateLineEquation((double) point.x, (double) point.y, (double) point2.x, (double) point2.y);
                Point point3 = poly2[i7];
                double d = (double) point3.x;
                double d2 = (double) point3.y;
                Point point4 = poly2[2];
                double[] generateLineEquation2 = generateLineEquation(d, d2, (double) point4.x, (double) point4.y);
                double d3 = generateLineEquation[i8];
                double d5 = generateLineEquation[i7];
                double d6 = generateLineEquation[2];
                Point point5 = poly[i8];
                double[] dArr = generateLineEquation2;
                float perpendicularDistanceOfPointFromLine = (float) getPerpendicularDistanceOfPointFromLine(d3, d5, d6, (double) point5.x, (double) point5.y);
                double d7 = dArr[i8];
                double d9 = dArr[i7];
                double d10 = dArr[2];
                Point point6 = poly[i7];
                float perpendicularDistanceOfPointFromLine2 = (float) getPerpendicularDistanceOfPointFromLine(d7, d9, d10, (double) point6.x, (double) point6.y);
                float abs = Math.abs(perpendicularDistanceOfPointFromLine - perpendicularDistanceOfPointFromLine2);
                int width = (int) (((double) sceneText.getWidth()) * 0.03d);
                String str = TAG;
                LTTLogger.h(str, "MIN_VARIATION" + width);
                LTTLogger.h(str, "Text : " + ((SceneText) copyOnWriteArrayList.get(i19)).getValue());
                LTTLogger.h(str, "LeftVariation - " + perpendicularDistanceOfPointFromLine + " RightVariation - " + perpendicularDistanceOfPointFromLine2 + " CenterVariation - " + abs);
                float f = (float) width;
                if (perpendicularDistanceOfPointFromLine > f || perpendicularDistanceOfPointFromLine2 > f) {
                    if (perpendicularDistanceOfPointFromLine < perpendicularDistanceOfPointFromLine2 && perpendicularDistanceOfPointFromLine < abs) {
                        i14++;
                    } else if (perpendicularDistanceOfPointFromLine2 >= perpendicularDistanceOfPointFromLine || perpendicularDistanceOfPointFromLine2 >= abs) {
                        i16++;
                    } else {
                        i15++;
                    }
                }
            }
            i17 = i2 + 1;
            i13 = i8;
            i10 = i7;
        }
        int i20 = i13;
        int i21 = i10;
        if (i14 + i15 + i16 < ((int) Math.ceil(((double) sceneText.getComponents().size()) * 0.3d)) || (max = Math.max(i14, Math.max(i15, i16))) == i14) {
            return i20;
        }
        if (max == i15) {
            return 2;
        }
        return i21;
    }

    public static double getPerpendicularDistanceOfPointFromLine(double d, double d2, double d3, double d5, double d6) {
        return Math.abs(((d6 * d2) + (d5 * d)) + d3) / Math.sqrt((d2 * d2) + (d * d));
    }

    private static int[] getRotatedRectangleCenter(double d, double d2, double d3, double d5, double d6, double d7, double d9, double d10) {
        double d11 = (((d + d3) + d6) + d9) / 4.0d;
        double d12 = (((d2 + d5) + d7) + d10) / 4.0d;
        double atan2 = Math.atan2(d5 - d2, d3 - d);
        return new int[]{(int) ((Math.cos(atan2) * d11) - (Math.sin(atan2) * d12)), (int) ((Math.cos(atan2) * d12) + (Math.sin(atan2) * d11))};
    }
}
