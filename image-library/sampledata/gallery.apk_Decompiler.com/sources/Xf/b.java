package Xf;

import L1.d;
import L2.a;
import Qe.B;
import Vf.C0875l;
import Vf.D;
import Vf.E0;
import cg.s;
import cg.t;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.internal.j;
import re.C1245a;
import se.C1271c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b implements E0 {
    public Object d = g.f3908p;
    public C0875l e;
    public final /* synthetic */ e f;

    public b(e eVar) {
        this.f = eVar;
    }

    public final void a(s sVar, int i2) {
        C0875l lVar = this.e;
        if (lVar != null) {
            lVar.a(sVar, i2);
        }
    }

    public final Object b(C1271c cVar) {
        m mVar;
        m mVar2;
        Object obj = this.d;
        boolean z = true;
        if (obj == g.f3908p || obj == g.l) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = e.f3901j;
            e eVar = this.f;
            m mVar3 = (m) atomicReferenceFieldUpdater.get(eVar);
            while (true) {
                if (eVar.t(e.e.get(eVar), true)) {
                    this.d = g.l;
                    Throwable p6 = eVar.p();
                    if (p6 == null) {
                        z = false;
                    } else {
                        int i2 = t.f4033a;
                        throw p6;
                    }
                } else {
                    long andIncrement = e.f.getAndIncrement(eVar);
                    long j2 = (long) g.b;
                    long j3 = andIncrement / j2;
                    int i7 = (int) (andIncrement % j2);
                    if (mVar3.f4032c != j3) {
                        m o2 = eVar.o(j3, mVar3);
                        if (o2 == null) {
                            continue;
                        } else {
                            mVar = o2;
                        }
                    } else {
                        mVar = mVar3;
                    }
                    Object E = eVar.E(mVar, i7, andIncrement, (Object) null);
                    B b = g.m;
                    if (E != b) {
                        B b5 = g.f3907o;
                        if (E == b5) {
                            if (andIncrement < eVar.r()) {
                                mVar.b();
                            }
                            mVar3 = mVar;
                        } else if (E == g.n) {
                            C0875l k = D.k(d.m(cVar));
                            try {
                                this.e = k;
                                Object E4 = eVar.E(mVar, i7, andIncrement, this);
                                if (E4 == b) {
                                    a(mVar, i7);
                                } else {
                                    if (E4 == b5) {
                                        if (andIncrement < eVar.r()) {
                                            mVar.b();
                                        }
                                        m mVar4 = (m) e.f3901j.get(eVar);
                                        while (true) {
                                            if (eVar.t(e.e.get(eVar), true)) {
                                                C0875l lVar = this.e;
                                                j.b(lVar);
                                                this.e = null;
                                                this.d = g.l;
                                                Throwable p8 = eVar.p();
                                                if (p8 == null) {
                                                    lVar.resumeWith(Boolean.FALSE);
                                                } else {
                                                    lVar.resumeWith(a.l(p8));
                                                }
                                            } else {
                                                long andIncrement2 = e.f.getAndIncrement(eVar);
                                                long j8 = (long) g.b;
                                                long j10 = andIncrement2 / j8;
                                                int i8 = (int) (andIncrement2 % j8);
                                                if (mVar4.f4032c != j10) {
                                                    m o3 = eVar.o(j10, mVar4);
                                                    if (o3 != null) {
                                                        mVar2 = o3;
                                                    }
                                                } else {
                                                    mVar2 = mVar4;
                                                }
                                                Object E8 = eVar.E(mVar2, i8, andIncrement2, this);
                                                m mVar5 = mVar2;
                                                if (E8 == g.m) {
                                                    a(mVar5, i8);
                                                    break;
                                                } else if (E8 == g.f3907o) {
                                                    if (andIncrement2 < eVar.r()) {
                                                        mVar5.b();
                                                    }
                                                    mVar4 = mVar5;
                                                } else if (E8 != g.n) {
                                                    mVar5.b();
                                                    this.d = E8;
                                                    this.e = null;
                                                } else {
                                                    throw new IllegalStateException("unexpected");
                                                }
                                            }
                                        }
                                    } else {
                                        mVar.b();
                                        this.d = E4;
                                        this.e = null;
                                    }
                                    k.z(Boolean.TRUE, (Ae.d) null);
                                }
                                Object q = k.q();
                                C1245a aVar = C1245a.COROUTINE_SUSPENDED;
                                return q;
                            } catch (Throwable th) {
                                Throwable th2 = th;
                                k.y();
                                throw th2;
                            }
                        } else {
                            mVar.b();
                            this.d = E;
                        }
                    } else {
                        throw new IllegalStateException("unreachable");
                    }
                }
            }
        }
        return Boolean.valueOf(z);
    }

    public final Object c() {
        Object obj = this.d;
        B b = g.f3908p;
        if (obj != b) {
            this.d = b;
            if (obj != g.l) {
                return obj;
            }
            Throwable p6 = this.f.p();
            if (p6 == null) {
                p6 = new NoSuchElementException("Channel was closed");
            }
            int i2 = t.f4033a;
            throw p6;
        }
        throw new IllegalStateException("`hasNext()` has not been invoked");
    }
}
