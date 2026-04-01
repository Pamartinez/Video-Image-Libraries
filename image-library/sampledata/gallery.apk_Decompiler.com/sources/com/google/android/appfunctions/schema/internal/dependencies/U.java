package com.google.android.appfunctions.schema.internal.dependencies;

import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class U extends V {
    public final void a(Object obj, long j2, byte b) {
        this.f1215a.putByte(obj, j2, b);
    }

    public final boolean b(Object obj, long j2) {
        return this.f1215a.getBoolean(obj, j2);
    }

    public final void c(Object obj, long j2, boolean z) {
        this.f1215a.putBoolean(obj, j2, z);
    }

    public final float d(Object obj, long j2) {
        return this.f1215a.getFloat(obj, j2);
    }

    public final void e(Object obj, long j2, float f) {
        this.f1215a.putFloat(obj, j2, f);
    }

    public final double f(Object obj, long j2) {
        return this.f1215a.getDouble(obj, j2);
    }

    public final void g(Object obj, long j2, double d) {
        this.f1215a.putDouble(obj, j2, d);
    }

    public final boolean h() {
        Class<Object> cls = Object.class;
        if (!super.h()) {
            return false;
        }
        try {
            Class<?> cls2 = this.f1215a.getClass();
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
            Logger.getLogger(W.class.getName()).logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(th.toString()));
            return false;
        }
    }

    public final boolean i() {
        Class<Object> cls = Object.class;
        if (!super.i()) {
            return false;
        }
        try {
            Class<?> cls2 = this.f1215a.getClass();
            Class cls3 = Long.TYPE;
            cls2.getMethod("getByte", new Class[]{cls3});
            cls2.getMethod("putByte", new Class[]{cls3, Byte.TYPE});
            cls2.getMethod("getInt", new Class[]{cls3});
            cls2.getMethod("putInt", new Class[]{cls3, Integer.TYPE});
            cls2.getMethod("getLong", new Class[]{cls3});
            cls2.getMethod("putLong", new Class[]{cls3, cls3});
            cls2.getMethod("copyMemory", new Class[]{cls3, cls3, cls3});
            cls2.getMethod("copyMemory", new Class[]{cls, cls3, cls, cls3, cls3});
            return true;
        } catch (Throwable th) {
            Logger.getLogger(W.class.getName()).logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "logMissingMethod", "platform method missing - proto runtime falling back to safer methods: ".concat(th.toString()));
            return false;
        }
    }
}
