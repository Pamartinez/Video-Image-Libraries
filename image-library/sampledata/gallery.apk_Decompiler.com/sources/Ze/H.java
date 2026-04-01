package Ze;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.j;
import me.i;
import ne.C1182C;
import ne.C1192j;
import ne.C1194l;
import ne.C1196n;
import ne.z;
import qf.C1240g;
import yf.C1359c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class H {

    /* renamed from: a  reason: collision with root package name */
    public static final ArrayList f3932a;
    public static final ArrayList b;

    /* renamed from: c  reason: collision with root package name */
    public static final Object f3933c;
    public static final LinkedHashMap d;
    public static final Set e;
    public static final Set f;
    public static final D g;

    /* renamed from: h  reason: collision with root package name */
    public static final Object f3934h;

    /* renamed from: i  reason: collision with root package name */
    public static final LinkedHashMap f3935i;

    /* renamed from: j  reason: collision with root package name */
    public static final HashSet f3936j;
    public static final LinkedHashMap k;

    /* JADX WARNING: type inference failed for: r0v14, types: [java.util.Map, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r0v26, types: [java.util.Map, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r0v29, types: [java.util.Map, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r0v32, types: [java.util.Map, java.lang.Object] */
    static {
        Set<String> z02 = C1192j.z0(new String[]{"containsAll", "removeAll", "retainAll"});
        ArrayList arrayList = new ArrayList(C1196n.w0(z02, 10));
        for (String a7 : z02) {
            String d2 = C1359c.BOOLEAN.d();
            j.d(d2, "getDesc(...)");
            arrayList.add(l.a("java/util/Collection", a7, "Ljava/util/Collection;", d2));
        }
        f3932a = arrayList;
        ArrayList arrayList2 = new ArrayList(C1196n.w0(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((D) it.next()).e);
        }
        b = arrayList2;
        ArrayList<D> arrayList3 = f3932a;
        ArrayList arrayList4 = new ArrayList(C1196n.w0(arrayList3, 10));
        for (D d3 : arrayList3) {
            arrayList4.add(d3.b.b());
        }
        String concat = "java/util/".concat("Collection");
        C1359c cVar = C1359c.BOOLEAN;
        String d5 = cVar.d();
        j.d(d5, "getDesc(...)");
        D a10 = l.a(concat, "contains", "Ljava/lang/Object;", d5);
        G g3 = G.FALSE;
        i iVar = new i(a10, g3);
        String concat2 = "java/util/".concat("Collection");
        String d6 = cVar.d();
        j.d(d6, "getDesc(...)");
        i iVar2 = new i(l.a(concat2, "remove", "Ljava/lang/Object;", d6), g3);
        String concat3 = "java/util/".concat("Map");
        String d7 = cVar.d();
        j.d(d7, "getDesc(...)");
        i iVar3 = new i(l.a(concat3, "containsKey", "Ljava/lang/Object;", d7), g3);
        String concat4 = "java/util/".concat("Map");
        String d9 = cVar.d();
        j.d(d9, "getDesc(...)");
        i iVar4 = new i(l.a(concat4, "containsValue", "Ljava/lang/Object;", d9), g3);
        String concat5 = "java/util/".concat("Map");
        String d10 = cVar.d();
        j.d(d10, "getDesc(...)");
        i iVar5 = new i(l.a(concat5, "remove", "Ljava/lang/Object;Ljava/lang/Object;", d10), g3);
        i iVar6 = new i(l.a("java/util/".concat("Map"), "getOrDefault", "Ljava/lang/Object;Ljava/lang/Object;", "Ljava/lang/Object;"), G.MAP_GET_OR_DEFAULT);
        D a11 = l.a("java/util/".concat("Map"), "get", "Ljava/lang/Object;", "Ljava/lang/Object;");
        G g10 = G.NULL;
        i iVar7 = new i(a11, g10);
        i iVar8 = new i(l.a("java/util/".concat("Map"), "remove", "Ljava/lang/Object;", "Ljava/lang/Object;"), g10);
        String concat6 = "java/util/".concat("List");
        C1359c cVar2 = C1359c.INT;
        String d11 = cVar2.d();
        j.d(d11, "getDesc(...)");
        i iVar9 = iVar8;
        D a12 = l.a(concat6, "indexOf", "Ljava/lang/Object;", d11);
        G g11 = G.INDEX;
        i iVar10 = new i(a12, g11);
        String concat7 = "java/util/".concat("List");
        String d12 = cVar2.d();
        j.d(d12, "getDesc(...)");
        Map b0 = z.b0(iVar, iVar2, iVar3, iVar4, iVar5, iVar6, iVar7, iVar9, iVar10, new i(l.a(concat7, "lastIndexOf", "Ljava/lang/Object;", d12), g11));
        f3933c = b0;
        LinkedHashMap linkedHashMap = new LinkedHashMap(z.Z(b0.size()));
        for (Map.Entry entry : b0.entrySet()) {
            linkedHashMap.put(((D) entry.getKey()).e, entry.getValue());
        }
        d = linkedHashMap;
        LinkedHashSet<D> b02 = C1182C.b0(f3933c.keySet(), f3932a);
        ArrayList arrayList5 = new ArrayList(C1196n.w0(b02, 10));
        for (D d13 : b02) {
            arrayList5.add(d13.b);
        }
        e = C1194l.p1(arrayList5);
        ArrayList arrayList6 = new ArrayList(C1196n.w0(b02, 10));
        for (D d14 : b02) {
            arrayList6.add(d14.e);
        }
        f = C1194l.p1(arrayList6);
        C1359c cVar3 = C1359c.INT;
        String d15 = cVar3.d();
        j.d(d15, "getDesc(...)");
        D a13 = l.a("java/util/List", "removeAt", d15, "Ljava/lang/Object;");
        g = a13;
        String concat8 = "java/lang/".concat("Number");
        String d16 = C1359c.BYTE.d();
        j.d(d16, "getDesc(...)");
        i iVar11 = new i(l.a(concat8, "toByte", "", d16), C1240g.e("byteValue"));
        String concat9 = "java/lang/".concat("Number");
        String d17 = C1359c.SHORT.d();
        j.d(d17, "getDesc(...)");
        i iVar12 = new i(l.a(concat9, "toShort", "", d17), C1240g.e("shortValue"));
        String concat10 = "java/lang/".concat("Number");
        String d18 = cVar3.d();
        j.d(d18, "getDesc(...)");
        i iVar13 = new i(l.a(concat10, "toInt", "", d18), C1240g.e("intValue"));
        String concat11 = "java/lang/".concat("Number");
        String d19 = C1359c.LONG.d();
        j.d(d19, "getDesc(...)");
        i iVar14 = new i(l.a(concat11, "toLong", "", d19), C1240g.e("longValue"));
        String concat12 = "java/lang/".concat("Number");
        String d20 = C1359c.FLOAT.d();
        j.d(d20, "getDesc(...)");
        i iVar15 = new i(l.a(concat12, "toFloat", "", d20), C1240g.e("floatValue"));
        String concat13 = "java/lang/".concat("Number");
        String d21 = C1359c.DOUBLE.d();
        j.d(d21, "getDesc(...)");
        i iVar16 = new i(l.a(concat13, "toDouble", "", d21), C1240g.e("doubleValue"));
        i iVar17 = new i(a13, C1240g.e("remove"));
        String concat14 = "java/lang/".concat("CharSequence");
        String d22 = cVar3.d();
        j.d(d22, "getDesc(...)");
        String d23 = C1359c.CHAR.d();
        j.d(d23, "getDesc(...)");
        Map b03 = z.b0(iVar11, iVar12, iVar13, iVar14, iVar15, iVar16, iVar17, new i(l.a(concat14, "get", d22, d23), C1240g.e("charAt")));
        f3934h = b03;
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(z.Z(b03.size()));
        for (Map.Entry entry2 : b03.entrySet()) {
            linkedHashMap2.put(((D) entry2.getKey()).e, entry2.getValue());
        }
        f3935i = linkedHashMap2;
        ? r0 = f3934h;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Map.Entry entry3 : r0.entrySet()) {
            D d24 = (D) entry3.getKey();
            C1240g gVar = (C1240g) entry3.getValue();
            String str = d24.f3930a;
            String str2 = d24.f3931c;
            String str3 = d24.d;
            j.e(str, "classInternalName");
            j.e(gVar, "name");
            String str4 = gVar + '(' + str2 + ')' + str3;
            j.e(str4, "jvmDescriptor");
            linkedHashSet.add(str + '.' + str4);
        }
        Set<D> keySet = f3934h.keySet();
        HashSet hashSet = new HashSet();
        for (D d25 : keySet) {
            hashSet.add(d25.b);
        }
        f3936j = hashSet;
        Set<Map.Entry> entrySet = f3934h.entrySet();
        ArrayList arrayList7 = new ArrayList(C1196n.w0(entrySet, 10));
        for (Map.Entry entry4 : entrySet) {
            arrayList7.add(new i(((D) entry4.getKey()).b, entry4.getValue()));
        }
        int Z = z.Z(C1196n.w0(arrayList7, 10));
        if (Z < 16) {
            Z = 16;
        }
        LinkedHashMap linkedHashMap3 = new LinkedHashMap(Z);
        Iterator it2 = arrayList7.iterator();
        while (it2.hasNext()) {
            i iVar18 = (i) it2.next();
            linkedHashMap3.put((C1240g) iVar18.e, (C1240g) iVar18.d);
        }
        k = linkedHashMap3;
    }
}
