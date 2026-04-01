package com.samsung.android.gallery.module.mde;

import A4.C0375j;
import A8.C0545b;
import D3.i;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.MediaType;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.data.ContentUri;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.mdebase.constants.MdeResultCode;
import com.samsung.android.gallery.module.mdebase.db.MdeDatabase;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.samsung.android.gallery.support.utils.Utils;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MdeAlbumHelper {
    public static boolean sSemsPermissionDenied = false;

    /* renamed from: com.samsung.android.gallery.module.mde.MdeAlbumHelper$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$database$dbtype$MediaType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.samsung.android.gallery.database.dbtype.MediaType[] r0 = com.samsung.android.gallery.database.dbtype.MediaType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$database$dbtype$MediaType = r0
                com.samsung.android.gallery.database.dbtype.MediaType r1 = com.samsung.android.gallery.database.dbtype.MediaType.Image     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$database$dbtype$MediaType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.database.dbtype.MediaType r1 = com.samsung.android.gallery.database.dbtype.MediaType.Video     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.mde.MdeAlbumHelper.AnonymousClass1.<clinit>():void");
        }
    }

    public static String buildCreatureSelectForEmptyFamilyAlbumLocation(int i2, String str, String str2, boolean z) {
        String str3;
        if (Features.isEnabled(Features.SUPPORT_PET_ON_AUTO_ALBUM)) {
            str3 = "location://search/fileList/Category/CreatureSelect";
        } else {
            str3 = "location://search/fileList/Category/PeopleSelect";
        }
        return new UriBuilder(str3).appendArg("from_suggestion_pictures", z).appendArg("id", i2).appendArg("groupId", str).appendArg(BundleKey.SPACE_ID, str2).build();
    }

    public static String buildFamilyAlbumWelcomePageLocation(boolean z, boolean z3) {
        return new UriBuilder("location://family/shared/welcome").appendArg("from_samsung_account", z).appendArg("from_picker", z3).build();
    }

    public static String buildSharingPicturesSettingLocation(String str, int i2, FileItemInterface fileItemInterface) {
        return new UriBuilder("location://sharing/albums/fileList/SharingAlbumSetting").appendArg("owner", MediaItemMde.isOwnedByMe(fileItemInterface)).appendArg("space_name", fileItemInterface.getTitle()).appendArg("groupId", MediaItemMde.getGroupId(fileItemInterface)).appendArg(BundleKey.SPACE_ID, MediaItemMde.getSpaceId(fileItemInterface)).appendArg("familyAlbumId", i2).appendArg("space_weblink_url", MediaItemMde.getWebLinkUrl(fileItemInterface)).appendArg("space_weblink_expiry", MediaItemMde.getWebLinkExpiry(fileItemInterface)).appendArg("blackboard_name", str).build();
    }

    public static String buildSharingPicturesSuggestionLocation(int i2, String str, String str2, boolean z) {
        return new UriBuilder("location://family/shared/suggested/fileList").appendArg("groupId", str).appendArg(BundleKey.SPACE_ID, str2).appendArg("id", i2).appendArg("support_normal_mode", z).build();
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x000d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean checkCoverRatio(java.lang.String r2) {
        /*
            java.util.StringTokenizer r0 = new java.util.StringTokenizer
            java.lang.String r1 = ","
            r0.<init>(r2, r1)
        L_0x0007:
            boolean r2 = r0.hasMoreTokens()
            if (r2 == 0) goto L_0x0022
            java.lang.String r2 = r0.nextToken()
            float r2 = java.lang.Float.parseFloat(r2)
            r1 = 1065353216(0x3f800000, float:1.0)
            int r1 = (r2 > r1 ? 1 : (r2 == r1 ? 0 : -1))
            if (r1 > 0) goto L_0x0020
            r1 = 0
            int r2 = (r2 > r1 ? 1 : (r2 == r1 ? 0 : -1))
            if (r2 >= 0) goto L_0x0007
        L_0x0020:
            r2 = 0
            return r2
        L_0x0022:
            r2 = 1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.mde.MdeAlbumHelper.checkCoverRatio(java.lang.String):boolean");
    }

    public static boolean checkGroupExist(String str) {
        return new MdeDatabase().isGroupAvailable(str);
    }

    public static boolean checkSpaceExist(String str) {
        return new MdeDatabase().isSpaceAvailable(str);
    }

    public static ArrayList<Uri> convertMediaItemsToUris(MediaItem[] mediaItemArr) {
        ArrayList<Uri> arrayList = new ArrayList<>();
        for (MediaItem mediaItem : mediaItemArr) {
            if (!mediaItem.isUriItem()) {
                arrayList.add(ContentUri.getUri(mediaItem));
            } else if (mediaItem.getCustomUriProvider() != null) {
                arrayList.add(ContentUri.getUri(mediaItem));
            } else {
                arrayList.add(Uri.parse(mediaItem.getPath()));
            }
        }
        return arrayList;
    }

    public static String getExpiredTimeText(long j2) {
        long timeInMillis = (j2 * 1000) + Calendar.getInstance().getTimeInMillis();
        return AppResources.getString(R$string.shared_album_invitation_expries) + " " + new SimpleDateFormat().format(Long.valueOf(timeInMillis));
    }

    public static MediaType getMediaTypeOfItems(List<FileItemInterface> list) {
        MediaType valueOf2 = MediaType.valueOf2(list.stream().filter(new C0375j(17)).map(new i(8)).distinct().mapToInt(new C0545b(5)).sum());
        if (list.size() > 1) {
            int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$database$dbtype$MediaType[valueOf2.ordinal()];
            if (i2 == 1) {
                return MediaType.Images;
            }
            if (i2 == 2) {
                return MediaType.Videos;
            }
        }
        return valueOf2;
    }

    public static String getRequesterNameText(String str) {
        if (TextUtils.isEmpty(str)) {
            str = AppResources.getString(R$string.name_unknown);
        }
        return AppResources.getString(R$string.whose_invitation, str);
    }

    public static String getSharedAlbumIdByGroupId(String str) {
        return new MdeDatabase().getSpaceIdByGroupId(str);
    }

    public static String getSharedAlbumPeriod(String str) {
        Pair<Long, Long> periodInSpace = new MdeDatabase().getPeriodInSpace(str);
        return TimeUtil.getEventDatePeriod(((Long) periodInSpace.first).longValue(), ((Long) periodInSpace.second).longValue(), 50);
    }

    public static void handleMdeFailResultCode(Context context, int i2) {
        handleMdeFailResultCode(context, i2, "");
    }

    public static boolean hasNewBadge() {
        if (PreferenceCache.SharedLastUpdateTime.getLong() > PreferenceCache.SharedLastVisitTime.getLong()) {
            return true;
        }
        return false;
    }

    public static boolean isCloudExistedItem(FileItemInterface fileItemInterface) {
        StorageType storageType = fileItemInterface.getStorageType();
        if (CloudStateCompat.isEnabled()) {
            if (storageType == StorageType.Cloud) {
                return true;
            }
            return false;
        } else if (storageType == StorageType.Cloud || storageType == StorageType.LocalCloud) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isCloudOnlyItem(FileItemInterface fileItemInterface) {
        if (fileItemInterface.getStorageType() == StorageType.Cloud) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getMediaTypeOfItems$0(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null || fileItemInterface.getMediaType() == MediaType.Unsupported) {
            return false;
        }
        return true;
    }

    public static void updateLastUpdatedTime() {
        PreferenceCache.SharedLastUpdateTime.setLong(System.currentTimeMillis());
    }

    public static void updateLastVisitedTime() {
        PreferenceCache.SharedLastVisitTime.setLong(System.currentTimeMillis());
        Blackboard.publishGlobal("data://shared_albums_updated", Boolean.FALSE);
    }

    public static void handleMdeFailResultCode(Context context, int i2, int i7) {
        handleMdeFailResultCode(context, i2, context.getString(i7));
    }

    public static void handleMdeFailResultCode(Context context, int i2, String str) {
        Log.sh("MdeAlbumHelper", "handleMdeFailResultCode" + Logger.v(Integer.valueOf(i2)));
        if (MdeResultCode.isGdprError(i2)) {
            context.startActivity(MdeSocialHelper.getIntentForGdprErrorPopup());
        } else if (!MdeResultCode.isQuotaExceeded(i2) && !TextUtils.isEmpty(str)) {
            Utils.showShortToast(context, str);
        }
    }
}
