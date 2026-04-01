package com.samsung.android.sdk.moneta.memory.service;

import L2.a;
import com.samsung.android.sdk.moneta.common.Logger;
import com.samsung.android.sdk.moneta.memory.ICountResponse;
import i.C0212a;
import kotlin.Metadata;
import qe.C1227c;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J!\u0010\n\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00022\b\u0010\t\u001a\u0004\u0018\u00010\bH\u0016¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"com/samsung/android/sdk/moneta/memory/service/MemorySearchServiceImpl$createCountResponse$1", "Lcom/samsung/android/sdk/moneta/memory/ICountResponse$Stub;", "", "response", "Lme/x;", "onResponse", "(I)V", "code", "", "message", "onError", "(ILjava/lang/String;)V", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MemorySearchServiceImpl$createCountResponse$1 extends ICountResponse.Stub {
    final /* synthetic */ C1227c $continuation;

    public MemorySearchServiceImpl$createCountResponse$1(C1227c cVar) {
        this.$continuation = cVar;
    }

    public void onError(int i2, String str) {
        Logger.INSTANCE.i$pde_sdk_1_0_40_release("Memory-MemorySearchServiceImpl", C0212a.k(i2, "[createCountResponse-onError] in error code: ", ", message: ", str));
        this.$continuation.resumeWith(a.l(new Exception(C0212a.k(i2, "Response error code: ", ", message: ", str))));
    }

    public void onResponse(int i2) {
        Logger.INSTANCE.i$pde_sdk_1_0_40_release("Memory-MemorySearchServiceImpl", "[createCountResponse-onResponse] in");
        this.$continuation.resumeWith(Integer.valueOf(i2));
    }
}
