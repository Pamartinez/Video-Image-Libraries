package ge;

import He.F;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class F1 implements Executor, Runnable {
    public static final Logger g;

    /* renamed from: h  reason: collision with root package name */
    public static final I1 f4426h;
    public final Executor d;
    public final ConcurrentLinkedQueue e = new ConcurrentLinkedQueue();
    public volatile int f = 0;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: ge.D1} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: ge.D1} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: ge.D1} */
    /* JADX WARNING: Multi-variable type inference failed */
    static {
        /*
            java.lang.Class<ge.F1> r0 = ge.F1.class
            java.lang.String r1 = r0.getName()
            java.util.logging.Logger r1 = java.util.logging.Logger.getLogger(r1)
            g = r1
            ge.D1 r1 = new ge.D1     // Catch:{ all -> 0x0018 }
            java.lang.String r2 = "f"
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r0 = java.util.concurrent.atomic.AtomicIntegerFieldUpdater.newUpdater(r0, r2)     // Catch:{ all -> 0x0018 }
            r1.<init>(r0)     // Catch:{ all -> 0x0018 }
            goto L_0x0027
        L_0x0018:
            r0 = move-exception
            java.util.logging.Logger r1 = g
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            java.lang.String r3 = "FieldUpdaterAtomicHelper failed"
            r1.log(r2, r3, r0)
            ge.E1 r1 = new ge.E1
            r1.<init>()
        L_0x0027:
            f4426h = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: ge.F1.<clinit>():void");
    }

    public F1(Executor executor) {
        F.n(executor, "'executor' must not be null.");
        this.d = executor;
    }

    public final void a(Runnable runnable) {
        I1 i12 = f4426h;
        if (i12.c(this)) {
            try {
                this.d.execute(this);
            } catch (Throwable th) {
                if (runnable != null) {
                    this.e.remove(runnable);
                }
                i12.d(this);
                throw th;
            }
        }
    }

    public final void execute(Runnable runnable) {
        F.n(runnable, "'r' must not be null.");
        this.e.add(runnable);
        a(runnable);
    }

    public final void run() {
        Runnable runnable;
        I1 i12 = f4426h;
        ConcurrentLinkedQueue concurrentLinkedQueue = this.e;
        while (true) {
            try {
                runnable = (Runnable) concurrentLinkedQueue.poll();
                if (runnable == null) {
                    break;
                }
                runnable.run();
            } catch (RuntimeException e7) {
                Logger logger = g;
                Level level = Level.SEVERE;
                logger.log(level, "Exception while executing runnable " + runnable, e7);
            } catch (Throwable th) {
                i12.d(this);
                throw th;
            }
        }
        i12.d(this);
        if (!concurrentLinkedQueue.isEmpty()) {
            a((Runnable) null);
        }
    }
}
