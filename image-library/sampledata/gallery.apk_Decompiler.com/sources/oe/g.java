package Oe;

import Gd.a;
import Hf.C0774x;
import Hf.X;
import Qe.C0813c;
import Qe.C0822l;
import Qe.C0831v;
import Qe.Q;
import Re.h;
import Te.C0857s;
import Te.K;
import Te.t;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.j;
import me.i;
import ne.C1194l;
import ne.C1196n;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g extends K {
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public g(Qe.C0822l r8, Oe.g r9, Qe.C0813c r10, boolean r11) {
        /*
            r7 = this;
            qf.g r4 = Nf.r.g
            Qe.S r6 = Qe.Q.f3662a
            Re.f r3 = Re.g.f3692a
            r0 = r7
            r1 = r8
            r2 = r9
            r5 = r10
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r7 = 1
            r0.q = r7
            r0.y = r11
            r7 = 0
            r0.z = r7
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Oe.g.<init>(Qe.l, Oe.g, Qe.c, boolean):void");
    }

    public final t G0(C0813c cVar, C0822l lVar, C0831v vVar, Q q, h hVar, C1240g gVar) {
        j.e(lVar, "newOwner");
        j.e(cVar, "kind");
        j.e(hVar, "annotations");
        return new g(lVar, (g) vVar, cVar, this.y);
    }

    public final t H0(C0857s sVar) {
        C1240g gVar;
        g gVar2 = (g) super.H0(sVar);
        if (gVar2 == null) {
            return null;
        }
        List B = gVar2.B();
        j.d(B, "getValueParameters(...)");
        Iterable<Te.Q> iterable = B;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return gVar2;
        }
        for (Te.Q type : iterable) {
            C0774x type2 = type.getType();
            j.d(type2, "getType(...)");
            if (a.s(type2) != null) {
                List B9 = gVar2.B();
                j.d(B9, "getValueParameters(...)");
                Iterable<Te.Q> iterable2 = B9;
                ArrayList arrayList = new ArrayList(C1196n.w0(iterable2, 10));
                for (Te.Q type3 : iterable2) {
                    C0774x type4 = type3.getType();
                    j.d(type4, "getType(...)");
                    arrayList.add(a.s(type4));
                }
                int size = gVar2.B().size() - arrayList.size();
                boolean z = true;
                if (size == 0) {
                    List B10 = gVar2.B();
                    j.d(B10, "getValueParameters(...)");
                    ArrayList r1 = C1194l.r1(arrayList, B10);
                    if (r1.isEmpty()) {
                        return gVar2;
                    }
                    Iterator it = r1.iterator();
                    while (it.hasNext()) {
                        i iVar = (i) it.next();
                        if (!j.a((C1240g) iVar.d, ((Te.Q) iVar.e).getName())) {
                        }
                    }
                    return gVar2;
                }
                List B11 = gVar2.B();
                j.d(B11, "getValueParameters(...)");
                Iterable<Te.Q> iterable3 = B11;
                ArrayList arrayList2 = new ArrayList(C1196n.w0(iterable3, 10));
                for (Te.Q q : iterable3) {
                    C1240g name = q.getName();
                    j.d(name, "getName(...)");
                    int i2 = q.f3770j;
                    int i7 = i2 - size;
                    if (i7 >= 0 && (gVar = (C1240g) arrayList.get(i7)) != null) {
                        name = gVar;
                    }
                    arrayList2.add(q.E0(gVar2, name, i2));
                }
                C0857s K02 = gVar2.K0(X.b);
                if (!arrayList.isEmpty()) {
                    Iterator it2 = arrayList.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            if (((C1240g) it2.next()) == null) {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
                z = false;
                K02.y = Boolean.valueOf(z);
                K02.f3791j = arrayList2;
                K02.f3789h = gVar2.a();
                t H02 = super.H0(K02);
                j.b(H02);
                return H02;
            }
        }
        return gVar2;
    }

    public final boolean isExternal() {
        return false;
    }

    public final boolean isInline() {
        return false;
    }

    public final boolean w() {
        return false;
    }
}
