package Vf;

import Ae.c;
import Sf.m;
import me.x;
import qe.C1227c;
import se.h;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class m0 extends h implements c {
    public q0 d;
    public C0879p e;
    public int f;
    public /* synthetic */ Object g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ n0 f3868h;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public m0(n0 n0Var, C1227c cVar) {
        super(2, cVar);
        this.f3868h = n0Var;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        m0 m0Var = new m0(this.f3868h, cVar);
        m0Var.g = obj;
        return m0Var;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((m0) create((m) obj, (C1227c) obj2)).invokeSuspend(x.f4917a);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0087, code lost:
        if (re.C1245a.COROUTINE_SUSPENDED == r0) goto L_0x0089;
     */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            re.a r0 = re.C1245a.COROUTINE_SUSPENDED
            int r1 = r7.f
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x0026
            if (r1 == r4) goto L_0x0022
            if (r1 != r3) goto L_0x001a
            Vf.p r1 = r7.e
            Vf.q0 r4 = r7.d
            java.lang.Object r5 = r7.g
            Sf.m r5 = (Sf.m) r5
            L2.a.A(r8)
            goto L_0x008a
        L_0x001a:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0022:
            L2.a.A(r8)
            goto L_0x008f
        L_0x0026:
            L2.a.A(r8)
            java.lang.Object r8 = r7.g
            Sf.m r8 = (Sf.m) r8
            Vf.n0 r1 = r7.f3868h
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r5 = Vf.n0.d
            java.lang.Object r1 = r5.get(r1)
            boolean r5 = r1 instanceof Vf.C0879p
            if (r5 == 0) goto L_0x0048
            Vf.p r1 = (Vf.C0879p) r1
            Vf.n0 r1 = r1.f3871h
            r7.f = r4
            Sf.l r8 = (Sf.l) r8
            r8.e = r1
            r8.d = r2
            r8.g = r7
            goto L_0x0089
        L_0x0048:
            boolean r4 = r1 instanceof Vf.C0861b0
            if (r4 == 0) goto L_0x008f
            Vf.b0 r1 = (Vf.C0861b0) r1
            Vf.q0 r1 = r1.c()
            if (r1 == 0) goto L_0x008f
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r4 = cg.j.d
            java.lang.Object r4 = r4.get(r1)
            java.lang.String r5 = "null cannot be cast to non-null type kotlinx.coroutines.internal.LockFreeLinkedListNode"
            kotlin.jvm.internal.j.c(r4, r5)
            cg.j r4 = (cg.j) r4
            r5 = r4
            r4 = r1
            r1 = r5
            r5 = r8
        L_0x0065:
            boolean r8 = r1.equals(r4)
            if (r8 != 0) goto L_0x008f
            boolean r8 = r1 instanceof Vf.C0879p
            if (r8 == 0) goto L_0x008a
            r8 = r1
            Vf.p r8 = (Vf.C0879p) r8
            Vf.n0 r6 = r8.f3871h
            r7.g = r5
            r7.d = r4
            r7.e = r8
            r7.f = r3
            r8 = r5
            Sf.l r8 = (Sf.l) r8
            r8.e = r6
            r8.d = r2
            r8.g = r7
            re.a r8 = re.C1245a.COROUTINE_SUSPENDED
            if (r8 != r0) goto L_0x008a
        L_0x0089:
            return r0
        L_0x008a:
            cg.j r1 = r1.g()
            goto L_0x0065
        L_0x008f:
            me.x r7 = me.x.f4917a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: Vf.m0.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
