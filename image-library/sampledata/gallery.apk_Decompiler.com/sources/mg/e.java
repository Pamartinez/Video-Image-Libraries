package mg;

import Ae.c;
import ig.f;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final /* synthetic */ class e extends h implements c {
    public final Object invoke(Object obj, Object obj2) {
        boolean z;
        f fVar = (f) obj;
        int intValue = ((Number) obj2).intValue();
        j.e(fVar, "p0");
        f fVar2 = (f) this.receiver;
        fVar2.getClass();
        if (fVar.j(intValue) || !fVar.h(intValue).c()) {
            z = false;
        } else {
            z = true;
        }
        fVar2.b = z;
        return Boolean.valueOf(z);
    }
}
