package Tf;

import Ae.b;
import Gd.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1195m;
import ne.C1196n;
import ne.C1202t;
import o1.C0246a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class o extends a {
    public static String l0(String str) {
        List list;
        int i2;
        Comparable comparable;
        int i7;
        String str2;
        j.e(str, "<this>");
        g gVar = new g(str);
        if (!gVar.hasNext()) {
            list = C1202t.d;
        } else {
            Object next = gVar.next();
            if (!gVar.hasNext()) {
                list = C0246a.e0(next);
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(next);
                while (gVar.hasNext()) {
                    arrayList.add(gVar.next());
                }
                list = arrayList;
            }
        }
        Iterable iterable = list;
        ArrayList arrayList2 = new ArrayList();
        for (Object next2 : iterable) {
            if (!n.C0((String) next2)) {
                arrayList2.add(next2);
            }
        }
        ArrayList arrayList3 = new ArrayList(C1196n.w0(arrayList2, 10));
        Iterator it = arrayList2.iterator();
        while (true) {
            i2 = 0;
            if (!it.hasNext()) {
                break;
            }
            String str3 = (String) it.next();
            int length = str3.length();
            while (true) {
                if (i2 >= length) {
                    i2 = -1;
                    break;
                } else if (!B1.a.I(str3.charAt(i2))) {
                    break;
                } else {
                    i2++;
                }
            }
            if (i2 == -1) {
                i2 = str3.length();
            }
            arrayList3.add(Integer.valueOf(i2));
        }
        Iterator it2 = arrayList3.iterator();
        if (!it2.hasNext()) {
            comparable = null;
        } else {
            comparable = (Comparable) it2.next();
            while (it2.hasNext()) {
                Comparable comparable2 = (Comparable) it2.next();
                if (comparable.compareTo(comparable2) > 0) {
                    comparable = comparable2;
                }
            }
        }
        Integer num = (Integer) comparable;
        if (num != null) {
            i7 = num.intValue();
        } else {
            i7 = 0;
        }
        int length2 = str.length();
        list.size();
        int p02 = C1195m.p0(list);
        ArrayList arrayList4 = new ArrayList();
        for (Object next3 : iterable) {
            int i8 = i2 + 1;
            if (i2 >= 0) {
                String str4 = (String) next3;
                if ((i2 == 0 || i2 == p02) && n.C0(str4)) {
                    str2 = null;
                } else {
                    str2 = n.w0(i7, str4);
                }
                if (str2 != null) {
                    arrayList4.add(str2);
                }
                i2 = i8;
            } else {
                C1195m.v0();
                throw null;
            }
        }
        StringBuilder sb2 = new StringBuilder(length2);
        C1194l.Q0(arrayList4, sb2, "\n", (String) null, (String) null, (b) null, 124);
        return sb2.toString();
    }
}
