package com.samsung.android.sdk.mobileservice.social.group;

import android.text.TextUtils;
import com.samsung.android.sdk.mobileservice.social.social.OpenSessionApi;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GroupAuthority {
    public static final int REASON_NOT_ACCEPTED_THE_INVITATION_YET = 1102;
    public static final int REASON_NOT_A_MEMBER = 1101;
    public static final int REASON_OWNERS_PERMISSIONS_CANNOT_BE_CHANGED = 1103;
    public static final int REASON_SERVER_ERROR = 1501;
    private AuthorityType mAuthority;
    private int mFailedReason;
    private String mGroupId;
    private String mId;
    private String mOptionalId;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum AuthorityType {
        INVALID(""),
        READ_ONLY(OpenSessionApi.EDIT_AUTHORITY_READ_ONLY),
        READ_WRITE(OpenSessionApi.EDIT_AUTHORITY_WRITABLE);
        
        private String authority;

        private AuthorityType(String str) {
            this.authority = str;
        }

        public static AuthorityType toAuthorityType(String str) {
            if (TextUtils.isEmpty(str)) {
                return INVALID;
            }
            for (AuthorityType authorityType : values()) {
                if (str.equals(authorityType.getAuthority())) {
                    return authorityType;
                }
            }
            return INVALID;
        }

        public String getAuthority() {
            return this.authority;
        }
    }

    public GroupAuthority(String str, String str2, String str3, AuthorityType authorityType, int i2) {
        this.mGroupId = str;
        this.mId = str2;
        this.mOptionalId = str3;
        this.mAuthority = authorityType;
        this.mFailedReason = i2;
    }

    public AuthorityType getAuthority() {
        return this.mAuthority;
    }

    public int getFailedReason() {
        return this.mFailedReason;
    }

    public String getGroupId() {
        return this.mGroupId;
    }

    public String getId() {
        return this.mId;
    }

    public String getOptionalId() {
        return this.mOptionalId;
    }
}
