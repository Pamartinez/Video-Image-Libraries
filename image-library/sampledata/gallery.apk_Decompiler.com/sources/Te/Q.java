package Te;

import Hf.C0774x;
import Hf.X;
import Oe.g;
import Qe.C0812b;
import Qe.C0813c;
import Qe.C0814d;
import Qe.C0822l;
import Qe.C0823m;
import Qe.C0824n;
import Qe.C0826p;
import Qe.C0827q;
import Qe.M;
import Qe.Y;
import Re.h;
import java.util.ArrayList;
import java.util.Collection;
import kotlin.jvm.internal.j;
import ne.C1196n;
import qf.C1240g;
import vf.C1327g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class Q extends S implements M, Y {

    /* renamed from: j  reason: collision with root package name */
    public final int f3770j;
    public final boolean k;
    public final boolean l;
    public final boolean m;
    public final C0774x n;

    /* renamed from: o  reason: collision with root package name */
    public final Q f3771o;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Q(Qe.C0812b r7, Te.Q r8, int r9, Re.h r10, qf.C1240g r11, Hf.C0774x r12, boolean r13, boolean r14, boolean r15, Hf.C0774x r16, Qe.Q r17) {
        /*
            r6 = this;
            java.lang.String r0 = "containingDeclaration"
            kotlin.jvm.internal.j.e(r7, r0)
            java.lang.String r0 = "annotations"
            kotlin.jvm.internal.j.e(r10, r0)
            java.lang.String r0 = "name"
            kotlin.jvm.internal.j.e(r11, r0)
            java.lang.String r0 = "outType"
            kotlin.jvm.internal.j.e(r12, r0)
            java.lang.String r0 = "source"
            r5 = r17
            kotlin.jvm.internal.j.e(r5, r0)
            r0 = r6
            r1 = r7
            r2 = r10
            r3 = r11
            r4 = r12
            r0.<init>(r1, r2, r3, r4, r5)
            r6.f3770j = r9
            r6.k = r13
            r6.l = r14
            r6.m = r15
            r1 = r16
            r6.n = r1
            if (r8 != 0) goto L_0x0033
            r1 = r6
            goto L_0x0034
        L_0x0033:
            r1 = r8
        L_0x0034:
            r6.f3771o = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Te.Q.<init>(Qe.b, Te.Q, int, Re.h, qf.g, Hf.x, boolean, boolean, boolean, Hf.x, Qe.Q):void");
    }

    public Q E0(g gVar, C1240g gVar2, int i2) {
        h annotations = getAnnotations();
        j.d(annotations, "<get-annotations>(...)");
        C0774x type = getType();
        j.d(type, "getType(...)");
        return new Q(gVar, (Q) null, i2, annotations, gVar2, type, F0(), this.l, this.m, this.n, Qe.Q.f3662a);
    }

    public final boolean F0() {
        if (!this.k) {
            return false;
        }
        C0813c b = ((C0814d) g()).b();
        b.getClass();
        if (b != C0813c.FAKE_OVERRIDE) {
            return true;
        }
        return false;
    }

    public final boolean G() {
        return false;
    }

    /* renamed from: G0 */
    public final C0812b g() {
        C0822l g = super.g();
        j.c(g, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.CallableDescriptor");
        return (C0812b) g;
    }

    /* renamed from: H0 */
    public final Q a() {
        Q q = this.f3771o;
        if (q == this) {
            return this;
        }
        return q.a();
    }

    public final C0823m c(X x9) {
        j.e(x9, "substitutor");
        if (x9.f3438a.e()) {
            return this;
        }
        throw new UnsupportedOperationException();
    }

    public final /* bridge */ /* synthetic */ C1327g d0() {
        return null;
    }

    public final C0826p getVisibility() {
        C0826p pVar = C0827q.f;
        j.d(pVar, "LOCAL");
        return pVar;
    }

    public final Collection h() {
        Collection h5 = g().h();
        j.d(h5, "getOverriddenDescriptors(...)");
        Iterable<C0812b> iterable = h5;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        for (C0812b B : iterable) {
            arrayList.add((Q) B.B().get(this.f3770j));
        }
        return arrayList;
    }

    public final Object v(C0824n nVar, Object obj) {
        return nVar.E(this, obj);
    }
}
