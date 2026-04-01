package com.samsung.android.imagetranslation.util;

import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.imagetranslation.data.LttOcrResult;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class LineWidthRule implements Rule {
    private static final double MAX_LINE_WIDTH_RATIO = 0.8d;
    private static final int MIN_LINE_COUNT = 2;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class LineOutlierDetector {
        private double meanHeight = MapUtil.INVALID_LOCATION;
        private double stdDeviation = MapUtil.INVALID_LOCATION;

        public LineOutlierDetector(LttOcrResult.BlockInfo blockInfo) {
            if (blockInfo.getLineInfo().size() > 2) {
                double meanHeight2 = getMeanHeight(blockInfo);
                this.meanHeight = meanHeight2;
                this.stdDeviation = getStdDeviation(blockInfo, meanHeight2);
            }
        }

        /* JADX WARNING: type inference failed for: r0v2, types: [java.util.function.ToIntFunction, java.lang.Object] */
        private static double getMeanHeight(LttOcrResult.BlockInfo blockInfo) {
            if (blockInfo.getLineInfo().isEmpty()) {
                return MapUtil.INVALID_LOCATION;
            }
            return blockInfo.getLineInfo().stream().mapToInt(new Object()).average().orElse(MapUtil.INVALID_LOCATION);
        }

        /* JADX WARNING: type inference failed for: r3v0, types: [java.util.function.ToDoubleFunction, java.lang.Object] */
        private static double getStdDeviation(LttOcrResult.BlockInfo blockInfo, double d) {
            if (blockInfo.getLineInfo().isEmpty()) {
                return MapUtil.INVALID_LOCATION;
            }
            return Math.sqrt((blockInfo.getLineInfo().stream().mapToDouble(new Object()).reduce(MapUtil.INVALID_LOCATION, new m(d)) / ((double) blockInfo.getLineInfo().size())) - 1.0d);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ double lambda$getStdDeviation$1(LttOcrResult.LineInfo lineInfo) {
            return (double) lineInfo.getRect().height();
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ double lambda$getStdDeviation$2(double d, double d2, double d3) {
            double d5 = d3 - d;
            return (d5 * d5) + d2;
        }

        public boolean isOutlier(LttOcrResult.LineInfo lineInfo) {
            if (this.meanHeight == MapUtil.INVALID_LOCATION) {
                return false;
            }
            if (((double) lineInfo.getRect().height()) > (this.stdDeviation * 1.25d) + this.meanHeight) {
                return true;
            }
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getScore$0(LttOcrResult.BlockInfo blockInfo) {
        if (blockInfo.getLineInfo().size() >= 2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getScore$1(LineOutlierDetector lineOutlierDetector, LttOcrResult.LineInfo lineInfo) {
        return !lineOutlierDetector.isOutlier(lineInfo);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$getScore$2(AnonymousClass2 r42, LttOcrResult.LineInfo lineInfo) {
        r42.value += 1.0d;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getScore$3(double d, LttOcrResult.LineInfo lineInfo) {
        if (((double) lineInfo.getRect().width()) > d) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getScore$4(AnonymousClass1 r9, LttOcrResult.BlockInfo blockInfo) {
        double maxLineWidth = ((double) BlockInfoUtil.getMaxLineWidth(blockInfo)) * MAX_LINE_WIDTH_RATIO;
        LineOutlierDetector lineOutlierDetector = new LineOutlierDetector(blockInfo);
        AnonymousClass2 r32 = new Object() {
            double value = MapUtil.INVALID_LOCATION;
        };
        double count = (double) blockInfo.getLineInfo().stream().filter(new h(0, lineOutlierDetector)).peek(new d(1, r32)).filter(new i(maxLineWidth)).count();
        double d = r9.score;
        double d2 = r32.value;
        double d3 = MapUtil.INVALID_LOCATION;
        if (d2 > MapUtil.INVALID_LOCATION) {
            d3 = count / d2;
        }
        r9.score = d + d3;
        r9.multiLineBlockCount++;
    }

    public double getScore(List<LttOcrResult.BlockInfo> list) {
        AnonymousClass1 r0 = new Object() {
            int multiLineBlockCount = 0;
            double score = MapUtil.INVALID_LOCATION;
        };
        list.stream().filter(new j(0)).forEach(new k(this, r0));
        int i2 = r0.multiLineBlockCount;
        if (i2 > 0) {
            return r0.score / ((double) i2);
        }
        return MapUtil.INVALID_LOCATION;
    }
}
