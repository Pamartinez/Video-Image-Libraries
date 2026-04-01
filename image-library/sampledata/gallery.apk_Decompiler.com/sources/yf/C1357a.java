package yf;

import B0.a;
import Qe.C0816f;
import Te.H;
import java.util.ArrayList;
import kotlin.jvm.internal.j;
import ne.C1200r;
import ne.C1201s;
import oe.C1214c;
import qf.C1240g;

/* renamed from: yf.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1357a implements C1361e {
    public final void a(C0816f fVar, ArrayList arrayList, a aVar) {
        j.e(fVar, "thisDescriptor");
        j.e(aVar, "c");
        while (true) {
            C1201s sVar = C1201s.d;
            if (sVar.hasNext()) {
                ((C1357a) ((C1361e) sVar.next())).a(fVar, arrayList, aVar);
            } else {
                return;
            }
        }
    }

    public final void b(C0816f fVar, C1240g gVar, ArrayList arrayList, a aVar) {
        j.e(fVar, "thisDescriptor");
        j.e(gVar, "name");
        j.e(aVar, "c");
        while (true) {
            C1201s sVar = C1201s.d;
            if (sVar.hasNext()) {
                ((C1357a) ((C1361e) sVar.next())).b(fVar, gVar, arrayList, aVar);
            } else {
                return;
            }
        }
    }

    public final void c(C0816f fVar, C1240g gVar, C1214c cVar, a aVar) {
        j.e(fVar, "thisDescriptor");
        j.e(gVar, "name");
        j.e(aVar, "c");
        while (true) {
            C1201s sVar = C1201s.d;
            if (sVar.hasNext()) {
                ((C1357a) ((C1361e) sVar.next())).c(fVar, gVar, cVar, aVar);
            } else {
                return;
            }
        }
    }

    public final void d(C0816f fVar, C1240g gVar, ArrayList arrayList, a aVar) {
        j.e(fVar, "thisDescriptor");
        j.e(gVar, "name");
        j.e(aVar, "c");
        while (true) {
            C1201s sVar = C1201s.d;
            if (sVar.hasNext()) {
                ((C1357a) ((C1361e) sVar.next())).d(fVar, gVar, arrayList, aVar);
            } else {
                return;
            }
        }
    }

    public final ArrayList e(C0816f fVar, a aVar) {
        j.e(fVar, "thisDescriptor");
        j.e(aVar, "c");
        ArrayList arrayList = new ArrayList();
        while (true) {
            C1201s sVar = C1201s.d;
            if (!sVar.hasNext()) {
                return arrayList;
            }
            C1200r.A0(((C1357a) ((C1361e) sVar.next())).e(fVar, aVar), arrayList);
        }
    }

    public final ArrayList f(C0816f fVar, a aVar) {
        j.e(fVar, "thisDescriptor");
        j.e(aVar, "c");
        ArrayList arrayList = new ArrayList();
        while (true) {
            C1201s sVar = C1201s.d;
            if (!sVar.hasNext()) {
                return arrayList;
            }
            C1200r.A0(((C1357a) ((C1361e) sVar.next())).f(fVar, aVar), arrayList);
        }
    }

    public final ArrayList g(C0816f fVar, a aVar) {
        j.e(fVar, "thisDescriptor");
        j.e(aVar, "c");
        ArrayList arrayList = new ArrayList();
        while (true) {
            C1201s sVar = C1201s.d;
            if (!sVar.hasNext()) {
                return arrayList;
            }
            C1200r.A0(((C1357a) ((C1361e) sVar.next())).g(fVar, aVar), arrayList);
        }
    }

    public final H h(C0816f fVar, H h5, a aVar) {
        j.e(h5, "propertyDescriptor");
        j.e(aVar, "c");
        while (true) {
            C1201s sVar = C1201s.d;
            if (!sVar.hasNext()) {
                return h5;
            }
            h5 = ((C1357a) ((C1361e) sVar.next())).h(fVar, h5, aVar);
        }
    }
}
