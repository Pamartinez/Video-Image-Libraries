package Vf;

import P1.e;
import eg.f;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import qe.C1232h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Z extends Y implements I {
    public final Executor d;

    public Z(Executor executor) {
        this.d = executor;
        if (executor instanceof ScheduledThreadPoolExecutor) {
            ((ScheduledThreadPoolExecutor) executor).setRemoveOnCancelPolicy(true);
        }
    }

    public final void close() {
        ExecutorService executorService;
        Executor executor = this.d;
        if (executor instanceof ExecutorService) {
            executorService = (ExecutorService) executor;
        } else {
            executorService = null;
        }
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Z) || ((Z) obj).d != this.d) {
            return false;
        }
        return true;
    }

    public final O f(long j2, A0 a0, C1232h hVar) {
        ScheduledExecutorService scheduledExecutorService;
        Executor executor = this.d;
        ScheduledFuture<?> scheduledFuture = null;
        if (executor instanceof ScheduledExecutorService) {
            scheduledExecutorService = (ScheduledExecutorService) executor;
        } else {
            scheduledExecutorService = null;
        }
        if (scheduledExecutorService != null) {
            try {
                scheduledFuture = scheduledExecutorService.schedule(a0, j2, TimeUnit.MILLISECONDS);
            } catch (RejectedExecutionException e) {
                CancellationException cancellationException = new CancellationException("The task was rejected");
                cancellationException.initCause(e);
                C0867e0 e0Var = (C0867e0) hVar.get(C0887y.e);
                if (e0Var != null) {
                    e0Var.a(cancellationException);
                }
            }
        }
        if (scheduledFuture != null) {
            return new N(scheduledFuture);
        }
        return E.k.f(j2, a0, hVar);
    }

    public final void h(long j2, C0875l lVar) {
        ScheduledExecutorService scheduledExecutorService;
        Executor executor = this.d;
        ScheduledFuture<?> scheduledFuture = null;
        if (executor instanceof ScheduledExecutorService) {
            scheduledExecutorService = (ScheduledExecutorService) executor;
        } else {
            scheduledExecutorService = null;
        }
        if (scheduledExecutorService != null) {
            e eVar = new e(this, lVar, false, 3);
            C1232h hVar = lVar.f3866h;
            try {
                scheduledFuture = scheduledExecutorService.schedule(eVar, j2, TimeUnit.MILLISECONDS);
            } catch (RejectedExecutionException e) {
                CancellationException cancellationException = new CancellationException("The task was rejected");
                cancellationException.initCause(e);
                C0867e0 e0Var = (C0867e0) hVar.get(C0887y.e);
                if (e0Var != null) {
                    e0Var.a(cancellationException);
                }
            }
        }
        if (scheduledFuture != null) {
            lVar.u(new C0871h(0, scheduledFuture));
        } else {
            E.k.h(j2, lVar);
        }
    }

    public final int hashCode() {
        return System.identityHashCode(this.d);
    }

    public final void i(C1232h hVar, Runnable runnable) {
        try {
            this.d.execute(runnable);
        } catch (RejectedExecutionException e) {
            CancellationException cancellationException = new CancellationException("The task was rejected");
            cancellationException.initCause(e);
            C0867e0 e0Var = (C0867e0) hVar.get(C0887y.e);
            if (e0Var != null) {
                e0Var.a(cancellationException);
            }
            f fVar = M.f3843a;
            eg.e.d.i(hVar, runnable);
        }
    }

    public final Executor l() {
        return this.d;
    }

    public final String toString() {
        return this.d.toString();
    }
}
