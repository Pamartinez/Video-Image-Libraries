package androidx.work.impl.constraints.trackers;

import android.content.Context;
import android.net.ConnectivityManager;
import androidx.work.Logger;
import androidx.work.impl.constraints.NetworkState;
import androidx.work.impl.utils.NetworkApi21;
import androidx.work.impl.utils.NetworkApi24;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0004*\u0001\u0012\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\t\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\f\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\u000e\u0010\rR\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/work/impl/constraints/trackers/NetworkStateTracker24;", "Landroidx/work/impl/constraints/trackers/ConstraintTracker;", "Landroidx/work/impl/constraints/NetworkState;", "Landroid/content/Context;", "context", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "taskExecutor", "<init>", "(Landroid/content/Context;Landroidx/work/impl/utils/taskexecutor/TaskExecutor;)V", "readSystemState", "()Landroidx/work/impl/constraints/NetworkState;", "Lme/x;", "startTracking", "()V", "stopTracking", "Landroid/net/ConnectivityManager;", "connectivityManager", "Landroid/net/ConnectivityManager;", "androidx/work/impl/constraints/trackers/NetworkStateTracker24$networkCallback$1", "networkCallback", "Landroidx/work/impl/constraints/trackers/NetworkStateTracker24$networkCallback$1;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class NetworkStateTracker24 extends ConstraintTracker<NetworkState> {
    /* access modifiers changed from: private */
    public final ConnectivityManager connectivityManager;
    private final NetworkStateTracker24$networkCallback$1 networkCallback = new NetworkStateTracker24$networkCallback$1(this);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NetworkStateTracker24(Context context, TaskExecutor taskExecutor) {
        super(context, taskExecutor);
        j.e(context, "context");
        j.e(taskExecutor, "taskExecutor");
        Object systemService = getAppContext().getSystemService("connectivity");
        j.c(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        this.connectivityManager = (ConnectivityManager) systemService;
    }

    public void startTracking() {
        try {
            Logger.get().debug(NetworkStateTrackerKt.TAG, "Registering network callback");
            NetworkApi24.registerDefaultNetworkCallbackCompat(this.connectivityManager, this.networkCallback);
        } catch (IllegalArgumentException e) {
            Logger.get().error(NetworkStateTrackerKt.TAG, "Received exception while registering network callback", e);
        } catch (SecurityException e7) {
            Logger.get().error(NetworkStateTrackerKt.TAG, "Received exception while registering network callback", e7);
        }
    }

    public void stopTracking() {
        try {
            Logger.get().debug(NetworkStateTrackerKt.TAG, "Unregistering network callback");
            NetworkApi21.unregisterNetworkCallbackCompat(this.connectivityManager, this.networkCallback);
        } catch (IllegalArgumentException e) {
            Logger.get().error(NetworkStateTrackerKt.TAG, "Received exception while unregistering network callback", e);
        } catch (SecurityException e7) {
            Logger.get().error(NetworkStateTrackerKt.TAG, "Received exception while unregistering network callback", e7);
        }
    }

    public NetworkState readSystemState() {
        return NetworkStateTrackerKt.getActiveNetworkState(this.connectivityManager);
    }
}
