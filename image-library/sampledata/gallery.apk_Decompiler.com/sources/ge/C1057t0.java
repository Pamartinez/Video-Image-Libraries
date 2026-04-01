package ge;

import A0.l;
import B1.b;
import B2.A;
import Df.n;
import G0.c;
import He.F;
import Kd.a;
import ee.C0971d;
import ee.C0973f;
import ee.C0979l;
import ee.C0981n;
import ee.G;
import ee.I;
import ee.M;
import ee.Z;
import ee.a0;
import ee.e0;
import he.C1076a;
import io.grpc.CallOptions;
import io.grpc.Deadline;
import io.grpc.MethodDescriptor;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: ge.t0  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1057t0 implements r {

    /* renamed from: H  reason: collision with root package name */
    public static final G f4547H;

    /* renamed from: I  reason: collision with root package name */
    public static final G f4548I;

    /* renamed from: J  reason: collision with root package name */
    public static final a0 f4549J = a0.f.g("Stream thrown away because RetriableStream committed");

    /* renamed from: K  reason: collision with root package name */
    public static final Random f4550K = new Random();

    /* renamed from: A  reason: collision with root package name */
    public long f4551A;
    public a0 B;

    /* renamed from: C  reason: collision with root package name */
    public boolean f4552C;
    public final /* synthetic */ MethodDescriptor D;
    public final /* synthetic */ CallOptions E;

    /* renamed from: F  reason: collision with root package name */
    public final /* synthetic */ C0979l f4553F;

    /* renamed from: G  reason: collision with root package name */
    public final /* synthetic */ C1059u0 f4554G;
    public final MethodDescriptor d;
    public final Executor e;
    public final e0 f;
    public final ScheduledExecutorService g;

    /* renamed from: h  reason: collision with root package name */
    public final M f4555h;

    /* renamed from: i  reason: collision with root package name */
    public final x1 f4556i;

    /* renamed from: j  reason: collision with root package name */
    public final C1007a0 f4557j;
    public final boolean k;
    public final Object l;
    public final b m;
    public final long n;

    /* renamed from: o  reason: collision with root package name */
    public final long f4558o;

    /* renamed from: p  reason: collision with root package name */
    public final w1 f4559p;
    public final c q;
    public volatile r1 r;
    public final AtomicBoolean s;
    public final AtomicInteger t;
    public final AtomicInteger u;
    public l v;
    public long w;

    /* renamed from: x  reason: collision with root package name */
    public C1056t f4560x;
    public a y;
    public a z;

    static {
        Z z3 = M.d;
        BitSet bitSet = I.d;
        f4547H = new G("grpc-previous-rpc-attempts", z3);
        f4548I = new G("grpc-retry-pushback-ms", z3);
    }

    /* JADX WARNING: type inference failed for: r13v0, types: [java.lang.Thread$UncaughtExceptionHandler, java.lang.Object] */
    public C1057t0(C1059u0 u0Var, MethodDescriptor methodDescriptor, M m4, CallOptions callOptions, x1 x1Var, C1007a0 a0Var, C0979l lVar) {
        boolean z3;
        C1059u0 u0Var2 = u0Var;
        MethodDescriptor methodDescriptor2 = methodDescriptor;
        CallOptions callOptions2 = callOptions;
        x1 x1Var2 = x1Var;
        C1007a0 a0Var2 = a0Var;
        this.f4554G = u0Var2;
        this.D = methodDescriptor2;
        this.E = callOptions2;
        this.f4553F = lVar;
        F0 f02 = u0Var2.b;
        b bVar = f02.U;
        long j2 = f02.V;
        long j3 = f02.f4416W;
        Executor executor = callOptions2.b;
        executor = executor == null ? f02.f4420h : executor;
        ScheduledExecutorService scheduledExecutorService = f02.f.d.l;
        w1 w1Var = u0Var2.f4561a;
        this.f = new e0(new Object());
        this.l = new Object();
        this.q = new c(11);
        this.r = new r1(new ArrayList(8), Collections.EMPTY_LIST, (Collection) null, (v1) null, false, false, false, 0);
        this.s = new AtomicBoolean();
        this.t = new AtomicInteger();
        this.u = new AtomicInteger();
        this.d = methodDescriptor2;
        this.m = bVar;
        this.n = j2;
        this.f4558o = j3;
        this.e = executor;
        this.g = scheduledExecutorService;
        this.f4555h = m4;
        this.f4556i = x1Var2;
        if (x1Var2 != null) {
            this.f4551A = x1Var2.b;
        }
        this.f4557j = a0Var2;
        boolean z7 = true;
        if (x1Var2 == null || a0Var2 == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        F.i("Should not provide both retryPolicy and hedgingPolicy", z3);
        this.k = a0Var2 == null ? false : z7;
        this.f4559p = w1Var;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0029, code lost:
        if (r1 == null) goto L_0x002f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002b, code lost:
        r1.cancel(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002f, code lost:
        r2.f(r4.g.schedule(new P1.e(29, r4, r2), (long) r5.intValue(), java.util.concurrent.TimeUnit.MILLISECONDS));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0046, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(ge.C1057t0 r4, java.lang.Integer r5) {
        /*
            if (r5 != 0) goto L_0x0003
            return
        L_0x0003:
            int r0 = r5.intValue()
            if (r0 >= 0) goto L_0x000d
            r4.m()
            return
        L_0x000d:
            java.lang.Object r0 = r4.l
            monitor-enter(r0)
            Kd.a r1 = r4.z     // Catch:{ all -> 0x0016 }
            if (r1 != 0) goto L_0x0018
            monitor-exit(r0)     // Catch:{ all -> 0x0016 }
            return
        L_0x0016:
            r4 = move-exception
            goto L_0x0047
        L_0x0018:
            r2 = 1
            r1.d = r2     // Catch:{ all -> 0x0016 }
            java.lang.Object r1 = r1.f     // Catch:{ all -> 0x0016 }
            java.util.concurrent.Future r1 = (java.util.concurrent.Future) r1     // Catch:{ all -> 0x0016 }
            Kd.a r2 = new Kd.a     // Catch:{ all -> 0x0016 }
            java.lang.Object r3 = r4.l     // Catch:{ all -> 0x0016 }
            r2.<init>(r3)     // Catch:{ all -> 0x0016 }
            r4.z = r2     // Catch:{ all -> 0x0016 }
            monitor-exit(r0)     // Catch:{ all -> 0x0016 }
            if (r1 == 0) goto L_0x002f
            r0 = 0
            r1.cancel(r0)
        L_0x002f:
            java.util.concurrent.ScheduledExecutorService r0 = r4.g
            P1.e r1 = new P1.e
            r3 = 29
            r1.<init>(r3, r4, r2)
            int r4 = r5.intValue()
            long r4 = (long) r4
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MILLISECONDS
            java.util.concurrent.ScheduledFuture r4 = r0.schedule(r1, r4, r3)
            r2.f(r4)
            return
        L_0x0047:
            monitor-exit(r0)     // Catch:{ all -> 0x0016 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: ge.C1057t0.a(ge.t0, java.lang.Integer):void");
    }

    public final void b(C0981n nVar) {
        i(new C1035j1(2, nVar));
    }

    public final C1032i1 c(v1 v1Var) {
        boolean z3;
        boolean z7;
        Set set;
        List list;
        Future future;
        Future future2;
        synchronized (this.l) {
            try {
                if (this.r.f != null) {
                    return null;
                }
                Collection collection = this.r.f4544c;
                r1 r1Var = this.r;
                if (r1Var.f == null) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                F.t(z3, "Already committed");
                List list2 = r1Var.b;
                v1 v1Var2 = v1Var;
                if (r1Var.f4544c.contains(v1Var2)) {
                    list = null;
                    set = Collections.singleton(v1Var2);
                    z7 = true;
                } else {
                    list = list2;
                    z7 = false;
                    set = Collections.EMPTY_LIST;
                }
                this.r = new r1(list, set, r1Var.d, v1Var2, r1Var.g, z7, r1Var.f4545h, r1Var.e);
                ((AtomicLong) this.m.e).addAndGet(-this.w);
                a aVar = this.y;
                if (aVar != null) {
                    aVar.d = true;
                    this.y = null;
                    future = (Future) aVar.f;
                } else {
                    future = null;
                }
                a aVar2 = this.z;
                if (aVar2 != null) {
                    aVar2.d = true;
                    this.z = null;
                    future2 = (Future) aVar2.f;
                } else {
                    future2 = null;
                }
                C1032i1 i1Var = new C1032i1(this, collection, v1Var, future, future2);
                return i1Var;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: type inference failed for: r3v0, types: [ee.M, java.lang.Object] */
    public final v1 d(int i2, boolean z3) {
        int i7;
        C1058u uVar;
        int i8;
        AtomicInteger atomicInteger = this.u;
        do {
            i7 = atomicInteger.get();
            if (i7 < 0) {
                return null;
            }
        } while (!atomicInteger.compareAndSet(i7, i7 + 1));
        v1 v1Var = new v1(i2);
        n1 n1Var = new n1(new p1(this, v1Var));
        M m4 = this.f4555h;
        ? obj = new Object();
        int i10 = m4.b;
        if (i10 != 0) {
            Object[] objArr = obj.f4275a;
            if (objArr != null) {
                i8 = objArr.length;
            } else {
                i8 = 0;
            }
            int i11 = obj.b;
            int i12 = i8 - (i11 * 2);
            if (i11 == 0 || i12 < i10 * 2) {
                obj.b((i10 * 2) + (i11 * 2));
            }
            System.arraycopy(m4.f4275a, 0, obj.f4275a, obj.b * 2, m4.b * 2);
            obj.b += m4.b;
        }
        if (i2 > 0) {
            obj.c(f4547H, String.valueOf(i2));
        }
        CallOptions callOptions = this.E;
        callOptions.getClass();
        List list = callOptions.f;
        ArrayList arrayList = new ArrayList(list.size() + 1);
        arrayList.addAll(list);
        arrayList.add(n1Var);
        n b = CallOptions.b(callOptions);
        b.f = Collections.unmodifiableList(arrayList);
        CallOptions callOptions2 = new CallOptions(b);
        io.grpc.a[] a7 = Z.a(callOptions2, i2, z3);
        C1059u0 u0Var = this.f4554G;
        MethodDescriptor methodDescriptor = this.D;
        F.n(methodDescriptor, "method");
        C0971d dVar = u0Var.b.y;
        if (u0Var.b.f4407G.get()) {
            uVar = u0Var.b.E;
        } else if (dVar == null) {
            u0Var.b.m.execute(new A(7, (Object) u0Var));
            uVar = u0Var.b.E;
        } else {
            C1058u d2 = Z.d(dVar.j(), Boolean.TRUE.equals(callOptions2.g));
            if (d2 != null) {
                uVar = d2;
            } else {
                uVar = u0Var.b.E;
            }
        }
        C0979l lVar = this.f4553F;
        C0979l a10 = lVar.a();
        try {
            r a11 = uVar.a(methodDescriptor, obj, callOptions2, a7);
            lVar.c(a10);
            v1Var.f4567a = a11;
            return v1Var;
        } catch (Throwable th) {
            lVar.c(a10);
            throw th;
        }
    }

    public final void e(c cVar) {
        r1 r1Var;
        synchronized (this.l) {
            cVar.i(this.q, "closed");
            r1Var = this.r;
        }
        if (r1Var.f != null) {
            c cVar2 = new c(11);
            r1Var.f.f4567a.e(cVar2);
            cVar.i(cVar2, "committed");
            return;
        }
        c cVar3 = new c(11);
        for (v1 v1Var : r1Var.f4544c) {
            c cVar4 = new c(11);
            v1Var.f4567a.e(cVar4);
            ((ArrayList) cVar3.e).add(String.valueOf(cVar4));
        }
        cVar.i(cVar3, "open");
    }

    public final void f(int i2) {
        r1 r1Var = this.r;
        if (r1Var.f4543a) {
            r1Var.f.f4567a.f(i2);
        } else {
            i(new C1041l1(i2, 2));
        }
    }

    public final void flush() {
        r1 r1Var = this.r;
        if (r1Var.f4543a) {
            r1Var.f.f4567a.flush();
        } else {
            i(new C1038k1(0));
        }
    }

    public final void g(int i2) {
        i(new C1041l1(i2, 0));
    }

    public final void h(int i2) {
        i(new C1041l1(i2, 1));
    }

    public final void i(o1 o1Var) {
        Collection<v1> collection;
        synchronized (this.l) {
            try {
                if (!this.r.f4543a) {
                    this.r.b.add(o1Var);
                }
                collection = this.r.f4544c;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        for (v1 a7 : collection) {
            o1Var.a(a7);
        }
    }

    public final boolean isReady() {
        for (v1 v1Var : this.r.f4544c) {
            if (v1Var.f4567a.isReady()) {
                return true;
            }
        }
        return false;
    }

    public final void j() {
        i(new C1038k1(2));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0064, code lost:
        if (r7 != false) goto L_0x0066;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void k(ge.C1056t r7) {
        /*
            r6 = this;
            r6.f4560x = r7
            ge.u0 r7 = r6.f4554G
            ge.F0 r7 = r7.b
            D0.f r7 = r7.f4406F
            java.lang.Object r0 = r7.e
            monitor-enter(r0)
            java.lang.Object r1 = r7.g     // Catch:{ all -> 0x0014 }
            ee.a0 r1 = (ee.a0) r1     // Catch:{ all -> 0x0014 }
            r2 = 0
            if (r1 == 0) goto L_0x0017
            monitor-exit(r0)     // Catch:{ all -> 0x0014 }
            goto L_0x0020
        L_0x0014:
            r6 = move-exception
            goto L_0x0095
        L_0x0017:
            java.lang.Object r7 = r7.f     // Catch:{ all -> 0x0014 }
            java.util.HashSet r7 = (java.util.HashSet) r7     // Catch:{ all -> 0x0014 }
            r7.add(r6)     // Catch:{ all -> 0x0014 }
            monitor-exit(r0)     // Catch:{ all -> 0x0014 }
            r1 = r2
        L_0x0020:
            if (r1 == 0) goto L_0x0026
            r6.v(r1)
            return
        L_0x0026:
            java.lang.Object r7 = r6.l
            monitor-enter(r7)
            ge.r1 r0 = r6.r     // Catch:{ all -> 0x0092 }
            java.util.List r0 = r0.b     // Catch:{ all -> 0x0092 }
            ge.q1 r1 = new ge.q1     // Catch:{ all -> 0x0092 }
            r1.<init>(r6)     // Catch:{ all -> 0x0092 }
            r0.add(r1)     // Catch:{ all -> 0x0092 }
            monitor-exit(r7)     // Catch:{ all -> 0x0092 }
            r7 = 0
            ge.v1 r0 = r6.d(r7, r7)
            if (r0 != 0) goto L_0x003e
            return
        L_0x003e:
            boolean r1 = r6.k
            if (r1 == 0) goto L_0x008e
            java.lang.Object r1 = r6.l
            monitor-enter(r1)
            ge.r1 r3 = r6.r     // Catch:{ all -> 0x0070 }
            ge.r1 r3 = r3.a(r0)     // Catch:{ all -> 0x0070 }
            r6.r = r3     // Catch:{ all -> 0x0070 }
            ge.r1 r3 = r6.r     // Catch:{ all -> 0x0070 }
            boolean r3 = r6.n(r3)     // Catch:{ all -> 0x0070 }
            if (r3 == 0) goto L_0x0072
            ge.w1 r3 = r6.f4559p     // Catch:{ all -> 0x0070 }
            if (r3 == 0) goto L_0x0066
            java.util.concurrent.atomic.AtomicInteger r4 = r3.d     // Catch:{ all -> 0x0070 }
            int r4 = r4.get()     // Catch:{ all -> 0x0070 }
            int r3 = r3.b     // Catch:{ all -> 0x0070 }
            if (r4 <= r3) goto L_0x0064
            r7 = 1
        L_0x0064:
            if (r7 == 0) goto L_0x0072
        L_0x0066:
            Kd.a r2 = new Kd.a     // Catch:{ all -> 0x0070 }
            java.lang.Object r7 = r6.l     // Catch:{ all -> 0x0070 }
            r2.<init>(r7)     // Catch:{ all -> 0x0070 }
            r6.z = r2     // Catch:{ all -> 0x0070 }
            goto L_0x0072
        L_0x0070:
            r6 = move-exception
            goto L_0x008c
        L_0x0072:
            monitor-exit(r1)     // Catch:{ all -> 0x0070 }
            if (r2 == 0) goto L_0x008e
            java.util.concurrent.ScheduledExecutorService r7 = r6.g
            P1.e r1 = new P1.e
            r3 = 29
            r1.<init>(r3, r6, r2)
            ge.a0 r3 = r6.f4557j
            long r3 = r3.b
            java.util.concurrent.TimeUnit r5 = java.util.concurrent.TimeUnit.NANOSECONDS
            java.util.concurrent.ScheduledFuture r7 = r7.schedule(r1, r3, r5)
            r2.f(r7)
            goto L_0x008e
        L_0x008c:
            monitor-exit(r1)     // Catch:{ all -> 0x0070 }
            throw r6
        L_0x008e:
            r6.l(r0)
            return
        L_0x0092:
            r6 = move-exception
            monitor-exit(r7)     // Catch:{ all -> 0x0092 }
            throw r6
        L_0x0095:
            monitor-exit(r0)     // Catch:{ all -> 0x0014 }
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: ge.C1057t0.k(ge.t):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x008b, code lost:
        r0 = r3.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0093, code lost:
        if (r0.hasNext() == false) goto L_0x00b0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0095, code lost:
        r4 = (ge.o1) r0.next();
        r4.a(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00a0, code lost:
        if ((r4 instanceof ge.q1) == false) goto L_0x00a3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00a2, code lost:
        r2 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00a3, code lost:
        r4 = r8.r;
        r5 = r4.f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00a7, code lost:
        if (r5 == null) goto L_0x00ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00a9, code lost:
        if (r5 == r9) goto L_0x00ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00ae, code lost:
        if (r4.g == false) goto L_0x008f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void l(ge.v1 r9) {
        /*
            r8 = this;
            r0 = 0
            r1 = 0
            r2 = r0
            r3 = r1
        L_0x0004:
            java.lang.Object r4 = r8.l
            monitor-enter(r4)
            ge.r1 r5 = r8.r     // Catch:{ all -> 0x0011 }
            ge.v1 r6 = r5.f     // Catch:{ all -> 0x0011 }
            if (r6 == 0) goto L_0x0014
            if (r6 == r9) goto L_0x0014
            monitor-exit(r4)     // Catch:{ all -> 0x0011 }
            goto L_0x0038
        L_0x0011:
            r8 = move-exception
            goto L_0x00b3
        L_0x0014:
            boolean r6 = r5.g     // Catch:{ all -> 0x0011 }
            if (r6 == 0) goto L_0x001a
            monitor-exit(r4)     // Catch:{ all -> 0x0011 }
            goto L_0x0038
        L_0x001a:
            java.util.List r6 = r5.b     // Catch:{ all -> 0x0011 }
            int r6 = r6.size()     // Catch:{ all -> 0x0011 }
            if (r0 != r6) goto L_0x005e
            ge.r1 r0 = r5.e(r9)     // Catch:{ all -> 0x0011 }
            r8.r = r0     // Catch:{ all -> 0x0011 }
            boolean r0 = r8.isReady()     // Catch:{ all -> 0x0011 }
            if (r0 != 0) goto L_0x0030
            monitor-exit(r4)     // Catch:{ all -> 0x0011 }
            return
        L_0x0030:
            B2.A r1 = new B2.A     // Catch:{ all -> 0x0011 }
            r0 = 13
            r1.<init>((int) r0, (java.lang.Object) r8)     // Catch:{ all -> 0x0011 }
            monitor-exit(r4)     // Catch:{ all -> 0x0011 }
        L_0x0038:
            if (r1 == 0) goto L_0x0040
            ee.e0 r8 = r8.f
            r8.execute(r1)
            return
        L_0x0040:
            if (r2 != 0) goto L_0x004d
            ge.r r0 = r9.f4567a
            ge.W0 r1 = new ge.W0
            r2 = 1
            r1.<init>((int) r2, (java.lang.Object) r8, (java.lang.Object) r9)
            r0.k(r1)
        L_0x004d:
            ge.r r0 = r9.f4567a
            ge.r1 r1 = r8.r
            ge.v1 r1 = r1.f
            if (r1 != r9) goto L_0x0058
            ee.a0 r8 = r8.B
            goto L_0x005a
        L_0x0058:
            ee.a0 r8 = f4549J
        L_0x005a:
            r0.v(r8)
            return
        L_0x005e:
            boolean r6 = r9.b     // Catch:{ all -> 0x0011 }
            if (r6 == 0) goto L_0x0064
            monitor-exit(r4)     // Catch:{ all -> 0x0011 }
            return
        L_0x0064:
            int r6 = r0 + 128
            java.util.List r7 = r5.b     // Catch:{ all -> 0x0011 }
            int r7 = r7.size()     // Catch:{ all -> 0x0011 }
            int r6 = java.lang.Math.min(r6, r7)     // Catch:{ all -> 0x0011 }
            if (r3 != 0) goto L_0x007e
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x0011 }
            java.util.List r5 = r5.b     // Catch:{ all -> 0x0011 }
            java.util.List r0 = r5.subList(r0, r6)     // Catch:{ all -> 0x0011 }
            r3.<init>(r0)     // Catch:{ all -> 0x0011 }
            goto L_0x008a
        L_0x007e:
            r3.clear()     // Catch:{ all -> 0x0011 }
            java.util.List r5 = r5.b     // Catch:{ all -> 0x0011 }
            java.util.List r0 = r5.subList(r0, r6)     // Catch:{ all -> 0x0011 }
            r3.addAll(r0)     // Catch:{ all -> 0x0011 }
        L_0x008a:
            monitor-exit(r4)     // Catch:{ all -> 0x0011 }
            java.util.Iterator r0 = r3.iterator()
        L_0x008f:
            boolean r4 = r0.hasNext()
            if (r4 == 0) goto L_0x00b0
            java.lang.Object r4 = r0.next()
            ge.o1 r4 = (ge.o1) r4
            r4.a(r9)
            boolean r4 = r4 instanceof ge.q1
            if (r4 == 0) goto L_0x00a3
            r2 = 1
        L_0x00a3:
            ge.r1 r4 = r8.r
            ge.v1 r5 = r4.f
            if (r5 == 0) goto L_0x00ac
            if (r5 == r9) goto L_0x00ac
            goto L_0x00b0
        L_0x00ac:
            boolean r4 = r4.g
            if (r4 == 0) goto L_0x008f
        L_0x00b0:
            r0 = r6
            goto L_0x0004
        L_0x00b3:
            monitor-exit(r4)     // Catch:{ all -> 0x0011 }
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: ge.C1057t0.l(ge.v1):void");
    }

    public final void m() {
        Future future;
        synchronized (this.l) {
            try {
                a aVar = this.z;
                future = null;
                if (aVar != null) {
                    aVar.d = true;
                    this.z = null;
                    future = (Future) aVar.f;
                }
                r1 r1Var = this.r;
                if (!r1Var.f4545h) {
                    r1Var = new r1(r1Var.b, r1Var.f4544c, r1Var.d, r1Var.f, r1Var.g, r1Var.f4543a, true, r1Var.e);
                }
                this.r = r1Var;
            } catch (Throwable th) {
                while (true) {
                    throw th;
                }
            }
        }
        if (future != null) {
            future.cancel(false);
        }
    }

    public final boolean n(r1 r1Var) {
        if (r1Var.f != null || r1Var.e >= this.f4557j.f4495a || r1Var.f4545h) {
            return false;
        }
        return true;
    }

    public final void o(a0 a0Var, C1054s sVar, M m4) {
        a0 a0Var2 = a0Var;
        C1054s sVar2 = sVar;
        M m8 = m4;
        this.v = new l(a0Var2, sVar2, m8, false, 17);
        if (this.u.addAndGet(Integer.MIN_VALUE) == Integer.MIN_VALUE) {
            this.f.execute(new J(this, a0Var2, sVar2, m8, 1));
        }
    }

    public final void p(Object obj) {
        r1 r1Var = this.r;
        if (r1Var.f4543a) {
            r1Var.f.f4567a.x(this.d.c(obj));
        } else {
            i(new C1044m1(this, obj));
        }
    }

    public final void t() {
        i(new C1038k1(1));
    }

    public final void u(Deadline deadline) {
        i(new C1035j1(1, deadline));
    }

    /* JADX WARNING: type inference failed for: r1v4, types: [ee.M, java.lang.Object] */
    public final void v(a0 a0Var) {
        v1 v1Var;
        v1 v1Var2 = new v1(0);
        v1Var2.f4567a = new Q0(0);
        C1032i1 c5 = c(v1Var2);
        if (c5 != null) {
            synchronized (this.l) {
                try {
                    this.r = this.r.e(v1Var2);
                } catch (Throwable th) {
                    while (true) {
                        throw th;
                    }
                }
            }
            c5.run();
            o(a0Var, C1054s.PROCESSED, new Object());
            return;
        }
        synchronized (this.l) {
            try {
                if (this.r.f4544c.contains(this.r.f)) {
                    v1Var = this.r.f;
                } else {
                    this.B = a0Var;
                    v1Var = null;
                }
                r1 r1Var = this.r;
                this.r = new r1(r1Var.b, r1Var.f4544c, r1Var.d, r1Var.f, true, r1Var.f4543a, r1Var.f4545h, r1Var.e);
            } catch (Throwable th2) {
                while (true) {
                    throw th2;
                }
            }
        }
        if (v1Var != null) {
            v1Var.f4567a.v(a0Var);
        }
    }

    public final void w(C0973f fVar) {
        i(new C1035j1(0, fVar));
    }

    public final void x(C1076a aVar) {
        throw new IllegalStateException("RetriableStream.writeMessage() should not be called directly");
    }
}
