package com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content;

import android.os.Bundle;
import com.samsung.android.sdk.moneta.memory.entity.content.GetRecommendationsResponse;
import com.samsung.android.sdk.moneta.memory.entity.content.KeywordInfo;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1196n;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"toBundleWrapper", "Lcom/samsung/android/sdk/moneta/memory/entity/bundlewrapper/content/GetRecommendationsResponseBundleWrapper;", "Lcom/samsung/android/sdk/moneta/memory/entity/content/GetRecommendationsResponse;", "pde-sdk-1.0.40_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class GetRecommendationsResponseBundleWrapperKt {
    public static final GetRecommendationsResponseBundleWrapper toBundleWrapper(GetRecommendationsResponse getRecommendationsResponse) {
        j.e(getRecommendationsResponse, "<this>");
        Bundle bundle = new Bundle();
        bundle.putString(GetRecommendationsResponseBundleWrapper.BUNDLE_KEY_DEBUG_INFO, getRecommendationsResponse.getDebugInfo());
        Iterable<KeywordInfo> keywords = getRecommendationsResponse.getKeywords();
        ArrayList arrayList = new ArrayList(C1196n.w0(keywords, 10));
        for (KeywordInfo bundleWrapper : keywords) {
            arrayList.add(KeywordInfoBundleWrapperKt.toBundleWrapper(bundleWrapper));
        }
        bundle.putParcelableArrayList("keywords", new ArrayList(arrayList));
        return new GetRecommendationsResponseBundleWrapper(bundle);
    }
}
