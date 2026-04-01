package com.samsung.android.sdk.bixby2.data;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AppMetaInfo {
    int appVersionCode;
    String capsuleId;

    public AppMetaInfo(String str, int i2) {
        this.capsuleId = str;
        this.appVersionCode = i2;
    }

    public int getAppVersionCode() {
        return this.appVersionCode;
    }

    public String getCapsuleId() {
        return this.capsuleId;
    }

    public void setAppVersionCode(int i2) {
        this.appVersionCode = i2;
    }

    public void setCapsuleId(String str) {
        this.capsuleId = str;
    }
}
