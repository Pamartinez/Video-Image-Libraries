package Ke;

import He.o;
import He.t;
import L1.d;
import Le.g;
import Qe.C0814d;
import Qe.N;
import Te.I;
import c0.C0086a;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;
import me.h;
import o1.C0246a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class k0 extends i0 implements o {
    public static final /* synthetic */ t[] l;

    /* renamed from: j  reason: collision with root package name */
    public final x0 f3501j = C0246a.d0((C0814d) null, new j0(this, 0));
    public final Object k = d.p(h.PUBLICATION, new j0(this, 1));

    static {
        w wVar = v.f4727a;
        l = new t[]{wVar.f(new p(wVar.b(k0.class), "descriptor", "getDescriptor()Lorg/jetbrains/kotlin/descriptors/PropertyGetterDescriptor;"))};
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof k0) || !j.a(s(), ((k0) obj).s())) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [me.f, java.lang.Object] */
    public final g g() {
        return (g) this.k.getValue();
    }

    public final String getName() {
        return C0086a.m(new StringBuilder("<get-"), s().k, '>');
    }

    public final int hashCode() {
        return s().hashCode();
    }

    public final C0814d j() {
        t tVar = l[0];
        Object invoke = this.f3501j.invoke();
        j.d(invoke, "getValue(...)");
        return (I) invoke;
    }

    public final N r() {
        t tVar = l[0];
        Object invoke = this.f3501j.invoke();
        j.d(invoke, "getValue(...)");
        return (I) invoke;
    }

    public final String toString() {
        return "getter of " + s();
    }
}
