package Pe;

import D1.f;
import Df.E;
import Gf.h;
import He.t;
import Ne.i;
import Ne.m;
import Se.b;
import Se.d;
import Te.z;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;
import ne.C1194l;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class k extends i {

    /* renamed from: h  reason: collision with root package name */
    public static final /* synthetic */ t[] f3643h;
    public m f;
    public final Gf.i g;

    static {
        w wVar = v.f4727a;
        f3643h = new t[]{wVar.f(new p(wVar.b(k.class), "customizer", "getCustomizer()Lorg/jetbrains/kotlin/builtins/jvm/JvmBuiltInsCustomizer;"))};
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [Gf.h, Gf.i] */
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public k(Gf.m mVar, h hVar) {
        super(mVar);
        j.e(hVar, "kind");
        this.g = new h(mVar, new E(8, (Object) this, (Object) mVar));
        int i2 = j.f3642a[hVar.ordinal()];
        if (i2 == 1) {
            return;
        }
        if (i2 == 2) {
            c();
        } else if (i2 == 3) {
            c();
        } else {
            throw new RuntimeException();
        }
    }

    public final q J() {
        return (q) f.y(this.g, f3643h[0]);
    }

    public final b d() {
        return J();
    }

    public final Iterable l() {
        Iterable l = super.l();
        z k = k();
        j.d(k, "getBuiltInsModule(...)");
        return C1194l.W0(l, new g(this.d, k));
    }

    public final d p() {
        return J();
    }
}
