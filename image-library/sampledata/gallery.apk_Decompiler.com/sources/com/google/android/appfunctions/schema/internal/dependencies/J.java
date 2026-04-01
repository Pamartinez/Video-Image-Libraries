package com.google.android.appfunctions.schema.internal.dependencies;

import java.nio.charset.Charset;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class J {

    /* renamed from: c  reason: collision with root package name */
    public static final J f1206c = new J();

    /* renamed from: a  reason: collision with root package name */
    public final C0114y f1207a = new C0114y();
    public final ConcurrentHashMap b = new ConcurrentHashMap();

    public final M a(Class cls) {
        M m;
        Charset charset = C0106p.f1226a;
        if (cls != null) {
            ConcurrentHashMap concurrentHashMap = this.b;
            M m4 = (M) concurrentHashMap.get(cls);
            if (m4 != null) {
                return m4;
            }
            C0114y yVar = this.f1207a;
            yVar.getClass();
            Class cls2 = N.f1211a;
            Class<C0102l> cls3 = C0102l.class;
            if (!cls3.isAssignableFrom(cls)) {
                Class cls4 = b0.f1220a;
                Class cls5 = N.f1211a;
                if (cls5 != null && !cls5.isAssignableFrom(cls)) {
                    throw new IllegalArgumentException("Message classes must extend GeneratedMessage or GeneratedMessageLite");
                }
            }
            L b5 = ((C0113x) yVar.f1229a).b(cls);
            int i2 = b5.d;
            A a7 = b5.f1209a;
            if ((i2 & 2) == 2) {
                Class cls6 = b0.f1220a;
                if (cls3.isAssignableFrom(cls)) {
                    m = new F(N.f1212c, C0095e.f1222a, a7);
                } else {
                    P p6 = N.b;
                    C0094d dVar = C0095e.b;
                    if (dVar != null) {
                        m = new F(p6, dVar, a7);
                    } else {
                        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
                    }
                }
            } else {
                Class cls7 = b0.f1220a;
                C0094d dVar2 = null;
                if (cls3.isAssignableFrom(cls)) {
                    int i7 = H.f1205a;
                    int i8 = C0111v.f1227a;
                    P p8 = N.f1212c;
                    if (b5.a() - 1 != 1) {
                        dVar2 = C0095e.f1222a;
                    }
                    int i10 = B.f1198a;
                    m = E.s(b5, p8, dVar2);
                } else {
                    int i11 = H.f1205a;
                    int i12 = C0111v.f1227a;
                    P p10 = N.b;
                    if (b5.a() - 1 == 1 || (dVar2 = C0095e.b) != null) {
                        int i13 = B.f1198a;
                        m = E.s(b5, p10, dVar2);
                    } else {
                        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
                    }
                }
            }
            M m8 = (M) concurrentHashMap.putIfAbsent(cls, m);
            if (m8 != null) {
                return m8;
            }
            return m;
        }
        throw new NullPointerException("messageType");
    }
}
