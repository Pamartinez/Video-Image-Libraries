package gg;

import Ae.b;
import ig.a;
import ig.f;
import java.util.List;
import java.util.Map;
import kg.e0;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.k;
import me.x;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c extends k implements b {
    public final /* synthetic */ int d;
    public final /* synthetic */ e e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ c(e eVar, int i2) {
        super(1);
        this.d = i2;
        this.e = eVar;
    }

    public final Object invoke(Object obj) {
        switch (this.d) {
            case 0:
                a aVar = (a) obj;
                j.e(aVar, "$this$buildSerialDescriptor");
                for (Map.Entry entry : this.e.d.entrySet()) {
                    a.a(aVar, (String) entry.getKey(), ((a) entry.getValue()).getDescriptor());
                }
                return x.f4917a;
            default:
                a aVar2 = (a) obj;
                j.e(aVar2, "$this$buildSerialDescriptor");
                a.a(aVar2, "type", e0.b);
                StringBuilder sb2 = new StringBuilder("kotlinx.serialization.Sealed<");
                e eVar = this.e;
                sb2.append(eVar.f4578a.o());
                sb2.append('>');
                c cVar = new c(eVar, 0);
                a.a(aVar2, "value", L2.a.e(sb2.toString(), ig.j.d, new f[0], cVar));
                List list = eVar.b;
                j.e(list, "<set-?>");
                aVar2.b = list;
                return x.f4917a;
        }
    }
}
