package com.samsung.android.vexfwk.coroutines;

import Ae.b;
import Vf.A;
import Vf.C0867e0;
import Vf.D;
import Vf.P;
import Vf.n0;
import Zf.a;
import com.samsung.android.vexfwk.sdk.common.f;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;
import qe.C1227c;
import qe.C1232h;
import qe.C1233i;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\b&\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\t\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ'\u0010\u0010\u001a\u00020\r2\u0018\u0010\u000f\u001a\u0014\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0004\u0012\u00020\r0\u000bj\u0002`\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\r\u0010\u0012\u001a\u00020\r¢\u0006\u0004\b\u0012\u0010\u0013J\u000f\u0010\u0014\u001a\u00020\rH\u0016¢\u0006\u0004\b\u0014\u0010\u0013J\r\u0010\u0015\u001a\u00020\r¢\u0006\u0004\b\u0015\u0010\u0013R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\b8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018¨\u0006\u001a"}, d2 = {"Lcom/samsung/android/vexfwk/coroutines/VexFwkBaseScope;", "", "LVf/e0;", "rootJob", "Lqe/h;", "coroutineContext", "<init>", "(LVf/e0;Lqe/h;)V", "LVf/A;", "get", "()LVf/A;", "Lkotlin/Function1;", "", "Lme/x;", "Lkotlinx/coroutines/CompletionHandler;", "handler", "invokeOnComplete", "(LAe/b;)V", "cancel", "()V", "close", "cancelAndClose", "LVf/e0;", "coroutineScope", "LVf/A;", "Companion", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class VexFwkBaseScope {
    public static final Companion Companion = new Companion((e) null);
    private static final String TAG = v.f4727a.b(VexFwkBaseScope.class).o();
    private final A coroutineScope;
    /* access modifiers changed from: private */
    public final C0867e0 rootJob;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/samsung/android/vexfwk/coroutines/VexFwkBaseScope$Companion;", "", "<init>", "()V", "TAG", "", "VexFrameworkSDK_forInternalRelease"}, k = 1, mv = {2, 0, 0}, xi = 48)
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class Companion {
        public /* synthetic */ Companion(e eVar) {
            this();
        }

        private Companion() {
        }
    }

    public VexFwkBaseScope(C0867e0 e0Var, C1232h hVar) {
        j.e(e0Var, "rootJob");
        j.e(hVar, "coroutineContext");
        this.rootJob = e0Var;
        this.coroutineScope = D.a(hVar.plus(e0Var));
    }

    public final void cancel() {
        Object unused = D.r(C1233i.d, new f(this, (C1227c) null, 1));
    }

    public final void cancelAndClose() {
        cancel();
        close();
    }

    public void close() {
        Object unused = D.r(C1233i.d, new a(this, (C1227c) null, 9));
    }

    public final A get() {
        return this.coroutineScope;
    }

    public final void invokeOnComplete(b bVar) {
        j.e(bVar, "handler");
        n0 n0Var = (n0) this.rootJob;
        n0Var.getClass();
        n0Var.D(true, new P(1, bVar));
    }
}
