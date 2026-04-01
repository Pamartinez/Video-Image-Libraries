package hf;

import Sf.b;
import Sf.q;
import Sf.r;
import ge.W0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import kotlin.jvm.internal.j;
import me.i;
import ne.C1196n;
import ne.x;
import ne.z;
import yf.C1359c;

/* renamed from: hf.n  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1092n {

    /* renamed from: a  reason: collision with root package name */
    public final String f4594a;
    public final ArrayList b = new ArrayList();

    /* renamed from: c  reason: collision with root package name */
    public i f4595c = new i("V", (Object) null);

    public C1092n(W0 w02, String str, String str2) {
        this.f4594a = str2;
    }

    public final void a(String str, C1082d... dVarArr) {
        C1094p pVar;
        j.e(str, "type");
        if (dVarArr.length == 0) {
            pVar = null;
        } else {
            r rVar = new r(3, new q(21, dVarArr));
            int Z = z.Z(C1196n.w0(rVar, 10));
            if (Z < 16) {
                Z = 16;
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap(Z);
            Iterator it = rVar.iterator();
            while (true) {
                b bVar = (b) it;
                if (!bVar.e.hasNext()) {
                    break;
                }
                x xVar = (x) bVar.next();
                linkedHashMap.put(Integer.valueOf(xVar.f4950a), (C1082d) xVar.b);
            }
            pVar = new C1094p(linkedHashMap);
        }
        this.b.add(new i(str, pVar));
    }

    public final void b(String str, C1082d... dVarArr) {
        j.e(str, "type");
        r rVar = new r(3, new q(21, dVarArr));
        int Z = z.Z(C1196n.w0(rVar, 10));
        if (Z < 16) {
            Z = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(Z);
        Iterator it = rVar.iterator();
        while (true) {
            b bVar = (b) it;
            if (bVar.e.hasNext()) {
                x xVar = (x) bVar.next();
                linkedHashMap.put(Integer.valueOf(xVar.f4950a), (C1082d) xVar.b);
            } else {
                this.f4595c = new i(str, new C1094p(linkedHashMap));
                return;
            }
        }
    }

    public final void c(C1359c cVar) {
        j.e(cVar, "type");
        String d = cVar.d();
        j.d(d, "getDesc(...)");
        this.f4595c = new i(d, (Object) null);
    }
}
