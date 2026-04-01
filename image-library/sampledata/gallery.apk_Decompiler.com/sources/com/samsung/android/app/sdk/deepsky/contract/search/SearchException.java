package com.samsung.android.app.sdk.deepsky.contract.search;

import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/samsung/android/app/sdk/deepsky/contract/search/SearchException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "msg", "", "<init>", "(Ljava/lang/String;)V", "deepsky-sdk-smartsuggestion-6.1.0_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class SearchException extends RuntimeException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SearchException(String str) {
        super(str);
        j.e(str, "msg");
    }
}
