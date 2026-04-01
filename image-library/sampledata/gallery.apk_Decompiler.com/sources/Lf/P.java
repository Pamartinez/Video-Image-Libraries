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
public final class P extends C1263m {
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public List f4765h;

    /* renamed from: i  reason: collision with root package name */
    public boolean f4766i;

    /* renamed from: j  reason: collision with root package name */
    public int f4767j;
    public Q k;
    public int l;
    public int m;
    public int n;

    /* renamed from: o  reason: collision with root package name */
    public int f4768o;

    /* renamed from: p  reason: collision with root package name */
    public int f4769p;
    public Q q;
    public int r;
    public Q s;
    public int t;
    public int u;

    /* JADX WARNING: type inference failed for: r0v0, types: [rf.m, lf.P] */
    public static P f() {
        ? mVar = new C1263m();
        mVar.f4765h = Collections.EMPTY_LIST;
        Q q10 = Q.w;
        mVar.k = q10;
        mVar.q = q10;
        mVar.s = q10;
        return mVar;
    }

    public final C1252b a() {
        Q e = e();
        if (e.isInitialized()) {
            return e;
        }
        throw new C0730a();
    }

    public final C1262l b(C1256f fVar, C1258h hVar) {
        Q q10;
        Q q11 = null;
        try {
            Q.f4770x.getClass();
            g(new Q(fVar, hVar));
            return this;
        } catch (u e) {
            q10 = (Q) e.d;
            throw e;
        } catch (Throwable th) {
            th = th;
            q11 = q10;
        }
        if (q11 != null) {
            g(q11);
        }
        throw th;
    }

    public final /* bridge */ /* synthetic */ C1262l c(C1267q qVar) {
        g((Q) qVar);
        return this;
    }

    public final Object clone() {
        P f = f();
        f.g(e());
        return f;
    }

    public final Q e() {
        Q q10 = new Q(this);
        int i2 = this.g;
        int i7 = 1;
        if ((i2 & 1) == 1) {
            this.f4765h = Collections.unmodifiableList(this.f4765h);
            this.g &= -2;
        }
        q10.g = this.f4765h;
        if ((i2 & 2) != 2) {
            i7 = 0;
        }
        q10.f4771h = this.f4766i;
        if ((i2 & 4) == 4) {
            i7 |= 2;
        }
        q10.f4772i = this.f4767j;
        if ((i2 & 8) == 8) {
            i7 |= 4;
        }
        q10.f4773j = this.k;
        if ((i2 & 16) == 16) {
            i7 |= 8;
        }
        q10.k = this.l;
        if ((i2 & 32) == 32) {
            i7 |= 16;
        }
        q10.l = this.m;
        if ((i2 & 64) == 64) {
            i7 |= 32;
        }
        q10.m = this.n;
        if ((i2 & 128) == 128) {
            i7 |= 64;
        }
        q10.n = this.f4768o;
        if ((i2 & 256) == 256) {
            i7 |= 128;
        }
        q10.f4774o = this.f4769p;
        if ((i2 & 512) == 512) {
            i7 |= 256;
        }
        q10.f4775p = this.q;
        if ((i2 & 1024) == 1024) {
            i7 |= 512;
        }
        q10.q = this.r;
        if ((i2 & 2048) == 2048) {
            i7 |= 1024;
        }
        q10.r = this.s;
        if ((i2 & 4096) == 4096) {
            i7 |= 2048;
        }
        q10.s = this.t;
        if ((i2 & SerializeOptions.SORT) == 8192) {
            i7 |= 4096;
        }
        q10.t = this.u;
        q10.f = i7;
        return q10;
    }

    public final P g(Q q10) {
        Q q11;
        Q q12;
        Q q13;
        Q q14 = Q.w;
        if (q10 == q14) {
            return this;
        }
        if (!q10.g.isEmpty()) {
            if (this.f4765h.isEmpty()) {
                this.f4765h = q10.g;
                this.g &= -2;
            } else {
                if ((this.g & 1) != 1) {
                    this.f4765h = new ArrayList(this.f4765h);
                    this.g |= 1;
                }
                this.f4765h.addAll(q10.g);
            }
        }
        int i2 = q10.f;
        if ((i2 & 1) == 1) {
            boolean z = q10.f4771h;
            this.g |= 2;
            this.f4766i = z;
        }
        if ((i2 & 2) == 2) {
            int i7 = q10.f4772i;
            this.g |= 4;
            this.f4767j = i7;
        }
        if ((i2 & 4) == 4) {
            Q q15 = q10.f4773j;
            if ((this.g & 8) != 8 || (q13 = this.k) == q14) {
                this.k = q15;
            } else {
                P p6 = Q.p(q13);
                p6.g(q15);
                this.k = p6.e();
            }
            this.g |= 8;
        }
        int i8 = q10.f;
        if ((i8 & 8) == 8) {
            int i10 = q10.k;
            this.g |= 16;
            this.l = i10;
        }
        if ((i8 & 16) == 16) {
            int i11 = q10.l;
            this.g |= 32;
            this.m = i11;
        }
        if ((i8 & 32) == 32) {
            int i12 = q10.m;
            this.g |= 64;
            this.n = i12;
        }
        if ((i8 & 64) == 64) {
            int i13 = q10.n;
            this.g |= 128;
            this.f4768o = i13;
        }
        if ((i8 & 128) == 128) {
            int i14 = q10.f4774o;
            this.g |= 256;
            this.f4769p = i14;
        }
        if ((i8 & 256) == 256) {
            Q q16 = q10.f4775p;
            if ((this.g & 512) != 512 || (q12 = this.q) == q14) {
                this.q = q16;
            } else {
                P p8 = Q.p(q12);
                p8.g(q16);
                this.q = p8.e();
            }
            this.g |= 512;
        }
        int i15 = q10.f;
        if ((i15 & 512) == 512) {
            int i16 = q10.q;
            this.g |= 1024;
            this.r = i16;
        }
        if ((i15 & 1024) == 1024) {
            Q q17 = q10.r;
            if ((this.g & 2048) != 2048 || (q11 = this.s) == q14) {
                this.s = q17;
            } else {
                P p10 = Q.p(q11);
                p10.g(q17);
                this.s = p10.e();
            }
            this.g |= 2048;
        }
        int i17 = q10.f;
        if ((i17 & 2048) == 2048) {
            int i18 = q10.s;
            this.g |= 4096;
            this.t = i18;
        }
        if ((i17 & 4096) == 4096) {
            int i19 = q10.t;
            this.g |= SerializeOptions.SORT;
            this.u = i19;
        }
        d(q10);
        this.d = this.d.p(q10.e);
        return this;
    }
}
