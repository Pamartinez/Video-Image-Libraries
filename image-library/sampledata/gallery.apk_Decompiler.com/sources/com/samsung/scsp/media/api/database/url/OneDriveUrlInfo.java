package com.samsung.scsp.media.api.database.url;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OneDriveUrlInfo {
    public final long expirationDate;
    public final String hash;
    public final String url;

    public OneDriveUrlInfo(String str, String str2, long j2) {
        this.hash = str;
        this.url = str2;
        this.expirationDate = j2;
    }

    public String toString() {
        return "OneDriveUrlInfo{hash='" + this.hash + "', url='" + this.url + "', expirationDate=" + this.expirationDate + '}';
    }
}
