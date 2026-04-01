package androidx.work.impl.constraints;

import Ae.c;
import L2.a;
import Vf.A;
import Yf.g;
import Yf.h;
import androidx.work.impl.model.WorkSpec;
import kotlin.Metadata;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.C1273e;
import se.i;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H@¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"LVf/A;", "Lme/x;", "<anonymous>", "(LVf/A;)V"}, k = 3, mv = {1, 8, 0})
@C1273e(c = "androidx.work.impl.constraints.WorkConstraintsTrackerKt$listen$1", f = "WorkConstraintsTracker.kt", l = {67}, m = "invokeSuspend")
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WorkConstraintsTrackerKt$listen$1 extends i implements c {
    final /* synthetic */ OnConstraintsStateChangedListener $listener;
    final /* synthetic */ WorkSpec $spec;
    final /* synthetic */ WorkConstraintsTracker $this_listen;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WorkConstraintsTrackerKt$listen$1(WorkConstraintsTracker workConstraintsTracker, WorkSpec workSpec, OnConstraintsStateChangedListener onConstraintsStateChangedListener, C1227c cVar) {
        super(2, cVar);
        this.$this_listen = workConstraintsTracker;
        this.$spec = workSpec;
        this.$listener = onConstraintsStateChangedListener;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new WorkConstraintsTrackerKt$listen$1(this.$this_listen, this.$spec, this.$listener, cVar);
    }

    public final Object invoke(A a7, C1227c cVar) {
        return ((WorkConstraintsTrackerKt$listen$1) create(a7, cVar)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        int i2 = this.label;
        if (i2 == 0) {
            a.A(obj);
            g track = this.$this_listen.track(this.$spec);
            final OnConstraintsStateChangedListener onConstraintsStateChangedListener = this.$listener;
            final WorkSpec workSpec = this.$spec;
            AnonymousClass1 r1 = new h() {
                public final Object emit(ConstraintsState constraintsState, C1227c cVar) {
                    onConstraintsStateChangedListener.onConstraintsStateChanged(workSpec, constraintsState);
                    return x.f4917a;
                }
            };
            this.label = 1;
            if (track.collect(r1, this) == aVar) {
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
