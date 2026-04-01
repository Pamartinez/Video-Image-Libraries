package ge;

import B1.a;
import D0.f;
import E2.k;
import He.F;
import com.google.common.util.concurrent.r;
import com.samsung.scsp.common.Header;
import ee.C0971d;
import ee.C0974g;
import ee.C0979l;
import ee.C0981n;
import ee.C0984q;
import ee.M;
import ee.O;
import ee.a0;
import ie.b;
import io.grpc.CallOptions;
import io.grpc.MethodDescriptor;
import java.nio.charset.Charset;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/* renamed from: ge.q  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1051q extends C0984q {
    public static final Logger t = Logger.getLogger(C1051q.class.getName());
    public static final double u = (((double) TimeUnit.SECONDS.toNanos(1)) * 1.0d);
    public final MethodDescriptor d;
    public final Executor e;
    public final boolean f;
    public final f g;

    /* renamed from: h  reason: collision with root package name */
    public final C0979l f4536h;

    /* renamed from: i  reason: collision with root package name */
    public volatile ScheduledFuture f4537i;

    /* renamed from: j  reason: collision with root package name */
    public final boolean f4538j;
    public CallOptions k;
    public r l;
    public volatile boolean m;
    public boolean n;

    /* renamed from: o  reason: collision with root package name */
    public boolean f4539o;

    /* renamed from: p  reason: collision with root package name */
    public final C1047o f4540p;
    public final ScheduledExecutorService q;
    public C0981n r = C0981n.d;
    public C0974g s = C0974g.b;

    static {
        Header.GZIP.getBytes(Charset.forName("US-ASCII"));
    }

    /* JADX WARNING: type inference failed for: r5v4, types: [java.util.concurrent.Executor, java.lang.Object] */
    public C1051q(MethodDescriptor methodDescriptor, Executor executor, CallOptions callOptions, C1047o oVar, ScheduledExecutorService scheduledExecutorService, f fVar) {
        this.d = methodDescriptor;
        String str = methodDescriptor.b;
        System.identityHashCode(this);
        b.f4600a.getClass();
        boolean z = false;
        if (executor == r.INSTANCE) {
            this.e = new Object();
            this.f = true;
        } else {
            this.e = new F1(executor);
            this.f = false;
        }
        this.g = fVar;
        this.f4536h = C0979l.b();
        O o2 = methodDescriptor.f4620a;
        this.f4538j = (o2 == O.UNARY || o2 == O.SERVER_STREAMING) ? true : z;
        this.k = callOptions;
        this.f4540p = oVar;
        this.q = scheduledExecutorService;
    }

    public final void b(String str, Throwable th) {
        b.b();
        try {
            b.a();
            m(str, th);
            b.f4600a.getClass();
            return;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public final void d() {
        boolean z;
        b.b();
        try {
            b.a();
            if (this.l != null) {
                z = true;
            } else {
                z = false;
            }
            F.t(z, "Not started");
            F.t(!this.n, "call was cancelled");
            F.t(!this.f4539o, "call already half-closed");
            this.f4539o = true;
            this.l.t();
            b.f4600a.getClass();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public final void h(int i2) {
        boolean z;
        b.b();
        try {
            b.a();
            boolean z3 = false;
            if (this.l != null) {
                z = true;
            } else {
                z = false;
            }
            F.t(z, "Not started");
            if (i2 >= 0) {
                z3 = true;
            }
            F.i("Number requested must be non-negative", z3);
            this.l.f(i2);
            b.f4600a.getClass();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public final void i(Object obj) {
        b.b();
        try {
            b.a();
            o(obj);
            b.f4600a.getClass();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public final void l(C0971d dVar, M m4) {
        b.b();
        try {
            b.a();
            p(dVar, m4);
            b.f4600a.getClass();
            return;
        } catch (Throwable th) {
            th.addSuppressed(th);
        }
        throw th;
    }

    public final void m(String str, Throwable th) {
        a0 a0Var;
        if (str == null && th == null) {
            th = new CancellationException("Cancelled without a message or cause");
            t.log(Level.WARNING, "Cancelling without a message or cause is suboptimal", th);
        }
        if (!this.n) {
            this.n = true;
            try {
                if (this.l != null) {
                    a0 a0Var2 = a0.f;
                    if (str != null) {
                        a0Var = a0Var2.g(str);
                    } else {
                        a0Var = a0Var2.g("Call cancelled without message");
                    }
                    if (th != null) {
                        a0Var = a0Var.f(th);
                    }
                    this.l.v(a0Var);
                }
                n();
            } catch (Throwable th2) {
                n();
                throw th2;
            }
        }
    }

    public final void n() {
        this.f4536h.getClass();
        ScheduledFuture scheduledFuture = this.f4537i;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
    }

    public final void o(Object obj) {
        boolean z;
        if (this.l != null) {
            z = true;
        } else {
            z = false;
        }
        F.t(z, "Not started");
        F.t(!this.n, "call was cancelled");
        F.t(!this.f4539o, "call was half-closed");
        try {
            r rVar = this.l;
            if (rVar instanceof C1057t0) {
                ((C1057t0) rVar).p(obj);
            } else {
                rVar.x(this.d.c(obj));
            }
            if (!this.f4538j) {
                this.l.flush();
            }
        } catch (RuntimeException e7) {
            this.l.v(a0.f.f(e7).g("Failed to stream message"));
        } catch (Error e8) {
            this.l.v(a0.f.g("Client sendMessage() failed with Error"));
            throw e8;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0061, code lost:
        if ((r14.e - r12.e) < 0) goto L_0x0089;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void p(ee.C0971d r19, ee.M r20) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r5 = r20
            ee.f r2 = ee.C0973f.b
            ge.r r3 = r0.l
            r4 = 1
            r6 = 0
            if (r3 != 0) goto L_0x0010
            r3 = r4
            goto L_0x0011
        L_0x0010:
            r3 = r6
        L_0x0011:
            java.lang.String r7 = "Already started"
            He.F.t(r3, r7)
            boolean r3 = r0.n
            r3 = r3 ^ r4
            java.lang.String r4 = "call was cancelled"
            He.F.t(r3, r4)
            ee.l r3 = r0.f4536h
            r3.getClass()
            io.grpc.CallOptions r3 = r0.k
            io.grpc.CallOptions$Key r4 = ge.L0.g
            java.lang.Object r3 = r3.a(r4)
            ge.L0 r3 = (ge.L0) r3
            if (r3 != 0) goto L_0x0033
            r15 = 0
            goto L_0x0121
        L_0x0033:
            java.lang.Integer r9 = r3.d
            java.lang.Integer r10 = r3.f4458c
            java.lang.Long r11 = r3.f4457a
            if (r11 == 0) goto L_0x00a4
            long r11 = r11.longValue()
            java.util.concurrent.TimeUnit r13 = java.util.concurrent.TimeUnit.NANOSECONDS
            if (r13 == 0) goto L_0x009a
            io.grpc.Deadline r14 = new io.grpc.Deadline
            long r11 = r13.toNanos(r11)
            r14.<init>(r11)
            io.grpc.CallOptions r11 = r0.k
            io.grpc.Deadline r12 = r11.f4612a
            if (r12 == 0) goto L_0x0087
            ee.Z r13 = r14.d
            ee.Z r15 = r12.d
            if (r13 != r15) goto L_0x0064
            r15 = 0
            long r7 = r14.e
            long r12 = r12.e
            long r7 = r7 - r12
            int r7 = (r7 > r15 ? 1 : (r7 == r15 ? 0 : -1))
            if (r7 >= 0) goto L_0x00a6
            goto L_0x0089
        L_0x0064:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Tickers ("
            r1.<init>(r2)
            r1.append(r13)
            java.lang.String r2 = " and "
            r1.append(r2)
            ee.Z r2 = r12.d
            r1.append(r2)
            java.lang.String r2 = ") don't match. Custom Ticker should only be used in tests!"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0087:
            r15 = 0
        L_0x0089:
            r11.getClass()
            Df.n r7 = io.grpc.CallOptions.b(r11)
            r7.f3358a = r14
            io.grpc.CallOptions r8 = new io.grpc.CallOptions
            r8.<init>(r7)
            r0.k = r8
            goto L_0x00a6
        L_0x009a:
            ee.Z r0 = io.grpc.Deadline.g
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "units"
            r0.<init>(r1)
            throw r0
        L_0x00a4:
            r15 = 0
        L_0x00a6:
            java.lang.Boolean r3 = r3.b
            if (r3 == 0) goto L_0x00d7
            boolean r3 = r3.booleanValue()
            if (r3 == 0) goto L_0x00c3
            io.grpc.CallOptions r3 = r0.k
            r3.getClass()
            Df.n r3 = io.grpc.CallOptions.b(r3)
            java.lang.Boolean r7 = java.lang.Boolean.TRUE
            r3.g = r7
            io.grpc.CallOptions r7 = new io.grpc.CallOptions
            r7.<init>(r3)
            goto L_0x00d5
        L_0x00c3:
            io.grpc.CallOptions r3 = r0.k
            r3.getClass()
            Df.n r3 = io.grpc.CallOptions.b(r3)
            java.lang.Boolean r7 = java.lang.Boolean.FALSE
            r3.g = r7
            io.grpc.CallOptions r7 = new io.grpc.CallOptions
            r7.<init>(r3)
        L_0x00d5:
            r0.k = r7
        L_0x00d7:
            if (r10 == 0) goto L_0x00fc
            io.grpc.CallOptions r3 = r0.k
            java.lang.Integer r7 = r3.f4614h
            if (r7 == 0) goto L_0x00f2
            int r7 = r7.intValue()
            int r8 = r10.intValue()
            int r7 = java.lang.Math.min(r7, r8)
            io.grpc.CallOptions r3 = r3.c(r7)
            r0.k = r3
            goto L_0x00fc
        L_0x00f2:
            int r7 = r10.intValue()
            io.grpc.CallOptions r3 = r3.c(r7)
            r0.k = r3
        L_0x00fc:
            if (r9 == 0) goto L_0x0121
            io.grpc.CallOptions r3 = r0.k
            java.lang.Integer r7 = r3.f4615i
            if (r7 == 0) goto L_0x0117
            int r7 = r7.intValue()
            int r8 = r9.intValue()
            int r7 = java.lang.Math.min(r7, r8)
            io.grpc.CallOptions r3 = r3.d(r7)
            r0.k = r3
            goto L_0x0121
        L_0x0117:
            int r7 = r9.intValue()
            io.grpc.CallOptions r3 = r3.d(r7)
            r0.k = r3
        L_0x0121:
            io.grpc.CallOptions r3 = r0.k
            java.lang.String r3 = r3.d
            if (r3 == 0) goto L_0x0144
            ee.g r7 = r0.s
            java.util.concurrent.ConcurrentHashMap r7 = r7.f4297a
            java.lang.Object r7 = r7.get(r3)
            ee.f r7 = (ee.C0973f) r7
            if (r7 != 0) goto L_0x0142
            ge.Q0 r2 = ge.Q0.e
            r0.l = r2
            java.util.concurrent.Executor r2 = r0.e
            ge.l r4 = new ge.l
            r4.<init>(r0, r1, r3)
            r2.execute(r4)
            return
        L_0x0142:
            r10 = r7
            goto L_0x0145
        L_0x0144:
            r10 = r2
        L_0x0145:
            ee.n r3 = r0.r
            ee.G r7 = ge.Z.f4491h
            r5.a(r7)
            ee.G r7 = ge.Z.d
            r5.a(r7)
            if (r10 == r2) goto L_0x015a
            java.lang.String r2 = r10.a()
            r5.c(r7, r2)
        L_0x015a:
            ee.K r2 = ge.Z.e
            r5.a(r2)
            byte[] r3 = r3.b
            int r7 = r3.length
            if (r7 == 0) goto L_0x0167
            r5.c(r2, r3)
        L_0x0167:
            ee.G r2 = ge.Z.f
            r5.a(r2)
            ee.K r2 = ge.Z.g
            r5.a(r2)
            io.grpc.CallOptions r2 = r0.k
            io.grpc.Deadline r2 = r2.f4612a
            ee.l r3 = r0.f4536h
            r3.getClass()
            r11 = 0
            if (r2 != 0) goto L_0x017f
            r12 = r11
            goto L_0x0180
        L_0x017f:
            r12 = r2
        L_0x0180:
            if (r12 == 0) goto L_0x01e1
            boolean r2 = r12.a()
            if (r2 == 0) goto L_0x01e1
            io.grpc.CallOptions r2 = r0.k
            io.grpc.a[] r2 = ge.Z.a(r2, r6, r6)
            io.grpc.CallOptions r3 = r0.k
            io.grpc.Deadline r3 = r3.f4612a
            ee.l r4 = r0.f4536h
            r4.getClass()
            if (r3 != 0) goto L_0x019c
            java.lang.String r3 = "Context"
            goto L_0x019e
        L_0x019c:
            java.lang.String r3 = "CallOptions"
        L_0x019e:
            io.grpc.CallOptions r4 = r0.k
            io.grpc.CallOptions$Key r5 = io.grpc.a.d
            java.lang.Object r4 = r4.a(r5)
            java.lang.Long r4 = (java.lang.Long) r4
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.NANOSECONDS
            long r5 = r12.b()
            double r5 = (double) r5
            double r7 = u
            double r5 = r5 / r7
            java.lang.Double r5 = java.lang.Double.valueOf(r5)
            if (r4 != 0) goto L_0x01bb
            r6 = 0
            goto L_0x01c2
        L_0x01bb:
            long r13 = r4.longValue()
            double r13 = (double) r13
            double r6 = r13 / r7
        L_0x01c2:
            java.lang.Double r4 = java.lang.Double.valueOf(r6)
            java.lang.Object[] r3 = new java.lang.Object[]{r3, r5, r4}
            java.lang.String r4 = "ClientCall started after %s deadline was exceeded %.9f seconds ago. Name resolution delay %.9f seconds."
            java.lang.String r3 = java.lang.String.format(r4, r3)
            ge.T r4 = new ge.T
            ee.a0 r5 = ee.a0.f4286h
            ee.a0 r3 = r5.g(r3)
            ge.s r5 = ge.C1054s.PROCESSED
            r4.<init>(r3, r5, r2)
            r0.l = r4
            goto L_0x02d0
        L_0x01e1:
            ee.l r2 = r0.f4536h
            r2.getClass()
            io.grpc.CallOptions r2 = r0.k
            io.grpc.Deadline r2 = r2.f4612a
            java.util.logging.Logger r3 = t
            java.util.logging.Level r7 = java.util.logging.Level.FINE
            boolean r7 = r3.isLoggable(r7)
            if (r7 == 0) goto L_0x0240
            if (r12 == 0) goto L_0x0240
            boolean r7 = r12.equals(r11)
            if (r7 != 0) goto L_0x01fd
            goto L_0x0240
        L_0x01fd:
            java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.NANOSECONDS
            long r7 = r12.b()
            r13 = r15
            long r7 = java.lang.Math.max(r13, r7)
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.util.Locale r13 = java.util.Locale.US
            java.lang.String r13 = "Call timeout set to '"
            java.lang.String r14 = "' ns, due to context deadline."
            java.lang.String r7 = A.a.e(r7, r13, r14)
            r9.<init>(r7)
            if (r2 != 0) goto L_0x021f
            java.lang.String r2 = " Explicit call timeout was not set."
            r9.append(r2)
            goto L_0x0239
        L_0x021f:
            long r7 = r2.b()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r13 = " Explicit call timeout was '"
            r2.<init>(r13)
            r2.append(r7)
            java.lang.String r7 = "' ns."
            r2.append(r7)
            java.lang.String r2 = r2.toString()
            r9.append(r2)
        L_0x0239:
            java.lang.String r2 = r9.toString()
            r3.fine(r2)
        L_0x0240:
            ge.o r2 = r0.f4540p
            io.grpc.MethodDescriptor r3 = r0.d
            io.grpc.CallOptions r7 = r0.k
            ee.l r9 = r0.f4536h
            ge.u0 r2 = (ge.C1059u0) r2
            ge.F0 r8 = r2.b
            boolean r8 = r8.f4417X
            if (r8 != 0) goto L_0x02ae
            java.lang.String r4 = "method"
            He.F.n(r3, r4)
            java.lang.String r4 = "callOptions"
            He.F.n(r7, r4)
            ge.F0 r4 = r2.b
            ee.d r4 = r4.y
            ge.F0 r8 = r2.b
            java.util.concurrent.atomic.AtomicBoolean r8 = r8.f4407G
            boolean r8 = r8.get()
            if (r8 == 0) goto L_0x026d
            ge.F0 r2 = r2.b
            ge.G r2 = r2.E
            goto L_0x0299
        L_0x026d:
            if (r4 != 0) goto L_0x0281
            ge.F0 r4 = r2.b
            ee.e0 r4 = r4.m
            B2.A r8 = new B2.A
            r13 = 7
            r8.<init>((int) r13, (java.lang.Object) r2)
            r4.execute(r8)
            ge.F0 r2 = r2.b
            ge.G r2 = r2.E
            goto L_0x0299
        L_0x0281:
            ee.A r4 = r4.j()
            java.lang.Boolean r8 = java.lang.Boolean.TRUE
            java.lang.Boolean r13 = r7.g
            boolean r8 = r8.equals(r13)
            ge.u r4 = ge.Z.d(r4, r8)
            if (r4 == 0) goto L_0x0295
            r2 = r4
            goto L_0x0299
        L_0x0295:
            ge.F0 r2 = r2.b
            ge.G r2 = r2.E
        L_0x0299:
            ee.l r4 = r9.a()
            io.grpc.a[] r6 = ge.Z.a(r7, r6, r6)
            ge.r r2 = r2.a(r3, r5, r7, r6)     // Catch:{ all -> 0x02a9 }
            r9.c(r4)
            goto L_0x02ce
        L_0x02a9:
            r0 = move-exception
            r9.c(r4)
            throw r0
        L_0x02ae:
            java.lang.Object r4 = r7.a(r4)
            ge.L0 r4 = (ge.L0) r4
            if (r4 != 0) goto L_0x02b8
            r6 = r11
            goto L_0x02ba
        L_0x02b8:
            ge.x1 r6 = r4.e
        L_0x02ba:
            if (r4 != 0) goto L_0x02c0
            r8 = r11
        L_0x02bd:
            r4 = r3
            r3 = r2
            goto L_0x02c4
        L_0x02c0:
            ge.a0 r4 = r4.f
            r8 = r4
            goto L_0x02bd
        L_0x02c4:
            ge.t0 r2 = new ge.t0
            r17 = r7
            r7 = r6
            r6 = r17
            r2.<init>(r3, r4, r5, r6, r7, r8, r9)
        L_0x02ce:
            r0.l = r2
        L_0x02d0:
            boolean r2 = r0.f
            if (r2 == 0) goto L_0x02d9
            ge.r r2 = r0.l
            r2.j()
        L_0x02d9:
            io.grpc.CallOptions r2 = r0.k
            r2.getClass()
            io.grpc.CallOptions r2 = r0.k
            java.lang.Integer r2 = r2.f4614h
            if (r2 == 0) goto L_0x02ed
            ge.r r3 = r0.l
            int r2 = r2.intValue()
            r3.g(r2)
        L_0x02ed:
            io.grpc.CallOptions r2 = r0.k
            java.lang.Integer r2 = r2.f4615i
            if (r2 == 0) goto L_0x02fc
            ge.r r3 = r0.l
            int r2 = r2.intValue()
            r3.h(r2)
        L_0x02fc:
            if (r12 == 0) goto L_0x0303
            ge.r r2 = r0.l
            r2.u(r12)
        L_0x0303:
            ge.r r2 = r0.l
            r2.w(r10)
            ge.r r2 = r0.l
            ee.n r3 = r0.r
            r2.b(r3)
            D0.f r2 = r0.g
            java.lang.Object r3 = r2.f
            ge.o0 r3 = (ge.C1048o0) r3
            r3.y()
            java.lang.Object r2 = r2.e
            ge.Q0 r2 = (ge.Q0) r2
            r2.getClass()
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.MILLISECONDS
            long r3 = java.lang.System.currentTimeMillis()
            r2.toNanos(r3)
            ge.r r2 = r0.l
            A0.l r3 = new A0.l
            r3.<init>((ge.C1051q) r0, (ee.C0971d) r1)
            r2.k(r3)
            ee.l r1 = r0.f4536h
            com.google.common.util.concurrent.r r2 = com.google.common.util.concurrent.r.INSTANCE
            r1.getClass()
            java.util.logging.Logger r1 = ee.C0979l.f4300a
            if (r2 == 0) goto L_0x036e
            if (r12 == 0) goto L_0x0366
            ee.l r1 = r0.f4536h
            r1.getClass()
            boolean r1 = r12.equals(r11)
            if (r1 != 0) goto L_0x0366
            java.util.concurrent.ScheduledExecutorService r1 = r0.q
            if (r1 == 0) goto L_0x0366
            java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.NANOSECONDS
            long r2 = r12.b()
            java.util.concurrent.ScheduledExecutorService r4 = r0.q
            ge.n0 r5 = new ge.n0
            ge.p r6 = new ge.p
            r6.<init>(r0, r2)
            r5.<init>(r6)
            java.util.concurrent.ScheduledFuture r1 = r4.schedule(r5, r2, r1)
            r0.f4537i = r1
        L_0x0366:
            boolean r1 = r0.m
            if (r1 == 0) goto L_0x036d
            r0.n()
        L_0x036d:
            return
        L_0x036e:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "executor"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: ge.C1051q.p(ee.d, ee.M):void");
    }

    public final String toString() {
        k V = a.V(this);
        V.a(this.d, "method");
        return V.toString();
    }
}
