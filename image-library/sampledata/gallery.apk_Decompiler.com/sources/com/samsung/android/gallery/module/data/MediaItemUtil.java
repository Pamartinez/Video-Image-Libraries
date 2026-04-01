package com.samsung.android.gallery.module.data;

import B8.b;
import N2.j;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import androidx.exifinterface.media.ExifInterface;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.database.dbtype.AlbumType;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.lockedalbum.LockedAlbum;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.abstraction.VideoPropData;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.dynamicview.DynamicViewInfo;
import com.samsung.android.gallery.module.graphics.QuramBitmapFactory;
import com.samsung.android.gallery.module.motionphoto.MotionPhotoViewMode;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.DeviceConfig;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.debugger.FileHistory;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.library.sef.SefParser;
import com.samsung.android.gallery.support.providers.CameraUsbUri;
import com.samsung.android.gallery.support.shotmode.RecordingMode;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.BucketUtils;
import com.samsung.android.gallery.support.utils.ExifUtils;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileType;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.IdentityCreatureUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.MathUtils;
import com.samsung.android.gallery.support.utils.PocFeatures;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IFormat;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import com.samsung.android.sum.core.types.NumericEnum;
import i.C0212a;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.json.JSONObject;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MediaItemUtil {
    private static final CharSequence TAG = "MediaItemUtil";

    public static String calcVideoTimezoneOffset(MediaItem mediaItem) {
        String str;
        long j2;
        long dateLocal = (mediaItem.getDateLocal() - mediaItem.getDateTaken()) + ((long) mediaItem.getFileDuration());
        int i2 = (dateLocal > 0 ? 1 : (dateLocal == 0 ? 0 : -1));
        if (i2 >= 0) {
            str = "+";
        } else {
            str = "-";
        }
        if (i2 < 0) {
            j2 = Math.min((long) (((float) ((-dateLocal) + 59999)) / 60000.0f), 720);
        } else {
            j2 = Math.min((long) (((float) (dateLocal + 59999)) / 60000.0f), 840);
        }
        long j3 = j2 / 60;
        return str.concat(String.format(Locale.US, "%02d%02d", new Object[]{Long.valueOf(j3), Long.valueOf(((j2 - (60 * j3)) / 10) * 10)}));
    }

    public static boolean checkWrongSize(MediaItem mediaItem, Bitmap bitmap) {
        float f;
        boolean equals;
        if (mediaItem == null || bitmap == null) {
            return false;
        }
        if (mediaItem.getWidth() <= 0 || mediaItem.getHeight() <= 0) {
            return true;
        }
        if (!mediaItem.isLocal() || bitmap.getWidth() <= 0 || bitmap.getHeight() <= 0) {
            return false;
        }
        if (mediaItem.isPanoramic()) {
            f = 0.4f;
        } else {
            f = 0.2f;
        }
        if (bitmap.getHeight() > bitmap.getWidth()) {
            equals = MathUtils.equals(((float) mediaItem.getHeight()) / ((float) mediaItem.getWidth()), ((float) bitmap.getHeight()) / ((float) bitmap.getWidth()), f);
        } else {
            equals = MathUtils.equals(((float) mediaItem.getWidth()) / ((float) mediaItem.getHeight()), ((float) bitmap.getWidth()) / ((float) bitmap.getHeight()), f);
        }
        return !equals;
    }

    public static boolean containsAlbum(MediaItem mediaItem, int i2) {
        if (mediaItem == null) {
            return false;
        }
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS || !mediaItem.isMergedAlbum()) {
            if (mediaItem.getAlbumID() == i2) {
                return true;
            }
            return false;
        } else if (mediaItem.getAlbumID() == i2 || mediaItem.getChildList().stream().anyMatch(new b(i2, 11))) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean containsLockedAlbum(MediaItem mediaItem) {
        if (mediaItem == null) {
            return false;
        }
        if (mediaItem.isMergedAlbum()) {
            for (MediaItem albumID : mediaItem.getChildList()) {
                if (LockedAlbum.getInstance().contains(albumID.getAlbumID())) {
                    return true;
                }
            }
        }
        return LockedAlbum.getInstance().contains(mediaItem.getAlbumID());
    }

    public static boolean equals(FileItemInterface fileItemInterface, FileItemInterface fileItemInterface2) {
        if (equalsBitmap(fileItemInterface, fileItemInterface2) && fileItemInterface.getCount() == fileItemInterface2.getCount() && TextUtils.equals(fileItemInterface.getTitle(), fileItemInterface2.getTitle()) && fileItemInterface.getLatitude() == fileItemInterface2.getLatitude() && fileItemInterface.getLongitude() == fileItemInterface2.getLongitude()) {
            return true;
        }
        return false;
    }

    public static boolean equalsBitmap(FileItemInterface fileItemInterface, FileItemInterface fileItemInterface2) {
        if (fileItemInterface == null || fileItemInterface2 == null || fileItemInterface.getFileId() != fileItemInterface2.getFileId() || fileItemInterface.getDateTaken() != fileItemInterface2.getDateTaken() || fileItemInterface.getSefFileType() != fileItemInterface2.getSefFileType()) {
            return false;
        }
        if ((fileItemInterface.getFileSize() != fileItemInterface2.getFileSize() && !isVideoAndNotSupportVideoPlayerForMotionVideo(fileItemInterface)) || !TextUtils.equals(fileItemInterface.getPath(), fileItemInterface2.getPath())) {
            return false;
        }
        if ((!fileItemInterface.isBroken() && fileItemInterface.getOrientation() != fileItemInterface2.getOrientation()) || isDifferentRemasterStatus(fileItemInterface, fileItemInterface2) || isDifferentPortraitsStatus(fileItemInterface, fileItemInterface2) || isDifferentFilterAndToneStatus(fileItemInterface, fileItemInterface2)) {
            return false;
        }
        if (isDualShot(fileItemInterface) && isDualShot(fileItemInterface2) && fileItemInterface.getDateModified() != fileItemInterface2.getDateModified()) {
            return false;
        }
        if (fileItemInterface.isBroken() || !SdkConfig.atLeast(SdkConfig.GED.V)) {
            return true;
        }
        if (fileItemInterface.getWidth() == fileItemInterface2.getWidth() && fileItemInterface.getHeight() == fileItemInterface2.getHeight()) {
            return true;
        }
        return false;
    }

    public static boolean equalsId(FileItemInterface fileItemInterface, FileItemInterface fileItemInterface2) {
        if (fileItemInterface == null || fileItemInterface2 == null || fileItemInterface.getFileId() != fileItemInterface2.getFileId()) {
            return false;
        }
        return true;
    }

    public static boolean equalsPpp(FileItemInterface fileItemInterface, FileItemInterface fileItemInterface2) {
        if (fileItemInterface == null || fileItemInterface2 == null || fileItemInterface.getFileId() != fileItemInterface2.getFileId() || fileItemInterface.getCount() != fileItemInterface2.getCount() || fileItemInterface.getSefFileType() != fileItemInterface2.getSefFileType()) {
            return false;
        }
        if ((fileItemInterface.getFileSize() == fileItemInterface2.getFileSize() || isVideoAndNotSupportVideoPlayerForMotionVideo(fileItemInterface)) && TextUtils.equals(fileItemInterface.getPath(), fileItemInterface2.getPath()) && TextUtils.equals(fileItemInterface.getTitle(), fileItemInterface2.getTitle()) && fileItemInterface.getOrientation() == fileItemInterface2.getOrientation()) {
            return true;
        }
        return false;
    }

    public static boolean equalsSimple(FileItemInterface fileItemInterface, FileItemInterface fileItemInterface2) {
        if (fileItemInterface == null || fileItemInterface2 == null || fileItemInterface.getFileId() != fileItemInterface2.getFileId() || !TextUtils.equals(fileItemInterface.getPath(), fileItemInterface2.getPath())) {
            return false;
        }
        return true;
    }

    public static boolean equalsWithDateModified(FileItemInterface fileItemInterface, FileItemInterface fileItemInterface2) {
        if (!equals(fileItemInterface, fileItemInterface2) || fileItemInterface.getDateModified() != fileItemInterface2.getDateModified()) {
            return false;
        }
        return true;
    }

    private static void fetchChildAlbums(HashMap<AlbumType, ArrayList<MediaItem>> hashMap, MediaItem mediaItem) {
        for (MediaItem next : mediaItem.getChildList()) {
            if (next.isFolder() || next.isMergedAlbum()) {
                fetchChildAlbums(hashMap, next);
            } else {
                hashMap.computeIfAbsent(next.getAlbumType(), new j(9)).add(next);
            }
        }
    }

    public static HashMap<AlbumType, ArrayList<MediaItem>> getAllChildAlbums(Collection<MediaItem> collection) {
        HashMap<AlbumType, ArrayList<MediaItem>> hashMap = new HashMap<>();
        for (MediaItem next : collection) {
            if (next.isFolder() || next.isMergedAlbum()) {
                fetchChildAlbums(hashMap, next);
            }
            hashMap.computeIfAbsent(next.getAlbumType(), new j(7)).add(next);
        }
        Log.d(TAG, "getAllChildAlbums", (String) hashMap.entrySet().stream().map(new j(8)).collect(Collectors.joining(GlobalPostProcInternalPPInterface.SPLIT_REGEX)));
        return hashMap;
    }

    public static int getContentCount(MediaItem mediaItem) {
        int i2 = 0;
        if (mediaItem.isFolder() || mediaItem.isMergedAlbum()) {
            for (MediaItem contentCount : mediaItem.getChildList()) {
                i2 += getContentCount(contentCount);
            }
            return i2;
        } else if (mediaItem.isReadOnlyAlbum() || mediaItem.isAlbumHide()) {
            return 0;
        } else {
            if (!PocFeatures.SUPPORT_LOCKED_ALBUM || !containsLockedAlbum(mediaItem)) {
                return mediaItem.getCount();
            }
            return 0;
        }
    }

    public static String getDebugLog(MediaItem mediaItem) {
        if (mediaItem == null) {
            return "MediaItem{null}";
        }
        String mediaItem2 = mediaItem.toString();
        FileHistory.put(mediaItem.getFileId(), mediaItem2);
        return mediaItem2;
    }

    public static DynamicViewInfo getDynamicViewInfo(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null) {
            return null;
        }
        return DynamicViewData.of(fileItemInterface).dynamicViewInfo;
    }

    public static long getHighLightClipEndTime(MediaItem mediaItem) {
        if (mediaItem != null && PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_GROUP_PANEL) {
            return mediaItem.getVideoThumbEndTime();
        }
        Log.e(TAG, "getHighLightClipEndTime failed : null item");
        return 0;
    }

    public static String getMediaLog(MediaItem mediaItem) {
        if (mediaItem == null) {
            return "MediaItem{null}";
        }
        StringBuilder sb2 = new StringBuilder("MediaItem{");
        sb2.append(mediaItem.getFileId());
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(mediaItem.mWidth);
        sb2.append("x");
        sb2.append(mediaItem.mHeight);
        sb2.append(com.samsung.android.sdk.scs.base.utils.Log.TAG_SEPARATOR);
        sb2.append(mediaItem.mOrientation);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(mediaItem.mOrientationTag);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(mediaItem.mMimeType);
        sb2.append(',');
        sb2.append(mediaItem.mStorageType);
        String str = "";
        if (mediaItem.mMediaType == MediaType.Video) {
            StringBuilder sb3 = new StringBuilder(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
            sb3.append(mediaItem.getFileDuration());
            if (mediaItem.isHdr10Video()) {
                str = ",hdr10+";
            }
            sb3.append(str);
            str = sb3.toString();
        }
        return C0086a.m(sb2, str, '}');
    }

    public static MotionPhotoViewMode getMotionPhotoViewMode(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isMotionPhoto()) {
            return MotionPhotoViewMode.OFF;
        }
        if (PreferenceFeatures.OneUi7x.SUPPORT_MOTION_PHOTO_VIEW_MODE) {
            return (MotionPhotoViewMode) Optional.ofNullable(DetailsData.of(mediaItem).motionPhotoViewMode).orElse(MotionPhotoViewMode.ON);
        }
        return MotionPhotoViewMode.ON;
    }

    public static String getSamsungDngVersion(String str) {
        if (str == null || !"dng".equalsIgnoreCase(FileUtils.getExtension(str))) {
            return "";
        }
        return QuramBitmapFactory.getDngVer(str);
    }

    public static String getSimpleLog(MediaItem mediaItem) {
        if (mediaItem == null) {
            return "MediaItem{null}";
        }
        FileHistory.put(mediaItem.getFileId(), mediaItem.toString());
        StringBuilder sb2 = new StringBuilder("MediaItem{");
        Object obj = mediaItem.mMediaType;
        if (obj == MediaType.Video) {
            obj = "v";
        } else if (obj == MediaType.Image) {
            obj = "i";
        }
        sb2.append(obj);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(mediaItem.mMediaID);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(mediaItem.mFileID);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(mediaItem.mAlbumID);
        sb2.append(GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        sb2.append(mediaItem.mStorageType);
        sb2.append(NumericEnum.SEP);
        return C0086a.l(sb2, mediaItem.mSefFileType, "}");
    }

    public static String getStorage(MediaItem mediaItem) {
        if (mediaItem.isLocalOnly()) {
            return AnalyticsDetail.LOCAL.toString();
        }
        if (mediaItem.isCloudOnly()) {
            return AnalyticsDetail.CLOUD_ONLY.toString();
        }
        return AnalyticsDetail.LOCAL_AND_CLOUD.toString();
    }

    public static int getSuperSlowClipEffect(MediaItem mediaItem) {
        if (!PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_GROUP_PANEL || mediaItem == null) {
            Log.e(TAG, "getSuperSlowClipEffect failed : null item");
            return 0;
        }
        Integer num = VideoPropData.of(mediaItem).superSlowClipEffect;
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }

    public static String getTargetPathFromPPP(String str) {
        String str2 = FileUtils.PPP_TEMP_DIR;
        return str.replace(str2, StorageInfo.getDefault().camera + "/").replace("_temp.jpg", ".jpg");
    }

    public static String getViewerBitmapKey(MediaItem mediaItem) {
        StringBuilder sb2 = new StringBuilder("data://bitmap/viewer/");
        sb2.append(mediaItem != null ? Integer.valueOf(mediaItem.getSimpleHashCode()) : "null");
        return sb2.toString();
    }

    public static boolean hasInstantSloMoLatestExecuteSection(MediaItem mediaItem) {
        if (mediaItem == null || VideoPropData.of(mediaItem).instantSlowMoLastExecutedSection == null) {
            return false;
        }
        return true;
    }

    public static boolean hasValidSize(MediaItem mediaItem) {
        if (mediaItem == null) {
            return false;
        }
        if (mediaItem.isCloudOnly()) {
            if (mediaItem.getCloudOriginalSize() > 0) {
                return true;
            }
            return false;
        } else if (mediaItem.getFileSize() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean hasWatermarkFrameInfo(MediaItem mediaItem) {
        byte[] data;
        try {
            if (!mediaItem.hasSefFileTypes(3201) || (data = SeApiCompat.getSefFileCompat().getData(new File(mediaItem.getPath()), SefInfo.WATERMARK_INFO.keyName)) == null || data.length <= 0 || !new JSONObject(new String(data)).has("frameType")) {
                return false;
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isApv(MediaItem mediaItem) {
        if (mediaItem == null) {
            return false;
        }
        if (RecordingMode.isApv(mediaItem.getRecordingMode()) || "apv".equalsIgnoreCase(VideoPropData.of(mediaItem).videoCodec)) {
            return true;
        }
        return false;
    }

    public static boolean isColorCorrectAutoSave(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isLogVideo()) {
            return false;
        }
        return DetailsData.of(mediaItem).autoSaveByColorCorrect;
    }

    public static boolean isCreatureCandidate(String str) {
        if (!PocFeatures.DEBUG_FACE_RECT || !IdentityCreatureUtil.isIdentityKey(str)) {
            return false;
        }
        return true;
    }

    public static boolean isDifferentFilterAndToneStatus(FileItemInterface fileItemInterface, FileItemInterface fileItemInterface2) {
        if (fileItemInterface.getFileId() != fileItemInterface2.getFileId() || fileItemInterface.hasFilterAndTone() == fileItemInterface2.hasFilterAndTone()) {
            return false;
        }
        return true;
    }

    public static boolean isDifferentPortraitsStatus(FileItemInterface fileItemInterface, FileItemInterface fileItemInterface2) {
        if (fileItemInterface.getFileId() != fileItemInterface2.getFileId() || fileItemInterface.hasPortraitsEffect() == fileItemInterface2.hasPortraitsEffect()) {
            return false;
        }
        return true;
    }

    public static boolean isDifferentRemasterStatus(FileItemInterface fileItemInterface, FileItemInterface fileItemInterface2) {
        if (fileItemInterface.getFileId() != fileItemInterface2.getFileId() || fileItemInterface.isRemasterSaved() == fileItemInterface2.isRemasterSaved()) {
            return false;
        }
        return true;
    }

    public static boolean isDualShot(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null || !fileItemInterface.isShotMode("Dual capture")) {
            return false;
        }
        return true;
    }

    public static boolean isEmptyAlbum(MediaItem mediaItem) {
        if (mediaItem == null) {
            return false;
        }
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS || !isVirtualEmptyAlbum(mediaItem)) {
            return mediaItem.isEmptyAlbum();
        }
        return true;
    }

    public static boolean isFavouriteAlbum(MediaItem mediaItem) {
        if (mediaItem == null || !BucketUtils.isFavourite(mediaItem.getAlbumID())) {
            return false;
        }
        return true;
    }

    public static boolean isGroupedAlbum(MediaItem mediaItem) {
        if (mediaItem == null) {
            return false;
        }
        if (mediaItem.isFolder()) {
            return true;
        }
        if (!PreferenceFeatures.OneUi5x.MX_ALBUMS || !mediaItem.isMergedAlbum()) {
            return false;
        }
        return true;
    }

    public static boolean isHdr10OrHlgVideo(MediaItem mediaItem) {
        if (mediaItem == null) {
            return false;
        }
        if (mediaItem.isHdr10Video() || mediaItem.isHlgVideo()) {
            return true;
        }
        return false;
    }

    public static boolean isHdr10plusWithAutoConvertOn(MediaItem mediaItem) {
        if (!Features.isEnabled(Features.SUPPORT_HDR10PLUS_CONVERSION) || !PreferenceFeatures.isEnabled(PreferenceFeatures.HDR10PlusAutoConvert) || !isSamsungHdr10plusVideo(mediaItem)) {
            return false;
        }
        return true;
    }

    public static boolean isHeifWithAutoConvertOn(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isHeif() || mediaItem.isUriItem() || mediaItem.isRevitalization()) {
            return false;
        }
        return true;
    }

    public static boolean isHighLightClip(MediaItem mediaItem) {
        if (mediaItem == null || !PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_GROUP_PANEL || mediaItem.getVideoThumbEndTime() <= 0) {
            return false;
        }
        return true;
    }

    public static boolean isLandscapeItem(MediaItem mediaItem) {
        if (mediaItem == null) {
            return false;
        }
        int width = mediaItem.getWidth();
        int height = mediaItem.getHeight();
        if (mediaItem.getOrientation() == 90 || mediaItem.getOrientation() == 270) {
            if (height > width) {
                return true;
            }
            return false;
        } else if (width > height) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isMotionPhotoAutoPlayViewMode(MediaItem mediaItem) {
        if (!PreferenceFeatures.OneUi7x.SUPPORT_MOTION_PHOTO_VIEW_MODE) {
            return false;
        }
        MotionPhotoViewMode motionPhotoViewMode = getMotionPhotoViewMode(mediaItem);
        if (motionPhotoViewMode == MotionPhotoViewMode.BOOMERANG || motionPhotoViewMode == MotionPhotoViewMode.SLOW_MO) {
            return true;
        }
        return false;
    }

    public static boolean isNonMovieClip(MediaItem mediaItem) {
        String str;
        if (PreferenceFeatures.VIDEO_THUMBNAIL_WITH_FIRST_FRAME || mediaItem == null || ((long) mediaItem.mFileDuration) < 15000) {
            return true;
        }
        if (TrashData.isTrash(mediaItem)) {
            str = TrashData.getOriginalPath(mediaItem);
        } else {
            str = mediaItem.getPath();
        }
        if (FileUtils.isZeroVideoFrameTimePath(str)) {
            return true;
        }
        return false;
    }

    public static boolean isNoneDestructionSlowRecordingMode(int i2) {
        return RecordingMode.isNds(i2);
    }

    public static boolean isRecentAlbum(MediaItem mediaItem) {
        if (mediaItem == null || !BucketUtils.isRecent(mediaItem.getAlbumID())) {
            return false;
        }
        return true;
    }

    public static boolean isRemasterAutosave(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isRevitalization()) {
            return false;
        }
        return DetailsData.of(mediaItem).autoSaveByRemaster;
    }

    public static boolean isRemastering(MediaItem mediaItem) {
        Object extra;
        if (mediaItem == null || (extra = mediaItem.getExtra(ExtrasID.REMASTERING_STATE)) == null || !((Boolean) extra).booleanValue()) {
            return false;
        }
        return true;
    }

    public static boolean isSamsungHdr10plusVideo(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isVideo() || !RecordingMode.isHdr10Plus(mediaItem.getRecordingMode()) || !mediaItem.isHdr10Video()) {
            return false;
        }
        return true;
    }

    public static boolean isSamsungPrivilegedVideo(MediaItem mediaItem) {
        int recordingMode;
        if (mediaItem == null) {
            return false;
        }
        if (DynamicViewData.of(mediaItem).dynamicViewPlayInfo != null || (recordingMode = mediaItem.getRecordingMode()) == 2) {
            return true;
        }
        if ((recordingMode != 12 || !Features.isEnabled(Features.SUPPORT_SLOWMOTION_V2)) && ((recordingMode != 13 || !Features.isEnabled(Features.SUPPORT_SLOWMOTION_V2_120FS)) && ((recordingMode != 15 || !Features.isEnabled(Features.SUPPORT_SLOWMOTION_V2_NO_SVC)) && (((recordingMode != 18 && recordingMode != 19) || !Features.isEnabled(Features.SUPPORT_SLOWMOTION_V2_WITHOUT_SVC_240)) && recordingMode != 21 && recordingMode != 22)))) {
            return false;
        }
        return true;
    }

    public static boolean isSharableItem(MediaItem mediaItem, boolean z) {
        long j2;
        if (mediaItem != null && !mediaItem.isDrm()) {
            boolean hasValidSize = hasValidSize(mediaItem);
            if (!hasValidSize && !mediaItem.isCloudOnly()) {
                String path = mediaItem.getPath();
                if (path != null) {
                    j2 = FileUtils.length(path);
                } else {
                    j2 = 0;
                }
                Log.d(TAG, "isSharableItem invalid size : " + j2 + ArcCommonLog.TAG_COMMA + mediaItem);
                if (j2 > 0) {
                    mediaItem.setFileSizeOnly(j2);
                    hasValidSize = true;
                }
            }
            if (!hasValidSize || mediaItem.getMediaType() == MediaType.UnlockScreen || mediaItem.isCommonPostProcessing() || (z && ContentUri.getUri(mediaItem) == null)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean isSlowMoConvertible(MediaItem mediaItem) {
        if (SdkConfig.atLeast(SdkConfig.GED.T) || mediaItem == null || !mediaItem.isVideo() || !mediaItem.isLocal() || !mediaItem.isShotMode("slow_motion")) {
            return false;
        }
        int recordingMode = mediaItem.getRecordingMode();
        if (recordingMode == 1 || ((recordingMode == 12 && Features.isEnabled(Features.SUPPORT_SLOWMOTION_V2)) || ((recordingMode == 13 && Features.isEnabled(Features.SUPPORT_SLOWMOTION_V2_120FS)) || ((recordingMode == 15 && Features.isEnabled(Features.SUPPORT_SLOWMOTION_V2_NO_SVC)) || ((recordingMode == 19 && Features.isEnabled(Features.SUPPORT_SLOWMOTION_V2_WITHOUT_SVC_240)) || recordingMode == 21))))) {
            return true;
        }
        return false;
    }

    private static boolean isSuggestedEditSuperSlowRecordingMode(int i2) {
        if (i2 == 90 || i2 == 91 || i2 == 92) {
            return true;
        }
        return false;
    }

    public static boolean isSuggestedEditSuperSlowVideo(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isVideo() || !isSuggestedEditSuperSlowRecordingMode(mediaItem.getRecordingMode())) {
            return false;
        }
        return true;
    }

    public static boolean isSuggestedEditVideo(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isVideo() || !isSuggestedEditVideoRecordingMode(mediaItem.getRecordingMode())) {
            return false;
        }
        return true;
    }

    private static boolean isSuggestedEditVideoRecordingMode(int i2) {
        if (isSuggestedEditSuperSlowRecordingMode(i2) || i2 == 93 || i2 == 94 || i2 == 95 || i2 == 97 || i2 == 96 || i2 == 98) {
            return true;
        }
        return false;
    }

    public static boolean isSuperSlowClip(MediaItem mediaItem) {
        if (!PreferenceFeatures.OneUi6x.SUPPORT_AI_EDIT_GROUP_PANEL || mediaItem == null || VideoPropData.of(mediaItem).superSlowClipEffect == null) {
            return false;
        }
        return true;
    }

    public static boolean isSuperSlowMotion(MediaItem mediaItem) {
        if (mediaItem == null || !Features.isEnabled(Features.SUPPORT_SUPER_SLOW_MOTION) || !RecordingMode.isSuperSlowMo(mediaItem.getRecordingMode())) {
            return false;
        }
        return true;
    }

    public static boolean isUrlAvailable(MediaItem mediaItem) {
        if (!mediaItem.isRevitalization() && Features.isEnabled(Features.SUPPORT_GO_TO_URL)) {
            DetailsData of2 = DetailsData.of(mediaItem);
            String str = of2.capturedUrl;
            if (!TextUtils.isEmpty(str) && !"package".equals(Uri.parse(str).getScheme())) {
                return true;
            }
            String str2 = of2.capturedPath;
            if (!TextUtils.isEmpty(str2)) {
                String scheme = Uri.parse(str2).getScheme();
                if (TextUtils.isEmpty(scheme) || "package".equals(scheme)) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public static boolean isUsbFile(MediaItem mediaItem) {
        if (mediaItem == null || !CameraUsbUri.match(mediaItem.getPath())) {
            return false;
        }
        return true;
    }

    public static boolean isVideoAndNotSupportVideoPlayerForMotionVideo(FileItemInterface fileItemInterface) {
        if (!fileItemInterface.isVideo() || Features.isEnabled(Features.SUPPORT_VIDEO_PLAYER_FOR_MOTION_VIDEO)) {
            return false;
        }
        return true;
    }

    public static boolean isVirtualAlbum(MediaItem mediaItem) {
        if (isRecentAlbum(mediaItem) || isFavouriteAlbum(mediaItem)) {
            return true;
        }
        return false;
    }

    public static boolean isVirtualEmptyAlbum(MediaItem mediaItem) {
        if (mediaItem == null || mediaItem.getCount() != 0) {
            return false;
        }
        if (mediaItem.isReadOnlyAlbum() || mediaItem.isMergedAlbum()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$containsAlbum$3(int i2, MediaItem mediaItem) {
        if (mediaItem.getAlbumID() == i2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ArrayList lambda$fetchChildAlbums$2(AlbumType albumType) {
        return new ArrayList();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ ArrayList lambda$getAllChildAlbums$0(AlbumType albumType) {
        return new ArrayList();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ String lambda$getAllChildAlbums$1(Map.Entry entry) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(entry.getKey());
        sb2.append("=");
        return j.g(sb2, (ArrayList) entry.getValue());
    }

    public static void setColorCorrectAutoSave(MediaItem mediaItem, boolean z) {
        if (mediaItem != null) {
            DetailsData.of(mediaItem).autoSaveByColorCorrect = z;
        }
    }

    public static void setRemasterAutosave(MediaItem mediaItem, boolean z) {
        if (mediaItem != null) {
            DetailsData.of(mediaItem).autoSaveByRemaster = z;
        }
    }

    public static void setRemastering(MediaItem mediaItem, boolean z) {
        if (mediaItem != null) {
            mediaItem.setExtra(ExtrasID.REMASTERING_STATE, Boolean.valueOf(z));
        }
    }

    public static boolean supportCloudPreviewPlay(MediaItem mediaItem) {
        if (Features.isEnabled(Features.SUPPORT_NEW_SAMSUNG_CLOUD)) {
            if ((!mediaItem.isMotionPhoto() || !CloudStateCompat.isNewGalleryAvailable()) && !IFormat.FORMAT_MP4.equalsIgnoreCase(FileUtils.getExtension(mediaItem.getPath()))) {
                return false;
            }
            return true;
        } else if (!PocFeatures.SUPPORT_CLOUD_VIDEO_STREAMING_PREVIEW || mediaItem.isShotMode("slow_motion") || mediaItem.isSingleTakenShot()) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean supportPreviewPlay(MediaItem mediaItem) {
        if (mediaItem == null) {
            return false;
        }
        if ((!mediaItem.isVideo() && !mediaItem.isMotionPhoto()) || mediaItem.isBroken()) {
            return false;
        }
        if (mediaItem.isCloudOnly()) {
            return supportCloudPreviewPlay(mediaItem);
        }
        if (mediaItem.isSharing()) {
            return supportSharedPreviewPlay(mediaItem);
        }
        return true;
    }

    private static boolean supportSharedPreviewPlay(MediaItem mediaItem) {
        if (Features.isEnabled(Features.USE_SHARED_DOWNLOAD_FILE_VERIFY)) {
            return MediaItemMde.isDownloadVideoVerified(mediaItem);
        }
        return false;
    }

    public static MediaItem toMediaItem(final ClusterItem clusterItem) {
        if (clusterItem == null) {
            return null;
        }
        MediaItem mediaItem = clusterItem.getMediaItem();
        if (mediaItem != null) {
            return mediaItem;
        }
        AnonymousClass1 r0 = new MediaItem() {
            public int getCount() {
                return ClusterItem.this.getCount();
            }

            public String getDate() {
                return ClusterItem.this.getDate();
            }

            public String getDateRaw() {
                return ClusterItem.this.getDateRaw();
            }

            public long getDateTaken() {
                return ClusterItem.this.getDateTaken();
            }

            public Pair<Long, Long> getDateTimeRange() {
                return ClusterItem.this.getDateTimeRange();
            }

            public long getFileSize() {
                return ClusterItem.this.getFileSize();
            }

            public String getLatitudeList() {
                return ClusterItem.this.getLatitudeList();
            }

            public String getLocation() {
                return ClusterItem.this.getLocation();
            }

            public String getLongitudeList() {
                return ClusterItem.this.getLongitudeList();
            }

            public String getTitle() {
                return ClusterItem.this.getTitle();
            }

            public String toString() {
                return ClusterItem.this.toString();
            }
        };
        clusterItem.setMediaItem(r0);
        return r0;
    }

    public static void updateFromUri(MediaItem mediaItem) {
        Cursor query;
        Throwable th;
        Long l;
        MediaType mediaType;
        try {
            query = AppResources.getAppContext().getContentResolver().query(Uri.parse(mediaItem.mPath), (String[]) null, (String) null, (String[]) null, (String) null);
            if (query != null) {
                if (query.moveToFirst()) {
                    int columnIndex = query.getColumnIndex(IParameterKey.SIZE);
                    int columnIndex2 = query.getColumnIndex("_display_name");
                    String str = null;
                    if (columnIndex < 0) {
                        l = null;
                    } else {
                        l = Long.valueOf(query.getLong(columnIndex));
                    }
                    if (l != null && l.longValue() > 0) {
                        mediaItem.mFileSize = l.longValue();
                    }
                    if (columnIndex2 >= 0) {
                        str = query.getString(columnIndex2);
                    }
                    if (str != null) {
                        mediaItem.mTitle = str;
                        mediaItem.mDisplayName = str;
                        if (mediaItem.mMimeType == null) {
                            mediaItem.mMimeType = FileType.getMimeType(str);
                        }
                        if (mediaItem.mMediaType != null) {
                            if (mediaItem.mMimeType.startsWith("video")) {
                                mediaType = MediaType.Video;
                            } else {
                                mediaType = MediaType.Image;
                            }
                            mediaItem.mMediaType = mediaType;
                        }
                    }
                }
            }
            if (query != null) {
                query.close();
                return;
            }
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public static void updatePpp(MediaItem mediaItem, MediaItem mediaItem2) {
        if (Features.isEnabled(Features.SUPPORT_PPP_V2) && !Features.isEnabled(Features.SUPPORT_PPP_V3)) {
            mediaItem.setPath(mediaItem2.getPath());
            String nameFromPath = FileUtils.getNameFromPath(mediaItem2.getPath());
            mediaItem.mDisplayName = nameFromPath;
            mediaItem.mTitle = nameFromPath;
            mediaItem.mMediaID = mediaItem2.getMediaId();
            DetailsData.of(mediaItem).getClass();
            if (mediaItem2.getDateModified() > 0) {
                mediaItem.setDateModified(mediaItem2.getDateModified());
            }
            if (mediaItem2.getSefFileType() != Integer.MIN_VALUE) {
                mediaItem.setSefFileType(mediaItem2.getSefFileType(), mediaItem2.getSefFileSubType());
            }
            if (mediaItem2.getWidth() > 0) {
                mediaItem.mWidth = mediaItem2.getWidth();
            }
            if (mediaItem2.getHeight() > 0) {
                mediaItem.mHeight = mediaItem2.getHeight();
            }
            mediaItem.mFileSize = mediaItem2.getFileSize();
            mediaItem.mSourceSize = null;
            mediaItem.setMimeType(mediaItem2.mMimeType);
            if (!TextUtils.isEmpty(mediaItem2.getSefFileTypes())) {
                mediaItem.mSefFileTypes = mediaItem2.getSefFileTypes();
            }
            mediaItem.mDateTaken = mediaItem2.getDateTaken();
            mediaItem.mOrientation = mediaItem2.getOrientation();
            mediaItem.mOrientationTag = mediaItem2.getOrientationTag();
            mediaItem.mLatitude = mediaItem2.getLatitude();
            mediaItem.mLongitude = mediaItem2.getLongitude();
            CharSequence charSequence = TAG;
            Log.d(charSequence, "updatePpp(M) PPP{" + mediaItem.mMediaID + GlobalPostProcInternalPPInterface.SPLIT_REGEX + mediaItem.mFileID + GlobalPostProcInternalPPInterface.SPLIT_REGEX + mediaItem2.getWidth() + "x" + mediaItem2.getHeight() + com.samsung.android.sdk.scs.base.utils.Log.TAG_SEPARATOR + mediaItem2.getOrientation() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + mediaItem2.getMimeType() + NumericEnum.SEP + mediaItem2.getSefFileType() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + mediaItem2.getSefFileSubType() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + mediaItem.mSefFileTypes + NumericEnum.SEP + mediaItem.mFileSize + GlobalPostProcInternalPPInterface.SPLIT_REGEX + mediaItem2.getDateTaken() + GlobalPostProcInternalPPInterface.SPLIT_REGEX + mediaItem2.getDateModified() + "}");
        }
    }

    public static void updateVideoResumePosition(Blackboard blackboard, MediaItem mediaItem) {
        String str;
        if (mediaItem.isVideo() && LaunchIntent.isFromCamera(blackboard)) {
            LaunchIntent launchIntent = (LaunchIntent) blackboard.read("data://launch_intent");
            Uri secUri = ContentUri.getSecUri(mediaItem);
            if (secUri != null) {
                str = secUri.toString();
            } else {
                str = null;
            }
            int resumePos = launchIntent.getResumePos(str);
            if (resumePos != 0) {
                VideoPropData.of(mediaItem).videoResumePos = Integer.valueOf(resumePos);
                launchIntent.clearResumePos();
            }
        }
    }

    public static boolean verifyPppCompletion(MediaItem mediaItem) {
        if (!DeviceConfig.DEBUG_BINARY || !SdkConfig.atLeast(SdkConfig.GED.T)) {
            return true;
        }
        if (mediaItem.isPostProcessing()) {
            Log.i(TAG, "verify ppp failed(2928,2784,2947) " + mediaItem.getSefFileType());
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        boolean verifyPppIsSame = verifyPppIsSame("fileSize", mediaItem.getFileSize(), FileUtils.length(mediaItem.getPath()));
        boolean verifyPppIsSame2 = verifyPppIsSame("dateModified", mediaItem.getDateModified(), FileUtils.getDateModified(mediaItem.getPath()) / 1000);
        if (!verifyPppIsSame || !verifyPppIsSame2) {
            Log.d(TAG, "verify ppp failed(s/m) +" + (System.currentTimeMillis() - currentTimeMillis));
            return false;
        }
        ExifInterface exif = ExifUtils.getExif(mediaItem.getPath());
        if (exif == null || verifyPppIsSame("Orientation", (long) mediaItem.getOrientation(), (long) ExifUtils.getOrientation(exif))) {
            try {
                SefParser unpack = new SefParser(mediaItem.getPath()).unpack();
                int[] dataTypeArray = unpack.getDataTypeArray();
                String[] keyNameArray = unpack.getKeyNameArray();
                if (mediaItem.getSefFileType() == 0 && keyNameArray.length > 0) {
                    HashSet hashSet = new HashSet(Arrays.asList(keyNameArray));
                    if (!hashSet.contains(SefInfo.MOTION_PHOTO_DATA.keyName)) {
                        if (hashSet.contains(SefInfo.DUAL_SHOT_INFO.keyName)) {
                        }
                    }
                    Log.d(TAG, "verify ppp failed", " sef in db=" + mediaItem.getSefFileType(), "SEF in file=" + Arrays.toString(keyNameArray));
                    return false;
                }
                if (dataTypeArray != null && dataTypeArray.length > 0) {
                    Log.d(TAG, "verify ppp SEF dataTypeArray : " + Arrays.toString(dataTypeArray));
                    int length = keyNameArray.length;
                    int i2 = 0;
                    while (i2 < length) {
                        String str = keyNameArray[i2];
                        int dataType = unpack.getDataType(str);
                        boolean isMajorType = SefParser.isMajorType(dataType);
                        if (!isMajorType || mediaItem.getSefFileType() != 0) {
                            Log.d(TAG, "verify ppp SEF keyNameArray : " + str, Integer.valueOf(dataType), Boolean.valueOf(isMajorType));
                            i2++;
                        } else {
                            Log.e(TAG, "verify ppp failed : SEF " + str + "(" + dataType + ") not exist in camera core2 db");
                            return false;
                        }
                    }
                }
                Log.d(TAG, "verify ppp +" + (System.currentTimeMillis() - currentTimeMillis));
                return true;
            } catch (Exception e) {
                Log.d(TAG, "verify ppp failed +" + (System.currentTimeMillis() - currentTimeMillis), e);
                return false;
            }
        } else {
            Log.d(TAG, "verify ppp failed(d/o) +" + (System.currentTimeMillis() - currentTimeMillis));
            return false;
        }
    }

    private static boolean verifyPppIsSame(String str, long j2, long j3) {
        if (j2 == j3) {
            return true;
        }
        Log.i(TAG, C0212a.m("verify ppp failed for ", str, "(db,file) = "), Logger.v(Long.valueOf(j2), Long.valueOf(j3)));
        return false;
    }

    public static String getViewerBitmapKey(MediaItem mediaItem, int i2) {
        return getViewerBitmapKey(mediaItem) + "?position=" + i2;
    }
}
