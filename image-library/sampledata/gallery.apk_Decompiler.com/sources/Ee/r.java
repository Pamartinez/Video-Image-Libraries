package ee;

import B1.a;
import D1.f;
import E2.k;
import He.F;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Arrays;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class r extends SocketAddress {

    /* renamed from: h  reason: collision with root package name */
    public static final /* synthetic */ int f4308h = 0;
    public final SocketAddress d;
    public final InetSocketAddress e;
    public final String f;
    public final String g;

    public r(InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, String str, String str2) {
        F.n(inetSocketAddress, "proxyAddress");
        F.n(inetSocketAddress2, "targetAddress");
        F.q("The proxy address %s is not resolved", inetSocketAddress, !inetSocketAddress.isUnresolved());
        this.d = inetSocketAddress;
        this.e = inetSocketAddress2;
        this.f = str;
        this.g = str2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof r)) {
            return false;
        }
        r rVar = (r) obj;
        if (!f.p(this.d, rVar.d) || !f.p(this.e, rVar.e) || !f.p(this.f, rVar.f) || !f.p(this.g, rVar.g)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.d, this.e, this.f, this.g});
    }

    public final String toString() {
        boolean z;
        k V = a.V(this);
        V.a(this.d, "proxyAddr");
        V.a(this.e, "targetAddr");
        V.a(this.f, "username");
        if (this.g != null) {
            z = true;
        } else {
            z = false;
        }
        V.c("hasPassword", z);
        return V.toString();
    }
}
