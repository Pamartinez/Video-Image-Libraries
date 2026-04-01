package ge;

import B2.A;
import He.F;
import P1.e;
import ee.C0979l;
import ee.C0984q;
import io.grpc.CallOptions;
import io.grpc.MethodDescriptor;
import io.grpc.a;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledFuture;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class A0 extends C1003D {
    public final C0979l n;

    /* renamed from: o  reason: collision with root package name */
    public final MethodDescriptor f4380o;

    /* renamed from: p  reason: collision with root package name */
    public final CallOptions f4381p;
    public final long q;
    public final /* synthetic */ B0 r;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public A0(ge.B0 r4, ee.C0979l r5, io.grpc.MethodDescriptor r6, io.grpc.CallOptions r7) {
        /*
            r3 = this;
            r3.r = r4
            ge.F0 r4 = r4.d
            java.util.logging.Logger r0 = ge.F0.f4398c0
            java.util.concurrent.Executor r0 = r7.b
            if (r0 != 0) goto L_0x000c
            java.util.concurrent.Executor r0 = r4.f4420h
        L_0x000c:
            ge.D0 r1 = r4.g
            io.grpc.Deadline r2 = r7.f4612a
            r3.<init>(r0, r1, r2)
            r3.n = r5
            r3.f4380o = r6
            r3.f4381p = r7
            ee.Z r4 = r4.Y
            r4.getClass()
            long r4 = java.lang.System.nanoTime()
            r3.q = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: ge.A0.<init>(ge.B0, ee.l, io.grpc.MethodDescriptor, io.grpc.CallOptions):void");
    }

    public final void p() {
        C1045n nVar;
        boolean z;
        C0979l a7 = this.n.a();
        try {
            CallOptions callOptions = this.f4381p;
            CallOptions.Key key = a.d;
            this.r.d.Y.getClass();
            C0984q h5 = this.r.h(this.f4380o, callOptions.e(key, Long.valueOf(System.nanoTime() - this.q)));
            synchronized (this) {
                try {
                    C0984q qVar = this.f4388i;
                    if (qVar != null) {
                        nVar = null;
                    } else {
                        if (qVar == null) {
                            z = true;
                        } else {
                            z = false;
                        }
                        F.q("realCall already set to %s", qVar, z);
                        ScheduledFuture scheduledFuture = this.d;
                        if (scheduledFuture != null) {
                            scheduledFuture.cancel(false);
                        }
                        this.f4388i = h5;
                        nVar = new C1045n(this, this.f);
                    }
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
            if (nVar == null) {
                this.r.d.m.execute(new A(9, (Object) this));
                return;
            }
            F0 f02 = this.r.d;
            Executor executor = this.f4381p.b;
            if (executor == null) {
                executor = f02.f4420h;
            }
            executor.execute(new e(27, this, nVar));
        } finally {
            this.n.c(a7);
        }
    }
}
