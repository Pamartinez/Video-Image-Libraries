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

/* renamed from: lf.x  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1170x extends C1263m {
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public int f4881h;

    /* renamed from: i  reason: collision with root package name */
    public int f4882i;

    /* renamed from: j  reason: collision with root package name */
    public int f4883j;
    public Q k;
    public int l;
    public List m;
    public Q n;

    /* renamed from: o  reason: collision with root package name */
    public int f4884o;

    /* renamed from: p  reason: collision with root package name */
    public List f4885p;
    public List q;
    public List r;
    public X s;
    public List t;
    public C1161n u;

    /* JADX WARNING: type inference failed for: r0v0, types: [rf.m, lf.x] */
    public static C1170x f() {
        ? mVar = new C1263m();
        mVar.f4881h = 6;
        mVar.f4882i = 6;
        Q q10 = Q.w;
        mVar.k = q10;
        List list = Collections.EMPTY_LIST;
        mVar.m = list;
        mVar.n = q10;
        mVar.f4885p = list;
        mVar.q = list;
        mVar.r = list;
        mVar.s = X.f4794j;
        mVar.t = list;
        mVar.u = C1161n.f4862h;
        return mVar;
    }

    public final C1252b a() {
        C1171y e = e();
        if (e.isInitialized()) {
            return e;
        }
        throw new C0730a();
    }

    public final C1262l b(C1256f fVar, C1258h hVar) {
        C1171y yVar;
        C1171y yVar2 = null;
        try {
            C1171y.y.getClass();
            g(new C1171y(fVar, hVar));
            return this;
        } catch (u e) {
            yVar = (C1171y) e.d;
            throw e;
        } catch (Throwable th) {
            th = th;
            yVar2 = yVar;
        }
        if (yVar2 != null) {
            g(yVar2);
        }
        throw th;
    }

    public final /* bridge */ /* synthetic */ C1262l c(C1267q qVar) {
        g((C1171y) qVar);
        return this;
    }

    public final Object clone() {
        C1170x f = f();
        f.g(e());
        return f;
    }

    public final C1171y e() {
        C1171y yVar = new C1171y(this);
        int i2 = this.g;
        int i7 = 1;
        if ((i2 & 1) != 1) {
            i7 = 0;
        }
        yVar.g = this.f4881h;
        if ((i2 & 2) == 2) {
            i7 |= 2;
        }
        yVar.f4887h = this.f4882i;
        if ((i2 & 4) == 4) {
            i7 |= 4;
        }
        yVar.f4888i = this.f4883j;
        if ((i2 & 8) == 8) {
            i7 |= 8;
        }
        yVar.f4889j = this.k;
        if ((i2 & 16) == 16) {
            i7 |= 16;
        }
        yVar.k = this.l;
        if ((i2 & 32) == 32) {
            this.m = Collections.unmodifiableList(this.m);
            this.g &= -33;
        }
        yVar.l = this.m;
        if ((i2 & 64) == 64) {
            i7 |= 32;
        }
        yVar.m = this.n;
        if ((i2 & 128) == 128) {
            i7 |= 64;
        }
        yVar.n = this.f4884o;
        if ((this.g & 256) == 256) {
            this.f4885p = Collections.unmodifiableList(this.f4885p);
            this.g &= -257;
        }
        yVar.f4890o = this.f4885p;
        if ((this.g & 512) == 512) {
            this.q = Collections.unmodifiableList(this.q);
            this.g &= -513;
        }
        yVar.f4891p = this.q;
        if ((this.g & 1024) == 1024) {
            this.r = Collections.unmodifiableList(this.r);
            this.g &= -1025;
        }
        yVar.r = this.r;
        if ((i2 & 2048) == 2048) {
            i7 |= 128;
        }
        yVar.s = this.s;
        if ((this.g & 4096) == 4096) {
            this.t = Collections.unmodifiableList(this.t);
            this.g &= -4097;
        }
        yVar.t = this.t;
        if ((i2 & SerializeOptions.SORT) == 8192) {
            i7 |= 256;
        }
        yVar.u = this.u;
        yVar.f = i7;
        return yVar;
    }

    public final void g(C1171y yVar) {
        C1161n nVar;
        X x9;
        Q q10;
        Q q11;
        if (yVar != C1171y.f4886x) {
            int i2 = yVar.f;
            if ((i2 & 1) == 1) {
                int i7 = yVar.g;
                this.g = 1 | this.g;
                this.f4881h = i7;
            }
            if ((i2 & 2) == 2) {
                int i8 = yVar.f4887h;
                this.g = 2 | this.g;
                this.f4882i = i8;
            }
            if ((i2 & 4) == 4) {
                int i10 = yVar.f4888i;
                this.g = 4 | this.g;
                this.f4883j = i10;
            }
            if ((i2 & 8) == 8) {
                Q q12 = yVar.f4889j;
                if ((this.g & 8) != 8 || (q11 = this.k) == Q.w) {
                    this.k = q12;
                } else {
                    P p6 = Q.p(q11);
                    p6.g(q12);
                    this.k = p6.e();
                }
                this.g |= 8;
            }
            if ((yVar.f & 16) == 16) {
                int i11 = yVar.k;
                this.g = 16 | this.g;
                this.l = i11;
            }
            if (!yVar.l.isEmpty()) {
                if (this.m.isEmpty()) {
                    this.m = yVar.l;
                    this.g &= -33;
                } else {
                    if ((this.g & 32) != 32) {
                        this.m = new ArrayList(this.m);
                        this.g |= 32;
                    }
                    this.m.addAll(yVar.l);
                }
            }
            if ((yVar.f & 32) == 32) {
                Q q13 = yVar.m;
                if ((this.g & 64) != 64 || (q10 = this.n) == Q.w) {
                    this.n = q13;
                } else {
                    P p8 = Q.p(q10);
                    p8.g(q13);
                    this.n = p8.e();
                }
                this.g |= 64;
            }
            if ((yVar.f & 64) == 64) {
                int i12 = yVar.n;
                this.g |= 128;
                this.f4884o = i12;
            }
            if (!yVar.f4890o.isEmpty()) {
                if (this.f4885p.isEmpty()) {
                    this.f4885p = yVar.f4890o;
                    this.g &= -257;
                } else {
                    if ((this.g & 256) != 256) {
                        this.f4885p = new ArrayList(this.f4885p);
                        this.g |= 256;
                    }
                    this.f4885p.addAll(yVar.f4890o);
                }
            }
            if (!yVar.f4891p.isEmpty()) {
                if (this.q.isEmpty()) {
                    this.q = yVar.f4891p;
                    this.g &= -513;
                } else {
                    if ((this.g & 512) != 512) {
                        this.q = new ArrayList(this.q);
                        this.g |= 512;
                    }
                    this.q.addAll(yVar.f4891p);
                }
            }
            if (!yVar.r.isEmpty()) {
                if (this.r.isEmpty()) {
                    this.r = yVar.r;
                    this.g &= -1025;
                } else {
                    if ((this.g & 1024) != 1024) {
                        this.r = new ArrayList(this.r);
                        this.g |= 1024;
                    }
                    this.r.addAll(yVar.r);
                }
            }
            if ((yVar.f & 128) == 128) {
                X x10 = yVar.s;
                if ((this.g & 2048) != 2048 || (x9 = this.s) == X.f4794j) {
                    this.s = x10;
                } else {
                    C1153f g3 = X.g(x9);
                    g3.k(x10);
                    this.s = g3.f();
                }
                this.g |= 2048;
            }
            if (!yVar.t.isEmpty()) {
                if (this.t.isEmpty()) {
                    this.t = yVar.t;
                    this.g &= -4097;
                } else {
                    if ((this.g & 4096) != 4096) {
                        this.t = new ArrayList(this.t);
                        this.g |= 4096;
                    }
                    this.t.addAll(yVar.t);
                }
            }
            if ((yVar.f & 256) == 256) {
                C1161n nVar2 = yVar.u;
                if ((this.g & SerializeOptions.SORT) != 8192 || (nVar = this.u) == C1161n.f4862h) {
                    this.u = nVar2;
                } else {
                    C1160m mVar = new C1160m(0);
                    mVar.g = Collections.EMPTY_LIST;
                    mVar.h(nVar);
                    mVar.h(nVar2);
                    this.u = mVar.d();
                }
                this.g |= SerializeOptions.SORT;
            }
            d(yVar);
            this.d = this.d.p(yVar.e);
        }
    }
}
