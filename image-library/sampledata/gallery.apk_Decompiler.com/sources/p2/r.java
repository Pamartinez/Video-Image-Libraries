package P2;

import L2.a;
import P0.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class r extends A {

    /* renamed from: h  reason: collision with root package name */
    public final q f601h;

    /* renamed from: i  reason: collision with root package name */
    public final D f602i;

    /* renamed from: j  reason: collision with root package name */
    public final p f603j;
    public final int k;

    public r(q qVar, D d, p pVar, p pVar2, int i2) {
        super(4, 12);
        if (qVar == null) {
            throw new NullPointerException("type == null");
        } else if (pVar == null) {
            throw new NullPointerException("firstItem == null");
        } else if (pVar2 == null) {
            throw new NullPointerException("lastItem == null");
        } else if (i2 > 0) {
            this.f601h = qVar;
            this.f602i = d;
            this.f603j = pVar;
            this.k = i2;
        } else {
            throw new IllegalArgumentException("itemCount <= 0");
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v0, resolved type: P2.p} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void l(P2.D[] r13, P2.z r14) {
        /*
            if (r13 == 0) goto L_0x0074
            java.util.ArrayList r0 = r14.f
            int r0 = r0.size()
            if (r0 != 0) goto L_0x006c
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 50
            r0.<init>(r1)
            int r1 = r13.length
            r2 = 0
            r3 = r2
        L_0x0014:
            if (r3 >= r1) goto L_0x0061
            r6 = r13[r3]
            java.util.Collection r4 = r6.c()
            java.util.Iterator r10 = r4.iterator()
            r4 = 0
            r9 = r2
            r5 = r4
            r7 = r5
            r8 = r7
        L_0x0025:
            boolean r4 = r10.hasNext()
            if (r4 == 0) goto L_0x0049
            java.lang.Object r4 = r10.next()
            r11 = r4
            P2.p r11 = (P2.p) r11
            P2.q r12 = r11.b()
            if (r12 == r5) goto L_0x0045
            if (r9 == 0) goto L_0x0042
            P2.r r4 = new P2.r
            r4.<init>(r5, r6, r7, r8, r9)
            r0.add(r4)
        L_0x0042:
            r9 = r2
            r7 = r11
            r5 = r12
        L_0x0045:
            int r9 = r9 + 1
            r8 = r11
            goto L_0x0025
        L_0x0049:
            if (r9 == 0) goto L_0x0054
            P2.r r4 = new P2.r
            r4.<init>(r5, r6, r7, r8, r9)
            r0.add(r4)
            goto L_0x005e
        L_0x0054:
            if (r6 != r14) goto L_0x005e
            P2.r r4 = new P2.r
            r4.<init>(r14)
            r0.add(r4)
        L_0x005e:
            int r3 = r3 + 1
            goto L_0x0014
        L_0x0061:
            P2.J r13 = new P2.J
            P2.q r1 = P2.q.TYPE_MAP_LIST
            r13.<init>(r1, r0)
            r14.k(r13)
            return
        L_0x006c:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.String r14 = "mapSection.items().size() != 0"
            r13.<init>(r14)
            throw r13
        L_0x0074:
            java.lang.NullPointerException r13 = new java.lang.NullPointerException
            java.lang.String r14 = "sections == null"
            r13.<init>(r14)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: P2.r.l(P2.D[], P2.z):void");
    }

    public final q b() {
        return q.TYPE_MAP_ITEM;
    }

    public final void k(C0056f fVar, b bVar) {
        int i2;
        q qVar = this.f601h;
        int c5 = qVar.c();
        D d = this.f602i;
        p pVar = this.f603j;
        if (pVar == null) {
            i2 = d.b();
        } else {
            i2 = d.a(pVar);
        }
        boolean d2 = bVar.d();
        int i7 = this.k;
        if (d2) {
            bVar.b(0, g() + ' ' + qVar.d() + " map");
            StringBuilder sb2 = new StringBuilder("  type:   ");
            sb2.append(a.D(c5));
            sb2.append(" // ");
            sb2.append(qVar.toString());
            bVar.b(2, sb2.toString());
            bVar.b(2, "  unused: 0");
            bVar.b(4, "  size:   ".concat(a.E(i7)));
            bVar.b(4, "  offset: ".concat(a.E(i2)));
        }
        bVar.m(c5);
        bVar.m(0);
        bVar.l(i7);
        bVar.l(i2);
    }

    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer(100);
        stringBuffer.append(r.class.getName());
        stringBuffer.append('{');
        stringBuffer.append(this.f602i.toString());
        stringBuffer.append(' ');
        stringBuffer.append(this.f601h.a());
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    public r(z zVar) {
        super(4, 12);
        if (zVar != null) {
            this.f601h = q.TYPE_MAP_LIST;
            this.f602i = zVar;
            this.f603j = null;
            this.k = 1;
            return;
        }
        throw new NullPointerException("section == null");
    }

    public final void a(C0056f fVar) {
    }
}
