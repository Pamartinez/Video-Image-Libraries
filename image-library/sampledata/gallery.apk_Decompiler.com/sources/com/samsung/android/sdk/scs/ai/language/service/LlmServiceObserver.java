package com.samsung.android.sdk.scs.ai.language.service;

import android.os.Bundle;
import com.samsung.android.sivs.ai.sdkcommon.language.C0176x;

@Deprecated
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class LlmServiceObserver extends C0176x {
    public LlmServiceObserver() {
        attachInterface(this, "com.samsung.android.sivs.ai.sdkcommon.language.ILlmServiceObserver");
    }

    public abstract /* synthetic */ void onComplete();

    public abstract /* synthetic */ void onError(Bundle bundle);

    public abstract /* synthetic */ void onNext(String str);
}
