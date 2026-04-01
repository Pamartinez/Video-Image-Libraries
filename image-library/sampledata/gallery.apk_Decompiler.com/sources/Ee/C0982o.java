package ee;

import He.F;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: ee.o  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0982o {
    public static final C0968a d = new C0968a("io.grpc.EquivalentAddressGroup.ATTR_AUTHORITY_OVERRIDE");

    /* renamed from: a  reason: collision with root package name */
    public final List f4304a;
    public final C0969b b;

    /* renamed from: c  reason: collision with root package name */
    public final int f4305c;

    public C0982o(SocketAddress socketAddress) {
        C0969b bVar = C0969b.b;
        List singletonList = Collections.singletonList(socketAddress);
        F.i("addrs is empty", !singletonList.isEmpty());
        List unmodifiableList = Collections.unmodifiableList(new ArrayList(singletonList));
        this.f4304a = unmodifiableList;
        F.n(bVar, "attrs");
        this.b = bVar;
        this.f4305c = unmodifiableList.hashCode();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0982o)) {
            return false;
        }
        C0982o oVar = (C0982o) obj;
        List list = oVar.f4304a;
        List list2 = this.f4304a;
        if (list2.size() != list.size()) {
            return false;
        }
        for (int i2 = 0; i2 < list2.size(); i2++) {
            if (!((SocketAddress) list2.get(i2)).equals(list.get(i2))) {
                return false;
            }
        }
        if (!this.b.equals(oVar.b)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.f4305c;
    }

    public final String toString() {
        return "[" + this.f4304a + "/" + this.b + "]";
    }
}
