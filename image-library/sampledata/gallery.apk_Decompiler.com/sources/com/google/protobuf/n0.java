package com.google.protobuf;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class n0 extends o0 {
    public final void c(long j2, long j3, byte[] bArr) {
        this.f1623a.copyMemory((Object) null, j2, bArr, p0.f, j3);
    }

    public final boolean d(Object obj, long j2) {
        return this.f1623a.getBoolean(obj, j2);
    }

    public final byte e(long j2) {
        return this.f1623a.getByte(j2);
    }

    public final byte f(Object obj, long j2) {
        return this.f1623a.getByte(obj, j2);
    }

    public final double g(Object obj, long j2) {
        return this.f1623a.getDouble(obj, j2);
    }

    public final float h(Object obj, long j2) {
        return this.f1623a.getFloat(obj, j2);
    }

    public final void m(Object obj, long j2, boolean z) {
        this.f1623a.putBoolean(obj, j2, z);
    }

    public final void n(Object obj, long j2, byte b) {
        this.f1623a.putByte(obj, j2, b);
    }

    public final void o(Object obj, long j2, double d) {
        this.f1623a.putDouble(obj, j2, d);
    }

    public final void p(Object obj, long j2, float f) {
        this.f1623a.putFloat(obj, j2, f);
    }

    public final boolean t() {
        Class<Object> cls = Object.class;
        if (!super.t()) {
            return false;
        }
        try {
            Class<?> cls2 = this.f1623a.getClass();
            Class cls3 = Long.TYPE;
            cls2.getMethod("getByte", new Class[]{cls, cls3});
            cls2.getMethod("putByte", new Class[]{cls, cls3, Byte.TYPE});
            cls2.getMethod("getBoolean", new Class[]{cls, cls3});
            cls2.getMethod("putBoolean", new Class[]{cls, cls3, Boolean.TYPE});
            cls2.getMethod("getFloat", new Class[]{cls, cls3});
            cls2.getMethod("putFloat", new Class[]{cls, cls3, Float.TYPE});
            cls2.getMethod("getDouble", new Class[]{cls, cls3});
            cls2.getMethod("putDouble", new Class[]{cls, cls3, Double.TYPE});
            return true;
        } catch (Throwable th) {
            p0.a(th);
            return false;
        }
    }

    public final boolean u() {
        Class<Object> cls = Object.class;
        Unsafe unsafe = this.f1623a;
        if (unsafe != null) {
            try {
                Class<?> cls2 = unsafe.getClass();
                cls2.getMethod("objectFieldOffset", new Class[]{Field.class});
                Class cls3 = Long.TYPE;
                cls2.getMethod("getLong", new Class[]{cls, cls3});
                if (p0.e() != null) {
                    try {
                        Class<?> cls4 = this.f1623a.getClass();
                        cls4.getMethod("getByte", new Class[]{cls3});
                        cls4.getMethod("putByte", new Class[]{cls3, Byte.TYPE});
                        cls4.getMethod("getInt", new Class[]{cls3});
                        cls4.getMethod("putInt", new Class[]{cls3, Integer.TYPE});
                        cls4.getMethod("getLong", new Class[]{cls3});
                        cls4.getMethod("putLong", new Class[]{cls3, cls3});
                        cls4.getMethod("copyMemory", new Class[]{cls3, cls3, cls3});
                        cls4.getMethod("copyMemory", new Class[]{cls, cls3, cls, cls3, cls3});
                        return true;
                    } catch (Throwable th) {
                        p0.a(th);
                        return false;
                    }
                }
            } catch (Throwable th2) {
                p0.a(th2);
            }
        }
        return false;
    }
}
