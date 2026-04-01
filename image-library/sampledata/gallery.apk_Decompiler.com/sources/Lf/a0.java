package lf;

import rf.C1252b;
import rf.C1256f;
import rf.C1258h;
import rf.C1262l;
import rf.C1267q;
import rf.u;
import rf.y;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a0 extends C1262l implements y {
    public int e;
    public int f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public b0 f4806h;

    /* renamed from: i  reason: collision with root package name */
    public int f4807i;

    /* renamed from: j  reason: collision with root package name */
    public int f4808j;
    public c0 k;

    /* JADX WARNING: type inference failed for: r0v0, types: [lf.a0, rf.l] */
    public static a0 e() {
        ? lVar = new C1262l();
        lVar.f4806h = b0.ERROR;
        lVar.k = c0.LANGUAGE_VERSION;
        return lVar;
    }

    public final C1252b a() {
        d0 d = d();
        d.isInitialized();
        return d;
    }

    public final C1262l b(C1256f fVar, C1258h hVar) {
        d0 d0Var;
        d0 d0Var2 = null;
        try {
            d0.f4819o.getClass();
            f(new d0(fVar));
            return this;
        } catch (u e7) {
            d0Var = (d0) e7.d;
            throw e7;
        } catch (Throwable th) {
            th = th;
            d0Var2 = d0Var;
        }
        if (d0Var2 != null) {
            f(d0Var2);
        }
        throw th;
    }

    public final /* bridge */ /* synthetic */ C1262l c(C1267q qVar) {
        f((d0) qVar);
        return this;
    }

    public final Object clone() {
        a0 e7 = e();
        e7.f(d());
        return e7;
    }

    public final d0 d() {
        d0 d0Var = new d0(this);
        int i2 = this.e;
        int i7 = 1;
        if ((i2 & 1) != 1) {
            i7 = 0;
        }
        d0Var.f = this.f;
        if ((i2 & 2) == 2) {
            i7 |= 2;
        }
        d0Var.g = this.g;
        if ((i2 & 4) == 4) {
            i7 |= 4;
        }
        d0Var.f4820h = this.f4806h;
        if ((i2 & 8) == 8) {
            i7 |= 8;
        }
        d0Var.f4821i = this.f4807i;
        if ((i2 & 16) == 16) {
            i7 |= 16;
        }
        d0Var.f4822j = this.f4808j;
        if ((i2 & 32) == 32) {
            i7 |= 32;
        }
        d0Var.k = this.k;
        d0Var.e = i7;
        return d0Var;
    }

    public final void f(d0 d0Var) {
        if (d0Var != d0.n) {
            int i2 = d0Var.e;
            if ((i2 & 1) == 1) {
                int i7 = d0Var.f;
                this.e = 1 | this.e;
                this.f = i7;
            }
            if ((i2 & 2) == 2) {
                int i8 = d0Var.g;
                this.e = 2 | this.e;
                this.g = i8;
            }
            if ((i2 & 4) == 4) {
                b0 b0Var = d0Var.f4820h;
                b0Var.getClass();
                this.e = 4 | this.e;
                this.f4806h = b0Var;
            }
            int i10 = d0Var.e;
            if ((i10 & 8) == 8) {
                int i11 = d0Var.f4821i;
                this.e = 8 | this.e;
                this.f4807i = i11;
            }
            if ((i10 & 16) == 16) {
                int i12 = d0Var.f4822j;
                this.e = 16 | this.e;
                this.f4808j = i12;
            }
            if ((i10 & 32) == 32) {
                c0 c0Var = d0Var.k;
                c0Var.getClass();
                this.e = 32 | this.e;
                this.k = c0Var;
            }
            this.d = this.d.p(d0Var.d);
        }
    }
}
