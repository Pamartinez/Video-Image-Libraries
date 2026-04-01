package Te;

import Gf.p;
import Hf.C0774x;
import Hf.X;
import Qe.A;
import Qe.C0813c;
import Qe.C0814d;
import Qe.C0816f;
import Qe.C0820j;
import Qe.C0822l;
import Qe.C0826p;
import Qe.C0831v;
import Qe.Q;
import Qe.U;
import Re.h;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;
import qf.C1240g;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class N extends t implements M {

    /* renamed from: K  reason: collision with root package name */
    public static final C f3763K = new Object();

    /* renamed from: H  reason: collision with root package name */
    public final p f3764H;

    /* renamed from: I  reason: collision with root package name */
    public final U f3765I;

    /* renamed from: J  reason: collision with root package name */
    public C0848i f3766J;

    /* JADX WARNING: type inference failed for: r0v1, types: [Te.C, java.lang.Object] */
    static {
        w wVar = v.f4727a;
        wVar.f(new kotlin.jvm.internal.p(wVar.b(N.class), "withDispatchReceiver", "getWithDispatchReceiver()Lorg/jetbrains/kotlin/descriptors/impl/TypeAliasConstructorDescriptor;"));
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public N(Gf.p r8, Qe.U r9, Te.C0848i r10, Te.M r11, Re.h r12, Qe.C0813c r13, Qe.Q r14) {
        /*
            r7 = this;
            qf.g r6 = qf.C1242i.e
            r0 = r7
            r2 = r9
            r3 = r11
            r5 = r12
            r1 = r13
            r4 = r14
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r0.f3764H = r8
            r0.f3765I = r2
            Df.E r7 = new Df.E
            r9 = 13
            r7.<init>((int) r9, (java.lang.Object) r0, (java.lang.Object) r10)
            Gf.m r8 = (Gf.m) r8
            r8.getClass()
            Gf.h r9 = new Gf.h
            r9.<init>(r8, r7)
            r0.f3766J = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Te.N.<init>(Gf.p, Qe.U, Te.i, Te.M, Re.h, Qe.c, Qe.Q):void");
    }

    public final t G0(C0813c cVar, C0822l lVar, C0831v vVar, Q q, h hVar, C1240g gVar) {
        j.e(lVar, "newOwner");
        j.e(cVar, "kind");
        j.e(hVar, "annotations");
        C0813c cVar2 = C0813c.DECLARATION;
        if (cVar != cVar2) {
            C0813c cVar3 = C0813c.SYNTHESIZED;
        }
        return new N(this.f3764H, this.f3765I, this.f3766J, this, hVar, cVar2, q);
    }

    /* renamed from: P0 */
    public final M a() {
        C0831v a7 = super.a();
        j.c(a7, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.impl.TypeAliasConstructorDescriptor");
        return (M) a7;
    }

    /* renamed from: Q0 */
    public final N c(X x9) {
        j.e(x9, "substitutor");
        C0831v c5 = super.c(x9);
        j.c(c5, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.impl.TypeAliasConstructorDescriptorImpl");
        N n = (N) c5;
        C0774x xVar = n.k;
        j.b(xVar);
        C0848i U02 = this.f3766J.a().c(X.d(xVar));
        if (U02 == null) {
            return null;
        }
        n.f3766J = U02;
        return n;
    }

    public final C0814d S(C0816f fVar, A a7, C0826p pVar, C0813c cVar) {
        j.e(fVar, "newOwner");
        j.e(pVar, "visibility");
        j.e(cVar, "kind");
        C0857s K02 = K0(X.b);
        K02.e = fVar;
        K02.f = a7;
        K02.g = pVar;
        K02.f3790i = cVar;
        K02.f3793p = false;
        t H02 = K02.f3788A.H0(K02);
        j.c(H02, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.impl.TypeAliasConstructorDescriptor");
        return (M) H02;
    }

    public final boolean V() {
        return this.f3766J.f3779H;
    }

    public final C0816f W() {
        C0816f W6 = this.f3766J.W();
        j.d(W6, "getConstructedClass(...)");
        return W6;
    }

    public final C0820j g() {
        return this.f3765I;
    }

    public final C0774x getReturnType() {
        C0774x xVar = this.k;
        j.b(xVar);
        return xVar;
    }

    /* renamed from: g  reason: collision with other method in class */
    public final C0822l m52g() {
        return this.f3765I;
    }
}
