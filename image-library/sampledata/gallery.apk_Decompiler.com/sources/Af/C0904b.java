package af;

import B0.a;
import D1.f;
import Df.E;
import Gf.h;
import Gf.m;
import He.t;
import Hf.B;
import Hf.C0774x;
import Qe.Q;
import We.C0893e;
import bf.i;
import cf.C0922a;
import gf.C1070a;
import java.util.Map;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;
import ne.C1194l;
import ne.C1203u;
import qf.C1236c;

/* renamed from: af.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class C0904b implements i {
    public static final /* synthetic */ t[] e;

    /* renamed from: a  reason: collision with root package name */
    public final C1236c f3990a;
    public final Q b;

    /* renamed from: c  reason: collision with root package name */
    public final Gf.i f3991c;
    public final C1070a d;

    static {
        w wVar = v.f4727a;
        e = new t[]{wVar.f(new p(wVar.b(C0904b.class), "type", "getType()Lorg/jetbrains/kotlin/types/SimpleType;"))};
    }

    /* JADX WARNING: type inference failed for: r3v1, types: [Gf.h, Gf.i] */
    public C0904b(a aVar, C0893e eVar, C1236c cVar) {
        Q q;
        C1070a aVar2;
        j.e(aVar, "c");
        C0922a aVar3 = (C0922a) aVar.d;
        j.e(cVar, "fqName");
        this.f3990a = cVar;
        if (eVar != null) {
            q = aVar3.f4010j.b(eVar);
        } else {
            q = Q.f3662a;
        }
        this.b = q;
        Gf.p pVar = aVar3.f4006a;
        E e7 = new E(14, (Object) aVar, (Object) this);
        m mVar = (m) pVar;
        mVar.getClass();
        this.f3991c = new h(mVar, e7);
        if (eVar != null) {
            aVar2 = (C1070a) C1194l.M0(eVar.b());
        } else {
            aVar2 = null;
        }
        this.d = aVar2;
    }

    public Map a() {
        return C1203u.d;
    }

    public final C1236c b() {
        return this.f3990a;
    }

    public final Q getSource() {
        return this.b;
    }

    public final C0774x getType() {
        return (B) f.y(this.f3991c, e[0]);
    }
}
