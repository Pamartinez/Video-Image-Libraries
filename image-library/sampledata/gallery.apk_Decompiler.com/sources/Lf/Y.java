package lf;

import Dd.C0730a;
import rf.C1252b;
import rf.C1256f;
import rf.C1258h;
import rf.C1262l;
import rf.C1263m;
import rf.C1267q;
import rf.u;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Y extends C1263m {
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public int f4797h;

    /* renamed from: i  reason: collision with root package name */
    public int f4798i;

    /* renamed from: j  reason: collision with root package name */
    public Q f4799j;
    public int k;
    public Q l;
    public int m;

    public final C1252b a() {
        Z e = e();
        if (e.isInitialized()) {
            return e;
        }
        throw new C0730a();
    }

    public final C1262l b(C1256f fVar, C1258h hVar) {
        Z z;
        Z z3 = null;
        try {
            Z.f4801p.getClass();
            f(new Z(fVar, hVar));
            return this;
        } catch (u e) {
            z = (Z) e.d;
            throw e;
        } catch (Throwable th) {
            th = th;
            z3 = z;
        }
        if (z3 != null) {
            f(z3);
        }
        throw th;
    }

    public final /* bridge */ /* synthetic */ C1262l c(C1267q qVar) {
        f((Z) qVar);
        return this;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [rf.m, lf.Y, java.lang.Object] */
    public final Object clone() {
        ? mVar = new C1263m();
        Q q = Q.w;
        mVar.f4799j = q;
        mVar.l = q;
        mVar.f(e());
        return mVar;
    }

    public final Z e() {
        Z z = new Z(this);
        int i2 = this.g;
        int i7 = 1;
        if ((i2 & 1) != 1) {
            i7 = 0;
        }
        z.g = this.f4797h;
        if ((i2 & 2) == 2) {
            i7 |= 2;
        }
        z.f4802h = this.f4798i;
        if ((i2 & 4) == 4) {
            i7 |= 4;
        }
        z.f4803i = this.f4799j;
        if ((i2 & 8) == 8) {
            i7 |= 8;
        }
        z.f4804j = this.k;
        if ((i2 & 16) == 16) {
            i7 |= 16;
        }
        z.k = this.l;
        if ((i2 & 32) == 32) {
            i7 |= 32;
        }
        z.l = this.m;
        z.f = i7;
        return z;
    }

    public final void f(Z z) {
        Q q;
        Q q10;
        if (z != Z.f4800o) {
            int i2 = z.f;
            if ((i2 & 1) == 1) {
                int i7 = z.g;
                this.g = 1 | this.g;
                this.f4797h = i7;
            }
            if ((i2 & 2) == 2) {
                int i8 = z.f4802h;
                this.g = 2 | this.g;
                this.f4798i = i8;
            }
            if ((i2 & 4) == 4) {
                Q q11 = z.f4803i;
                if ((this.g & 4) != 4 || (q10 = this.f4799j) == Q.w) {
                    this.f4799j = q11;
                } else {
                    P p6 = Q.p(q10);
                    p6.g(q11);
                    this.f4799j = p6.e();
                }
                this.g |= 4;
            }
            int i10 = z.f;
            if ((i10 & 8) == 8) {
                int i11 = z.f4804j;
                this.g = 8 | this.g;
                this.k = i11;
            }
            if ((i10 & 16) == 16) {
                Q q12 = z.k;
                if ((this.g & 16) != 16 || (q = this.l) == Q.w) {
                    this.l = q12;
                } else {
                    P p8 = Q.p(q);
                    p8.g(q12);
                    this.l = p8.e();
                }
                this.g |= 16;
            }
            if ((z.f & 32) == 32) {
                int i12 = z.l;
                this.g = 32 | this.g;
                this.m = i12;
            }
            d(z);
            this.d = this.d.p(z.e);
        }
    }
}
