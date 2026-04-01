package Ke;

import D1.f;
import Qe.C0831v;
import Te.C0852m;
import Te.I;
import Te.J;
import Ze.w;
import a.C0068a;
import kotlin.jvm.internal.j;
import pf.e;
import qf.C1235b;
import qf.C1236c;
import qf.C1240g;
import xf.C1353d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class C0 {

    /* renamed from: a  reason: collision with root package name */
    public static final C1235b f3487a;

    static {
        C1236c cVar = new C1236c("java.lang.Void");
        C1236c e = cVar.e();
        C1240g f = cVar.f();
        j.d(f, "shortName(...)");
        f3487a = new C1235b(e, f);
    }

    public static C0793k a(C0831v vVar) {
        String s = f.s(vVar);
        if (s == null) {
            if (vVar instanceof I) {
                String b = C1353d.k(vVar).getName().b();
                j.d(b, "asString(...)");
                s = w.a(b);
            } else if (vVar instanceof J) {
                String b5 = C1353d.k(vVar).getName().b();
                j.d(b5, "asString(...)");
                s = w.b(b5);
            } else {
                s = ((C0852m) vVar).getName().b();
                j.d(s, "asString(...)");
            }
        }
        return new C0793k(new e(s, C0068a.m(vVar, 1)));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: Ke.k} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.lang.reflect.Method} */
    /* JADX WARNING: type inference failed for: r0v1 */
    /* JADX WARNING: type inference failed for: r0v10 */
    /* JADX WARNING: type inference failed for: r0v11 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static a.C0068a b(Qe.O r6) {
        /*
            java.lang.String r0 = "possiblyOverriddenProperty"
            kotlin.jvm.internal.j.e(r6, r0)
            Qe.d r6 = tf.C1301e.t(r6)
            Qe.O r6 = (Qe.O) r6
            Qe.O r1 = r6.a()
            java.lang.String r6 = "getOriginal(...)"
            kotlin.jvm.internal.j.d(r1, r6)
            boolean r6 = r1 instanceof Ff.u
            r0 = 0
            if (r6 == 0) goto L_0x0037
            r6 = r1
            Ff.u r6 = (Ff.u) r6
            lf.G r2 = r6.E
            rf.p r3 = of.k.d
            java.lang.String r4 = "propertySignature"
            kotlin.jvm.internal.j.d(r3, r4)
            java.lang.Object r3 = D1.f.q(r2, r3)
            of.e r3 = (of.e) r3
            if (r3 == 0) goto L_0x00b4
            Ke.n r0 = new Ke.n
            nf.f r4 = r6.f3400F
            B1.b r5 = r6.f3401G
            r0.<init>(r1, r2, r3, r4, r5)
            return r0
        L_0x0037:
            boolean r6 = r1 instanceof bf.h
            if (r6 == 0) goto L_0x00b4
            r6 = r1
            bf.h r6 = (bf.h) r6
            Qe.Q r2 = r6.getSource()
            boolean r3 = r2 instanceof Ve.f
            if (r3 == 0) goto L_0x0049
            Ve.f r2 = (Ve.f) r2
            goto L_0x004a
        L_0x0049:
            r2 = r0
        L_0x004a:
            if (r2 == 0) goto L_0x004f
            We.s r2 = r2.d
            goto L_0x0050
        L_0x004f:
            r2 = r0
        L_0x0050:
            boolean r3 = r2 instanceof We.u
            if (r3 == 0) goto L_0x005e
            Ke.l r6 = new Ke.l
            We.u r2 = (We.u) r2
            java.lang.reflect.Field r0 = r2.f3894a
            r6.<init>(r0)
            return r6
        L_0x005e:
            boolean r3 = r2 instanceof We.x
            if (r3 == 0) goto L_0x0092
            Ke.m r1 = new Ke.m
            We.x r2 = (We.x) r2
            java.lang.reflect.Method r2 = r2.f3895a
            Te.J r6 = r6.B
            if (r6 == 0) goto L_0x0073
            Te.n r6 = (Te.C0853n) r6
            Qe.Q r6 = r6.getSource()
            goto L_0x0074
        L_0x0073:
            r6 = r0
        L_0x0074:
            boolean r3 = r6 instanceof Ve.f
            if (r3 == 0) goto L_0x007b
            Ve.f r6 = (Ve.f) r6
            goto L_0x007c
        L_0x007b:
            r6 = r0
        L_0x007c:
            if (r6 == 0) goto L_0x0081
            We.s r6 = r6.d
            goto L_0x0082
        L_0x0081:
            r6 = r0
        L_0x0082:
            boolean r3 = r6 instanceof We.x
            if (r3 == 0) goto L_0x0089
            We.x r6 = (We.x) r6
            goto L_0x008a
        L_0x0089:
            r6 = r0
        L_0x008a:
            if (r6 == 0) goto L_0x008e
            java.lang.reflect.Method r0 = r6.f3895a
        L_0x008e:
            r1.<init>(r2, r0)
            return r1
        L_0x0092:
            Ke.v0 r6 = new Ke.v0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r3 = "Incorrect resolution sequence for Java field "
            r0.<init>(r3)
            r0.append(r1)
            java.lang.String r1 = " (source = "
            r0.append(r1)
            r0.append(r2)
            r1 = 41
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r1 = 0
            r6.<init>(r0, r1)
            throw r6
        L_0x00b4:
            Te.I r6 = r1.getGetter()
            kotlin.jvm.internal.j.b(r6)
            Ke.k r6 = a(r6)
            Te.J r1 = r1.getSetter()
            if (r1 == 0) goto L_0x00c9
            Ke.k r0 = a(r1)
        L_0x00c9:
            Ke.o r1 = new Ke.o
            r1.<init>(r6, r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: Ke.C0.b(Qe.O):a.a");
    }

    /* JADX WARNING: type inference failed for: r2v11, types: [We.s] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static L2.a c(Qe.C0831v r7) {
        /*
            java.lang.String r0 = "possiblySubstitutedFunction"
            kotlin.jvm.internal.j.e(r7, r0)
            Qe.d r0 = tf.C1301e.t(r7)
            Qe.v r0 = (Qe.C0831v) r0
            Qe.v r0 = r0.a()
            java.lang.String r1 = "getOriginal(...)"
            kotlin.jvm.internal.j.d(r0, r1)
            boolean r1 = r0 instanceof Ff.b
            if (r1 == 0) goto L_0x0135
            r1 = r0
            Ff.n r1 = (Ff.n) r1
            rf.b r2 = r1.Y()
            boolean r3 = r2 instanceof lf.C1171y
            if (r3 == 0) goto L_0x003c
            rf.h r3 = pf.i.f5029a
            r3 = r2
            lf.y r3 = (lf.C1171y) r3
            nf.f r4 = r1.C()
            B1.b r5 = r1.z()
            pf.e r3 = pf.i.c(r3, r4, r5)
            if (r3 == 0) goto L_0x003c
            Ke.k r7 = new Ke.k
            r7.<init>(r3)
            return r7
        L_0x003c:
            boolean r3 = r2 instanceof lf.C1159l
            if (r3 == 0) goto L_0x0130
            rf.h r3 = pf.i.f5029a
            lf.l r2 = (lf.C1159l) r2
            nf.f r3 = r1.C()
            B1.b r1 = r1.z()
            pf.e r1 = pf.i.a(r2, r3, r1)
            if (r1 == 0) goto L_0x0130
            java.lang.String r0 = r1.d
            java.lang.String r2 = r1.e
            Qe.l r3 = r7.g()
            java.lang.String r4 = "getContainingDeclaration(...)"
            kotlin.jvm.internal.j.d(r3, r4)
            boolean r3 = tf.C1305i.b(r3)
            if (r3 == 0) goto L_0x006b
            Ke.k r7 = new Ke.k
            r7.<init>(r1)
            return r7
        L_0x006b:
            Qe.l r3 = r7.g()
            kotlin.jvm.internal.j.d(r3, r4)
            boolean r3 = tf.C1305i.d(r3)
            if (r3 == 0) goto L_0x012a
            Qe.k r7 = (Qe.C0821k) r7
            boolean r3 = r7.V()
            java.lang.String r4 = ")V"
            java.lang.String r5 = "constructor-impl"
            java.lang.String r6 = "Invalid signature: "
            if (r3 == 0) goto L_0x00a9
            boolean r7 = kotlin.jvm.internal.j.a(r0, r5)
            if (r7 == 0) goto L_0x0093
            boolean r7 = Tf.v.o0(r2, r4)
            if (r7 == 0) goto L_0x0093
            goto L_0x00f8
        L_0x0093:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>(r6)
            r7.append(r1)
            java.lang.String r7 = r7.toString()
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r7 = r7.toString()
            r0.<init>(r7)
            throw r0
        L_0x00a9:
            boolean r3 = kotlin.jvm.internal.j.a(r0, r5)
            if (r3 == 0) goto L_0x0114
            Qe.f r7 = r7.W()
            java.lang.String r3 = "getConstructedClass(...)"
            kotlin.jvm.internal.j.d(r7, r3)
            qf.b r7 = xf.C1353d.f(r7)
            kotlin.jvm.internal.j.b(r7)
            java.lang.String r7 = r7.b()
            java.lang.String r7 = pf.b.b(r7)
            boolean r3 = Tf.v.o0(r2, r4)
            if (r3 == 0) goto L_0x00f2
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = "V"
            java.lang.String r2 = Tf.n.I0(r2, r3)
            r1.append(r2)
            r1.append(r7)
            java.lang.String r7 = r1.toString()
            java.lang.String r1 = "name"
            kotlin.jvm.internal.j.e(r0, r1)
            java.lang.String r1 = "desc"
            kotlin.jvm.internal.j.e(r7, r1)
            pf.e r1 = new pf.e
            r1.<init>(r0, r7)
            goto L_0x00f8
        L_0x00f2:
            boolean r7 = Tf.v.o0(r2, r7)
            if (r7 == 0) goto L_0x00fe
        L_0x00f8:
            Ke.k r7 = new Ke.k
            r7.<init>(r1)
            return r7
        L_0x00fe:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>(r6)
            r7.append(r1)
            java.lang.String r7 = r7.toString()
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r7 = r7.toString()
            r0.<init>(r7)
            throw r0
        L_0x0114:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>(r6)
            r7.append(r1)
            java.lang.String r7 = r7.toString()
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r7 = r7.toString()
            r0.<init>(r7)
            throw r0
        L_0x012a:
            Ke.j r7 = new Ke.j
            r7.<init>(r1)
            return r7
        L_0x0130:
            Ke.k r7 = a(r0)
            return r7
        L_0x0135:
            boolean r7 = r0 instanceof bf.g
            r1 = 0
            r2 = 0
            if (r7 == 0) goto L_0x0177
            r7 = r0
            bf.g r7 = (bf.g) r7
            Qe.Q r7 = r7.getSource()
            boolean r3 = r7 instanceof Ve.f
            if (r3 == 0) goto L_0x0149
            Ve.f r7 = (Ve.f) r7
            goto L_0x014a
        L_0x0149:
            r7 = r2
        L_0x014a:
            if (r7 == 0) goto L_0x014f
            We.s r7 = r7.d
            goto L_0x0150
        L_0x014f:
            r7 = r2
        L_0x0150:
            boolean r3 = r7 instanceof We.x
            if (r3 == 0) goto L_0x0157
            r2 = r7
            We.x r2 = (We.x) r2
        L_0x0157:
            if (r2 == 0) goto L_0x0163
            java.lang.reflect.Method r7 = r2.f3895a
            if (r7 == 0) goto L_0x0163
            Ke.i r0 = new Ke.i
            r0.<init>(r7)
            return r0
        L_0x0163:
            Ke.v0 r7 = new Ke.v0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Incorrect resolution sequence for Java method "
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r7.<init>(r0, r1)
            throw r7
        L_0x0177:
            boolean r7 = r0 instanceof bf.C0917b
            r3 = 41
            java.lang.String r4 = " ("
            if (r7 == 0) goto L_0x01d2
            r7 = r0
            bf.b r7 = (bf.C0917b) r7
            Qe.Q r7 = r7.getSource()
            boolean r5 = r7 instanceof Ve.f
            if (r5 == 0) goto L_0x018d
            Ve.f r7 = (Ve.f) r7
            goto L_0x018e
        L_0x018d:
            r7 = r2
        L_0x018e:
            if (r7 == 0) goto L_0x0192
            We.s r2 = r7.d
        L_0x0192:
            boolean r7 = r2 instanceof We.r
            if (r7 == 0) goto L_0x01a0
            Ke.h r7 = new Ke.h
            We.r r2 = (We.r) r2
            java.lang.reflect.Constructor r0 = r2.f3893a
            r7.<init>(r0)
            return r7
        L_0x01a0:
            boolean r7 = r2 instanceof We.o
            if (r7 == 0) goto L_0x01b5
            r7 = r2
            We.o r7 = (We.o) r7
            java.lang.Class r7 = r7.f3891a
            boolean r5 = r7.isAnnotation()
            if (r5 == 0) goto L_0x01b5
            Ke.g r0 = new Ke.g
            r0.<init>(r7)
            return r0
        L_0x01b5:
            Ke.v0 r7 = new Ke.v0
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "Incorrect resolution sequence for Java constructor "
            r5.<init>(r6)
            r5.append(r0)
            r5.append(r4)
            r5.append(r2)
            r5.append(r3)
            java.lang.String r0 = r5.toString()
            r7.<init>(r0, r1)
            throw r7
        L_0x01d2:
            r7 = r0
            Te.m r7 = (Te.C0852m) r7
            qf.g r2 = r7.getName()
            qf.g r5 = Ne.q.f3575c
            boolean r2 = r2.equals(r5)
            if (r2 == 0) goto L_0x01e8
            boolean r2 = tf.C1312p.n(r0)
            if (r2 == 0) goto L_0x01e8
            goto L_0x0211
        L_0x01e8:
            qf.g r2 = r7.getName()
            qf.g r5 = Ne.q.f3574a
            boolean r2 = r2.equals(r5)
            if (r2 == 0) goto L_0x01fb
            boolean r2 = tf.C1312p.n(r0)
            if (r2 == 0) goto L_0x01fb
            goto L_0x0211
        L_0x01fb:
            qf.g r7 = r7.getName()
            qf.g r2 = Pe.a.e
            boolean r7 = kotlin.jvm.internal.j.a(r7, r2)
            if (r7 == 0) goto L_0x0216
            java.util.List r7 = r0.B()
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto L_0x0216
        L_0x0211:
            Ke.k r7 = a(r0)
            return r7
        L_0x0216:
            Ke.v0 r7 = new Ke.v0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r5 = "Unknown origin of "
            r2.<init>(r5)
            r2.append(r0)
            r2.append(r4)
            java.lang.Class r0 = r0.getClass()
            r2.append(r0)
            r2.append(r3)
            java.lang.String r0 = r2.toString()
            r7.<init>(r0, r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: Ke.C0.c(Qe.v):L2.a");
    }
}
