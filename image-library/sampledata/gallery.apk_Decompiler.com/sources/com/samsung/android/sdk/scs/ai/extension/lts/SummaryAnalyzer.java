package com.samsung.android.sdk.scs.ai.extension.lts;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SummaryAnalyzer {
    private double compressionHigh = 5.0d;
    private double compressionLow = 2.5d;
    private double coverageHigh = 0.67d;
    private double coverageLow = 0.33d;
    private double densityHigh = 2.0d;
    private double densityLow = 1.0d;
    private final SummaryReportGenerator mReportGenerator;

    public SummaryAnalyzer(SummaryReportGenerator summaryReportGenerator) {
        if (summaryReportGenerator != null) {
            this.mReportGenerator = summaryReportGenerator;
            return;
        }
        throw new IllegalArgumentException("SummaryReportGenerator cannot be null");
    }

    public SummaryAnalysisResult analyze(String str, String str2) {
        if (str == null || str2 == null) {
            throw new NullPointerException("Source text and summary text cannot be null");
        }
        SummaryScores evaluate = evaluate(str, str2);
        return new SummaryAnalysisResult(evaluate, classifyStyle(evaluate), interpretCoverage(evaluate.coverage), interpretDensity(evaluate.density), interpretCompression(evaluate.compression));
    }

    public SummaryStyle classifyStyle(SummaryScores summaryScores) {
        String interpretCoverage = interpretCoverage(summaryScores.coverage);
        String interpretDensity = interpretDensity(summaryScores.density);
        String interpretCompression = interpretCompression(summaryScores.compression);
        if (interpretCoverage.equals("High") && interpretDensity.equals("High") && !interpretCompression.equals("High")) {
            return SummaryStyle.EXTRACTIVE;
        }
        if (!interpretCoverage.equals("Low") || !interpretDensity.equals("Low") || !interpretCompression.equals("High")) {
            return SummaryStyle.MIXED;
        }
        return SummaryStyle.ABSTRACTIVE;
    }

    public SummaryScores evaluate(String str, String str2) {
        return new SummaryScores(SummaryEvaluator.calculateCoverage(str, str2), SummaryEvaluator.calculateDensity(str, str2), SummaryEvaluator.calculateCompression(str, str2));
    }

    public String generateSummaryReport(SummaryScores summaryScores) {
        return this.mReportGenerator.generateReport(summaryScores, interpretCoverage(summaryScores.coverage), interpretDensity(summaryScores.density), interpretCompression(summaryScores.compression), classifyStyle(summaryScores));
    }

    public String interpretCompression(double d) {
        if (d < this.compressionLow) {
            return "Low";
        }
        if (d < this.compressionHigh) {
            return "Medium";
        }
        return "High";
    }

    public String interpretCoverage(double d) {
        if (d < this.coverageLow) {
            return "Low";
        }
        if (d < this.coverageHigh) {
            return "Medium";
        }
        return "High";
    }

    public String interpretDensity(double d) {
        if (d < this.densityLow) {
            return "Low";
        }
        if (d < this.densityHigh) {
            return "Medium";
        }
        return "High";
    }

    public String generateSummaryReport(String str, String str2) {
        if (str != null && str2 != null) {
            return generateSummaryReport(evaluate(str, str2));
        }
        throw new NullPointerException("Source text and summary text cannot be null");
    }

    public SummaryAnalyzer(SummaryReportGenerator summaryReportGenerator, double d, double d2, double d3, double d5, double d6, double d7) {
        if (summaryReportGenerator != null) {
            this.mReportGenerator = summaryReportGenerator;
            this.coverageLow = d;
            this.coverageHigh = d2;
            this.densityLow = d3;
            this.densityHigh = d5;
            this.compressionLow = d6;
            this.compressionHigh = d7;
            return;
        }
        throw new IllegalArgumentException("SummaryReportGenerator cannot be null");
    }
}
