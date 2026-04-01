package com.samsung.android.sdk.moneta.memory.service;

import L2.a;
import Vf.C0873j;
import com.samsung.android.app.sdk.deepsky.contract.search.Contract;
import com.samsung.android.sdk.moneta.common.Logger;
import com.samsung.android.sdk.moneta.memory.IKeywordInfoListResponse;
import com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content.KeywordInfoBundleWrapper;
import i.C0212a;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1196n;

@Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001d\u0010\u0006\u001a\u00020\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016¢\u0006\u0004\b\u0006\u0010\u0007J!\u0010\f\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0016¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"com/samsung/android/sdk/moneta/memory/service/MemorySearchServiceImpl$createKeywordInfoListResponse$1", "Lcom/samsung/android/sdk/moneta/memory/IKeywordInfoListResponse$Stub;", "", "Lcom/samsung/android/sdk/moneta/memory/entity/bundlewrapper/content/KeywordInfoBundleWrapper;", "response", "Lme/x;", "onResponse", "(Ljava/util/List;)V", "", "code", "", "message", "onError", "(ILjava/lang/String;)V", "pde-sdk-1.0.40_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class MemorySearchServiceImpl$createKeywordInfoListResponse$1 extends IKeywordInfoListResponse.Stub {
    final /* synthetic */ C0873j $continuation;

    public MemorySearchServiceImpl$createKeywordInfoListResponse$1(C0873j jVar) {
        this.$continuation = jVar;
    }

    public void onError(int i2, String str) {
        Logger.INSTANCE.i$pde_sdk_1_0_40_release("Memory-MemorySearchServiceImpl", C0212a.k(i2, "[createKeywordInfoListResponse-onError] error code ", " message: ", str));
        this.$continuation.resumeWith(a.l(new Exception(C0212a.k(i2, "Response error code: ", ", message: ", str))));
    }

    public void onResponse(List<KeywordInfoBundleWrapper> list) {
        j.e(list, Contract.RESPONSE);
        Logger logger = Logger.INSTANCE;
        logger.i$pde_sdk_1_0_40_release("Memory-MemorySearchServiceImpl", "[createKeywordInfoListResponse-onResponse] response size " + list.size());
        C0873j jVar = this.$continuation;
        Iterable<KeywordInfoBundleWrapper> iterable = list;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (KeywordInfoBundleWrapper content : iterable) {
            arrayList.add(content.toContent());
        }
        jVar.resumeWith(arrayList);
    }
}
