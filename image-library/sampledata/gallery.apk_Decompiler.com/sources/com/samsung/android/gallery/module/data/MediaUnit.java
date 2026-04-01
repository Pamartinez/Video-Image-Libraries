package com.samsung.android.gallery.module.data;

import android.graphics.RectF;
import android.text.TextUtils;
import android.util.Size;
import c0.C0086a;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.dbtype.MimeType;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.database.dbtype.XmpType;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.thumbnail.type.ThumbnailInterface;
import com.samsung.android.gallery.support.shotmode.ShotMode;
import com.samsung.android.gallery.support.shotmode.ShotModeList;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.scs.base.utils.Log;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
abstract class MediaUnit implements ThumbnailInterface {
    protected static final RectF NULL_RECT = new RectF(0.0f, 0.0f, -1.0f, -1.0f);
    public String camModel;
    boolean m360Video;
    int mAlbumID;
    int mBestImage;
    int mBucketID;
    final CloudData mCloudData = new CloudData();
    protected int mComplexHashcode;
    int mCount;
    protected RectF mCropRect;
    ArrayList<RectF> mCropRectRatioList;
    RectF mCustomCropRect;
    String mDataStamp;
    long mDateAdded;
    long mDateLocal;
    long mDateModified;
    String mDateRaw;
    long mDateTaken;
    String mDisplayName;
    RectF mFaceRectRatio;
    boolean mFavorite;
    int mFileDuration;
    protected int mFileHashCode;
    long mFileID = -1;
    long mFileSize;
    long mGroupMediaId;
    String mGroupMode;
    int mGroupType = 0;
    int mHeight;
    int mHeightInDB;
    String mHttpUri;
    boolean mIsAlbumHide;
    boolean mIsBroken;
    boolean mIsDrm;
    private boolean mIsPanoramaRatio;
    private boolean mIsPanoramaRatioSet;
    Boolean mIsSamsungDng;
    double mLatitude;
    String mLatitudeList;
    String mLocation;
    double mLongitude;
    String mLongitudeList;
    String mMainCategory;
    long mMediaID = -1;
    MediaType mMediaType;
    String mMimeType;
    MimeType mMimeTypeEnum;
    int mOrientation;
    int mOrientationTag;
    String mOriginalFileHash;
    String mOwnerPackage;
    String mPath;
    int mRecordingMode;
    int mRecordingType;
    String mRelativePath;
    String mResolution;
    String mSamsungDngVersion;
    boolean mScenesOrCreature;
    int mSefFileSubType;
    int mSefFileType;
    String mSefFileTypes;
    /* access modifiers changed from: package-private */
    public String mShotMode;
    RectF mSmartCropRectRatio;
    Size mSourceSize;
    String mSpanList;
    StorageType mStorageType;
    String mSubCategory;
    String mSubGroupCategory;
    protected final ConcurrentHashMap<String, Object> mTag = new ConcurrentHashMap<>();
    int mTagType;
    String mThumbnailCacheKey;
    long mTimeStamp;
    String mTitle;
    int mVideoColorFormat;
    long mVideoHighlightEndTime;
    long mVideoHighlightStartTime;
    int mVideoMetadataOrientation = -1;
    protected long mVideoThumbStartTime;
    String mVolumeName;
    int mWidth;
    int mWidthInDB;
    String mWidthList;
    XmpType mXmpType;

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setSefFileType$0(ShotMode shotMode) {
        this.mShotMode = shotMode.type;
    }

    public String buildThumbCacheKey() {
        long j2;
        if (this.mStorageType == StorageType.UriItem) {
            return this.mPath + "/" + this.mFileSize + "/" + this.mDateModified;
        } else if (this.mVideoThumbStartTime > 0) {
            return "" + this.mFileID + '/' + this.mPath + '/' + this.mVideoThumbStartTime;
        } else {
            if (isCloudOnly()) {
                j2 = getCloudOriginalSize();
            } else {
                j2 = this.mFileSize;
            }
            if (this.mScenesOrCreature) {
                return "" + this.mFileID + '/' + this.mPath + '/' + this.mMainCategory + '/' + this.mSubCategory + '/' + j2 + '/' + this.mOrientation + '/' + this.mFaceRectRatio + '/' + getTag("is_rectangle_thumb");
            } else if (!PocFeatures.DEBUG_FACE_RECT || !MediaItemUtil.isCreatureCandidate(this.mSubCategory)) {
                return "" + this.mFileID + '/' + this.mPath + '/' + j2 + '/' + this.mOrientation;
            } else {
                return "" + this.mFileID + '/' + this.mPath + '/' + this.mSubCategory + '/' + j2 + '/' + this.mOrientation;
            }
        }
    }

