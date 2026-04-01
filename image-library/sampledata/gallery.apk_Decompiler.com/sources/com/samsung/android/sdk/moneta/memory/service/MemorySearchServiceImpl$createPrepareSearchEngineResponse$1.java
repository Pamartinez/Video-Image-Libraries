package com.samsung.android.sdk.moneta.memory.service;

import L2.a;
import Vf.C0873j;
import com.samsung.android.sdk.moneta.common.Logger;
import com.samsung.android.sdk.moneta.memory.IPrepareSearchEngineResponse;
import i.C0212a;
import kotlin.Metadata;
import me.x;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u000f\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0003\u0010\u0004J!\u0010\t\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0016¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"com/samsung/android/sdk/moneta/memory/service/MemorySearchServiceImpl$createPrepareSearchEngineResponse$1", "Lcom/samsung/android/sdk/moneta/memory/IPrepareSearchEngineResponse$Stub;", "Lme/x;", "onResponse", "()V", "", "code", "", "message", "onError", "(ILjava/lang/String;)V", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MemorySearchServiceImpl$createPrepareSearchEngineResponse$1 extends IPrepareSearchEngineResponse.Stub {
    final /* synthetic */ C0873j $continuation;

    public MemorySearchServiceImpl$createPrepareSearchEngineResponse$1(C0873j jVar) {
        this.$continuation = jVar;
    }

    public void onError(int i2, String str) {
        Logger.INSTANCE.i$pde_sdk_1_0_40_release("Memory-MemorySearchServiceImpl", C0212a.k(i2, "[createPrepareSearchEngineResponse-onError] error code ", " message: ", str));
        this.$continuation.resumeWith(a.l(new Exception(C0212a.k(i2, "Response error code: ", ", message: ", str))));
    }

    public void onResponse() {
        this.$continuation.resumeWith(x.f4917a);
    }
}
