package com.samsung.android.sdk.mobileservice.social.share.result;

import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;
import com.samsung.android.sdk.mobileservice.common.result.Result;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class ItemListResult implements Result {
    private List<SharedItemListFailureResult> mFailureList;
    private CommonResultStatus mStatus;
    private List<SharedItemListSuccessResult> mSuccessList;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SharedItemListFailureResult {
        private Long errorCode;
        private String itemId;
        private String spaceId;

        public SharedItemListFailureResult(String str, String str2, Long l) {
            this.spaceId = str;
            this.itemId = str2;
            this.errorCode = l;
        }

        public Long getErrorCode() {
            return this.errorCode;
        }

        public String getItemId() {
            return this.itemId;
        }

        public String getSpaceId() {
            return this.spaceId;
        }
    }

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class SharedItemListSuccessResult {
        private String itemId;
        private String spaceId;

        public SharedItemListSuccessResult(String str, String str2) {
            this.spaceId = str;
            this.itemId = str2;
        }

        public String getItemId() {
            return this.itemId;
        }

        public String getSpaceId() {
            return this.spaceId;
        }
    }

    public ItemListResult(CommonResultStatus commonResultStatus, List<SharedItemListSuccessResult> list, List<SharedItemListFailureResult> list2) {
        this.mStatus = commonResultStatus;
        this.mSuccessList = list;
        this.mFailureList = list2;
    }

    public List<SharedItemListFailureResult> getFailureList() {
        return this.mFailureList;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }

    public List<SharedItemListSuccessResult> getSuccessList() {
        return this.mSuccessList;
    }
}
