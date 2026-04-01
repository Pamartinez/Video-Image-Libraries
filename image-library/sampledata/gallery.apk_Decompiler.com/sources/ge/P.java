package ge;

import A0.l;
import D0.e;
import E2.q;
import E2.v;
import He.F;
import L1.d;
import com.google.gson.stream.JsonReader;
import com.samsung.android.sdk.pen.ocr.SpenRecogConfig;
import com.samsung.android.sum.core.types.NumericEnum;
import ee.C0971d;
import ee.C0982o;
import ee.C0984q;
import ee.E;
import ee.Q;
import ee.V;
import ee.a0;
import ee.e0;
import java.io.IOException;
import java.io.StringReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class P extends C0984q {

    /* renamed from: A  reason: collision with root package name */
    public static String f4464A;
    public static final Logger v;
    public static final Set w = Collections.unmodifiableSet(new HashSet(Arrays.asList(new String[]{"clientLanguage", "percentage", "clientHostname", "serviceConfig"})));

    /* renamed from: x  reason: collision with root package name */
    public static final boolean f4465x;
    public static final boolean y;
    public static final boolean z;
    public final V d;
    public final Random e = new Random();
    public volatile M f = M.INSTANCE;
    public final AtomicReference g = new AtomicReference();

    /* renamed from: h  reason: collision with root package name */
    public final String f4466h;

    /* renamed from: i  reason: collision with root package name */
    public final String f4467i;

    /* renamed from: j  reason: collision with root package name */
    public final int f4468j;
    public final K1 k;
    public final long l;
    public final e0 m;
    public final q n;

    /* renamed from: o  reason: collision with root package name */
    public boolean f4469o;

    /* renamed from: p  reason: collision with root package name */
    public boolean f4470p;
    public Executor q;
    public final boolean r;
    public final B1 s;
    public boolean t;
    public C0971d u;

    static {
        Class<P> cls = P.class;
        Logger logger = Logger.getLogger(cls.getName());
        v = logger;
        String property = System.getProperty("io.grpc.internal.DnsNameResolverProvider.enable_jndi", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_TRUE);
        String property2 = System.getProperty("io.grpc.internal.DnsNameResolverProvider.enable_jndi_localhost", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE);
        String property3 = System.getProperty("io.grpc.internal.DnsNameResolverProvider.enable_service_config", SpenRecogConfig.OCR_RECOGNIZER_CONFIGURATION_VAL_FALSE);
        f4465x = Boolean.parseBoolean(property);
        y = Boolean.parseBoolean(property2);
        z = Boolean.parseBoolean(property3);
        try {
            try {
                try {
                    if (Class.forName("ge.j0", true, cls.getClassLoader()).asSubclass(O.class).getConstructor((Class[]) null).newInstance((Object[]) null) == null) {
                        throw null;
                    }
                    throw new ClassCastException();
                } catch (Exception e7) {
                    logger.log(Level.FINE, "Can't construct JndiResourceResolverFactory, skipping.", e7);
                }
            } catch (Exception e8) {
                logger.log(Level.FINE, "Can't find JndiResourceResolverFactory ctor, skipping.", e8);
            }
        } catch (ClassNotFoundException e9) {
            logger.log(Level.FINE, "Unable to find JndiResourceResolverFactory, skipping.", e9);
        } catch (ClassCastException e10) {
            logger.log(Level.FINE, "Unable to cast JndiResourceResolverFactory, skipping.", e10);
        }
    }

    public P(String str, ee.P p6, K1 k12, q qVar, boolean z3) {
        boolean z7;
        F.n(p6, "args");
        this.k = k12;
        F.n(str, "name");
        URI create = URI.create("//".concat(str));
        boolean z9 = false;
        if (create.getHost() != null) {
            z7 = true;
        } else {
            z7 = false;
        }
        F.h("Invalid DNS name: %s", str, z7);
        String authority = create.getAuthority();
        if (authority != null) {
            this.f4466h = authority;
            this.f4467i = create.getHost();
            if (create.getPort() == -1) {
                this.f4468j = p6.b;
            } else {
                this.f4468j = create.getPort();
            }
            V v6 = (V) p6.f4277c;
            F.n(v6, "proxyDetector");
            this.d = v6;
            long j2 = 0;
            if (!z3) {
                String property = System.getProperty("networkaddress.cache.ttl");
                long j3 = 30;
                if (property != null) {
                    try {
                        j3 = Long.parseLong(property);
                    } catch (NumberFormatException unused) {
                        v.log(Level.WARNING, "Property({0}) valid is not valid number format({1}), fall back to default({2})", new Object[]{"networkaddress.cache.ttl", property, 30L});
                    }
                }
                if (j3 > 0) {
                    j2 = TimeUnit.SECONDS.toNanos(j3);
                } else {
                    j2 = j3;
                }
            }
            this.l = j2;
            this.n = qVar;
            e0 e0Var = (e0) p6.d;
            F.n(e0Var, "syncContext");
            this.m = e0Var;
            C1063w0 w0Var = (C1063w0) p6.f4278h;
            this.q = w0Var;
            this.r = w0Var == null ? true : z9;
            B1 b1 = (B1) p6.e;
            F.n(b1, "serviceConfigParser");
            this.s = b1;
            return;
        }
        throw new NullPointerException(d.r("nameUri (%s) doesn't have an authority", create));
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x009a A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x009b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Map n(java.util.Map r5, java.util.Random r6, java.lang.String r7) {
        /*
            java.util.Set r0 = r5.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x0008:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0024
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.util.Set r2 = w
            java.lang.Object r3 = r1.getKey()
            boolean r2 = r2.contains(r3)
            java.lang.String r3 = "Bad key: %s"
            L2.a.G(r3, r1, r2)
            goto L_0x0008
        L_0x0024:
            java.lang.String r0 = "clientLanguage"
            java.util.List r0 = ge.C1043m0.d(r0, r5)
            if (r0 == 0) goto L_0x004a
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x004a
            java.util.Iterator r0 = r0.iterator()
        L_0x0036:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0090
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r2 = "java"
            boolean r1 = r2.equalsIgnoreCase(r1)
            if (r1 == 0) goto L_0x0036
        L_0x004a:
            java.lang.String r0 = "percentage"
            java.lang.Double r0 = ge.C1043m0.e(r0, r5)
            if (r0 == 0) goto L_0x006b
            int r1 = r0.intValue()
            r2 = 100
            if (r1 < 0) goto L_0x005e
            if (r1 > r2) goto L_0x005e
            r3 = 1
            goto L_0x005f
        L_0x005e:
            r3 = 0
        L_0x005f:
            java.lang.String r4 = "Bad percentage: %s"
            L2.a.G(r4, r0, r3)
            int r6 = r6.nextInt(r2)
            if (r6 < r1) goto L_0x006b
            goto L_0x0090
        L_0x006b:
            java.lang.String r6 = "clientHostname"
            java.util.List r6 = ge.C1043m0.d(r6, r5)
            if (r6 == 0) goto L_0x0092
            boolean r0 = r6.isEmpty()
            if (r0 != 0) goto L_0x0092
            java.util.Iterator r6 = r6.iterator()
        L_0x007d:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L_0x0090
            java.lang.Object r0 = r6.next()
            java.lang.String r0 = (java.lang.String) r0
            boolean r0 = r0.equals(r7)
            if (r0 == 0) goto L_0x007d
            goto L_0x0092
        L_0x0090:
            r5 = 0
            return r5
        L_0x0092:
            java.lang.String r6 = "serviceConfig"
            java.util.Map r7 = ge.C1043m0.g(r6, r5)
            if (r7 == 0) goto L_0x009b
            return r7
        L_0x009b:
            Dd.a r7 = new Dd.a
            java.lang.String r0 = "key '%s' missing in '%s'"
            java.lang.Object[] r5 = new java.lang.Object[]{r5, r6}
            java.lang.String r5 = java.lang.String.format(r0, r5)
            r7.<init>(r5)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: ge.P.n(java.util.Map, java.util.Random, java.lang.String):java.util.Map");
    }

    public static ArrayList o() {
        List<String> list = Collections.EMPTY_LIST;
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            if (!str.startsWith("grpc_config=")) {
                v.log(Level.FINE, "Ignoring non service config {0}", new Object[]{str});
            } else {
                String substring = str.substring(12);
                Logger logger = C1040l0.f4531a;
                JsonReader jsonReader = new JsonReader(new StringReader(substring));
                try {
                    Object a7 = C1040l0.a(jsonReader);
                    if (a7 instanceof List) {
                        List list2 = (List) a7;
                        C1043m0.a(list2);
                        arrayList.addAll(list2);
                    } else {
                        throw new ClassCastException("wrong type " + a7);
                    }
                } finally {
                    try {
                        jsonReader.close();
                    } catch (IOException e7) {
                        logger.log(Level.WARNING, "Failed to close", e7);
                    }
                }
            }
        }
        return arrayList;
    }

    public final String c() {
        return this.f4466h;
    }

    public final void g() {
        boolean z3;
        if (this.u != null) {
            z3 = true;
        } else {
            z3 = false;
        }
        F.t(z3, "not started");
        p();
    }

    public final void j() {
        if (!this.f4470p) {
            this.f4470p = true;
            Executor executor = this.q;
            if (executor != null && this.r) {
                L1.b(this.k, executor);
                this.q = null;
            }
        }
    }

    public final void k(C0971d dVar) {
        boolean z3;
        if (this.u == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        F.t(z3, "already started");
        if (this.r) {
            this.q = (Executor) L1.a(this.k);
        }
        this.u = dVar;
        p();
    }

    public final l m() {
        Q q10;
        Q q11;
        Q q12;
        List list;
        boolean z3;
        String str = this.f4467i;
        l lVar = new l(15);
        try {
            lVar.g = q();
            if (!z) {
                return lVar;
            }
            List list2 = Collections.EMPTY_LIST;
            boolean z7 = false;
            if (f4465x) {
                if ("localhost".equalsIgnoreCase(str)) {
                    z7 = y;
                } else if (!str.contains(NumericEnum.SEP)) {
                    boolean z9 = true;
                    for (int i2 = 0; i2 < str.length(); i2++) {
                        char charAt = str.charAt(i2);
                        if (charAt != '.') {
                            if (charAt < '0' || charAt > '9') {
                                z3 = false;
                            } else {
                                z3 = true;
                            }
                            z9 &= z3;
                        }
                    }
                    z7 = !z9;
                }
            }
            if (z7 && this.g.get() != null) {
                throw new ClassCastException();
            }
            Object obj = null;
            if (!list2.isEmpty()) {
                Random random = this.e;
                if (f4464A == null) {
                    try {
                        f4464A = InetAddress.getLocalHost().getHostName();
                    } catch (UnknownHostException e7) {
                        throw new RuntimeException(e7);
                    }
                }
                String str2 = f4464A;
                try {
                    Iterator it = o().iterator();
                    Map map = null;
                    while (it.hasNext()) {
                        try {
                            map = n((Map) it.next(), random, str2);
                            if (map != null) {
                                break;
                            }
                        } catch (RuntimeException e8) {
                            q10 = new Q(a0.g.g("failed to pick service config choice").f(e8));
                        }
                    }
                    if (map == null) {
                        q10 = null;
                    } else {
                        q10 = new Q((Object) map);
                    }
                } catch (IOException | RuntimeException e9) {
                    q10 = new Q(a0.g.g("failed to parse TXT records").f(e9));
                }
                if (q10 != null) {
                    a0 a0Var = q10.f4279a;
                    if (a0Var != null) {
                        obj = new Q(a0Var);
                    } else {
                        Map map2 = (Map) q10.b;
                        B1 b1 = this.s;
                        b1.getClass();
                        try {
                            e eVar = b1.d;
                            eVar.getClass();
                            if (map2 != null) {
                                try {
                                    list = I1.f(I1.b(map2));
                                } catch (RuntimeException e10) {
                                    q12 = new Q(a0.g.g("can't parse load balancer configuration").f(e10));
                                }
                            } else {
                                list = null;
                            }
                            if (list == null || list.isEmpty()) {
                                q12 = null;
                            } else {
                                q12 = I1.e(list, (E) eVar.e);
                            }
                            if (q12 != null) {
                                a0 a0Var2 = q12.f4279a;
                                if (a0Var2 != null) {
                                    obj = new Q(a0Var2);
                                } else {
                                    obj = q12.b;
                                }
                            }
                            q11 = new Q((Object) N0.a(map2, b1.f4384a, b1.b, b1.f4385c, obj));
                        } catch (RuntimeException e11) {
                            q11 = new Q(a0.g.g("failed to parse service config").f(e11));
                        }
                        obj = q11;
                    }
                }
            } else {
                v.log(Level.FINE, "No TXT records found for {0}", new Object[]{str});
            }
            lVar.f = obj;
            return lVar;
        } catch (Exception e12) {
            lVar.e = a0.f4289o.g("Unable to resolve host " + str).f(e12);
            return lVar;
        }
    }

    public final void p() {
        if (!this.t && !this.f4470p) {
            if (this.f4469o) {
                long j2 = this.l;
                long j3 = 0;
                int i2 = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1));
                if (i2 != 0) {
                    if (i2 > 0) {
                        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
                        q qVar = this.n;
                        qVar.getClass();
                        if (qVar.f175a) {
                            j3 = System.nanoTime() - qVar.b;
                        }
                        if (timeUnit.convert(j3, timeUnit) <= j2) {
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }
            this.t = true;
            this.q.execute(new P1.e(this, this.u));
        }
    }

    public final List q() {
        Exception exc;
        try {
            M m4 = this.f;
            String str = this.f4467i;
            m4.getClass();
            List<InetAddress> unmodifiableList = Collections.unmodifiableList(Arrays.asList(InetAddress.getAllByName(str)));
            ArrayList arrayList = new ArrayList(unmodifiableList.size());
            for (InetAddress inetSocketAddress : unmodifiableList) {
                arrayList.add(new C0982o(new InetSocketAddress(inetSocketAddress, this.f4468j)));
            }
            return Collections.unmodifiableList(arrayList);
        } catch (Exception e7) {
            exc = e7;
            Object obj = v.f176a;
            if (!(exc instanceof RuntimeException)) {
                throw new RuntimeException(exc);
            }
            throw ((RuntimeException) exc);
        } catch (Throwable th) {
            if (exc != null) {
                v.log(Level.FINE, "Address resolution failure", exc);
            }
            throw th;
        }
    }
}
