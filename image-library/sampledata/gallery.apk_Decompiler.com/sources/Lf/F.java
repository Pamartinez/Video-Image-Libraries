package lf;

import Dd.C0730a;
import com.adobe.internal.xmp.options.SerializeOptions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import rf.C1252b;
import rf.C1256f;
import rf.C1258h;
import rf.C1262l;
import rf.C1263m;
import rf.C1267q;
import rf.u;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class F extends C1263m {
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public int f4742h;

    /* renamed from: i  reason: collision with root package name */
    public int f4743i;

    /* renamed from: j  reason: collision with root package name */
    public int f4744j;
    public Q k;
    public int l;
    public List m;
    public Q n;

    /* renamed from: o  reason: collision with root package name */
    public int f4745o;

    /* renamed from: p  reason: collision with root package name */
    public List f4746p;
    public List q;
    public Z r;
    public int s;
    public int t;
    public List u;

    /* JADX WARNING: type inference failed for: r0v0, types: [lf.F, rf.m] */
    public static F f() {
        ? mVar = new C1263m();
        mVar.f4742h = 518;
        mVar.f4743i = 2054;
        Q q10 = Q.w;
        mVar.k = q10;
        List list = Collections.EMPTY_LIST;
        mVar.m = list;
        mVar.n = q10;
        mVar.f4746p = list;
        mVar.q = list;
        mVar.r = Z.f4800o;
        mVar.u = list;
        return mVar;
    }

    public final C1252b a() {
        G e = e();
        if (e.isInitialized()) {
            return e;
        }
        throw new C0730a();
    }

    public final C1262l b(C1256f fVar, C1258h hVar) {
        G g3;
        G g10 = null;
        try {
            G.y.getClass();
            g(new G(fVar, hVar));
            return this;
        } catch (u e) {
            g3 = (G) e.d;
            throw e;
        } catch (Throwable th) {
            th = th;
            g10 = g3;
        }
        if (g10 != null) {
            g(g10);
        }
        throw th;
    }

    public final /* bridge */ /* synthetic */ C1262l c(C1267q qVar) {
        g((G) qVar);
        return this;
    }

    public final Object clone() {
        F f = f();
        f.g(e());
        return f;
    }

    public final G e() {
        G g3 = new G(this);
        int i2 = this.g;
        int i7 = 1;
        if ((i2 & 1) != 1) {
            i7 = 0;
        }
        g3.g = this.f4742h;
        if ((i2 & 2) == 2) {
            i7 |= 2;
        }
        g3.f4748h = this.f4743i;
        if ((i2 & 4) == 4) {
            i7 |= 4;
        }
        g3.f4749i = this.f4744j;
        if ((i2 & 8) == 8) {
            i7 |= 8;
        }
        g3.f4750j = this.k;
        if ((i2 & 16) == 16) {
            i7 |= 16;
        }
        g3.k = this.l;
        if ((i2 & 32) == 32) {
            this.m = Collections.unmodifiableList(this.m);
            this.g &= -33;
        }
        g3.l = this.m;
        if ((i2 & 64) == 64) {
            i7 |= 32;
        }
        g3.m = this.n;
        if ((i2 & 128) == 128) {
            i7 |= 64;
        }
        g3.n = this.f4745o;
        if ((this.g & 256) == 256) {
            this.f4746p = Collections.unmodifiableList(this.f4746p);
            this.g &= -257;
        }
        g3.f4751o = this.f4746p;
        if ((this.g & 512) == 512) {
            this.q = Collections.unmodifiableList(this.q);
            this.g &= -513;
        }
        g3.f4752p = this.q;
        if ((i2 & 1024) == 1024) {
            i7 |= 128;
        }
        g3.r = this.r;
        if ((i2 & 2048) == 2048) {
            i7 |= 256;
        }
        g3.s = this.s;
        if ((i2 & 4096) == 4096) {
            i7 |= 512;
        }
        g3.t = this.t;
        if ((this.g & SerializeOptions.SORT) == 8192) {
            this.u = Collections.unmodifiableList(this.u);
            this.g &= -8193;
        }
        g3.u = this.u;
        g3.f = i7;
        return g3;
    }

    /* JADX WARNING: type inference failed for: r5v1, types: [rf.m, lf.Y] */
    public final void g(G g3) {
        Z z;
        Q q10;
        Q q11;
        if (g3 != G.f4747x) {
            int i2 = g3.f;
            if ((i2 & 1) == 1) {
                int i7 = g3.g;
                this.g = 1 | this.g;
                this.f4742h = i7;
            }
            if ((i2 & 2) == 2) {
                int i8 = g3.f4748h;
                this.g = 2 | this.g;
                this.f4743i = i8;
            }
            if ((i2 & 4) == 4) {
                int i10 = g3.f4749i;
                this.g = 4 | this.g;
                this.f4744j = i10;
            }
            if ((i2 & 8) == 8) {
                Q q12 = g3.f4750j;
                if ((this.g & 8) != 8 || (q11 = this.k) == Q.w) {
                    this.k = q12;
                } else {
                    P p6 = Q.p(q11);
                    p6.g(q12);
                    this.k = p6.e();
                }
                this.g |= 8;
            }
            if ((g3.f & 16) == 16) {
                int i11 = g3.k;
                this.g = 16 | this.g;
                this.l = i11;
            }
            if (!g3.l.isEmpty()) {
                if (this.m.isEmpty()) {
                    this.m = g3.l;
                    this.g &= -33;
                } else {
                    if ((this.g & 32) != 32) {
                        this.m = new ArrayList(this.m);
                        this.g |= 32;
                    }
                    this.m.addAll(g3.l);
                }
            }
            if ((g3.f & 32) == 32) {
                Q q13 = g3.m;
                if ((this.g & 64) != 64 || (q10 = this.n) == Q.w) {
                    this.n = q13;
                } else {
                    P p8 = Q.p(q10);
                    p8.g(q13);
                    this.n = p8.e();
                }
                this.g |= 64;
            }
            if ((g3.f & 64) == 64) {
                int i12 = g3.n;
                this.g |= 128;
                this.f4745o = i12;
            }
            if (!g3.f4751o.isEmpty()) {
                if (this.f4746p.isEmpty()) {
                    this.f4746p = g3.f4751o;
                    this.g &= -257;
                } else {
                    if ((this.g & 256) != 256) {
                        this.f4746p = new ArrayList(this.f4746p);
                        this.g |= 256;
                    }
                    this.f4746p.addAll(g3.f4751o);
                }
            }
            if (!g3.f4752p.isEmpty()) {
                if (this.q.isEmpty()) {
                    this.q = g3.f4752p;
                    this.g &= -513;
                } else {
                    if ((this.g & 512) != 512) {
                        this.q = new ArrayList(this.q);
                        this.g |= 512;
                    }
                    this.q.addAll(g3.f4752p);
                }
            }
            if ((g3.f & 128) == 128) {
                Z z3 = g3.r;
                if ((this.g & 1024) != 1024 || (z = this.r) == Z.f4800o) {
                    this.r = z3;
                } else {
                    ? mVar = new C1263m();
                    Q q14 = Q.w;
                    mVar.f4799j = q14;
                    mVar.l = q14;
                    mVar.f(z);
                    mVar.f(z3);
                    this.r = mVar.e();
                }
                this.g |= 1024;
            }
            int i13 = g3.f;
            if ((i13 & 256) == 256) {
                int i14 = g3.s;
                this.g |= 2048;
                this.s = i14;
            }
            if ((i13 & 512) == 512) {
                int i15 = g3.t;
                this.g |= 4096;
                this.t = i15;
            }
            if (!g3.u.isEmpty()) {
                if (this.u.isEmpty()) {
                    this.u = g3.u;
                    this.g &= -8193;
                } else {
                    if ((this.g & SerializeOptions.SORT) != 8192) {
                        this.u = new ArrayList(this.u);
                        this.g |= SerializeOptions.SORT;
                    }
                    this.u.addAll(g3.u);
                }
            }
            d(g3);
            this.d = this.d.p(g3.e);
        }
    }
}
