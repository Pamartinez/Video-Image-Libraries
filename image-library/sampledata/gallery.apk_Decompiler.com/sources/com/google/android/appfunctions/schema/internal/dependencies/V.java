package com.google.android.appfunctions.schema.internal.dependencies;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class V {

    /* renamed from: a  reason: collision with root package name */
    public final Unsafe f1215a;

    public V(Unsafe unsafe) {
        this.f1215a = unsafe;
    }

    public abstract void a(Object obj, long j2, byte b);

    public abstract boolean b(Object obj, long j2);

    public abstract void c(Object obj, long j2, boolean z);

    public abstract float d(Object obj, long j2);

    public abstract void e(Object obj, long j2, float f);

    public abstract double f(Object obj, long j2);

    public abstract void g(Object obj, long j2, double d);

    public boolean h() {
        Class<Class> cls = Class.class;
        Class<Object> cls2 = Object.class;
        try {
            Class<?> cls3 = this.f1215a.getClass();
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
            Logger.getLogger(W.class.getName()).logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(th.toString()));
            return false;
        }
    }

    public boolean i() {
        try {
            Class<?> cls = this.f1215a.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            if (W.d() == null) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            Logger.getLogger(W.class.getName()).logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(th.toString()));
            return false;
        }
    }
}
