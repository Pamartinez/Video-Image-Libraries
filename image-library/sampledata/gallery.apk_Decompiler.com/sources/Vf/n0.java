package Vf;

import Ae.c;
import Dd.C0730a;
import L1.d;
import cg.j;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import me.x;
import o1.C0246a;
import qe.C1227c;
import qe.C1230f;
import qe.C1231g;
import qe.C1232h;
import qe.C1233i;
import re.C1245a;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class n0 implements C0867e0, t0 {
    public static final /* synthetic */ AtomicReferenceFieldUpdater d;
    public static final /* synthetic */ AtomicReferenceFieldUpdater e;
    private volatile /* synthetic */ Object _parentHandle$volatile;
    private volatile /* synthetic */ Object _state$volatile;

    static {
        Class<n0> cls = n0.class;
        Class<Object> cls2 = Object.class;
        d = AtomicReferenceFieldUpdater.newUpdater(cls, cls2, "_state$volatile");
        e = AtomicReferenceFieldUpdater.newUpdater(cls, cls2, "_parentHandle$volatile");
    }

    public n0(boolean z) {
        Q q;
        if (z) {
            q = D.f3840j;
        } else {
            q = D.f3839i;
        }
        this._state$volatile = q;
    }

    public static C0879p K(j jVar) {
        while (jVar.h()) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = j.e;
            j e7 = jVar.e();
            if (e7 == null) {
                Object obj = atomicReferenceFieldUpdater.get(jVar);
                while (true) {
                    jVar = (j) obj;
                    if (!jVar.h()) {
                        break;
                    }
                    obj = atomicReferenceFieldUpdater.get(jVar);
                }
            } else {
                jVar = e7;
            }
        }
        while (true) {
            jVar = jVar.g();
            if (!jVar.h()) {
                if (jVar instanceof C0879p) {
                    return (C0879p) jVar;
                }
                if (jVar instanceof q0) {
                    return null;
                }
            }
        }
    }

    public static String S(Object obj) {
        if (obj instanceof l0) {
            l0 l0Var = (l0) obj;
            if (l0Var.d()) {
                return "Cancelling";
            }
            if (l0.e.get(l0Var) != 0) {
                return "Completing";
            }
            return "Active";
        } else if (obj instanceof C0861b0) {
            if (((C0861b0) obj).isActive()) {
                return "Active";
            }
            return "New";
        } else if (obj instanceof C0883u) {
            return "Cancelled";
        } else {
            return "Completed";
        }
    }

    public boolean A(Throwable th) {
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v0, resolved type: Vf.p} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: Vf.r0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: Vf.r0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: Vf.r0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v4, resolved type: Vf.r0} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v5, resolved type: Vf.r0} */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0079, code lost:
        if (r4 != false) goto L_0x0092;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void C(Vf.C0867e0 r8) {
        /*
            r7 = this;
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = e
            Vf.r0 r1 = Vf.r0.d
            if (r8 != 0) goto L_0x000a
            r0.set(r7, r1)
            return
        L_0x000a:
            Vf.n0 r8 = (Vf.n0) r8
            r8.Q()
            Vf.p r2 = new Vf.p
            r2.<init>(r7)
            r2.g = r8
        L_0x0016:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r3 = d
            java.lang.Object r4 = r3.get(r8)
            boolean r5 = r4 instanceof Vf.Q
            if (r5 == 0) goto L_0x003a
            r5 = r4
            Vf.Q r5 = (Vf.Q) r5
            boolean r6 = r5.d
            if (r6 == 0) goto L_0x0036
        L_0x0027:
            boolean r5 = r3.compareAndSet(r8, r4, r2)
            if (r5 == 0) goto L_0x002f
            goto L_0x0092
        L_0x002f:
            java.lang.Object r5 = r3.get(r8)
            if (r5 == r4) goto L_0x0027
            goto L_0x0016
        L_0x0036:
            r8.O(r5)
            goto L_0x0016
        L_0x003a:
            boolean r5 = r4 instanceof Vf.C0861b0
            r6 = 0
            if (r5 == 0) goto L_0x007e
            r5 = r4
            Vf.b0 r5 = (Vf.C0861b0) r5
            Vf.q0 r5 = r5.c()
            if (r5 != 0) goto L_0x004e
            Vf.i0 r4 = (Vf.i0) r4
            r8.P(r4)
            goto L_0x0016
        L_0x004e:
            r4 = 7
            boolean r4 = r5.d(r2, r4)
            if (r4 == 0) goto L_0x0056
            goto L_0x0092
        L_0x0056:
            r4 = 3
            boolean r4 = r5.d(r2, r4)
            java.lang.Object r8 = r3.get(r8)
            boolean r5 = r8 instanceof Vf.l0
            if (r5 == 0) goto L_0x006a
            Vf.l0 r8 = (Vf.l0) r8
            java.lang.Throwable r6 = r8.b()
            goto L_0x0076
        L_0x006a:
            boolean r5 = r8 instanceof Vf.C0883u
            if (r5 == 0) goto L_0x0071
            Vf.u r8 = (Vf.C0883u) r8
            goto L_0x0072
        L_0x0071:
            r8 = r6
        L_0x0072:
            if (r8 == 0) goto L_0x0076
            java.lang.Throwable r6 = r8.f3874a
        L_0x0076:
            r2.k(r6)
            if (r4 == 0) goto L_0x007c
            goto L_0x0092
        L_0x007c:
            r2 = r1
            goto L_0x0092
        L_0x007e:
            java.lang.Object r8 = r3.get(r8)
            boolean r4 = r8 instanceof Vf.C0883u
            if (r4 == 0) goto L_0x0089
            Vf.u r8 = (Vf.C0883u) r8
            goto L_0x008a
        L_0x0089:
            r8 = r6
        L_0x008a:
            if (r8 == 0) goto L_0x008e
            java.lang.Throwable r6 = r8.f3874a
        L_0x008e:
            r2.k(r6)
            goto L_0x007c
        L_0x0092:
            r0.set(r7, r2)
            java.lang.Object r8 = r3.get(r7)
            boolean r8 = r8 instanceof Vf.C0861b0
            if (r8 != 0) goto L_0x00a3
            r2.a()
            r0.set(r7, r1)
        L_0x00a3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Vf.n0.C(Vf.e0):void");
    }

    public final O D(boolean z, i0 i0Var) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        r0 r0Var;
        boolean z3;
        Throwable th;
        C0883u uVar;
        boolean z7;
        l0 l0Var;
        Throwable th2;
        i0Var.g = this;
        loop0:
        while (true) {
            atomicReferenceFieldUpdater = d;
            Object obj = atomicReferenceFieldUpdater.get(this);
            boolean z9 = obj instanceof Q;
            r0Var = r0.d;
            z3 = true;
            th = null;
            if (!z9) {
                if (!(obj instanceof C0861b0)) {
                    z3 = false;
                    break;
                }
                C0861b0 b0Var = (C0861b0) obj;
                q0 c5 = b0Var.c();
                if (c5 == null) {
                    P((i0) obj);
                } else {
                    if (i0Var.j()) {
                        if (b0Var instanceof l0) {
                            l0Var = (l0) b0Var;
                        } else {
                            l0Var = null;
                        }
                        if (l0Var != null) {
                            th2 = l0Var.b();
                        } else {
                            th2 = null;
                        }
                        if (th2 == null) {
                            z7 = c5.d(i0Var, 5);
                        } else if (z) {
                            i0Var.k(th2);
                            return r0Var;
                        }
                    } else {
                        z7 = c5.d(i0Var, 1);
                    }
                    if (z7) {
                        break;
                    }
                }
            } else {
                Q q = (Q) obj;
                if (q.d) {
                    while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, i0Var)) {
                        if (atomicReferenceFieldUpdater.get(this) != obj) {
                        }
                    }
                    break loop0;
                }
                O(q);
            }
        }
        if (z3) {
            return i0Var;
        }
        if (z) {
            Object obj2 = atomicReferenceFieldUpdater.get(this);
            if (obj2 instanceof C0883u) {
                uVar = (C0883u) obj2;
            } else {
                uVar = null;
            }
            if (uVar != null) {
                th = uVar.f3874a;
            }
            i0Var.k(th);
        }
        return r0Var;
    }

    public final boolean E() {
        Object obj = d.get(this);
        if (obj instanceof C0883u) {
            return true;
        }
        if (!(obj instanceof l0) || !((l0) obj).d()) {
            return false;
        }
        return true;
    }

    public boolean F() {
        return this instanceof C0868f;
    }

    public final Object G(i iVar) {
        Object obj;
        x xVar;
        do {
            obj = d.get(this);
            boolean z = obj instanceof C0861b0;
            xVar = x.f4917a;
            if (!z) {
                D.f(iVar.getContext());
                return xVar;
            }
        } while (R(obj) < 0);
        C0875l lVar = new C0875l(1, d.m(iVar));
        lVar.r();
        lVar.u(new C0871h(2, D.m(this, true, new C0877n(lVar, 1))));
        Object q = lVar.q();
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        if (q != aVar) {
            q = xVar;
        }
        if (q == aVar) {
            return q;
        }
        return xVar;
    }

    public final boolean H(Object obj) {
        Object T;
        do {
            T = T(d.get(this), obj);
            if (T == D.d) {
                return false;
            }
            if (T == D.e) {
                return true;
            }
        } while (T == D.f);
        k(T);
        return true;
    }

    public final Object I(Object obj) {
        Object T;
        C0883u uVar;
        do {
            T = T(d.get(this), obj);
            if (T == D.d) {
                String str = "Job " + this + " is already complete or completing, but is being completed with " + obj;
                Throwable th = null;
                if (obj instanceof C0883u) {
                    uVar = (C0883u) obj;
                } else {
                    uVar = null;
                }
                if (uVar != null) {
                    th = uVar.f3874a;
                }
                throw new IllegalStateException(str, th);
            }
        } while (T == D.f);
        return T;
    }

    public String J() {
        return getClass().getSimpleName();
    }

    /* JADX WARNING: type inference failed for: r1v5, types: [java.lang.RuntimeException] */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0053, code lost:
        r1 = r1;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void L(Vf.q0 r6, java.lang.Throwable r7) {
        /*
            r5 = this;
            cg.h r0 = new cg.h
            r1 = 4
            r0.<init>(r1)
            r6.d(r0, r1)
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = cg.j.d
            java.lang.Object r0 = r0.get(r6)
            java.lang.String r1 = "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode"
            kotlin.jvm.internal.j.c(r0, r1)
            cg.j r0 = (cg.j) r0
            r1 = 0
        L_0x0017:
            boolean r2 = r0.equals(r6)
            if (r2 != 0) goto L_0x0058
            boolean r2 = r0 instanceof Vf.i0
            if (r2 == 0) goto L_0x0053
            r2 = r0
            Vf.i0 r2 = (Vf.i0) r2
            boolean r2 = r2.j()
            if (r2 == 0) goto L_0x0053
            r2 = r0
            Vf.i0 r2 = (Vf.i0) r2     // Catch:{ all -> 0x0031 }
            r2.k(r7)     // Catch:{ all -> 0x0031 }
            goto L_0x0053
        L_0x0031:
            r2 = move-exception
            if (r1 == 0) goto L_0x0038
            He.F.e(r1, r2)
            goto L_0x0053
        L_0x0038:
            Dd.a r1 = new Dd.a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Exception in completion handler "
            r3.<init>(r4)
            r3.append(r0)
            java.lang.String r4 = " for "
            r3.append(r4)
            r3.append(r5)
            java.lang.String r3 = r3.toString()
            r1.<init>(r3, r2)
        L_0x0053:
            cg.j r0 = r0.g()
            goto L_0x0017
        L_0x0058:
            if (r1 == 0) goto L_0x005d
            r5.B(r1)
        L_0x005d:
            r5.p(r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Vf.n0.L(Vf.q0, java.lang.Throwable):void");
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [Vf.q0, cg.j] */
    public final void O(Q q) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        ? jVar = new j();
        C0859a0 a0Var = jVar;
        if (!q.d) {
            a0Var = new C0859a0(jVar);
        }
        do {
            atomicReferenceFieldUpdater = d;
            if (atomicReferenceFieldUpdater.compareAndSet(this, q, a0Var) || atomicReferenceFieldUpdater.get(this) != q) {
            }
            atomicReferenceFieldUpdater = d;
            return;
        } while (atomicReferenceFieldUpdater.get(this) != q);
    }

    public final void P(i0 i0Var) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        j jVar = new j();
        i0Var.getClass();
        j.e.set(jVar, i0Var);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = j.d;
        atomicReferenceFieldUpdater2.set(jVar, i0Var);
        loop0:
        while (true) {
            if (atomicReferenceFieldUpdater2.get(i0Var) != i0Var) {
                break;
            }
            while (true) {
                if (atomicReferenceFieldUpdater2.compareAndSet(i0Var, i0Var, jVar)) {
                    jVar.f(i0Var);
                    break loop0;
                } else if (atomicReferenceFieldUpdater2.get(i0Var) != i0Var) {
                }
            }
        }
        j g = i0Var.g();
        do {
            atomicReferenceFieldUpdater = d;
            if (atomicReferenceFieldUpdater.compareAndSet(this, i0Var, g) || atomicReferenceFieldUpdater.get(this) != i0Var) {
            }
            atomicReferenceFieldUpdater = d;
            return;
        } while (atomicReferenceFieldUpdater.get(this) != i0Var);
    }

    public final boolean Q() {
        int R;
        do {
            R = R(d.get(this));
            if (R == 0) {
                return false;
            }
        } while (R != 1);
        return true;
    }

    public final int R(Object obj) {
        boolean z = obj instanceof Q;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = d;
        if (z) {
            if (((Q) obj).d) {
                return 0;
            }
            Q q = D.f3840j;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, q)) {
                if (atomicReferenceFieldUpdater.get(this) != obj) {
                    return -1;
                }
            }
            N();
            return 1;
        } else if (!(obj instanceof C0859a0)) {
            return 0;
        } else {
            q0 q0Var = ((C0859a0) obj).d;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, obj, q0Var)) {
                if (atomicReferenceFieldUpdater.get(this) != obj) {
                    return -1;
                }
            }
            N();
            return 1;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:68:0x00a6, code lost:
        if (r2 == null) goto L_0x00ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x00a8, code lost:
        L(r0, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x00ab, code lost:
        r7 = K(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x00af, code lost:
        if (r7 == null) goto L_0x00ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x00b5, code lost:
        if (U(r1, r7, r8) == false) goto L_0x00ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x00b9, code lost:
        return Vf.D.e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x00ba, code lost:
        r0.d(new cg.h(2), 2);
        r7 = K(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x00c7, code lost:
        if (r7 == null) goto L_0x00d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x00cd, code lost:
        if (U(r1, r7, r8) == false) goto L_0x00d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x00d1, code lost:
        return Vf.D.e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x00d6, code lost:
        return u(r1, r8);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object T(java.lang.Object r7, java.lang.Object r8) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof Vf.C0861b0
            if (r0 != 0) goto L_0x0007
            Qe.B r6 = Vf.D.d
            return r6
        L_0x0007:
            boolean r0 = r7 instanceof Vf.Q
            if (r0 != 0) goto L_0x000f
            boolean r0 = r7 instanceof Vf.i0
            if (r0 == 0) goto L_0x0041
        L_0x000f:
            boolean r0 = r7 instanceof Vf.C0879p
            if (r0 != 0) goto L_0x0041
            boolean r0 = r8 instanceof Vf.C0883u
            if (r0 != 0) goto L_0x0041
            r0 = r7
            Vf.b0 r0 = (Vf.C0861b0) r0
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r1 = d
            boolean r7 = r8 instanceof Vf.C0861b0
            if (r7 == 0) goto L_0x002a
            Vf.c0 r7 = new Vf.c0
            r2 = r8
            Vf.b0 r2 = (Vf.C0861b0) r2
            r7.<init>(r2)
            r2 = r7
            goto L_0x002b
        L_0x002a:
            r2 = r8
        L_0x002b:
            boolean r7 = r1.compareAndSet(r6, r0, r2)
            if (r7 == 0) goto L_0x0038
            r6.M(r8)
            r6.s(r0, r8)
            return r8
        L_0x0038:
            java.lang.Object r7 = r1.get(r6)
            if (r7 == r0) goto L_0x002b
            Qe.B r6 = Vf.D.f
            return r6
        L_0x0041:
            Vf.b0 r7 = (Vf.C0861b0) r7
            Vf.q0 r0 = r6.z(r7)
            if (r0 != 0) goto L_0x004c
            Qe.B r6 = Vf.D.f
            return r6
        L_0x004c:
            boolean r1 = r7 instanceof Vf.l0
            r2 = 0
            if (r1 == 0) goto L_0x0055
            r1 = r7
            Vf.l0 r1 = (Vf.l0) r1
            goto L_0x0056
        L_0x0055:
            r1 = r2
        L_0x0056:
            if (r1 != 0) goto L_0x005d
            Vf.l0 r1 = new Vf.l0
            r1.<init>(r0, r2)
        L_0x005d:
            monitor-enter(r1)
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r3 = Vf.l0.e     // Catch:{ all -> 0x0070 }
            int r4 = r3.get(r1)     // Catch:{ all -> 0x0070 }
            r5 = 1
            if (r4 == 0) goto L_0x0069
            r4 = r5
            goto L_0x006a
        L_0x0069:
            r4 = 0
        L_0x006a:
            if (r4 == 0) goto L_0x0072
            Qe.B r6 = Vf.D.d     // Catch:{ all -> 0x0070 }
            monitor-exit(r1)
            return r6
        L_0x0070:
            r6 = move-exception
            goto L_0x00d7
        L_0x0072:
            r3.set(r1, r5)     // Catch:{ all -> 0x0070 }
            if (r1 == r7) goto L_0x008a
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r3 = d     // Catch:{ all -> 0x0070 }
        L_0x0079:
            boolean r4 = r3.compareAndSet(r6, r7, r1)     // Catch:{ all -> 0x0070 }
            if (r4 == 0) goto L_0x0080
            goto L_0x008a
        L_0x0080:
            java.lang.Object r4 = r3.get(r6)     // Catch:{ all -> 0x0070 }
            if (r4 == r7) goto L_0x0079
            Qe.B r6 = Vf.D.f     // Catch:{ all -> 0x0070 }
            monitor-exit(r1)
            return r6
        L_0x008a:
            boolean r7 = r1.d()     // Catch:{ all -> 0x0070 }
            boolean r3 = r8 instanceof Vf.C0883u     // Catch:{ all -> 0x0070 }
            if (r3 == 0) goto L_0x0096
            r3 = r8
            Vf.u r3 = (Vf.C0883u) r3     // Catch:{ all -> 0x0070 }
            goto L_0x0097
        L_0x0096:
            r3 = r2
        L_0x0097:
            if (r3 == 0) goto L_0x009e
            java.lang.Throwable r3 = r3.f3874a     // Catch:{ all -> 0x0070 }
            r1.a(r3)     // Catch:{ all -> 0x0070 }
        L_0x009e:
            java.lang.Throwable r3 = r1.b()     // Catch:{ all -> 0x0070 }
            if (r7 != 0) goto L_0x00a5
            r2 = r3
        L_0x00a5:
            monitor-exit(r1)
            if (r2 == 0) goto L_0x00ab
            r6.L(r0, r2)
        L_0x00ab:
            Vf.p r7 = K(r0)
            if (r7 == 0) goto L_0x00ba
            boolean r7 = r6.U(r1, r7, r8)
            if (r7 == 0) goto L_0x00ba
            Qe.B r6 = Vf.D.e
            return r6
        L_0x00ba:
            cg.h r7 = new cg.h
            r2 = 2
            r7.<init>(r2)
            r0.d(r7, r2)
            Vf.p r7 = K(r0)
            if (r7 == 0) goto L_0x00d2
            boolean r7 = r6.U(r1, r7, r8)
            if (r7 == 0) goto L_0x00d2
            Qe.B r6 = Vf.D.e
            return r6
        L_0x00d2:
            java.lang.Object r6 = r6.u(r1, r8)
            return r6
        L_0x00d7:
            monitor-exit(r1)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: Vf.n0.T(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public final boolean U(l0 l0Var, C0879p pVar, Object obj) {
        while (D.m(pVar.f3871h, false, new k0(this, l0Var, pVar, obj)) == r0.d) {
            pVar = K(pVar);
            if (pVar == null) {
                return false;
            }
        }
        return true;
    }

    public void a(CancellationException cancellationException) {
        if (cancellationException == null) {
            cancellationException = new C0869f0(q(), (Throwable) null, this);
        }
        o(cancellationException);
    }

    public Object c() {
        Object obj = d.get(this);
        if (obj instanceof C0861b0) {
            throw new IllegalStateException("This job has not completed yet");
        } else if (!(obj instanceof C0883u)) {
            return D.u(obj);
        } else {
            throw ((C0883u) obj).f3874a;
        }
    }

    public final Object fold(Object obj, c cVar) {
        return cVar.invoke(obj, this);
    }

    public final C1230f get(C1231g gVar) {
        kotlin.jvm.internal.j.e(gVar, "key");
        if (kotlin.jvm.internal.j.a(C0887y.e, gVar)) {
            return this;
        }
        return null;
    }

    public final C1231g getKey() {
        return C0887y.e;
    }

    public boolean isActive() {
        Object obj = d.get(this);
        if (!(obj instanceof C0861b0) || !((C0861b0) obj).isActive()) {
            return false;
        }
        return true;
    }

    public void l(Object obj) {
        k(obj);
    }

    public final Object m(C1227c cVar) {
        Object obj;
        do {
            obj = d.get(this);
            if (!(obj instanceof C0861b0)) {
                if (!(obj instanceof C0883u)) {
                    return D.u(obj);
                }
                throw ((C0883u) obj).f3874a;
            }
        } while (R(obj) < 0);
        j0 j0Var = new j0(this, d.m(cVar));
        j0Var.r();
        j0Var.u(new C0871h(2, D.m(this, true, new P(2, j0Var))));
        Object q = j0Var.q();
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        return q;
    }

    public final C1232h minusKey(C1231g gVar) {
        return C0246a.h0(this, gVar);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0036, code lost:
        r0 = Vf.D.d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003a, code lost:
        if (r0 == Vf.D.e) goto L_0x0100;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean n(java.lang.Object r10) {
        /*
            r9 = this;
            Qe.B r0 = Vf.D.d
            boolean r1 = r9.y()
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x003e
        L_0x000a:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = d
            java.lang.Object r0 = r0.get(r9)
            boolean r1 = r0 instanceof Vf.C0861b0
            if (r1 == 0) goto L_0x0036
            boolean r1 = r0 instanceof Vf.l0
            if (r1 == 0) goto L_0x0024
            r1 = r0
            Vf.l0 r1 = (Vf.l0) r1
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r4 = Vf.l0.e
            int r1 = r4.get(r1)
            if (r1 == 0) goto L_0x0024
            goto L_0x0036
        L_0x0024:
            Vf.u r1 = new Vf.u
            java.lang.Throwable r4 = r9.t(r10)
            r1.<init>(r4, r2)
            java.lang.Object r0 = r9.T(r0, r1)
            Qe.B r1 = Vf.D.f
            if (r0 == r1) goto L_0x000a
            goto L_0x0038
        L_0x0036:
            Qe.B r0 = Vf.D.d
        L_0x0038:
            Qe.B r1 = Vf.D.e
            if (r0 != r1) goto L_0x003e
            goto L_0x0100
        L_0x003e:
            Qe.B r1 = Vf.D.d
            if (r0 != r1) goto L_0x00f7
            r0 = 0
            r1 = r0
        L_0x0044:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r4 = d
            java.lang.Object r5 = r4.get(r9)
            boolean r6 = r5 instanceof Vf.l0
            if (r6 == 0) goto L_0x0095
            monitor-enter(r5)
            r4 = r5
            Vf.l0 r4 = (Vf.l0) r4     // Catch:{ all -> 0x0067 }
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r6 = Vf.l0.g     // Catch:{ all -> 0x0067 }
            java.lang.Object r4 = r6.get(r4)     // Catch:{ all -> 0x0067 }
            Qe.B r6 = Vf.D.f3838h     // Catch:{ all -> 0x0067 }
            if (r4 != r6) goto L_0x005e
            r4 = r3
            goto L_0x005f
        L_0x005e:
            r4 = r2
        L_0x005f:
            if (r4 == 0) goto L_0x0069
            Qe.B r10 = Vf.D.g     // Catch:{ all -> 0x0067 }
            monitor-exit(r5)
        L_0x0064:
            r0 = r10
            goto L_0x00f7
        L_0x0067:
            r9 = move-exception
            goto L_0x0093
        L_0x0069:
            r4 = r5
            Vf.l0 r4 = (Vf.l0) r4     // Catch:{ all -> 0x0067 }
            boolean r4 = r4.d()     // Catch:{ all -> 0x0067 }
            if (r1 != 0) goto L_0x0076
            java.lang.Throwable r1 = r9.t(r10)     // Catch:{ all -> 0x0067 }
        L_0x0076:
            r10 = r5
            Vf.l0 r10 = (Vf.l0) r10     // Catch:{ all -> 0x0067 }
            r10.a(r1)     // Catch:{ all -> 0x0067 }
            r10 = r5
            Vf.l0 r10 = (Vf.l0) r10     // Catch:{ all -> 0x0067 }
            java.lang.Throwable r10 = r10.b()     // Catch:{ all -> 0x0067 }
            if (r4 != 0) goto L_0x0086
            r0 = r10
        L_0x0086:
            monitor-exit(r5)
            if (r0 == 0) goto L_0x0090
            Vf.l0 r5 = (Vf.l0) r5
            Vf.q0 r10 = r5.d
            r9.L(r10, r0)
        L_0x0090:
            Qe.B r10 = Vf.D.d
            goto L_0x0064
        L_0x0093:
            monitor-exit(r5)
            throw r9
        L_0x0095:
            boolean r6 = r5 instanceof Vf.C0861b0
            if (r6 == 0) goto L_0x00f3
            if (r1 != 0) goto L_0x009f
            java.lang.Throwable r1 = r9.t(r10)
        L_0x009f:
            r6 = r5
            Vf.b0 r6 = (Vf.C0861b0) r6
            boolean r7 = r6.isActive()
            if (r7 == 0) goto L_0x00c8
            Vf.q0 r7 = r9.z(r6)
            if (r7 != 0) goto L_0x00af
            goto L_0x0044
        L_0x00af:
            Vf.l0 r8 = new Vf.l0
            r8.<init>(r7, r1)
        L_0x00b4:
            boolean r5 = r4.compareAndSet(r9, r6, r8)
            if (r5 == 0) goto L_0x00c0
            r9.L(r7, r1)
            Qe.B r10 = Vf.D.d
            goto L_0x0064
        L_0x00c0:
            java.lang.Object r5 = r4.get(r9)
            if (r5 == r6) goto L_0x00b4
            goto L_0x0044
        L_0x00c8:
            Vf.u r4 = new Vf.u
            r4.<init>(r1, r2)
            java.lang.Object r4 = r9.T(r5, r4)
            Qe.B r6 = Vf.D.d
            if (r4 == r6) goto L_0x00db
            Qe.B r5 = Vf.D.f
            if (r4 == r5) goto L_0x0044
            r0 = r4
            goto L_0x00f7
        L_0x00db:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r0 = "Cannot happen in "
            r10.<init>(r0)
            r10.append(r5)
            java.lang.String r10 = r10.toString()
            java.lang.String r10 = r10.toString()
            r9.<init>(r10)
            throw r9
        L_0x00f3:
            Qe.B r10 = Vf.D.g
            goto L_0x0064
        L_0x00f7:
            Qe.B r10 = Vf.D.d
            if (r0 != r10) goto L_0x00fc
            goto L_0x0100
        L_0x00fc:
            Qe.B r10 = Vf.D.e
            if (r0 != r10) goto L_0x0101
        L_0x0100:
            return r3
        L_0x0101:
            Qe.B r10 = Vf.D.g
            if (r0 != r10) goto L_0x0106
            return r2
        L_0x0106:
            r9.k(r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: Vf.n0.n(java.lang.Object):boolean");
    }

    public void o(CancellationException cancellationException) {
        n(cancellationException);
    }

    public final boolean p(Throwable th) {
        if (F()) {
            return true;
        }
        boolean z = th instanceof CancellationException;
        C0878o oVar = (C0878o) e.get(this);
        if (oVar == null || oVar == r0.d) {
            return z;
        }
        if (oVar.b(th) || z) {
            return true;
        }
        return false;
    }

    public final C1232h plus(C1232h hVar) {
        kotlin.jvm.internal.j.e(hVar, "context");
        if (hVar == C1233i.d) {
            return this;
        }
        return (C1232h) hVar.fold(this, new C0884v(9));
    }

    public String q() {
        return "Job was cancelled";
    }

    public boolean r(Throwable th) {
        if (th instanceof CancellationException) {
            return true;
        }
        if (!n(th) || !x()) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: type inference failed for: r1v5, types: [java.lang.RuntimeException] */
    /* JADX WARNING: type inference failed for: r0v10, types: [Dd.a, java.lang.RuntimeException] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void s(Vf.C0861b0 r7, java.lang.Object r8) {
        /*
            r6 = this;
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = e
            java.lang.Object r1 = r0.get(r6)
            Vf.o r1 = (Vf.C0878o) r1
            if (r1 == 0) goto L_0x0012
            r1.a()
            Vf.r0 r1 = Vf.r0.d
            r0.set(r6, r1)
        L_0x0012:
            boolean r0 = r8 instanceof Vf.C0883u
            r1 = 0
            if (r0 == 0) goto L_0x001a
            Vf.u r8 = (Vf.C0883u) r8
            goto L_0x001b
        L_0x001a:
            r8 = r1
        L_0x001b:
            if (r8 == 0) goto L_0x0020
            java.lang.Throwable r8 = r8.f3874a
            goto L_0x0021
        L_0x0020:
            r8 = r1
        L_0x0021:
            boolean r0 = r7 instanceof Vf.i0
            java.lang.String r2 = " for "
            java.lang.String r3 = "Exception in completion handler "
            if (r0 == 0) goto L_0x004c
            r0 = r7
            Vf.i0 r0 = (Vf.i0) r0     // Catch:{ all -> 0x0030 }
            r0.k(r8)     // Catch:{ all -> 0x0030 }
            return
        L_0x0030:
            r8 = move-exception
            Dd.a r0 = new Dd.a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r3)
            r1.append(r7)
            r1.append(r2)
            r1.append(r6)
            java.lang.String r7 = r1.toString()
            r0.<init>(r7, r8)
            r6.B(r0)
            goto L_0x00a1
        L_0x004c:
            Vf.q0 r7 = r7.c()
            if (r7 == 0) goto L_0x00a1
            cg.h r0 = new cg.h
            r4 = 1
            r0.<init>(r4)
            r7.d(r0, r4)
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = cg.j.d
            java.lang.Object r0 = r0.get(r7)
            java.lang.String r4 = "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode"
            kotlin.jvm.internal.j.c(r0, r4)
            cg.j r0 = (cg.j) r0
        L_0x0068:
            boolean r4 = r0.equals(r7)
            if (r4 != 0) goto L_0x009c
            boolean r4 = r0 instanceof Vf.i0
            if (r4 == 0) goto L_0x0097
            r4 = r0
            Vf.i0 r4 = (Vf.i0) r4     // Catch:{ all -> 0x0079 }
            r4.k(r8)     // Catch:{ all -> 0x0079 }
            goto L_0x0097
        L_0x0079:
            r4 = move-exception
            if (r1 == 0) goto L_0x0080
            He.F.e(r1, r4)
            goto L_0x0097
        L_0x0080:
            Dd.a r1 = new Dd.a
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>(r3)
            r5.append(r0)
            r5.append(r2)
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r1.<init>(r5, r4)
        L_0x0097:
            cg.j r0 = r0.g()
            goto L_0x0068
        L_0x009c:
            if (r1 == 0) goto L_0x00a1
            r6.B(r1)
        L_0x00a1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Vf.n0.s(Vf.b0, java.lang.Object):void");
    }

    public final Throwable t(Object obj) {
        Throwable th;
        if (obj instanceof Throwable) {
            return (Throwable) obj;
        }
        n0 n0Var = (n0) ((t0) obj);
        Object obj2 = d.get(n0Var);
        CancellationException cancellationException = null;
        if (obj2 instanceof l0) {
            th = ((l0) obj2).b();
        } else if (obj2 instanceof C0883u) {
            th = ((C0883u) obj2).f3874a;
        } else if (!(obj2 instanceof C0861b0)) {
            th = null;
        } else {
            throw new IllegalStateException(("Cannot be cancelling child in this state: " + obj2).toString());
        }
        if (th instanceof CancellationException) {
            cancellationException = (CancellationException) th;
        }
        if (cancellationException == null) {
            return new C0869f0("Parent job is ".concat(S(obj2)), th, n0Var);
        }
        return cancellationException;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder();
        sb2.append(J() + '{' + S(d.get(this)) + '}');
        sb2.append('@');
        sb2.append(D.j(this));
        return sb2.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x008e A[LOOP:1: B:39:0x008e->B:42:0x0099, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object u(Vf.l0 r8, java.lang.Object r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof Vf.C0883u
            r1 = 0
            if (r0 == 0) goto L_0x0009
            r0 = r9
            Vf.u r0 = (Vf.C0883u) r0
            goto L_0x000a
        L_0x0009:
            r0 = r1
        L_0x000a:
            if (r0 == 0) goto L_0x000e
            java.lang.Throwable r1 = r0.f3874a
        L_0x000e:
            monitor-enter(r8)
            r8.d()     // Catch:{ all -> 0x009f }
            java.util.ArrayList r0 = r8.e(r1)     // Catch:{ all -> 0x009f }
            java.lang.Throwable r2 = r7.w(r8, r0)     // Catch:{ all -> 0x009f }
            r3 = 1
            if (r2 == 0) goto L_0x0053
            int r4 = r0.size()     // Catch:{ all -> 0x009f }
            if (r4 > r3) goto L_0x0024
            goto L_0x0053
        L_0x0024:
            int r4 = r0.size()     // Catch:{ all -> 0x009f }
            java.util.IdentityHashMap r5 = new java.util.IdentityHashMap     // Catch:{ all -> 0x009f }
            r5.<init>(r4)     // Catch:{ all -> 0x009f }
            java.util.Set r4 = java.util.Collections.newSetFromMap(r5)     // Catch:{ all -> 0x009f }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x009f }
        L_0x0035:
            boolean r5 = r0.hasNext()     // Catch:{ all -> 0x009f }
            if (r5 == 0) goto L_0x0053
            java.lang.Object r5 = r0.next()     // Catch:{ all -> 0x009f }
            java.lang.Throwable r5 = (java.lang.Throwable) r5     // Catch:{ all -> 0x009f }
            if (r5 == r2) goto L_0x0035
            if (r5 == r2) goto L_0x0035
            boolean r6 = r5 instanceof java.util.concurrent.CancellationException     // Catch:{ all -> 0x009f }
            if (r6 != 0) goto L_0x0035
            boolean r6 = r4.add(r5)     // Catch:{ all -> 0x009f }
            if (r6 == 0) goto L_0x0035
            He.F.e(r2, r5)     // Catch:{ all -> 0x009f }
            goto L_0x0035
        L_0x0053:
            monitor-exit(r8)
            r0 = 0
            if (r2 != 0) goto L_0x0058
            goto L_0x0060
        L_0x0058:
            if (r2 != r1) goto L_0x005b
            goto L_0x0060
        L_0x005b:
            Vf.u r9 = new Vf.u
            r9.<init>(r2, r0)
        L_0x0060:
            if (r2 == 0) goto L_0x007b
            boolean r1 = r7.p(r2)
            if (r1 != 0) goto L_0x006e
            boolean r1 = r7.A(r2)
            if (r1 == 0) goto L_0x007b
        L_0x006e:
            java.lang.String r1 = "null cannot be cast to non-null type kotlinx.coroutines.CompletedExceptionally"
            kotlin.jvm.internal.j.c(r9, r1)
            r1 = r9
            Vf.u r1 = (Vf.C0883u) r1
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r2 = Vf.C0883u.b
            r2.compareAndSet(r1, r0, r3)
        L_0x007b:
            r7.M(r9)
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = d
            boolean r1 = r9 instanceof Vf.C0861b0
            if (r1 == 0) goto L_0x008d
            Vf.c0 r1 = new Vf.c0
            r2 = r9
            Vf.b0 r2 = (Vf.C0861b0) r2
            r1.<init>(r2)
            goto L_0x008e
        L_0x008d:
            r1 = r9
        L_0x008e:
            boolean r2 = r0.compareAndSet(r7, r8, r1)
            if (r2 == 0) goto L_0x0095
            goto L_0x009b
        L_0x0095:
            java.lang.Object r2 = r0.get(r7)
            if (r2 == r8) goto L_0x008e
        L_0x009b:
            r7.s(r8, r9)
            return r9
        L_0x009f:
            r7 = move-exception
            monitor-exit(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: Vf.n0.u(Vf.l0, java.lang.Object):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: java.lang.Throwable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.util.concurrent.CancellationException} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.concurrent.CancellationException v() {
        /*
            r4 = this;
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = d
            java.lang.Object r0 = r0.get(r4)
            boolean r1 = r0 instanceof Vf.l0
            java.lang.String r2 = "Job is still new or active: "
            r3 = 0
            if (r1 == 0) goto L_0x004f
            Vf.l0 r0 = (Vf.l0) r0
            java.lang.Throwable r0 = r0.b()
            if (r0 == 0) goto L_0x0039
            java.lang.Class r1 = r4.getClass()
            java.lang.String r1 = r1.getSimpleName()
            java.lang.String r2 = " is cancelling"
            java.lang.String r1 = r1.concat(r2)
            boolean r2 = r0 instanceof java.util.concurrent.CancellationException
            if (r2 == 0) goto L_0x002a
            r3 = r0
            java.util.concurrent.CancellationException r3 = (java.util.concurrent.CancellationException) r3
        L_0x002a:
            if (r3 != 0) goto L_0x0038
            Vf.f0 r2 = new Vf.f0
            if (r1 != 0) goto L_0x0034
            java.lang.String r1 = r4.q()
        L_0x0034:
            r2.<init>(r1, r0, r4)
            return r2
        L_0x0038:
            return r3
        L_0x0039:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r2)
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            java.lang.String r4 = r4.toString()
            r0.<init>(r4)
            throw r0
        L_0x004f:
            boolean r1 = r0 instanceof Vf.C0861b0
            if (r1 != 0) goto L_0x0083
            boolean r1 = r0 instanceof Vf.C0883u
            if (r1 == 0) goto L_0x006f
            Vf.u r0 = (Vf.C0883u) r0
            java.lang.Throwable r0 = r0.f3874a
            boolean r1 = r0 instanceof java.util.concurrent.CancellationException
            if (r1 == 0) goto L_0x0062
            r3 = r0
            java.util.concurrent.CancellationException r3 = (java.util.concurrent.CancellationException) r3
        L_0x0062:
            if (r3 != 0) goto L_0x006e
            Vf.f0 r1 = new Vf.f0
            java.lang.String r2 = r4.q()
            r1.<init>(r2, r0, r4)
            return r1
        L_0x006e:
            return r3
        L_0x006f:
            Vf.f0 r0 = new Vf.f0
            java.lang.Class r1 = r4.getClass()
            java.lang.String r1 = r1.getSimpleName()
            java.lang.String r2 = " has completed normally"
            java.lang.String r1 = r1.concat(r2)
            r0.<init>(r1, r3, r4)
            return r0
        L_0x0083:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r2)
            r1.append(r4)
            java.lang.String r4 = r1.toString()
            java.lang.String r4 = r4.toString()
            r0.<init>(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: Vf.n0.v():java.util.concurrent.CancellationException");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: java.lang.Throwable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.Throwable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: java.lang.Throwable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.lang.Throwable} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Throwable w(Vf.l0 r3, java.util.ArrayList r4) {
        /*
            r2 = this;
            boolean r0 = r4.isEmpty()
            r1 = 0
            if (r0 == 0) goto L_0x0018
            boolean r3 = r3.d()
            if (r3 == 0) goto L_0x0017
            Vf.f0 r3 = new Vf.f0
            java.lang.String r4 = r2.q()
            r3.<init>(r4, r1, r2)
            return r3
        L_0x0017:
            return r1
        L_0x0018:
            java.util.Iterator r2 = r4.iterator()
        L_0x001c:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x002e
            java.lang.Object r3 = r2.next()
            r0 = r3
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            boolean r0 = r0 instanceof java.util.concurrent.CancellationException
            if (r0 != 0) goto L_0x001c
            goto L_0x002f
        L_0x002e:
            r3 = r1
        L_0x002f:
            java.lang.Throwable r3 = (java.lang.Throwable) r3
            if (r3 == 0) goto L_0x0034
            return r3
        L_0x0034:
            r2 = 0
            java.lang.Object r2 = r4.get(r2)
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            boolean r3 = r2 instanceof Vf.z0
            if (r3 == 0) goto L_0x005c
            java.util.Iterator r3 = r4.iterator()
        L_0x0043:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0057
            java.lang.Object r4 = r3.next()
            r0 = r4
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            if (r0 == r2) goto L_0x0043
            boolean r0 = r0 instanceof Vf.z0
            if (r0 == 0) goto L_0x0043
            r1 = r4
        L_0x0057:
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            if (r1 == 0) goto L_0x005c
            return r1
        L_0x005c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: Vf.n0.w(Vf.l0, java.util.ArrayList):java.lang.Throwable");
    }

    public boolean x() {
        return true;
    }

    public boolean y() {
        return this instanceof r;
    }

    /* JADX WARNING: type inference failed for: r2v3, types: [Vf.q0, cg.j] */
    public final q0 z(C0861b0 b0Var) {
        q0 c5 = b0Var.c();
        if (c5 != null) {
            return c5;
        }
        if (b0Var instanceof Q) {
            return new j();
        }
        if (b0Var instanceof i0) {
            P((i0) b0Var);
            return null;
        }
        throw new IllegalStateException(("State should have list: " + b0Var).toString());
    }

    public void N() {
    }

    public void B(C0730a aVar) {
        throw aVar;
    }

    public void M(Object obj) {
    }

    public void k(Object obj) {
    }
}
