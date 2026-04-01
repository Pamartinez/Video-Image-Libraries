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

/* renamed from: lf.o  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1162o extends C1262l implements y {
    public int e;
    public C1163p f;
    public List g;

    /* renamed from: h  reason: collision with root package name */
    public C1169w f4864h;

    /* renamed from: i  reason: collision with root package name */
    public C1164q f4865i;

    /* JADX WARNING: type inference failed for: r0v0, types: [rf.l, lf.o] */
    public static C1162o e() {
        ? lVar = new C1262l();
        lVar.f = C1163p.RETURNS_CONSTANT;
        lVar.g = Collections.EMPTY_LIST;
        lVar.f4864h = C1169w.f4876o;
        lVar.f4865i = C1164q.AT_MOST_ONCE;
        return lVar;
    }

    public final C1252b a() {
        r d = d();
        if (d.isInitialized()) {
            return d;
        }
        throw new C0730a();
    }

    public final C1262l b(C1256f fVar, C1258h hVar) {
        r rVar;
        r rVar2 = null;
        try {
            r.m.getClass();
            f(new r(fVar, hVar));
            return this;
        } catch (u e7) {
            rVar = (r) e7.d;
            throw e7;
        } catch (Throwable th) {
            th = th;
            rVar2 = rVar;
        }
        if (rVar2 != null) {
            f(rVar2);
        }
        throw th;
    }

    public final /* bridge */ /* synthetic */ C1262l c(C1267q qVar) {
        f((r) qVar);
        return this;
    }

    public final Object clone() {
        C1162o e7 = e();
        e7.f(d());
        return e7;
    }

    public final r d() {
        r rVar = new r(this);
        int i2 = this.e;
        int i7 = 1;
        if ((i2 & 1) != 1) {
            i7 = 0;
        }
        rVar.f = this.f;
        if ((i2 & 2) == 2) {
            this.g = Collections.unmodifiableList(this.g);
            this.e &= -3;
        }
        rVar.g = this.g;
        if ((i2 & 4) == 4) {
            i7 |= 2;
        }
        rVar.f4866h = this.f4864h;
        if ((i2 & 8) == 8) {
            i7 |= 4;
        }
        rVar.f4867i = this.f4865i;
        rVar.e = i7;
        return rVar;
    }

    public final void f(r rVar) {
        C1169w wVar;
        if (rVar != r.l) {
            if ((rVar.e & 1) == 1) {
                C1163p pVar = rVar.f;
                pVar.getClass();
                this.e = 1 | this.e;
                this.f = pVar;
            }
            if (!rVar.g.isEmpty()) {
                if (this.g.isEmpty()) {
                    this.g = rVar.g;
                    this.e &= -3;
                } else {
                    if ((this.e & 2) != 2) {
                        this.g = new ArrayList(this.g);
                        this.e |= 2;
                    }
                    this.g.addAll(rVar.g);
                }
            }
            if ((rVar.e & 2) == 2) {
                C1169w wVar2 = rVar.f4866h;
                if ((this.e & 4) != 4 || (wVar = this.f4864h) == C1169w.f4876o) {
                    this.f4864h = wVar2;
                } else {
                    C1167u e7 = C1167u.e();
                    e7.f(wVar);
                    e7.f(wVar2);
                    this.f4864h = e7.d();
                }
                this.e |= 4;
            }
            if ((rVar.e & 4) == 4) {
                C1164q qVar = rVar.f4867i;
                qVar.getClass();
                this.e |= 8;
                this.f4865i = qVar;
            }
            this.d = this.d.p(rVar.d);
        }
    }
}
