package com.samsung.android.sdk.mobileservice.social.share.result;

import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.common.result.Result;
import com.samsung.android.sdk.mobileservice.social.share.ShareApi;
import com.samsung.android.sdk.mobileservice.social.share.TrashedItem;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class TrashedItemListResult implements Result {
    private final List<ShareApi.SharedItemRequest> mFailureList;
    private final CommonResultStatus mStatus;
    private final List<TrashedItem> mSuccessList;

    public TrashedItemListResult(CommonResultStatus commonResultStatus, List<TrashedItem> list, List<ShareApi.SharedItemRequest> list2) {
        this.mStatus = commonResultStatus;
        this.mSuccessList = list;
        this.mFailureList = list2;
    }

    public List<ShareApi.SharedItemRequest> getFailureList() {
        return this.mFailureList;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }

    public List<TrashedItem> getSuccessList() {
        return this.mSuccessList;
    }
}
