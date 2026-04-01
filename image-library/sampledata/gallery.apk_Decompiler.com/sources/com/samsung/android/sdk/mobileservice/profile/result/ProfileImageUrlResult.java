package com.samsung.android.sdk.mobileservice.profile.result;

import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.common.result.Result;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ProfileImageUrlResult implements Result {
    private String mProfileImageUrl;
    private CommonResultStatus mStatus;

    public ProfileImageUrlResult(CommonResultStatus commonResultStatus, String str) {
        this.mStatus = commonResultStatus;
        this.mProfileImageUrl = str;
    }

    public String getResult() {
        return this.mProfileImageUrl;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }
}
