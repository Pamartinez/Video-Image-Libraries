package re;

import Ae.c;
import L2.a;
import kotlin.jvm.internal.y;
import qe.C1227c;
import se.g;

/* renamed from: re.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1246b extends g {
    public int d;
    public final /* synthetic */ c e;
    public final /* synthetic */ C1227c f;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1246b(c cVar, C1227c cVar2, C1227c cVar3) {
        super(cVar2);
        this.e = cVar;
        this.f = cVar3;
    }

    public final Object invokeSuspend(Object obj) {
        int i2 = this.d;
        if (i2 == 0) {
            this.d = 1;
            a.A(obj);
            c cVar = this.e;
            y.c(2, cVar);
            return cVar.invoke(this.f, this);
        } else if (i2 == 1) {
            this.d = 2;
            a.A(obj);
            return obj;
        } else {
            throw new IllegalStateException("This coroutine had already completed");
        }
    }
}
