package com.samsung.android.sdk.mobileservice.common.result;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StringResult implements Result {
    private String mResult;
    private CommonResultStatus mStatus;

    public StringResult(CommonResultStatus commonResultStatus, String str) {
        this.mStatus = commonResultStatus;
        this.mResult = str;
    }

    public String getResult() {
        return this.mResult;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }
}
