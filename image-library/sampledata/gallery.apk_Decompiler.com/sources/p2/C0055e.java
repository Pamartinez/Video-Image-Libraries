package P2;

import A0.l;
import D0.f;
import Ed.b;
import N2.g;
import N2.h;
import N2.r;
import N2.t;
import R2.a;
import S2.p;
import S2.q;
import S2.u;
import T2.c;
import T2.e;
import U2.d;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/* renamed from: P2.e  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0055e extends A {

    /* renamed from: h  reason: collision with root package name */
    public final q f590h;

    /* renamed from: i  reason: collision with root package name */
    public final f f591i;

    /* renamed from: j  reason: collision with root package name */
    public b f592j;
    public final boolean k;
    public final e l;

    public C0055e(q qVar, f fVar, boolean z, e eVar) {
        super(4, -1);
        if (qVar == null) {
            throw new NullPointerException("ref == null");
        } else if (eVar != null) {
            this.f590h = qVar;
            this.f591i = fVar;
            this.k = z;
            this.l = eVar;
            this.f592j = null;
        } else {
            throw new NullPointerException("throwsList == null");
        }
    }

    public final void a(C0056f fVar) {
        z zVar = fVar.l;
        C c5 = fVar.f;
        f fVar2 = this.f591i;
        fVar2.getClass();
        ((t) fVar2.e).getClass();
        R2.b bVar = (R2.b) ((l) ((l) fVar2.f).e).e;
        int length = bVar.e.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            } else if (((d) ((a) bVar.d(i2)).b.g().d()).e.length != 0) {
                l lVar = (l) fVar2.f;
                lVar.getClass();
                HashSet hashSet = new HashSet(20);
                R2.b bVar2 = (R2.b) ((l) lVar.e).e;
                int length2 = bVar2.e.length;
                for (int i7 = 0; i7 < length2; i7++) {
                    e d = ((a) bVar2.d(i7)).b.g().d();
                    int length3 = ((d) d).e.length;
                    for (int i8 = 0; i8 < length3; i8++) {
                        hashSet.add(d.getType(i8));
                    }
                }
                Iterator it = hashSet.iterator();
                while (it.hasNext()) {
                    c5.q((c) it.next());
                }
                this.f592j = new b(fVar2);
            } else {
                i2++;
            }
        }
        t tVar = (t) fVar2.e;
        tVar.getClass();
        HashSet hashSet2 = new HashSet(20);
        Iterator it2 = ((ArrayList) tVar.f).iterator();
        while (it2.hasNext()) {
            g gVar = (g) it2.next();
            if (gVar instanceof N2.f) {
                hashSet2.add(((N2.f) gVar).f);
            } else if (gVar instanceof r) {
                throw null;
            }
        }
        Iterator it3 = hashSet2.iterator();
        while (it3.hasNext()) {
            fVar.a((S2.a) it3.next());
        }
    }

    public final q b() {
        return q.TYPE_CODE_ITEM;
    }

    public final void i(z zVar, int i2) {
        boolean z;
        int L;
        C0056f fVar = zVar.b;
        G0.e eVar = new G0.e((Object) fVar);
        f fVar2 = this.f591i;
        Iterator it = ((ArrayList) ((t) fVar2.e).f).iterator();
        while (it.hasNext()) {
            g gVar = (g) it.next();
            if (gVar instanceof N2.f) {
                N2.f fVar3 = (N2.f) gVar;
                S2.a aVar = fVar3.f;
                int L3 = eVar.L(aVar);
                if (L3 >= 0) {
                    fVar3.n(L3);
                }
                if ((aVar instanceof p) && (L = eVar.L(((p) aVar).d)) >= 0) {
                    fVar3.m(L);
                }
            }
        }
        b bVar = this.f592j;
        int i7 = 0;
        if (bVar != null) {
            bVar.r();
            C c5 = fVar.f;
            int length = ((N2.d) bVar.d).e.length;
            bVar.f = new TreeMap();
            for (int i8 = 0; i8 < length; i8++) {
                ((TreeMap) bVar.f).put(((N2.c) ((N2.d) bVar.d).d(i8)).f, (Object) null);
            }
            if (((TreeMap) bVar.f).size() <= 65535) {
                P0.b bVar2 = new P0.b();
                bVar.b = bVar2.o(((TreeMap) bVar.f).size());
                for (Map.Entry entry : ((TreeMap) bVar.f).entrySet()) {
                    N2.b bVar3 = (N2.b) entry.getKey();
                    Object[] objArr = bVar3.e;
                    int length2 = objArr.length;
                    int length3 = objArr.length;
                    if (length3 == 0) {
                        z = false;
                    } else {
                        z = ((N2.a) bVar3.d(length3 - 1)).d.equals(u.g);
                    }
                    entry.setValue(Integer.valueOf(bVar2.b));
                    if (z) {
                        bVar2.n(-(length2 - 1));
                        length2--;
                    } else {
                        bVar2.n(length2);
                    }
                    for (int i10 = 0; i10 < length2; i10++) {
                        N2.a aVar2 = (N2.a) bVar3.d(i10);
                        bVar2.o(c5.m(aVar2.d));
                        bVar2.o(aVar2.e);
                    }
                    if (z) {
                        bVar2.o(((N2.a) bVar3.d(length2)).e);
                    }
                }
                int i11 = bVar2.b;
                byte[] bArr = new byte[i11];
                System.arraycopy((byte[]) bVar2.e, 0, bArr, 0, i11);
                bVar.e = bArr;
                b bVar4 = this.f592j;
                bVar4.r();
                i7 = (((N2.d) bVar4.d).e.length * 8) + ((byte[]) bVar4.e).length;
            } else {
                throw new UnsupportedOperationException("too many catch handlers");
            }
        }
        fVar2.D();
        int g = ((h) fVar2.f106h).g();
        if ((g & 1) != 0) {
            g++;
        }
        j((g * 2) + 16 + i7);
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x015a  */
    /* JADX WARNING: Removed duplicated region for block: B:97:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void k(P2.C0056f r18, P0.b r19) {
        /*
            r17 = this;
            r0 = r17
            r1 = r19
            boolean r2 = r1.d()
            D0.f r3 = r0.f591i
            r3.D()
            java.lang.Object r4 = r3.f106h
            N2.h r4 = (N2.h) r4
            int r4 = r4.f
            r3.D()
            java.lang.Object r5 = r3.f106h
            N2.h r5 = (N2.h) r5
            java.lang.Object[] r6 = r5.e
            int r6 = r6.length
            r7 = 0
            r8 = r7
            r9 = r8
        L_0x0020:
            r10 = 1
            if (r8 >= r6) goto L_0x004e
            java.lang.Object r11 = r5.d(r8)
            N2.g r11 = (N2.g) r11
            boolean r12 = r11 instanceof N2.f
            if (r12 != 0) goto L_0x002e
            goto L_0x004b
        L_0x002e:
            r12 = r11
            N2.f r12 = (N2.f) r12
            S2.a r12 = r12.f
            boolean r13 = r12 instanceof S2.q
            if (r13 != 0) goto L_0x0038
            goto L_0x004b
        L_0x0038:
            N2.i r11 = r11.b
            int r11 = r11.b
            r13 = 113(0x71, float:1.58E-43)
            if (r11 != r13) goto L_0x0041
            goto L_0x0042
        L_0x0041:
            r10 = r7
        L_0x0042:
            S2.q r12 = (S2.q) r12
            int r10 = r12.f(r10)
            if (r10 <= r9) goto L_0x004b
            r9 = r10
        L_0x004b:
            int r8 = r8 + 1
            goto L_0x0020
        L_0x004e:
            boolean r5 = r0.k
            S2.q r6 = r0.f590h
            int r5 = r6.f(r5)
            r3.D()
            java.lang.Object r8 = r3.f106h
            N2.h r8 = (N2.h) r8
            int r8 = r8.g()
            r11 = r8 & 1
            if (r11 == 0) goto L_0x0066
            goto L_0x0067
        L_0x0066:
            r10 = r7
        L_0x0067:
            Ed.b r11 = r0.f592j
            if (r11 != 0) goto L_0x006d
            r11 = r7
            goto L_0x0077
        L_0x006d:
            r11.r()
            java.lang.Object r11 = r11.d
            N2.d r11 = (N2.d) r11
            java.lang.Object[] r11 = r11.e
            int r11 = r11.length
        L_0x0077:
            r12 = 2
            if (r2 == 0) goto L_0x0138
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.lang.String r14 = r0.g()
            r13.append(r14)
            r14 = 32
            r13.append(r14)
            java.lang.String r14 = r6.a()
            r13.append(r14)
            java.lang.String r13 = r13.toString()
            r1.b(r7, r13)
            java.lang.String r13 = L2.a.D(r4)
            java.lang.String r14 = "  registers_size: "
            java.lang.String r13 = r14.concat(r13)
            r1.b(r12, r13)
            java.lang.String r13 = L2.a.D(r5)
            java.lang.String r14 = "  ins_size:       "
            java.lang.String r13 = r14.concat(r13)
            r1.b(r12, r13)
            java.lang.String r13 = L2.a.D(r9)
            java.lang.String r14 = "  outs_size:      "
            java.lang.String r13 = r14.concat(r13)
            r1.b(r12, r13)
            java.lang.String r13 = L2.a.D(r11)
            java.lang.String r14 = "  tries_size:     "
            java.lang.String r13 = r14.concat(r13)
            r1.b(r12, r13)
            java.lang.String r13 = L2.a.E(r7)
            java.lang.String r14 = "  debug_off:      "
            java.lang.String r13 = r14.concat(r13)
            r14 = 4
            r1.b(r14, r13)
            java.lang.String r13 = L2.a.E(r8)
            java.lang.String r15 = "  insns_size:     "
            java.lang.String r13 = r15.concat(r13)
            r1.b(r14, r13)
            T2.e r13 = r0.l
            r14 = r13
            U2.d r14 = (U2.d) r14
            java.lang.Object[] r15 = r14.e
            int r15 = r15.length
            if (r15 == 0) goto L_0x0138
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            java.lang.String r12 = "  throws "
            r15.<init>(r12)
            java.lang.Object[] r12 = r14.e
            int r12 = r12.length
            if (r12 != 0) goto L_0x0103
            java.lang.String r12 = "<empty>"
            r16 = r2
            goto L_0x012c
        L_0x0103:
            java.lang.StringBuffer r14 = new java.lang.StringBuffer
            r7 = 100
            r14.<init>(r7)
            r7 = 0
        L_0x010b:
            if (r7 >= r12) goto L_0x0126
            r16 = r2
            if (r7 == 0) goto L_0x0116
            java.lang.String r2 = ", "
            r14.append(r2)
        L_0x0116:
            T2.c r2 = r13.getType(r7)
            java.lang.String r2 = r2.a()
            r14.append(r2)
            int r7 = r7 + 1
            r2 = r16
            goto L_0x010b
        L_0x0126:
            r16 = r2
            java.lang.String r12 = r14.toString()
        L_0x012c:
            r15.append(r12)
            java.lang.String r2 = r15.toString()
            r7 = 0
            r1.b(r7, r2)
            goto L_0x013a
        L_0x0138:
            r16 = r2
        L_0x013a:
            r1.m(r4)
            r1.m(r5)
            r1.m(r9)
            r1.m(r11)
            r1.l(r7)
            r1.l(r8)
            r3.D()
            java.lang.Object r2 = r3.f106h
            N2.h r2 = (N2.h) r2
            r2.h(r1)     // Catch:{ RuntimeException -> 0x02b0 }
            Ed.b r2 = r0.f592j
            if (r2 == 0) goto L_0x02af
            if (r10 == 0) goto L_0x0168
            if (r16 == 0) goto L_0x0164
            java.lang.String r2 = "  padding: 0"
            r3 = 2
            r1.b(r3, r2)
        L_0x0164:
            r7 = 0
            r1.m(r7)
        L_0x0168:
            Ed.b r0 = r0.f592j
            r0.r()
            boolean r2 = r1.d()
            java.lang.String r3 = ".."
            if (r2 == 0) goto L_0x024c
            r0.r()
            java.lang.Object r2 = r0.d
            N2.d r2 = (N2.d) r2
            java.lang.Object[] r2 = r2.e
            int r2 = r2.length
            java.lang.String r4 = "  tries:"
            r7 = 0
            r1.b(r7, r4)
            r7 = 0
        L_0x0186:
            java.lang.String r4 = "    "
            if (r7 >= r2) goto L_0x01d7
            java.lang.Object r5 = r0.d
            N2.d r5 = (N2.d) r5
            java.lang.Object r5 = r5.d(r7)
            N2.c r5 = (N2.c) r5
            N2.b r6 = r5.f
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "    try "
            r8.<init>(r9)
            int r9 = r5.d
            char r10 = (char) r9
            if (r9 != r10) goto L_0x01a7
            java.lang.String r9 = L2.a.D(r9)
            goto L_0x01ab
        L_0x01a7:
            java.lang.String r9 = L2.a.E(r9)
        L_0x01ab:
            r8.append(r9)
            r8.append(r3)
            int r5 = r5.e
            char r9 = (char) r5
            if (r5 != r9) goto L_0x01bb
            java.lang.String r5 = L2.a.D(r5)
            goto L_0x01bf
        L_0x01bb:
            java.lang.String r5 = L2.a.E(r5)
        L_0x01bf:
            r8.append(r5)
            java.lang.String r5 = r8.toString()
            java.lang.String r8 = ""
            java.lang.String r4 = r6.h(r4, r8)
            r6 = 6
            r1.b(r6, r5)
            r5 = 2
            r1.b(r5, r4)
            int r7 = r7 + 1
            goto L_0x0186
        L_0x01d7:
            java.lang.String r2 = "  handlers:"
            r7 = 0
            r1.b(r7, r2)
            int r2 = r0.b
            java.lang.Object r5 = r0.f
            java.util.TreeMap r5 = (java.util.TreeMap) r5
            int r5 = r5.size()
            java.lang.String r5 = L2.a.D(r5)
            java.lang.String r6 = "    size: "
            java.lang.String r5 = r6.concat(r5)
            r1.b(r2, r5)
            java.lang.Object r2 = r0.f
            java.util.TreeMap r2 = (java.util.TreeMap) r2
            java.util.Set r2 = r2.entrySet()
            java.util.Iterator r2 = r2.iterator()
            r5 = 0
            r6 = r7
        L_0x0202:
            boolean r8 = r2.hasNext()
            java.lang.String r9 = ": "
            if (r8 == 0) goto L_0x0236
            java.lang.Object r8 = r2.next()
            java.util.Map$Entry r8 = (java.util.Map.Entry) r8
            java.lang.Object r10 = r8.getKey()
            N2.b r10 = (N2.b) r10
            java.lang.Object r8 = r8.getValue()
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r8 = r8.intValue()
            if (r5 == 0) goto L_0x0233
            int r11 = r8 - r6
            java.lang.String r6 = L2.a.D(r6)
            java.lang.String r6 = r6.concat(r9)
            java.lang.String r5 = r5.h(r4, r6)
            r1.b(r11, r5)
        L_0x0233:
            r6 = r8
            r5 = r10
            goto L_0x0202
        L_0x0236:
            java.lang.Object r2 = r0.e
            byte[] r2 = (byte[]) r2
            int r2 = r2.length
            int r2 = r2 - r6
            java.lang.String r6 = L2.a.D(r6)
            java.lang.String r6 = r6.concat(r9)
            java.lang.String r4 = r5.h(r4, r6)
            r1.b(r2, r4)
            goto L_0x024d
        L_0x024c:
            r7 = 0
        L_0x024d:
            java.lang.Object r2 = r0.d
            N2.d r2 = (N2.d) r2
            java.lang.Object[] r2 = r2.e
            int r2 = r2.length
        L_0x0254:
            if (r7 >= r2) goto L_0x02a8
            java.lang.Object r4 = r0.d
            N2.d r4 = (N2.d) r4
            java.lang.Object r4 = r4.d(r7)
            N2.c r4 = (N2.c) r4
            int r5 = r4.d
            int r6 = r4.e
            int r8 = r6 - r5
            r9 = 65536(0x10000, float:9.18355E-41)
            if (r8 >= r9) goto L_0x0286
            r1.l(r5)
            r1.m(r8)
            java.lang.Object r5 = r0.f
            java.util.TreeMap r5 = (java.util.TreeMap) r5
            N2.b r4 = r4.f
            java.lang.Object r4 = r5.get(r4)
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            r1.m(r4)
            int r7 = r7 + 1
            goto L_0x0254
        L_0x0286:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "bogus exception range: "
            r1.<init>(r2)
            java.lang.String r2 = L2.a.E(r5)
            r1.append(r2)
            r1.append(r3)
            java.lang.String r2 = L2.a.E(r6)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x02a8:
            java.lang.Object r0 = r0.e
            byte[] r0 = (byte[]) r0
            r1.j(r0)
        L_0x02af:
            return
        L_0x02b0:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "...while writing instructions for "
            r1.<init>(r2)
            java.lang.String r2 = r6.a()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            U2.c r0 = U2.c.a(r0, r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: P2.C0055e.k(P2.f, P0.b):void");
    }

    public final String toString() {
        return "CodeItem{" + this.f590h.a() + "}";
    }
}
