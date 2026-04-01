package Zf;

import Ae.d;
import Tf.o;
import Tf.w;
import Vf.C0884v;
import Vf.D;
import Yf.h;
import kotlin.jvm.internal.j;
import me.k;
import me.x;
import qe.C1227c;
import qe.C1232h;
import qe.C1233i;
import re.C1245a;
import se.C1271c;
import se.C1272d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class l extends C1271c implements h {
    public final h d;
    public final C1232h e;
    public final int f;
    public C1232h g;

    /* renamed from: h  reason: collision with root package name */
    public C1227c f3985h;

    public l(h hVar, C1232h hVar2) {
        super(i.d, C1233i.d);
        this.d = hVar;
        this.e = hVar2;
        this.f = ((Number) hVar2.fold(0, new C0884v(3))).intValue();
    }

    public final Object a(C1227c cVar, Object obj) {
        C1232h context = cVar.getContext();
        D.f(context);
        C1232h hVar = this.g;
        if (hVar != context) {
            if (hVar instanceof h) {
                throw new IllegalStateException(o.l0("\n            Flow exception transparency is violated:\n                Previous 'emit' call has thrown exception " + ((h) hVar).e + ", but then emission attempt of value '" + obj + "' has been detected.\n                Emissions from 'catch' blocks are prohibited in order to avoid unspecified behaviour, 'Flow.catch' operator can be used instead.\n                For a more detailed explanation, please refer to Flow documentation.\n            ").toString());
            } else if (((Number) context.fold(0, new w(2, this))).intValue() == this.f) {
                this.g = context;
            } else {
                throw new IllegalStateException(("Flow invariant is violated:\n\t\tFlow was collected in " + this.e + ",\n\t\tbut emission happened in " + context + ".\n\t\tPlease refer to 'flow' documentation or use 'flowOn' instead").toString());
            }
        }
        this.f3985h = cVar;
        d dVar = n.f3986a;
        h hVar2 = this.d;
        j.c(hVar2, "null cannot be cast to non-null type kotlinx.coroutines.flow.FlowCollector<kotlin.Any?>");
        Object invoke = dVar.invoke(hVar2, obj, this);
        if (!j.a(invoke, C1245a.COROUTINE_SUSPENDED)) {
            this.f3985h = null;
        }
        return invoke;
    }

    public final Object emit(Object obj, C1227c cVar) {
        try {
            Object a7 = a(cVar, obj);
            if (a7 == C1245a.COROUTINE_SUSPENDED) {
                return a7;
            }
            return x.f4917a;
        } catch (Throwable th) {
            this.g = new h(th, cVar.getContext());
            throw th;
        }
    }

    public final C1272d getCallerFrame() {
        C1227c cVar = this.f3985h;
        if (cVar instanceof C1272d) {
            return (C1272d) cVar;
        }
        return null;
    }

    public final C1232h getContext() {
        C1232h hVar = this.g;
        if (hVar == null) {
            return C1233i.d;
        }
        return hVar;
    }

    public final StackTraceElement getStackTraceElement() {
        return null;
    }

    public final Object invokeSuspend(Object obj) {
        Throwable a7 = k.a(obj);
        if (a7 != null) {
            this.g = new h(a7, getContext());
        }
        C1227c cVar = this.f3985h;
        if (cVar != null) {
            cVar.resumeWith(obj);
        }
        return C1245a.COROUTINE_SUSPENDED;
    }
}
