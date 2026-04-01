package Df;

import B1.b;
import D0.e;
import Ff.a;
import Ff.c;
import Ff.k;
import Ff.m;
import Ff.v;
import Ff.y;
import Hf.C0774x;
import Qe.C0812b;
import Qe.C0813c;
import Qe.C0816f;
import Qe.C0821k;
import Qe.C0822l;
import Qe.H;
import Qe.Q;
import Re.f;
import Re.g;
import Re.h;
import Te.B;
import Te.K;
import Te.u;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.j;
import lf.C1147A;
import lf.C1159l;
import lf.C1171y;
import lf.C1172z;
import lf.G;
import lf.Z;
import lf.f0;
import ne.C1194l;
import ne.C1195m;
import ne.C1196n;
import ne.C1202t;
import ne.C1203u;
import nf.C1204a;
import nf.C1208e;
import nf.C1209f;
import nf.C1211h;
import o1.C0246a;
import qf.C1236c;
import qf.C1240g;
import rf.C1264n;
import tf.C1312p;
import xf.C1353d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class w {

    /* renamed from: a  reason: collision with root package name */
    public final n f3367a;
    public final e b;

    public w(n nVar) {
        this.f3367a = nVar;
        l lVar = (l) nVar.f3358a;
        this.b = new e(lVar.b, lVar.l);
    }

    public final z a(C0822l lVar) {
        if (lVar instanceof H) {
            C1236c cVar = ((B) ((H) lVar)).f3743i;
            n nVar = this.f3367a;
            return new y(cVar, (C1209f) nVar.b, (b) nVar.d, (m) nVar.g);
        } else if (lVar instanceof k) {
            return ((k) lVar).y;
        } else {
            return null;
        }
    }

    public final h b(C1264n nVar, int i2, C0737c cVar) {
        if (!C1208e.f4966c.c(i2).booleanValue()) {
            return g.f3692a;
        }
        return new y(((l) this.f3367a.f3358a).f3349a, new t(this, nVar, cVar, 0));
    }

    public final h c(G g, boolean z) {
        if (!C1208e.f4966c.c(g.g).booleanValue()) {
            return g.f3692a;
        }
        return new y(((l) this.f3367a.f3358a).f3349a, new u(this, z, g));
    }

    public final c d(C1159l lVar, boolean z) {
        n nVar = this.f3367a;
        C0822l lVar2 = (C0822l) nVar.f3359c;
        j.c(lVar2, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
        C0816f fVar = (C0816f) lVar2;
        int i2 = lVar.g;
        C0737c cVar = C0737c.FUNCTION;
        boolean z3 = z;
        c cVar2 = new c(fVar, (C0821k) null, b(lVar, i2, cVar), z3, C0813c.DECLARATION, lVar, (C1209f) nVar.b, (b) nVar.d, (C1211h) nVar.e, (m) nVar.g, (Q) null);
        List list = lVar.f4859h;
        j.d(list, "getValueParameterList(...)");
        cVar2.S0(((w) nVar.a(cVar2, C1202t.d, (C1209f) nVar.b, (b) nVar.d, (C1211h) nVar.e, (C1204a) nVar.f).f3361i).g(list, lVar, cVar), C0246a.T((f0) C1208e.d.c(lVar.g)));
        cVar2.O0(fVar.i());
        cVar2.v = fVar.b0();
        cVar2.z = !C1208e.f4970o.c(lVar.g).booleanValue();
        return cVar2;
    }

    public final v e(C1171y yVar) {
        int i2;
        h hVar;
        C1211h hVar2;
        u uVar;
        C0816f fVar;
        u uVar2;
        C0774x g;
        C1171y yVar2 = yVar;
        n nVar = this.f3367a;
        C1209f fVar2 = (C1209f) nVar.b;
        b bVar = (b) nVar.d;
        j.e(yVar2, "proto");
        if ((yVar2.f & 1) == 1) {
            i2 = yVar2.g;
        } else {
            int i7 = yVar2.f4887h;
            i2 = ((i7 >> 8) << 6) + (i7 & 63);
        }
        int i8 = i2;
        C0737c cVar = C0737c.FUNCTION;
        h b5 = b(yVar2, i8, cVar);
        int i10 = yVar2.f;
        int i11 = i10 & 32;
        f fVar3 = g.f3692a;
        if (i11 == 32 || (i10 & 64) == 64) {
            hVar = new a(((l) nVar.f3358a).f3349a, new t(this, yVar2, cVar, 1));
        } else {
            hVar = fVar3;
        }
        if (C1353d.g((C0822l) nVar.f3359c).c(com.samsung.context.sdk.samsunganalytics.internal.sender.c.C(fVar2, yVar2.f4888i)).equals(C.f3337a)) {
            hVar2 = C1211h.b;
        } else {
            hVar2 = (C1211h) nVar.e;
        }
        C1211h hVar3 = hVar2;
        h hVar4 = hVar;
        h hVar5 = hVar4;
        v vVar = new v((C0822l) nVar.f3359c, (K) null, b5, com.samsung.context.sdk.samsunganalytics.internal.sender.c.C(fVar2, yVar2.f4888i), C0246a.g0((C1172z) C1208e.f4971p.c(i8)), yVar2, (C1209f) nVar.b, bVar, hVar3, (m) nVar.g, (Q) null);
        List list = yVar2.l;
        j.d(list, "getTypeParameterList(...)");
        n b8 = nVar.a(vVar, list, (C1209f) nVar.b, (b) nVar.d, (C1211h) nVar.e, (C1204a) nVar.f);
        H h5 = (H) b8.f3360h;
        lf.Q c02 = Gd.a.c0(yVar2, bVar);
        if (c02 == null || (g = h5.g(c02)) == null) {
            uVar = null;
        } else {
            uVar = C1312p.k(vVar, g, hVar5);
        }
        C0822l lVar = (C0822l) nVar.f3359c;
        if (lVar instanceof C0816f) {
            fVar = (C0816f) lVar;
        } else {
            fVar = null;
        }
        if (fVar != null) {
            uVar2 = fVar.v0();
        } else {
            uVar2 = null;
        }
        ArrayList arrayList = yVar2.f4890o;
        if (arrayList.isEmpty()) {
            arrayList = null;
        }
        if (arrayList == null) {
            List list2 = yVar2.f4891p;
            j.d(list2, "getContextReceiverTypeIdList(...)");
            Iterable<Integer> iterable = list2;
            ArrayList arrayList2 = new ArrayList(C1196n.w0(iterable, 10));
            for (Integer num : iterable) {
                j.b(num);
                arrayList2.add(bVar.g(num.intValue()));
            }
            arrayList = arrayList2;
        }
        ArrayList arrayList3 = new ArrayList();
        int i12 = 0;
        for (Object next : arrayList) {
            int i13 = i12 + 1;
            if (i12 >= 0) {
                u e = C1312p.e(vVar, h5.g((lf.Q) next), (C1240g) null, fVar3, i12);
                if (e != null) {
                    arrayList3.add(e);
                }
                i12 = i13;
            } else {
                C1195m.v0();
                throw null;
            }
        }
        List b10 = h5.b();
        List list3 = yVar2.r;
        j.d(list3, "getValueParameterList(...)");
        vVar.S0(uVar, uVar2, arrayList3, b10, ((w) b8.f3361i).g(list3, yVar2, C0737c.FUNCTION), h5.g(Gd.a.e0(yVar2, bVar)), m.e((C1147A) C1208e.e.c(i8)), C0246a.T((f0) C1208e.d.c(i8)), C1203u.d);
        vVar.q = C1208e.q.c(i8).booleanValue();
        vVar.r = C1208e.r.c(i8).booleanValue();
        vVar.s = C1208e.u.c(i8).booleanValue();
        vVar.t = C1208e.s.c(i8).booleanValue();
        vVar.u = C1208e.t.c(i8).booleanValue();
        vVar.y = C1208e.v.c(i8).booleanValue();
        vVar.v = C1208e.w.c(i8).booleanValue();
        vVar.z = !C1208e.f4972x.c(i8).booleanValue();
        ((l) nVar.f3358a).m.getClass();
        return vVar;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v3, resolved type: java.lang.Throwable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v11, resolved type: Te.J} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v12, resolved type: Te.J} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v15, resolved type: Te.J} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v4, resolved type: java.lang.Throwable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v5, resolved type: java.lang.Throwable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v6, resolved type: java.lang.Throwable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r21v7, resolved type: java.lang.Throwable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v30, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v24, resolved type: Te.J} */
    /* JADX WARNING: type inference failed for: r1v13, types: [Te.r, Bf.a] */
    /* JADX WARNING: type inference failed for: r2v21, types: [Te.r, Bf.a] */
    /* JADX WARNING: type inference failed for: r2v25, types: [Gf.h] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:101:0x03d1  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x012d  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0132  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0135  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x013a  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0141  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0144  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x015a  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x015f  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x016c  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0171  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x01a6  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x01be  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x020e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final Ff.u f(lf.G r27) {
        /*
            r26 = this;
            r0 = r26
            r15 = r27
            Df.n r1 = r0.f3367a
            java.lang.Object r2 = r1.d
            r17 = r2
            B1.b r17 = (B1.b) r17
            java.lang.String r2 = "proto"
            kotlin.jvm.internal.j.e(r15, r2)
            int r2 = r15.f
            r3 = 1
            r2 = r2 & r3
            r20 = 6
            if (r2 != r3) goto L_0x001c
            int r2 = r15.g
            goto L_0x0025
        L_0x001c:
            int r2 = r15.f4748h
            r4 = r2 & 63
            int r2 = r2 >> 8
            int r2 = r2 << 6
            int r2 = r2 + r4
        L_0x0025:
            Ff.u r5 = new Ff.u
            java.lang.Object r4 = r1.f3359c
            Qe.l r4 = (Qe.C0822l) r4
            Df.c r6 = Df.C0737c.PROPERTY
            Re.h r6 = r0.b(r15, r2, r6)
            nf.c r7 = nf.C1208e.e
            java.lang.Object r7 = r7.c(r2)
            lf.A r7 = (lf.C1147A) r7
            Qe.A r7 = Df.m.e(r7)
            nf.c r8 = nf.C1208e.d
            java.lang.Object r8 = r8.c(r2)
            lf.f0 r8 = (lf.f0) r8
            Qe.p r8 = o1.C0246a.T(r8)
            nf.b r9 = nf.C1208e.y
            java.lang.Boolean r9 = r9.c(r2)
            boolean r9 = r9.booleanValue()
            java.lang.Object r10 = r1.b
            nf.f r10 = (nf.C1209f) r10
            int r11 = r15.f4749i
            qf.g r10 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.C(r10, r11)
            nf.c r11 = nf.C1208e.f4971p
            java.lang.Object r11 = r11.c(r2)
            lf.z r11 = (lf.C1172z) r11
            Qe.c r11 = o1.C0246a.g0(r11)
            nf.b r12 = nf.C1208e.f4956C
            java.lang.Boolean r12 = r12.c(r2)
            boolean r12 = r12.booleanValue()
            nf.b r13 = nf.C1208e.B
            java.lang.Boolean r13 = r13.c(r2)
            boolean r13 = r13.booleanValue()
            nf.b r14 = nf.C1208e.E
            java.lang.Boolean r14 = r14.c(r2)
            boolean r14 = r14.booleanValue()
            nf.b r3 = nf.C1208e.f4957F
            java.lang.Boolean r3 = r3.c(r2)
            boolean r3 = r3.booleanValue()
            r18 = r3
            nf.b r3 = nf.C1208e.f4958G
            java.lang.Boolean r3 = r3.c(r2)
            boolean r3 = r3.booleanValue()
            r19 = r2
            java.lang.Object r2 = r1.b
            nf.f r2 = (nf.C1209f) r2
            r21 = r2
            java.lang.Object r2 = r1.e
            nf.h r2 = (nf.C1211h) r2
            r22 = r2
            java.lang.Object r2 = r1.g
            Ff.m r2 = (Ff.m) r2
            r23 = r19
            r19 = r2
            r2 = r4
            r4 = r6
            r6 = r8
            r8 = r10
            r10 = r12
            r12 = r14
            r14 = r3
            r3 = 0
            r0 = r1
            r1 = r5
            r5 = r7
            r7 = r9
            r9 = r11
            r11 = r13
            r13 = r18
            r16 = r21
            r18 = r22
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)
            r5 = r1
            r2 = r17
            java.util.List r1 = r15.l
            java.lang.String r3 = "getTypeParameterList(...)"
            kotlin.jvm.internal.j.d(r1, r3)
            Df.n r1 = r0.a(r5, r1, (nf.C1209f) r0.b, (B1.b) r0.d, (nf.C1211h) r0.e, (nf.C1204a) r0.f)
            java.lang.Object r3 = r1.f3360h
            Df.H r3 = (Df.H) r3
            nf.b r4 = nf.C1208e.z
            r10 = r23
            java.lang.Boolean r4 = r4.c(r10)
            boolean r11 = r4.booleanValue()
            r4 = 64
            r6 = 32
            Re.f r7 = Re.g.f3692a
            if (r11 == 0) goto L_0x0112
            int r8 = r15.f
            r9 = r8 & 32
            if (r9 != r6) goto L_0x00f7
            goto L_0x00fa
        L_0x00f7:
            r8 = r8 & r4
            if (r8 != r4) goto L_0x0112
        L_0x00fa:
            Df.c r8 = Df.C0737c.PROPERTY_GETTER
            Ff.a r9 = new Ff.a
            java.lang.Object r12 = r0.f3358a
            Df.l r12 = (Df.l) r12
            Gf.m r12 = r12.f3349a
            Df.t r13 = new Df.t
            r14 = 1
            r16 = r4
            r4 = r26
            r13.<init>(r4, r15, r8, r14)
            r9.<init>(r12, r13)
            goto L_0x0117
        L_0x0112:
            r16 = r4
            r4 = r26
            r9 = r7
        L_0x0117:
            lf.Q r8 = Gd.a.f0(r15, r2)
            Hf.x r8 = r3.g(r8)
            java.util.List r12 = r3.b()
            java.lang.Object r13 = r0.f3359c
            Qe.l r13 = (Qe.C0822l) r13
            boolean r14 = r13 instanceof Qe.C0816f
            r17 = r13
            if (r14 == 0) goto L_0x0132
            r14 = r17
            Qe.f r14 = (Qe.C0816f) r14
            goto L_0x0133
        L_0x0132:
            r14 = 0
        L_0x0133:
            if (r14 == 0) goto L_0x013a
            Te.u r14 = r14.v0()
            goto L_0x013b
        L_0x013a:
            r14 = 0
        L_0x013b:
            int r13 = r15.f
            r4 = r13 & 32
            if (r4 != r6) goto L_0x0144
            lf.Q r4 = r15.m
            goto L_0x0152
        L_0x0144:
            r4 = r13 & 64
            r6 = r16
            if (r4 != r6) goto L_0x0151
            int r4 = r15.n
            lf.Q r4 = r2.g(r4)
            goto L_0x0152
        L_0x0151:
            r4 = 0
        L_0x0152:
            if (r4 == 0) goto L_0x015f
            Hf.x r4 = r3.g(r4)
            if (r4 == 0) goto L_0x015f
            Te.u r4 = tf.C1312p.k(r5, r4, r9)
            goto L_0x0160
        L_0x015f:
            r4 = 0
        L_0x0160:
            java.util.List r6 = r15.f4751o
            r9 = r6
            java.util.Collection r9 = (java.util.Collection) r9
            boolean r9 = r9.isEmpty()
            if (r9 != 0) goto L_0x016c
            goto L_0x016d
        L_0x016c:
            r6 = 0
        L_0x016d:
            r13 = 10
            if (r6 != 0) goto L_0x01a6
            java.util.List r6 = r15.f4752p
            java.lang.String r9 = "getContextReceiverTypeIdList(...)"
            kotlin.jvm.internal.j.d(r6, r9)
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.ArrayList r9 = new java.util.ArrayList
            r16 = r4
            int r4 = ne.C1196n.w0(r6, r13)
            r9.<init>(r4)
            java.util.Iterator r4 = r6.iterator()
        L_0x0189:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L_0x01a4
            java.lang.Object r6 = r4.next()
            java.lang.Integer r6 = (java.lang.Integer) r6
            kotlin.jvm.internal.j.b(r6)
            int r6 = r6.intValue()
            lf.Q r6 = r2.g(r6)
            r9.add(r6)
            goto L_0x0189
        L_0x01a4:
            r6 = r9
            goto L_0x01a8
        L_0x01a6:
            r16 = r4
        L_0x01a8:
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.ArrayList r9 = new java.util.ArrayList
            int r2 = ne.C1196n.w0(r6, r13)
            r9.<init>(r2)
            java.util.Iterator r2 = r6.iterator()
            r6 = 0
        L_0x01b8:
            boolean r18 = r2.hasNext()
            if (r18 == 0) goto L_0x01e2
            java.lang.Object r18 = r2.next()
            int r19 = r6 + 1
            if (r6 < 0) goto L_0x01dd
            r4 = r18
            lf.Q r4 = (lf.Q) r4
            Hf.x r4 = r3.g(r4)
            r18 = r13
            r13 = 0
            Te.u r4 = tf.C1312p.e(r5, r4, r13, r7, r6)
            r9.add(r4)
            r13 = r18
            r6 = r19
            goto L_0x01b8
        L_0x01dd:
            r13 = 0
            ne.C1195m.v0()
            throw r13
        L_0x01e2:
            r3 = r26
            r4 = r5
            r5 = r8
            r6 = r12
            r18 = r13
            r7 = r14
            r8 = r16
            r2 = 0
            r13 = 0
            r4.L0(r5, r6, r7, r8, r9)
            r5 = r4
            nf.b r4 = nf.C1208e.f4966c
            java.lang.Boolean r6 = r4.c(r10)
            boolean r6 = r6.booleanValue()
            nf.c r7 = nf.C1208e.d
            java.lang.Object r8 = r7.c(r10)
            lf.f0 r8 = (lf.f0) r8
            nf.c r9 = nf.C1208e.e
            java.lang.Object r12 = r9.c(r10)
            lf.A r12 = (lf.C1147A) r12
            if (r8 == 0) goto L_0x03d1
            if (r12 == 0) goto L_0x03c9
            if (r6 == 0) goto L_0x0219
            int r4 = r4.f4954a
            r16 = 1
            int r4 = r16 << r4
            goto L_0x021c
        L_0x0219:
            r16 = 1
            r4 = r2
        L_0x021c:
            int r6 = r12.a()
            int r12 = r9.f4954a
            int r6 = r6 << r12
            r4 = r4 | r6
            int r6 = r8.a()
            int r8 = r7.f4954a
            int r6 = r6 << r8
            r17 = r4 | r6
            nf.b r4 = nf.C1208e.f4962K
            r4.getClass()
            nf.b r6 = nf.C1208e.L
            r6.getClass()
            nf.b r8 = nf.C1208e.f4963M
            r8.getClass()
            Qe.S r14 = Qe.Q.f3662a
            if (r11 == 0) goto L_0x02c7
            int r11 = r15.f
            r12 = 256(0x100, float:3.59E-43)
            r11 = r11 & r12
            if (r11 != r12) goto L_0x024a
            int r11 = r15.s
            goto L_0x024c
        L_0x024a:
            r11 = r17
        L_0x024c:
            java.lang.Boolean r12 = r4.c(r11)
            boolean r12 = r12.booleanValue()
            java.lang.Boolean r18 = r6.c(r11)
            boolean r18 = r18.booleanValue()
            java.lang.Boolean r19 = r8.c(r11)
            boolean r19 = r19.booleanValue()
            Df.c r13 = Df.C0737c.PROPERTY_GETTER
            Re.h r13 = r3.b(r15, r11, r13)
            if (r12 == 0) goto L_0x02ab
            r22 = r4
            Te.I r4 = new Te.I
            java.lang.Object r23 = r9.c(r11)
            lf.A r23 = (lf.C1147A) r23
            Qe.A r23 = Df.m.e(r23)
            java.lang.Object r11 = r7.c(r11)
            lf.f0 r11 = (lf.f0) r11
            Qe.p r11 = o1.C0246a.T(r11)
            r12 = r12 ^ 1
            r24 = r9
            r9 = r12
            Qe.c r12 = r5.b()
            r25 = r6
            r6 = r13
            r13 = 0
            r16 = r0
            r3 = r8
            r2 = r10
            r8 = r11
            r10 = r18
            r11 = r19
            r0 = r22
            r21 = 0
            r19 = r1
            r18 = r7
            r7 = r23
            r1 = r25
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
        L_0x02a9:
            r13 = r4
            goto L_0x02bf
        L_0x02ab:
            r16 = r0
            r19 = r1
            r0 = r4
            r1 = r6
            r18 = r7
            r3 = r8
            r24 = r9
            r2 = r10
            r6 = r13
            r21 = 0
            Te.I r4 = tf.C1312p.f(r5, r6)
            goto L_0x02a9
        L_0x02bf:
            Hf.x r4 = r5.getReturnType()
            r13.H0(r4)
            goto L_0x02d5
        L_0x02c7:
            r16 = r0
            r19 = r1
            r0 = r4
            r1 = r6
            r18 = r7
            r3 = r8
            r24 = r9
            r2 = r10
            r21 = r13
        L_0x02d5:
            nf.b r4 = nf.C1208e.f4955A
            java.lang.Boolean r4 = r4.c(r2)
            boolean r4 = r4.booleanValue()
            if (r4 == 0) goto L_0x036e
            int r4 = r15.f
            r6 = 512(0x200, float:7.175E-43)
            r4 = r4 & r6
            if (r4 != r6) goto L_0x02eb
            int r4 = r15.t
            goto L_0x02ed
        L_0x02eb:
            r4 = r17
        L_0x02ed:
            java.lang.Boolean r0 = r0.c(r4)
            boolean r0 = r0.booleanValue()
            java.lang.Boolean r1 = r1.c(r4)
            boolean r10 = r1.booleanValue()
            java.lang.Boolean r1 = r3.c(r4)
            boolean r11 = r1.booleanValue()
            Df.c r1 = Df.C0737c.PROPERTY_SETTER
            r3 = r26
            Re.h r6 = r3.b(r15, r4, r1)
            if (r0 == 0) goto L_0x0366
            Te.J r7 = new Te.J
            r8 = r24
            java.lang.Object r8 = r8.c(r4)
            lf.A r8 = (lf.C1147A) r8
            Qe.A r8 = Df.m.e(r8)
            r9 = r18
            java.lang.Object r4 = r9.c(r4)
            lf.f0 r4 = (lf.f0) r4
            Qe.p r4 = o1.C0246a.T(r4)
            r24 = 1
            r9 = r0 ^ 1
            Qe.c r12 = r5.b()
            r0 = r13
            r13 = 0
            r17 = r8
            r8 = r4
            r4 = r7
            r7 = r17
            r17 = r0
            r0 = r24
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
            ne.t r6 = ne.C1202t.d
            r7 = r19
            Df.n r6 = r7.a(r4, r6, (nf.C1209f) r7.b, (B1.b) r7.d, (nf.C1211h) r7.e, (nf.C1204a) r7.f)
            java.lang.Object r6 = r6.f3361i
            Df.w r6 = (Df.w) r6
            lf.Z r7 = r15.r
            java.util.List r7 = o1.C0246a.e0(r7)
            java.util.List r1 = r6.g(r7, r15, r1)
            java.lang.Object r1 = ne.C1194l.b1(r1)
            Te.Q r1 = (Te.Q) r1
            if (r1 == 0) goto L_0x0362
            r4.q = r1
            r13 = r4
            goto L_0x0375
        L_0x0362:
            Te.J.w0(r20)
            throw r21
        L_0x0366:
            r17 = r13
            r0 = 1
            Te.J r13 = tf.C1312p.g(r5, r6)
            goto L_0x0375
        L_0x036e:
            r3 = r26
            r17 = r13
            r0 = 1
            r13 = r21
        L_0x0375:
            nf.b r1 = nf.C1208e.D
            java.lang.Boolean r1 = r1.c(r2)
            boolean r1 = r1.booleanValue()
            if (r1 == 0) goto L_0x038c
            Df.s r1 = new Df.s
            r2 = 0
            r1.<init>(r3, r15, r5, r2)
            r2 = r21
            r5.J0(r2, r1)
        L_0x038c:
            r1 = r16
            java.lang.Object r1 = r1.f3359c
            Qe.l r1 = (Qe.C0822l) r1
            boolean r2 = r1 instanceof Qe.C0816f
            if (r2 == 0) goto L_0x0399
            Qe.f r1 = (Qe.C0816f) r1
            goto L_0x039a
        L_0x0399:
            r1 = 0
        L_0x039a:
            if (r1 == 0) goto L_0x03a1
            Qe.g r1 = r1.b()
            goto L_0x03a2
        L_0x03a1:
            r1 = 0
        L_0x03a2:
            Qe.g r2 = Qe.C0817g.ANNOTATION_CLASS
            if (r1 != r2) goto L_0x03b0
            Df.s r1 = new Df.s
            r2 = 1
            r1.<init>(r3, r15, r5, r2)
            r2 = 0
            r5.J0(r2, r1)
        L_0x03b0:
            Te.r r1 = new Te.r
            r2 = 0
            Re.h r2 = r3.c(r15, r2)
            r1.<init>((Re.h) r2)
            Te.r r2 = new Te.r
            Re.h r0 = r3.c(r15, r0)
            r2.<init>((Re.h) r0)
            r0 = r17
            r5.I0(r0, r13, r1, r2)
            return r5
        L_0x03c9:
            r0 = 11
            nf.C1208e.a(r0)
            r21 = 0
            throw r21
        L_0x03d1:
            r21 = r13
            nf.C1208e.a(r18)
            throw r21
        */
        throw new UnsupportedOperationException("Method not decompiled: Df.w.f(lf.G):Ff.u");
    }

    public final List g(List list, C1264n nVar, C0737c cVar) {
        int i2;
        h hVar;
        int i7;
        lf.Q q;
        w wVar = this;
        n nVar2 = wVar.f3367a;
        b bVar = (b) nVar2.d;
        H h5 = (H) nVar2.f3360h;
        C0822l lVar = (C0822l) nVar2.f3359c;
        j.c(lVar, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.CallableDescriptor");
        C0812b bVar2 = (C0812b) lVar;
        C0822l g = bVar2.g();
        j.d(g, "getContainingDeclaration(...)");
        z a7 = wVar.a(g);
        Iterable iterable = list;
        ArrayList arrayList = new ArrayList(C1196n.w0(iterable, 10));
        int i8 = 0;
        for (Object next : iterable) {
            int i10 = i8 + 1;
            C0774x xVar = null;
            if (i8 >= 0) {
                Z z = (Z) next;
                if ((z.f & 1) == 1) {
                    i2 = z.g;
                } else {
                    i2 = 0;
                }
                if (a7 == null || !C1208e.f4966c.c(i2).booleanValue()) {
                    i7 = i8;
                    hVar = g.f3692a;
                } else {
                    i7 = i8;
                    hVar = new y(((l) nVar2.f3358a).f3349a, new v(wVar, a7, nVar, cVar, i7, z));
                }
                C1240g C5 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.C((C1209f) nVar2.b, z.f4802h);
                C0774x g3 = h5.g(Gd.a.j0(z, bVar));
                boolean booleanValue = C1208e.f4959H.c(i2).booleanValue();
                boolean booleanValue2 = C1208e.f4960I.c(i2).booleanValue();
                boolean booleanValue3 = C1208e.f4961J.c(i2).booleanValue();
                int i11 = z.f;
                if ((i11 & 16) == 16) {
                    q = z.k;
                } else if ((i11 & 32) == 32) {
                    q = bVar.g(z.l);
                } else {
                    q = null;
                }
                if (q != null) {
                    xVar = h5.g(q);
                }
                ArrayList arrayList2 = arrayList;
                arrayList2.add(new Te.Q(bVar2, (Te.Q) null, i7, hVar, C5, g3, booleanValue, booleanValue2, booleanValue3, xVar, Q.f3662a));
                arrayList = arrayList2;
                i8 = i10;
                wVar = this;
            } else {
                C1195m.v0();
                throw null;
            }
        }
        return C1194l.k1(arrayList);
    }
}
