package of;

import rf.C1252b;
import rf.C1256f;
import rf.C1258h;
import rf.C1262l;
import rf.C1267q;
import rf.u;
import rf.y;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a extends C1262l implements y {
    public final /* synthetic */ int e;
    public int f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public int f4984h;

    public /* synthetic */ a(int i2) {
        this.e = i2;
    }

    public final C1252b a() {
        switch (this.e) {
            case 0:
                b d = d();
                d.isInitialized();
                return d;
            default:
                c e7 = e();
                e7.isInitialized();
                return e7;
        }
    }

    public final C1262l b(C1256f fVar, C1258h hVar) {
        b bVar;
        b bVar2;
        c cVar;
        c cVar2;
        switch (this.e) {
            case 0:
                bVar = null;
                try {
                    b.k.getClass();
                    f(new b(fVar));
                    return this;
                } catch (u e7) {
                    bVar2 = (b) e7.d;
                    throw e7;
                } catch (Throwable th) {
                    th = th;
                    bVar = bVar2;
                    break;
                }
            default:
                cVar = null;
                try {
                    c.k.getClass();
                    g(new c(fVar));
                    return this;
                } catch (u e8) {
                    cVar2 = (c) e8.d;
                    throw e8;
                } catch (Throwable th2) {
                    th = th2;
                    cVar = cVar2;
                    break;
                }
        }
        if (bVar != null) {
            f(bVar);
        }
        throw th;
        if (cVar != null) {
            g(cVar);
        }
        throw th;
    }

    public final /* bridge */ /* synthetic */ C1262l c(C1267q qVar) {
        switch (this.e) {
            case 0:
                f((b) qVar);
                return this;
            default:
                g((c) qVar);
                return this;
        }
    }

    public final Object clone() {
        switch (this.e) {
            case 0:
                a aVar = new a(0);
                aVar.f(d());
                return aVar;
            default:
                a aVar2 = new a(1);
                aVar2.g(e());
                return aVar2;
        }
    }

    public b d() {
        b bVar = new b(this);
        int i2 = this.f;
        int i7 = 1;
        if ((i2 & 1) != 1) {
            i7 = 0;
        }
        bVar.f = this.g;
        if ((i2 & 2) == 2) {
            i7 |= 2;
        }
        bVar.g = this.f4984h;
        bVar.e = i7;
        return bVar;
    }

    public c e() {
        c cVar = new c(this);
        int i2 = this.f;
        int i7 = 1;
        if ((i2 & 1) != 1) {
            i7 = 0;
        }
        cVar.f = this.g;
        if ((i2 & 2) == 2) {
            i7 |= 2;
        }
        cVar.g = this.f4984h;
        cVar.e = i7;
        return cVar;
    }

    public void f(b bVar) {
        if (bVar != b.f4985j) {
            int i2 = bVar.e;
            if ((i2 & 1) == 1) {
                int i7 = bVar.f;
                this.f = 1 | this.f;
                this.g = i7;
            }
            if ((i2 & 2) == 2) {
                int i8 = bVar.g;
                this.f = 2 | this.f;
                this.f4984h = i8;
            }
            this.d = this.d.p(bVar.d);
        }
    }

    public void g(c cVar) {
        if (cVar != c.f4988j) {
            int i2 = cVar.e;
            if ((i2 & 1) == 1) {
                int i7 = cVar.f;
                this.f = 1 | this.f;
                this.g = i7;
            }
            if ((i2 & 2) == 2) {
                int i8 = cVar.g;
                this.f = 2 | this.f;
                this.f4984h = i8;
            }
            this.d = this.d.p(cVar.d);
        }
    }
}
