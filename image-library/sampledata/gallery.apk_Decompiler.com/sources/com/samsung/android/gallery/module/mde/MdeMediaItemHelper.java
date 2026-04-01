package com.samsung.android.gallery.module.mde;

import A.a;
import D3.i;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.account.SamsungAccountManager;
import com.samsung.android.gallery.module.data.MdeData;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.extendedformat.SefDualShotFormat;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.MediaHelper;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.motionphoto.utils.v2.MediaDefs;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IParameterKey;
import com.samsung.android.sdk.mobileservice.social.group.BundleKey;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MdeMediaItemHelper {
    public static void applyGroupDataToSpace(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem2 != null) {
            MdeData of2 = MdeData.of(mediaItem);
            of2.groupMemberCount = mediaItem2.getCount();
            of2.groupType = MediaItemMde.getGroupType(mediaItem2);
            of2.albumExpiry = MediaItemMde.getAlbumExpiry(mediaItem2);
            of2.groupName = mediaItem2.getTitle();
        }
    }

    public static Map<String, Object> createInstantMetaDataMap(MediaItem mediaItem, String str, String str2) {
        long j2;
        HashMap hashMap = new HashMap();
        hashMap.put("editedSharedItemPath", str);
        hashMap.put("instantThumbnailPath", str2);
        hashMap.put("width", Integer.valueOf(mediaItem.getWidth()));
        hashMap.put("height", Integer.valueOf(mediaItem.getHeight()));
        hashMap.put("orientation", Integer.valueOf(mediaItem.getOrientation()));
        if (mediaItem.isCloudOnly()) {
            j2 = mediaItem.getCloudOriginalSize();
        } else {
            j2 = mediaItem.getFileSize();
        }
        hashMap.put("original_size", Long.valueOf(j2));
        hashMap.put("sef_file_type", Integer.valueOf(mediaItem.getSefFileType()));
        if (mediaItem.isVideo()) {
            hashMap.put("recording_mode", Integer.valueOf(mediaItem.getRecordingMode()));
            hashMap.put("duration", Integer.valueOf(mediaItem.getFileDuration()));
        }
        if (Features.isEnabled(Features.SUPPORT_SEC_MP_ORIENTATION_TAG)) {
            hashMap.put("orientation_tag", Integer.valueOf(mediaItem.getOrientationTag()));
        }
        return hashMap;
    }

    public static Map<String, Object> createMetaDataMap(FileItemInterface fileItemInterface, String[] strArr) {
        long j2;
        long j3;
        HashMap hashMap = new HashMap();
        if (fileItemInterface.getDateTaken() > 0) {
            j2 = fileItemInterface.getDateTaken();
        } else {
            j2 = System.currentTimeMillis();
        }
        hashMap.put(IParameterKey.DATE_TAKEN, Long.valueOf(j2));
        hashMap.put("width", Integer.valueOf(fileItemInterface.getWidth()));
        hashMap.put("height", Integer.valueOf(fileItemInterface.getHeight()));
        hashMap.put("orientation", Integer.valueOf(fileItemInterface.getOrientation()));
        if (fileItemInterface.isCloudOnly()) {
            j3 = fileItemInterface.getCloudOriginalSize();
        } else {
            j3 = fileItemInterface.getFileSize();
        }
        hashMap.put("original_size", Long.valueOf(j3));
        hashMap.put("sef_file_type", Integer.valueOf(fileItemInterface.getSefFileType()));
        hashMap.put("sef_file_sub_type", Integer.valueOf(fileItemInterface.getSefFileSubType()));
        hashMap.put(BundleKey.GROUP_TYPE, Integer.valueOf(fileItemInterface.getGroupType()));
        String cropRectRatio = MediaItemMde.getCropRectRatio(fileItemInterface);
        if (cropRectRatio != null) {
            hashMap.put("cropRectRatioListString", cropRectRatio);
        }
        if (fileItemInterface.getSefFileType() == 2752 || fileItemInterface.getSefFileType() == 2768) {
            hashMap.put("isWideImage", Boolean.valueOf(SefDualShotFormat.isWideImage(fileItemInterface.getPath())));
        }
        if (fileItemInterface.isVideo()) {
            hashMap.put("recording_mode", Integer.valueOf(fileItemInterface.getRecordingMode()));
            hashMap.put("is_360_video", Boolean.valueOf(fileItemInterface.is360Video()));
            hashMap.put("duration", Integer.valueOf(fileItemInterface.getFileDuration()));
            hashMap.put("latitude", Double.valueOf(fileItemInterface.getLatitude()));
            hashMap.put("longitude", Double.valueOf(fileItemInterface.getLongitude()));
            hashMap.put("video_color_format", Integer.valueOf(fileItemInterface.getVideoColorFormat()));
        }
        if (MdeAlbumHelper.isCloudExistedItem(fileItemInterface) && MdeAlbumHelper.isCloudOnlyItem(fileItemInterface)) {
            hashMap.put("original_size", Long.valueOf(fileItemInterface.getCloudOriginalSize()));
        }
        if (Features.isEnabled(Features.SUPPORT_SEC_MP_ORIENTATION_TAG)) {
            hashMap.put("orientation_tag", Integer.valueOf(fileItemInterface.getOrientationTag()));
        }
        if (PreferenceFeatures.OneUi6x.SUPPORT_SHARE_STORY) {
            hashMap.put("bucketId", Integer.valueOf(fileItemInterface.getBucketID()));
            if (strArr != null && strArr.length > 1) {
                hashMap.put("totalSmartCropRatio", strArr[0]);
                hashMap.put("totalSmartCropDeviceRatio", strArr[1]);
            }
        }
        return hashMap;
    }

    public static String getCreatorName(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null) {
            return "";
        }
        if (isCreatedByMe(fileItemInterface)) {
            return AppResources.getString(R$string.f2914me);
        }
        return MdeData.of(fileItemInterface).creatorName;
    }

    public static Bitmap getEditedImageBitmap(String str, MediaItem mediaItem) {
        return BitmapUtils.getDecodedBitmap(str, mediaItem.getOrientation(), MediaDefs.Meta.XMP.XMP_MIX_RESERVED_SIZE, mediaItem.isQuramDecodable());
    }

    public static Bitmap getEditedVideoBitmap(String str, MediaItem mediaItem, MediaHelper.VideoInfo videoInfo) {
        long j2;
        MediaMetadataRetriever mediaMetadataRetriever;
        int i2;
        if (mediaItem.getFileDuration() < 15000) {
            j2 = 0;
        } else {
            j2 = 15000000;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            try {
                mediaMetadataRetriever = new MediaMetadataRetriever();
                mediaMetadataRetriever.setDataSource(fileInputStream.getFD());
                if (videoInfo != null) {
                    if (!mediaItem.is4K()) {
                        if (videoInfo.corrected) {
                        }
                    }
                    int i7 = videoInfo.width;
                    if (i7 > 0 && (i2 = videoInfo.height) > 0) {
                        float max = 1280.0f / ((float) Math.max(i7, i2));
                        if (max < 1.0f) {
                            SeApiCompat.setVideoSize(mediaMetadataRetriever, Math.round(((float) videoInfo.width) * max), Math.round(((float) videoInfo.height) * max));
                        }
                    }
                }
                Bitmap resizeBitmap = resizeBitmap(SeApiCompat.getVideoFrameAtTime(mediaMetadataRetriever, j2, 2), 720);
                mediaMetadataRetriever.close();
                fileInputStream.close();
                return resizeBitmap;
            } catch (Throwable th) {
                fileInputStream.close();
                throw th;
            }
            throw th;
        } catch (Error | Exception e) {
            a.z(e, new StringBuilder("getEditedVideoBitmap failed e="), "MdeMediaItemHelper");
            return null;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
    }

    public static String getLastModifierName(FileItemInterface fileItemInterface) {
        String str;
        if (fileItemInterface == null) {
            str = "";
        } else if (isMe(MediaItemMde.getLastModifier(fileItemInterface))) {
            str = AppResources.getString(R$string.f2914me);
        } else {
            str = MediaItemMde.getLastModifierName(fileItemInterface);
        }
        if (TextUtils.isEmpty(str)) {
            return AppResources.getString(R$string.former_group_member);
        }
        return str;
    }

    private static boolean isCreatedByMe(FileItemInterface fileItemInterface) {
        if (Features.isEnabled(Features.SUPPORT_CREATOR)) {
            return isMe(MediaItemMde.getCreatorId(fileItemInterface));
        }
        return MediaItemMde.isOwnedByMe(fileItemInterface);
    }

    private static boolean isMe(String str) {
        return TextUtils.equals(SamsungAccountManager.getInstance().getGUID(new i(9)), str);
    }

    public static boolean isSameOwner(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem == null || mediaItem2 == null || MediaItemMde.isOwnedByMe(mediaItem) != MediaItemMde.isOwnedByMe(mediaItem2)) {
            return false;
        }
        return true;
    }

    public static boolean isSameSpaceWebLink(MediaItem mediaItem, MediaItem mediaItem2) {
        if (mediaItem == null || mediaItem2 == null || !TextUtils.equals(MediaItemMde.getWebLinkUrl(mediaItem), MediaItemMde.getWebLinkUrl(mediaItem2)) || MediaItemMde.getWebLinkExpiry(mediaItem) != MediaItemMde.getWebLinkExpiry(mediaItem2)) {
            return false;
        }
        return true;
    }

    private static Bitmap resizeBitmap(Bitmap bitmap, int i2) {
        if (bitmap == null) {
            return bitmap;
        }
        float max = ((float) i2) / ((float) Math.max(bitmap.getWidth(), bitmap.getHeight()));
        if (max < 0.9f) {
            return BitmapUtils.resize(bitmap, max);
        }
        return bitmap;
    }
}
