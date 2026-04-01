package cg;

import Vf.C0883u;
import Vf.C0886x;
import Vf.D;
import Vf.K;
import Vf.X;
import Vf.y0;
import com.arcsoft.libarccommon.utils.ArcCommonLog;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import me.k;
import qe.C1227c;
import qe.C1232h;
import se.C1272d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class f extends K implements C1272d, C1227c {
    public static final /* synthetic */ AtomicReferenceFieldUpdater k = AtomicReferenceFieldUpdater.newUpdater(f.class, Object.class, "_reusableCancellableContinuation$volatile");
    private volatile /* synthetic */ Object _reusableCancellableContinuation$volatile;
    public final C0886x g;

    /* renamed from: h  reason: collision with root package name */
    public final C1227c f4020h;

    /* renamed from: i  reason: collision with root package name */
    public Object f4021i = a.b;

    /* renamed from: j  reason: collision with root package name */
    public final Object f4022j;

    public f(C0886x xVar, C1227c cVar) {
        super(-1);
        this.g = xVar;
        this.f4020h = cVar;
        this.f4022j = a.k(cVar.getContext());
    }

    public final C1272d getCallerFrame() {
        C1227c cVar = this.f4020h;
        if (cVar instanceof C1272d) {
            return (C1272d) cVar;
        }
        return null;
    }

    public final C1232h getContext() {
        return this.f4020h.getContext();
    }

    public final Object i() {
        Object obj = this.f4021i;
        this.f4021i = a.b;
        return obj;
    }

    public final void resumeWith(Object obj) {
        Object obj2;
        C1232h context;
        Object l;
        Throwable a7 = k.a(obj);
        if (a7 == null) {
            obj2 = obj;
        } else {
            obj2 = new C0883u(a7, false);
        }
        C1227c cVar = this.f4020h;
        C1232h context2 = cVar.getContext();
        C0886x xVar = this.g;
        if (xVar.j(context2)) {
            this.f4021i = obj2;
            this.f = 0;
            xVar.i(cVar.getContext(), this);
            return;
        }
        X a10 = y0.a();
        if (a10.d >= 4294967296L) {
            this.f4021i = obj2;
            this.f = 0;
            a10.m(this);
            return;
        }
        a10.p(true);
        try {
            context = cVar.getContext();
            l = a.l(context, this.f4022j);
            cVar.resumeWith(obj);
            a.g(context, l);
            do {
            } while (a10.r());
        } catch (Throwable th) {
            a10.l(true);
            throw th;
        }
        a10.l(true);
    }

    public final String toString() {
        return "DispatchedContinuation[" + this.g + ArcCommonLog.TAG_COMMA + D.t(this.f4020h) + ']';
    }

    public final C1227c e() {
        return this;
    }
}
