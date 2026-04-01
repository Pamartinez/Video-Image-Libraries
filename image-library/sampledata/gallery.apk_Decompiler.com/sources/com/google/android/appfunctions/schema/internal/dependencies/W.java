package com.google.android.appfunctions.schema.internal.dependencies;

import com.samsung.android.sdk.cover.ScoverState;
import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import sun.misc.Unsafe;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class W {

    /* renamed from: a  reason: collision with root package name */
    public static final Unsafe f1216a;
    public static final Class b = b0.f1220a;

    /* renamed from: c  reason: collision with root package name */
    public static final V f1217c;
    public static final boolean d;
    public static final boolean e;
    public static final long f = ((long) b(byte[].class));
    public static final boolean g;

    static {
        boolean z;
        boolean z3;
        Unsafe p6 = p();
        f1216a = p6;
        boolean q = q(Long.TYPE);
        boolean q10 = q(Integer.TYPE);
        V v = null;
        if (p6 != null) {
            if (!b0.a()) {
                v = new V(p6);
            } else if (q) {
                v = new V(p6);
            } else if (q10) {
                v = new V(p6);
            }
        }
        f1217c = v;
        boolean z7 = false;
        if (v == null) {
            z = false;
        } else {
            z = v.i();
        }
        d = z;
        if (v == null) {
            z3 = false;
        } else {
            z3 = v.h();
        }
        e = z3;
        Class<boolean[]> cls = boolean[].class;
        b(cls);
        c(cls);
        Class<int[]> cls2 = int[].class;
        b(cls2);
        c(cls2);
        Class<long[]> cls3 = long[].class;
        b(cls3);
        c(cls3);
        Class<float[]> cls4 = float[].class;
        b(cls4);
        c(cls4);
        Class<double[]> cls5 = double[].class;
        b(cls5);
        c(cls5);
        Class<Object[]> cls6 = Object[].class;
        b(cls6);
        c(cls6);
        Field d2 = d();
        if (!(d2 == null || v == null)) {
            v.f1215a.objectFieldOffset(d2);
        }
        if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
            z7 = true;
        }
        g = z7;
    }

    public static int b(Class cls) {
        if (e) {
            return f1217c.f1215a.arrayBaseOffset(cls);
        }
        return -1;
    }

    public static void c(Class cls) {
        if (e) {
            f1217c.f1215a.arrayIndexScale(cls);
        }
    }

    public static Field d() {
        Field field;
        Field field2;
        Class<Buffer> cls = Buffer.class;
        if (b0.a()) {
            try {
                field2 = cls.getDeclaredField("effectiveDirectAddress");
            } catch (Throwable unused) {
                field2 = null;
            }
            if (field2 != null) {
                return field2;
            }
        }
        try {
            field = cls.getDeclaredField("address");
        } catch (Throwable unused2) {
            field = null;
        }
        if (field == null || field.getType() != Long.TYPE) {
            return null;
        }
        return field;
    }

    public static byte e(Object obj, long j2) {
        return (byte) ((f1217c.f1215a.getInt(obj, -4 & j2) >>> ((int) (((~j2) & 3) << 3))) & ScoverState.TYPE_NFC_SMART_COVER);
    }

    public static byte f(Object obj, long j2) {
        return (byte) ((f1217c.f1215a.getInt(obj, -4 & j2) >>> ((int) ((j2 & 3) << 3))) & ScoverState.TYPE_NFC_SMART_COVER);
    }

    public static void g(Object obj, long j2, byte b5) {
        Unsafe unsafe = f1217c.f1215a;
        long j3 = -4 & j2;
        int i2 = unsafe.getInt(obj, j3);
        int i7 = ((~((int) j2)) & 3) << 3;
        unsafe.putInt(obj, j3, ((255 & b5) << i7) | (i2 & (~(ScoverState.TYPE_NFC_SMART_COVER << i7))));
    }

    public static void h(Object obj, long j2, byte b5) {
        Unsafe unsafe = f1217c.f1215a;
        long j3 = -4 & j2;
        int i2 = (((int) j2) & 3) << 3;
        unsafe.putInt(obj, j3, ((255 & b5) << i2) | (unsafe.getInt(obj, j3) & (~(ScoverState.TYPE_NFC_SMART_COVER << i2))));
    }

    public static Object i(Class cls) {
        try {
            return f1216a.allocateInstance(cls);
        } catch (InstantiationException e7) {
            throw new IllegalStateException(e7);
        }
    }

    public static int j(Object obj, long j2) {
        return f1217c.f1215a.getInt(obj, j2);
    }

    public static void k(int i2, long j2, Object obj) {
        f1217c.f1215a.putInt(obj, j2, i2);
    }

    public static long l(Object obj, long j2) {
        return f1217c.f1215a.getLong(obj, j2);
    }

    public static void m(Object obj, long j2, long j3) {
        f1217c.f1215a.putLong(obj, j2, j3);
    }

    public static Object n(Object obj, long j2) {
        return f1217c.f1215a.getObject(obj, j2);
    }

    public static void o(long j2, Object obj, Object obj2) {
        f1217c.f1215a.putObject(obj, j2, obj2);
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Object, java.security.PrivilegedExceptionAction] */
    public static Unsafe p() {
        try {
            return (Unsafe) AccessController.doPrivileged(new Object());
        } catch (Throwable unused) {
            return null;
        }
    }

    public static boolean q(Class cls) {
        Class<byte[]> cls2 = byte[].class;
        if (!b0.a()) {
            return false;
        }
        try {
            Class cls3 = b;
            Class cls4 = Boolean.TYPE;
            cls3.getMethod("peekLong", new Class[]{cls, cls4});
            cls3.getMethod("pokeLong", new Class[]{cls, Long.TYPE, cls4});
            Class cls5 = Integer.TYPE;
            cls3.getMethod("pokeInt", new Class[]{cls, cls5, cls4});
            cls3.getMethod("peekInt", new Class[]{cls, cls4});
            cls3.getMethod("pokeByte", new Class[]{cls, Byte.TYPE});
            cls3.getMethod("peekByte", new Class[]{cls});
            cls3.getMethod("pokeByteArray", new Class[]{cls, cls2, cls5, cls5});
            cls3.getMethod("peekByteArray", new Class[]{cls, cls2, cls5, cls5});
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }
}
