package We;

import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class A extends w {

    /* renamed from: a  reason: collision with root package name */
    public final Object f3877a;

    public A(Object obj) {
        j.e(obj, "recordComponent");
        this.f3877a = obj;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.reflect.Method} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.reflect.Member b() {
        /*
            r5 = this;
            java.lang.String r0 = "recordComponent"
            java.lang.Object r5 = r5.f3877a
            kotlin.jvm.internal.j.e(r5, r0)
            We.a r0 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.f4196c
            r1 = 0
            if (r0 != 0) goto L_0x002a
            java.lang.Class r0 = r5.getClass()
            We.a r2 = new We.a     // Catch:{ NoSuchMethodException -> 0x0023 }
            java.lang.String r3 = "getType"
            java.lang.reflect.Method r3 = r0.getMethod(r3, r1)     // Catch:{ NoSuchMethodException -> 0x0023 }
            java.lang.String r4 = "getAccessor"
            java.lang.reflect.Method r0 = r0.getMethod(r4, r1)     // Catch:{ NoSuchMethodException -> 0x0023 }
            r2.<init>(r3, r0)     // Catch:{ NoSuchMethodException -> 0x0023 }
            r0 = r2
            goto L_0x0028
        L_0x0023:
            We.a r0 = new We.a
            r0.<init>(r1, r1)
        L_0x0028:
            com.samsung.context.sdk.samsunganalytics.internal.sender.c.f4196c = r0
        L_0x002a:
            java.lang.reflect.Method r0 = r0.b
            if (r0 != 0) goto L_0x002f
            goto L_0x003b
        L_0x002f:
            java.lang.Object r5 = r0.invoke(r5, r1)
            java.lang.String r0 = "null cannot be cast to non-null type java.lang.reflect.Method"
            kotlin.jvm.internal.j.c(r5, r0)
            r1 = r5
            java.lang.reflect.Method r1 = (java.lang.reflect.Method) r1
        L_0x003b:
            if (r1 == 0) goto L_0x003e
            return r1
        L_0x003e:
            java.lang.NoSuchMethodError r5 = new java.lang.NoSuchMethodError
            java.lang.String r0 = "Can't find `getAccessor` method"
            r5.<init>(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: We.A.b():java.lang.reflect.Member");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Class} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final gf.C1073d f() {
        /*
            r5 = this;
            java.lang.String r0 = "recordComponent"
            java.lang.Object r5 = r5.f3877a
            kotlin.jvm.internal.j.e(r5, r0)
            We.a r0 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.f4196c
            r1 = 0
            if (r0 != 0) goto L_0x002a
            java.lang.Class r0 = r5.getClass()
            We.a r2 = new We.a     // Catch:{ NoSuchMethodException -> 0x0023 }
            java.lang.String r3 = "getType"
            java.lang.reflect.Method r3 = r0.getMethod(r3, r1)     // Catch:{ NoSuchMethodException -> 0x0023 }
            java.lang.String r4 = "getAccessor"
            java.lang.reflect.Method r0 = r0.getMethod(r4, r1)     // Catch:{ NoSuchMethodException -> 0x0023 }
            r2.<init>(r3, r0)     // Catch:{ NoSuchMethodException -> 0x0023 }
            r0 = r2
            goto L_0x0028
        L_0x0023:
            We.a r0 = new We.a
            r0.<init>(r1, r1)
        L_0x0028:
            com.samsung.context.sdk.samsunganalytics.internal.sender.c.f4196c = r0
        L_0x002a:
            java.lang.reflect.Method r0 = r0.f3882a
            if (r0 != 0) goto L_0x002f
            goto L_0x003b
        L_0x002f:
            java.lang.Object r5 = r0.invoke(r5, r1)
            java.lang.String r0 = "null cannot be cast to non-null type java.lang.Class<*>"
            kotlin.jvm.internal.j.c(r5, r0)
            r1 = r5
            java.lang.Class r1 = (java.lang.Class) r1
        L_0x003b:
            if (r1 == 0) goto L_0x0043
            We.q r5 = new We.q
            r5.<init>(r1)
            return r5
        L_0x0043:
            java.lang.NoSuchMethodError r5 = new java.lang.NoSuchMethodError
            java.lang.String r0 = "Can't find `getType` method"
            r5.<init>(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: We.A.f():gf.d");
    }
}
