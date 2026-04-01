package of;

import rf.C1252b;
import rf.C1256f;
import rf.C1258h;
import rf.C1262l;
import rf.C1267q;
import rf.u;
import rf.y;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d extends C1262l implements y {
    public int e;
    public b f;
    public c g;

    /* renamed from: h  reason: collision with root package name */
    public c f4991h;

    /* renamed from: i  reason: collision with root package name */
    public c f4992i;

    /* renamed from: j  reason: collision with root package name */
    public c f4993j;

    /* JADX WARNING: type inference failed for: r0v0, types: [rf.l, of.d] */
    public static d e() {
        ? lVar = new C1262l();
        lVar.f = b.f4985j;
        c cVar = c.f4988j;
        lVar.g = cVar;
        lVar.f4991h = cVar;
        lVar.f4992i = cVar;
        lVar.f4993j = cVar;
        return lVar;
    }

    public final C1252b a() {
        e d = d();
        d.isInitialized();
        return d;
    }

    public final C1262l b(C1256f fVar, C1258h hVar) {
        e eVar;
        e eVar2 = null;
        try {
            e.n.getClass();
            f(new e(fVar, hVar));
            return this;
        } catch (u e7) {
            eVar = (e) e7.d;
            throw e7;
        } catch (Throwable th) {
            th = th;
            eVar2 = eVar;
        }
        if (eVar2 != null) {
            f(eVar2);
        }
        throw th;
    }

    public final /* bridge */ /* synthetic */ C1262l c(C1267q qVar) {
        f((e) qVar);
        return this;
    }

    public final Object clone() {
        d e7 = e();
        e7.f(d());
        return e7;
    }

    public final e d() {
        e eVar = new e(this);
        int i2 = this.e;
        int i7 = 1;
        if ((i2 & 1) != 1) {
            i7 = 0;
        }
        eVar.f = this.f;
        if ((i2 & 2) == 2) {
            i7 |= 2;
        }
        eVar.g = this.g;
        if ((i2 & 4) == 4) {
            i7 |= 4;
        }
        eVar.f4994h = this.f4991h;
        if ((i2 & 8) == 8) {
            i7 |= 8;
        }
        eVar.f4995i = this.f4992i;
        if ((i2 & 16) == 16) {
            i7 |= 16;
        }
        eVar.f4996j = this.f4993j;
        eVar.e = i7;
        return eVar;
    }

    public final void f(e eVar) {
        c cVar;
        c cVar2;
        c cVar3;
        c cVar4;
        b bVar;
        if (eVar != e.m) {
            if ((eVar.e & 1) == 1) {
                b bVar2 = eVar.f;
                if ((this.e & 1) != 1 || (bVar = this.f) == b.f4985j) {
                    this.f = bVar2;
                } else {
                    a aVar = new a(0);
                    aVar.f(bVar);
                    aVar.f(bVar2);
                    this.f = aVar.d();
                }
                this.e |= 1;
            }
            if ((eVar.e & 2) == 2) {
                c cVar5 = eVar.g;
                if ((this.e & 2) != 2 || (cVar4 = this.g) == c.f4988j) {
                    this.g = cVar5;
                } else {
                    a g3 = c.g(cVar4);
                    g3.g(cVar5);
                    this.g = g3.e();
                }
                this.e |= 2;
            }
            if ((eVar.e & 4) == 4) {
                c cVar6 = eVar.f4994h;
                if ((this.e & 4) != 4 || (cVar3 = this.f4991h) == c.f4988j) {
                    this.f4991h = cVar6;
                } else {
                    a g10 = c.g(cVar3);
                    g10.g(cVar6);
                    this.f4991h = g10.e();
                }
                this.e |= 4;
            }
            if ((eVar.e & 8) == 8) {
                c cVar7 = eVar.f4995i;
                if ((this.e & 8) != 8 || (cVar2 = this.f4992i) == c.f4988j) {
                    this.f4992i = cVar7;
                } else {
                    a g11 = c.g(cVar2);
                    g11.g(cVar7);
                    this.f4992i = g11.e();
                }
                this.e |= 8;
            }
            if ((eVar.e & 16) == 16) {
                c cVar8 = eVar.f4996j;
                if ((this.e & 16) != 16 || (cVar = this.f4993j) == c.f4988j) {
                    this.f4993j = cVar8;
                } else {
                    a g12 = c.g(cVar);
                    g12.g(cVar8);
                    this.f4993j = g12.e();
                }
                this.e |= 16;
            }
            this.d = this.d.p(eVar.d);
        }
    }
}
