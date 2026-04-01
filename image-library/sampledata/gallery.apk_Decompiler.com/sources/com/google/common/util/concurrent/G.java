package com.google.common.util.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.locks.LockSupport;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class G extends q implements RunnableFuture, i {
    public volatile F d;

    public G(Callable callable) {
        this.d = new F(this, callable);
    }

    public final void afterDone() {
        F f;
        super.afterDone();
        if (wasInterrupted() && (f = this.d) != null) {
            w wVar = F.g;
            w wVar2 = F.f;
            Runnable runnable = (Runnable) f.get();
            if (runnable instanceof Thread) {
                v vVar = new v(f);
                v.a(vVar, Thread.currentThread());
                if (f.compareAndSet(runnable, vVar)) {
                    try {
                        ((Thread) runnable).interrupt();
                    } finally {
                        if (((Runnable) f.getAndSet(wVar2)) == wVar) {
                            LockSupport.unpark((Thread) runnable);
                        }
                    }
                }
            }
        }
        this.d = null;
    }

    public final String pendingToString() {
        F f = this.d;
        if (f == null) {
            return super.pendingToString();
        }
        return "task=[" + f + "]";
    }

    public final void run() {
        F f = this.d;
        if (f != null) {
            f.run();
        }
        this.d = null;
    }
}
