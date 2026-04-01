package lf;

import Dd.C0730a;
import rf.C1252b;
import rf.C1256f;
import rf.C1258h;
import rf.C1262l;
import rf.C1267q;
import rf.u;
import rf.y;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class M extends C1262l implements y {
    public int e;
    public N f;
    public Q g;

    /* renamed from: h  reason: collision with root package name */
    public int f4761h;

    /* JADX WARNING: type inference failed for: r0v0, types: [rf.l, lf.M] */
    public static M e() {
        ? lVar = new C1262l();
        lVar.f = N.INV;
        lVar.g = Q.w;
        return lVar;
    }

    public final C1252b a() {
        O d = d();
        if (d.isInitialized()) {
            return d;
        }
        throw new C0730a();
    }

    public final C1262l b(C1256f fVar, C1258h hVar) {
        O o2;
        O o3 = null;
        try {
            O.l.getClass();
            f(new O(fVar, hVar));
            return this;
        } catch (u e7) {
            o2 = (O) e7.d;
            throw e7;
        } catch (Throwable th) {
            th = th;
            o3 = o2;
        }
        if (o3 != null) {
            f(o3);
        }
        throw th;
    }

    public final /* bridge */ /* synthetic */ C1262l c(C1267q qVar) {
        f((O) qVar);
        return this;
    }

    public final Object clone() {
        M e7 = e();
        e7.f(d());
        return e7;
    }

    public final O d() {
        O o2 = new O(this);
        int i2 = this.e;
        int i7 = 1;
        if ((i2 & 1) != 1) {
            i7 = 0;
        }
        o2.f = this.f;
        if ((i2 & 2) == 2) {
            i7 |= 2;
        }
        o2.g = this.g;
        if ((i2 & 4) == 4) {
            i7 |= 4;
        }
        o2.f4762h = this.f4761h;
        o2.e = i7;
        return o2;
    }

    public final void f(O o2) {
        Q q;
        if (o2 != O.k) {
            if ((o2.e & 1) == 1) {
                N n = o2.f;
                n.getClass();
                this.e = 1 | this.e;
                this.f = n;
            }
            if ((o2.e & 2) == 2) {
                Q q10 = o2.g;
                if ((this.e & 2) != 2 || (q = this.g) == Q.w) {
                    this.g = q10;
                } else {
                    P p6 = Q.p(q);
                    p6.g(q10);
                    this.g = p6.e();
                }
                this.e |= 2;
            }
            if ((o2.e & 4) == 4) {
                int i2 = o2.f4762h;
                this.e = 4 | this.e;
                this.f4761h = i2;
            }
            this.d = this.d.p(o2.d);
        }
    }
}
