package R2;

import S2.a;
import S2.t;
import T2.e;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class q extends c {

    /* renamed from: i  reason: collision with root package name */
    public final e f774i;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public q(R2.n r7, R2.p r8, R2.l r9, T2.e r10, S2.a r11) {
        /*
            r6 = this;
            r3 = 0
            r0 = r6
            r1 = r7
            r2 = r8
            r4 = r9
            r5 = r11
            r0.<init>(r1, r2, r3, r4, r5)
            int r6 = r1.e
            r7 = 6
            if (r6 != r7) goto L_0x001b
            if (r10 == 0) goto L_0x0013
            r0.f774i = r10
            return
        L_0x0013:
            java.lang.NullPointerException r6 = new java.lang.NullPointerException
            java.lang.String r7 = "catches == null"
            r6.<init>(r7)
            throw r6
        L_0x001b:
            java.lang.IllegalArgumentException r6 = new java.lang.IllegalArgumentException
            java.lang.String r7 = "bogus branchingness"
            r6.<init>(r7)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: R2.q.<init>(R2.n, R2.p, R2.l, T2.e, S2.a):void");
    }

    public final void c(e eVar) {
        eVar.d(this);
    }

    public final e d() {
        return this.f774i;
    }

    public final String e() {
        a aVar = this.f647h;
        String a7 = aVar.a();
        if (aVar instanceof t) {
            a7 = ((t) aVar).f();
        }
        StringBuilder t = C0212a.t(a7, " ");
        t.append(r.f(this.f774i));
        return t.toString();
    }
}
