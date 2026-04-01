package L2;

import R2.f;
import R2.h;
import R2.i;
import R2.k;
import R2.l;
import R2.n;
import R2.o;
import R2.p;
import R2.q;
import S2.j;
import T2.a;
import T2.c;
import U2.d;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public final q f389a;
    public final ArrayList b = new ArrayList();

    /* renamed from: c  reason: collision with root package name */
    public o f390c;
    public boolean d;
    public final p e;
    public final ArrayList f;
    public final ArrayList g;

    /* renamed from: h  reason: collision with root package name */
    public final p f391h;

    /* renamed from: i  reason: collision with root package name */
    public final ArrayList f392i;

    /* renamed from: j  reason: collision with root package name */
    public final T2.b f393j;

    public b(k kVar) {
        ArrayList arrayList = new ArrayList();
        this.f = arrayList;
        this.g = new ArrayList();
        this.f391h = p.f773a;
        new ArrayList();
        this.f392i = new ArrayList();
        this.f393j = T2.b.f;
        q qVar = kVar.f395a;
        this.f389a = qVar;
        if ((kVar.b & 8) != 0) {
            this.e = null;
        } else {
            p pVar = new p(this, qVar.f407a);
            this.e = pVar;
            arrayList.add(pVar);
        }
        for (r pVar2 : qVar.d.f414a) {
            this.f.add(new p(this, pVar2));
        }
        o oVar = new o();
        this.f390c = oVar;
        b(oVar);
        this.f390c.f404c = true;
    }

    public static void d(p pVar, r rVar) {
        if (!pVar.b.equals(rVar)) {
            throw new IllegalArgumentException("requested " + rVar + " but was " + pVar.b);
        }
    }

    public final void a(f fVar, o oVar) {
        o oVar2 = this.f390c;
        if (oVar2 == null || !oVar2.f404c) {
            throw new IllegalStateException("no current label");
        }
        oVar2.f403a.add(fVar);
        int i2 = fVar.d.e;
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 6) {
                            throw new IllegalArgumentException();
                        } else if (oVar == null) {
                            ArrayList arrayList = new ArrayList(this.f392i);
                            o oVar3 = new o();
                            b(oVar3);
                            o oVar4 = this.f390c;
                            oVar4.e = oVar3;
                            oVar4.f = null;
                            oVar4.d = arrayList;
                            this.f390c = oVar3;
                            oVar3.f404c = true;
                        } else {
                            throw new IllegalArgumentException("unexpected branch: " + oVar);
                        }
                    } else if (oVar != null) {
                        List list = Collections.EMPTY_LIST;
                        o oVar5 = new o();
                        b(oVar5);
                        o oVar6 = this.f390c;
                        oVar6.e = oVar5;
                        oVar6.f = oVar;
                        oVar6.d = list;
                        this.f390c = oVar5;
                        oVar5.f404c = true;
                    } else {
                        throw new IllegalArgumentException("branch == null");
                    }
                } else if (oVar != null) {
                    this.f390c.e = oVar;
                    this.f390c = null;
                } else {
                    throw new IllegalArgumentException("branch == null");
                }
            } else if (oVar == null) {
                this.f390c = null;
            } else {
                throw new IllegalArgumentException("unexpected branch: " + oVar);
            }
        } else if (oVar != null) {
            throw new IllegalArgumentException("unexpected branch: " + oVar);
        }
    }

    public final void b(o oVar) {
        b bVar = oVar.b;
        if (bVar != this) {
            if (bVar == null) {
                oVar.b = this;
                this.b.add(oVar);
                return;
            }
            throw new IllegalArgumentException("Cannot adopt label; it belongs to another Code");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005f, code lost:
        if (r6 == 7) goto L_0x0068;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void c(L2.p r11, L2.p r12) {
        /*
            r10 = this;
            L2.r r0 = r12.b
            T2.c r0 = r0.b
            int r1 = r0.e
            r2 = 9
            r3 = 0
            if (r1 != r2) goto L_0x002a
            R2.q r4 = new R2.q
            R2.n r5 = R2.o.f683L1
            R2.k r12 = r12.a()
            R2.l r7 = R2.l.h(r12)
            L2.r r12 = r11.b
            S2.u r9 = r12.f413c
            R2.p r6 = r10.f391h
            T2.b r8 = r10.f393j
            r4.<init>(r5, r6, r7, r8, r9)
            r10.a(r4, r3)
            r12 = 1
            r10.j(r11, r12)
            return
        L_0x002a:
            R2.i r2 = new R2.i
            L2.r r4 = r11.b
            T2.c r4 = r4.b
            r5 = 6
            if (r1 != r5) goto L_0x004c
            int r1 = r4.e
            r6 = 2
            if (r1 == r6) goto L_0x0048
            r6 = 3
            if (r1 == r6) goto L_0x0044
            r6 = 8
            if (r1 == r6) goto L_0x0040
            goto L_0x004c
        L_0x0040:
            R2.n r0 = R2.o.f714Z0
            goto L_0x00b7
        L_0x0044:
            R2.n r0 = R2.o.f712Y0
            goto L_0x00b7
        L_0x0048:
            R2.n r0 = R2.o.f710X0
            goto L_0x00b7
        L_0x004c:
            R2.n r1 = R2.o.f716a
            int r1 = r4.d()
            int r6 = r0.d()
            r7 = 7
            r8 = 5
            r9 = 4
            if (r6 == r9) goto L_0x0091
            if (r6 == r8) goto L_0x0081
            if (r6 == r5) goto L_0x0062
            if (r6 != r7) goto L_0x009a
            goto L_0x0068
        L_0x0062:
            if (r1 == r9) goto L_0x007e
            if (r1 == r8) goto L_0x007b
            if (r1 == r7) goto L_0x0078
        L_0x0068:
            if (r1 == r9) goto L_0x0075
            if (r1 == r8) goto L_0x0072
            if (r1 == r5) goto L_0x006f
            goto L_0x0081
        L_0x006f:
            R2.n r0 = R2.o.f682L0
            goto L_0x00b7
        L_0x0072:
            R2.n r0 = R2.o.S0
            goto L_0x00b7
        L_0x0075:
            R2.n r0 = R2.o.f704V0
            goto L_0x00b7
        L_0x0078:
            R2.n r0 = R2.o.f691O0
            goto L_0x00b7
        L_0x007b:
            R2.n r0 = R2.o.f698R0
            goto L_0x00b7
        L_0x007e:
            R2.n r0 = R2.o.f702U0
            goto L_0x00b7
        L_0x0081:
            if (r1 == r9) goto L_0x008e
            if (r1 == r5) goto L_0x008b
            if (r1 == r7) goto L_0x0088
            goto L_0x0091
        L_0x0088:
            R2.n r0 = R2.o.f694P0
            goto L_0x00b7
        L_0x008b:
            R2.n r0 = R2.o.f685M0
            goto L_0x00b7
        L_0x008e:
            R2.n r0 = R2.o.f707W0
            goto L_0x00b7
        L_0x0091:
            if (r1 == r8) goto L_0x00b5
            if (r1 == r5) goto L_0x00b2
            if (r1 != r7) goto L_0x009a
            R2.n r0 = R2.o.f696Q0
            goto L_0x00b7
        L_0x009a:
            T2.b r10 = T2.b.h(r4, r0)
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r12 = new java.lang.StringBuilder
            java.lang.String r0 = "bad types: "
            r12.<init>(r0)
            r12.append(r10)
            java.lang.String r10 = r12.toString()
            r11.<init>(r10)
            throw r11
        L_0x00b2:
            R2.n r0 = R2.o.f688N0
            goto L_0x00b7
        L_0x00b5:
            R2.n r0 = R2.o.T0
        L_0x00b7:
            R2.k r11 = r11.a()
            R2.k r12 = r12.a()
            R2.l r12 = R2.l.h(r12)
            R2.p r1 = r10.f391h
            r2.<init>(r0, r1, r11, r12)
            r10.a(r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: L2.b.c(L2.p, L2.p):void");
    }

    public final p e(int i2, r rVar) {
        if (this.e != null) {
            i2++;
        }
        p pVar = (p) this.f.get(i2);
        d(pVar, rVar);
        return pVar;
    }

    public final void f() {
        n nVar;
        if (!this.d) {
            this.d = true;
            Iterator it = this.g.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                p pVar = (p) it.next();
                pVar.f406c = i2;
                r rVar = pVar.b;
                pVar.d = k.d(i2, rVar.b);
                i2 += rVar.b.e();
            }
            ArrayList arrayList = new ArrayList();
            Iterator it2 = this.f.iterator();
            int i7 = i2;
            while (it2.hasNext()) {
                p pVar2 = (p) it2.next();
                j i8 = j.i(i7 - i2);
                pVar2.f406c = i7;
                r rVar2 = pVar2.b;
                pVar2.d = k.d(i7, rVar2.b);
                i7 += rVar2.b.e();
                c cVar = rVar2.b;
                n nVar2 = o.f716a;
                int d2 = cVar.d();
                if (d2 == 4) {
                    nVar = o.f740j;
                } else if (d2 == 5) {
                    nVar = o.f737i;
                } else if (d2 == 6) {
                    nVar = o.g;
                } else if (d2 == 7) {
                    nVar = o.f734h;
                } else if (d2 == 9) {
                    nVar = o.k;
                } else {
                    o.b(cVar);
                    throw null;
                }
                arrayList.add(new h(nVar, this.f391h, pVar2.a(), l.f, i8));
            }
            ((o) this.b.get(0)).f403a.addAll(0, arrayList);
            return;
        }
        throw new AssertionError();
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [U2.d, R2.l] */
    public final void g(n nVar, q qVar, p pVar, p pVar2, p... pVarArr) {
        int i2;
        if (pVar2 != null) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        ? dVar = new d(pVarArr.length + i2);
        if (pVar2 != null) {
            dVar.e(0, pVar2.a());
        }
        for (int i7 = 0; i7 < pVarArr.length; i7++) {
            dVar.e(i7 + i2, pVarArr[i7].a());
        }
        a(new q(nVar, this.f391h, dVar, this.f393j, qVar.e), (o) null);
        if (pVar != null) {
            j(pVar, false);
        }
    }

    public final void h(q qVar, p pVar, p pVar2, p... pVarArr) {
        a c5 = a.c(qVar.a(true));
        n nVar = o.f716a;
        g(new n(51, c5.b(), T2.b.m), qVar, pVar, pVar2, pVarArr);
    }

    public final void i(p pVar, Integer num) {
        n nVar;
        if (num == null) {
            nVar = o.q;
        } else {
            c cVar = pVar.b.b;
            n nVar2 = o.f716a;
            cVar.getClass();
            if (cVar == c.s) {
                nVar = o.q;
            } else {
                int d2 = cVar.d();
                if (d2 == 4) {
                    nVar = o.f752o;
                } else if (d2 == 5) {
                    nVar = o.n;
                } else if (d2 == 6) {
                    nVar = o.l;
                } else if (d2 == 7) {
                    nVar = o.m;
                } else if (d2 == 9) {
                    nVar = o.f754p;
                } else {
                    o.b(cVar);
                    throw null;
                }
            }
        }
        n nVar3 = nVar;
        if (nVar3.e == 1) {
            a(new h(nVar3, this.f391h, pVar.a(), l.f, Gd.a.u(num)), (o) null);
            return;
        }
        a(new q(nVar3, this.f391h, l.f, this.f393j, Gd.a.u(num)), (o) null);
        j(pVar, true);
    }

    public final void j(p pVar, boolean z) {
        n nVar;
        if (z) {
            c cVar = pVar.b.b;
            n nVar2 = o.f716a;
            cVar.getClass();
            nVar = new n(56, cVar, T2.b.f, (String) null);
        } else {
            c cVar2 = pVar.b.b;
            n nVar3 = o.f716a;
            cVar2.getClass();
            nVar = new n(55, cVar2, T2.b.f, (String) null);
        }
        a(new i(nVar, this.f391h, pVar.a(), l.f), (o) null);
    }

    public final p k(r rVar) {
        if (!this.d) {
            p pVar = new p(this, rVar);
            this.g.add(pVar);
            return pVar;
        }
        throw new IllegalStateException("Cannot allocate locals after adding instructions");
    }

    public final void l(p pVar) {
        n nVar;
        r rVar = pVar.b;
        q qVar = this.f389a;
        if (rVar.equals(qVar.b)) {
            c cVar = rVar.b;
            n nVar2 = o.f716a;
            int d2 = cVar.d();
            if (d2 == 0) {
                nVar = o.f717a1;
            } else if (d2 == 9) {
                nVar = o.f729f1;
            } else if (d2 == 4) {
                nVar = o.f726e1;
            } else if (d2 == 5) {
                nVar = o.f724d1;
            } else if (d2 == 6) {
                nVar = o.b1;
            } else if (d2 == 7) {
                nVar = o.f722c1;
            } else {
                o.b(cVar);
                throw null;
            }
            a(new i(nVar, this.f391h, (k) null, l.h(pVar.a())), (o) null);
            return;
        }
        throw new IllegalArgumentException("declared " + qVar.b + " but returned " + rVar);
    }

    public final void m() {
        q qVar = this.f389a;
        if (qVar.b.equals(r.l)) {
            a(new i(o.f717a1, this.f391h, (k) null, l.f), (o) null);
            return;
        }
        throw new IllegalArgumentException("declared " + qVar.b + " but returned void");
    }
}
