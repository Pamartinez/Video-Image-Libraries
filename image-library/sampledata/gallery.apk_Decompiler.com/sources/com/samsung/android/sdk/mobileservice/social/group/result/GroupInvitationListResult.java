package com.samsung.android.sdk.mobileservice.social.group.result;

import android.net.Uri;
import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.common.result.Result;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GroupInvitationListResult implements Result {
    private List<GroupInvitation> mInvitations;
    private CommonResultStatus mStatus;

    public GroupInvitationListResult(CommonResultStatus commonResultStatus, List<GroupInvitation> list) {
        this.mStatus = commonResultStatus;
        this.mInvitations = list;
    }

    public List<GroupInvitation> getInvitationList() {
        return this.mInvitations;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class GroupInvitation {
        private long mExpiredTime;
        private Uri mGroupCoverThumbnailContentUri;
        private Uri mGroupCoverThumbnailFileUri;
        private String mGroupCoverThumbnailUrl;
        private String mGroupId;
        private String mGroupName;
        private String mMessage;
        private long mRequestedTime;
        private String mRequesterId;
        private Uri mRequesterImageContentUri;
        private Uri mRequesterImageFileUri;
        private String mRequesterImageUrl;
        private String mRequesterName;

        @Deprecated
        public GroupInvitation(String str, String str2, String str3, Uri uri, Uri uri2, String str4, String str5, String str6, String str7, Uri uri3, Uri uri4, long j2, long j3) {
            this.mGroupId = str;
            this.mGroupName = str2;
            this.mGroupCoverThumbnailUrl = str3;
            this.mGroupCoverThumbnailFileUri = uri;
            this.mGroupCoverThumbnailContentUri = uri2;
            this.mMessage = str4;
            this.mRequesterId = str5;
            this.mRequesterName = str6;
            this.mRequesterImageUrl = str7;
            this.mRequesterImageFileUri = uri3;
            this.mRequesterImageContentUri = uri4;
            this.mRequestedTime = j2;
            this.mExpiredTime = j3;
        }

        public long getExpiredTime() {
            return this.mExpiredTime;
        }

        @Deprecated
        public Uri getGroupCoverThumbnailContentUri() {
            return this.mGroupCoverThumbnailContentUri;
        }

        @Deprecated
        public Uri getGroupCoverThumbnailFileUri() {
            return this.mGroupCoverThumbnailFileUri;
        }

        @Deprecated
        public String getGroupCoverThumbnailUrl() {
            return this.mGroupCoverThumbnailUrl;
        }

        public String getGroupId() {
            return this.mGroupId;
        }

        public String getGroupName() {
            return this.mGroupName;
        }

        public String getMessage() {
            return this.mMessage;
        }

        public long getRequestedTime() {
            return this.mRequestedTime;
        }

        public String getRequesterId() {
            return this.mRequesterId;
        }

        public Uri getRequesterImageContentUri() {
            return this.mRequesterImageContentUri;
        }

        public Uri getRequesterImageFileUri() {
            return this.mRequesterImageFileUri;
        }

        public String getRequesterImageUrl() {
            return this.mRequesterImageUrl;
        }

        public String getRequesterName() {
            return this.mRequesterName;
        }

        public GroupInvitation(String str, String str2, String str3, String str4, String str5, String str6, Uri uri, Uri uri2, long j2, long j3) {
            this.mGroupId = str;
            this.mGroupName = str2;
            this.mGroupCoverThumbnailUrl = null;
            this.mGroupCoverThumbnailFileUri = null;
            this.mGroupCoverThumbnailContentUri = null;
            this.mMessage = str3;
            this.mRequesterId = str4;
            this.mRequesterName = str5;
            this.mRequesterImageUrl = str6;
            this.mRequesterImageFileUri = uri;
            this.mRequesterImageContentUri = uri2;
            this.mRequestedTime = j2;
            this.mExpiredTime = j3;
        }
    }
}
