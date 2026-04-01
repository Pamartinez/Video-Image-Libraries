package com.samsung.android.gallery.module.cloud;

import A.a;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import com.samsung.android.gallery.module.cloud.abstraction.CloudDownloadMonitor;
import com.samsung.android.gallery.module.cloud.abstraction.DownloadCanceller;
import com.samsung.android.gallery.module.cloud.abstraction.DownloadParams;
import com.samsung.android.gallery.module.cloud.abstraction.SamsungCloud;
import com.samsung.android.gallery.module.cloud.sdk.SamsungCloud2Sdk;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.scsp.media.Media;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SamsungCloudCompat {
    /* access modifiers changed from: private */
    public static volatile SamsungCloud sInstance = createInstance(isNewGalleryAvailable());

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SamsungCloud2StatusHolder {
        static final SamsungCloud2Status instance = new SamsungCloud2Status().setSyncSettingListener(new b(0)).setSyncStatusListener(new b(1)).setServiceChangeListener(new b(2)).setAlbumSyncDataChangeListener(new b(3));

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$static$0(Boolean bool) {
            SamsungCloudCompat.updateCloudSyncOnCache(bool.booleanValue());
            Blackboard.getApplicationInstance().post("cloud/sync/on/off/changed", bool);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$static$1(Integer num) {
            SamsungCloudCompat.updateSyncStatus(num.intValue());
            Blackboard.getApplicationInstance().post("global://cloud/media/sync/status/changed", num);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$static$2(Integer num) {
            boolean z;
            if (num.intValue() > 0) {
                if (num.intValue() == SamsungCloud2Sdk.SERVICE_NEW_GALLERY_AVAILABLE) {
                    z = true;
                } else {
                    z = false;
                }
                SamsungCloudCompat.sInstance = SamsungCloudCompat.createInstance(z);
                if (z) {
                    SamsungCloudCompat.initSamsungCloud2(AppResources.getAppContext());
                }
                Features.recycle(Features.SUPPORT_CLOUD);
            }
            Blackboard.getApplicationInstance().post("global://event/cloud/service/changed", (Object) null);
        }

        /* access modifiers changed from: private */
        public static /* synthetic */ void lambda$static$3(Bundle bundle) {
            SamsungCloudCompat.updateAlbumSyncData(bundle);
            Blackboard.publishGlobal("album_sync_data_changed", Boolean.TRUE);
        }
    }

    public static boolean changeSyncState(Context context, boolean z) {
        if (context == null || !sInstance.changeContentSyncState(context, z)) {
            return false;
        }
        return true;
    }

    public static void checkSamsungCloud2(Context context) {
        if (Features.isEnabled(Features.SUPPORT_NEW_SAMSUNG_CLOUD)) {
            SamsungCloud2StatusHolder.instance.checkServiceAvailable(context);
        }
    }

    public static int copy(Context context, String str, String str2) {
        return sInstance.copy(context, str, str2);
    }

    /* access modifiers changed from: private */
    public static SamsungCloud createInstance(boolean z) {
        if (z) {
            return new SamsungCloud2Impl();
        }
        if (SdkConfig.atLeast(SdkConfig.SEM.T_MR1)) {
            return new SamsungCloudTImpl();
        }
        if (SdkConfig.atLeast(SdkConfig.GED.Q)) {
            return new SamsungCloudQImpl();
        }
        if (SdkConfig.atLeast(SdkConfig.GED.P)) {
            return new SamsungCloudPImpl();
        }
        return new SamsungCloudOImpl();
    }

    public static boolean delete(Context context, String str) {
        return sInstance.delete(context, str);
    }

    public static ArrayList<Uri> download(Context context, DownloadParams downloadParams) {
        return sInstance.download(context, downloadParams);
    }

    public static boolean downloadFileDirectly(Context context, DownloadParams downloadParams) {
        return sInstance.downloadFileDirectly(context, downloadParams);
    }

    public static boolean downloadOriginalFile(Context context, String str, String str2, boolean z, String str3) {
        return sInstance.downloadItemOriginalFile(context, str, str2, z, str3, true);
    }

    public static Media empty(Context context, Media media) {
        return sInstance.empty(context, media);
    }

    public static int getAlbumSyncStatus(Context context, int i2, String str) {
        return sInstance.getAlbumSyncStatus(context, i2, str);
    }

    public static DownloadCanceller getDownloadCanceller() {
        return sInstance.getDownloadCanceller();
    }

    public static CloudDownloadMonitor getDownloadMonitor() {
        return sInstance.getDownloadMonitor();
    }

    public static String getDownloadUrl(Context context, long j2, String str) {
        return sInstance.getDownloadUrl(context, j2, str);
    }

    public static long getOneDriveEndDate() {
        return SamsungCloud2StatusHolder.instance.getOneDriveEndDate();
    }

    public static String getOriginalFilePath(String str) {
        return sInstance.getOriginalFilePath(str);
    }

    public static int getService() {
        return SamsungCloud2StatusHolder.instance.getService();
    }

    public static String getStreamingUrl(Context context, long j2, String str) {
        return sInstance.getStreamingUrl(context, j2, str);
    }

    public static int getSyncStatusInPref() {
        return SamsungCloud2StatusHolder.instance.getSyncStatusInPref();
    }

    public static boolean initSamsungCloud2(Context context) {
        if (!Features.isEnabled(Features.SUPPORT_NEW_SAMSUNG_CLOUD)) {
            return false;
        }
        SamsungCloud2Status samsungCloud2Status = SamsungCloud2StatusHolder.instance;
        if (!samsungCloud2Status.isNewGalleryAvailable() || !samsungCloud2Status.loadConfig(context)) {
            return false;
        }
        return true;
    }

    public static boolean isAccountRequired() {
        return SamsungCloud2StatusHolder.instance.isAccountRequired();
    }

    public static boolean isAccountRequiredForNewGallery() {
        return SamsungCloud2StatusHolder.instance.isAccountRequiredForNewGallery();
    }

    public static boolean isCloudSyncOn(Context context) {
        return sInstance.isCloudSyncOn(context);
    }

    public static boolean isCloudSyncOnCached(Context context) {
        return sInstance.isCloudSyncOnCached(context);
    }

    public static boolean isGracePeriod() {
        return SamsungCloud2StatusHolder.instance.isGracePeriod();
    }

    public static boolean isLegacyServiceStatusRequired() {
        return SamsungCloud2StatusHolder.instance.isLegacyServiceStatusRequired();
    }

    public static boolean isMigrationAvailable() {
        return SamsungCloud2StatusHolder.instance.isMigrationAvailable();
    }

    public static boolean isNewGalleryAvailable() {
        if (!Features.isEnabled(Features.SUPPORT_NEW_SAMSUNG_CLOUD) || !SamsungCloud2StatusHolder.instance.isNewGalleryAvailable()) {
            return false;
        }
        return true;
    }

    public static boolean isOneDriveAvailable() {
        return SamsungCloud2StatusHolder.instance.isOneDriveAvailable();
    }

    public static boolean isOneDriveLinkRequired() {
        return SamsungCloud2StatusHolder.instance.isOneDriveLinkRequired();
    }

    public static boolean isPermissionRequired() {
        return SamsungCloud2StatusHolder.instance.isPermissionRequired();
    }

    public static boolean isServiceNotAvailable() {
        return SamsungCloud2StatusHolder.instance.isServiceNotAvailable();
    }

    public static boolean isShowTips() {
        return SamsungCloud2StatusHolder.instance.isShowTips();
    }

    public static boolean isSubscriptionRequired() {
        return SamsungCloud2StatusHolder.instance.isSubscriptionRequired();
    }

    public static boolean isSubscriptionRequiredLinkedBefore() {
        return SamsungCloud2StatusHolder.instance.isSubscriptionRequiredLinkedBefore();
    }

    public static boolean isSyncOff(Context context, String str) {
        return sInstance.isSyncOffFolder(context, str);
    }

    public static boolean isSyncOn(Context context) {
        boolean z = false;
        if (context != null) {
            try {
                if (sInstance.isContentSyncOn(context)) {
                    z = true;
                }
            } catch (Exception e) {
                a.s(e, new StringBuilder("isSyncOn failed. e="), "SamsungCloudCompat");
            }
        }
        GalleryPreference.getInstance().saveState(PreferenceName.IS_SCLOUD_SYNC_ON, z);
        return z;
    }

    public static boolean isSyncSuccess(Context context) {
        return sInstance.isSyncSuccess(context);
    }

    public static boolean isSyncing(Context context) {
        return sInstance.isSyncing(context);
    }

    public static int move(Context context, String str, String str2, boolean z) {
        return sInstance.move(context, str, str2, z);
    }

    public static void registerSamsungCloud2(Context context) {
        if (Features.isEnabled(Features.SUPPORT_NEW_SAMSUNG_CLOUD)) {
            SamsungCloud2StatusHolder.instance.register(context);
        }
    }

    public static void releaseSyncState() {
        sInstance.releaseSyncState();
    }

    public static Media restore(Context context, Media media) {
        return sInstance.restore(context, media);
    }

    public static boolean setFavorite(Context context, String str, String str2, boolean z) {
        return sInstance.setFavorite(context, str, str2, z ? 1 : 0);
    }

    public static void unregisterSamsungCloud2() {
        if (Features.isEnabled(Features.SUPPORT_NEW_SAMSUNG_CLOUD)) {
            SamsungCloud2StatusHolder.instance.unregister();
        }
    }

    public static void updateAlbumSyncData(Bundle bundle) {
        sInstance.updateAlbumSyncData(bundle);
    }

    public static void updateCloudSyncOnCache(boolean z) {
        sInstance.updateCloudSyncOnCache(z);
    }

    public static void updateSyncStatus(Bundle bundle) {
        sInstance.updateSyncStatus(bundle);
    }

    public static int copy(Context context, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        return sInstance.copy(context, arrayList, arrayList2);
    }

    public static boolean isSyncSuccess() {
        return sInstance.isSyncSuccess();
    }

    public static boolean isSyncing() {
        return sInstance.isSyncing();
    }

    public static int move(Context context, ArrayList<String> arrayList, ArrayList<String> arrayList2, boolean z) {
        return sInstance.move(context, arrayList, arrayList2, z);
    }

    public static void updateSyncStatus(int i2) {
        sInstance.updateSyncStatus(i2);
    }
}
