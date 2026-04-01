package x0;

import J0.b;
import J0.c;
import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import t8.e;

/* renamed from: x0.D  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0322D {
    public static final ExecutorService e = Executors.newCachedThreadPool(new c());

    /* renamed from: a  reason: collision with root package name */
    public final LinkedHashSet f2043a = new LinkedHashSet(1);
    public final LinkedHashSet b = new LinkedHashSet(1);

    /* renamed from: c  reason: collision with root package name */
    public final Handler f2044c = new Handler(Looper.getMainLooper());
    public volatile C0320B d = null;

    public C0322D(C0332j jVar) {
        d(new C0320B(jVar));
    }

    public final synchronized void a(z zVar) {
        Throwable th;
        try {
            C0320B b5 = this.d;
            if (!(b5 == null || (th = b5.b) == null)) {
                zVar.onResult(th);
            }
            this.b.add(zVar);
        } catch (Throwable th2) {
            while (true) {
                throw th2;
            }
        }
    }

    public final synchronized void b(z zVar) {
        C0332j jVar;
        try {
            C0320B b5 = this.d;
            if (!(b5 == null || (jVar = b5.f2042a) == null)) {
                zVar.onResult(jVar);
            }
            this.f2043a.add(zVar);
        } catch (Throwable th) {
            while (true) {
                throw th;
            }
        }
    }

    public final void c() {
        C0320B b5 = this.d;
        if (b5 != null) {
            C0332j jVar = b5.f2042a;
            if (jVar != null) {
                synchronized (this) {
                    Iterator it = new ArrayList(this.f2043a).iterator();
                    while (it.hasNext()) {
                        ((z) it.next()).onResult(jVar);
                    }
                }
                return;
            }
            Throwable th = b5.b;
            synchronized (this) {
                ArrayList arrayList = new ArrayList(this.b);
                if (arrayList.isEmpty()) {
                    b.c("Lottie encountered an error but no failure listener was added:", th);
                    return;
                }
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    ((z) it2.next()).onResult(th);
                }
            }
        }
    }

    public final void d(C0320B b5) {
        if (this.d == null) {
            this.d = b5;
            if (Looper.myLooper() == Looper.getMainLooper()) {
                c();
            } else {
                this.f2044c.post(new e(23, this));
            }
        } else {
            throw new IllegalStateException("A task may only be set once.");
        }
    }

    /* JADX WARNING: type inference failed for: r0v4, types: [java.util.concurrent.FutureTask, x0.C, java.lang.Runnable] */
    public C0322D(Callable callable, boolean z) {
        if (z) {
            try {
                d((C0320B) callable.call());
            } catch (Throwable th) {
                d(new C0320B(th));
            }
        } else {
            ExecutorService executorService = e;
            ? futureTask = new FutureTask(callable);
            futureTask.d = this;
            executorService.execute(futureTask);
        }
    }
}
