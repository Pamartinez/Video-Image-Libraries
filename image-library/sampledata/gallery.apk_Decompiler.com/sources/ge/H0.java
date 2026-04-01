package ge;

import ee.C0984q;
import ee.P;
import ee.T;
import io.grpc.binder.a;
import java.net.URI;
import java.util.Collections;
import java.util.Set;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class H0 extends T {

    /* renamed from: a  reason: collision with root package name */
    public final a f4433a;
    public final String b;

    /* renamed from: c  reason: collision with root package name */
    public final Set f4434c = Collections.singleton(a.class);

    public H0(a aVar, String str) {
        this.f4433a = aVar;
        this.b = str;
    }

    public final String a() {
        return "directaddress";
    }

    public final Set b() {
        return this.f4434c;
    }

    public final C0984q c(URI uri, P p6) {
        return new G0(this);
    }
}
