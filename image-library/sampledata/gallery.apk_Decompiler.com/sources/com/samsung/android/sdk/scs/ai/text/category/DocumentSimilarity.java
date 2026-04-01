package com.samsung.android.sdk.scs.ai.text.category;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DocumentSimilarity {
    private boolean isSimilar;
    private double similarityScore;

    public static DocumentSimilarity create() {
        return new DocumentSimilarity();
    }

    public double getScore() {
        return this.similarityScore;
    }

    public boolean isSimilar() {
        return this.isSimilar;
    }

    public void setScore(double d) {
        this.similarityScore = d;
    }

    public void setSimilarity(boolean z) {
        this.isSimilar = z;
    }
}
