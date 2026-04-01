package androidx.work.impl.constraints.controllers;

import Xf.q;
import Xf.r;
import androidx.work.impl.constraints.ConstraintListener;
import androidx.work.impl.constraints.ConstraintsState;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0013\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u0017\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"androidx/work/impl/constraints/controllers/BaseConstraintController$track$1$listener$1", "Landroidx/work/impl/constraints/ConstraintListener;", "newValue", "Lme/x;", "onConstraintChanged", "(Ljava/lang/Object;)V", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class BaseConstraintController$track$1$listener$1 implements ConstraintListener<T> {
    final /* synthetic */ r $$this$callbackFlow;
    final /* synthetic */ BaseConstraintController<T> this$0;

    public BaseConstraintController$track$1$listener$1(BaseConstraintController<T> baseConstraintController, r rVar) {
        this.this$0 = baseConstraintController;
        this.$$this$callbackFlow = rVar;
    }

    public void onConstraintChanged(T t) {
        Object obj;
        if (this.this$0.isConstrained(t)) {
            obj = new ConstraintsState.ConstraintsNotMet(this.this$0.getReason());
        } else {
            obj = ConstraintsState.ConstraintsMet.INSTANCE;
        }
        q qVar = (q) this.$$this$callbackFlow;
        qVar.getClass();
        qVar.e(obj);
    }
}
