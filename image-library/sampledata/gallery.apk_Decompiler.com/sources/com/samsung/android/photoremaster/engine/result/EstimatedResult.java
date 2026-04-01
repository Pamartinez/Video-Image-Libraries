package com.samsung.android.photoremaster.engine.result;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EstimatedResult {
    public DefactLevel defectLevel = null;
    public float defectProbability = 0.0f;
    public boolean hasDefect = false;
    public QualityLevel qualityLevel = null;
    public float qualityScore = 0.0f;
    public EstimationType type;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum DefactLevel {
        None,
        Low,
        Mid,
        High
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum EstimationType {
        Blur,
        Noise,
        IQA
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum QualityLevel {
        NeedImprovement,
        Good,
        VeryGood
    }
}
