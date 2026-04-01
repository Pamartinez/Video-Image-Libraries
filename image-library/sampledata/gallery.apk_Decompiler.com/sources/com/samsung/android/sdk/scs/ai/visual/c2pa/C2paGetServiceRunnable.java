package com.samsung.android.sdk.scs.ai.visual.c2pa;

import com.samsung.android.sdk.scs.base.feature.Feature;
import com.samsung.android.sdk.scs.base.tasks.TaskRunnable;
import com.samsung.android.visual.ai.sdkcommon.o;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C2paGetServiceRunnable extends TaskRunnable<o> {
    private static final String TAG = "C2paGetServiceRunnable";
    private final C2paServiceExecutor mServiceExecutor;

    public C2paGetServiceRunnable(C2paServiceExecutor c2paServiceExecutor) {
        this.mServiceExecutor = c2paServiceExecutor;
    }

    public void execute() {
        this.mSource.setResult(this.mServiceExecutor.getC2PAService());
    }

    public String getFeatureName() {
        return Feature.FEATURE_VISUAL_C2PA;
    }
}
