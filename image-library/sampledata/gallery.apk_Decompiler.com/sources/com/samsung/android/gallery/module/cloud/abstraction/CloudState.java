package com.samsung.android.gallery.module.cloud.abstraction;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface CloudState {
    long getOneDriveEndDate() {
        return 0;
    }

    int getService() {
        return -1;
    }

    String getServiceName(int i2) {
        return null;
    }

    boolean isAccountRequired() {
        return false;
    }

    boolean isAccountRequiredForNewGallery() {
        return false;
    }

    boolean isEnabled();

    boolean isEnabledInPref() {
        return false;
    }

    boolean isGracePeriod() {
        return false;
    }

    boolean isLegacyServiceStatusRequired() {
        return true;
    }

    boolean isLoadingCompleted() {
        return false;
    }

    boolean isMigrated() {
        return false;
    }

    boolean isMigrationAvailable() {
        return false;
    }

    boolean isMigrationSupported() {
        return false;
    }

    boolean isMigrationSupportedInPref() {
        return false;
    }

    boolean isNewBadgeRequired();

    boolean isNewGalleryAvailable() {
        return false;
    }

    boolean isNone() {
        return false;
    }

    boolean isOneDriveAvailable() {
        return false;
    }

    boolean isOneDriveLinkRequired() {
        return false;
    }

    boolean isPermissionRequired();

    boolean isServiceNotAvailable() {
        return false;
    }

    boolean isShowTips() {
        return false;
    }

    boolean isSubscriptionRequired() {
        return false;
    }

    boolean isSubscriptionRequiredLinkedBefore() {
        return false;
    }

    boolean isSyncOnInPref();

    boolean isUnknown() {
        return false;
    }

    boolean isUnlinked() {
        return false;
    }

    boolean load(boolean z) {
        return false;
    }

    float loadQuota() {
        return 0.0f;
    }

    boolean loadTrialStatus() {
        return false;
    }

    void saveSyncOnInPref(boolean z);
}
