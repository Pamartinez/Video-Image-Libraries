package ge;

import B1.b;
import D0.e;
import D0.f;
import E2.k;
import E2.q;
import E2.r;
import G0.c;
import ee.C0970c;
import ee.C0971d;
import ee.C0974g;
import ee.C0981n;
import ee.C0984q;
import ee.C0987u;
import ee.C0989w;
import ee.C0990x;
import ee.F;
import ee.P;
import ee.U;
import ee.Z;
import ee.a0;
import ee.e0;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.Deadline;
import io.grpc.MethodDescriptor;
import io.grpc.binder.a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class F0 extends F implements C0989w {

    /* renamed from: c0  reason: collision with root package name */
    public static final Logger f4398c0 = Logger.getLogger(F0.class.getName());
    public static final Pattern d0 = Pattern.compile("[a-zA-Z][a-zA-Z0-9+.-]*:/.*");

    /* renamed from: e0  reason: collision with root package name */
    public static final a0 f4399e0;

    /* renamed from: f0  reason: collision with root package name */
    public static final a0 f4400f0;

    /* renamed from: g0  reason: collision with root package name */
    public static final a0 f4401g0;
    public static final N0 h0 = new N0((L0) null, new HashMap(), new HashMap(), (w1) null, (Object) null, (Map) null);

    /* renamed from: i0  reason: collision with root package name */
    public static final C1050p0 f4402i0 = new Object();

    /* renamed from: j0  reason: collision with root package name */
    public static final C1000A f4403j0 = new C1000A(1);

    /* renamed from: A  reason: collision with root package name */
    public final HashSet f4404A = new HashSet(16, 0.75f);
    public LinkedHashSet B;

    /* renamed from: C  reason: collision with root package name */
    public final Object f4405C = new Object();
    public final HashSet D = new HashSet(1, 0.75f);
    public final G E;

    /* renamed from: F  reason: collision with root package name */
    public final f f4406F = new f(this);

    /* renamed from: G  reason: collision with root package name */
    public final AtomicBoolean f4407G = new AtomicBoolean(false);

    /* renamed from: H  reason: collision with root package name */
    public boolean f4408H;

    /* renamed from: I  reason: collision with root package name */
    public volatile boolean f4409I;

    /* renamed from: J  reason: collision with root package name */
    public final CountDownLatch f4410J = new CountDownLatch(1);

    /* renamed from: K  reason: collision with root package name */
    public final Q0 f4411K;
    public final f L;

    /* renamed from: M  reason: collision with root package name */
    public final C1036k f4412M;

    /* renamed from: N  reason: collision with root package name */
    public final C1027h f4413N;

    /* renamed from: O  reason: collision with root package name */
    public final C0987u f4414O;

    /* renamed from: P  reason: collision with root package name */
    public final B0 f4415P;
    public C0 Q = C0.NO_RESOLUTION;
    public N0 R = h0;
    public boolean S = false;
    public final boolean T;
    public final b U = new b();
    public final long V;

    /* renamed from: W  reason: collision with root package name */
    public final long f4416W;

    /* renamed from: X  reason: collision with root package name */
    public final boolean f4417X;
    public final Z Y = Deadline.g;
    public final C1010b0 Z;

    /* renamed from: a  reason: collision with root package name */
    public final C0990x f4418a;
    public final C1059u0 a0;
    public final String b;
    public final C1026g1 b0;

    /* renamed from: c  reason: collision with root package name */
    public final U f4419c;
    public final P d;
    public final e e;
    public final C1021f f;
    public final D0 g;

    /* renamed from: h  reason: collision with root package name */
    public final Executor f4420h;

    /* renamed from: i  reason: collision with root package name */
    public final c f4421i;

    /* renamed from: j  reason: collision with root package name */
    public final C1063w0 f4422j;
    public final C1063w0 k;
    public final Q0 l;
    public final e0 m;
    public final C0981n n;

    /* renamed from: o  reason: collision with root package name */
    public final C0974g f4423o;

    /* renamed from: p  reason: collision with root package name */
    public final r f4424p;
    public final long q;
    public final C1066y r;
    public final Q0 s;
    public final Channel t;
    public final ArrayList u;
    public A1 v;
    public boolean w;

    /* renamed from: x  reason: collision with root package name */
    public e f4425x;
    public volatile C0971d y;
    public boolean z;

    /* JADX WARNING: type inference failed for: r0v7, types: [java.lang.Object, ge.p0] */
    static {
        a0 a0Var = a0.f4289o;
        f4399e0 = a0Var.g("Channel shutdownNow invoked");
        f4400f0 = a0Var.g("Channel shutdown invoked");
        f4401g0 = a0Var.g("Subchannel shutdown invoked");
    }

    /* JADX WARNING: type inference failed for: r5v1, types: [ge.y, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r9v2, types: [ee.e] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public F0(ge.I0 r18, io.grpc.binder.c r19, ge.Q0 r20, G0.c r21, E2.r r22, java.util.ArrayList r23) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r22
            ge.Q0 r4 = ge.Q0.f
            r0.<init>()
            ee.e0 r7 = new ee.e0
            ge.s0 r5 = new ge.s0
            r5.<init>(r0)
            r7.<init>(r5)
            r0.m = r7
            ge.y r5 = new ge.y
            r5.<init>()
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            r5.f4574a = r6
            ee.h r6 = ee.C0975h.IDLE
            r5.b = r6
            r0.r = r5
            java.util.HashSet r5 = new java.util.HashSet
            r6 = 16
            r8 = 1061158912(0x3f400000, float:0.75)
            r5.<init>(r6, r8)
            r0.f4404A = r5
            java.lang.Object r5 = new java.lang.Object
            r5.<init>()
            r0.f4405C = r5
            java.util.HashSet r5 = new java.util.HashSet
            r12 = 1
            r5.<init>(r12, r8)
            r0.D = r5
            D0.f r5 = new D0.f
            r5.<init>((ge.F0) r0)
            r0.f4406F = r5
            java.util.concurrent.atomic.AtomicBoolean r5 = new java.util.concurrent.atomic.AtomicBoolean
            r13 = 0
            r5.<init>(r13)
            r0.f4407G = r5
            java.util.concurrent.CountDownLatch r5 = new java.util.concurrent.CountDownLatch
            r5.<init>(r12)
            r0.f4410J = r5
            ge.C0 r5 = ge.C0.NO_RESOLUTION
            r0.Q = r5
            ge.N0 r5 = h0
            r0.R = r5
            r0.S = r13
            B1.b r5 = new B1.b
            r5.<init>()
            r0.U = r5
            ee.Z r5 = io.grpc.Deadline.g
            r0.Y = r5
            G0.e r14 = new G0.e
            r14.<init>((java.lang.Object) r0)
            ge.b0 r5 = new ge.b0
            r6 = 1
            r5.<init>(r0, r6)
            r0.Z = r5
            ge.u0 r5 = new ge.u0
            r5.<init>(r0)
            r0.a0 = r5
            java.lang.String r15 = r1.f4442i
            java.lang.String r5 = "target"
            He.F.n(r15, r5)
            r0.b = r15
            ee.x r5 = new ee.x
            java.util.concurrent.atomic.AtomicLong r6 = ee.C0990x.d
            long r8 = r6.incrementAndGet()
            java.lang.String r6 = "Channel"
            r5.<init>(r6, r15, r8)
            r0.f4418a = r5
            r0.l = r4
            G0.c r6 = r1.d
            java.lang.String r8 = "executorPool"
            He.F.n(r6, r8)
            r0.f4421i = r6
            java.lang.Object r6 = r6.e
            ge.K1 r6 = (ge.K1) r6
            java.lang.Object r6 = ge.L1.a(r6)
            java.util.concurrent.Executor r6 = (java.util.concurrent.Executor) r6
            java.lang.String r8 = "executor"
            He.F.n(r6, r8)
            r0.f4420h = r6
            ge.w0 r11 = new ge.w0
            G0.c r8 = r1.e
            java.lang.String r9 = "offloadExecutorPool"
            He.F.n(r8, r9)
            r11.<init>(r8)
            r0.k = r11
            ge.f r8 = new ge.f
            r8.<init>(r2, r11)
            r0.f = r8
            ge.D0 r9 = new ge.D0
            java.util.concurrent.ScheduledExecutorService r2 = r2.l
            r9.<init>(r2)
            r0.g = r9
            ge.k r2 = new ge.k
            java.util.concurrent.TimeUnit r10 = java.util.concurrent.TimeUnit.MILLISECONDS
            long r12 = java.lang.System.currentTimeMillis()
            long r12 = r10.toNanos(r12)
            java.lang.String r10 = "Channel for '"
            r16 = r6
            java.lang.String r6 = "'"
            java.lang.String r6 = i.C0212a.m(r10, r15, r6)
            r2.<init>(r5, r12, r6)
            r0.f4412M = r2
            ge.h r10 = new ge.h
            r10.<init>(r2, r4)
            r0.f4413N = r10
            ge.d1 r6 = ge.Z.f4492i
            boolean r2 = r1.r
            r0.f4417X = r2
            D0.e r4 = new D0.e
            java.lang.String r5 = r1.f4443j
            r4.<init>((java.lang.String) r5)
            r0.e = r4
            ee.U r12 = r1.g
            r0.f4419c = r12
            r5 = r8
            ge.B1 r8 = new ge.B1
            int r13 = r1.n
            r19 = r5
            int r5 = r1.f4444o
            r8.<init>(r2, r13, r5, r4)
            ge.Q0 r2 = r1.f4440A
            r2.getClass()
            r6.getClass()
            ee.P r5 = new ee.P
            r4 = r19
            r2 = r16
            r5.<init>(r6, r7, r8, r9, r10, r11)
            r0.d = r5
            io.grpc.binder.c r6 = r4.d
            r6.getClass()
            java.lang.Class<io.grpc.binder.a> r6 = io.grpc.binder.a.class
            java.util.Set r6 = java.util.Collections.singleton(r6)
            ge.A1 r5 = l(r15, r12, r5, r6)
            r0.v = r5
            ge.w0 r5 = new ge.w0
            r6 = r21
            r5.<init>(r6)
            r0.f4422j = r5
            ge.G r5 = new ge.G
            r5.<init>(r2, r7)
            r0.E = r5
            r5.e(r14)
            r2 = r20
            r0.s = r2
            boolean r2 = r1.t
            r0.T = r2
            ge.B0 r5 = new ge.B0
            ge.A1 r6 = r0.v
            java.lang.String r6 = r6.c()
            r5.<init>(r0, r6)
            r0.f4415P = r5
            java.util.Iterator r6 = r23.iterator()
        L_0x0167:
            boolean r8 = r6.hasNext()
            if (r8 == 0) goto L_0x017a
            java.lang.Object r8 = r6.next()
            io.grpc.ClientInterceptor r8 = (io.grpc.ClientInterceptor) r8
            ee.e r9 = new ee.e
            r9.<init>(r5, r8)
            r5 = r9
            goto L_0x0167
        L_0x017a:
            r0.t = r5
            java.util.ArrayList r5 = new java.util.ArrayList
            java.util.ArrayList r6 = r1.f4441h
            r5.<init>(r6)
            r0.u = r5
            java.lang.String r5 = "stopwatchSupplier"
            He.F.n(r3, r5)
            r0.f4424p = r3
            long r5 = r1.m
            r8 = -1
            int r8 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r8 != 0) goto L_0x0197
            r0.q = r5
            goto L_0x01a9
        L_0x0197:
            long r8 = ge.I0.D
            int r8 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r8 < 0) goto L_0x019f
            r13 = 1
            goto L_0x01a0
        L_0x019f:
            r13 = 0
        L_0x01a0:
            java.lang.String r8 = "invalid idleTimeoutMillis %s"
            He.F.k(r13, r8, r5)
            long r5 = r1.m
            r0.q = r5
        L_0x01a9:
            ge.g1 r5 = new ge.g1
            ge.q0 r6 = new ge.q0
            r8 = 2
            r6.<init>(r0, r8)
            io.grpc.binder.c r4 = r4.d
            java.util.concurrent.ScheduledExecutorService r4 = r4.l
            java.lang.Object r3 = r3.get()
            E2.q r3 = (E2.q) r3
            r5.<init>(r6, r7, r4, r3)
            r0.b0 = r5
            ee.n r3 = r1.k
            java.lang.String r4 = "decompressorRegistry"
            He.F.n(r3, r4)
            r0.n = r3
            ee.g r3 = r1.l
            java.lang.String r4 = "compressorRegistry"
            He.F.n(r3, r4)
            r0.f4423o = r3
            long r3 = r1.f4445p
            r0.f4416W = r3
            long r3 = r1.q
            r0.V = r3
            ge.Q0 r3 = new ge.Q0
            r4 = 9
            r3.<init>(r4)
            r0.f4411K = r3
            D0.f r3 = new D0.f
            r4 = 12
            r3.<init>((int) r4)
            r0.L = r3
            ee.u r1 = r1.s
            r1.getClass()
            r0.f4414O = r1
            java.util.concurrent.ConcurrentSkipListMap r1 = r1.f4311a
            ee.x r3 = r0.c()
            long r3 = r3.f4315c
            java.lang.Long r3 = java.lang.Long.valueOf(r3)
            java.lang.Object r1 = r1.put(r3, r0)
            ee.w r1 = (ee.C0989w) r1
            if (r2 != 0) goto L_0x020a
            r1 = 1
            r0.S = r1
        L_0x020a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: ge.F0.<init>(ge.I0, io.grpc.binder.c, ge.Q0, G0.c, E2.r, java.util.ArrayList):void");
    }

    public static void i(F0 f02) {
        if (!f02.f4409I && f02.f4407G.get() && f02.f4404A.isEmpty() && f02.D.isEmpty()) {
            f02.f4413N.b(C0970c.INFO, "Terminated");
            C0989w wVar = (C0989w) f02.f4414O.f4311a.remove(Long.valueOf(f02.c().f4315c));
            f02.f4421i.B(f02.f4420h);
            C1063w0 w0Var = f02.f4422j;
            synchronized (w0Var) {
                Executor executor = w0Var.e;
                if (executor != null) {
                    w0Var.d.B(executor);
                    w0Var.e = null;
                }
            }
            C1063w0 w0Var2 = f02.k;
            synchronized (w0Var2) {
                Executor executor2 = w0Var2.e;
                if (executor2 != null) {
                    w0Var2.d.B(executor2);
                    w0Var2.e = null;
                }
            }
            f02.f.close();
            f02.f4409I = true;
            f02.f4410J.countDown();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: ee.T} */
    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    /* JADX WARNING: Multi-variable type inference failed */
    public static ge.A1 l(java.lang.String r7, ee.U r8, ee.P r9, java.util.Collection r10) {
        /*
            java.lang.String r0 = "/"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r2 = 0
            java.net.URI r3 = new java.net.URI     // Catch:{ URISyntaxException -> 0x000e }
            r3.<init>(r7)     // Catch:{ URISyntaxException -> 0x000e }
            goto L_0x0017
        L_0x000e:
            r3 = move-exception
            java.lang.String r3 = r3.getMessage()
            r1.append(r3)
            r3 = r2
        L_0x0017:
            if (r3 == 0) goto L_0x0037
            java.lang.String r4 = r3.getScheme()
            if (r4 != 0) goto L_0x0023
            r8.getClass()
            goto L_0x0037
        L_0x0023:
            monitor-enter(r8)
            F2.Y r5 = r8.f4283c     // Catch:{ all -> 0x0034 }
            monitor-exit(r8)
            java.util.Locale r6 = java.util.Locale.US
            java.lang.String r4 = r4.toLowerCase(r6)
            java.lang.Object r4 = r5.get(r4)
            ee.T r4 = (ee.T) r4
            goto L_0x0038
        L_0x0034:
            r7 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x0034 }
            throw r7
        L_0x0037:
            r4 = r2
        L_0x0038:
            if (r4 != 0) goto L_0x0084
            java.util.regex.Pattern r5 = d0
            java.util.regex.Matcher r5 = r5.matcher(r7)
            boolean r5 = r5.matches()
            if (r5 != 0) goto L_0x0084
            java.net.URI r3 = new java.net.URI     // Catch:{ URISyntaxException -> 0x007d }
            monitor-enter(r8)     // Catch:{ URISyntaxException -> 0x007d }
            java.lang.String r4 = r8.f4282a     // Catch:{ all -> 0x007a }
            monitor-exit(r8)     // Catch:{ URISyntaxException -> 0x007d }
            java.lang.String r5 = ""
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ URISyntaxException -> 0x007d }
            r6.<init>(r0)     // Catch:{ URISyntaxException -> 0x007d }
            r6.append(r7)     // Catch:{ URISyntaxException -> 0x007d }
            java.lang.String r0 = r6.toString()     // Catch:{ URISyntaxException -> 0x007d }
            r3.<init>(r4, r5, r0, r2)     // Catch:{ URISyntaxException -> 0x007d }
            java.lang.String r0 = r3.getScheme()
            if (r0 != 0) goto L_0x0064
            goto L_0x0075
        L_0x0064:
            monitor-enter(r8)
            F2.Y r2 = r8.f4283c     // Catch:{ all -> 0x0077 }
            monitor-exit(r8)
            java.util.Locale r8 = java.util.Locale.US
            java.lang.String r8 = r0.toLowerCase(r8)
            java.lang.Object r8 = r2.get(r8)
            r2 = r8
            ee.T r2 = (ee.T) r2
        L_0x0075:
            r4 = r2
            goto L_0x0084
        L_0x0077:
            r7 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x0077 }
            throw r7
        L_0x007a:
            r7 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x007a }
            throw r7     // Catch:{ URISyntaxException -> 0x007d }
        L_0x007d:
            r7 = move-exception
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            r8.<init>(r7)
            throw r8
        L_0x0084:
            if (r4 != 0) goto L_0x00ae
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            int r9 = r1.length()
            if (r9 <= 0) goto L_0x00a2
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = " ("
            r9.<init>(r10)
            r9.append(r1)
            java.lang.String r10 = ")"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            goto L_0x00a4
        L_0x00a2:
            java.lang.String r9 = ""
        L_0x00a4:
            java.lang.String r10 = "Could not find a NameResolverProvider for "
            java.lang.String r7 = i.C0212a.m(r10, r7, r9)
            r8.<init>(r7)
            throw r8
        L_0x00ae:
            if (r10 == 0) goto L_0x00cf
            java.util.Set r8 = r4.b()
            boolean r8 = r10.containsAll(r8)
            if (r8 == 0) goto L_0x00bb
            goto L_0x00cf
        L_0x00bb:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = r3.getScheme()
            java.lang.String r10 = "Address types of NameResolver '"
            java.lang.String r0 = "' for '"
            java.lang.String r1 = "' not supported by transport"
            java.lang.String r7 = N2.j.d(r10, r9, r0, r7, r1)
            r8.<init>(r7)
            throw r8
        L_0x00cf:
            ee.q r8 = r4.c(r3, r9)
            if (r8 == 0) goto L_0x00f8
            ge.A1 r7 = new ge.A1
            ge.d r10 = new ge.d
            ge.Q0 r0 = new ge.Q0
            r1 = 4
            r0.<init>(r1)
            java.lang.Object r1 = r9.f
            ge.D0 r1 = (ge.D0) r1
            if (r1 == 0) goto L_0x00f0
            java.lang.Object r9 = r9.d
            ee.e0 r9 = (ee.e0) r9
            r10.<init>(r0, r1, r9)
            r7.<init>(r8, r10, r9)
            return r7
        L_0x00f0:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "ScheduledExecutorService not set in Builder"
            r7.<init>(r8)
            throw r7
        L_0x00f8:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            int r9 = r1.length()
            if (r9 <= 0) goto L_0x0114
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = " ("
            r9.<init>(r10)
            r9.append(r1)
            java.lang.String r10 = ")"
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            goto L_0x0116
        L_0x0114:
            java.lang.String r9 = ""
        L_0x0116:
            java.lang.String r10 = "cannot create a NameResolver for "
            java.lang.String r7 = i.C0212a.m(r10, r7, r9)
            r8.<init>(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: ge.F0.l(java.lang.String, ee.U, ee.P, java.util.Collection):ge.A1");
    }

    public final C0990x c() {
        return this.f4418a;
    }

    public final String f() {
        return this.t.f();
    }

    public final C0984q g(MethodDescriptor methodDescriptor, CallOptions callOptions) {
        return this.t.g(methodDescriptor, callOptions);
    }

    public final /* bridge */ /* synthetic */ F h() {
        n();
        return this;
    }

    public final void j(boolean z3) {
        ScheduledFuture scheduledFuture;
        C1026g1 g1Var = this.b0;
        g1Var.f = false;
        if (z3 && (scheduledFuture = g1Var.g) != null) {
            scheduledFuture.cancel(false);
            g1Var.g = null;
        }
    }

    public final void k() {
        this.m.d();
        if (!this.f4407G.get() && !this.z) {
            if (!((Set) this.Z.e).isEmpty()) {
                j(false);
            } else {
                m();
            }
            if (this.f4425x == null) {
                this.f4413N.b(C0970c.INFO, "Exiting idle mode");
                e eVar = new e(this);
                e eVar2 = this.e;
                eVar2.getClass();
                eVar.e = new f(eVar2, eVar);
                this.f4425x = eVar;
                this.v.k(new C1065x0(this, eVar, this.v));
                this.w = true;
            }
        }
    }

    public final void m() {
        long j2;
        long j3 = this.q;
        if (j3 != -1) {
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            C1026g1 g1Var = this.b0;
            g1Var.getClass();
            long nanos = timeUnit.toNanos(j3);
            q qVar = g1Var.d;
            TimeUnit timeUnit2 = TimeUnit.NANOSECONDS;
            qVar.getClass();
            if (qVar.f175a) {
                j2 = System.nanoTime() - qVar.b;
            } else {
                j2 = 0;
            }
            long convert = timeUnit2.convert(j2, timeUnit2) + nanos;
            g1Var.f = true;
            if (convert - g1Var.e < 0 || g1Var.g == null) {
                ScheduledFuture scheduledFuture = g1Var.g;
                if (scheduledFuture != null) {
                    scheduledFuture.cancel(false);
                }
                g1Var.g = g1Var.f4512a.schedule(new C1023f1(g1Var, 1), nanos, timeUnit2);
            }
            g1Var.e = convert;
        }
    }

    public final void n() {
        this.f4413N.b(C0970c.DEBUG, "shutdown() called");
        if (this.f4407G.compareAndSet(false, true)) {
            C1052q0 q0Var = new C1052q0(this, 1);
            e0 e0Var = this.m;
            e0Var.execute(q0Var);
            B0 b02 = this.f4415P;
            b02.d.m.execute(new C1069z0(b02, 0));
            e0Var.execute(new C1052q0(this, 0));
        }
    }

    public final void o(boolean z3) {
        boolean z7;
        this.m.d();
        if (z3) {
            He.F.t(this.w, "nameResolver is not started");
            if (this.f4425x != null) {
                z7 = true;
            } else {
                z7 = false;
            }
            He.F.t(z7, "lbHelper is null");
        }
        A1 a12 = this.v;
        if (a12 != null) {
            a12.j();
            this.w = false;
            if (z3) {
                String str = this.b;
                U u3 = this.f4419c;
                P p6 = this.d;
                this.f.d.getClass();
                this.v = l(str, u3, p6, Collections.singleton(a.class));
            } else {
                this.v = null;
            }
        }
        e eVar = this.f4425x;
        if (eVar != null) {
            f fVar = (f) eVar.e;
            ((C0984q) fVar.f).j();
            fVar.f = null;
            this.f4425x = null;
        }
        this.y = null;
    }

    public final String toString() {
        k V5 = B1.a.V(this);
        V5.b("logId", this.f4418a.f4315c);
        V5.a(this.b, "target");
        return V5.toString();
    }
}
