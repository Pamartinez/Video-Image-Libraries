package com.samsung.android.gallery.module.data;

import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.database.dal.abstraction.table.DbTable;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.data.CursorHolder;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.shotmode.ShotMode;
import com.samsung.android.gallery.support.shotmode.ShotModeList;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.ExifTag;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.StringCompat;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MediaItemBuilder {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class ShotModeListHolder {
        static final ShotModeList instance = ShotModeList.getInstance();
    }

    private static void addAlbumData(MediaItem mediaItem, CursorHolder cursorHolder) {
        String str;
        boolean z = false;
        mediaItem.mCount = cursorHolder.getInt(cursorHolder.indexCount, 0);
        String string = cursorHolder.getString(cursorHolder.indexTitle, (String) null);
        if (string == null) {
            if (mediaItem.isCloudOnly()) {
                str = mediaItem.getCloudServerPath();
            } else {
                str = mediaItem.getPath();
            }
            string = FileUtils.getBucketNameFromPath(str);
        }
        if (string != null) {
            mediaItem.mDisplayName = string;
            String category = mediaItem.getCategory();
            if (category == null || "Search Auto Complete".equalsIgnoreCase(category)) {
                mediaItem.mTitle = string;
            }
        }
        mediaItem.mBucketID = cursorHolder.getInt(cursorHolder.indexBucketID, -1);
        mediaItem.mAlbumID = cursorHolder.getInt(cursorHolder.indexAlbumID, 0);
        if (cursorHolder.getInt(cursorHolder.indexAlbumHide, 0) == 1) {
            z = true;
        }
        mediaItem.mIsAlbumHide = z;
        int i2 = cursorHolder.indexCoverRect;
        if (i2 != -1) {
            String string2 = cursorHolder.getString(i2, "");
            if (!TextUtils.isEmpty(string2) && string2.contains(";")) {
                setAlbumCover(mediaItem, (String) null, string2);
            }
        }
        mediaItem.mVolumeName = cursorHolder.getString(cursorHolder.indexVolumeName, (String) null);
    }

    private static void addCloudData(MediaItem mediaItem, CursorHolder cursorHolder) {
        String string = cursorHolder.getString(cursorHolder.indexCloudServerId, (String) null);
        if (string != null || mediaItem.mStorageType == StorageType.Cloud) {
            mediaItem.mCloudData.parse(cursorHolder, string);
        }
    }

    private static void addExtraFileData(MediaItem mediaItem, CursorHolder cursorHolder) {
        if (mediaItem.mDateTaken == 0) {
            mediaItem.mDateTaken = cursorHolder.getLong(cursorHolder.indexDateTaken, 0);
        }
        String string = cursorHolder.getString(cursorHolder.indexDay, (String) null);
        if (string != null) {
            mediaItem.mDateRaw = string;
        } else if (cursorHolder.indexDay >= 0) {
            String isoLocalDate = TimeUtil.getIsoLocalDate(mediaItem.mDateTaken);
            mediaItem.mDateRaw = isoLocalDate;
            if (isoLocalDate.length() > 10) {
                Log.w("MediaItemBuilder", "WrongDate{" + mediaItem.mDateTaken + GlobalPostProcInternalPPInterface.SPLIT_REGEX + mediaItem.mDateRaw + GlobalPostProcInternalPPInterface.SPLIT_REGEX + Logger.getEncodedString(mediaItem.getPath()) + "}");
                mediaItem.mDateRaw = "9999-12-31";
            }
        }
        DetailsData of2 = DetailsData.of(mediaItem);
        String string2 = cursorHolder.getString(cursorHolder.indexAddress, (String) null);
        if (!TextUtils.isEmpty(string2) && !"null".equals(string2)) {
            if (string2.contains("|")) {
                String[] split = string2.split("\\|");
                int i2 = 3;
                if (split.length > 3) {
                    if (PreferenceFeatures.OneUi5x.SUPPORT_ADDRESS_FROM_DATABASE) {
                        of2.addressInDB = string2;
                    }
                    if (PreferenceFeatures.OneUi6x.VISUAL_SEARCH_61) {
                        of2.countryInDB = split[0];
                    }
                    while (true) {
                        if (i2 < 0) {
                            break;
                        }
                        String str = split[i2];
                        if (!TextUtils.isEmpty(str) && !TextUtils.equals(str, "null")) {
                            mediaItem.mLocation = split[i2];
                            break;
                        }
                        i2--;
                    }
                } else {
                    Log.e("MediaItemBuilder", "fail parsing address : ".concat(string2));
                }
            } else {
                mediaItem.mLocation = makeAddressString(string2);
            }
        }
        addLatLngData(mediaItem, cursorHolder);
        mediaItem.mFavorite = cursorHolder.getBoolean(cursorHolder.indexFavourite, false);
        of2.capturedApp = cursorHolder.getString(cursorHolder.indexCapturedApp, (String) null);
        of2.capturedUrl = cursorHolder.getString(cursorHolder.indexCapturedUrl, (String) null);
        of2.capturedPath = cursorHolder.getString(cursorHolder.indexCapturedPath, (String) null);
        mediaItem.mOwnerPackage = cursorHolder.getString(cursorHolder.indexOwnerPackage, (String) null);
        if (PreferenceFeatures.OneUi40.SUPPORT_NONDESTRUCTIVE_SLOW_MO) {
            mediaItem.mOriginalFileHash = cursorHolder.getString(cursorHolder.indexOriginalFileHash, (String) null);
        }
    }

    public static void addGroupMode(MediaItem mediaItem, CursorHolder cursorHolder) {
        if (mediaItem.mGroupMode == null) {
            int i2 = mediaItem.mGroupType;
            if (i2 > 0) {
                int i7 = cursorHolder.getInt(cursorHolder.indexCount, -1);
                mediaItem.mCount = i7;
                if (i7 > 1 || i7 == DbTable.UNLOADED) {
                    if (mediaItem.mGroupMediaId == 0) {
                        Log.w("MediaItemBuilder", "wrong group media id=" + mediaItem.mGroupType + ", g=" + mediaItem.mGroupMediaId + ", i=" + mediaItem.mFileID + " " + Logger.getEncodedString(mediaItem.mPath));
                    } else if (i2 == 1) {
                        mediaItem.mGroupMode = "burst_shot";
                    } else if (i2 == 2) {
                        mediaItem.mGroupMode = "Similar photo";
                    } else if (i2 == 3) {
                        mediaItem.mGroupMode = "Single Taken";
                    }
                } else if (i7 != 1) {
                } else {
                    if (mediaItem.isSingleTakenPostProcessing()) {
                        Log.d("MediaItemBuilder", "first single take ppp file");
                        mediaItem.mGroupMode = "Single Taken";
                    } else if (mediaItem.getGroupType() == 3) {
                        Log.w((CharSequence) "MediaItemBuilder", "only one single take file. sef_file_type is not ppp", Integer.valueOf(mediaItem.getGroupType()), Long.valueOf(mediaItem.getGroupMediaId()), Integer.valueOf(mediaItem.getSefFileType()), Integer.valueOf(mediaItem.getSefFileSubType()), mediaItem.getSefFileTypes());
                    }
                }
            } else if (mediaItem.mGroupMediaId <= 0) {
            } else {
                if (PreferenceFeatures.OneUi41.SUPPORT_DIRECTORS_VIEW && (isDirectorsView(mediaItem) || isDualRec(mediaItem))) {
                    mediaItem.mDirectorsViewGroupId = mediaItem.mGroupMediaId;
                    mediaItem.mGroupMediaId = 0;
                } else if (SdkConfig.atLeast(SdkConfig.GED.Q)) {
                    mediaItem.mGroupMediaId = 0;
                } else {
                    int i8 = cursorHolder.getInt(cursorHolder.indexCount, -1);
                    mediaItem.mCount = i8;
                    if (i8 > 1 || i8 < 0) {
                        mediaItem.mGroupMode = "burst_shot";
                    }
                }
            }
        }
    }

    private static void addLatLngData(MediaItem mediaItem, CursorHolder cursorHolder) {
        mediaItem.mLatitude = cursorHolder.getDouble(cursorHolder.indexLatitude, MapUtil.INVALID_LOCATION);
        mediaItem.mLongitude = cursorHolder.getDouble(cursorHolder.indexLongitude, MapUtil.INVALID_LOCATION);
        mediaItem.mLatitudeList = cursorHolder.getString(cursorHolder.indexLatitudeList, (String) null);
        mediaItem.mLongitudeList = cursorHolder.getString(cursorHolder.indexLongitudeList, (String) null);
    }

    private static void addShotMode(MediaItem mediaItem) {
        if (mediaItem.mShotMode != null) {
            return;
        }
        if (mediaItem.isVideo() && mediaItem.is360VideoExtended()) {
            mediaItem.mShotMode = "360_video";
        } else if (isValidRecordingMode(mediaItem)) {
            Optional.ofNullable(ShotModeListHolder.instance.getByRecMode(mediaItem.mRecordingMode)).ifPresent(new a(mediaItem, 3));
        } else {
            int i2 = mediaItem.mSefFileType;
            if (i2 != -1) {
                Optional.ofNullable(ShotModeListHolder.instance.getBySefValue(i2)).ifPresent(new a(mediaItem, 4));
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0086  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void addTagData(com.samsung.android.gallery.module.data.MediaItem r6, com.samsung.android.gallery.module.data.CursorHolder r7) {
        /*
            int r0 = r7.indexMainCategory
            r1 = 0
            java.lang.String r0 = r7.getString(r0, r1)
            if (r0 == 0) goto L_0x0011
            r6.mMainCategory = r0
            boolean r2 = r6.isScenesOrCreature(r0)
            r6.mScenesOrCreature = r2
        L_0x0011:
            int r2 = r7.indexSubCategory
            java.lang.String r2 = r7.getString(r2, r1)
            r3 = 0
            java.lang.String r4 = "Camera mode"
            if (r2 == 0) goto L_0x0048
            r6.mSubCategory = r2
            int r5 = r7.indexTagType
            int r5 = r7.getInt(r5, r3)
            r6.mTagType = r5
            boolean r5 = r4.equals(r0)
            if (r5 == 0) goto L_0x003f
            com.samsung.android.gallery.support.shotmode.ShotModeList r5 = com.samsung.android.gallery.module.data.MediaItemBuilder.ShotModeListHolder.instance
            com.samsung.android.gallery.support.shotmode.ShotMode r2 = r5.getByType(r2)
            if (r2 == 0) goto L_0x0048
            java.lang.String r5 = r2.type
            r6.mShotMode = r5
            int r2 = r2.titleRes
            java.lang.String r2 = com.samsung.android.gallery.support.utils.AppResources.getString(r2)
            goto L_0x0049
        L_0x003f:
            com.samsung.android.gallery.support.translation.TranslationManager r5 = com.samsung.android.gallery.support.translation.TranslationManager.getInstance()
            java.lang.String r2 = r5.getTranslatedString(r0, r2)
            goto L_0x0049
        L_0x0048:
            r2 = r1
        L_0x0049:
            int r5 = r7.indexSubGroupCategory
            java.lang.String r5 = r7.getString(r5, r1)
            r6.mSubGroupCategory = r5
            java.lang.String r5 = "My tags"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L_0x005f
            java.lang.String r5 = "#"
            java.lang.String r2 = i.C0212a.l(r5, r2)
        L_0x005f:
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x006b
            if (r2 != 0) goto L_0x006b
            java.lang.String r2 = updateShotModeExtra(r6, r7)
        L_0x006b:
            java.lang.String r4 = com.samsung.android.gallery.module.data.MediaItemBuilderDelegate.updateCreatureExtra(r6, r7)
            java.lang.String r5 = "People"
            boolean r5 = r5.equals(r0)
            if (r5 != 0) goto L_0x007f
            java.lang.String r5 = "Pet"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0080
        L_0x007f:
            r2 = r4
        L_0x0080:
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            if (r0 != 0) goto L_0x0088
            r6.mTitle = r2
        L_0x0088:
            int r0 = r7.indexClusterType
            java.lang.String r0 = r7.getString(r0, r1)
            com.samsung.android.gallery.database.dbtype.ExtrasID r1 = com.samsung.android.gallery.database.dbtype.ExtrasID.CLUSTER_TYPE
            r6.setExtra(r1, r0)
            int r0 = r7.indexPDCStartTime
            r1 = 0
            long r4 = r7.getLong(r0, r1)
            int r0 = r7.indexPDCEndTime
            long r0 = r7.getLong(r0, r1)
            com.samsung.android.gallery.database.dbtype.ExtrasID r7 = com.samsung.android.gallery.database.dbtype.ExtrasID.PDC_TIME_INFO
            r2 = 2
            long[] r2 = new long[r2]
            r2[r3] = r4
            r3 = 1
            r2[r3] = r0
            r6.setExtra(r7, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.data.MediaItemBuilder.addTagData(com.samsung.android.gallery.module.data.MediaItem, com.samsung.android.gallery.module.data.CursorHolder):void");
    }

    private static void addThumbnailData(MediaItem mediaItem, CursorHolder cursorHolder) {
        String string = cursorHolder.getString(cursorHolder.indexSceneRegion, (String) null);
        if (string != null) {
            ArrayList<RectF> listOf = RectUtils.listOf(string);
            mediaItem.mCropRectRatioList = listOf;
            if (listOf != null) {
                mediaItem.mSmartCropRectRatio = listOf.get(Math.min(listOf.size() - 1, 1));
            }
        }
    }

    private static void addVideoModeInGroup(MediaItem mediaItem, CursorHolder cursorHolder) {
        if (mediaItem.mSefFileTypes == null) {
            mediaItem.mSefFileTypes = cursorHolder.getString(cursorHolder.indexSefFileTypes, (String) null);
        }
    }

    private static MediaItem build(String str) {
        MediaItem mediaItem = new MediaItem();
        mediaItem.mTitle = str;
        return mediaItem;
    }

    private static MediaItem buildAlbumCover(Cursor cursor) {
        boolean z;
        MediaItem mediaItem = new MediaItem();
        CursorHolder holder = getHolder(cursor);
        mediaItem.mAlbumOrder = holder.getLong(holder.indexAlbumOrder, -1);
        String string = holder.getString(holder.indexTitle, (String) null);
        mediaItem.mTitle = string;
        mediaItem.mDisplayName = string;
        mediaItem.mCount = holder.getInt(holder.indexAlbumCount, -1);
        mediaItem.mAlbumID = holder.getInt(holder.indexBucketID, -1);
        boolean z3 = true;
        if (holder.getInt(holder.indexAlbumHide, 0) == 1) {
            z = true;
        } else {
            z = false;
        }
        mediaItem.mIsAlbumHide = z;
        mediaItem.mSefFileType = holder.getInt(holder.indexSefFileType, -1);
        if (holder.getInt(holder.indexDrm, 0) != 1) {
            z3 = false;
        }
        mediaItem.mIsDrm = z3;
        setAlbumCover(mediaItem, holder.getString(holder.indexCoverPath, (String) null), holder.getString(holder.indexCoverRect, (String) null));
        mediaItem.mPath = holder.getString(holder.indexDefaultPath, (String) null);
        mediaItem.mVolumeName = holder.getString(holder.indexVolumeName, (String) null);
        mediaItem.mCloudData.albumSyncStatus = holder.getInt(holder.indexAlbumSyncStatus, 0);
        return mediaItem;
    }

    private static MediaItem buildGpsDate(Cursor cursor) {
        MediaItem mediaItem = new MediaItem();
        CursorHolder holder = getHolder(cursor);
        mediaItem.mFileID = holder.getLong(holder.indexId, -1);
        mediaItem.mLatitude = holder.getDouble(holder.indexLatitude, MapUtil.INVALID_LOCATION);
        mediaItem.mLongitude = holder.getDouble(holder.indexLongitude, MapUtil.INVALID_LOCATION);
        mediaItem.mDateTaken = holder.getLong(holder.indexDateTaken, 0);
        return mediaItem;
    }

    private static MediaItem buildNewAlbumLabel(Cursor cursor) {
        CursorHolder holder = getHolder(cursor);
        MediaItem mediaItem = new MediaItem();
        mediaItem.mBucketID = holder.getInt(holder.indexBucketID, -1);
        mediaItem.mAlbumID = holder.getInt(holder.indexAlbumID, -1);
        mediaItem.mDateModified = holder.getLong(holder.indexDateModified, -1);
        mediaItem.mPath = holder.getString(holder.indexPath, (String) null);
        return mediaItem;
    }

    private static MediaItem buildPrimitive(Cursor cursor) {
        MediaItem mediaItem = new MediaItem();
        CursorHolder holder = getHolder(cursor);
        mediaItem.mMediaType = MediaType.valueOf(holder.getInt(holder.indexMediaType, MediaType.Unsupported.toInt()));
        String string = holder.getString(holder.indexMimeType, "");
        mediaItem.mMimeType = string;
        if (string != null) {
            mediaItem.mMimeType = string.toLowerCase(Locale.ENGLISH);
        }
        mediaItem.mOrientation = holder.getInt(holder.indexOrientation, 0);
        mediaItem.mOrientationTag = holder.getInt(holder.indexOrientationTag, 0);
        mediaItem.mWidth = holder.getInt(holder.indexWidth, 0);
        int i2 = holder.getInt(holder.indexHeight, 0);
        mediaItem.mHeight = i2;
        mediaItem.mWidthInDB = mediaItem.mWidth;
        mediaItem.mHeightInDB = i2;
        return mediaItem;
    }

    private static MediaItem buildSimple(Cursor cursor) {
        CursorHolder holder = getHolder(cursor);
        MediaItem defaultInfo = getDefaultInfo(holder);
        addGroupMode(defaultInfo, holder);
        return defaultInfo;
    }

    private static MediaItem buildTimeline(Cursor cursor) {
        MediaItem mediaItem = new MediaItem();
        CursorHolder timelineHolder = getTimelineHolder(cursor);
        addExtraFileData(mediaItem, timelineHolder);
        addAlbumData(mediaItem, timelineHolder);
        return mediaItem;
    }

    public static MediaItem cloneCreatureItem(MediaItem mediaItem) {
        if (mediaItem == null) {
            Log.e("MediaItemBuilder", "null input item");
            return null;
        }
        MediaItem mediaItem2 = new MediaItem();
        partialCopyBasicInfo(mediaItem, mediaItem2);
        partialCopyCreatureInfo(mediaItem, mediaItem2);
        return mediaItem2;
    }

    public static void copyAlbumData(MediaItem mediaItem, MediaItem mediaItem2) {
        mediaItem2.mDisplayName = mediaItem.mDisplayName;
        mediaItem2.mTitle = mediaItem.mTitle;
        mediaItem2.mCount = mediaItem.mCount;
        mediaItem2.mBucketID = mediaItem.mBucketID;
        mediaItem2.mAlbumID = mediaItem.mAlbumID;
        mediaItem2.mAlbumOrder = mediaItem.mAlbumOrder;
        mediaItem2.mIsAlbumHide = mediaItem.mIsAlbumHide;
        mediaItem2.mPath = mediaItem.mPath;
        mediaItem2.mFileDuration = mediaItem.mFileDuration;
        mediaItem2.mDateTaken = mediaItem.mDateTaken;
        mediaItem2.cloneTags(mediaItem);
        mediaItem2.mAlbumType = mediaItem.mAlbumType;
    }

    public static void copyCoverData(String str, MediaItem mediaItem, MediaItem mediaItem2) {
        if (!TextUtils.isEmpty(str)) {
            mediaItem2.mAlbumCover = str;
            RectF rectF = mediaItem.mCustomCropRect;
            mediaItem2.mCustomCropRect = rectF;
            mediaItem2.mSmartCropRectRatio = rectF;
            long j2 = mediaItem.mFileID;
            mediaItem2.mFileID = j2;
            mediaItem2.mMediaID = j2;
            mediaItem2.mOrientation = mediaItem.mOrientation;
            mediaItem2.mStorageType = mediaItem.mStorageType;
            mediaItem2.mMediaType = mediaItem.mMediaType;
            mediaItem2.mWidth = mediaItem.mWidth;
            mediaItem2.mHeight = mediaItem.mHeight;
            mediaItem2.mWidthInDB = mediaItem.mWidthInDB;
            mediaItem2.mHeightInDB = mediaItem.mHeightInDB;
            mediaItem2.mDateModified = mediaItem.mDateModified;
            mediaItem2.mFileDuration = mediaItem.mFileDuration;
            mediaItem2.setFileSize(mediaItem.getFileSize());
            mediaItem2.mDateTaken = mediaItem.mDateTaken;
            mediaItem2.mCloudData.originalSize = mediaItem.mCloudData.originalSize;
            mediaItem2.mOrientationTag = mediaItem.mOrientationTag;
        }
    }

    public static MediaItem create(Cursor cursor) {
        return build(cursor);
    }

    public static MediaItem createAlbumCover(Cursor cursor) {
        return buildAlbumCover(cursor);
    }

    public static MediaItem createBrokenMedia() {
        MediaItem mediaItem = new MediaItem();
        mediaItem.mDisplayName = "Broken";
        mediaItem.mTitle = "Broken";
        mediaItem.mIsBroken = true;
        mediaItem.mPath = "";
        mediaItem.mMediaType = MediaType.Image;
        mediaItem.mMimeType = "image/jpeg";
        return mediaItem;
    }

    public static MediaItem createEmpty() {
        MediaItem mediaItem = new MediaItem();
        mediaItem.mFileID = -1;
        mediaItem.mMediaType = MediaType.Image;
        return mediaItem;
    }

    public static MediaItem createEmptyAlbum(int i2, String str, String str2) {
        MediaItem mediaItem = new MediaItem();
        mediaItem.mAlbumID = i2;
        mediaItem.mDisplayName = str;
        mediaItem.mPath = str2;
        return mediaItem;
    }

    public static MediaItem createGpsDate(Cursor cursor) {
        return buildGpsDate(cursor);
    }

    public static MediaItem createListClusterItem(long j2, String str, int i2, ArrayList<String> arrayList, String str2, String str3) {
        MediaItem mediaItem = new MediaItem();
        mediaItem.mDateTaken = j2;
        mediaItem.mCount = i2;
        mediaItem.mDateRaw = str;
        mediaItem.mLocation = makeAddressString(String.join(GlobalPostProcInternalPPInterface.SPLIT_REGEX, arrayList));
        mediaItem.mLatitudeList = str2;
        mediaItem.mLongitudeList = str3;
        return mediaItem;
    }

    public static MediaItem createNewAlbumLabel(Cursor cursor) {
        return buildNewAlbumLabel(cursor);
    }

    public static MediaItem createPrimitive(Cursor cursor) {
        return buildPrimitive(cursor);
    }

    public static MediaItem createRealRatioCluster(Cursor cursor) {
        MediaItem build = build("RealRatioCluster");
        build.mWidthList = cursor.getString(cursor.getColumnIndex("__widthList"));
        return build;
    }

    public static MediaItem createShareData(Cursor cursor) {
        CursorHolder holder = getHolder(cursor);
        MediaItem mediaItem = new MediaItem();
        mediaItem.mFileID = holder.getLong(holder.indexId, -1);
        mediaItem.mMediaID = holder.getLong(holder.indexMediaId, -1);
        mediaItem.mBucketID = holder.getInt(holder.indexBucketID, -1);
        mediaItem.mAlbumID = holder.getInt(holder.indexAlbumID, -1);
        mediaItem.mPath = holder.getString(holder.indexPath, (String) null);
        mediaItem.mMediaType = MediaType.valueOf(holder.getInt(holder.indexMediaType, MediaType.Unsupported.toInt()));
        mediaItem.mStorageType = StorageType.valueOf(holder.getInt(holder.indexStorageType, StorageType.Local.ordinal()));
        mediaItem.mFileSize = holder.getLong(holder.indexSize, 0);
        String string = holder.getString(holder.indexCloudServerId, (String) null);
        if (string == null && mediaItem.mStorageType != StorageType.Cloud) {
            return mediaItem;
        }
        CloudData cloudData = mediaItem.mCloudData;
        cloudData.serverId = string;
        cloudData.serverPath = holder.getString(holder.indexCloudServerPath, (String) null);
        mediaItem.mCloudData.originalSize = holder.getLong(holder.indexCloudOriginalSize, 0);
        return mediaItem;
    }

    public static MediaItem createSimple(Cursor cursor) {
        return buildSimple(cursor);
    }

    public static MediaItem createSpanCluster(Cursor cursor) {
        MediaItem build = build("SpanCluster");
        build.mSpanList = cursor.getString(cursor.getColumnIndex("__spanList"));
        return build;
    }

    public static MediaItem createTempItem(String str, String str2) {
        MediaItem mediaItem = new MediaItem();
        mediaItem.mStorageType = StorageType.TempItem;
        mediaItem.mPath = str;
        mediaItem.mMimeType = str2;
        mediaItem.mFileSize = FileUtils.length(str);
        if (str2.startsWith("video/")) {
            mediaItem.mMediaType = MediaType.Video;
            MediaHelper.VideoInfo videoInfo = MediaHelper.getVideoInfo(str);
            mediaItem.mWidth = videoInfo.width;
            mediaItem.mHeight = videoInfo.height;
            mediaItem.mFileDuration = videoInfo.duration;
            mediaItem.mOrientation = videoInfo.orientation;
            return mediaItem;
        }
        mediaItem.mMediaType = MediaType.Image;
        ExifInterface exif = ExifUtils.getExif(str);
        if (exif != null) {
            mediaItem.mWidth = ExifUtils.getWidth(exif);
            mediaItem.mHeight = ExifUtils.getHeight(exif);
            mediaItem.mOrientation = ExifUtils.getOrientation(exif);
            mediaItem.mOrientationTag = ExifUtils.getOrientationTag(exif);
            return mediaItem;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str);
        mediaItem.mWidth = options.outWidth;
        mediaItem.mHeight = options.outHeight;
        return mediaItem;
    }

    public static MediaItem createTimeline(Cursor cursor) {
        return buildTimeline(cursor);
    }

    public static MediaItem createUnlockScreen() {
        MediaItem mediaItem = new MediaItem();
        mediaItem.mMediaType = MediaType.UnlockScreen;
        return mediaItem;
    }

    public static MediaItem createVirtualAlbum(int i2, String str) {
        MediaItem mediaItem = new MediaItem();
        mediaItem.setAlbumID(i2);
        mediaItem.setAlbumType(AlbumType.Virtual);
        mediaItem.setDisplayName(str);
        mediaItem.setTitle(str);
        mediaItem.setVirtualAlbum(true);
        mediaItem.setFileId(-1);
        return mediaItem;
    }

    public static String getCoverRectInfo(MediaItem mediaItem) {
        String str;
        RectF smartCropRectRatio = mediaItem.getSmartCropRectRatio();
        if (smartCropRectRatio != null) {
            str = "" + smartCropRectRatio.left + GlobalPostProcInternalPPInterface.SPLIT_REGEX + smartCropRectRatio.top + GlobalPostProcInternalPPInterface.SPLIT_REGEX + smartCropRectRatio.right + GlobalPostProcInternalPPInterface.SPLIT_REGEX + smartCropRectRatio.bottom + GlobalPostProcInternalPPInterface.SPLIT_REGEX;
        } else {
            str = "";
        }
        StringBuilder t = C0212a.t(str, ";");
        t.append(mediaItem.getFileId());
        t.append(";");
        t.append(mediaItem.getOrientation());
        t.append(";");
        t.append(StorageType.toInt(mediaItem.getStorageType(), StorageType.Local.toInt()));
        t.append(";");
        t.append(MediaType.toInt(mediaItem.getMediaType(), MediaType.Image.toInt()));
        t.append(";");
        t.append(mediaItem.getWidth());
        t.append(";");
        t.append(mediaItem.getHeight());
        t.append(";");
        t.append(mediaItem.getDateModified());
        t.append(";");
        t.append(StringCompat.valueOf(mediaItem.getCloudServerPath(), ""));
        t.append(";");
        t.append(mediaItem.getFileDuration());
        t.append(";");
        t.append(mediaItem.getFileSize());
        t.append(";");
        t.append(mediaItem.getDateTaken());
        t.append(";");
        t.append(mediaItem.getCloudOriginalSize());
        t.append(";");
        t.append(mediaItem.getOrientationTag());
        return t.toString();
    }

    private static MediaItem getDefaultInfo(CursorHolder cursorHolder) {
        return getDefaultInfo(cursorHolder, (MediaItem) null);
    }

    public static CursorHolder getHolder(Cursor cursor) {
        return CursorHolder.getCursorHolder(cursor, CursorHolder.CursorType.FILE_CURSOR);
    }

    private static CursorHolder getTimelineHolder(Cursor cursor) {
        return CursorHolder.getCursorHolder(cursor, CursorHolder.CursorType.TIMELINE_CURSOR);
    }

    private static boolean isDirectorsView(MediaItem mediaItem) {
        return "directors_view".equals(mediaItem.mShotMode);
    }

    private static boolean isDualRec(MediaItem mediaItem) {
        if (!Features.isEnabled(Features.SUPPORT_DUAL_REC) || !"Dual_Recording_Info".equals(mediaItem.mShotMode)) {
            return false;
        }
        return true;
    }

    private static boolean isValidRecordingMode(MediaItem mediaItem) {
        int i2 = mediaItem.mRecordingMode;
        if (i2 <= 0 || MediaItemUtil.isNoneDestructionSlowRecordingMode(i2)) {
            return false;
        }
        return true;
    }

    public static String makeAddressString(String str) {
        String str2;
        String str3;
        String str4;
        if (!str.contains(GlobalPostProcInternalPPInterface.SPLIT_REGEX)) {
            return str;
        }
        ArrayList arrayList = new ArrayList(new LinkedHashSet(Arrays.asList(str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX))));
        if (Features.isEnabled(Features.IS_JAPAN_DEVICE)) {
            str4 = "、";
            str2 = "...";
            str3 = str4;
        } else {
            str4 = ArcCommonLog.TAG_COMMA;
            str3 = " & ";
            str2 = "&...";
        }
        if (arrayList.size() == 2) {
            if (((String) arrayList.get(1)).contains(str2)) {
                return str;
            }
            return ((String) arrayList.get(0)) + str3 + ((String) arrayList.get(1));
        } else if (arrayList.size() <= 2) {
            return "";
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append((String) arrayList.get(0));
            sb2.append(str4);
            return C0212a.p(sb2, (String) arrayList.get(1), str2);
        }
    }

    private static void partialCopyBasicInfo(MediaItem mediaItem, MediaItem mediaItem2) {
        mediaItem2.mWidth = mediaItem.mWidth;
        mediaItem2.mHeight = mediaItem.mHeight;
        mediaItem2.mWidthInDB = mediaItem.mWidthInDB;
        mediaItem2.mHeightInDB = mediaItem.mHeightInDB;
        mediaItem2.mFileID = mediaItem.mFileID;
        mediaItem2.mMediaID = mediaItem.mMediaID;
        mediaItem2.mMediaType = mediaItem.mMediaType;
        mediaItem2.mMimeType = mediaItem.mMimeType;
        mediaItem2.mMimeTypeEnum = null;
        mediaItem2.mPath = mediaItem.mPath;
        mediaItem2.mDisplayName = mediaItem.mDisplayName;
        mediaItem2.mTitle = mediaItem.mTitle;
        mediaItem2.mOrientation = mediaItem.mOrientation;
        mediaItem2.mOrientationTag = mediaItem.mOrientationTag;
        mediaItem2.mStorageType = mediaItem.mStorageType;
        mediaItem2.mSefFileType = mediaItem.mSefFileType;
        mediaItem2.mSefFileSubType = mediaItem.mSefFileSubType;
        mediaItem2.mDateTaken = mediaItem.mDateTaken;
        mediaItem2.mDateModified = mediaItem.mDateModified;
        mediaItem2.mFileSize = mediaItem.mFileSize;
    }

    private static void partialCopyCreatureInfo(MediaItem mediaItem, MediaItem mediaItem2) {
        mediaItem2.mMainCategory = mediaItem.mMainCategory;
        mediaItem2.mSubCategory = mediaItem.mSubCategory;
        mediaItem2.mScenesOrCreature = true;
        CreatureData.copy(mediaItem, mediaItem2);
        mediaItem2.mFaceRectRatio = mediaItem.mFaceRectRatio;
    }

    private static MediaItem replaceIfStoryData(CursorHolder cursorHolder, MediaItem mediaItem) {
        if (cursorHolder.indexStoryType >= 0) {
            return (MediaItem) MediaItemStory.replaceIfRequired(mediaItem);
        }
        return mediaItem;
    }

    public static void setAlbumCover(MediaItem mediaItem, String str, String str2) {
        mediaItem.mAlbumCover = str;
        if (!TextUtils.isEmpty(str2)) {
            String[] split = str2.split(";");
            RectF stringToRectF = RectUtils.stringToRectF(split[0]);
            mediaItem.mCustomCropRect = stringToRectF;
            mediaItem.mSmartCropRectRatio = stringToRectF;
            long j2 = UnsafeCast.toLong(split[1], 0);
            mediaItem.mFileID = j2;
            mediaItem.mMediaID = j2;
            mediaItem.mOrientation = UnsafeCast.toInt(split[2], 0);
            mediaItem.mStorageType = StorageType.valueOf(UnsafeCast.toInt(split[3], StorageType.Local.toInt()));
            mediaItem.mMediaType = MediaType.valueOf(UnsafeCast.toInt(split[4], MediaType.Image.toInt()));
            if (split.length > 5) {
                mediaItem.mWidth = UnsafeCast.toInt(split[5], 0);
                int i2 = UnsafeCast.toInt(split[6], 0);
                mediaItem.mHeight = i2;
                mediaItem.mWidthInDB = mediaItem.mWidth;
                mediaItem.mHeightInDB = i2;
            }
            if (split.length > 7) {
                mediaItem.mDateModified = UnsafeCast.toLong(split[7], 0);
            }
            if (split.length > 9) {
                mediaItem.mFileDuration = UnsafeCast.toInt(split[9], 0);
            }
            if (split.length > 10) {
                mediaItem.setFileSize(UnsafeCast.toLong(split[10], 0));
            }
            if (split.length > 11) {
                mediaItem.mDateTaken = UnsafeCast.toLong(split[11], 0);
            }
            if (split.length > 12) {
                mediaItem.mCloudData.originalSize = UnsafeCast.toLong(split[12], 0);
            }
            if (split.length > 13) {
                mediaItem.mOrientationTag = UnsafeCast.toInt(split[13], 0);
            }
        } else if (str == null && str2 == null) {
            mediaItem.mAlbumCover = null;
            mediaItem.mCustomCropRect = null;
        }
    }

    public static void setSuggestExtraData(MediaItem mediaItem, String str) {
        try {
            MediaItemBuilderDelegate.setSuggestExtraData(mediaItem, str);
        } catch (ArrayIndexOutOfBoundsException e) {
            Log.w((CharSequence) "MediaItemBuilder", "setSuggestExtraData failed", (Throwable) e);
        }
    }

    public static void updateAlbumOrder(MediaItem mediaItem, long j2) {
        if (mediaItem != null) {
            mediaItem.mAlbumOrder = j2;
        }
    }

    public static void updateExif(MediaItem mediaItem, ExifTag exifTag) {
        mediaItem.mExifTag = exifTag;
        if (exifTag != null) {
            if (exifTag.location != null && mediaItem.getLatitude() == MapUtil.INVALID_LOCATION && mediaItem.getLongitude() == MapUtil.INVALID_LOCATION) {
                mediaItem.setLatLong(exifTag.location);
            }
            String path = mediaItem.getPath();
            if (!TextUtils.isEmpty(path)) {
                exifTag.modifiedTime = TimeUtil.getIsoLocalDateTime(FileUtils.getDateModified(path)).replace("-", NumericEnum.SEP);
                exifTag.utcTime = ExifTag.getSefUtcTime(path);
            }
        }
    }

    public static String updateShotModeExtra(MediaItem mediaItem, CursorHolder cursorHolder) {
        String str;
        ShotMode byRecMode;
        ShotMode bySefValue;
        int i2 = mediaItem.mSefFileType;
        if (i2 <= 0 || (bySefValue = ShotModeListHolder.instance.getBySefValue(i2)) == null) {
            str = null;
        } else {
            String str2 = bySefValue.type;
            mediaItem.mShotMode = str2;
            mediaItem.mSubCategory = str2;
            str = AppResources.getString(bySefValue.titleRes);
        }
        if (str != null || !isValidRecordingMode(mediaItem) || (byRecMode = ShotModeListHolder.instance.getByRecMode(mediaItem.mRecordingMode)) == null) {
            return str;
        }
        String str3 = byRecMode.type;
        mediaItem.mShotMode = str3;
        mediaItem.mSubCategory = str3;
        return AppResources.getString(byRecMode.titleRes);
    }

    public static MediaItem create(String str) {
        return build(str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0156  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0170  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x01e8  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0201  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0213  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0223  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0225  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0261  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x028a  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x02ce  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x02de  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.samsung.android.gallery.module.data.MediaItem getDefaultInfo(com.samsung.android.gallery.module.data.CursorHolder r27, com.samsung.android.gallery.module.data.MediaItem r28) {
        /*
            r0 = r27
            r1 = r28
            int r2 = r0.indexId
            r3 = -1
            long r7 = r0.getLong(r2, r3)
            int r2 = r0.indexMediaType
            com.samsung.android.gallery.database.dbtype.MediaType r5 = com.samsung.android.gallery.database.dbtype.MediaType.Unsupported
            int r5 = r5.toInt()
            int r2 = r0.getInt(r2, r5)
            com.samsung.android.gallery.database.dbtype.MediaType r11 = com.samsung.android.gallery.database.dbtype.MediaType.valueOf((int) r2)
            int r2 = r0.indexPath
            r5 = 0
            java.lang.String r2 = r0.getString(r2, r5)
            int r6 = r0.indexDraftPath
            java.lang.String r6 = r0.getString(r6, r5)
            int r9 = r0.indexDateTaken
            long r12 = r0.getLong(r9, r3)
            int r9 = r0.indexOrientation
            r10 = 0
            int r14 = r0.getInt(r9, r10)
            int r9 = r0.indexOrientationTag
            int r15 = r0.getInt(r9, r10)
            int r9 = r0.indexWidth
            int r9 = r0.getInt(r9, r10)
            int r5 = r0.indexHeight
            int r5 = r0.getInt(r5, r10)
            int r10 = r0.indexMediaId
            r18 = r9
            long r9 = r0.getLong(r10, r3)
            int r3 = r0.indexSize
            r19 = r5
            r4 = 0
            r20 = r14
            r21 = r15
            long r14 = r0.getLong(r3, r4)
            int r3 = r0.indexSefFileType
            r22 = r4
            r4 = -1
            int r3 = r0.getInt(r3, r4)
            java.lang.String r5 = "MediaItemBuilder"
            java.lang.String r4 = ""
            if (r2 == 0) goto L_0x00e7
            r0 = 2928(0xb70, float:4.103E-42)
            if (r3 != r0) goto L_0x00e7
            com.samsung.android.gallery.support.utils.Features r0 = com.samsung.android.gallery.support.utils.Features.SUPPORT_PPP_DRAFT
            boolean r0 = com.samsung.android.gallery.support.utils.Features.isEnabled(r0)
            if (r0 == 0) goto L_0x00da
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            if (r0 == 0) goto L_0x00d5
            int r0 = (r9 > r22 ? 1 : (r9 == r22 ? 0 : -1))
            r24 = r0
            java.lang.String r0 = ", dp="
            if (r24 == 0) goto L_0x00ab
            boolean r24 = android.text.TextUtils.isEmpty(r2)
            if (r24 != 0) goto L_0x00ab
            r24 = r3
            java.lang.String r3 = "PPP wait metadata parsing. p="
            java.lang.String r0 = i.C0212a.n(r3, r2, r0, r6)
            java.lang.Long r3 = java.lang.Long.valueOf(r7)
            java.lang.Long r6 = java.lang.Long.valueOf(r9)
            r25 = r14
            java.lang.Integer r14 = java.lang.Integer.valueOf(r24)
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r6, r14}
            com.samsung.android.gallery.support.utils.Log.w((java.lang.CharSequence) r5, (java.lang.String) r0, (java.lang.Object[]) r3)
            goto L_0x00e4
        L_0x00ab:
            r24 = r3
            r25 = r14
            java.lang.String r3 = "PPP draftPath has invalid data : p="
            java.lang.String r0 = i.C0212a.n(r3, r2, r0, r6)
            java.lang.Long r3 = java.lang.Long.valueOf(r7)
            java.lang.Long r6 = java.lang.Long.valueOf(r9)
            java.lang.Integer r14 = java.lang.Integer.valueOf(r24)
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r6, r14}
            com.samsung.android.gallery.support.utils.Log.e((java.lang.CharSequence) r5, (java.lang.String) r0, (java.lang.Object[]) r3)
            boolean r0 = com.samsung.android.gallery.support.config.DeviceConfig.DEBUG_BINARY
            if (r0 != 0) goto L_0x00cd
            goto L_0x00e4
        L_0x00cd:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "ppp file draft path is empty"
            r0.<init>(r1)
            throw r0
        L_0x00d5:
            r24 = r3
            r25 = r14
            goto L_0x00ec
        L_0x00da:
            r24 = r3
            r25 = r14
            java.lang.String r0 = "!@#\\$%\\^"
            java.lang.String r2 = r2.replaceAll(r0, r4)
        L_0x00e4:
            r6 = r2
            r2 = 0
            goto L_0x00ec
        L_0x00e7:
            r24 = r3
            r25 = r14
            goto L_0x00e4
        L_0x00ec:
            if (r1 == 0) goto L_0x0156
            r1.mPath = r6
            long r14 = r1.mFileID
            int r0 = (r14 > r7 ? 1 : (r14 == r7 ? 0 : -1))
            if (r0 != 0) goto L_0x0102
            boolean r0 = r1.isPostProcessing()
            if (r0 != 0) goto L_0x012d
            long r14 = r1.mMediaID
            int r0 = (r14 > r9 ? 1 : (r14 == r9 ? 0 : -1))
            if (r0 == 0) goto L_0x012d
        L_0x0102:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r3 = "maybe wrong item update : "
            r0.<init>(r3)
            long r14 = r1.mFileID
            r0.append(r14)
            java.lang.String r3 = "/"
            r0.append(r3)
            r0.append(r7)
            java.lang.String r6 = ","
            r0.append(r6)
            long r14 = r1.mMediaID
            r0.append(r14)
            r0.append(r3)
            r0.append(r9)
            java.lang.String r0 = r0.toString()
            com.samsung.android.gallery.support.utils.Log.w(r5, r0)
        L_0x012d:
            r1.mFileID = r7
            r1.mMediaID = r9
            r1.mMediaType = r11
            r1.mDateTaken = r12
            r14 = r20
            r1.mOrientation = r14
            r15 = r21
            r1.mOrientationTag = r15
            r5 = r25
            r1.mFileSize = r5
            r0 = r18
            r1.mWidth = r0
            r3 = r19
            r1.mHeight = r3
            r1.mWidthInDB = r0
            r1.mHeightInDB = r3
            r5 = 0
            r1.mMimeTypeEnum = r5
            r5 = r1
            r3 = r2
            r1 = r22
            r0 = 0
            goto L_0x016b
        L_0x0156:
            r0 = r18
            r3 = r19
            r14 = r20
            r15 = r21
            r16 = 0
            com.samsung.android.gallery.module.data.MediaItem r5 = new com.samsung.android.gallery.module.data.MediaItem
            r16 = r25
            r0 = 0
            r3 = r2
            r1 = r22
            r5.<init>(r6, r7, r9, r11, r12, r14, r15, r16, r18, r19)
        L_0x016b:
            com.samsung.android.gallery.database.dbtype.MediaType r6 = com.samsung.android.gallery.database.dbtype.MediaType.Video
            r10 = 1
            if (r11 != r6) goto L_0x01e8
            r6 = r27
            int r7 = r6.indexResolution
            java.lang.String r7 = r6.getString(r7, r4)
            r5.mResolution = r7
            int r7 = r6.indexRecordingMode
            int r7 = r6.getInt(r7, r0)
            r5.mRecordingMode = r7
            int r7 = r6.indexRecordingType
            int r7 = r6.getInt(r7, r0)
            r5.mRecordingType = r7
            int r7 = r6.index360Video
            int r7 = r6.getInt(r7, r0)
            if (r7 <= 0) goto L_0x0194
            r7 = r10
            goto L_0x0195
        L_0x0194:
            r7 = r0
        L_0x0195:
            r5.m360Video = r7
            int r7 = r6.indexDuration
            int r7 = r6.getInt(r7, r0)
            r5.mFileDuration = r7
            com.samsung.android.gallery.module.abstraction.VideoPropData r7 = com.samsung.android.gallery.module.abstraction.VideoPropData.of(r5)
            int r8 = r6.indexAudioCodec
            java.lang.String r8 = r6.getString(r8, r4)
            r7.audioCodec = r8
            int r8 = r6.indexVideoCodec
            java.lang.String r8 = r6.getString(r8, r4)
            r7.videoCodec = r8
            int r8 = r6.indexHdr10Video
            int r8 = r6.getInt(r8, r0)
            r7.hdr10Video = r8
            int r8 = r6.indexColorTransfer
            int r8 = r6.getInt(r8, r0)
            r7.colorTransfer = r8
            int r9 = r7.hdr10Video
            if (r9 <= 0) goto L_0x01cd
            r8 = 1001(0x3e9, float:1.403E-42)
            r5.setVideoColorFormat(r8)
            goto L_0x01d5
        L_0x01cd:
            r9 = 7
            if (r8 != r9) goto L_0x01d5
            r8 = 1000(0x3e8, float:1.401E-42)
            r5.setVideoColorFormat(r8)
        L_0x01d5:
            boolean r8 = com.samsung.android.gallery.module.data.CursorHolder.SUPPORT_MP_CAPTURE_FRAMERATE
            if (r8 == 0) goto L_0x01ea
            int r8 = r6.indexCaptureFrameRate     // Catch:{ NumberFormatException -> 0x01ea }
            r12 = 0
            double r8 = r6.getDouble(r8, r12)     // Catch:{ NumberFormatException -> 0x01ea }
            int r12 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
            if (r12 <= 0) goto L_0x01ea
            r7.videoCaptureFrameRate = r8     // Catch:{ NumberFormatException -> 0x01ea }
            goto L_0x01ea
        L_0x01e8:
            r6 = r27
        L_0x01ea:
            int r7 = r6.indexGroupMediaId
            long r7 = r6.getLong(r7, r1)
            r5.mGroupMediaId = r7
            int r7 = r6.indexBestImage
            int r7 = r6.getInt(r7, r0)
            r5.mBestImage = r7
            r7 = r24
            r5.mSefFileType = r7
            r8 = -1
            if (r7 == r8) goto L_0x0209
            int r7 = r6.indexSefFileSubType
            int r7 = r6.getInt(r7, r8)
            r5.mSefFileSubType = r7
        L_0x0209:
            int r7 = r6.indexMimeType
            java.lang.String r7 = r6.getString(r7, r4)
            r5.mMimeType = r7
            if (r7 == 0) goto L_0x021b
            java.util.Locale r8 = java.util.Locale.ENGLISH
            java.lang.String r7 = r7.toLowerCase(r8)
            r5.mMimeType = r7
        L_0x021b:
            int r7 = r6.indexDrm
            int r7 = r6.getInt(r7, r0)
            if (r7 != r10) goto L_0x0225
            r7 = r10
            goto L_0x0226
        L_0x0225:
            r7 = r0
        L_0x0226:
            r5.mIsDrm = r7
            int r7 = r6.indexDateAdded
            r8 = -1
            long r12 = r6.getLong(r7, r8)
            r5.mDateAdded = r12
            int r7 = r6.indexDateModified
            long r7 = r6.getLong(r7, r8)
            r5.mDateModified = r7
            int r7 = r6.indexStorageType
            com.samsung.android.gallery.database.dbtype.StorageType r8 = com.samsung.android.gallery.database.dbtype.StorageType.Local
            int r8 = r8.ordinal()
            int r7 = r6.getInt(r7, r8)
            com.samsung.android.gallery.database.dbtype.StorageType r7 = com.samsung.android.gallery.database.dbtype.StorageType.valueOf((int) r7)
            r5.mStorageType = r7
            int r7 = r6.indexGroupType
            int r7 = r6.getInt(r7, r0)
            r5.mGroupType = r7
            int r7 = r6.indexRelativePath
            r8 = 0
            java.lang.String r7 = r6.getString(r7, r8)
            r5.mRelativePath = r7
            int r7 = r6.indexDataStamp
            if (r7 < 0) goto L_0x0267
            java.lang.String r4 = r6.getString(r7, r4)
            r5.mDataStamp = r4
        L_0x0267:
            boolean r4 = com.samsung.android.gallery.module.data.CursorHolder.SUPPORT_MP_CAM_MODEL
            if (r4 == 0) goto L_0x0278
            com.samsung.android.gallery.database.dbtype.MediaType r4 = com.samsung.android.gallery.database.dbtype.MediaType.Image
            if (r11 != r4) goto L_0x0278
            int r4 = r6.indexCamModel
            r8 = 0
            java.lang.String r4 = r6.getString(r4, r8)
            r5.camModel = r4
        L_0x0278:
            com.samsung.android.gallery.module.data.DetailsData r4 = com.samsung.android.gallery.module.data.DetailsData.of(r5)
            r4.pppOriginal = r3
            int r3 = r6.indexParentAlbumId
            int r0 = r6.getInt(r3, r0)
            r4.parentAlbumId = r0
            boolean r0 = com.samsung.android.gallery.database.dal.mp.table.MpAnalyzeInfoTable.SUPPORT
            if (r0 == 0) goto L_0x02ce
            int r0 = r6.indexAnalyzeInfo
            r8 = 0
            java.lang.String r0 = r6.getString(r0, r8)
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            if (r3 != 0) goto L_0x02da
            java.util.Map r0 = com.samsung.android.gallery.database.dal.mp.table.MpAnalyzeInfoTable.unpack(r0)
            java.lang.String r3 = "__motionPhotoViewMode"
            java.lang.Object r3 = r0.get(r3)
            if (r3 == 0) goto L_0x02af
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            com.samsung.android.gallery.module.motionphoto.MotionPhotoViewMode r3 = com.samsung.android.gallery.module.motionphoto.MotionPhotoViewMode.valueOf((int) r3)
            r4.motionPhotoViewMode = r3
        L_0x02af:
            java.lang.String r3 = "__c2paType"
            java.lang.Object r3 = r0.get(r3)
            boolean r7 = r3 instanceof java.lang.Integer
            if (r7 == 0) goto L_0x02c3
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            if (r3 != r10) goto L_0x02c3
            r4.hasC2pa = r10
        L_0x02c3:
            java.lang.String r3 = "__media_cache"
            java.lang.Object r0 = r0.get(r3)
            java.lang.String r0 = (java.lang.String) r0
            r4.aiCache = r0
            goto L_0x02da
        L_0x02ce:
            boolean r0 = com.samsung.android.gallery.database.dal.mp.table.MpAnalyzeInfoTable.SUPPORT_CACHE
            if (r0 == 0) goto L_0x02da
            int r0 = r6.indexMediaCache
            byte[] r0 = r6.getBlob(r0)
            r4.aiCacheStream = r0
        L_0x02da:
            int r0 = r6.indexLocalTime
            if (r0 < 0) goto L_0x02e4
            long r3 = r6.getLong(r0, r1)
            r5.mDateLocal = r3
        L_0x02e4:
            int r0 = r6.indexTimeStamp
            long r0 = r6.getLong(r0, r1)
            r5.mTimeStamp = r0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.data.MediaItemBuilder.getDefaultInfo(com.samsung.android.gallery.module.data.CursorHolder, com.samsung.android.gallery.module.data.MediaItem):com.samsung.android.gallery.module.data.MediaItem");
    }

    public static MediaItem build(Cursor cursor) {
        return build(cursor, (MediaItem) null);
    }

    public static MediaItem create(long j2, String str, int i2, int i7, int i8, long j3, int i10, int i11, int i12, String str2, long j8, long j10) {
        MediaItem mediaItem = new MediaItem();
        mediaItem.mFileID = j2;
        mediaItem.mPath = str;
        mediaItem.mWidth = i2;
        mediaItem.mHeight = i7;
        mediaItem.mWidthInDB = i2;
        mediaItem.mHeightInDB = i7;
        mediaItem.mOrientation = i8;
        mediaItem.mDateModified = j3;
        mediaItem.mDateTaken = j8;
        mediaItem.mDateRaw = TimeUtil.getIsoLocalDate(j8);
        mediaItem.mFileSize = j10;
        mediaItem.mStorageType = StorageType.valueOf(i11);
        mediaItem.mMediaType = MediaType.valueOf(i12);
        mediaItem.mMimeType = str2;
        return mediaItem;
    }

    private static MediaItem build(Cursor cursor, MediaItem mediaItem) {
        CursorHolder holder = getHolder(cursor);
        MediaItem defaultInfo = getDefaultInfo(holder, mediaItem);
        addThumbnailData(defaultInfo, holder);
        addTagData(defaultInfo, holder);
        addExtraFileData(defaultInfo, holder);
        addCloudData(defaultInfo, holder);
        addAlbumData(defaultInfo, holder);
        MediaItemBuilderDelegate.addStoryData(defaultInfo, holder);
        MediaItemBuilderDelegate.addTrashData(defaultInfo, holder);
        addShotMode(defaultInfo);
        addGroupMode(defaultInfo, holder);
        addVideoModeInGroup(defaultInfo, holder);
        MediaItemBuilderDelegate.addCleanOutData(defaultInfo, holder);
        MediaItemBuilderDelegate.addRevitalizedData(defaultInfo, holder);
        MediaItemBuilderDelegate.addHighlightData(defaultInfo, holder);
        MediaItemBuilderDelegate.addPortraitData(defaultInfo, holder);
        MediaItemBuilderDelegate.addFilterAndToneData(defaultInfo);
        return replaceIfStoryData(holder, defaultInfo);
    }
}
