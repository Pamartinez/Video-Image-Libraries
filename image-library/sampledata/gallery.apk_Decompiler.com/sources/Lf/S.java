package lf;

import Dd.C0730a;
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
public final class S extends C1263m {
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public int f4776h;

    /* renamed from: i  reason: collision with root package name */
    public int f4777i;

    /* renamed from: j  reason: collision with root package name */
    public List f4778j;
    public Q k;
    public int l;
    public Q m;
    public int n;

    /* renamed from: o  reason: collision with root package name */
    public List f4779o;

    /* renamed from: p  reason: collision with root package name */
    public List f4780p;

    /* JADX WARNING: type inference failed for: r0v0, types: [rf.m, lf.S] */
    public static S f() {
        ? mVar = new C1263m();
        mVar.f4776h = 6;
        List list = Collections.EMPTY_LIST;
        mVar.f4778j = list;
        Q q = Q.w;
        mVar.k = q;
        mVar.m = q;
        mVar.f4779o = list;
        mVar.f4780p = list;
        return mVar;
    }

    public final C1252b a() {
        T e = e();
        if (e.isInitialized()) {
            return e;
        }
        throw new C0730a();
    }

    public final C1262l b(C1256f fVar, C1258h hVar) {
        T t;
        T t3 = null;
        try {
            T.s.getClass();
            g(new T(fVar, hVar));
            return this;
        } catch (u e) {
            t = (T) e.d;
            throw e;
        } catch (Throwable th) {
            th = th;
            t3 = t;
        }
        if (t3 != null) {
            g(t3);
        }
        throw th;
    }

    public final /* bridge */ /* synthetic */ C1262l c(C1267q qVar) {
        g((T) qVar);
        return this;
    }

    public final Object clone() {
        S f = f();
        f.g(e());
        return f;
    }

    public final T e() {
        T t = new T(this);
        int i2 = this.g;
        int i7 = 1;
        if ((i2 & 1) != 1) {
            i7 = 0;
        }
        t.g = this.f4776h;
        if ((i2 & 2) == 2) {
            i7 |= 2;
        }
        t.f4781h = this.f4777i;
        if ((i2 & 4) == 4) {
            this.f4778j = Collections.unmodifiableList(this.f4778j);
            this.g &= -5;
        }
        t.f4782i = this.f4778j;
        if ((i2 & 8) == 8) {
            i7 |= 4;
        }
        t.f4783j = this.k;
        if ((i2 & 16) == 16) {
            i7 |= 8;
        }
        t.k = this.l;
        if ((i2 & 32) == 32) {
            i7 |= 16;
        }
        t.l = this.m;
        if ((i2 & 64) == 64) {
            i7 |= 32;
        }
        t.m = this.n;
        if ((this.g & 128) == 128) {
            this.f4779o = Collections.unmodifiableList(this.f4779o);
            this.g &= -129;
        }
        t.n = this.f4779o;
        if ((this.g & 256) == 256) {
            this.f4780p = Collections.unmodifiableList(this.f4780p);
            this.g &= -257;
        }
        t.f4784o = this.f4780p;
        t.f = i7;
        return t;
    }

    public final void g(T t) {
        Q q;
        Q q10;
        if (t != T.r) {
            int i2 = t.f;
            if ((i2 & 1) == 1) {
                int i7 = t.g;
                this.g = 1 | this.g;
                this.f4776h = i7;
            }
            if ((i2 & 2) == 2) {
                int i8 = t.f4781h;
                this.g = 2 | this.g;
                this.f4777i = i8;
            }
            if (!t.f4782i.isEmpty()) {
                if (this.f4778j.isEmpty()) {
                    this.f4778j = t.f4782i;
                    this.g &= -5;
                } else {
                    if ((this.g & 4) != 4) {
                        this.f4778j = new ArrayList(this.f4778j);
                        this.g |= 4;
                    }
                    this.f4778j.addAll(t.f4782i);
                }
            }
            if ((t.f & 4) == 4) {
                Q q11 = t.f4783j;
                if ((this.g & 8) != 8 || (q10 = this.k) == Q.w) {
                    this.k = q11;
                } else {
                    P p6 = Q.p(q10);
                    p6.g(q11);
                    this.k = p6.e();
                }
                this.g |= 8;
            }
            int i10 = t.f;
            if ((i10 & 8) == 8) {
                int i11 = t.k;
                this.g |= 16;
                this.l = i11;
            }
            if ((i10 & 16) == 16) {
                Q q12 = t.l;
                if ((this.g & 32) != 32 || (q = this.m) == Q.w) {
                    this.m = q12;
                } else {
                    P p8 = Q.p(q);
                    p8.g(q12);
                    this.m = p8.e();
                }
                this.g |= 32;
            }
            if ((t.f & 32) == 32) {
                int i12 = t.m;
                this.g |= 64;
                this.n = i12;
            }
            if (!t.n.isEmpty()) {
                if (this.f4779o.isEmpty()) {
                    this.f4779o = t.n;
                    this.g &= -129;
                } else {
                    if ((this.g & 128) != 128) {
                        this.f4779o = new ArrayList(this.f4779o);
                        this.g |= 128;
                    }
                    this.f4779o.addAll(t.n);
                }
            }
            if (!t.f4784o.isEmpty()) {
                if (this.f4780p.isEmpty()) {
                    this.f4780p = t.f4784o;
                    this.g &= -257;
                } else {
                    if ((this.g & 256) != 256) {
                        this.f4780p = new ArrayList(this.f4780p);
                        this.g |= 256;
                    }
                    this.f4780p.addAll(t.f4784o);
                }
            }
            d(t);
            this.d = this.d.p(t.e);
        }
    }
}
