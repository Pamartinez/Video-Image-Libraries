package com.google.android.appfunctions.schema.internal.dependencies;

/* renamed from: com.google.android.appfunctions.schema.internal.dependencies.x  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0113x implements C {

    /* renamed from: a  reason: collision with root package name */
    public final C[] f1228a;

    public C0113x(C... cArr) {
        this.f1228a = cArr;
    }

    public final boolean a(Class cls) {
        for (int i2 = 0; i2 < 2; i2++) {
            if (this.f1228a[i2].a(cls)) {
                return true;
            }
        }
        return false;
    }

    public final L b(Class cls) {
        for (int i2 = 0; i2 < 2; i2++) {
            C c5 = this.f1228a[i2];
            if (c5.a(cls)) {
                return c5.b(cls);
            }
        }
        throw new UnsupportedOperationException("No factory is available for message type: ".concat(cls.getName()));
    }
}
