package lf;

import B2.o;
import java.io.IOException;
import rf.C1254d;
import rf.C1255e;
import rf.C1256f;
import rf.C1262l;
import rf.C1267q;
import rf.u;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class J extends C1267q {
    public static final J k;
    public static final C1148a l = new C1148a(14);
    public final C1255e d;
    public int e;
    public int f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public I f4754h;

    /* renamed from: i  reason: collision with root package name */
    public byte f4755i;

    /* renamed from: j  reason: collision with root package name */
    public int f4756j;

    static {
        J j2 = new J();
        k = j2;
        j2.f = -1;
        j2.g = 0;
        j2.f4754h = I.PACKAGE;
    }

    public J() {
        this.f4755i = -1;
        this.f4756j = -1;
        this.d = C1255e.d;
    }

    public final int a() {
        int i2;
        int i7 = this.f4756j;
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
        if ((this.e & 4) == 4) {
            i2 += o.a(3, this.f4754h.a());
        }
        int size = this.d.size() + i2;
        this.f4756j = size;
        return size;
    }

    public final C1262l b() {
        return H.e();
    }

    public final C1262l c() {
        H e7 = H.e();
        e7.f(this);
        return e7;
    }

    public final void d(o oVar) {
        a();
        if ((this.e & 1) == 1) {
            oVar.m(1, this.f);
        }
        if ((this.e & 2) == 2) {
            oVar.m(2, this.g);
        }
        if ((this.e & 4) == 4) {
            oVar.l(3, this.f4754h.a());
        }
        oVar.r(this.d);
    }

    public final boolean isInitialized() {
        byte b = this.f4755i;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        if ((this.e & 2) == 2) {
            this.f4755i = 1;
            return true;
        }
        this.f4755i = 0;
        return false;
    }

    public J(H h5) {
        this.f4755i = -1;
        this.f4756j = -1;
        this.d = h5.d;
    }

    public J(C1256f fVar) {
        I i2;
        this.f4755i = -1;
        this.f4756j = -1;
        this.f = -1;
        boolean z = false;
        this.g = 0;
        this.f4754h = I.PACKAGE;
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
                    } else if (n == 24) {
                        int k10 = fVar.k();
                        if (k10 == 0) {
                            i2 = I.CLASS;
                        } else if (k10 == 1) {
                            i2 = I.PACKAGE;
                        } else if (k10 != 2) {
                            i2 = null;
                        } else {
                            i2 = I.LOCAL;
                        }
                        if (i2 == null) {
                            j2.v(n);
                            j2.v(k10);
                        } else {
                            this.e |= 4;
                            this.f4754h = i2;
                        }
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
