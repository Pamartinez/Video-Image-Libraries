package com.samsung.android.sdk.mobileservice.social.group;

import android.net.Uri;
import com.samsung.android.sdk.mobileservice.social.group.GroupAuthority;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Group {
    public static final String GROUP_TYPE_AUTO_HOTSPOT = "AHSP";
    @Deprecated
    public static final String GROUP_TYPE_FAMILY = "FMLY";
    @Deprecated
    public static final String GROUP_TYPE_GENERAL = "GNRL";
    public static final String GROUP_TYPE_SA_FAMILY_GROUP = "SAFM";
    public static final String GROUP_TYPE_UNNAMED = "UNM1";
    private int mActiveMemberCount;
    private boolean mAuthorityManage;
    private long mContentsUpdatedTime;
    private Uri mCoverThumbnailContentUri;
    private Uri mCoverThumbnailFileUri;
    private long mCreatedTime;
    private GroupAuthority.AuthorityType mDefaultMemberAuthority;
    private long mExpireTime;
    private String mGroupId;
    private String mGroupName;
    private String mGroupType;
    private InvitationLink mInvitationLink;
    private String mLeaderId;
    private int mMaxMemberCount;
    private Map mMetaData;
    private long mUpdatedTime;

    @Deprecated
    public Group(String str, String str2, String str3, String str4, Uri uri, Uri uri2, long j2, int i2, int i7) {
        this.mGroupId = str;
        this.mGroupName = str2;
        this.mGroupType = str3;
        this.mLeaderId = str4;
        this.mCoverThumbnailFileUri = uri;
        this.mCoverThumbnailContentUri = uri2;
        this.mCreatedTime = j2;
        this.mMaxMemberCount = i2;
        this.mActiveMemberCount = i7;
        this.mAuthorityManage = false;
    }

    public int getActiveMemberCount() {
        return this.mActiveMemberCount;
    }

    public boolean getAuthorityManage() {
        return this.mAuthorityManage;
    }

    public long getContentsUpdatedTime() {
        return this.mContentsUpdatedTime;
    }

    @Deprecated
    public Uri getCoverThumbnailContentUri() {
        return this.mCoverThumbnailContentUri;
    }

    @Deprecated
    public Uri getCoverThumbnailFileUri() {
        return this.mCoverThumbnailFileUri;
    }

    public long getCreatedTime() {
        return this.mCreatedTime;
    }

    public GroupAuthority.AuthorityType getDefaultMemberAuthority() {
        return this.mDefaultMemberAuthority;
    }

    public long getExpireTime() {
        return this.mExpireTime;
    }

    public String getGroupId() {
        return this.mGroupId;
    }

    public String getGroupName() {
        return this.mGroupName;
    }

    public String getGroupType() {
        return this.mGroupType;
    }

    public InvitationLink getInvitationLink() {
        return this.mInvitationLink;
    }

    public String getLeaderId() {
        return this.mLeaderId;
    }

    public int getMaxMemberCount() {
        return this.mMaxMemberCount;
    }

    @Deprecated
    public Map getMetaData() {
        return this.mMetaData;
    }

    public long getUpdatedTime() {
        return this.mUpdatedTime;
    }

    @Deprecated
    public Group(String str, String str2, String str3, String str4, Uri uri, Uri uri2, long j2, int i2, int i7, long j3, Map map, long j8, long j10, boolean z, InvitationLink invitationLink) {
        this.mGroupId = str;
        this.mGroupName = str2;
        this.mGroupType = str3;
        this.mLeaderId = str4;
        this.mCoverThumbnailFileUri = uri;
        this.mCoverThumbnailContentUri = uri2;
        this.mCreatedTime = j2;
        this.mMaxMemberCount = i2;
        this.mActiveMemberCount = i7;
        this.mUpdatedTime = j3;
        this.mMetaData = map;
        this.mContentsUpdatedTime = j8;
        this.mExpireTime = j10;
        this.mAuthorityManage = z;
        this.mInvitationLink = invitationLink;
    }

    public Group(String str, String str2, String str3, String str4, long j2, int i2, int i7, long j3, long j8, long j10) {
        this.mAuthorityManage = false;
        this.mGroupId = str;
        this.mGroupName = str2;
        this.mGroupType = str3;
        this.mLeaderId = str4;
        this.mCoverThumbnailFileUri = null;
        this.mCoverThumbnailContentUri = null;
        this.mCreatedTime = j2;
        this.mMaxMemberCount = i2;
        this.mActiveMemberCount = i7;
        this.mUpdatedTime = j3;
        this.mMetaData = new HashMap();
        this.mContentsUpdatedTime = j8;
        this.mExpireTime = j10;
        this.mAuthorityManage = false;
    }

    public Group(String str, String str2, String str3, String str4, long j2, int i2, int i7, long j3, Map map, long j8, long j10, boolean z) {
        this.mGroupId = str;
        this.mGroupName = str2;
        this.mGroupType = str3;
        this.mLeaderId = str4;
        this.mCoverThumbnailFileUri = null;
        this.mCoverThumbnailContentUri = null;
        this.mCreatedTime = j2;
        this.mMaxMemberCount = i2;
        this.mActiveMemberCount = i7;
        this.mUpdatedTime = j3;
        this.mMetaData = map;
        this.mContentsUpdatedTime = j8;
        this.mExpireTime = j10;
        this.mAuthorityManage = z;
    }

    public Group(String str, String str2, String str3, String str4, long j2, int i2, int i7, long j3, Map map, long j8, long j10, boolean z, InvitationLink invitationLink) {
        this.mGroupId = str;
        this.mGroupName = str2;
        this.mGroupType = str3;
        this.mLeaderId = str4;
        this.mCoverThumbnailFileUri = null;
        this.mCoverThumbnailContentUri = null;
        this.mCreatedTime = j2;
        this.mMaxMemberCount = i2;
        this.mActiveMemberCount = i7;
        this.mUpdatedTime = j3;
        this.mMetaData = map;
        this.mContentsUpdatedTime = j8;
        this.mExpireTime = j10;
        this.mAuthorityManage = z;
        this.mInvitationLink = invitationLink;
    }

    public Group(String str, String str2, String str3, String str4, long j2, int i2, int i7, long j3, long j8, long j10, boolean z, InvitationLink invitationLink, GroupAuthority.AuthorityType authorityType) {
        this.mAuthorityManage = false;
        this.mGroupId = str;
        this.mGroupName = str2;
        this.mGroupType = str3;
        this.mLeaderId = str4;
        this.mCoverThumbnailFileUri = null;
        this.mCoverThumbnailContentUri = null;
        this.mCreatedTime = j2;
        this.mMaxMemberCount = i2;
        this.mActiveMemberCount = i7;
        this.mUpdatedTime = j3;
        this.mMetaData = new HashMap();
        this.mContentsUpdatedTime = j8;
        this.mExpireTime = j10;
        this.mAuthorityManage = z;
        this.mInvitationLink = invitationLink;
        this.mDefaultMemberAuthority = authorityType;
    }
}
