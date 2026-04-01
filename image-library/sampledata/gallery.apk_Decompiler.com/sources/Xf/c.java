package Xf;

import re.C1245a;
import se.C1271c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c extends C1271c {
    public /* synthetic */ Object d;
    public final /* synthetic */ e e;
    public int f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public c(e eVar, C1271c cVar) {
        super(cVar);
        this.e = eVar;
    }

    public final Object invokeSuspend(Object obj) {
        this.d = obj;
        this.f |= Integer.MIN_VALUE;
        Object z = e.z(this.e, this);
        if (z == C1245a.COROUTINE_SUSPENDED) {
            return z;
        }
        return new l(z);
    }
}
