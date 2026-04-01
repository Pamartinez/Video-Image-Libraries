package pf;

import Ae.b;
import Sf.r;
import i.C0212a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import ne.C1194l;
import ne.C1195m;
import ne.C1196n;
import ne.v;
import ne.x;
import ne.z;
import nf.C1209f;
import of.h;
import of.i;
import of.j;
import rf.C1255e;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class g implements C1209f {
    public static final List g;
    public final String[] d;
    public final Set e;
    public final ArrayList f;

    static {
        String R02 = C1194l.R0(C1195m.q0('k', 'o', 't', 'l', 'i', 'n'), "", (String) null, (String) null, (b) null, 62);
        List q0 = C1195m.q0(C0212a.A(R02, "/Any"), C0212a.A(R02, "/Nothing"), C0212a.A(R02, "/Unit"), C0212a.A(R02, "/Throwable"), C0212a.A(R02, "/Number"), C0212a.A(R02, "/Byte"), C0212a.A(R02, "/Double"), C0212a.A(R02, "/Float"), C0212a.A(R02, "/Int"), C0212a.A(R02, "/Long"), C0212a.A(R02, "/Short"), C0212a.A(R02, "/Boolean"), C0212a.A(R02, "/Char"), C0212a.A(R02, "/CharSequence"), C0212a.A(R02, "/String"), C0212a.A(R02, "/Comparable"), C0212a.A(R02, "/Enum"), C0212a.A(R02, "/Array"), C0212a.A(R02, "/ByteArray"), C0212a.A(R02, "/DoubleArray"), C0212a.A(R02, "/FloatArray"), C0212a.A(R02, "/IntArray"), C0212a.A(R02, "/LongArray"), C0212a.A(R02, "/ShortArray"), C0212a.A(R02, "/BooleanArray"), C0212a.A(R02, "/CharArray"), C0212a.A(R02, "/Cloneable"), C0212a.A(R02, "/Annotation"), C0212a.A(R02, "/collections/Iterable"), C0212a.A(R02, "/collections/MutableIterable"), C0212a.A(R02, "/collections/Collection"), C0212a.A(R02, "/collections/MutableCollection"), C0212a.A(R02, "/collections/List"), C0212a.A(R02, "/collections/MutableList"), C0212a.A(R02, "/collections/Set"), C0212a.A(R02, "/collections/MutableSet"), C0212a.A(R02, "/collections/Map"), C0212a.A(R02, "/collections/MutableMap"), C0212a.A(R02, "/collections/Map.Entry"), C0212a.A(R02, "/collections/MutableMap.MutableEntry"), C0212a.A(R02, "/collections/Iterator"), C0212a.A(R02, "/collections/MutableIterator"), C0212a.A(R02, "/collections/ListIterator"), C0212a.A(R02, "/collections/MutableListIterator"));
        g = q0;
        r q12 = C1194l.q1(q0);
        int Z = z.Z(C1196n.w0(q12, 10));
        if (Z < 16) {
            Z = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(Z);
        Iterator it = q12.iterator();
        while (true) {
            Sf.b bVar = (Sf.b) it;
            if (bVar.e.hasNext()) {
                x xVar = (x) bVar.next();
                linkedHashMap.put((String) xVar.b, Integer.valueOf(xVar.f4950a));
            } else {
                return;
            }
        }
    }

    public g(j jVar, String[] strArr) {
        Set set;
        kotlin.jvm.internal.j.e(strArr, "strings");
        List list = jVar.f;
        if (list.isEmpty()) {
            set = v.d;
        } else {
            set = C1194l.p1(list);
        }
        List<i> list2 = jVar.e;
        kotlin.jvm.internal.j.d(list2, "getRecordList(...)");
        ArrayList arrayList = new ArrayList();
        arrayList.ensureCapacity(list2.size());
        for (i iVar : list2) {
            int i2 = iVar.f;
            for (int i7 = 0; i7 < i2; i7++) {
                arrayList.add(iVar);
            }
        }
        arrayList.trimToSize();
        this.d = strArr;
        this.e = set;
        this.f = arrayList;
    }

    public final boolean F(int i2) {
        return this.e.contains(Integer.valueOf(i2));
    }

    public final String X(int i2) {
        return getString(i2);
    }

    public final String getString(int i2) {
        String str;
        i iVar = (i) this.f.get(i2);
        int i7 = iVar.e;
        if ((i7 & 4) == 4) {
            Object obj = iVar.f5001h;
            if (obj instanceof String) {
                str = (String) obj;
            } else {
                C1255e eVar = (C1255e) obj;
                String A10 = eVar.A();
                if (eVar.u()) {
                    iVar.f5001h = A10;
                }
                str = A10;
            }
        } else {
            if ((i7 & 2) == 2) {
                List list = g;
                int size = list.size();
                int i8 = iVar.g;
                if (i8 >= 0 && i8 < size) {
                    str = (String) list.get(i8);
                }
            }
            str = this.d[i2];
        }
        if (iVar.f5003j.size() >= 2) {
            List list2 = iVar.f5003j;
            kotlin.jvm.internal.j.b(list2);
            Integer num = (Integer) list2.get(0);
            Integer num2 = (Integer) list2.get(1);
            if (num.intValue() >= 0 && num.intValue() <= num2.intValue() && num2.intValue() <= str.length()) {
                str = str.substring(num.intValue(), num2.intValue());
                kotlin.jvm.internal.j.d(str, "substring(...)");
            }
        }
        if (iVar.l.size() >= 2) {
            List list3 = iVar.l;
            kotlin.jvm.internal.j.b(list3);
            kotlin.jvm.internal.j.b(str);
            str = Tf.v.r0(str, (char) ((Integer) list3.get(0)).intValue(), (char) ((Integer) list3.get(1)).intValue());
        }
        h hVar = iVar.f5002i;
        if (hVar == null) {
            hVar = h.NONE;
        }
        int i10 = h.f5028a[hVar.ordinal()];
        if (i10 != 1) {
            if (i10 == 2) {
                kotlin.jvm.internal.j.b(str);
                str = Tf.v.r0(str, '$', '.');
            } else if (i10 == 3) {
                if (str.length() >= 2) {
                    str = str.substring(1, str.length() - 1);
                    kotlin.jvm.internal.j.d(str, "substring(...)");
                }
                str = Tf.v.r0(str, '$', '.');
            } else {
                throw new RuntimeException();
            }
        }
        kotlin.jvm.internal.j.b(str);
        return str;
    }
}
