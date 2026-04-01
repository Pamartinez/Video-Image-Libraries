package com.samsung.android.gallery.module.mdebase.db;

import android.database.Cursor;
import com.samsung.android.gallery.support.cache.LruCache;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.mobileservice.social.group.provider.GroupContract;
import com.samsung.android.sdk.mobileservice.social.group.provider.GroupInvitationContract;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import com.samsung.android.sdk.mobileservice.social.share.provider.SharedItemContract;
import com.samsung.android.sdk.mobileservice.social.share.provider.SpaceContract;
import com.samsung.android.sdk.moneta.lifestyle.common.ContentProviderConstants;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharingCursorHolder {
    private static LruCache<Integer, SharingCursorHolder> sMap = new LruCache<>(10);
    public int indexAlbumExpiry = -1;
    public int indexContentsUpdateTime = -1;
    public int indexCoverId = -1;
    public int indexCoverItem = -1;
    public int indexCoverThumbnailUrl = -1;
    public int indexCreateTime = -1;
    public int indexCreatedTime = -1;
    public int indexCreator = -1;
    public int indexDownloadUrl = -1;
    public int indexExpiredTime = -1;
    public int indexExpiryDate = -1;
    public int indexFeatureId = -1;
    public int indexGroupCoverImage = -1;
    public int indexGroupId = -1;
    public int indexGroupName = -1;
    public int indexGroupThumbnailLocalPath = -1;
    public int indexHash = -1;
    public int indexId = -1;
    public int indexInstantMetaData = -1;
    public int indexIsOwnedByMe = -1;
    public int indexIsRead = -1;
    public int indexItemHash = -1;
    public int indexItemId = -1;
    public int indexItemStatus = -1;
    public int indexLastModifier = -1;
    public int indexLastSyncedTime = -1;
    public int indexMaxMemberCount = -1;
    public int indexMediaCount = -1;
    public int indexMediaType = -1;
    public int indexMemberStatus = -1;
    public int indexMembersCount = -1;
    public int indexMemo = -1;
    public int indexMessage = -1;
    public int indexMetaData = -1;
    public int indexMimeType = -1;
    public int indexModifiedTime = -1;
    public int indexMyUsage = -1;
    public int indexName = -1;
    public int indexOptionalId = -1;
    public int indexOriginalContentPath = -1;
    public int indexOriginalContentUri = -1;
    public int indexOriginalInHiddenFolderContentUri = -1;
    public int indexOriginalInHiddenFolderPath = -1;
    public int indexOriginalUrl = -1;
    public int indexOwner = -1;
    public int indexOwnerId = -1;
    public int indexProfileImagePath = -1;
    public int indexRequestedTime = -1;
    public int indexRequesterId = -1;
    public int indexRequesterName = -1;
    public int indexRequesterThumbnailLocalPath = -1;
    public int indexRequesterUpdatedTime = -1;
    public int indexSize = -1;
    public int indexSourceCid = -1;
    public int indexSpaceId = -1;
    public int indexSpaceName = -1;
    public int indexStatus = -1;
    public int indexStreamingUrl = -1;
    public int indexThumbnailHdUrl = -1;
    public int indexThumbnailLocalPath = -1;
    public int indexThumbnailUrl = -1;
    public int indexTitle = -1;
    public int indexType = -1;
    public int indexUnreadCount = -1;
    public int indexUpdatedTime = -1;
    public int indexWebLinkExpiry = -1;
    public int indexWebLinkUrl = -1;
    final Cursor mCursor;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum SharedCursorType {
        GROUP_CURSOR,
        GROUP_MEMBER_CURSOR,
        SPACE_CURSOR,
        ITEM_CURSOR,
        INVITATION_CURSOR
    }

    public SharingCursorHolder(Cursor cursor, SharedCursorType sharedCursorType) {
        this.mCursor = cursor;
        if (sharedCursorType == SharedCursorType.GROUP_CURSOR) {
            setGroupIndex(cursor);
        } else if (sharedCursorType == SharedCursorType.GROUP_MEMBER_CURSOR) {
            setGroupMemberIndex(cursor);
        } else if (sharedCursorType == SharedCursorType.SPACE_CURSOR) {
            setSpaceIndex(cursor);
        } else if (sharedCursorType == SharedCursorType.ITEM_CURSOR) {
            setItemIndex(cursor);
        } else if (sharedCursorType == SharedCursorType.INVITATION_CURSOR) {
            setInvitationListIndex(cursor);
        }
    }

    public static int createHashKey(Cursor cursor) {
        return cursor.toString().hashCode();
    }

    public static SharingCursorHolder getCursorHolder(Cursor cursor, SharedCursorType sharedCursorType) {
        int createHashKey = createHashKey(cursor);
        SharingCursorHolder sharingCursorHolder = sMap.get(Integer.valueOf(createHashKey));
        if (sharingCursorHolder != null) {
            return sharingCursorHolder;
        }
        SharingCursorHolder sharingCursorHolder2 = new SharingCursorHolder(cursor, sharedCursorType);
        sMap.put(Integer.valueOf(createHashKey), sharingCursorHolder2);
        return sharingCursorHolder2;
    }

    private void setGroupIndex(Cursor cursor) {
        this.indexId = cursor.getColumnIndex("_id");
        this.indexGroupId = cursor.getColumnIndex("groupId");
        this.indexUpdatedTime = cursor.getColumnIndex("updatedTime");
        this.indexGroupName = cursor.getColumnIndex("groupName");
        this.indexType = cursor.getColumnIndex("type");
        this.indexOwnerId = cursor.getColumnIndex(GroupContract.Group.LEADER_ID);
        this.indexGroupCoverImage = cursor.getColumnIndex(GroupContract.Group.COVER_IMAGE);
        this.indexCoverThumbnailUrl = cursor.getColumnIndex("coverThumbnailUrl");
        this.indexHash = cursor.getColumnIndex("hash");
        this.indexMetaData = cursor.getColumnIndex(GroupContract.Group.META_DATA);
        this.indexCreatedTime = cursor.getColumnIndex(GroupContract.Group.CREATED_TIME);
        this.indexMaxMemberCount = cursor.getColumnIndex(GroupContract.Group.MAX_MEMBER_COUNT);
        this.indexMembersCount = cursor.getColumnIndex(GroupContract.Group.MEMBERS_COUNT);
        this.indexMemberStatus = cursor.getColumnIndex(GroupContract.Group.MEMBER_STATUS);
        this.indexContentsUpdateTime = cursor.getColumnIndex("contents_update_time");
        this.indexLastSyncedTime = cursor.getColumnIndex(GroupContract.Group.LAST_SPACE_SYNCED_TIME);
        if (PreferenceFeatures.OneUi41.SHARING_ONE_PERSON_SHARED_ALBUM_NOTICE) {
            this.indexAlbumExpiry = cursor.getColumnIndex(GroupContract.Group.EXPIRE_TIME);
        }
        if (Features.isEnabled(Features.SUPPORT_SHARED_GROUP_RAW_QUERY)) {
            this.indexStatus = cursor.getColumnIndex("status");
            this.indexOptionalId = cursor.getColumnIndex("optionalId");
            this.indexName = cursor.getColumnIndex("name");
            this.indexProfileImagePath = cursor.getColumnIndex("profileImagePath");
            this.indexThumbnailLocalPath = cursor.getColumnIndex("groupCoverImagePath");
            return;
        }
        this.indexThumbnailLocalPath = cursor.getColumnIndex("thumbnail_local_path");
    }

    private void setGroupMemberIndex(Cursor cursor) {
        this.indexId = cursor.getColumnIndex("_id");
        this.indexName = cursor.getColumnIndex("name");
        this.indexOwnerId = cursor.getColumnIndex("id");
    }

    private void setInvitationListIndex(Cursor cursor) {
        this.indexId = cursor.getColumnIndex("_id");
        this.indexGroupId = cursor.getColumnIndex("groupId");
        this.indexFeatureId = cursor.getColumnIndex("featureId");
        this.indexSpaceName = cursor.getColumnIndex("groupName");
        this.indexGroupThumbnailLocalPath = cursor.getColumnIndex(GroupInvitationContract.Invitation.GROUP_THUMBNAIL_LOCAL_PATH);
        this.indexHash = cursor.getColumnIndex("hash");
        this.indexRequesterId = cursor.getColumnIndex(GroupInvitationContract.Invitation.REQUESTER_ID);
        this.indexRequesterName = cursor.getColumnIndex(GroupInvitationContract.Invitation.REQUESTER_NAME);
        this.indexRequesterThumbnailLocalPath = cursor.getColumnIndex(GroupInvitationContract.Invitation.REQUESTER_THUMBNAIL_LOCAL_PATH);
        this.indexRequesterUpdatedTime = cursor.getColumnIndex("requesterUpdatedTime");
        this.indexMessage = cursor.getColumnIndex("message");
        this.indexRequestedTime = cursor.getColumnIndex(GroupInvitationContract.Invitation.REQUESTED_TIME);
        this.indexExpiredTime = cursor.getColumnIndex("expiredTime");
    }

    private void setItemIndex(Cursor cursor) {
        this.indexId = cursor.getColumnIndex("__absID");
        this.indexSpaceId = cursor.getColumnIndex("spaceId");
        this.indexItemId = cursor.getColumnIndex(SharedItemContract.Item.ITEM_ID);
        this.indexTitle = cursor.getColumnIndex("title");
        this.indexMemo = cursor.getColumnIndex("memo");
        this.indexCreateTime = cursor.getColumnIndex("createTime");
        this.indexModifiedTime = cursor.getColumnIndex("modifiedTime");
        this.indexOwner = cursor.getColumnIndex("owner");
        this.indexMimeType = cursor.getColumnIndex("mime_type");
        this.indexMediaType = cursor.getColumnIndex(ContentProviderConstants.Entertainment.ParameterKey.MEDIA_TYPE);
        this.indexIsOwnedByMe = cursor.getColumnIndex("is_owned_by_me");
        this.indexOriginalUrl = cursor.getColumnIndex(SharedItemContract.Item.ORIGINAL_URL);
        this.indexDownloadUrl = cursor.getColumnIndex("download_url");
        this.indexThumbnailUrl = cursor.getColumnIndex("thumbnail_url");
        this.indexStreamingUrl = cursor.getColumnIndex("streaming_url");
        this.indexMetaData = cursor.getColumnIndex("meta_data");
        this.indexSize = cursor.getColumnIndex("size");
        this.indexThumbnailLocalPath = cursor.getColumnIndex("thumbnail_local_path");
        this.indexOriginalContentPath = cursor.getColumnIndex("original_content_path");
        this.indexLastSyncedTime = cursor.getColumnIndex("last_synced_time");
        this.indexSourceCid = cursor.getColumnIndex("source_cid");
        this.indexThumbnailHdUrl = cursor.getColumnIndex("thumbnail_hd_url");
        this.indexIsRead = cursor.getColumnIndex("is_read");
        if (Features.isEnabled(Features.USE_SHARED_DOWNLOAD_FILE_VERIFY)) {
            this.indexItemHash = cursor.getColumnIndex("hash");
            this.indexOriginalContentUri = cursor.getColumnIndex(BundleKey.ORIGINAL_CONTENT_URI);
        }
        if (Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_EDIT)) {
            this.indexInstantMetaData = cursor.getColumnIndex("instant_meta_data");
        }
        if (Features.isEnabled(Features.SUPPORT_HIDDEN_PATH_IN_SEMS_SHARE_DB)) {
            this.indexOriginalInHiddenFolderPath = cursor.getColumnIndex("original_in_hidden_folder_path");
            this.indexOriginalInHiddenFolderContentUri = cursor.getColumnIndex("original_in_hidden_folder_content_uri");
        }
        if (Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_TRASH)) {
            this.indexExpiryDate = cursor.getColumnIndex(BundleKey.EXPIRY_AT);
        }
        if (Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_ITEM_STATUS)) {
            this.indexItemStatus = cursor.getColumnIndex("__itemStatus");
            this.indexLastModifier = cursor.getColumnIndex("__lastModifier");
        }
        if (Features.isEnabled(Features.SUPPORT_CREATOR)) {
            this.indexCreator = cursor.getColumnIndex("__creator");
        }
    }

    private void setSpaceIndex(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("__absID");
        this.indexId = columnIndex;
        if (columnIndex < 0) {
            this.indexId = cursor.getColumnIndex("_id");
        }
        this.indexSpaceId = cursor.getColumnIndex("spaceId");
        this.indexTitle = cursor.getColumnIndex("title");
        this.indexMemo = cursor.getColumnIndex("memo");
        this.indexCreateTime = cursor.getColumnIndex("createTime");
        this.indexModifiedTime = cursor.getColumnIndex("modifiedTime");
        this.indexOwner = cursor.getColumnIndex("owner");
        this.indexGroupId = cursor.getColumnIndex("groupId");
        this.indexSourceCid = cursor.getColumnIndex(SpaceContract.Space.SORUCE_CID);
        this.indexMimeType = cursor.getColumnIndex("mime_type");
        this.indexDownloadUrl = cursor.getColumnIndex("download_url");
        this.indexThumbnailUrl = cursor.getColumnIndex("thumbnail_url");
        this.indexStreamingUrl = cursor.getColumnIndex("streaming_url");
        this.indexMetaData = cursor.getColumnIndex("meta_data");
        this.indexSize = cursor.getColumnIndex("size");
        this.indexThumbnailLocalPath = cursor.getColumnIndex("thumbnail_local_path");
        this.indexUnreadCount = cursor.getColumnIndex("unread_count");
        this.indexMediaCount = cursor.getColumnIndex(SpaceContract.Space.MEDIA_COUNT);
        this.indexIsOwnedByMe = cursor.getColumnIndex("is_owned_by_me");
        this.indexLastSyncedTime = cursor.getColumnIndex("last_synced_time");
        this.indexContentsUpdateTime = cursor.getColumnIndex("contents_update_time");
        if (PreferenceFeatures.OneUi41.SHARING_ALBUM_STORAGE_USAGE) {
            this.indexMyUsage = cursor.getColumnIndex("my_usage");
        }
        if (PreferenceFeatures.OneUi41.SHARING_ALBUM_WEB_LINK) {
            this.indexWebLinkUrl = cursor.getColumnIndex("weblink_url");
            this.indexWebLinkExpiry = cursor.getColumnIndex("weblink_expired_time");
        }
        if (Features.isEnabled(Features.SUPPORT_SHARED_RAW_QUERY)) {
            this.indexCoverId = cursor.getColumnIndex("cover_id");
            this.indexCoverItem = cursor.getColumnIndex("_cover_item");
        }
        if (PreferenceFeatures.OneUi6x.SUPPORT_SHARE_STORY) {
            this.indexMemo = cursor.getColumnIndex("memo");
        }
        int columnIndex2 = cursor.getColumnIndex("groupName");
        this.indexGroupName = columnIndex2;
        if (columnIndex2 != -1) {
            this.indexMembersCount = cursor.getColumnIndex(GroupContract.Group.MEMBERS_COUNT);
            this.indexCreatedTime = cursor.getColumnIndex(GroupContract.Group.CREATED_TIME);
            this.indexType = cursor.getColumnIndex("type");
        }
    }

    public int getInt(int i2, int i7) {
        return i2 < 0 ? i7 : this.mCursor.getInt(i2);
    }

    public long getLong(int i2, long j2) {
        return i2 < 0 ? j2 : this.mCursor.getLong(i2);
    }

    public String getString(int i2, String str) {
        return i2 < 0 ? str : this.mCursor.getString(i2);
    }

    public int getInt(Cursor cursor, int i2, int i7) {
        return i2 < 0 ? i7 : cursor.getInt(i2);
    }

    public long getLong(Cursor cursor, int i2, long j2) {
        return i2 < 0 ? j2 : cursor.getLong(i2);
    }

    public String getString(Cursor cursor, int i2, String str) {
        return i2 < 0 ? str : cursor.getString(i2);
    }
}
