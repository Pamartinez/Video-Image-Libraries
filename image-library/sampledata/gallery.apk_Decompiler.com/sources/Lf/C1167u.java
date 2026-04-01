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

/* renamed from: lf.u  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1167u extends C1262l implements y {
    public int e;
    public int f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public C1168v f4873h;

    /* renamed from: i  reason: collision with root package name */
    public Q f4874i;

    /* renamed from: j  reason: collision with root package name */
    public int f4875j;
    public List k;
    public List l;

    /* JADX WARNING: type inference failed for: r0v0, types: [lf.u, rf.l] */
    public static C1167u e() {
        ? lVar = new C1262l();
        lVar.f4873h = C1168v.TRUE;
        lVar.f4874i = Q.w;
        List list = Collections.EMPTY_LIST;
        lVar.k = list;
        lVar.l = list;
        return lVar;
    }

    public final C1252b a() {
        C1169w d = d();
        if (d.isInitialized()) {
            return d;
        }
        throw new C0730a();
    }

    public final C1262l b(C1256f fVar, C1258h hVar) {
        C1169w wVar;
        C1169w wVar2 = null;
        try {
            C1169w.f4877p.getClass();
            f(new C1169w(fVar, hVar));
            return this;
        } catch (u e7) {
            wVar = (C1169w) e7.d;
            throw e7;
        } catch (Throwable th) {
            th = th;
            wVar2 = wVar;
        }
        if (wVar2 != null) {
            f(wVar2);
        }
        throw th;
    }

    public final /* bridge */ /* synthetic */ C1262l c(C1267q qVar) {
        f((C1169w) qVar);
        return this;
    }

    public final Object clone() {
        C1167u e7 = e();
        e7.f(d());
        return e7;
    }

    public final C1169w d() {
        C1169w wVar = new C1169w(this);
        int i2 = this.e;
        int i7 = 1;
        if ((i2 & 1) != 1) {
            i7 = 0;
        }
        wVar.f = this.f;
        if ((i2 & 2) == 2) {
            i7 |= 2;
        }
        wVar.g = this.g;
        if ((i2 & 4) == 4) {
            i7 |= 4;
        }
        wVar.f4878h = this.f4873h;
        if ((i2 & 8) == 8) {
            i7 |= 8;
        }
        wVar.f4879i = this.f4874i;
        if ((i2 & 16) == 16) {
            i7 |= 16;
        }
        wVar.f4880j = this.f4875j;
        if ((i2 & 32) == 32) {
            this.k = Collections.unmodifiableList(this.k);
            this.e &= -33;
        }
        wVar.k = this.k;
        if ((this.e & 64) == 64) {
            this.l = Collections.unmodifiableList(this.l);
            this.e &= -65;
        }
        wVar.l = this.l;
        wVar.e = i7;
        return wVar;
    }

    public final void f(C1169w wVar) {
        Q q;
        if (wVar != C1169w.f4876o) {
            int i2 = wVar.e;
            if ((i2 & 1) == 1) {
                int i7 = wVar.f;
                this.e = 1 | this.e;
                this.f = i7;
            }
            if ((i2 & 2) == 2) {
                int i8 = wVar.g;
                this.e = 2 | this.e;
                this.g = i8;
            }
            if ((i2 & 4) == 4) {
                C1168v vVar = wVar.f4878h;
                vVar.getClass();
                this.e = 4 | this.e;
                this.f4873h = vVar;
            }
            if ((wVar.e & 8) == 8) {
                Q q10 = wVar.f4879i;
                if ((this.e & 8) != 8 || (q = this.f4874i) == Q.w) {
                    this.f4874i = q10;
                } else {
                    P p6 = Q.p(q);
                    p6.g(q10);
                    this.f4874i = p6.e();
                }
                this.e |= 8;
            }
            if ((wVar.e & 16) == 16) {
                int i10 = wVar.f4880j;
                this.e = 16 | this.e;
                this.f4875j = i10;
            }
            if (!wVar.k.isEmpty()) {
                if (this.k.isEmpty()) {
                    this.k = wVar.k;
                    this.e &= -33;
                } else {
                    if ((this.e & 32) != 32) {
                        this.k = new ArrayList(this.k);
                        this.e |= 32;
                    }
                    this.k.addAll(wVar.k);
                }
            }
            if (!wVar.l.isEmpty()) {
                if (this.l.isEmpty()) {
                    this.l = wVar.l;
                    this.e &= -65;
                } else {
                    if ((this.e & 64) != 64) {
                        this.l = new ArrayList(this.l);
                        this.e |= 64;
                    }
                    this.l.addAll(wVar.l);
                }
            }
            this.d = this.d.p(wVar.d);
        }
    }
}
