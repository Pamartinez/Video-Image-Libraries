package Ff;

import Af.p;
import Af.q;
import B1.b;
import D0.f;
import Df.H;
import Df.n;
import Df.x;
import Gf.h;
import Gf.i;
import Hf.M;
import If.l;
import Qe.A;
import Qe.C0816f;
import Qe.C0817g;
import Qe.C0822l;
import Qe.C0826p;
import Qe.P;
import Qe.Q;
import Qe.W;
import Re.g;
import Te.C0841b;
import Te.C0848i;
import Te.u;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.jvm.internal.j;
import lf.C1156i;
import lf.C1157j;
import ne.C1196n;
import nf.C1204a;
import nf.C1208e;
import qf.C1235b;
import qf.C1240g;
import xf.C1353d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class k extends C0841b implements C0822l {

    /* renamed from: h  reason: collision with root package name */
    public final C1157j f3386h;

    /* renamed from: i  reason: collision with root package name */
    public final C1204a f3387i;

    /* renamed from: j  reason: collision with root package name */
    public final Q f3388j;
    public final C1235b k;
    public final A l;
    public final C0826p m;
    public final C0817g n;

    /* renamed from: o  reason: collision with root package name */
    public final n f3389o;

    /* renamed from: p  reason: collision with root package name */
    public final q f3390p;
    public final i q;
    public final P r;
    public final f s;
    public final C0822l t;
    public final h u;
    public final i v;
    public final i w;

    /* renamed from: x  reason: collision with root package name */
    public final h f3391x;
    public final x y;
    public final Re.h z;

    /* JADX WARNING: type inference failed for: r5v10, types: [Gf.h, Gf.i] */
    /* JADX WARNING: type inference failed for: r5v13, types: [Gf.h, Gf.i] */
    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public k(Df.n r15, lf.C1157j r16, nf.C1209f r17, nf.C1204a r18, Qe.Q r19) {
        /*
            r14 = this;
            r1 = r16
            r5 = r17
            r0 = r19
            java.lang.String r2 = "outerContext"
            kotlin.jvm.internal.j.e(r15, r2)
            java.lang.String r2 = "classProto"
            kotlin.jvm.internal.j.e(r1, r2)
            java.lang.String r2 = "nameResolver"
            kotlin.jvm.internal.j.e(r5, r2)
            java.lang.String r2 = "sourceElement"
            kotlin.jvm.internal.j.e(r0, r2)
            java.lang.Object r2 = r15.f3358a
            Df.l r2 = (Df.l) r2
            Gf.m r2 = r2.f3349a
            int r3 = r1.f4850h
            qf.b r3 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.A(r5, r3)
            qf.g r3 = r3.f()
            r14.<init>(r2, r3)
            r14.f3386h = r1
            r8 = r18
            r14.f3387i = r8
            r14.f3388j = r0
            int r2 = r1.f4850h
            qf.b r2 = com.samsung.context.sdk.samsunganalytics.internal.sender.c.A(r5, r2)
            r14.k = r2
            nf.c r2 = nf.C1208e.e
            int r3 = r1.g
            java.lang.Object r2 = r2.c(r3)
            lf.A r2 = (lf.C1147A) r2
            Qe.A r2 = Df.m.e(r2)
            r14.l = r2
            nf.c r2 = nf.C1208e.d
            int r3 = r1.g
            java.lang.Object r2 = r2.c(r3)
            lf.f0 r2 = (lf.f0) r2
            Qe.p r2 = o1.C0246a.T(r2)
            r14.m = r2
            nf.c r2 = nf.C1208e.f
            int r3 = r1.g
            java.lang.Object r2 = r2.c(r3)
            lf.i r2 = (lf.C1156i) r2
            if (r2 != 0) goto L_0x006b
            r2 = -1
            goto L_0x0073
        L_0x006b:
            int[] r3 = Df.A.b
            int r2 = r2.ordinal()
            r2 = r3[r2]
        L_0x0073:
            switch(r2) {
                case 1: goto L_0x0089;
                case 2: goto L_0x0086;
                case 3: goto L_0x0083;
                case 4: goto L_0x0080;
                case 5: goto L_0x007d;
                case 6: goto L_0x007a;
                case 7: goto L_0x007a;
                default: goto L_0x0076;
            }
        L_0x0076:
            Qe.g r2 = Qe.C0817g.CLASS
        L_0x0078:
            r9 = r2
            goto L_0x008c
        L_0x007a:
            Qe.g r2 = Qe.C0817g.OBJECT
            goto L_0x0078
        L_0x007d:
            Qe.g r2 = Qe.C0817g.ANNOTATION_CLASS
            goto L_0x0078
        L_0x0080:
            Qe.g r2 = Qe.C0817g.ENUM_ENTRY
            goto L_0x0078
        L_0x0083:
            Qe.g r2 = Qe.C0817g.ENUM_CLASS
            goto L_0x0078
        L_0x0086:
            Qe.g r2 = Qe.C0817g.INTERFACE
            goto L_0x0078
        L_0x0089:
            Qe.g r2 = Qe.C0817g.CLASS
            goto L_0x0078
        L_0x008c:
            r14.n = r9
            java.util.List r4 = r1.f4852j
            java.lang.String r2 = "getTypeParameterList(...)"
            kotlin.jvm.internal.j.d(r4, r2)
            B1.b r6 = new B1.b
            lf.X r2 = r1.f4846H
            java.lang.String r3 = "getTypeTable(...)"
            kotlin.jvm.internal.j.d(r2, r3)
            r6.<init>((lf.X) r2)
            nf.h r2 = nf.C1211h.b
            lf.e0 r2 = r1.f4848J
            java.lang.String r3 = "getVersionRequirementTable(...)"
            kotlin.jvm.internal.j.d(r2, r3)
            nf.h r7 = He.F.x(r2)
            r3 = r14
            r2 = r15
            Df.n r4 = r2.a(r3, r4, r5, r6, r7, r8)
            java.lang.Object r3 = r4.f3358a
            Df.l r3 = (Df.l) r3
            r14.f3389o = r4
            nf.b r5 = nf.C1208e.m
            int r7 = r1.g
            java.lang.Boolean r5 = r5.c(r7)
            boolean r5 = r5.booleanValue()
            Qe.g r7 = Qe.C0817g.ENUM_CLASS
            r8 = 0
            r10 = 1
            if (r9 != r7) goto L_0x00e8
            if (r5 != 0) goto L_0x00df
            Df.o r5 = r3.s
            java.lang.Boolean r5 = r5.d()
            java.lang.Boolean r11 = java.lang.Boolean.TRUE
            boolean r5 = kotlin.jvm.internal.j.a(r5, r11)
            if (r5 == 0) goto L_0x00dd
            goto L_0x00df
        L_0x00dd:
            r5 = r8
            goto L_0x00e0
        L_0x00df:
            r5 = r10
        L_0x00e0:
            Af.t r11 = new Af.t
            Gf.m r12 = r3.f3349a
            r11.<init>(r12, r14, r5)
            goto L_0x00ea
        L_0x00e8:
            Af.o r11 = Af.o.b
        L_0x00ea:
            r14.f3390p = r11
            Ff.i r5 = new Ff.i
            r5.<init>((Ff.k) r14)
            r14.q = r5
            Qe.S r5 = Qe.P.d
            Gf.m r11 = r3.f3349a
            Gf.m r12 = r3.f3349a
            If.k r3 = r3.q
            If.l r3 = (If.l) r3
            r3.getClass()
            Ef.b r3 = new Ef.b
            r13 = 3
            r3.<init>(r10, r14, r13)
            r5.getClass()
            java.lang.String r5 = "storageManager"
            kotlin.jvm.internal.j.e(r11, r5)
            Qe.P r5 = new Qe.P
            r5.<init>(r14, r11, r3)
            r14.r = r5
            r3 = 0
            if (r9 != r7) goto L_0x011e
            D0.f r5 = new D0.f
            r5.<init>((Ff.k) r14)
            goto L_0x011f
        L_0x011e:
            r5 = r3
        L_0x011f:
            r14.s = r5
            java.lang.Object r15 = r15.f3359c
            Qe.l r15 = (Qe.C0822l) r15
            r14.t = r15
            Ff.d r2 = new Ff.d
            r2.<init>(r14, r8)
            r12.getClass()
            Gf.h r5 = new Gf.h
            r5.<init>(r12, r2)
            r14.u = r5
            Ff.d r2 = new Ff.d
            r2.<init>(r14, r10)
            Gf.i r5 = new Gf.i
            r5.<init>(r12, r2)
            r14.v = r5
            Ff.d r2 = new Ff.d
            r5 = 2
            r2.<init>(r14, r5)
            Gf.h r5 = new Gf.h
            r5.<init>(r12, r2)
            Ff.d r2 = new Ff.d
            r2.<init>(r14, r13)
            Gf.i r5 = new Gf.i
            r5.<init>(r12, r2)
            r14.w = r5
            Ff.d r2 = new Ff.d
            r5 = 4
            r2.<init>(r14, r5)
            Gf.h r5 = new Gf.h
            r5.<init>(r12, r2)
            r14.f3391x = r5
            Df.x r0 = new Df.x
            java.lang.Object r2 = r4.b
            nf.f r2 = (nf.C1209f) r2
            java.lang.Object r4 = r4.d
            B1.b r4 = (B1.b) r4
            boolean r5 = r15 instanceof Ff.k
            if (r5 == 0) goto L_0x0177
            Ff.k r15 = (Ff.k) r15
            goto L_0x0178
        L_0x0177:
            r15 = r3
        L_0x0178:
            if (r15 == 0) goto L_0x017c
            Df.x r3 = r15.y
        L_0x017c:
            r5 = r3
            r3 = r4
            r4 = r19
            r0.<init>(r1, r2, r3, r4, r5)
            r14.y = r0
            nf.b r15 = nf.C1208e.f4966c
            int r0 = r1.g
            java.lang.Boolean r15 = r15.c(r0)
            boolean r15 = r15.booleanValue()
            if (r15 != 0) goto L_0x0196
            Re.f r15 = Re.g.f3692a
            goto L_0x01a1
        L_0x0196:
            Ff.y r15 = new Ff.y
            Ff.d r0 = new Ff.d
            r1 = 5
            r0.<init>(r14, r1)
            r15.<init>(r12, r0)
        L_0x01a1:
            r14.z = r15
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Ff.k.<init>(Df.n, lf.j, nf.f, nf.a, Qe.Q):void");
    }

    public final p K(If.f fVar) {
        P p6 = this.r;
        C1353d.j(p6.f3660a);
        return (p) D1.f.y(p6.f3661c, P.e[0]);
    }

    public final W N() {
        return (W) this.f3391x.invoke();
    }

    public final boolean Q() {
        return false;
    }

    public final List R() {
        n nVar = this.f3389o;
        b bVar = (b) nVar.d;
        C1157j jVar = this.f3386h;
        j.e(jVar, "<this>");
        ArrayList arrayList = jVar.f4854p;
        if (arrayList.isEmpty()) {
            arrayList = null;
        }
        if (arrayList == null) {
            List list = jVar.q;
            j.d(list, "getContextReceiverTypeIdList(...)");
            Iterable<Integer> iterable = list;
            ArrayList arrayList2 = new ArrayList(C1196n.w0(iterable, 10));
            for (Integer num : iterable) {
                j.b(num);
                arrayList2.add(bVar.g(num.intValue()));
            }
            arrayList = arrayList2;
        }
        Iterable<lf.Q> iterable2 = arrayList;
        ArrayList arrayList3 = new ArrayList(C1196n.w0(iterable2, 10));
        for (lf.Q g : iterable2) {
            arrayList3.add(new u(v0(), new Bf.b((C0816f) this, ((H) nVar.f3360h).g(g), (C1240g) null), g.f3692a));
        }
        return arrayList3;
    }

    public final boolean T() {
        if (C1208e.f.c(this.f3386h.g) == C1156i.COMPANION_OBJECT) {
            return true;
        }
        return false;
    }

    public final boolean X() {
        return C1208e.l.c(this.f3386h.g).booleanValue();
    }

    public final C0817g b() {
        return this.n;
    }

    public final boolean b0() {
        return C1208e.f4969j.c(this.f3386h.g).booleanValue();
    }

    public final p c0() {
        return this.f3390p;
    }

    public final Collection d() {
        return (Collection) this.v.invoke();
    }

    public final Collection e() {
        return (Collection) this.w.invoke();
    }

    public final h e0() {
        ((l) ((Df.l) this.f3389o.f3358a).q).getClass();
        P p6 = this.r;
        C1353d.j(p6.f3660a);
        return (h) ((p) D1.f.y(p6.f3661c, P.e[0]));
    }

    public final C0822l g() {
        return this.t;
    }

    public final Re.h getAnnotations() {
        return this.z;
    }

    public final Q getSource() {
        return this.f3388j;
    }

    public final C0826p getVisibility() {
        return this.m;
    }

    public final boolean isExternal() {
        return C1208e.f4968i.c(this.f3386h.g).booleanValue();
    }

    public final boolean isInline() {
        if (!C1208e.k.c(this.f3386h.g).booleanValue()) {
            return false;
        }
        C1204a aVar = this.f3387i;
        int i2 = aVar.b;
        if (i2 >= 1) {
            if (i2 > 1) {
                return false;
            }
            int i7 = aVar.f4952c;
            if (i7 >= 4 && (i7 > 4 || aVar.d > 1)) {
                return false;
            }
        }
        return true;
    }

    public final List j() {
        return ((H) this.f3389o.f3360h).b();
    }

    public final A k() {
        return this.l;
    }

    public final boolean l() {
        if (!C1208e.k.c(this.f3386h.g).booleanValue() || !this.f3387i.a(1, 4, 2)) {
            return false;
        }
        return true;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v0, resolved type: Qe.O} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: Qe.O} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: Qe.O} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v4, resolved type: Qe.O} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: Qe.O} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v5, resolved type: Qe.O} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: Qe.O} */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0028, code lost:
        r1 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002d, code lost:
        if (r0 == false) goto L_0x0028;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final Hf.B p0(qf.C1240g r5) {
        /*
            r4 = this;
            Ff.h r4 = r4.e0()
            Ye.c r0 = Ye.c.FROM_DESERIALIZATION
            java.util.Collection r4 = r4.f(r5, r0)
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.util.Iterator r4 = r4.iterator()
            r5 = 0
            r0 = 0
            r1 = r5
        L_0x0013:
            boolean r2 = r4.hasNext()
            if (r2 == 0) goto L_0x002d
            java.lang.Object r2 = r4.next()
            r3 = r2
            Qe.O r3 = (Qe.O) r3
            Te.u r3 = r3.H()
            if (r3 != 0) goto L_0x0013
            if (r0 == 0) goto L_0x002a
        L_0x0028:
            r1 = r5
            goto L_0x0030
        L_0x002a:
            r0 = 1
            r1 = r2
            goto L_0x0013
        L_0x002d:
            if (r0 != 0) goto L_0x0030
            goto L_0x0028
        L_0x0030:
            Qe.O r1 = (Qe.O) r1
            if (r1 == 0) goto L_0x0038
            Hf.x r5 = r1.getType()
        L_0x0038:
            Hf.B r5 = (Hf.B) r5
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: Ff.k.p0(qf.g):Hf.B");
    }

    public final M q() {
        return this.q;
    }

    public final boolean s() {
        return C1208e.g.c(this.f3386h.g).booleanValue();
    }

    public final boolean t0() {
        return C1208e.f4967h.c(this.f3386h.g).booleanValue();
    }

    public final String toString() {
        String str;
        StringBuilder sb2 = new StringBuilder("deserialized ");
        if (b0()) {
            str = "expect ";
        } else {
            str = "";
        }
        sb2.append(str);
        sb2.append("class ");
        sb2.append(getName());
        return sb2.toString();
    }

    public final C0848i y() {
        return (C0848i) this.u.invoke();
    }
}
