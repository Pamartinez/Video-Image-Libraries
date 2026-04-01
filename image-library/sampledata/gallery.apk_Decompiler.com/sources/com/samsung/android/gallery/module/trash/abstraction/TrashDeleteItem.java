package com.samsung.android.gallery.module.trash.abstraction;

import android.database.Cursor;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.data.DetailsData;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.module.data.TrashData;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.sgpl.trash.TrashExtra;
import com.samsung.android.sum.core.types.NumericEnum;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrashDeleteItem extends TrashBaseItem {
    static volatile boolean BURSTSHOT_FOR_DEFAULT_GROUP_TYPE = (!SdkConfig.atLeast(SdkConfig.GED.Q));
    private final String mAudioCodecInfo;
    private final int mBucketId;
    private final double mCaptureFramerate;
    private final long mMediaId;
    private final long mTimeStamp;
    private final String mVideoCodecInfo;

    public TrashDeleteItem(Cursor cursor, long j2) {
        this.mFileId = getLong(cursor, "__absID");
        this.mMediaId = getLong(cursor, "__fileMediaId");
        String string = getString(cursor, "", "__absPath");
        this.mPath = string;
        if (string != null && getInt(cursor, 0, "__sefFileType") == 2928) {
            String string2 = getString(cursor, "", "__draftPath");
            if (Features.isEnabled(Features.SUPPORT_PPP_DRAFT)) {
                Log.d(this.TAG, "PPP use draftPath : ");
                this.mPath = string2;
            }
        }
        this.mRelativePath = getString(cursor, (String) null, "__relativePath");
        this.mDateTaken = getLong(cursor, "__dateTaken");
        this.mDateModified = getLong(cursor, "__dateModified");
        this.mLatitude = getDouble(cursor, "__latitude").doubleValue();
        this.mLongitude = getDouble(cursor, "__longitude").doubleValue();
        this.mTitle = getString(cursor, (String) null, "__Title");
        this.mResolution = getString(cursor, (String) null, "__resolution");
        this.mMimeType = getString(cursor, (String) null, "__mimeType");
        this.mFileDuration = getInt(cursor, 0, "__fileDuration");
        this.mMediaType = MediaType.valueOf(getInt(cursor, 1, "__mediaType"));
        this.mOrientation = getInt(cursor, 0, "__orientation");
        this.mIsDrm = getInt(cursor, 0, "__isDrm") == 1;
        this.mIsFavorite = getInt(cursor, 0, "__isFavourite") == 1;
        this.m360Video = getInt(cursor, 0, "__is360Video") == 1;
        this.mIsHdr10Video = getInt(cursor, 0, "__isHdr10Video") == 1;
        this.mRecordingMode = getInt(cursor, 0, "__recordingMode");
        this.mRecordingType = getInt(cursor, 0, "__recordingType");
        this.mSefFileType = getInt(cursor, -1, "__sefFileType");
        this.mSefFileTypes = getString(cursor, (String) null, "__sefFileTypes");
        this.mSefFileSubType = getInt(cursor, -1, "__sefFileSubType");
        this.mFileSize = getLong(cursor, "__size");
        this.mGroupMediaId = getLong(cursor, "__GroupMediaID");
        this.mBestImage = getInt(cursor, 0, "__GroupMediaBest");
        this.mGroupType = getInt(cursor, 0, "__group_type");
        this.mWidth = getInt(cursor, 0, "__width");
        this.mHeight = getInt(cursor, 0, "__height");
        DetailsData.of(this).capturedApp = getString(cursor, (String) null, "__capturedAPP");
        DetailsData.of(this).capturedUrl = getString(cursor, (String) null, "__capturedURL");
        this.mStorageType = StorageType.valueOf(getInt(cursor, 1, "__storageType"));
        this.mOrientationTag = getInt(cursor, 0, "__orientationTag");
        this.mCloudData.serverId = getString(cursor, (String) null, "__cloudServerId");
        this.mCloudData.serverPath = getString(cursor, (String) null, "__cloudServerPath");
        this.mCloudData.thumbPath = getString(cursor, (String) null, "__cloudTP");
        this.mCloudData.revision = getInt(cursor, -1, "__cloudRevision");
        this.mCloudData.hash = getString(cursor, (String) null, "__hash");
        this.mCloudData.originalSize = getLong(cursor, "__cloudOriginalSize");
        this.mCloudData.originalBinaryHash = getString(cursor, (String) null, "__cloudOriginalBinaryHash");
        this.mCloudData.originalBinarySize = getLong(cursor, "__cloudOriginalBinarySize");
        TrashData.of(this).deleteTime = j2 <= 0 ? System.currentTimeMillis() : j2;
        this.mBucketId = getBucketIdFromPath();
        this.mGroupMode = getShotModeFromIdAndType();
        this.mTimeStamp = getLong(cursor, "__timestamp");
        this.mVideoCodecInfo = getString(cursor, (String) null, "__videoCodecInfo");
        this.mAudioCodecInfo = getString(cursor, (String) null, "__audioCodecInfo");
        this.mCaptureFramerate = getDouble(cursor, "__capture_framerate").doubleValue();
    }

    private boolean getBackupDrm() {
        if (isSingleTakenPPP() || !this.mIsDrm) {
            return false;
        }
        return true;
    }

    private int getBackupGroupType() {
        if (isSimilarShot()) {
            return 0;
        }
        return this.mGroupType;
    }

    private int getBackupSefFileType() {
        if (isSingleTakenPPP()) {
            return -1;
        }
        return this.mSefFileType;
    }

    private int getBucketIdFromPath() {
        if (this.mStorageType == StorageType.Cloud) {
            return FileUtils.getBucketId(FileUtils.getDirectoryFromPath(getCloudServerPath(), false));
        }
        return FileUtils.getBucketId(FileUtils.getDirectoryFromPath(this.mPath, false));
    }

    private long getGroupMediaId(FileItemInterface fileItemInterface) {
        if (isDirectorsViewItem(fileItemInterface.getSefFileType())) {
            return fileItemInterface.getDirectorsViewGroupId();
        }
        return fileItemInterface.getGroupMediaId();
    }

    private String getShotModeFromIdAndType() {
        int i2 = this.mGroupType;
        if (i2 > 0) {
            if (i2 == 1) {
                return "burst_shot";
            }
            if (i2 == 2) {
                return "Similar photo";
            }
            if (i2 != 3) {
                return null;
            }
            return "Single Taken";
        } else if (this.mGroupMediaId <= 0 || !BURSTSHOT_FOR_DEFAULT_GROUP_TYPE) {
            return null;
        } else {
            return "burst_shot";
        }
    }

    private boolean isSingleTakenPPP() {
        if (this.mSefFileType != 2947 || !this.mIsDrm) {
            return false;
        }
        return true;
    }

    public int getBucketID() {
        return this.mBucketId;
    }

    public long getCloudTimestamp() {
        return 0;
    }

    public long getFileId() {
        return this.mFileId;
    }

    public long getMediaId() {
        return this.mMediaId;
    }

    public long getTimeStamp() {
        return this.mTimeStamp;
    }

    public boolean isGroupItem() {
        if (this.mGroupMode != null) {
            return true;
        }
        return false;
    }

    public void setGroupType(int i2) {
        this.mGroupType = i2;
        this.mGroupMode = getShotModeFromIdAndType();
    }

    public final void setRestoreExtra() {
        try {
            DetailsData of2 = DetailsData.of(this);
            TrashExtra.Builder append = new TrashExtra.Builder().append(TrashExtra.IS_360_VIDEO, Boolean.valueOf(this.m360Video)).append(TrashExtra.IS_DRM, Boolean.valueOf(getBackupDrm())).append(TrashExtra.IS_FAVOURITE, Boolean.valueOf(this.mIsFavorite)).append(TrashExtra.FILE_DURATION, Integer.valueOf(this.mFileDuration)).append(TrashExtra.RECORDING_MODE, Integer.valueOf(this.mRecordingMode)).append(TrashExtra.SEF_FILE_SUB_TYPE, Integer.valueOf(this.mSefFileSubType)).append(TrashExtra.SEF_FILE_TYPE, Integer.valueOf(getBackupSefFileType())).append(TrashExtra.SEF_FILE_TYPES, this.mSefFileTypes).append(TrashExtra.SIZE, Long.valueOf(this.mFileSize)).append(TrashExtra.GROUP_TYPE, Integer.valueOf(getBackupGroupType())).append(TrashExtra.DATE_TAKEN, Long.valueOf(this.mDateTaken)).append(TrashExtra.LATITUDE, Double.valueOf(this.mLatitude)).append(TrashExtra.LONGITUDE, Double.valueOf(this.mLongitude)).append(TrashExtra.CAPTURED_APP, of2.capturedApp).append(TrashExtra.CAPTURED_URL, of2.capturedUrl).append(TrashExtra.MIME_TYPE, this.mMimeType).append(TrashExtra.RESOLUTION, this.mResolution).append(TrashExtra.RECORDING_TYPE, Integer.valueOf(this.mRecordingType)).append(TrashExtra.IS_HDR_10_VIDEO, Boolean.valueOf(this.mIsHdr10Video)).append(TrashExtra.CLOUD_ORIGINAL_SIZE, Long.valueOf(getCloudOriginalSize())).append(TrashExtra.CLOUD_REVISION, Integer.valueOf(getCloudRevision())).append(TrashExtra.CLOUD_SERVER_PATH, getCloudServerPath()).append(TrashExtra.HASH, getCloudHash()).append(TrashExtra.LC_THUMB_PATH, this.mLCThumbPath).append(TrashExtra.ID, Long.valueOf(this.mFileId)).append(TrashExtra.RELATIVE_PATH, this.mRelativePath);
            if (SdkConfig.range(SdkConfig.GED.R, SdkConfig.GED.V) && !TextUtils.isEmpty(this.mOriginFileHash)) {
                append.append(TrashExtra.ORIGINAL_FILE_HASH, getEncodedOriginFileHash(this.mOriginFileHash));
            }
            if (isDirectorsViewItem(this.mSefFileType)) {
                append.append(TrashExtra.GROUP_MEDIA_ID, Long.valueOf(getGroupMediaId()));
            }
            if (PreferenceFeatures.OneUi6x.SUPPORT_ONE_TRASH) {
                String str = this.mCloudData.thumbPath;
                if (str != null) {
                    append.append(TrashExtra.CLOUD_THUMB_PATH, str);
                }
                append.append(TrashExtra.BURST_SHOT_GROUP_ID, Long.valueOf(getGroupMediaId())).append(TrashExtra.BEST_IMAGE, Integer.valueOf(getBestImage()));
            }
            if (Features.isEnabled(Features.SUPPORT_NEW_SAMSUNG_CLOUD)) {
                append.append(TrashExtra.VIDEO_CODEC_INFO, this.mVideoCodecInfo).append(TrashExtra.AUDIO_CODEC_INFO, this.mAudioCodecInfo).append(TrashExtra.CAPTURE_FRAMERATE, Double.valueOf(this.mCaptureFramerate));
            }
            TrashData.of(this).restoreData = append.build();
        } catch (Exception e) {
            Log.e((CharSequence) this.TAG, "setRestoreExtra failed", (Throwable) e);
        }
    }

    public String tag() {
        return "TrashDeleteData";
    }

    public String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder("TrashDeleteData{");
        if (this.mMediaType == MediaType.Video) {
            str = "v";
        } else {
            str = "i";
        }
        sb2.append(str);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mMediaId);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mFileId);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(getVolumeName());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mStorageType);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mBucketId);
        sb2.append(",G(");
        sb2.append(this.mGroupMode);
        sb2.append(NumericEnum.SEP);
        sb2.append(this.mGroupType);
        sb2.append(NumericEnum.SEP);
        sb2.append(this.mGroupMediaId);
        sb2.append("),");
        sb2.append(Logger.getEncodedString(this.mPath));
        sb2.append(",C(");
        sb2.append(getCloudServerId());
        sb2.append(NumericEnum.SEP);
        sb2.append(Logger.getEncodedString(getCloudThumbPath()));
        sb2.append(")}");
        return sb2.toString();
    }

    public void updatePpp() {
        if (Features.isEnabled(Features.SUPPORT_PPP_MENU)) {
            this.mPath = MediaItemUtil.getTargetPathFromPPP(this.mPath);
            String replace = this.mTitle.replace("!@#$%^", "");
            this.mTitle = replace;
            this.mTitle = replace.replace("_temp.jpg", ".jpg");
        }
    }

    public TrashDeleteItem(FileItemInterface fileItemInterface, long j2) {
        this.mFileId = fileItemInterface.getFileId();
        this.mMediaId = fileItemInterface.getMediaId();
        this.mPath = fileItemInterface.getPath();
        this.mRelativePath = fileItemInterface.getRelativePath();
        this.mDateTaken = fileItemInterface.getDateTaken();
        this.mDateModified = fileItemInterface.getDateModified();
        this.mLatitude = fileItemInterface.getLatitude();
        this.mLongitude = fileItemInterface.getLongitude();
        this.mTitle = fileItemInterface.getTitle();
        this.mResolution = fileItemInterface.getResolution();
        this.mMimeType = fileItemInterface.getMimeType();
        this.mFileDuration = fileItemInterface.getFileDuration();
        this.mMediaType = fileItemInterface.getMediaType();
        this.mOrientation = fileItemInterface.getOrientation();
        this.mIsDrm = fileItemInterface.isDrm();
        this.mIsFavorite = fileItemInterface.isFavourite();
        this.m360Video = fileItemInterface.is360Video();
        this.mIsHdr10Video = fileItemInterface.isHdr10Video();
        this.mRecordingMode = fileItemInterface.getRecordingMode();
        this.mRecordingType = fileItemInterface.getRecordingType();
        this.mSefFileType = fileItemInterface.getSefFileType();
        this.mSefFileTypes = fileItemInterface.getSefFileTypes();
        this.mSefFileSubType = fileItemInterface.getSefFileSubType();
        this.mFileSize = fileItemInterface.getFileSize();
        this.mGroupMediaId = getGroupMediaId(fileItemInterface);
        this.mBestImage = fileItemInterface.getBestImage();
        this.mGroupType = fileItemInterface.getGroupType();
        this.mWidth = fileItemInterface.getWidth();
        this.mHeight = fileItemInterface.getHeight();
        DetailsData.of(this).capturedApp = DetailsData.of(fileItemInterface).capturedApp;
        DetailsData.of(this).capturedUrl = DetailsData.of(fileItemInterface).capturedUrl;
        this.mStorageType = fileItemInterface.getStorageType();
        this.mOrientationTag = fileItemInterface.getOrientationTag();
        this.mCloudData.serverId = fileItemInterface.getCloudServerId();
        this.mCloudData.serverPath = fileItemInterface.getCloudServerPath();
        this.mCloudData.thumbPath = fileItemInterface.getCloudThumbPath();
        this.mCloudData.revision = fileItemInterface.getCloudRevision();
        this.mCloudData.hash = fileItemInterface.getCloudHash();
        this.mCloudData.originalSize = fileItemInterface.getCloudOriginalSize();
        this.mCloudData.originalBinaryHash = fileItemInterface.getCloudOriginalBinaryHash();
        this.mCloudData.originalBinarySize = fileItemInterface.getCloudOriginalBinarySize();
        long j3 = TrashData.of(fileItemInterface).deleteTime;
        TrashData of2 = TrashData.of(this);
        if (j3 > 0) {
            j2 = j3;
        } else if (j2 <= 0) {
            j2 = System.currentTimeMillis();
        }
        of2.deleteTime = j2;
        this.mBucketId = getBucketIdFromPath();
        this.mGroupMode = getShotModeFromIdAndType();
        this.mTimeStamp = fileItemInterface.getTimeStamp();
        VideoPropData of3 = VideoPropData.of(fileItemInterface);
        this.mVideoCodecInfo = of3.videoCodec;
        this.mAudioCodecInfo = of3.audioCodec;
        this.mCaptureFramerate = of3.videoCaptureFrameRate;
    }
}
