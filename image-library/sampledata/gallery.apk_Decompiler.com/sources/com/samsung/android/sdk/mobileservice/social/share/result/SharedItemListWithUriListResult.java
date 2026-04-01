package com.samsung.android.sdk.mobileservice.social.share.result;

import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.common.result.Result;
import com.samsung.android.sdk.mobileservice.social.share.ShareApi;
import com.samsung.android.sdk.mobileservice.social.share.SharedItemWithUriList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharedItemListWithUriListResult implements Result {
    private List<ShareApi.SharedItemWithUriListRequest> mFailureList;
    private CommonResultStatus mStatus;
    private List<SharedItemWithUriList> mSuccessList;

    public SharedItemListWithUriListResult(CommonResultStatus commonResultStatus, List<SharedItemWithUriList> list, List<ShareApi.SharedItemWithUriListRequest> list2) {
        this.mStatus = commonResultStatus;
        this.mSuccessList = list;
        this.mFailureList = list2;
    }

    public List<ShareApi.SharedItemWithUriListRequest> getFailureList() {
        return this.mFailureList;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }

    public List<SharedItemWithUriList> getSuccessList() {
        return this.mSuccessList;
    }
}
