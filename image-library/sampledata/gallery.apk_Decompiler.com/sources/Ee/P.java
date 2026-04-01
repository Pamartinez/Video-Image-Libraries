package ee;

import B1.a;
import E2.k;
import He.F;
import ge.B1;
import ge.C1027h;
import ge.C1063w0;
import ge.D0;
import kf.C1116b;
import kotlin.jvm.internal.j;
import pf.f;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class P {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f4276a;
    public final int b;

    /* renamed from: c  reason: collision with root package name */
    public final Object f4277c;
    public final Object d;
    public final Object e;
    public final Object f;
    public final Object g;

    /* renamed from: h  reason: collision with root package name */
    public final Object f4278h;

    public P(C1116b bVar, f fVar, String[] strArr, String[] strArr2, String[] strArr3, String str, int i2) {
        this.f4276a = 1;
        j.e(bVar, "kind");
        this.f4277c = bVar;
        this.d = fVar;
        this.e = strArr;
        this.f = strArr2;
        this.g = strArr3;
        this.f4278h = str;
        this.b = i2;
    }

    public final String toString() {
        switch (this.f4276a) {
            case 0:
                k V = a.V(this);
                V.d("defaultPort", String.valueOf(this.b));
                V.a((V) this.f4277c, "proxyDetector");
                V.a((e0) this.d, "syncContext");
                V.a((B1) this.e, "serviceConfigParser");
                V.a((D0) this.f, "scheduledExecutorService");
                V.a((C1027h) this.g, "channelLogger");
                V.a((C1063w0) this.f4278h, "executor");
                V.a((Object) null, "overrideAuthority");
                return V.toString();
            default:
                return ((C1116b) this.f4277c) + " version=" + ((f) this.d);
        }
    }

    public P(V v, e0 e0Var, B1 b1, D0 d0, C1027h hVar, C1063w0 w0Var) {
        this.f4276a = 0;
        this.b = 443;
        F.n(v, "proxyDetector not set");
        this.f4277c = v;
        this.d = e0Var;
        this.e = b1;
        this.f = d0;
        this.g = hVar;
        this.f4278h = w0Var;
    }
}
