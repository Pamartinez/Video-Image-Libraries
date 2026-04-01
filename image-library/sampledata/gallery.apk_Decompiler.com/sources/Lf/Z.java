package lf;

import B2.o;
import ge.W0;
import java.io.IOException;
import rf.C1252b;
import rf.C1254d;
import rf.C1255e;
import rf.C1256f;
import rf.C1258h;
import rf.C1262l;
import rf.C1263m;
import rf.C1264n;
import rf.u;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Z extends C1264n {

    /* renamed from: o  reason: collision with root package name */
    public static final Z f4800o;

    /* renamed from: p  reason: collision with root package name */
    public static final C1148a f4801p = new C1148a(21);
    public final C1255e e;
    public int f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public int f4802h;

    /* renamed from: i  reason: collision with root package name */
    public Q f4803i;

    /* renamed from: j  reason: collision with root package name */
    public int f4804j;
    public Q k;
    public int l;
    public byte m;
    public int n;

    static {
        Z z = new Z();
        f4800o = z;
        z.g = 0;
        z.f4802h = 0;
        Q q = Q.w;
        z.f4803i = q;
        z.f4804j = 0;
        z.k = q;
        z.l = 0;
    }

    public Z(Y y) {
        super(y);
        this.m = -1;
        this.n = -1;
        this.e = y.d;
    }

    public final int a() {
        int i2;
        int i7 = this.n;
        if (i7 != -1) {
            return i7;
        }
        if ((this.f & 1) == 1) {
            i2 = o.b(1, this.g);
        } else {
            i2 = 0;
        }
        if ((this.f & 2) == 2) {
            i2 += o.b(2, this.f4802h);
        }
        if ((this.f & 4) == 4) {
            i2 += o.d(3, this.f4803i);
        }
        if ((this.f & 16) == 16) {
            i2 += o.d(4, this.k);
        }
        if ((this.f & 8) == 8) {
            i2 += o.b(5, this.f4804j);
        }
        if ((this.f & 32) == 32) {
            i2 += o.b(6, this.l);
        }
        int size = this.e.size() + h() + i2;
        this.n = size;
        return size;
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [rf.m, lf.Y, rf.l] */
    public final C1262l b() {
        ? mVar = new C1263m();
        Q q = Q.w;
        mVar.f4799j = q;
        mVar.l = q;
        return mVar;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [rf.m, lf.Y, rf.l] */
    public final C1262l c() {
        ? mVar = new C1263m();
        Q q = Q.w;
        mVar.f4799j = q;
        mVar.l = q;
        mVar.f(this);
        return mVar;
    }

    public final void d(o oVar) {
        a();
        W0 w02 = new W0((C1264n) this);
        if ((this.f & 1) == 1) {
            oVar.m(1, this.g);
        }
        if ((this.f & 2) == 2) {
            oVar.m(2, this.f4802h);
        }
        if ((this.f & 4) == 4) {
            oVar.o(3, this.f4803i);
        }
        if ((this.f & 16) == 16) {
            oVar.o(4, this.k);
        }
        if ((this.f & 8) == 8) {
            oVar.m(5, this.f4804j);
        }
        if ((this.f & 32) == 32) {
            oVar.m(6, this.l);
        }
        w02.A0(200, oVar);
        oVar.r(this.e);
    }

    public final C1252b getDefaultInstanceForType() {
        return f4800o;
    }

    public final boolean isInitialized() {
        byte b = this.m;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        int i2 = this.f;
        if ((i2 & 2) != 2) {
            this.m = 0;
            return false;
        } else if ((i2 & 4) == 4 && !this.f4803i.isInitialized()) {
            this.m = 0;
            return false;
        } else if ((this.f & 16) == 16 && !this.k.isInitialized()) {
            this.m = 0;
            return false;
        } else if (!g()) {
            this.m = 0;
            return false;
        } else {
            this.m = 1;
            return true;
        }
    }

    public Z() {
        this.m = -1;
        this.n = -1;
        this.e = C1255e.d;
    }

    public Z(C1256f fVar, C1258h hVar) {
        this.m = -1;
        this.n = -1;
        boolean z = false;
        this.g = 0;
        this.f4802h = 0;
        Q q = Q.w;
        this.f4803i = q;
        this.f4804j = 0;
        this.k = q;
        this.l = 0;
        C1254d dVar = new C1254d();
        o j2 = o.j(dVar, 1);
        while (!z) {
            try {
                int n3 = fVar.n();
                if (n3 != 0) {
                    if (n3 == 8) {
                        this.f |= 1;
                        this.g = fVar.k();
                    } else if (n3 != 16) {
                        P p6 = null;
                        if (n3 == 26) {
                            if ((this.f & 4) == 4) {
                                Q q10 = this.f4803i;
                                q10.getClass();
                                p6 = Q.p(q10);
                            }
                            Q q11 = (Q) fVar.g(Q.f4770x, hVar);
                            this.f4803i = q11;
                            if (p6 != null) {
                                p6.g(q11);
                                this.f4803i = p6.e();
                            }
                            this.f |= 4;
                        } else if (n3 == 34) {
                            if ((this.f & 16) == 16) {
                                Q q12 = this.k;
                                q12.getClass();
                                p6 = Q.p(q12);
                            }
                            Q q13 = (Q) fVar.g(Q.f4770x, hVar);
                            this.k = q13;
                            if (p6 != null) {
                                p6.g(q13);
                                this.k = p6.e();
                            }
                            this.f |= 16;
                        } else if (n3 == 40) {
                            this.f |= 8;
                            this.f4804j = fVar.k();
                        } else if (n3 == 48) {
                            this.f |= 32;
                            this.l = fVar.k();
                        } else if (m(fVar, j2, hVar, n3)) {
                        }
                    } else {
                        this.f |= 2;
                        this.f4802h = fVar.k();
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
                    this.e = dVar.f();
                    throw th2;
                }
                this.e = dVar.f();
                l();
                throw th;
            }
        }
        try {
            j2.i();
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            this.e = dVar.f();
            throw th3;
        }
        this.e = dVar.f();
        l();
    }
}
