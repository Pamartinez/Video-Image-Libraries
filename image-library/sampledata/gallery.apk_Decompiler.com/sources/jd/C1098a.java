package jd;

import Ae.c;
import L2.a;
import Vf.A;
import Vf.C;
import Vf.C0888z;
import Vf.D;
import Vf.H;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.i;

/* renamed from: jd.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1098a extends i implements c {
    public final /* synthetic */ int d;
    public int e;
    public final /* synthetic */ c f;
    public /* synthetic */ Object g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1098a(c cVar, Object obj, C1227c cVar2, int i2) {
        super(2, cVar2);
        this.d = i2;
        this.f = cVar;
        this.g = obj;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        switch (this.d) {
            case 0:
                return new C1098a(this.f, this.g, cVar, 0);
            case 1:
                return new C1098a(this.f, this.g, cVar, 1);
            default:
                C1098a aVar = new C1098a(this.f, cVar);
                aVar.g = obj;
                return aVar;
        }
    }

    public final Object invoke(Object obj, Object obj2) {
        A a7 = (A) obj;
        C1227c cVar = (C1227c) obj2;
        switch (this.d) {
            case 0:
                return ((C1098a) create(a7, cVar)).invokeSuspend(x.f4917a);
            case 1:
                return ((C1098a) create(a7, cVar)).invokeSuspend(x.f4917a);
            default:
                return ((C1098a) create(a7, cVar)).invokeSuspend(x.f4917a);
        }
    }

    public final Object invokeSuspend(Object obj) {
        switch (this.d) {
            case 0:
                C1245a aVar = C1245a.COROUTINE_SUSPENDED;
                int i2 = this.e;
                if (i2 == 0) {
                    a.A(obj);
                    Object obj2 = this.g;
                    this.e = 1;
                    Object invoke = this.f.invoke(obj2, this);
                    if (invoke == aVar) {
                        return aVar;
                    }
                    return invoke;
                } else if (i2 == 1) {
                    a.A(obj);
                    return obj;
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            case 1:
                C1245a aVar2 = C1245a.COROUTINE_SUSPENDED;
                int i7 = this.e;
                if (i7 == 0) {
                    a.A(obj);
                    Object obj3 = this.g;
                    this.e = 1;
                    if (this.f.invoke(obj3, this) == aVar2) {
                        return aVar2;
                    }
                } else if (i7 == 1) {
                    a.A(obj);
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                return x.f4917a;
            default:
                C1245a aVar3 = C1245a.COROUTINE_SUSPENDED;
                int i8 = this.e;
                if (i8 == 0) {
                    a.A(obj);
                    H b = D.b((A) this.g, (C0888z) null, (C) null, this.f, 3);
                    this.e = 1;
                    Object m = b.m(this);
                    if (m == aVar3) {
                        return aVar3;
                    }
                    return m;
                } else if (i8 == 1) {
                    a.A(obj);
                    return obj;
                } else {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1098a(c cVar, C1227c cVar2) {
        super(2, cVar2);
        this.d = 2;
        this.f = cVar;
    }
}
