package com.samsung.android.sdk.mobileservice.social.share;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SyncOption {
    private boolean isPermissionUINeeded;
    private boolean isSyncWithQuota;
    private SyncType syncType;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public enum SyncType {
        SYNC_WITH_LAST_THUMBNAIL("SYNC_WITH_LAST_THUMBNAIL"),
        FULL_SYNC("FULL_SYNC");
        
        private final String syncType;

        private SyncType(String str) {
            this.syncType = str;
        }

        public String toString() {
            return this.syncType;
        }
    }

    public SyncOption(SyncType syncType2, boolean z, boolean z3) {
        this.syncType = syncType2;
        this.isPermissionUINeeded = z;
        this.isSyncWithQuota = z3;
    }

    public SyncType getSyncType() {
        return this.syncType;
    }

    public boolean isPermissionUINeeded() {
        return this.isPermissionUINeeded;
    }

    public boolean isSyncWithQuota() {
        return this.isSyncWithQuota;
    }
}
