package Zf;

import Ae.c;
import L2.a;
import Vf.A;
import Xf.e;
import Yf.g;
import java.util.concurrent.atomic.AtomicInteger;
import me.x;
import qe.C1227c;
import re.C1245a;
import se.i;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f extends i implements c {
    public int d;
    public final /* synthetic */ g[] e;
    public final /* synthetic */ int f;
    public final /* synthetic */ AtomicInteger g;

    /* renamed from: h  reason: collision with root package name */
    public final /* synthetic */ e f3980h;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public f(g[] gVarArr, int i2, AtomicInteger atomicInteger, e eVar, C1227c cVar) {
        super(2, cVar);
        this.e = gVarArr;
        this.f = i2;
        this.g = atomicInteger;
        this.f3980h = eVar;
    }

    public final C1227c create(Object obj, C1227c cVar) {
        return new f(this.e, this.f, this.g, this.f3980h, cVar);
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((f) create((A) obj, (C1227c) obj2)).invokeSuspend(x.f4917a);
    }

    public final Object invokeSuspend(Object obj) {
        C1245a aVar = C1245a.COROUTINE_SUSPENDED;
        int i2 = this.d;
        AtomicInteger atomicInteger = this.g;
        e eVar = this.f3980h;
        if (i2 == 0) {
            a.A(obj);
            g[] gVarArr = this.e;
            int i7 = this.f;
            g gVar = gVarArr[i7];
            e eVar2 = new e(eVar, i7);
            this.d = 1;
            if (gVar.collect(eVar2, this) == aVar) {
                return aVar;
            }
        } else if (i2 == 1) {
            try {
                a.A(obj);
            } catch (Throwable th) {
                if (atomicInteger.decrementAndGet() == 0) {
                    eVar.j((Throwable) null);
                }
                throw th;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (atomicInteger.decrementAndGet() == 0) {
            eVar.j((Throwable) null);
        }
        return x.f4917a;
    }
}
