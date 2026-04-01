package cf;

import Ae.b;
import B0.a;
import Df.E;
import Gf.e;
import Gf.g;
import Gf.m;
import Qe.K;
import We.y;
import df.C0954q;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.jvm.internal.j;
import ne.C1202t;
import qf.C1236c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d implements K {

    /* renamed from: a  reason: collision with root package name */
    public final a f4015a;
    public final e b;

    /* JADX WARNING: type inference failed for: r2v0, types: [me.f, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r2v2, types: [Ae.b, java.lang.Object] */
    public d(C0922a aVar) {
        this.f4015a = new a(aVar, b.b, new Object());
        m mVar = (m) aVar.f4006a;
        mVar.getClass();
        this.b = new e(mVar, new ConcurrentHashMap(3, 1.0f, 2), new Object(), 0);
    }

    public final void a(C1236c cVar, ArrayList arrayList) {
        j.e(cVar, "fqName");
        arrayList.add(c(cVar));
    }

    public final boolean b(C1236c cVar) {
        j.e(cVar, "fqName");
        ((C0922a) this.f4015a.d).b.getClass();
        return false;
    }

    public final C0954q c(C1236c cVar) {
        ((C0922a) this.f4015a.d).b.getClass();
        j.e(cVar, "fqName");
        E e = new E(17, (Object) this, (Object) new y(cVar));
        e eVar = this.b;
        eVar.getClass();
        Object invoke = eVar.invoke(new g(cVar, e));
        if (invoke != null) {
            return (C0954q) invoke;
        }
        e.a(3);
        throw null;
    }

    public final Collection m(C1236c cVar, b bVar) {
        j.e(cVar, "fqName");
        Collection collection = (List) c(cVar).f4256o.invoke();
        if (collection == null) {
            collection = C1202t.d;
        }
        return collection;
    }

    public final String toString() {
        return "LazyJavaPackageFragmentProvider of module " + ((C0922a) this.f4015a.d).f4011o;
    }
}
