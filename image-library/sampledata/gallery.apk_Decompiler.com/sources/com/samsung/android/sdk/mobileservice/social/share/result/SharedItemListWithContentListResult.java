package com.samsung.android.sdk.mobileservice.social.share.result;

import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.common.result.Result;
import com.samsung.android.sdk.mobileservice.social.share.SharedItemWithUriList;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharedItemListWithContentListResult implements Result {
    private List<SharedItemWithUriList> mItemList;
    private CommonResultStatus mStatus;

    public SharedItemListWithContentListResult(CommonResultStatus commonResultStatus, List<SharedItemWithUriList> list) {
        this.mStatus = commonResultStatus;
        this.mItemList = list;
    }

    public List<SharedItemWithUriList> getItemsList() {
        return this.mItemList;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }
}
