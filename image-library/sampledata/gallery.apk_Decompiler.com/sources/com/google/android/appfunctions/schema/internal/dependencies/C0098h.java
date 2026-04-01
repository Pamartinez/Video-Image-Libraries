package com.google.android.appfunctions.schema.internal.dependencies;

/* renamed from: com.google.android.appfunctions.schema.internal.dependencies.h  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0098h implements C {
    public static final C0098h b = new C0098h(0);

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f1224a;

    public /* synthetic */ C0098h(int i2) {
        this.f1224a = i2;
    }

    public final boolean a(Class cls) {
        switch (this.f1224a) {
            case 0:
                return C0102l.class.isAssignableFrom(cls);
            default:
                return false;
        }
    }

    public final L b(Class cls) {
        switch (this.f1224a) {
            case 0:
                Class<C0102l> cls2 = C0102l.class;
                if (cls2.isAssignableFrom(cls)) {
                    try {
                        return (L) C0102l.c(cls.asSubclass(cls2)).b(C0101k.zzc);
                    } catch (Exception e) {
                        throw new RuntimeException("Unable to get message info for ".concat(cls.getName()), e);
                    }
                } else {
                    throw new IllegalArgumentException("Unsupported message type: ".concat(cls.getName()));
                }
            default:
                throw new IllegalStateException("This should never be called.");
        }
    }
}
