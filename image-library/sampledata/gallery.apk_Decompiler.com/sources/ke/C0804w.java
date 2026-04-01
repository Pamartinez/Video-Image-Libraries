package Ke;

import Ae.a;
import D1.f;
import Ff.q;
import Hf.C0774x;
import Ne.d;
import Ne.i;
import Ne.p;
import Qe.C0816f;
import Qe.C0817g;
import Qe.V;
import Qf.k;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1196n;
import qf.C1240g;
import tf.C1301e;
import xf.C1353d;

/* renamed from: Ke.w  reason: case insensitive filesystem */
public final class C0804w implements a {
    public final /* synthetic */ int d;
    public final C0805x e;
    public final B f;

    public /* synthetic */ C0804w(C0805x xVar, B b, int i2) {
        this.d = i2;
        this.e = xVar;
        this.f = b;
    }

    public final Object invoke() {
        Field field;
        int i2 = this.d;
        B b = this.f;
        C0805x xVar = this.e;
        switch (i2) {
            case 0:
                Class cls = b.e;
                C0816f a7 = xVar.a();
                if (a7.b() != C0817g.OBJECT) {
                    return null;
                }
                if (a7.T()) {
                    LinkedHashSet linkedHashSet = d.f3545a;
                    if (!f.D(a7)) {
                        field = cls.getEnclosingClass().getDeclaredField(a7.getName().b());
                        Object obj = field.get((Object) null);
                        j.c(obj, "null cannot be cast to non-null type T of kotlin.reflect.jvm.internal.KClassImpl.Data.objectInstance_delegate$lambda$11");
                        return obj;
                    }
                }
                field = cls.getDeclaredField("INSTANCE");
                Object obj2 = field.get((Object) null);
                j.c(obj2, "null cannot be cast to non-null type T of kotlin.reflect.jvm.internal.KClassImpl.Data.objectInstance_delegate$lambda$11");
                return obj2;
            case 1:
                List j2 = xVar.a().j();
                j.d(j2, "getDeclaredTypeParameters(...)");
                Iterable<V> iterable = j2;
                ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
                for (V v : iterable) {
                    j.b(v);
                    arrayList.add(new t0(b, v));
                }
                return arrayList;
            default:
                Collection<C0774x> h5 = xVar.a().q().h();
                j.d(h5, "getSupertypes(...)");
                ArrayList arrayList2 = new ArrayList(h5.size());
                for (C0774x xVar2 : h5) {
                    j.b(xVar2);
                    arrayList2.add(new r0(xVar2, new q(xVar2, xVar, b, 1)));
                }
                C0816f a10 = xVar.a();
                C1240g gVar = i.e;
                if (!i.b(a10, p.f3565a) && !i.b(a10, p.b)) {
                    if (!arrayList2.isEmpty()) {
                        Iterator it = arrayList2.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                C0817g b5 = C1301e.c(((r0) it.next()).d).b();
                                j.d(b5, "getKind(...)");
                                if (b5 == C0817g.INTERFACE || b5 == C0817g.ANNOTATION_CLASS) {
                                }
                            }
                        }
                    }
                    arrayList2.add(new r0(C1353d.e(xVar.a()).e(), C0803v.d));
                }
                return k.d(arrayList2);
        }
    }
}
