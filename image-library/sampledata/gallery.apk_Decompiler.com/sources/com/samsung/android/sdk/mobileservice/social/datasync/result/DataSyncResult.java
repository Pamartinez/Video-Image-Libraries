package com.samsung.android.sdk.mobileservice.social.datasync.result;

import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.social.datasync.SyncedData;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class DataSyncResult {
    private final List<SyncedData> mResponse;
    private final CommonResultStatus mStatus;

    public DataSyncResult(CommonResultStatus commonResultStatus) {
        this.mStatus = commonResultStatus;
        this.mResponse = null;
    }

    public List<SyncedData> getResponse() {
        return this.mResponse;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }

    public DataSyncResult(CommonResultStatus commonResultStatus, List<SyncedData> list) {
        this.mStatus = commonResultStatus;
        this.mResponse = list;
    }
}
