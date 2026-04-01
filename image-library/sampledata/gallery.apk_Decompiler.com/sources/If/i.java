package If;

import Ae.a;
import Af.g;
import Ff.e;
import Hf.C0774x;
import Hf.P;
import L1.d;
import Qe.C0819i;
import Qe.V;
import com.samsung.context.sdk.samsunganalytics.internal.sender.c;
import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.j;
import me.h;
import ne.C1202t;
import uf.C1317b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class i implements C1317b {

    /* renamed from: a  reason: collision with root package name */
    public final P f3466a;
    public a b;

    /* renamed from: c  reason: collision with root package name */
    public final i f3467c;
    public final V d;
    public final Object e;

    public i(P p6, a aVar, i iVar, V v) {
        j.e(p6, "projection");
        this.f3466a = p6;
        this.b = aVar;
        this.f3467c = iVar;
        this.d = v;
        this.e = d.p(h.PUBLICATION, new g(10, this));
    }

    public final P a() {
        return this.f3466a;
    }

    public final boolean equals(Object obj) {
        Class<?> cls;
        if (this == obj) {
            return true;
        }
        if (obj != null) {
            cls = obj.getClass();
        } else {
            cls = null;
        }
        if (!i.class.equals(cls)) {
            return false;
        }
        j.c(obj, "null cannot be cast to non-null type org.jetbrains.kotlin.types.checker.NewCapturedTypeConstructor");
        i iVar = (i) obj;
        i iVar2 = this.f3467c;
        if (iVar2 != null) {
            this = iVar2;
        }
        i iVar3 = iVar.f3467c;
        if (iVar3 != null) {
            obj = iVar3;
        }
        if (this == obj) {
            return true;
        }
        return false;
    }

    public final Ne.i f() {
        C0774x b5 = this.f3466a.b();
        j.d(b5, "getType(...)");
        return c.z(b5);
    }

    public final C0819i g() {
        return null;
    }

    public final List getParameters() {
        return C1202t.d;
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [me.f, java.lang.Object] */
    public final Collection h() {
        Collection collection = (List) this.e.getValue();
        if (collection == null) {
            collection = C1202t.d;
        }
        return collection;
    }

    public final int hashCode() {
        i iVar = this.f3467c;
        if (iVar != null) {
            return iVar.hashCode();
        }
        return super.hashCode();
    }

    public final boolean i() {
        return false;
    }

    public final String toString() {
        return "CapturedType(" + this.f3466a + ')';
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ i(P p6, e eVar, V v, int i2) {
        this(p6, (a) (i2 & 2) != 0 ? null : eVar, (i) null, (i2 & 8) != 0 ? null : v);
    }
}
