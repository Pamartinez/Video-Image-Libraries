package ge;

import A0.l;
import B2.A;
import D0.e;
import D0.f;
import E2.r;
import He.F;
import ee.C0966C;
import ee.C0985s;
import ee.C0986t;
import ee.C0987u;
import ee.C0989w;
import ee.C0990x;
import ee.e0;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class E0 {

    /* renamed from: a  reason: collision with root package name */
    public final l f4391a;
    public final C0990x b;

    /* renamed from: c  reason: collision with root package name */
    public final C1027h f4392c;
    public final C1036k d;
    public List e;
    public C1031i0 f;
    public boolean g;

    /* renamed from: h  reason: collision with root package name */
    public boolean f4393h;

    /* renamed from: i  reason: collision with root package name */
    public e f4394i;

    /* renamed from: j  reason: collision with root package name */
    public final /* synthetic */ F0 f4395j;

    public E0(F0 f02, l lVar) {
        this.f4395j = f02;
        List list = (List) lVar.g;
        this.e = list;
        Logger logger = F0.f4398c0;
        this.f4391a = lVar;
        C0990x xVar = new C0990x("Subchannel", f02.t.f(), C0990x.d.incrementAndGet());
        this.b = xVar;
        Q0 q0 = f02.l;
        q0.getClass();
        long nanos = TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis());
        C1036k kVar = new C1036k(xVar, nanos, "Subchannel for " + list);
        this.d = kVar;
        this.f4392c = new C1027h(kVar, q0);
    }

    public final void a() {
        this.f4395j.m.d();
        F.t(this.g, "not started");
        C1031i0 i0Var = this.f;
        if (i0Var.v == null) {
            i0Var.k.execute(new C1013c0(i0Var, 1));
        }
    }

    public final void b() {
        e eVar;
        F0 f02 = this.f4395j;
        f02.m.d();
        if (this.f == null) {
            this.f4393h = true;
            return;
        }
        if (!this.f4393h) {
            this.f4393h = true;
        } else if (f02.f4408H && (eVar = this.f4394i) != null) {
            eVar.B();
            this.f4394i = null;
        } else {
            return;
        }
        if (!f02.f4408H) {
            this.f4394i = f02.m.c(new C1046n0(new A(10, (Object) this)), 5, TimeUnit.SECONDS, f02.f.d.l);
            return;
        }
        C1031i0 i0Var = this.f;
        i0Var.k.execute(new C1016d0(i0Var, F0.f4400f0, 0));
    }

    public final void c(C0966C c5) {
        F0 f02 = this.f4395j;
        f02.m.d();
        F.t(!this.g, "already started");
        F.t(!this.f4393h, "already shutdown");
        F.t(!f02.f4408H, "Channel is being terminated");
        this.g = true;
        String f5 = f02.t.f();
        Q0 q0 = f02.s;
        C1021f fVar = f02.f;
        ScheduledExecutorService scheduledExecutorService = fVar.d.l;
        r rVar = f02.f4424p;
        e0 e0Var = f02.m;
        e eVar = new e((Object) this, (Object) c5, false, 29);
        C0987u uVar = f02.f4414O;
        f02.f4411K.getClass();
        f fVar2 = new f(12);
        C1027h hVar = this.f4392c;
        ArrayList arrayList = f02.u;
        C1036k kVar = this.d;
        C1031i0 i0Var = new C1031i0((List) this.f4391a.g, f5, q0, fVar, scheduledExecutorService, rVar, e0Var, eVar, uVar, fVar2, kVar, this.b, hVar, arrayList);
        C1036k kVar2 = f02.f4412M;
        C0985s sVar = C0985s.CT_INFO;
        f02.l.getClass();
        long nanos = TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis());
        F.n(sVar, "severity");
        C1031i0 i0Var2 = i0Var;
        C1031i0 i0Var3 = i0Var2;
        kVar2.b(new C0986t("Child Subchannel started", sVar, nanos, i0Var2));
        this.f = i0Var3;
        C0989w wVar = (C0989w) f02.f4414O.b.put(Long.valueOf(i0Var3.c().f4315c), i0Var3);
        f02.f4404A.add(i0Var3);
    }

    public final String toString() {
        return this.b.toString();
    }
}