    public void cloneBasicInfo(MediaItem mediaItem) {
        if (mediaItem != null) {
            this.mFileID = mediaItem.mFileID;
            this.mMediaID = mediaItem.mMediaID;
            this.mWidth = mediaItem.mWidth;
            this.mHeight = mediaItem.mHeight;
            this.mFileSize = mediaItem.mFileSize;
            this.mOrientation = mediaItem.mOrientation;
            this.mOrientationTag = mediaItem.mOrientationTag;
            this.mDateModified = mediaItem.mDateModified;
            this.mDateTaken = mediaItem.mDateTaken;
            this.mStorageType = mediaItem.mStorageType;
            this.mMediaType = mediaItem.mMediaType;
            this.mMimeType = mediaItem.mMimeType;
            this.mMimeTypeEnum = mediaItem.mMimeTypeEnum;
            this.mPath = mediaItem.mPath;
            this.mCount = mediaItem.mCount;
            this.mTitle = mediaItem.mTitle;
            this.mGroupType = mediaItem.mGroupType;
            this.mSefFileType = mediaItem.mSefFileType;
            this.mSefFileSubType = mediaItem.mSefFileSubType;
            this.camModel = mediaItem.camModel;
            this.mThumbnailCacheKey = null;
        }
    }

    public void cloneExtraInfo(MediaItem mediaItem) {
        if (mediaItem != null) {
            this.mCropRect = mediaItem.getCropRectRatio();
            String str = mediaItem.mMainCategory;
            this.mMainCategory = str;
            this.mSubCategory = mediaItem.mSubCategory;
            this.mScenesOrCreature = isScenesOrCreature(str);
        }
    }

    public String dump() {
        String str;
        String str2 = "MediaItem{mFileID=" + this.mFileID + ", mMediaID=" + this.mMediaID + ", mMediaType=" + this.mMediaType + ", mMimeType='" + this.mMimeType + "', mDateTaken=" + this.mDateTaken + ", mOrientation=" + this.mOrientation + ", mOrientationTag=" + this.mOrientationTag + ", mWidth=" + this.mWidth + ", mHeight=" + this.mHeight + ", hdr10Video=" + isHdr10Video() + ", mFileSize=" + this.mFileSize + ", mCount=" + this.mCount + ", mAlbumID=" + this.mAlbumID + ", mBucketID=" + this.mBucketID + ", mIsAlbumHide=" + this.mIsAlbumHide + ", mStorageType=" + this.mStorageType + ", mGroupMediaId=" + this.mGroupMediaId + ", mGroupType=" + this.mGroupType + ", mBestImage=" + this.mBestImage + ", mResolution='" + this.mResolution + "', mShotMode=" + this.mShotMode + ", mGroupMode=" + this.mGroupMode + ", mFavorite=" + this.mFavorite + ", mIsDrm=" + this.mIsDrm + ", mIsBroken=" + this.mIsBroken + ", mFileDuration=" + this.mFileDuration + ", mDateModified=" + this.mDateModified + ", mDateAdded=" + this.mDateAdded + ", mRecordingMode=" + this.mRecordingMode + ", mRecordingType=" + this.mRecordingType + ", mXmpType=" + this.mXmpType + ", mSefFileType=" + this.mSefFileType + ", mSefFileSubType=" + this.mSefFileSubType + ", mSefFileTypes=" + this.mSefFileTypes + ", mVideoThumbStartTime=" + this.mVideoThumbStartTime + ", mSmartCropRect=" + getSmartCropRectRatio() + ", mCropRect=" + this.mCropRect + ", mCropRectList=" + this.mCropRectRatioList;
        String str3 = ", mTitle='" + this.mTitle + "', mDisplayName='" + this.mDisplayName + "', mCloudServerPath='" + getCloudServerPath() + "', mPath='" + this.mPath + "', mMainCategory='" + this.mMainCategory + "', mSubCategory='" + this.mSubCategory + "', mTagType=" + this.mTagType;
        if (Logger.isAllowPrivateLog()) {
            str = C0212a.A(str2, str3);
        } else {
            StringBuilder s = C0212a.s(str2);
            s.append(Logger.getEncodedString(str3));
            str = s.toString();
        }
        return C0212a.A(str, "}");
    }

