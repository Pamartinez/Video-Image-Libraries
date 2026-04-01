package Ke;

import Ae.a;
import Af.f;
import He.F;
import He.t;
import Qe.C0816f;
import Qe.C0822l;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.jvm.internal.j;
import ne.C1194l;
import tf.C1301e;

/* renamed from: Ke.u  reason: case insensitive filesystem */
public final class C0802u implements a {
    public final /* synthetic */ int d;
    public final C0805x e;

    public /* synthetic */ C0802u(C0805x xVar, int i2) {
        this.d = i2;
        this.e = xVar;
    }

    public final Object invoke() {
        C0816f fVar;
        Class cls;
        B b;
        B b5;
        switch (this.d) {
            case 0:
                C0805x xVar = this.e;
                x0 x0Var = xVar.f3517i;
                t[] tVarArr = C0805x.f3514o;
                t tVar = tVarArr[9];
                Object invoke = x0Var.invoke();
                j.d(invoke, "getValue(...)");
                x0 x0Var2 = xVar.k;
                t tVar2 = tVarArr[11];
                Object invoke2 = x0Var2.invoke();
                j.d(invoke2, "getValue(...)");
                return C1194l.X0((Collection) invoke2, (Collection) invoke);
            case 1:
                C0805x xVar2 = this.e;
                x0 x0Var3 = xVar2.f3518j;
                t[] tVarArr2 = C0805x.f3514o;
                t tVar3 = tVarArr2[10];
                Object invoke3 = x0Var3.invoke();
                j.d(invoke3, "getValue(...)");
                x0 x0Var4 = xVar2.l;
                t tVar4 = tVarArr2[12];
                Object invoke4 = x0Var4.invoke();
                j.d(invoke4, "getValue(...)");
                return C1194l.X0((Collection) invoke4, (Collection) invoke3);
            case 2:
                C0805x xVar3 = this.e;
                x0 x0Var5 = xVar3.f3517i;
                t[] tVarArr3 = C0805x.f3514o;
                t tVar5 = tVarArr3[9];
                Object invoke5 = x0Var5.invoke();
                j.d(invoke5, "getValue(...)");
                x0 x0Var6 = xVar3.f3518j;
                t tVar6 = tVarArr3[10];
                Object invoke6 = x0Var6.invoke();
                j.d(invoke6, "getValue(...)");
                return C1194l.X0((Collection) invoke6, (Collection) invoke5);
            case 3:
                C0805x xVar4 = this.e;
                x0 x0Var7 = xVar4.m;
                t[] tVarArr4 = C0805x.f3514o;
                t tVar7 = tVarArr4[13];
                Object invoke7 = x0Var7.invoke();
                j.d(invoke7, "getValue(...)");
                x0 x0Var8 = xVar4.n;
                t tVar8 = tVarArr4[14];
                Object invoke8 = x0Var8.invoke();
                j.d(invoke8, "getValue(...)");
                return C1194l.X0((Collection) invoke8, (Collection) invoke7);
            case 4:
                return E0.d(this.e.a());
            case 5:
                ArrayList arrayList = new ArrayList();
                for (Object next : F.F(this.e.a().M(), (f) null, 3)) {
                    if (!C1301e.m((C0822l) next)) {
                        arrayList.add(next);
                    }
                }
                ArrayList arrayList2 = new ArrayList();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    C0822l lVar = (C0822l) it.next();
                    if (lVar instanceof C0816f) {
                        fVar = (C0816f) lVar;
                    } else {
                        fVar = null;
                    }
                    if (fVar != null) {
                        cls = E0.k(fVar);
                    } else {
                        cls = null;
                    }
                    if (cls != null) {
                        b = new B(cls);
                    } else {
                        b = null;
                    }
                    if (b != null) {
                        arrayList2.add(b);
                    }
                }
                return arrayList2;
            default:
                Collection<C0816f> e7 = this.e.a().e();
                j.d(e7, "getSealedSubclasses(...)");
                ArrayList arrayList3 = new ArrayList();
                for (C0816f fVar2 : e7) {
                    j.c(fVar2, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                    Class k = E0.k(fVar2);
                    if (k != null) {
                        b5 = new B(k);
                    } else {
                        b5 = null;
                    }
                    if (b5 != null) {
                        arrayList3.add(b5);
                    }
                }
                return arrayList3;
        }
    }
}
