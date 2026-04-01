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
public final class B extends C1263m {
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public List f4729h;

    /* renamed from: i  reason: collision with root package name */
    public List f4730i;

    /* renamed from: j  reason: collision with root package name */
    public List f4731j;
    public X k;
    public e0 l;

    /* JADX WARNING: type inference failed for: r0v0, types: [rf.m, lf.B] */
    public static B f() {
        ? mVar = new C1263m();
        List list = Collections.EMPTY_LIST;
        mVar.f4729h = list;
        mVar.f4730i = list;
        mVar.f4731j = list;
        mVar.k = X.f4794j;
        mVar.l = e0.f4826h;
        return mVar;
    }

    public final C1252b a() {
        C e = e();
        if (e.isInitialized()) {
            return e;
        }
        throw new C0730a();
    }

    public final C1262l b(C1256f fVar, C1258h hVar) {
        C c5;
        C c6 = null;
        try {
            C.f4732o.getClass();
            g(new C(fVar, hVar));
            return this;
        } catch (u e) {
            c5 = (C) e.d;
            throw e;
        } catch (Throwable th) {
            th = th;
            c6 = c5;
        }
        if (c6 != null) {
            g(c6);
        }
        throw th;
    }

    public final /* bridge */ /* synthetic */ C1262l c(C1267q qVar) {
        g((C) qVar);
        return this;
    }

    public final Object clone() {
        B f = f();
        f.g(e());
        return f;
    }

    public final C e() {
        C c5 = new C(this);
        int i2 = this.g;
        int i7 = 1;
        if ((i2 & 1) == 1) {
            this.f4729h = Collections.unmodifiableList(this.f4729h);
            this.g &= -2;
        }
        c5.g = this.f4729h;
        if ((this.g & 2) == 2) {
            this.f4730i = Collections.unmodifiableList(this.f4730i);
            this.g &= -3;
        }
        c5.f4733h = this.f4730i;
        if ((this.g & 4) == 4) {
            this.f4731j = Collections.unmodifiableList(this.f4731j);
            this.g &= -5;
        }
        c5.f4734i = this.f4731j;
        if ((i2 & 8) != 8) {
            i7 = 0;
        }
        c5.f4735j = this.k;
        if ((i2 & 16) == 16) {
            i7 |= 2;
        }
        c5.k = this.l;
        c5.f = i7;
        return c5;
    }

    public final void g(C c5) {
        e0 e0Var;
        X x9;
        if (c5 != C.n) {
            if (!c5.g.isEmpty()) {
                if (this.f4729h.isEmpty()) {
                    this.f4729h = c5.g;
                    this.g &= -2;
                } else {
                    if ((this.g & 1) != 1) {
                        this.f4729h = new ArrayList(this.f4729h);
                        this.g |= 1;
                    }
                    this.f4729h.addAll(c5.g);
                }
            }
            if (!c5.f4733h.isEmpty()) {
                if (this.f4730i.isEmpty()) {
                    this.f4730i = c5.f4733h;
                    this.g &= -3;
                } else {
                    if ((this.g & 2) != 2) {
                        this.f4730i = new ArrayList(this.f4730i);
                        this.g |= 2;
                    }
                    this.f4730i.addAll(c5.f4733h);
                }
            }
            if (!c5.f4734i.isEmpty()) {
                if (this.f4731j.isEmpty()) {
                    this.f4731j = c5.f4734i;
                    this.g &= -5;
                } else {
                    if ((this.g & 4) != 4) {
                        this.f4731j = new ArrayList(this.f4731j);
                        this.g |= 4;
                    }
                    this.f4731j.addAll(c5.f4734i);
                }
            }
            if ((c5.f & 1) == 1) {
                X x10 = c5.f4735j;
                if ((this.g & 8) != 8 || (x9 = this.k) == X.f4794j) {
                    this.k = x10;
                } else {
                    C1153f g3 = X.g(x9);
                    g3.k(x10);
                    this.k = g3.f();
                }
                this.g |= 8;
            }
            if ((c5.f & 2) == 2) {
                e0 e0Var2 = c5.k;
                if ((this.g & 16) != 16 || (e0Var = this.l) == e0.f4826h) {
                    this.l = e0Var2;
                } else {
                    C1160m mVar = new C1160m(2);
                    mVar.g = Collections.EMPTY_LIST;
                    mVar.l(e0Var);
                    mVar.l(e0Var2);
                    this.l = mVar.g();
                }
                this.g |= 16;
            }
            d(c5);
            this.d = this.d.p(c5.e);
        }
    }
}
