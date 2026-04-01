package com.google.android.appfunctions.schema.internal.dependencies;

import I0.a;
import java.util.Arrays;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class N {

    /* renamed from: a  reason: collision with root package name */
    public static final Class f1211a;
    public static final P b;

    /* renamed from: c  reason: collision with root package name */
    public static final P f1212c = new Object();

    /* JADX WARNING: type inference failed for: r0v3, types: [com.google.android.appfunctions.schema.internal.dependencies.P, java.lang.Object] */
    static {
        Class<?> cls;
        Class<?> cls2;
        Class cls3 = b0.f1220a;
        P p6 = null;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessage");
        } catch (Throwable unused) {
            cls = null;
        }
        f1211a = cls;
        try {
            cls2 = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused2) {
            cls2 = null;
        }
        if (cls2 != null) {
            try {
                p6 = (P) cls2.getConstructor((Class[]) null).newInstance((Object[]) null);
            } catch (Throwable unused3) {
            }
        }
        b = p6;
    }

    public static int a(int i2, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (h0.l0(i2 << 3) + 4) * size;
    }

    public static int b(int i2, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (h0.l0(i2 << 3) + 8) * size;
    }

    public static boolean c(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || !obj.equals(obj2)) {
            return false;
        }
        return true;
    }

    public static void d(P p6, Object obj, Object obj2) {
        p6.getClass();
        C0102l lVar = (C0102l) obj;
        O o2 = lVar.unknownFields;
        O o3 = ((C0102l) obj2).unknownFields;
        O o9 = O.f;
        if (!o9.equals(o3)) {
            if (o9.equals(o2)) {
                int i2 = o2.f1213a + o3.f1213a;
                int[] copyOf = Arrays.copyOf(o2.b, i2);
                System.arraycopy(o3.b, 0, copyOf, o2.f1213a, o3.f1213a);
                Object[] copyOf2 = Arrays.copyOf(o2.f1214c, i2);
                System.arraycopy(o3.f1214c, 0, copyOf2, o2.f1213a, o3.f1213a);
                o2 = new O(i2, copyOf, copyOf2, true);
            } else {
                o2.getClass();
                if (!o3.equals(o9)) {
                    if (o2.e) {
                        int i7 = o2.f1213a;
                        int i8 = o3.f1213a + i7;
                        int[] iArr = o2.b;
                        if (i8 > iArr.length) {
                            int i10 = (i7 / 2) + i7;
                            if (i10 < i8) {
                                i10 = i8;
                            }
                            if (i10 < 8) {
                                i10 = 8;
                            }
                            o2.b = Arrays.copyOf(iArr, i10);
                            o2.f1214c = Arrays.copyOf(o2.f1214c, i10);
                        }
                        System.arraycopy(o3.b, 0, o2.b, o2.f1213a, o3.f1213a);
                        System.arraycopy(o3.f1214c, 0, o2.f1214c, o2.f1213a, o3.f1213a);
                        o2.f1213a = i8;
                    } else {
                        throw new UnsupportedOperationException();
                    }
                }
            }
        }
        lVar.unknownFields = o2;
    }

    public static void e(int i2, List list, C0114y yVar, boolean z) {
        if (list != null && !list.isEmpty()) {
            h0 h0Var = (h0) yVar.f1229a;
            if (!(list instanceof C0091a)) {
                int i7 = 0;
                if (z) {
                    h0Var.Z(i2, 2);
                    int i8 = 0;
                    for (int i10 = 0; i10 < list.size(); i10++) {
                        ((Double) list.get(i10)).getClass();
                        i8 += 8;
                    }
                    h0Var.h0(i8);
                    while (i7 < list.size()) {
                        h0Var.k0(Double.doubleToRawLongBits(((Double) list.get(i7)).doubleValue()));
                        i7++;
                    }
                    return;
                }
                while (i7 < list.size()) {
                    h0Var.e0(i2, Double.doubleToRawLongBits(((Double) list.get(i7)).doubleValue()));
                    i7++;
                }
                return;
            }
            throw new ClassCastException();
        }
    }

    public static void f(int i2, List list, C0114y yVar, boolean z) {
        if (list != null && !list.isEmpty()) {
            h0 h0Var = (h0) yVar.f1229a;
            if (!(list instanceof C0097g)) {
                int i7 = 0;
                if (z) {
                    h0Var.Z(i2, 2);
                    int i8 = 0;
                    for (int i10 = 0; i10 < list.size(); i10++) {
                        ((Float) list.get(i10)).getClass();
                        i8 += 4;
                    }
                    h0Var.h0(i8);
                    while (i7 < list.size()) {
                        h0Var.i0(Float.floatToRawIntBits(((Float) list.get(i7)).floatValue()));
                        i7++;
                    }
                    return;
                }
                while (i7 < list.size()) {
                    h0Var.b0(i2, Float.floatToRawIntBits(((Float) list.get(i7)).floatValue()));
                    i7++;
                }
                return;
            }
            throw new ClassCastException();
        }
    }

    public static void g(int i2, List list, C0114y yVar, boolean z) {
        if (list != null && !list.isEmpty()) {
            h0 h0Var = (h0) yVar.f1229a;
            if (!(list instanceof C0112w)) {
                int i7 = 0;
                if (z) {
                    h0Var.Z(i2, 2);
                    int i8 = 0;
                    for (int i10 = 0; i10 < list.size(); i10++) {
                        i8 += h0.m0(((Long) list.get(i10)).longValue());
                    }
                    h0Var.h0(i8);
                    while (i7 < list.size()) {
                        h0Var.j0(((Long) list.get(i7)).longValue());
                        i7++;
                    }
                    return;
                }
                while (i7 < list.size()) {
                    h0Var.c0(i2, ((Long) list.get(i7)).longValue());
                    i7++;
                }
                return;
            }
            throw new ClassCastException();
        }
    }

    public static void h(int i2, List list, C0114y yVar, boolean z) {
        if (list != null && !list.isEmpty()) {
            h0 h0Var = (h0) yVar.f1229a;
            if (!(list instanceof C0112w)) {
                int i7 = 0;
                if (z) {
                    h0Var.Z(i2, 2);
                    int i8 = 0;
                    for (int i10 = 0; i10 < list.size(); i10++) {
                        i8 += h0.m0(((Long) list.get(i10)).longValue());
                    }
                    h0Var.h0(i8);
                    while (i7 < list.size()) {
                        h0Var.j0(((Long) list.get(i7)).longValue());
                        i7++;
                    }
                    return;
                }
                while (i7 < list.size()) {
                    h0Var.c0(i2, ((Long) list.get(i7)).longValue());
                    i7++;
                }
                return;
            }
            throw new ClassCastException();
        }
    }

    public static void i(int i2, List list, C0114y yVar, boolean z) {
        if (list != null && !list.isEmpty()) {
            h0 h0Var = (h0) yVar.f1229a;
            if (!(list instanceof C0112w)) {
                int i7 = 0;
                if (z) {
                    h0Var.Z(i2, 2);
                    int i8 = 0;
                    for (int i10 = 0; i10 < list.size(); i10++) {
                        long longValue = ((Long) list.get(i10)).longValue();
                        i8 += h0.m0((longValue >> 63) ^ (longValue + longValue));
                    }
                    h0Var.h0(i8);
                    while (i7 < list.size()) {
                        long longValue2 = ((Long) list.get(i7)).longValue();
                        h0Var.j0((longValue2 >> 63) ^ (longValue2 + longValue2));
                        i7++;
                    }
                    return;
                }
                while (i7 < list.size()) {
                    long longValue3 = ((Long) list.get(i7)).longValue();
                    h0Var.c0(i2, (longValue3 >> 63) ^ (longValue3 + longValue3));
                    i7++;
                }
                return;
            }
            throw new ClassCastException();
        }
    }

    public static void j(int i2, List list, C0114y yVar, boolean z) {
        if (list != null && !list.isEmpty()) {
            h0 h0Var = (h0) yVar.f1229a;
            if (!(list instanceof C0112w)) {
                int i7 = 0;
                if (z) {
                    h0Var.Z(i2, 2);
                    int i8 = 0;
                    for (int i10 = 0; i10 < list.size(); i10++) {
                        ((Long) list.get(i10)).getClass();
                        i8 += 8;
                    }
                    h0Var.h0(i8);
                    while (i7 < list.size()) {
                        h0Var.k0(((Long) list.get(i7)).longValue());
                        i7++;
                    }
                    return;
                }
                while (i7 < list.size()) {
                    h0Var.e0(i2, ((Long) list.get(i7)).longValue());
                    i7++;
                }
                return;
            }
            throw new ClassCastException();
        }
    }

    public static void k(int i2, List list, C0114y yVar, boolean z) {
        if (list != null && !list.isEmpty()) {
            h0 h0Var = (h0) yVar.f1229a;
            if (!(list instanceof C0112w)) {
                int i7 = 0;
                if (z) {
                    h0Var.Z(i2, 2);
                    int i8 = 0;
                    for (int i10 = 0; i10 < list.size(); i10++) {
                        ((Long) list.get(i10)).getClass();
                        i8 += 8;
                    }
                    h0Var.h0(i8);
                    while (i7 < list.size()) {
                        h0Var.k0(((Long) list.get(i7)).longValue());
                        i7++;
                    }
                    return;
                }
                while (i7 < list.size()) {
                    h0Var.e0(i2, ((Long) list.get(i7)).longValue());
                    i7++;
                }
                return;
            }
            throw new ClassCastException();
        }
    }

    public static void l(int i2, List list, C0114y yVar, boolean z) {
        if (list != null && !list.isEmpty()) {
            h0 h0Var = (h0) yVar.f1229a;
            if (!(list instanceof C0103m)) {
                int i7 = 0;
                if (z) {
                    h0Var.Z(i2, 2);
                    int i8 = 0;
                    for (int i10 = 0; i10 < list.size(); i10++) {
                        i8 += h0.m0((long) ((Integer) list.get(i10)).intValue());
                    }
                    h0Var.h0(i8);
                    while (i7 < list.size()) {
                        int intValue = ((Integer) list.get(i7)).intValue();
                        if (intValue >= 0) {
                            h0Var.h0(intValue);
                        } else {
                            h0Var.j0((long) intValue);
                        }
                        i7++;
                    }
                    return;
                }
                while (i7 < list.size()) {
                    int intValue2 = ((Integer) list.get(i7)).intValue();
                    h0Var.h0(i2 << 3);
                    if (intValue2 >= 0) {
                        h0Var.h0(intValue2);
                    } else {
                        h0Var.j0((long) intValue2);
                    }
                    i7++;
                }
                return;
            }
            throw new ClassCastException();
        }
    }

    public static void m(int i2, List list, C0114y yVar, boolean z) {
        if (list != null && !list.isEmpty()) {
            h0 h0Var = (h0) yVar.f1229a;
            if (!(list instanceof C0103m)) {
                int i7 = 0;
                if (z) {
                    h0Var.Z(i2, 2);
                    int i8 = 0;
                    for (int i10 = 0; i10 < list.size(); i10++) {
                        i8 += h0.l0(((Integer) list.get(i10)).intValue());
                    }
                    h0Var.h0(i8);
                    while (i7 < list.size()) {
                        h0Var.h0(((Integer) list.get(i7)).intValue());
                        i7++;
                    }
                    return;
                }
                while (i7 < list.size()) {
                    h0Var.a0(i2, ((Integer) list.get(i7)).intValue());
                    i7++;
                }
                return;
            }
            throw new ClassCastException();
        }
    }

    public static void n(int i2, List list, C0114y yVar, boolean z) {
        if (list != null && !list.isEmpty()) {
            h0 h0Var = (h0) yVar.f1229a;
            if (!(list instanceof C0103m)) {
                int i7 = 0;
                if (z) {
                    h0Var.Z(i2, 2);
                    int i8 = 0;
                    for (int i10 = 0; i10 < list.size(); i10++) {
                        int intValue = ((Integer) list.get(i10)).intValue();
                        i8 += h0.l0((intValue >> 31) ^ (intValue + intValue));
                    }
                    h0Var.h0(i8);
                    while (i7 < list.size()) {
                        int intValue2 = ((Integer) list.get(i7)).intValue();
                        h0Var.h0((intValue2 >> 31) ^ (intValue2 + intValue2));
                        i7++;
                    }
                    return;
                }
                while (i7 < list.size()) {
                    int intValue3 = ((Integer) list.get(i7)).intValue();
                    h0Var.a0(i2, (intValue3 >> 31) ^ (intValue3 + intValue3));
                    i7++;
                }
                return;
            }
            throw new ClassCastException();
        }
    }

    public static void o(int i2, List list, C0114y yVar, boolean z) {
        if (list != null && !list.isEmpty()) {
            h0 h0Var = (h0) yVar.f1229a;
            if (!(list instanceof C0103m)) {
                int i7 = 0;
                if (z) {
                    h0Var.Z(i2, 2);
                    int i8 = 0;
                    for (int i10 = 0; i10 < list.size(); i10++) {
                        ((Integer) list.get(i10)).getClass();
                        i8 += 4;
                    }
                    h0Var.h0(i8);
                    while (i7 < list.size()) {
                        h0Var.i0(((Integer) list.get(i7)).intValue());
                        i7++;
                    }
                    return;
                }
                while (i7 < list.size()) {
                    h0Var.b0(i2, ((Integer) list.get(i7)).intValue());
                    i7++;
                }
                return;
            }
            throw new ClassCastException();
        }
    }

    public static void p(int i2, List list, C0114y yVar, boolean z) {
        if (list != null && !list.isEmpty()) {
            h0 h0Var = (h0) yVar.f1229a;
            if (!(list instanceof C0103m)) {
                int i7 = 0;
                if (z) {
                    h0Var.Z(i2, 2);
                    int i8 = 0;
                    for (int i10 = 0; i10 < list.size(); i10++) {
                        ((Integer) list.get(i10)).getClass();
                        i8 += 4;
                    }
                    h0Var.h0(i8);
                    while (i7 < list.size()) {
                        h0Var.i0(((Integer) list.get(i7)).intValue());
                        i7++;
                    }
                    return;
                }
                while (i7 < list.size()) {
                    h0Var.b0(i2, ((Integer) list.get(i7)).intValue());
                    i7++;
                }
                return;
            }
            throw new ClassCastException();
        }
    }

    public static void q(int i2, List list, C0114y yVar, boolean z) {
        if (list != null && !list.isEmpty()) {
            h0 h0Var = (h0) yVar.f1229a;
            if (!(list instanceof C0103m)) {
                int i7 = 0;
                if (z) {
                    h0Var.Z(i2, 2);
                    int i8 = 0;
                    for (int i10 = 0; i10 < list.size(); i10++) {
                        i8 += h0.m0((long) ((Integer) list.get(i10)).intValue());
                    }
                    h0Var.h0(i8);
                    while (i7 < list.size()) {
                        int intValue = ((Integer) list.get(i7)).intValue();
                        if (intValue >= 0) {
                            h0Var.h0(intValue);
                        } else {
                            h0Var.j0((long) intValue);
                        }
                        i7++;
                    }
                    return;
                }
                while (i7 < list.size()) {
                    int intValue2 = ((Integer) list.get(i7)).intValue();
                    h0Var.h0(i2 << 3);
                    if (intValue2 >= 0) {
                        h0Var.h0(intValue2);
                    } else {
                        h0Var.j0((long) intValue2);
                    }
                    i7++;
                }
                return;
            }
            throw new ClassCastException();
        }
    }

    public static void r(int i2, List list, C0114y yVar, boolean z) {
        if (list != null && !list.isEmpty()) {
            h0 h0Var = (h0) yVar.f1229a;
            if (!(list instanceof c0)) {
                int i7 = 0;
                if (z) {
                    h0Var.Z(i2, 2);
                    int i8 = 0;
                    for (int i10 = 0; i10 < list.size(); i10++) {
                        ((Boolean) list.get(i10)).getClass();
                        i8++;
                    }
                    h0Var.h0(i8);
                    while (i7 < list.size()) {
                        byte booleanValue = ((Boolean) list.get(i7)).booleanValue();
                        int i11 = h0Var.g;
                        try {
                            int i12 = i11 + 1;
                            try {
                                h0Var.e[i11] = booleanValue;
                                h0Var.g = i12;
                                i7++;
                            } catch (IndexOutOfBoundsException e) {
                                e = e;
                                i11 = i12;
                                throw new a((long) i11, (long) h0Var.f, 1, e);
                            }
                        } catch (IndexOutOfBoundsException e7) {
                            e = e7;
                            throw new a((long) i11, (long) h0Var.f, 1, e);
                        }
                    }
                    return;
                }
                while (i7 < list.size()) {
                    byte booleanValue2 = ((Boolean) list.get(i7)).booleanValue();
                    h0Var.h0(i2 << 3);
                    int i13 = h0Var.g;
                    try {
                        int i14 = i13 + 1;
                        try {
                            h0Var.e[i13] = booleanValue2;
                            h0Var.g = i14;
                            i7++;
                        } catch (IndexOutOfBoundsException e8) {
                            e = e8;
                            i13 = i14;
                            throw new a((long) i13, (long) h0Var.f, 1, e);
                        }
                    } catch (IndexOutOfBoundsException e9) {
                        e = e9;
                        throw new a((long) i13, (long) h0Var.f, 1, e);
                    }
                }
                return;
            }
            throw new ClassCastException();
        }
    }

    public static int s(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof C0112w) {
            C0112w wVar = (C0112w) list;
            if (size <= 0) {
                return 0;
            }
            throw null;
        }
        int i2 = 0;
        for (int i7 = 0; i7 < size; i7++) {
            i2 += h0.m0(((Long) list.get(i7)).longValue());
        }
        return i2;
    }

    public static int t(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof C0112w) {
            C0112w wVar = (C0112w) list;
            if (size <= 0) {
                return 0;
            }
            throw null;
        }
        int i2 = 0;
        for (int i7 = 0; i7 < size; i7++) {
            i2 += h0.m0(((Long) list.get(i7)).longValue());
        }
        return i2;
    }

    public static int u(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof C0112w) {
            C0112w wVar = (C0112w) list;
            if (size <= 0) {
                return 0;
            }
            throw null;
        }
        int i2 = 0;
        for (int i7 = 0; i7 < size; i7++) {
            long longValue = ((Long) list.get(i7)).longValue();
            i2 += h0.m0((longValue >> 63) ^ (longValue + longValue));
        }
        return i2;
    }

    public static int v(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof C0103m) {
            C0103m mVar = (C0103m) list;
            if (size <= 0) {
                return 0;
            }
            throw null;
        }
        int i2 = 0;
        for (int i7 = 0; i7 < size; i7++) {
            i2 += h0.m0((long) ((Integer) list.get(i7)).intValue());
        }
        return i2;
    }

    public static int w(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof C0103m) {
            C0103m mVar = (C0103m) list;
            if (size <= 0) {
                return 0;
            }
            throw null;
        }
        int i2 = 0;
        for (int i7 = 0; i7 < size; i7++) {
            i2 += h0.m0((long) ((Integer) list.get(i7)).intValue());
        }
        return i2;
    }

    public static int x(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof C0103m) {
            C0103m mVar = (C0103m) list;
            if (size <= 0) {
                return 0;
            }
            throw null;
        }
        int i2 = 0;
        for (int i7 = 0; i7 < size; i7++) {
            i2 += h0.l0(((Integer) list.get(i7)).intValue());
        }
        return i2;
    }

    public static int y(List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        if (list instanceof C0103m) {
            C0103m mVar = (C0103m) list;
            if (size <= 0) {
                return 0;
            }
            throw null;
        }
        int i2 = 0;
        for (int i7 = 0; i7 < size; i7++) {
            int intValue = ((Integer) list.get(i7)).intValue();
            i2 += h0.l0((intValue >> 31) ^ (intValue + intValue));
        }
        return i2;
    }
}
