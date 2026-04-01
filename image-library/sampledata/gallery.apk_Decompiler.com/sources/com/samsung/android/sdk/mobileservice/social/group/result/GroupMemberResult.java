package com.samsung.android.sdk.mobileservice.social.group.result;

import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.common.result.Result;
import com.samsung.android.sdk.mobileservice.social.group.GroupMember;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GroupMemberResult implements Result {
    private List<GroupMember> mGroupMembers;
    private CommonResultStatus mStatus;
    private int mTotalCountWithInvitation;

    public GroupMemberResult(CommonResultStatus commonResultStatus, List<GroupMember> list) {
        this.mStatus = commonResultStatus;
        this.mGroupMembers = list;
    }

    public List<GroupMember> getGroupMembers() {
        return this.mGroupMembers;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }

    public int getTotalCountWithInvitation() {
        return this.mTotalCountWithInvitation;
    }

    public void setTotalCountWithInvitation(int i2) {
        this.mTotalCountWithInvitation = i2;
    }
}
