package Ke;

import Ae.b;
import D0.e;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: Ke.c  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0781c {

    /* renamed from: a  reason: collision with root package name */
    public static final e f3500a;
    public static final e b = new e((b) C0779b.f);

    static {
        C0779b bVar = C0779b.e;
        int i2 = C0777a.f3496a;
        f3500a = new e((b) bVar);
        new ConcurrentHashMap();
        new ConcurrentHashMap();
        new ConcurrentHashMap();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0014, code lost:
        r2 = ((Ae.b) r0.e).invoke(r3);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final Ke.B a(java.lang.Class r3) {
        /*
            java.lang.String r0 = "jClass"
            kotlin.jvm.internal.j.e(r3, r0)
            D0.e r0 = f3500a
            r0.getClass()
            java.lang.Object r1 = r0.f
            java.util.concurrent.ConcurrentHashMap r1 = (java.util.concurrent.ConcurrentHashMap) r1
            java.lang.Object r2 = r1.get(r3)
            if (r2 != 0) goto L_0x0024
            java.lang.Object r0 = r0.e
            Ae.b r0 = (Ae.b) r0
            java.lang.Object r2 = r0.invoke(r3)
            java.lang.Object r3 = r1.putIfAbsent(r3, r2)
            if (r3 != 0) goto L_0x0023
            goto L_0x0024
        L_0x0023:
            r2 = r3
        L_0x0024:
            java.lang.String r3 = "null cannot be cast to non-null type kotlin.reflect.jvm.internal.KClassImpl<T of kotlin.reflect.jvm.internal.CachesKt.getOrCreateKotlinClass>"
            kotlin.jvm.internal.j.c(r2, r3)
            Ke.B r2 = (Ke.B) r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: Ke.C0781c.a(java.lang.Class):Ke.B");
    }
}
