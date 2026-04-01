package p1;

import com.google.android.appfunctions.schema.internal.dependencies.C0099i;
import com.google.android.appfunctions.schema.internal.dependencies.C0101k;
import com.google.android.appfunctions.schema.internal.dependencies.C0102l;
import com.google.android.appfunctions.schema.internal.dependencies.I;
import com.google.android.appfunctions.schema.internal.dependencies.L;

/* renamed from: p1.f  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0258f extends C0102l {
    private static final C0258f DEFAULT_INSTANCE;
    public static final int DURATION_MS_FIELD_NUMBER = 1;
    private static volatile I PARSER;
    private int bitField0_;
    private long durationMs_;

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.appfunctions.schema.internal.dependencies.l, p1.f] */
    static {
        ? lVar = new C0102l();
        DEFAULT_INSTANCE = lVar;
        C0102l.g(C0258f.class, lVar);
    }

    public static C0257e i() {
        return (C0257e) ((C0099i) DEFAULT_INSTANCE.b(C0101k.zze));
    }

    /* JADX WARNING: type inference failed for: r2v14, types: [java.lang.Object, com.google.android.appfunctions.schema.internal.dependencies.I] */
    public final Object b(C0101k kVar) {
        I i2;
        int ordinal = kVar.ordinal();
        if (ordinal == 0) {
            return (byte) 1;
        }
        if (ordinal == 2) {
            return new L(DEFAULT_INSTANCE, "\u0004\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဂ\u0000", new Object[]{"bitField0_", "durationMs_"});
        } else if (ordinal == 3) {
            return new C0102l();
        } else {
            if (ordinal == 4) {
                return new C0099i(DEFAULT_INSTANCE);
            }
            if (ordinal == 5) {
                return DEFAULT_INSTANCE;
            }
            if (ordinal == 6) {
                I i7 = PARSER;
                if (i7 != null) {
                    return i7;
                }
                synchronized (C0258f.class) {
                    try {
                        I i8 = PARSER;
                        i2 = i8;
                        if (i8 == null) {
                            ? obj = new Object();
                            PARSER = obj;
                            i2 = obj;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return i2;
            }
            throw null;
        }
    }

    public final void j(long j2) {
        this.bitField0_ |= 1;
        this.durationMs_ = j2;
    }
}
