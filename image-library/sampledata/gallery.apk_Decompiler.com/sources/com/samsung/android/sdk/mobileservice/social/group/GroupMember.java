package com.samsung.android.sdk.mobileservice.social.group;

import android.net.Uri;
import com.samsung.android.sdk.mobileservice.social.group.GroupAuthority;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class GroupMember {
    @Deprecated
    public static final int ID_TYPE_ACCOUNT_ID = 3;
    @Deprecated
    public static final int ID_TYPE_DUID = 2;
    @Deprecated
    public static final int ID_TYPE_GUID = 0;
    @Deprecated
    public static final int ID_TYPE_MSISDN = 1;
    private GroupAuthority.AuthorityType mAuthority;
    private String mId;
    private boolean mIsLeader;
    private String mName;
    private String mOptionalId;
    private int mOptionalIdType;
    private int mStatus;
    private Uri mThumbnailContentUri;
    private Uri mThumbnailFileUri;

    @Deprecated
    public GroupMember(int i2, String str, String str2, int i7, String str3, boolean z, Uri uri, Uri uri2) {
        this.mOptionalIdType = i2;
        this.mId = str;
        this.mOptionalId = str2;
        this.mStatus = i7;
        this.mName = str3;
        this.mIsLeader = z;
        this.mThumbnailFileUri = uri;
        this.mThumbnailContentUri = uri2;
    }

    public GroupAuthority.AuthorityType getAuthority() {
        return this.mAuthority;
    }

    public String getId() {
        return this.mId;
    }

    public String getName() {
        return this.mName;
    }

    public String getOptionalId() {
        return this.mOptionalId;
    }

    @Deprecated
    public int getOptionalIdType() {
        return this.mOptionalIdType;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public Uri getThumbnailContentUri() {
        return this.mThumbnailContentUri;
    }

    public Uri getThumbnailFileUri() {
        return this.mThumbnailFileUri;
    }

    public boolean isLeader() {
        return this.mIsLeader;
    }

    public GroupMember(String str, String str2, int i2, String str3, boolean z, Uri uri, Uri uri2, GroupAuthority.AuthorityType authorityType) {
        this.mOptionalIdType = 0;
        this.mId = str;
        this.mOptionalId = str2;
        this.mStatus = i2;
        this.mName = str3;
        this.mIsLeader = z;
        this.mThumbnailFileUri = uri;
        this.mThumbnailContentUri = uri2;
        this.mAuthority = authorityType;
    }
}
