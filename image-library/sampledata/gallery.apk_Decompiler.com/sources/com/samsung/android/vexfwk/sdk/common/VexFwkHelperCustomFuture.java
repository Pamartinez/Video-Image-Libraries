package com.samsung.android.vexfwk.sdk.common;

import Ae.b;
import Vf.A;
import Vf.D;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;
import qe.C1227c;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\u0018\u0000 \u000e*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002:\u0001\u000eB-\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u001c\u0010\b\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00028\u0000H@¢\u0006\u0004\b\u000b\u0010\fR*\u0010\b\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00058\u0002X\u0004¢\u0006\u0006\n\u0004\b\b\u0010\r¨\u0006\u000f"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperCustomFuture;", "T", "Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperBaseFuture;", "LVf/A;", "coroutineScope", "Lkotlin/Function1;", "Lqe/c;", "", "resultSupplier", "<init>", "(LVf/A;LAe/b;)V", "execute", "(Lqe/c;)Ljava/lang/Object;", "LAe/b;", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class VexFwkHelperCustomFuture<T> extends VexFwkHelperBaseFuture<T> {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG = v.f4727a.b(VexFwkHelperCustomFuture.class).o();
    private final b resultSupplier;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/vexfwk/sdk/common/VexFwkHelperCustomFuture$Companion;", "", "<init>", "()V", "TAG", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VexFwkHelperCustomFuture(A a7, b bVar) {
        super(a7);
        j.e(a7, "coroutineScope");
        j.e(bVar, "resultSupplier");
        this.resultSupplier = bVar;
    }

    public Object execute(C1227c cVar) {
        D.f(cVar.getContext());
        return this.resultSupplier.invoke(cVar);
    }
}
