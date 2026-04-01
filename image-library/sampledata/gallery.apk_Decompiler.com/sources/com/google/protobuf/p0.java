package com.google.protobuf;

import com.samsung.android.sdk.cover.ScoverState;
import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class p0 {

    /* renamed from: a  reason: collision with root package name */
    public static final Unsafe f1624a;
    public static final Class b = C0132d.f1600a;

    /* renamed from: c  reason: collision with root package name */
    public static final o0 f1625c;
    public static final boolean d;
    public static final boolean e;
    public static final long f = ((long) c(byte[].class));
    public static final long g;

    /* renamed from: h  reason: collision with root package name */
    public static final boolean f1626h;

    static {
        boolean z;
        boolean z3;
        long j2;
        Unsafe j3 = j();
        f1624a = j3;
        boolean f5 = f(Long.TYPE);
        boolean f8 = f(Integer.TYPE);
        o0 o0Var = null;
        if (j3 != null) {
            if (!C0132d.a()) {
                o0Var = new o0(j3);
            } else if (f5) {
                o0Var = new m0(j3, 1);
            } else if (f8) {
                o0Var = new m0(j3, 0);
            }
        }
        f1625c = o0Var;
        boolean z7 = false;
        if (o0Var == null) {
            z = false;
        } else {
            z = o0Var.u();
        }
        d = z;
        if (o0Var == null) {
            z3 = false;
        } else {
            z3 = o0Var.t();
        }
        e = z3;
        Class<boolean[]> cls = boolean[].class;
        c(cls);
        d(cls);
        Class<int[]> cls2 = int[].class;
        c(cls2);
        d(cls2);
        Class<long[]> cls3 = long[].class;
        c(cls3);
        d(cls3);
        Class<float[]> cls4 = float[].class;
        c(cls4);
        d(cls4);
        Class<double[]> cls5 = double[].class;
        c(cls5);
        d(cls5);
        Class<Object[]> cls6 = Object[].class;
        c(cls6);
        d(cls6);
        Field e7 = e();
        if (e7 == null || o0Var == null) {
            j2 = -1;
        } else {
            j2 = o0Var.l(e7);
        }
        g = j2;
        if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
            z7 = true;
        }
        f1626h = z7;
    }

    public static void a(Throwable th) {
        Logger logger = Logger.getLogger(p0.class.getName());
        Level level = Level.WARNING;
        logger.log(level, "platform method missing - proto runtime falling back to safer methods: " + th);
    }

    public static Object b(Class cls) {
        try {
            return f1624a.allocateInstance(cls);
        } catch (InstantiationException e7) {
            throw new IllegalStateException(e7);
        }
    }

    public static int c(Class cls) {
        if (e) {
            return f1625c.a(cls);
        }
        return -1;
    }

    public static void d(Class cls) {
        if (e) {
            f1625c.b(cls);
        }
    }

    public static Field e() {
        Field field;
        Field field2;
        Class<Buffer> cls = Buffer.class;
        if (C0132d.a()) {
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

    public static boolean f(Class cls) {
        Class<byte[]> cls2 = byte[].class;
        if (!C0132d.a()) {
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

    public static byte g(byte[] bArr, long j2) {
        return f1625c.f(bArr, f + j2);
    }

    public static byte h(Object obj, long j2) {
        return (byte) ((f1625c.i(obj, -4 & j2) >>> ((int) (((~j2) & 3) << 3))) & ScoverState.TYPE_NFC_SMART_COVER);
    }

    public static byte i(Object obj, long j2) {
        return (byte) ((f1625c.i(obj, -4 & j2) >>> ((int) ((j2 & 3) << 3))) & ScoverState.TYPE_NFC_SMART_COVER);
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [java.lang.Object, java.security.PrivilegedExceptionAction] */
    public static Unsafe j() {
        try {
            return (Unsafe) AccessController.doPrivileged(new Object());
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void k(byte[] bArr, long j2, byte b5) {
        f1625c.n(bArr, f + j2, b5);
    }

    public static void l(Object obj, long j2, byte b5) {
        long j3 = -4 & j2;
        int i2 = f1625c.i(obj, j3);
        int i7 = ((~((int) j2)) & 3) << 3;
        n(((255 & b5) << i7) | (i2 & (~(ScoverState.TYPE_NFC_SMART_COVER << i7))), j3, obj);
    }

    public static void m(Object obj, long j2, byte b5) {
        long j3 = -4 & j2;
        int i2 = (((int) j2) & 3) << 3;
        n(((255 & b5) << i2) | (f1625c.i(obj, j3) & (~(ScoverState.TYPE_NFC_SMART_COVER << i2))), j3, obj);
    }

    public static void n(int i2, long j2, Object obj) {
        f1625c.q(i2, j2, obj);
    }

    public static void o(Object obj, long j2, long j3) {
        f1625c.r(obj, j2, j3);
    }

    public static void p(long j2, Object obj, Object obj2) {
        f1625c.s(j2, obj, obj2);
    }
}
