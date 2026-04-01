package com.samsung.android.sdk.mobileservice.social.group.result;

import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.common.result.Result;
import com.samsung.android.sdk.mobileservice.social.group.Group;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GroupInvitationResult implements Result {
    private String mDisplayMessage;
    private List<ExcludedMember> mExcludedMembers;
    private Group mGroup;
    private CommonResultStatus mStatus;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ExcludedMember {
        public static final String REASON_ALREADY_INVITED_USER = "Already invited user";
        public static final String REASON_ALREADY_JOINED = "Already joined the group";
        @Deprecated
        public static final String REASON_ALREADY_JOINED_ANOTHER_UNIQUE_GROUP = "reason_already_joined_another_unique_group";
        public static final String REASON_DIFFERENT_COUNTRY_INFO = "Different country info";
        public static final String REASON_UNKNOWN = "No reason";
        public static final String REASON_UNREGISTERED_USER = "Unregistered user";
        public static final String REASON_UNREGISTERED_USER_FOR_SAMSUNG_ACCOUNT = "Not registered user for Samsung account service";
        @Deprecated
        public static final String REASON_UNREGISTERED_USER_FOR_SAMSUNG_CLOUD = "reason_unregistered_user_for_samsung_cloud";
        public static final String REASON_USER_USING_OLD_APP_VERSION = "User using old app version";
        private String mId;
        private String mOptionalId;
        private String mReason;

        public ExcludedMember(String str, String str2, String str3) {
            this.mId = str;
            this.mOptionalId = str2;
            this.mReason = str3;
        }

        public String getId() {
            return this.mId;
        }

        public String getOptionalId() {
            return this.mOptionalId;
        }

        public String getReason() {
            return this.mReason;
        }
    }

    public GroupInvitationResult(CommonResultStatus commonResultStatus, Group group, List<ExcludedMember> list, String str) {
        this.mStatus = commonResultStatus;
        this.mGroup = group;
        this.mExcludedMembers = list;
        this.mDisplayMessage = str;
    }

    public String getDisplayMessage() {
        return this.mDisplayMessage;
    }

    public List<ExcludedMember> getExcludedMembers() {
        return this.mExcludedMembers;
    }

    public Group getGroup() {
        return this.mGroup;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }
}
