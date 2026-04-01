package If;

import Ae.c;
import D0.e;
import Hf.B;
import Hf.C0754c;
import Hf.C0756e;
import Hf.C0759h;
import Hf.C0773w;
import Hf.C0774x;
import Hf.I;
import Hf.M;
import Hf.c0;
import Jf.h;
import Jf.l;
import Qf.k;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1196n;
import ne.C1200r;
import ne.C1202t;
import vf.C1334n;
import vf.C1335o;
import vf.C1336p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class u {

    /* renamed from: a  reason: collision with root package name */
    public static final u f3471a = new Object();

    public static ArrayList a(AbstractCollection abstractCollection, c cVar) {
        ArrayList arrayList = new ArrayList(abstractCollection);
        Iterator it = arrayList.iterator();
        j.d(it, "iterator(...)");
        while (it.hasNext()) {
            B b = (B) it.next();
            if (!arrayList.isEmpty()) {
                Iterator it2 = arrayList.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    B b5 = (B) it2.next();
                    if (b5 != b) {
                        j.b(b5);
                        j.b(b);
                        if (((Boolean) cVar.invoke(b5, b)).booleanValue()) {
                            it.remove();
                            break;
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    public final B b(ArrayList arrayList) {
        B b;
        B b5;
        B b8;
        Set set;
        I i2;
        arrayList.size();
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            B b10 = (B) it.next();
            if (b10.s0() instanceof C0773w) {
                Collection h5 = b10.s0().h();
                j.d(h5, "getSupertypes(...)");
                Iterable<C0774x> iterable = h5;
                ArrayList arrayList3 = new ArrayList(C1196n.w0(iterable, 10));
                for (C0774x xVar : iterable) {
                    j.b(xVar);
                    B E = C0754c.E(xVar);
                    if (b10.u0()) {
                        E = E.y0(true);
                    }
                    arrayList3.add(E);
                }
                arrayList2.addAll(arrayList3);
            } else {
                arrayList2.add(b10);
            }
        }
        s sVar = s.START;
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            sVar = sVar.a((c0) it2.next());
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it3 = arrayList2.iterator();
        while (it3.hasNext()) {
            B b11 = (B) it3.next();
            if (sVar == s.NOT_NULL) {
                if (b11 instanceof h) {
                    h hVar = (h) b11;
                    b11 = new h(hVar.e, hVar.f, hVar.g, hVar.f3463h, hVar.f3464i, true);
                }
                j.e(b11, "<this>");
                B o2 = C0756e.o(b11, false);
                if (o2 == null && (o2 = C0754c.o(b11)) == null) {
                    b11 = b11.y0(false);
                } else {
                    b11 = o2;
                }
            }
            linkedHashSet.add(b11);
        }
        ArrayList<B> arrayList4 = arrayList;
        ArrayList arrayList5 = new ArrayList(C1196n.w0(arrayList4, 10));
        for (B p02 : arrayList4) {
            arrayList5.add(p02.p0());
        }
        Iterator it4 = arrayList5.iterator();
        if (it4.hasNext()) {
            I i7 = it4.next();
            while (it4.hasNext()) {
                I i8 = (I) it4.next();
                I i10 = (I) i7;
                i10.getClass();
                e eVar = I.e;
                j.e(i8, "other");
                if (!i10.isEmpty() || !i8.isEmpty()) {
                    ArrayList arrayList6 = new ArrayList();
                    Collection<Number> values = ((ConcurrentHashMap) eVar.e).values();
                    j.d(values, "<get-values>(...)");
                    for (Number intValue : values) {
                        int intValue2 = intValue.intValue();
                        C0759h hVar2 = (C0759h) i10.d.get(intValue2);
                        C0759h hVar3 = (C0759h) i8.d.get(intValue2);
                        if (hVar2 != null) {
                            if (!j.a(hVar3, hVar2)) {
                                hVar2 = null;
                            }
                            hVar3 = hVar2;
                        } else if (hVar3 == null || !j.a(hVar2, hVar3)) {
                            hVar3 = null;
                        }
                        k.a(arrayList6, hVar3);
                    }
                    i2 = e.D(arrayList6);
                } else {
                    i2 = i10;
                }
                i7 = i2;
            }
            I i11 = (I) i7;
            if (linkedHashSet.size() == 1) {
                b = (B) C1194l.a1(linkedHashSet);
            } else {
                ArrayList a7 = a(linkedHashSet, new t(2, this, 0));
                a7.isEmpty();
                C1334n nVar = C1334n.INTERSECTION_TYPE;
                if (a7.isEmpty()) {
                    b5 = null;
                } else {
                    Iterator it5 = a7.iterator();
                    if (it5.hasNext()) {
                        Object obj = it5.next();
                        while (it5.hasNext()) {
                            B b12 = (B) it5.next();
                            B b13 = (B) obj;
                            if (!(b13 == null || b12 == null)) {
                                M s0 = b13.s0();
                                M s02 = b12.s0();
                                boolean z = s0 instanceof C1336p;
                                if (z && (s02 instanceof C1336p)) {
                                    Set set2 = ((C1336p) s0).f5161a;
                                    Set set3 = ((C1336p) s02).f5161a;
                                    int i12 = C1335o.f5160a[nVar.ordinal()];
                                    if (i12 == 1) {
                                        j.e(set2, "<this>");
                                        j.e(set3, "other");
                                        set = C1194l.o1(set2);
                                        set.retainAll(set3);
                                    } else if (i12 == 2) {
                                        j.e(set2, "<this>");
                                        j.e(set3, "other");
                                        set = C1194l.o1(set2);
                                        C1200r.A0(set3, set);
                                    } else {
                                        throw new RuntimeException();
                                    }
                                    C1336p pVar = new C1336p(set);
                                    I.e.getClass();
                                    I i13 = I.f;
                                    j.e(i13, "attributes");
                                    b8 = C0754c.v(l.a(h.INTEGER_LITERAL_TYPE_SCOPE, true, "unknown integer literal type"), i13, pVar, C1202t.d, false);
                                    obj = b8;
                                } else if (z) {
                                    if (((C1336p) s0).f5161a.contains(b12)) {
                                        b8 = b12;
                                        obj = b8;
                                    }
                                } else if ((s02 instanceof C1336p) && ((C1336p) s02).f5161a.contains(b13)) {
                                    b8 = b13;
                                    obj = b8;
                                }
                            }
                            b8 = null;
                            obj = b8;
                        }
                        b5 = (B) obj;
                    } else {
                        throw new UnsupportedOperationException("Empty collection can't be reduced.");
                    }
                }
                if (b5 != null) {
                    b = b5;
                } else {
                    k.b.getClass();
                    ArrayList a10 = a(a7, new t(2, j.b, 1));
                    a10.isEmpty();
                    if (a10.size() < 2) {
                        b = (B) C1194l.a1(a10);
                    } else {
                        b = new C0773w(linkedHashSet).b();
                    }
                }
            }
            return b.A0(i11);
        }
        throw new UnsupportedOperationException("Empty collection can't be reduced.");
    }
}
