package ge;

import E2.f;
import He.F;
import com.google.common.util.concurrent.E;
import ee.G;
import ee.I;
import ee.K;
import ee.M;
import ee.Y;
import ee.a0;
import io.grpc.CallOptions;
import io.grpc.a;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class Z {

    /* renamed from: a  reason: collision with root package name */
    public static final Logger f4489a = Logger.getLogger(Z.class.getName());
    public static final Set b = Collections.unmodifiableSet(EnumSet.of(Y.OK, new Y[]{Y.INVALID_ARGUMENT, Y.NOT_FOUND, Y.ALREADY_EXISTS, Y.FAILED_PRECONDITION, Y.ABORTED, Y.OUT_OF_RANGE, Y.DATA_LOSS}));

    /* renamed from: c  reason: collision with root package name */
    public static final G f4490c = new G("grpc-timeout", new Q0(8));
    public static final G d;
    public static final K e;
    public static final G f;
    public static final K g;

    /* renamed from: h  reason: collision with root package name */
    public static final G f4491h;

    /* renamed from: i  reason: collision with root package name */
    public static final C1017d1 f4492i = new C1017d1();

    /* renamed from: j  reason: collision with root package name */
    public static final CallOptions.Key f4493j = new CallOptions.Key("io.grpc.internal.CALL_OPTIONS_RPC_OWNED_BY_BALANCER");
    public static final X k = new Object();
    public static final Q0 l = new Q0(5);
    public static final Q0 m = new Q0(6);
    public static final Y n = new Y(0);

    /* JADX WARNING: type inference failed for: r0v13, types: [ge.X, java.lang.Object] */
    static {
        boolean z;
        Charset.forName("US-ASCII");
        ee.Z z3 = M.d;
        d = new G("grpc-encoding", z3);
        Q0 q0 = new Q0(7);
        boolean z7 = true;
        if ("grpc-accept-encoding".charAt(0) == ':') {
            z = true;
        } else {
            z = false;
        }
        BitSet bitSet = I.d;
        e = new K("grpc-accept-encoding", z, q0);
        f = new G("content-encoding", z3);
        Q0 q02 = new Q0(7);
        if ("accept-encoding".charAt(0) != ':') {
            z7 = false;
        }
        g = new K("accept-encoding", z7, q02);
        f4491h = new G("content-length", z3);
        I.a("content-type", z3);
        I.a("te", z3);
        I.a("user-agent", z3);
        f.f.getClass();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        timeUnit.toNanos(20);
        TimeUnit.HOURS.toNanos(2);
        timeUnit.toNanos(20);
    }

    public static a[] a(CallOptions callOptions, int i2, boolean z) {
        List list = callOptions.f;
        int size = list.size();
        a[] aVarArr = new a[(size + 1)];
        CallOptions callOptions2 = CallOptions.f4611j;
        for (int i7 = 0; i7 < list.size(); i7++) {
            aVarArr[i7] = ((n1) list.get(i7)).f4535a;
        }
        aVarArr[size] = k;
        return aVarArr;
    }

    public static String b(InetSocketAddress inetSocketAddress) {
        try {
            return (String) InetSocketAddress.class.getMethod("getHostString", (Class[]) null).invoke(inetSocketAddress, (Object[]) null);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return inetSocketAddress.getHostName();
        }
    }

    public static E c(String str) {
        Boolean bool = Boolean.TRUE;
        String.format(Locale.ROOT, str, new Object[]{0});
        return new E(Executors.defaultThreadFactory(), str, new AtomicLong(0), bool);
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x0023 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static ge.C1058u d(ee.C0964A r6, boolean r7) {
        /*
            ge.E0 r0 = r6.f4266a
            ee.a0 r1 = r6.b
            r2 = 0
            if (r0 == 0) goto L_0x0020
            boolean r3 = r0.g
            java.lang.String r4 = "Subchannel is not started"
            He.F.t(r3, r4)
            ge.i0 r0 = r0.f
            ge.e0 r3 = r0.v
            if (r3 == 0) goto L_0x0015
            goto L_0x0021
        L_0x0015:
            ee.e0 r3 = r0.k
            ge.c0 r4 = new ge.c0
            r5 = 1
            r4.<init>(r0, r5)
            r3.execute(r4)
        L_0x0020:
            r3 = r2
        L_0x0021:
            if (r3 == 0) goto L_0x0024
            return r3
        L_0x0024:
            boolean r0 = r1.e()
            if (r0 != 0) goto L_0x0048
            boolean r6 = r6.f4267c
            if (r6 == 0) goto L_0x003a
            ge.U r6 = new ge.U
            ee.a0 r7 = e(r1)
            ge.s r0 = ge.C1054s.DROPPED
            r6.<init>(r7, r0)
            return r6
        L_0x003a:
            if (r7 != 0) goto L_0x0048
            ge.U r6 = new ge.U
            ee.a0 r7 = e(r1)
            ge.s r0 = ge.C1054s.PROCESSED
            r6.<init>(r7, r0)
            return r6
        L_0x0048:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: ge.Z.d(ee.A, boolean):ge.u");
    }

    public static a0 e(a0 a0Var) {
        boolean z;
        if (a0Var != null) {
            z = true;
        } else {
            z = false;
        }
        F.j(z);
        if (!b.contains(a0Var.f4290a)) {
            return a0Var;
        }
        a0 a0Var2 = a0.n;
        return a0Var2.g("Inappropriate status code from control plane: " + a0Var.f4290a + " " + a0Var.b).f(a0Var.f4291c);
    }
}
