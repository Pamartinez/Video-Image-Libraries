package com.samsung.android.sdk.scs.ai.extension.lts;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SummaryAnalysisResult {
    private final String mCompressionLevel;
    private final String mCoverageLevel;
    private final String mDensityLevel;
    private final SummaryScores mScores;
    private final SummaryStyle mStyle;

    public SummaryAnalysisResult(SummaryScores summaryScores, SummaryStyle summaryStyle, String str, String str2, String str3) {
        this.mScores = summaryScores;
        this.mStyle = summaryStyle;
        this.mCoverageLevel = str;
        this.mDensityLevel = str2;
        this.mCompressionLevel = str3;
    }

    public String getCompressionLevel() {
        return this.mCompressionLevel;
    }

    public String getCoverageLevel() {
        return this.mCoverageLevel;
    }

    public String getDensityLevel() {
        return this.mDensityLevel;
    }

    public SummaryScores getScores() {
        return this.mScores;
    }

    public SummaryStyle getStyle() {
        return this.mStyle;
    }
}
