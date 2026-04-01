package com.google.protobuf;

import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import java.util.logging.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class g0 {

    /* renamed from: a  reason: collision with root package name */
    public static final Class f1606a;
    public static final h0 b;

    /* renamed from: c  reason: collision with root package name */
    public static final h0 f1607c = new Object();

    /* JADX WARNING: type inference failed for: r0v2, types: [com.google.protobuf.h0, java.lang.Object] */
    static {
        Class<?> cls;
        Class<?> cls2;
        h0 h0Var = null;
        try {
            cls = Class.forName("com.google.protobuf.GeneratedMessageV3");
        } catch (Throwable unused) {
            cls = null;
        }
        f1606a = cls;
        try {
            cls2 = Class.forName("com.google.protobuf.UnknownFieldSetSchema");
        } catch (Throwable unused2) {
            cls2 = null;
        }
        if (cls2 != null) {
            try {
                h0Var = (h0) cls2.getConstructor((Class[]) null).newInstance((Object[]) null);
            } catch (Throwable unused3) {
            }
        }
        b = h0Var;
    }

    public static void A(int i2, List list, P p6, boolean z) {
        if (list != null && !list.isEmpty()) {
            CodedOutputStream codedOutputStream = (CodedOutputStream) p6.f1586a;
            int i7 = 0;
            if (z) {
                codedOutputStream.p0(i2, 2);
                int i8 = 0;
                for (int i10 = 0; i10 < list.size(); i10++) {
                    int intValue = ((Integer) list.get(i10)).intValue();
                    i8 += CodedOutputStream.b0((intValue >> 31) ^ (intValue << 1));
                }
                codedOutputStream.r0(i8);
                while (i7 < list.size()) {
                    int intValue2 = ((Integer) list.get(i7)).intValue();
                    codedOutputStream.r0((intValue2 >> 31) ^ (intValue2 << 1));
                    i7++;
                }
                return;
            }
            while (i7 < list.size()) {
                int intValue3 = ((Integer) list.get(i7)).intValue();
                codedOutputStream.q0(i2, (intValue3 >> 31) ^ (intValue3 << 1));
                i7++;
            }
        }
    }

    public static void B(int i2, List list, P p6, boolean z) {
        if (list != null && !list.isEmpty()) {
            CodedOutputStream codedOutputStream = (CodedOutputStream) p6.f1586a;
            int i7 = 0;
            if (z) {
                codedOutputStream.p0(i2, 2);
                int i8 = 0;
                for (int i10 = 0; i10 < list.size(); i10++) {
                    long longValue = ((Long) list.get(i10)).longValue();
                    i8 += CodedOutputStream.c0((longValue >> 63) ^ (longValue << 1));
                }
                codedOutputStream.r0(i8);
                while (i7 < list.size()) {
                    long longValue2 = ((Long) list.get(i7)).longValue();
                    codedOutputStream.t0((longValue2 >> 63) ^ (longValue2 << 1));
                    i7++;
                }
                return;
            }
            while (i7 < list.size()) {
                long longValue3 = ((Long) list.get(i7)).longValue();
                codedOutputStream.s0(i2, (longValue3 >> 63) ^ (longValue3 << 1));
                i7++;
            }
        }
    }

    public static void C(int i2, List list, P p6) {
        if (list != null && !list.isEmpty()) {
            CodedOutputStream codedOutputStream = (CodedOutputStream) p6.f1586a;
            int i7 = 0;
            if (list instanceof I) {
                I i8 = (I) list;
                while (i7 < list.size()) {
                    Object n = i8.n(i7);
                    if (n instanceof String) {
                        codedOutputStream.o0(i2, (String) n);
                    } else {
                        codedOutputStream.g0(i2, (ByteString) n);
                    }
                    i7++;
                }
                return;
            }
            while (i7 < list.size()) {
                codedOutputStream.o0(i2, (String) list.get(i7));
                i7++;
            }
        }
    }

    public static void D(int i2, List list, P p6, boolean z) {
        if (list != null && !list.isEmpty()) {
            CodedOutputStream codedOutputStream = (CodedOutputStream) p6.f1586a;
            int i7 = 0;
            if (z) {
                codedOutputStream.p0(i2, 2);
                int i8 = 0;
                for (int i10 = 0; i10 < list.size(); i10++) {
                    i8 += CodedOutputStream.b0(((Integer) list.get(i10)).intValue());
                }
                codedOutputStream.r0(i8);
                while (i7 < list.size()) {
                    codedOutputStream.r0(((Integer) list.get(i7)).intValue());
                    i7++;
                }
                return;
            }
            while (i7 < list.size()) {
                codedOutputStream.q0(i2, ((Integer) list.get(i7)).intValue());
                i7++;
            }
        }
    }

    public static void E(int i2, List list, P p6, boolean z) {
        if (list != null && !list.isEmpty()) {
            CodedOutputStream codedOutputStream = (CodedOutputStream) p6.f1586a;
            int i7 = 0;
            if (z) {
                codedOutputStream.p0(i2, 2);
                int i8 = 0;
                for (int i10 = 0; i10 < list.size(); i10++) {
                    i8 += CodedOutputStream.c0(((Long) list.get(i10)).longValue());
                }
                codedOutputStream.r0(i8);
                while (i7 < list.size()) {
                    codedOutputStream.t0(((Long) list.get(i7)).longValue());
                    i7++;
                }
                return;
            }
            while (i7 < list.size()) {
                codedOutputStream.s0(i2, ((Long) list.get(i7)).longValue());
                i7++;
            }
        }
    }

    public static int a(List list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof B) {
            B b5 = (B) list;
            int i7 = 0;
            while (i2 < size) {
                i7 += CodedOutputStream.Y(b5.getInt(i2));
                i2++;
            }
            return i7;
        }
        int i8 = 0;
        while (i2 < size) {
            i8 += CodedOutputStream.Y(((Integer) list.get(i2)).intValue());
            i2++;
        }
        return i8;
    }

    public static int b(int i2, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (CodedOutputStream.a0(i2) + 4) * size;
    }

    public static int c(int i2, List list) {
        int size = list.size();
        if (size == 0) {
            return 0;
        }
        return (CodedOutputStream.a0(i2) + 8) * size;
    }

    public static int d(List list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof B) {
            B b5 = (B) list;
            int i7 = 0;
            while (i2 < size) {
                i7 += CodedOutputStream.Y(b5.getInt(i2));
                i2++;
            }
            return i7;
        }
        int i8 = 0;
        while (i2 < size) {
            i8 += CodedOutputStream.Y(((Integer) list.get(i2)).intValue());
            i2++;
        }
        return i8;
    }

    public static int e(List list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof M) {
            M m = (M) list;
            int i7 = 0;
            while (i2 < size) {
                m.q(i2);
                i7 += CodedOutputStream.c0(m.e[i2]);
                i2++;
            }
            return i7;
        }
        int i8 = 0;
        while (i2 < size) {
            i8 += CodedOutputStream.c0(((Long) list.get(i2)).longValue());
            i2++;
        }
        return i8;
    }

    public static int f(List list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof B) {
            B b5 = (B) list;
            int i7 = 0;
            while (i2 < size) {
                int i8 = b5.getInt(i2);
                i7 += CodedOutputStream.b0((i8 >> 31) ^ (i8 << 1));
                i2++;
            }
            return i7;
        }
        int i10 = 0;
        while (i2 < size) {
            int intValue = ((Integer) list.get(i2)).intValue();
            i10 += CodedOutputStream.b0((intValue >> 31) ^ (intValue << 1));
            i2++;
        }
        return i10;
    }

    public static int g(List list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof M) {
            M m = (M) list;
            int i7 = 0;
            while (i2 < size) {
                m.q(i2);
                long j2 = m.e[i2];
                i7 += CodedOutputStream.c0((j2 >> 63) ^ (j2 << 1));
                i2++;
            }
            return i7;
        }
        int i8 = 0;
        while (i2 < size) {
            long longValue = ((Long) list.get(i2)).longValue();
            i8 += CodedOutputStream.c0((longValue >> 63) ^ (longValue << 1));
            i2++;
        }
        return i8;
    }

    public static int h(List list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof B) {
            B b5 = (B) list;
            int i7 = 0;
            while (i2 < size) {
                i7 += CodedOutputStream.b0(b5.getInt(i2));
                i2++;
            }
            return i7;
        }
        int i8 = 0;
        while (i2 < size) {
            i8 += CodedOutputStream.b0(((Integer) list.get(i2)).intValue());
            i2++;
        }
        return i8;
    }

    public static int i(List list) {
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            return 0;
        }
        if (list instanceof M) {
            M m = (M) list;
            int i7 = 0;
            while (i2 < size) {
                m.q(i2);
                i7 += CodedOutputStream.c0(m.e[i2]);
                i2++;
            }
            return i7;
        }
        int i8 = 0;
        while (i2 < size) {
            i8 += CodedOutputStream.c0(((Long) list.get(i2)).longValue());
            i2++;
        }
        return i8;
    }

    public static Object j(Object obj, int i2, List list, Internal$EnumVerifier internal$EnumVerifier, Object obj2, h0 h0Var) {
        if (internal$EnumVerifier == null) {
            return obj2;
        }
        if (list instanceof RandomAccess) {
            int size = list.size();
            int i7 = 0;
            for (int i8 = 0; i8 < size; i8++) {
                Integer num = (Integer) list.get(i8);
                int intValue = num.intValue();
                if (internal$EnumVerifier.isInRange(intValue)) {
                    if (i8 != i7) {
                        list.set(i7, num);
                    }
                    i7++;
                } else {
                    obj2 = m(obj, i2, intValue, obj2, h0Var);
                }
            }
            if (i7 != size) {
                list.subList(i7, size).clear();
            }
            return obj2;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            int intValue2 = ((Integer) it.next()).intValue();
            if (!internal$EnumVerifier.isInRange(intValue2)) {
                obj2 = m(obj, i2, intValue2, obj2, h0Var);
                it.remove();
            }
        }
        return obj2;
    }

    public static void k(h0 h0Var, Object obj, Object obj2) {
        h0Var.getClass();
        GeneratedMessageLite generatedMessageLite = (GeneratedMessageLite) obj;
        UnknownFieldSetLite unknownFieldSetLite = generatedMessageLite.unknownFields;
        UnknownFieldSetLite unknownFieldSetLite2 = ((GeneratedMessageLite) obj2).unknownFields;
        UnknownFieldSetLite unknownFieldSetLite3 = UnknownFieldSetLite.f;
        if (!unknownFieldSetLite3.equals(unknownFieldSetLite2)) {
            if (unknownFieldSetLite3.equals(unknownFieldSetLite)) {
                unknownFieldSetLite = UnknownFieldSetLite.e(unknownFieldSetLite, unknownFieldSetLite2);
            } else {
                unknownFieldSetLite.getClass();
                if (!unknownFieldSetLite2.equals(unknownFieldSetLite3)) {
                    unknownFieldSetLite.a();
                    int i2 = unknownFieldSetLite.f1588a + unknownFieldSetLite2.f1588a;
                    unknownFieldSetLite.b(i2);
                    System.arraycopy(unknownFieldSetLite2.b, 0, unknownFieldSetLite.b, unknownFieldSetLite.f1588a, unknownFieldSetLite2.f1588a);
                    System.arraycopy(unknownFieldSetLite2.f1589c, 0, unknownFieldSetLite.f1589c, unknownFieldSetLite.f1588a, unknownFieldSetLite2.f1588a);
                    unknownFieldSetLite.f1588a = i2;
                }
            }
        }
        generatedMessageLite.unknownFields = unknownFieldSetLite;
    }

    public static boolean l(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || !obj.equals(obj2)) {
            return false;
        }
        return true;
    }

    public static Object m(Object obj, int i2, int i7, Object obj2, h0 h0Var) {
        if (obj2 == null) {
            h0Var.getClass();
            obj2 = h0.a(obj);
        }
        h0Var.getClass();
        ((UnknownFieldSetLite) obj2).f(i2 << 3, Long.valueOf((long) i7));
        return obj2;
    }

    public static void n(int i2, List list, P p6, boolean z) {
        if (list != null && !list.isEmpty()) {
            CodedOutputStream codedOutputStream = (CodedOutputStream) p6.f1586a;
            int i7 = 0;
            if (z) {
                codedOutputStream.p0(i2, 2);
                int i8 = 0;
                for (int i10 = 0; i10 < list.size(); i10++) {
                    ((Boolean) list.get(i10)).getClass();
                    Logger logger = CodedOutputStream.f1576h;
                    i8++;
                }
                codedOutputStream.r0(i8);
                while (i7 < list.size()) {
                    codedOutputStream.e0(((Boolean) list.get(i7)).booleanValue() ? (byte) 1 : 0);
                    i7++;
                }
                return;
            }
            while (i7 < list.size()) {
                codedOutputStream.f0(i2, ((Boolean) list.get(i7)).booleanValue());
                i7++;
            }
        }
    }

    public static void o(int i2, List list, P p6) {
        if (list != null && !list.isEmpty()) {
            p6.getClass();
            for (int i7 = 0; i7 < list.size(); i7++) {
                ((CodedOutputStream) p6.f1586a).g0(i2, (ByteString) list.get(i7));
            }
        }
    }

    public static void p(int i2, List list, P p6, boolean z) {
        if (list != null && !list.isEmpty()) {
            CodedOutputStream codedOutputStream = (CodedOutputStream) p6.f1586a;
            int i7 = 0;
            if (z) {
                codedOutputStream.p0(i2, 2);
                int i8 = 0;
                for (int i10 = 0; i10 < list.size(); i10++) {
                    ((Double) list.get(i10)).getClass();
                    Logger logger = CodedOutputStream.f1576h;
                    i8 += 8;
                }
                codedOutputStream.r0(i8);
                while (i7 < list.size()) {
                    codedOutputStream.k0(Double.doubleToRawLongBits(((Double) list.get(i7)).doubleValue()));
                    i7++;
                }
                return;
            }
            while (i7 < list.size()) {
                double doubleValue = ((Double) list.get(i7)).doubleValue();
                codedOutputStream.getClass();
                codedOutputStream.j0(i2, Double.doubleToRawLongBits(doubleValue));
                i7++;
            }
        }
    }

    public static void q(int i2, List list, P p6, boolean z) {
        if (list != null && !list.isEmpty()) {
            CodedOutputStream codedOutputStream = (CodedOutputStream) p6.f1586a;
            int i7 = 0;
            if (z) {
                codedOutputStream.p0(i2, 2);
                int i8 = 0;
                for (int i10 = 0; i10 < list.size(); i10++) {
                    i8 += CodedOutputStream.Y(((Integer) list.get(i10)).intValue());
                }
                codedOutputStream.r0(i8);
                while (i7 < list.size()) {
                    codedOutputStream.m0(((Integer) list.get(i7)).intValue());
                    i7++;
                }
                return;
            }
            while (i7 < list.size()) {
                codedOutputStream.l0(i2, ((Integer) list.get(i7)).intValue());
                i7++;
            }
        }
    }

    public static void r(int i2, List list, P p6, boolean z) {
        if (list != null && !list.isEmpty()) {
            CodedOutputStream codedOutputStream = (CodedOutputStream) p6.f1586a;
            int i7 = 0;
            if (z) {
                codedOutputStream.p0(i2, 2);
                int i8 = 0;
                for (int i10 = 0; i10 < list.size(); i10++) {
                    ((Integer) list.get(i10)).getClass();
                    Logger logger = CodedOutputStream.f1576h;
                    i8 += 4;
                }
                codedOutputStream.r0(i8);
                while (i7 < list.size()) {
                    codedOutputStream.i0(((Integer) list.get(i7)).intValue());
                    i7++;
                }
                return;
            }
            while (i7 < list.size()) {
                codedOutputStream.h0(i2, ((Integer) list.get(i7)).intValue());
                i7++;
            }
        }
    }

    public static void s(int i2, List list, P p6, boolean z) {
        if (list != null && !list.isEmpty()) {
            CodedOutputStream codedOutputStream = (CodedOutputStream) p6.f1586a;
            int i7 = 0;
            if (z) {
                codedOutputStream.p0(i2, 2);
                int i8 = 0;
                for (int i10 = 0; i10 < list.size(); i10++) {
                    ((Long) list.get(i10)).getClass();
                    Logger logger = CodedOutputStream.f1576h;
                    i8 += 8;
                }
                codedOutputStream.r0(i8);
                while (i7 < list.size()) {
                    codedOutputStream.k0(((Long) list.get(i7)).longValue());
                    i7++;
                }
                return;
            }
            while (i7 < list.size()) {
                codedOutputStream.j0(i2, ((Long) list.get(i7)).longValue());
                i7++;
            }
        }
    }

    public static void t(int i2, List list, P p6, boolean z) {
        if (list != null && !list.isEmpty()) {
            CodedOutputStream codedOutputStream = (CodedOutputStream) p6.f1586a;
            int i7 = 0;
            if (z) {
                codedOutputStream.p0(i2, 2);
                int i8 = 0;
                for (int i10 = 0; i10 < list.size(); i10++) {
                    ((Float) list.get(i10)).getClass();
                    Logger logger = CodedOutputStream.f1576h;
                    i8 += 4;
                }
                codedOutputStream.r0(i8);
                while (i7 < list.size()) {
                    codedOutputStream.i0(Float.floatToRawIntBits(((Float) list.get(i7)).floatValue()));
                    i7++;
                }
                return;
            }
            while (i7 < list.size()) {
                float floatValue = ((Float) list.get(i7)).floatValue();
                codedOutputStream.getClass();
                codedOutputStream.h0(i2, Float.floatToRawIntBits(floatValue));
                i7++;
            }
        }
    }

    public static void u(int i2, List list, P p6, Schema schema) {
        if (list != null && !list.isEmpty()) {
            p6.getClass();
            for (int i7 = 0; i7 < list.size(); i7++) {
                p6.d(i2, list.get(i7), schema);
            }
        }
    }

    public static void v(int i2, List list, P p6, boolean z) {
        if (list != null && !list.isEmpty()) {
            CodedOutputStream codedOutputStream = (CodedOutputStream) p6.f1586a;
            int i7 = 0;
            if (z) {
                codedOutputStream.p0(i2, 2);
                int i8 = 0;
                for (int i10 = 0; i10 < list.size(); i10++) {
                    i8 += CodedOutputStream.Y(((Integer) list.get(i10)).intValue());
                }
                codedOutputStream.r0(i8);
                while (i7 < list.size()) {
                    codedOutputStream.m0(((Integer) list.get(i7)).intValue());
                    i7++;
                }
                return;
            }
            while (i7 < list.size()) {
                codedOutputStream.l0(i2, ((Integer) list.get(i7)).intValue());
                i7++;
            }
        }
    }

    public static void w(int i2, List list, P p6, boolean z) {
        if (list != null && !list.isEmpty()) {
            CodedOutputStream codedOutputStream = (CodedOutputStream) p6.f1586a;
            int i7 = 0;
            if (z) {
                codedOutputStream.p0(i2, 2);
                int i8 = 0;
                for (int i10 = 0; i10 < list.size(); i10++) {
                    i8 += CodedOutputStream.c0(((Long) list.get(i10)).longValue());
                }
                codedOutputStream.r0(i8);
                while (i7 < list.size()) {
                    codedOutputStream.t0(((Long) list.get(i7)).longValue());
                    i7++;
                }
                return;
            }
            while (i7 < list.size()) {
                codedOutputStream.s0(i2, ((Long) list.get(i7)).longValue());
                i7++;
            }
        }
    }

    public static void x(int i2, List list, P p6, Schema schema) {
        if (list != null && !list.isEmpty()) {
            p6.getClass();
            for (int i7 = 0; i7 < list.size(); i7++) {
                p6.g(i2, list.get(i7), schema);
            }
        }
    }

    public static void y(int i2, List list, P p6, boolean z) {
        if (list != null && !list.isEmpty()) {
            CodedOutputStream codedOutputStream = (CodedOutputStream) p6.f1586a;
            int i7 = 0;
            if (z) {
                codedOutputStream.p0(i2, 2);
                int i8 = 0;
                for (int i10 = 0; i10 < list.size(); i10++) {
                    ((Integer) list.get(i10)).getClass();
                    Logger logger = CodedOutputStream.f1576h;
                    i8 += 4;
                }
                codedOutputStream.r0(i8);
                while (i7 < list.size()) {
                    codedOutputStream.i0(((Integer) list.get(i7)).intValue());
                    i7++;
                }
                return;
            }
            while (i7 < list.size()) {
                codedOutputStream.h0(i2, ((Integer) list.get(i7)).intValue());
                i7++;
            }
        }
    }

    public static void z(int i2, List list, P p6, boolean z) {
        if (list != null && !list.isEmpty()) {
            CodedOutputStream codedOutputStream = (CodedOutputStream) p6.f1586a;
            int i7 = 0;
            if (z) {
                codedOutputStream.p0(i2, 2);
                int i8 = 0;
                for (int i10 = 0; i10 < list.size(); i10++) {
                    ((Long) list.get(i10)).getClass();
                    Logger logger = CodedOutputStream.f1576h;
                    i8 += 8;
                }
                codedOutputStream.r0(i8);
                while (i7 < list.size()) {
                    codedOutputStream.k0(((Long) list.get(i7)).longValue());
                    i7++;
                }
                return;
            }
            while (i7 < list.size()) {
                codedOutputStream.j0(i2, ((Long) list.get(i7)).longValue());
                i7++;
            }
        }
    }
}
