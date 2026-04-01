package Vf;

import He.F;
import L1.d;
import Qe.B;
import Wf.b;
import cg.a;
import cg.c;
import cg.f;
import cg.r;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import kotlin.jvm.internal.g;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.y;
import me.k;
import me.x;
import qe.C1227c;
import qe.C1228d;
import qe.C1229e;
import qe.C1230f;
import qe.C1232h;
import qe.C1233i;
import re.C1245a;
import se.C1272d;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class D {

    /* renamed from: a  reason: collision with root package name */
    public static final B f3836a = new B("RESUME_TOKEN", 2);
    public static final B b = new B("REMOVED_TASK", 2);

    /* renamed from: c  reason: collision with root package name */
    public static final B f3837c = new B("CLOSED_EMPTY", 2);
    public static final B d = new B("COMPLETING_ALREADY", 2);
    public static final B e = new B("COMPLETING_WAITING_CHILDREN", 2);
    public static final B f = new B("COMPLETING_RETRY", 2);
    public static final B g = new B("TOO_LATE_TO_CANCEL", 2);

    /* renamed from: h  reason: collision with root package name */
    public static final B f3838h = new B("SEALED", 2);

    /* renamed from: i  reason: collision with root package name */
    public static final Q f3839i = new Q(false);

    /* renamed from: j  reason: collision with root package name */
    public static final Q f3840j = new Q(true);

    public static final c a(C1232h hVar) {
        if (hVar.get(C0887y.e) == null) {
            hVar = hVar.plus(new g0());
        }
        return new c(hVar);
    }

    /* JADX WARNING: type inference failed for: r2v4, types: [Vf.a] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static Vf.H b(Vf.A r1, Vf.C0888z r2, Vf.C r3, Ae.c r4, int r5) {
        /*
            r0 = r5 & 1
            if (r0 == 0) goto L_0x0006
            qe.i r2 = qe.C1233i.d
        L_0x0006:
            r5 = r5 & 2
            if (r5 == 0) goto L_0x000c
            Vf.C r3 = Vf.C.DEFAULT
        L_0x000c:
            qe.h r1 = o(r1, r2)
            r3.getClass()
            Vf.C r2 = Vf.C.LAZY
            if (r3 != r2) goto L_0x001d
            Vf.o0 r2 = new Vf.o0
            r2.<init>(r1, r4)
            goto L_0x0023
        L_0x001d:
            Vf.H r2 = new Vf.H
            r5 = 1
            r2.<init>(r1, r5)
        L_0x0023:
            r2.X(r3, r2, r4)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: Vf.D.b(Vf.A, Vf.z, Vf.C, Ae.c, int):Vf.H");
    }

    public static void c(A a7) {
        C0867e0 e0Var = (C0867e0) a7.getCoroutineContext().get(C0887y.e);
        if (e0Var != null) {
            e0Var.a((CancellationException) null);
            return;
        }
        throw new IllegalStateException(("Scope cannot be cancelled because it does not have a job: " + a7).toString());
    }

    public static final Object d(Ae.c cVar, C1227c cVar2) {
        r rVar = new r(cVar2, cVar2.getContext());
        Object w = d.w(rVar, rVar, cVar);
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        return w;
    }

    public static final Object e(long j2, i iVar) {
        if (j2 > 0) {
            C0875l lVar = new C0875l(1, d.m(iVar));
            lVar.r();
            if (j2 < Long.MAX_VALUE) {
                i(lVar.f3866h).h(j2, lVar);
            }
            Object q = lVar.q();
            if (q == C1245a.COROUTINE_SUSPENDED) {
                return q;
            }
        }
        return x.f4917a;
    }

    public static final void f(C1232h hVar) {
        C0867e0 e0Var = (C0867e0) hVar.get(C0887y.e);
        if (e0Var != null && !e0Var.isActive()) {
            throw ((n0) e0Var).v();
        }
    }

    public static final C1232h g(C1232h hVar, C1232h hVar2, boolean z) {
        Boolean bool = Boolean.FALSE;
        boolean booleanValue = ((Boolean) hVar.fold(bool, new C0884v(0))).booleanValue();
        boolean booleanValue2 = ((Boolean) hVar2.fold(bool, new C0884v(0))).booleanValue();
        if (!booleanValue && !booleanValue2) {
            return hVar.plus(hVar2);
        }
        C0884v vVar = new C0884v(1);
        C1233i iVar = C1233i.d;
        C1232h hVar3 = (C1232h) hVar.fold(iVar, vVar);
        Object obj = hVar2;
        if (booleanValue2) {
            obj = hVar2.fold(iVar, new C0884v(2));
        }
        return hVar3.plus((C1232h) obj);
    }

    public static final C0886x h(Executor executor) {
        L l;
        C0886x xVar;
        if (executor instanceof L) {
            l = (L) executor;
        } else {
            l = null;
        }
        if (l == null || (xVar = l.d) == null) {
            return new Z(executor);
        }
        return xVar;
    }

    public static final I i(C1232h hVar) {
        I i2;
        C1230f fVar = hVar.get(C1228d.d);
        if (fVar instanceof I) {
            i2 = (I) fVar;
        } else {
            i2 = null;
        }
        if (i2 == null) {
            return F.f3841a;
        }
        return i2;
    }

    public static final String j(Object obj) {
        return Integer.toHexString(System.identityHashCode(obj));
    }

    public static final C0875l k(C1227c cVar) {
        C0875l lVar;
        C0875l lVar2;
        if (!(cVar instanceof f)) {
            return new C0875l(1, cVar);
        }
        f fVar = (f) cVar;
        B b5 = a.f4017c;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f.k;
        loop0:
        while (true) {
            Object obj = atomicReferenceFieldUpdater.get(fVar);
            lVar = null;
            if (obj == null) {
                atomicReferenceFieldUpdater.set(fVar, b5);
                lVar2 = null;
                break;
            } else if (obj instanceof C0875l) {
                while (!atomicReferenceFieldUpdater.compareAndSet(fVar, obj, b5)) {
                    if (atomicReferenceFieldUpdater.get(fVar) != obj) {
                    }
                }
                lVar2 = (C0875l) obj;
                break loop0;
            } else if (obj != b5 && !(obj instanceof Throwable)) {
                throw new IllegalStateException(("Inconsistent state " + obj).toString());
            }
        }
        if (lVar2 != null) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = C0875l.f3865j;
            Object obj2 = atomicReferenceFieldUpdater2.get(lVar2);
            if (!(obj2 instanceof C0882t) || ((C0882t) obj2).d == null) {
                C0875l.f3864i.set(lVar2, 536870911);
                atomicReferenceFieldUpdater2.set(lVar2, C0860b.f3850a);
                lVar = lVar2;
            } else {
                lVar2.n();
            }
            if (lVar != null) {
                return lVar;
            }
        }
        return new C0875l(2, cVar);
    }

    public static final void l(Throwable th, C1232h hVar) {
        try {
            if (((b) hVar.get(C0887y.d)) == null) {
                a.d(th, hVar);
            }
        } catch (Throwable th2) {
            if (th != th2) {
                RuntimeException runtimeException = new RuntimeException("Exception while trying to handle coroutine exception", th2);
                F.e(runtimeException, th);
                th = runtimeException;
            }
            a.d(th, hVar);
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Object, Vf.h0, kotlin.jvm.internal.g] */
    public static final O m(C0867e0 e0Var, boolean z, i0 i0Var) {
        i0 i0Var2;
        if (e0Var instanceof n0) {
            return ((n0) e0Var).D(z, i0Var);
        }
        boolean j2 = i0Var.j();
        ? gVar = new g(1, i0Var, i0.class, "invoke", "invoke(Ljava/lang/Throwable;)V", 0);
        n0 n0Var = (n0) e0Var;
        n0Var.getClass();
        if (j2) {
            i0Var2 = new C0865d0(gVar);
        } else {
            i0Var2 = new P(1, gVar);
        }
        return n0Var.D(z, i0Var2);
    }

    /* JADX WARNING: type inference failed for: r2v4, types: [Vf.a] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static Vf.u0 n(Vf.A r1, Vf.C0886x r2, Vf.C r3, Ae.c r4, int r5) {
        /*
            r0 = r5 & 1
            if (r0 == 0) goto L_0x0006
            qe.i r2 = qe.C1233i.d
        L_0x0006:
            r5 = r5 & 2
            if (r5 == 0) goto L_0x000c
            Vf.C r3 = Vf.C.DEFAULT
        L_0x000c:
            qe.h r1 = o(r1, r2)
            r3.getClass()
            Vf.C r2 = Vf.C.LAZY
            if (r3 != r2) goto L_0x001d
            Vf.p0 r2 = new Vf.p0
            r2.<init>(r1, r4)
            goto L_0x0023
        L_0x001d:
            Vf.u0 r2 = new Vf.u0
            r5 = 1
            r2.<init>(r1, r5)
        L_0x0023:
            r2.X(r3, r2, r4)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: Vf.D.n(Vf.A, Vf.x, Vf.C, Ae.c, int):Vf.u0");
    }

    public static final C1232h o(A a7, C1232h hVar) {
        C1232h g3 = g(a7.getCoroutineContext(), hVar, true);
        eg.f fVar = M.f3843a;
        if (g3 == fVar || g3.get(C1228d.d) != null) {
            return g3;
        }
        return g3.plus(fVar);
    }

    public static final Object p(Object obj) {
        if (obj instanceof C0883u) {
            return L2.a.l(((C0883u) obj).f3874a);
        }
        return obj;
    }

    public static final void q(C0875l lVar, C1227c cVar, boolean z) {
        Object obj;
        C0 c02;
        Object obj2 = C0875l.f3865j.get(lVar);
        Throwable f5 = lVar.f(obj2);
        if (f5 != null) {
            obj = L2.a.l(f5);
        } else {
            obj = lVar.g(obj2);
        }
        if (z) {
            j.c(cVar, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T of kotlinx.coroutines.DispatchedTaskKt.resume>");
            f fVar = (f) cVar;
            C1227c cVar2 = fVar.f4020h;
            Object obj3 = fVar.f4022j;
            C1232h context = cVar2.getContext();
            Object l = a.l(context, obj3);
            if (l != a.d) {
                c02 = v(cVar2, context, l);
            } else {
                c02 = null;
            }
            try {
                cVar2.resumeWith(obj);
                if (c02 == null || c02.Y()) {
                    a.g(context, l);
                }
            } catch (Throwable th) {
                if (c02 == null || c02.Y()) {
                    a.g(context, l);
                }
                throw th;
            }
        } else {
            cVar.resumeWith(obj);
        }
    }

    public static final Object r(C1232h hVar, Ae.c cVar) {
        C1232h hVar2;
        X x9;
        long j2;
        C0883u uVar;
        Thread currentThread = Thread.currentThread();
        C1228d dVar = C1228d.d;
        C1229e eVar = (C1229e) hVar.get(dVar);
        C1233i iVar = C1233i.d;
        if (eVar == null) {
            x9 = y0.a();
            hVar2 = g(iVar, hVar.plus(x9), true);
            eg.f fVar = M.f3843a;
            if (hVar2 != fVar && hVar2.get(dVar) == null) {
                hVar2 = hVar2.plus(fVar);
            }
        } else {
            if (eVar instanceof X) {
                X x10 = (X) eVar;
            }
            x9 = (X) y0.f3876a.get();
            hVar2 = g(iVar, hVar, true);
            eg.f fVar2 = M.f3843a;
            if (hVar2 != fVar2 && hVar2.get(dVar) == null) {
                hVar2 = hVar2.plus(fVar2);
            }
        }
        C0868f fVar3 = new C0868f(hVar2, currentThread, x9);
        fVar3.X(C.DEFAULT, fVar3, cVar);
        X x11 = fVar3.f3859h;
        if (x11 != null) {
            int i2 = X.g;
            x11.p(false);
        }
        while (!Thread.interrupted()) {
            try {
                if (x11 != null) {
                    j2 = x11.q();
                } else {
                    j2 = Long.MAX_VALUE;
                }
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = n0.d;
                if (atomicReferenceFieldUpdater.get(fVar3) instanceof C0861b0) {
                    LockSupport.parkNanos(fVar3, j2);
                } else {
                    if (x11 != null) {
                        int i7 = X.g;
                        x11.l(false);
                    }
                    Object u = u(atomicReferenceFieldUpdater.get(fVar3));
                    if (u instanceof C0883u) {
                        uVar = (C0883u) u;
                    } else {
                        uVar = null;
                    }
                    if (uVar == null) {
                        return u;
                    }
                    throw uVar.f3874a;
                }
            } catch (Throwable th) {
                if (x11 != null) {
                    int i8 = X.g;
                    x11.l(false);
                }
                throw th;
            }
        }
        InterruptedException interruptedException = new InterruptedException();
        fVar3.n(interruptedException);
        throw interruptedException;
    }

    public static final String t(C1227c cVar) {
        Object obj;
        if (cVar instanceof f) {
            return ((f) cVar).toString();
        }
        try {
            obj = cVar + '@' + j(cVar);
        } catch (Throwable th) {
            obj = L2.a.l(th);
        }
        if (k.a(obj) != null) {
            obj = cVar.getClass().getName() + '@' + j(cVar);
        }
        return (String) obj;
    }

    public static final Object u(Object obj) {
        C0863c0 c0Var;
        C0861b0 b0Var;
        if (obj instanceof C0863c0) {
            c0Var = (C0863c0) obj;
        } else {
            c0Var = null;
        }
        if (c0Var == null || (b0Var = c0Var.f3854a) == null) {
            return obj;
        }
        return b0Var;
    }

    public static final C0 v(C1227c cVar, C1232h hVar, Object obj) {
        C0 c02 = null;
        if ((cVar instanceof C1272d) && hVar.get(D0.d) != null) {
            C1272d dVar = (C1272d) cVar;
            while (true) {
                if (!(dVar instanceof J) && (dVar = dVar.getCallerFrame()) != null) {
                    if (dVar instanceof C0) {
                        c02 = (C0) dVar;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (c02 != null) {
                c02.Z(hVar, obj);
            }
        }
        return c02;
    }

    /* JADX INFO: finally extract failed */
    public static final Object w(C1232h hVar, Ae.c cVar, C1227c cVar2) {
        C1232h hVar2;
        Object obj;
        C1232h context = cVar2.getContext();
        if (!((Boolean) hVar.fold(Boolean.FALSE, new C0884v(0))).booleanValue()) {
            hVar2 = context.plus(hVar);
        } else {
            hVar2 = g(context, hVar, false);
        }
        f(hVar2);
        if (hVar2 == context) {
            r rVar = new r(cVar2, hVar2);
            obj = d.w(rVar, rVar, cVar);
        } else {
            C1228d dVar = C1228d.d;
            if (j.a(hVar2.get(dVar), context.get(dVar))) {
                C0 c02 = new C0(cVar2, hVar2);
                C1232h hVar3 = c02.f;
                Object l = a.l(hVar3, (Object) null);
                try {
                    Object w = d.w(c02, c02, cVar);
                    a.g(hVar3, l);
                    obj = w;
                } catch (Throwable th) {
                    a.g(hVar3, l);
                    throw th;
                }
            } else {
                r rVar2 = new r(cVar2, hVar2);
                try {
                    a.h(x.f4917a, d.m(d.f(cVar, rVar2, rVar2)));
                    AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = J.f3842h;
                    while (true) {
                        int i2 = atomicIntegerFieldUpdater.get(rVar2);
                        if (i2 == 0) {
                            if (atomicIntegerFieldUpdater.compareAndSet(rVar2, 0, 1)) {
                                obj = C1245a.COROUTINE_SUSPENDED;
                                break;
                            }
                        } else if (i2 == 2) {
                            obj = u(n0.d.get(rVar2));
                            if (obj instanceof C0883u) {
                                throw ((C0883u) obj).f3874a;
                            }
                        } else {
                            throw new IllegalStateException("Already suspended");
                        }
                    }
                } catch (Throwable th2) {
                    rVar2.resumeWith(L2.a.l(th2));
                    throw th2;
                }
            }
        }
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        return obj;
    }

    public static final Object x(long j2, Ae.c cVar, i iVar) {
        Object obj;
        Object I6;
        if (j2 > 0) {
            A0 a0 = new A0(j2, iVar);
            m(a0, true, new P(0, i(a0.g.getContext()).f(a0.f3833h, a0, a0.f)));
            try {
                y.c(2, cVar);
                obj = cVar.invoke(a0, a0);
            } catch (Throwable th) {
                obj = new C0883u(th, false);
            }
            C1245a aVar = C1245a.COROUTINE_SUSPENDED;
            if (obj == aVar || (I6 = a0.I(obj)) == e) {
                return aVar;
            }
            if (I6 instanceof C0883u) {
                Throwable th2 = ((C0883u) I6).f3874a;
                if (!(th2 instanceof z0) || ((z0) th2).d != a0) {
                    throw th2;
                } else if (obj instanceof C0883u) {
                    throw ((C0883u) obj).f3874a;
                }
            } else {
                obj = u(I6);
            }
            return obj;
        }
        throw new z0("Timed out immediately", (C0867e0) null);
    }
}
