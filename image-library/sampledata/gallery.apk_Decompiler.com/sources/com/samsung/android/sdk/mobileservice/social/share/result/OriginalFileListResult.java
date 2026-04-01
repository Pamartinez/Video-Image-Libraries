package com.samsung.android.sdk.mobileservice.social.share.result;

import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.common.result.Result;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class OriginalFileListResult implements Result {
    private final List<OriginalFile> mResultList;
    private final CommonResultStatus mStatus;

    public OriginalFileListResult(CommonResultStatus commonResultStatus, List<OriginalFile> list) {
        this.mStatus = commonResultStatus;
        this.mResultList = list;
    }

    public List<OriginalFile> getResultList() {
        return this.mResultList;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }
}
