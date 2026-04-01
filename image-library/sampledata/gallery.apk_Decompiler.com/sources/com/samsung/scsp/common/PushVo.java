package com.samsung.scsp.common;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PushVo {
    @SerializedName("callbackUrl")
    public String callbackUrl;
    @SerializedName("category")
    public String category;
    @SerializedName("dataJson")
    public JsonObject data;
    @SerializedName("data")
    public String dataValue;
    @SerializedName("deviceSignature")
    public String deviceSignature;
    public int originalPriority;
    public int priority;
    @SerializedName("signature")
    public String signature;
}
