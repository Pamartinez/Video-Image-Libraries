package Df;

import B1.b;
import Qe.Q;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import kotlin.jvm.internal.j;
import lf.C1156i;
import lf.C1157j;
import nf.C1208e;
import nf.C1209f;
import qf.C1235b;
import qf.C1236c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class x extends z {
    public final C1157j d;
    public final x e;
    public final C1235b f;
    public final C1156i g;

    /* renamed from: h  reason: collision with root package name */
    public final boolean f3368h;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public x(C1157j jVar, C1209f fVar, b bVar, Q q, x xVar) {
        super(fVar, bVar, q);
        j.e(jVar, "classProto");
        j.e(fVar, "nameResolver");
        this.d = jVar;
        this.e = xVar;
        this.f = c.A(fVar, jVar.f4850h);
        C1156i iVar = (C1156i) C1208e.f.c(jVar.g);
        this.g = iVar == null ? C1156i.CLASS : iVar;
        this.f3368h = C1208e.g.c(jVar.g).booleanValue();
        C1208e.f4967h.getClass();
    }

    public final C1236c a() {
        return this.f.a();
    }
}
