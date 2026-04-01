package lf;

import Dd.C0730a;
import rf.C1252b;
import rf.C1256f;
import rf.C1258h;
import rf.C1262l;
import rf.C1263m;
import rf.C1267q;
import rf.u;

/* renamed from: lf.s  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1165s extends C1263m {
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public int f4869h;

    public final C1252b a() {
        C1166t tVar = new C1166t(this);
        int i2 = 1;
        if ((this.g & 1) != 1) {
            i2 = 0;
        }
        tVar.g = this.f4869h;
        tVar.f = i2;
        if (tVar.isInitialized()) {
            return tVar;
        }
        throw new C0730a();
    }

    public final C1262l b(C1256f fVar, C1258h hVar) {
        C1166t tVar;
        C1166t tVar2 = null;
        try {
            C1166t.k.getClass();
            e(new C1166t(fVar, hVar));
            return this;
        } catch (u e) {
            tVar = (C1166t) e.d;
            throw e;
        } catch (Throwable th) {
            th = th;
            tVar2 = tVar;
        }
        if (tVar2 != null) {
            e(tVar2);
        }
        throw th;
    }

    public final /* bridge */ /* synthetic */ C1262l c(C1267q qVar) {
        e((C1166t) qVar);
        return this;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [rf.m, java.lang.Object, lf.s] */
    public final Object clone() {
        ? mVar = new C1263m();
        C1166t tVar = new C1166t(this);
        int i2 = 1;
        if ((this.g & 1) != 1) {
            i2 = 0;
        }
        tVar.g = this.f4869h;
        tVar.f = i2;
        mVar.e(tVar);
        return mVar;
    }

    public final void e(C1166t tVar) {
        if (tVar != C1166t.f4870j) {
            if ((tVar.f & 1) == 1) {
                int i2 = tVar.g;
                this.g = 1 | this.g;
                this.f4869h = i2;
            }
            d(tVar);
            this.d = this.d.p(tVar.e);
        }
    }
}
