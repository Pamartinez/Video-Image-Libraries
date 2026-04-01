package ge;

import He.F;
import ee.C0984q;
import ee.P;
import ee.T;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.Collections;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class Q extends T {

    /* renamed from: a  reason: collision with root package name */
    public static final boolean f4471a;

    static {
        boolean z = false;
        try {
            Class.forName("android.app.Application", false, Q.class.getClassLoader());
            z = true;
        } catch (Exception unused) {
        }
        f4471a = z;
    }

    public final String a() {
        return "dns";
    }

    public final Set b() {
        return Collections.singleton(InetSocketAddress.class);
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [E2.q, java.lang.Object] */
    public final C0984q c(URI uri, P p6) {
        if (!"dns".equals(uri.getScheme())) {
            return null;
        }
        String path = uri.getPath();
        F.n(path, "targetPath");
        F.l(path.startsWith("/"), "the path component (%s) of the target (%s) must start with '/'", path, uri);
        String substring = path.substring(1);
        uri.getAuthority();
        return new P(substring, p6, Z.l, new Object(), f4471a);
    }
}
