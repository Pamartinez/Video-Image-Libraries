package ne;

import Ae.b;
import Gd.a;
import Ge.c;
import Ge.e;
import Sf.o;
import Sf.q;
import Sf.r;
import c0.C0086a;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import i.C0212a;
import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.Set;
import kotlin.jvm.internal.j;
import me.i;
import o1.C0246a;

/* renamed from: ne.l  reason: case insensitive filesystem */
public abstract class C1194l extends C1200r {
    public static final int D0(int i2, List list) {
        if (i2 >= 0 && i2 <= C1195m.p0(list)) {
            return C1195m.p0(list) - i2;
        }
        StringBuilder o2 = C0086a.o(i2, "Element index ", " must be in range [");
        o2.append(new c(0, C1195m.p0(list), 1));
        o2.append("].");
        throw new IndexOutOfBoundsException(o2.toString());
    }

    public static final int E0(int i2, List list) {
        if (i2 >= 0 && i2 <= list.size()) {
            return list.size() - i2;
        }
        StringBuilder o2 = C0086a.o(i2, "Position index ", " must be in range [");
        o2.append(new c(0, list.size(), 1));
        o2.append("].");
        throw new IndexOutOfBoundsException(o2.toString());
    }

    public static o F0(Iterable iterable) {
        j.e(iterable, "<this>");
        return new o(4, iterable);
    }

    public static boolean G0(Iterable iterable, Object obj) {
        int i2;
        j.e(iterable, "<this>");
        if (iterable instanceof Collection) {
            return ((Collection) iterable).contains(obj);
        }
        if (!(iterable instanceof List)) {
            Iterator it = iterable.iterator();
            int i7 = 0;
            while (true) {
                if (!it.hasNext()) {
                    i2 = -1;
                    break;
                }
                Object next = it.next();
                if (i7 < 0) {
                    C1195m.v0();
                    throw null;
                } else if (j.a(obj, next)) {
                    i2 = i7;
                    break;
                } else {
                    i7++;
                }
            }
        } else {
            i2 = ((List) iterable).indexOf(obj);
        }
        if (i2 >= 0) {
            return true;
        }
        return false;
    }

    public static List H0(Iterable iterable) {
        j.e(iterable, "<this>");
        return k1(o1(iterable));
    }

    public static List I0(Iterable iterable) {
        ArrayList arrayList;
        if (iterable instanceof Collection) {
            int size = ((Collection) iterable).size() - 1;
            if (size <= 0) {
                return C1202t.d;
            }
            if (size == 1) {
                return C0246a.e0(S0(iterable));
            }
            arrayList = new ArrayList(size);
            if (iterable instanceof List) {
                if (iterable instanceof RandomAccess) {
                    List list = (List) iterable;
                    int size2 = list.size();
                    for (int i2 = 1; i2 < size2; i2++) {
                        arrayList.add(list.get(i2));
                    }
                } else {
                    ListIterator listIterator = ((List) iterable).listIterator(1);
                    while (listIterator.hasNext()) {
                        arrayList.add(listIterator.next());
                    }
                }
                return arrayList;
            }
        } else {
            arrayList = new ArrayList();
        }
        int i7 = 0;
        for (Object next : iterable) {
            if (i7 >= 1) {
                arrayList.add(next);
            } else {
                i7++;
            }
        }
        return C1195m.t0(arrayList);
    }

    public static List J0(List list) {
        j.e(list, "<this>");
        Iterable iterable = list;
        int size = list.size() - 1;
        if (size < 0) {
            size = 0;
        }
        return h1(iterable, size);
    }

    public static Object K0(Iterable iterable) {
        j.e(iterable, "<this>");
        if (iterable instanceof List) {
            return L0((List) iterable);
        }
        Iterator it = iterable.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        throw new NoSuchElementException("Collection is empty.");
    }

    public static Object L0(List list) {
        j.e(list, "<this>");
        if (!list.isEmpty()) {
            return list.get(0);
        }
        throw new NoSuchElementException("List is empty.");
    }

    public static Object M0(Iterable iterable) {
        j.e(iterable, "<this>");
        if (iterable instanceof List) {
            List list = (List) iterable;
            if (list.isEmpty()) {
                return null;
            }
            return list.get(0);
        }
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        return it.next();
    }

