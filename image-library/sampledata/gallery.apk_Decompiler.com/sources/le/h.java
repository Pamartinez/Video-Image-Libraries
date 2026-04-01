package Le;

import D0.c;
import L1.d;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h extends w implements f {
    public final /* synthetic */ int e;
    public final Object f;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public h(java.lang.reflect.Constructor r4, java.lang.Object r5, int r6) {
        /*
            r3 = this;
            r3.e = r6
            switch(r6) {
                case 1: goto L_0x002f;
                default: goto L_0x0005;
            }
        L_0x0005:
            java.lang.Class r6 = r4.getDeclaringClass()
            java.lang.String r0 = "getDeclaringClass(...)"
            kotlin.jvm.internal.j.d(r6, r0)
            java.lang.reflect.Type[] r0 = r4.getGenericParameterTypes()
            java.lang.String r1 = "getGenericParameterTypes(...)"
            kotlin.jvm.internal.j.d(r0, r1)
            int r1 = r0.length
            r2 = 2
            if (r1 > r2) goto L_0x001f
            r0 = 0
            java.lang.reflect.Type[] r0 = new java.lang.reflect.Type[r0]
            goto L_0x0026
        L_0x001f:
            int r1 = r0.length
            r2 = 1
            int r1 = r1 - r2
            java.lang.Object[] r0 = ne.C1192j.i0(r0, r2, r1)
        L_0x0026:
            java.lang.reflect.Type[] r0 = (java.lang.reflect.Type[]) r0
            r1 = 0
            r3.<init>(r4, r6, r1, r0)
            r3.f = r5
            return
        L_0x002f:
            java.lang.Class r6 = r4.getDeclaringClass()
            java.lang.String r0 = "getDeclaringClass(...)"
            kotlin.jvm.internal.j.d(r6, r0)
            java.lang.reflect.Type[] r0 = r4.getGenericParameterTypes()
            java.lang.String r1 = "getGenericParameterTypes(...)"
            kotlin.jvm.internal.j.d(r0, r1)
            r1 = 0
            r3.<init>(r4, r6, r1, r0)
            r3.f = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Le.h.<init>(java.lang.reflect.Constructor, java.lang.Object, int):void");
    }

    public final Object call(Object[] objArr) {
        switch (this.e) {
            case 0:
                j.e(objArr, "args");
                d.b(this, objArr);
                c cVar = new c(3);
                cVar.a(this.f);
                cVar.b(objArr);
                cVar.a((Object) null);
                ArrayList arrayList = cVar.d;
                return ((Constructor) this.f3530a).newInstance(arrayList.toArray(new Object[arrayList.size()]));
            default:
                j.e(objArr, "args");
                d.b(this, objArr);
                c cVar2 = new c(2);
                cVar2.a(this.f);
                cVar2.b(objArr);
                ArrayList arrayList2 = cVar2.d;
                return ((Constructor) this.f3530a).newInstance(arrayList2.toArray(new Object[arrayList2.size()]));
        }
    }
}
