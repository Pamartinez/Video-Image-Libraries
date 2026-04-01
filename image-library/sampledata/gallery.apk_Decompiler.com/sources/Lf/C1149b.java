package lf;

import Dd.C0730a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import rf.C1252b;
import rf.C1256f;
import rf.C1258h;
import rf.C1262l;
import rf.C1267q;
import rf.u;
import rf.y;

/* renamed from: lf.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1149b extends C1262l implements y {
    public int e;
    public C1150c f;
    public long g;

    /* renamed from: h  reason: collision with root package name */
    public float f4809h;

    /* renamed from: i  reason: collision with root package name */
    public double f4810i;

    /* renamed from: j  reason: collision with root package name */
    public int f4811j;
    public int k;
    public int l;
    public C1154g m;
    public List n;

    /* renamed from: o  reason: collision with root package name */
    public int f4812o;

    /* renamed from: p  reason: collision with root package name */
    public int f4813p;

    /* JADX WARNING: type inference failed for: r0v0, types: [rf.l, lf.b] */
    public static C1149b e() {
        ? lVar = new C1262l();
        lVar.f = C1150c.BYTE;
        lVar.m = C1154g.f4829j;
        lVar.n = Collections.EMPTY_LIST;
        return lVar;
    }

    public final C1252b a() {
        C1151d d = d();
        if (d.isInitialized()) {
            return d;
        }
        throw new C0730a();
    }

    public final C1262l b(C1256f fVar, C1258h hVar) {
        C1151d dVar;
        C1151d dVar2 = null;
        try {
            C1151d.t.getClass();
            f(new C1151d(fVar, hVar));
            return this;
        } catch (u e7) {
            dVar = (C1151d) e7.d;
            throw e7;
        } catch (Throwable th) {
            th = th;
            dVar2 = dVar;
        }
        if (dVar2 != null) {
            f(dVar2);
        }
        throw th;
    }

    public final /* bridge */ /* synthetic */ C1262l c(C1267q qVar) {
        f((C1151d) qVar);
        return this;
    }

    public final Object clone() {
        C1149b e7 = e();
        e7.f(d());
        return e7;
    }

    public final C1151d d() {
        C1151d dVar = new C1151d(this);
        int i2 = this.e;
        int i7 = 1;
        if ((i2 & 1) != 1) {
            i7 = 0;
        }
        dVar.f = this.f;
        if ((i2 & 2) == 2) {
            i7 |= 2;
        }
        dVar.g = this.g;
        if ((i2 & 4) == 4) {
            i7 |= 4;
        }
        dVar.f4814h = this.f4809h;
        if ((i2 & 8) == 8) {
            i7 |= 8;
        }
        dVar.f4815i = this.f4810i;
        if ((i2 & 16) == 16) {
            i7 |= 16;
        }
        dVar.f4816j = this.f4811j;
        if ((i2 & 32) == 32) {
            i7 |= 32;
        }
        dVar.k = this.k;
        if ((i2 & 64) == 64) {
            i7 |= 64;
        }
        dVar.l = this.l;
        if ((i2 & 128) == 128) {
            i7 |= 128;
        }
        dVar.m = this.m;
        if ((i2 & 256) == 256) {
            this.n = Collections.unmodifiableList(this.n);
            this.e &= -257;
        }
        dVar.n = this.n;
        if ((i2 & 512) == 512) {
            i7 |= 256;
        }
        dVar.f4817o = this.f4812o;
        if ((i2 & 1024) == 1024) {
            i7 |= 512;
        }
        dVar.f4818p = this.f4813p;
        dVar.e = i7;
        return dVar;
    }

    public final void f(C1151d dVar) {
        C1154g gVar;
        if (dVar != C1151d.s) {
            if ((dVar.e & 1) == 1) {
                C1150c cVar = dVar.f;
                cVar.getClass();
                this.e = 1 | this.e;
                this.f = cVar;
            }
            int i2 = dVar.e;
            if ((i2 & 2) == 2) {
                long j2 = dVar.g;
                this.e |= 2;
                this.g = j2;
            }
            if ((i2 & 4) == 4) {
                float f5 = dVar.f4814h;
                this.e = 4 | this.e;
                this.f4809h = f5;
            }
            if ((i2 & 8) == 8) {
                double d = dVar.f4815i;
                this.e |= 8;
                this.f4810i = d;
            }
            if ((i2 & 16) == 16) {
                int i7 = dVar.f4816j;
                this.e = 16 | this.e;
                this.f4811j = i7;
            }
            if ((i2 & 32) == 32) {
                int i8 = dVar.k;
                this.e = 32 | this.e;
                this.k = i8;
            }
            if ((i2 & 64) == 64) {
                int i10 = dVar.l;
                this.e = 64 | this.e;
                this.l = i10;
            }
            if ((i2 & 128) == 128) {
                C1154g gVar2 = dVar.m;
                if ((this.e & 128) != 128 || (gVar = this.m) == C1154g.f4829j) {
                    this.m = gVar2;
                } else {
                    C1153f fVar = new C1153f(0);
                    fVar.g = Collections.EMPTY_LIST;
                    fVar.j(gVar);
                    fVar.j(gVar2);
                    this.m = fVar.e();
                }
                this.e |= 128;
            }
            if (!dVar.n.isEmpty()) {
                if (this.n.isEmpty()) {
                    this.n = dVar.n;
                    this.e &= -257;
                } else {
                    if ((this.e & 256) != 256) {
                        this.n = new ArrayList(this.n);
                        this.e |= 256;
                    }
                    this.n.addAll(dVar.n);
                }
            }
            int i11 = dVar.e;
            if ((i11 & 256) == 256) {
                int i12 = dVar.f4817o;
                this.e |= 512;
                this.f4812o = i12;
            }
            if ((i11 & 512) == 512) {
                int i13 = dVar.f4818p;
                this.e |= 1024;
                this.f4813p = i13;
            }
            this.d = this.d.p(dVar.d);
        }
    }
}
