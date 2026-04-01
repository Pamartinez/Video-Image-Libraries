package io.grpc.binder;

import android.content.Context;
import ee.C0969b;
import ge.C1028h0;
import ge.C1060v;
import ge.C1062w;
import ge.C1064x;
import ge.K1;
import ge.L1;
import java.net.SocketAddress;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class c implements C1062w {
    public final Context d;
    public final ie.c e;
    public final Executor f;
    public final G0.c g;

    /* renamed from: h  reason: collision with root package name */
    public final G0.c f4624h;

    /* renamed from: i  reason: collision with root package name */
    public final com.samsung.context.sdk.samsunganalytics.internal.sender.c f4625i;

    /* renamed from: j  reason: collision with root package name */
    public final b f4626j;
    public final e k;
    public ScheduledExecutorService l;
    public Executor m;
    public boolean n;

    public c(Context context, ie.c cVar, Executor executor, G0.c cVar2, G0.c cVar3, com.samsung.context.sdk.samsunganalytics.internal.sender.c cVar4, b bVar, e eVar) {
        this.d = context;
        this.e = cVar;
        this.f = executor;
        this.g = cVar2;
        this.f4624h = cVar3;
        this.f4625i = cVar4;
        this.f4626j = bVar;
        this.k = eVar;
        this.l = (ScheduledExecutorService) L1.a((K1) cVar2.e);
        this.m = (Executor) L1.a((K1) cVar3.e);
    }

    public final void close() {
        this.n = true;
        this.g.B(this.l);
        this.l = null;
        this.f4624h.B(this.m);
        this.m = null;
    }

    public final C1064x o(SocketAddress socketAddress, C1060v vVar, C1028h0 h0Var) {
        if (!this.n) {
            e eVar = this.k;
            C0969b bVar = vVar.b;
            return new fe.c(this.d, this.e, (a) socketAddress, this.f4626j, this.f, this.g, this.f4624h, this.f4625i, eVar, bVar);
        }
        throw new IllegalStateException("The transport factory is closed.");
    }
}
