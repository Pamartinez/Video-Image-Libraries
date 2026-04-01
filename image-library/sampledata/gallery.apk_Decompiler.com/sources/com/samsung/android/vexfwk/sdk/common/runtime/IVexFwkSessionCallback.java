package com.samsung.android.vexfwk.sdk.common.runtime;

import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/runtime/IVexFwkSessionCallback;", "", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionPartialResult;", "partialResult", "Lme/x;", "onRequestProgressed", "(Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionPartialResult;)V", "Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionTotalResult;", "totalResult", "onRequestCompleted", "(Lcom/samsung/android/vexfwk/sdk/common/runtime/VexFwkSessionTotalResult;)V", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IVexFwkSessionCallback {

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class DefaultImpls {
        public static void onRequestCompleted(IVexFwkSessionCallback iVexFwkSessionCallback, VexFwkSessionTotalResult vexFwkSessionTotalResult) {
            j.e(vexFwkSessionTotalResult, "totalResult");
        }

        public static void onRequestProgressed(IVexFwkSessionCallback iVexFwkSessionCallback, VexFwkSessionPartialResult vexFwkSessionPartialResult) {
            j.e(vexFwkSessionPartialResult, "partialResult");
        }
    }

    void onRequestCompleted(VexFwkSessionTotalResult vexFwkSessionTotalResult);

    void onRequestProgressed(VexFwkSessionPartialResult vexFwkSessionPartialResult);
}
