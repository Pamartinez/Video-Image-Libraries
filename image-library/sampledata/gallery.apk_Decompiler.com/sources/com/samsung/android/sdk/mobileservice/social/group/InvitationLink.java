package com.samsung.android.sdk.mobileservice.social.group;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class InvitationLink {
    private long createdTime;
    private long expiredTime;
    private String url;

    public InvitationLink(String str, long j2, long j3) {
        this.url = str;
        this.createdTime = j2;
        this.expiredTime = j3;
    }

    public long getCreatedTime() {
        return this.createdTime;
    }

    public long getExpiredTime() {
        return this.expiredTime;
    }

    public String getUrl() {
        return this.url;
    }
}
