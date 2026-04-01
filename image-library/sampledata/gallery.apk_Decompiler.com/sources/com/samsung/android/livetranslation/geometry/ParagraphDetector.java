package com.samsung.android.livetranslation.geometry;

import android.graphics.Point;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ParagraphDetector {
    private ParagraphAlgorithm mMode = ParagraphAlgorithm.RANDOM_FOREST;

    /* renamed from: com.samsung.android.livetranslation.geometry.ParagraphDetector$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$livetranslation$geometry$ParagraphDetector$ParagraphAlgorithm;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.samsung.android.livetranslation.geometry.ParagraphDetector$ParagraphAlgorithm[] r0 = com.samsung.android.livetranslation.geometry.ParagraphDetector.ParagraphAlgorithm.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$livetranslation$geometry$ParagraphDetector$ParagraphAlgorithm = r0
                com.samsung.android.livetranslation.geometry.ParagraphDetector$ParagraphAlgorithm r1 = com.samsung.android.livetranslation.geometry.ParagraphDetector.ParagraphAlgorithm.RANDOM_FOREST     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$livetranslation$geometry$ParagraphDetector$ParagraphAlgorithm     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.livetranslation.geometry.ParagraphDetector$ParagraphAlgorithm r1 = com.samsung.android.livetranslation.geometry.ParagraphDetector.ParagraphAlgorithm.DATA_SET     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.livetranslation.geometry.ParagraphDetector.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum ParagraphAlgorithm {
        RANDOM_FOREST,
        DATA_SET
    }

    private boolean dataSet(Point[] pointArr, Point[] pointArr2, int i2, int i7) {
        return false;
    }

    private boolean randomForest(Point[] pointArr, Point[] pointArr2, int i2, int i7) {
        return false;
    }

    public boolean isSameParagraph(Point[] pointArr, Point[] pointArr2, int i2, int i7) {
        int i8 = AnonymousClass1.$SwitchMap$com$samsung$android$livetranslation$geometry$ParagraphDetector$ParagraphAlgorithm[this.mMode.ordinal()];
        if (i8 == 1) {
            return randomForest(pointArr, pointArr2, i2, i7);
        }
        if (i8 != 2) {
            return false;
        }
        return dataSet(pointArr, pointArr2, i2, i7);
    }
}
