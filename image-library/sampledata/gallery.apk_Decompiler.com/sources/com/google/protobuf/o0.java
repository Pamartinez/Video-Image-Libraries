package com.google.protobuf;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class o0 {

    /* renamed from: a  reason: collision with root package name */
    public final Unsafe f1623a;

    public o0(Unsafe unsafe) {
        this.f1623a = unsafe;
    }

    public final int a(Class cls) {
        return this.f1623a.arrayBaseOffset(cls);
    }

    public final int b(Class cls) {
        return this.f1623a.arrayIndexScale(cls);
    }

    public abstract void c(long j2, long j3, byte[] bArr);

    public abstract boolean d(Object obj, long j2);

    public abstract byte e(long j2);

    public abstract byte f(Object obj, long j2);

    public abstract double g(Object obj, long j2);

    public abstract float h(Object obj, long j2);

    public final int i(Object obj, long j2) {
        return this.f1623a.getInt(obj, j2);
    }

    public final long j(Object obj, long j2) {
        return this.f1623a.getLong(obj, j2);
    }

    public final Object k(Object obj, long j2) {
        return this.f1623a.getObject(obj, j2);
    }

    public final long l(Field field) {
        return this.f1623a.objectFieldOffset(field);
    }

    public abstract void m(Object obj, long j2, boolean z);

    public abstract void n(Object obj, long j2, byte b);

    public abstract void o(Object obj, long j2, double d);

    public abstract void p(Object obj, long j2, float f);

    public final void q(int i2, long j2, Object obj) {
        this.f1623a.putInt(obj, j2, i2);
    }

    public final void r(Object obj, long j2, long j3) {
        this.f1623a.putLong(obj, j2, j3);
    }

    public final void s(long j2, Object obj, Object obj2) {
        this.f1623a.putObject(obj, j2, obj2);
    }

    public boolean t() {
        Class<Class> cls = Class.class;
        Class<Object> cls2 = Object.class;
        Unsafe unsafe = this.f1623a;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls3 = unsafe.getClass();
            cls3.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls3.getMethod("arrayBaseOffset", new Class[]{cls});
            cls3.getMethod("arrayIndexScale", new Class[]{cls});
            Class cls4 = Long.TYPE;
            cls3.getMethod("getInt", new Class[]{cls2, cls4});
            cls3.getMethod("putInt", new Class[]{cls2, cls4, Integer.TYPE});
            cls3.getMethod("getLong", new Class[]{cls2, cls4});
            cls3.getMethod("putLong", new Class[]{cls2, cls4, cls4});
            cls3.getMethod("getObject", new Class[]{cls2, cls4});
            cls3.getMethod("putObject", new Class[]{cls2, cls4, cls2});
            return true;
        } catch (Throwable th) {
            p0.a(th);
            return false;
        }
    }

    public abstract boolean u();
}
