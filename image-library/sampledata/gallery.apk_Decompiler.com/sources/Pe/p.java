package Pe;

import Ae.b;
import L2.a;
import Qe.C0814d;
import Qe.C0816f;
import Qf.k;
import java.io.Serializable;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.u;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class p extends k {
    public final /* synthetic */ int b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ Object f3645c;
    public final /* synthetic */ Serializable d;

    public /* synthetic */ p(Object obj, Serializable serializable, int i2) {
        this.b = i2;
        this.f3645c = obj;
        this.d = serializable;
    }

    public void b(Object obj) {
        switch (this.b) {
            case 2:
                C0814d dVar = (C0814d) obj;
                j.e(dVar, "current");
                u uVar = (u) this.d;
                if (uVar.d == null && ((Boolean) ((b) this.f3645c).invoke(dVar)).booleanValue()) {
                    uVar.d = dVar;
                    return;
                }
                return;
            default:
                return;
        }
    }

    public final boolean c(Object obj) {
        switch (this.b) {
            case 0:
                C0816f fVar = (C0816f) obj;
                u uVar = (u) this.d;
                j.e(fVar, "javaClassDescriptor");
                String z = a.z(fVar, (String) this.f3645c);
                if (t.b.contains(z)) {
                    uVar.d = m.HIDDEN;
                } else if (t.d.contains(z)) {
                    uVar.d = m.VISIBLE;
                } else if (t.f3653c.contains(z)) {
                    uVar.d = m.DEPRECATED_LIST_METHODS;
                } else if (t.f3652a.contains(z)) {
                    uVar.d = m.DROP;
                }
                if (uVar.d == null) {
                    return true;
                }
                return false;
            case 1:
                boolean[] zArr = (boolean[]) this.d;
                if (((Boolean) ((b) this.f3645c).invoke(obj)).booleanValue()) {
                    zArr[0] = true;
                }
                return !zArr[0];
            default:
                j.e((C0814d) obj, "current");
                if (((u) this.d).d == null) {
                    return true;
                }
                return false;
        }
    }

    public final Object j() {
        switch (this.b) {
            case 0:
                m mVar = (m) ((u) this.d).d;
                if (mVar == null) {
                    return m.NOT_CONSIDERED;
                }
                return mVar;
            case 1:
                return Boolean.valueOf(((boolean[]) this.d)[0]);
            default:
                return (C0814d) ((u) this.d).d;
        }
    }

    public p(u uVar, b bVar) {
        this.b = 2;
        this.d = uVar;
        this.f3645c = bVar;
    }
}
