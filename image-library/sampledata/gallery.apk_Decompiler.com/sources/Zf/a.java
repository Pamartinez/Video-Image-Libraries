package Zf;

import Ae.c;
import L1.d;
import Vf.A;
import Vf.C0867e0;
import Vf.C0880q;
import Vf.C0883u;
import Vf.D;
import Vf.m0;
import Vf.n0;
import Xf.r;
import Yf.h;
import com.samsung.android.vexfwk.coroutines.VexFwkBaseScope;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperBase;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperBaseFuture;
import com.samsung.android.vexfwk.sdk.common.VexFwkHelperConfigurationFuture;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionPartialResult;
import com.samsung.android.vexfwk.sdk.common.runtime.VexFwkSessionTotalResult;
import com.samsung.android.vexfwk.session.VexFwkUsecase;
import java.util.Iterator;
import java.util.Map;
import me.k;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class a extends i implements c {
    public final /* synthetic */ int d;
    public int e;
    public Object f;
    public final /* synthetic */ Object g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(c cVar, AutoCloseable autoCloseable, C1227c cVar2, int i2) {
        super(2, cVar2);
        this.d = i2;
        this.f = cVar;
        this.g = autoCloseable;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        switch (this.d) {
            case 0:
                a aVar = new a((b) this.g, cVar, 0);
                aVar.f = obj;
                return aVar;
            case 1:
                a aVar2 = new a((c) this.g, cVar, 1);
                aVar2.f = obj;
                return aVar2;
            case 2:
                a aVar3 = new a((h) this.g, cVar, 2);
                aVar3.f = obj;
                return aVar3;
            case 3:
                a aVar4 = new a((VexFwkHelperBase) this.g, cVar, 3);
                aVar4.f = obj;
                return aVar4;
            case 4:
                a aVar5 = new a((VexFwkHelperBaseFuture) this.g, cVar, 4);
                aVar5.f = obj;
                return aVar5;
            case 5:
                return new a((VexFwkHelperConfigurationFuture) this.g, cVar, 5);
            case 6:
                a aVar6 = new a((VexFwkHelperConfigurationFuture) this.g, cVar, 6);
                aVar6.f = obj;
                return aVar6;
            case 7:
                return new a((c) this.f, (VexFwkSessionTotalResult) this.g, cVar, 7);
            case 8:
                return new a((c) this.f, (VexFwkSessionPartialResult) this.g, cVar, 8);
            default:
                return new a((VexFwkBaseScope) this.g, cVar, 9);
        }
    }

    public final Object invoke(Object obj, Object obj2) {
        switch (this.d) {
            case 0:
                return ((a) create((r) obj, (C1227c) obj2)).invokeSuspend(x.f4917a);
            case 1:
                return ((a) create((h) obj, (C1227c) obj2)).invokeSuspend(x.f4917a);
            case 2:
                return ((a) create(obj, (C1227c) obj2)).invokeSuspend(x.f4917a);
            case 3:
                return ((a) create((VexFwkUsecase) obj, (C1227c) obj2)).invokeSuspend(x.f4917a);
            case 4:
                return ((a) create((A) obj, (C1227c) obj2)).invokeSuspend(x.f4917a);
            case 5:
                return ((a) create((A) obj, (C1227c) obj2)).invokeSuspend(x.f4917a);
            case 6:
                return ((a) create((A) obj, (C1227c) obj2)).invokeSuspend(x.f4917a);
            case 7:
                return ((a) create((A) obj, (C1227c) obj2)).invokeSuspend(x.f4917a);
            case 8:
                return ((a) create((A) obj, (C1227c) obj2)).invokeSuspend(x.f4917a);
            default:
                return ((a) create((A) obj, (C1227c) obj2)).invokeSuspend(x.f4917a);
        }
    }

    /* JADX WARNING: type inference failed for: r6v56, types: [Sf.l, java.lang.Object, qe.c] */
    public final Object invokeSuspend(Object obj) {
        VexFwkHelperBaseFuture vexFwkHelperBaseFuture;
        C0880q qVar;
        Object obj2;
        Iterator it;
        switch (this.d) {
            case 0:
                Object obj3 = C1245a.COROUTINE_SUSPENDED;
                int i2 = this.e;
                if (i2 == 0) {
                    L2.a.A(obj);
                    b bVar = (b) this.g;
                    this.e = 1;
                    if (bVar.a((r) this.f, this) == obj3) {
                        return obj3;
                    }
                } else if (i2 == 1) {
                    L2.a.A(obj);
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                return x.f4917a;
            case 1:
                Object obj4 = C1245a.COROUTINE_SUSPENDED;
                int i7 = this.e;
                Object obj5 = x.f4917a;
                if (i7 == 0) {
                    L2.a.A(obj);
                    this.e = 1;
                    Object collect = ((c) this.g).d.collect((h) this.f, this);
                    if (collect != obj4) {
                        collect = obj5;
                    }
                    if (collect == obj4) {
                        return obj4;
                    }
                } else if (i7 == 1) {
                    L2.a.A(obj);
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                return obj5;
            case 2:
                Object obj6 = C1245a.COROUTINE_SUSPENDED;
                int i8 = this.e;
                if (i8 == 0) {
                    L2.a.A(obj);
                    Object obj7 = this.f;
                    this.e = 1;
                    if (((h) this.g).emit(obj7, this) == obj6) {
                        return obj6;
                    }
                } else if (i8 == 1) {
                    L2.a.A(obj);
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                return x.f4917a;
            case 3:
                C1245a aVar = C1245a.COROUTINE_SUSPENDED;
                int i10 = this.e;
                if (i10 == 0) {
                    L2.a.A(obj);
                    VexFwkHelperBase vexFwkHelperBase = (VexFwkHelperBase) this.g;
                    this.e = 1;
                    Object session = vexFwkHelperBase.getSession((VexFwkUsecase) this.f, this);
                    if (session == aVar) {
                        return aVar;
                    }
                    return session;
                } else if (i10 == 1) {
                    L2.a.A(obj);
                    return obj;
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            case 4:
                Object obj8 = C1245a.COROUTINE_SUSPENDED;
                int i11 = this.e;
                if (i11 == 0) {
                    L2.a.A(obj);
                    D.f(((A) this.f).getCoroutineContext());
                    VexFwkHelperBaseFuture vexFwkHelperBaseFuture2 = (VexFwkHelperBaseFuture) this.g;
                    this.f = vexFwkHelperBaseFuture2;
                    this.e = 1;
                    Object execute = vexFwkHelperBaseFuture2.execute(this);
                    if (execute == obj8) {
                        return obj8;
                    }
                    VexFwkHelperBaseFuture vexFwkHelperBaseFuture3 = vexFwkHelperBaseFuture2;
                    obj = execute;
                    vexFwkHelperBaseFuture = vexFwkHelperBaseFuture3;
                } else if (i11 == 1) {
                    vexFwkHelperBaseFuture = (VexFwkHelperBaseFuture) this.f;
                    L2.a.A(obj);
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                return Boolean.valueOf(vexFwkHelperBaseFuture.set(obj));
            case 5:
                VexFwkHelperConfigurationFuture vexFwkHelperConfigurationFuture = (VexFwkHelperConfigurationFuture) this.g;
                Object obj9 = C1245a.COROUTINE_SUSPENDED;
                int i12 = this.e;
                if (i12 == 0) {
                    L2.a.A(obj);
                    C0880q access$getConfigMap$p = vexFwkHelperConfigurationFuture.configMap;
                    this.f = access$getConfigMap$p;
                    this.e = 1;
                    Object access$buildConfigMap = vexFwkHelperConfigurationFuture.buildConfigMap(this);
                    if (access$buildConfigMap == obj9) {
                        return obj9;
                    }
                    C0880q qVar2 = access$getConfigMap$p;
                    obj = access$buildConfigMap;
                    qVar = qVar2;
                } else if (i12 == 1) {
                    qVar = (C0880q) this.f;
                    L2.a.A(obj);
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                return Boolean.valueOf(((Vf.r) qVar).H(obj));
            case 6:
                Object obj10 = C1245a.COROUTINE_SUSPENDED;
                int i13 = this.e;
                if (i13 == 0) {
                    L2.a.A(obj);
                    A a7 = (A) this.f;
                    VexFwkHelperConfigurationFuture vexFwkHelperConfigurationFuture2 = (VexFwkHelperConfigurationFuture) this.g;
                    this.e = 1;
                    obj = vexFwkHelperConfigurationFuture2.awaitConfigMap(this);
                    if (obj == obj10) {
                        return obj10;
                    }
                } else if (i13 == 1) {
                    try {
                        L2.a.A(obj);
                    } catch (Throwable th) {
                        obj2 = L2.a.l(th);
                    }
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                obj2 = (Map) obj;
                if (k.a(obj2) == null) {
                    for (com.samsung.android.vexfwk.sdk.common.h hVar : ((Map) obj2).values()) {
                        Vf.r rVar = hVar.f;
                        IllegalStateException illegalStateException = new IllegalStateException("Session creation failed");
                        rVar.getClass();
                        rVar.H(new C0883u(illegalStateException, false));
                    }
                }
                return x.f4917a;
            case 7:
                Object obj11 = C1245a.COROUTINE_SUSPENDED;
                int i14 = this.e;
                if (i14 == 0) {
                    L2.a.A(obj);
                    this.e = 1;
                    if (((c) this.f).invoke((VexFwkSessionTotalResult) this.g, this) == obj11) {
                        return obj11;
                    }
                } else if (i14 == 1) {
                    L2.a.A(obj);
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                return x.f4917a;
            case 8:
                Object obj12 = C1245a.COROUTINE_SUSPENDED;
                int i15 = this.e;
                if (i15 == 0) {
                    L2.a.A(obj);
                    this.e = 1;
                    if (((c) this.f).invoke((VexFwkSessionPartialResult) this.g, this) == obj12) {
                        return obj12;
                    }
                } else if (i15 == 1) {
                    L2.a.A(obj);
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                return x.f4917a;
            default:
                Object obj13 = C1245a.COROUTINE_SUSPENDED;
                int i16 = this.e;
                if (i16 == 0) {
                    L2.a.A(obj);
                    n0 n0Var = (n0) ((VexFwkBaseScope) this.g).rootJob;
                    n0Var.getClass();
                    m0 m0Var = new m0(n0Var, (C1227c) null);
                    ? obj14 = new Object();
                    obj14.g = d.f(m0Var, obj14, obj14);
                    it = obj14;
                } else if (i16 == 1) {
                    it = (Iterator) this.f;
                    L2.a.A(obj);
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                while (it.hasNext()) {
                    this.f = it;
                    this.e = 1;
                    if (((n0) ((C0867e0) it.next())).G(this) == obj13) {
                        return obj13;
                    }
                }
                return x.f4917a;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ a(Object obj, C1227c cVar, int i2) {
        super(2, cVar);
        this.d = i2;
        this.g = obj;
    }
}
