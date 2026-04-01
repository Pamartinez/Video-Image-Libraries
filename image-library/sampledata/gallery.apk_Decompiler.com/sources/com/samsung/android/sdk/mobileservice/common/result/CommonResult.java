package com.samsung.android.sdk.mobileservice.common.result;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CommonResult<T> implements BaseResult<T> {
    private T mResult;
    private CommonResultStatus mStatus;

    public CommonResult(CommonResultStatus commonResultStatus, T t) {
        this.mStatus = commonResultStatus;
        this.mResult = t;
    }

    public T getResult() {
        return this.mResult;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }
}
