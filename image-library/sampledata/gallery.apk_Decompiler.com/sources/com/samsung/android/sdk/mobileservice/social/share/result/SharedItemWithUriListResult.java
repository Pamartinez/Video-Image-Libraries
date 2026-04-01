package com.samsung.android.sdk.mobileservice.social.share.result;

import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.common.result.Result;
import com.samsung.android.sdk.mobileservice.social.share.SharedItemWithUriList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharedItemWithUriListResult implements Result {
    private SharedItemWithUriList mResult;
    private CommonResultStatus mStatus;

    public SharedItemWithUriListResult(CommonResultStatus commonResultStatus, SharedItemWithUriList sharedItemWithUriList) {
        this.mStatus = commonResultStatus;
        this.mResult = sharedItemWithUriList;
    }

    public SharedItemWithUriList getResult() {
        return this.mResult;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }
}
