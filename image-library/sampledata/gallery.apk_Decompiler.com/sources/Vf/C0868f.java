package Vf;

import java.util.concurrent.locks.LockSupport;
import kotlin.jvm.internal.j;
import qe.C1232h;

/* renamed from: Vf.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0868f extends C0858a {
    public final Thread g;

    /* renamed from: h  reason: collision with root package name */
    public final X f3859h;

    public C0868f(C1232h hVar, Thread thread, X x9) {
        super(hVar, true);
        this.g = thread;
        this.f3859h = x9;
    }

    public final void k(Object obj) {
        Thread currentThread = Thread.currentThread();
        Thread thread = this.g;
        if (!j.a(currentThread, thread)) {
            LockSupport.unpark(thread);
        }
    }
}
