package com.samsung.android.sdk.mobileservice.social.group.provider;

import android.net.Uri;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GroupInvitationContract {
    public static final String AUTHORITY = "com.samsung.android.mobileservice.social.group.invitation";
    private static final String AUTHORITY_URI = "content://com.samsung.android.mobileservice.social.group.invitation";

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class Invitation {
        public static final Uri CONTENT_URI = Uri.parse("content://com.samsung.android.mobileservice.social.group.invitation/");
        public static final String EXPIRED_TIME = "expiredTime";
        public static final String FEATURE_ID = "featureId";
        public static final String GROUP_ID = "groupId";
        public static final String GROUP_NAME = "groupName";
        public static final String GROUP_THUMBNAIL_LOCAL_PATH = "groupThumbnailLocalPath";
        public static final String GROUP_TYPE = "groupType";
        public static final String MESSAGE = "message";
        public static final String REQUESTED_TIME = "requestedTime";
        public static final String REQUESTER_ID = "requesterId";
        public static final String REQUESTER_NAME = "requesterName";
        public static final String REQUESTER_THUMBNAIL_LOCAL_PATH = "requesterThumbnailLocalPath";

        /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
        public static class Type {
            public static final String AUTO_HOTSPOT = "AHSP";
            public static final String FAMILY = "FMLY";
            public static final String GENERAL = "GNRL";
            public static final String UNNAMED = "UNM1";

            private Type() {
            }
        }

        private Invitation() {
        }
    }

    private GroupInvitationContract() {
    }
}
