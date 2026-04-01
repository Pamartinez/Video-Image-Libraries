package com.samsung.android.sdk.mobileservice.social.group.result;

import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.common.result.Result;
import com.samsung.android.sdk.mobileservice.social.group.Group;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GroupResult implements Result {
    private Group mGroup;
    private CommonResultStatus mStatus;

    public GroupResult(CommonResultStatus commonResultStatus, Group group) {
        this.mStatus = commonResultStatus;
        this.mGroup = group;
    }

    public Group getResult() {
        return this.mGroup;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }
}
