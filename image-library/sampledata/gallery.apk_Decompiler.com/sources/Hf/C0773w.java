package Hf;

import Ae.b;
import Df.C0736b;
import Kf.g;
import L1.d;
import Ne.i;
import Qe.C0819i;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.jvm.internal.j;
import ne.C1194l;
import ne.C1202t;

/* renamed from: Hf.w  reason: case insensitive filesystem */
/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class C0773w implements M, g {

    /* renamed from: a  reason: collision with root package name */
    public C0774x f3452a;
    public final LinkedHashSet b;

    /* renamed from: c  reason: collision with root package name */
    public final int f3453c;

    public C0773w(AbstractCollection abstractCollection) {
        j.e(abstractCollection, "typesToIntersect");
        abstractCollection.isEmpty();
        LinkedHashSet linkedHashSet = new LinkedHashSet(abstractCollection);
        this.b = linkedHashSet;
        this.f3453c = linkedHashSet.hashCode();
    }

    public final B b() {
        I.e.getClass();
        return C0754c.w(I.f, this, C1202t.d, false, d.d("member scope for intersection type", this.b), new C0736b(4, this));
    }

    public final String c(b bVar) {
        j.e(bVar, "getProperTypeRelatedToStringify");
        return C1194l.R0(C1194l.g1(this.b, new C0772v(0, bVar)), " & ", "{", "}", new C0736b(3, bVar), 24);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0773w)) {
            return false;
        }
        return j.a(this.b, ((C0773w) obj).b);
    }

    public final i f() {
        i f = ((C0774x) this.b.iterator().next()).s0().f();
        j.d(f, "getBuiltIns(...)");
        return f;
    }

    public final C0819i g() {
        return null;
    }

    public final List getParameters() {
        return C1202t.d;
    }

    public final Collection h() {
        return this.b;
    }

    public final int hashCode() {
        return this.f3453c;
    }

    public final boolean i() {
        return false;
    }

    public final String toString() {
        return c(C0771u.e);
    }
}
