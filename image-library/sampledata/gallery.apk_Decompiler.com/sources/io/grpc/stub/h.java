package io.grpc.stub;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h extends ConcurrentLinkedQueue implements Executor {
    public static final Logger e = Logger.getLogger(h.class.getName());
    public static final Object f = new Object();
    public volatile Object d;

    public final void execute(Runnable runnable) {
        add(runnable);
        Object obj = this.d;
        if (obj != f) {
            LockSupport.unpark((Thread) obj);
        } else if (remove(runnable) && i.b) {
            throw new RejectedExecutionException();
        }
    }

    /* JADX INFO: finally extract failed */
    public final void p() {
        if (!Thread.interrupted()) {
            Runnable runnable = (Runnable) poll();
            if (runnable == null) {
                this.d = Thread.currentThread();
                while (true) {
                    try {
                        Runnable runnable2 = (Runnable) poll();
                        if (runnable2 != null) {
                            this.d = null;
                            runnable = runnable2;
                            break;
                        }
                        LockSupport.park(this);
                        if (Thread.interrupted()) {
                            throw new InterruptedException();
                        }
                    } catch (Throwable th) {
                        this.d = null;
                        throw th;
                    }
                }
            }
            do {
                try {
                    runnable.run();
                } catch (Throwable th2) {
                    e.log(Level.WARNING, "Runnable threw exception", th2);
                }
                runnable = (Runnable) poll();
            } while (runnable != null);
            return;
        }
        throw new InterruptedException();
    }

    public final void shutdown() {
        this.d = f;
        while (true) {
            Runnable runnable = (Runnable) poll();
            if (runnable != null) {
                try {
                    runnable.run();
                } catch (Throwable th) {
                    e.log(Level.WARNING, "Runnable threw exception", th);
                }
            } else {
                return;
            }
        }
    }
}
