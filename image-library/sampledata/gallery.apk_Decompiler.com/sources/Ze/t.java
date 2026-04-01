package Ze;

import kotlin.jvm.internal.j;
import me.e;
import qf.C1236c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class t {

    /* renamed from: c  reason: collision with root package name */
    public static final t f3957c;

    /* renamed from: a  reason: collision with root package name */
    public final v f3958a;
    public final boolean b;

    static {
        C c5;
        C c6;
        C1236c cVar = q.f3953a;
        e eVar = e.f4914h;
        j.e(eVar, "configuredKotlinVersion");
        r rVar = q.d;
        e eVar2 = rVar.b;
        if (eVar2 == null || eVar2.g - eVar.g > 0) {
            c5 = rVar.f3955a;
        } else {
            c5 = rVar.f3956c;
        }
        j.e(c5, "globalReportLevel");
        if (c5 == C.WARN) {
            c6 = null;
        } else {
            c6 = c5;
        }
        v vVar = new v(c5, c6);
        s sVar = s.d;
        f3957c = new t(vVar);
    }

    public t(v vVar) {
        boolean z;
        s sVar = s.d;
        this.f3958a = vVar;
        if (vVar.d || sVar.invoke(q.f3953a) == C.IGNORE) {
            z = true;
        } else {
            z = false;
        }
        this.b = z;
    }

    public final String toString() {
        return "JavaTypeEnhancementState(jsr305=" + this.f3958a + ", getReportLevelForAnnotation=" + s.d + ')';
    }
}
