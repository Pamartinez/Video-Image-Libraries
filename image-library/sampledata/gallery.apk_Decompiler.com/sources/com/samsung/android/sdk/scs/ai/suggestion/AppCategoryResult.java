package com.samsung.android.sdk.scs.ai.suggestion;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AppCategoryResult {
    private int mCategoryId;
    private String mCategoryString;
    private String mPackageName;

    private AppCategoryResult() {
    }

    public static AppCategoryResult create() {
        return new AppCategoryResult();
    }

    public int getCategoryId() {
        return this.mCategoryId;
    }

    public String getCategoryString() {
        return this.mCategoryString;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public void setCategoryId(int i2) {
        this.mCategoryId = i2;
    }

    public void setCategoryString(String str) {
        this.mCategoryString = str;
    }

    public void setPackageName(String str) {
        this.mPackageName = str;
    }
}
