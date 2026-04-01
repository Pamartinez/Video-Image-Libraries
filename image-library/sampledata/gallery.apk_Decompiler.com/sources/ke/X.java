package Ke;

import Ae.a;
import He.l;
import He.m;
import He.t;
import Hf.C0774x;
import Qe.C0814d;
import Qe.C0831v;
import Qe.M;
import Qe.O;
import Te.Q;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.p;
import kotlin.jvm.internal.v;
import kotlin.jvm.internal.w;
import o1.C0246a;
import qf.C1240g;
import sf.C1283j;
import xf.C1353d;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class X implements m {

    /* renamed from: h  reason: collision with root package name */
    public static final /* synthetic */ t[] f3495h;
    public final C0800s d;
    public final int e;
    public final l f;
    public final x0 g;

    static {
        w wVar = v.f4727a;
        Class<X> cls = X.class;
        f3495h = new t[]{wVar.f(new p(wVar.b(cls), "descriptor", "getDescriptor()Lorg/jetbrains/kotlin/descriptors/ParameterDescriptor;")), wVar.f(new p(wVar.b(cls), "annotations", "getAnnotations()Ljava/util/List;"))};
    }

    public X(C0800s sVar, int i2, l lVar, a aVar) {
        j.e(lVar, "kind");
        this.d = sVar;
        this.e = i2;
        this.f = lVar;
        this.g = C0246a.d0((C0814d) null, aVar);
        C0246a.d0((C0814d) null, new V(this, 0));
    }

    public final M b() {
        t tVar = f3495h[0];
        Object invoke = this.g.invoke();
        j.d(invoke, "getValue(...)");
        return (M) invoke;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof X)) {
            return false;
        }
        X x9 = (X) obj;
        if (!j.a(this.d, x9.d) || this.e != x9.e) {
            return false;
        }
        return true;
    }

    public final r0 f() {
        C0774x type = b().getType();
        j.d(type, "getType(...)");
        return new r0(type, new V(this, 1));
    }

    public final boolean g() {
        Q q;
        M b = b();
        if (b instanceof Q) {
            q = (Q) b;
        } else {
            q = null;
        }
        if (q != null) {
            return C1353d.a(q);
        }
        return false;
    }

    public final String getName() {
        Q q;
        M b = b();
        if (b instanceof Q) {
            q = (Q) b;
        } else {
            q = null;
        }
        if (q != null && !q.g().Z()) {
            C1240g name = q.getName();
            j.d(name, "getName(...)");
            if (!name.e) {
                return name.b();
            }
        }
        return null;
    }

    public final boolean h() {
        M b = b();
        if (!(b instanceof Q) || ((Q) b).n == null) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Integer.hashCode(this.e) + (this.d.hashCode() * 31);
    }

    public final String toString() {
        String str;
        C1283j jVar = B0.f3485a;
        StringBuilder sb2 = new StringBuilder();
        int i2 = A0.f3484a[this.f.ordinal()];
        if (i2 == 1) {
            sb2.append("extension receiver parameter");
        } else if (i2 == 2) {
            sb2.append("instance parameter");
        } else if (i2 == 3) {
            sb2.append("parameter #" + this.e + ' ' + getName());
        } else {
            throw new RuntimeException();
        }
        sb2.append(" of ");
        C0814d j2 = this.d.j();
        if (j2 instanceof O) {
            str = B0.c((O) j2);
        } else if (j2 instanceof C0831v) {
            str = B0.b((C0831v) j2);
        } else {
            throw new IllegalStateException(("Illegal callable: " + j2).toString());
        }
        sb2.append(str);
        String sb3 = sb2.toString();
        j.d(sb3, "toString(...)");
        return sb3;
    }
}
