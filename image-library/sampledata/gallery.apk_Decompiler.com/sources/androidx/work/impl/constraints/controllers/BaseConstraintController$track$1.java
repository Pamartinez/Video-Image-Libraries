package androidx.work.impl.constraints.controllers;

import Ae.c;
import L1.d;
import L2.a;
import Xf.r;
import kotlin.Metadata;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00020\u00020\u0001H@¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "LXf/r;", "Landroidx/work/impl/constraints/ConstraintsState;", "Lme/x;", "<anonymous>", "(LXf/r;)V"}, k = 3, mv = {1, 8, 0})
@C1273e(c = "androidx.work.impl.constraints.controllers.BaseConstraintController$track$1", f = "ContraintControllers.kt", l = {63}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class BaseConstraintController$track$1 extends i implements c {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ BaseConstraintController<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BaseConstraintController$track$1(BaseConstraintController<T> baseConstraintController, C1227c cVar) {
        super(2, cVar);
        this.this$0 = baseConstraintController;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        BaseConstraintController$track$1 baseConstraintController$track$1 = new BaseConstraintController$track$1(this.this$0, cVar);
        baseConstraintController$track$1.L$0 = obj;
        return baseConstraintController$track$1;
    }

    public final Object invoke(r rVar, C1227c cVar) {
        return ((BaseConstraintController$track$1) create(rVar, cVar)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        int i2 = this.label;
        if (i2 == 0) {
            a.A(obj);
            r rVar = (r) this.L$0;
            final BaseConstraintController$track$1$listener$1 baseConstraintController$track$1$listener$1 = new BaseConstraintController$track$1$listener$1(this.this$0, rVar);
            this.this$0.tracker.addListener(baseConstraintController$track$1$listener$1);
            final BaseConstraintController<T> baseConstraintController = this.this$0;
            AnonymousClass1 r32 = new Ae.a() {
                public final void invoke() {
                    baseConstraintController.tracker.removeListener(baseConstraintController$track$1$listener$1);
                }
            };
            this.label = 1;
            if (d.a(rVar, r32, this) == aVar) {
                return aVar;
            }
        } else if (i2 == 1) {
            a.A(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return x.f4917a;
    }
}