    public final boolean equalsBasic(MediaItem mediaItem) {
        if (this.mFileID == mediaItem.mFileID && this.mWidth == mediaItem.mWidth && this.mHeight == mediaItem.mHeight && this.mFileSize == mediaItem.mFileSize && this.mOrientation == mediaItem.mOrientation && this.mDateModified == mediaItem.mDateModified && this.mDateTaken == mediaItem.mDateTaken && this.mStorageType == mediaItem.mStorageType && this.mCount == mediaItem.mCount && this.mSefFileType == mediaItem.mSefFileType && TextUtils.equals(this.mPath, mediaItem.mPath) && TextUtils.equals(this.mTitle, mediaItem.mTitle)) {
            return true;
        }
        return false;
    }

    public String getCamModel() {
        return this.camModel;
    }

    public String getCategory() {
        return this.mMainCategory;
    }

    public CloudData getCloudData() {
        return this.mCloudData;
    }

    public String getCloudData2() {
        return this.mCloudData.data2;
    }

    public String getCloudHash() {
        return this.mCloudData.hash;
    }

    public long getCloudId() {
        return this.mCloudData.id;
    }

    public String getCloudOriginalBinaryHash() {
        return this.mCloudData.originalBinaryHash;
    }

    public long getCloudOriginalBinarySize() {
        return this.mCloudData.originalBinarySize;
    }

    public long getCloudOriginalSize() {
        return this.mCloudData.originalSize;
    }

    public int getCloudRevision() {
        return this.mCloudData.revision;
    }

    public String getCloudServerId() {
        return this.mCloudData.serverId;
    }

    public String getCloudServerPath() {
        return this.mCloudData.serverPath;
    }

    public String getCloudThumbPath() {
        return this.mCloudData.thumbPath;
    }

    public int getComplexHashCode() {
        if (this.mComplexHashcode == 0) {
            this.mComplexHashcode = (getThumbCacheKey() + ':' + this.mDateModified + ':' + this.mFileSize + ':' + this.mOrientation + ':' + this.mOrientationTag).hashCode();
        }
        return this.mComplexHashcode;
    }

    public RectF getCropRect() {
        if (isCustomCover()) {
            return this.mCustomCropRect;
        }
        return getSmartCropRectRatio();
    }

    public ArrayList<RectF> getCropRectRatioList() {
        return this.mCropRectRatioList;
    }

    public String getDataPath() {
        return this.mPath;
    }

    public String getDataStamp() {
        return this.mDataStamp;
    }

    public long getDateAdded() {
        return this.mDateAdded;
    }

    public long getDateLocal() {
        return this.mDateLocal;
    }

    public long getDateModified() {
        return this.mDateModified;
    }

    public String getDateRaw() {
        return this.mDateRaw;
    }

    public long getDateTaken() {
        return this.mDateTaken;
    }

    public String getDisplayName() {
        String str = this.mDisplayName;
        if (str != null) {
            return str;
        }
        return FileUtils.getNameFromPath(getPath());
    }

    public RectF getFaceRect() {
        return this.mFaceRectRatio;
    }

    public int getFileDuration() {
        return this.mFileDuration;
    }

    public int getFileHashCode() {
        if (this.mFileHashCode == 0) {
            this.mFileHashCode = ("" + this.mFileID + ':' + this.mPath + ':' + this.mDateModified + ':' + this.mFileSize + ':' + (this.mWidthInDB * this.mHeightInDB) + ':' + this.mOrientation + ':' + this.mOrientationTag).hashCode();
        }
        return this.mFileHashCode;
    }

    public long getFileId() {
        return this.mFileID;
    }

    public long getFileSize() {
        return this.mFileSize;
    }

    public long getGroupMediaId() {
        return this.mGroupMediaId;
    }

    public String getGroupMode() {
        return this.mGroupMode;
    }

