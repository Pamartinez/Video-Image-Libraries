package Ne;

import Hf.C0774x;
import Hf.a0;
import Qe.C0819i;
import Qe.C0822l;
import Qe.H;
import Te.B;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.jvm.internal.j;
import me.i;
import ne.C1194l;
import ne.z;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class u {

    /* renamed from: a  reason: collision with root package name */
    public static final Set f3582a;
    public static final HashMap b = new HashMap();

    /* renamed from: c  reason: collision with root package name */
    public static final HashMap f3583c = new HashMap();
    public static final LinkedHashSet d;

    static {
        t[] values = t.values();
        ArrayList arrayList = new ArrayList(values.length);
        for (t c5 : values) {
            arrayList.add(c5.c());
        }
        f3582a = C1194l.p1(arrayList);
        s[] values2 = s.values();
        ArrayList arrayList2 = new ArrayList(values2.length);
        for (s a7 : values2) {
            arrayList2.add(a7.a());
        }
        C1194l.p1(arrayList2);
        z.d0(new HashMap(z.Z(4)), new i[]{new i(s.UBYTEARRAY, C1240g.e("ubyteArrayOf")), new i(s.USHORTARRAY, C1240g.e("ushortArrayOf")), new i(s.UINTARRAY, C1240g.e("uintArrayOf")), new i(s.ULONGARRAY, C1240g.e("ulongArrayOf"))});
        t[] values3 = t.values();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (t a10 : values3) {
            linkedHashSet.add(a10.a().f());
        }
        d = linkedHashSet;
        for (t tVar : t.values()) {
            b.put(tVar.a(), tVar.b());
            f3583c.put(tVar.b(), tVar.a());
        }
    }

    public static final boolean a(C0774x xVar) {
        C0819i g;
        if (a0.l(xVar) || (g = xVar.s0().g()) == null) {
            return false;
        }
        C0822l g3 = g.g();
        if (!(g3 instanceof H) || !j.a(((B) ((H) g3)).f3743i, q.l) || !f3582a.contains(g.getName())) {
            return false;
        }
        return true;
    }
}
