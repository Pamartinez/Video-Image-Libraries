package com.samsung.android.sdk.mobileservice.auth.result;

import com.samsung.android.sdk.mobileservice.auth.DeviceAuthInfo;
import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.common.result.Result;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DeviceAuthInfoResult implements Result {
    private DeviceAuthInfo mResult;
    private CommonResultStatus mStatus;

    public DeviceAuthInfoResult(CommonResultStatus commonResultStatus, DeviceAuthInfo deviceAuthInfo) {
        this.mStatus = commonResultStatus;
        this.mResult = deviceAuthInfo;
    }

    public DeviceAuthInfo getResult() {
        return this.mResult;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }
}
