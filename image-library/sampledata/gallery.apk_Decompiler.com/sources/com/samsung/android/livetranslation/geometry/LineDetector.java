package com.samsung.android.livetranslation.geometry;

import android.graphics.Point;
import com.samsung.android.gallery.support.utils.MapUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class LineDetector {
    private LineAlgorithm mMode = LineAlgorithm.RANDOM_FOREST;

    /* renamed from: com.samsung.android.livetranslation.geometry.LineDetector$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$livetranslation$geometry$LineDetector$LineAlgorithm;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.samsung.android.livetranslation.geometry.LineDetector$LineAlgorithm[] r0 = com.samsung.android.livetranslation.geometry.LineDetector.LineAlgorithm.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$livetranslation$geometry$LineDetector$LineAlgorithm = r0
                com.samsung.android.livetranslation.geometry.LineDetector$LineAlgorithm r1 = com.samsung.android.livetranslation.geometry.LineDetector.LineAlgorithm.LOGISTIC_REGRESSION     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$livetranslation$geometry$LineDetector$LineAlgorithm     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.livetranslation.geometry.LineDetector$LineAlgorithm r1 = com.samsung.android.livetranslation.geometry.LineDetector.LineAlgorithm.RANDOM_FOREST     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$livetranslation$geometry$LineDetector$LineAlgorithm     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.livetranslation.geometry.LineDetector$LineAlgorithm r1 = com.samsung.android.livetranslation.geometry.LineDetector.LineAlgorithm.DATA_SET     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.livetranslation.geometry.LineDetector.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum LineAlgorithm {
        LOGISTIC_REGRESSION,
        RANDOM_FOREST,
        DATA_SET
    }

    private boolean dataSet(Point[] pointArr, Point[] pointArr2, int i2, int i7) {
        return false;
    }

    private boolean logisticRegression(Point[] pointArr, Point[] pointArr2, int i2, int i7) {
        double[] dArr = {-0.18471853157439835d, -0.0031349510660258475d, 0.1794453136803585d, 0.023158140659835905d, -0.18284697975598033d, -0.0235184793319766d, 0.18588336366154318d, 0.024228456284229025d, 0.18822890144735413d, -0.06543840936111293d, -0.024772544749421054d, 0.028730626449414248d, 0.02271880842351833d, -0.02351149065570263d, -0.18391774716961576d, 0.040871512378172645d, -0.006742633478447907d, -0.004890368716609771d};
        double d = MapUtil.INVALID_LOCATION;
        for (int i8 = 0; i8 < 4; i8++) {
            int i10 = i8 * 2;
            double d2 = dArr[i10];
            Point point = pointArr[i8];
            d = (dArr[i10 + 1] * ((double) point.y)) + (d2 * ((double) point.x)) + d;
        }
        for (int i11 = 0; i11 < 4; i11++) {
            int i12 = i11 * 2;
            double d3 = dArr[i12 + 8];
            Point point2 = pointArr2[i11];
            d = (dArr[i12 + 9] * ((double) point2.y)) + (d3 * ((double) point2.x)) + d;
        }
        if (1.0d / (Math.exp(-(((dArr[17] * ((double) i7)) + (dArr[16] * ((double) i2))) + d)) + 1.0d) > 0.5d) {
            return true;
        }
        return false;
    }

    private boolean randomForest(Point[] pointArr, Point[] pointArr2, int i2, int i7) {
        return false;
    }

    public boolean isSameLine(Point[] pointArr, Point[] pointArr2, int i2, int i7) {
        int i8 = AnonymousClass1.$SwitchMap$com$samsung$android$livetranslation$geometry$LineDetector$LineAlgorithm[this.mMode.ordinal()];
        if (i8 == 1) {
            return logisticRegression(pointArr, pointArr2, i2, i7);
        }
        if (i8 == 2) {
            return randomForest(pointArr, pointArr2, i2, i7);
        }
        if (i8 != 3) {
            return false;
        }
        return dataSet(pointArr, pointArr2, i2, i7);
    }
}
