package androidx.work.impl.constraints;

import Xf.a;
import Yf.c;
import Yf.g;
import android.net.ConnectivityManager;
import androidx.work.Constraints;
import androidx.work.impl.constraints.controllers.ConstraintController;
import androidx.work.impl.model.WorkSpec;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import qe.C1227c;
import qe.C1233i;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0013\u0010\u0012R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0014R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0015¨\u0006\u0016"}, d2 = {"Landroidx/work/impl/constraints/NetworkRequestConstraintController;", "Landroidx/work/impl/constraints/controllers/ConstraintController;", "Landroid/net/ConnectivityManager;", "connManager", "", "timeoutMs", "<init>", "(Landroid/net/ConnectivityManager;J)V", "Landroidx/work/Constraints;", "constraints", "LYf/g;", "Landroidx/work/impl/constraints/ConstraintsState;", "track", "(Landroidx/work/Constraints;)LYf/g;", "Landroidx/work/impl/model/WorkSpec;", "workSpec", "", "hasConstraint", "(Landroidx/work/impl/model/WorkSpec;)Z", "isCurrentlyConstrained", "Landroid/net/ConnectivityManager;", "J", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class NetworkRequestConstraintController implements ConstraintController {
    /* access modifiers changed from: private */
    public final ConnectivityManager connManager;
    /* access modifiers changed from: private */
    public final long timeoutMs;

    public NetworkRequestConstraintController(ConnectivityManager connectivityManager, long j2) {
        j.e(connectivityManager, "connManager");
        this.connManager = connectivityManager;
        this.timeoutMs = j2;
    }

    public boolean hasConstraint(WorkSpec workSpec) {
        j.e(workSpec, "workSpec");
        if (workSpec.constraints.getRequiredNetworkRequest() != null) {
            return true;
        }
        return false;
    }

    public boolean isCurrentlyConstrained(WorkSpec workSpec) {
        j.e(workSpec, "workSpec");
        if (!hasConstraint(workSpec)) {
            return false;
        }
        throw new IllegalStateException("isCurrentlyConstrained() must never be called onNetworkRequestConstraintController. isCurrentlyConstrained() is called only on older platforms where NetworkRequest isn't supported");
    }

    public g track(Constraints constraints) {
        j.e(constraints, "constraints");
        return new c(new NetworkRequestConstraintController$track$1(constraints, this, (C1227c) null), C1233i.d, -2, a.SUSPEND);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ NetworkRequestConstraintController(ConnectivityManager connectivityManager, long j2, int i2, e eVar) {
        this(connectivityManager, (i2 & 2) != 0 ? 1000 : j2);
    }
}
