package Ke;

import He.h;
import He.t;
import L1.d;
import Le.g;
import Qe.C0814d;
import Qe.N;
import Te.J;
import c0.C0086a;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;
import o1.C0246a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class m0 extends i0 implements h {
    public static final /* synthetic */ t[] l;

    /* renamed from: j  reason: collision with root package name */
    public final x0 f3502j = C0246a.d0((C0814d) null, new l0(this, 0));
    public final Object k = d.p(me.h.PUBLICATION, new l0(this, 1));

    static {
        w wVar = v.f4727a;
        l = new t[]{wVar.f(new p(wVar.b(m0.class), "descriptor", "getDescriptor()Lorg/jetbrains/kotlin/descriptors/PropertySetterDescriptor;"))};
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof m0) || !j.a(s(), ((m0) obj).s())) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [me.f, java.lang.Object] */
    public final g g() {
        return (g) this.k.getValue();
    }

    public final String getName() {
        return C0086a.m(new StringBuilder("<set-"), s().k, '>');
    }

    public final int hashCode() {
        return s().hashCode();
    }

    public final C0814d j() {
        t tVar = l[0];
        Object invoke = this.f3502j.invoke();
        j.d(invoke, "getValue(...)");
        return (J) invoke;
    }

    public final N r() {
        t tVar = l[0];
        Object invoke = this.f3502j.invoke();
        j.d(invoke, "getValue(...)");
        return (J) invoke;
    }

    public final String toString() {
        return "setter of " + s();
    }
}
