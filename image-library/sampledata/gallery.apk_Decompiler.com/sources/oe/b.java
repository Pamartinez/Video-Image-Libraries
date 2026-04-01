package Oe;

import Hf.C0753b;
import Hf.C0754c;
import Hf.C0774x;
import Hf.G;
import Hf.I;
import Ne.q;
import Qe.C;
import Qe.C0816f;
import Qe.C0819i;
import Qe.C0833x;
import Qe.S;
import Qe.V;
import Rf.a;
import Te.B;
import i.C0212a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1195m;
import ne.C1196n;
import ne.C1202t;
import o1.C0246a;
import qf.C1235b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b extends C0753b {

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ c f3612c;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public b(c cVar) {
        super(cVar.f3615h);
        this.f3612c = cVar;
    }

    public final Collection b() {
        List list;
        Iterable iterable;
        c cVar = this.f3612c;
        int i2 = cVar.k;
        l lVar = cVar.f3617j;
        h hVar = h.f3618c;
        if (j.a(lVar, hVar)) {
            list = C0246a.e0(c.f3613o);
        } else if (j.a(lVar, i.f3619c)) {
            list = C1195m.q0(c.f3614p, new C1235b(q.l, hVar.a(i2)));
        } else {
            k kVar = k.f3621c;
            if (j.a(lVar, kVar)) {
                list = C0246a.e0(c.f3613o);
            } else if (j.a(lVar, j.f3620c)) {
                list = C1195m.q0(c.f3614p, new C1235b(q.f, kVar.a(i2)));
            } else {
                int i7 = a.f3695a;
                throw new IllegalStateException("should not be called");
            }
        }
        C E02 = ((B) cVar.f3616i).g();
        Iterable<C1235b> iterable2 = list;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable2, 10));
        for (C1235b bVar : iterable2) {
            C0816f d = C0833x.d(E02, bVar);
            if (d != null) {
                List list2 = cVar.n;
                int size = d.q().getParameters().size();
                j.e(list2, "<this>");
                if (size >= 0) {
                    if (size == 0) {
                        iterable = C1202t.d;
                    } else {
                        int size2 = list2.size();
                        if (size >= size2) {
                            iterable = C1194l.k1(list2);
                        } else if (size == 1) {
                            iterable = C0246a.e0(C1194l.T0(list2));
                        } else {
                            ArrayList arrayList2 = new ArrayList(size);
                            if (list2 instanceof RandomAccess) {
                                for (int i8 = size2 - size; i8 < size2; i8++) {
                                    arrayList2.add(list2.get(i8));
                                }
                            } else {
                                ListIterator listIterator = list2.listIterator(size2 - size);
                                while (listIterator.hasNext()) {
                                    arrayList2.add(listIterator.next());
                                }
                            }
                            iterable = arrayList2;
                        }
                    }
                    Iterable<V> iterable3 = iterable;
                    ArrayList arrayList3 = new ArrayList(C1196n.w0(iterable3, 10));
                    for (V i10 : iterable3) {
                        arrayList3.add(new G((C0774x) i10.i()));
                    }
                    I.e.getClass();
                    arrayList.add(C0754c.t(I.f, d, arrayList3));
                } else {
                    throw new IllegalArgumentException(C0212a.j(size, "Requested element count ", " is less than zero.").toString());
                }
            } else {
                throw new IllegalStateException(("Built-in class " + bVar + " not found").toString());
            }
        }
        return C1194l.k1(arrayList);
    }

    public final S d() {
        return S.f;
    }

    public final C0819i g() {
        return this.f3612c;
    }

    public final List getParameters() {
        return this.f3612c.n;
    }

    public final boolean i() {
        return true;
    }

    public final C0816f m() {
        return this.f3612c;
    }

    public final String toString() {
        return this.f3612c.toString();
    }
}
