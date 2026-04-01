package com.samsung.android.gallery.module.data;

import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.data.MdeData;
import com.samsung.android.gallery.module.mdebase.db.SharingCursorHolder;
import com.samsung.android.gallery.module.mdebase.utils.MdeMetadataParser;
import com.samsung.android.gallery.support.shotmode.ShotModeList;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SharingMediaItemBuilder {
    public static void addShotMode(MediaItem mediaItem) {
        if (mediaItem.mShotMode != null) {
            return;
        }
        if (mediaItem.isVideo() && mediaItem.is360VideoExtended()) {
            mediaItem.mShotMode = "360_video";
        } else if (mediaItem.mRecordingMode > 0) {
            Optional.ofNullable(ShotModeList.getInstance().getByRecMode(mediaItem.mRecordingMode)).ifPresent(new a(mediaItem, 5));
        } else if (mediaItem.mSefFileType != -1) {
            Optional.ofNullable(ShotModeList.getInstance().getBySefValue(mediaItem.mSefFileType)).ifPresent(new a(mediaItem, 16));
        }
    }

    public static void applyItem(MediaItem mediaItem, MdeData.MdeMetadata mdeMetadata) {
        if (mdeMetadata != null) {
            Optional.ofNullable(mdeMetadata.dateTaken).ifPresent(new a(mediaItem, 20));
            Optional.ofNullable(mdeMetadata.width).ifPresent(new a(mediaItem, 9));
            Optional.ofNullable(mdeMetadata.height).ifPresent(new a(mediaItem, 11));
            Optional.ofNullable(mdeMetadata.orientation).ifPresent(new a(mediaItem, 12));
            Optional.ofNullable(mdeMetadata.orientationTag).ifPresent(new a(mediaItem, 13));
            Optional.ofNullable(mdeMetadata.fileSize).ifPresent(new a(mediaItem, 14));
            Optional.ofNullable(mdeMetadata.latitude).ifPresent(new a(mediaItem, 15));
            Optional.ofNullable(mdeMetadata.longitude).ifPresent(new a(mediaItem, 17));
            Optional.ofNullable(mdeMetadata.sefFileType).ifPresent(new a(mediaItem, 18));
            Optional.ofNullable(mdeMetadata.sefFileSubType).ifPresent(new a(mediaItem, 19));
            Optional.ofNullable(mdeMetadata.groupType).ifPresent(new a(mediaItem, 21));
            Optional.ofNullable(mdeMetadata.recordingMode).ifPresent(new a(mediaItem, 22));
            Optional.ofNullable(mdeMetadata.is360Video).ifPresent(new a(mediaItem, 23));
            Optional.ofNullable(mdeMetadata.fileDuration).ifPresent(new a(mediaItem, 24));
            Optional.ofNullable(mdeMetadata.path).ifPresent(new a(mediaItem, 25));
            Optional.ofNullable(mdeMetadata.cropRectRatioList).ifPresent(new a(mediaItem, 26));
            Optional.ofNullable(mdeMetadata.smartCropRectRatio).ifPresent(new a(mediaItem, 27));
            Optional.ofNullable(mdeMetadata.totalSmartCropRatio).ifPresent(new a(mediaItem, 6));
            Optional.ofNullable(mdeMetadata.totalSmartCropDeviceRatio).ifPresent(new a(mediaItem, 7));
            Optional.ofNullable(mdeMetadata.spaceCoverRectRatio).ifPresent(new a(mediaItem, 8));
            Optional ofNullable = Optional.ofNullable(mdeMetadata.videoColorFormat);
            Objects.requireNonNull(mediaItem);
            ofNullable.ifPresent(new a(mediaItem, 10));
        }
    }

    private static MediaItem buildMdeGroup(Cursor cursor) {
        MediaItem mediaItem = new MediaItem();
        SharingCursorHolder cursorHolder = SharingCursorHolder.getCursorHolder(cursor, SharingCursorHolder.SharedCursorType.GROUP_CURSOR);
        mediaItem.mTitle = cursor.getString(cursorHolder.indexGroupName);
        mediaItem.mCount = cursor.getInt(cursorHolder.indexMembersCount);
        String string = cursor.getString(cursorHolder.indexThumbnailLocalPath);
        mediaItem.mPath = string;
        mediaItem.mStorageType = StorageType.Sharing;
        mediaItem.mMediaType = MediaType.Image;
        if (string != null) {
            mediaItem.mPath = string.replace("file://", "");
        }
        MdeData.of(mediaItem).parseGroup(cursorHolder);
        return mediaItem;
    }

    private static MediaItem buildMdeGroupMember(Cursor cursor) {
        MediaItem mediaItem = new MediaItem();
        SharingCursorHolder cursorHolder = SharingCursorHolder.getCursorHolder(cursor, SharingCursorHolder.SharedCursorType.GROUP_MEMBER_CURSOR);
        mediaItem.mDisplayName = cursor.getString(cursorHolder.indexName);
        MdeData.of(mediaItem).parseGroupMember(cursorHolder);
        return mediaItem;
    }

    private static MediaItem buildMdeSharedItemPrimitive(Cursor cursor) {
        MediaType mediaType;
        MediaItem mediaItem = new MediaItem();
        SharingCursorHolder cursorHolder = SharingCursorHolder.getCursorHolder(cursor, SharingCursorHolder.SharedCursorType.ITEM_CURSOR);
        long j2 = cursor.getLong(cursorHolder.indexId);
        mediaItem.mMediaID = j2;
        mediaItem.mFileID = j2;
        String string = cursor.getString(cursorHolder.indexTitle);
        mediaItem.mTitle = string;
        mediaItem.mDisplayName = string;
        String string2 = cursor.getString(cursorHolder.indexMimeType);
        mediaItem.mMimeType = string2;
        if (string2 != null) {
            String lowerCase = string2.toLowerCase(Locale.ENGLISH);
            mediaItem.mMimeType = lowerCase;
            if (lowerCase.startsWith("image")) {
                mediaType = MediaType.Image;
            } else {
                mediaType = MediaType.Video;
            }
            mediaItem.mMediaType = mediaType;
        } else {
            mediaItem.mMimeType = "*";
            mediaItem.mMediaType = MediaType.Image;
        }
        mediaItem.mPath = cursor.getString(cursorHolder.indexThumbnailLocalPath);
        mediaItem.mStorageType = StorageType.Sharing;
        MdeData parse = MdeData.of(mediaItem).parse(cursorHolder);
        applyItem(mediaItem, parse.mdeMetadata);
        addShotMode(mediaItem);
        if (Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_TRASH) && parse.expiryDate > 0) {
            TrashData.of(mediaItem).expiryDate = parse.expiryDate;
        }
        return mediaItem;
    }

    private static MediaItem buildMdeSharingInvitationListItem(Cursor cursor) {
        MediaItem mediaItem = new MediaItem();
        SharingCursorHolder cursorHolder = SharingCursorHolder.getCursorHolder(cursor, SharingCursorHolder.SharedCursorType.INVITATION_CURSOR);
        mediaItem.mMediaID = cursor.getLong(cursorHolder.indexId);
        if (PreferenceFeatures.OneUi41.SHARING_INVITATION_ON_ALBUMS) {
            mediaItem.mTitle = cursorHolder.getString(cursor, cursorHolder.indexSpaceName, (String) null);
            mediaItem.mStorageType = StorageType.Sharing;
            mediaItem.mPath = cursor.getString(cursorHolder.indexRequesterThumbnailLocalPath);
        }
        MdeData.of(mediaItem).parseInvitation(cursorHolder);
        return mediaItem;
    }

    private static MediaItem buildMdeSpacePrimitive(Cursor cursor) {
        MediaItem mediaItem = new MediaItem();
        SharingCursorHolder cursorHolder = SharingCursorHolder.getCursorHolder(cursor, SharingCursorHolder.SharedCursorType.SPACE_CURSOR);
        int i2 = cursorHolder.getInt(cursor, cursorHolder.indexId, 0);
        mediaItem.mAlbumID = i2;
        mediaItem.mFileID = (long) i2;
        mediaItem.mCount = cursorHolder.getInt(cursor, cursorHolder.indexMediaCount, 0);
        String string = cursorHolder.getString(cursor, cursorHolder.indexTitle, (String) null);
        mediaItem.mTitle = string;
        mediaItem.mDisplayName = string;
        mediaItem.mStorageType = StorageType.Sharing;
        mediaItem.mMediaType = MediaType.Image;
        applyItem(mediaItem, MdeData.of(mediaItem).parseSpace(cursorHolder).mdeMetadata);
        if (Features.isEnabled(Features.SUPPORT_SHARED_RAW_QUERY)) {
            loadCover(cursor, mediaItem, cursorHolder);
        }
        return mediaItem;
    }

    public static MediaItem createPrimitive(Cursor cursor) {
        MediaType mediaType;
        MediaItem mediaItem = new MediaItem();
        SharingCursorHolder cursorHolder = SharingCursorHolder.getCursorHolder(cursor, SharingCursorHolder.SharedCursorType.ITEM_CURSOR);
        String string = cursor.getString(cursorHolder.indexMimeType);
        mediaItem.mMimeType = string;
        if (string != null) {
            if (cursor.getString(cursorHolder.indexMimeType).startsWith("image")) {
                mediaType = MediaType.Image;
            } else {
                mediaType = MediaType.Video;
            }
            mediaItem.mMediaType = mediaType;
        } else {
            mediaItem.mMediaType = MediaType.Image;
        }
        mediaItem.mStorageType = StorageType.Sharing;
        applyItem(mediaItem, MdeData.of(mediaItem).parseMetadata(cursor.getString(cursorHolder.indexMetaData)).mdeMetadata);
        return mediaItem;
    }

    public static MediaItem createSharingAlbum(Cursor cursor) {
        return buildMdeSpacePrimitive(cursor);
    }

    public static MediaItem createSharingGroup(Cursor cursor) {
        return buildMdeGroup(cursor);
    }

    public static MediaItem createSharingGroupMember(Cursor cursor) {
        return buildMdeGroupMember(cursor);
    }

    public static MediaItem createSharingInvitationListItem(Cursor cursor) {
        return buildMdeSharingInvitationListItem(cursor);
    }

    public static MediaItem createSharingItem(Cursor cursor) {
        return buildMdeSharedItemPrimitive(cursor);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$applyItem$1(MediaItem mediaItem, Integer num) {
        int intValue = num.intValue();
        mediaItem.mWidthInDB = intValue;
        mediaItem.mWidth = intValue;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$applyItem$2(MediaItem mediaItem, Integer num) {
        int intValue = num.intValue();
        mediaItem.mHeightInDB = intValue;
        mediaItem.mHeight = intValue;
    }

    private static MediaItem loadCover(Cursor cursor, MediaItem mediaItem, SharingCursorHolder sharingCursorHolder) {
        MediaType mediaType;
        String string = sharingCursorHolder.getString(cursor, sharingCursorHolder.indexCoverItem, (String) null);
        if (string != null) {
            String[] split = string.split("\\|");
            if (split.length > 0) {
                string = split[0];
            }
            mediaItem.setPath(string);
            if (split.length > 1) {
                mediaItem.setMimeType(split[1]);
                if (TextUtils.isEmpty(split[1]) || split[1].startsWith("image")) {
                    mediaType = MediaType.Image;
                } else {
                    mediaType = MediaType.Video;
                }
                mediaItem.setMediaType(mediaType);
            }
            if (split.length > 2) {
                String str = split[2];
                String string2 = cursor.getString(sharingCursorHolder.indexCoverId);
                if (!TextUtils.isEmpty(string2) && !string2.equals(str)) {
                    MdeData.of(mediaItem).isUserCoverItem = false;
                }
            }
            if (split.length > 3) {
                mediaItem.setOrientation(MdeMetadataParser.getOrientationFromMetadata(split[3]));
                mediaItem.setOrientationTag(MdeMetadataParser.getOrientationTagFromMetadata(split[3]));
            }
            return mediaItem;
        }
        mediaItem.setPath("");
        mediaItem.setMediaType(MediaType.Image);
        mediaItem.setMimeType("image/jpg");
        return mediaItem;
    }
}
