package androidx.work.impl.background.greedy;

import androidx.work.RunnableScheduler;
import androidx.work.impl.StartStopToken;
import androidx.work.impl.WorkLauncher;
import h.C0199b;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B#\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0015\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u000f\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0010R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0011R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00018\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R \u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00160\u00158\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Landroidx/work/impl/background/greedy/TimeLimiter;", "", "Landroidx/work/RunnableScheduler;", "runnableScheduler", "Landroidx/work/impl/WorkLauncher;", "launcher", "", "timeoutMs", "<init>", "(Landroidx/work/RunnableScheduler;Landroidx/work/impl/WorkLauncher;J)V", "Landroidx/work/impl/StartStopToken;", "token", "Lme/x;", "track", "(Landroidx/work/impl/StartStopToken;)V", "cancel", "Landroidx/work/RunnableScheduler;", "Landroidx/work/impl/WorkLauncher;", "J", "lock", "Ljava/lang/Object;", "", "Ljava/lang/Runnable;", "tracked", "Ljava/util/Map;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class TimeLimiter {
    private final WorkLauncher launcher;
    private final Object lock;
    private final RunnableScheduler runnableScheduler;
    private final long timeoutMs;
    private final Map<StartStopToken, Runnable> tracked;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public TimeLimiter(RunnableScheduler runnableScheduler2, WorkLauncher workLauncher) {
        this(runnableScheduler2, workLauncher, 0, 4, (e) null);
        j.e(runnableScheduler2, "runnableScheduler");
        j.e(workLauncher, "launcher");
    }

    /* access modifiers changed from: private */
    public static final void track$lambda$0(TimeLimiter timeLimiter, StartStopToken startStopToken) {
        timeLimiter.launcher.stopWork(startStopToken, 3);
    }

    public final void cancel(StartStopToken startStopToken) {
        Runnable remove;
        j.e(startStopToken, "token");
        synchronized (this.lock) {
            remove = this.tracked.remove(startStopToken);
        }
        if (remove != null) {
            this.runnableScheduler.cancel(remove);
        }
    }

    public final void track(StartStopToken startStopToken) {
        j.e(startStopToken, "token");
        C0199b bVar = new C0199b(15, this, startStopToken);
        synchronized (this.lock) {
            Runnable put = this.tracked.put(startStopToken, bVar);
        }
        this.runnableScheduler.scheduleWithDelay(this.timeoutMs, bVar);
    }

    public TimeLimiter(RunnableScheduler runnableScheduler2, WorkLauncher workLauncher, long j2) {
        j.e(runnableScheduler2, "runnableScheduler");
        j.e(workLauncher, "launcher");
        this.runnableScheduler = runnableScheduler2;
        this.launcher = workLauncher;
        this.timeoutMs = j2;
        this.lock = new Object();
        this.tracked = new LinkedHashMap();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ TimeLimiter(RunnableScheduler runnableScheduler2, WorkLauncher workLauncher, long j2, int i2, e eVar) {
        this(runnableScheduler2, workLauncher, (i2 & 4) != 0 ? TimeUnit.MINUTES.toMillis(90) : j2);
    }
}
