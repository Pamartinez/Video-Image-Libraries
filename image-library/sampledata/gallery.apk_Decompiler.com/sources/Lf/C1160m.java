package lf;

import Dd.C0730a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import rf.C1252b;
import rf.C1256f;
import rf.C1258h;
import rf.C1262l;
import rf.C1267q;
import rf.u;
import rf.v;
import rf.w;
import rf.y;

/* renamed from: lf.m  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1160m extends C1262l implements y {
    public final /* synthetic */ int e;
    public int f;
    public List g;

    public /* synthetic */ C1160m(int i2) {
        this.e = i2;
    }

    public final C1252b a() {
        switch (this.e) {
            case 0:
                C1161n d = d();
                if (d.isInitialized()) {
                    return d;
                }
                throw new C0730a();
            case 1:
                K e7 = e();
                if (e7.isInitialized()) {
                    return e7;
                }
                throw new C0730a();
            case 2:
                e0 g3 = g();
                g3.isInitialized();
                return g3;
            default:
                L f5 = f();
                f5.isInitialized();
                return f5;
        }
    }

    public final C1262l b(C1256f fVar, C1258h hVar) {
        C1161n nVar;
        C1161n nVar2;
        K k;
        K k10;
        e0 e0Var;
        e0 e0Var2;
        L l;
        L l8;
        switch (this.e) {
            case 0:
                nVar = null;
                try {
                    C1161n.f4863i.getClass();
                    h(new C1161n(fVar, hVar));
                    return this;
                } catch (u e7) {
                    nVar2 = (C1161n) e7.d;
                    throw e7;
                } catch (Throwable th) {
                    th = th;
                    nVar = nVar2;
                    break;
                }
            case 1:
                k = null;
                try {
                    K.f4758i.getClass();
                    j(new K(fVar, hVar));
                    return this;
                } catch (u e8) {
                    k10 = (K) e8.d;
                    throw e8;
                } catch (Throwable th2) {
                    th = th2;
                    k = k10;
                    break;
                }
            case 2:
                e0Var = null;
                try {
                    e0.f4827i.getClass();
                    l(new e0(fVar, hVar));
                    return this;
                } catch (u e9) {
                    e0Var2 = (e0) e9.d;
                    throw e9;
                } catch (Throwable th3) {
                    th = th3;
                    e0Var = e0Var2;
                    break;
                }
            default:
                l = null;
                try {
                    L.f4760i.getClass();
                    k(new L(fVar));
                    return this;
                } catch (u e10) {
                    l8 = (L) e10.d;
                    throw e10;
                } catch (Throwable th4) {
                    th = th4;
                    l = l8;
                    break;
                }
        }
        if (nVar != null) {
            h(nVar);
        }
        throw th;
        if (k != null) {
            j(k);
        }
        throw th;
        if (e0Var != null) {
            l(e0Var);
        }
        throw th;
        if (l != null) {
            k(l);
        }
        throw th;
    }

    public final /* bridge */ /* synthetic */ C1262l c(C1267q qVar) {
        switch (this.e) {
            case 0:
                h((C1161n) qVar);
                return this;
            case 1:
                j((K) qVar);
                return this;
            case 2:
                l((e0) qVar);
                return this;
            default:
                k((L) qVar);
                return this;
        }
    }

    public final Object clone() {
        switch (this.e) {
            case 0:
                C1160m mVar = new C1160m(0);
                mVar.g = Collections.EMPTY_LIST;
                mVar.h(d());
                return mVar;
            case 1:
                C1160m mVar2 = new C1160m(1);
                mVar2.g = Collections.EMPTY_LIST;
                mVar2.j(e());
                return mVar2;
            case 2:
                C1160m mVar3 = new C1160m(2);
                mVar3.g = Collections.EMPTY_LIST;
                mVar3.l(g());
                return mVar3;
            default:
                C1160m mVar4 = new C1160m(3);
                mVar4.g = v.e;
                mVar4.k(f());
                return mVar4;
        }
    }

    public C1161n d() {
        C1161n nVar = new C1161n(this);
        if ((this.f & 1) == 1) {
            this.g = Collections.unmodifiableList(this.g);
            this.f &= -2;
        }
        nVar.e = this.g;
        return nVar;
    }

    public K e() {
        K k = new K(this);
        if ((this.f & 1) == 1) {
            this.g = Collections.unmodifiableList(this.g);
            this.f &= -2;
        }
        k.e = this.g;
        return k;
    }

    public L f() {
        L l = new L(this);
        if ((this.f & 1) == 1) {
            this.g = ((w) this.g).c();
            this.f &= -2;
        }
        l.e = (w) this.g;
        return l;
    }

    public e0 g() {
        e0 e0Var = new e0(this);
        if ((this.f & 1) == 1) {
            this.g = Collections.unmodifiableList(this.g);
            this.f &= -2;
        }
        e0Var.e = this.g;
        return e0Var;
    }

    public void h(C1161n nVar) {
        if (nVar != C1161n.f4862h) {
            if (!nVar.e.isEmpty()) {
                if (this.g.isEmpty()) {
                    this.g = nVar.e;
                    this.f &= -2;
                } else {
                    if ((this.f & 1) != 1) {
                        this.g = new ArrayList(this.g);
                        this.f |= 1;
                    }
                    this.g.addAll(nVar.e);
                }
            }
            this.d = this.d.p(nVar.d);
        }
    }

    public void j(K k) {
        if (k != K.f4757h) {
            if (!k.e.isEmpty()) {
                if (this.g.isEmpty()) {
                    this.g = k.e;
                    this.f &= -2;
                } else {
                    if ((this.f & 1) != 1) {
                        this.g = new ArrayList(this.g);
                        this.f |= 1;
                    }
                    this.g.addAll(k.e);
                }
            }
            this.d = this.d.p(k.d);
        }
    }

    public void k(L l) {
        if (l != L.f4759h) {
            if (!l.e.isEmpty()) {
                if (((w) this.g).isEmpty()) {
                    this.g = l.e;
                    this.f &= -2;
                } else {
                    if ((this.f & 1) != 1) {
                        this.g = new v((w) this.g);
                        this.f |= 1;
                    }
                    ((w) this.g).addAll(l.e);
                }
            }
            this.d = this.d.p(l.d);
        }
    }

    public void l(e0 e0Var) {
        if (e0Var != e0.f4826h) {
            if (!e0Var.e.isEmpty()) {
                if (this.g.isEmpty()) {
                    this.g = e0Var.e;
                    this.f &= -2;
                } else {
                    if ((this.f & 1) != 1) {
                        this.g = new ArrayList(this.g);
                        this.f |= 1;
                    }
                    this.g.addAll(e0Var.e);
                }
            }
            this.d = this.d.p(e0Var.d);
        }
    }
}
