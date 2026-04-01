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

/* renamed from: lf.k  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1158k extends C1263m {
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public int f4856h;

    /* renamed from: i  reason: collision with root package name */
    public List f4857i;

    /* renamed from: j  reason: collision with root package name */
    public List f4858j;

    /* JADX WARNING: type inference failed for: r0v0, types: [lf.k, rf.m] */
    public static C1158k f() {
        ? mVar = new C1263m();
        mVar.f4856h = 6;
        List list = Collections.EMPTY_LIST;
        mVar.f4857i = list;
        mVar.f4858j = list;
        return mVar;
    }

    public final C1252b a() {
        C1159l e = e();
        if (e.isInitialized()) {
            return e;
        }
        throw new C0730a();
    }

    public final C1262l b(C1256f fVar, C1258h hVar) {
        C1159l lVar;
        C1159l lVar2 = null;
        try {
            C1159l.m.getClass();
            g(new C1159l(fVar, hVar));
            return this;
        } catch (u e) {
            lVar = (C1159l) e.d;
            throw e;
        } catch (Throwable th) {
            th = th;
            lVar2 = lVar;
        }
        if (lVar2 != null) {
            g(lVar2);
        }
        throw th;
    }

    public final /* bridge */ /* synthetic */ C1262l c(C1267q qVar) {
        g((C1159l) qVar);
        return this;
    }

    public final Object clone() {
        C1158k f = f();
        f.g(e());
        return f;
    }

    public final C1159l e() {
        C1159l lVar = new C1159l(this);
        int i2 = this.g;
        int i7 = 1;
        if ((i2 & 1) != 1) {
            i7 = 0;
        }
        lVar.g = this.f4856h;
        if ((i2 & 2) == 2) {
            this.f4857i = Collections.unmodifiableList(this.f4857i);
            this.g &= -3;
        }
        lVar.f4859h = this.f4857i;
        if ((this.g & 4) == 4) {
            this.f4858j = Collections.unmodifiableList(this.f4858j);
            this.g &= -5;
        }
        lVar.f4860i = this.f4858j;
        lVar.f = i7;
        return lVar;
    }

    public final void g(C1159l lVar) {
        if (lVar != C1159l.l) {
            if ((lVar.f & 1) == 1) {
                int i2 = lVar.g;
                this.g = 1 | this.g;
                this.f4856h = i2;
            }
            if (!lVar.f4859h.isEmpty()) {
                if (this.f4857i.isEmpty()) {
                    this.f4857i = lVar.f4859h;
                    this.g &= -3;
                } else {
                    if ((this.g & 2) != 2) {
                        this.f4857i = new ArrayList(this.f4857i);
                        this.g |= 2;
                    }
                    this.f4857i.addAll(lVar.f4859h);
                }
            }
            if (!lVar.f4860i.isEmpty()) {
                if (this.f4858j.isEmpty()) {
                    this.f4858j = lVar.f4860i;
                    this.g &= -5;
                } else {
                    if ((this.g & 4) != 4) {
                        this.f4858j = new ArrayList(this.f4858j);
                        this.g |= 4;
                    }
                    this.f4858j.addAll(lVar.f4860i);
                }
            }
            d(lVar);
            this.d = this.d.p(lVar.e);
        }
    }
}
