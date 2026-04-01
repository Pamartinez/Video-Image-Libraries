package com.samsung.scsp.framework.core.identity;

import com.google.gson.annotations.SerializedName;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PushInfo {
    @SerializedName("id")
    private String id;
    @SerializedName("token")
    private String token;
    @SerializedName("type")
    private String type;

    public PushInfo(String str, String str2, String str3) {
        this.id = str;
        this.type = str2;
        this.token = str3;
    }

    public boolean equalsValue(PushInfo pushInfo) {
        if (!this.id.equals(pushInfo.id) || !this.type.equals(pushInfo.type) || !this.token.equals(pushInfo.token)) {
            return false;
        }
        return true;
    }

    public String getId() {
        return this.id;
    }

    public String getToken() {
        return this.token;
    }

    public String getType() {
        return this.type;
    }

    public String toString() {
        String str = this.id;
        String str2 = this.type;
        String str3 = this.token;
        return str + "_" + str2 + "_" + str3;
    }
}
