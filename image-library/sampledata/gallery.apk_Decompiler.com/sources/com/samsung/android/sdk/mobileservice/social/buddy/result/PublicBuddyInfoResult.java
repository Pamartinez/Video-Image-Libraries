package com.samsung.android.sdk.mobileservice.social.buddy.result;

import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.common.result.Result;
import com.samsung.android.sdk.mobileservice.social.buddy.PublicBuddyInfo;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PublicBuddyInfoResult implements Result {
    private PublicBuddyInfo mResult;
    private CommonResultStatus mStatus;

    public PublicBuddyInfoResult(CommonResultStatus commonResultStatus, PublicBuddyInfo publicBuddyInfo) {
        this.mStatus = commonResultStatus;
        this.mResult = publicBuddyInfo;
    }

    public PublicBuddyInfo getResult() {
        return this.mResult;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }
}
