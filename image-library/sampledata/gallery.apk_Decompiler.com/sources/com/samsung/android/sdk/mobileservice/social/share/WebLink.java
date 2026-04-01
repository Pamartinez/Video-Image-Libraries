package com.samsung.android.sdk.mobileservice.social.share;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class WebLink {
    private long createdTime;
    private String creator;
    private long expiredTime;
    private String url;

    public WebLink(String str, String str2, long j2, long j3) {
        this.url = str;
        this.creator = str2;
        this.createdTime = j2;
        this.expiredTime = j3;
    }

    public long getCreatedTime() {
        return this.createdTime;
    }

    public String getCreator() {
        return this.creator;
    }

    public long getExpiredTime() {
        return this.expiredTime;
    }

    public String getUrl() {
        return this.url;
    }
}
