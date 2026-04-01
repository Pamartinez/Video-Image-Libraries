package mg;

import ig.f;
import java.util.Set;
import kg.i0;
import kg.l0;
import kg.o0;
import kg.r0;
import kotlin.jvm.internal.j;
import ne.C1192j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class s {

    /* renamed from: a  reason: collision with root package name */
    public static final Set f4948a = C1192j.z0(new f[]{l0.b, o0.b, i0.b, r0.b});

    public static final boolean a(f fVar) {
        j.e(fVar, "<this>");
        if (!fVar.isInline() || !f4948a.contains(fVar)) {
            return false;
        }
        return true;
    }
}
