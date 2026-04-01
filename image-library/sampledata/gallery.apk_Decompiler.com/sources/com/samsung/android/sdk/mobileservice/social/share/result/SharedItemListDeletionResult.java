package com.samsung.android.sdk.mobileservice.social.share.result;

import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.common.result.Result;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SharedItemListDeletionResult implements Result {
    private List<SharedItemDeletionResult> mResultList;
    private CommonResultStatus mStatus;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SharedItemDeletionResult {
        private String itemId;
        private boolean result;
        private String spaceId;

        public SharedItemDeletionResult(String str, String str2, boolean z) {
            this.spaceId = str;
            this.itemId = str2;
            this.result = z;
        }

        public String getItemId() {
            return this.itemId;
        }

        public boolean getResult() {
            return this.result;
        }

        public String getSpaceId() {
            return this.spaceId;
        }
    }

    public SharedItemListDeletionResult(CommonResultStatus commonResultStatus, List<SharedItemDeletionResult> list) {
        this.mStatus = commonResultStatus;
        this.mResultList = list;
    }

    public List<SharedItemDeletionResult> getResultList() {
        return this.mResultList;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }
}
