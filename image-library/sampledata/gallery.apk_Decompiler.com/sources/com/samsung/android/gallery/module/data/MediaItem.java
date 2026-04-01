package com.samsung.android.gallery.module.data;

import android.graphics.RectF;
import android.net.Uri;
import android.util.Size;
import androidx.exifinterface.media.ExifInterface;
import com.samsung.android.gallery.database.dal.abstraction.table.DbTable;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.database.dbtype.GroupType;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.dbtype.MimeType;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.database.dbtype.XmpType;
import com.samsung.android.gallery.module.graphics.QuramBitmapFactory;
import com.samsung.android.gallery.module.thumbnail.type.ThumbKind;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.library.sef.SingleTakeType;
import com.samsung.android.gallery.support.shotmode.RecordingMode;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.ExifTag;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.gallery.support.utils.SafeMode;
import com.samsung.android.gallery.support.utils.SuperHdrConfig;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MediaItem extends MediaUnit implements ClusterItem, Cloneable {
    private static final MediaItem[] EMPTY_ARRAY = new MediaItem[0];
    boolean hasSingleFrame;
    String mAlbumCover;
    int mAlbumLevel;
    long mAlbumOrder;
    boolean mAlbumShowInfo;
    AlbumType mAlbumType;
    long mDirectorsViewGroupId;
    ExifTag mExifTag;
    int mFolderID;
    String mFolderName;
    FolderPosition mFolderPosition = FolderPosition.NONE;
    boolean mHasFilterAndTone;
    boolean mHasPortraitsEffect;
    boolean mIsRevitalization;
    boolean mIsStoriesItem;
    Boolean mIsVirtualAlbum;
    private boolean mUidCachedSet = false;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum FolderPosition {
        FIRST,
        MIDDLE,
        LAST,
        ALL,
        NONE
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SefFileTypeKey {
        static final String LIVE_EFFECT_INFO = String.valueOf(3505);
        static final String PHOTO_HDR_INFO = String.valueOf(3282);
        static final String REMASTER_INFO = String.valueOf(SefInfo.REMASTER_INFO.keyCode);
        static final String SINGLE_TAKE_IMAGE_COLLAGE = String.valueOf(SingleTakeType.ImageCollage.value);
    }

    public MediaItem(String str, long j2, long j3, MediaType mediaType, long j8, int i2, int i7, long j10, int i8, int i10) {
        this.mPath = str;
        this.mFileID = j2;
        this.mMediaID = j3;
        this.mMediaType = mediaType;
        this.mDateTaken = j8;
        this.mOrientation = i2;
        this.mOrientationTag = i7;
        this.mFileSize = j10;
        this.mWidth = i8;
        this.mHeight = i10;
        this.mWidthInDB = i8;
        this.mHeightInDB = i10;
    }

    public static MediaItem from(Uri uri) {
        MediaType mediaType;
        MediaItem mediaItem = new MediaItem();
        mediaItem.mPath = uri.toString();
        mediaItem.mStorageType = StorageType.UriItem;
        String from = MimeType.from(uri);
        if (from != null) {
            mediaItem.mMimeType = from;
            if (from.startsWith("video")) {
                mediaType = MediaType.Video;
            } else {
                mediaType = MediaType.Image;
            }
            mediaItem.mMediaType = mediaType;
        }
        return mediaItem;
    }

    private RectF getCropRectRatioInternal() {
        RectF rawCropRectRatio = getRawCropRectRatio();
        if (MediaUnit.NULL_RECT.equals(rawCropRectRatio) || this.mOrientation == 0 || (isCloudOnly() && isVideo())) {
            return rawCropRectRatio;
        }
        return RectUtils.getRotatedRectFRatio(rawCropRectRatio, this.mOrientation);
    }

    private boolean is360RecordingType() {
        int i2 = this.mRecordingType;
        if (i2 == 6 || i2 == 7) {
            return true;
        }
        return false;
    }

    public void clearPending() {
        if (this.mMediaType == MediaType.Image) {
            String str = this.mPath;
            if (str != null && str.contains("/.pending-")) {
                this.mPath = this.mPath.replaceFirst("\\.pending-\\d+-", "");
            }
            this.mIsDrm = false;
        }
    }

    public /* bridge */ /* synthetic */ void cloneBasicInfo(MediaItem mediaItem) {
        super.cloneBasicInfo(mediaItem);
    }

    public /* bridge */ /* synthetic */ void cloneExtraInfo(MediaItem mediaItem) {
        super.cloneExtraInfo(mediaItem);
    }

    public /* bridge */ /* synthetic */ String dump() {
        return super.dump();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MediaItem)) {
            return false;
        }
        MediaItem mediaItem = (MediaItem) obj;
        if (!isFolderItem() || !mediaItem.isFolderItem()) {
            if (isFolderItem() || mediaItem.isFolderItem()) {
                return equalsBasic(mediaItem);
            }
            return MediaItemUtil.equals(this, mediaItem);
        } else if (getAlbumID() != mediaItem.getAlbumID() || !equalsBasic(mediaItem)) {
            return false;
        } else {
            return true;
        }
    }

    public String getAlbumCover() {
        return this.mAlbumCover;
    }

    public long getAlbumDateModified() {
        return this.mDateModified;
    }

    public long getAlbumFileId() {
        return this.mFileID;
    }

    public int getAlbumID() {
        return this.mAlbumID;
    }

    public long getAlbumOrder() {
        return this.mAlbumOrder;
    }

    public AlbumType getAlbumType() {
        AlbumType albumType = this.mAlbumType;
        if (albumType != null) {
            return albumType;
        }
        return AlbumType.None;
    }

    public MediaItem[] getAlbumsInFolder() {
        return EMPTY_ARRAY;
    }

    public int getBestImage() {
        return this.mBestImage;
    }

    public int getBucketID() {
        int i2 = this.mBucketID;
        if (i2 != -1) {
            return i2;
        }
        return this.mAlbumID;
    }

    public /* bridge */ /* synthetic */ String getCamModel() {
        return super.getCamModel();
    }

    public List<MediaItem> getChildAlbums() {
        return Collections.EMPTY_LIST;
    }

    public List<MediaItem> getChildItems() {
        return Collections.EMPTY_LIST;
    }

    public List<MediaItem> getChildList() {
        return Collections.EMPTY_LIST;
    }

    public /* bridge */ /* synthetic */ CloudData getCloudData() {
        return super.getCloudData();
    }

    public /* bridge */ /* synthetic */ String getCloudData2() {
        return super.getCloudData2();
    }

    public /* bridge */ /* synthetic */ String getCloudHash() {
        return super.getCloudHash();
    }

    public /* bridge */ /* synthetic */ long getCloudId() {
        return super.getCloudId();
    }

    public /* bridge */ /* synthetic */ String getCloudOriginalBinaryHash() {
        return super.getCloudOriginalBinaryHash();
    }

    public /* bridge */ /* synthetic */ long getCloudOriginalBinarySize() {
        return super.getCloudOriginalBinarySize();
    }

    public /* bridge */ /* synthetic */ long getCloudOriginalSize() {
        return super.getCloudOriginalSize();
    }

    public /* bridge */ /* synthetic */ int getCloudRevision() {
        return super.getCloudRevision();
    }

    public /* bridge */ /* synthetic */ String getCloudServerId() {
        return super.getCloudServerId();
    }

    public /* bridge */ /* synthetic */ String getCloudServerPath() {
        return super.getCloudServerPath();
    }

    public /* bridge */ /* synthetic */ String getCloudThumbPath() {
        return super.getCloudThumbPath();
    }

    public /* bridge */ /* synthetic */ int getComplexHashCode() {
        return super.getComplexHashCode();
    }

    public int getCount() {
        return this.mCount;
    }

    public /* bridge */ /* synthetic */ RectF getCropRect() {
        return super.getCropRect();
    }

    public RectF getCropRectRatio() {
        if (this.mCropRect == null) {
            this.mCropRect = getCropRectRatioInternal();
        }
        return this.mCropRect;
    }

    public /* bridge */ /* synthetic */ ArrayList getCropRectRatioList() {
        return super.getCropRectRatioList();
    }

    public /* bridge */ /* synthetic */ String getDataStamp() {
        return super.getDataStamp();
    }

    public String getDate() {
        return TimeUtil.getDateString(this.mDateRaw, this.mDateTaken);
    }

    public /* bridge */ /* synthetic */ long getDateAdded() {
        return super.getDateAdded();
    }

    public /* bridge */ /* synthetic */ long getDateLocal() {
        return super.getDateLocal();
    }

    public /* bridge */ /* synthetic */ long getDateModified() {
        return super.getDateModified();
    }

    public /* bridge */ /* synthetic */ String getDateRaw() {
        return super.getDateRaw();
    }

    public /* bridge */ /* synthetic */ long getDateTaken() {
        return super.getDateTaken();
    }

    public long getDirectorsViewGroupId() {
        return this.mDirectorsViewGroupId;
    }

    public String getDiskCacheKey() {
        if (this.mScenesOrCreature) {
            return getThumbCacheKey();
        }
        if (this.mVideoHighlightStartTime > 0 || this.mVideoHighlightEndTime > 0) {
            return getPath() + "/" + this.mVideoHighlightStartTime + "/" + this.mVideoHighlightEndTime;
        }
        if (isVideo() && isLocal()) {
            if (isStories()) {
                return getThumbCacheKey();
            }
            if (PreferenceFeatures.VIDEO_THUMBNAIL_WITH_FIRST_FRAME && !FileUtils.isNonMoviePath(getPath())) {
                return "0" + getPath();
            }
        }
        return getPath();
    }

    public /* bridge */ /* synthetic */ String getDisplayName() {
        return super.getDisplayName();
    }

    public String getDngVersion() {
        String str;
        if (this.mSamsungDngVersion == null) {
            if (isDng()) {
                str = MediaItemUtil.getSamsungDngVersion(this.mPath);
            } else {
                str = "";
            }
            this.mSamsungDngVersion = str;
        }
        return this.mSamsungDngVersion;
    }

    public ExifTag getExifTag() {
        return this.mExifTag;
    }

    public /* bridge */ /* synthetic */ RectF getFaceRect() {
        return super.getFaceRect();
    }

    public /* bridge */ /* synthetic */ int getFileDuration() {
        return super.getFileDuration();
    }

    public /* bridge */ /* synthetic */ int getFileHashCode() {
        return super.getFileHashCode();
    }

    public /* bridge */ /* synthetic */ long getFileId() {
        return super.getFileId();
    }

    public /* bridge */ /* synthetic */ long getFileSize() {
        return super.getFileSize();
    }

    public MediaItem getFirst() {
        return null;
    }

    public int getFolderID() {
        return this.mFolderID;
    }

    public String getFolderName() {
        return this.mFolderName;
    }

    public FolderPosition getFolderPosition() {
        return this.mFolderPosition;
    }

    public /* bridge */ /* synthetic */ long getGroupMediaId() {
        return super.getGroupMediaId();
    }

    public /* bridge */ /* synthetic */ String getGroupMode() {
        return super.getGroupMode();
    }

    public /* bridge */ /* synthetic */ int getGroupType() {
        return super.getGroupType();
    }

    public GroupType getGroupTypeEnum() {
        if (isBurstShot()) {
            return GroupType.BURST;
        }
        if (isSimilarShot()) {
            return GroupType.SIMILAR;
        }
        if (isSingleTakenShot()) {
            return GroupType.SINGLE_TAKEN;
        }
        return null;
    }

    public /* bridge */ /* synthetic */ int getHeight() {
        return super.getHeight();
    }

    public /* bridge */ /* synthetic */ int getHeightInDB() {
        return super.getHeightInDB();
    }

    public String getHttpUri() {
        return this.mHttpUri;
    }

    public int getItemCount() {
        return 0;
    }

    public MediaItem[] getItemsInFolder() {
        return new MediaItem[0];
    }

    public /* bridge */ /* synthetic */ double getLatitude() {
        return super.getLatitude();
    }

    public /* bridge */ /* synthetic */ String getLatitudeList() {
        return super.getLatitudeList();
    }

    public /* bridge */ /* synthetic */ String getLocation() {
        return super.getLocation();
    }

    public /* bridge */ /* synthetic */ double getLongitude() {
        return super.getLongitude();
    }

    public /* bridge */ /* synthetic */ String getLongitudeList() {
        return super.getLongitudeList();
    }

    public String getMdeDownloadedPath() {
        if (!isSharing() || !isMotionPhoto()) {
            return MediaItemMde.getDownloadVideoPath(this, getPath());
        }
        return MediaItemMde.getDownloadMotionPhotoPath(this);
    }

    public /* bridge */ /* synthetic */ long getMediaId() {
        return super.getMediaId();
    }

    public /* bridge */ /* synthetic */ MediaType getMediaType() {
        return super.getMediaType();
    }

    public /* bridge */ /* synthetic */ String getMimeType() {
        return super.getMimeType();
    }

    public /* bridge */ /* synthetic */ MimeType getMimeTypeEnum() {
        return super.getMimeTypeEnum();
    }

    public /* bridge */ /* synthetic */ int getOrientation() {
        return super.getOrientation();
    }

    public /* bridge */ /* synthetic */ int getOrientationTag() {
        return super.getOrientationTag();
    }

    public /* bridge */ /* synthetic */ String getOriginalFileHash() {
        return super.getOriginalFileHash();
    }

    public /* bridge */ /* synthetic */ String getOwnerPackage() {
        return super.getOwnerPackage();
    }

    public /* bridge */ /* synthetic */ String getPath() {
        return super.getPath();
    }

    public RectF getRawCropRectRatio() {
        RectF rectF;
        if (isPanoramic()) {
            return MediaUnit.NULL_RECT;
        }
        if (isCreature()) {
            rectF = this.mFaceRectRatio;
        } else if (isCustomCover()) {
            rectF = this.mCustomCropRect;
        } else {
            rectF = getSmartCropRectRatio();
        }
        if (ExifUtils.isHorizontalMirror(this.mOrientationTag)) {
            return RectUtils.getMirrorHorizontalRectF(rectF, this.mOrientation);
        }
        return rectF;
    }

    public /* bridge */ /* synthetic */ String getReadableLog() {
        return super.getReadableLog();
    }

    public /* bridge */ /* synthetic */ int getRecordingMode() {
        return super.getRecordingMode();
    }

    public /* bridge */ /* synthetic */ int getRecordingType() {
        return super.getRecordingType();
    }

    public /* bridge */ /* synthetic */ String getRelativePath() {
        return super.getRelativePath();
    }

    public /* bridge */ /* synthetic */ String getResolution() {
        return super.getResolution();
    }

    public /* bridge */ /* synthetic */ int getSefFileSubType() {
        return super.getSefFileSubType();
    }

    public /* bridge */ /* synthetic */ int getSefFileType() {
        return super.getSefFileType();
    }

    public /* bridge */ /* synthetic */ String getSefFileTypes() {
        return super.getSefFileTypes();
    }

    public /* bridge */ /* synthetic */ String getShotMode() {
        return super.getShotMode();
    }

    public /* bridge */ /* synthetic */ int getSimpleHashCode() {
        return super.getSimpleHashCode();
    }

    public Size getSourceSize() {
        Size size;
        if (this.mSourceSize == null && this.mWidth > 0 && this.mHeight > 0) {
            int i2 = this.mOrientation;
            if (i2 == 90 || i2 == 270) {
                size = new Size(this.mHeight, this.mWidth);
            } else {
                size = new Size(this.mWidth, this.mHeight);
            }
            this.mSourceSize = size;
        }
        return this.mSourceSize;
    }

    public /* bridge */ /* synthetic */ StorageType getStorageType() {
        return super.getStorageType();
    }

    public String getSubGroupCategory() {
        return this.mSubGroupCategory;
    }

    public int getTagType() {
        return this.mTagType;
    }

    public /* bridge */ /* synthetic */ String getThumbCacheKey() {
        return super.getThumbCacheKey();
    }

    public /* bridge */ /* synthetic */ long getTimeStamp() {
        return super.getTimeStamp();
    }

    public /* bridge */ /* synthetic */ String getTitle() {
        return super.getTitle();
    }

    public /* bridge */ /* synthetic */ int getVideoColorFormat() {
        return super.getVideoColorFormat();
    }

    public int getVideoMetadataOrientation() {
        return this.mVideoMetadataOrientation;
    }

    public long getVideoThumbEndTime() {
        return this.mVideoHighlightEndTime;
    }

    public long getVideoThumbStartTime() {
        return this.mVideoThumbStartTime;
    }

    public boolean getVirtualAlbum() {
        Boolean bool = this.mIsVirtualAlbum;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public /* bridge */ /* synthetic */ String getVolumeName() {
        return super.getVolumeName();
    }

    public /* bridge */ /* synthetic */ int getWidth() {
        return super.getWidth();
    }

    public /* bridge */ /* synthetic */ int getWidthInDB() {
        return super.getWidthInDB();
    }

    public String getWidthList() {
        return this.mWidthList;
    }

    public /* bridge */ /* synthetic */ XmpType getXmpType() {
        return super.getXmpType();
    }

    public boolean hasFilterAndTone() {
        return this.mHasFilterAndTone;
    }

    public boolean hasPhotoHdrInfo() {
        String str = this.mSefFileTypes;
        if (str == null || !str.contains(SefFileTypeKey.PHOTO_HDR_INFO)) {
            return false;
        }
        return true;
    }

    public boolean hasPortraitsEffect() {
        return this.mHasPortraitsEffect;
    }

    public /* bridge */ /* synthetic */ boolean hasSefFileTypes(int i2) {
        return super.hasSefFileTypes(i2);
    }

    public void initDisplayName() {
        this.mDisplayName = null;
    }

    public /* bridge */ /* synthetic */ boolean is360Video() {
        return super.is360Video();
    }

    public boolean is360VideoExtended() {
        if (is360Video() || is360RecordingType()) {
            return true;
        }
        return false;
    }

    public boolean is4K() {
        if (this.mWidth * this.mHeight >= 8294400) {
            return true;
        }
        return false;
    }

    public boolean is8K() {
        if (this.mWidth * this.mHeight >= 20971520) {
            return true;
        }
        return false;
    }

    public boolean isAlbumHide() {
        return this.mIsAlbumHide;
    }

    public boolean isAlbumLvFirst() {
        if (this.mAlbumLevel == 1) {
            return true;
        }
        return false;
    }

    public boolean isAlbumShowInfo() {
        return this.mAlbumShowInfo;
    }

    public boolean isAutoAlbum() {
        if (this.mAlbumType == AlbumType.AutoUpdated) {
            return true;
        }
        return false;
    }

    public /* bridge */ /* synthetic */ boolean isBroken() {
        return super.isBroken();
    }

    public /* bridge */ /* synthetic */ boolean isCamModelSamsung() {
        return super.isCamModelSamsung();
    }

    public /* bridge */ /* synthetic */ boolean isCommonPostProcessing() {
        return super.isCommonPostProcessing();
    }

    public /* bridge */ /* synthetic */ boolean isDrm() {
        return super.isDrm();
    }

    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    public boolean isEmptyAlbum() {
        String str;
        if (isFolder() || this.mCount != 0 || (str = this.mPath) == null || !str.endsWith("!$&Welcome@#Image.jpg")) {
            return false;
        }
        return true;
    }

    public boolean isFHD() {
        if (this.mWidth * this.mHeight >= 2073600) {
            return true;
        }
        return false;
    }

    public boolean isFavourite() {
        return this.mFavorite;
    }

    public boolean isFolder() {
        return false;
    }

    public boolean isFolderItem() {
        return false;
    }

    public /* bridge */ /* synthetic */ boolean isHdr10Video() {
        return super.isHdr10Video();
    }

    public /* bridge */ /* synthetic */ boolean isHlgVideo() {
        return super.isHlgVideo();
    }

    public /* bridge */ /* synthetic */ boolean isImage() {
        return super.isImage();
    }

    public boolean isLegacyFolder() {
        AlbumType albumType = this.mAlbumType;
        if (albumType == null || albumType == AlbumType.None) {
            return true;
        }
        return false;
    }

    public boolean isLiveEffect() {
        String str = this.mSefFileTypes;
        if (str == null || !str.contains(SefFileTypeKey.LIVE_EFFECT_INFO)) {
            return false;
        }
        return true;
    }

    public boolean isLogVideo() {
        return RecordingMode.isLogVideo(this.mRecordingMode);
    }

    public boolean isMergedAlbum() {
        AlbumType albumType = this.mAlbumType;
        if (albumType == AlbumType.Merged || albumType == AlbumType.SystemMerged) {
            return true;
        }
        return false;
    }

    public boolean isMyAlbum() {
        if (this.mAlbumType == AlbumType.MyAlbum) {
            return true;
        }
        return false;
    }

    public boolean isNonMovieClip() {
        return MediaItemUtil.isNonMovieClip(this);
    }

    public boolean isOCRScanned() {
        if (this.mSefFileType == 2960) {
            return true;
        }
        return false;
    }

    public /* bridge */ /* synthetic */ boolean isPanoramic() {
        return super.isPanoramic();
    }

    public boolean isPhotoHdrCandidate() {
        if (SuperHdrConfig.SUPPORT) {
            if (this.mSefFileType == 2928) {
                return true;
            }
            if (this.mWidth * this.mHeight < 8000000 || !isCamModelSamsung() || isSingleTakeCollage() || isBurstShot() || isPanoramaShot()) {
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean isPhotoHdrSupported() {
        if (!SuperHdrConfig.SUPPORT || this.mMediaType != MediaType.Image) {
            return false;
        }
        MimeType mimeTypeEnum = getMimeTypeEnum();
        if (mimeTypeEnum == MimeType.JPG || mimeTypeEnum == MimeType.HEIF || mimeTypeEnum == MimeType.HEIC || mimeTypeEnum == MimeType.AVIF) {
            return true;
        }
        return false;
    }

    public /* bridge */ /* synthetic */ boolean isPostProcessing() {
        return super.isPostProcessing();
    }

    public /* bridge */ /* synthetic */ boolean isPostProcessingDraftProcessed() {
        return super.isPostProcessingDraftProcessed();
    }

    public boolean isQuramDecodable() {
        if (MimeType.isQuramDecodable(getMimeTypeEnum()) || isSamsungDng()) {
            return true;
        }
        return false;
    }

    public boolean isQuramDng() {
        return getDngVersion().startsWith("samsung");
    }

    public boolean isReadOnlyAlbum() {
        AlbumType albumType = this.mAlbumType;
        if (albumType == AlbumType.Virtual || albumType == AlbumType.AutoUpdated) {
            return true;
        }
        return false;
    }

    public boolean isRemasterSaved() {
        String str = this.mSefFileTypes;
        if (str == null || !str.contains(SefFileTypeKey.REMASTER_INFO)) {
            return false;
        }
        return true;
    }

    public boolean isRevitalization() {
        return this.mIsRevitalization;
    }

    public boolean isSamsungDng() {
        if (this.mIsSamsungDng == null) {
            this.mIsSamsungDng = Boolean.valueOf("samsung".equals(getDngVersion()));
        }
        return this.mIsSamsungDng.booleanValue();
    }

    public boolean isScreenShot() {
        if (BucketUtils.isScreenshot(getBucketID())) {
            return true;
        }
        String str = this.mTitle;
        if (str == null || !str.startsWith("Screenshot")) {
            return false;
        }
        return true;
    }

    public boolean isSingleFrameMovie() {
        return this.hasSingleFrame;
    }

    public boolean isSingleTakeCollage() {
        String str = this.mSefFileTypes;
        if (str == null || !str.contains(SefFileTypeKey.SINGLE_TAKE_IMAGE_COLLAGE)) {
            return false;
        }
        return true;
    }

    public /* bridge */ /* synthetic */ boolean isSingleTakenPostProcessing() {
        return super.isSingleTakenPostProcessing();
    }

    public boolean isStories() {
        return this.mIsStoriesItem;
    }

    public boolean isSupportRegionDecoding() {
        if (isBroken() || (!isLocal() && !isPrivateItem() && !isUriItem() && !isTemporary())) {
            return false;
        }
        if (isJpeg() || isPng() || isHeif() || isBmp()) {
            return true;
        }
        if (isWebp() && isSingleFrameMovie()) {
            return true;
        }
        if (!isDng() || !QuramBitmapFactory.isSecDng2(getDngVersion())) {
            return false;
        }
        return true;
    }

    public boolean isSystemMergedAlbum() {
        if (this.mAlbumType == AlbumType.SystemMerged) {
            return true;
        }
        return false;
    }

    public boolean isTransparent() {
        MimeType mimeTypeEnum = getMimeTypeEnum();
        if (mimeTypeEnum == MimeType.PNG || mimeTypeEnum == MimeType.GIF || mimeTypeEnum == MimeType.WEBP || mimeTypeEnum == MimeType.TIFF) {
            return true;
        }
        return false;
    }

    public /* bridge */ /* synthetic */ boolean isVideo() {
        return super.isVideo();
    }

    public boolean isVirtualAlbum() {
        if (this.mAlbumType == AlbumType.Virtual) {
            return true;
        }
        return false;
    }

    public void resetGroupShotMode() {
        this.mGroupMode = null;
    }

    public void setAlbumHide(boolean z) {
        this.mIsAlbumHide = z;
    }

    public void setAlbumID(int i2) {
        this.mAlbumID = i2;
    }

    public void setAlbumLevel(int i2) {
        this.mAlbumLevel = i2;
    }

    public void setAlbumOrder(long j2) {
        this.mAlbumOrder = j2;
    }

    public void setAlbumShowInfo(boolean z) {
        this.mAlbumShowInfo = z;
    }

    public void setAlbumType(AlbumType albumType) {
        this.mAlbumType = albumType;
    }

    public void setBestImage() {
        this.mBestImage = 1;
    }

    public void setBroken(boolean z) {
        if (!SafeMode.ENABLED) {
            this.mIsBroken = z;
            if (z) {
                this.mWidth = ThumbKind.MEDIUM_KIND_SIZE;
                this.mHeight = ThumbKind.MEDIUM_KIND_SIZE;
                this.mOrientation = 0;
                this.mOrientationTag = 0;
                this.mSourceSize = null;
                Log.i("MediaItem", "setBroken " + this);
            }
        }
    }

    public void setCategory(String str) {
        this.mMainCategory = str;
        this.mScenesOrCreature = isScenesOrCreature(str);
    }

    public void setCount(int i2) {
        if (Features.isEnabled(Features.SUPPORT_CLUSTER_TABLE)) {
            updateGroupModeSimilar(i2);
        }
        this.mCount = i2;
    }

    public /* bridge */ /* synthetic */ void setCropRect(RectF rectF) {
        super.setCropRect(rectF);
    }

    public void setCropRectRatio(RectF rectF) {
        this.mCropRect = rectF;
    }

    public /* bridge */ /* synthetic */ void setCropRectRatioList(ArrayList arrayList) {
        super.setCropRectRatioList(arrayList);
    }

    public void setDateModified(long j2) {
        if (j2 != this.mDateModified) {
            this.mUidCachedSet = false;
        }
        this.mDateModified = j2;
        this.mComplexHashcode = 0;
    }

    public /* bridge */ /* synthetic */ void setDisplayName(String str) {
        super.setDisplayName(str);
    }

    public void setFavourite(boolean z) {
        this.mFavorite = z;
    }

    public /* bridge */ /* synthetic */ void setFileDuration(int i2) {
        super.setFileDuration(i2);
    }

    public /* bridge */ /* synthetic */ void setFileId(long j2) {
        super.setFileId(j2);
    }

    public /* bridge */ /* synthetic */ void setFileSize(long j2) {
        super.setFileSize(j2);
    }

    public /* bridge */ /* synthetic */ void setFileSizeOnly(long j2) {
        super.setFileSizeOnly(j2);
    }

    public void setFolderFirstFileId(long j2) {
        this.mFileID = j2;
    }

    public void setFolderInfo(int i2, String str) {
        this.mFolderID = i2;
        this.mFolderName = str;
    }

    public void setFolderName(String str) {
        this.mFolderName = str;
    }

    public void setFolderPosition(FolderPosition folderPosition) {
        this.mFolderPosition = folderPosition;
    }

    public /* bridge */ /* synthetic */ void setGroupMediaId(long j2) {
        super.setGroupMediaId(j2);
    }

    public /* bridge */ /* synthetic */ void setLatLong(double[] dArr) {
        super.setLatLong(dArr);
    }

    public /* bridge */ /* synthetic */ void setMediaId(long j2) {
        super.setMediaId(j2);
    }

    public /* bridge */ /* synthetic */ void setMediaType(MediaType mediaType) {
        super.setMediaType(mediaType);
    }

    public /* bridge */ /* synthetic */ void setMimeType(String str) {
        super.setMimeType(str);
    }

    public /* bridge */ /* synthetic */ void setOrientation(int i2) {
        super.setOrientation(i2);
    }

    public /* bridge */ /* synthetic */ void setOrientationTag(int i2) {
        super.setOrientationTag(i2);
    }

    public void setPath(String str) {
        this.mPath = str;
        this.mThumbnailCacheKey = null;
        this.mUidCachedSet = false;
        this.mComplexHashcode = 0;
    }

    public /* bridge */ /* synthetic */ void setRecordingMode(int i2) {
        super.setRecordingMode(i2);
    }

    public void setRevitalization() {
        this.mIsRevitalization = true;
    }

    public /* bridge */ /* synthetic */ void setSefFileType(int i2, int i7) {
        super.setSefFileType(i2, i7);
    }

    public void setSingleFrameMovie() {
        this.hasSingleFrame = true;
    }

    public /* bridge */ /* synthetic */ void setSize(int i2, int i7) {
        super.setSize(i2, i7);
    }

    public /* bridge */ /* synthetic */ void setStorageType(StorageType storageType) {
        super.setStorageType(storageType);
    }

    public void setSubCategory(String str) {
        this.mSubCategory = str;
    }

    public /* bridge */ /* synthetic */ void setTitle(String str) {
        super.setTitle(str);
    }

    public /* bridge */ /* synthetic */ void setVideoColorFormat(int i2) {
        super.setVideoColorFormat(i2);
    }

    public void setVideoHighlightTime(Long l, Long l8) {
        if (l != null) {
            this.mVideoHighlightStartTime = l.longValue();
            setVideoThumbStartTime(l.longValue());
        }
        if (l8 != null) {
            this.mVideoHighlightEndTime = l8.longValue();
        }
    }

    public /* bridge */ /* synthetic */ void setVideoMetadataOrientation(int i2) {
        super.setVideoMetadataOrientation(i2);
    }

    public void setVideoThumbStartTime(long j2) {
        this.mVideoThumbStartTime = j2;
        this.mThumbnailCacheKey = null;
        this.mComplexHashcode = 0;
    }

    public void setVirtualAlbum(boolean z) {
        this.mIsVirtualAlbum = Boolean.valueOf(z);
    }

    public /* bridge */ /* synthetic */ void setVolumeName(String str) {
        super.setVolumeName(str);
    }

    public /* bridge */ /* synthetic */ void setXmpType(XmpType xmpType) {
        super.setXmpType(xmpType);
    }

    public /* bridge */ /* synthetic */ Map tags() {
        return super.tags();
    }

    public String toString() {
        String str;
        String str2;
        String str3;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(super.toString());
        sb2.append(" [");
        Object obj = this.mAlbumType;
        if (obj == null) {
            obj = "-";
        }
        sb2.append(obj);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        String str4 = "";
        if (this.mHasPortraitsEffect) {
            str = "P";
        } else {
            str = str4;
        }
        sb2.append(str);
        if (this.mHasFilterAndTone) {
            str2 = "F";
        } else {
            str2 = str4;
        }
        sb2.append(str2);
        if (this.mIsRevitalization) {
            str3 = "R";
        } else {
            str3 = str4;
        }
        sb2.append(str3);
        sb2.append("]");
        if (this.mIsBroken || this.mFileSize == 0) {
            str4 = " " + getOwnerPackage() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + Logger.getEncodedString(this.mPath);
        }
        sb2.append(str4);
        return sb2.toString();
    }

    public void update(ExifInterface exifInterface) {
        try {
            this.mOrientation = ExifUtils.getOrientation(exifInterface);
            this.mOrientationTag = ExifUtils.getOrientationTag(exifInterface);
            this.mThumbnailCacheKey = null;
            if (!isRawImage()) {
                this.mWidth = ExifUtils.getWidth(exifInterface);
                this.mHeight = ExifUtils.getHeight(exifInterface);
                this.mSourceSize = null;
            }
            long dateTimeOriginal = ExifUtils.getDateTimeOriginal(exifInterface);
            if (dateTimeOriginal != -1) {
                this.mDateTaken = TimeUtil.toUtcTimeInMillis(dateTimeOriginal);
            }
            ExifTag exifTag = new ExifTag(exifInterface, PocFeatures.isEnabled(PocFeatures.MoreInfoExif));
            this.mExifTag = exifTag;
            double[] dArr = exifTag.location;
            if (dArr != null) {
                this.mLatitude = dArr[0];
                this.mLongitude = dArr[1];
            }
        } catch (Exception unused) {
        }
    }

    public void updateGroupModeSimilar(int i2) {
        if (this.mCount == DbTable.UNLOADED && this.mGroupType == 2 && i2 > 1) {
            this.mGroupMode = "Similar photo";
        } else if (i2 == 1 || i2 == 0) {
            this.mGroupMode = null;
            this.mGroupType = 0;
            this.mGroupMediaId = 0;
        }
    }

    public MediaItem clone() {
        try {
            return (MediaItem) super.clone();
        } catch (CloneNotSupportedException e) {
            Log.e("MediaItem", "clone failed e=" + e.getMessage());
            return this;
        }
    }

    public MediaItem[] getAlbumsInFolder(boolean z) {
        return EMPTY_ARRAY;
    }

    public MediaItem(String str, MediaType mediaType, StorageType storageType) {
        this.mMimeType = str;
        this.mMediaType = mediaType;
        this.mStorageType = storageType;
    }

    public MediaItem getMediaItem() {
        return this;
    }

    public MediaItem() {
    }

    public void setMediaItem(MediaItem mediaItem) {
    }
}
