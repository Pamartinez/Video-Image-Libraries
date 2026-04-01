package qf;

import Tf.n;
import Tf.v;
import c0.C0086a;
import kotlin.jvm.internal.j;

/* renamed from: qf.b  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C1235b {

    /* renamed from: a  reason: collision with root package name */
    public final C1236c f5033a;
    public final C1236c b;

    /* renamed from: c  reason: collision with root package name */
    public final boolean f5034c;

    public C1235b(C1236c cVar, C1236c cVar2, boolean z) {
        j.e(cVar, "packageFqName");
        this.f5033a = cVar;
        this.b = cVar2;
        this.f5034c = z;
        cVar2.d();
    }

    public static final String c(C1236c cVar) {
        String b5 = cVar.b();
        if (n.v0(b5, '/')) {
            return C0086a.h('`', "`", b5);
        }
        return b5;
    }

    public final C1236c a() {
        C1236c cVar = this.f5033a;
        boolean d = cVar.d();
        C1236c cVar2 = this.b;
        if (d) {
            return cVar2;
        }
        return new C1236c(cVar.b() + '.' + cVar2.b());
    }

    public final String b() {
        C1236c cVar = this.f5033a;
        boolean d = cVar.d();
        C1236c cVar2 = this.b;
        if (d) {
            return c(cVar2);
        }
        String str = v.r0(cVar.b(), '.', '/') + "/" + c(cVar2);
        j.d(str, "toString(...)");
        return str;
    }

    public final C1235b d(C1240g gVar) {
        j.e(gVar, "name");
        return new C1235b(this.f5033a, this.b.c(gVar), this.f5034c);
    }

    public final C1235b e() {
        C1236c e = this.b.e();
        if (!e.d()) {
            return new C1235b(this.f5033a, e, this.f5034c);
        }
        return null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1235b)) {
            return false;
        }
        C1235b bVar = (C1235b) obj;
        if (j.a(this.f5033a, bVar.f5033a) && j.a(this.b, bVar.b) && this.f5034c == bVar.f5034c) {
            return true;
        }
        return false;
    }

    public final C1240g f() {
        C1240g f = this.b.f();
        j.d(f, "shortName(...)");
        return f;
    }

    public final int hashCode() {
        int hashCode = this.b.hashCode();
        return Boolean.hashCode(this.f5034c) + ((hashCode + (this.f5033a.hashCode() * 31)) * 31);
    }

    public final String toString() {
        if (!this.f5033a.d()) {
            return b();
        }
        return "/" + b();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public C1235b(C1236c cVar, C1240g gVar) {
        this(cVar, C1236c.j(gVar), false);
        j.e(cVar, "packageFqName");
        j.e(gVar, "topLevelName");
    }
}
