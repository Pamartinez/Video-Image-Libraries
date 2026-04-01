package of;

import B2.o;
import java.io.IOException;
import lf.C1148a;
import rf.C1254d;
import rf.C1255e;
import rf.C1256f;
import rf.C1262l;
import rf.C1267q;
import rf.u;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c extends C1267q {

    /* renamed from: j  reason: collision with root package name */
    public static final c f4988j;
    public static final C1148a k = new C1148a(25);
    public final C1255e d;
    public int e;
    public int f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public byte f4989h;

    /* renamed from: i  reason: collision with root package name */
    public int f4990i;

    static {
        c cVar = new c();
        f4988j = cVar;
        cVar.f = 0;
        cVar.g = 0;
    }

    public c() {
        this.f4989h = -1;
        this.f4990i = -1;
        this.d = C1255e.d;
    }

    public static a g(c cVar) {
        a aVar = new a(1);
        aVar.g(cVar);
        return aVar;
    }

    public final int a() {
        int i2;
        int i7 = this.f4990i;
        if (i7 != -1) {
            return i7;
        }
        if ((this.e & 1) == 1) {
            i2 = o.b(1, this.f);
        } else {
            i2 = 0;
        }
        if ((this.e & 2) == 2) {
            i2 += o.b(2, this.g);
        }
        int size = this.d.size() + i2;
        this.f4990i = size;
        return size;
    }

    public final C1262l b() {
        return new a(1);
    }

    public final C1262l c() {
        return g(this);
    }

    public final void d(o oVar) {
        a();
        if ((this.e & 1) == 1) {
            oVar.m(1, this.f);
        }
        if ((this.e & 2) == 2) {
            oVar.m(2, this.g);
        }
        oVar.r(this.d);
    }

    public final boolean isInitialized() {
        if (this.f4989h == 1) {
            return true;
        }
        this.f4989h = 1;
        return true;
    }

    public c(a aVar) {
        this.f4989h = -1;
        this.f4990i = -1;
        this.d = aVar.d;
    }

    public c(C1256f fVar) {
        this.f4989h = -1;
        this.f4990i = -1;
        boolean z = false;
        this.f = 0;
        this.g = 0;
        C1254d dVar = new C1254d();
        o j2 = o.j(dVar, 1);
        while (!z) {
            try {
                int n = fVar.n();
                if (n != 0) {
                    if (n == 8) {
                        this.e |= 1;
                        this.f = fVar.k();
                    } else if (n == 16) {
                        this.e |= 2;
                        this.g = fVar.k();
                    } else if (fVar.q(n, j2)) {
                    }
                }
                z = true;
            } catch (u e7) {
                e7.d = this;
                throw e7;
            } catch (IOException e8) {
                u uVar = new u(e8.getMessage());
                uVar.d = this;
                throw uVar;
            } catch (Throwable th) {
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
