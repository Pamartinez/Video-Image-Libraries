package com.samsung.android.gallery.module.cloud;

import Sd.B;
import Sd.x;
import com.samsung.android.gallery.module.cloud.abstraction.CloudState;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.PreferenceName;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class CloudStateSamsung2Impl implements CloudState {
    public long getOneDriveEndDate() {
        return SamsungCloudCompat.getOneDriveEndDate();
    }

    public int getService() {
        return SamsungCloudCompat.getService();
    }

    public String getServiceName(int i2) {
        if (i2 > -1) {
            return x.values()[i2].name();
        }
        return "Invalid";
    }

    public boolean isAccountRequired() {
        return SamsungCloudCompat.isAccountRequired();
    }

    public boolean isAccountRequiredForNewGallery() {
        return SamsungCloudCompat.isAccountRequiredForNewGallery();
    }

    public boolean isEnabled() {
        if (SamsungCloudCompat.isMigrationAvailable() || SamsungCloudCompat.isOneDriveAvailable() || SamsungCloudCompat.isNewGalleryAvailable()) {
            return true;
        }
        return false;
    }

    public boolean isGracePeriod() {
        return SamsungCloudCompat.isGracePeriod();
    }

    public boolean isLegacyServiceStatusRequired() {
        return SamsungCloudCompat.isLegacyServiceStatusRequired();
    }

    public boolean isMigrationAvailable() {
        return SamsungCloudCompat.isMigrationAvailable();
    }

    public boolean isNewBadgeRequired() {
        if ((isOneDriveAvailable() || isOneDriveLinkRequired()) && !isSyncOnInPref() && !isGracePeriod()) {
            return true;
        }
        return false;
    }

    public boolean isNewGalleryAvailable() {
        return SamsungCloudCompat.isNewGalleryAvailable();
    }

    public boolean isOneDriveAvailable() {
        return SamsungCloudCompat.isOneDriveAvailable();
    }

    public boolean isOneDriveLinkRequired() {
        return SamsungCloudCompat.isOneDriveLinkRequired();
    }

    public boolean isPermissionRequired() {
        return SamsungCloudCompat.isPermissionRequired();
    }

    public boolean isServiceNotAvailable() {
        return SamsungCloudCompat.isServiceNotAvailable();
    }

    public boolean isShowTips() {
        return SamsungCloudCompat.isShowTips();
    }

    public boolean isSubscriptionRequired() {
        return SamsungCloudCompat.isSubscriptionRequired();
    }

    public boolean isSubscriptionRequiredLinkedBefore() {
        return SamsungCloudCompat.isSubscriptionRequiredLinkedBefore();
    }

    public boolean isSyncOnInPref() {
        return GalleryPreference.getInstance().loadBoolean(PreferenceName.IS_SCLOUD_SYNC_ON, false);
    }

    public void saveSyncOnInPref(boolean z) {
        GalleryPreference.getInstance().saveState(PreferenceName.IS_SCLOUD_SYNC_ON, z);
    }

    public String toString() {
        String str;
        int syncStatusInPref = SamsungCloudCompat.getSyncStatusInPref();
        StringBuilder sb2 = new StringBuilder("SamsungCloud2{Service#");
        sb2.append(getServiceName(getService()));
        sb2.append(",ShowTips#");
        sb2.append(isShowTips());
        sb2.append(",GracePeriod#");
        sb2.append(isGracePeriod());
        sb2.append(",OneDriveEndDate#");
        sb2.append(getOneDriveEndDate());
        sb2.append(",PermissionReq#");
        sb2.append(isPermissionRequired());
        sb2.append(",SyncOn#");
        sb2.append(isSyncOnInPref());
        sb2.append(",SyncStatus#");
        if (syncStatusInPref > -1) {
            str = B.values()[syncStatusInPref].name();
        } else {
            str = "Invalid";
        }
        return C0212a.p(sb2, str, "}");
    }
}
