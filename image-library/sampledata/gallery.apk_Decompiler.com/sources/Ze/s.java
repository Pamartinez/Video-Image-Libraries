package Ze;

import Ae.b;
import D0.e;
import He.C0750f;
import kotlin.jvm.internal.g;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;
import qf.C1236c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class s extends g implements b {
    public static final s d = new g(1);

    public final String getName() {
        return "getDefaultReportLevelForAnnotation";
    }

    public final C0750f getOwner() {
        return v.f4727a.c(q.class, "compiler.common.jvm");
    }

    public final String getSignature() {
        return "getDefaultReportLevelForAnnotation(Lorg/jetbrains/kotlin/name/FqName;)Lorg/jetbrains/kotlin/load/java/ReportLevel;";
    }

    public final Object invoke(Object obj) {
        C1236c cVar = (C1236c) obj;
        j.e(cVar, "p0");
        C1236c cVar2 = q.f3953a;
        A.f3929c.getClass();
        e eVar = z.b;
        me.e eVar2 = new me.e(1, 7, 20);
        j.e(eVar, "configuredReportLevels");
        C c5 = (C) ((Gf.j) eVar.f).invoke(cVar);
        if (c5 != null) {
            return c5;
        }
        e eVar3 = q.f3954c;
        eVar3.getClass();
        r rVar = (r) ((Gf.j) eVar3.f).invoke(cVar);
        if (rVar == null) {
            return C.IGNORE;
        }
        me.e eVar4 = rVar.b;
        if (eVar4 == null || eVar4.g - eVar2.g > 0) {
            return rVar.f3955a;
        }
        return rVar.f3956c;
    }
}
