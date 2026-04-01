package com.samsung.android.sdk.mobileservice.social.group.result;

import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.common.result.Result;
import com.samsung.android.sdk.mobileservice.social.group.InvitationLink;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class InvitationLinkResult implements Result {
    private InvitationLink mInvitationLink;
    private CommonResultStatus mStatus;

    public InvitationLinkResult(CommonResultStatus commonResultStatus, InvitationLink invitationLink) {
        this.mStatus = commonResultStatus;
        this.mInvitationLink = invitationLink;
    }

    public InvitationLink getResult() {
        return this.mInvitationLink;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }
}
