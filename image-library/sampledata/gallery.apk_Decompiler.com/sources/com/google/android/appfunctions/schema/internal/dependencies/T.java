package com.google.android.appfunctions.schema.internal.dependencies;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class T extends V {
    public final void a(Object obj, long j2, byte b) {
        if (W.g) {
            W.g(obj, j2, b);
        } else {
            W.h(obj, j2, b);
        }
    }

    public final boolean b(Object obj, long j2) {
        if (W.g) {
            if (W.e(obj, j2) != 0) {
                return true;
            }
            return false;
        } else if (W.f(obj, j2) != 0) {
            return true;
        } else {
            return false;
        }
    }

    public final void c(Object obj, long j2, boolean z) {
        if (W.g) {
            W.g(obj, j2, z);
        } else {
            W.h(obj, j2, z ? (byte) 1 : 0);
        }
    }

    public final float d(Object obj, long j2) {
        return Float.intBitsToFloat(this.f1215a.getInt(obj, j2));
    }

    public final void e(Object obj, long j2, float f) {
        this.f1215a.putInt(obj, j2, Float.floatToIntBits(f));
    }

    public final double f(Object obj, long j2) {
        return Double.longBitsToDouble(this.f1215a.getLong(obj, j2));
    }

    public final void g(Object obj, long j2, double d) {
        this.f1215a.putLong(obj, j2, Double.doubleToLongBits(d));
    }
}
