package com.samsung.android.gallery.module.cloud;

import com.samsung.android.gallery.module.cloud.abstraction.CloudState;
import com.samsung.android.gallery.support.utils.Features;
import java.io.PrintWriter;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class CloudStateCompat {

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class OneDriveState {
        static final CloudState instance = new CloudStateOneDriveImpl();
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SamsungCloud2State {
        static final CloudState instance = new CloudStateSamsung2Impl();
    }

    public static void dump(PrintWriter printWriter) {
        printWriter.println("SamsungCloud2 state : " + SamsungCloud2State.instance);
        printWriter.println("OneDrive state : " + OneDriveState.instance);
    }

    public static CloudState getInstance() {
        if (!Features.isEnabled(Features.SUPPORT_NEW_SAMSUNG_CLOUD) || SamsungCloudCompat.isLegacyServiceStatusRequired()) {
            return OneDriveState.instance;
        }
        return SamsungCloud2State.instance;
    }

    public static long getOneDriveEndDate() {
        return getInstance().getOneDriveEndDate();
    }

    public static int getService() {
        return getInstance().getService();
    }

    public static String getServiceName(int i2) {
        return getInstance().getServiceName(i2);
    }

    public static boolean isAccountRequired() {
        return getInstance().isAccountRequired();
    }

    public static boolean isAccountRequiredForNewGallery() {
        return getInstance().isAccountRequiredForNewGallery();
    }

    public static boolean isEnabled() {
        return getInstance().isEnabled();
    }

    public static boolean isEnabledInPref() {
        return getInstance().isEnabledInPref();
    }

    public static boolean isGracePeriod() {
        return getInstance().isGracePeriod();
    }

    public static boolean isLegacyServiceStatusRequired() {
        return getInstance().isLegacyServiceStatusRequired();
    }

    public static boolean isLoadingCompleted() {
        return getInstance().isLoadingCompleted();
    }

    public static boolean isMigrated() {
        return getInstance().isMigrated();
    }

    public static boolean isMigrationAvailable() {
        return getInstance().isMigrationAvailable();
    }

    public static boolean isMigrationSupported() {
        return getInstance().isMigrationSupported();
    }

    public static boolean isMigrationSupportedInPref() {
        return getInstance().isMigrationSupportedInPref();
    }

    public static boolean isNewBadgeRequired() {
        return getInstance().isNewBadgeRequired();
    }

    public static boolean isNewGalleryAvailable() {
        return getInstance().isNewGalleryAvailable();
    }

    public static boolean isNone() {
        return getInstance().isNone();
    }

    public static boolean isOneDriveAvailable() {
        return getInstance().isOneDriveAvailable();
    }

    public static boolean isOneDriveLinkRequired() {
        return getInstance().isOneDriveLinkRequired();
    }

    public static boolean isPermissionRequired() {
        return getInstance().isPermissionRequired();
    }

    public static boolean isServiceNotAvailable() {
        return getInstance().isServiceNotAvailable();
    }

    public static boolean isShowTips() {
        return getInstance().isShowTips();
    }

    public static boolean isSubscriptionRequired() {
        return getInstance().isSubscriptionRequired();
    }

    public static boolean isSubscriptionRequiredLinkedBefore() {
        return getInstance().isSubscriptionRequiredLinkedBefore();
    }

    public static boolean isSyncOnInPref() {
        return getInstance().isSyncOnInPref();
    }

    public static boolean isUnknown() {
        return getInstance().isUnknown();
    }

    public static boolean isUnlinked() {
        return getInstance().isUnlinked();
    }

    public static boolean load(boolean z) {
        return getInstance().load(z);
    }

    public static float loadQuota() {
        return getInstance().loadQuota();
    }

    public static boolean loadTrialStatus() {
        return getInstance().loadTrialStatus();
    }

    public static void saveSyncOnInPref(boolean z) {
        getInstance().saveSyncOnInPref(z);
    }
}
