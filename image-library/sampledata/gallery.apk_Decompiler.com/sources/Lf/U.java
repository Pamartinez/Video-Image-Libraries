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
public final class U extends C1263m {
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public int f4786h;

    /* renamed from: i  reason: collision with root package name */
    public int f4787i;

    /* renamed from: j  reason: collision with root package name */
    public boolean f4788j;
    public V k;
    public List l;
    public List m;

    /* JADX WARNING: type inference failed for: r0v0, types: [lf.U, rf.m] */
    public static U f() {
        ? mVar = new C1263m();
        mVar.k = V.INV;
        List list = Collections.EMPTY_LIST;
        mVar.l = list;
        mVar.m = list;
        return mVar;
    }

    public final C1252b a() {
        W e = e();
        if (e.isInitialized()) {
            return e;
        }
        throw new C0730a();
    }

    public final C1262l b(C1256f fVar, C1258h hVar) {
        W w;
        W w6 = null;
        try {
            W.q.getClass();
            g(new W(fVar, hVar));
            return this;
        } catch (u e) {
            w = (W) e.d;
            throw e;
        } catch (Throwable th) {
            th = th;
            w6 = w;
        }
        if (w6 != null) {
            g(w6);
        }
        throw th;
    }

    public final /* bridge */ /* synthetic */ C1262l c(C1267q qVar) {
        g((W) qVar);
        return this;
    }

    public final Object clone() {
        U f = f();
        f.g(e());
        return f;
    }

    public final W e() {
        W w = new W(this);
        int i2 = this.g;
        int i7 = 1;
        if ((i2 & 1) != 1) {
            i7 = 0;
        }
        w.g = this.f4786h;
        if ((i2 & 2) == 2) {
            i7 |= 2;
        }
        w.f4790h = this.f4787i;
        if ((i2 & 4) == 4) {
            i7 |= 4;
        }
        w.f4791i = this.f4788j;
        if ((i2 & 8) == 8) {
            i7 |= 8;
        }
        w.f4792j = this.k;
        if ((i2 & 16) == 16) {
            this.l = Collections.unmodifiableList(this.l);
            this.g &= -17;
        }
        w.k = this.l;
        if ((this.g & 32) == 32) {
            this.m = Collections.unmodifiableList(this.m);
            this.g &= -33;
        }
        w.l = this.m;
        w.f = i7;
        return w;
    }

    public final void g(W w) {
        if (w != W.f4789p) {
            int i2 = w.f;
            if ((i2 & 1) == 1) {
                int i7 = w.g;
                this.g = 1 | this.g;
                this.f4786h = i7;
            }
            if ((i2 & 2) == 2) {
                int i8 = w.f4790h;
                this.g = 2 | this.g;
                this.f4787i = i8;
            }
            if ((i2 & 4) == 4) {
                boolean z = w.f4791i;
                this.g = 4 | this.g;
                this.f4788j = z;
            }
            if ((i2 & 8) == 8) {
                V v = w.f4792j;
                v.getClass();
                this.g = 8 | this.g;
                this.k = v;
            }
            if (!w.k.isEmpty()) {
                if (this.l.isEmpty()) {
                    this.l = w.k;
                    this.g &= -17;
                } else {
                    if ((this.g & 16) != 16) {
                        this.l = new ArrayList(this.l);
                        this.g |= 16;
                    }
                    this.l.addAll(w.k);
                }
            }
            if (!w.l.isEmpty()) {
                if (this.m.isEmpty()) {
                    this.m = w.l;
                    this.g &= -33;
                } else {
                    if ((this.g & 32) != 32) {
                        this.m = new ArrayList(this.m);
                        this.g |= 32;
                    }
                    this.m.addAll(w.l);
                }
            }
            d(w);
            this.d = this.d.p(w.e);
        }
    }
}
