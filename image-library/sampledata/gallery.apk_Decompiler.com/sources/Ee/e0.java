package ee;

import D0.e;
import He.F;
import S1.j;
import java.lang.Thread;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e0 implements Executor {
    public final Thread.UncaughtExceptionHandler d;
    public final ConcurrentLinkedQueue e = new ConcurrentLinkedQueue();
    public final AtomicReference f = new AtomicReference();

    public e0(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.d = uncaughtExceptionHandler;
    }

    public final void a() {
        AtomicReference atomicReference;
        ConcurrentLinkedQueue concurrentLinkedQueue = this.e;
        do {
            Thread currentThread = Thread.currentThread();
            while (true) {
                atomicReference = this.f;
                if (atomicReference.compareAndSet((Object) null, currentThread)) {
                    while (true) {
                        try {
                            break;
                        } catch (Throwable th) {
                            atomicReference.set((Object) null);
                            throw th;
                        }
                    }
                } else if (atomicReference.get() != null) {
                    return;
                }
            }
            while (true) {
                break;
                Runnable runnable = (Runnable) concurrentLinkedQueue.poll();
                if (runnable != null) {
                    runnable.run();
                } else {
                    atomicReference.set((Object) null);
                }
            }
        } while (!concurrentLinkedQueue.isEmpty());
    }

    public final void b(Runnable runnable) {
        F.n(runnable, "runnable is null");
        this.e.add(runnable);
    }

    public final e c(Runnable runnable, long j2, TimeUnit timeUnit, ScheduledExecutorService scheduledExecutorService) {
        d0 d0Var = new d0(runnable);
        return new e(d0Var, (ScheduledFuture) scheduledExecutorService.schedule(new j(this, d0Var, runnable, 1), j2, timeUnit));
    }

    public final void d() {
        boolean z;
        if (Thread.currentThread() == this.f.get()) {
            z = true;
        } else {
            z = false;
        }
        F.t(z, "Not called from the SynchronizationContext");
    }

    public final void execute(Runnable runnable) {
        b(runnable);
        a();
    }
}
