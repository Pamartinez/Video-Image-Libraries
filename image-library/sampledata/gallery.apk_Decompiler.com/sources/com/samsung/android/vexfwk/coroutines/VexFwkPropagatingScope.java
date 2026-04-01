package com.samsung.android.vexfwk.coroutines;

import Vf.A;
import Vf.g0;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/vexfwk/coroutines/VexFwkPropagatingScope;", "Lcom/samsung/android/vexfwk/coroutines/VexFwkBaseScope;", "LVf/A;", "parentScope", "<init>", "(LVf/A;)V", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkPropagatingScope extends VexFwkBaseScope {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VexFwkPropagatingScope(A a7) {
        super(new g0(), a7.getCoroutineContext());
        j.e(a7, "parentScope");
    }
}
