package kg;

import Ae.a;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.k;
import mg.h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f0 extends k implements a {
    public final /* synthetic */ int d;
    public final /* synthetic */ mg.a e;
    public final /* synthetic */ gg.a f;
    public final /* synthetic */ Object g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ f0(mg.a aVar, gg.a aVar2, Object obj, int i2) {
        super(0);
        this.d = i2;
        this.e = aVar;
        this.f = aVar2;
        this.g = obj;
    }

    public final Object invoke() {
        switch (this.d) {
            case 0:
                gg.a aVar = this.f;
                boolean c5 = aVar.getDescriptor().c();
                mg.a aVar2 = this.e;
                if (c5 || aVar2.D()) {
                    return h.h(aVar2, aVar);
                }
                return null;
            default:
                gg.a aVar3 = this.f;
                j.e(aVar3, "deserializer");
                return h.h(this.e, aVar3);
        }
    }
}
