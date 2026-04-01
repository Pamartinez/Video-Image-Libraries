package Ke;

import Ae.a;
import me.f;

public final class p0 implements a {
    public final r0 d;
    public final int e;
    public final Object f;

    public p0(r0 r0Var, int i2, f fVar) {
        this.d = r0Var;
        this.e = i2;
        this.f = fVar;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: java.lang.reflect.Type} */
    /* JADX WARNING: type inference failed for: r4v2, types: [me.f, java.lang.Object] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invoke() {
        /*
            r4 = this;
            Ke.r0 r0 = r4.d
            Ke.x0 r1 = r0.e
            if (r1 == 0) goto L_0x000d
            java.lang.Object r1 = r1.invoke()
            java.lang.reflect.Type r1 = (java.lang.reflect.Type) r1
            goto L_0x000e
        L_0x000d:
            r1 = 0
        L_0x000e:
            boolean r2 = r1 instanceof java.lang.Class
            if (r2 == 0) goto L_0x0025
            java.lang.Class r1 = (java.lang.Class) r1
            boolean r4 = r1.isArray()
            if (r4 == 0) goto L_0x001f
            java.lang.Class r4 = r1.getComponentType()
            goto L_0x0021
        L_0x001f:
            java.lang.Class<java.lang.Object> r4 = java.lang.Object.class
        L_0x0021:
            kotlin.jvm.internal.j.b(r4)
            return r4
        L_0x0025:
            boolean r2 = r1 instanceof java.lang.reflect.GenericArrayType
            int r3 = r4.e
            if (r2 == 0) goto L_0x004c
            if (r3 != 0) goto L_0x0037
            java.lang.reflect.GenericArrayType r1 = (java.lang.reflect.GenericArrayType) r1
            java.lang.reflect.Type r4 = r1.getGenericComponentType()
            kotlin.jvm.internal.j.b(r4)
            return r4
        L_0x0037:
            Ke.v0 r4 = new Ke.v0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Array type has been queried for a non-0th argument: "
            r1.<init>(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r1 = 0
            r4.<init>(r0, r1)
            throw r4
        L_0x004c:
            boolean r1 = r1 instanceof java.lang.reflect.ParameterizedType
            if (r1 == 0) goto L_0x008a
            java.lang.Object r4 = r4.f
            java.lang.Object r4 = r4.getValue()
            java.util.List r4 = (java.util.List) r4
            java.lang.Object r4 = r4.get(r3)
            java.lang.reflect.Type r4 = (java.lang.reflect.Type) r4
            boolean r0 = r4 instanceof java.lang.reflect.WildcardType
            if (r0 != 0) goto L_0x0063
            return r4
        L_0x0063:
            java.lang.reflect.WildcardType r4 = (java.lang.reflect.WildcardType) r4
            java.lang.reflect.Type[] r0 = r4.getLowerBounds()
            java.lang.String r1 = "getLowerBounds(...)"
            kotlin.jvm.internal.j.d(r0, r1)
            java.lang.Object r0 = ne.C1192j.n0(r0)
            java.lang.reflect.Type r0 = (java.lang.reflect.Type) r0
            if (r0 != 0) goto L_0x0086
            java.lang.reflect.Type[] r4 = r4.getUpperBounds()
            java.lang.String r0 = "getUpperBounds(...)"
            kotlin.jvm.internal.j.d(r4, r0)
            java.lang.Object r4 = ne.C1192j.m0(r4)
            r0 = r4
            java.lang.reflect.Type r0 = (java.lang.reflect.Type) r0
        L_0x0086:
            kotlin.jvm.internal.j.b(r0)
            return r0
        L_0x008a:
            Ke.v0 r4 = new Ke.v0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Non-generic type has been queried for arguments: "
            r1.<init>(r2)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r1 = 0
            r4.<init>(r0, r1)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: Ke.p0.invoke():java.lang.Object");
    }
}
