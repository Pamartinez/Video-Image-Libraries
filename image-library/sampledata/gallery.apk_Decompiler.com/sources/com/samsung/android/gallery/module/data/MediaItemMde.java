package com.samsung.android.gallery.module.data;

import android.graphics.RectF;
import android.net.Uri;
import android.text.TextUtils;
import com.samsung.android.gallery.database.dbtype.ExtrasID;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.module.abstraction.MediaItemBase;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.RectUtils;
import com.samsung.android.sdk.mobileservice.social.share.result.OriginalFile;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MediaItemMde extends MediaItemBase {
    public static long getAlbumExpiry(FileItemInterface fileItemInterface) {
        return MdeData.of(fileItemInterface).albumExpiry;
    }

    public static String getBgmName(FileItemInterface fileItemInterface) {
        return MdeData.of(fileItemInterface).storyBgmName;
    }

    public static long getCreatedTime(FileItemInterface fileItemInterface) {
        return MdeData.of(fileItemInterface).createdTime;
    }

    public static String getCreatorId(FileItemInterface fileItemInterface) {
        String str = MdeData.of(fileItemInterface).creator;
        if (TextUtils.isEmpty(str)) {
            return getOwnerId(fileItemInterface);
        }
        return str;
    }

    public static String getCropRectRatio(FileItemInterface fileItemInterface) {
        ArrayList<RectF> cropRectRatioList;
        if (fileItemInterface == null || (cropRectRatioList = fileItemInterface.getCropRectRatioList()) == null) {
            return null;
        }
        return RectUtils.toString(cropRectRatioList);
    }

    public static String getDownloadMotionPhotoPath(FileItemInterface fileItemInterface) {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(FileUtils.EXTERNAL_STORAGE_DIR);
        String str = File.separator;
        sb2.append(str);
        sb2.append(".sems");
        sb2.append(str);
        sb2.append(fileItemInterface.getDisplayName());
        String sb3 = sb2.toString();
        if (FileUtils.exists(sb3)) {
            return sb3;
        }
        String str2 = "/data/sec/sems/" + fileItemInterface.getDisplayName();
        if (FileUtils.exists(str2)) {
            return str2;
        }
        return "";
    }

    public static String getDownloadVideoPath(FileItemInterface fileItemInterface) {
        return MdeData.of(fileItemInterface).downloadVideoPath;
    }

    public static Uri getDownloadVideoUri(FileItemInterface fileItemInterface) {
        return MdeData.of(fileItemInterface).downloadVideoUri;
    }

    public static Boolean getDownloadVideoVerified(FileItemInterface fileItemInterface) {
        return MdeData.of(fileItemInterface).shareVideoDownloadVerified;
    }

    public static String getFilterName(FileItemInterface fileItemInterface) {
        return MdeData.of(fileItemInterface).storyFilterName;
    }

    public static String getGroupId(FileItemInterface fileItemInterface) {
        String str = MdeData.of(fileItemInterface).groupId;
        if (str == null) {
            return "";
        }
        return str;
    }

    public static String getGroupType(FileItemInterface fileItemInterface) {
        String str = MdeData.of(fileItemInterface).groupType;
        if (str == null) {
            return "UNM1";
        }
        return str;
    }

    public static Uri getHiddenContentUri(FileItemInterface fileItemInterface) {
        return MdeData.of(fileItemInterface).uriInHiddenFolder;
    }

    public static String getHiddenFilePath(FileItemInterface fileItemInterface) {
        return getHiddenFilePath(fileItemInterface, (String) null);
    }

    public static long getInvitationExpiredTime(FileItemInterface fileItemInterface) {
        return MdeData.of(fileItemInterface).expiredTime;
    }

    public static String getInvitationGroupId(FileItemInterface fileItemInterface) {
        return MdeData.of(fileItemInterface).groupId;
    }

    public static long getInvitationRequestedTime(FileItemInterface fileItemInterface) {
        return MdeData.of(fileItemInterface).requestedTime;
    }

    public static String getInvitationRequesterName(FileItemInterface fileItemInterface) {
        return MdeData.of(fileItemInterface).requesterName;
    }

    public static String getInvitationSpaceName(FileItemInterface fileItemInterface) {
        return MdeData.of(fileItemInterface).spaceName;
    }

    public static String getItemId(FileItemInterface fileItemInterface) {
        String str = MdeData.of(fileItemInterface).itemId;
        if (str == null) {
            return "";
        }
        return str;
    }

    public static String getLastModifier(FileItemInterface fileItemInterface) {
        String str = MdeData.of(fileItemInterface).lastModifier;
        if (str == null) {
            return "";
        }
        return str;
    }

    public static String getLastModifierName(FileItemInterface fileItemInterface) {
        String str = MdeData.of(fileItemInterface).lastModifierName;
        if (str == null) {
            return "";
        }
        return str;
    }

    public static int getMemberCount(MediaItem mediaItem) {
        int i2;
        if (mediaItem == null) {
            i2 = 0;
        } else {
            i2 = MdeData.of(mediaItem).groupMemberCount;
        }
        if (i2 > 0) {
            return i2;
        }
        return 1;
    }

    public static long getModifiedTime(FileItemInterface fileItemInterface) {
        return MdeData.of(fileItemInterface).modifiedTime;
    }

    public static long getMyUsage(FileItemInterface fileItemInterface) {
        return MdeData.of(fileItemInterface).myUsage;
    }

    public static String getOwnerId(FileItemInterface fileItemInterface) {
        String str = MdeData.of(fileItemInterface).owner;
        if (str == null) {
            return "";
        }
        return str;
    }

    public static String getSpaceCoverId(FileItemInterface fileItemInterface) {
        String str = MdeData.of(fileItemInterface).spaceCoverMediaId;
        if (str == null) {
            return "";
        }
        return str;
    }

    public static String getSpaceCoverRectRatio(FileItemInterface fileItemInterface) {
        return MediaItemBase.toString(MediaItemBase.getExtra(fileItemInterface, ExtrasID.SPACE_COVER_RECT_RATIO), "");
    }

    public static String getSpaceId(FileItemInterface fileItemInterface) {
        String str = MdeData.of(fileItemInterface).spaceId;
        if (str == null) {
            return "";
        }
        return str;
    }

    public static Map<String, Object> getSpaceMetaData(FileItemInterface fileItemInterface) {
        HashMap hashMap = new HashMap();
        Optional.ofNullable(MdeData.of(fileItemInterface).spaceCoverMediaId).ifPresent(new i(0, hashMap));
        Optional.ofNullable(MdeData.of(fileItemInterface).spaceCoverRectRatio).ifPresent(new i(1, hashMap));
        return hashMap;
    }

    public static String getThemeName(FileItemInterface fileItemInterface) {
        return MdeData.of(fileItemInterface).storyThemeName;
    }

    public static String getTotalSmartCropDeviceRatio(FileItemInterface fileItemInterface) {
        return MediaItemBase.toString(MediaItemBase.getExtra(fileItemInterface, ExtrasID.TOTAL_SMART_CROP_DEVICE_RATIO), (String) null);
    }

    public static String getTotalSmartCropRatio(FileItemInterface fileItemInterface) {
        return MediaItemBase.toString(MediaItemBase.getExtra(fileItemInterface, ExtrasID.TOTAL_SMART_CROP_RATIO), (String) null);
    }

    public static int getUnreadCount(FileItemInterface fileItemInterface) {
        return MdeData.of(fileItemInterface).unreadCount;
    }

    public static long getWebLinkExpiry(FileItemInterface fileItemInterface) {
        return MdeData.of(fileItemInterface).webLinkUrlExpiry;
    }

    public static String getWebLinkUrl(FileItemInterface fileItemInterface) {
        String str = MdeData.of(fileItemInterface).webLinkUrl;
        if (str == null) {
            return "";
        }
        return str;
    }

    public static boolean hasDownloadMotionPhotoPath(FileItemInterface fileItemInterface) {
        return !TextUtils.isEmpty(getDownloadMotionPhotoPath(fileItemInterface));
    }

    public static void initializeVerifyStatus(MediaItem mediaItem) {
        setDownloadVideoUri(mediaItem, Uri.EMPTY);
        setDownloadVideoPath(mediaItem, (String) null);
    }

    public static boolean isDownloadMotionPhotoPath(String str) {
        if (str.startsWith("/data/sec/sems/")) {
            return true;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(FileUtils.EXTERNAL_STORAGE_DIR);
        String str2 = File.separator;
        sb2.append(str2);
        sb2.append(".sems");
        sb2.append(str2);
        if (str.startsWith(sb2.toString())) {
            return true;
        }
        return false;
    }

    public static boolean isDownloadVideoVerified(FileItemInterface fileItemInterface) {
        Boolean downloadVideoVerified = getDownloadVideoVerified(fileItemInterface);
        if (downloadVideoVerified == null || !downloadVideoVerified.booleanValue() || isSharingEditedItemUploading(fileItemInterface)) {
            return false;
        }
        return true;
    }

    public static boolean isDualPhotoWide(FileItemInterface fileItemInterface) {
        return MdeData.of(fileItemInterface).isWideImage;
    }

    public static boolean isInvitation(FileItemInterface fileItemInterface) {
        if (!PreferenceFeatures.OneUi41.SHARING_INVITATION_ON_ALBUMS || fileItemInterface == null || getInvitationRequestedTime(fileItemInterface) == 0) {
            return false;
        }
        return true;
    }

    public static boolean isModified(FileItemInterface fileItemInterface) {
        String str = MdeData.of(fileItemInterface).itemStatus;
        if (str == null) {
            str = "";
        }
        return TextUtils.equals("U", str);
    }

    public static boolean isOwnedByMe(FileItemInterface fileItemInterface) {
        return MdeData.of(fileItemInterface).ownedByMe;
    }

    public static boolean isSharingEditedItemUploading(FileItemInterface fileItemInterface) {
        if (Features.isEnabled(Features.SUPPORT_FAMILY_SHARED_EDIT) && fileItemInterface != null && fileItemInterface.isSharing()) {
            String directoryFromPath = FileUtils.getDirectoryFromPath(fileItemInterface.getPath());
            if (!directoryFromPath.startsWith("/data/sec/sems/") || !directoryFromPath.endsWith("edited/")) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean isUploading(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null || !fileItemInterface.isSharing()) {
            return false;
        }
        return getItemId(fileItemInterface).startsWith("temp_");
    }

    public static boolean isUserCoverItem(FileItemInterface fileItemInterface) {
        return MdeData.of(fileItemInterface).isUserCoverItem;
    }

    public static void setCreatorName(FileItemInterface fileItemInterface, String str) {
        MdeData of2 = MdeData.of(fileItemInterface);
        if (str == null) {
            str = "";
        }
        of2.creatorName = str;
    }

    public static void setDownloadSharedVideoInfo(MediaItem mediaItem, OriginalFile originalFile) {
        if (originalFile != null) {
            setDownloadVideoUri(mediaItem, originalFile.getContentUri());
            setDownloadVideoPath(mediaItem, originalFile.getFilePath());
            setDownloadVideoVerified(mediaItem, Boolean.TRUE);
            return;
        }
        setDownloadVideoUri(mediaItem, (Uri) null);
        setDownloadVideoPath(mediaItem, (String) null);
        setDownloadVideoVerified(mediaItem, (Boolean) null);
    }

    public static void setDownloadVideoPath(FileItemInterface fileItemInterface, String str) {
        MdeData.of(fileItemInterface).downloadVideoPath = str;
    }

    public static void setDownloadVideoUri(FileItemInterface fileItemInterface, Uri uri) {
        MdeData.of(fileItemInterface).downloadVideoUri = uri;
    }

    public static void setDownloadVideoVerified(FileItemInterface fileItemInterface, Boolean bool) {
        MdeData.of(fileItemInterface).shareVideoDownloadVerified = bool;
    }

    public static void setHiddenContentUri(FileItemInterface fileItemInterface, Uri uri) {
        MdeData.of(fileItemInterface).uriInHiddenFolder = uri;
    }

    public static void setHiddenFilePath(FileItemInterface fileItemInterface, String str) {
        MdeData.of(fileItemInterface).pathInHiddenFolder = str;
    }

    public static void setLastModifierName(FileItemInterface fileItemInterface, String str) {
        MdeData of2 = MdeData.of(fileItemInterface);
        if (str == null) {
            str = "";
        }
        of2.lastModifierName = str;
    }

    public static void setUserCoverItem(FileItemInterface fileItemInterface, boolean z) {
        MdeData.of(fileItemInterface).isUserCoverItem = z;
    }

    public static int toUid(String str) {
        return str.hashCode();
    }

    public static String getDownloadVideoPath(FileItemInterface fileItemInterface, String str) {
        String str2 = MdeData.of(fileItemInterface).downloadVideoPath;
        return str2 == null ? str : str2;
    }

    public static String getHiddenFilePath(FileItemInterface fileItemInterface, String str) {
        String str2 = MdeData.of(fileItemInterface).pathInHiddenFolder;
        return str2 == null ? str : str2;
    }
}
