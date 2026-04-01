package lf;

import B2.o;
import java.io.IOException;
import rf.C1254d;
import rf.C1255e;
import rf.C1256f;
import rf.C1258h;
import rf.C1262l;
import rf.C1267q;
import rf.u;

/* renamed from: lf.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1152e extends C1267q {

    /* renamed from: j  reason: collision with root package name */
    public static final C1152e f4823j;
    public static final C1148a k = new C1148a(1);
    public final C1255e d;
    public int e;
    public int f;
    public C1151d g;

    /* renamed from: h  reason: collision with root package name */
    public byte f4824h;

    /* renamed from: i  reason: collision with root package name */
    public int f4825i;

    static {
        C1152e eVar = new C1152e();
        f4823j = eVar;
        eVar.f = 0;
        eVar.g = C1151d.s;
    }

    public C1152e() {
        this.f4824h = -1;
        this.f4825i = -1;
        this.d = C1255e.d;
    }

    public final int a() {
        int i2;
        int i7 = this.f4825i;
        if (i7 != -1) {
            return i7;
        }
        if ((this.e & 1) == 1) {
            i2 = o.b(1, this.f);
        } else {
            i2 = 0;
        }
        if ((this.e & 2) == 2) {
            i2 += o.d(2, this.g);
        }
        int size = this.d.size() + i2;
        this.f4825i = size;
        return size;
    }

    public final C1262l b() {
        C1153f fVar = new C1153f(2);
        fVar.g = C1151d.s;
        return fVar;
    }

    public final C1262l c() {
        C1153f fVar = new C1153f(2);
        fVar.g = C1151d.s;
        fVar.h(this);
        return fVar;
    }

    public final void d(o oVar) {
        a();
        if ((this.e & 1) == 1) {
            oVar.m(1, this.f);
        }
        if ((this.e & 2) == 2) {
            oVar.o(2, this.g);
        }
        oVar.r(this.d);
    }

    public final boolean isInitialized() {
        byte b = this.f4824h;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        int i2 = this.e;
        if ((i2 & 1) != 1) {
            this.f4824h = 0;
            return false;
        } else if ((i2 & 2) != 2) {
            this.f4824h = 0;
            return false;
        } else if (!this.g.isInitialized()) {
            this.f4824h = 0;
            return false;
        } else {
            this.f4824h = 1;
            return true;
        }
    }

    public C1152e(C1153f fVar) {
        this.f4824h = -1;
        this.f4825i = -1;
        this.d = fVar.d;
    }

    public C1152e(C1256f fVar, C1258h hVar) {
        C1149b bVar;
        this.f4824h = -1;
        this.f4825i = -1;
        boolean z = false;
        this.f = 0;
        this.g = C1151d.s;
        C1254d dVar = new C1254d();
        o j2 = o.j(dVar, 1);
        while (!z) {
            try {
                int n = fVar.n();
                if (n != 0) {
                    if (n == 8) {
                        this.e |= 1;
                        this.f = fVar.k();
                    } else if (n == 18) {
                        if ((this.e & 2) == 2) {
                            C1151d dVar2 = this.g;
                            dVar2.getClass();
                            bVar = C1149b.e();
                            bVar.f(dVar2);
                        } else {
                            bVar = null;
                        }
                        C1151d dVar3 = (C1151d) fVar.g(C1151d.t, hVar);
                        this.g = dVar3;
                        if (bVar != null) {
                            bVar.f(dVar3);
                            this.g = bVar.d();
                        }
                        this.e |= 2;
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
