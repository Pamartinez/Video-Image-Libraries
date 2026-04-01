package com.samsung.android.sdk.mobileservice.social.feedback.result;

import com.samsung.android.sdk.mobileservice.common.result.BaseResult;
import com.samsung.android.sdk.mobileservice.common.result.CommonResultStatus;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class FeedbackResult<T> implements BaseResult<T> {
    private T mResult;
    private CommonResultStatus mStatus;

    public FeedbackResult(CommonResultStatus commonResultStatus, T t) {
        this.mStatus = commonResultStatus;
        this.mResult = t;
    }

    public T getResult() {
        return this.mResult;
    }

    public CommonResultStatus getStatus() {
        return this.mStatus;
    }
}
