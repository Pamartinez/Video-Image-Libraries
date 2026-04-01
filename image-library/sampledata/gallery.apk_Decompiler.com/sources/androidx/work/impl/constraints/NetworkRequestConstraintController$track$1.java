package androidx.work.impl.constraints;

import Ae.c;
import L1.d;
import L2.a;
import Vf.C;
import Vf.C0886x;
import Vf.D;
import Xf.q;
import Xf.r;
import android.net.NetworkRequest;
import androidx.work.Constraints;
import kotlin.Metadata;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0003\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00010\u0000H@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"LXf/r;", "Landroidx/work/impl/constraints/ConstraintsState;", "Lme/x;", "<anonymous>", "(LXf/r;)V"}, k = 3, mv = {1, 8, 0})
@C1273e(c = "androidx.work.impl.constraints.NetworkRequestConstraintController$track$1", f = "WorkConstraintsTracker.kt", l = {178}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class NetworkRequestConstraintController$track$1 extends i implements c {
    final /* synthetic */ Constraints $constraints;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ NetworkRequestConstraintController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NetworkRequestConstraintController$track$1(Constraints constraints, NetworkRequestConstraintController networkRequestConstraintController, C1227c cVar) {
        super(2, cVar);
        this.$constraints = constraints;
        this.this$0 = networkRequestConstraintController;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        NetworkRequestConstraintController$track$1 networkRequestConstraintController$track$1 = new NetworkRequestConstraintController$track$1(this.$constraints, this.this$0, cVar);
        networkRequestConstraintController$track$1.L$0 = obj;
        return networkRequestConstraintController$track$1;
    }

    public final Object invoke(r rVar, C1227c cVar) {
        return ((NetworkRequestConstraintController$track$1) create(rVar, cVar)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        int i2 = this.label;
        x xVar = x.f4917a;
        if (i2 == 0) {
            a.A(obj);
            r rVar = (r) this.L$0;
            NetworkRequest requiredNetworkRequest = this.$constraints.getRequiredNetworkRequest();
            if (requiredNetworkRequest == null) {
                q qVar = (q) rVar;
                qVar.getClass();
                qVar.g.k((Throwable) null, false);
                return xVar;
            }
            final Ae.a addCallback = SharedNetworkCallback.INSTANCE.addCallback(this.this$0.connManager, requiredNetworkRequest, new NetworkRequestConstraintController$track$1$onConstraintState$1(D.n(rVar, (C0886x) null, (C) null, new NetworkRequestConstraintController$track$1$timeoutJob$1(this.this$0, rVar, (C1227c) null), 3), rVar));
            AnonymousClass1 r42 = new Ae.a() {
                public final void invoke() {
                    addCallback.invoke();
                }
            };
            this.label = 1;
            if (d.a(rVar, r42, this) == aVar) {
                return aVar;
            }
            return xVar;
        } else if (i2 == 1) {
            a.A(obj);
            return xVar;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
