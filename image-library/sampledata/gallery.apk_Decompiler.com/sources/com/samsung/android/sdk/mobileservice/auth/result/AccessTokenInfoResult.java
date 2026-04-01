package com.samsung.android.sdk.mobileservice.auth.result;

import com.samsung.android.sdk.mobileservice.auth.TokenInfo;
import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.common.result.Result;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class AccessTokenInfoResult implements Result {
    private TokenInfo mAccessToken;
    private CommonResultStatus mStatus;

    public AccessTokenInfoResult(CommonResultStatus commonResultStatus, TokenInfo tokenInfo) {
        this.mStatus = commonResultStatus;
        this.mAccessToken = tokenInfo;
    }

    public TokenInfo getAccessToken() {
        return this.mAccessToken;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }
}
