package com.samsung.android.sdk.mobileservice.social.share.result;

import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.common.result.Result;
import com.samsung.android.sdk.mobileservice.social.share.Quota;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class QuotaResult implements Result {
    private Quota mResult;
    private CommonResultStatus mStatus;

    public QuotaResult(CommonResultStatus commonResultStatus, Quota quota) {
        this.mStatus = commonResultStatus;
        this.mResult = quota;
    }

    public Quota getResult() {
        return this.mResult;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }
}
