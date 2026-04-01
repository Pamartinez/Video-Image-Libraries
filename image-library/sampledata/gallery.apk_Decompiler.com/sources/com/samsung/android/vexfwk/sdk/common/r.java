package com.samsung.android.vexfwk.sdk.common;

import Ae.c;
import He.F;
import Vf.A;
import Vf.C;
import Vf.D;
import Xf.q;
import Yf.h;
import Zf.b;
import a.C0068a;
import fg.a;
import me.x;
import qe.C1227c;
import qe.C1232h;
import re.C1245a;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class r extends i implements c {
    public final /* synthetic */ int d;
    public Object e;
    public Object f;
    public int g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ Object f4176h;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public r(h hVar, b bVar, C1227c cVar) {
        super(2, cVar);
        this.d = 2;
        this.f = hVar;
        this.f4176h = bVar;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        switch (this.d) {
            case 0:
                return new r((VexFwkHelperProcessFuture) this.f4176h, cVar, 0);
            case 1:
                return new r((VexFwkHelperProcessFuture) this.f4176h, cVar, 1);
            default:
                r rVar = new r((h) this.f, (b) this.f4176h, cVar);
                rVar.e = obj;
                return rVar;
        }
    }

    public final Object invoke(Object obj, Object obj2) {
        A a7 = (A) obj;
        C1227c cVar = (C1227c) obj2;
        switch (this.d) {
            case 0:
                return ((r) create(a7, cVar)).invokeSuspend(x.f4917a);
            case 1:
                return ((r) create(a7, cVar)).invokeSuspend(x.f4917a);
            default:
                return ((r) create(a7, cVar)).invokeSuspend(x.f4917a);
        }
    }

    public final Object invokeSuspend(Object obj) {
        a aVar;
        a aVar2;
        switch (this.d) {
            case 0:
                VexFwkHelperProcessFuture vexFwkHelperProcessFuture = (VexFwkHelperProcessFuture) this.f4176h;
                Object obj2 = C1245a.COROUTINE_SUSPENDED;
                int i2 = this.g;
                if (i2 == 0) {
                    L2.a.A(obj);
                    a access$getTaskMutex$p = vexFwkHelperProcessFuture.taskMutex;
                    this.e = access$getTaskMutex$p;
                    this.f = vexFwkHelperProcessFuture;
                    this.g = 1;
                    fg.c cVar = (fg.c) access$getTaskMutex$p;
                    if (cVar.c(this) != obj2) {
                        aVar = cVar;
                    }
                    return obj2;
                } else if (i2 == 1) {
                    vexFwkHelperProcessFuture = (VexFwkHelperProcessFuture) this.f;
                    aVar = (a) this.e;
                    L2.a.A(obj);
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                try {
                    VexFwkHelperProcessTask access$getCurrentTask$p = vexFwkHelperProcessFuture.currentTask;
                    if (access$getCurrentTask$p != null) {
                        access$getCurrentTask$p.cancel();
                        obj2 = x.f4917a;
                    } else {
                        obj2 = null;
                    }
                    return obj2;
                } finally {
                    ((fg.c) aVar).d((Object) null);
                }
            case 1:
                VexFwkHelperProcessFuture vexFwkHelperProcessFuture2 = (VexFwkHelperProcessFuture) this.f4176h;
                C1245a aVar3 = C1245a.COROUTINE_SUSPENDED;
                int i7 = this.g;
                if (i7 == 0) {
                    L2.a.A(obj);
                    a access$getTaskMutex$p2 = vexFwkHelperProcessFuture2.taskMutex;
                    this.e = access$getTaskMutex$p2;
                    this.f = vexFwkHelperProcessFuture2;
                    this.g = 1;
                    fg.c cVar2 = (fg.c) access$getTaskMutex$p2;
                    if (cVar2.c(this) == aVar3) {
                        return aVar3;
                    }
                    aVar2 = cVar2;
                } else if (i7 == 1) {
                    vexFwkHelperProcessFuture2 = (VexFwkHelperProcessFuture) this.f;
                    aVar2 = (a) this.e;
                    L2.a.A(obj);
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                try {
                    VexFwkHelperProcessTask access$getCurrentTask$p2 = vexFwkHelperProcessFuture2.currentTask;
                    if (access$getCurrentTask$p2 != null) {
                        access$getCurrentTask$p2.close();
                    }
                    vexFwkHelperProcessFuture2.currentTask = null;
                    VexFwkHelperProcessTask access$getPreviousTask$p = vexFwkHelperProcessFuture2.previousTask;
                    if (access$getPreviousTask$p != null) {
                        access$getPreviousTask$p.close();
                    }
                    vexFwkHelperProcessFuture2.previousTask = null;
                    ((fg.c) aVar2).d((Object) null);
                    return x.f4917a;
                } catch (Throwable th) {
                    ((fg.c) aVar2).d((Object) null);
                    throw th;
                }
            default:
                C1245a aVar4 = C1245a.COROUTINE_SUSPENDED;
                int i8 = this.g;
                x xVar = x.f4917a;
                if (i8 == 0) {
                    L2.a.A(obj);
                    A a7 = (A) this.e;
                    h hVar = (h) this.f;
                    b bVar = (b) this.f4176h;
                    C1232h hVar2 = bVar.f3978a;
                    int i10 = bVar.b;
                    if (i10 == -3) {
                        i10 = -2;
                    }
                    Xf.a aVar5 = bVar.f3979c;
                    C c5 = C.ATOMIC;
                    Zf.a aVar6 = new Zf.a(bVar, (C1227c) null, 0);
                    q qVar = new q(D.o(a7, hVar2), F.c(i10, aVar5, 4));
                    qVar.X(c5, qVar, aVar6);
                    this.g = 1;
                    Object t = C0068a.t(hVar, qVar, true, this);
                    if (t != aVar4) {
                        t = xVar;
                    }
                    if (t == aVar4) {
                        return aVar4;
                    }
                } else if (i8 == 1) {
                    L2.a.A(obj);
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                return xVar;
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ r(VexFwkHelperProcessFuture vexFwkHelperProcessFuture, C1227c cVar, int i2) {
        super(2, cVar);
        this.d = i2;
        this.f4176h = vexFwkHelperProcessFuture;
    }
}
