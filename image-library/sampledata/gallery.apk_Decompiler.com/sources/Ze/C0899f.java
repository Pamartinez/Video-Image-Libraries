package Ze;

import Ne.p;
import Ne.q;
import Pe.d;
import com.samsung.android.sdk.bixby2.state.StateHandler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.j;
import me.i;
import ne.C1194l;
import ne.C1196n;
import ne.z;
import qf.C1235b;
import qf.C1236c;
import qf.C1238e;
import qf.C1240g;

/* renamed from: Ze.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0899f {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f3942a;
    public static final LinkedHashMap b;

    /* renamed from: c  reason: collision with root package name */
    public static final Set f3943c;
    public static final Set d;

    /* JADX WARNING: type inference failed for: r0v14, types: [java.util.Map, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r0v17, types: [java.util.Map, java.lang.Object] */
    static {
        C1238e eVar = p.f3570j;
        i iVar = new i(eVar.b(C1240g.e("name")).g(), q.d);
        i iVar2 = new i(eVar.b(C1240g.e("ordinal")).g(), C1240g.e("ordinal"));
        i iVar3 = new i(p.f3552C.c(C1240g.e("size")), C1240g.e("size"));
        C1236c cVar = p.f3554G;
        Map b0 = z.b0(iVar, iVar2, iVar3, new i(cVar.c(C1240g.e("size")), C1240g.e("size")), new i(p.e.b(C1240g.e("length")).g(), C1240g.e("length")), new i(cVar.c(C1240g.e("keys")), C1240g.e("keySet")), new i(cVar.c(C1240g.e(StateHandler.VALUES)), C1240g.e(StateHandler.VALUES)), new i(cVar.c(C1240g.e("entries")), C1240g.e("entrySet")));
        f3942a = b0;
        Set<Map.Entry> entrySet = b0.entrySet();
        ArrayList arrayList = new ArrayList(C1196n.w0(entrySet, 10));
        for (Map.Entry entry : entrySet) {
            arrayList.add(new i(((C1236c) entry.getKey()).f(), entry.getValue()));
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            i iVar4 = (i) it.next();
            C1240g gVar = (C1240g) iVar4.e;
            Object obj = linkedHashMap.get(gVar);
            if (obj == null) {
                obj = new ArrayList();
                linkedHashMap.put(gVar, obj);
            }
            ((List) obj).add((C1240g) iVar4.d);
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(z.Z(linkedHashMap.size()));
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            linkedHashMap2.put(entry2.getKey(), C1194l.H0((Iterable) entry2.getValue()));
        }
        b = linkedHashMap2;
        ? r0 = f3942a;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Map.Entry entry3 : r0.entrySet()) {
            String str = d.f3633a;
            C1238e i2 = ((C1236c) entry3.getKey()).e().i();
            j.d(i2, "toUnsafe(...)");
            C1235b f = d.f(i2);
            j.b(f);
            linkedHashSet.add(f.a().c((C1240g) entry3.getValue()));
        }
        Set<C1236c> keySet = f3942a.keySet();
        f3943c = keySet;
        ArrayList arrayList2 = new ArrayList(C1196n.w0(keySet, 10));
        for (C1236c f5 : keySet) {
            arrayList2.add(f5.f());
        }
        d = C1194l.p1(arrayList2);
    }
}
