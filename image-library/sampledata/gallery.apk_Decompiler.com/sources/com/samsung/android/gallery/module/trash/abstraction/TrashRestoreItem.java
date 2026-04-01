package com.samsung.android.gallery.module.trash.abstraction;

import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MapUtil;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.sgpl.trash.TrashExtra;
import com.samsung.android.sum.core.types.NumericEnum;
import com.samsung.scsp.media.Media;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrashRestoreItem extends TrashBaseItem {
    private Media mMedia;
    private String mNewPath;
    private String mOwnerPackage;
    private final boolean mPrimaryItem;
    private long mTrashMediaId;

    public TrashRestoreItem(Cursor cursor) {
        this.mPath = getString(cursor, "", "__absPath");
        this.mTitle = getString(cursor, (String) null, "__Title");
        boolean z = true;
        this.mMediaType = MediaType.valueOf(getInt(cursor, 1, "__mediaType"));
        this.mWidth = getInt(cursor, 0, "__width");
        this.mHeight = getInt(cursor, 0, "__height");
        this.mOrientation = getInt(cursor, 0, "__orientation");
        this.mStorageType = StorageType.valueOf(getInt(cursor, 1, "__storageType"));
        this.mGroupMediaId = getLong(cursor, "__burstGroupID");
        this.mBestImage = getInt(cursor, 0, "__bestImage");
        this.mOrientationTag = getInt(cursor, 0, "__orientationTag");
        String string = getString(cursor, "", "__originPath");
        if (string != null) {
            this.mTag.put(ExtrasID.ORIGIN_PATH.name(), string);
        }
        TrashData of2 = TrashData.of(this);
        of2.originalTitle = getString(cursor, (String) null, "__originTitle");
        of2.restoreData = getString(cursor, (String) null, "__restoreExtra");
        this.mVolumeName = getString(cursor, (String) null, "__volumeName");
        this.mCloudData.serverId = getString(cursor, (String) null, "__cloudServerId");
        this.mCloudData.thumbPath = getString(cursor, (String) null, "__cloudTP");
        this.mCloudData.originalBinaryHash = getString(cursor, (String) null, "__cloudOriginalBinaryHash");
        this.mCloudData.originalBinarySize = getLong(cursor, "__cloudOriginalBinarySize");
        if (FileUtils.hasSdcardVolume() && !FileUtils.isInExternalDir(TrashData.getOriginalPath(this))) {
            z = false;
        }
        this.mPrimaryItem = z;
        if (PreferenceFeatures.OneUi6x.SUPPORT_ONE_TRASH) {
            this.mOwnerPackage = getString(cursor, "", "__ownerPackage");
            this.mTrashMediaId = getLong(cursor, "__trashMediaId");
            this.mFileDuration = getInt(cursor, 0, "__fileDuration");
        }
        extractExtra();
        if (!TextUtils.isEmpty(this.mOriginFileHash)) {
            this.mOriginFileHash = getDecodedOriginFileHash(this.mOriginFileHash);
        } else if (SdkConfig.atLeast(SdkConfig.GED.V)) {
            this.mOriginFileHash = getString(cursor, "", "__origin_file_hash");
        }
        handleInvalidDataMatch(of2.restoreData, this.mCloudData.thumbPath);
    }

    private void extractExtra() {
        try {
            String str = TrashData.of(this).restoreData;
            if (!TextUtils.isEmpty(str)) {
                HashMap hashMap = (HashMap) TrashExtra.Builder.fromJsonString(new HashMap().getClass(), str);
                this.m360Video = toBoolean(hashMap.get(TrashExtra.IS_360_VIDEO), false).booleanValue();
                this.mIsDrm = toBoolean(hashMap.get(TrashExtra.IS_DRM), false).booleanValue();
                this.mIsFavorite = toBoolean(hashMap.get(TrashExtra.IS_FAVOURITE), false).booleanValue();
                if (hashMap.containsKey(TrashExtra.FILE_DURATION) && this.mFileDuration <= 0) {
                    this.mFileDuration = toInteger(hashMap.getOrDefault(TrashExtra.FILE_DURATION, 0), 0).intValue();
                }
                this.mRecordingMode = toInteger(hashMap.getOrDefault(TrashExtra.RECORDING_MODE, 0), 0).intValue();
                this.mSefFileSubType = toInteger(hashMap.getOrDefault(TrashExtra.SEF_FILE_SUB_TYPE, 0), 0).intValue();
                this.mSefFileType = toInteger(hashMap.getOrDefault(TrashExtra.SEF_FILE_TYPE, 0), 0).intValue();
                this.mSefFileTypes = (String) hashMap.getOrDefault(TrashExtra.SEF_FILE_TYPES, (Object) null);
                this.mFileSize = toLong(hashMap.getOrDefault(TrashExtra.SIZE, 0), 0L).longValue();
                this.mGroupType = toInteger(hashMap.getOrDefault(TrashExtra.GROUP_TYPE, 0), 0).intValue();
                this.mDateTaken = toLong(hashMap.getOrDefault(TrashExtra.DATE_TAKEN, 0), 0L).longValue();
                this.mLatitude = ((Double) hashMap.getOrDefault(TrashExtra.LATITUDE, 0)).doubleValue();
                this.mLongitude = ((Double) hashMap.getOrDefault(TrashExtra.LONGITUDE, 0)).doubleValue();
                DetailsData of2 = DetailsData.of(this);
                of2.capturedApp = (String) hashMap.getOrDefault(TrashExtra.CAPTURED_APP, (Object) null);
                of2.capturedUrl = (String) hashMap.getOrDefault(TrashExtra.CAPTURED_URL, (Object) null);
                this.mCloudData.originalSize = toLong(hashMap.getOrDefault(TrashExtra.CLOUD_ORIGINAL_SIZE, 0), 0L).longValue();
                this.mCloudData.revision = toInteger(hashMap.getOrDefault(TrashExtra.CLOUD_REVISION, 0), 0).intValue();
                this.mCloudData.hash = (String) hashMap.getOrDefault(TrashExtra.HASH, (Object) null);
                this.mCloudData.serverPath = (String) hashMap.getOrDefault(TrashExtra.CLOUD_SERVER_PATH, (Object) null);
                this.mMimeType = (String) hashMap.getOrDefault(TrashExtra.MIME_TYPE, (Object) null);
                this.mResolution = (String) hashMap.getOrDefault(TrashExtra.RESOLUTION, (Object) null);
                this.mRecordingType = toInteger(hashMap.getOrDefault(TrashExtra.RECORDING_TYPE, 0), 0).intValue();
                this.mIsHdr10Video = toBoolean(hashMap.get(TrashExtra.IS_HDR_10_VIDEO), false).booleanValue();
                this.mLCThumbPath = (String) hashMap.getOrDefault(TrashExtra.LC_THUMB_PATH, (Object) null);
                this.mRelativePath = (String) hashMap.getOrDefault(TrashExtra.RELATIVE_PATH, (Object) null);
                if (PreferenceFeatures.OneUi6x.SUPPORT_ONE_TRASH) {
                    if (TextUtils.isEmpty(this.mCloudData.thumbPath)) {
                        this.mCloudData.thumbPath = (String) hashMap.getOrDefault(TrashExtra.CLOUD_THUMB_PATH, (Object) null);
                    }
                    this.mGroupMediaId = toLong(hashMap.getOrDefault(TrashExtra.GROUP_MEDIA_ID, 0), 0L).longValue();
                    this.mBestImage = toInteger(hashMap.getOrDefault(TrashExtra.BEST_IMAGE, 0), 0).intValue();
                }
                if (SdkConfig.atLeast(SdkConfig.GED.R)) {
                    this.mOriginFileHash = (String) hashMap.getOrDefault(TrashExtra.ORIGINAL_FILE_HASH, (Object) null);
                }
                if (isDirectorsViewItem(this.mSefFileType)) {
                    this.mGroupMediaId = toLong(hashMap.getOrDefault(TrashExtra.GROUP_MEDIA_ID, 0), 0L).longValue();
                }
                if (Features.isEnabled(Features.SUPPORT_PPP_MENU)) {
                    this.mFileId = toLong(hashMap.getOrDefault(TrashExtra.ID, 0), 0L).longValue();
                } else {
                    this.mFileId = 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Boolean toBoolean(Object obj, boolean z) {
        boolean z3;
        if (obj != null) {
            if (obj instanceof Boolean) {
                return (Boolean) obj;
            }
            if (obj instanceof Double) {
                if (((Double) obj).doubleValue() == MapUtil.INVALID_LOCATION) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                return Boolean.valueOf(z3);
            }
        }
        return Boolean.valueOf(z);
    }

    private Integer toInteger(Object obj, Integer num) {
        if (obj != null) {
            try {
                return Integer.valueOf(((Number) obj).intValue());
            } catch (ClassCastException unused) {
                String str = this.TAG;
                Log.e(str, "getExtraInteger [" + obj + "] failed");
            }
        }
        return num;
    }

    private Long toLong(Object obj, Long l) {
        if (obj != null) {
            try {
                return Long.valueOf(((Number) obj).longValue());
            } catch (ClassCastException unused) {
                String str = this.TAG;
                Log.e(str, "getExtraLong [" + obj + "] failed");
            }
        }
        return l;
    }

    public int getBucketID() {
        return 0;
    }

    public long getCloudTimestamp() {
        return System.currentTimeMillis();
    }

    public long getFileId() {
        return this.mFileId;
    }

    public long getMediaId() {
        return 0;
    }

    public Media getMediaResult() {
        return this.mMedia;
    }

    public String getNewPath() {
        return this.mNewPath;
    }

    public String getOwnerPackage() {
        return this.mOwnerPackage;
    }

    public String getRestoreExtraForLocalUpdate() {
        JSONObject jSONObject = new JSONObject();
        try {
            DetailsData of2 = DetailsData.of(this);
            jSONObject.put("__is360Video", this.m360Video).put("__isFavourite", this.mIsFavorite).put("__cloudOriginalSize", -1).put("__cloudRevision", -1).put("__fileDuration", this.mFileDuration).put("__recordingMode", this.mRecordingMode).put("__sefFileSubType", this.mSefFileSubType).put("__sefFileType", this.mSefFileType).put("__sefFileTypes", this.mSefFileTypes).put("__size", this.mFileSize).put("__group_type", this.mGroupType).put("__dateTaken", this.mDateTaken).put("__latitude", this.mLatitude).put("__longitude", this.mLongitude).put("__capturedAPP", of2.capturedApp).put("__capturedURL", of2.capturedUrl).put("__hash", (Object) null).put("__cloudServerPath", (Object) null).put("__mimeType", this.mMimeType).put("__resolution", this.mResolution).put("__recordingType", this.mRecordingType).put("__isHdr10Video", this.mIsHdr10Video).put("__lcThumbPath", (Object) null).put("__relativePath", this.mRelativePath);
            if (SdkConfig.atLeast(SdkConfig.GED.R)) {
                jSONObject.put("__origin_file_hash", this.mOriginFileHash);
            }
            if (PreferenceFeatures.OneUi6x.SUPPORT_ONE_TRASH) {
                this.mBurstShotId = jSONObject.optLong("__burstGroupID");
                this.mBestImage = jSONObject.optInt("__bestImage");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    public long getTimeStamp() {
        return 0;
    }

    public long getTrashMediaId() {
        return this.mTrashMediaId;
    }

    public boolean isBurstShot() {
        return false;
    }

    public boolean isPrimaryItem() {
        return this.mPrimaryItem;
    }

    public boolean isSimilarShot() {
        return false;
    }

    public boolean isSingleTakenShot() {
        return false;
    }

    public void setMediaResult(Media media) {
        this.mMedia = media;
    }

    public void setNewPath(String str) {
        this.mNewPath = str;
    }

    public void setStorageType(StorageType storageType) {
        this.mStorageType = storageType;
    }

    public String tag() {
        return "TrashRestoreData";
    }

    public String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder("TrashRestoreData{");
        if (this.mMediaType == MediaType.Video) {
            str = "v";
        } else {
            str = "i";
        }
        sb2.append(str);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(getVolumeName());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mStorageType);
        sb2.append(",G(");
        sb2.append(this.mGroupType);
        sb2.append(NumericEnum.SEP);
        sb2.append(this.mGroupMediaId);
        sb2.append("),");
        sb2.append(Logger.getEncodedString(TrashData.getOriginalPath(this)));
        sb2.append(",C(");
        sb2.append(getCloudServerId());
        sb2.append(NumericEnum.SEP);
        sb2.append(Logger.getEncodedString(getCloudThumbPath()));
        sb2.append(NumericEnum.SEP);
        sb2.append(Logger.getEncodedString(getCloudServerPath()));
        sb2.append(")}");
        return sb2.toString();
    }

    public TrashRestoreItem(FileItemInterface fileItemInterface) {
        this.mDateModified = fileItemInterface.getDateModified();
        this.mPath = fileItemInterface.getPath();
        this.mTitle = fileItemInterface.getTitle();
        this.mWidth = fileItemInterface.getWidth();
        this.mHeight = fileItemInterface.getHeight();
        this.mOrientation = fileItemInterface.getOrientation();
        this.mMediaType = fileItemInterface.getMediaType();
        this.mStorageType = fileItemInterface.getStorageType();
        this.mGroupMediaId = fileItemInterface.getGroupMediaId();
        this.mBestImage = fileItemInterface.getBestImage();
        this.mOrientationTag = fileItemInterface.getOrientationTag();
        String originalPath = TrashData.getOriginalPath(fileItemInterface);
        if (originalPath != null) {
            this.mTag.put(ExtrasID.ORIGIN_PATH.name(), originalPath);
        }
        TrashData.of(this).originalTitle = TrashData.of(fileItemInterface).originalTitle;
        TrashData.of(this).restoreData = TrashData.of(fileItemInterface).restoreData;
        this.mVolumeName = fileItemInterface.getVolumeName();
        this.mCloudData.serverId = fileItemInterface.getCloudServerId();
        this.mCloudData.thumbPath = fileItemInterface.getCloudThumbPath();
        this.mCloudData.originalBinaryHash = fileItemInterface.getCloudOriginalBinaryHash();
        this.mCloudData.originalBinarySize = fileItemInterface.getCloudOriginalBinarySize();
        this.mPrimaryItem = !FileUtils.hasSdcardVolume() || FileUtils.isInExternalDir(TrashData.getOriginalPath(this));
        if (PreferenceFeatures.OneUi6x.SUPPORT_ONE_TRASH) {
            this.mOwnerPackage = fileItemInterface.getOwnerPackage();
            this.mTrashMediaId = TrashData.of(fileItemInterface).mediaId;
            this.mFileDuration = fileItemInterface.getFileDuration();
        }
        extractExtra();
        if (!TextUtils.isEmpty(this.mOriginFileHash)) {
            this.mOriginFileHash = getDecodedOriginFileHash(this.mOriginFileHash);
        } else if (SdkConfig.atLeast(SdkConfig.GED.V)) {
            this.mOriginFileHash = fileItemInterface.getOriginalFileHash();
        }
        handleInvalidDataMatch(TrashData.of(this).restoreData, fileItemInterface.getCloudThumbPath());
    }
}
