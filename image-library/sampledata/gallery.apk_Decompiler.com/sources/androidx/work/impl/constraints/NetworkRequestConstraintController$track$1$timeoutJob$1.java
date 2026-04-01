package androidx.work.impl.constraints;

import Ae.c;
import L2.a;
import Vf.A;
import Vf.D;
import Xf.q;
import Xf.r;
import androidx.work.Logger;
import androidx.work.impl.constraints.ConstraintsState;
import kotlin.Metadata;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"LVf/A;", "Lme/x;", "<anonymous>", "(LVf/A;)V"}, k = 3, mv = {1, 8, 0})
@C1273e(c = "androidx.work.impl.constraints.NetworkRequestConstraintController$track$1$timeoutJob$1", f = "WorkConstraintsTracker.kt", l = {149}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class NetworkRequestConstraintController$track$1$timeoutJob$1 extends i implements c {
    final /* synthetic */ r $$this$callbackFlow;
    int label;
    final /* synthetic */ NetworkRequestConstraintController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NetworkRequestConstraintController$track$1$timeoutJob$1(NetworkRequestConstraintController networkRequestConstraintController, r rVar, C1227c cVar) {
        super(2, cVar);
        this.this$0 = networkRequestConstraintController;
        this.$$this$callbackFlow = rVar;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new NetworkRequestConstraintController$track$1$timeoutJob$1(this.this$0, this.$$this$callbackFlow, cVar);
    }

    public final Object invoke(A a7, C1227c cVar) {
        return ((NetworkRequestConstraintController$track$1$timeoutJob$1) create(a7, cVar)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        int i2 = this.label;
        if (i2 == 0) {
            a.A(obj);
            long access$getTimeoutMs$p = this.this$0.timeoutMs;
            this.label = 1;
            if (D.e(access$getTimeoutMs$p, this) == aVar) {
                return aVar;
            }
        } else if (i2 == 1) {
            a.A(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Logger logger = Logger.get();
        String access$getTAG$p = WorkConstraintsTrackerKt.TAG;
        logger.debug(access$getTAG$p, "NetworkRequestConstraintController didn't receive neither onCapabilitiesChanged/onLost callback, sending `ConstraintsNotMet` after " + this.this$0.timeoutMs + " ms");
        ((q) this.$$this$callbackFlow).e(new ConstraintsState.ConstraintsNotMet(7));
        return x.f4917a;
    }
}
