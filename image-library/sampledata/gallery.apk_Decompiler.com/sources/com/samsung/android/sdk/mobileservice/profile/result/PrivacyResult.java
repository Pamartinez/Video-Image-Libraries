package com.samsung.android.sdk.mobileservice.profile.result;

import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.common.result.Result;
import com.samsung.android.sdk.mobileservice.profile.Privacy;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PrivacyResult implements Result {
    private Privacy mResult;
    private CommonResultStatus mStatus;

    public PrivacyResult(CommonResultStatus commonResultStatus, Privacy privacy) {
        this.mStatus = commonResultStatus;
        this.mResult = privacy;
    }

    public Privacy getResult() {
        return this.mResult;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }
}
