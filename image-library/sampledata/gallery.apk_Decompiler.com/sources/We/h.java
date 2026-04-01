package We;

import gf.C1070a;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.j;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class h extends f implements C1070a {
    public final Object[] b;

    public h(C1240g gVar, Object[] objArr) {
        super(gVar);
        this.b = objArr;
    }

    public final ArrayList a() {
        Object obj;
        Object[] objArr = this.b;
        ArrayList arrayList = new ArrayList(objArr.length);
        for (Object obj2 : objArr) {
            j.b(obj2);
            Class<?> cls = obj2.getClass();
            List list = C0892d.f3885a;
            if (Enum.class.isAssignableFrom(cls)) {
                obj = new t((C1240g) null, (Enum) obj2);
            } else if (obj2 instanceof Annotation) {
                obj = new g((C1240g) null, (Annotation) obj2);
            } else if (obj2 instanceof Object[]) {
                obj = new h((C1240g) null, (Object[]) obj2);
            } else if (obj2 instanceof Class) {
                obj = new p((C1240g) null, (Class) obj2);
            } else {
                obj = new v((C1240g) null, obj2);
            }
            arrayList.add(obj);
        }
        return arrayList;
    }
}
