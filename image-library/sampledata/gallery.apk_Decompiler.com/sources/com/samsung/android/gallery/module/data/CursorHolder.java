package com.samsung.android.gallery.module.data;

import android.database.Cursor;
import com.samsung.android.gallery.database.dal.mp.table.MpAnalyzeInfoTable;
import com.samsung.android.gallery.support.cache.LruCache;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordInfoBundleWrapper;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class CursorHolder {
    static final boolean SUPPORT_MP_CAM_MODEL = Features.isEnabled(Features.SUPPORT_MP_CAM_MODEL);
    static final boolean SUPPORT_MP_CAPTURE_FRAMERATE = Features.isEnabled(Features.SUPPORT_MP_CAPTURE_FRAMERATE);
    private static final LruCache<Integer, CursorHolder> sMap = new LruCache<>(10);
    int index360Video = -1;
    int indexAddress = -1;
    int indexAlbumCount = -1;
    int indexAlbumHide = -1;
    int indexAlbumID = -1;
    int indexAlbumOrder = -1;
    int indexAlbumSyncStatus = -1;
    int indexAnalyzeInfo = -1;
    int indexAudioCodec = -1;
    int indexBestImage = -1;
    int indexBucketID = -1;
    int indexCamModel = -1;
    int indexCaptureFrameRate = -1;
    int indexCapturedApp = -1;
    int indexCapturedPath = -1;
    int indexCapturedUrl = -1;
    int indexCleanState = -1;
    int indexCloudId = -1;
    int indexCloudOriginalBinaryHash = -1;
    int indexCloudOriginalBinarySize = -1;
    int indexCloudOriginalSize = -1;
    int indexCloudRevision = -1;
    int indexCloudServerId = -1;
    int indexCloudServerPath = -1;
    int indexCloudThumbPath = -1;
    int indexClusterType = -1;
    int indexCmhFileId = -1;
    int indexColorTransfer = -1;
    int indexContactRawId = -1;
    int indexCount = -1;
    int indexCoverPath = -1;
    int indexCoverRect = -1;
    int indexCreatureEssentialGroup = -1;
    int indexCreatureFaceGroupId = -1;
    int indexCreatureFaceHide = -1;
    int indexCreatureFacePosRatio = -1;
    int indexCreatureFaceRecommendedId = -1;
    int indexCreatureFaceScore = -1;
    int indexCreatureGroupUuid = -1;
    int indexCreatureRelationship = -1;
    int indexCreatureTagId = -1;
    int indexCreatureTagName = -1;
    int indexCreatureType = -1;
    int indexCreatureUniqueDays = -1;
    int indexCreatureUsable = -1;
    int indexCreatureUuid = -1;
    int indexData2 = -1;
    int indexDataStamp = -1;
    int indexDateAdded = -1;
    int indexDateModified = -1;
    int indexDateTaken = -1;
    int indexDay = -1;
    int indexDefaultPath = -1;
    int indexDeleteGroupId = -1;
    int indexDeleteTime = -1;
    int indexDeleteType = -1;
    int indexDraftPath = -1;
    int indexDrm = -1;
    int indexDuration = -1;
    int indexDynamicViewInfo = -1;
    int indexEffectType = -1;
    int indexExpiredPeriod = -1;
    int indexExpiredTime = -1;
    int indexFavourite = -1;
    int indexFileAddedStatus = -1;
    int indexFileStatus = -1;
    int indexGroupMediaId = -1;
    int indexGroupType = -1;
    int indexHash = -1;
    int indexHdr10Video = -1;
    int indexHeight = -1;
    int indexId = -1;
    int indexLatitude = -1;
    int indexLatitudeList = -1;
    int indexLocalTime = -1;
    int indexLongitude = -1;
    int indexLongitudeList = -1;
    int indexMainCategory = -1;
    int indexMediaCache = -1;
    int indexMediaId = -1;
    int indexMediaType = -1;
    int indexMemoryCollagePath = -1;
    int indexMemoryCollageType = -1;
    int indexMemoryCollageVideoIndex = -1;
    int indexMemoryStreetName = -1;
    int indexMimeType = -1;
    int indexOrientation = -1;
    int indexOrientationTag = -1;
    int indexOriginPath = -1;
    int indexOriginTitle = -1;
    int indexOriginalFileHash = -1;
    int indexOwnerPackage = -1;
    int indexPDCEndTime = -1;
    int indexPDCStartTime = -1;
    int indexParentAlbumId = -1;
    int indexPath = -1;
    int indexPoiCategory = -1;
    int indexPoiName = -1;
    int indexPortraitPath = -1;
    int indexRecapStoryPath = -1;
    int indexRecordingMode = -1;
    int indexRecordingType = -1;
    int indexRect = -1;
    int indexRelativePath = -1;
    int indexResolution = -1;
    int indexRestoreExtra = -1;
    int indexRevitalizedPath = -1;
    int indexRevitalizedType = -1;
    int indexSceneRegion = -1;
    int indexSefFileSubType = -1;
    int indexSefFileType = -1;
    int indexSefFileTypes = -1;
    int indexSize = -1;
    int indexStorageType = -1;
    int indexStoryBgmInfo = -1;
    int indexStoryCover = -1;
    int indexStoryCoverFaceRectRatio = -1;
    int indexStoryCoverRectRatio = -1;
    int indexStoryCreationTime = -1;
    int indexStoryDisplayOrder = -1;
    int indexStoryEndTime = -1;
    int indexStoryFavorite = -1;
    int indexStoryFilter = -1;
    int indexStoryHideRuleEndTime = -1;
    int indexStoryHideRuleGroupId = -1;
    int indexStoryHideRuleId = -1;
    int indexStoryHideRuleIndividual = -1;
    int indexStoryHideRuleModifyTime = -1;
    int indexStoryHideRulePersonId = -1;
    int indexStoryHideRulePrivateData = -1;
    int indexStoryHideRuleProvider = -1;
    int indexStoryHideRuleStartTime = -1;
    int indexStoryHideRuleType = -1;
    int indexStoryHighlightVideo = -1;
    int indexStoryMfcScore = -1;
    int indexStoryNotifyStatus = -1;
    int indexStorySaType = -1;
    int indexStoryScoring = -1;
    int indexStoryStartTime = -1;
    int indexStoryTheme = -1;
    int indexStoryType = -1;
    int indexStoryUpdatedByUser = -1;
    int indexStoryUserCuration = -1;
    int indexStoryUserEnter = -1;
    int indexSubCategory = -1;
    int indexSubGroupCategory = -1;
    int indexSuggestExtra = -1;
    int indexSuggestExtraData = -1;
    int indexSuggestExtraValue = -1;
    int indexSuggestType = -1;
    int indexTagType = -1;
    int indexTimeStamp = -1;
    int indexTitle = -1;
    int indexTotalSize = -1;
    int indexTotalSmartCropDeviceRatio = -1;
    int indexTotalSmartCropRatio = -1;
    int indexTrashMediaId = -1;
    int indexVideoCodec = -1;
    int indexVideoFrameId = -1;
    int indexVideoFramePos = -1;
    int indexVideoThumbStartTime = -1;
    int indexVolumeName = -1;
    int indexWidth = -1;
    Cursor mCursor;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum CursorType {
        FILE_CURSOR,
        TIMELINE_CURSOR,
        SUGGESTS_CURSOR,
        STORY_APPBAR_CURSOR,
        STORY_HIDE_RULE
    }

    private CursorHolder(Cursor cursor, CursorType cursorType) {
        this.mCursor = cursor;
        if (cursorType == CursorType.TIMELINE_CURSOR) {
            setTimelineIndex(cursor);
            setAlbumIndex(cursor);
        } else if (cursorType == CursorType.FILE_CURSOR) {
            setTimelineIndex(cursor);
            setAlbumIndex(cursor);
            setFileIndex(cursor);
            setStoryIndex(cursor);
            setSearchIndex(cursor);
            setCleanOutIndex(cursor);
            setRevitalizedIndex(cursor);
            setTrashIndex(cursor);
        } else if (cursorType == CursorType.SUGGESTS_CURSOR) {
            setSuggestionsIndex(cursor);
        } else if (cursorType == CursorType.STORY_APPBAR_CURSOR) {
            setStoryAppBarIndex(cursor);
        } else if (cursorType == CursorType.STORY_HIDE_RULE) {
            setStoryHideRuleIndex(cursor);
        }
    }

    public static void clear() {
        sMap.evictAll();
    }

    public static int createHashKey(Cursor cursor) {
        return cursor.hashCode();
    }

    public static CursorHolder getCursorHolder(Cursor cursor, CursorType cursorType) {
        int createHashKey = createHashKey(cursor);
        LruCache<Integer, CursorHolder> lruCache = sMap;
        CursorHolder cursorHolder = lruCache.get(Integer.valueOf(createHashKey));
        if (cursorHolder != null) {
            return cursorHolder;
        }
        CursorHolder cursorHolder2 = new CursorHolder(cursor, cursorType);
        lruCache.put(Integer.valueOf(createHashKey), cursorHolder2);
        return cursorHolder2;
    }

    private void setAlbumIndex(Cursor cursor) {
        this.indexCount = cursor.getColumnIndex("__count");
        this.indexTitle = cursor.getColumnIndex("__Title");
        this.indexAlbumID = cursor.getColumnIndex("__albumID");
        this.indexBucketID = cursor.getColumnIndex("__bucketID");
        this.indexAlbumHide = cursor.getColumnIndex("__ishide");
        this.indexCoverPath = cursor.getColumnIndex("cover_path");
        this.indexDefaultPath = cursor.getColumnIndex("default_cover_path");
        this.indexCoverRect = cursor.getColumnIndex("cover_rect");
        this.indexAlbumOrder = cursor.getColumnIndex("album_order");
        this.indexAlbumCount = cursor.getColumnIndex("album_count");
        this.indexAlbumSyncStatus = cursor.getColumnIndex("album_sync_status");
    }

    private void setCleanOutIndex(Cursor cursor) {
        this.indexDeleteType = cursor.getColumnIndex("__deleteType");
        this.indexCleanState = cursor.getColumnIndex("__cleanState");
        this.indexDeleteGroupId = cursor.getColumnIndex("delete_group_id");
        this.indexTotalSize = cursor.getColumnIndex("total_size");
    }

    private void setFileIndex(Cursor cursor) {
        this.indexId = cursor.getColumnIndex("__absID");
        this.indexMediaId = cursor.getColumnIndex("__fileMediaId");
        this.indexMediaType = cursor.getColumnIndex("__mediaType");
        this.indexPath = cursor.getColumnIndex("__absPath");
        if (Features.isEnabled(Features.SUPPORT_PPP_DRAFT)) {
            this.indexDraftPath = cursor.getColumnIndex("__draftPath");
        }
        this.indexOrientation = cursor.getColumnIndex("__orientation");
        this.indexOrientationTag = cursor.getColumnIndex("__orientationTag");
        this.indexSize = cursor.getColumnIndex("__size");
        this.indexWidth = cursor.getColumnIndex("__width");
        this.indexHeight = cursor.getColumnIndex("__height");
        this.indexSefFileType = cursor.getColumnIndex("__sefFileType");
        this.indexSefFileSubType = cursor.getColumnIndex("__sefFileSubType");
        this.indexSefFileTypes = cursor.getColumnIndex("__sefFileTypes");
        this.indexRecordingMode = cursor.getColumnIndex("__recordingMode");
        this.indexRecordingType = cursor.getColumnIndex("__recordingType");
        this.index360Video = cursor.getColumnIndex("__is360Video");
        this.indexHdr10Video = cursor.getColumnIndex("__isHdr10Video");
        this.indexResolution = cursor.getColumnIndex("__resolution");
        this.indexOriginalFileHash = cursor.getColumnIndex("__origin_file_hash");
        this.indexGroupMediaId = cursor.getColumnIndex("__GroupMediaID");
        this.indexBestImage = cursor.getColumnIndex("__GroupMediaBest");
        this.indexGroupType = cursor.getColumnIndex("__group_type");
        this.indexSceneRegion = cursor.getColumnIndex("__sceneRegion");
        this.indexStorageType = cursor.getColumnIndex("__storageType");
        this.indexFavourite = cursor.getColumnIndex("__isFavourite");
        this.indexDrm = cursor.getColumnIndex("__isDrm");
        this.indexMimeType = cursor.getColumnIndex("__mimeType");
        this.indexDuration = cursor.getColumnIndex("__fileDuration");
        this.indexOriginPath = cursor.getColumnIndex("__originPath");
        this.indexOriginTitle = cursor.getColumnIndex("__originTitle");
        this.indexCloudId = cursor.getColumnIndex("__cloudId");
        this.indexFileStatus = cursor.getColumnIndex("__fileStatus");
        this.indexCloudServerId = cursor.getColumnIndex("__cloudServerId");
        this.indexCloudServerPath = cursor.getColumnIndex("__cloudServerPath");
        this.indexHash = cursor.getColumnIndex("__hash");
        this.indexData2 = cursor.getColumnIndex("__data2");
        this.indexCloudOriginalSize = cursor.getColumnIndex("__cloudOriginalSize");
        this.indexCloudOriginalBinaryHash = cursor.getColumnIndex("__cloudOriginalBinaryHash");
        this.indexCloudOriginalBinarySize = cursor.getColumnIndex("__cloudOriginalBinarySize");
        this.indexCapturedApp = cursor.getColumnIndex("__capturedAPP");
        this.indexCapturedUrl = cursor.getColumnIndex("__capturedURL");
        this.indexCapturedPath = cursor.getColumnIndex("__capturedPATH");
        this.indexCloudThumbPath = cursor.getColumnIndex("__cloudTP");
        this.indexAddress = cursor.getColumnIndex("__Address");
        this.indexLatitude = cursor.getColumnIndex("__latitude");
        this.indexLongitude = cursor.getColumnIndex("__longitude");
        this.indexRestoreExtra = cursor.getColumnIndex("__restoreExtra");
        this.indexDateModified = cursor.getColumnIndex("__dateModified");
        this.indexDateAdded = cursor.getColumnIndex("__dateAdded");
        this.indexCloudRevision = cursor.getColumnIndex("__cloudRevision");
        this.indexVolumeName = cursor.getColumnIndex("__volumeName");
        this.indexRelativePath = cursor.getColumnIndex("__relativePath");
        this.indexOwnerPackage = cursor.getColumnIndex("__ownerPackage");
        this.indexDynamicViewInfo = cursor.getColumnIndex("__dynamicViewInfo");
        this.indexAudioCodec = cursor.getColumnIndex("__audioCodecInfo");
        this.indexVideoCodec = cursor.getColumnIndex("__videoCodecInfo");
        this.indexColorTransfer = cursor.getColumnIndex("__color_transfer");
        this.indexDataStamp = cursor.getColumnIndex("__dataStamp");
        if (SUPPORT_MP_CAPTURE_FRAMERATE) {
            this.indexCaptureFrameRate = cursor.getColumnIndex("__capture_framerate");
        }
        if (SUPPORT_MP_CAM_MODEL) {
            this.indexCamModel = cursor.getColumnIndex("__cam_model");
        }
        this.indexParentAlbumId = cursor.getColumnIndex("__parentAlbumId");
        if (MpAnalyzeInfoTable.SUPPORT) {
            this.indexAnalyzeInfo = cursor.getColumnIndex("__analyze_info");
        } else if (MpAnalyzeInfoTable.SUPPORT_CACHE) {
            this.indexMediaCache = cursor.getColumnIndex("__media_cache");
        }
        this.indexLocalTime = cursor.getColumnIndex("local_time");
        this.indexTimeStamp = cursor.getColumnIndex("__timestamp");
    }

    private void setRevitalizedIndex(Cursor cursor) {
        this.indexRevitalizedType = cursor.getColumnIndex("revitalized_type");
        this.indexRevitalizedPath = cursor.getColumnIndex("revitalized_path");
    }

    private void setSearchIndex(Cursor cursor) {
        this.indexMainCategory = cursor.getColumnIndex("__mainCategory");
        this.indexSubCategory = cursor.getColumnIndex("__subCategory");
        this.indexSubGroupCategory = cursor.getColumnIndex("__subGroupCategory");
        this.indexCreatureTagName = cursor.getColumnIndex("__creatureName");
        this.indexCreatureFacePosRatio = cursor.getColumnIndex("__creatureFacePosRatio");
        this.indexTagType = cursor.getColumnIndex("__tagType");
        this.indexCreatureFaceGroupId = cursor.getColumnIndex("__creatureFaceGroupID");
        this.indexCreatureTagId = cursor.getColumnIndex("__creatureID");
        this.indexCreatureFaceRecommendedId = cursor.getColumnIndex("__creatureFaceRecommendedID");
        this.indexCreatureFaceHide = cursor.getColumnIndex("__creatureFaceHide");
        this.indexCreatureFaceScore = cursor.getColumnIndex("__faceScore");
        this.indexCreatureType = cursor.getColumnIndex("__creatureType");
        this.indexCreatureRelationship = cursor.getColumnIndex("__creatureRelationship");
        this.indexContactRawId = cursor.getColumnIndex("__creatureContactRawId");
        this.indexCreatureUuid = cursor.getColumnIndex("__creatureContactUuid");
        this.indexClusterType = cursor.getColumnIndex("__clusterType");
        this.indexPDCStartTime = cursor.getColumnIndex("__pdcStartTime");
        this.indexPDCEndTime = cursor.getColumnIndex("__pdcEndTime");
        if (PreferenceFeatures.OneUi8x.SUPPORT_ESSENTIAL_FACES) {
            this.indexCreatureEssentialGroup = cursor.getColumnIndex("__facesEssentialGroup");
            this.indexCreatureUniqueDays = cursor.getColumnIndex("__facesUniqueDays");
            this.indexCreatureGroupUuid = cursor.getColumnIndex("__facesGroupId");
        }
        if (PreferenceFeatures.OneUi8x.SUPPORT_CREATURE_THUMB_FROM_VIDEO_FRAME) {
            this.indexVideoFrameId = cursor.getColumnIndex("__videoFrameId");
            this.indexVideoFramePos = cursor.getColumnIndex("__videoFamePos");
        }
    }

    private void setStoryHideRuleIndex(Cursor cursor) {
        this.indexStoryHideRuleId = cursor.getColumnIndex("__rule_id");
        this.indexStoryHideRuleProvider = cursor.getColumnIndex("__rule_provider");
        this.indexStoryHideRuleType = cursor.getColumnIndex("__rule_type");
        this.indexStoryHideRuleStartTime = cursor.getColumnIndex("__rule_startTime");
        this.indexStoryHideRuleEndTime = cursor.getColumnIndex("__rule_endTime");
        this.indexStoryHideRuleModifyTime = cursor.getColumnIndex("__rule_modifyTime");
        this.indexStoryHideRulePersonId = cursor.getColumnIndex("__rule_personId");
        this.indexStoryHideRuleGroupId = cursor.getColumnIndex("__rule_groupId");
        this.indexStoryHideRulePrivateData = cursor.getColumnIndex("__rule_private_data");
        this.indexStoryHideRuleIndividual = cursor.getColumnIndex("__rule_individual");
    }

    private void setStoryIndex(Cursor cursor) {
        this.indexStoryStartTime = cursor.getColumnIndex("__startTime");
        this.indexStoryEndTime = cursor.getColumnIndex("__endTime");
        this.indexStoryHighlightVideo = cursor.getColumnIndex("__highlightVideo");
        this.indexStoryCover = cursor.getColumnIndex("__storyCover");
        this.indexStoryNotifyStatus = cursor.getColumnIndex("__storyNotifyStatus");
        this.indexStoryType = cursor.getColumnIndex("__storyType");
        this.indexStoryCoverRectRatio = cursor.getColumnIndex("__storyCoverRectRatio");
        this.indexStoryCreationTime = cursor.getColumnIndex("creation_time");
        this.indexMemoryCollagePath = cursor.getColumnIndex("cover_path");
        this.indexMemoryCollageType = cursor.getColumnIndex("collage_type");
        this.indexMemoryCollageVideoIndex = cursor.getColumnIndex("collage_video_index");
        this.indexMemoryStreetName = cursor.getColumnIndex("__streetName");
        this.indexStoryFavorite = cursor.getColumnIndex("__story_favorite");
        this.indexStoryScoring = cursor.getColumnIndex("__story_scoring");
        this.indexRevitalizedPath = cursor.getColumnIndex("revitalized_path");
        this.indexPortraitPath = cursor.getColumnIndex("__portrait_path");
        this.indexStoryUserEnter = cursor.getColumnIndex("__story_user_enter");
        this.indexStoryUserCuration = cursor.getColumnIndex("__story_user_curation");
        this.indexTotalSmartCropRatio = cursor.getColumnIndex("__total_smartcrop_ratio");
        this.indexTotalSmartCropDeviceRatio = cursor.getColumnIndex("__total_smartcrop_device_ratio");
        this.indexStoryCoverFaceRectRatio = cursor.getColumnIndex("__cover_face_ratio");
        this.indexStorySaType = cursor.getColumnIndex("__storySaType");
        this.indexStoryTheme = cursor.getColumnIndex("__storyTheme");
        this.indexStoryFilter = cursor.getColumnIndex("__storyFilter");
        this.indexStoryBgmInfo = cursor.getColumnIndex("__storyBgmInfo");
        this.indexStoryUpdatedByUser = cursor.getColumnIndex("__storyUpdatedByUser");
        this.indexStoryMfcScore = cursor.getColumnIndex("__mfc_score");
        this.indexStoryDisplayOrder = cursor.getColumnIndex("__story_display_order");
        this.indexCmhFileId = cursor.getColumnIndex("__cmh_file_id");
        this.indexEffectType = cursor.getColumnIndex("__effect_type");
        this.indexFileAddedStatus = cursor.getColumnIndex("__file_added_status");
        this.indexPoiName = cursor.getColumnIndex("__poiName");
        this.indexPoiCategory = cursor.getColumnIndex("__poiCategory");
        this.indexRecapStoryPath = cursor.getColumnIndex("__recap_story_path");
        setStoryHideRuleIndex(cursor);
    }

    private void setSuggestionsIndex(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("__Type");
        this.indexSuggestType = columnIndex;
        if (columnIndex >= 0) {
            this.indexId = cursor.getColumnIndex("__absID");
            this.indexTitle = cursor.getColumnIndex("__Title");
            this.indexMediaId = cursor.getColumnIndex("__mediaId");
            this.indexPath = cursor.getColumnIndex("__data");
            this.indexWidth = cursor.getColumnIndex("__width");
            this.indexHeight = cursor.getColumnIndex("__height");
            this.indexOrientation = cursor.getColumnIndex("__orientation");
            this.indexOrientationTag = cursor.getColumnIndex("__orientationTag");
            this.indexRect = cursor.getColumnIndex("__rect");
            this.indexMediaType = cursor.getColumnIndex("__mediaType");
            this.indexStorageType = cursor.getColumnIndex("__storageType");
            this.indexSuggestExtra = cursor.getColumnIndex(KeywordInfoBundleWrapper.BUNDLE_KEY_EXTRA);
            this.indexSuggestExtraValue = cursor.getColumnIndex("extraValue");
            this.indexSuggestExtraData = cursor.getColumnIndex("extraData");
            this.indexDeleteType = cursor.getColumnIndex("__deleteType");
            this.indexVideoThumbStartTime = cursor.getColumnIndex("video_thumb_start_time");
        }
    }

    private void setTimelineIndex(Cursor cursor) {
        this.indexDay = cursor.getColumnIndex("__day");
        this.indexDateTaken = cursor.getColumnIndex("__dateTaken");
        this.indexAddress = cursor.getColumnIndex("__Address");
        this.indexLatitude = cursor.getColumnIndex("__latitude");
        this.indexLongitude = cursor.getColumnIndex("__longitude");
        this.indexLatitudeList = cursor.getColumnIndex("__latitudeList");
        this.indexLongitudeList = cursor.getColumnIndex("__longitudeList");
    }

    private void setTrashIndex(Cursor cursor) {
        this.indexDeleteTime = cursor.getColumnIndex("__deleteTime");
        this.indexExpiredPeriod = cursor.getColumnIndex("__expiredPeriod");
        if (PreferenceFeatures.OneUi6x.SUPPORT_ONE_TRASH) {
            this.indexExpiredTime = cursor.getColumnIndex("__expiredTime");
            this.indexTrashMediaId = cursor.getColumnIndex("__trashMediaId");
        }
    }

    public byte[] getBlob(int i2) {
        if (i2 < 0) {
            return null;
        }
        return this.mCursor.getBlob(i2);
    }

    public boolean getBoolean(int i2, boolean z) {
        if (i2 < 0) {
            return z;
        }
        if (this.mCursor.getInt(i2) == 1) {
            return true;
        }
        return false;
    }

    public double getDouble(int i2, double d) {
        if (i2 < 0) {
            return d;
        }
        return this.mCursor.getDouble(i2);
    }

    public double getFloat(int i2, double d) {
        if (i2 < 0) {
            return d;
        }
        return (double) this.mCursor.getFloat(i2);
    }

    public int getInt(int i2, int i7) {
        if (i2 < 0) {
            return i7;
        }
        return this.mCursor.getInt(i2);
    }

    public long getLong(int i2, long j2) {
        if (i2 < 0) {
            return j2;
        }
        return this.mCursor.getLong(i2);
    }

    public String getString(int i2, String str) {
        if (i2 < 0) {
            return str;
        }
        return this.mCursor.getString(i2);
    }

    public void setStoryAppBarIndex(Cursor cursor) {
        this.indexAlbumID = cursor.getColumnIndex("__albumID");
        this.indexId = cursor.getColumnIndex("__absID");
        this.indexMediaType = cursor.getColumnIndex("__mediaType");
        this.indexPath = cursor.getColumnIndex("__absPath");
        this.indexOrientation = cursor.getColumnIndex("__orientation");
        this.indexOrientationTag = cursor.getColumnIndex("__orientationTag");
        this.indexWidth = cursor.getColumnIndex("__width");
        this.indexHeight = cursor.getColumnIndex("__height");
        this.indexStoryCoverRectRatio = cursor.getColumnIndex("__storyCoverRectRatio");
        this.indexStoryCreationTime = cursor.getColumnIndex("creation_time");
    }
}
