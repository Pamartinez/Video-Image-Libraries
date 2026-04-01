package com.samsung.android.sdk.mobileservice.social.share.result;

import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.common.result.Result;
import com.samsung.android.sdk.mobileservice.social.share.Space;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpaceResult implements Result {
    private Space mResult;
    private CommonResultStatus mStatus;

    public SpaceResult(CommonResultStatus commonResultStatus, Space space) {
        this.mStatus = commonResultStatus;
        this.mResult = space;
    }

    public Space getResult() {
        return this.mResult;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }
}