    public int getGroupType() {
        return this.mGroupType;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getHeightInDB() {
        return this.mHeightInDB;
    }

    public double getLatitude() {
        return this.mLatitude;
    }

    public String getLatitudeList() {
        return this.mLatitudeList;
    }

    public String getLocation() {
        return this.mLocation;
    }

    public double getLongitude() {
        return this.mLongitude;
    }

    public String getLongitudeList() {
        return this.mLongitudeList;
    }

    public long getMediaId() {
        return this.mMediaID;
    }

    public MediaType getMediaType() {
        return this.mMediaType;
    }

    public String getMimeType() {
        return this.mMimeType;
    }

    public MimeType getMimeTypeEnum() {
        String str;
        if (this.mMimeTypeEnum == null) {
            String str2 = this.mMimeType;
            if (isCloudOnly()) {
                str = getCloudServerPath();
            } else {
                str = this.mPath;
            }
            this.mMimeTypeEnum = MimeType.getMimeType(str2, str);
        }
        return this.mMimeTypeEnum;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public int getOrientationTag() {
        return this.mOrientationTag;
    }

    public String getOriginalFileHash() {
        return this.mOriginalFileHash;
    }

    public String getOwnerPackage() {
        return this.mOwnerPackage;
    }

    public String getPath() {
        return this.mPath;
    }

    public String getReadableLog() {
        String str;
        String str2;
        String str3;
        String str4;
        StringBuilder sb2 = new StringBuilder("MediaItem{");
        Object obj = this.mMediaType;
        if (obj == MediaType.Video) {
            obj = "v";
        } else if (obj == MediaType.Image) {
            obj = "i";
        }
        sb2.append(obj);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mMediaID);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mFileID);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mAlbumID);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mStorageType);
        sb2.append(" (");
        sb2.append(this.mWidth);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mHeight);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mOrientation);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mOrientationTag);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mFileSize);
        sb2.append(NumericEnum.SEP);
        sb2.append(this.mDateTaken);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mDateModified);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mMimeType);
        sb2.append(NumericEnum.SEP);
        sb2.append(this.mSefFileType);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mSefFileSubType);
        sb2.append(NumericEnum.SEP);
        if (this.mIsBroken) {
            str = "broken";
        } else {
            str = "normal";
        }
        sb2.append(str);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mVideoThumbStartTime);
        sb2.append(") DateRaw=");
        sb2.append(this.mDateRaw);
        sb2.append(",XmpType=");
        sb2.append(this.mXmpType);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (isPanoramic()) {
            str2 = "panoramic";
        } else {
            str2 = "non-panoramic";
        }
        sb2.append(str2);
        sb2.append(",ShotMode=");
        sb2.append(this.mShotMode);
        sb2.append(",GroupMode=");
        sb2.append(this.mGroupMode);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        if (this.mIsDrm) {
            str3 = "drm";
        } else {
            str3 = "non-drm";
        }
        sb2.append(str3);
        sb2.append(":RecordingMode=");
        sb2.append(this.mRecordingMode);
        sb2.append(",RecordingType=");
        sb2.append(this.mRecordingType);
        sb2.append(",Category=");
        sb2.append(this.mMainCategory);
        sb2.append(",SubCategory=");
        sb2.append(this.mSubCategory);
        sb2.append(",TagType=");
        sb2.append(this.mTagType);
        sb2.append(",Count=");
        sb2.append(this.mCount);
        String str5 = "";
        if (this.mCount > 0) {
            str4 = C0086a.l(new StringBuilder("[G"), this.mGroupType, "]");
        } else {
            str4 = str5;
        }
        sb2.append(str4);
        sb2.append("}\n\nSmartCropRect=");
        sb2.append(getSmartCropRectRatio());
        sb2.append("\nCropRect=");
        sb2.append(this.mCropRect);
        sb2.append("\nCropRectList=");
        sb2.append(this.mCropRectRatioList);
        sb2.append("\n\nTitle=");
        sb2.append(this.mTitle);
        sb2.append("\nDisplayName=");
        sb2.append(this.mDisplayName);
        sb2.append((String) Optional.ofNullable(getCloudServerPath()).map(new j(0)).orElse(str5));
        if (this.mSefFileTypes != null) {
            str5 = "\nSefFileTypes=" + this.mSefFileTypes;
        }
        sb2.append(str5);
        sb2.append("\nCacheKey=");
        sb2.append(this.mThumbnailCacheKey);
        return sb2.toString();
    }

    public int getRecordingMode() {
        return this.mRecordingMode;
    }

    public int getRecordingType() {
        return this.mRecordingType;
    }

    public String getRelativePath() {
        return this.mRelativePath;
    }

    public String getResolution() {
        return this.mResolution;
    }

    public int getSefFileSubType() {
        return this.mSefFileSubType;
    }

    public int getSefFileType() {
        return this.mSefFileType;
    }

    public String getSefFileTypes() {
        return this.mSefFileTypes;
    }

    public String getShotMode() {
        return this.mShotMode;
    }

    public int getSimpleHashCode() {
        return getThumbCacheKey().hashCode();
    }

    public final RectF getSmartCropRectRatio() {
        if (isVideo()) {
            return NULL_RECT;
        }
        return this.mSmartCropRectRatio;
    }

    public StorageType getStorageType() {
        return this.mStorageType;
    }

    public String getSubCategory() {
        return this.mSubCategory;
    }

    public String getThumbCacheKey() {
        String str = this.mThumbnailCacheKey;
        if (str != null) {
            return str;
        }
        String buildThumbCacheKey = buildThumbCacheKey();
        this.mThumbnailCacheKey = buildThumbCacheKey;
        return buildThumbCacheKey;
    }

    public long getTimeStamp() {
        return this.mTimeStamp;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public int getVideoColorFormat() {
        return this.mVideoColorFormat;
    }

    public String getVolumeName() {
        return this.mVolumeName;
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getWidthInDB() {
        return this.mWidthInDB;
    }

    public XmpType getXmpType() {
        return this.mXmpType;
    }

    public boolean hasSefFileTypes(int i2) {
        String str = this.mSefFileTypes;
        if (str == null || !str.contains(String.valueOf(i2))) {
            return false;
        }
        return true;
    }

    public boolean is360Video() {
        return this.m360Video;
    }

    public boolean isBroken() {
        return this.mIsBroken;
    }

    public boolean isCamModelSamsung() {
        String str = this.camModel;
        if (str == null) {
            return false;
        }
        if (str.startsWith("samsung") || this.camModel.startsWith("Samsung")) {
            return true;
        }
        return false;
    }

    public boolean isCommonPostProcessing() {
        int i2 = this.mSefFileType;
        if (i2 == 2928 || i2 == 2784) {
            return true;
        }
        return false;
    }

    public boolean isDrm() {
        if (!this.mIsDrm || isPostProcessing()) {
            return false;
        }
        return true;
    }

    public boolean isEmpty() {
        if (this.mFileID == -1) {
            return true;
        }
        return false;
    }

    public boolean isHdr10Video() {
        if (this.mVideoColorFormat == 1001) {
            return true;
        }
        return false;
    }

    public boolean isHlgVideo() {
        if (this.mVideoColorFormat == 1000) {
            return true;
        }
        return false;
    }

    public boolean isImage() {
        if (MediaType.Image == this.mMediaType) {
            return true;
        }
        return false;
    }

    public boolean isPanoramic() {
        if (this.mIsPanoramaRatioSet) {
            return this.mIsPanoramaRatio;
        }
        boolean isPanoramic = BitmapUtils.isPanoramic(this.mWidth, this.mHeight);
        this.mIsPanoramaRatio = isPanoramic;
        this.mIsPanoramaRatioSet = true;
        return isPanoramic;
    }

    public boolean isPostProcessing() {
        int i2 = this.mSefFileType;
        if (i2 == 2928 || i2 == 2784 || i2 == 2947) {
            return true;
        }
        return false;
    }

    public boolean isPostProcessingDraftProcessed() {
        if (this.mSefFileType != 2928 || !hasSefFileTypes(2929)) {
            return false;
        }
        return true;
    }

    public boolean isScenesOrCreature() {
        return this.mScenesOrCreature;
    }

    public boolean isSingleTakenPostProcessing() {
        if (this.mSefFileType == 2947) {
            return true;
        }
        return false;
    }

    public boolean isVideo() {
        if (MediaType.Video == this.mMediaType) {
            return true;
        }
        return false;
    }

    public void setCropRect(RectF rectF) {
        this.mSmartCropRectRatio = rectF;
    }

    public void setCropRectRatioList(ArrayList<RectF> arrayList) {
        this.mCropRectRatioList = arrayList;
    }

    public void setDisplayName(String str) {
        this.mDisplayName = str;
    }

    public void setFileDuration(int i2) {
        this.mFileDuration = i2;
    }

    public void setFileId(long j2) {
        this.mFileID = j2;
    }

    public void setFileSize(long j2) {
        this.mFileSize = j2;
        this.mThumbnailCacheKey = null;
        this.mComplexHashcode = 0;
    }

    public void setFileSizeOnly(long j2) {
        this.mFileSize = j2;
    }

    public void setGroupMediaId(long j2) {
        this.mGroupMediaId = j2;
    }

    public void setLatLong(double[] dArr) {
        this.mLatitude = dArr[0];
        this.mLongitude = dArr[1];
    }

    public void setMediaId(long j2) {
        this.mMediaID = j2;
    }

    public void setMediaType(MediaType mediaType) {
        this.mMediaType = mediaType;
    }

    public void setMimeType(String str) {
        this.mMimeType = str;
        this.mMimeTypeEnum = null;
    }

    public void setOrientation(int i2) {
        this.mOrientation = i2;
        this.mThumbnailCacheKey = null;
    }

    public void setOrientationTag(int i2) {
        this.mOrientationTag = i2;
    }

    public void setRecordingMode(int i2) {
        this.mRecordingMode = i2;
    }

    public void setSefFileType(int i2, int i7) {
        this.mSefFileType = i2;
        this.mSefFileSubType = i7;
        if (i2 > 0) {
            Optional.ofNullable(ShotModeList.getInstance().getBySefValue(i2)).ifPresent(new f(1, this));
        }
    }

    public void setSize(int i2, int i7) {
        this.mWidth = i2;
        this.mHeight = i7;
        this.mSourceSize = null;
    }

    public void setStorageType(StorageType storageType) {
        this.mStorageType = storageType;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public void setVideoColorFormat(int i2) {
        this.mVideoColorFormat = i2;
    }

    public void setVideoMetadataOrientation(int i2) {
        this.mVideoMetadataOrientation = i2;
    }

    public void setVolumeName(String str) {
        this.mVolumeName = str;
    }

    public void setXmpType(XmpType xmpType) {
        this.mXmpType = xmpType;
    }

    public Map<String, Object> tags() {
        return this.mTag;
    }

    public String toString() {
        String str;
        String str2;
        String str3;
        String str4;
        StringBuilder sb2 = new StringBuilder("MediaItem{");
        String str5 = "";
        if (this.mIsBroken) {
            str = "b";
        } else {
            str = str5;
        }
        sb2.append(str);
        if (this.mIsDrm) {
            str2 = "d";
        } else {
            str2 = str5;
        }
        sb2.append(str2);
        MediaType mediaType = this.mMediaType;
        MediaType mediaType2 = MediaType.Video;
        if (mediaType != mediaType2) {
            str3 = "i";
        } else if (isHdrVideo()) {
            str3 = "v+";
        } else {
            str3 = "v";
        }
        sb2.append(str3);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mMediaID);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mFileID);
        sb2.append(NumericEnum.SEP);
        sb2.append(this.mAlbumID);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mStorageType);
        sb2.append("(");
        sb2.append(this.mWidth);
        sb2.append("x");
        sb2.append(this.mHeight);
        sb2.append(Log.TAG_SEPARATOR);
        sb2.append(this.mOrientation);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mOrientationTag);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mFileSize);
        sb2.append(NumericEnum.SEP);
        sb2.append(this.mDateTaken);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mDateModified);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mDateLocal);
        sb2.append(NumericEnum.SEP);
        sb2.append(this.mMimeType);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mSefFileType);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(this.mSefFileSubType);
        String str6 = this.mSefFileTypes;
        if (str6 == null) {
            str6 = str5;
        }
        sb2.append(str6);
        if (this.mGroupType > 0) {
            str4 = ":G" + this.mGroupType + "=" + this.mGroupMediaId + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mBestImage + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mCount;
        } else {
            str4 = str5;
        }
        sb2.append(str4);
        if (this.mMediaType == mediaType2) {
            str5 = ":R" + this.mRecordingMode + GlobalPostProcInternalPPInterface.SPLIT_REGEX + this.mFileDuration;
        }
        return C0212a.p(sb2, str5, ")}");
    }
}
