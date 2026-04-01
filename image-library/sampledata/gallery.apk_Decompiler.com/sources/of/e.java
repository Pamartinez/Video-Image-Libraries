package of;

import B2.o;
import java.io.IOException;
import lf.C1148a;
import rf.C1254d;
import rf.C1255e;
import rf.C1256f;
import rf.C1258h;
import rf.C1262l;
import rf.C1267q;
import rf.u;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class e extends C1267q {
    public static final e m;
    public static final C1148a n = new C1148a(26);
    public final C1255e d;
    public int e;
    public b f;
    public c g;

    /* renamed from: h  reason: collision with root package name */
    public c f4994h;

    /* renamed from: i  reason: collision with root package name */
    public c f4995i;

    /* renamed from: j  reason: collision with root package name */
    public c f4996j;
    public byte k;
    public int l;

    static {
        e eVar = new e();
        m = eVar;
        eVar.f = b.f4985j;
        c cVar = c.f4988j;
        eVar.g = cVar;
        eVar.f4994h = cVar;
        eVar.f4995i = cVar;
        eVar.f4996j = cVar;
    }

    public e() {
        this.k = -1;
        this.l = -1;
        this.d = C1255e.d;
    }

    public final int a() {
        int i2;
        int i7 = this.l;
        if (i7 != -1) {
            return i7;
        }
        if ((this.e & 1) == 1) {
            i2 = o.d(1, this.f);
        } else {
            i2 = 0;
        }
        if ((this.e & 2) == 2) {
            i2 += o.d(2, this.g);
        }
        if ((this.e & 4) == 4) {
            i2 += o.d(3, this.f4994h);
        }
        if ((this.e & 8) == 8) {
            i2 += o.d(4, this.f4995i);
        }
        if ((this.e & 16) == 16) {
            i2 += o.d(5, this.f4996j);
        }
        int size = this.d.size() + i2;
        this.l = size;
        return size;
    }

    public final C1262l b() {
        return d.e();
    }

    public final C1262l c() {
        d e7 = d.e();
        e7.f(this);
        return e7;
    }

    public final void d(o oVar) {
        a();
        if ((this.e & 1) == 1) {
            oVar.o(1, this.f);
        }
        if ((this.e & 2) == 2) {
            oVar.o(2, this.g);
        }
        if ((this.e & 4) == 4) {
            oVar.o(3, this.f4994h);
        }
        if ((this.e & 8) == 8) {
            oVar.o(4, this.f4995i);
        }
        if ((this.e & 16) == 16) {
            oVar.o(5, this.f4996j);
        }
        oVar.r(this.d);
    }

    public final boolean isInitialized() {
        if (this.k == 1) {
            return true;
        }
        this.k = 1;
        return true;
    }

    public e(d dVar) {
        this.k = -1;
        this.l = -1;
        this.d = dVar.d;
    }

    public e(C1256f fVar, C1258h hVar) {
        this.k = -1;
        this.l = -1;
        this.f = b.f4985j;
        c cVar = c.f4988j;
        this.g = cVar;
        this.f4994h = cVar;
        this.f4995i = cVar;
        this.f4996j = cVar;
        C1254d dVar = new C1254d();
        o j2 = o.j(dVar, 1);
        boolean z = false;
        while (!z) {
            try {
                int n3 = fVar.n();
                if (n3 != 0) {
                    a aVar = null;
                    if (n3 == 10) {
                        if ((this.e & 1) == 1) {
                            b bVar = this.f;
                            bVar.getClass();
                            aVar = new a(0);
                            aVar.f(bVar);
                        }
                        b bVar2 = (b) fVar.g(b.k, hVar);
                        this.f = bVar2;
                        if (aVar != null) {
                            aVar.f(bVar2);
                            this.f = aVar.d();
                        }
                        this.e |= 1;
                    } else if (n3 == 18) {
                        if ((this.e & 2) == 2) {
                            c cVar2 = this.g;
                            cVar2.getClass();
                            aVar = c.g(cVar2);
                        }
                        c cVar3 = (c) fVar.g(c.k, hVar);
                        this.g = cVar3;
                        if (aVar != null) {
                            aVar.g(cVar3);
                            this.g = aVar.e();
                        }
                        this.e |= 2;
                    } else if (n3 == 26) {
                        if ((this.e & 4) == 4) {
                            c cVar4 = this.f4994h;
                            cVar4.getClass();
                            aVar = c.g(cVar4);
                        }
                        c cVar5 = (c) fVar.g(c.k, hVar);
                        this.f4994h = cVar5;
                        if (aVar != null) {
                            aVar.g(cVar5);
                            this.f4994h = aVar.e();
                        }
                        this.e |= 4;
                    } else if (n3 == 34) {
                        if ((this.e & 8) == 8) {
                            c cVar6 = this.f4995i;
                            cVar6.getClass();
                            aVar = c.g(cVar6);
                        }
                        c cVar7 = (c) fVar.g(c.k, hVar);
                        this.f4995i = cVar7;
                        if (aVar != null) {
                            aVar.g(cVar7);
                            this.f4995i = aVar.e();
                        }
                        this.e |= 8;
                    } else if (n3 == 42) {
                        if ((this.e & 16) == 16) {
                            c cVar8 = this.f4996j;
                            cVar8.getClass();
                            aVar = c.g(cVar8);
                        }
                        c cVar9 = (c) fVar.g(c.k, hVar);
                        this.f4996j = cVar9;
                        if (aVar != null) {
                            aVar.g(cVar9);
                            this.f4996j = aVar.e();
                        }
                        this.e |= 16;
                    } else if (fVar.q(n3, j2)) {
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
