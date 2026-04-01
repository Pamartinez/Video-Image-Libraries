package androidx.work.impl.constraints.controllers;

import Xf.a;
import Yf.c;
import Yf.g;
import androidx.work.Constraints;
import androidx.work.impl.constraints.trackers.ConstraintTracker;
import androidx.work.impl.model.WorkSpec;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import qe.C1227c;
import qe.C1233i;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00028\u0000H\u0014¢\u0006\u0004\b\t\u0010\nJ\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u00038\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0015R\u001a\u0010\u001b\u001a\u00020\u00168$X¤\u0004¢\u0006\f\u0012\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001c"}, d2 = {"Landroidx/work/impl/constraints/controllers/BaseConstraintController;", "T", "Landroidx/work/impl/constraints/controllers/ConstraintController;", "Landroidx/work/impl/constraints/trackers/ConstraintTracker;", "tracker", "<init>", "(Landroidx/work/impl/constraints/trackers/ConstraintTracker;)V", "value", "", "isConstrained", "(Ljava/lang/Object;)Z", "Landroidx/work/Constraints;", "constraints", "LYf/g;", "Landroidx/work/impl/constraints/ConstraintsState;", "track", "(Landroidx/work/Constraints;)LYf/g;", "Landroidx/work/impl/model/WorkSpec;", "workSpec", "isCurrentlyConstrained", "(Landroidx/work/impl/model/WorkSpec;)Z", "Landroidx/work/impl/constraints/trackers/ConstraintTracker;", "", "getReason", "()I", "getReason$annotations", "()V", "reason", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BaseConstraintController<T> implements ConstraintController {
    /* access modifiers changed from: private */
    public final ConstraintTracker<T> tracker;

    public BaseConstraintController(ConstraintTracker<T> constraintTracker) {
        j.e(constraintTracker, "tracker");
        this.tracker = constraintTracker;
    }

    public abstract int getReason();

    public abstract boolean isConstrained(T t);

    public boolean isCurrentlyConstrained(WorkSpec workSpec) {
        j.e(workSpec, "workSpec");
        if (!hasConstraint(workSpec) || !isConstrained(this.tracker.readSystemState())) {
            return false;
        }
        return true;
    }

    public g track(Constraints constraints) {
        j.e(constraints, "constraints");
        return new c(new BaseConstraintController$track$1(this, (C1227c) null), C1233i.d, -2, a.SUSPEND);
    }
}
