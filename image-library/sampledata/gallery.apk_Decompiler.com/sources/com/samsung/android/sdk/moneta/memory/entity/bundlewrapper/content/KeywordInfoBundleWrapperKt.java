package com.samsung.android.sdk.moneta.memory.entity.bundlewrapper.content;

import android.os.Bundle;
import com.samsung.android.sdk.moneta.memory.entity.content.KeywordInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"toBundleWrapper", "Lcom/samsung/android/sdk/moneta/memory/entity/bundlewrapper/content/KeywordInfoBundleWrapper;", "Lcom/samsung/android/sdk/moneta/memory/entity/content/KeywordInfo;", "pde-sdk-1.0.40_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class KeywordInfoBundleWrapperKt {
    public static final KeywordInfoBundleWrapper toBundleWrapper(KeywordInfo keywordInfo) {
        j.e(keywordInfo, "<this>");
        Bundle bundle = new Bundle();
        bundle.putString("keyword", keywordInfo.getKeyword());
        bundle.putBundle(KeywordInfoBundleWrapper.BUNDLE_KEY_EXTRA, keywordInfo.getExtra());
        return new KeywordInfoBundleWrapper(bundle);
    }
}
