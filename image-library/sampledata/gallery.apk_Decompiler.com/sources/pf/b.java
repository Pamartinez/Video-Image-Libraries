package pf;

import L1.d;
import Tf.v;
import c0.C0086a;
import i.C0212a;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1195m;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class b {

    /* renamed from: a  reason: collision with root package name */
    public static final String f5025a = C1194l.R0(C1195m.q0('k', 'o', 't', 'l', 'i', 'n'), "", (String) null, (String) null, (Ae.b) null, 62);
    public static final LinkedHashMap b;

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        List q0 = C1195m.q0("Boolean", "Z", "Char", "C", "Byte", "B", "Short", "S", "Int", "I", "Float", "F", "Long", "J", "Double", "D");
        int l = d.l(0, q0.size() - 1, 2);
        if (l >= 0) {
            int i2 = 0;
            while (true) {
                StringBuilder sb2 = new StringBuilder();
                String str = f5025a;
                sb2.append(str);
                sb2.append('/');
                sb2.append((String) q0.get(i2));
                int i7 = i2 + 1;
                linkedHashMap.put(sb2.toString(), q0.get(i7));
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str);
                sb3.append('/');
                linkedHashMap.put(C0212a.p(sb3, (String) q0.get(i2), "Array"), "[" + ((String) q0.get(i7)));
                if (i2 == l) {
                    break;
                }
                i2 += 2;
            }
        }
        linkedHashMap.put(f5025a + "/Unit", "V");
        a(linkedHashMap, "Any", "java/lang/Object");
        a(linkedHashMap, "Nothing", "java/lang/Void");
        a(linkedHashMap, "Annotation", "java/lang/annotation/Annotation");
        for (String str2 : C1195m.q0("String", "CharSequence", "Throwable", "Cloneable", "Number", "Comparable", "Enum")) {
            a(linkedHashMap, str2, "java/lang/" + str2);
        }
        for (String str3 : C1195m.q0("Iterator", "Collection", "List", "Set", "Map", "ListIterator")) {
            a(linkedHashMap, C0212a.l("collections/", str3), "java/util/" + str3);
            a(linkedHashMap, "collections/Mutable" + str3, "java/util/" + str3);
        }
        a(linkedHashMap, "collections/Iterable", "java/lang/Iterable");
        a(linkedHashMap, "collections/MutableIterable", "java/lang/Iterable");
        a(linkedHashMap, "collections/Map.Entry", "java/util/Map$Entry");
        a(linkedHashMap, "collections/MutableMap.MutableEntry", "java/util/Map$Entry");
        for (int i8 = 0; i8 < 23; i8++) {
            String i10 = C0086a.i(i8, "Function");
            StringBuilder sb4 = new StringBuilder();
            String str4 = f5025a;
            sb4.append(str4);
            sb4.append("/jvm/functions/Function");
            sb4.append(i8);
            a(linkedHashMap, i10, sb4.toString());
            a(linkedHashMap, "reflect/KFunction" + i8, str4 + "/reflect/KFunction");
        }
        for (String str5 : C1195m.q0("Char", "Byte", "Short", "Int", "Float", "Long", "Double", "String", "Enum")) {
            a(linkedHashMap, C0212a.A(str5, ".Companion"), C0212a.q(new StringBuilder(), f5025a, "/jvm/internal/", str5, "CompanionObject"));
        }
        b = linkedHashMap;
    }

    public static final void a(LinkedHashMap linkedHashMap, String str, String str2) {
        linkedHashMap.put(f5025a + '/' + str, "L" + str2 + ';');
    }

    public static final String b(String str) {
        j.e(str, "classId");
        String str2 = (String) b.get(str);
        if (str2 != null) {
            return str2;
        }
        return "L" + v.r0(str, '.', '$') + ';';
    }
}
