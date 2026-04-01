package com.google.android.appfunctions.schema.internal.dependencies;

/* renamed from: com.google.android.appfunctions.schema.internal.dependencies.j  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0100j implements I {
    static {
        C0093c cVar = C0093c.f1221a;
        Class cls = b0.f1220a;
        if (C0093c.f1221a == null) {
            synchronized (C0093c.class) {
                if (C0093c.f1221a == null) {
                    Class cls2 = C0092b.f1219a;
                    C0093c cVar2 = null;
                    if (cls2 != null) {
                        try {
                            cVar2 = (C0093c) cls2.getDeclaredMethod("getEmptyRegistry", (Class[]) null).invoke((Object) null, (Object[]) null);
                        } catch (Exception unused) {
                        }
                    }
                    if (cVar2 == null) {
                        cVar2 = C0093c.b;
                    }
                    C0093c.f1221a = cVar2;
                }
            }
        }
    }
}
