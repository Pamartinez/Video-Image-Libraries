package Ef;

import Tf.v;
import kotlin.jvm.internal.j;
import mf.C1179b;
import qf.C1236c;
import rf.C1258h;
import rf.C1266p;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a extends Cf.a {
    public static final a m;

    /* JADX WARNING: type inference failed for: r0v0, types: [Ef.a, Cf.a] */
    static {
        C1258h hVar = new C1258h();
        C1179b.a(hVar);
        C1266p pVar = C1179b.f4918a;
        j.d(pVar, "packageFqName");
        C1266p pVar2 = C1179b.f4919c;
        j.d(pVar2, "constructorAnnotation");
        C1266p pVar3 = C1179b.b;
        j.d(pVar3, "classAnnotation");
        C1266p pVar4 = C1179b.d;
        j.d(pVar4, "functionAnnotation");
        C1266p pVar5 = C1179b.e;
        j.d(pVar5, "propertyAnnotation");
        C1266p pVar6 = C1179b.f;
        j.d(pVar6, "propertyGetterAnnotation");
        C1266p pVar7 = C1179b.g;
        j.d(pVar7, "propertySetterAnnotation");
        C1266p pVar8 = C1179b.f4921i;
        j.d(pVar8, "enumEntryAnnotation");
        C1266p pVar9 = C1179b.f4920h;
        j.d(pVar9, "compileTimeValue");
        C1266p pVar10 = C1179b.f4922j;
        j.d(pVar10, "parameterAnnotation");
        C1266p pVar11 = C1179b.k;
        j.d(pVar11, "typeAnnotation");
        C1266p pVar12 = C1179b.l;
        j.d(pVar12, "typeParameterAnnotation");
        m = new Cf.a(hVar, pVar, pVar2, pVar3, pVar4, pVar5, pVar6, pVar7, pVar8, pVar9, pVar10, pVar11, pVar12);
    }

    public static String a(C1236c cVar) {
        String str;
        j.e(cVar, "fqName");
        StringBuilder sb2 = new StringBuilder();
        sb2.append(v.r0(cVar.b(), '.', '/'));
        sb2.append('/');
        if (cVar.d()) {
            str = "default-package";
        } else {
            str = cVar.f().b();
            j.d(str, "asString(...)");
        }
        sb2.append(str.concat(".kotlin_builtins"));
        return sb2.toString();
    }
}
