package rf;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* renamed from: rf.n  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C1264n extends C1267q {
    public final C1260j d;

    public C1264n() {
        this.d = new C1260j();
    }

    public final boolean g() {
        D d2 = this.d.f5066a;
        int i2 = 0;
        while (true) {
            if (i2 >= d2.e.size()) {
                for (Map.Entry e : d2.c()) {
                    if (!C1260j.e(e)) {
                    }
                }
                return true;
            } else if (!C1260j.e((Map.Entry) d2.e.get(i2))) {
                break;
            } else {
                i2++;
            }
        }
        return false;
    }

    public final int h() {
        D d2 = this.d.f5066a;
        int i2 = 0;
        for (int i7 = 0; i7 < d2.e.size(); i7++) {
            Map.Entry entry = (Map.Entry) d2.e.get(i7);
            i2 += C1260j.d((C1265o) entry.getKey(), entry.getValue());
        }
        for (Map.Entry entry2 : d2.c()) {
            i2 += C1260j.d((C1265o) entry2.getKey(), entry2.getValue());
        }
        return i2;
    }

    public final Object j(C1266p pVar) {
        n(pVar);
        C1265o oVar = pVar.d;
        Object obj = this.d.f5066a.get(oVar);
        if (obj == null) {
            return pVar.b;
        }
        if (!oVar.f) {
            return pVar.a(obj);
        }
        if (oVar.e.a() != S.ENUM) {
            return obj;
        }
        ArrayList arrayList = new ArrayList();
        for (Object a7 : (List) obj) {
            arrayList.add(pVar.a(a7));
        }
        return arrayList;
    }

    public final boolean k(C1266p pVar) {
        n(pVar);
        C1265o oVar = pVar.d;
        C1260j jVar = this.d;
        jVar.getClass();
        if (oVar.f) {
            throw new IllegalArgumentException("hasField() can only be called on non-repeated fields.");
        } else if (jVar.f5066a.get(oVar) != null) {
            return true;
        } else {
            return false;
        }
    }

    public final void l() {
        this.d.f();
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0043  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean m(rf.C1256f r9, B2.o r10, rf.C1258h r11, int r12) {
        /*
            r8 = this;
            rf.b r0 = r8.getDefaultInstanceForType()
            r1 = r12 & 7
            int r2 = r12 >>> 3
            java.util.Map r3 = r11.f5063a
            rf.g r4 = new rf.g
            r4.<init>(r2, r0)
            java.lang.Object r0 = r3.get(r4)
            rf.p r0 = (rf.C1266p) r0
            r2 = 2
            r3 = 1
            r4 = 0
            if (r0 != 0) goto L_0x001d
        L_0x001a:
            r1 = r3
            r5 = r4
            goto L_0x003c
        L_0x001d:
            rf.o r5 = r0.d
            rf.Q r6 = r5.e
            rf.j r7 = rf.C1260j.f5065c
            int r6 = r6.b()
            if (r1 != r6) goto L_0x002c
            r1 = r4
            r5 = r1
            goto L_0x003c
        L_0x002c:
            boolean r6 = r5.f
            if (r6 == 0) goto L_0x001a
            rf.Q r5 = r5.e
            boolean r5 = r5.c()
            if (r5 == 0) goto L_0x001a
            if (r1 != r2) goto L_0x001a
            r5 = r3
            r1 = r4
        L_0x003c:
            if (r1 == 0) goto L_0x0043
            boolean r8 = r9.q(r12, r10)
            return r8
        L_0x0043:
            r10 = 0
            rf.j r8 = r8.d
            if (r5 == 0) goto L_0x0077
            int r11 = r9.k()
            int r11 = r9.d(r11)
            rf.o r12 = r0.d
            rf.Q r0 = r12.e
            rf.Q r1 = rf.Q.ENUM
            if (r0 != r1) goto L_0x0063
            int r8 = r9.b()
            if (r8 > 0) goto L_0x005f
            goto L_0x0073
        L_0x005f:
            r9.k()
            throw r10
        L_0x0063:
            int r10 = r9.b()
            if (r10 <= 0) goto L_0x0073
            rf.Q r10 = r12.e
            java.lang.Object r10 = rf.C1260j.h(r9, r10)
            r8.a(r12, r10)
            goto L_0x0063
        L_0x0073:
            r9.c(r11)
            return r3
        L_0x0077:
            int[] r12 = rf.C1261k.f5067a
            rf.o r1 = r0.d
            rf.Q r5 = r1.e
            boolean r6 = r1.f
            rf.S r7 = r5.a()
            int r7 = r7.ordinal()
            r12 = r12[r7]
            if (r12 == r3) goto L_0x0096
            if (r12 == r2) goto L_0x0092
            java.lang.Object r9 = rf.C1260j.h(r9, r5)
            goto L_0x00f8
        L_0x0092:
            r9.k()
            throw r10
        L_0x0096:
            if (r6 != 0) goto L_0x00a6
            rf.D r12 = r8.f5066a
            java.lang.Object r12 = r12.get(r1)
            rf.b r12 = (rf.C1252b) r12
            if (r12 == 0) goto L_0x00a6
            rf.l r10 = r12.c()
        L_0x00a6:
            if (r10 != 0) goto L_0x00ae
            rf.b r10 = r0.f5069c
            rf.l r10 = r10.b()
        L_0x00ae:
            rf.Q r12 = rf.Q.GROUP
            java.lang.String r2 = "Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit."
            r7 = 64
            if (r5 != r12) goto L_0x00d5
            int r12 = r1.d
            int r4 = r9.f5061i
            if (r4 >= r7) goto L_0x00cf
            int r4 = r4 + r3
            r9.f5061i = r4
            r10.b(r9, r11)
            int r11 = r12 << 3
            r11 = r11 | 4
            r9.a(r11)
            int r11 = r9.f5061i
            int r11 = r11 - r3
            r9.f5061i = r11
            goto L_0x00f4
        L_0x00cf:
            rf.u r8 = new rf.u
            r8.<init>(r2)
            throw r8
        L_0x00d5:
            int r12 = r9.k()
            int r5 = r9.f5061i
            if (r5 >= r7) goto L_0x010a
            int r12 = r9.d(r12)
            int r2 = r9.f5061i
            int r2 = r2 + r3
            r9.f5061i = r2
            r10.b(r9, r11)
            r9.a(r4)
            int r11 = r9.f5061i
            int r11 = r11 - r3
            r9.f5061i = r11
            r9.c(r12)
        L_0x00f4:
            rf.b r9 = r10.a()
        L_0x00f8:
            if (r6 == 0) goto L_0x0102
            java.lang.Object r9 = r0.b(r9)
            r8.a(r1, r9)
            return r3
        L_0x0102:
            java.lang.Object r9 = r0.b(r9)
            r8.i(r1, r9)
            return r3
        L_0x010a:
            rf.u r8 = new rf.u
            r8.<init>(r2)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: rf.C1264n.m(rf.f, B2.o, rf.h, int):boolean");
    }

    public final void n(C1266p pVar) {
        if (pVar.f5068a != getDefaultInstanceForType()) {
            throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
        }
    }

    public C1264n(C1263m mVar) {
        mVar.e.f();
        mVar.f = false;
        this.d = mVar.e;
    }
}
