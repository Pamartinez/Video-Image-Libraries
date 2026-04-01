package com.samsung.android.sdk.scs.ai.language.service;

import android.os.Bundle;
import com.samsung.android.sivs.ai.sdkcommon.language.C0178z;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class LlmServiceObserver2 extends C0178z {
    public LlmServiceObserver2() {
        attachInterface(this, "com.samsung.android.sivs.ai.sdkcommon.language.ILlmServiceObserver2");
    }

    public abstract /* synthetic */ void onComplete();

    public abstract /* synthetic */ void onError(Bundle bundle);

    public abstract /* synthetic */ void onNext(List list);
}
