package com.samsung.android.sdk.mobileservice.auth.result;

import com.samsung.android.sdk.mobileservice.auth.TokenInfo;
import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.common.result.Result;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ResultTokenInfo implements Result {
    private TokenInfo mResult;
    private CommonResultStatus mStatus;

    public ResultTokenInfo(CommonResultStatus commonResultStatus, TokenInfo tokenInfo) {
        this.mStatus = commonResultStatus;
        this.mResult = tokenInfo;
    }

    public TokenInfo getResult() {
        return this.mResult;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }
}
