package com.samsung.o3dp.lib3dphotography;

import c0.C0086a;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DepthClusteringConfig {
    public int expectedLayers = 3;
    public boolean forcePeakCountAsExpectedLayers = false;
    public float gradientMergeThreshold = 0.5f;
    public float gradientOutlierThreshold = 0.95f;
    public int histogramBins = 64;
    public float histogramPeakThreshold = 0.1f;
    public float histogramSmoothingSigma = 2.0f;
    public int maxKForKMeans = 50;
    public float minDepthThresholdForPeakAnalysis = 0.1f;
    public float minMergeThreshold = 0.1f;
    public boolean useAgglomerativeClustering = true;
    public boolean useDepthBasedMerging = false;
    public boolean useDepthClustering = true;
    public boolean useDeterministicKMeans = true;
    public boolean useInclusionClustering = true;
    public boolean useOptimized1DKMeans = true;
    public boolean usePeakCountForKMeans = true;
    public boolean verboseLogging = true;

    public String toString() {
        StringBuilder sb2 = new StringBuilder("Use Depth Clustering: ");
        boolean z = this.useDepthClustering;
        String str = SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE;
        String p6 = C0212a.p(sb2, z ? SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE : str, "\n");
        if (!this.useDepthClustering) {
            return p6;
        }
        StringBuilder t = C0212a.t(C0212a.p(C0212a.t(C0212a.p(C0212a.t(C0086a.l(C0212a.t(C0086a.l(C0212a.t(p6, "Expected Layers: "), this.expectedLayers, "\n"), "Max K for K-Means: "), this.maxKForKMeans, "\n"), "Use Inclusion Clustering: "), this.useInclusionClustering ? SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE : str, "\n"), "Use Agglomerative Clustering: "), this.useAgglomerativeClustering ? SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE : str, "\n"), "Min Merge Threshold: ");
        t.append(this.minMergeThreshold);
        t.append("\n");
        StringBuilder t3 = C0212a.t(t.toString(), "Gradient Merge Threshold: ");
        t3.append(this.gradientMergeThreshold);
        t3.append("\n");
        StringBuilder t5 = C0212a.t(t3.toString(), "Gradient Outlier Threshold: ");
        t5.append(this.gradientOutlierThreshold);
        t5.append("\n");
        String p8 = C0212a.p(C0212a.t(t5.toString(), "Use Deterministic K-Means: "), this.useDeterministicKMeans ? SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE : str, "\n");
        if (this.useDeterministicKMeans) {
            StringBuilder t6 = C0212a.t(C0086a.l(C0212a.t(p8, "Histogram Bins: "), this.histogramBins, "\n"), "Histogram Peak Threshold: ");
            t6.append(this.histogramPeakThreshold);
            t6.append("\n");
            StringBuilder t7 = C0212a.t(t6.toString(), "Histogram Smoothing Sigma: ");
            t7.append(this.histogramSmoothingSigma);
            t7.append("\n");
            p8 = t7.toString();
        }
        StringBuilder t10 = C0212a.t(C0212a.p(C0212a.t(C0212a.p(C0212a.t(p8, "Use Peak Count for K-Means: "), this.usePeakCountForKMeans ? SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE : str, "\n"), "Force Peak Count as Expected Layers: "), this.forcePeakCountAsExpectedLayers ? SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE : str, "\n"), "Min Depth Threshold for Peak Analysis: ");
        t10.append(this.minDepthThresholdForPeakAnalysis);
        t10.append("\n");
        StringBuilder t11 = C0212a.t(t10.toString(), "Use Optimized 1D K-Means: ");
        if (this.useOptimized1DKMeans) {
            str = SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE;
        }
        return C0212a.p(t11, str, "\n");
    }
}
