package com.google.protobuf;

import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d0 {

    /* renamed from: c  reason: collision with root package name */
    public static final d0 f1601c = new d0();

    /* renamed from: a  reason: collision with root package name */
    public final P f1602a = new P();
    public final ConcurrentHashMap b = new ConcurrentHashMap();

    public final Schema a(Class cls) {
        Schema schema;
        Class cls2;
        D.a(cls, "messageType");
        ConcurrentHashMap concurrentHashMap = this.b;
        Schema schema2 = (Schema) concurrentHashMap.get(cls);
        if (schema2 != null) {
            return schema2;
        }
        P p6 = this.f1602a;
        p6.getClass();
        Class cls3 = g0.f1606a;
        Class<GeneratedMessageLite> cls4 = GeneratedMessageLite.class;
        if (cls4.isAssignableFrom(cls) || (cls2 = g0.f1606a) == null || cls2.isAssignableFrom(cls)) {
            f0 a7 = ((O) p6.f1586a).a(cls);
            int i2 = a7.d;
            MessageLite messageLite = a7.f1603a;
            if ((i2 & 2) == 2) {
                if (cls4.isAssignableFrom(cls)) {
                    schema = new Y(g0.f1607c, C0147t.f1630a, messageLite);
                } else {
                    h0 h0Var = g0.b;
                    r rVar = C0147t.b;
                    if (rVar != null) {
                        schema = new Y(h0Var, rVar, messageLite);
                    } else {
                        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
                    }
                }
            } else if (cls4.isAssignableFrom(cls)) {
                if (N.f1584a[a7.a().ordinal()] != 1) {
                    schema = X.A(a7, a0.b, L.b, g0.f1607c, C0147t.f1630a, T.b);
                } else {
                    schema = X.A(a7, a0.b, L.b, g0.f1607c, (r) null, T.b);
                }
            } else if (N.f1584a[a7.a().ordinal()] != 1) {
                Z z = a0.f1599a;
                J j2 = L.f1583a;
                String str = "Protobuf runtime is not correctly loaded.";
                h0 h0Var2 = g0.b;
                r rVar2 = C0147t.b;
                if (rVar2 != null) {
                    schema = X.A(a7, z, j2, h0Var2, rVar2, T.f1587a);
                } else {
                    throw new IllegalStateException(str);
                }
            } else {
                schema = X.A(a7, a0.f1599a, L.f1583a, g0.b, (r) null, T.f1587a);
            }
            Schema schema3 = (Schema) concurrentHashMap.putIfAbsent(cls, schema);
            if (schema3 != null) {
                return schema3;
            }
            return schema;
        }
        throw new IllegalArgumentException("Message classes must extend GeneratedMessageV3 or GeneratedMessageLite");
    }
}
