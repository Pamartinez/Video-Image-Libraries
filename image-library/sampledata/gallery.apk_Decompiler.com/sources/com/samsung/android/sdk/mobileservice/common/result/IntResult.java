package com.samsung.android.sdk.mobileservice.common.result;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class IntResult implements Result {
    private int mResult;
    private CommonResultStatus mStatus;

    public IntResult(CommonResultStatus commonResultStatus, int i2) {
        this.mStatus = commonResultStatus;
        this.mResult = i2;
    }

    public int getResult() {
        return this.mResult;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }
}
