package androidx.work.impl.constraints.trackers;

import android.content.Context;
import androidx.work.impl.constraints.NetworkState;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001BQ\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\u000eR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010¨\u0006\u0017"}, d2 = {"Landroidx/work/impl/constraints/trackers/Trackers;", "", "context", "Landroid/content/Context;", "taskExecutor", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "batteryChargingTracker", "Landroidx/work/impl/constraints/trackers/ConstraintTracker;", "", "batteryNotLowTracker", "Landroidx/work/impl/constraints/trackers/BatteryNotLowTracker;", "networkStateTracker", "Landroidx/work/impl/constraints/NetworkState;", "storageNotLowTracker", "(Landroid/content/Context;Landroidx/work/impl/utils/taskexecutor/TaskExecutor;Landroidx/work/impl/constraints/trackers/ConstraintTracker;Landroidx/work/impl/constraints/trackers/BatteryNotLowTracker;Landroidx/work/impl/constraints/trackers/ConstraintTracker;Landroidx/work/impl/constraints/trackers/ConstraintTracker;)V", "getBatteryChargingTracker", "()Landroidx/work/impl/constraints/trackers/ConstraintTracker;", "getBatteryNotLowTracker", "()Landroidx/work/impl/constraints/trackers/BatteryNotLowTracker;", "getContext", "()Landroid/content/Context;", "getNetworkStateTracker", "getStorageNotLowTracker", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Trackers {
    private final ConstraintTracker<Boolean> batteryChargingTracker;
    private final BatteryNotLowTracker batteryNotLowTracker;
    private final Context context;
    private final ConstraintTracker<NetworkState> networkStateTracker;
    private final ConstraintTracker<Boolean> storageNotLowTracker;

    public Trackers(Context context2, TaskExecutor taskExecutor, ConstraintTracker<Boolean> constraintTracker, BatteryNotLowTracker batteryNotLowTracker2, ConstraintTracker<NetworkState> constraintTracker2, ConstraintTracker<Boolean> constraintTracker3) {
        j.e(context2, "context");
        j.e(taskExecutor, "taskExecutor");
        j.e(constraintTracker, "batteryChargingTracker");
        j.e(batteryNotLowTracker2, "batteryNotLowTracker");
        j.e(constraintTracker2, "networkStateTracker");
        j.e(constraintTracker3, "storageNotLowTracker");
        this.context = context2;
        this.batteryChargingTracker = constraintTracker;
        this.batteryNotLowTracker = batteryNotLowTracker2;
        this.networkStateTracker = constraintTracker2;
        this.storageNotLowTracker = constraintTracker3;
    }

    public final ConstraintTracker<Boolean> getBatteryChargingTracker() {
        return this.batteryChargingTracker;
    }

    public final BatteryNotLowTracker getBatteryNotLowTracker() {
        return this.batteryNotLowTracker;
    }

    public final Context getContext() {
        return this.context;
    }

    public final ConstraintTracker<NetworkState> getNetworkStateTracker() {
        return this.networkStateTracker;
    }

    public final ConstraintTracker<Boolean> getStorageNotLowTracker() {
        return this.storageNotLowTracker;
    }

    public /* synthetic */ Trackers(Context context2, TaskExecutor taskExecutor, ConstraintTracker constraintTracker, BatteryNotLowTracker batteryNotLowTracker2, ConstraintTracker constraintTracker2, ConstraintTracker constraintTracker3, int i2, e eVar) {
        BatteryChargingTracker batteryChargingTracker2;
        BatteryNotLowTracker batteryNotLowTracker3;
        ConstraintTracker<NetworkState> constraintTracker4;
        StorageNotLowTracker storageNotLowTracker2;
        TaskExecutor taskExecutor2;
        Context context3;
        Trackers trackers;
        if ((i2 & 4) != 0) {
            Context applicationContext = context2.getApplicationContext();
            j.d(applicationContext, "context.applicationContext");
            batteryChargingTracker2 = new BatteryChargingTracker(applicationContext, taskExecutor);
        } else {
            batteryChargingTracker2 = constraintTracker;
        }
        if ((i2 & 8) != 0) {
            Context applicationContext2 = context2.getApplicationContext();
            j.d(applicationContext2, "context.applicationContext");
            batteryNotLowTracker3 = new BatteryNotLowTracker(applicationContext2, taskExecutor);
        } else {
            batteryNotLowTracker3 = batteryNotLowTracker2;
        }
        if ((i2 & 16) != 0) {
            Context applicationContext3 = context2.getApplicationContext();
            j.d(applicationContext3, "context.applicationContext");
            constraintTracker4 = NetworkStateTrackerKt.NetworkStateTracker(applicationContext3, taskExecutor);
        } else {
            constraintTracker4 = constraintTracker2;
        }
        if ((i2 & 32) != 0) {
            Context applicationContext4 = context2.getApplicationContext();
            j.d(applicationContext4, "context.applicationContext");
            storageNotLowTracker2 = new StorageNotLowTracker(applicationContext4, taskExecutor);
            context3 = context2;
            taskExecutor2 = taskExecutor;
            trackers = this;
        } else {
            storageNotLowTracker2 = constraintTracker3;
            trackers = this;
            context3 = context2;
            taskExecutor2 = taskExecutor;
        }
        new Trackers(context3, taskExecutor2, batteryChargingTracker2, batteryNotLowTracker3, constraintTracker4, storageNotLowTracker2);
    }
}
