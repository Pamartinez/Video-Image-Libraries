package v1;

import ge.W0;
import java.util.Arrays;
import u1.C0284b;
import w1.r;

/* renamed from: v1.a  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0296a {

    /* renamed from: a  reason: collision with root package name */
    public final int f1953a;
    public final W0 b;

    /* renamed from: c  reason: collision with root package name */
    public final C0284b f1954c;
    public final String d;

    public C0296a(W0 w02, C0284b bVar, String str) {
        this.b = w02;
        this.f1954c = bVar;
        this.d = str;
        this.f1953a = Arrays.hashCode(new Object[]{w02, bVar, str});
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0296a)) {
            return false;
        }
        C0296a aVar = (C0296a) obj;
        if (!r.d(this.b, aVar.b) || !r.d(this.f1954c, aVar.f1954c) || !r.d(this.d, aVar.d)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.f1953a;
    }
}
