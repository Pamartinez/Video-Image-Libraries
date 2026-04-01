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
public final class H extends C1262l implements y {
    public int e;
    public int f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public I f4753h;

    /* JADX WARNING: type inference failed for: r0v0, types: [lf.H, rf.l] */
    public static H e() {
        ? lVar = new C1262l();
        lVar.f = -1;
        lVar.f4753h = I.PACKAGE;
        return lVar;
    }

    public final C1252b a() {
        J d = d();
        if (d.isInitialized()) {
            return d;
        }
        throw new C0730a();
    }

    public final C1262l b(C1256f fVar, C1258h hVar) {
        J j2;
        J j3 = null;
        try {
            J.l.getClass();
            f(new J(fVar));
            return this;
        } catch (u e7) {
            j2 = (J) e7.d;
            throw e7;
        } catch (Throwable th) {
            th = th;
            j3 = j2;
        }
        if (j3 != null) {
            f(j3);
        }
        throw th;
    }

    public final /* bridge */ /* synthetic */ C1262l c(C1267q qVar) {
        f((J) qVar);
        return this;
    }

    public final Object clone() {
        H e7 = e();
        e7.f(d());
        return e7;
    }

    public final J d() {
        J j2 = new J(this);
        int i2 = this.e;
        int i7 = 1;
        if ((i2 & 1) != 1) {
            i7 = 0;
        }
        j2.f = this.f;
        if ((i2 & 2) == 2) {
            i7 |= 2;
        }
        j2.g = this.g;
        if ((i2 & 4) == 4) {
            i7 |= 4;
        }
        j2.f4754h = this.f4753h;
        j2.e = i7;
        return j2;
    }

    public final void f(J j2) {
        if (j2 != J.k) {
            int i2 = j2.e;
            if ((i2 & 1) == 1) {
                int i7 = j2.f;
                this.e = 1 | this.e;
                this.f = i7;
            }
            if ((i2 & 2) == 2) {
                int i8 = j2.g;
                this.e = 2 | this.e;
                this.g = i8;
            }
            if ((i2 & 4) == 4) {
                I i10 = j2.f4754h;
                i10.getClass();
                this.e = 4 | this.e;
                this.f4753h = i10;
            }
            this.d = this.d.p(j2.d);
        }
    }
}
