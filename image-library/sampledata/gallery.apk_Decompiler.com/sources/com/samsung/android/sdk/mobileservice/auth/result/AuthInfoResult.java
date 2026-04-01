package com.samsung.android.sdk.mobileservice.auth.result;

import com.samsung.android.sdk.mobileservice.auth.AuthInfo;
import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.common.result.Result;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AuthInfoResult implements Result {
    private AuthInfo mResult;
    private CommonResultStatus mStatus;

    public AuthInfoResult(CommonResultStatus commonResultStatus, AuthInfo authInfo) {
        this.mStatus = commonResultStatus;
        this.mResult = authInfo;
    }

    public AuthInfo getResult() {
        return this.mResult;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }
}
