package com.google.protobuf;

/* renamed from: com.google.protobuf.t  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0147t {

    /* renamed from: a  reason: collision with root package name */
    public static final C0146s f1630a = new Object();
    public static final r b;

    /* JADX WARNING: type inference failed for: r0v0, types: [java.lang.Object, com.google.protobuf.s] */
    static {
        r rVar = null;
        try {
            rVar = (r) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
        } catch (Exception unused) {
        }
        b = rVar;
    }
}
