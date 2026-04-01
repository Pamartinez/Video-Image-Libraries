package com.google.android.appfunctions.schema.internal.dependencies;

/* renamed from: com.google.android.appfunctions.schema.internal.dependencies.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0095e {

    /* renamed from: a  reason: collision with root package name */
    public static final C0094d f1222a = new Object();
    public static final C0094d b;

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, com.google.android.appfunctions.schema.internal.dependencies.d] */
    static {
        Class cls = b0.f1220a;
        C0094d dVar = null;
        try {
            dVar = (C0094d) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
        } catch (Exception unused) {
        }
        b = dVar;
    }
}
