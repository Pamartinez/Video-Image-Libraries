package lf;

import B2.o;
import java.io.IOException;
import rf.C1254d;
import rf.C1255e;
import rf.C1256f;
import rf.C1262l;
import rf.C1267q;
import rf.u;
import rf.v;
import rf.w;
import rf.x;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class L extends C1267q {

    /* renamed from: h  reason: collision with root package name */
    public static final L f4759h;

    /* renamed from: i  reason: collision with root package name */
    public static final C1148a f4760i = new C1148a(15);
    public final C1255e d;
    public w e;
    public byte f;
    public int g;

    static {
        L l = new L();
        f4759h = l;
        l.e = v.e;
    }

    public L() {
        this.f = -1;
        this.g = -1;
        this.d = C1255e.d;
    }

    public final int a() {
        int i2 = this.g;
        if (i2 != -1) {
            return i2;
        }
        int i7 = 0;
        for (int i8 = 0; i8 < this.e.size(); i8++) {
            C1255e h5 = this.e.h(i8);
            i7 += h5.size() + o.f(h5.size());
        }
        int size = this.d.size() + this.e.size() + i7;
        this.g = size;
        return size;
    }

    public final C1262l b() {
        C1160m mVar = new C1160m(3);
        mVar.g = v.e;
        return mVar;
    }

    public final C1262l c() {
        C1160m mVar = new C1160m(3);
        mVar.g = v.e;
        mVar.k(this);
        return mVar;
    }

    public final void d(o oVar) {
        a();
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            C1255e h5 = this.e.h(i2);
            oVar.x(1, 2);
            oVar.v(h5.size());
            oVar.r(h5);
        }
        oVar.r(this.d);
    }

    public final boolean isInitialized() {
        if (this.f == 1) {
            return true;
        }
        this.f = 1;
        return true;
    }

    public L(C1160m mVar) {
        this.f = -1;
        this.g = -1;
        this.d = mVar.d;
    }

    public L(C1256f fVar) {
        this.f = -1;
        this.g = -1;
        this.e = v.e;
        C1254d dVar = new C1254d();
        o j2 = o.j(dVar, 1);
        boolean z = false;
        boolean z3 = false;
        while (!z) {
            try {
                int n = fVar.n();
                if (n != 0) {
                    if (n == 10) {
                        x e7 = fVar.e();
                        if (!z3) {
                            this.e = new v();
                            z3 = true;
                        }
                        this.e.l(e7);
                    } else if (fVar.q(n, j2)) {
                    }
                }
                z = true;
            } catch (u e8) {
                e8.d = this;
                throw e8;
            } catch (IOException e9) {
                u uVar = new u(e9.getMessage());
                uVar.d = this;
                throw uVar;
            } catch (Throwable th) {
                if (z3) {
                    this.e = this.e.c();
                }
                try {
                    j2.i();
                } catch (IOException unused) {
                } catch (Throwable th2) {
                    this.d = dVar.f();
                    throw th2;
                }
                this.d = dVar.f();
                throw th;
            }
        }
        if (z3) {
            this.e = this.e.c();
        }
        try {
            j2.i();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.d = dVar.f();
            throw th3;
        }
        this.d = dVar.f();
    }
}
