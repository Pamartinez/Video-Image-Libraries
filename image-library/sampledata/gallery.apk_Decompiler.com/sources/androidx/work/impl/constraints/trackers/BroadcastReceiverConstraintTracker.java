package androidx.work.impl.constraints.trackers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.work.Logger;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b'\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tH&¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u0010\u0010\u000fR\u0014\u0010\u0012\u001a\u00020\u00118\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0017\u001a\u00020\u00148&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0018"}, d2 = {"Landroidx/work/impl/constraints/trackers/BroadcastReceiverConstraintTracker;", "T", "Landroidx/work/impl/constraints/trackers/ConstraintTracker;", "Landroid/content/Context;", "context", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "taskExecutor", "<init>", "(Landroid/content/Context;Landroidx/work/impl/utils/taskexecutor/TaskExecutor;)V", "Landroid/content/Intent;", "intent", "Lme/x;", "onBroadcastReceive", "(Landroid/content/Intent;)V", "startTracking", "()V", "stopTracking", "Landroid/content/BroadcastReceiver;", "broadcastReceiver", "Landroid/content/BroadcastReceiver;", "Landroid/content/IntentFilter;", "getIntentFilter", "()Landroid/content/IntentFilter;", "intentFilter", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class BroadcastReceiverConstraintTracker<T> extends ConstraintTracker<T> {
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiverConstraintTracker$broadcastReceiver$1(this);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BroadcastReceiverConstraintTracker(Context context, TaskExecutor taskExecutor) {
        super(context, taskExecutor);
        j.e(context, "context");
        j.e(taskExecutor, "taskExecutor");
    }

    public abstract IntentFilter getIntentFilter();

    public abstract void onBroadcastReceive(Intent intent);

    public void startTracking() {
        Logger.get().debug(BroadcastReceiverConstraintTrackerKt.TAG, getClass().getSimpleName().concat(": registering receiver"));
        getAppContext().registerReceiver(this.broadcastReceiver, getIntentFilter());
    }

    public void stopTracking() {
        Logger.get().debug(BroadcastReceiverConstraintTrackerKt.TAG, getClass().getSimpleName().concat(": unregistering receiver"));
        getAppContext().unregisterReceiver(this.broadcastReceiver);
    }
}
