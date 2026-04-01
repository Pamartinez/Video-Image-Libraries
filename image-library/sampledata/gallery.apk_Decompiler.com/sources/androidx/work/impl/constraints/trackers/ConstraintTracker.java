package androidx.work.impl.constraints.trackers;

import android.content.Context;
import androidx.work.Logger;
import androidx.work.impl.constraints.ConstraintListener;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import ne.C1194l;
import q6.e;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\t\b'\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0019\b\u0004\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u001b\u0010\f\u001a\u00020\u000b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\t¢\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u000e\u001a\u00020\u000b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\t¢\u0006\u0004\b\u000e\u0010\rJ\u000f\u0010\u000f\u001a\u00028\u0000H&¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\u000bH&¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\u0013\u001a\u00020\u000bH&¢\u0006\u0004\b\u0013\u0010\u0012R\u0014\u0010\u0006\u001a\u00020\u00058\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u00038\u0004X\u0004¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR \u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t0\u001b8\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0018\u0010\u001e\u001a\u0004\u0018\u00018\u00008\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010\u001aR$\u0010#\u001a\u00028\u00002\u0006\u0010\u001f\u001a\u00028\u00008F@FX\u000e¢\u0006\f\u001a\u0004\b \u0010\u0010\"\u0004\b!\u0010\"¨\u0006$"}, d2 = {"Landroidx/work/impl/constraints/trackers/ConstraintTracker;", "T", "", "Landroid/content/Context;", "context", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "taskExecutor", "<init>", "(Landroid/content/Context;Landroidx/work/impl/utils/taskexecutor/TaskExecutor;)V", "Landroidx/work/impl/constraints/ConstraintListener;", "listener", "Lme/x;", "addListener", "(Landroidx/work/impl/constraints/ConstraintListener;)V", "removeListener", "readSystemState", "()Ljava/lang/Object;", "startTracking", "()V", "stopTracking", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "appContext", "Landroid/content/Context;", "getAppContext", "()Landroid/content/Context;", "lock", "Ljava/lang/Object;", "Ljava/util/LinkedHashSet;", "listeners", "Ljava/util/LinkedHashSet;", "currentState", "newState", "getState", "setState", "(Ljava/lang/Object;)V", "state", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ConstraintTracker<T> {
    private final Context appContext;
    private T currentState;
    private final LinkedHashSet<ConstraintListener<T>> listeners = new LinkedHashSet<>();
    private final Object lock = new Object();
    private final TaskExecutor taskExecutor;

    public ConstraintTracker(Context context, TaskExecutor taskExecutor2) {
        j.e(context, "context");
        j.e(taskExecutor2, "taskExecutor");
        this.taskExecutor = taskExecutor2;
        Context applicationContext = context.getApplicationContext();
        j.d(applicationContext, "context.applicationContext");
        this.appContext = applicationContext;
    }

    /* access modifiers changed from: private */
    public static final void _set_state_$lambda$4$lambda$3(List list, ConstraintTracker constraintTracker) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((ConstraintListener) it.next()).onConstraintChanged(constraintTracker.currentState);
        }
    }

    public final void addListener(ConstraintListener<T> constraintListener) {
        j.e(constraintListener, "listener");
        synchronized (this.lock) {
            try {
                if (this.listeners.add(constraintListener)) {
                    if (this.listeners.size() == 1) {
                        this.currentState = readSystemState();
                        Logger logger = Logger.get();
                        String access$getTAG$p = ConstraintTrackerKt.TAG;
                        logger.debug(access$getTAG$p, getClass().getSimpleName() + ": initial state = " + this.currentState);
                        startTracking();
                    }
                    constraintListener.onConstraintChanged(this.currentState);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final Context getAppContext() {
        return this.appContext;
    }

    public abstract T readSystemState();

    public final void removeListener(ConstraintListener<T> constraintListener) {
        j.e(constraintListener, "listener");
        synchronized (this.lock) {
            if (this.listeners.remove(constraintListener) && this.listeners.isEmpty()) {
                stopTracking();
            }
        }
    }

    public final void setState(T t) {
        synchronized (this.lock) {
            T t3 = this.currentState;
            if (t3 == null || !t3.equals(t)) {
                this.currentState = t;
                this.taskExecutor.getMainThreadExecutor().execute(new e(5, C1194l.k1(this.listeners), this));
            }
        }
    }

    public abstract void startTracking();

    public abstract void stopTracking();
}
