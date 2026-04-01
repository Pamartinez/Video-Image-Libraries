package Le;

import D0.c;
import L1.d;
import java.util.ArrayList;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class t extends q implements f {
    public final Object g;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public t(java.lang.reflect.Method r5, java.lang.Object r6) {
        /*
            r4 = this;
            java.lang.reflect.Type[] r0 = r5.getGenericParameterTypes()
            java.lang.String r1 = "getGenericParameterTypes(...)"
            kotlin.jvm.internal.j.d(r0, r1)
            int r1 = r0.length
            r2 = 0
            r3 = 1
            if (r1 > r3) goto L_0x0011
            java.lang.reflect.Type[] r0 = new java.lang.reflect.Type[r2]
            goto L_0x0016
        L_0x0011:
            int r1 = r0.length
            java.lang.Object[] r0 = ne.C1192j.i0(r0, r3, r1)
        L_0x0016:
            java.lang.reflect.Type[] r0 = (java.lang.reflect.Type[]) r0
            r4.<init>((java.lang.reflect.Method) r5, (boolean) r2, (java.lang.reflect.Type[]) r0)
            r4.g = r6
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Le.t.<init>(java.lang.reflect.Method, java.lang.Object):void");
    }

    public final Object call(Object[] objArr) {
        j.e(objArr, "args");
        d.b(this, objArr);
        c cVar = new c(2);
        cVar.a(this.g);
        cVar.b(objArr);
        ArrayList arrayList = cVar.d;
        return e(arrayList.toArray(new Object[arrayList.size()]), (Object) null);
    }
}
