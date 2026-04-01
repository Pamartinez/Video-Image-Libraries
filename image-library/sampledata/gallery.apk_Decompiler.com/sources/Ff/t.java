package Ff;

import Ae.b;
import Af.f;
import Df.l;
import Qe.C0819i;
import Qe.H;
import Ye.a;
import Ye.c;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1200r;
import ne.v;
import qf.C1235b;
import qf.C1236c;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class t extends s {
    public final H g;

    /* renamed from: h  reason: collision with root package name */
    public final String f3398h;

    /* renamed from: i  reason: collision with root package name */
    public final C1236c f3399i;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public t(Qe.H r9, lf.C r10, nf.C1209f r11, nf.C1204a r12, jf.C1109i r13, Df.l r14, java.lang.String r15, Ae.a r16) {
        /*
            r8 = this;
            r7 = r15
            java.lang.String r0 = "proto"
            kotlin.jvm.internal.j.e(r10, r0)
            java.lang.String r0 = "nameResolver"
            kotlin.jvm.internal.j.e(r11, r0)
            java.lang.String r0 = "metadataVersion"
            kotlin.jvm.internal.j.e(r12, r0)
            java.lang.String r0 = "components"
            kotlin.jvm.internal.j.e(r14, r0)
            java.lang.String r0 = "debugName"
            kotlin.jvm.internal.j.e(r15, r0)
            B1.b r3 = new B1.b
            lf.X r0 = r10.f4735j
            java.lang.String r4 = "getTypeTable(...)"
            kotlin.jvm.internal.j.d(r0, r4)
            r3.<init>((lf.X) r0)
            nf.h r0 = nf.C1211h.b
            lf.e0 r0 = r10.k
            java.lang.String r4 = "getVersionRequirementTable(...)"
            kotlin.jvm.internal.j.d(r0, r4)
            nf.h r4 = He.F.x(r0)
            r1 = r9
            r2 = r11
            r5 = r12
            r6 = r13
            r0 = r14
            Df.n r0 = r0.a(r1, r2, r3, r4, r5, r6)
            java.util.List r2 = r10.g
            java.lang.String r1 = "getFunctionList(...)"
            kotlin.jvm.internal.j.d(r2, r1)
            java.util.List r3 = r10.f4733h
            java.lang.String r1 = "getPropertyList(...)"
            kotlin.jvm.internal.j.d(r3, r1)
            java.util.List r4 = r10.f4734i
            java.lang.String r1 = "getTypeAliasList(...)"
            kotlin.jvm.internal.j.d(r4, r1)
            r5 = r16
            r1 = r0
            r0 = r8
            r0.<init>(r1, r2, r3, r4, r5)
            r8.g = r9
            r8.f3398h = r7
            r1 = r9
            Te.B r1 = (Te.B) r1
            qf.c r1 = r1.f3743i
            r8.f3399i = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Ff.t.<init>(Qe.H, lf.C, nf.f, nf.a, jf.i, Df.l, java.lang.String, Ae.a):void");
    }

    public final C0819i c(C1240g gVar, a aVar) {
        j.e(gVar, "name");
        j.e(aVar, "location");
        Gd.a.d0(((l) this.b.f3358a).f3352i, aVar, this.g, gVar);
        return super.c(gVar, aVar);
    }

    public final Collection d(f fVar, b bVar) {
        j.e(fVar, "kindFilter");
        Collection i2 = i(fVar, bVar, c.WHEN_GET_ALL_DESCRIPTORS);
        Iterable<Se.c> iterable = ((l) this.b.f3358a).k;
        ArrayList arrayList = new ArrayList();
        for (Se.c c5 : iterable) {
            C1200r.A0(c5.c(this.f3399i), arrayList);
        }
        return C1194l.X0(arrayList, i2);
    }

    public final C1235b l(C1240g gVar) {
        j.e(gVar, "name");
        return new C1235b(this.f3399i, gVar);
    }

    public final Set n() {
        return v.d;
    }

    public final Set o() {
        return v.d;
    }

    public final Set p() {
        return v.d;
    }

    public final boolean q(C1240g gVar) {
        j.e(gVar, "name");
        if (super.q(gVar)) {
            return true;
        }
        Iterable<Se.c> iterable = ((l) this.b.f3358a).k;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return false;
        }
        for (Se.c b : iterable) {
            if (b.b(this.f3399i, gVar)) {
                return true;
            }
        }
        return false;
    }

    public final String toString() {
        return this.f3398h;
    }

    public final void h(ArrayList arrayList, b bVar) {
    }
}
