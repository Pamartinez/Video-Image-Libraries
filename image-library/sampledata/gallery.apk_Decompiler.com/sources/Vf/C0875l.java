package Vf;

import Ae.b;
import Ae.d;
import Qe.B;
import cg.a;
import cg.f;
import cg.s;
import com.adobe.internal.xmp.options.PropertyOptions;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.internal.j;
import me.k;
import me.x;
import qe.C1227c;
import qe.C1232h;
import re.C1245a;
import se.C1272d;

/* renamed from: Vf.l  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C0875l extends K implements C0873j, C1272d, E0 {

    /* renamed from: i  reason: collision with root package name */
    public static final /* synthetic */ AtomicIntegerFieldUpdater f3864i;

    /* renamed from: j  reason: collision with root package name */
    public static final /* synthetic */ AtomicReferenceFieldUpdater f3865j;
    public static final /* synthetic */ AtomicReferenceFieldUpdater k;
    private volatile /* synthetic */ int _decisionAndIndex$volatile = 536870911;
    private volatile /* synthetic */ Object _parentHandle$volatile;
    private volatile /* synthetic */ Object _state$volatile = C0860b.f3850a;
    public final C1227c g;

    /* renamed from: h  reason: collision with root package name */
    public final C1232h f3866h;

    static {
        Class<C0875l> cls = C0875l.class;
        f3864i = AtomicIntegerFieldUpdater.newUpdater(cls, "_decisionAndIndex$volatile");
        Class<Object> cls2 = Object.class;
        f3865j = AtomicReferenceFieldUpdater.newUpdater(cls, cls2, "_state$volatile");
        k = AtomicReferenceFieldUpdater.newUpdater(cls, cls2, "_parentHandle$volatile");
    }

    public C0875l(int i2, C1227c cVar) {
        super(i2);
        this.g = cVar;
        this.f3866h = cVar.getContext();
    }

    public static Object C(s0 s0Var, Object obj, int i2, d dVar) {
        C0872i iVar;
        if (obj instanceof C0883u) {
            return obj;
        }
        if (i2 != 1 && i2 != 2) {
            return obj;
        }
        if (dVar == null && !(s0Var instanceof C0872i)) {
            return obj;
        }
        if (s0Var instanceof C0872i) {
            iVar = (C0872i) s0Var;
        } else {
            iVar = null;
        }
        return new C0882t(obj, iVar, dVar, (CancellationException) null, 16);
    }

    public static void w(Object obj, Object obj2) {
        throw new IllegalStateException(("It's prohibited to register multiple handlers, tried to register " + obj + ", already has " + obj2).toString());
    }

    public final void A(Object obj, int i2, d dVar) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f3865j;
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (obj2 instanceof s0) {
                Object C5 = C((s0) obj2, obj, i2, dVar);
                while (true) {
                    if (atomicReferenceFieldUpdater.compareAndSet(this, obj2, C5)) {
                        if (!v()) {
                            n();
                        }
                        o(i2);
                        return;
                    } else if (atomicReferenceFieldUpdater.get(this) != obj2) {
                    }
                }
            } else {
                if (obj2 instanceof C0876m) {
                    C0876m mVar = (C0876m) obj2;
                    if (C0876m.f3867c.compareAndSet(mVar, 0, 1)) {
                        if (dVar != null) {
                            l(dVar, mVar.f3874a, obj);
                            return;
                        }
                        return;
                    }
                }
                throw new IllegalStateException(("Already resumed, but proposed with update " + obj).toString());
            }
        }
    }

    public final void B(C0886x xVar) {
        f fVar;
        C0886x xVar2;
        int i2;
        C1227c cVar = this.g;
        if (cVar instanceof f) {
            fVar = (f) cVar;
        } else {
            fVar = null;
        }
        if (fVar != null) {
            xVar2 = fVar.g;
        } else {
            xVar2 = null;
        }
        if (xVar2 == xVar) {
            i2 = 4;
        } else {
            i2 = this.f;
        }
        A(x.f4917a, i2, (d) null);
    }

    public final B D(Object obj, d dVar) {
        B b = D.f3836a;
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f3865j;
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (!(obj2 instanceof s0)) {
                return null;
            }
            Object C5 = C((s0) obj2, obj, this.f, dVar);
            while (true) {
                if (atomicReferenceFieldUpdater.compareAndSet(this, obj2, C5)) {
                    if (!v()) {
                        n();
                    }
                    return b;
                } else if (atomicReferenceFieldUpdater.get(this) != obj2) {
                }
            }
        }
    }

    public final void a(s sVar, int i2) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i7;
        do {
            atomicIntegerFieldUpdater = f3864i;
            i7 = atomicIntegerFieldUpdater.get(this);
            if ((i7 & 536870911) != 536870911) {
                throw new IllegalStateException("invokeOnCancellation should be called at most once");
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i7, ((i7 >> 29) << 29) + i2));
        u(sVar);
    }

    public final B b(Object obj, d dVar) {
        return D(obj, dVar);
    }

    public final void c(CancellationException cancellationException) {
        CancellationException cancellationException2;
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f3865j;
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (obj instanceof s0) {
                throw new IllegalStateException("Not completed");
            } else if (!(obj instanceof C0883u)) {
                if (obj instanceof C0882t) {
                    C0882t tVar = (C0882t) obj;
                    if (tVar.e == null) {
                        C0882t a7 = C0882t.a(tVar, (C0872i) null, cancellationException, 15);
                        while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, a7)) {
                            if (atomicReferenceFieldUpdater.get(this) != obj) {
                                cancellationException2 = cancellationException;
                            }
                        }
                        C0872i iVar = tVar.b;
                        if (iVar != null) {
                            k(iVar, cancellationException);
                        }
                        d dVar = tVar.f3873c;
                        if (dVar != null) {
                            l(dVar, cancellationException, tVar.f3872a);
                            return;
                        }
                        return;
                    }
                    throw new IllegalStateException("Must be called at most once");
                }
                cancellationException2 = cancellationException;
                C0882t tVar2 = new C0882t(obj, (C0872i) null, (d) null, cancellationException2, 14);
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, tVar2)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj) {
                    }
                }
                return;
                cancellationException = cancellationException2;
            } else {
                return;
            }
        }
    }

    public final boolean d(Throwable th) {
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f3865j;
            Object obj = atomicReferenceFieldUpdater.get(this);
            boolean z = false;
            if (!(obj instanceof s0)) {
                return false;
            }
            if ((obj instanceof C0872i) || (obj instanceof s)) {
                z = true;
            }
            C0876m mVar = new C0876m(this, th, z);
            while (true) {
                if (atomicReferenceFieldUpdater.compareAndSet(this, obj, mVar)) {
                    s0 s0Var = (s0) obj;
                    if (s0Var instanceof C0872i) {
                        k((C0872i) obj, th);
                    } else if (s0Var instanceof s) {
                        m((s) obj, th);
                    }
                    if (!v()) {
                        n();
                    }
                    o(this.f);
                    return true;
                } else if (atomicReferenceFieldUpdater.get(this) != obj) {
                }
            }
        }
    }

    public final C1227c e() {
        return this.g;
    }

    public final Throwable f(Object obj) {
        Throwable f = super.f(obj);
        if (f != null) {
            return f;
        }
        return null;
    }

    public final Object g(Object obj) {
        if (obj instanceof C0882t) {
            return ((C0882t) obj).f3872a;
        }
        return obj;
    }

    public final C1272d getCallerFrame() {
        C1227c cVar = this.g;
        if (cVar instanceof C1272d) {
            return (C1272d) cVar;
        }
        return null;
    }

    public final C1232h getContext() {
        return this.f3866h;
    }

    public final Object i() {
        return f3865j.get(this);
    }

    public final boolean isActive() {
        return f3865j.get(this) instanceof s0;
    }

    public final void j(Object obj) {
        o(this.f);
    }

    public final void k(C0872i iVar, Throwable th) {
        try {
            iVar.a(th);
        } catch (Throwable th2) {
            D.l(new RuntimeException("Exception in invokeOnCancellation handler for " + this, th2), this.f3866h);
        }
    }

    public final void l(d dVar, Throwable th, Object obj) {
        C1232h hVar = this.f3866h;
        try {
            dVar.invoke(th, obj, hVar);
        } catch (Throwable th2) {
            D.l(new RuntimeException("Exception in resume onCancellation handler for " + this, th2), hVar);
        }
    }

    public final void m(s sVar, Throwable th) {
        C1232h hVar = this.f3866h;
        int i2 = f3864i.get(this) & 536870911;
        if (i2 != 536870911) {
            try {
                sVar.h(i2, hVar);
            } catch (Throwable th2) {
                D.l(new RuntimeException("Exception in invokeOnCancellation handler for " + this, th2), hVar);
            }
        } else {
            throw new IllegalStateException("The index for Segment.onCancellation(..) is broken");
        }
    }

    public final void n() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = k;
        O o2 = (O) atomicReferenceFieldUpdater.get(this);
        if (o2 != null) {
            o2.a();
            atomicReferenceFieldUpdater.set(this, r0.d);
        }
    }

    public final void o(int i2) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i7;
        boolean z;
        boolean z3;
        do {
            atomicIntegerFieldUpdater = f3864i;
            i7 = atomicIntegerFieldUpdater.get(this);
            int i8 = i7 >> 29;
            if (i8 != 0) {
                if (i8 == 1) {
                    boolean z7 = false;
                    if (i2 == 4) {
                        z = true;
                    } else {
                        z = false;
                    }
                    C1227c cVar = this.g;
                    if (!z && (cVar instanceof f)) {
                        if (i2 == 1 || i2 == 2) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        int i10 = this.f;
                        if (i10 == 1 || i10 == 2) {
                            z7 = true;
                        }
                        if (z3 == z7) {
                            f fVar = (f) cVar;
                            C0886x xVar = fVar.g;
                            C1232h context = fVar.f4020h.getContext();
                            if (xVar.j(context)) {
                                xVar.i(context, this);
                                return;
                            }
                            X a7 = y0.a();
                            if (a7.d >= 4294967296L) {
                                a7.m(this);
                                return;
                            }
                            a7.p(true);
                            try {
                                D.q(this, cVar, true);
                                do {
                                } while (a7.r());
                            } catch (Throwable th) {
                                a7.l(true);
                                throw th;
                            }
                            a7.l(true);
                            return;
                        }
                    }
                    D.q(this, cVar, z);
                    return;
                }
                throw new IllegalStateException("Already resumed");
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i7, 1073741824 + (536870911 & i7)));
    }

    public Throwable p(n0 n0Var) {
        return n0Var.v();
    }

    public final Object q() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        int i2;
        C0867e0 e0Var;
        boolean v = v();
        do {
            atomicIntegerFieldUpdater = f3864i;
            i2 = atomicIntegerFieldUpdater.get(this);
            int i7 = i2 >> 29;
            if (i7 != 0) {
                if (i7 == 2) {
                    if (v) {
                        y();
                    }
                    Object obj = f3865j.get(this);
                    if (!(obj instanceof C0883u)) {
                        int i8 = this.f;
                        if ((i8 != 1 && i8 != 2) || (e0Var = (C0867e0) this.f3866h.get(C0887y.e)) == null || e0Var.isActive()) {
                            return g(obj);
                        }
                        CancellationException v6 = ((n0) e0Var).v();
                        c(v6);
                        throw v6;
                    }
                    throw ((C0883u) obj).f3874a;
                }
                throw new IllegalStateException("Already suspended");
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(this, i2, PropertyOptions.DELETE_EXISTING + (536870911 & i2)));
        if (((O) k.get(this)) == null) {
            s();
        }
        if (v) {
            y();
        }
        return C1245a.COROUTINE_SUSPENDED;
    }

    public final void r() {
        O s = s();
        if (s != null && !(f3865j.get(this) instanceof s0)) {
            s.a();
            k.set(this, r0.d);
        }
    }

    public final void resumeWith(Object obj) {
        Throwable a7 = k.a(obj);
        if (a7 != null) {
            obj = new C0883u(a7, false);
        }
        A(obj, this.f, (d) null);
    }

    public final O s() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        C0867e0 e0Var = (C0867e0) this.f3866h.get(C0887y.e);
        if (e0Var == null) {
            return null;
        }
        O m = D.m(e0Var, true, new C0877n(this, 0));
        do {
            atomicReferenceFieldUpdater = k;
            if (atomicReferenceFieldUpdater.compareAndSet(this, (Object) null, m) || atomicReferenceFieldUpdater.get(this) != null) {
                return m;
            }
            atomicReferenceFieldUpdater = k;
            break;
        } while (atomicReferenceFieldUpdater.get(this) != null);
        return m;
    }

    public final void t(b bVar) {
        u(new C0871h(1, bVar));
    }

    public final String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(x());
        sb2.append('(');
        sb2.append(D.t(this.g));
        sb2.append("){");
        Object obj = f3865j.get(this);
        if (obj instanceof s0) {
            str = "Active";
        } else if (obj instanceof C0876m) {
            str = "Cancelled";
        } else {
            str = "Completed";
        }
        sb2.append(str);
        sb2.append("}@");
        sb2.append(D.j(this));
        return sb2.toString();
    }

    public final void u(s0 s0Var) {
        Object obj;
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f3865j;
            obj = atomicReferenceFieldUpdater.get(this);
            if (obj instanceof C0860b) {
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, s0Var)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj) {
                    }
                }
                return;
            } else if ((obj instanceof C0872i) || (obj instanceof s)) {
                w(s0Var, obj);
            } else if (obj instanceof C0883u) {
                C0883u uVar = (C0883u) obj;
                if (!C0883u.b.compareAndSet(uVar, 0, 1)) {
                    w(s0Var, obj);
                    throw null;
                } else if (obj instanceof C0876m) {
                    Throwable th = uVar.f3874a;
                    if (s0Var instanceof C0872i) {
                        k((C0872i) s0Var, th);
                        return;
                    }
                    j.c(s0Var, "null cannot be cast to non-null type kotlinx.coroutines.internal.Segment<*>");
                    m((s) s0Var, th);
                    return;
                } else {
                    return;
                }
            } else if (obj instanceof C0882t) {
                C0882t tVar = (C0882t) obj;
                if (tVar.b != null) {
                    w(s0Var, obj);
                    throw null;
                } else if (!(s0Var instanceof s)) {
                    j.c(s0Var, "null cannot be cast to non-null type kotlinx.coroutines.CancelHandler");
                    C0872i iVar = (C0872i) s0Var;
                    Throwable th2 = tVar.e;
                    if (th2 != null) {
                        k(iVar, th2);
                        return;
                    }
                    C0882t a7 = C0882t.a(tVar, iVar, (CancellationException) null, 29);
                    while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, a7)) {
                        if (atomicReferenceFieldUpdater.get(this) != obj) {
                        }
                    }
                    return;
                } else {
                    return;
                }
            } else if (!(s0Var instanceof s)) {
                j.c(s0Var, "null cannot be cast to non-null type kotlinx.coroutines.CancelHandler");
                C0882t tVar2 = new C0882t(obj, (C0872i) s0Var, (d) null, (CancellationException) null, 28);
                while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, tVar2)) {
                    if (atomicReferenceFieldUpdater.get(this) != obj) {
                    }
                }
                return;
            } else {
                return;
            }
        }
        w(s0Var, obj);
        throw null;
    }

    public final boolean v() {
        if (this.f != 2) {
            return false;
        }
        C1227c cVar = this.g;
        j.c(cVar, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
        if (f.k.get((f) cVar) != null) {
            return true;
        }
        return false;
    }

    public String x() {
        return "CancellableContinuation";
    }

    public final void y() {
        f fVar;
        C1227c cVar = this.g;
        Throwable th = null;
        if (cVar instanceof f) {
            fVar = (f) cVar;
        } else {
            fVar = null;
        }
        if (fVar != null) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f.k;
            loop0:
            while (true) {
                Object obj = atomicReferenceFieldUpdater.get(fVar);
                B b = a.f4017c;
                if (obj == b) {
                    while (true) {
                        if (atomicReferenceFieldUpdater.compareAndSet(fVar, b, this)) {
                            break loop0;
                        } else if (atomicReferenceFieldUpdater.get(fVar) != b) {
                        }
                    }
                } else if (obj instanceof Throwable) {
                    while (!atomicReferenceFieldUpdater.compareAndSet(fVar, obj, (Object) null)) {
                        if (atomicReferenceFieldUpdater.get(fVar) != obj) {
                            throw new IllegalArgumentException("Failed requirement.");
                        }
                    }
                    th = obj;
                } else {
                    throw new IllegalStateException(("Inconsistent state " + obj).toString());
                }
            }
            if (th != null) {
                n();
                d(th);
            }
        }
    }

    public final void z(Object obj, d dVar) {
        A(obj, this.f, dVar);
    }
}
