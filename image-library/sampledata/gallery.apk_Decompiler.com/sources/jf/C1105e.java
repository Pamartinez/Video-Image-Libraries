package jf;

import B0.a;
import D0.f;
import Fd.C0744a;
import Qe.C0816f;
import Qe.Q;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import qf.C1235b;
import qf.C1240g;
import vf.C1326f;
import vf.C1327g;
import vf.C1329i;
import vf.C1339s;

/* renamed from: jf.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1105e implements o {
    public final /* synthetic */ C0744a d;
    public final HashMap e = new HashMap();
    public final /* synthetic */ C0744a f;
    public final /* synthetic */ C0816f g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ C1235b f4639h;

    /* renamed from: i  reason: collision with root package name */
    public final /* synthetic */ List f4640i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ Q f4641j;

    public C1105e(C0744a aVar, C0816f fVar, C1235b bVar, List list, Q q) {
        this.f = aVar;
        this.g = fVar;
        this.f4639h = bVar;
        this.f4640i = list;
        this.f4641j = q;
        this.d = aVar;
    }

    public final void I(C1240g gVar, C1326f fVar) {
        this.e.put(gVar, new C1327g(new C1339s(fVar)));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v2, resolved type: vf.s} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void n() {
        /*
            r7 = this;
            java.lang.String r0 = "arguments"
            java.util.HashMap r1 = r7.e
            kotlin.jvm.internal.j.e(r1, r0)
            qf.b r0 = Me.a.b
            qf.b r2 = r7.f4639h
            boolean r0 = r2.equals(r0)
            Fd.a r3 = r7.f
            r4 = 0
            if (r0 != 0) goto L_0x0015
            goto L_0x003f
        L_0x0015:
            java.lang.String r0 = "value"
            qf.g r0 = qf.C1240g.e(r0)
            java.lang.Object r0 = r1.get(r0)
            boolean r5 = r0 instanceof vf.C1341u
            r6 = 0
            if (r5 == 0) goto L_0x0027
            vf.u r0 = (vf.C1341u) r0
            goto L_0x0028
        L_0x0027:
            r0 = r6
        L_0x0028:
            if (r0 != 0) goto L_0x002b
            goto L_0x003f
        L_0x002b:
            java.lang.Object r0 = r0.f5158a
            boolean r5 = r0 instanceof vf.C1339s
            if (r5 == 0) goto L_0x0034
            r6 = r0
            vf.s r6 = (vf.C1339s) r6
        L_0x0034:
            if (r6 != 0) goto L_0x0037
            goto L_0x003f
        L_0x0037:
            vf.f r0 = r6.f5163a
            qf.b r0 = r0.f5157a
            boolean r4 = r3.k(r0)
        L_0x003f:
            if (r4 == 0) goto L_0x0042
            goto L_0x0048
        L_0x0042:
            boolean r0 = r3.k(r2)
            if (r0 == 0) goto L_0x0049
        L_0x0048:
            return
        L_0x0049:
            Re.c r0 = new Re.c
            Qe.f r2 = r7.g
            Hf.B r2 = r2.i()
            Qe.Q r3 = r7.f4641j
            r0.<init>(r2, r1, r3)
            java.util.List r7 = r7.f4640i
            r7.add(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: jf.C1105e.n():void");
    }

    public final p o(C1240g gVar) {
        return new f(this.d, gVar, this);
    }

    public final o p(C1235b bVar, C1240g gVar) {
        ArrayList arrayList = new ArrayList();
        return new a(this.d.s(bVar, Q.f3662a, arrayList), this, gVar, arrayList);
    }

    public final void r(C1240g gVar, C1235b bVar, C1240g gVar2) {
        this.e.put(gVar, new C1329i(bVar, gVar2));
    }

    public final void s(C1240g gVar, Object obj) {
        this.e.put(gVar, C0744a.b(this.d, gVar, obj));
    }
}
