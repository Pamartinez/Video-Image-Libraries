package kg;

import a.C0068a;
import c0.C0086a;
import ig.e;
import ig.f;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1202t;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class X implements f {

    /* renamed from: a  reason: collision with root package name */
    public final String f4684a;
    public final e b;

    public X(String str, e eVar) {
        j.e(eVar, "kind");
        this.f4684a = str;
        this.b = eVar;
    }

    public final C0068a b() {
        return this.b;
    }

    public final boolean c() {
        return false;
    }

    public final int d(String str) {
        j.e(str, "name");
        throw new IllegalStateException("Primitive descriptor does not have elements");
    }

    public final int e() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof X)) {
            return false;
        }
        X x9 = (X) obj;
        if (!j.a(this.f4684a, x9.f4684a) || !j.a(this.b, x9.b)) {
            return false;
        }
        return true;
    }

    public final String f(int i2) {
        throw new IllegalStateException("Primitive descriptor does not have elements");
    }

    public final List g(int i2) {
        throw new IllegalStateException("Primitive descriptor does not have elements");
    }

    public final List getAnnotations() {
        return C1202t.d;
    }

    public final f h(int i2) {
        throw new IllegalStateException("Primitive descriptor does not have elements");
    }

    public final int hashCode() {
        return (this.b.hashCode() * 31) + this.f4684a.hashCode();
    }

    public final String i() {
        return this.f4684a;
    }

    public final boolean isInline() {
        return false;
    }

    public final boolean j(int i2) {
        throw new IllegalStateException("Primitive descriptor does not have elements");
    }

    public final String toString() {
        return C0086a.m(new StringBuilder("PrimitiveDescriptor("), this.f4684a, ')');
    }
}
