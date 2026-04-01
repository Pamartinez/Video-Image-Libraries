package Ze;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import me.i;
import ne.z;
import qf.C1235b;
import qf.C1236c;
import qf.C1240g;
import qf.C1243j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class j {

    /* renamed from: a  reason: collision with root package name */
    public static final LinkedHashMap f3945a;
    public static final Map b;

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        f3945a = linkedHashMap;
        b(C1243j.t, a("java.util.ArrayList", "java.util.LinkedList"));
        b(C1243j.u, a("java.util.HashSet", "java.util.TreeSet", "java.util.LinkedHashSet"));
        b(C1243j.v, a("java.util.HashMap", "java.util.TreeMap", "java.util.LinkedHashMap", "java.util.concurrent.ConcurrentHashMap", "java.util.concurrent.ConcurrentSkipListMap"));
        C1236c cVar = new C1236c("java.util.function.Function");
        C1236c e = cVar.e();
        C1240g f = cVar.f();
        kotlin.jvm.internal.j.d(f, "shortName(...)");
        b(new C1235b(e, f), a("java.util.function.UnaryOperator"));
        C1236c cVar2 = new C1236c("java.util.function.BiFunction");
        C1236c e7 = cVar2.e();
        C1240g f5 = cVar2.f();
        kotlin.jvm.internal.j.d(f5, "shortName(...)");
        b(new C1235b(e7, f5), a("java.util.function.BinaryOperator"));
        ArrayList arrayList = new ArrayList(linkedHashMap.size());
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            arrayList.add(new i(((C1235b) entry.getKey()).a(), ((C1235b) entry.getValue()).a()));
        }
        b = z.e0(arrayList);
    }

    public static ArrayList a(String... strArr) {
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String cVar : strArr) {
            C1236c cVar2 = new C1236c(cVar);
            C1236c e = cVar2.e();
            C1240g f = cVar2.f();
            kotlin.jvm.internal.j.d(f, "shortName(...)");
            arrayList.add(new C1235b(e, f));
        }
        return arrayList;
    }

    public static void b(C1235b bVar, ArrayList arrayList) {
        for (Object next : arrayList) {
            C1235b bVar2 = (C1235b) next;
            f3945a.put(next, bVar);
        }
    }
}
