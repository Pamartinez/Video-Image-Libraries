package com.samsung.android.sdk.mobileservice.social.group.result;

import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.common.result.Result;
import com.samsung.android.sdk.mobileservice.social.group.Group;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GroupListResult implements Result {
    private List<Group> mGroups;
    private CommonResultStatus mStatus;

    public GroupListResult(CommonResultStatus commonResultStatus, List<Group> list) {
        this.mStatus = commonResultStatus;
        this.mGroups = list;
    }

    public List<Group> getResult() {
        return this.mGroups;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }
}
