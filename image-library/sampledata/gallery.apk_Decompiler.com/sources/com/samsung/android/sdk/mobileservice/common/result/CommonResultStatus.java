package com.samsung.android.sdk.mobileservice.common.result;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CommonResultStatus {
    private String mAgentCode;
    private int mCode;
    private String mDisplayMessage;
    private String mMessage;

    public CommonResultStatus(int i2) {
        this.mCode = i2;
    }

    public String getAgentCode() {
        return this.mAgentCode;
    }

    public int getCode() {
        return this.mCode;
    }

    public String getDisplayMessage() {
        return this.mDisplayMessage;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public CommonResultStatus(int i2, String str, String str2) {
        this.mCode = i2;
        this.mMessage = str;
        this.mAgentCode = str2;
    }

    public CommonResultStatus(int i2, String str, String str2, String str3) {
        this.mCode = i2;
        this.mMessage = str;
        this.mAgentCode = str2;
        this.mDisplayMessage = str3;
    }
}
