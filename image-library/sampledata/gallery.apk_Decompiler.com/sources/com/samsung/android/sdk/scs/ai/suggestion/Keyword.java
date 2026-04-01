package com.samsung.android.sdk.scs.ai.suggestion;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Keyword {
    private String queryString;
    private String tagType;

    public Keyword(String str, String str2) {
        this.queryString = str;
        this.tagType = str2;
    }

    public String getQueryString() {
        return this.queryString;
    }

    public String getTagType() {
        return this.tagType;
    }
}
