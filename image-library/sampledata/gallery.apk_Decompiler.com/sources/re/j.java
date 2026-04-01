package Re;

import Af.g;
import Hf.C0774x;
import L1.d;
import Ne.i;
import Qe.Q;
import java.util.Map;
import me.h;
import qf.C1236c;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class j implements b {

    /* renamed from: a  reason: collision with root package name */
    public final i f3693a;
    public final C1236c b;

    /* renamed from: c  reason: collision with root package name */
    public final Map f3694c;
    public final Object d = d.p(h.PUBLICATION, new g(19, this));

    public j(i iVar, C1236c cVar, Map map) {
        kotlin.jvm.internal.j.e(iVar, "builtIns");
        kotlin.jvm.internal.j.e(cVar, "fqName");
        this.f3693a = iVar;
        this.b = cVar;
        this.f3694c = map;
    }

    public final Map a() {
        return this.f3694c;
    }

    public final C1236c b() {
        return this.b;
    }

    public final Q getSource() {
        return Q.f3662a;
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [me.f, java.lang.Object] */
    public final C0774x getType() {
        Object value = this.d.getValue();
        kotlin.jvm.internal.j.d(value, "getValue(...)");
        return (C0774x) value;
    }
}
