package F2;

import E2.h;
import E2.l;
import E2.m;
import He.F;
import c0.C0086a;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedSet;

/* renamed from: F2.v  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0040v {
    public static void a(int i2, Object[] objArr) {
        int i7 = 0;
        while (i7 < i2) {
            if (objArr[i7] != null) {
                i7++;
            } else {
                throw new NullPointerException(C0086a.i(i7, "at index "));
            }
        }
    }

    public static void b(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException("null key in entry: null=" + obj2);
        } else if (obj2 == null) {
            throw new NullPointerException("null value in entry: " + obj + "=null");
        }
    }

    public static void c(int i2, String str) {
        if (i2 < 0) {
            throw new IllegalArgumentException(str + " cannot be negative but was: " + i2);
        }
    }

    public static Object d(int i2) {
        if (i2 < 2 || i2 > 1073741824 || Integer.highestOneBit(i2) != i2) {
            throw new IllegalArgumentException(C0086a.i(i2, "must be power of 2 between 2^1 and 2^30: "));
        } else if (i2 <= 256) {
            return new byte[i2];
        } else {
            if (i2 <= 65536) {
                return new short[i2];
            }
            return new int[i2];
        }
    }

    public static boolean e(Object obj, Map map) {
        if (map == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return map.entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    public static boolean f(Set set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (!(obj instanceof Set)) {
            return false;
        }
        Set set2 = (Set) obj;
        try {
            if (set.size() != set2.size() || !set.containsAll(set2)) {
                return false;
            }
            return true;
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    public static I0 g(Set set, l lVar) {
        if (set instanceof SortedSet) {
            SortedSet sortedSet = (SortedSet) set;
            if (!(sortedSet instanceof I0)) {
                return new I0(sortedSet, lVar);
            }
            I0 i02 = (I0) sortedSet;
            l lVar2 = i02.e;
            lVar2.getClass();
            return new I0((SortedSet) i02.d, new m(Arrays.asList(new l[]{lVar2, lVar})));
        } else if (set instanceof I0) {
            I0 i03 = (I0) set;
            l lVar3 = i03.e;
            lVar3.getClass();
            return new I0(i03.d, new m(Arrays.asList(new l[]{lVar3, lVar})));
        } else {
            set.getClass();
            return new I0(set, lVar);
        }
    }

    public static Object h(Iterable iterable, Object obj) {
        Iterator it = iterable.iterator();
        if (it.hasNext()) {
            return it.next();
        }
        return obj;
    }

    public static boolean i(Comparator comparator, Collection collection) {
        Object obj;
        comparator.getClass();
        collection.getClass();
        if (collection instanceof SortedSet) {
            obj = ((SortedSet) collection).comparator();
            if (obj == null) {
                obj = v0.e;
            }
        } else if (!(collection instanceof M0)) {
            return false;
        } else {
            obj = ((C0018g0) ((M0) collection)).g;
        }
        return comparator.equals(obj);
    }

    public static int j(Set set) {
        int i2;
        int i7 = 0;
        for (Object next : set) {
            if (next != null) {
                i2 = next.hashCode();
            } else {
                i2 = 0;
            }
            i7 = ~(~(i7 + i2));
        }
        return i7;
    }

    public static H0 k(C0010c0 c0Var, C0010c0 c0Var2) {
        F.n(c0Var, "set1");
        F.n(c0Var2, "set2");
        return new H0(c0Var, c0Var2);
    }

    public static int l(int i2, int i7, int i8) {
        return (i2 & (~i8)) | (i7 & i8);
    }

    public static ArrayList m(Iterator it) {
        ArrayList arrayList = new ArrayList();
        it.getClass();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002b, code lost:
        r9 = r6 & r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002d, code lost:
        if (r5 != -1) goto L_0x0033;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002f, code lost:
        s(r1, r9, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0032, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0033, code lost:
        r13[r5] = l(r13[r5], r9, r11);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003b, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int n(java.lang.Object r9, java.lang.Object r10, int r11, java.lang.Object r12, int[] r13, java.lang.Object[] r14, java.lang.Object[] r15) {
        /*
            int r0 = q(r9)
            r1 = r0 & r11
            int r2 = r(r1, r12)
            r3 = -1
            if (r2 != 0) goto L_0x000e
            goto L_0x0040
        L_0x000e:
            int r4 = ~r11
            r0 = r0 & r4
            r5 = r3
        L_0x0011:
            int r2 = r2 + -1
            r6 = r13[r2]
            r7 = r6 & r4
            if (r7 != r0) goto L_0x003c
            r7 = r14[r2]
            boolean r7 = D1.f.p(r9, r7)
            if (r7 == 0) goto L_0x003c
            if (r15 == 0) goto L_0x002b
            r7 = r15[r2]
            boolean r7 = D1.f.p(r10, r7)
            if (r7 == 0) goto L_0x003c
        L_0x002b:
            r9 = r6 & r11
            if (r5 != r3) goto L_0x0033
            s(r1, r9, r12)
            return r2
        L_0x0033:
            r10 = r13[r5]
            int r9 = l(r10, r9, r11)
            r13[r5] = r9
            return r2
        L_0x003c:
            r5 = r6 & r11
            if (r5 != 0) goto L_0x0041
        L_0x0040:
            return r3
        L_0x0041:
            r8 = r5
            r5 = r2
            r2 = r8
            goto L_0x0011
        */
        throw new UnsupportedOperationException("Method not decompiled: F2.C0040v.n(java.lang.Object, java.lang.Object, int, java.lang.Object, int[], java.lang.Object[], java.lang.Object[]):int");
    }

    public static void o(List list, l lVar, int i2, int i7) {
        for (int size = list.size() - 1; size > i7; size--) {
            if (lVar.apply(list.get(size))) {
                list.remove(size);
            }
        }
        for (int i8 = i7 - 1; i8 >= i2; i8--) {
            list.remove(i8);
        }
    }

    public static int p(int i2) {
        return (int) (((long) Integer.rotateLeft((int) (((long) i2) * -862048943), 15)) * 461845907);
    }

    public static int q(Object obj) {
        int i2;
        if (obj == null) {
            i2 = 0;
        } else {
            i2 = obj.hashCode();
        }
        return p(i2);
    }

    public static int r(int i2, Object obj) {
        if (obj instanceof byte[]) {
            return ((byte[]) obj)[i2] & 255;
        }
        if (obj instanceof short[]) {
            return ((short[]) obj)[i2] & 65535;
        }
        return ((int[]) obj)[i2];
    }

    public static void s(int i2, int i7, Object obj) {
        if (obj instanceof byte[]) {
            ((byte[]) obj)[i2] = (byte) i7;
        } else if (obj instanceof short[]) {
            ((short[]) obj)[i2] = (short) i7;
        } else {
            ((int[]) obj)[i2] = i7;
        }
    }

    public static AbstractList t(List list, h hVar) {
        if (list instanceof RandomAccess) {
            return new C0034o0(list, hVar);
        }
        return new p0(list, hVar);
    }
}
