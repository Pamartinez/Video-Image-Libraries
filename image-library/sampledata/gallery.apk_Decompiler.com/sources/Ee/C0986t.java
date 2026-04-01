package ee;

import B1.a;
import D1.f;
import E2.k;
import He.F;
import ge.C1031i0;
import java.util.Arrays;

/* renamed from: ee.t  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0986t {

    /* renamed from: a  reason: collision with root package name */
    public final String f4309a;
    public final C0985s b;

    /* renamed from: c  reason: collision with root package name */
    public final long f4310c;
    public final C0989w d;

    public C0986t(String str, C0985s sVar, long j2, C1031i0 i0Var) {
        this.f4309a = str;
        F.n(sVar, "severity");
        this.b = sVar;
        this.f4310c = j2;
        this.d = i0Var;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0986t)) {
            return false;
        }
        C0986t tVar = (C0986t) obj;
        if (!f.p(this.f4309a, tVar.f4309a) || !f.p(this.b, tVar.b) || this.f4310c != tVar.f4310c || !f.p((Object) null, (Object) null) || !f.p(this.d, tVar.d)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.f4309a, this.b, Long.valueOf(this.f4310c), null, this.d});
    }

    public final String toString() {
        k V = a.V(this);
        V.a(this.f4309a, "description");
        V.a(this.b, "severity");
        V.b("timestampNanos", this.f4310c);
        V.a((Object) null, "channelRef");
        V.a(this.d, "subchannelRef");
        return V.toString();
    }
}
