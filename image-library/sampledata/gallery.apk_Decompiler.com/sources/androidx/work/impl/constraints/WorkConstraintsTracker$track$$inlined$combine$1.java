package androidx.work.impl.constraints;

import Ae.a;
import Ae.d;
import Vf.v0;
import Yf.g;
import Yf.h;
import androidx.work.impl.constraints.ConstraintsState;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J!\u0010\u0005\u001a\u00020\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "LYf/g;", "LYf/h;", "collector", "Lme/x;", "collect", "(LYf/h;Lqe/c;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WorkConstraintsTracker$track$$inlined$combine$1 implements g {
    final /* synthetic */ g[] $flowArray$inlined;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0006\u001a\u00020\u0005\"\u0006\b\u0000\u0010\u0000\u0018\u0001\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00010\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H@¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"T", "R", "LYf/h;", "", "it", "Lme/x;", "<anonymous>", "(LYf/h;Lkotlin/Array;)V"}, k = 3, mv = {1, 8, 0})
    @C1273e(c = "androidx.work.impl.constraints.WorkConstraintsTracker$track$$inlined$combine$1$3", f = "WorkConstraintsTracker.kt", l = {292}, m = "invokeSuspend")
    /* renamed from: androidx.work.impl.constraints.WorkConstraintsTracker$track$$inlined$combine$1$3  reason: invalid class name */
    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static final class AnonymousClass3 extends i implements d {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        public final Object invoke(h hVar, ConstraintsState[] constraintsStateArr, C1227c cVar) {
            AnonymousClass3 r0 = new AnonymousClass3(cVar);
            r0.L$0 = hVar;
            r0.L$1 = constraintsStateArr;
            return r0.invokeSuspend(x.f4917a);
        }

        public final Object invokeSuspend(Object obj) {
            ConstraintsState constraintsState;
            C1245a aVar = C1245a.COROUTINE_SUSPENDED;
            int i2 = this.label;
            if (i2 == 0) {
                L2.a.A(obj);
                h hVar = (h) this.L$0;
                ConstraintsState[] constraintsStateArr = (ConstraintsState[]) ((Object[]) this.L$1);
                int length = constraintsStateArr.length;
                int i7 = 0;
                while (true) {
                    if (i7 >= length) {
                        constraintsState = null;
                        break;
                    }
                    constraintsState = constraintsStateArr[i7];
                    if (!j.a(constraintsState, ConstraintsState.ConstraintsMet.INSTANCE)) {
                        break;
                    }
                    i7++;
                }
                if (constraintsState == null) {
                    constraintsState = ConstraintsState.ConstraintsMet.INSTANCE;
                }
                this.label = 1;
                if (hVar.emit(constraintsState, this) == aVar) {
                    return aVar;
                }
            } else if (i2 == 1) {
                L2.a.A(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return x.f4917a;
        }
    }

    public WorkConstraintsTracker$track$$inlined$combine$1(g[] gVarArr) {
        this.$flowArray$inlined = gVarArr;
    }

    public Object collect(h hVar, C1227c cVar) {
        final g[] gVarArr = this.$flowArray$inlined;
        Zf.g gVar = new Zf.g(gVarArr, new a() {
            public final ConstraintsState[] invoke() {
                return new ConstraintsState[gVarArr.length];
            }
        }, new AnonymousClass3((C1227c) null), hVar, (C1227c) null);
        v0 v0Var = new v0(cVar.getContext(), cVar, 1);
        Object w = L1.d.w(v0Var, v0Var, gVar);
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        x xVar = x.f4917a;
        if (w != aVar) {
            w = xVar;
        }
        if (w == aVar) {
            return w;
        }
        return xVar;
    }
}
