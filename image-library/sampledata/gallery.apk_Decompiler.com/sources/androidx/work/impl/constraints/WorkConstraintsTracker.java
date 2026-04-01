package androidx.work.impl.constraints;

import Yf.f;
import Yf.g;
import androidx.work.Logger;
import androidx.work.impl.constraints.controllers.BatteryChargingController;
import androidx.work.impl.constraints.controllers.BatteryNotLowController;
import androidx.work.impl.constraints.controllers.ConstraintController;
import androidx.work.impl.constraints.controllers.NetworkConnectedController;
import androidx.work.impl.constraints.controllers.NetworkMeteredController;
import androidx.work.impl.constraints.controllers.NetworkNotRoamingController;
import androidx.work.impl.constraints.controllers.NetworkUnmeteredController;
import androidx.work.impl.constraints.controllers.StorageNotLowController;
import androidx.work.impl.constraints.trackers.Trackers;
import androidx.work.impl.model.WorkSpec;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1192j;
import ne.C1194l;
import ne.C1196n;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0016\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\u0005\u0010\tJ\u001b\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000e\u0010\u000fJ\u0015\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\n¢\u0006\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/work/impl/constraints/WorkConstraintsTracker;", "", "", "Landroidx/work/impl/constraints/controllers/ConstraintController;", "controllers", "<init>", "(Ljava/util/List;)V", "Landroidx/work/impl/constraints/trackers/Trackers;", "trackers", "(Landroidx/work/impl/constraints/trackers/Trackers;)V", "Landroidx/work/impl/model/WorkSpec;", "spec", "LYf/g;", "Landroidx/work/impl/constraints/ConstraintsState;", "track", "(Landroidx/work/impl/model/WorkSpec;)LYf/g;", "workSpec", "", "areAllConstraintsMet", "(Landroidx/work/impl/model/WorkSpec;)Z", "Ljava/util/List;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class WorkConstraintsTracker {
    private final List<ConstraintController> controllers;

    public WorkConstraintsTracker(List<? extends ConstraintController> list) {
        j.e(list, "controllers");
        this.controllers = list;
    }

    public final boolean areAllConstraintsMet(WorkSpec workSpec) {
        j.e(workSpec, "workSpec");
        ArrayList arrayList = new ArrayList();
        for (Object next : this.controllers) {
            if (((ConstraintController) next).isCurrentlyConstrained(workSpec)) {
                arrayList.add(next);
            }
        }
        if (!arrayList.isEmpty()) {
            Logger logger = Logger.get();
            String access$getTAG$p = WorkConstraintsTrackerKt.TAG;
            logger.debug(access$getTAG$p, "Work " + workSpec.id + " constrained by " + C1194l.R0(arrayList, (String) null, (String) null, (String) null, WorkConstraintsTracker$areAllConstraintsMet$1.INSTANCE, 31));
        }
        return arrayList.isEmpty();
    }

    public final g track(WorkSpec workSpec) {
        j.e(workSpec, "spec");
        ArrayList arrayList = new ArrayList();
        for (Object next : this.controllers) {
            if (((ConstraintController) next).hasConstraint(workSpec)) {
                arrayList.add(next);
            }
        }
        ArrayList arrayList2 = new ArrayList(C1196n.w0(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((ConstraintController) it.next()).track(workSpec.constraints));
        }
        return new f(new WorkConstraintsTracker$track$$inlined$combine$1((g[]) C1194l.k1(arrayList2).toArray(new g[0])));
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public WorkConstraintsTracker(Trackers trackers) {
        this((List<? extends ConstraintController>) C1192j.l0(new ConstraintController[]{new BatteryChargingController(trackers.getBatteryChargingTracker()), new BatteryNotLowController(trackers.getBatteryNotLowTracker()), new StorageNotLowController(trackers.getStorageNotLowTracker()), new NetworkConnectedController(trackers.getNetworkStateTracker()), new NetworkUnmeteredController(trackers.getNetworkStateTracker()), new NetworkNotRoamingController(trackers.getNetworkStateTracker()), new NetworkMeteredController(trackers.getNetworkStateTracker()), WorkConstraintsTrackerKt.NetworkRequestConstraintController(trackers.getContext())}));
        j.e(trackers, "trackers");
    }
}
