package androidx.work.impl.constraints;

import Ae.b;
import Vf.C0867e0;
import Xf.q;
import Xf.r;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.k;
import me.x;

@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/work/impl/constraints/ConstraintsState;", "it", "Lme/x;", "invoke", "(Landroidx/work/impl/constraints/ConstraintsState;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class NetworkRequestConstraintController$track$1$onConstraintState$1 extends k implements b {
    final /* synthetic */ r $$this$callbackFlow;
    final /* synthetic */ C0867e0 $timeoutJob;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NetworkRequestConstraintController$track$1$onConstraintState$1(C0867e0 e0Var, r rVar) {
        super(1);
        this.$timeoutJob = e0Var;
        this.$$this$callbackFlow = rVar;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ConstraintsState) obj);
        return x.f4917a;
    }

    public final void invoke(ConstraintsState constraintsState) {
        j.e(constraintsState, "it");
        this.$timeoutJob.a((CancellationException) null);
        ((q) this.$$this$callbackFlow).e(constraintsState);
    }
}
