package com.samsung.android.sdk.scs.ai.extension.lts;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SummaryReportGenerator {

    /* renamed from: com.samsung.android.sdk.scs.ai.extension.lts.SummaryReportGenerator$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$sdk$scs$ai$extension$lts$SummaryStyle;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.samsung.android.sdk.scs.ai.extension.lts.SummaryStyle[] r0 = com.samsung.android.sdk.scs.ai.extension.lts.SummaryStyle.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$sdk$scs$ai$extension$lts$SummaryStyle = r0
                com.samsung.android.sdk.scs.ai.extension.lts.SummaryStyle r1 = com.samsung.android.sdk.scs.ai.extension.lts.SummaryStyle.EXTRACTIVE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$sdk$scs$ai$extension$lts$SummaryStyle     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.sdk.scs.ai.extension.lts.SummaryStyle r1 = com.samsung.android.sdk.scs.ai.extension.lts.SummaryStyle.MIXED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$sdk$scs$ai$extension$lts$SummaryStyle     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.sdk.scs.ai.extension.lts.SummaryStyle r1 = com.samsung.android.sdk.scs.ai.extension.lts.SummaryStyle.ABSTRACTIVE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.sdk.scs.ai.extension.lts.SummaryReportGenerator.AnonymousClass1.<clinit>():void");
        }
    }

    public String generateReport(SummaryScores summaryScores, String str, String str2, String str3, SummaryStyle summaryStyle) {
        return String.format("Coverage: %.2f (%s) → ", new Object[]{Double.valueOf(summaryScores.coverage), str}) + getCoverageText(summaryScores.coverage, str) + String.format("Density: %.2f (%s) → ", new Object[]{Double.valueOf(summaryScores.density), str2}) + getDensityText(summaryScores.density, str2) + String.format("Compression: %.2f (%s) → ", new Object[]{Double.valueOf(summaryScores.compression), str3}) + getCompressionText(summaryScores.compression, str3) + "Style Classification: " + summaryStyle + "\nRecommendations: " + getStyleFeedback(summaryStyle);
    }

    public String getCompressionText(double d, String str) {
        str.getClass();
        if (str.equals("Medium")) {
            return "Moderately compressed — likely concise and informative.\n";
        }
        if (!str.equals("High")) {
            return "Summary is longer than source — possibly verbose or not a true summary.\n";
        }
        return "Very short summary — may omit important details.\n";
    }

    public String getCoverageText(double d, String str) {
        str.getClass();
        if (str.equals("Medium")) {
            return "Summary contains a mix of original and new words.\n";
        }
        if (!str.equals("High")) {
            return "Summary mostly uses new or unrelated words.\n";
        }
        return "Most summary words are taken directly from the source.\n";
    }

    public String getDensityText(double d, String str) {
        str.getClass();
        if (str.equals("Medium")) {
            return "Shorter fragments with some rephrasing.\n";
        }
        if (!str.equals("High")) {
            return "Summary is highly rephrased (abstractive).\n";
        }
        return "Long continuous spans were extracted from the source.\n";
    }

    public String getStyleFeedback(SummaryStyle summaryStyle) {
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$sdk$scs$ai$extension$lts$SummaryStyle[summaryStyle.ordinal()];
        if (i2 == 1) {
            return "Consider adding more creative rephrasing to improve readability while maintaining the key information. Try to combine related sentences and use more concise expressions.\n";
        }
        if (i2 == 2) {
            return "This is a well-balanced summary. To further improve, ensure that the rephrased content accurately captures the original meaning and maintains logical flow between extracted and rephrased parts.\n";
        }
        if (i2 == 3) {
            return "Review the summary to ensure all critical information from the source is preserved. Consider adding more specific details or examples from the original text to increase accuracy.\n";
        }
        throw new IncompatibleClassChangeError();
    }
}
