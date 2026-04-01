package Oe;

import Gf.m;
import Qe.C;
import Se.c;
import Te.z;
import Tf.v;
import java.util.Collection;
import kotlin.jvm.internal.j;
import qf.C1236c;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a implements c {

    /* renamed from: a  reason: collision with root package name */
    public final m f3611a;
    public final C b;

    public a(m mVar, z zVar) {
        j.e(zVar, "module");
        this.f3611a = mVar;
        this.b = zVar;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0024, code lost:
        r6 = r6.f5033a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final Qe.C0816f a(qf.C1235b r6) {
        /*
            r5 = this;
            java.lang.String r0 = "classId"
            kotlin.jvm.internal.j.e(r6, r0)
            qf.c r0 = r6.b
            boolean r1 = r6.f5034c
            if (r1 != 0) goto L_0x0092
            qf.c r1 = r0.e()
            boolean r1 = r1.d()
            if (r1 != 0) goto L_0x0017
            goto L_0x0092
        L_0x0017:
            java.lang.String r0 = r0.b()
            java.lang.String r1 = "Function"
            boolean r1 = Tf.n.u0(r0, r1)
            if (r1 != 0) goto L_0x0024
            goto L_0x0092
        L_0x0024:
            qf.c r6 = r6.f5033a
            Oe.n r1 = Oe.n.f3624c
            Oe.m r0 = r1.a(r0, r6)
            if (r0 != 0) goto L_0x002f
            goto L_0x0092
        L_0x002f:
            Oe.l r1 = r0.f3623a
            int r0 = r0.b
            Qe.C r2 = r5.b
            Qe.L r6 = r2.n0(r6)
            Te.w r6 = (Te.w) r6
            Gf.i r6 = r6.f3807i
            He.t[] r2 = Te.w.l
            r3 = 0
            r2 = r2[r3]
            java.lang.Object r6 = D1.f.y(r6, r2)
            java.util.List r6 = (java.util.List) r6
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r6 = r6.iterator()
        L_0x0053:
            boolean r3 = r6.hasNext()
            if (r3 == 0) goto L_0x0065
            java.lang.Object r3 = r6.next()
            boolean r4 = r3 instanceof Ef.d
            if (r4 == 0) goto L_0x0053
            r2.add(r3)
            goto L_0x0053
        L_0x0065:
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.Iterator r3 = r2.iterator()
        L_0x006e:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0078
            r3.next()
            goto L_0x006e
        L_0x0078:
            java.lang.Object r6 = ne.C1194l.N0(r6)
            if (r6 != 0) goto L_0x008c
            java.lang.Object r6 = ne.C1194l.L0(r2)
            Ef.d r6 = (Ef.d) r6
            Oe.c r2 = new Oe.c
            Gf.m r5 = r5.f3611a
            r2.<init>(r5, r6, r1, r0)
            return r2
        L_0x008c:
            java.lang.ClassCastException r5 = new java.lang.ClassCastException
            r5.<init>()
            throw r5
        L_0x0092:
            r5 = 0
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: Oe.a.a(qf.b):Qe.f");
    }

    public final boolean b(C1236c cVar, C1240g gVar) {
        j.e(cVar, "packageFqName");
        j.e(gVar, "name");
        String b5 = gVar.b();
        j.d(b5, "asString(...)");
        if ((v.t0(b5, "Function") || v.t0(b5, "KFunction") || v.t0(b5, "SuspendFunction") || v.t0(b5, "KSuspendFunction")) && n.f3624c.a(b5, cVar) != null) {
            return true;
        }
        return false;
    }

    public final Collection c(C1236c cVar) {
        j.e(cVar, "packageFqName");
        return ne.v.d;
    }
}
