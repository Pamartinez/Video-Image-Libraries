package Le;

import D0.c;
import L1.d;
import java.util.ArrayList;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class u extends q implements f {
    public final Object[] g;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public u(java.lang.reflect.Method r4, java.lang.Object[] r5) {
        /*
            r3 = this;
            java.lang.String r0 = "method"
            kotlin.jvm.internal.j.e(r4, r0)
            java.lang.String r0 = "boundReceiverComponents"
            kotlin.jvm.internal.j.e(r5, r0)
            java.lang.reflect.Type[] r0 = r4.getGenericParameterTypes()
            java.lang.String r1 = "getGenericParameterTypes(...)"
            kotlin.jvm.internal.j.d(r0, r1)
            int r1 = r5.length
            java.util.List r0 = ne.C1192j.j0(r1, r0)
            java.util.Collection r0 = (java.util.Collection) r0
            r1 = 0
            java.lang.reflect.Type[] r2 = new java.lang.reflect.Type[r1]
            java.lang.Object[] r0 = r0.toArray(r2)
            java.lang.reflect.Type[] r0 = (java.lang.reflect.Type[]) r0
            r3.<init>((java.lang.reflect.Method) r4, (boolean) r1, (java.lang.reflect.Type[]) r0)
            r3.g = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Le.u.<init>(java.lang.reflect.Method, java.lang.Object[]):void");
    }

    public final Object call(Object[] objArr) {
        j.e(objArr, "args");
        d.b(this, objArr);
        c cVar = new c(2);
        cVar.b(this.g);
        cVar.b(objArr);
        ArrayList arrayList = cVar.d;
        return e(arrayList.toArray(new Object[arrayList.size()]), (Object) null);
    }
}
