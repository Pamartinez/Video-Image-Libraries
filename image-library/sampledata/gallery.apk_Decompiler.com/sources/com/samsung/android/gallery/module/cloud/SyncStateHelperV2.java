package com.samsung.android.gallery.module.cloud;

import Sd.z;
import android.content.Context;
import android.os.Bundle;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import com.samsung.android.gallery.module.cloud.sdk.SamsungCloud2Sdk;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class SyncStateHelperV2 extends SyncStateHelper {
    public Bundle getAlbumSyncData(Context context) {
        Bundle requestGetAlbumStatus = SamsungCloud2Sdk.requestGetAlbumStatus(context);
        if (requestGetAlbumStatus != null) {
            return (Bundle) requestGetAlbumStatus.clone();
        }
        return null;
    }

    public boolean isSyncSuccess(Context context) {
        return isSyncSuccess();
    }

    public boolean isSyncing(Context context) {
        return isSyncing();
    }

    public String toString() {
        return "SyncState{" + z.values()[this.mSyncState.get()].name() + ArcCommonLog.TAG_COMMA + getCloudSyncString() + "}";
    }

    public void updateSyncStatus(int i2) {
        this.mSyncState.set(i2);
    }

    public boolean isSyncSuccess() {
        return this.mSyncState.get() == SamsungCloud2Sdk.SYNC_STATUS_COMPLETED;
    }

    public boolean isSyncing() {
        return this.mSyncState.get() == SamsungCloud2Sdk.SYNC_STATUS_PROGRESS;
    }
}