    public static Object N0(List list) {
        j.e(list, "<this>");
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public static Object O0(int i2, List list) {
        j.e(list, "<this>");
        if (i2 < 0 || i2 >= list.size()) {
            return null;
        }
        return list.get(i2);
    }

    public static final void P0(Iterable iterable, Appendable appendable, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, CharSequence charSequence4, b bVar) {
        j.e(iterable, "<this>");
        j.e(appendable, "buffer");
        j.e(charSequence2, "prefix");
        j.e(charSequence3, "postfix");
        appendable.append(charSequence2);
        int i2 = 0;
        for (Object next : iterable) {
            i2++;
            if (i2 > 1) {
                appendable.append(charSequence);
            }
            a.b(appendable, next, bVar);
        }
        appendable.append(charSequence3);
    }

    public static /* synthetic */ void Q0(Iterable iterable, StringBuilder sb2, String str, String str2, String str3, b bVar, int i2) {
        if ((i2 & 4) != 0) {
            str2 = "";
        }
        if ((i2 & 8) != 0) {
            str3 = "";
        }
        if ((i2 & 64) != 0) {
            bVar = null;
        }
        P0(iterable, sb2, str, str2, str3, "...", bVar);
    }

    public static String R0(Iterable iterable, String str, String str2, String str3, b bVar, int i2) {
        String str4;
        String str5;
        if ((i2 & 1) != 0) {
            str = ArcCommonLog.TAG_COMMA;
        }
        String str6 = str;
        if ((i2 & 2) != 0) {
            str4 = "";
        } else {
            str4 = str2;
        }
        if ((i2 & 4) != 0) {
            str5 = "";
        } else {
            str5 = str3;
        }
        if ((i2 & 32) != 0) {
            bVar = null;
        }
        j.e(iterable, "<this>");
        j.e(str6, "separator");
        j.e(str4, "prefix");
        StringBuilder sb2 = new StringBuilder();
        P0(iterable, sb2, str6, str4, str5, "...", bVar);
        return sb2.toString();
    }

    public static Object S0(Iterable iterable) {
        if (iterable instanceof List) {
            return T0((List) iterable);
        }
        Iterator it = iterable.iterator();
        if (it.hasNext()) {
            Object next = it.next();
            while (it.hasNext()) {
                next = it.next();
            }
            return next;
        }
        throw new NoSuchElementException("Collection is empty.");
    }

    public static Object T0(List list) {
        j.e(list, "<this>");
        if (!list.isEmpty()) {
            return list.get(C1195m.p0(list));
        }
        throw new NoSuchElementException("List is empty.");
    }

    public static Object U0(List list) {
        j.e(list, "<this>");
        if (list.isEmpty()) {
            return null;
        }
        return C0086a.f(1, list);
    }

    public static ArrayList V0(Iterable iterable, Iterable iterable2) {
        j.e(iterable, "<this>");
        if (iterable instanceof Collection) {
            return X0(iterable2, (Collection) iterable);
        }
        ArrayList arrayList = new ArrayList();
        C1200r.A0(iterable, arrayList);
        C1200r.A0(iterable2, arrayList);
        return arrayList;
    }

    public static ArrayList W0(Iterable iterable, Object obj) {
        if (iterable instanceof Collection) {
            return Y0((Collection) iterable, obj);
        }
        ArrayList arrayList = new ArrayList();
        C1200r.A0(iterable, arrayList);
        arrayList.add(obj);
        return arrayList;
    }

    public static ArrayList X0(Iterable iterable, Collection collection) {
        j.e(collection, "<this>");
        j.e(iterable, "elements");
        if (iterable instanceof Collection) {
            Collection collection2 = (Collection) iterable;
            ArrayList arrayList = new ArrayList(collection2.size() + collection.size());
            arrayList.addAll(collection);
            arrayList.addAll(collection2);
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList(collection);
        C1200r.A0(iterable, arrayList2);
        return arrayList2;
    }

    public static ArrayList Y0(Collection collection, Object obj) {
        ArrayList arrayList = new ArrayList(collection.size() + 1);
        arrayList.addAll(collection);
        arrayList.add(obj);
        return arrayList;
    }

    public static List Z0(Iterable iterable) {
        j.e(iterable, "<this>");
        if ((iterable instanceof Collection) && ((Collection) iterable).size() <= 1) {
            return k1(iterable);
        }
        List n12 = n1(iterable);
        Collections.reverse(n12);
        return n12;
    }

    public static Object a1(Iterable iterable) {
        j.e(iterable, "<this>");
        if (iterable instanceof List) {
            return b1((List) iterable);
        }
        Iterator it = iterable.iterator();
        if (it.hasNext()) {
            Object next = it.next();
            if (!it.hasNext()) {
                return next;
            }
            throw new IllegalArgumentException("Collection has more than one element.");
        }
        throw new NoSuchElementException("Collection is empty.");
    }

    public static Object b1(List list) {
        j.e(list, "<this>");
        int size = list.size();
        if (size == 0) {
            throw new NoSuchElementException("List is empty.");
        } else if (size == 1) {
            return list.get(0);
        } else {
            throw new IllegalArgumentException("List has more than one element.");
        }
    }

    public static Object c1(Iterable iterable) {
        j.e(iterable, "<this>");
        if (iterable instanceof List) {
            List list = (List) iterable;
            if (list.size() == 1) {
                return list.get(0);
            }
            return null;
        }
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            return null;
        }
        Object next = it.next();
        if (it.hasNext()) {
            return null;
        }
        return next;
    }

    public static Object d1(List list) {
        j.e(list, "<this>");
        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    public static List e1(List list, e eVar) {
        j.e(list, "<this>");
        j.e(eVar, "indices");
        if (eVar.isEmpty()) {
            return C1202t.d;
        }
        return k1(list.subList(eVar.d, eVar.e + 1));
    }

    public static List f1(AbstractList abstractList) {
        j.e(abstractList, "<this>");
        if (abstractList.size() <= 1) {
            return k1(abstractList);
        }
        Object[] array = abstractList.toArray(new Comparable[0]);
        Comparable[] comparableArr = (Comparable[]) array;
        j.e(comparableArr, "<this>");
        if (comparableArr.length > 1) {
            Arrays.sort(comparableArr);
        }
        return C1192j.a0(array);
    }

    public static List g1(Iterable iterable, Comparator comparator) {
        j.e(iterable, "<this>");
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            if (collection.size() <= 1) {
                return k1(iterable);
            }
            Object[] array = collection.toArray(new Object[0]);
            j.e(array, "<this>");
            if (array.length > 1) {
                Arrays.sort(array, comparator);
            }
            return C1192j.a0(array);
        }
        List n12 = n1(iterable);
        C1199q.z0(n12, comparator);
        return n12;
    }

    public static List h1(Iterable iterable, int i2) {
        j.e(iterable, "<this>");
        if (i2 < 0) {
            throw new IllegalArgumentException(C0212a.j(i2, "Requested element count ", " is less than zero.").toString());
        } else if (i2 == 0) {
            return C1202t.d;
        } else {
            if (iterable instanceof Collection) {
                if (i2 >= ((Collection) iterable).size()) {
                    return k1(iterable);
                }
                if (i2 == 1) {
                    return C0246a.e0(K0(iterable));
                }
            }
            ArrayList arrayList = new ArrayList(i2);
            int i7 = 0;
            for (Object add : iterable) {
                arrayList.add(add);
                i7++;
                if (i7 == i2) {
                    break;
                }
            }
            return C1195m.t0(arrayList);
        }
    }

    public static void i1(Iterable iterable, AbstractCollection abstractCollection) {
        j.e(iterable, "<this>");
        for (Object add : iterable) {
            abstractCollection.add(add);
        }
    }

    public static int[] j1(Collection collection) {
        j.e(collection, "<this>");
        int[] iArr = new int[collection.size()];
        Iterator it = collection.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            iArr[i2] = ((Number) it.next()).intValue();
            i2++;
        }
        return iArr;
    }

