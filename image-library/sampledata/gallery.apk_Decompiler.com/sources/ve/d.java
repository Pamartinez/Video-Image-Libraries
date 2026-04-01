package Ve;

import Df.p;
import Qe.C0814d;
import Qe.C0816f;
import We.s;
import gf.C1072c;
import java.util.ArrayList;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class d implements p {
    public static final d b = new Object();

    /* renamed from: c  reason: collision with root package name */
    public static final d f3831c = new Object();

    public void a(C0816f fVar, ArrayList arrayList) {
        throw new IllegalStateException("Incomplete hierarchy for class " + fVar.getName() + ", unresolved classes " + arrayList);
    }

    public f b(C1072c cVar) {
        j.e(cVar, "javaElement");
        return new f((s) cVar);
    }

    public void c(C0814d dVar) {
        j.e(dVar, "descriptor");
        throw new IllegalStateException("Cannot infer visibility for " + dVar);
    }
}
