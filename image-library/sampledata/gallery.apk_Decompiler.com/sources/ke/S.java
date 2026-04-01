package Ke;

import He.t;
import L1.d;
import Qe.C0814d;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;
import me.h;
import o1.C0246a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class S extends C {
    public static final /* synthetic */ t[] g;

    /* renamed from: c  reason: collision with root package name */
    public final x0 f3494c;
    public final x0 d = C0246a.d0((C0814d) null, new P(this, 0));
    public final Object e;
    public final Object f;

    static {
        w wVar = v.f4727a;
        Class<S> cls = S.class;
        g = new t[]{wVar.f(new p(wVar.b(cls), "kotlinClass", "getKotlinClass()Lorg/jetbrains/kotlin/descriptors/runtime/components/ReflectKotlinClass;")), wVar.f(new p(wVar.b(cls), "scope", "getScope()Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;")), wVar.f(new p(wVar.b(cls), "members", "getMembers()Ljava/util/Collection;"))};
    }

    public S(U u) {
        super(u);
        this.f3494c = C0246a.d0((C0814d) null, new O(u, 1));
        h hVar = h.PUBLICATION;
        this.e = d.p(hVar, new Q(this, u));
        this.f = d.p(hVar, new P(this, 1));
        C0246a.d0((C0814d) null, new Q(u, this));
    }
}