    public static List k1(Iterable iterable) {
        Object obj;
        j.e(iterable, "<this>");
        if (!(iterable instanceof Collection)) {
            return C1195m.t0(n1(iterable));
        }
        Collection collection = (Collection) iterable;
        int size = collection.size();
        if (size == 0) {
            return C1202t.d;
        }
        if (size != 1) {
            return m1(collection);
        }
        if (iterable instanceof List) {
            obj = ((List) iterable).get(0);
        } else {
            obj = collection.iterator().next();
        }
        return C0246a.e0(obj);
    }

    public static long[] l1(ArrayList arrayList) {
        long[] jArr = new long[arrayList.size()];
        Iterator it = arrayList.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            jArr[i2] = ((Number) it.next()).longValue();
            i2++;
        }
        return jArr;
    }

    public static ArrayList m1(Collection collection) {
        j.e(collection, "<this>");
        return new ArrayList(collection);
    }

    public static final List n1(Iterable iterable) {
        j.e(iterable, "<this>");
        if (iterable instanceof Collection) {
            return m1((Collection) iterable);
        }
        ArrayList arrayList = new ArrayList();
        i1(iterable, arrayList);
        return arrayList;
    }

    public static Set o1(Iterable iterable) {
        j.e(iterable, "<this>");
        if (iterable instanceof Collection) {
            return new LinkedHashSet((Collection) iterable);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        i1(iterable, linkedHashSet);
        return linkedHashSet;
    }

    public static Set p1(Iterable iterable) {
        Object obj;
        j.e(iterable, "<this>");
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            int size = collection.size();
            if (size != 0) {
                if (size != 1) {
                    LinkedHashSet linkedHashSet = new LinkedHashSet(z.Z(collection.size()));
                    i1(iterable, linkedHashSet);
                    return linkedHashSet;
                }
                if (iterable instanceof List) {
                    obj = ((List) iterable).get(0);
                } else {
                    obj = collection.iterator().next();
                }
                return B1.a.S(obj);
            }
        } else {
            LinkedHashSet linkedHashSet2 = new LinkedHashSet();
            i1(iterable, linkedHashSet2);
            int size2 = linkedHashSet2.size();
            if (size2 != 0) {
                if (size2 != 1) {
                    return linkedHashSet2;
                }
                return B1.a.S(linkedHashSet2.iterator().next());
            }
        }
        return v.d;
    }

    public static r q1(Iterable iterable) {
        j.e(iterable, "<this>");
        return new r(3, new q(22, iterable));
    }

    public static ArrayList r1(Iterable iterable, Iterable iterable2) {
        j.e(iterable, "<this>");
        j.e(iterable2, "other");
        Iterator it = iterable.iterator();
        Iterator it2 = iterable2.iterator();
        ArrayList arrayList = new ArrayList(Math.min(C1196n.w0(iterable, 10), C1196n.w0(iterable2, 10)));
        while (it.hasNext() && it2.hasNext()) {
            arrayList.add(new i(it.next(), it2.next()));
        }
        return arrayList;
    }
}
