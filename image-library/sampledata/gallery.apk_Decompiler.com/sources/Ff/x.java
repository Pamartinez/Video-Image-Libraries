package Ff;

import B1.b;
import Df.H;
import Df.n;
import Re.h;
import Te.C0842c;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.j;
import lf.Q;
import lf.W;
import ne.C1196n;
import o1.C0246a;
import xf.C1353d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class x extends C0842c {

    /* renamed from: o  reason: collision with root package name */
    public final n f3410o;

    /* renamed from: p  reason: collision with root package name */
    public final W f3411p;
    public final a q;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public x(Df.n r11, lf.W r12, int r13) {
        /*
            r10 = this;
            java.lang.Object r0 = r11.f3358a
            Df.l r0 = (Df.l) r0
            Gf.m r2 = r0.f3349a
            java.lang.Object r1 = r11.f3359c
            r3 = r1
            Qe.l r3 = (Qe.C0822l) r3
            java.lang.Object r1 = r11.b
            nf.f r1 = (nf.C1209f) r1
            int r4 = r12.f4790h
            qf.g r5 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.C(r1, r4)
            lf.V r1 = r12.f4792j
            java.lang.String r4 = "getVariance(...)"
            kotlin.jvm.internal.j.d(r1, r4)
            int[] r4 = Df.A.f3335c
            int r1 = r1.ordinal()
            r1 = r4[r1]
            r4 = 1
            if (r1 == r4) goto L_0x003a
            r4 = 2
            if (r1 == r4) goto L_0x0037
            r4 = 3
            if (r1 != r4) goto L_0x0031
            Hf.d0 r1 = Hf.d0.INVARIANT
        L_0x002f:
            r6 = r1
            goto L_0x003d
        L_0x0031:
            Dd.a r10 = new Dd.a
            r10.<init>()
            throw r10
        L_0x0037:
            Hf.d0 r1 = Hf.d0.OUT_VARIANCE
            goto L_0x002f
        L_0x003a:
            Hf.d0 r1 = Hf.d0.IN_VARIANCE
            goto L_0x002f
        L_0x003d:
            boolean r7 = r12.f4791i
            Qe.S r9 = Qe.S.f
            Re.f r4 = Re.g.f3692a
            r1 = r10
            r8 = r13
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            r1.f3410o = r11
            r1.f3411p = r12
            Ff.a r10 = new Ff.a
            Gf.m r11 = r0.f3349a
            Af.g r12 = new Af.g
            r13 = 6
            r12.<init>(r13, r1)
            r10.<init>(r11, r12)
            r1.q = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Ff.x.<init>(Df.n, lf.W, int):void");
    }

    public final List F0() {
        n nVar = this.f3410o;
        b bVar = (b) nVar.d;
        W w = this.f3411p;
        j.e(w, "<this>");
        ArrayList arrayList = w.k;
        if (arrayList.isEmpty()) {
            arrayList = null;
        }
        if (arrayList == null) {
            List list = w.l;
            j.d(list, "getUpperBoundIdList(...)");
            Iterable<Integer> iterable = list;
            ArrayList arrayList2 = new ArrayList(C1196n.w0(iterable, 10));
            for (Integer num : iterable) {
                j.b(num);
                arrayList2.add(bVar.g(num.intValue()));
            }
            arrayList = arrayList2;
        }
        if (arrayList.isEmpty()) {
            return C0246a.e0(C1353d.e(this).m());
        }
        Iterable<Q> iterable2 = arrayList;
        H h5 = (H) nVar.f3360h;
        ArrayList arrayList3 = new ArrayList(C1196n.w0(iterable2, 10));
        for (Q g : iterable2) {
            arrayList3.add(h5.g(g));
        }
        return arrayList3;
    }

    public final h getAnnotations() {
        return this.q;
    }
}
