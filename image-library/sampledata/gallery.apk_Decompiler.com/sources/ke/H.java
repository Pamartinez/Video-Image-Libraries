package Ke;

import Df.E;
import He.C0751g;
import He.t;
import L1.d;
import L2.a;
import Le.g;
import Qe.C0831v;
import Te.C0852m;
import kotlin.jvm.internal.b;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;
import me.h;
import o1.C0246a;
import sf.C1283j;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class H extends C0800s implements f, C0751g, C0785e {

    /* renamed from: p  reason: collision with root package name */
    public static final /* synthetic */ t[] f3491p;

    /* renamed from: j  reason: collision with root package name */
    public final F f3492j;
    public final String k;
    public final Object l;
    public final x0 m;
    public final Object n;

    /* renamed from: o  reason: collision with root package name */
    public final Object f3493o;

    static {
        w wVar = v.f4727a;
        f3491p = new t[]{wVar.f(new p(wVar.b(H.class), "descriptor", "getDescriptor()Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;"))};
    }

    public H(F f, String str, String str2, C0831v vVar, Object obj) {
        this.f3492j = f;
        this.k = str2;
        this.l = obj;
        this.m = C0246a.d0(vVar, new E(4, (Object) this, (Object) str));
        h hVar = h.PUBLICATION;
        this.n = d.p(hVar, new G(this, 0));
        this.f3493o = d.p(hVar, new G(this, 1));
    }

    public final boolean equals(Object obj) {
        H b = E0.b(obj);
        if (b != null && j.a(this.f3492j, b.f3492j) && getName().equals(b.getName()) && j.a(this.k, b.k) && j.a(this.l, b.l)) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [me.f, java.lang.Object] */
    public final g g() {
        return (g) this.n.getValue();
    }

    public final int getArity() {
        return a.o(g());
    }

    public final String getName() {
        String b = ((C0852m) j()).getName().b();
        j.d(b, "asString(...)");
        return b;
    }

    public final F h() {
        return this.f3492j;
    }

    public final int hashCode() {
        int hashCode = getName().hashCode();
        return this.k.hashCode() + ((hashCode + (this.f3492j.hashCode() * 31)) * 31);
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [me.f, java.lang.Object] */
    public final g i() {
        return (g) this.f3493o.getValue();
    }

    public final Object invoke() {
        return call(new Object[0]);
    }

    public final boolean isExternal() {
        return j().isExternal();
    }

    public final boolean isInfix() {
        return j().isInfix();
    }

    public final boolean isInline() {
        return j().isInline();
    }

    public final boolean isOperator() {
        return j().isOperator();
    }

    public final boolean isSuspend() {
        return j().isSuspend();
    }

    public final boolean q() {
        if (this.l != b.NO_RECEIVER) {
            return true;
        }
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: java.lang.reflect.Type[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v13, resolved type: java.lang.reflect.Type[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v14, resolved type: java.lang.reflect.Type[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final Le.w r(java.lang.reflect.Constructor r11, Qe.C0831v r12, boolean r13) {
        /*
            r10 = this;
            java.lang.Object r3 = r10.l
            java.lang.String r4 = "getGenericParameterTypes(...)"
            java.lang.String r5 = "getDeclaringClass(...)"
            r6 = 0
            if (r13 != 0) goto L_0x00b9
            boolean r7 = r12 instanceof Te.C0848i
            if (r7 == 0) goto L_0x0011
            r0 = r12
            Te.i r0 = (Te.C0848i) r0
            goto L_0x0012
        L_0x0011:
            r0 = r6
        L_0x0012:
            if (r0 != 0) goto L_0x0016
            goto L_0x00b9
        L_0x0016:
            r7 = r0
            Te.t r7 = (Te.t) r7
            Qe.p r8 = r7.getVisibility()
            boolean r8 = Qe.C0827q.e(r8)
            if (r8 == 0) goto L_0x0025
            goto L_0x00b9
        L_0x0025:
            Qe.f r8 = r0.W()
            java.lang.String r9 = "getConstructedClass(...)"
            kotlin.jvm.internal.j.d(r8, r9)
            boolean r8 = tf.C1305i.f(r8)
            if (r8 == 0) goto L_0x0036
            goto L_0x00b9
        L_0x0036:
            Qe.f r0 = r0.W()
            boolean r0 = tf.C1301e.q(r0)
            if (r0 == 0) goto L_0x0042
            goto L_0x00b9
        L_0x0042:
            java.util.List r0 = r7.B()
            java.lang.String r7 = "getValueParameters(...)"
            kotlin.jvm.internal.j.d(r0, r7)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            boolean r7 = r0 instanceof java.util.Collection
            if (r7 == 0) goto L_0x005b
            r7 = r0
            java.util.Collection r7 = (java.util.Collection) r7
            boolean r7 = r7.isEmpty()
            if (r7 == 0) goto L_0x005b
            goto L_0x00b9
        L_0x005b:
            java.util.Iterator r0 = r0.iterator()
        L_0x005f:
            boolean r7 = r0.hasNext()
            if (r7 == 0) goto L_0x00b9
            java.lang.Object r7 = r0.next()
            Te.Q r7 = (Te.Q) r7
            Te.S r7 = (Te.S) r7
            Hf.x r7 = r7.getType()
            java.lang.String r8 = "getType(...)"
            kotlin.jvm.internal.j.d(r7, r8)
            boolean r7 = L1.d.u(r7)
            if (r7 == 0) goto L_0x005f
            boolean r0 = r10.q()
            if (r0 == 0) goto L_0x0091
            Le.h r0 = new Le.h
            Qe.v r2 = r10.j()
            java.lang.Object r2 = a.C0068a.k(r3, r2)
            r3 = 0
            r0.<init>(r11, r2, r3)
            return r0
        L_0x0091:
            Le.i r0 = new Le.i
            java.lang.Class r2 = r11.getDeclaringClass()
            kotlin.jvm.internal.j.d(r2, r5)
            java.lang.reflect.Type[] r3 = r11.getGenericParameterTypes()
            kotlin.jvm.internal.j.d(r3, r4)
            int r4 = r3.length
            r5 = 0
            r7 = 1
            if (r4 > r7) goto L_0x00a9
            java.lang.reflect.Type[] r3 = new java.lang.reflect.Type[r5]
            goto L_0x00af
        L_0x00a9:
            int r4 = r3.length
            int r4 = r4 - r7
            java.lang.Object[] r3 = ne.C1192j.i0(r3, r5, r4)
        L_0x00af:
            r4 = r3
            java.lang.reflect.Type[] r4 = (java.lang.reflect.Type[]) r4
            r5 = 0
            r1 = r11
            r3 = r6
            r0.<init>(r1, r2, r3, r4, r5)
            return r0
        L_0x00b9:
            boolean r0 = r10.q()
            if (r0 == 0) goto L_0x00ce
            Le.h r0 = new Le.h
            Qe.v r2 = r10.j()
            java.lang.Object r2 = a.C0068a.k(r3, r2)
            r3 = 1
            r0.<init>(r11, r2, r3)
            return r0
        L_0x00ce:
            Le.i r0 = new Le.i
            java.lang.Class r2 = r11.getDeclaringClass()
            kotlin.jvm.internal.j.d(r2, r5)
            java.lang.Class r3 = r11.getDeclaringClass()
            java.lang.Class r5 = r3.getDeclaringClass()
            if (r5 == 0) goto L_0x00ed
            int r3 = r3.getModifiers()
            boolean r3 = java.lang.reflect.Modifier.isStatic(r3)
            if (r3 != 0) goto L_0x00ed
            r3 = r5
            goto L_0x00ee
        L_0x00ed:
            r3 = r6
        L_0x00ee:
            java.lang.reflect.Type[] r5 = r11.getGenericParameterTypes()
            kotlin.jvm.internal.j.d(r5, r4)
            r4 = r5
            r5 = 1
            r1 = r11
            r0.<init>(r1, r2, r3, r4, r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: Ke.H.r(java.lang.reflect.Constructor, Qe.v, boolean):Le.w");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0034, code lost:
        if (r1.isInterface() == true) goto L_0x003f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final Le.q s(java.lang.reflect.Method r6) {
        /*
            r5 = this;
            boolean r0 = r5.q()
            if (r0 == 0) goto L_0x0043
            Le.t r0 = new Le.t
            Qe.v r1 = r5.j()
            Te.u r1 = r1.E()
            java.lang.Object r2 = r5.l
            if (r1 == 0) goto L_0x0037
            Hf.x r1 = r1.getType()
            boolean r1 = tf.C1305i.c(r1)
            r3 = 1
            if (r1 != r3) goto L_0x0037
            java.lang.Class[] r1 = r6.getParameterTypes()
            java.lang.String r4 = "getParameterTypes(...)"
            kotlin.jvm.internal.j.d(r1, r4)
            java.lang.Object r1 = ne.C1192j.n0(r1)
            java.lang.Class r1 = (java.lang.Class) r1
            if (r1 == 0) goto L_0x0037
            boolean r1 = r1.isInterface()
            if (r1 != r3) goto L_0x0037
            goto L_0x003f
        L_0x0037:
            Qe.v r5 = r5.j()
            java.lang.Object r2 = a.C0068a.k(r2, r5)
        L_0x003f:
            r0.<init>(r6, r2)
            return r0
        L_0x0043:
            Le.v r5 = new Le.v
            r0 = 6
            r1 = 2
            r2 = 0
            r5.<init>(r6, r2, r0, r1)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: Ke.H.s(java.lang.reflect.Method):Le.q");
    }

    /* renamed from: t */
    public final C0831v j() {
        t tVar = f3491p[0];
        Object invoke = this.m.invoke();
        j.d(invoke, "getValue(...)");
        return (C0831v) invoke;
    }

    public final String toString() {
        C1283j jVar = B0.f3485a;
        return B0.b(j());
    }

    public final Object invoke(Object obj) {
        return call(obj);
    }

    public final Object invoke(Object obj, Object obj2) {
        return call(obj, obj2);
    }

    public final Object invoke(Object obj, Object obj2, Object obj3) {
        return call(obj, obj2, obj3);
    }

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        return call(obj, obj2, obj3, obj4);
    }

    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        return call(obj, obj2, obj3, obj4, obj5, obj6);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public H(Ke.F r8, Qe.C0831v r9) {
        /*
            r7 = this;
            java.lang.String r0 = "descriptor"
            kotlin.jvm.internal.j.e(r9, r0)
            r0 = r9
            Te.m r0 = (Te.C0852m) r0
            qf.g r0 = r0.getName()
            java.lang.String r3 = r0.b()
            java.lang.String r0 = "asString(...)"
            kotlin.jvm.internal.j.d(r3, r0)
            L2.a r0 = Ke.C0.c(r9)
            java.lang.String r4 = r0.d()
            java.lang.Object r6 = kotlin.jvm.internal.b.NO_RECEIVER
            r1 = r7
            r2 = r8
            r5 = r9
            r1.<init>(r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Ke.H.<init>(Ke.F, Qe.v):void");
    }
}
