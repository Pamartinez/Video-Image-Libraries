package com.samsung.android.sdk.mobileservice.social.share.result;

import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.common.result.Result;
import com.samsung.android.sdk.mobileservice.social.share.Space;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SpaceListResult implements Result {
    private List<Space> mResult;
    private CommonResultStatus mStatus;

    public SpaceListResult(CommonResultStatus commonResultStatus, List<Space> list) {
        this.mStatus = commonResultStatus;
        this.mResult = list;
    }

    public List<Space> getResult() {
        return this.mResult;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }
}
