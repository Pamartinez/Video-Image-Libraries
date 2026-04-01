package Te;

import Af.p;
import Af.u;
import D0.e;
import Gf.m;
import Hf.B;
import Hf.C0754c;
import Hf.C0759h;
import Hf.C0761j;
import Hf.C0774x;
import Hf.I;
import Hf.M;
import Hf.T;
import Hf.X;
import Hf.a0;
import Hf.d0;
import If.f;
import Qe.A;
import Qe.C0816f;
import Qe.C0817g;
import Qe.C0822l;
import Qe.C0823m;
import Qe.C0824n;
import Qe.C0826p;
import Qe.Q;
import Qe.V;
import Re.h;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.j;
import o1.C0246a;
import qf.C1240g;
import tf.C1301e;
import xf.C1353d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class x extends y {
    public final y d;
    public final X e;
    public X f;
    public ArrayList g;

    /* renamed from: h  reason: collision with root package name */
    public ArrayList f3809h;

    /* renamed from: i  reason: collision with root package name */
    public C0761j f3810i;

    public x(y yVar, X x9) {
        this.d = yVar;
        this.e = x9;
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x00bc  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00c2  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x00c6 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00e3 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ void L(int r15) {
        /*
            r0 = 23
            r1 = 13
            r2 = 10
            r3 = 8
            r4 = 6
            r5 = 5
            r6 = 3
            r7 = 2
            if (r15 == r7) goto L_0x001f
            if (r15 == r6) goto L_0x001f
            if (r15 == r5) goto L_0x001f
            if (r15 == r4) goto L_0x001f
            if (r15 == r3) goto L_0x001f
            if (r15 == r2) goto L_0x001f
            if (r15 == r1) goto L_0x001f
            if (r15 == r0) goto L_0x001f
            java.lang.String r8 = "@NotNull method %s.%s must not return null"
            goto L_0x0021
        L_0x001f:
            java.lang.String r8 = "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        L_0x0021:
            if (r15 == r7) goto L_0x0033
            if (r15 == r6) goto L_0x0033
            if (r15 == r5) goto L_0x0033
            if (r15 == r4) goto L_0x0033
            if (r15 == r3) goto L_0x0033
            if (r15 == r2) goto L_0x0033
            if (r15 == r1) goto L_0x0033
            if (r15 == r0) goto L_0x0033
            r9 = r7
            goto L_0x0034
        L_0x0033:
            r9 = r6
        L_0x0034:
            java.lang.Object[] r9 = new java.lang.Object[r9]
            java.lang.String r10 = "kotlin/reflect/jvm/internal/impl/descriptors/impl/LazySubstitutingClassDescriptor"
            r11 = 0
            if (r15 == r7) goto L_0x005b
            if (r15 == r6) goto L_0x0056
            if (r15 == r5) goto L_0x0051
            if (r15 == r4) goto L_0x0056
            if (r15 == r3) goto L_0x005b
            if (r15 == r2) goto L_0x0051
            if (r15 == r1) goto L_0x0056
            if (r15 == r0) goto L_0x004c
            r9[r11] = r10
            goto L_0x005f
        L_0x004c:
            java.lang.String r12 = "substitutor"
            r9[r11] = r12
            goto L_0x005f
        L_0x0051:
            java.lang.String r12 = "typeSubstitution"
            r9[r11] = r12
            goto L_0x005f
        L_0x0056:
            java.lang.String r12 = "kotlinTypeRefiner"
            r9[r11] = r12
            goto L_0x005f
        L_0x005b:
            java.lang.String r12 = "typeArguments"
            r9[r11] = r12
        L_0x005f:
            java.lang.String r11 = "getMemberScope"
            java.lang.String r12 = "getUnsubstitutedMemberScope"
            java.lang.String r13 = "substitute"
            r14 = 1
            switch(r15) {
                case 2: goto L_0x00c2;
                case 3: goto L_0x00c2;
                case 4: goto L_0x00bf;
                case 5: goto L_0x00c2;
                case 6: goto L_0x00c2;
                case 7: goto L_0x00bf;
                case 8: goto L_0x00c2;
                case 9: goto L_0x00bf;
                case 10: goto L_0x00c2;
                case 11: goto L_0x00bf;
                case 12: goto L_0x00bc;
                case 13: goto L_0x00c2;
                case 14: goto L_0x00bc;
                case 15: goto L_0x00b7;
                case 16: goto L_0x00b2;
                case 17: goto L_0x00ad;
                case 18: goto L_0x00a8;
                case 19: goto L_0x00a3;
                case 20: goto L_0x009e;
                case 21: goto L_0x0099;
                case 22: goto L_0x0094;
                case 23: goto L_0x00c2;
                case 24: goto L_0x0091;
                case 25: goto L_0x008c;
                case 26: goto L_0x0087;
                case 27: goto L_0x0082;
                case 28: goto L_0x007d;
                case 29: goto L_0x0078;
                case 30: goto L_0x0073;
                case 31: goto L_0x006e;
                default: goto L_0x0069;
            }
        L_0x0069:
            java.lang.String r10 = "getTypeConstructor"
            r9[r14] = r10
            goto L_0x00c4
        L_0x006e:
            java.lang.String r10 = "getSealedSubclasses"
            r9[r14] = r10
            goto L_0x00c4
        L_0x0073:
            java.lang.String r10 = "getDeclaredTypeParameters"
            r9[r14] = r10
            goto L_0x00c4
        L_0x0078:
            java.lang.String r10 = "getSource"
            r9[r14] = r10
            goto L_0x00c4
        L_0x007d:
            java.lang.String r10 = "getUnsubstitutedInnerClassesScope"
            r9[r14] = r10
            goto L_0x00c4
        L_0x0082:
            java.lang.String r10 = "getVisibility"
            r9[r14] = r10
            goto L_0x00c4
        L_0x0087:
            java.lang.String r10 = "getModality"
            r9[r14] = r10
            goto L_0x00c4
        L_0x008c:
            java.lang.String r10 = "getKind"
            r9[r14] = r10
            goto L_0x00c4
        L_0x0091:
            r9[r14] = r13
            goto L_0x00c4
        L_0x0094:
            java.lang.String r10 = "getContainingDeclaration"
            r9[r14] = r10
            goto L_0x00c4
        L_0x0099:
            java.lang.String r10 = "getOriginal"
            r9[r14] = r10
            goto L_0x00c4
        L_0x009e:
            java.lang.String r10 = "getName"
            r9[r14] = r10
            goto L_0x00c4
        L_0x00a3:
            java.lang.String r10 = "getAnnotations"
            r9[r14] = r10
            goto L_0x00c4
        L_0x00a8:
            java.lang.String r10 = "getConstructors"
            r9[r14] = r10
            goto L_0x00c4
        L_0x00ad:
            java.lang.String r10 = "getContextReceivers"
            r9[r14] = r10
            goto L_0x00c4
        L_0x00b2:
            java.lang.String r10 = "getDefaultType"
            r9[r14] = r10
            goto L_0x00c4
        L_0x00b7:
            java.lang.String r10 = "getStaticScope"
            r9[r14] = r10
            goto L_0x00c4
        L_0x00bc:
            r9[r14] = r12
            goto L_0x00c4
        L_0x00bf:
            r9[r14] = r11
            goto L_0x00c4
        L_0x00c2:
            r9[r14] = r10
        L_0x00c4:
            if (r15 == r7) goto L_0x00db
            if (r15 == r6) goto L_0x00db
            if (r15 == r5) goto L_0x00db
            if (r15 == r4) goto L_0x00db
            if (r15 == r3) goto L_0x00db
            if (r15 == r2) goto L_0x00db
            if (r15 == r1) goto L_0x00d8
            if (r15 == r0) goto L_0x00d5
            goto L_0x00dd
        L_0x00d5:
            r9[r7] = r13
            goto L_0x00dd
        L_0x00d8:
            r9[r7] = r12
            goto L_0x00dd
        L_0x00db:
            r9[r7] = r11
        L_0x00dd:
            java.lang.String r8 = java.lang.String.format(r8, r9)
            if (r15 == r7) goto L_0x00f7
            if (r15 == r6) goto L_0x00f7
            if (r15 == r5) goto L_0x00f7
            if (r15 == r4) goto L_0x00f7
            if (r15 == r3) goto L_0x00f7
            if (r15 == r2) goto L_0x00f7
            if (r15 == r1) goto L_0x00f7
            if (r15 == r0) goto L_0x00f7
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            r15.<init>(r8)
            goto L_0x00fc
        L_0x00f7:
            java.lang.IllegalArgumentException r15 = new java.lang.IllegalArgumentException
            r15.<init>(r8)
        L_0x00fc:
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: Te.x.L(int):void");
    }

    public final p K(f fVar) {
        p K6 = this.d.K(fVar);
        if (!this.e.f3438a.e()) {
            return new u(K6, O());
        }
        if (K6 != null) {
            return K6;
        }
        L(14);
        throw null;
    }

    public final p M() {
        p M2 = this.d.M();
        if (M2 != null) {
            return M2;
        }
        L(28);
        throw null;
    }

    /* JADX WARNING: type inference failed for: r7v4, types: [Hf.x] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final Qe.W N() {
        /*
            r7 = this;
            Te.y r0 = r7.d
            Qe.W r0 = r0.N()
            if (r0 != 0) goto L_0x000a
            r7 = 0
            return r7
        L_0x000a:
            boolean r1 = r0 instanceof Qe.C0832w
            Hf.X r2 = r7.e
            if (r1 == 0) goto L_0x0036
            Qe.w r1 = new Qe.w
            Qe.w r0 = (Qe.C0832w) r0
            qf.g r3 = r0.f3683a
            Kf.e r0 = r0.b
            Hf.B r0 = (Hf.B) r0
            if (r0 == 0) goto L_0x0032
            Hf.T r2 = r2.f3438a
            boolean r2 = r2.e()
            if (r2 == 0) goto L_0x0025
            goto L_0x0032
        L_0x0025:
            Hf.X r7 = r7.O()
            Hf.d0 r2 = Hf.d0.INVARIANT
            Hf.x r7 = r7.i(r0, r2)
            r0 = r7
            Hf.B r0 = (Hf.B) r0
        L_0x0032:
            r1.<init>(r3, r0)
            return r1
        L_0x0036:
            boolean r1 = r0 instanceof Qe.D
            if (r1 == 0) goto L_0x0089
            Qe.D r0 = (Qe.D) r0
            java.util.ArrayList r0 = r0.f3656a
            java.util.ArrayList r1 = new java.util.ArrayList
            r3 = 10
            int r3 = ne.C1196n.w0(r0, r3)
            r1.<init>(r3)
            java.util.Iterator r0 = r0.iterator()
        L_0x004d:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x0083
            java.lang.Object r3 = r0.next()
            me.i r3 = (me.i) r3
            java.lang.Object r4 = r3.d
            qf.g r4 = (qf.C1240g) r4
            java.lang.Object r3 = r3.e
            Kf.e r3 = (Kf.e) r3
            Hf.B r3 = (Hf.B) r3
            if (r3 == 0) goto L_0x007a
            Hf.T r5 = r2.f3438a
            boolean r5 = r5.e()
            if (r5 == 0) goto L_0x006e
            goto L_0x007a
        L_0x006e:
            Hf.X r5 = r7.O()
            Hf.d0 r6 = Hf.d0.INVARIANT
            Hf.x r3 = r5.i(r3, r6)
            Hf.B r3 = (Hf.B) r3
        L_0x007a:
            me.i r5 = new me.i
            r5.<init>(r4, r3)
            r1.add(r5)
            goto L_0x004d
        L_0x0083:
            Qe.D r7 = new Qe.D
            r7.<init>(r1)
            return r7
        L_0x0089:
            Dd.a r7 = new Dd.a
            r7.<init>()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: Te.x.N():Qe.W");
    }

    public final X O() {
        if (this.f == null) {
            X x9 = this.e;
            if (x9.f3438a.e()) {
                this.f = x9;
            } else {
                List parameters = this.d.q().getParameters();
                this.g = new ArrayList(parameters.size());
                this.f = C0754c.A(parameters, x9.f(), this, this.g);
                ArrayList arrayList = this.g;
                j.e(arrayList, "<this>");
                ArrayList arrayList2 = new ArrayList();
                for (Object next : arrayList) {
                    if (!((V) next).J()) {
                        arrayList2.add(next);
                    }
                }
                this.f3809h = arrayList2;
            }
        }
        return this.f;
    }

    public final p P() {
        C1353d.i(C1301e.d(this.d));
        return K(f.f3461a);
    }

    public final boolean Q() {
        return this.d.Q();
    }

    public final List R() {
        List list = Collections.EMPTY_LIST;
        if (list != null) {
            return list;
        }
        L(17);
        throw null;
    }

    public final boolean T() {
        return this.d.T();
    }

    public final boolean X() {
        return this.d.X();
    }

    public final C0816f a() {
        C0816f a7 = this.d.a();
        if (a7 != null) {
            return a7;
        }
        L(21);
        throw null;
    }

    public final p a0(T t) {
        C1353d.i(C1301e.d(this));
        return n(t, f.f3461a);
    }

    public final C0817g b() {
        C0817g b = this.d.b();
        if (b != null) {
            return b;
        }
        L(25);
        throw null;
    }

    public final boolean b0() {
        return this.d.b0();
    }

    public final C0823m c(X x9) {
        if (x9 == null) {
            L(23);
            throw null;
        } else if (x9.f3438a.e()) {
            return this;
        } else {
            return new x(this, X.e(x9.f(), O().f()));
        }
    }

    public final p c0() {
        p c02 = this.d.c0();
        if (c02 != null) {
            return c02;
        }
        L(15);
        throw null;
    }

    public final Collection d() {
        Collection<C0848i> d2 = this.d.d();
        ArrayList arrayList = new ArrayList(d2.size());
        for (C0848i iVar : d2) {
            t tVar = iVar;
            tVar.getClass();
            C0857s K02 = tVar.K0(X.b);
            K02.f3789h = iVar.a();
            K02.H(tVar.k());
            K02.t(tVar.getVisibility());
            K02.u(tVar.b());
            K02.f3793p = false;
            arrayList.add(((C0848i) K02.f3788A.H0(K02)).c(O()));
        }
        return arrayList;
    }

    public final Collection e() {
        Collection e7 = this.d.e();
        if (e7 != null) {
            return e7;
        }
        L(31);
        throw null;
    }

    public final C0822l g() {
        C0822l g3 = this.d.g();
        if (g3 != null) {
            return g3;
        }
        L(22);
        throw null;
    }

    public final h getAnnotations() {
        h annotations = this.d.getAnnotations();
        if (annotations != null) {
            return annotations;
        }
        L(19);
        throw null;
    }

    public final C1240g getName() {
        C1240g name = this.d.getName();
        if (name != null) {
            return name;
        }
        L(20);
        throw null;
    }

    public final Q getSource() {
        return Q.f3662a;
    }

    public final C0826p getVisibility() {
        C0826p visibility = this.d.getVisibility();
        if (visibility != null) {
            return visibility;
        }
        L(27);
        throw null;
    }

    public final B i() {
        I i2;
        List d2 = a0.d(q().getParameters());
        h annotations = getAnnotations();
        if (annotations.isEmpty()) {
            I.e.getClass();
            i2 = I.f;
        } else {
            e eVar = I.e;
            List e02 = C0246a.e0(new C0759h(annotations));
            eVar.getClass();
            i2 = e.D(e02);
        }
        return C0754c.v(P(), i2, q(), d2, false);
    }

    public final boolean isExternal() {
        return this.d.isExternal();
    }

    public final boolean isInline() {
        return this.d.isInline();
    }

    public final List j() {
        O();
        ArrayList arrayList = this.f3809h;
        if (arrayList != null) {
            return arrayList;
        }
        L(30);
        throw null;
    }

    public final A k() {
        A k = this.d.k();
        if (k != null) {
            return k;
        }
        L(26);
        throw null;
    }

    public final boolean l() {
        return this.d.l();
    }

    public final p n(T t, f fVar) {
        p n = this.d.n(t, fVar);
        if (!this.e.f3438a.e()) {
            return new u(n, O());
        }
        if (n != null) {
            return n;
        }
        L(7);
        throw null;
    }

    public final M q() {
        M q = this.d.q();
        if (!this.e.f3438a.e()) {
            if (this.f3810i == null) {
                X O4 = O();
                Collection<C0774x> h5 = q.h();
                ArrayList arrayList = new ArrayList(h5.size());
                for (C0774x i2 : h5) {
                    arrayList.add(O4.i(i2, d0.INVARIANT));
                }
                this.f3810i = new C0761j(this, this.g, arrayList, m.e);
            }
            C0761j jVar = this.f3810i;
            if (jVar != null) {
                return jVar;
            }
            L(1);
            throw null;
        } else if (q != null) {
            return q;
        } else {
            L(0);
            throw null;
        }
    }

    public final boolean s() {
        return this.d.s();
    }

    public final boolean t0() {
        return this.d.t0();
    }

    public final Object v(C0824n nVar, Object obj) {
        return nVar.f(this, obj);
    }

    public final u v0() {
        throw new UnsupportedOperationException();
    }

    public final C0848i y() {
        return this.d.y();
    }
}
