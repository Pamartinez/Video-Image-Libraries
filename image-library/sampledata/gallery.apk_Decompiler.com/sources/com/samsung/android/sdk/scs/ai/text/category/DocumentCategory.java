package com.samsung.android.sdk.scs.ai.text.category;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DocumentCategory {
    private long categoryId;
    private String categoryName;
    private double categoryScore;

    public static DocumentCategory create() {
        return new DocumentCategory();
    }

    public long getId() {
        return this.categoryId;
    }

    public String getName() {
        return this.categoryName;
    }

    public double getScore() {
        return this.categoryScore;
    }

    public void setId(Long l) {
        this.categoryId = l.longValue();
    }

    public void setName(String str) {
        this.categoryName = str;
    }

    public void setScore(double d) {
        this.categoryScore = d;
    }
}
