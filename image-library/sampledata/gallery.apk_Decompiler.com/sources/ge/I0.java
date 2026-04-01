package ge;

import A0.l;
import G0.c;
import ee.C0974g;
import ee.C0981n;
import ee.C0984q;
import ee.C0987u;
import ee.U;
import io.grpc.binder.a;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class I0 extends C0984q {
    public static final Logger B = Logger.getLogger(I0.class.getName());

    /* renamed from: C  reason: collision with root package name */
    public static final long f4436C = TimeUnit.MINUTES.toMillis(30);
    public static final long D = TimeUnit.SECONDS.toMillis(1);
    public static final c E = new c(12, (Object) Z.l);

    /* renamed from: F  reason: collision with root package name */
    public static final C0981n f4437F = C0981n.d;

    /* renamed from: G  reason: collision with root package name */
    public static final C0974g f4438G = C0974g.b;

    /* renamed from: H  reason: collision with root package name */
    public static final Method f4439H;

    /* renamed from: A  reason: collision with root package name */
    public final Q0 f4440A;
    public final c d;
    public final c e;
    public final ArrayList f = new ArrayList();
    public final U g = U.b();

    /* renamed from: h  reason: collision with root package name */
    public final ArrayList f4441h = new ArrayList();

    /* renamed from: i  reason: collision with root package name */
    public final String f4442i;

    /* renamed from: j  reason: collision with root package name */
    public final String f4443j = "pick_first";
    public final C0981n k = f4437F;
    public final C0974g l = f4438G;
    public long m = f4436C;
    public final int n = 5;

    /* renamed from: o  reason: collision with root package name */
    public final int f4444o = 5;

    /* renamed from: p  reason: collision with root package name */
    public final long f4445p = 16777216;
    public final long q = 1048576;
    public final boolean r = true;
    public final C0987u s = C0987u.d;
    public final boolean t = true;
    public final boolean u = true;
    public final boolean v = true;
    public final boolean w = true;

    /* renamed from: x  reason: collision with root package name */
    public final boolean f4446x = true;
    public final boolean y = true;
    public final l z;

    static {
        Method method;
        try {
            Class<?> cls = Class.forName("io.grpc.census.InternalCensusStatsAccessor");
            Class cls2 = Boolean.TYPE;
            method = cls.getDeclaredMethod("getClientInterceptor", new Class[]{cls2, cls2, cls2, cls2});
        } catch (ClassNotFoundException e7) {
            B.log(Level.FINE, "Unable to apply census stats", e7);
            method = null;
            f4439H = method;
        } catch (NoSuchMethodException e8) {
            B.log(Level.FINE, "Unable to apply census stats", e8);
            method = null;
            f4439H = method;
        }
        f4439H = method;
    }

    public I0(a aVar, String str, l lVar) {
        c cVar = E;
        this.d = cVar;
        this.e = cVar;
        try {
            this.f4442i = new URI("directaddress", "", "/" + aVar, (String) null).toString();
            this.z = lVar;
            U u3 = new U();
            H0 h0 = new H0(aVar, str);
            synchronized (u3) {
                u3.a(h0);
                u3.c();
            }
            this.g = u3;
            this.f4440A = new Q0(10);
        } catch (URISyntaxException e7) {
            throw new RuntimeException(e7);
        }
    }
}
