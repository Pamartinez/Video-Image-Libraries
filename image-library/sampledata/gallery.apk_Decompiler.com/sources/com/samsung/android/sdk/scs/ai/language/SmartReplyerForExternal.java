package com.samsung.android.sdk.scs.ai.language;

import android.content.Context;
import com.samsung.android.sdk.scs.ai.language.service.SmartReplyServiceExecutorForExternal;
import com.samsung.android.sdk.scs.base.feature.Feature;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SmartReplyerForExternal extends SmartReplyer {
    public SmartReplyerForExternal(Context context) {
        super(context);
    }

    public void createExecutor(Context context) {
        this.mServiceExecutor = new SmartReplyServiceExecutorForExternal(context);
        this.featureName = Feature.FEATURE_AI_GEN_SMART_REPLY_FOR_EXTERNAL;
    }
}
