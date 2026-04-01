package ge;

import B1.a;
import D0.e;
import D0.f;
import E2.k;
import E2.q;
import E2.r;
import He.F;
import N2.j;
import ee.C0966C;
import ee.C0969b;
import ee.C0970c;
import ee.C0971d;
import ee.C0975h;
import ee.C0976i;
import ee.C0982o;
import ee.C0987u;
import ee.C0989w;
import ee.C0990x;
import ee.Y;
import ee.a0;
import ee.e0;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

/* renamed from: ge.i0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1031i0 implements C0989w {

    /* renamed from: a  reason: collision with root package name */
    public final C0990x f4516a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final Q0 f4517c;
    public final e d;
    public final C1062w e;
    public final ScheduledExecutorService f;
    public final C0987u g;

    /* renamed from: h  reason: collision with root package name */
    public final f f4518h;

    /* renamed from: i  reason: collision with root package name */
    public final C0971d f4519i;

    /* renamed from: j  reason: collision with root package name */
    public final List f4520j;
    public final e0 k;
    public final C1022f0 l;
    public volatile List m;
    public S n;

    /* renamed from: o  reason: collision with root package name */
    public final q f4521o;

    /* renamed from: p  reason: collision with root package name */
    public e f4522p;
    public e q;
    public P0 r;
    public final ArrayList s = new ArrayList();
    public final C1010b0 t = new C1010b0(this, 0);
    public C1019e0 u;
    public volatile C1019e0 v;
    public volatile C0976i w = C0976i.a(C0975h.IDLE);

    /* renamed from: x  reason: collision with root package name */
    public a0 f4523x;

    /* JADX WARNING: type inference failed for: r2v9, types: [ge.f0, java.lang.Object] */
    public C1031i0(List list, String str, Q0 q0, C1021f fVar, ScheduledExecutorService scheduledExecutorService, r rVar, e0 e0Var, e eVar, C0987u uVar, f fVar2, C1036k kVar, C0990x xVar, C0971d dVar, ArrayList arrayList) {
        C0990x xVar2 = xVar;
        C0971d dVar2 = dVar;
        F.n(list, "addressGroups");
        F.i("addressGroups is empty", !list.isEmpty());
        for (Object n3 : list) {
            F.n(n3, "addressGroups contains null entry");
        }
        List unmodifiableList = Collections.unmodifiableList(new ArrayList(list));
        this.m = unmodifiableList;
        ? obj = new Object();
        obj.f4509a = unmodifiableList;
        this.l = obj;
        this.b = str;
        this.f4517c = q0;
        this.e = fVar;
        this.f = scheduledExecutorService;
        this.f4521o = (q) rVar.get();
        this.k = e0Var;
        this.d = eVar;
        this.g = uVar;
        this.f4518h = fVar2;
        F.n(kVar, "channelTracer");
        F.n(xVar2, "logId");
        this.f4516a = xVar2;
        F.n(dVar2, "channelLogger");
        this.f4519i = dVar2;
        this.f4520j = arrayList;
    }

    public static void f(C1031i0 i0Var, C0975h hVar) {
        i0Var.k.d();
        i0Var.h(C0976i.a(hVar));
    }

    /* JADX WARNING: type inference failed for: r5v1, types: [ge.v, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r0v5, types: [ge.h0, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r3v12, types: [java.lang.Object, ge.O0, Kd.a] */
    public static void g(C1031i0 i0Var) {
        boolean z;
        SocketAddress socketAddress;
        ee.r rVar;
        C1022f0 f0Var = i0Var.l;
        e0 e0Var = i0Var.k;
        e0Var.d();
        if (i0Var.f4522p == null) {
            z = true;
        } else {
            z = false;
        }
        F.t(z, "Should have no reconnectTask scheduled");
        if (f0Var.b == 0 && f0Var.f4510c == 0) {
            q qVar = i0Var.f4521o;
            qVar.f175a = false;
            qVar.a();
        }
        SocketAddress socketAddress2 = (SocketAddress) ((C0982o) f0Var.f4509a.get(f0Var.b)).f4304a.get(f0Var.f4510c);
        if (socketAddress2 instanceof ee.r) {
            rVar = (ee.r) socketAddress2;
            socketAddress = rVar.e;
        } else {
            socketAddress = socketAddress2;
            rVar = null;
        }
        C0969b bVar = ((C0982o) f0Var.f4509a.get(f0Var.b)).b;
        String str = (String) bVar.f4292a.get(C0982o.d);
        ? obj = new Object();
        obj.f4562a = "unknown-authority";
        obj.b = C0969b.b;
        if (str == null) {
            str = i0Var.b;
        }
        F.n(str, "authority");
        obj.f4562a = str;
        obj.b = bVar;
        obj.f4563c = rVar;
        ? obj2 = new Object();
        obj2.f4515c = i0Var.f4516a;
        C1019e0 e0Var2 = new C1019e0(i0Var.e.o(socketAddress, obj, obj2), i0Var.f4518h);
        obj2.f4515c = e0Var2.c();
        C0989w wVar = (C0989w) i0Var.g.f4312c.put(Long.valueOf(e0Var2.c().f4315c), e0Var2);
        i0Var.u = e0Var2;
        i0Var.s.add(e0Var2);
        ? obj3 = new Object();
        obj3.f = i0Var;
        obj3.d = false;
        obj3.e = e0Var2;
        Runnable e7 = e0Var2.e(obj3);
        if (e7 != null) {
            e0Var.b(e7);
        }
        i0Var.f4519i.c(C0970c.INFO, "Started transport {0}", obj2.f4515c);
    }

    public static String i(a0 a0Var) {
        StringBuilder sb2 = new StringBuilder();
        Y y = a0Var.f4290a;
        Throwable th = a0Var.f4291c;
        sb2.append(y);
        String str = a0Var.b;
        if (str != null) {
            j.z(sb2, "(", str, ")");
        }
        if (th != null) {
            sb2.append("[");
            sb2.append(th);
            sb2.append("]");
        }
        return sb2.toString();
    }

    public final C0990x c() {
        return this.f4516a;
    }

    public final void h(C0976i iVar) {
        boolean z;
        this.k.d();
        if (this.w.f4298a != iVar.f4298a) {
            if (this.w.f4298a != C0975h.SHUTDOWN) {
                z = true;
            } else {
                z = false;
            }
            F.t(z, "Cannot transition out of SHUTDOWN to " + iVar);
            this.w = iVar;
            ((C0966C) this.d.e).a(iVar);
        }
    }

    public final String toString() {
        k V = a.V(this);
        V.b("logId", this.f4516a.f4315c);
        V.a(this.m, "addressGroups");
        return V.toString();
    }
}
