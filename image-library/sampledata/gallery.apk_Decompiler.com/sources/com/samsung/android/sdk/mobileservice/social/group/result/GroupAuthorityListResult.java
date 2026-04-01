package com.samsung.android.sdk.mobileservice.social.group.result;

import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.common.result.Result;
import com.samsung.android.sdk.mobileservice.social.group.GroupAuthority;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GroupAuthorityListResult implements Result {
    private List<GroupAuthority> mAuthorityList;
    private CommonResultStatus mStatus;

    public GroupAuthorityListResult(CommonResultStatus commonResultStatus, List<GroupAuthority> list) {
        this.mStatus = commonResultStatus;
        this.mAuthorityList = list;
    }

    public List<GroupAuthority> getResult() {
        return this.mAuthorityList;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }
}
