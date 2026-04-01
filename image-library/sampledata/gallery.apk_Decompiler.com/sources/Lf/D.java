package lf;

import Dd.C0730a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import rf.C1252b;
import rf.C1256f;
import rf.C1258h;
import rf.C1262l;
import rf.C1263m;
import rf.C1267q;
import rf.u;
import rf.v;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class D extends C1263m {
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public L f4736h;

    /* renamed from: i  reason: collision with root package name */
    public K f4737i;

    /* renamed from: j  reason: collision with root package name */
    public C f4738j;
    public List k;

    /* JADX WARNING: type inference failed for: r0v0, types: [lf.D, rf.m] */
    public static D f() {
        ? mVar = new C1263m();
        mVar.f4736h = L.f4759h;
        mVar.f4737i = K.f4757h;
        mVar.f4738j = C.n;
        mVar.k = Collections.EMPTY_LIST;
        return mVar;
    }

    public final C1252b a() {
        E e = e();
        if (e.isInitialized()) {
            return e;
        }
        throw new C0730a();
    }

    public final C1262l b(C1256f fVar, C1258h hVar) {
        E e;
        E e7 = null;
        try {
            E.n.getClass();
            g(new E(fVar, hVar));
            return this;
        } catch (u e8) {
            e = (E) e8.d;
            throw e8;
        } catch (Throwable th) {
            th = th;
            e7 = e;
        }
        if (e7 != null) {
            g(e7);
        }
        throw th;
    }

    public final /* bridge */ /* synthetic */ C1262l c(C1267q qVar) {
        g((E) qVar);
        return this;
    }

    public final Object clone() {
        D f = f();
        f.g(e());
        return f;
    }

    public final E e() {
        E e = new E(this);
        int i2 = this.g;
        int i7 = 1;
        if ((i2 & 1) != 1) {
            i7 = 0;
        }
        e.g = this.f4736h;
        if ((i2 & 2) == 2) {
            i7 |= 2;
        }
        e.f4739h = this.f4737i;
        if ((i2 & 4) == 4) {
            i7 |= 4;
        }
        e.f4740i = this.f4738j;
        if ((i2 & 8) == 8) {
            this.k = Collections.unmodifiableList(this.k);
            this.g &= -9;
        }
        e.f4741j = this.k;
        e.f = i7;
        return e;
    }

    public final void g(E e) {
        C c5;
        K k10;
        L l;
        if (e != E.m) {
            if ((e.f & 1) == 1) {
                L l8 = e.g;
                if ((this.g & 1) != 1 || (l = this.f4736h) == L.f4759h) {
                    this.f4736h = l8;
                } else {
                    C1160m mVar = new C1160m(3);
                    mVar.g = v.e;
                    mVar.k(l);
                    mVar.k(l8);
                    this.f4736h = mVar.f();
                }
                this.g |= 1;
            }
            if ((e.f & 2) == 2) {
                K k11 = e.f4739h;
                if ((this.g & 2) != 2 || (k10 = this.f4737i) == K.f4757h) {
                    this.f4737i = k11;
                } else {
                    C1160m mVar2 = new C1160m(1);
                    mVar2.g = Collections.EMPTY_LIST;
                    mVar2.j(k10);
                    mVar2.j(k11);
                    this.f4737i = mVar2.e();
                }
                this.g |= 2;
            }
            if ((e.f & 4) == 4) {
                C c6 = e.f4740i;
                if ((this.g & 4) != 4 || (c5 = this.f4738j) == C.n) {
                    this.f4738j = c6;
                } else {
                    B f = B.f();
                    f.g(c5);
                    f.g(c6);
                    this.f4738j = f.e();
                }
                this.g |= 4;
            }
            if (!e.f4741j.isEmpty()) {
                if (this.k.isEmpty()) {
                    this.k = e.f4741j;
                    this.g &= -9;
                } else {
                    if ((this.g & 8) != 8) {
                        this.k = new ArrayList(this.k);
                        this.g |= 8;
                    }
                    this.k.addAll(e.f4741j);
                }
            }
            d(e);
            this.d = this.d.p(e.e);
        }
    }
}
