package com.samsung.android.sdk.moneta.memory.service;

import L2.a;
import Vf.C0873j;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.sdk.moneta.common.Logger;
import com.samsung.android.sdk.moneta.memory.IGetRecommendationsResponse;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.GetRecommendationsResponseBundleWrapper;
import com.samsung.android.sdk.moneta.memory.entity.content.GetRecommendationsResponse;
import i.C0212a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J!\u0010\u000b\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"com/samsung/android/sdk/moneta/memory/service/MemorySearchServiceImpl$createGetRecommendationsResponse$1", "Lcom/samsung/android/sdk/moneta/memory/IGetRecommendationsResponse$Stub;", "Lcom/samsung/android/sdk/moneta/memory/entity/bundlewrapper/content/GetRecommendationsResponseBundleWrapper;", "response", "Lme/x;", "onResponse", "(Lcom/samsung/android/sdk/moneta/memory/entity/bundlewrapper/content/GetRecommendationsResponseBundleWrapper;)V", "", "code", "", "message", "onError", "(ILjava/lang/String;)V", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MemorySearchServiceImpl$createGetRecommendationsResponse$1 extends IGetRecommendationsResponse.Stub {
    final /* synthetic */ C0873j $continuation;

    public MemorySearchServiceImpl$createGetRecommendationsResponse$1(C0873j jVar) {
        this.$continuation = jVar;
    }

    public void onError(int i2, String str) {
        Logger.INSTANCE.i$pde_sdk_1_0_40_release("Memory-MemorySearchServiceImpl", C0212a.k(i2, "[createGetRecommendationsResponse-onError] error code ", " message: ", str));
        this.$continuation.resumeWith(a.l(new Exception(C0212a.k(i2, "Response error code: ", ", message: ", str))));
    }

    public void onResponse(GetRecommendationsResponseBundleWrapper getRecommendationsResponseBundleWrapper) {
        j.e(getRecommendationsResponseBundleWrapper, Contract.RESPONSE);
        GetRecommendationsResponse content = getRecommendationsResponseBundleWrapper.toContent();
        Logger logger = Logger.INSTANCE;
        logger.i$pde_sdk_1_0_40_release("Memory-MemorySearchServiceImpl", "[createKeywordInfoListResponse-onResponse] response keyword info size " + content.getKeywords().size());
        this.$continuation.resumeWith(content);
    }
}
