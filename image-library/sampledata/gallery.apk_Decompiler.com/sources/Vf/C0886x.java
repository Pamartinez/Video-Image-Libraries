package Vf;

import Bd.C0725a;
import cg.a;
import cg.f;
import cg.g;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.jvm.internal.j;
import qe.C1225a;
import qe.C1227c;
import qe.C1228d;
import qe.C1229e;
import qe.C1230f;
import qe.C1231g;
import qe.C1232h;
import qe.C1233i;

/* renamed from: Vf.x  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0886x extends C1225a implements C1229e {
    public static final C0885w Key = new C0885w(C1228d.d, new C0725a(10));

    public C0886x() {
        super(C1228d.d);
    }

    public static /* synthetic */ C0886x limitedParallelism$default(C0886x xVar, int i2, String str, int i7, Object obj) {
        if (obj == null) {
            if ((i7 & 2) != 0) {
                str = null;
            }
            return xVar.limitedParallelism(i2, str);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: limitedParallelism");
    }

    public void dispatchYield(C1232h hVar, Runnable runnable) {
        i(hVar, runnable);
    }

    public <E extends C1230f> E get(C1231g gVar) {
        E e;
        j.e(gVar, "key");
        if (gVar instanceof C0885w) {
            C0885w wVar = (C0885w) gVar;
            C1231g key = getKey();
            j.e(key, "key");
            if ((key == wVar || wVar.e == key) && (e = (C1230f) wVar.d.invoke(this)) != null) {
                return e;
            }
        } else if (C1228d.d == gVar) {
            return this;
        }
        return null;
    }

    public abstract void i(C1232h hVar, Runnable runnable);

    public final <T> C1227c interceptContinuation(C1227c cVar) {
        return new f(this, cVar);
    }

    public boolean j(C1232h hVar) {
        return !(this instanceof B0);
    }

    public C0886x limitedParallelism(int i2, String str) {
        a.a(i2);
        return new g(this, i2, str);
    }

    public C1232h minusKey(C1231g gVar) {
        j.e(gVar, "key");
        if (gVar instanceof C0885w) {
            C0885w wVar = (C0885w) gVar;
            C1231g key = getKey();
            j.e(key, "key");
            if (!(key == wVar || wVar.e == key) || ((C1230f) wVar.d.invoke(this)) == null) {
                return this;
            }
        } else if (C1228d.d != gVar) {
            return this;
        }
        return C1233i.d;
    }

    public final void releaseInterceptedContinuation(C1227c cVar) {
        C0875l lVar;
        j.c(cVar, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<*>");
        f fVar = (f) cVar;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f.k;
        do {
        } while (atomicReferenceFieldUpdater.get(fVar) == a.f4017c);
        Object obj = atomicReferenceFieldUpdater.get(fVar);
        if (obj instanceof C0875l) {
            lVar = (C0875l) obj;
        } else {
            lVar = null;
        }
        if (lVar != null) {
            lVar.n();
        }
    }

    public String toString() {
        return getClass().getSimpleName() + '@' + D.j(this);
    }
}
