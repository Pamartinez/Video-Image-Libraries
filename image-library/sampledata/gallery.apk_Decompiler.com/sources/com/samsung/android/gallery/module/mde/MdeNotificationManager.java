package com.samsung.android.gallery.module.mde;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
import android.os.PowerManager;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.samsung.android.gallery.module.R$color;
import com.samsung.android.gallery.module.R$dimen;
import com.samsung.android.gallery.module.R$drawable;
import com.samsung.android.gallery.module.R$string;
import com.samsung.android.gallery.module.data.MediaItemMde;
import com.samsung.android.gallery.module.graphics.BitmapOptions;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.module.graphics.ImageDecoder;
import com.samsung.android.gallery.module.mdebase.utils.MdeMetadataParser;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.AndroidCompat;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PendingIntentCompat;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.ocr.MOCRLang;
import com.samsung.android.sdk.mobileservice.social.share.BundleKey;
import com.samsung.android.sum.core.Def;
import java.lang.ref.WeakReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MdeNotificationManager {
    private static volatile MdeNotificationManager sInstance;
    private final WeakReference<Context> mContext;
    private final NotificationManager mNotificationManager;

    /* renamed from: com.samsung.android.gallery.module.mde.MdeNotificationManager$1  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$samsung$android$gallery$module$mde$MdeNotificationManager$SharedAlbumPushType;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.samsung.android.gallery.module.mde.MdeNotificationManager$SharedAlbumPushType[] r0 = com.samsung.android.gallery.module.mde.MdeNotificationManager.SharedAlbumPushType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$samsung$android$gallery$module$mde$MdeNotificationManager$SharedAlbumPushType = r0
                com.samsung.android.gallery.module.mde.MdeNotificationManager$SharedAlbumPushType r1 = com.samsung.android.gallery.module.mde.MdeNotificationManager.SharedAlbumPushType.NEW_ALBUM     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$mde$MdeNotificationManager$SharedAlbumPushType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.samsung.android.gallery.module.mde.MdeNotificationManager$SharedAlbumPushType r1 = com.samsung.android.gallery.module.mde.MdeNotificationManager.SharedAlbumPushType.NEW_POST     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$mde$MdeNotificationManager$SharedAlbumPushType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.samsung.android.gallery.module.mde.MdeNotificationManager$SharedAlbumPushType r1 = com.samsung.android.gallery.module.mde.MdeNotificationManager.SharedAlbumPushType.MEMBER_JOINED     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$mde$MdeNotificationManager$SharedAlbumPushType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.samsung.android.gallery.module.mde.MdeNotificationManager$SharedAlbumPushType r1 = com.samsung.android.gallery.module.mde.MdeNotificationManager.SharedAlbumPushType.MEMBER_LEFT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$samsung$android$gallery$module$mde$MdeNotificationManager$SharedAlbumPushType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.samsung.android.gallery.module.mde.MdeNotificationManager$SharedAlbumPushType r1 = com.samsung.android.gallery.module.mde.MdeNotificationManager.SharedAlbumPushType.FORCE_MEMBER_DELETE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.samsung.android.gallery.module.mde.MdeNotificationManager.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum SharedAlbumPushType {
        NEW_ALBUM,
        NEW_POST,
        MEMBER_LEFT,
        FORCE_MEMBER_DELETE,
        MEMBER_JOINED
    }

    private MdeNotificationManager(Context context) {
        this.mContext = new WeakReference<>(context);
        this.mNotificationManager = (NotificationManager) context.getSystemService("notification");
        createNotificationChannel();
    }

    private boolean checkNonNull(Context context, String str) {
        if (context == null) {
            Log.she("MdeNotificationManager", "Context is null");
            return true;
        } else if (str != null) {
            return false;
        } else {
            Log.she("MdeNotificationManager", "Cannot get group id");
            return true;
        }
    }

    private Intent createAcceptIntent(String str) {
        Intent intent = new Intent("com.samsung.android.intent.action.SHARED_INVITE_ACCEPTED");
        intent.putExtra("refresh_notification", true);
        intent.addFlags(268435456);
        intent.putExtra(BundleKey.GROUP_ID, str);
        intent.setPackage("com.sec.android.gallery3d");
        return intent;
    }

    private Intent createDeclineIntent(String str) {
        Intent intent = new Intent("com.samsung.android.intent.action.SHARED_INVITE_DECLINED");
        intent.putExtra("refresh_notification", true);
        intent.putExtra(BundleKey.GROUP_ID, str);
        intent.setPackage("com.sec.android.gallery3d");
        return intent;
    }

    private Intent createDeleteIntent() {
        Intent intent = new Intent("com.samsung.android.intent.action.REFRESH_GALLERY_CHANNEL_NOTIFICATION");
        intent.putExtra("refresh_notification", true);
        return intent;
    }

    private PendingIntent createDeletePendingIntent(Context context) {
        return createPendingBroadcastIntent(context, createDeleteIntent());
    }

    private void createGroupSummaryNotification(PendingIntent pendingIntent) {
        int i2 = 0;
        for (StatusBarNotification notification : this.mNotificationManager.getActiveNotifications()) {
            Notification notification2 = notification.getNotification();
            if (notification2 != null && "gallery_group_key".equals(notification2.getGroup())) {
                i2++;
            }
        }
        Log.sh("MdeNotificationManager", "createGroupSummaryNotification notificationCount = " + i2);
        if (i2 == 0) {
            Log.sh("MdeNotificationManager", "createGroupSummaryNotification create Noti Group Summary");
            NotificationCompat.Builder autoCancel = new NotificationCompat.Builder(getContext()).setGroup("gallery_group_key").setGroupSummary(true).setPriority(0).setVibrate(new long[]{0, 0, 0}).setAutoCancel(true);
            autoCancel.setColor(getContext().getColor(R$color.quick_panel_notification_color));
            autoCancel.setSmallIcon(R$drawable.stat_notify_gallery);
            autoCancel.setContentIntent(pendingIntent);
            autoCancel.setChannelId("samsung_gallery_channel_shared_album");
            this.mNotificationManager.notify("gallery_summary_noti", 1, autoCancel.build());
        }
    }

    private NotificationCompat.Builder createNotificationBuilder(Context context, String str, String str2, PendingIntent pendingIntent) {
        return new NotificationCompat.Builder(context).setContentTitle(str).setContentText(str2).setGroup("gallery_group_key").setPriority(2).setVibrate(new long[]{1, 1, 1}).setSound(RingtoneManager.getDefaultUri(2)).setAutoCancel(true).setSmallIcon(R$drawable.stat_notify_gallery).setColor(context.getColor(R$color.quick_panel_notification_color)).setContentIntent(pendingIntent).setDeleteIntent(createDeletePendingIntent(context)).setChannelId("samsung_gallery_channel_shared_album");
    }

    private void createNotificationChannel() {
        NotificationChannel notificationChannel = new NotificationChannel("samsung_gallery_channel_shared_album", this.mContext.get().getResources().getString(R$string.shared_album), 4);
        notificationChannel.setShowBadge(false);
        this.mNotificationManager.createNotificationChannel(notificationChannel);
    }

    private Intent createOpenInvitationIntent() {
        Intent intent = new Intent("android.intent.action.VIEW");
        if (PreferenceFeatures.OneUi41.SHARING_INVITATION_ON_ALBUMS) {
            intent.putExtra("start-shared-view", true);
        } else {
            intent.putExtra("refresh_notification", true);
            intent.putExtra("start-sharing-invitations-view", true);
        }
        intent.addFlags(268435456);
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.external.GalleryExternalActivity");
        return intent;
    }

    private Intent createOpenOnePersonAlbum(String str, String str2) {
        Intent intent = new Intent("com.samsung.android.mobileservice.ACTION_OPEN_ONE_PERSON_ALBUM");
        intent.putExtra(BundleKey.SPACE_ID, str);
        intent.putExtra(BundleKey.GROUP_ID, str2);
        intent.setPackage("com.sec.android.gallery3d");
        return intent;
    }

    private Intent createOpenSharedAlbumGroupDetailViewIntent(String str, String str2) {
        Intent intent = new Intent("com.samsung.android.intent.action.ACTION_SHARED_GROUP_DETAIL");
        intent.putExtra("refresh_notification", true);
        intent.putExtra(BundleKey.GROUP_ID, str);
        intent.putExtra("space_name", str2);
        intent.setPackage("com.sec.android.gallery3d");
        return intent;
    }

    private Intent createOpenSharedViewIntent() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.putExtra("refresh_notification", true);
        intent.putExtra("start-shared-view", true);
        intent.addFlags(268435456);
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.external.GalleryExternalActivity");
        return intent;
    }

    private PendingIntent createPendingActivityIntent(Context context, Intent intent) {
        return createPendingActivityIntent(context, 0, intent);
    }

    private PendingIntent createPendingBroadcastIntent(Context context, Intent intent) {
        return createPendingBroadcastIntent(context, 0, intent);
    }

    private Intent createRemoveNotificationIntent(String str) {
        Intent intent = new Intent("com.samsung.android.mobileservice.ACTION_REMOVE_NOTIFICATION");
        intent.putExtra(BundleKey.GROUP_ID, str);
        intent.setPackage("com.sec.android.gallery3d");
        return intent;
    }

    private Context getContext() {
        return this.mContext.get();
    }

    private String[] getDeleteNotificationString(Context context, String str, String str2) {
        int i2;
        if (MdeGroupHelper.isSAFamilyGroup(str)) {
            i2 = R$string.shared_family_album_delete_success;
        } else {
            i2 = R$string.album_deleted_push_description_title;
        }
        return new String[]{context.getString(i2), SeApiCompat.naturalizeText(context.getString(R$string.shared_album_deleted_by_its_creator, new Object[]{getNonNullText(context, str2, "groupName")}))};
    }

    public static MdeNotificationManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (MdeNotificationManager.class) {
                try {
                    if (sInstance == null) {
                        sInstance = new MdeNotificationManager(context.getApplicationContext());
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    private Bitmap getLargeIconFromFilePath(String str) {
        float dimensionPixelSize = ((float) MOCRLang.GURMUKHI) / ((float) getContext().getResources().getDimensionPixelSize(R$dimen.sharing_noti_layout_item_image_size));
        BitmapOptions bitmapOptions = new BitmapOptions();
        bitmapOptions.inSampleSize = BitmapUtils.computeSampleSizeLarger(dimensionPixelSize);
        return ImageDecoder.decodeFile(str, bitmapOptions);
    }

    private String[] getMemberNotificationString(Context context, SharedAlbumPushType sharedAlbumPushType, String str, String str2) {
        String[] strArr = new String[2];
        int i2 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$module$mde$MdeNotificationManager$SharedAlbumPushType[sharedAlbumPushType.ordinal()];
        if (i2 == 3) {
            strArr[0] = context.getString(R$string.member_joined_push_description_title);
            strArr[1] = context.getString(R$string.member_joined_push_description_body, new Object[]{getNonNullText(context, str, "joinedMemberName"), getNonNullText(context, str2, "groupName")});
            return strArr;
        } else if (i2 == 4) {
            strArr[0] = context.getString(R$string.member_left_push_description_title);
            strArr[1] = context.getString(R$string.member_left_push_description_body, new Object[]{getNonNullText(context, str, "leftMemberName"), getNonNullText(context, str2, "groupName")});
            return strArr;
        } else if (i2 != 5) {
            return strArr;
        } else {
            strArr[0] = context.getString(R$string.force_member_deleted_push_description_title);
            strArr[1] = context.getString(R$string.force_member_deleted_push_description_body, new Object[]{getNonNullText(context, str, "leftMemberName"), getNonNullText(context, str2, "groupName")});
            return strArr;
        }
    }

    private String getNonNullText(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        Log.she("MdeNotificationManager", str2 + " = null or empty");
        return context.getResources().getString(R$string.unknown);
    }

    private NotificationCompat.Builder initDefaultNotification(Context context, String[] strArr, PendingIntent pendingIntent, PendingIntent pendingIntent2) {
        wakeUpForNotification();
        createGroupSummaryNotification(pendingIntent);
        return createNotificationBuilder(context, strArr[0], strArr[1], pendingIntent2);
    }

    private boolean isGroupNotification(Notification notification) {
        if (notification == null || !"gallery_group_key".equals(notification.getGroup())) {
            return false;
        }
        return true;
    }

    private boolean isSummaryNotification(StatusBarNotification statusBarNotification) {
        if (statusBarNotification == null || !"gallery_summary_noti".equals(statusBarNotification.getTag())) {
            return false;
        }
        return true;
    }

    private void wakeUpForNotification() {
        PowerManager powerManager = (PowerManager) this.mContext.get().getSystemService("power");
        if (powerManager != null) {
            powerManager.newWakeLock(268435482, "wakeUpForNotification_Gallery").acquire(Def.MEDIAFILTER_MESSAGE_TIMEOUT_MILLIS);
        }
    }

    public void cancel(int i2) {
        this.mNotificationManager.cancel(i2);
        if (MdeSharingService.getInstance().computeShareServiceAvailable()) {
            refreshNotification();
        }
    }

    public void cancelAll() {
        this.mNotificationManager.cancelAll();
    }

    public Intent createOpenSharedAlbumDetailViewIntent(String str, String str2) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.putExtra("refresh_notification", true);
        intent.putExtra("start-shared-detail-view", true);
        intent.putExtra("key-shared-album-space-id", str);
        intent.putExtra("key-shared-album-group-id", str2);
        intent.addFlags(268435456);
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.external.GalleryExternalActivity");
        return intent;
    }

    public Intent createOpenSharingStorageUseIntent() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.putExtra("refresh_notification", true);
        intent.putExtra("start-sharing-storage-use-view", true);
        intent.addFlags(268435456);
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.activity.external.GalleryExternalActivity");
        return intent;
    }

    public String[] getNewInfoNotificationString(Context context, SharedAlbumPushType sharedAlbumPushType, String str, String str2, String str3, int i2) {
        boolean z;
        String str4;
        String str5;
        String str6;
        String str7;
        int i7;
        String[] strArr = new String[2];
        if (MdeGroupHelper.isLocalGroup(str) || MdeGroupHelper.isSAFamilyGroup(str)) {
            z = true;
        } else {
            z = false;
        }
        int i8 = AnonymousClass1.$SwitchMap$com$samsung$android$gallery$module$mde$MdeNotificationManager$SharedAlbumPushType[sharedAlbumPushType.ordinal()];
        if (i8 == 1) {
            if (z) {
                str4 = context.getString(R$string.new_local_group_album_push_title);
            } else {
                str4 = context.getString(R$string.sharing_invitations_push_description_title, new Object[]{str2});
            }
            strArr[0] = str4;
            if (z) {
                str5 = context.getString(R$string.new_local_group_album_push_description, new Object[]{str2, str3});
            } else {
                str5 = context.getString(R$string.new_album_push_description, new Object[]{str2});
            }
            strArr[1] = str5;
            return strArr;
        } else if (i8 != 2) {
            return strArr;
        } else {
            if (z) {
                if (i2 > 1) {
                    i7 = R$string.new_posts_push_title;
                } else {
                    i7 = R$string.new_post_push_title;
                }
                str6 = context.getString(i7);
            } else {
                str6 = context.getString(R$string.album_updated);
            }
            strArr[0] = str6;
            if (z) {
                if (i2 > 1) {
                    str7 = context.getString(R$string.new_local_group_posts_push_description, new Object[]{Integer.valueOf(i2), str3});
                } else {
                    str7 = context.getString(R$string.new_local_group_post_push_description, new Object[]{str3});
                }
                strArr[1] = str7;
                return strArr;
            }
            strArr[1] = context.getString(R$string.new_post_push_description, new Object[]{str2, str3});
            return strArr;
        }
    }

    public void notifyGroupDelegateAuthorityInfo(String str, String str2, String str3) {
        Log.sh("MdeNotificationManager", "notifyGroupDelegateAuthorityInfo");
        Context context = getContext();
        if (!checkNonNull(context, str)) {
            String sharedAlbumIdByGroupId = MdeAlbumHelper.getSharedAlbumIdByGroupId(str);
            if (TextUtils.isEmpty(sharedAlbumIdByGroupId)) {
                Log.she("MdeNotificationManager", "albumId is null");
                return;
            }
            int uid = MediaItemMde.toUid(sharedAlbumIdByGroupId);
            Intent createOpenSharedAlbumDetailViewIntent = createOpenSharedAlbumDetailViewIntent(sharedAlbumIdByGroupId, str);
            PendingIntent createPendingActivityIntent = createPendingActivityIntent(context, createOpenSharedAlbumDetailViewIntent);
            PendingIntent createPendingActivityIntent2 = createPendingActivityIntent(context, uid, createOpenSharedAlbumDetailViewIntent);
            String string = context.getString(R$string.group_delegate_authority_push_title);
            String string2 = context.getString(R$string.group_delegate_authority_push_body, new Object[]{str3, str2});
            NotificationCompat.Builder initDefaultNotification = initDefaultNotification(context, new String[]{string, string2}, createPendingActivityIntent, createPendingActivityIntent2);
            initDefaultNotification.setStyle(new NotificationCompat.BigTextStyle().bigText(string2));
            this.mNotificationManager.notify(uid, initDefaultNotification.build());
        }
    }

    public void notifyMemberUpdates(SharedAlbumPushType sharedAlbumPushType, String str, String str2, String str3) {
        PendingIntent pendingIntent;
        PendingIntent pendingIntent2;
        Log.sh("MdeNotificationManager", "notifyMemberUpdates");
        Context context = getContext();
        if (!checkNonNull(context, str)) {
            int uid = MediaItemMde.toUid(str);
            if (MdeAlbumHelper.checkGroupExist(str)) {
                Intent createOpenSharedAlbumGroupDetailViewIntent = createOpenSharedAlbumGroupDetailViewIntent(str, str2);
                pendingIntent2 = createPendingBroadcastIntent(context, createOpenSharedAlbumGroupDetailViewIntent);
                pendingIntent = createPendingBroadcastIntent(context, uid, createOpenSharedAlbumGroupDetailViewIntent);
            } else {
                Intent createOpenSharedViewIntent = createOpenSharedViewIntent();
                pendingIntent2 = createPendingActivityIntent(context, createOpenSharedViewIntent);
                pendingIntent = createPendingActivityIntent(context, uid, createOpenSharedViewIntent);
            }
            this.mNotificationManager.notify(uid, initDefaultNotification(context, getMemberNotificationString(context, sharedAlbumPushType, str3, str2), pendingIntent2, pendingIntent).build());
        }
    }

    public void notifyOnePersonAlbumDeleteNotice(String str, String str2, long j2) {
        Log.sh("MdeNotificationManager", "notifyOnePersonAlbumDeleteNotice");
        Context context = getContext();
        if (!checkNonNull(context, str)) {
            int uid = MediaItemMde.toUid(str);
            String string = context.getString(R$string.one_person_album_delete_push_title, new Object[]{str2});
            String string2 = context.getString(R$string.one_person_album_delete_push_description_body, new Object[]{TimeUtil.getLocalizedDateTime(j2)});
            PendingIntent createPendingBroadcastIntent = createPendingBroadcastIntent(context, uid, createOpenOnePersonAlbum(MdeAlbumHelper.getSharedAlbumIdByGroupId(str), str));
            NotificationCompat.Builder initDefaultNotification = initDefaultNotification(context, new String[]{string, string2}, createPendingBroadcastIntent, createPendingBroadcastIntent);
            initDefaultNotification.setStyle(new NotificationCompat.BigTextStyle().bigText(string2)).addAction(0, context.getString(R$string.dismiss), createPendingBroadcastIntent(context, uid, createRemoveNotificationIntent(str))).addAction(0, context.getString(R$string.go_to_album), createPendingBroadcastIntent);
            this.mNotificationManager.notify(uid, initDefaultNotification.build());
        }
    }

    public void notifySharedAlbumDeleted(String str, String str2, String str3) {
        Log.sh("MdeNotificationManager", "notifySharedAlbumDeleted");
        Context context = getContext();
        if (!checkNonNull(context, str)) {
            int uid = MediaItemMde.toUid(str);
            Intent createOpenSharedViewIntent = createOpenSharedViewIntent();
            this.mNotificationManager.notify(uid, initDefaultNotification(context, getDeleteNotificationString(context, str, str2), createPendingActivityIntent(context, createOpenSharedViewIntent), createPendingActivityIntent(context, uid, createOpenSharedViewIntent)).build());
        }
    }

    public void notifySharedAlbumInfo(SharedAlbumPushType sharedAlbumPushType, String str, String str2, String str3, String str4, String str5, String str6, int i2) {
        Bitmap bitmap;
        Log.sh("MdeNotificationManager", "notifySharedAlbumInfo");
        Context context = getContext();
        if (!checkNonNull(context, str)) {
            if (str2 == null) {
                Log.she("MdeNotificationManager", "Cannot get shared album id");
                return;
            }
            int uid = MediaItemMde.toUid(str2);
            String[] newInfoNotificationString = getNewInfoNotificationString(context, sharedAlbumPushType, str, getNonNullText(context, str4, "ownerName"), str3, i2);
            NotificationCompat.Builder initDefaultNotification = initDefaultNotification(context, newInfoNotificationString, createPendingActivityIntent(context, createOpenSharedViewIntent()), createPendingActivityIntent(context, uid, createOpenSharedAlbumDetailViewIntent(str2, str)));
            initDefaultNotification.setStyle(new NotificationCompat.BigTextStyle().bigText(newInfoNotificationString[1]));
            if (str5 != null) {
                bitmap = getLargeIconFromFilePath(str5);
            } else {
                bitmap = null;
            }
            if (bitmap != null) {
                int orientationFromMetadata = MdeMetadataParser.getOrientationFromMetadata(str6);
                if (MdeMetadataParser.getDurationFromMetadata(str6) <= 0 && orientationFromMetadata != 0) {
                    bitmap = BitmapUtils.rotateBitmap(bitmap, orientationFromMetadata);
                }
                if (bitmap.getWidth() > 0 && bitmap.getHeight() > 0) {
                    initDefaultNotification.setLargeIcon(bitmap);
                }
            }
            this.mNotificationManager.notify(uid, initDefaultNotification.build());
        }
    }

    public void notifySharedAlbumInvitation(String str, String str2, String str3) {
        Intent intent;
        Log.sh("MdeNotificationManager", "notifySharedAlbumInvitation");
        Context context = getContext();
        if (!checkNonNull(context, str)) {
            int uid = MediaItemMde.toUid(str);
            String string = context.getString(R$string.join_shared_album);
            String string2 = context.getString(R$string.sharing_invitations_push_description_body, new Object[]{str3, str2});
            if (PreferenceFeatures.OneUi41.SHARING_INVITATION_ON_ALBUMS) {
                intent = createOpenSharedViewIntent();
            } else {
                intent = createOpenInvitationIntent();
            }
            PendingIntent createPendingActivityIntent = createPendingActivityIntent(context, intent);
            NotificationCompat.Builder initDefaultNotification = initDefaultNotification(context, new String[]{string, string2}, createPendingActivityIntent, createPendingActivityIntent);
            initDefaultNotification.setStyle(new NotificationCompat.BigTextStyle().bigText(string2)).addAction(0, context.getString(R$string.join_decline), createPendingBroadcastIntent(context, uid, createDeclineIntent(str))).addAction(0, context.getString(R$string.join_accept), createPendingBroadcastIntent(context, uid, createAcceptIntent(str)));
            this.mNotificationManager.notify(uid, initDefaultNotification.build());
        }
    }

    public void refreshNotification() {
        StatusBarNotification statusBarNotification = null;
        int i2 = 0;
        for (StatusBarNotification statusBarNotification2 : this.mNotificationManager.getActiveNotifications()) {
            if (isGroupNotification(statusBarNotification2.getNotification())) {
                i2++;
                if (isSummaryNotification(statusBarNotification2)) {
                    statusBarNotification = statusBarNotification2;
                }
            }
        }
        if (i2 == 1 && statusBarNotification != null) {
            this.mNotificationManager.cancel("gallery_summary_noti", 1);
        }
    }

    private PendingIntent createPendingActivityIntent(Context context, int i2, Intent intent) {
        return AndroidCompat.createActivityPendingIntent(context, i2, intent, 134217728);
    }

    private PendingIntent createPendingBroadcastIntent(Context context, int i2, Intent intent) {
        return PendingIntentCompat.getBroadcast(context, i2, intent, 134217728);
    }
}
