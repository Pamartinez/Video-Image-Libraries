package com.google.common.util.concurrent;

import com.arcsoft.libarccommon.utils.ArcCommonLog;
import i.C0212a;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class F extends AtomicReference implements Runnable {
    public static final w f = new Object();
    public static final w g = new Object();
    public final Callable d;
    public final /* synthetic */ G e;

    public F(G g3, Callable callable) {
        this.e = g3;
        callable.getClass();
        this.d = callable;
    }

    public final void a(Thread thread) {
        Runnable runnable = (Runnable) get();
        v vVar = null;
        boolean z = false;
        int i2 = 0;
        while (true) {
            boolean z3 = runnable instanceof v;
            w wVar = g;
            if (!z3 && runnable != wVar) {
                break;
            }
            if (z3) {
                vVar = (v) runnable;
            }
            i2++;
            if (i2 <= 1000) {
                Thread.yield();
            } else if (runnable == wVar || compareAndSet(runnable, wVar)) {
                if (Thread.interrupted() || z) {
                    z = true;
                } else {
                    z = false;
                }
                LockSupport.park(vVar);
            }
            runnable = (Runnable) get();
        }
        if (z) {
            thread.interrupt();
        }
    }

    public final void run() {
        Thread currentThread = Thread.currentThread();
        Object obj = null;
        if (compareAndSet((Object) null, currentThread)) {
            G g3 = this.e;
            boolean isDone = g3.isDone();
            w wVar = f;
            if (!isDone) {
                try {
                    obj = this.d.call();
                } catch (Throwable th) {
                    if (!compareAndSet(currentThread, wVar)) {
                        a(currentThread);
                    }
                    if (!isDone) {
                        g3.set((Object) null);
                    }
                    throw th;
                }
            }
            if (!compareAndSet(currentThread, wVar)) {
                a(currentThread);
            }
            if (!isDone) {
                g3.set(obj);
            }
        }
    }

    public final String toString() {
        String str;
        Runnable runnable = (Runnable) get();
        if (runnable == f) {
            str = "running=[DONE]";
        } else if (runnable instanceof v) {
            str = "running=[INTERRUPTED]";
        } else if (runnable instanceof Thread) {
            str = "running=[RUNNING ON " + ((Thread) runnable).getName() + "]";
        } else {
            str = "running=[NOT STARTED YET]";
        }
        StringBuilder t = C0212a.t(str, ArcCommonLog.TAG_COMMA);
        t.append(this.d.toString());
        return t.toString();
    }
}
